/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.struts.action.generic;

import java.io.IOException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.cidrz.webapp.dynasite.dao.CommentDAO;
import org.cidrz.webapp.dynasite.dao.OutcomeDAO;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.dao.PatientStatusDAO;
import org.cidrz.webapp.dynasite.dao.PregnancyDAO;
import org.cidrz.webapp.dynasite.dao.ProblemDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.exception.PopulationException;
import org.cidrz.webapp.dynasite.logic.PersistenceManagerFactory;
import org.cidrz.webapp.dynasite.rules.Outcome;
import org.cidrz.webapp.dynasite.rules.impl.EncounterOutcome;
import org.cidrz.webapp.dynasite.rules.impl.InfoOutcome;
import org.cidrz.webapp.dynasite.rules.impl.OutcomeImpl;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.utils.BeanPopulator;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.struts.StrutsUtils;
import org.cidrz.webapp.dynasite.valueobject.Comment;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.valueobject.Pregnancy;
import org.cidrz.webapp.dynasite.valueobject.Problem;
import org.cidrz.webapp.dynasite.valueobject.Site;

/**
 * Created by IntelliJ IDEA.
 * User: dave
 * Date: Mar 17, 2004
 * Time: 9:49:43 AM
 * To change this template use File | Settings | File Templates.
 */

public class SaveObjectAction extends PersistentObjectAction {
    /**
     * Commons Logging instance.
     */

    protected Log log = LogFactory.getFactory().getInstance(this.getClass().getName());


    protected final ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Class clazz = getMappedClass(mapping);
        DynaActionForm dynaForm = (DynaActionForm) form;
        Identifiable subject = null;
        Long id;

        try {
            id = (Long) dynaForm.get("id");
        } catch (IllegalArgumentException e) {
            id = Long.valueOf(request.getParameter("id"));
        }

        if (request.getParameter("preview") != null) {
        	request.setAttribute("id", id);
        	return mapping.findForward("admin/form/preview");
        	/*if (form instanceof DynaBean &&
                    ((DynaBean)form).getDynaClass() instanceof MutableDynaClass) {
                    DynaBean         dynaBean  = (DynaBean)form;
                    MutableDynaClass dynaClass = (MutableDynaClass)dynaBean.getDynaClass();

                    // Add properties
                    dynaClass.setRestricted(false);
                    ModuleConfigFactory factoryObject =
                    	ModuleConfigFactory.createFactory();

                    ModuleConfig config =
                    	factoryObject.createModuleConfig("");
                  //find the form bean configuration for the form bean to include

                    FormBeanConfig includeConfig =
              config.findFormBeanConfig(formBeanToInclude);
                    FormPropertyConfig props[] = findFormPropertyConfigs();
                    for (int i = 0; i < props.length; i++) {
                        dynaClass.add(props[i].getName(), props[i].getTypeClass());
                        dynaBean.set(props[i].getName(), props[i].initial());
                    }
                    dynaClass.setRestricted(isRestricted());

                }*/

        	/*// Support for module-wide ActionMapping type override
            ModuleConfigFactory factoryObject = ModuleConfigFactory.createFactory();
            String prefix = "";
            String paths = "/WEB-INF/foo.xml";
            ModuleConfig config = factoryObject.createModuleConfig(prefix);
            String mappingStr = this.getServlet().getInitParameter("mapping")
            if (mapping != null)
            {
                config.setActionMappingClass(mappingStr);
            }

            // Configure the Digester instance we will use
            //Digester digester = this.getServlet().getServletConfi
            	//initConfigDigester();

            // Process each specified resource path
            while (paths.length() > 0)
            {
                digester.push(config);
                String path = null;
                int comma = paths.indexOf(',');
                if (comma >= 0)
                {
                    path = paths.substring(0, comma).trim();
                    paths = paths.substring(comma + 1);
                } else
                {
                    path = paths.trim();
                    paths = "";
                }

                if (path.length() < 1)
                {
                    break;
                }

                this.parseModuleConfigFile(prefix, paths, config, digester, path);
            }*/

            // Force creation and registration of DynaActionFormClass instances
            // for all dynamic form beans we wil be using

            //FormBeanConfig fbs[] = config.findFormBeanConfigs();
        	/*Long formId = (Long) dynaForm.get("id");
        	String formName = "form" + formId.toString();
        	FormBeanConfig config = mapping.getModuleConfig().findFormBeanConfig(formName);
        	config.setRestricted(false);
            //MutableDynaClass dynaClass = (MutableDynaClass)dynaBean.getDynaClass();
            DynaActionFormClass dynaClass = (DynaActionFormClass)dynaForm.getDynaClass();


        	FormPropertyConfig props[] = config.findFormPropertyConfigs();
            System.out.println("**The number of props for the bean  is " + props.length);
            HashMap fieldMap = new HashMap();
            for (FormPropertyConfig formPropertyConfig : props) {
				System.out.println("item: " + formPropertyConfig.getName());
				fieldMap.put(formPropertyConfig.getName(), formPropertyConfig.getType());
			}

            //dynaClass.createDynaActionFormClass(config)
            //dynaClass.setRestricted(false);
            //dynaClass.add(newFormPropertyConfig.getName(), newFormPropertyConfig.getTypeClass());
            //dynaForm.set(newFormPropertyConfig.getName(), newFormPropertyConfig.initial());
            for (int i = 0; i < props.length; i++) {
                dynaClass.add(props[i].getName(), props[i].getTypeClass());
                dynaForm.set(props[i].getName(), props[i].initial());
            }
           // dynaClass.setRestricted(true);


            List drugs = DynaSiteObjects.getDrugs();
            request.setAttribute("drugs", drugs);
            List sites = DynaSiteObjects.getClinics();
            request.setAttribute("sites", sites);
            Connection conn = DatabaseUtils.getAdminConnection();
            subject = FormDisplayDAO.getFormDisplayGraph(conn, id);
            conn.close();
            Form encounterForm = (Form) subject;
            Set pageItems = encounterForm.getPageItems();
            for (Iterator iterator = pageItems.iterator(); iterator.hasNext();) {
				PageItem pageItem = (PageItem) iterator.next();
				String name = "field" + pageItem.getForm_field().getId();
				String currentField = (String) fieldMap.get(name);
				if (currentField == null) {
					// Add properties
		            FormPropertyConfig newFormPropertyConfig = new FormPropertyConfig();
		            newFormPropertyConfig.setName(name);
		            newFormPropertyConfig.setType("java.lang.String");
			        config.addFormPropertyConfig(newFormPropertyConfig);
				}
			}
            props = config.findFormPropertyConfigs();
            System.out.println("**The number of props for the bean  is " + props.length);
            for (FormPropertyConfig formPropertyConfig : props) {
				System.out.println("item: " + formPropertyConfig.getName());
			}

            request.setAttribute("encounterForm", subject);
            request.setAttribute("preview", "1");
            List yearList = DateUtils.getYearList();
            // yearlist is used to render the year tag - with dates in reverse order.
            request.setAttribute("yearList", yearList);
            FormBeanConfig formCfg = mapping.getModuleConfig().findFormBeanConfig("form" + subject.getId());
            if (formCfg == null) {
                request.setAttribute("instaBean", "1");
            }
            SessionPatient sessionPatient = new SessionPatient();
            sessionPatient.setId(new Long(1));
            SessionUtil sessionUtil = SessionUtil.getInstance(request.getSession());
            sessionUtil.setSessionPatient(sessionPatient);
            return mapping.findForward("preview");*/
        }
        // Since we may be getting a record from the zeprs database, we need to pass conn.
        // at this point, only CorrectOutcome uses conn..
        Principal userPrincipal = request.getUserPrincipal();
        String username = userPrincipal.getName();
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);

            if (!Identifiable.NEW.equals(id)) {
            //    subject = getSubject(conn, clazz, id);
            }
        } catch (ServletException e) {
            log.error(e);
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        //ActionErrors errors = form.validate(mapping, request);
        ActionMessages errors = form.validate(mapping, request);
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            if (dynaForm.get(ID_KEY) != null) {
                request.setAttribute(SUBJECT_KEY, subject);
            }
            return handleInputForward(mapping, request);
        }
        if (subject == null) {
            subject = (Identifiable) clazz.newInstance();
        }

        //this class is not just for admin, so we need to check the type
        /*if (subject instanceof Configuration && SystemStateManager.getCurrentState() == SystemStateManager.STATUS_NORMAL)
        {
            return handleNotLockedForward(mapping, request);
        }*/

        try {
            handlePersistence(mapping, dynaForm, subject, clazz, request);
        } catch (PopulationException e) {
            log.error(e);
            request.setAttribute("exception", e);
            return mapping.findForward("error");
        } catch (PersistenceException e) {
            log.error(e);
            request.setAttribute("exception", e);
            return mapping.findForward("error");
        } catch (SQLException e) {
            log.error(e);
            request.setAttribute("exception", e);
            return mapping.findForward("error");
        } catch (SessionUtil.AttributeNotFoundException e) {
            return handleAttributeNotFoundException(e, request, mapping);
        } catch (ObjectNotFoundException e) {
            log.error(e);
            request.setAttribute("exception", e);
            return mapping.findForward("error");
        } catch (NullPointerException e) {
            log.error("clazz: " + clazz + " dynaform: " + dynaForm.toString());
            e.printStackTrace();
            request.setAttribute("exception", e);
            return mapping.findForward("error");
        }

        request.setAttribute(SUBJECT_KEY, subject);

        // Reset form
        form.reset(mapping, request);
        StrutsUtils.removeFormBean(mapping, request);
        return handleSuccessForward(mapping, request);
    }

    protected ActionForward handleAttributeNotFoundException(SessionUtil.AttributeNotFoundException e, HttpServletRequest request, ActionMapping mapping) {
        log.error(e);
        request.setAttribute("exception", e);
        return mapping.findForward("error");
    }

    protected ActionForward handleInputForward(ActionMapping mapping, HttpServletRequest request) {
        return mapping.getInputForward();
    }

    protected Identifiable getSubject(Class clazz, Long id) throws PersistenceException, ObjectNotFoundException, IOException {
        Identifiable subject = PersistenceManagerFactory.getInstance(clazz).getFreshOne(id);
        log.debug("Clazz: " + clazz + " id: " + id);
        return subject;
    }

    protected Identifiable getSubject(Connection conn, Class clazz, Long id) throws PersistenceException, ObjectNotFoundException, IOException {
        Identifiable subject = PersistenceManagerFactory.getInstance(clazz).getFreshOne(id);
        log.debug("Clazz: " + clazz + " id: " + id);
        return subject;
    }

    protected ActionForward handleSuccessForward(ActionMapping mapping, HttpServletRequest request) throws PersistenceException, ObjectNotFoundException {
        return mapping.findForward(SUCCESS_FORWARD);
    }

    protected ActionForward handleNotLockedForward(ActionMapping mapping, HttpServletRequest request) {
        return mapping.findForward(NOTLOCKED_FORWARD);
    }

    protected void handlePersistence(ActionMapping mapping, DynaActionForm dynaForm, Identifiable subject, Class clazz, HttpServletRequest request) throws PopulationException, PersistenceException, SessionUtil.AttributeNotFoundException, ObjectNotFoundException, SQLException, ServletException {
        HttpSession session = request.getSession();
        Site site = SessionUtil.getInstance(session).getClientSettings().getSite();
        request.setAttribute(SUBJECT_KEY, subject);
        BeanPopulator.populate(dynaForm, subject);
        Principal user = request.getUserPrincipal();
        String userName = user.getName();
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(userName);
            if (subject.getClass().equals(org.cidrz.webapp.dynasite.valueobject.Problem.class)) {
                Problem problem = (Problem) subject;
                if (problem.getId().intValue() != 0) {
                    ProblemDAO.update(conn, problem, userName, site.getId());
                } else {
                    ProblemDAO.saveNewProblem(conn, problem, userName, site);
                }
            } else if (subject.getClass().equals(OutcomeImpl.class)) {
                Outcome outcome = (Outcome) subject;
                OutcomeDAO.update(conn, outcome, userName, site.getId());
            } else if (subject.getClass().equals(EncounterOutcome.class)) {
                Outcome outcome = (Outcome) subject;
                OutcomeDAO.update(conn, outcome, userName, site.getId());
            } else if (subject.getClass().equals(InfoOutcome.class)) {
                Outcome outcome = (Outcome) subject;
                OutcomeDAO.update(conn, outcome, userName, site.getId());
            } else if (subject.getClass().equals(org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome.class)) {
                Outcome outcome = (Outcome) subject;
                OutcomeDAO.update(conn, outcome, userName, site.getId());
            } else if (subject.getClass().equals(org.cidrz.webapp.dynasite.valueobject.Comment.class)) {
                Comment comment = (Comment) subject;
                Long problemId = comment.getProblemId();
                if (problemId != null) {
                	Problem problem = ProblemDAO.getOne(conn, problemId);
                    comment.setProblemUuid(problem.getUuid());
                }
                Long outcomeId = comment.getOutcomeId();
                if (outcomeId != null) {
                	Outcome outcome = OutcomeDAO.getOne(conn, outcomeId);
                	comment.setOutcomeUuid(outcome.getOutcomeUuid());
                }
                Pregnancy pregnancy = PregnancyDAO.getOne(conn, comment.getPregnancyId());
                Patient patient = PatientDAO.getOne(conn, comment.getPatientId());
                String patientUuid = patient.getUuid();
            	String pregnancyUuid = pregnancy.getUuid();
            	comment.setPatientUuid(patientUuid);
            	comment.setPregnancyUuid(pregnancyUuid);
            	UUID uuid = UUID.randomUUID();
            	String commentUuid = uuid.toString();
                comment.setUuid(commentUuid);
                CommentDAO.save(conn, comment, userName, site.getId());
                PatientStatusDAO.touchPatientStatus(conn, null, userName, site.getId(), comment.getPatientId());
                String problemType = (String) dynaForm.get("problemType");
                if (problemType.equals("problem")) {
                    ProblemDAO.touchProblem(conn, comment.getProblemId(), userName, site.getId());
                } else {
                    OutcomeDAO.touchOutcome(conn, comment.getOutcomeId(), userName, site.getId());
                }
            } else {
                // PersistenceManagerFactory.getInstance(clazz).save(subject, request.getUserPrincipal(), site);
                log.debug("*** Using PersistenceManagerFactory ***");
            }
        } catch (ServletException e) {
            log.error(e);
        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();
            request.setAttribute("exception", e);
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }


    }


}

