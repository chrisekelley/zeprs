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
import org.apache.struts.config.ModuleConfig;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.PopulatePatientRecord;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by IntelliJ IDEA.
 * User: Dave Peckham
 * Date: Apr 23, 2004
 * Time: 3:45:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class CreatePatientAction extends BaseAction {
    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(CreatePatientAction.class);

    protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Principal user = request.getUserPrincipal();
        String username = user.getName();
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            ModuleConfig cfg = mapping.getModuleConfig();
            String patientType = request.getParameter("patientType");
            String number = request.getParameter("number");
            EncounterData enc;
            // setup clock
            Calendar cal = new GregorianCalendar();
            Date starttime = cal.getTime();
            long long_starttime = starttime.getTime();

            if (patientType != null & number == null) {
                enc = PopulatePatientRecord.populate(conn, cfg, session, patientType, username);
                request.setAttribute("patientId", enc.getPatientId());
            } else if (number != null) {
                int numberI = new Integer(number);
                int patients = numberI * 10;
                if (patientType != null) {
                    log.debug("Start Time for generating " + patients + " " + patientType + " patients: " + starttime.toString());
                    numberI = numberI * 10;
                    for (int i = 0; i < numberI; i++) {
                        PopulatePatientRecord.populate(conn, cfg, session, patientType, username);
                    }
                } else {
                    log.debug("Start Time for generating " + patients + " test patients: " + starttime.toString());
                    for (int i = 0; i < numberI; i++) {
                        patientType = "basic";
                        PopulatePatientRecord.populate(conn, cfg, session, patientType, username);
                        patientType = "partograph";
                        PopulatePatientRecord.populate(conn, cfg, session, patientType, username);
                        patientType = "infants";
                        PopulatePatientRecord.populate(conn, cfg, session, patientType, username);
                        patientType = "PosIinfant";
                        PopulatePatientRecord.populate(conn, cfg, session, patientType, username);
                        patientType = "NegInfant";
                        PopulatePatientRecord.populate(conn, cfg, session, patientType, username);
                        patientType = "NegInfantClose";
                        PopulatePatientRecord.populate(conn, cfg, session, patientType, username);
                        patientType = "NegInfantEnd";
                        PopulatePatientRecord.populate(conn, cfg, session, patientType, username);
                        patientType = "NegInfantSBEnd";
                        PopulatePatientRecord.populate(conn, cfg, session, patientType, username);
                        patientType = "NegInfantSB";
                        PopulatePatientRecord.populate(conn, cfg, session, patientType, username);
                        patientType = "basic";
                        PopulatePatientRecord.populate(conn, cfg, session, patientType, username);
                        patientType = "NegInfantEnd";
                        PopulatePatientRecord.populate(conn, cfg, session, patientType, username);
                    }
                }

                // Stop clock and calculate time elapsed
                Calendar cal2 = new GregorianCalendar();
                Date endtime = cal2.getTime();
                long long_endtime = endtime.getTime();
                long difference = (long_endtime - long_starttime);

                log.debug("Time to generate " + patients + " test patients: " + difference / 1000 + " seconds");
            } else {
                enc = PopulatePatientRecord.populate(conn, cfg, session, "basic", username);
                request.setAttribute("patientId", enc.getPatientId());
            }
        } catch (ServletException e) {
            log.error(e);
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        return mapping.findForward("success");
    }
}
