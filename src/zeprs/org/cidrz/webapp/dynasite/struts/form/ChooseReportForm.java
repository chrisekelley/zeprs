package org.cidrz.webapp.dynasite.struts.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Mar 25, 2005
 * Time: 4:55:38 PM
 */
public class ChooseReportForm extends ActionForm {
    private String report = null;
	private String bdate = null;
	private String edate = null;

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

	public void reset (ActionMapping mapping, HttpServletRequest request) {
		report = null;;
	}
}
