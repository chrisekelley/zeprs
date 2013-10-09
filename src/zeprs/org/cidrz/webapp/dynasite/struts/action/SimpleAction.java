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

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.webapp.dynasite.session.SessionUtil;


public final class SimpleAction extends Action {

    public static final String SUBJECT_KEY = "subject";
    public static final String ID_KEY = "id";
    public static final String SUCCESS_FORWARD = "success";
    public static final String LOCKED_FORWARD = "locked";
    public static final String SETUP_FORWARD = "setup";
    public static final String NOTLOCKED_FORWARD = "notlocked";

	public final ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // Extract attributes we will need
        HttpSession session = request.getSession();
        SessionUtil.clear(session);
        session.invalidate();
        request.getSession(true);
        if (request.getAttribute("message") != null)  {
            request.setAttribute("message", request.getAttribute("message"));
        }
        return doExecute(mapping, form, request, response);
    }

    protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward(SUCCESS_FORWARD);
    }
}

