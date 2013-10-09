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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.dao.ApplicationUpdateDAO;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.ApplicationUpdate;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Updates the local zeprs database using appupdates records from admin db
 *
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Aug 1, 2006
 *         Time: 11:58:53 AM
 */
public class UpdateDatabase {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(UpdateDatabase.class);

    public static String execute() throws SQLException {

        Connection conn = null;
        String message = null;
        StringBuffer sbuf = new StringBuffer();
        try {
            conn = DatabaseUtils.getAdminConnection();
            // get list of updates already done on local db
            List localUpdates = ApplicationUpdateDAO.getAll(conn, "zeprs");
            // make a map of these values
            Map appMap = new HashMap();

            for (int i = 0; i < localUpdates.size(); i++) {
                ApplicationUpdate localUpdate = (ApplicationUpdate) localUpdates.get(i);
                appMap.put(localUpdate.getUpdateid(), localUpdate.getUpdateid());
            }
            // Get a list of updates form the admin db.
            List newUpdates = ApplicationUpdateDAO.getAll(conn, "admin");
            int num = 0;
            for (int i = 0; i < newUpdates.size(); i++) {
                ApplicationUpdate applicationUpdate = (ApplicationUpdate) newUpdates.get(i);
                Long id = (Long) appMap.get(applicationUpdate.getId());
                if (id == null) {   // then this is a new one.
                    String type = applicationUpdate.getType();
                    if (type.equals("S")) {
                        String sql = applicationUpdate.getJob();
                        try {
                            num++;                            
                            DatabaseUtils.executeQueryUpdate(conn, applicationUpdate.getJob());
                        } catch (Exception e) {
                            // log.error("ApplicationUpdate Error: " + e);
                        }
                        Timestamp datePosted = applicationUpdate.getDateposted();
                        Timestamp dateInstalled = new Timestamp(System.currentTimeMillis());
                        ApplicationUpdateDAO.save(conn, "zeprs", applicationUpdate.getId(), datePosted, dateInstalled, type, sql);
                    }
                }
            }
            if (num > 0) {
                log.info("Application Updates: " + num);
            }

            // load the updated admin database
            // String adminSql = Constants.DYNASITE_RESOURCES_PATH + "/admin_install.sql";
            message = sbuf.toString();
        } catch (ServletException e) {
            log.error(e);
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        return message;
    }


}
