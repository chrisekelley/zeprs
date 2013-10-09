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
import org.cidrz.webapp.dynasite.valueobject.ReportSpec;
import org.cidrz.webapp.dynasite.logic.PersistenceManagerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: dave
 * Date: Mar 11, 2004
 * Time: 8:59:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReportAction extends BaseAction {
    /**
     * Commons Logging instance.
     */

    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    protected ActionForward doExecute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        int reportId = Integer.parseInt(httpServletRequest.getParameter("report_id"));
        ReportSpec report = (ReportSpec) PersistenceManagerFactory.getInstance(ReportSpec.class).getOne(Long.valueOf(httpServletRequest.getParameter("report_id")));
        try {
            httpServletRequest.setAttribute("resultset", report.executeQuery());
        } catch (SQLException e) {
            log.error("Report " + reportId + " has a SQL error. " + e);
            return actionMapping.findForward("error");
        }
        httpServletRequest.setAttribute("report_spec", report);
        return actionMapping.findForward("success");
    }

}
