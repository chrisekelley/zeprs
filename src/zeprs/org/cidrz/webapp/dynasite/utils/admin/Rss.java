/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.utils.admin;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.NoRouteToHostException;
import java.net.SocketException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.dao.ArchiveDAO;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.dao.SubscriptionDAO;
import org.cidrz.webapp.dynasite.dao.UpdateLogDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.utils.DateUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.utils.XmlUtils;
import org.cidrz.webapp.dynasite.valueobject.ArchiveLog;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.valueobject.Publisher;
import org.cidrz.webapp.dynasite.valueobject.Site;
import org.cidrz.webapp.dynasite.valueobject.Subscription;
import org.cidrz.webapp.dynasite.valueobject.UpdateLog;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Description;
import com.sun.syndication.feed.rss.Guid;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.ParsingFeedException;
import com.sun.syndication.io.WireFeedInput;
import com.sun.syndication.io.WireFeedOutput;
import com.sun.syndication.io.XmlReader;
import com.thoughtworks.xstream.io.StreamException;

/**
 * Manages generation of RSS files and patient record XML files
 *
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Mar 20, 2006
 *         Time: 1:21:32 PM
 */
public class Rss {
    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(Rss.class);

    /**
     * Exports a list of patients at the clinic and their records.
     * Compare a list of all patients in the rss file last published by the server with a list of patients in the system.
     * Publish any changed records if they are not equal
     * If site is the master data center (such as ZEPRS), loops through each site's patient list and creates RSS file for each site.
     * Note that a patient's xml record could be listed in more than one site -
     * The patient listing for each site selects  from patient_status.site_id.
     * Download the remote site listing and see if there are adidtional patients who may have been transferred to another site - UTH for instance.
     * This enables sites to see records of their regular patients who were referred to another site.
     * Also creates an index which is used on remote sites for finding patient records within the imported archive.
     * @param conn
     * @param siteAbbrev - if siteabbrev = ZEP,, export all sites w/ their own rss feed.
     * @param siteId
     * @param checkXml             - true if you want to check if xml files have been generated.
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws PersistenceException
     * @throws InvocationTargetException
     * @throws FeedException
     */
    public static String createSiteRssFiles(Connection conn, String siteAbbrev, Long siteId, Boolean checkXml) throws SQLException, ServletException, ObjectNotFoundException, IOException, ClassNotFoundException, IllegalAccessException, PersistenceException, InvocationTargetException, FeedException {
    	String message = null;
    	StringBuffer sbuf = new StringBuffer();
    	Timestamp beginDate = null;
    	Timestamp endDate = new Timestamp(System.currentTimeMillis());
        Map statusMap = DynaSiteObjects.getStatusMap();
    	Publisher publisher = null;
    	String publisherFile = Constants.ARCHIVE_PATH + "publisher.xml";
    	String ipAddress = null;
    	Boolean renderAllSites = false;
    	try {
    		publisher = (Publisher) XmlUtils.getOne(publisherFile);
    		ipAddress = publisher.getUrl();
    	} catch (FileNotFoundException e) {
    		log.debug("publisher.xml not available - cannot render site rss files.");
    	}

    	if (publisher != null) {
    		// check the db for the date of the last archive.
    		// Fetch list of updated patients. This list is used to generate each patient's updated xml file
    		ResultSet updatedPatients = null;
    		try {
    			ArchiveLog mostRecentArchive = (ArchiveLog) ArchiveDAO.getOne(conn);
    			beginDate = mostRecentArchive.getArchived();
    			updatedPatients = PatientDAO.getAllDate(conn, beginDate, endDate);
    		} catch (SQLException e) {
    			e.printStackTrace();
    		} catch (ServletException e) {
    			e.printStackTrace();
    		} catch (ObjectNotFoundException e) {
    			// This is the first time this has been run - archive all.
    			updatedPatients = PatientDAO.getAllRS(conn);
    			renderAllSites = true;
    		}

    		// update date of last archive before any more records get updated.
    		Long archiveId = (Long) ArchiveDAO.save(conn, endDate, (long) 1);
    		HashMap updatedMap = new HashMap();
    		while (updatedPatients.next()) {
    			Long updatedId = updatedPatients.getLong("id");
    			updatedMap.put(updatedId, updatedId);
    		}
    		List<Site> clinics = null;

    		if (siteAbbrev.equals("ZEP")) {
    			// loop thorough all of the sites and render an rss file for each one.
    			clinics = DynaSiteObjects.getClinics();
    			// todo: reset to the big list - preset to KAL and AIR
    			/*clinics = new ArrayList<Site>();
    			Site site = (Site) DynaSiteObjects.getClinicMap().get(Long.valueOf(16));
    			clinics.add(site);
    			site = (Site) DynaSiteObjects.getClinicMap().get(Long.valueOf(1));
    			clinics.add(site);*/
    		} else {
    			clinics = new ArrayList<Site>();
    			Site site = (Site) DynaSiteObjects.getClinicMap().get(siteId);
    			clinics.add(site);
    		}
    		// create the archive listing
    		// StringBuffer indexBuffer = new StringBuffer();
    		HashMap<String, String> indexMap = new HashMap<String, String>();
    		int currentSiteNum = 0;
			int archivedPatientsAllSites = 0;
    		for (Site site : clinics) {
    			currentSiteNum++;
    			int archivedPatientsThisSite = 0;
    			siteAbbrev = site.getAbbreviation();
    			siteId = site.getId();
    			String rssFileName = org.cidrz.webapp.dynasite.Constants.ARCHIVE_PATH + siteAbbrev + Constants.pathSep + "rss.xml";
    			Timestamp lastBuildDate = new Timestamp(System.currentTimeMillis());
    			Channel feed = new Channel();
    			String feedType = "rss_2.0";
    			feed.setFeedType(feedType);
    			feed.setDescription(siteAbbrev + " Patients");
    			String port = Constants.APP_PORT;
    			feed.setLink("http://" + ipAddress + ":" + port + "/" + "archive/" + siteAbbrev + "/rss.xml");
    			feed.setTitle(siteAbbrev);  // Title is used to identify which site the feed is from.
    			feed.setLastBuildDate(lastBuildDate);
    			List entries = new ArrayList();
    			// Loop through the list of all patients and see if their id exists in the updatedMap
    			ResultSet patientListResultSet = PatientDAO.getAllSiteRS(conn, siteId);
    			ArrayList<Patient> patientRssList = new ArrayList<Patient>();
    			while (patientListResultSet.next()) {
    				Long patientId = patientListResultSet.getLong("id");
    				String zeprsId = patientListResultSet.getString("district_patient_id");
    				String surname = patientListResultSet.getString("surname");
    				Timestamp lastModified = patientListResultSet.getTimestamp("last_modified");
    				Long clinicId = patientListResultSet.getLong("site_id");   // clinic the patient belongs to - should be the same as the site feed.
    				Patient rssPatient = new Patient();
    				rssPatient.setId(patientId);
    				rssPatient.setDistrictPatientid(zeprsId);
    				rssPatient.setSurname(surname);
    				rssPatient.setLastModified(lastModified);
    				rssPatient.setSiteId(clinicId);
    				patientRssList.add(rssPatient);
    			}
    			HashMap<String,String> patientRssMap = new HashMap<String,String>();
    			// loop through the patientRssList to weed out duplicates
    	        for (Patient patient : patientRssList) {
    	        	String zeprsId = patient.getDistrictPatientid();
    	        	patientRssMap.put(zeprsId, zeprsId);
				}
    			String rssMessage = "Generating RSS and Patient Xml for " + siteAbbrev + " :" + currentSiteNum + " out of " + clinics.size() + " sites. " +
    			updatedMap.size() + " patient records to be generated.";
    			statusMap.put("RSS-message", rssMessage);
    			if (renderAllSites == true) {
    				log.debug(rssMessage);
    			}
    			// see if there is already a subscription to this site.
    			String importFilePath = org.cidrz.webapp.dynasite.Constants.ARCHIVE_PATH + siteAbbrev + Constants.pathSep + "import" + Constants.pathSep;
    	        String importedRssPath = importFilePath + "rss.xml";
    	        //Reader reader = new BufferedReader((new FileReader(importedRssPath)));
    	        File importedRssFile = new File(importedRssPath);
    	        if (importedRssFile.exists() && importedRssFile.length() > 0) {
        	        final WireFeedInput input = new WireFeedInput();
        	        WireFeed rssFeed = null;
        	        XmlReader reader = null;
        	        Channel channel = null;
        	        reader = new XmlReader(importedRssFile);
        	        try {
						rssFeed = input.build(reader);
						rssFeed.setFeedType("rss_2.0");
	        	        channel = (Channel) rssFeed;
	        	        List items = channel.getItems();
	        	        //siteAbbrev = channel.getTitle();
	        	        // Create a list of patients the remote site is "interested" in.
	        	        // ArrayList<String> subscribedPatientList = new ArrayList<String>();
	        			ArrayList<Patient> subscribedPatientList = new ArrayList<Patient>();
	        	        for (int i = 0; i < items.size(); i++) {
	        	        	Item item = (Item) items.get(i);
	        	        	//String link = item.getLink();
	        	        	String fileName = item.getTitle();
	        	        	String zeprsId = fileName.replace(".xml", "");
	        	        	try {
								Patient rssPatient = PatientDAO.getOneFromZeprsId(conn, zeprsId);
								// check if the patient is not already in the patientRssList
								if (patientRssMap.get(zeprsId) == null) {
			        				subscribedPatientList.add(rssPatient);
								}
							} catch (ObjectNotFoundException e) {
								// Patient may not yet be in system. It should be OK.
							}
	        	        }
	        	        patientRssList.addAll(subscribedPatientList);
					} catch (ParsingFeedException e1) {
						log.debug("Unable to parse RSS feed at " + importedRssPath);
					}
        	        reader.close();
    	        }
    			//while (patientListResultSet.next()) {
        	    for (Patient rssPatient : patientRssList) {
    				Long patientId = rssPatient.getId();
    				String zeprsId = rssPatient.getDistrictPatientid();
    				String surname = rssPatient.getSurname();
    				Timestamp lastModified = rssPatient.getLastModified();
    				Long clinicId = rssPatient.getSiteId();   // clinic the patient belongs to - should be the same as the site feed.
    				// get the siteAbbrev from the parent's record only
    	    		// no longer need this part.
    				/*try {
    					site = (Site) DynaSiteObjects.getClinicMap().get(clinicId);
    					siteAbbrev = site.getAbbreviation();
    				} catch (Exception e) {
    					log.error("Incorrect site data for patient: " + zeprsId + " clinicId: " + clinicId);
    				}*/
    				String filePath = org.cidrz.webapp.dynasite.Constants.ARCHIVE_PATH + siteAbbrev + Constants.pathSep + "local" + Constants.pathSep;
    				String fileName = getPatientFilename(zeprsId, surname, patientId);
    				// write to the index
    				// String indexValue = zeprsId + ":" + siteAbbrev + Constants.pathSep + "local" + Constants.pathSep + fileName + "\n";
    				//indexBuffer.append(indexValue);
    				indexMap.put(zeprsId, siteAbbrev + Constants.pathSep + "local" + Constants.pathSep + fileName);
    				// check if the xml file exists. This is useful when testing - you may not have all of the xml files generated yet.
    				boolean forceXml = false;
    				if (checkXml != null) {
    					File filecheck = new File(filePath + fileName);
    					if (filecheck.length() == 0) {
    						log.debug("Re-rendering file for : " + fileName + " in " +  siteAbbrev + "/local.");
    						forceXml = true;
    					}
    				}
    				if (updatedMap.get(patientId) != null || forceXml) {
    					PatientRecordUtils.archivePatient(conn, patientId, siteAbbrev, filePath, fileName);
    					archivedPatientsThisSite ++;
    					archivedPatientsAllSites ++;
    					rssMessage = "Generating RSS and Patient Xml for " + siteAbbrev + ": " + currentSiteNum + " out of " + clinics.size() + " sites. " +
    					archivedPatientsAllSites + " out of " + updatedMap.size() + " patient records have been generated. " +
    					"Current file: " + filePath + fileName;
    	                statusMap.put("RSS-message", rssMessage);
    					// don't remove from the list because this patient may also be at another site (referral).
    					// updatedMap.remove(patientId);
    				}
    				if (lastModified == null) {
    					lastModified = DateUtils.generateTimestamp();
    					log.debug("Null lastModified for patient: " + zeprsId);
    				}
    				java.util.Date pubDate = new Date(lastModified.getTime());
    				Item item;
    				Description description;
    				String url = "http://" + ipAddress + ":" + port + "/archive/" + siteAbbrev + "/local/" + fileName;
    				item = new Item();
    				item.setTitle(fileName);
    				item.setLink(url);
    				item.setPubDate(pubDate);
    				item.setAuthor(clinicId.toString());
    				Guid gd = new Guid();
    				gd.setPermaLink(true);
    				gd.setValue(url);
    				item.setGuid(gd);
    				description = new Description();
    				description.setType("text/plain");
    				description.setValue(clinicId.toString());
    				item.setDescription(description);
    				entries.add(item);
    			}
    			// Open the remote site RSS file and add patients who may have been transferred
    			//ArrayList<String> remotePatientIdList = populateRemotePatientIdList(urlString);
    			//log.debug("RSS feed being generated for " + siteAbbrev + " has " + entries.size() + " patients. URL: " + rssFileName);
    			sbuf.append(entries.size() + " patients added to " + siteAbbrev + "<br/>");
    			feed.setItems(entries);
    			Writer writer = new FileWriter(rssFileName);
    			WireFeedOutput output = new WireFeedOutput();
    			output.output(feed, writer);
    			writer.close();
    			entries.clear();
    		}
			// create the archive listing
    		/*Writer indexWriter = new FileWriter(Constants.MASTER_ARCHIVE_INDEX);
			indexWriter.write(indexBuffer.toString());
			indexWriter.flush();
			indexWriter.close();*/
			XmlUtils.save(indexMap, Constants.MASTER_ARCHIVE_INDEX_XML);
    		//log.debug("writing index file to " + Constants.MASTER_ARCHIVE_INDEX_JS);
			//XmlUtils.saveJson(indexMap, Constants.MASTER_ARCHIVE_INDEX_JS);
			updatedMap.clear();
			indexMap.clear();
			String rssMessage = "Completed RSS and patient XML generation task";
            statusMap.put("RSS-message", rssMessage);
    	}

    	// Create the archive
    	//Compress.zipDirectory(Constants.ARCHIVE_PATH, Constants.MASTER_ARCHIVE_ZIP);
    	//Zip.zip(Constants.ARCHIVE_PATH, Constants.MASTER_ARCHIVE_ZIP, Constants.MASTER_ARCHIVE_CHECKSUM);
    	//Zip.CreateZipFile(new File(Constants.MASTER_ARCHIVE_ZIP), new File[]{new File(Constants.ARCHIVE_PATH)}, false);
    	message = sbuf.toString();
    	return message;
    }

    /**
     * Creates a patient id string using zeprsId. If zeprsId is not available, concats surname and patient Id.
     * appends the extension from Constants.PATIENT_RECORD_OUTPUT, which is in application.properties' patient.record.output.
     * @param zeprsId
     * @param surname
     * @param patientId
     * @return
     */
    public static String getPatientFilename(String zeprsId, String surname, Long patientId) {
        String patientName = null;
        if (zeprsId != null) {
            patientName = zeprsId;
        } else {
        	if (surname != null) {
        		if (surname.length() > 10) {
                    try {
                        patientName = surname.substring(0, 10).toLowerCase().trim() + "." + patientId;
                    } catch (Exception e) {
                        log.error(e);
                    }
                } else {
                    patientName = surname.toLowerCase().trim() + "." + patientId;
                }
        	} else {
        		log.debug("Unable to generate patient filename: surname and zeprsId are null.");
        	}
        }
        String extension = Constants.PATIENT_RECORD_OUTPUT;
        String fileName = patientName.replace("/", "-") + "." + extension;
        return fileName;
    }

    /**
     * Imports patient records from remote pc.
     *
     * @param conn
     * @param urlString - url of feed
     * @param view      - if not 0, simply parses rss feed and provides output.
     * @param comments  - messages generated during import process
     * @param start     - indicates if this is the first time this feed has been imported.
     * @param siteId    - id of site being imported.
     * @param reload
     * @param refreshPatientZeprsId
     * @return status message
     * @throws Exception
     */
    public static String importFeed(Connection conn, String urlString, Boolean view, StringBuffer comments, Long start, Long siteId, Long reload, String refreshPatientZeprsId) throws Exception, ConnectException, NoRouteToHostException {
        // download the rss file from the remote server
        URL feedUrl = new URL(urlString);
        HttpURLConnection httpcon = (HttpURLConnection) feedUrl.openConnection();
        boolean isProxied = httpcon.usingProxy();
        if (isProxied) {
            log.debug(" Proxied: " + isProxied);
        }
        // BASE64Encoder is Sun proprietary encoder. Use Apache commons Base64 codec instead.
        String credentials = new sun.misc.BASE64Encoder().encode("zepadmin:zepadmin11".getBytes());
        /*String encodeString = "zepadmin:zepadmin11";
        Base64 encoder = new Base64();
        String encoding = String.valueOf(encoder.encode(encodeString.getBytes()));*/
        httpcon.setRequestProperty("Authorization", "Basic " + credentials);

        final WireFeedInput input = new WireFeedInput();
        WireFeed rssFeed = null;
        XmlReader reader = null;
        String message = null;
        String siteAbbrev = null;
        Channel channel = null;
        java.util.Date lastBuildDate = null;

        Site site = (Site) DynaSiteObjects.getClinicMap().get(siteId);
        siteAbbrev = site.getAbbreviation();

        try {
	        String importFilePath = org.cidrz.webapp.dynasite.Constants.ARCHIVE_PATH + siteAbbrev + Constants.pathSep + "import" + Constants.pathSep;
	        // Save the feed to the local filesystem. This will be used to help the master create its own feed for this site.
	        BufferedInputStream in = null;
	        in = new BufferedInputStream(httpcon.getInputStream());
	        String importedRssPath = importFilePath + "rss.xml";
	        Writer writer = new FileWriter(importedRssPath);
	        for (int c = in.read(); c != -1; c = in.read()) {
	            writer.write(c);
	        }
	        writer.close();

	        // make a hashmap of zeprs id's:last_modified from database
	        HashMap<String,Timestamp>  localPatientMap = new HashMap<String,Timestamp>();
	        ResultSet updatedPatients = PatientDAO.getAllRS(conn);
	        while (updatedPatients.next()) {
	            String zeprsId = updatedPatients.getString("district_patient_id");
	            Timestamp lastModified = updatedPatients.getTimestamp("last_modified");
	            // Long site_id = updatedPatients.getLong("site_id");
	            String fileName = zeprsId + ".xml";
	            localPatientMap.put(fileName, lastModified);
	        }
	        int changes = 0;
	        // check what site this pc is publishing
	        Long publisherSiteId = null;
	        Publisher publisher = null;
	        String publisherFile = Constants.ARCHIVE_PATH + "publisher.xml";
	        try {
	            publisher = (Publisher) XmlUtils.getOne(publisherFile);
	            publisherSiteId = publisher.getSiteId();
	        } catch (FileNotFoundException e) {
	            // it's ok - file not created yet.
	        }
	        boolean process = true;
	        Timestamp buildDate = null;
	        try {
	            UpdateLog updateLog = (UpdateLog) UpdateLogDAO.getLastUpdate(conn, siteAbbrev);
	            buildDate = updateLog.getBuilddate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (ServletException e) {
	            e.printStackTrace();
	        } catch (ObjectNotFoundException e) {
	            //log.debug("No entries in updateLog - this must be a new site subscription for " + siteAbbrev + ". Forcing import of records for this site. ");
	            log.debug("No entries in updateLog - this must be a new site subscription for " + siteAbbrev + ". ");
	            // cekelley May 28, 2009 - disabled forced import (reload) of records. Unnecessary and time-consuming.
	            //reload = Long.valueOf("1");
	            comments.append("Subscribed to site: " + siteAbbrev + ". ");
	        }

	        File importedRssFile = new File(importedRssPath);
	        reader = new XmlReader(importedRssFile);
			rssFeed = input.build(reader);
	        rssFeed.setFeedType("rss_2.0");
	        channel = (Channel) rssFeed;
	        List items = channel.getItems();
	        siteAbbrev = channel.getTitle();
	        lastBuildDate = channel.getLastBuildDate();
	        String previousSiteAbbrev = null;

	        for (int i = 0; i < items.size(); i++) {
	            process = true;
	            Item item = (Item) items.get(i);
	            String link = item.getLink();
	            String fileName = item.getTitle();
	            Long clinicHomeId = null;
	            if (item.getAuthor() != null) {
	                clinicHomeId = Long.valueOf(item.getAuthor());
	            }
	            if (publisherSiteId != null && (publisherSiteId.longValue() != clinicHomeId.longValue()) && !publisher.getSite().getSiteAlphaId().equals("MST"))
	            {
	                process = false;
	            } else if (publisher.getSite().getSiteAlphaId().equals("MST")) {
	                process = true; // MST site will update no matter the sites conti
	            }
	            java.util.Date pubDate = item.getPubDate();
	            Timestamp lastModifiedTimestamp = localPatientMap.get(fileName);
	        	java.util.Date currentLastModified = null;
	        	if (lastModifiedTimestamp != null) {
		        	currentLastModified = DateUtils.toDate(lastModifiedTimestamp);
	        	}

				if ((DynaSiteObjects.getStatusMap().get("Halt-Feed-Imports")!= null) && (DynaSiteObjects.getStatusMap().get("Halt-Feed-Imports").equals("true"))) {
					log.debug("Halting Feed Imports: Halt-Feed-Imports = true");
					break;
	            }
	            site = (Site) DynaSiteObjects.getClinicMap().get(clinicHomeId);
	            try {
					siteAbbrev = site.getAbbreviation();
					previousSiteAbbrev = siteAbbrev;
				} catch (NullPointerException e) {
					log.debug("Site not found for " + fileName + " Setting siteAbbrev to " + previousSiteAbbrev);
				}

	            URL patientUrl = new URL(link);
	            //  if (view == 0) {
	            if (process) {
	                // If imported record's pubDate > most recent buildDate, process. Previously we only checked if
	                // currentLastModified != pubdate, but that would cause every out-of-date record to be imported.
	        		if (refreshPatientZeprsId != null && (refreshPatientZeprsId.concat(".xml").equals(fileName))) {
	        			try {
	        				importPatientRecord(currentLastModified, fileName, pubDate, importFilePath, patientUrl, credentials, conn, comments, Long.valueOf(1), siteAbbrev, view);
	        			} catch (Exception e) {
	        				log.debug(e);
	        			}
	                } else {
	                    if ((reload != null) || (view) || (currentLastModified == null) || (buildDate == null) || (buildDate != null && pubDate.getTime() > buildDate.getTime()))
	                    {
	                        changes ++;
	                    	try {
	        					importPatientRecord(currentLastModified, fileName, pubDate, importFilePath, patientUrl, credentials, conn, comments, reload, siteAbbrev, view);
	        				} catch (Exception e) {
	        					log.debug(e);
	        					e.printStackTrace();
	        				}
	                    }
	                }
	            }
	            // }
	        }
        } catch (SocketException e1) {
			message = "Unable to connect to host for feed: " + urlString;
			log.debug(message);
		}

        if (!view) {
        	if (comments.toString().equals("")) {
        		message = "No records to update.";
        	} else {
        		Timestamp updated = new Timestamp(System.currentTimeMillis());
        		// reset siteAbbrev to channel
        		siteAbbrev = channel.getTitle();
        		String commentsString = null;
        		if (comments.toString().length() > 500) {
        			commentsString = comments.toString().substring(0, 500);
        		} else {
        			commentsString = comments.toString();
        		}
        		UpdateLogDAO.save(conn, updated, new Timestamp(lastBuildDate.getTime()), siteAbbrev, commentsString);
        		Subscription subscription = (Subscription) SubscriptionDAO.getOne(conn, urlString);
        		// this is getting imported for the first time either manually or via a scheduled job.
        		if (start != null || subscription.getDatesubscribed() == null) {
        			Timestamp datesubscribed = new Timestamp(System.currentTimeMillis());
        			SubscriptionDAO.updateSite(conn, subscription.getId(), siteAbbrev, datesubscribed);
        		}
        		// log.debug(comments);
        	}
        }
        return message;
    }

    /**
     * download the rss file from the remote server and populate a list of zeprs id's
     * @param urlString
     * @return a list of zeprs id's on remote site
     * @throws Exception
     * @throws ConnectException
     * @throws NoRouteToHostException
     */
    public static ArrayList<String> populateRemotePatientIdList(String urlString) throws Exception, ConnectException, NoRouteToHostException {
    	// download the rss file from the remote server
		ArrayList<String> remotePatients = new ArrayList<String>();
		URL feedUrl = new URL(urlString);
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
    	String message = null;
    	Channel channel = null;
    	try {
    		reader = new XmlReader(httpcon);
    		rssFeed = input.build(reader);
    		rssFeed.setFeedType("rss_2.0");
    		channel = (Channel) rssFeed;
    		List items = channel.getItems();
    		for (int i = 0; i < items.size(); i++) {
    			Item item = (Item) items.get(i);
    			String link = item.getLink();
    			String fileName = item.getTitle();
    			String zeprsId = fileName.replace(".xml", "");
    			remotePatients.add(zeprsId);
    		}
    	} catch (SocketException e1) {
    		message = "Unable to connect to host for feed: " + urlString;
    		log.debug(message);
    	}
    	return remotePatients;
    }

    /**
     * Download RSS file and saves it to the archive siteAbbrev/import dir.
     * Gets the siteAbbrev from the Channel title.
     * @param urlString
     * @return
     * @throws Exception
     * @throws ConnectException
     * @throws NoRouteToHostException
     */
    public static Channel downloadRssFile(String urlString) throws Exception, ConnectException, NoRouteToHostException {
    	// download the rss file from the remote server
    	URL feedUrl = new URL(urlString);
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
    	String message = null;
    	Channel channel = null;
    	try {
    		reader = new XmlReader(httpcon);
    		rssFeed = input.build(reader);
    		rssFeed.setFeedType("rss_2.0");
    		channel = (Channel) rssFeed;
    		String siteAbbrev = channel.getTitle();
    		String importFilePath = org.cidrz.webapp.dynasite.Constants.ARCHIVE_PATH + siteAbbrev + Constants.pathSep + "import" + Constants.pathSep;
	        // Save the feed to the local filesystem. This will be used to help the master create its own feed for this site.
	        BufferedInputStream in = null;
	        in = new BufferedInputStream(httpcon.getInputStream());
	        String importedRssPath = importFilePath + "rss.xml";
	        Writer writer = new FileWriter(importedRssPath);
	        for (int c = in.read(); c != -1; c = in.read()) {
	            writer.write(c);
	        }
	        writer.close();
    	} catch (SocketException e1) {
    		message = "Unable to connect to host for feed: " + urlString;
    		log.debug(message);
    	}
    	return channel;
    }

    /**
     * imports a single patient record
     * @param currentLastModified - if null, is a new patient (or a deleted patient to import)
     * @param fileName
     * @param pubDate
     * @param importFilePath
     * @param patientUrl
     * @param encoding
     * @param conn
     * @param comments
     * @param reload
     * @param siteAbbrev
     * @param view
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws PersistenceException
     * @throws InvocationTargetException
     * @throws SQLException
     */
    public static void importPatientRecord(java.util.Date currentLastModified, String fileName, java.util.Date pubDate, String importFilePath, URL patientUrl, String encoding, Connection conn, StringBuffer comments, Long reload, String siteAbbrev, Boolean view) throws IOException, ClassNotFoundException, IllegalAccessException, PersistenceException, InvocationTargetException, SQLException {
    	if (currentLastModified == null) {
    		log.debug(fileName + " New file -  pubDate: " + pubDate);
    	} else {
    		if (currentLastModified.getTime() != pubDate.getTime()) {
    			log.debug(fileName + ": Current Record: " + new java.util.Date(currentLastModified.getTime()) + " Imported Record: " + new java.util.Date(pubDate.getTime()));
    		}
    	}
        String importedRecordFile = importFilePath + fileName;
        if (!view) {    // Don't fetch if just viewing data. We want to see the current state on the local filesystem.
            try {
            	if (encoding != null) {
            		copyRemotePatientRecordFile(importedRecordFile, patientUrl, encoding);
            	}
            } catch (FileNotFoundException e) {  // file not rendered for this patient - this has not been imported yet.
                log.debug("New patient - importFilePath: " + importFilePath + " patientUrl: " + patientUrl);
                // TODO - Try to fetch the file from master server. If that fails, this is a new patient.
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Patient importedPatient = null;
        try {
            importedPatient = (Patient) XmlUtils.getOne(importedRecordFile);    // this may be null if the importedRecordFile was not created (patientUrl wrong?)
            // import the patient record
            importPatient(currentLastModified, fileName, pubDate, conn, comments, reload, siteAbbrev, view,importedPatient);
        } catch (ObjectNotFoundException e) {
            log.error("Error processing file: " + importedRecordFile + " Message: " + e);
            e.printStackTrace();
        } catch (SQLException e) {
        	throw new SQLException(e);
        } catch (StreamException e) {
        	log.error("Error fetching file: " + importedRecordFile);
        } catch (Exception e) {    // field no longer used.
            log.error(e);
            //e.printStackTrace();
        }
    }

	/**
	 * Imports and saves the patient record.
	 * @param currentLastModified
	 * @param fileName
	 * @param pubDate
	 * @param conn
	 * @param comments
	 * @param reload
	 * @param siteAbbrev
	 * @param view
	 * @param importedPatient
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ServletException
	 * @throws Exception
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws PersistenceException
	 * @throws InvocationTargetException
	 */
	public static void importPatient(java.util.Date currentLastModified, String fileName, java.util.Date pubDate,
			Connection conn, StringBuffer comments, Long reload, String siteAbbrev, Boolean view,
			Patient importedPatient) throws ClassNotFoundException, SQLException, ServletException, Exception,
			IOException, IllegalAccessException, PersistenceException, InvocationTargetException {
		Patient convertedPatient;
		convertedPatient = XmlUtils.importPatientRecord(importedPatient, conn, comments, reload, view);
		// if the patient record was not updated, convertedPatient will be null.
		if (convertedPatient != null) {
			if (currentLastModified == null) {
			    comments.append("Added ").append(convertedPatient.getDistrictPatientid()).append(" ");
			    if (view) {
			        log.debug("Need to add " + convertedPatient.getDistrictPatientid());
			    } else {
			        PatientDAO.updateImportData(conn, convertedPatient.getId(), siteAbbrev, new Timestamp(pubDate.getTime()));
			    }
			}
			// Now save the patient record.
			// Yes, this may seem redundant, but if the local and imported versions differ, we can compare.
			String filePath = Constants.ARCHIVE_PATH + siteAbbrev + Constants.pathSep + "local" + Constants.pathSep;
			if (! view) {
			    PatientRecordUtils.archivePatient(conn, convertedPatient.getId(), siteAbbrev, filePath, fileName);
			}
		}
	}

    /**
     * Fetches patient XML file from remote site using HttpURLConnection.
     * This can be used to compare when site was last updated.
     * Writes file to the local file system
     *
     * @param importedRecordFile
     * @param patientUrl
     * @param encoding
     * @throws IOException
     */
    private static void copyRemotePatientRecordFile(String importedRecordFile, URL patientUrl, String encoding) throws FileNotFoundException, IOException {

        HttpURLConnection patientUrlCon = (HttpURLConnection) patientUrl.openConnection();
        patientUrlCon.setRequestProperty("Authorization", "Basic " + encoding);
        BufferedInputStream in = null;
        in = new BufferedInputStream(patientUrlCon.getInputStream());
        Writer writer = new FileWriter(importedRecordFile);
        for (int c = in.read(); c != -1; c = in.read()) {
            writer.write(c);
        }
        writer.close();
    }
}