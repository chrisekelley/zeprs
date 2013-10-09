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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.cidrz.webapp.dynasite.logic.PersistenceManagerFactory;
import org.cidrz.webapp.dynasite.utils.BeanPopulator;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: dave
 * Date: Mar 17, 2004
 * Time: 9:49:43 AM
 * To change this template use File | Settings | File Templates.
 */


public class EditObjectAction extends PersistentObjectAction {
    protected final ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Class clazz = getMappedClass(mapping);
        //does the user want to edit an existing object?
        Identifiable subject = null;
        if (request.getAttribute(SUBJECT_KEY) != null) {
            subject = (Identifiable) request.getAttribute(SUBJECT_KEY);
        } else if (request.getParameter(ID_KEY) != null && !request.getParameter(ID_KEY).equals("")) {
            subject = PersistenceManagerFactory.getInstance(clazz).getOne(Long.valueOf(request.getParameter(ID_KEY)));
        }
        BeanPopulator.populate(subject, (DynaActionForm) form);
        request.setAttribute(SUBJECT_KEY, subject);
        addRequestItems(request, subject);
        return mapping.findForward(SUCCESS_FORWARD);
    }

    protected void addRequestItems(HttpServletRequest request, Identifiable subject) {
    }
}


