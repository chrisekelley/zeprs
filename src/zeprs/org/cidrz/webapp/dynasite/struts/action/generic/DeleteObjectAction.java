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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.logic.PersistenceManagerFactory;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 * User: dave
 * Date: Mar 17, 2004
 * Time: 9:49:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class DeleteObjectAction extends PersistentObjectAction {
    /**
     * Commons Logging instance.
     */
    protected Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    protected final ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Class clazz = getMappedClass(mapping);
        //DynaActionForm dynaForm = (DynaActionForm) form;
        HttpSession session = request.getSession();
        Identifiable subject = null;
        Long id;
        id = Long.valueOf(request.getParameter("id"));

        if (!Identifiable.NEW.equals(id)) {
            subject = PersistenceManagerFactory.getInstance(clazz).getOne(id);
        }
        setExtraRequestAttributes(mapping, request);
        handlePersistence(mapping, subject, clazz, request);

        //this class is not just for admin, so we need to check the type
        /*if (subject instanceof Configuration && SystemStateManager.getCurrentState() == SystemStateManager.STATUS_NORMAL) {
            return handleNotLockedForward(mapping, request);
        }*/

        PersistenceManagerFactory.getInstance(clazz).delete(subject, request.getUserPrincipal());
        request.setAttribute(SUBJECT_KEY, subject);

        return handleSuccessForward(mapping, request);
    }

    protected ActionForward handleInputForward(ActionMapping mapping, HttpServletRequest request) {
        return mapping.getInputForward();
    }

    protected void setExtraRequestAttributes(ActionMapping mapping, HttpServletRequest request) {
    }

    protected ActionForward handleSuccessForward(ActionMapping mapping, HttpServletRequest request) {
        return mapping.findForward(SUCCESS_FORWARD);
    }

    protected ActionForward handleNotLockedForward(ActionMapping mapping, HttpServletRequest request) {
        return mapping.findForward(NOTLOCKED_FORWARD);
    }

    protected ActionForward handlePersistence(ActionMapping mapping, Identifiable subject, Class clazz, HttpServletRequest request) throws SessionUtil.AttributeNotFoundException, PersistenceException, ObjectNotFoundException {
        request.setAttribute(SUBJECT_KEY, subject);
        return mapping.findForward(SUCCESS_FORWARD);
    }
}
