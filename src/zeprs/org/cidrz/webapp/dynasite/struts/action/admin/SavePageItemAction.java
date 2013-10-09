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
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.action.DynaActionForm;
import org.cidrz.webapp.dynasite.dao.FormDisplayDAO;
import org.cidrz.webapp.dynasite.dao.FormFieldDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.exception.PopulationException;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.SaveObjectAction;
import org.cidrz.webapp.dynasite.utils.BeanPopulator;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.struts.ParameterActionForward;
import org.cidrz.webapp.dynasite.valueobject.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;


public class SavePageItemAction extends SaveObjectAction {

    protected void handlePersistence(ActionMapping mapping, DynaActionForm dynaForm, Identifiable subject, Class clazz, HttpServletRequest request) throws PopulationException, PersistenceException, SessionUtil.AttributeNotFoundException, ObjectNotFoundException, SQLException, ServletException {
        HttpSession session = request.getSession();
        Site site = SessionUtil.getInstance(session).getClientSettings().getSite();
        Long siteId = site.getId();
        request.setAttribute(SUBJECT_KEY, subject);
        BeanPopulator.populate(dynaForm, subject);
        Principal user = request.getUserPrincipal();
        String userName = user.getName();
        //Connection conn = null;
        Connection adminConn = null;
        String sql = "START TRANSACTION;";
        try {
            adminConn = DatabaseUtils.getSpecialRootConnection(userName);
            adminConn.setAutoCommit(false);
            // start the transaction
            DatabaseUtils.create(adminConn, sql);
            PageItem pageItem = (PageItem) subject;
            Long formId = pageItem.getFormId();
            FormField formField = pageItem.getForm_field();
            FormFieldDAO.createColumn(adminConn, formField, formId, pageItem);
            Long pageItemId = (Long) FormFieldDAO.createFormfield(adminConn, formField, pageItem, userName, siteId);
            sql = "COMMIT";
            DatabaseUtils.create(adminConn, sql);
            // PersistenceManagerFactory.getInstance(clazz).save(subject, request.getUserPrincipal(), site);
            // log.debug("*** Using PersistenceManagerFactory ***");
            // }
        } catch (ServletException e) {
            log.error(e);
        } catch (Exception e) {
            sql = "ROLLBACK";
            try {
                DatabaseUtils.create(adminConn, sql);
                log.error(e);
                e.printStackTrace();
                request.setAttribute("exception", e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } finally {
            /*if (conn != null && !conn.isClosed()) {
                conn.close();
            }*/
            if (adminConn != null && !adminConn.isClosed()) {
                adminConn.close();
            }
        }
    }

    protected ActionForward handleSuccessForward(ActionMapping mapping, HttpServletRequest request) {
        // This refreshes our view of the form with the new fields
        Form encounterForm = null;
        /*try {
            form = (Form) PersistenceManagerFactory.getInstance(Form.class).getFreshOne(Long.decode(request.getParameter("form_id")));
            request.setAttribute(SUBJECT_KEY, form);
            DynaSiteObjects.getForms().remove(form.getId());
            try {
                DynaSiteObjects.getForms().put(form.getId(), form);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            log.error("failed to re-fetch the parent Form -  id = " + request.getParameter("form_id"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        Long formId = Long.valueOf(request.getParameter("form_id"));
        Connection conn = DatabaseUtils.getAdminConnection();
        try {
            encounterForm = FormDisplayDAO.getFormGraph(conn, formId);
            DynaSiteObjects.getForms().remove(formId);
            DynaSiteObjects.getForms().put(formId, encounterForm);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        request.setAttribute(SUBJECT_KEY, encounterForm);

        /*ParameterActionForward fwd = new ParameterActionForward(mapping.findForward(SUCCESS_FORWARD));
        //log.info("Form -  formId = " + request.getParameter("formId"));
        request.setAttribute("form_id", request.getParameter("form_id"));
        request.setAttribute("formId", request.getParameter("form_id"));*/
        //fwd.addParameter("id", field.getForm().getId());


        ActionRedirect redirect = new ActionRedirect(mapping.findForward(SUCCESS_FORWARD));
        redirect.addParameter("id",request.getParameter("form_id"));
        redirect.addParameter("formId",formId);
        return redirect;
    }
}

