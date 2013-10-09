/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.security;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
// import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.cidrz.webapp.dynasite.exception.PersistenceException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public abstract class AuthManager {
    private static Map identityCache = new HashMap();

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(AuthManager.class.getName());

    /**
     * Method to confirm that the authenticated user is still the user making this request.
     *
     * @param request
     * @param username
     * @param password
     * @throws org.cidrz.webapp.dynasite.security.UserUnauthorizedException
     */
    public static final void confirmIdentity(HttpServletRequest request, String username, String password) throws org.cidrz.webapp.dynasite.security.UserUnauthorizedException {
        if (request.getUserPrincipal().getName().equals(username)) {
            if (identityCache.containsKey(username)) {
                try {
                    if (identityCache.get(username).equals(MD5Hash(password))) {
                        return;
                    }
                } catch (NoSuchAlgorithmException e) {
                    log.error(e.getMessage());
                }
            } else {
                checkDB(username, password);
                try {
                    identityCache.put(username, MD5Hash(password));
                    return;
                } catch (NoSuchAlgorithmException e) {
                    log.error(e.getMessage());
                }
            }
        }
        throw new UserUnauthorizedException("identity not confirmed.");
    }

    private static final void checkDB(String username, String password) throws UserUnauthorizedException {
        Context ctx = null;
        DataSource ds = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            ctx = new InitialContext();
            if (ctx == null) {
                log.error("No initial context.");
                throw new PersistenceException("No initial context");
            }
            try {
                ds = (DataSource) ctx.lookup("java:comp/env/jdbc/zeprsDB");
            } catch (NamingException e) {
                // unit testing...
                // uncoment and import import org.springframework.jdbc.datasource.DriverManagerDataSource;
                /*DriverManagerDataSource dms = new DriverManagerDataSource();
                dms.setDriverClassName("com.mysql.jdbc.Driver");
                dms.setUrl("jdbc:mysql://localhost/zeprs");
                dms.setUsername("zeprs_web_user");
                dms.setPassword("**password**");
                ds = dms;*/
            }
            if (ds != null) {
                conn = ds.getConnection();
                PreparedStatement ps = conn.prepareStatement("select 1 from mail.accountuser where username=? and password=password(?)");
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return;
                }
            }
        } catch (Exception e) {
            log.error("Error re-authenticating the user: " + e.getMessage());
            throw new UserUnauthorizedException("Unable to check the database.");
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
        throw new UserUnauthorizedException("No matching rows in database.");
    }

    private static String MD5Hash(String data)
            throws NoSuchAlgorithmException {
        StringBuffer sb = new StringBuffer();
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(data.getBytes());
        byte[] digestBytes = messageDigest.digest();

        String hex = null;

        for (int i = 0; i < digestBytes.length; i++) {
            hex = Integer.toHexString(0xFF & digestBytes[i]);

            if (hex.length() < 2) {
                sb.append("0");
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
