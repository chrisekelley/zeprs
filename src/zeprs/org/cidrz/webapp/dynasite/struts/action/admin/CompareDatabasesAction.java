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
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

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
import org.cidrz.webapp.dynasite.utils.FileUtils;
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
public class CompareDatabasesAction extends BaseAction {
	/**
	 * Commons Logging instance.
	 */
	private static Log log = LogFactory.getFactory().getInstance(CompareDatabasesAction.class);

	protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/**
		 * Fields you want to exclude from the comparison
		 * prevpregnancies: zeprs_pregnancy_id
		 * referral_reasons: encounter_id
		 */
		String resultsFilename = null;
		String[] excludeFields = new String[]{"id","importId","zeprs_pregnancy_id","encounter_id","field1920","field1917"};
		//assemble the items we're going to pass in the request
		HttpSession session = request.getSession();
		// set the session to 90 minutes - 5400 seconds.
		session.setMaxInactiveInterval(5400);
		Principal userPrincipal = request.getUserPrincipal();
		String username = userPrincipal.getName();
		Connection conn = null;
		//Connection remoteConn = null;
		String message = null;
		StringBuffer sbuf = new StringBuffer();
		Long localSiteId = null;
		//Long remoteSiteId = null;
		Site localSite = null;
		Site remoteSite = null;
		String siteAbbrev= null;
		String url = null;
		Timestamp beginTimestamp = null;
		Timestamp endTimestamp = null;
		if (request.getParameter("siteAbbrev") != null) {
			siteAbbrev = String.valueOf(request.getParameter("siteAbbrev"));
		}
		if (request.getParameter("url") != null) {
			url = String.valueOf(request.getParameter("url"));
		}
		if (request.getParameter("bdate") != null) {
			Date beginDate = Date.valueOf(String.valueOf(request.getParameter("bdate")));
			beginTimestamp = new Timestamp(beginDate.getTime());
		}
		if (request.getParameter("edate") != null) {
			Date endDate = Date.valueOf(String.valueOf(request.getParameter("edate")));
			endTimestamp = new Timestamp(endDate.getTime());
		} else {
			endTimestamp = new Timestamp(System.currentTimeMillis());
		}
		//List<String> localPatientToSync = new ArrayList<String>();
		List<String> patientSyncList = new ArrayList<String>();

		String DATE_FORMAT = "yyyy-MM-dd";
		java.util.Calendar cal1 = java.util.Calendar.getInstance();
		cal1.add(java.util.Calendar.DATE, -1);
		java.util.Date date1daypast = cal1.getTime();
		java.text.SimpleDateFormat sdf1 = new java.text.SimpleDateFormat(DATE_FORMAT);
		sdf1.setTimeZone(TimeZone.getDefault());
		String date1daypastStr = sdf1.format(date1daypast);
		java.sql.Date date1daypastSql =  java.sql.Date.valueOf(date1daypastStr);
		//Timestamp date1daypast = new Timestamp(c4.getTime().getTime());
		//java.sql.Date dateNow = DateUtils.getNow();
		java.util.Calendar cal2 = java.util.Calendar.getInstance();
		cal2.add(java.util.Calendar.DATE, 1);
		java.util.Date date1DayFuture = cal2.getTime();
		java.text.SimpleDateFormat sdf2 = new java.text.SimpleDateFormat(DATE_FORMAT);
		sdf2.setTimeZone(TimeZone.getDefault());
		String date1DayFutureStr = sdf2.format(date1DayFuture);
		java.sql.Date date1DayFutureSql =  java.sql.Date.valueOf(date1DayFutureStr);

		String location = url.replace("/archive/rss.xml", "");
		Server server = new Server();
		server.setLocation(location.replace("http://", ""));
		server.setUsername("zepadmin");
		server.setPassword("zepadmin11");
		server.setUrl(Constants.PATIENT_GET_URL);
		String[] arr1 = location.split(":");
		String ipAddress = arr1[1].replace("//", "");
		SiteComparisonReport siteReport = new SiteComparisonReport();
		siteReport.setLocalSiteAbbrev(siteAbbrev);
		try {
			//open connection on local site
			conn = DatabaseUtils.getZEPRSConnection(username);
			URL feedUrl = new URL(url);
			HttpURLConnection httpcon = (HttpURLConnection) feedUrl.openConnection();
			boolean isProxied = httpcon.usingProxy();
			if (isProxied) {
				log.debug(" Proxied: " + isProxied);
			}
			String encoding = new sun.misc.BASE64Encoder().encode("zepadmin:zepadmin11".getBytes());
			httpcon.setRequestProperty("Authorization", "Basic " + encoding);
			final WireFeedInput input = new WireFeedInput();
			WireFeed rssFeed = null;
			XmlReader reader = null;
			/*String message = null;
	        String siteAbbrev = null;*/
			Channel channel = null;
			java.util.Date lastBuildDate = null;
			List items = null;
			try {
				reader = new XmlReader(httpcon);
				rssFeed = input.build(reader);
				rssFeed.setFeedType("rss_2.0");
				channel = (Channel) rssFeed;
				items = channel.getItems();
				siteAbbrev = channel.getTitle();
				lastBuildDate = channel.getLastBuildDate();
			} catch (ConnectException e2) {
				log.debug("Unable to connect to : " + url + " Error: " + e2);
			}
			localSite = (Site) SiteDAO.getOne(conn, siteAbbrev);
			localSiteId = localSite.getId();
			ResultSet localPatientsRs = null;
			if (beginTimestamp != null) {
				localPatientsRs = PatientDAO.getAllSiteRS(conn, localSiteId, beginTimestamp, endTimestamp);
			} else {
				localPatientsRs = PatientDAO.getAllSiteRS(conn, localSiteId);
			}
			int localSize = 0;
			int remoteSize = 0;
			HashMap<String, Patient> localPatientMap = new HashMap<String, Patient>();
			HashMap<String, Patient> remotePatientMap = new HashMap<String, Patient>();
			// Process the local patients
			while (localPatientsRs.next()) {
				localSize++;
				Long patientId = localPatientsRs.getLong("id");
				String zeprsId = localPatientsRs.getString("district_patient_id");
				String surname = localPatientsRs.getString("surname");
				Timestamp lastModified = localPatientsRs.getTimestamp("last_modified");
				Long clinicId = localPatientsRs.getLong("site_id");   // clinic the patient belongs to
				Patient patient = new Patient();
				patient.setId(patientId);
				patient.setDistrictPatientid(zeprsId);
				patient.setSurname(surname);
				patient.setLastModified(lastModified);
				patient.setSiteId(clinicId);
				// get the siteAbbrev from the parent's record only
				/*try {
					localSite = (Site) DynaSiteObjects.getClinicMap().get(clinicId);
					siteAbbrev = localSite.getAbbreviation();
				} catch (Exception e) {
					log.error("Incorrect site data for patient: " + zeprsId + " clinicId: " + clinicId);
				}*/
				/*String filePath = org.cidrz.webapp.dynasite.Constants.ARCHIVE_PATH + siteAbbrev + Constants.pathSep + "local" + Constants.pathSep;
				String fileName = getPatientFilename(zeprsId, surname, patientId);
				// write to the index
				indexMap.put(zeprsId, siteAbbrev + Constants.pathSep + "local" + Constants.pathSep + fileName);
				// check if the xml file exists. This is useful when testing - you may not have all of the xml files generated yet.
				boolean forceXml = false;
				if (checkXml != null) {
					File filecheck = new File(filePath + fileName);
					if (filecheck.length() == 0) {
						log.debug("Re-rendering file for : " + fileName + " in " +  siteAbbrev + "/local.");
						forceXml = true;
					}
				}*/
				localPatientMap.put(zeprsId, patient);
			}
			siteReport.setLocalSize(localSize);
			// Process the remote patients
			if (items != null) {
				for (int i = 0; i < items.size(); i++) {
					Item item = (Item) items.get(i);
					Long clinicHomeId = null;
					if (item.getAuthor() != null) {
						clinicHomeId = Long.valueOf(item.getAuthor());
					}
					String zeprsFilename = item.getTitle();
					java.util.Date pubDate = item.getPubDate();
					/*boolean processRemotePatient = true;
					if (beginTimestamp != null) {
						if (pubDate.getTime() >= beginTimestamp.getTime() && pubDate.getTime() <= endTimestamp.getTime()) {
							processRemotePatient = false;
						}
					}*/
					String zeprsId = zeprsFilename.replace(".xml", "");
					//java.util.Date pubDate = item.getPubDate();
					/*Site site = (Site) DynaSiteObjects.getClinicMap().get(clinicHomeId);
					siteAbbrev = site.getAbbreviation();*/
					//URL patientUrl = new URL(link);
					Patient patient = new Patient();
					//patient.setId(patientId);
					patient.setDistrictPatientid(zeprsId);
					//patient.setSurname(surname);
					//patient.setLastModified(pubDate);
					patient.setSiteId(clinicHomeId);
					//if (processRemotePatient == true) {
						remotePatientMap.put(zeprsId, patient);
					//}
				}
			}
			siteReport.setRemoteSize(remoteSize);
			// identify which patient records are different
			// look through the localPatientMap and compare to remotePatientMap
			Set<Map.Entry<String,Patient>> localPatientSet = localPatientMap.entrySet();
			log.debug("localPatientMap size: " + localPatientSet.size());
			String jsessionid = null;
			// No need to process this list if the remotePatientMap is empty.
			if (remotePatientMap.size() == 0) {
				log.debug("remotePatientMap is empty");
			} else {
				int cnt=0;
				int total = 0;
				for (Map.Entry<String, Patient> entry : localPatientSet) {
					if ((DynaSiteObjects.getStatusMap().get("Halt-Database-Comparison")!= null) && (DynaSiteObjects.getStatusMap().get("Halt-Database-Comparison").equals("true"))) {
						log.debug("Halting DB comparison: Halt-Database-Comparison = true");
						break;
					}
					cnt++;
					total++;
					if (cnt%500 == 0) {
						log.debug("Comparison progress: " + total + "/" + localPatientSet.size());
						cnt = 0;
					}
					Patient localPatient = (Patient) entry.getValue();
					Long localPatientId = localPatient.getId();
					String zeprsId = entry.getKey();
					Patient localPatientGraph = XmlUtils.generatePatient(conn, localPatientId, false);
					// fetch patient from remote map
					Patient remotePatient = remotePatientMap.get(zeprsId);
					if (remotePatient == null) {
						siteReport.getMissingRemotePatientMap().put(zeprsId, localPatient);
						if (!patientSyncList.contains(zeprsId)) {
							patientSyncList.add(zeprsId);
						}
					} else {
						// Long remotePatientId = remotePatient.getId();
						//Patient remotePatientGraph = XmlUtils.generatePatient(remoteConn, remotePatientId, false);
						Patient remotePatientGraph = null;
						/*try {
						//remotePatientGraph = XmlUtils.downloadPatientRecord(zeprsId, server);
					} catch (CannotResolveClassException e) {
						log.debug("Problem with xml for : " + zeprsId + " Error: " + e.getMessage());
					}*/

						//Patient patient = null;

						//String location = server.getLocation();
						//String url = server.getUrl();
						//String username = server.getUsername();
						// location = server.getLocation();
						location = "192.168.21.1:8088";
						//location = "localhost:8088";
						String password = server.getPassword();
						String authString = "?j_username="+username+"&j_password="+password;
						String jsessionidString = "jsessionid=";
						String homeUrl = "http://" + location + "/zeprs";
						//String homeUrl = "http://" + location + "/zeprs/home.do;jsessionid=x";
						HttpState initialState = new HttpState();
						HttpClient httpclient = new HttpClient();
						if (jsessionid == null) {
							// first get  a session
							// System.out.println("Target URL: " + homeUrl);

							httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
							httpclient.setState(initialState);

							GetMethod httpget = new GetMethod(homeUrl);
							httpget.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
							int result;
							try {
								result = httpclient.executeMethod(httpget);
								//log.debug("Response status code: " + result);
								String returnedPath = httpget.getPath();
								//log.debug("returnedPath " + returnedPath);
								jsessionid = returnedPath.replace("/zeprs/home.do;jsessionid=", "");
								// now login w/ credentials in the querystring
								String loginUrl = "http://" + location + "/zeprs/j_security_check" + ";" + jsessionidString + jsessionid + authString;
								//log.debug("loginUrl " + loginUrl);
								httpget = new GetMethod(loginUrl);
								httpget.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
								result = httpclient.executeMethod(httpget);
								//log.debug("Response status code: " + result);
								returnedPath = httpget.getPath();
								//log.debug("returnedPath " + returnedPath);
							} catch (ConnectException e) {
								log.debug("Unable to connect to : " + location + " Error: " + e);
							}
						}

						// If there was a ConnectException, jsessionid wil be null
						if (!jsessionid.equals("null")) {
							// now go to the app
							String importPatientUrl = "http://" + server.getLocation() + server.getUrl() + ";" + jsessionidString + jsessionid + "?zeprsId=" + zeprsId ;
							//String importPatientUrl = "http://192.168.21.1:8088/archive/rss.xml;" + jsessionidString + jsessionid + "?zeprsId=" + zeprsId ;
							GetMethod httpget = null;
							try {
								httpget = new GetMethod(importPatientUrl);
								httpget.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
								int result = httpclient.executeMethod(httpget);
								int statuscode = httpget.getStatusCode();
								InputStream inputStream = httpget.getResponseBodyAsStream();
								try {
									remotePatientGraph = (Patient) XmlUtils.getOne(inputStream);
								} catch (ConversionException e) {
									log.debug("Error Converting fields from record: " + zeprsId);
								} catch (CannotResolveClassException e) {
									log.debug("importPatientUrl: " + importPatientUrl + " statuscode: " + statuscode);
								}
							} catch (IllegalArgumentException e1) {
								log.debug("Error fetching " + importPatientUrl + " message: " + e1.getMessage());
							}
						}

						if (remotePatientGraph != null) {
							// populate the localPregnancyMap and localPregnancyEncounterMap
							// localPregnancyEncounterMap stores pregnancyUuid:currentLocalEncounterMap(encounterData.getUuid():encounterData)
							HashMap<String,HashMap<String, EncounterData>> localPregnancyEncounterMap = new HashMap<String,HashMap<String, EncounterData>>();
							// localPregnancyMap stores pregnancyUuid:zeprsId
							HashMap<String, String> localPregnancyMap = new HashMap<String, String>();
							String localZeprsId = localPatientGraph.getDistrictPatientid();
							List<Pregnancy> localPregnancyList = localPatientGraph.getPregnancyList();
							for (Pregnancy pregnancy : localPregnancyList) {
								HashMap<String, EncounterData> currentLocalEncounterMap = new HashMap<String, EncounterData>();  // create a hashmap that stores the uuid and modified date, if any
								List<EncounterData> localEncounters = pregnancy.getEncounters();
								for (int i = 0; i < localEncounters.size(); i++) {
									EncounterData encounterData = (EncounterData) localEncounters.get(i);
									encounterData.setZeprsId(localZeprsId);
									currentLocalEncounterMap.put(encounterData.getUuid(), encounterData);
								}
								localPregnancyEncounterMap.put(pregnancy.getUuid(), currentLocalEncounterMap);
								localPregnancyMap.put(pregnancy.getUuid(), localZeprsId);
							}

							// populate the remotePregnancyMap and remotePregnancyEncounterMap
							// remotePregnancyEncounterMap stores pregnancyUuid:currentLocalEncounterMap(encounterData.getUuid():encounterData)
							HashMap<String,HashMap<String, EncounterData>> remotePregnancyEncounterMap = new HashMap<String,HashMap<String, EncounterData>>();
							// localPregnancyMap stores pregnancyUuid:zeprsId
							HashMap<String, String> remotePregnancyMap = new HashMap<String, String>();
							String remoteZeprsId = remotePatientGraph.getDistrictPatientid();
							List<Pregnancy> remotePregnancyList = remotePatientGraph.getPregnancyList();
							for (Pregnancy pregnancy : remotePregnancyList) {
								HashMap<String, EncounterData> currentRemoteEncounterMap = new HashMap<String, EncounterData>();  // create a hashmap that stores the uuid and modified date, if any
								List<EncounterData> remoteEncounters = pregnancy.getEncounters();
								for (int i = 0; i < remoteEncounters.size(); i++) {
									EncounterData encounterData = (EncounterData) remoteEncounters.get(i);
									encounterData.setZeprsId(localZeprsId);
									currentRemoteEncounterMap.put(encounterData.getUuid(), encounterData);
								}
								remotePregnancyEncounterMap.put(pregnancy.getUuid(), currentRemoteEncounterMap);
								remotePregnancyMap.put(pregnancy.getUuid(), remoteZeprsId);
							}

							// now see if they all match
							// loop through the local pregnancy encounters and compare to remote

							Set<Map.Entry<String,HashMap<String, EncounterData>>> localPregnancySet = localPregnancyEncounterMap.entrySet();
							for (Map.Entry<String, HashMap<String, EncounterData>> entryLocal : localPregnancySet) {
								String localPregnancyUuid = entryLocal.getKey();
								HashMap<String, EncounterData> currentLocalEncounterMap = entryLocal.getValue();
								HashMap<String, EncounterData> currentRemoteEncounterMap = remotePregnancyEncounterMap.get(localPregnancyUuid);
								zeprsId = localPregnancyMap.get(localPregnancyUuid);
								if (currentRemoteEncounterMap == null) {
									// put in the missing list
									siteReport.getMissingRemotePregnancyMap().put(localPregnancyUuid, zeprsId);
									if (!patientSyncList.contains(zeprsId)) {
										patientSyncList.add(zeprsId);
									}
								} else {
									// loop through this list
									Set<Map.Entry<String, EncounterData>> localPregnancyEncounterSet = currentLocalEncounterMap.entrySet();
									for (Map.Entry<String, EncounterData> entryLocalEncounter : localPregnancyEncounterSet) {
										String localEncounterUuid = entryLocalEncounter.getKey();
										EncounterData localPregnancyEncounter = entryLocalEncounter.getValue();
										EncounterData remotePregnancyEncounter = currentRemoteEncounterMap.get(localEncounterUuid);
										if (remotePregnancyEncounter == null) {
											siteReport.getMissingRemotePregnancyEncounterMap().put(localEncounterUuid, localPregnancyEncounter);
											if (!patientSyncList.contains(zeprsId)) {
												patientSyncList.add(zeprsId);
											}
										} else {
											// compare the two encounters
											//String[] excludeFields = new String[]{"id,importId"};
											ArrayList results = CompareToBuilder.reflectionCompareResults(localPregnancyEncounter, remotePregnancyEncounter, false, EncounterData.class, excludeFields);
											if (results.size() > 0) {
												// todo - Perhaps we'lll add the encounter later. Concerned about memory.
												siteReport.getIncorrectRemotePregnancyEncounterMap().put(localEncounterUuid, null);
												siteReport.getIncorrectRemotePregnancyEncounterResultsMap().put(localEncounterUuid, results);
												if (!patientSyncList.contains(zeprsId)) {
													patientSyncList.add(zeprsId);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

			siteReport.setPatientSyncList(patientSyncList);

			Date datenow = DateUtils.getNow();
			siteReport.setReportDate(datenow);
			String fileDate = DateUtils.getFormattedDateDashes(datenow.getTime());
			String archiveFilename = Constants.ARCHIVE_PATH + siteAbbrev + Constants.pathSep +  "syncReports" + Constants.pathSep + "report" + fileDate + ".xml";
			XmlUtils.save(siteReport, archiveFilename);
			resultsFilename = Constants.ARCHIVE_PATH + siteAbbrev + Constants.pathSep +  "syncReports" + Constants.pathSep + "report.xml";
			FileUtils.copyQuick(archiveFilename, resultsFilename);
			int missingRemotePatients = siteReport.getMissingRemotePatientMap().size();
			int missingRemotePregnancies = siteReport.getMissingRemotePregnancyMap().size();
			int missingRemotePregnancyEncounters = siteReport.getMissingRemotePregnancyEncounterMap().size();
			int incorrectRemotePregnancyEncounters = siteReport.getIncorrectRemotePregnancyEncounterMap().size();
			sbuf.append("Missing Remote Patients: " + missingRemotePatients);
			sbuf.append("<br>");
			if (missingRemotePatients > 0) {
				sbuf.append("<p>");
				sbuf.append(siteReport.getMissingRemotePatientMap());
				sbuf.append("</p>");
			}
			sbuf.append("Missing Remote Pregnancies: " + missingRemotePregnancies);
			sbuf.append("<br>");
			sbuf.append("Missing Remote Pregnancy Encounters: " + missingRemotePregnancyEncounters);
			sbuf.append("<br>");
			sbuf.append("Incorrect Remote Pregnancy Encounters: " + incorrectRemotePregnancyEncounters);
			message = sbuf.toString();
			log.debug(message);
			log.debug("patientSyncList: " + patientSyncList);
			request.setAttribute("siteReport", siteReport);
			request.setAttribute("resultsFilename", resultsFilename);

		} catch (ServletException e) {
			log.error(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			/*if (remoteConn != null && !remoteConn.isClosed()) {
				remoteConn.close();
			}*/

		}
		request.setAttribute("date1daypast", date1daypastSql);
		request.setAttribute("date1DayFuture", date1DayFutureSql);
		ParameterActionForward forward = new ParameterActionForward(mapping.findForward(SUCCESS_FORWARD));
		forward.addParameter("resultsFilename",resultsFilename);
		//return mapping.findForward("success");
		return forward;
	}
}
