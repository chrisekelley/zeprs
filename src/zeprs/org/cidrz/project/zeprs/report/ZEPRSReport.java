/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

/*
 * Created on Mar 25, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.cidrz.project.zeprs.report;

import org.cidrz.webapp.dynasite.utils.XmlUtils;
import org.cidrz.webapp.dynasite.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author ericl
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class ZEPRSReport {

    protected int siteId;
    protected String siteName;
    protected HashMap reportProperties;

    ZEPRSReport() {

       // getSiteInfo();
    }

    /**
     * @return Returns the siteId.
     */
    public int getSiteId() {
        return siteId;
    }

    /**
     * @param siteId The siteId to set.
     */
    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    /**
     * @return Returns the siteName.
     */
    public String getSiteName() {
        return siteName;
    }

    /**
     * @param siteName The siteName to set.
     */
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public HashMap getReportProperties() {
        return reportProperties;
    }

    public void setReportProperties(HashMap reportProperties) {
        this.reportProperties = reportProperties;
    }

    public abstract void loadReport(Date beginDate, Date endDate);

    private void getSiteInfo() {
    	Connection conn = null;
        try {
            conn = ZEPRSUtils.getZEPRSConnection();

            String sql = "SELECT site.id as site_id, site_name FROM site, client_setting " +
                    "WHERE site.id = client_setting.site_id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.first()) {

                this.siteId = rs.getInt("site_id");
                this.siteName = rs.getString("site_name");
            }
        } catch (Exception ex) {
            // TBD
        } finally {
        	try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    public ZEPRSReport loadClass(String className) throws IOException {
        // ZEPRSReport report = null;
        String path = org.cidrz.webapp.dynasite.Constants.REPORTS_XML_PATH + className + "Report.xml";
        // report = (ZEPRSReport)
        ZEPRSReport report = (ZEPRSReport) XmlUtils.getOne(path);
        return report;
    }

}
