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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;

/**
 * Displays output of an administrative action.
 * User: Chris Kelley
 * Date: Nov 19, 2009
 */
public class MessageAction extends BaseAction {
    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(MessageAction.class);

    protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String resultUpdatePStr = (String) request.getParameter("resultUpdate");
        String messagePStr = (String) request.getParameter("message");

        request.setAttribute("result", resultUpdatePStr);
        request.setAttribute("message", messagePStr);

        return mapping.findForward(SUCCESS_FORWARD);
    }

}
