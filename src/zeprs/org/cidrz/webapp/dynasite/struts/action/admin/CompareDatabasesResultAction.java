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

import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Principal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.dao.SiteDAO;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.utils.CompareToBuilder;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.DateUtils;
import org.cidrz.webapp.dynasite.utils.XmlUtils;
import org.cidrz.webapp.dynasite.utils.struts.ParameterActionForward;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.valueobject.Pregnancy;
import org.cidrz.webapp.dynasite.valueobject.Server;
import org.cidrz.webapp.dynasite.valueobject.Site;
import org.cidrz.webapp.dynasite.valueobject.SiteComparisonReport;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.io.WireFeedInput;
import com.sun.syndication.io.XmlReader;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.mapper.CannotResolveClassException;

/**
 * Compare patient records using rss/xml files.
 * Created by IntelliJ IDEA.
 * User: Chris Kelleu
 * Date: Mar 20, 2006
 * Time: 3:45:34 PM
 */
public class CompareDatabasesResultAction extends BaseAction {
	/**
	 * Commons Logging instance.
	 */
	private static Log log = LogFactory.getFactory().getInstance(CompareDatabasesResultAction.class);

	protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String resultsFilename= null;
		String url = null;
		if (request.getParameter("resultsFilename") != null) {
			resultsFilename = String.valueOf(request.getParameter("resultsFilename"));
		}

		SiteComparisonReport siteReport = (SiteComparisonReport) XmlUtils.getOne(resultsFilename);
		/*HashMap<String, Patient> missingPatientMap = siteReport.getMissingRemotePatientMap();
		Set<Map.Entry<String, Patient>> missingPatientSet = missingPatientMap.entrySet();*/



		request.setAttribute("siteReport", siteReport);

        return mapping.findForward("success");
	}

}
