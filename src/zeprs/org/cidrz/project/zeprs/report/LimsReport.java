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
 * Created on Mar 29, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.cidrz.project.zeprs.report;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.report.valueobject.FixedCostIncentive;
import org.cidrz.project.zeprs.valueobject.gen.LabTest;
import org.cidrz.project.zeprs.valueobject.lims.LastRecordImported;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.dao.LabTestDAO;
import org.cidrz.webapp.dynasite.utils.AuditInfoBeanProcessor;
import org.cidrz.webapp.dynasite.utils.XmlUtils;
import org.cidrz.webapp.dynasite.utils.ZEPRSRowProcessor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author ckelley@rti.org
 */
public class LimsReport extends ZEPRSReport {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(LimsReport.class);

    private String reportMonth;
    private String reportYear;
    private Date beginDate;
    private Date endDate;
    private int reportMonthInt;
    private List<LabTest> labTests;
    private LastRecordImported lastImportedRecord;


    /**
     * @return Returns the reportMonth.
     */
    public String getReportMonth() {
        return reportMonth;
    }

    /**
     * @param reportMonth The reportMonth to set.
     */
    public void setReportMonth(String reportMonth) {
        this.reportMonth = reportMonth;
    }

    /**
     * @return Returns the reportYear.
     */
    public String getReportYear() {
        return reportYear;
    }

    /**
     * @param reportYear The reportYear to set.
     */
    public void setReportYear(String reportYear) {
        this.reportYear = reportYear;
    }

    /**
     * @return Returns the siteId from the super class.
     */
    public int getSiteId() {
        return super.getSiteId();
    }

    /**
     * @return Returns the siteName from the super class.
     */
    public String getSiteName() {
        return super.getSiteName();
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

    public int getReportMonthInt() {
        return reportMonthInt;
    }

    public void setReportMonthInt(int reportMonthInt) {
        this.reportMonthInt = reportMonthInt;
    }

    public static Log getLog() {
        return log;
    }

    public static void setLog(Log log) {
        LimsReport.log = log;
    }

    /**
	 * @return the labTests
	 */
	public List<LabTest> getLabTests() {
		return labTests;
	}

	/**
	 * @param labTests the labTests to set
	 */
	public void setLabTests(List<LabTest> labTests) {
		this.labTests = labTests;
	}


	/**
	 * @return the lastImportedRecord
	 */
	public LastRecordImported getLastImportedRecord() {
		return lastImportedRecord;
	}

	/**
	 * @param lastImportedRecord the lastImportedRecord to set
	 */
	public void setLastImportedRecord(LastRecordImported lastImportedRecord) {
		this.lastImportedRecord = lastImportedRecord;
	}

	/**
     *
     * @param beginDate
     * @param endDate
     */
    public void loadReport(Date beginDate, Date endDate) {

        int siteID = super.getSiteId();

        // set the month and year for the report
        this.setReportMonth(ZEPRSUtils.getReportMonth(beginDate, endDate));
        this.setReportYear(ZEPRSUtils.getReportYear(beginDate, endDate));
/*
        Calendar gc = new GregorianCalendar();
        gc.setTime(endDate);
        int maxDate = gc.getActualMaximum(Calendar.DAY_OF_MONTH);
        int month = gc.get(Calendar.MONTH);
        int year = gc.get(Calendar.YEAR);
        gc.set(year, month, 0, 0, 0, 0);
        gc.add(Calendar.DAY_OF_MONTH, 1);
        Calendar monthEndCal = new GregorianCalendar();
        monthEndCal.set(year, month, maxDate, 0, 0, 0);

        // re-assign values for begin/endDate
        beginDate = new Date(gc.getTime().getTime());
        endDate = new Date(monthEndCal.getTime().getTime());*/

        this.setBeginDate(beginDate);
        this.setEndDate(endDate);
        // setup datasource
        Connection conn = null;
        try {

            conn = ZEPRSUtils.getZEPRSConnection();
            List<LabTest> items = null;
            try {
            	items = LabTestDAO.getLabTests(conn, beginDate, endDate);
            	this.setLabTests(items);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

     // fetch id of most recent LIMS record
		String fileName = Constants.ARCHIVE_PATH + "lims.xml";
		lastImportedRecord = null;
		try {
			lastImportedRecord = (LastRecordImported) XmlUtils.getOne(fileName);
		} catch (FileNotFoundException e) {
			log.debug("No lims xml file - importing entire lims db.");
		} catch (IOException e) {
			log.debug("No lims xml file - importing entire lims db.");
		}
    }
}
