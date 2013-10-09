/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.remote;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.valueobject.gen.UltrasoundFetusEval;
import org.cidrz.webapp.dynasite.dao.FormDAO;
import org.cidrz.webapp.dynasite.dao.UltrasoundDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.exception.PopulationException;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.DateUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.FieldEnumeration;
import org.cidrz.webapp.dynasite.valueobject.Form;
import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;

import javax.servlet.ServletException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Oct 10, 2005
 * Time: 11:2:43 PMA
 * used by dwr remote process to add fetuses to ultrasound eval form
 */
public class Fetus {

    /**
     * Commons Logging instance.
     */
    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    /**
     * @param conditionValue
     * @param lieValue
     * @param presentationValue
     * @param presentationOther
     * @param sequence
     * @param patientId
     * @return
     * @throws org.cidrz.webapp.dynasite.exception.PersistenceException
     *
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException
     *
     * @throws org.cidrz.webapp.dynasite.session.SessionUtil.AttributeNotFoundException
     *
     */
    public String createFetus(String examSequence, String conditionValue, String lieValue, String presentationValue, String presentationOther, Float bpd, Integer femur, Integer circumference, Float weight, String sequence, String patientId, String user, String siteId, String pregnancyId) throws PersistenceException, ObjectNotFoundException, SessionUtil.AttributeNotFoundException, PopulationException, SQLException, ServletException {
        WebContext exec = WebContextFactory.get();
        String username = null;
        try {
            username = exec.getHttpServletRequest().getUserPrincipal().getName();
        } catch (NullPointerException e) {
            // unit testing - it's ok...
            username = "demo";
        }
        Connection conn = DatabaseUtils.getZEPRSConnection(username);
        //WebContext exec = WebContextFactory.get();
        //HttpSession session = exec.getSession();
        HashMap fields = new HashMap();
        Date now = DateUtils.getNow();
        Long motherId = new Long(patientId);

        fields.put("field1", now.toString());
        fields.put("field1916", examSequence);
        fields.put("field1915", sequence);
        fields.put("field964", conditionValue);
        fields.put("field313", lieValue);
        fields.put("field314", presentationValue);
        fields.put("field1508", presentationOther);
        fields.put("field955", bpd);
        fields.put("field956", femur);
        fields.put("field957", circumference);
        fields.put("field1947", weight);

        if (sequence.equals("")) {
            return "Error: Empty field submitted. Please enter a Sequence Number.";
        }

        // create an instance of UltrasoundFetusEval for fetus
        UltrasoundFetusEval fetusEval = new UltrasoundFetusEval();
        fetusEval.setDateVisit(now);
        Long flowId = new Long("100");
        Long formId = new Long("93");
        Long pregnancyIdL = new Long(pregnancyId);
        fetusEval.setFlowId(flowId);
        fetusEval.setFormId(formId);
        fetusEval.setPatientId(motherId);
        fetusEval.setPregnancyId(pregnancyIdL);
        try {
            BeanUtils.copyProperties(fetusEval, fields);
        } catch (IllegalAccessException e) {
            log.error(e);
        } catch (InvocationTargetException e) {
            log.error(e);
        }

        Form fetusEvalForm = (Form) DynaSiteObjects.getForms().get(formId);
        try {
            FormDAO.create(conn, fetusEval, user, new Long(siteId), fetusEvalForm, flowId, null);
        } catch (Exception e) {
            log.error(e);
        }
        List fetuses = null;
        try {
            fetuses = UltrasoundDAO.getExams(conn, Integer.valueOf(examSequence), motherId, pregnancyIdL, formId, UltrasoundFetusEval.class);
        } catch (IOException e) {
            log.error(e);
        } catch (ServletException e) {
            log.error(e);
        } catch (SQLException e) {
            log.error(e);
        }

        String sbuff = createResponse(examSequence, motherId, pregnancyIdL);
        try {
            if (!conn.isClosed()) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sbuff;
    }

    public String createResponse(String examSequence, Long motherId, Long pregnancyId) {
        WebContext exec = WebContextFactory.get();
        String userName = null;
        StringBuffer sbuff = null;
        try {
            userName = exec.getHttpServletRequest().getUserPrincipal().getName();
        } catch (NullPointerException e) {
            // unit testing - it's ok...
            userName = "demo";
        }
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(userName);
            Long formId = new Long("93");
            List fetuses = null;
            try {
                fetuses = UltrasoundDAO.getExams(conn, Integer.valueOf(examSequence), motherId, pregnancyId, formId, UltrasoundFetusEval.class);
            } catch (IOException e) {
                log.error(e);
            } catch (ServletException e) {
                log.error(e);
            } catch (SQLException e) {
                log.error(e);
            }
            sbuff = new StringBuffer();
            String tdb = "TBD";
            if (fetuses != null && fetuses.size() > 0) {
                sbuff.append(fetuses.size() + "|<p><strong>" + String.valueOf(fetuses.size()) + " fetus:</strong></p>\n<table width=\"60%\" class=\"enhancedtable\">\n");
                sbuff.append("<tr><th>#</th><th>Condition</th><th>Lie</th><th>Presentation</th><th>BPD</th><th>Femur</th><th>Abdom.</th><th>Weight</th><th>Del.</th></tr>\n");
            } else {
                sbuff.append(fetuses.size() + "|<table width=\"60%\" class=\"enhancedtableMargin\">");
            }
            for (Iterator iterator = fetuses.iterator(); iterator.hasNext();) {
                UltrasoundFetusEval fetusEval = (UltrasoundFetusEval) iterator.next();
                Integer seqNumber = fetusEval.getField1915();
                FieldEnumeration fEnum = null;
                String condition = null;
                String lie = null;
                String presentation = null;
                if (fetusEval.getField964() != null && fetusEval.getField964() > 0) {
                    fEnum = (FieldEnumeration) DynaSiteObjects.getFieldEnumerations().get((long) fetusEval.getField964());
                    condition = fEnum.getEnumeration();
                }
                if (fetusEval.getField313() != null && fetusEval.getField313() > 0) {
                    fEnum = (FieldEnumeration) DynaSiteObjects.getFieldEnumerations().get((long) fetusEval.getField313());
                    lie = fEnum.getEnumeration();
                }
                if (fetusEval.getField314() != null && fetusEval.getField314() > 0) {
                    fEnum = (FieldEnumeration) DynaSiteObjects.getFieldEnumerations().get((long) fetusEval.getField314());
                    presentation = fEnum.getEnumeration();
                    if (!fetusEval.getField1508().equals("")) {
                        presentation = presentation + ": " + fetusEval.getField1508();
                    }
                }

                sbuff.append("<tr><td>" + seqNumber + "</td><td>" + condition + "</td>" +
                        "<td>" + lie + "</td><td>" + presentation + "</td>" +
                        "<td>" + fetusEval.getField955() + "</td>" +
                        "<td>" + fetusEval.getField956() + "</td>" +
                        "<td>" + fetusEval.getField957() + "</td>" +
                        "<td>" + fetusEval.getField1947() + "</td>" +
                        "<td><a href=\"#\" onClick=\"deleteFetus(" + examSequence + "," + fetusEval.getId() + "," + motherId + "," + pregnancyId + ")\">X</a></td></tr>\n");
            }
            sbuff.append("</table>\n");
        } catch (ServletException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return sbuff.toString();
    }

    public String deleteFetus(String examSequence, String encounterId, Long parent_id, String pregnancyId) throws Exception {
        WebContext exec = WebContextFactory.get();
        String userName = null;
        String sbuff = null;
        try {
            userName = exec.getHttpServletRequest().getUserPrincipal().getName();
        } catch (NullPointerException e) {
            // unit testing - it's ok...
            userName = "demo";
        }
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(userName);
            Long pregnancyIdL = new Long(pregnancyId);
            FormDAO.deleteFetusEval(conn, Long.valueOf(encounterId));
            Long formId = new Long("93");
            List fetuses = null;
            try {
                fetuses = UltrasoundDAO.getExams(conn, Integer.valueOf(examSequence), parent_id, pregnancyIdL, formId, UltrasoundFetusEval.class);
            } catch (IOException e) {
                log.error(e);
            } catch (ServletException e) {
                log.error(e);
            } catch (SQLException e) {
                log.error(e);
            }
            sbuff = createResponse(examSequence, parent_id, pregnancyIdL);
        } catch (ServletException e) {
            log.error(e);
        } finally {
            if (!conn.isClosed()) {
                conn.close();
                conn = null;
            }
        }
        return sbuff;
    }

}
