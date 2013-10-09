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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.webapp.dynasite.dao.SiteDAO;
import org.cidrz.webapp.dynasite.dao.SubscriptionDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.admin.Rss;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Site;
import org.cidrz.webapp.dynasite.valueobject.Subscription;

import com.sun.syndication.feed.rss.Channel;

/**
 * Imports patient records from an rss feed.
 *
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Mar 8, 2006
 *         Time: 1:08:00 PM
 */
public class ImportFeedAction extends BaseAction {
	/**
	 * Commons Logging instance.
	 */
	private static Log log = LogFactory.getFactory().getInstance(ImportFeedAction.class);

	protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Date generateStatusDate = null;
		Map statusMap = DynaSiteObjects.getStatusMap();
		if (statusMap.get("RSS-Gen-date")!= null) {
			generateStatusDate = (Date) statusMap.get("RSS-Gen-date");
		}
		Date now = new Date(System.currentTimeMillis());
		Connection conn = null;
		try {
			conn = DatabaseUtils.getSpecialRootConnection("zepadmin");
			StringBuffer comments = new StringBuffer();
			String urlString = null;
			Boolean view = false;
			Long subscriptionId = null;
			Long start = null;
			Long reload = null;
			String patientId = null;
			String message = null;

			if (generateStatusDate == null) {
				statusMap.put("RSS-Gen-date", now);

				if (request.getParameter("url") != null) {
					urlString = request.getParameter("url");
				}
				if (request.getParameter("view") != null) {
					if (request.getParameter("view").equals("1")) {
						view = Boolean.TRUE;
					}
				}
				if (request.getParameter("subscriptionId") != null) {
					subscriptionId = Long.valueOf(request.getParameter("subscriptionId"));
				}
				if (request.getParameter("start") != null) {
					start = Long.valueOf(request.getParameter("start"));
				}
				if (request.getParameter("reload") != null) {
					reload = Long.valueOf(request.getParameter("reload"));
				}
				if (request.getParameter("patientId") != null) {
					patientId = String.valueOf(request.getParameter("patientId"));
				}
                statusMap.put("RSS-message", "Importing Feed from" + urlString);
    			try {
					Subscription subscription = (Subscription) SubscriptionDAO.getOne(conn, urlString);
	    			String siteAbbrev = subscription.getSite();
	    			if (siteAbbrev == null) {
						// import it manually
						Channel channel = Rss.downloadRssFile(urlString);
						siteAbbrev = channel.getTitle();
						SubscriptionDAO.updateSite(conn, subscriptionId, siteAbbrev, null);
	    			}
	    			Site site;
					try {
						site = (Site) SiteDAO.getOne(conn, siteAbbrev);
		    			Long siteId = site.getId();
						message = Rss.importFeed(conn, urlString, view, comments, start, siteId, reload, patientId);
					} catch (ObjectNotFoundException e) {
						log.debug("Site not found for " + siteAbbrev);
					}
				} catch (ObjectNotFoundException e) {
					log.debug("Subscription not found for urlString: " + urlString);
				}

				request.setAttribute("message", message);

			} else {
				log.info("Busy w/ another process. Try importing a feed later.");
			}
		} catch (ConnectException e) {
			String message = "Unable to connect to remote site.";
			log.error(message);
			request.setAttribute("message", message);
			request.setAttribute("exception", e);
			return mapping.findForward("error");
		}  catch (FileNotFoundException e) {
			String message = "Unable to get file.";
			log.error(message + " Cause: " + e.getClass().toString() + " - " + e.getMessage());
			log.error(e);
			e.printStackTrace();
			//request.setAttribute("message", message);
			//request.setAttribute("exception", e);
			//return mapping.findForward("error");
		}  catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} finally {
			generateStatusDate = (Date) statusMap.get("RSS-Gen-date");
			if (generateStatusDate != null && generateStatusDate == now) {
				statusMap.remove("RSS-Gen-date");
                statusMap.remove("RSS-message");
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return mapping.findForward("success");
	}

}
