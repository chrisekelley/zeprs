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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.dao.ApplicationUpdateDAO;
import org.cidrz.webapp.dynasite.struts.action.generic.PersistentObjectAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.XmlUtils;
import org.cidrz.webapp.dynasite.utils.admin.ImportMailDatabase;
import org.cidrz.webapp.dynasite.valueobject.Publisher;
import org.cidrz.webapp.dynasite.valueobject.ApplicationUpdate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.sql.Result;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Logs and implements updates to the database.
 * kudos: http://www.tonyspencer.com/mt/archives/2005/01/execute_mysql_s.htm
 * User: Chris Kelley
 * Date: Jun 20, 2006
 * Time: 3:45:34 PM
 */
public class SqlAction extends PersistentObjectAction {
    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(SqlAction.class);

    protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Class clazz = getMappedClass(mapping);
        DynaActionForm dynaForm = (DynaActionForm) form;
        Principal userPrincipal = request.getUserPrincipal();
        String username = userPrincipal.getName();
        Connection conn = null;
        Result result = null;
        int resultUpdate = 0;
        int[] resultBatch = new int[0];
        String message = null;
        if (dynaForm != null) {
            try {
                conn = DatabaseUtils.getSpecialRootConnection(username);
                String sql = (String) dynaForm.get("job");
                String type = (String) dynaForm.get("type");
                Boolean logJob = (Boolean) dynaForm.get("logJob");
                try {
                    conn.setAutoCommit(false);
                    if (type.equals("select")) {
                        result = DatabaseUtils.executeQuery(conn, sql);
                    } else if (type.equals("update")) {
                        resultUpdate = DatabaseUtils.executeQueryUpdate(conn, sql);
                        if (logJob == Boolean.TRUE) {
                            saveSqlUpdate(conn, sql);
                        }
                    } else if (type.equals("batchfile")) {
                        try {
                            String publisherURL = null;
                            Publisher publisher = null;
                            String publisherFile = Constants.ARCHIVE_PATH + "publisher.xml";
                            publisher = (Publisher) XmlUtils.getOne(publisherFile);
                            publisherURL = publisher.getUrl();
                            String command = null;
                            String args = null;
                            String[] cmdArray = null;
                            String dbpassword = null;
                            String newSql = sql.replaceAll("\\\\", "\\\\\\\\");
                            if (publisherURL.equals("192.168.20.6")) {    // master server
                                command = "mysql";
                                dbpassword = "**password**";
                                cmdArray = new String[]{command, "zeprs",
                                        "--host=192.168.20.7",
                                        "--user=root",
                                        "--password=" + dbpassword,
                                        "-e",
                                        "\"source " + newSql + "\""
                                };
                            } else if (publisherURL.equals("192.168.0.11")) {  // test laptop
                                command = "\"C:\\Program Files\\MySQL\\MySQL Server 5.0\\bin\\mysql\"";
                                dbpassword = "**pasword**";
                                cmdArray = new String[]{command, "zeprs",
                                        "--user=root",
                                        "--password=" + dbpassword,
                                        "-e",
                                        "\"source " + newSql + "\""
                                };
                            } else {  // everyone else
                                command = "C:\\mysql\\bin\\mysql";
                                dbpassword = "**pasword**";
                                cmdArray = new String[]{command, "zeprs",
                                        "--user=root",
                                        "--password=" + dbpassword,
                                        "-e",
                                        "\"source " + newSql + "\""
                                };
                            }
                            log.debug(cmdArray[0] + " " + cmdArray[1] + " " +
                                    cmdArray[2] + " " + cmdArray[3] + " " +
                                    cmdArray[4] + " " + cmdArray[5]);
                            try {
                                Runtime rt = Runtime.getRuntime();
                                Process proc = rt.exec(cmdArray);
                                // Process proc = rt.exec(command);
                                InputStream stdin = proc.getInputStream();
                                InputStreamReader isr = new InputStreamReader(stdin);
                                BufferedReader br = new BufferedReader(isr);
                                String line = null;
                                // System.out.println("<OUTPUT>");
                                while ((line = br.readLine()) != null)
                                    log.debug(line);
                                // System.out.println(line);
                                // System.out.println("</OUTPUT>");
                                resultUpdate = proc.waitFor();
                                // System.out.println("Process exitValue: " + exitVal);
                                log.debug("Process exitValue: " + resultUpdate);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (resultUpdate > 0) {
                                // process failed. reset to 0
                                resultUpdate = 0;
                            } else {
                                resultUpdate = 1;
                            }
                        } catch (FileNotFoundException e) {
                            // it's ok - file not created yet.
                        }
                    } else {
                        resultBatch = DatabaseUtils.executeQueryBatch(conn, sql);
                        if (logJob == Boolean.TRUE) {
                        	String[] sqlArray = sql.split("\n");
                            for (int i = 0; i < sqlArray.length; i++) {
                                String sqlB = sqlArray[i];
                                saveSqlUpdate(conn, sqlB);
                            }
                        }
                    }
                    sql = "COMMIT";
                    DatabaseUtils.create(conn, sql);

                } catch (Exception e) {
                    sql = "ROLLBACK";
                    DatabaseUtils.create(conn, sql);
                    log.error(e);
                    request.setAttribute("exception", e);
                    return mapping.findForward("error");
                }
                message = "SQL statement sucessful";

            } catch (Exception e) {
                SqlAction.log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } finally {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            }
        } else {
            if (request.getParameter("mail") != null) {
                try {
                    ImportMailDatabase.execute();
                    message = "Mail database Updated.";
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        request.setAttribute("result", result);
        request.setAttribute("resultUpdate", resultUpdate);
        request.setAttribute("resultBatch", resultBatch);
        request.setAttribute("message", message);

        return mapping.findForward(SUCCESS_FORWARD);
    }

	/**
	 * @param conn
	 * @param sql
	 * @throws SQLException
	 * @throws IOException
	 */
	private void saveSqlUpdate(Connection conn, String sql) throws SQLException, IOException {
		// save it
		ApplicationUpdate appUpdate = new ApplicationUpdate();
		Timestamp datePosted = new Timestamp(System.currentTimeMillis());
		appUpdate.setDateposted(datePosted);
		appUpdate.setJob(sql);
		appUpdate.setType("S");
		Long id = (Long) ApplicationUpdateDAO.save(conn, "admin", null, datePosted, null, "S", sql);
		appUpdate.setId(id);
		// XML version is a safety in case the admin db get wiped out.
		XmlUtils.save(appUpdate, Constants.ARCHIVE_PATH_LOGS_APPUPDATES + "/update" + id + ".xml");
	}

}
