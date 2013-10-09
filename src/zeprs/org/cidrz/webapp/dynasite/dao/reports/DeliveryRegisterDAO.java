/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.dao.reports;

import org.apache.commons.dbutils.QueryLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.report.DeliveryRegisterPatient;
import org.cidrz.project.zeprs.report.ZEPRSUtils;
import org.cidrz.project.zeprs.valueobject.report.gen.DeliverySumReport;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.FieldEnumeration;
import org.cidrz.webapp.dynasite.valueobject.Form;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Sep 2, 2005
 *         Time: 5:05:27 PM
 */
public class DeliveryRegisterDAO {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(DeliveryRegisterDAO.class);

    /**
     * @param conn
     * @param beginDate
     * @param endDate
     * @param siteID
     * @return
     * @throws IOException
     * @throws ServletException
     * @throws SQLException
     */
    public static List getAll(Connection conn, Date beginDate, Date endDate, int siteID) throws IOException, ServletException, SQLException {
        List items;
        Map queries = QueryLoader.instance().load("/" + org.cidrz.webapp.dynasite.Constants.SQL_DEMO_PROPERTIES);
        String sql;
        if (siteID == 0) {
            sql = (String) queries.get("SQL_RETRIEVE_REPORT_SITES66");
        } else {
            sql = (String) queries.get("SQL_RETRIEVE_REPORT66");
        }
        ArrayList values = new ArrayList();
        values.add(beginDate);
        values.add(endDate);
        if (siteID != 0) {
            values.add(siteID);
        }

        items = DatabaseUtils.getList(conn, DeliverySumReport.class, sql, values);
        // Attach a map of encounter values that has enumerations already resolved.
        Form encounterForm = (Form) DynaSiteObjects.getForms().get(new Long("66"));
        Map encMap;
        // loop through list and assign string values to enum id's
        for (int i = 0; i < items.size(); i++) {
            DeliverySumReport deliverySumReport = (DeliverySumReport) items.get(i);
            encMap = PatientRecordUtils.getEncounterMap(encounterForm, deliverySumReport, "starSchemaName");
            deliverySumReport.setEncounterMap(encMap);
        }
        return items;
    }
}
