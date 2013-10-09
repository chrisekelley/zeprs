/**
 *
 */
package org.cidrz.webapp.dynasite.struts.action.admin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.project.zeprs.valueobject.gen.LabTest;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.utils.admin.ImportLimsRecords;

/**
 * @author ckelley
 *
 */
public class ImportLimsAction extends BaseAction {

	/**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(ImportLimsAction.class);

    protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {


	// setup clock
    Calendar cal = new GregorianCalendar();
    Date starttime = cal.getTime();
    long long_starttime = starttime.getTime();
    // setup messaging
    StringBuffer sbuf = new StringBuffer();
    String message = null;
    String combinedMessage = null;
    String previewImport = request.getParameter("previewImport");
    String zeprsOnly = request.getParameter("zeprsOnly");
    ArrayList<LabTest> list = null;

 if (previewImport == null & zeprsOnly == null) {
	 // don't do anything, just send to page
 } else {
	    // import
		try {
		    if (previewImport == null) {
		    	previewImport = "true";
		    }
		    if (zeprsOnly == null) {
		    	zeprsOnly = "false";
		    }
			list = ImportLimsRecords.importRecords(Boolean.valueOf(previewImport), Boolean.valueOf(zeprsOnly), true);
		} catch (Exception e) {
			log.debug(e);
			request.setAttribute("exception", e);
		    return mapping.findForward("error");
		}
 }
   // Stop clock and calculate time elapsed
    Calendar cal2 = new GregorianCalendar();
    Date endtime = cal2.getTime();
    long long_endtime = endtime.getTime();
    long difference = long_endtime - long_starttime;
    if (list != null && list.size() == 0) {
    	combinedMessage = "No records to import.";
    }

    request.setAttribute("starttime", String.valueOf(long_starttime));
    request.setAttribute("endtime", String.valueOf(long_endtime));
    request.setAttribute("report", "LIMS Import:");
    request.setAttribute("difference", String.valueOf(difference));
    request.setAttribute("message", combinedMessage);
    request.setAttribute("list", list);
    request.setAttribute("zeprsOnly", zeprsOnly);
    return mapping.findForward("success");

    }
}
