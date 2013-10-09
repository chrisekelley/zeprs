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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;
import java.io.IOException;

/**
 * Grab output from a tag.
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Mar 10, 2006
 *         Time: 2:39:53 PM
 */
public class RenderPageAction extends BaseAction {


    protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        JspFactory _jspxFactory = null;
        PageContext pageContext = null;
        _jspxFactory = JspFactory.getDefaultFactory();
        response.setContentType("text/html");

        pageContext = _jspxFactory.getPageContext(this.getServlet(), request, response, null, true, 8192, true);
        JspContext jspContext = pageContext;
        java.util.ArrayList _jspx_nested = null;
        java.util.ArrayList _jspx_at_begin = null;
        java.util.ArrayList _jspx_at_end = null;
        jspContext = new org.apache.jasper.runtime.JspContextWrapper(jspContext, _jspx_nested, _jspx_at_begin, _jspx_at_end, null);

        /*patientid_005fdistricts_tag tag = new patientid_005fdistricts_tag();
        tag.setJspContext(jspContext);
        try {
            tag.doTag();
        } catch (JspException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        return mapping.findForward(SUCCESS_FORWARD);
    }


}
