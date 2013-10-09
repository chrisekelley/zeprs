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
 * Created on Mar 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.cidrz.project.zeprs.report;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ericl
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class chooseReportForm extends ActionForm {

    private String report = null;
    private String bdate = null;
    private String edate = null;
    private int siteId;
    private boolean isXml;

    /**
     * @return Returns the bdate.
     */
    public String getBdate() {
        return bdate;
    }

    /**
     * @param bdate The bdate to set.
     */
    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    /**
     * @return Returns the edate.
     */
    public String getEdate() {
        return edate;
    }

    /**
     * @param edate The edate to set.
     */
    public void setEdate(String edate) {
        this.edate = edate;
    }

    /**
     * @return Returns the report.
     */
    public String getReport() {
        return report;
    }

    /**
     * @param report The report to set.
     */
    public void setReport(String report) {
        this.report = report;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        report = null;
    }

    /**
     * Site
     * @return siteId
     */
    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public boolean isXml() {
        return isXml;
    }

    public void setXml(boolean xml) {
        isXml = xml;
    }

}
