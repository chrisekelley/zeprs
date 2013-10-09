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
import org.cidrz.webapp.dynasite.logic.PersistenceManagerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dave
 * Date: Mar 17, 2004
 * Time: 9:49:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class ObjectListAction extends PersistentObjectAction {
    protected final ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Class clazz = getMappedClass(mapping);
        List theList = PersistenceManagerFactory.getInstance(clazz).getAll();
        request.setAttribute(SUBJECT_KEY, theList);
        return mapping.findForward(SUCCESS_FORWARD);
    }
}
