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

import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.exception.PopulationException;
import org.cidrz.webapp.dynasite.logic.PersistenceManagerFactory;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.SaveObjectAction;
import org.cidrz.webapp.dynasite.utils.BeanPopulator;
import org.cidrz.webapp.dynasite.valueobject.FormChanges;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;
import org.cidrz.webapp.dynasite.valueobject.Site;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.dao.FormDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.security.Principal;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Dec 1, 2004
 * Time: 5:19:28 PM
 */
public class SaveFormAction extends SaveObjectAction {

    protected Identifiable getSubject(Class clazz, Long id) throws PersistenceException, ObjectNotFoundException {
        Identifiable subject = PersistenceManagerFactory.getInstance(clazz).getOneForm(id);
        return subject;
    }

    protected void handlePersistence(ActionMapping mapping, DynaActionForm dynaForm, Identifiable subject, Class clazz, HttpServletRequest request) throws PopulationException, PersistenceException, SessionUtil.AttributeNotFoundException, ObjectNotFoundException, SQLException, ServletException {
        HttpSession session = request.getSession();
        Site site = SessionUtil.getInstance(session).getClientSettings().getSite();
        request.setAttribute(SUBJECT_KEY, subject);
        BeanPopulator.populate(dynaForm, subject);
        /*try {
            BeanUtils.copyProperties(dynaForm, subject);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }*/
        Principal user = request.getUserPrincipal();
        String userName = user.getName();
        try {
            Long formId = FormDAO.saveNewForm((Form) subject, userName, site.getId());
            // Now add this form to the FormChanges list.
            FormChanges.makeDirty(formId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*PersistenceManagerFactory.getInstance(clazz).save(subject, request.getUserPrincipal(), site);
        log.debug("*** Using PersistenceManagerFactory ***");*/
    }
}
