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

import org.cidrz.webapp.dynasite.valueobject.Identifiable;
import org.cidrz.webapp.dynasite.struts.action.generic.EditObjectAction;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Aug 6, 2004
 * Time: 3:03:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class EditEnumerationAction extends EditObjectAction {

    protected void addRequestItems(HttpServletRequest request, Identifiable subject) {
        request.setAttribute("form_id", request.getParameter("formId"));
    }


}
