/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.struts.action;

import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.webapp.dynasite.dao.CommentDAO;
import org.cidrz.webapp.dynasite.dao.OutcomeDAO;
import org.cidrz.webapp.dynasite.dao.ProblemDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.rules.Outcome;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.BasePatientAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.valueobject.Comment;
import org.cidrz.webapp.dynasite.valueobject.Problem;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;
import org.cidrz.webapp.dynasite.valueobject.Task;

/**
 * Combines lists of problems and outcomes, then presents a list of comments, if available.
 *
 * @author Chris Kelley
 */
public final class ProblemAction extends BasePatientAction {

    private Boolean status;

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(ProblemAction.class);

    /**
     * Create a list of problems, from problems and systme-generated outcomes.
     * If problemId is sent in request, the return the comments for that Id; otherwise, sned most recent comments.
     *
     * @param mapping  The ActionMapping used to select this instance
     * @param form     The optional ActionForm bean for this request (if any)
     * @param request  The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return Action to forward to
     * @throws Exception if an input/output error or servlet exception occurs
     */

    protected ActionForward doExecute(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
            throws Exception {

        // Extract attributes we will need
        HttpSession session = request.getSession();
        Principal user = request.getUserPrincipal();
        String username = user.getName();
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            SessionPatient sessionPatient = null;
            try {
                sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
            } catch (SessionUtil.AttributeNotFoundException e) {
                log.error("Unable to get SessionPatient");
            }

            Long patientId = null;
            Long pregnancyId = null;
            try {
                assert sessionPatient != null;
                patientId = sessionPatient.getId();
                pregnancyId = sessionPatient.getCurrentPregnancyId();
            } catch (Exception e) {
                log.error("Unable to get SessionPatient field" + e);
            }

            Boolean status = null;

            // Merge the problemList, outcomes, and tasklist into one List. Get both active and inactive problems and outcomes,
            // and incomplete tasks. First get active. Add incomplete tasks to active.
            // commentproblems is used later when getting comments. Only problems and outcomes are in commentproblems.
            // Tasks do not have comments.

            status = Boolean.valueOf(true);

            List activeProblems = null;
            try {
                activeProblems = PatientRecordUtils.assembleProblemList(conn, patientId, pregnancyId, status);
            } catch (Exception e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            }

            // long-term problems
            if (status == true) {
                List longTermProblems = OutcomeDAO.getLongTermProblems(conn, patientId);
                List longTermProblemsClassed = new ArrayList();
                if (longTermProblems != null) {
                    try {
                        PatientRecordUtils.classOutcomes(longTermProblems, longTermProblemsClassed);
                        if (longTermProblems != null && longTermProblems.size() > 0) {
                            Task ltp = new Task();
                            ltp.setLabel("Long-term Problems");
                            ltp.setMessageType("longterm");
                            activeProblems.add(ltp);
                            activeProblems.addAll(longTermProblems);
                        }
                        activeProblems.addAll(longTermProblemsClassed);
                    } catch (IllegalAccessException e) {
                        log.error(e);
                    } catch (InvocationTargetException e) {
                        log.error(e);
                    }
                }
            }
            // We'll use this simple list of commentProblems later...
            List commentProblems = (List) ((ArrayList) activeProblems).clone();

            // get the tasks and put them into activeProblems
            // but first check if this patient has a parent. if true, then you don't need to list missing forms.
            // forms(tasks) are only required for the mother.
            // also, you are viewing previous pregnancy, also do not need these tasks.

            /* if (status.equals(Boolean.TRUE)) {
                if ((sessionPatient.getParentId() == null) && (sessionPatient.getDatePregnancyEnd() == null)) {
                    TaskList tasklist = new TaskList();
                    List tasks = null;
                    tasks = tasklist.getProblemListTasks(sessionPatient);
                    Iterator alltasks = tasks.iterator();
                    while (alltasks.hasNext()) {
                        Task thisTask = (Task) alltasks.next();
                        if (thisTask.isActive()) {
                            if (thisTask.getLabel() != null) {
                                activeProblems.add(thisTask);
                            }
                        }
                    }
                }
            }*/

            request.setAttribute("activeProblems", activeProblems);
            // now get inactive problems
            status = Boolean.valueOf(false);

            List inactiveProblems = PatientRecordUtils.assembleProblemList(conn, patientId, pregnancyId, status);
            request.setAttribute("inactiveProblems", inactiveProblems);

            // get the problem and its comments if problemId was passed in the request
            if (request.getParameter("problemId") != null) {
                if (request.getParameter("problemType").equals("problem")) {
                    Long problemId = Long.decode(request.getParameter("problemId"));
                    Problem problem = null;
                    try {
                        problem = ProblemDAO.getOne(conn, problemId);
                    } catch (SQLException e) {
                        log.error(e);
                        request.setAttribute("exception", e);
                        return mapping.findForward("error");
                    } catch (ServletException e) {
                        log.error(e);
                        request.setAttribute("exception", e);
                        return mapping.findForward("error");
                    } catch (ObjectNotFoundException e) {
                        // it's ok
                    }
                    List comments = null;

                    try {
                        comments = CommentDAO.getAllforProblemFullname(conn, patientId, problem.getId());
                    } catch (SQLException e) {
                        log.error(e);
                        request.setAttribute("exception", e);
                        return mapping.findForward("error");
                    } catch (ServletException e) {
                        log.error(e);
                        request.setAttribute("exception", e);
                        return mapping.findForward("error");
                    }

                    request.setAttribute("problemId", request.getParameter("problemId"));
                    request.setAttribute("problemType", request.getParameter("problemType"));
                    request.setAttribute("comments", comments);
                    request.setAttribute("editStatus", problem.isActive());
                    return mapping.findForward("success");
                } else {
                    Long problemId = Long.decode(request.getParameter("problemId"));
                    Outcome outcome = null;
                    try {
                        outcome = OutcomeDAO.getOne(conn, problemId);
                    } catch (SQLException e) {
                        log.error(e);
                        request.setAttribute("exception", e);
                        return mapping.findForward("error");
                    } catch (ServletException e) {
                        log.error(e);
                        request.setAttribute("exception", e);
                        return mapping.findForward("error");
                    } catch (ObjectNotFoundException e) {
                        // it's ok
                    }
                    List comments = null;
                    try {
                        comments = CommentDAO.getAllforOutcome(conn, outcome.getId());
                    } catch (SQLException e) {
                        log.error(e);
                        request.setAttribute("exception", e);
                        return mapping.findForward("error");
                    } catch (ServletException e) {
                        log.error(e);
                        request.setAttribute("exception", e);
                        return mapping.findForward("error");
                    }
                    request.setAttribute("problemId", request.getParameter("problemId"));
                    request.setAttribute("outcome", "1");
                    Comment comment = new Comment();
                    comment.setId(new Long(0));
                    comment.setAuditInfo(outcome.getAuditInfo());
                    comment.setCommentText("System-generated Outcome");
                    comment.setOutcome(outcome);
                    comments.add(comment);
                    request.setAttribute("comments", comments);
                    request.setAttribute("problemType", request.getParameter("problemType"));
                    request.setAttribute("editStatus", outcome.isActive());
                    request.setAttribute("longTermProblem", outcome.getLongTermProblem());
                    return mapping.findForward("success");
                }
            }

            // see if there are active problems and return comments. if no active problems, then return comments for inactive
            // problems. otherwise empty set.
            // use commentProblems rather than activeProblems, because tasks do not have comments.
            /* try {*/
            List comments = null;
            /*Comment comment = null;*/
            if (commentProblems.size() != 0) {
                status = Boolean.valueOf(true);
                try {
                    getComments(conn, commentProblems, patientId, request, status);
                } catch (SQLException e) {
                    log.error(e);
                    request.setAttribute("exception", e);
                    return mapping.findForward("error");
                } catch (ServletException e) {
                    log.error(e);
                    request.setAttribute("exception", e);
                    return mapping.findForward("error");
                } catch (ObjectNotFoundException e) {
                    // it's ok
                    // send an empty list
                    comments = new ArrayList();
                    request.setAttribute("comments", comments);
                }
            } else if (inactiveProblems.size() != 0) {
                status = Boolean.valueOf(false);
                getComments(conn, inactiveProblems, patientId, request, status);
            } else {
                // send an empty list
                comments = new ArrayList();
                request.setAttribute("comments", comments);
            }

            request.setAttribute("patientId", patientId);

        } catch (ServletException e) {
            log.error(e);
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        return mapping.findForward("success");
    }

    /**
     * Check if the most recent comment was a problem or outcome.
     * the fetch the comments and add them to the request.
     *
     * @param conn
     * @param problems
     * @param patientId
     * @param request
     * @param status
     * @throws SQLException
     * @throws ServletException
     */
    public static void getComments(Connection conn, List problems, Long patientId, HttpServletRequest request, Boolean status) throws SQLException, ServletException, ObjectNotFoundException {
        Object mostRecent = problems.get(0);
        // is it a problem? then get the most reent comment
        Comment comment = null;
        boolean addComment = false;
        try {
            comment = (Comment) CommentDAO.getMostRecent(conn, patientId, status);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            // it's ok
        }

        if (mostRecent.getClass().getName().equals("org.cidrz.webapp.dynasite.valueobject.Problem")) {
            // status = Boolean.valueOf(true);
            request.setAttribute("problemType", "problem");
            // it must be an outcome then.
        } else if (mostRecent.getClass().getName().equals(" org.cidrz.webapp.dynasite.valueobject.Task")) {
        	// log.debug("Task is most recent problem for patientId: " + patientId + " ");
        } else {
            Outcome mostRecentoutcome = null;
            try {
                mostRecentoutcome = (Outcome) mostRecent;
                //now compare it to the most recent comment - if it's more recent than the outcome, make sure it is
                // sent over as a comment.
                //comment = (Comment) CommentDAO.getOne(patientId, status);
                if (comment != null) {
                    if (mostRecentoutcome.getLastModified().getTime() > comment.getLastModified().getTime()) {
                        // tell the system to do something special - we won't display the mostRecentComments.
                        request.setAttribute("outcome", "1");
                        request.setAttribute("problemType", "outcome");
                        request.setAttribute("problemId", mostRecentoutcome.getId());

                        //let's make a comment - the initial outcome has no comments.
                        comment = new Comment();
                        comment.setId(new Long(0));
                        comment.setId(new Long(0));
                        comment.setAuditInfo(mostRecentoutcome.getAuditInfo());
                        comment.setCommentText("System-generated Outcome");
                        comment.setOutcome(mostRecentoutcome);
                        comment.setOutcomeId(mostRecentoutcome.getId());
                        request.setAttribute("outcome", "1");
                        request.setAttribute("problemType", "outcome");
                        addComment = true;
                    }
                } else {
                    //let's make a comment - the initial outcome has no comments.
                    comment = new Comment();
                    comment.setId(new Long(0));
                    comment.setId(new Long(0));
                    comment.setAuditInfo(mostRecentoutcome.getAuditInfo());
                    comment.setCommentText("System-generated Outcome");
                    comment.setOutcome(mostRecentoutcome);
                    comment.setOutcomeId(mostRecentoutcome.getId());
                    request.setAttribute("outcome", "1");
                    request.setAttribute("problemType", "outcome");
                    addComment = true;
                }
            } catch (ClassCastException e) {
                log.error(e);
            }
        }
        // now fetch the comments.
        List comments = null;
        // It would be unusual for comment to be null, but let's be safe...
        if (comment != null) {
            if (comment.getProblemId() != null) {
                // this is a problem
                try {
                    comments = CommentDAO.getAllforProblemFullname(conn, patientId, comment.getProblemId());
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ServletException e) {
                    e.printStackTrace();
                }
                request.setAttribute("problemId", comment.getProblemId());
            } else {
                // it's an outcome
                try {
                    comments = CommentDAO.getAllforOutcome(conn, comment.getOutcomeId());
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ServletException e) {
                    e.printStackTrace();
                }
                if (addComment == true) {
                    comments.add(comment);
                }
                request.setAttribute("problemId", comment.getOutcomeId());
            }
        }
        if (comments == null) {
            comments = new ArrayList();
        }
        request.setAttribute("comments", comments);
    }
}