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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: dave
 * Date: Mar 11, 2004
 * Time: 8:59:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class HelpAction extends BaseAction {
    /**
     * Commons Logging instance.
     */

    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String page = null;
        if (request.getParameter("page") != null) {
            page = request.getParameter("page");
        }
        request.setAttribute("page", page);

        return mapping.findForward("success");
    }

}
