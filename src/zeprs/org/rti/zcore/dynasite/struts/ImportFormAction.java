/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.rti.zcore.dynasite.struts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Principal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.webapp.dynasite.Constants;
//import org.rti.zcore.utils.OpenMRS;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.StringManipulation;
import org.cidrz.webapp.dynasite.utils.XmlUtils;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.FormField;
import org.cidrz.webapp.dynasite.valueobject.PageItem;
import org.cidrz.webapp.dynasite.valueobject.Publisher;
import org.cidrz.webapp.dynasite.valueobject.Site;
import org.rti.zcore.dynasite.dao.FormImportDAO;
import org.rti.zcore.sync.utils.PubSubUtils;

/**
 * Imports a form into the system.
 * Place form(s) into the forms/imports directory in the archive filesystem.
 * If the database has a table with the same name as the table in the new form, the system logs an error but allows the form creation to proceed.
 *
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Oct 20, 2008
 *         Time: 12:40:00 PM
 */
public class ImportFormAction extends BaseAction {
	/**
	 * Commons Logging instance.
	 */
	public static Log log = LogFactory.getFactory().getInstance(ImportFormAction.class);
	protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Extract attributes we will need
        HttpSession session = request.getSession();
        Principal userPrincipal = request.getUserPrincipal();
        String username = userPrincipal.getName();
        
		Site site = SessionUtil.getInstance(session).getClientSettings().getSite();
		String message = null;
		Long siteId = site.getId();
		Principal user = request.getUserPrincipal();
		String userName = user.getName();
		Connection connAdmin = null;
		Connection connApp = null;
		String newPath = Constants.ARCHIVE_PATH_FORMS_IMPORT_NEW;
        Publisher publisher = PubSubUtils.getPublisher();

		if (request.getParameter("fileName") != null) {
			try {
				String fileName = request.getParameter("fileName");
				connAdmin = DatabaseUtils.getAdminConnection();
				connApp = DatabaseUtils.getSpecialRootConnection(username);
				StringBuffer comments = new StringBuffer();
				Long formId = null;
				int length = fileName.length();
				String extension = fileName.substring(length-4);
				Form importedForm = null;

				if (extension.equals(".xsd")) {
					// commented out for ZEPRS
					log.debug("disabled OpenMRS import for ZEPRS.");
//					try {
//						importedForm = OpenMRS.importOpenMRSform(mapping, request, connAdmin, newPath, fileName);
//					} catch (ObjectNotFoundException e) {
//						request.setAttribute("exception", e);
//						return mapping.findForward("error");
//					}
				} else if(extension.equals(".xml")) {
					// fetch the xml file
					importedForm = (Form)XmlUtils.getOne(newPath.concat(fileName), null, null);
					// save the FormFields' id as importId.
					Set<PageItem> pageItems = importedForm.getPageItems();
					for (PageItem pageItem : pageItems) {
						FormField formField = pageItem.getForm_field();
						formField.setImportId(formField.getId());
					}
				}

				importedForm.setName(StringManipulation.fixFirstDigit(importedForm.getName()));
		        Locale locale = (Locale) session.getAttribute(Globals.LOCALE_KEY);
		        String localeString = locale.toString();
				comments = FormImportDAO.saveImportedForm(userName, connAdmin, connApp, fileName, importedForm, localeString, false, publisher);

				if (comments.toString().startsWith("Failure")) {
					request.setAttribute("exception", comments.toString());
					return mapping.findForward("error");
				}
				message = "Import successful. This form's xml file has been backed up to " + Constants.ARCHIVE_PATH_FORMS_IMPORT_COMPLETE;
				request.setAttribute("message", message);
				request.setAttribute("errors", comments.toString());
				//todo: Check if comments is an integer and pass as formId instead.
				request.setAttribute("comments", comments.toString());
			} catch (FileNotFoundException e) {
				request.setAttribute("exception", e);
				return mapping.findForward("error");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} finally {
				if (connAdmin != null && !connAdmin.isClosed()) {
					connAdmin.close();
				}
			}
		} else {
			message = "Please place the forms you wish to import in " + Constants.ARCHIVE_PATH_FORMS_IMPORT_NEW;
			request.setAttribute("message", message);
		}
		File name = new File(newPath);
		String[] directory = name.list();
		List<String> fileList = new ArrayList<String>();
		for (int i = 0; i < directory.length; i++) {
			String fileName = directory[i];
			fileList.add(fileName);
		}
		request.setAttribute("fileList", fileList);

		return mapping.findForward("success");
	}

}
