/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.struts.action.admin;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.cidrz.webapp.dynasite.dao.FormDisplayDAO;
import org.cidrz.webapp.dynasite.dao.RuleDefinitionDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.exception.PopulationException;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.SaveObjectAction;
import org.cidrz.webapp.dynasite.utils.BeanPopulator;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.struts.ParameterActionForward;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;
import org.cidrz.webapp.dynasite.valueobject.RuleDefinition;
import org.cidrz.webapp.dynasite.valueobject.Site;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;

public class SaveRuleDefinitionAction extends SaveObjectAction {

    protected void handlePersistence(ActionMapping mapping, DynaActionForm dynaForm, Identifiable subject, Class clazz, HttpServletRequest request) throws PopulationException, PersistenceException, SessionUtil.AttributeNotFoundException, ObjectNotFoundException, SQLException, ServletException {
        HttpSession session = request.getSession();
        Site site = SessionUtil.getInstance(session).getClientSettings().getSite();
        request.setAttribute(SUBJECT_KEY, subject);
        BeanPopulator.populate(dynaForm, subject);
        Principal user = request.getUserPrincipal();
        String userName = user.getName();
        Connection conn = null;
        try {
            conn = DatabaseUtils.getAdminConnection();
            RuleDefinition rule = (RuleDefinition) subject;
            RuleDefinitionDAO.insertRule(conn, rule.getRuleClass(), rule.getOutcomeClass(), rule.getFormId(), rule.getFieldId(), rule.isEnabled(), rule.isAllPregnancies(), userName, site.getId(), rule.getMessage(), rule.getOperand(), rule.getOperator());
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

    protected ActionForward handleSuccessForward(ActionMapping mapping, HttpServletRequest request) {
        RuleDefinition rule = (RuleDefinition) request.getAttribute(SUBJECT_KEY);
        ParameterActionForward fwd = null;
        fwd = new ParameterActionForward(mapping.findForward(SUCCESS_FORWARD));
        request.setAttribute("formId", request.getParameter("formId"));
        String formId = request.getParameter("formId");
        fwd = new ParameterActionForward(mapping.findForward("success-form"));
        //fwd.addParameter("id", rule.getForm().getId());
        try {
            Connection conn = DatabaseUtils.getAdminConnection();
            request.setAttribute(SUBJECT_KEY, FormDisplayDAO.getFormGraph(conn, Long.decode(formId)));
            conn.close();
        } catch (Exception e) {
            log.error("failed to re-fetch the parent Form of this RuleDefinition. RuleDefinition id = " + rule.getId());
        }
        /*if (rule.getForm() != null) {
            fwd = new ParameterActionForward(mapping.findForward("success-form"));
            //fwd.addParameter("id", rule.getForm().getId());
            try {
                //request.setAttribute(SUBJECT_KEY, PersistenceManagerFactory.getInstance(Form.class).getOne(rule.getForm().getId()));
                request.setAttribute(SUBJECT_KEY, FormDisplayDAO.getFormGraph(Long.decode(request.getParameter("formId"))));
            } catch (Exception e) {
                log.error("failed to re-fetch the parent Form of this RuleDefinition. RuleDefinition id = " + rule.getId());
            }

        } else {
            fwd = new ParameterActionForward(mapping.findForward("success-field"));
            //fwd.addParameter("id", rule.getField().getId());
            try {
                //request.setAttribute(SUBJECT_KEY, PersistenceManagerFactory.getInstance(FormField.class).getOne(rule.getField().getId()));
                request.setAttribute("pageItem", request.getParameter("pageItem"));
                //request.setAttribute(SUBJECT_KEY, PersistenceManagerFactory.getInstance(PageItem.class).getOne(Long.decode(request.getParameter("pageItem"))));
                request.setAttribute(SUBJECT_KEY, PageItemDAO.getOne(Long.decode(request.getParameter("pageItem"))));
            } catch (Exception e) {
                log.error("failed to re-fetch the parent FormField of this RuleDefinition. RuleDefinition id = " + rule.getId());
            }

        }*/
        return fwd;
    }
}
