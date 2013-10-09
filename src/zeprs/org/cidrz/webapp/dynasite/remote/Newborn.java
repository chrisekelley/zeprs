/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.remote;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.dao.FormDAO;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.dao.SessionPatientDAO;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.exception.PopulationException;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.session.SessionUtil.AttributeNotFoundException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;
import org.cidrz.webapp.dynasite.valueobject.Site;
import org.cidrz.project.zeprs.valueobject.gen.NewbornEval;
import org.cidrz.project.zeprs.valueobject.gen.Newbornrecord;

import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Dec 19, 2004
 * Time: 5:28:43 PM
 * used by dwr remote process to add newborns
 */
public class Newborn {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(Newborn.class);

    /**
     * @param newbornDateField
     * @param birthtime
     * @param sequence
     * @param patientId
     * @param sex
     * @param weight
     * @return
     * @throws PersistenceException
     * @throws ObjectNotFoundException
     * @throws SessionUtil.AttributeNotFoundException
     *
     */
    public static String createNewborn(String newbornDateField, String birthtime, String sequence, String patientId, String sex, String username, String siteId, String pregnancyId, String weight) throws PersistenceException, ObjectNotFoundException, SessionUtil.AttributeNotFoundException, PopulationException, SQLException, ServletException {
        Connection conn = null;
        String response = null;
        //test
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            WebContext exec = WebContextFactory.get();
            HttpSession session = exec.getSession();
            SessionPatient sessionPatient = null;
            try {
				sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
			} catch (AttributeNotFoundException e1) {
				// redirect
				log.error(e1);
                response = "Error: Your session may have expired. Please refresh this page or login again in order to create the Newborn record.";
                return response;
			}
            if (sessionPatient != null && sessionPatient.getParentId() != null) {
                response = "Error: You may not post a Delivery Summary form or create another newborn record while viewing a child record. Please return to the mother's record.";
            } else {
                response = PatientRecordUtils.createNewborn(conn, patientId, sequence, newbornDateField, sex, pregnancyId, birthtime, username, siteId, session, weight);
            }
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ServletException e) {
            log.error(e);
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                conn = null;
            }
        }
        return response;
    }

    public static String createResponse(Long motherId, String pregnancyStr) {
        String response = "";
        Long pregnancyId = new Long(pregnancyStr);
        WebContext exec = WebContextFactory.get();
        String username = null;
        try {
            username = exec.getHttpServletRequest().getUserPrincipal().getName();
        } catch (NullPointerException e) {
            // unit testing - it's ok...
            username = "zepadmin";
        }
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            response = GetNewbornList(conn, motherId, pregnancyId);
            /*if (newborns.size() > 0) {
                response = sbuff.toString();
            } else {
                response = "0||";
            }*/
        } catch (ServletException e) {
            log.error(e);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    public static String GetNewbornList(Connection conn, Long motherId, Long pregnancyId) throws ServletException {
        String response = "";
        List newborns = PatientDAO.getChildren(conn, motherId, pregnancyId);
        StringBuffer sbuff = new StringBuffer();
        String infantSex = null;
        String male = "Male";
        String female = "Female";
        String tdb = "TBD";
        //  if (newborns.size() > 0) {
        sbuff.append(newborns.size() + "|<p><strong>" + String.valueOf(newborns.size()) + " newborn(s):</strong></p>\n<table width=\"80%\" class=\"enhancedtable\">\n");
        sbuff.append("<tr><th>Name</th><th>Sex</th><th>Weight</th><th>Date of Birth</th><th>Time</th><th>Delete</th></tr>\n");
        //  }
/*            else {
                sbuff.append(newborns.size() + "|<table width=\"60%\" class=\"enhancedtable\"><tr><td></td></tr>");
            }*/

        if (newborns.size() == 0) {
            sbuff.append("<tr><td colspan=\"6\"></td></tr>");
        }

        String birthTimeString = "TBD";
        String birthDateString = "TBD";
        for (Iterator iterator = newborns.iterator(); iterator.hasNext();) {
            Patient p = (Patient) iterator.next();
            String weight = "";
            if (p.getSex() != null) {
                if (p.getSex().intValue() == 1) {
                    infantSex = female;
                } else {
                    infantSex = male;
                }
            } else {
                infantSex = tdb;
            }
            try {
                Newbornrecord newbornRecord = (Newbornrecord) EncountersDAO.getOne(conn, p.getId(), p.getPregnancyId(), Long.valueOf("109"));
                Float weightF = newbornRecord.getField2087();
                if (weightF != null) {
                    weight = weightF.toString();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ObjectNotFoundException e) {
                // it's ok
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            String TIME_FORMAT = "HH:mm";
            java.text.SimpleDateFormat sdfTime = new java.text.SimpleDateFormat(TIME_FORMAT);
            sdfTime.setTimeZone(TimeZone.getDefault());
            Time timeOfBirth = p.getTimeOfBirth();
            Date dateOfBirth = p.getBirthdate();
            if (timeOfBirth != null) {
                birthTimeString = sdfTime.format(timeOfBirth.getTime());
            } else {
                birthTimeString = "TBD";
            }
            if (dateOfBirth != null) {
            	birthDateString = dateOfBirth.toString();
            } else {
            	birthDateString = "TBD";
            }
            sbuff.append("<tr><td>" + p.getFirstName() + " " + p.getSurname() + "</td><td>" + infantSex + "</td>" +
                    "<td>" + weight + "</td><td>" + birthDateString + "</td><td>" + birthTimeString + "</td>" +
                    "<td><a href=\"#\" onClick=\"deleteNewborn(" + p.getId() + "," + motherId + "," + pregnancyId + ")\">X</a></td></tr>\n");
        }
        sbuff.append("</table>\n");
        // add value for last newborn birthTimeString
        sbuff.append("|" + birthTimeString);
        response = sbuff.toString();
        return response;
    }

    public String deleteNewborn(String infantId, Long parent_id, String pregnancyId) throws Exception {
        WebContext exec = WebContextFactory.get();
        HttpSession session = exec.getSession();
        String username = null;
        String response = null;
        try {
            username = exec.getHttpServletRequest().getUserPrincipal().getName();
        } catch (NullPointerException e) {
            // unit testing - it's ok...
            username = "demo";
        }
        Site site = SessionUtil.getInstance(session).getClientSettings().getSite();
        String siteAbbrev = site.getAbbreviation();
        Connection conn = null;
        try {
            // conn = DatabaseUtils.getZEPRSConnection(username);
            // using the super special root connection for this one mate!
            conn = DatabaseUtils.getSpecialRootConnection(username);
            // changed to use archiveDelete Patient instead. Encountered problems of users deleting newborns that had additional encounters.
            // old method: FormDAO.deleteNewborn(conn, Long.valueOf(infantId));
            try {
                boolean isLogged = true;
                String filePath = org.cidrz.webapp.dynasite.Constants.PATIENTS_DELETED_PATH;
                StringBuffer sbuf = PatientRecordUtils.archiveDeletePatient(conn, Long.valueOf(infantId), siteAbbrev, isLogged, filePath);
            } catch (SQLException e) {
                e.printStackTrace();
                response = "SQL error: " + e;
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (ObjectNotFoundException e) {
                // hmmm
            }
            SessionPatientDAO.updateSessionPatient(conn, new Long(parent_id), new Long(pregnancyId), session);
            response = createResponse(parent_id, pregnancyId);
        } catch (ServletException e) {
            log.error(e);
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                conn = null;
            }
        }
        return response;
    }
}