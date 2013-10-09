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
import org.cidrz.webapp.dynasite.struts.action.generic.PersistentObjectAction;
import org.cidrz.webapp.dynasite.utils.XmlUtils;
import org.cidrz.webapp.dynasite.utils.FileUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;
import org.cidrz.webapp.dynasite.valueobject.Publisher;
import org.cidrz.webapp.dynasite.valueobject.Site;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Chris Kelleu
 * Date: Mar 20, 2006
 * Time: 3:45:34 PM
 */
public class PublisherAction extends PersistentObjectAction {
    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(PublisherAction.class);

    protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Class clazz = getMappedClass(mapping);
        DynaActionForm dynaForm = (DynaActionForm) form;
        String file = Constants.ARCHIVE_PATH + "publisher.xml";
        if (dynaForm != null) {
            FileUtils.createArchive();  // refresh the filesystem in case there were changes.
            Long siteId = (Long) dynaForm.get("siteId");
            String url = (String) dynaForm.get("url");
            Boolean redirectLocal = (Boolean) dynaForm.get("redirectLocal");
            Site site = (Site) DynaSiteObjects.getClinicMap().get(siteId);
            Publisher publisher = new Publisher();
            publisher.setSiteId(siteId);
            publisher.setSite(site);
            publisher.setUrl(url);
            publisher.setRedirectLocal(redirectLocal);
            XmlUtils.save(publisher, file);
            // register this server w/ zeprs master
        }
        Publisher publisher = null;
        try {
            publisher = (Publisher) XmlUtils.getOne(file);
        } catch (FileNotFoundException e) {
            // it's ok - file not created yet.
        }
        request.setAttribute("publisher", publisher);
        List sites = DynaSiteObjects.getSiteList();
        request.setAttribute("sites", sites);
        Map statusMap = DynaSiteObjects.getStatusMap();
        if (statusMap.get("RSS-Gen-date")!= null) {
            Date generateStatusDate = (Date) statusMap.get("RSS-Gen-date");
            request.setAttribute("generateStatusDate", generateStatusDate.toString());
        }
        if (statusMap.get("RSS-message")!= null) {
            String statusMessage = (String) statusMap.get("RSS-message");
            request.setAttribute("statusMessage", statusMessage);
        }
        request.setAttribute("masterZip", Constants.ARCHIVE_PATH + Constants.MASTER_ARCHIVE_ZIP_FILENAME);
        return mapping.findForward(SUCCESS_FORWARD);
    }

}