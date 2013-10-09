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
 * Created on Mar 31, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.cidrz.project.zeprs.report;

import org.cidrz.webapp.dynasite.utils.XmlUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;

/**
 * @author ericl
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class ZEPRSRegister {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(ZEPRSRegister.class);

    protected int siteId;
    protected String siteName;
    protected Date beginDate;
    protected Date endDate;
    protected String type;


    ZEPRSRegister() {

        getSiteInfo();
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

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract void getPatientRegister(Date beginDate, Date endDate, int siteId);

    private void getSiteInfo() {
         this.setSiteId(siteId);
    }

    public ZEPRSRegister loadClass(String className) throws IOException {
        // ZEPRSReport report = null;
        String path = org.cidrz.webapp.dynasite.Constants.REPORTS_XML_PATH + className + "Report.xml";
        // report = (ZEPRSReport)
        ZEPRSRegister report = (ZEPRSRegister) XmlUtils.getOne(path);
        return report;
    }
}
