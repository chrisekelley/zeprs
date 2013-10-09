/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.utils;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;


/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jul 25, 2005
 *         Time: 2:56:21 PM
 */
public class PoolBuddy {

    public static String checkPool() throws NamingException, SQLException {
        String response = null;
        DataSource dataSource = null;

        Context initCtx = new InitialContext();
        try {
            //dataSource = (DataSource) initCtx.lookup("java:comp/env/jdbc/zeprsPooledDB");
            dataSource = (DataSource) initCtx.lookup("java:comp/env/jdbc/zeprsDB");
            if (dataSource instanceof BasicDataSource) {
                BasicDataSource pds = (BasicDataSource) dataSource;
                String busy = "num_active_connections: " + pds.getNumActive();
                String idle = "num_idle_connections: " + pds.getNumIdle();
                response = "<p>" + busy + "\n<br/>" + idle + "</p>";
            } else {
                response = "Not a dbcp BasicDataSource!";
            }
            /*// dbcp
            if (dataSource instanceof SharedPoolDataSource) {
                SharedPoolDataSource  pds =    (SharedPoolDataSource) dataSource;
                String busy = "num_active_connections: " + pds.getNumActive();
                String idle = "num_idle_connections: " + pds.getNumIdle();
                response = "<p>" + busy +   "\n<br/>" + idle + "</p>";
            } else {
                response = "Not a dbcp PooledDataSource!";
            }*/

            /*if (dataSource instanceof PerUserPoolDataSource) {
                PerUserPoolDataSource pds = (PerUserPoolDataSource) dataSource;
                String busy = "num_active_connections: " + pds.getNumActive();
                String idle = "num_idle_connections: " + pds.getNumIdle();
                response = "<p>" + busy + "\n<br/>" + idle + "</p>";
            } else {
                response = "Not a dbcp PooledDataSource!";
            }*/

            /*//  make sure it's a c3p0 PooledDataSource
            if (dataSource instanceof PooledDataSource) {
                PooledDataSource pds = (PooledDataSource) dataSource;
                String conns = "num_connections: " + pds.getNumConnectionsDefaultUser();
                String busy = "num_busy_connections: " + pds.getNumBusyConnectionsDefaultUser();
                String idle = "num_idle_connections: " + pds.getNumIdleConnectionsDefaultUser();
                response = response + "<p>" + conns + "\n<br/>" + busy + "\n<br/>" + idle + "</p>";
            } else {
                response = response + "<br/>Not a c3p0 PooledDataSource!";
            }*/
        } catch (NamingException e) {
            response = "Not a dbcp PooledDataSource!";
        }


        return response;
    }

}
