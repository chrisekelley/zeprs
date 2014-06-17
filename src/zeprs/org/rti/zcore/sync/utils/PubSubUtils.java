package org.rti.zcore.sync.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.apache.http.HttpException;
//import org.apache.http.auth.Credentials;
//import org.apache.http.auth.NTCredentials;
//import org.apache.http.auth.UsernamePasswordCredentials;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Site;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.valueobject.Publisher;
import org.cidrz.webapp.dynasite.valueobject.Server;
import org.cidrz.webapp.dynasite.valueobject.Subscription;
import org.cidrz.webapp.dynasite.dao.SubscriptionDAO;
import org.cidrz.webapp.dynasite.utils.FileUtils;
//import org.rti.zcore.utils.WebDavUtils;
import org.cidrz.webapp.dynasite.utils.XmlUtils;

public class PubSubUtils {

	/**
     * Commons Logging instance.
     */
    public static Log log = LogFactory.getFactory().getInstance(PubSubUtils.class);

	/**
	 * Fetches Publisher from file.
	 * @return Publisher instance w/ data from publisher file.
	 * @throws IOException
	 */
	public static Publisher getPublisher() throws IOException {
		String file = Constants.ARCHIVE_PATH + "publisher." + Constants.SYNC_FORMAT;
		Publisher publisher = null;
		try {
			publisher = (Publisher) XmlUtils.getOne(file, null, Publisher.class);
		} catch (FileNotFoundException e) {
			// it's ok - file not created yet.
		}
		return publisher;
	}

	/**
	 * @return Server defined in syncEvent configuration file
	 * @throws IOException
	 */
	public static Server getSyncEventServer() throws IOException {
		Server server = null;
		try {
			String serverFile = Constants.ARCHIVE_PATH_SERVERS + "syncEvent." + Constants.SYNC_FORMAT;
			server = (Server) XmlUtils.getOne(serverFile, Constants.SYNC_FORMAT, Server.class);
		} catch (FileNotFoundException e) {
			// we cannot proceed.
			log.debug("Must setup SyncEvent server info.");
		}
		return server;
	}

	/**
	 * Helps automate the subscription process.
	 * Used when Constants.AUTO_SUBSCRIPTION is not empty to create a list of subscriptions.
	 * Otherwise gets a list of subscriptions from the filesystem.
	 * @return list of subscriptions.
	 * @throws IOException
	 */
//	public static ArrayList<Subscription> getSubscriptionList() throws IOException {
//		ArrayList<Subscription> subscriptionList = new ArrayList<Subscription>();
//		Boolean useAutoSubscription = false;
//		useAutoSubscription = Boolean.valueOf(Constants.AUTO_SUBSCRIPTION);
//		if (Constants.AUTO_SUBSCRIPTION != null && useAutoSubscription) {
//
//			String siteInstanceIdListStr = Constants.SITE_INSTANCES_ENABLED;
//			ArrayList<Integer> siteInstanceIdList = new ArrayList<Integer>();
//			if (siteInstanceIdListStr != null) {
//				for (StringTokenizer stringTokenizer = new StringTokenizer(siteInstanceIdListStr, ","); stringTokenizer.hasMoreTokens();) {
//					Integer siteInstanceId = Integer.decode(stringTokenizer.nextToken());
//					siteInstanceIdList.add(siteInstanceId);
//				}
//			}
//
//			Publisher publisher = getPublisher();
//			Integer publisherSiteInstanceId = publisher.getSiteInstanceId();
//			Boolean masterServer = publisher.getMasterServer();
//			String masterSites = Constants.MASTER_SITES;
//			ArrayList<String> masterSiteList = new ArrayList<String>();
//			if (masterSites != null) {
//				for (StringTokenizer stringTokenizer = new StringTokenizer(masterSites, ","); stringTokenizer.hasMoreTokens();) {
//					String siteAbbrev = stringTokenizer.nextToken();
//					masterSiteList.add(siteAbbrev);
//				}
//			}
//
//			if (masterServer != null && masterServer.equals(Boolean.TRUE)) {
//				for (String siteAbbrev : masterSiteList) {
//					ArrayList<Subscription> siteSubscriptionList = PubSubUtils.createSubscriptionsForSiteInstances(siteInstanceIdList, siteAbbrev, publisher);
//					subscriptionList.addAll(siteSubscriptionList);
//				}
//			} else {
//				Long sitePublishId = publisher.getSiteId();
//				Site site = (Site) DynaSiteObjects.getClinicMap().get(sitePublishId);
//				String siteAbbrev = site.getAbbreviation();
//				subscriptionList = PubSubUtils.createSubscriptionsForSiteInstances(siteInstanceIdList, siteAbbrev, publisher);
//			}
//		} else {
//			String path = Constants.ARCHIVE_PATH_SUBSCRIPTIONS;
//			List<String> subscriptionFileNameList = null;
//			subscriptionFileNameList = FileUtils.getDirectoryListing(path, false);
//			for (int i = 0; i < subscriptionFileNameList.size(); i++) {
//				String fileName = (String) subscriptionFileNameList.get(i);
//				Site site;
//				Subscription subscription = null;
//				try {
//					subscription = (Subscription) XmlUtils.getOne(path + fileName, Constants.SYNC_FORMAT, Subscription.class);
//					subscriptionList.add(subscription);
//				} catch (FileNotFoundException e) {
//					log.debug("Subscription not found for " + fileName);
//				}
//			}
//		}
//		return subscriptionList;
//	}

	/**
	 * Creates list of subscriptions.
	 * If it is not a master site, does not create a subscription for the same site id.
	 * (Does not subscribe to itself).
	 * @param siteInstanceIdList
	 * @param siteAbbrev
	 * @param publisher TODO
	 * @return list of Subscriptions
	 * @throws IOException
	 */
//	public static ArrayList<Subscription> createSubscriptionsForSiteInstances(ArrayList<Integer> siteInstanceIdList, String siteAbbrev, Publisher publisher) throws IOException {
//		ArrayList<Subscription> subscriptionList = new ArrayList<Subscription>();
//		Server server = getSyncEventServer();
//		if (server != null) {
//			String url = server.getUrl();
//			Integer publisherSiteInstanceId = publisher.getSiteInstanceId();
//
//			Site site = publisher.getSite();
//			String publisherSiteAbbrev = site.getAbbreviation();
//			Boolean master = publisher.getMasterServer();
//			for (Integer siteInstanceId : siteInstanceIdList) {
//				if (master != null && master == Boolean.TRUE) {
//					if ((publisherSiteInstanceId.intValue() != siteInstanceId.intValue())) {
//						Subscription subscription = PubSubUtils.createSubscription(siteAbbrev, server, siteInstanceId);
//						subscriptionList.add(subscription);
//					}
//				} else {
//					if ((publisherSiteInstanceId.intValue() != siteInstanceId.intValue())) {
//						Subscription subscription = PubSubUtils.createSubscription(siteAbbrev, server, siteInstanceId);
//						subscriptionList.add(subscription);
//					}
//				}
//			}
//		}
//		return subscriptionList;
//	}

	/**
	 * Creates subscription object for automated subscriptions.
	 * @param siteAbbrev
	 * @param server
	 * @param siteInstanceId
	 * @return Subscription
	 */
//	public static Subscription createSubscription(String siteAbbrev, Server server, Integer siteInstanceId) {
//		String url = server.getUrl();
//		String subscriptionUrl = url + "/" + siteAbbrev + "/" + siteInstanceId.toString() + "/archive/";
//		Subscription subscription = new Subscription();
//		subscription.setUrl(subscriptionUrl);
//		subscription.setAuthenticationType(server.getAuthenticationType());
//		subscription.setDomain(server.getDomain());
//		subscription.setServerType(server.getServerType());
//		subscription.setSite(siteAbbrev);
//		subscription.setUsername(server.getUsername());
//		subscription.setPassword(server.getPassword());
//		subscription.setEnabled(true);
//		subscription.setSiteInstanceId(siteInstanceId);
//		subscription.setAutoSubscription(true);
//		Timestamp datesubscribed = new Timestamp(System.currentTimeMillis());
//		subscription.setDatesubscribed(datesubscribed);
//		return subscription;
//	}

	/**
	 * Loops through Constants.MASTER_SITES and exports each site's records.
	 * @param conn
	 * @throws NumberFormatException
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 * @throws ClassNotFoundException
	 * @throws ObjectNotFoundException
	 */
//	public static void exportMasterSites(Connection conn) throws NumberFormatException, SQLException, IOException,
//			ServletException, ClassNotFoundException, ObjectNotFoundException {
//		String masterSites = Constants.MASTER_SITES;
//		for (StringTokenizer stringTokenizer = new StringTokenizer(masterSites, ","); stringTokenizer.hasMoreTokens();) {
//			String childSiteAbbrev = stringTokenizer.nextToken();
//			Site childSite = DynaSiteObjects.getSiteMap().get(childSiteAbbrev);
//			Publisher childPublisher = new Publisher();
//			childPublisher.setMasterServer(true);
//			Integer masterSiteInstanceId = 9;
//			if (Constants.MASTER_SITE_INSTANCE_ID != null) {
//				masterSiteInstanceId = Integer.valueOf(Constants.MASTER_SITE_INSTANCE_ID);
//			}
//			childPublisher.setSiteInstanceId(masterSiteInstanceId);
//			childPublisher.setSite(childSite);
//			childPublisher.setSiteId(childSite.getId());
//			int numFilesZipped = SyncUtils.exportSite(conn, childSiteAbbrev, childSite, childPublisher, null);
//			try {
//				SyncUtils.uploadPendingFiles(childPublisher);
//			} catch (HttpException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}

	/**
	 * Generates a subscription file.
	 * @param conn
	 * @param subscription
	 * @param siteAbbrev
	 * @throws SQLException
	 * @throws IOException
	 */
//	public static void outputSubscriptionFile(Connection conn, Subscription subscription, String siteAbbrev)
//			throws SQLException, IOException {
//		log.debug("Creating subscription file for URL: " + subscription.getUrl() + " instance: " + subscription.getSiteInstanceId());
//		Timestamp datesubscribed = new Timestamp(System.currentTimeMillis());
//		subscription.setDatesubscribed(datesubscribed);
//		SubscriptionDAO.updateSite(conn, subscription.getId(), siteAbbrev, datesubscribed);
//		String path = Constants.ARCHIVE_PATH_SUBSCRIPTIONS;
//		String filename = null;
//		if (subscription.getSiteInstanceId() != null) {
//		    filename = subscription.getSite() + "-" + subscription.getSiteInstanceId() + "." + Constants.SYNC_FORMAT;
//		} else {
//		    filename = subscription.getSite() + "." + Constants.SYNC_FORMAT;
//		}
//		XmlUtils.save(subscription, path + filename);
//	}

	/**
	 * Creates a Publisher and persists it to the file system.
	 * Also creates the archive directories on the server.
	 * @param siteId
	 * @param siteInstanceId
	 * @param enabledMasterServer
	 * @return
	 * @throws Exception
	 */
//	public static Publisher createPublisher(Long siteId, Integer siteInstanceId, Boolean enabledMasterServer) throws Exception {
//		Publisher publisher = new Publisher();
//		String file = Constants.ARCHIVE_PATH + "publisher." + Constants.SYNC_FORMAT;
//		Site site = (Site) DynaSiteObjects.getClinicMap().get(siteId);
//		publisher.setSiteId(siteId);
//		publisher.setSite(site);
//		publisher.setMasterServer(enabledMasterServer);
//		publisher.setSiteInstanceId(siteInstanceId);
//		//publisher.setUrl(url);
//		//publisher.setUsername(username);
//		//publisher.setPassword(password);
//		//publisher.setRedirectLocal(redirectLocal);
//		//publisher.setEnabledDataCenter(enabledDataCenter);
//		//publisher.setPort(port);
//		XmlUtils.save(publisher, file);
//		DynaSiteObjects.setWebDavArchiveFolderDate(null);	// resets in case you need to create new directories.
//		// changed for ZEPRS
////		createArchiveDirectoryForSite(siteInstanceId, site.getAbbreviation());
//		return publisher;
//	}

	/**
	 * Creates the archive directory for a site in the format:
	 *  server.getUrl() + "/" + siteAbbrev + "/" + siteInstanceId + "/archive"
	 * @param siteInstanceId
	 * @param siteAbbrev
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws FileNotFoundException
	 * @throws HttpException
	 * @throws Exception
	 */
//	public static void createArchiveDirectoryForSite(Integer siteInstanceId, String siteAbbrev) throws IOException,
//			MalformedURLException, FileNotFoundException, HttpException, Exception {
//		Server server = PubSubUtils.getSyncEventServer();
//		if (server != null) {
//			StringBuffer sbuf = new StringBuffer();
//			String baseUrl = server.getUrl() + "/" + siteAbbrev + "/" + siteInstanceId + "/";
//			String archiveUrlString = baseUrl + "archive/";
//			URL archiveUrl = new URL(archiveUrlString);
//			String host = archiveUrl.getHost();
//			Credentials creds = null;
//			if (server.getAuthenticationType().equals("ntlm")) {
//				creds = new NTCredentials(server.getUsername(), server.getPassword(), host, server.getDomain());
//			} else {
//				creds = new UsernamePasswordCredentials(server.getUsername(), server.getPassword());
//			}
//			boolean displayErrorMessage = false;
//			try {
//				displayErrorMessage = WebDavUtils.createWebdavDirectory(sbuf, server.getUrl() + "/" + siteAbbrev + "/", creds);
//				displayErrorMessage = WebDavUtils.createWebdavDirectory(sbuf, baseUrl, creds);
//				displayErrorMessage = WebDavUtils.createWebdavDirectory(sbuf, archiveUrlString, creds);
//			} catch (ConnectException e) {
//				displayErrorMessage = true;
//				sbuf.append("<p>Error: Unable to connect to server for url: " + archiveUrlString + " Error: Unable to connect to " + e.getMessage() + ".</p>");
//				log.debug(sbuf.toString());
//			} catch (UnknownHostException e) {
//				displayErrorMessage = true;
//				sbuf.append("<p>Error: Unable to connect to server for url: " + archiveUrlString + " Error: Unable to connect to " + e.getMessage() + ".</p>");
//				log.debug(sbuf.toString());
//			}
//			if (displayErrorMessage) {
//				throw new Exception(sbuf.toString());
//			}
//		}
//	}



}
