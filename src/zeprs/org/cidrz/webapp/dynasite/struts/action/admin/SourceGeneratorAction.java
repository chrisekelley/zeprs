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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.utils.*;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.FormChanges;
import org.cidrz.webapp.dynasite.dao.RuleDefinitionDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.sql.SQLException;
import java.sql.Connection;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Feb 27, 2005
 * Time: 5:22:24 PM
 */
public final class SourceGeneratorAction extends BaseAction {
    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());
    private DynasiteSourceGenerator site;

    protected ActionForward doExecute(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
            throws Exception {
        Connection conn = DatabaseUtils.getAdminConnection();
        //extract vars
        Integer gen = null;
        Boolean dev = DynaSiteObjects.getDev();
        if (request.getParameter("gen") != null) {
            gen = Integer.valueOf(request.getParameter("gen"));
        }
        // setup clock
        Calendar cal = new GregorianCalendar();
        Date starttime = cal.getTime();
        long long_starttime = starttime.getTime();
        // setup messaging
        StringBuffer sbuf = new StringBuffer();
        String message = null;
        String combinedMessage = null;
        if (gen.intValue() != 6) {
            // re-initialise the forms - may have made some schema changes in form admin
            DynaSiteObjects.setForms(null);
            try {
                DynaSiteObjects.getForms();
            } catch (Exception e) {
                log.error(e);
            }
        }

        switch (gen.intValue()) {
                // generate source only
            case 1:
                new DynasiteSourceGenerator().createSourceFiles("record", dev, null);
                break;
                // generate sql only
            case 2:
                new SqlGenerator().createSourceFiles(dev);
                break;
                // generate struts_config only
            case 3:
                new GenerateStrutsConfig().init(dev);
                break;
                // generate forms in xml only
            case 4:
                try {
                    log.debug("starting xml generation");
                    message = XmlUtils.outputForms(dev, conn, null);
                    message = message + XmlUtils.outputDynaSiteConfig(dev);
                } catch (SQLException e) {
                    log.error(e);
                    request.setAttribute("exception", e);
                    return mapping.findForward("error");
                } catch (ServletException e) {
                    log.error(e);
                    request.setAttribute("exception", e);
                    return mapping.findForward("error");
                } catch (IOException e) {
                    log.error(e);
                    request.setAttribute("exception", e);
                    return mapping.findForward("error");
                }
                sbuf.append(message);
                break;
                // generate hibernate mapping files only - this is not included in the default on purpose.
            case 6:
                new DynasiteSourceGenerator().createMappingfiles();
                sbuf.append("Mapping file generation.");
                break;
                // generate report source only
            case 8:
                new DynasiteSourceGenerator().createSourceFiles("report", dev, null);
                // new DynasiteSourceGenerator().createMappingfiles();
                break;
                // do it all
                // generate sql only
            case 9:
                new SqlGenerator().createSqlDeleteScript();
                break;
           /* case 10:
                RuleDefinitionDAO.updateRules();
                break;*/
            case 10:
                FormChanges.processFormChanges(dev);
                break;
            default:
                // must generate xml files first, in order to get the correct schema into DynaSiteObjects
                sbuf.append("Creating xml files\n");
                message = XmlUtils.outputForms(dev, conn, null);
                message = message + XmlUtils.outputDynaSiteConfig(dev);
                sbuf.append(message);
                sbuf.append("Creating source files\n");
               // new DynasiteSourceGenerator().createSourceFiles("record", false);
                new DynasiteSourceGenerator().createSourceFiles("record", dev, null);
                sbuf.append("Creating SQL file\n");
                new SqlGenerator().createSourceFiles(dev);
                sbuf.append("Creating sql delete script\n");
                new SqlGenerator().createSqlDeleteScript();
               // sbuf.append("Creating foo and bar.xml\n");
                new GenerateStrutsConfig().init(dev);
                sbuf.append("Creating report files\n");
              //  new DynasiteSourceGenerator().createSourceFiles("report", false);
                new DynasiteSourceGenerator().createSourceFiles("report", dev, null);
                //sbuf.append("Creating hibernate mapping files\n");
                // new DynasiteSourceGenerator().createMappingfiles();
                break;
        }
        // Stop clock and calculate time elapsed
        Calendar cal2 = new GregorianCalendar();
        Date endtime = cal2.getTime();
        long long_endtime = endtime.getTime();
        long difference = (long_endtime - long_starttime);

        combinedMessage = sbuf.toString();
        request.setAttribute("starttime", String.valueOf(long_starttime));
        request.setAttribute("endtime", String.valueOf(long_endtime));
        request.setAttribute("report", "Dyansite Source Generation");
        request.setAttribute("difference", String.valueOf(difference));
        request.setAttribute("message", combinedMessage);
        conn.close();
        return mapping.findForward("success");
    }
}
