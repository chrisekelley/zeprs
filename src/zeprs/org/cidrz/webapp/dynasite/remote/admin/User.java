/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.remote.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.dao.UserDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.ClientSettings;
import org.cidrz.webapp.dynasite.valueobject.UserGroup;
import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Oct 12, 2005
 *         Time: 3:31:15 PM
 */
public class User {

    /**
     * Commons Logging instance.
     */
    public static Log log = LogFactory.getFactory().getInstance(User.class);

    public static String modifyUserGroup(String username, Long groupId) {
        WebContext exec = WebContextFactory.get();
        String userName = null;
        Long siteId = null;
        String result = "";
        Connection conn = null;
        try {
            try {
                userName = exec.getHttpServletRequest().getUserPrincipal().getName();
            } catch (NullPointerException e) {
                // unit testing - it's ok...
                userName = "demo";
            }
            conn = DatabaseUtils.getZEPRSConnection(userName);
            SessionUtil zeprs_session = null;
            try {
                zeprs_session = (SessionUtil) exec.getSession().getAttribute("zeprs_session");
            } catch (Exception e) {
                // unit testing - it's ok...
            }

            try {
                ClientSettings clientSettings = zeprs_session.getClientSettings();
                siteId = clientSettings.getSiteId();
            } catch (SessionUtil.AttributeNotFoundException e) {
                log.error(e);
            } catch (NullPointerException e) {
                // it's ok - unit testing
                siteId = new Long("1");
            }

            try {
                UserDAO.getUserGroup(conn, username);
                UserDAO.updateGroup(conn, username, groupId);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (ObjectNotFoundException e) {
                try {
                    UserDAO.insertGroup(conn, username, groupId);
                } catch (SQLException ex) {
                    log.error(e);
                } catch (ServletException ex) {
                    log.error(e);
                }
            }

            UserGroup userGroup = null;
            try {
                userGroup = (UserGroup) UserDAO.getGroup(conn, groupId);
            } catch (SQLException e) {
                log.error(e);
            } catch (ServletException e) {
                log.error(e);
            } catch (ObjectNotFoundException e) {
                log.error(e);
            }

            String group = userGroup.getName();
            result = username + "=" + group;
        } catch (ServletException e) {
            log.error(e);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    public static String updateUserInfo(String fieldName, String username, String value) {

    	String result = fieldName + username + "=" + value;
    	if (fieldName.equals("Password")) {
        	if (value.length() < 8) {
        		return fieldName + username + "=Error: Password must be at least 8 characters.";
        	}
    	}
    	WebContext exec = WebContextFactory.get();
    	String dbUser = null;
    	Connection conn = null;
    	try {
    		try {
    			dbUser = exec.getHttpServletRequest().getUserPrincipal().getName();
            } catch (NullPointerException e) {
                // unit testing - it's ok...
            	dbUser = "demo";
            }
//            conn = DatabaseUtils.getZEPRSConnection(dbUser);
            conn = DatabaseUtils.getSpecialRootConnection(dbUser);
            if (fieldName.equals("Password")) {
            	UserDAO.updatePassword(conn, username, value);
            } else {
            	UserDAO.updateUserInfo(conn, fieldName, username, value);
            }
			
		} catch (SQLException e) {
			log.error(e);
			 result = fieldName + username + "=Error: " + e;
		} catch (ServletException e) {
			 result = fieldName + username + "=Error: " + e;
		} finally {
	            try {
	                if (conn != null && !conn.isClosed()) {
	                    conn.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
		return result;
    }
}