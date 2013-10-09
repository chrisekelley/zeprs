/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.dao;

import org.cidrz.webapp.dynasite.valueobject.ClientSettings;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.valueobject.Site;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;

import javax.servlet.ServletException;
import java.security.Principal;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jun 22, 2005
 *         Time: 11:48:02 AM
 */
public class ClientSettingsDAO {

     public static Object getOne(Connection conn, String ipAddress) throws SQLException, ServletException, ObjectNotFoundException {
        Object result = null;
        ArrayList values = new ArrayList();
        String sql = "select c.id, c.ip_address AS ipAddress, c.site_id AS siteId, c.district_id AS districtId " +
                "from client_setting c " +
                "where c.ip_address=?; ";
                values.add(ipAddress);
        result = DatabaseUtils.getBean(conn, ClientSettings.class, sql, values);
        return result;
     }

    public static void save(Connection conn, ClientSettings settings, Principal principal, Site site) throws SQLException, ServletException {
        String siteSql = "INSERT INTO client_setting " +
                "SET ip_address='" + settings.getIpAddress() +
                "', site_id=" + site.getId() +
                ", district_id=" + new Long("1");
        ArrayList values = new ArrayList();
        DatabaseUtils.save(conn, siteSql, values.toArray());
    }

    /**
     * Only used for testing inserts into client_settings
     * @param settings
     * @param principal
     * @param site
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static Long create(ClientSettings settings, Principal principal, Site site) throws SQLException, ServletException {
        String siteSql = "INSERT INTO client_setting " +
                "SET ip_address='" + settings.getIpAddress() +
                "', site_id=" + site.getId() +
                ", district_id=" + new Long("1");
        ArrayList values = new ArrayList();
        Long id = (Long) DatabaseUtils.create(siteSql, values.toArray());
        return id;
    }

    public static void delete(Connection conn, ClientSettings settings, Principal principal, Site site) throws SQLException, ServletException {
        String siteSql = "DELETE FROM client_setting " +
                "WHERE ip_address='" + settings.getIpAddress() +
                "'";
        ArrayList values = new ArrayList();
        DatabaseUtils.save(conn, siteSql, values.toArray());
    }


}
