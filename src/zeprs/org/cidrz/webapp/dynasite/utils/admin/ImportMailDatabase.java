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

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.utils.XmlUtils;
import org.cidrz.webapp.dynasite.valueobject.Publisher;

import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         kudos: http://forum.java.sun.com/thread.jspa?threadID=704547&tstart=15
 *         Date: Jun 19, 2006
 *         Time: 6:16:14 PM
 */
public class ImportMailDatabase {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(ImportMailDatabase.class);
    public static final String TEST_URL =  "http://192.168.0.11:8080";
    public static final String ZEPRS_URL =  "http://192.168.20.6:8080";


    public static void execute() throws Exception {
        String url;

        // check what site this pc is publishing
        String publisherURL = null;
        Publisher publisher = null;
        String publisherFile = Constants.ARCHIVE_PATH + "publisher.xml";
        String batchfile = null;
        String outFile = null;
        try {
            publisher = (Publisher) XmlUtils.getOne(publisherFile);
            publisherURL = publisher.getUrl();
            if (publisherURL.equals("192.168.20.6")) {
                // don't copy
            } else if (publisherURL.equals("192.168.0.11")) {  // test server
                url = TEST_URL + "/archive/mail.sql";
                outFile = Constants.ARCHIVE_PATH + "mail2.sql";
                batchfile = "\"c:/zeprs/zeprs-install/mail-test.bat";
                importFile(url, batchfile, outFile);
            } else if (publisherURL.equals("192.168.0.3")) {   // test pc
                url = TEST_URL + "/archive/mail.sql";
                outFile = Constants.ARCHIVE_PATH + "/mail.sql";
                batchfile = "\"c:/zeprs/zeprs-install/mail-test.bat";
                importFile(url,batchfile, outFile);
            } else {  // everyone else
                url = ZEPRS_URL + "/archive/mail.sql";
                outFile = Constants.ARCHIVE_PATH + "/mail.sql";
                batchfile = "\"c:/zeprs/zeprs-install/mail-lusaka.bat";
                importFile(url, batchfile, outFile);
            }
        } catch (FileNotFoundException e) {
            // it's ok - file not created yet.
        }

    }

    public static void importFile(String url, String batchfile, String file) throws Exception {
        InputStream is = null;
        // String file = Constants.ARCHIVE_PATH + "mail.sql";
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        URL feedUrl = new URL(url);
        HttpURLConnection httpcon = null;
        try {
            httpcon = (HttpURLConnection) feedUrl.openConnection();
            boolean isProxied = httpcon.usingProxy();
            if (isProxied) {
                log.debug(" Proxied: " + isProxied);
            }
            String encodeString = "zepadmin:zepadmin11";
            Base64 encoder = new Base64();
            String encoding = String.valueOf(encoder.encode(encodeString.getBytes()));
            //String encoding = new sun.misc.BASE64Encoder().encode("zepadmin:zepadmin11".getBytes());
            httpcon.setRequestProperty("Authorization", "Basic " + encoding);
            String contentEncoding = httpcon.getContentEncoding(); //automatically connects if not connected
            is = httpcon.getInputStream();
            int count;
            byte[] bytes = new byte[128];
            while ((count = is.read(bytes, 0, bytes.length)) != -1)
            {
                outputStream.write(bytes, 0, count);
            }

            try {
                Process p = Runtime.getRuntime().exec(batchfile);
                p.waitFor();
            } catch (Exception e) {
                log.error(e);
            }


        } catch (Exception e) {
            if (httpcon != null) {
                StringBuilder sb = new StringBuilder("HTTP return code: ");
                try {
                    sb.append(httpcon.getResponseCode());
                    sb.append(" (");
                    sb.append(httpcon.getResponseMessage());
                    sb.append("). :");
                    sb.append(e);
                    e = new Exception(sb.toString());
                } catch (IOException ioe) {
                    /* ignore */
                }
            }
            log.error("Download error: " + e, e);
            throw e;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ioe) {
                    log.error("Can't close URL stream:", ioe);
                }
            }
            if (httpcon != null) {
                httpcon.disconnect();
            }
        }

    }
}
