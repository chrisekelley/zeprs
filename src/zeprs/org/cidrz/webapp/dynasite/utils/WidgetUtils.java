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

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.valueobject.gen.LabTest;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.dao.UserDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.utils.sort.DisplayOrderComparator;
import org.cidrz.webapp.dynasite.valueobject.District;
import org.cidrz.webapp.dynasite.valueobject.Drugs;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.FieldEnumeration;
import org.cidrz.webapp.dynasite.valueobject.FormField;
import org.cidrz.webapp.dynasite.valueobject.PageItem;
import org.cidrz.webapp.dynasite.valueobject.Site;
import org.cidrz.webapp.dynasite.valueobject.UserGroup;

import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;

/**
 *
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jul 12, 2005
 *         Time: 9:59:41 AM
 */
public class WidgetUtils {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(WidgetUtils.class);

    /**
     * @param conn
     * @param pageItem
     * @param widgetType
     * @return String used to render the widget
     */
    public static String getOne(Connection conn, PageItem pageItem, String value, Long formId, Long encounterId, String widgetType) {

        String item = null;
        FormField formField = pageItem.getForm_field();
        Long formFieldId = formField.getId();
        Long pageItemId = pageItem.getId();
        StringBuffer sbuf = new StringBuffer();
        String inputType = pageItem.getInputType();
        value = value.trim();
        Set multiEnumList = null;
        String inputValidationRange = null;
        String inputValidationType = null;

        // multiselect enums require special handling, since their enums come from their parent.
        if (inputType.equals("multiselect_item")) {
            Integer enumValue = null;
            String colName = formField.getStarSchemaName();
            if (!value.equals("")) {
                // Fetch the actual enum id
                try {
                    enumValue = (Integer) EncountersDAO.getValue(conn, encounterId, formId, colName);
                } catch (ServletException e) {
                    log.error(e);
                } catch (SQLException e) {
                    log.error(e);
                }
                FieldEnumeration enumeration = (FieldEnumeration) DynaSiteObjects.getFieldEnumerations().get(Long.valueOf(enumValue));
                Long enumFormFieldId = enumeration.getFieldId();
                TreeMap enumMap = (TreeMap) DynaSiteObjects.getFieldEnumerationsByField().get(enumFormFieldId);
                if (enumMap.size() != 0) {
                    Set fieldEnumSet = enumMap.entrySet();
                    Set enumSet = new TreeSet(new DisplayOrderComparator());
                    for (Iterator iterator = fieldEnumSet.iterator(); iterator.hasNext();) {
                        Map.Entry entry = (Map.Entry) iterator.next();
                        FieldEnumeration fieldEnumeration = (FieldEnumeration) entry.getValue();
                        enumSet.add(fieldEnumeration);
                    }
                    formField.setEnumerations(enumSet);
                }
            } else {
                Long masterId = Long.valueOf(pageItem.getVisibleDependencies1());
                if (masterId != null && masterId.intValue() > 0) {
                    TreeMap enumMap = (TreeMap) DynaSiteObjects.getFieldEnumerationsByField().get(masterId);
                    try {
                        if (enumMap.size() != 0) {
                            Set fieldEnumSet = enumMap.entrySet();
                            Set enumSet = new TreeSet(new DisplayOrderComparator());
                            for (Iterator iterator = fieldEnumSet.iterator(); iterator.hasNext();) {
                                Map.Entry entry = (Map.Entry) iterator.next();
                                FieldEnumeration fieldEnumeration = (FieldEnumeration) entry.getValue();
                                enumSet.add(fieldEnumeration);
                            }
                            formField.setEnumerations(enumSet);
                        }
                    } catch (NullPointerException e) {
                        log.error("Error getting multiselect_item enum by pageItemId: masterId: " + masterId + " pageItemId -  " + pageItemId + " field id: " + formFieldId);
                    }
                } else {
                    log.error("Error getting multiselect_item enum by pageItemId: masterId: " + masterId + " pageItemId -  " + pageItemId + " field id: " + formFieldId);
                }
            }

            // it's a bit of a process to get the correct formfield - please bare w/ me...
            //HashMap map = (HashMap) DynaSiteObjects.getFieldToPageItem().get(formId);   // get map of pageItems to formfield
            //Long enumPageItemId =  (Long) map.get(enumFormFieldId);
            //PageItem enumPageItem = (PageItem) DynaSiteObjects.getPageItems().get(enumPageItemId);
            // we may just need the fieldenum from dynasite....
            //FormField enumFormfield =  (FormField) map.get(enumFormFieldId);
        }

        String visibleEnumIdTrigger1 = null;
        if (pageItem.getVisibleEnumIdTrigger1() != null) {
            if (pageItem.getVisibleEnumIdTrigger1().equals("")) {
                visibleEnumIdTrigger1 = "0";
            } else {
                visibleEnumIdTrigger1 = pageItem.getVisibleEnumIdTrigger1();
            }
        } else {
            visibleEnumIdTrigger1 = "0";
        }

        String visibleEnumIdTrigger2 = null;
        if (pageItem.getVisibleEnumIdTrigger2() != null) {
            if (pageItem.getVisibleEnumIdTrigger2().equals("")) {
                visibleEnumIdTrigger2 = "0";
            } else {
                visibleEnumIdTrigger2 = pageItem.getVisibleEnumIdTrigger2();
            }
        } else {
            visibleEnumIdTrigger2 = "0";
        }

        // some of the inputTypes are not stored properly - we'll get them from formField
        if (formField.getType() != null) {
        	if (formField.getType().equals("Yes/No")) {
                inputType = "Yes/No";
            } else if (formField.getType().equals("sex")) {
                inputType = "sex";
            }
        }

        // some widgets are shared
        if (inputType.equals("radio")) {
            inputType = "select";
        }

        String dep = null;
        String update = null;

        String formProperty = null;
        String widgetName = null;
        if (widgetType.equals("Chart")) {
            formProperty = "field" + String.valueOf(encounterId) + "." + String.valueOf(formFieldId);
            widgetName = "inputWidget" + String.valueOf(encounterId) + "." + String.valueOf(formFieldId);
        } else if (widgetType.equals("Metadata"))  {
        	if (inputType.equals("dateVisit")) {
        		formProperty = "field1";
                widgetName = "inputWidget1";
        	} else {
        		formProperty = "field" + inputType;
                widgetName = "inputWidget" + inputType;
        	}

        } else {
        	formProperty = "field" + String.valueOf(formFieldId);
        	widgetName = "inputWidget" + String.valueOf(formFieldId);
        }

        String updateRecord = "updateRecord('" + inputType + "', "
                + formFieldId + " ," + pageItemId + " ," + formId + ", " + encounterId + " , '" + widgetType + "');";
        if (widgetType.equals("Chart")) {
           /* if (formFieldId.intValue() == 1563) {
                updateRecord = "updateRecordChart('" + inputType + "', "
                        + formFieldId + " ," + pageItemId + " ," + formId + ", " + encounterId + " , '" + widgetType + "','');";
            } else {*/
                updateRecord = "updateRecordChart('" + inputType + "', "
                        + formFieldId + " ," + pageItemId + " ," + formId + ", " + encounterId + " , '" + widgetType + "','');";
           //  }
        } else if (widgetType.equals("patientid"))  {
            updateRecord = "updateRecord('" + inputType + "', "
                + formFieldId + " ," + pageItemId + " ," + formId + ", " + encounterId + " , 'patient');";
        }  else if (widgetType.equals("Metadata")) {
        	updateRecord = "updateRecordMetadata('" + inputType + "', " + encounterId + ");";
        }

        String updateRecordIdNameY = "updateRecordIdName('" + inputType + "', "
                + formFieldId + " ," + pageItemId + " ," + formId + ", " + encounterId + ", 'Y', '" + widgetType + "');";
        String updateRecordIdNameN = "updateRecordIdName('" + inputType + "', "
                + formFieldId + " ," + pageItemId + " ," + formId + ", " + encounterId + ", 'N', '" + widgetType + "');";
        // Choices for update are "onclick, onmouseout, onchange"

        String dep1 = "toggleField('dropdown', " + visibleEnumIdTrigger1 + ", '" + pageItem.getVisibleDependencies1() +
                "','" + formField.getId() + "');";
        String dep2 = "toggleField2DepsChoice('dropdown', " + visibleEnumIdTrigger1
                + ", '" + pageItem.getVisibleDependencies1() + "',"
                + visibleEnumIdTrigger2 + ", '" + pageItem.getVisibleDependencies2() + "', '"
                + formField.getId() + "');";
        String depSm = "toggleFieldSafeMotherhood('dropdown', " + visibleEnumIdTrigger1
                + ", '" + pageItem.getVisibleDependencies1() + "',"
                + visibleEnumIdTrigger2 + ", '" + pageItem.getVisibleDependencies2() + "', '"
                + formField.getId() + "');";

        if ((inputType.equals("emptyDate")) || (inputType.equals("birthdate")) || (inputType.equals("dateToday")) || (inputType.equals("dateVisit"))) {
            String month = DateUtils.getMonth();
            String day = DateUtils.getDay();
            String year = DateUtils.getYear();
            String lastTwoYears = String.valueOf(new Integer(year).intValue() - 2);
            String eightyYears = String.valueOf(new Integer(year).intValue() - 80);
            String twoYears = String.valueOf(new Integer(year).intValue() + 2);
            String startYear;
            String endYear;
            if (inputType.equals("birthdate")) {
                startYear = eightyYears;
                endYear = year;
            } else {
                //startYear = lastTwoYears;
            	startYear = "2000";
                endYear = twoYears;
            }
            if (inputType.equals("dateVisit")) {
            	formFieldId = Long.valueOf(1);
            }
            sbuf.append("<span onclick=\"showCalendar('" + year + "','" + month + "','" + day + "','dd/MM/yyyy','form"
                    + formId + "','" + formProperty + "',event," + startYear + "," + endYear + ");\">" +
                    "<img alt=\"Select Date\" border=\"0\" src=\"/zeprs/images/calendar.gif\" align=\"middle\">" +
                    "</span>\n");
            sbuf.append("<span id=\"span" + formProperty + "\" onclick=\"showCalendar('" + year + "','" + month + "','" + day + "','dd/MM/yyyy','form"
                    + formId + "','" + formProperty + "',event," + startYear + "," + endYear + ");\"></span>\n");
            sbuf.append("<span style=\"display:none;\">\n" +
                    "   <input type=\"text\" id=\"" + widgetName + "\" name=\"field" + formFieldId + "\"/>\n" +
                    "</span>\n");
            sbuf.append("<input type=\"button\" name=\"_add\" value=\"Change\"" + getEventHandler("onclick", updateRecord, encounterId) + ">\n");
            item = sbuf.toString();

        } else if (inputType.equals("select") || inputType.equals("select-dwr")) {
            Set enumList = formField.getEnumerations();
            renderSelect(visibleEnumIdTrigger1, sbuf, widgetName, formFieldId, updateRecord, dep1, encounterId, pageItem, visibleEnumIdTrigger2, dep2, value, enumList);
            item = sbuf.toString();
        } else if (inputType.equals("multiselect_item")) {
            Set enumList = formField.getEnumerations();
            renderSelect(visibleEnumIdTrigger1, sbuf, widgetName, formFieldId, updateRecord, dep1, encounterId, pageItem, visibleEnumIdTrigger2, dep2, value, enumList);
            item = sbuf.toString();
        } else if (inputType.equals("multiselect_enum")) {
            item = "Please make a selection from one of the following choices below.";
        } else if (inputType.equals("patientid_districts")) {
            List districts = DynaSiteObjects.getDistricts();
            sbuf.append("<select id=\"" + widgetName + "\" name=\"field" + formFieldId + "\"" + getEventHandler("onchange", updateRecord, encounterId) + ">\n");
            sbuf.append("   <option value=\"\">No Information</option>\n");
            for (int i = 0; i < districts.size(); i++) {
                District district = (District) districts.get(i);
                if (value.equals(district.getDistrictId())) {
                    sbuf.append("   <option value=\"" + district.getDistrictId() + "\" selected>" +
                            district.getDistrictName() + " (" + district.getDistrictId() + ")" + "</option>\n");
                } else {
                    sbuf.append("   <option value=\"" + district.getDistrictId() + "\">" +
                            district.getDistrictName() + " (" + district.getDistrictId() + ")" + "</option>\n");
                }
            }
            sbuf.append("</select>\n");
            item = sbuf.toString();
        } else if (inputType.equals("patientid_sites") || inputType.equals("sites") || inputType.equals("sites_not_selected")) {
            List sites = null;
            sites = DynaSiteObjects.getClinics();
            sbuf.append("<select id=\"" + widgetName + "\" name=\"field" + formFieldId + "\"" + getEventHandler("onchange", updateRecord, encounterId) + ">\n");
            sbuf.append("   <option value=\"\">No Information</option>\n");
            for (int i = 0; i < sites.size(); i++) {
                Site site = (Site) sites.get(i);
                if (site.getInactive() == null) {
                    String siteId = null;
                    String abbrev = null;
                    if (inputType.equals("patientid_sites")) {
                        //siteId = site.getSiteAlphaId().substring(0, 2);
                        siteId = site.getSiteAlphaId();
                        abbrev = " (" + siteId + ")";
                    } else {
                        siteId = site.getId().toString();
                        abbrev = "";
                    }
                    if (value.equals(site.getSiteAlphaId())) {
                        sbuf.append("   <option value=\"" + siteId + "\" selected>" +
                                site.getName() + abbrev + "</option>\n");
                    } else {
                        sbuf.append("   <option value=\"" + siteId + "\">" +
                                site.getName() + abbrev + "</option>\n");
                    }
                }
            }
            sbuf.append("</select>\n");
            item = sbuf.toString();
        } else if (inputType.equals("patientid")) {
            //ExecutionContext exec = ExecutionContext.get();
            WebContext exec = WebContextFactory.get();
            SessionUtil zeprs_session = null;
            String patientSiteId = null;
            try {
                zeprs_session = (SessionUtil) exec.getSession().getAttribute("zeprs_session");
                patientSiteId = zeprs_session.getClientSettings().getSiteId().toString();
            } catch (Exception e) {
                // unit testing - it's ok...
            }
            Site site = (Site) DynaSiteObjects.getClinicMap().get(new Long(patientSiteId));
            String siteAlphaId = site.getSiteAlphaId().substring(0, 2);
            String clinicId = site.getSiteAlphaId().substring(2, 3);
            ArrayList uthSubsites = new ArrayList();
            uthSubsites.add("A");
            uthSubsites.add("B");
            uthSubsites.add("C");
            uthSubsites.add("D");

            sbuf.append("<input type=\"hidden\" id=\"district\" value=\"5040\">\n");
            //sbuf.append("<input type=\"text\" id=\"site\" value=\"" + siteAlphaId + "\">\n");
            sbuf.append("<input type=\"hidden\" id=\"site\" value=\"\">\n");
            sbuf.append("<br/>Select Subsite: <select id=\"subsite\" onchange=\"calcPatientId();\"><option> -- </option>\n");
            if (clinicId.equals("A") || clinicId.equals("B") || clinicId.equals("C") || clinicId.equals("D")) {
                for (int i = 0; i < uthSubsites.size(); i++) {
                    String subsite = (String) uthSubsites.get(i);
                    if (clinicId.equals(subsite)) {
                        sbuf.append("<option value=\"" + subsite + "\" selected=\"selected\">* " + subsite + " *</option>\n");
                    } else {
                        sbuf.append("<option value=\"" + subsite + "\"> " + subsite + " </option>\n");
                    }
                }
            } else {
                int subsite = 0;
                while(subsite <=10) {
                   int clinicIdInt = new Integer(clinicId);
                   if (clinicIdInt == subsite) {
                        sbuf.append("<option value=\"" + subsite + "\" selected=\"selected\">* " + subsite + " *</option>\n");
                    } else {
                        sbuf.append("<option value=\"" + subsite + "\"> " + subsite + " </option>\n");
                    }
                   subsite ++;
                }
            }
            sbuf.append("</select>\n");
            sbuf.append("<input class=\"ibutton\" onclick=\"copySite();setPatientID('patient','Please select District and clinic fields.','patientid');\" value=\"Get New ID\" title=\"Get New ID\" type=\"button\">\n");
            sbuf.append("<span id=\"spanpatient\"></span>");
            sbuf.append("<div id=\"patientIdRow\"><br/> -- or -- <br/><br/>If patient already has an ID please enter the last five digits:");
            sbuf.append("<span id=\"patientIdFields\">\n" +
                    "                <input id=\"patientid\" name=\"patientid\" size=\"4\" maxlength=\"5\" onchange=\"copySite();calcPatientId()\" type=\"text\">\n" +
                    "                <input id=\"checksum\" name=\"checksum\" onchange=\"calcPatientId()\" type=\"hidden\">\n" +
                    "                <input id=\"checkPatientId\" class=\"ibutton\" onclick='copySite();checkPatientID(\"patient\",\"Please select/enter all of the Patient ID fields.\",\"patientid\");' value=\"Check ID\" title=\"Check ID\" type=\"button\">\n" +
                    "            </span>\n</div>\n");
            sbuf.append("<span id=\"d1\" class=\"reply\"></span>\n");
            sbuf.append("<input id=\"patient\" type=\"hidden\" name=\"field" + formFieldId + "\" size=\"20\" maxlength=\"255\"" + "\" value=\"" + value + "\"/>\n");
            // sbuf.append("<input id=\"" + widgetName + "\" type=\"text\" name=\"field" + formFieldId + "\" size=\"20\" maxlength=\"255\"" + "\" value=\"" + value + "\"" + getEventHandler("onchange", updateRecord, encounterId) + "/>\n");
            sbuf.append("<input id=\"siteId\" type=\"hidden\" name=\"field" + formFieldId + "\"/>\n");

            /*if ((pageItem.getSize() != null) && (pageItem.getSize().intValue() > 0)) {
                sbuf.append("<input id=\"" + widgetName + "\" type=\"text\" name=\"field" + formFieldId + "\" size=\"" + pageItem.getSize() + "\" maxlength=\"" + pageItem.getMaxlength() + "\" value=\"" + value + "\"" + getEventHandler("onchange", updateRecord, encounterId) + "/>\n");
            } else {
            }*/
            item = sbuf.toString();
        } else if (inputType.equals("currentMedicine")) {
            List drugs = null;
            // drugs = DrugsDAO.getAll();
            drugs = DynaSiteObjects.getDrugs();
            sbuf.append("<select id=\"" + widgetName + "\" name=\"field" + formFieldId + "\"" + getEventHandler("onchange", updateRecord, encounterId) + ">\n");
            sbuf.append("   <option value=\"\">No Information</option>\n");
            for (int i = 0; i < drugs.size(); i++) {
                Drugs drug = (Drugs) drugs.get(i);
                //if (value.equals(drug.getName())) {
                Pattern pattern = Pattern.compile(drug.getName());
                Matcher matcher = pattern.matcher(value);
                if (matcher.find() == true) {
                    if (drug.getTeratogenic() != null) {
                        sbuf.append("   <option value=\"" + drug.getId() + "\" selected  class=\"teratogenicAlert\">" +
                                drug.getName() + " *" + drug.getTeratogenic() + "*</option>\n");
                    } else {
                        sbuf.append("   <option value=\"" + drug.getId() + "\" selected>" +
                                drug.getName() + "</option>\n");
                    }
                } else {
                    if (drug.getTeratogenic() != null) {
                        sbuf.append("   <option value=\"" + drug.getId() + "\" class=\"teratogenicAlert\">" +
                                drug.getName() + " *" + drug.getTeratogenic() + "*</option>\n");
                    } else {
                        sbuf.append("   <option value=\"" + drug.getId() + "\">" +
                                drug.getName() + "</option>\n");
                    }
                }
            }
            sbuf.append("</select>\n");
            item = sbuf.toString();

        } else if (inputType.equals("firm")) {
            sbuf.append("<select id=\"" + widgetName + "\" name=\"field" + formFieldId + "\"" + getEventHandler("onchange", updateRecord, encounterId) + ">\n");
            sbuf.append("   <option value=\"\">No Information</option>\n");
            ArrayList firms = new ArrayList();
            firms.add("A");
            firms.add("B");
            firms.add("C");
            firms.add("D");
            firms.add("E");
            for (int i = 0; i < firms.size(); i++) {
                String firm =  (String) firms.get(i);
               sbuf.append("   <option value=\"" + firm + "\">" + firm + "</option>\n");
            }
            sbuf.append("</select>\n");
            item = sbuf.toString();

        } else if (inputType.equals("ega") || inputType.equals("ega_pregnancyDating")) {

            sbuf.append("<select id=\"" + widgetName + "\" name=\"field" + formFieldId + "\"" + getEventHandler("onchange", updateRecord, encounterId) + ">\n");
            sbuf.append("   <option value=\"\">No Information</option>\n");
            for (int i = 0; i < 350; i++) {

                int days = i % 7;
                int weeks = i / 7;
                String thisEga = weeks + ", " + days + "/7";
                if (value.equals(thisEga)) {
                    sbuf.append("   <option value=\"" + i + "\" selected>" +
                            weeks + " weeks, " + days + "/ 7 days" + "</option>\n");
                } else {
                    sbuf.append("   <option value=\"" + i + "\">" +
                            weeks + " weeks, " + days + "/ 7 days" + "</option>\n");
                }
            }
            sbuf.append("</select>\n");
            item = sbuf.toString();

        } else if (inputType.equals("lab_results")) {
            Long enumFormFieldId = null;
            LabTest labTest = null;
            try {
                labTest = (LabTest) EncountersDAO.getOneById(conn, encounterId, formId, LabTest.class);
            } catch (IOException e) {
                log.error(e);
            } catch (ServletException e) {
                log.error(e);
            } catch (SQLException e) {
                log.error(e);
            } catch (ObjectNotFoundException e) {
                log.error(e);
            }
            String labInputType = "labSelect";
            Integer labTypeId = labTest.getField1845();
            Long hbFormfieldId = null;
            switch (labTypeId.intValue()) {
                case 2923: // Blood Type
                    enumFormFieldId = new Long(193);
                    break;
                case 2924: // Rhesus Status
                    enumFormFieldId = new Long(196);
                    break;
                case 2925: // Hb - 1st screen
                    labInputType = "hbInput";
                    hbFormfieldId = new Long(1858);
                    /*inputValidationRange = "0.5,20";
                    inputValidationType = "float";*/
                    break;
                case 2926:  // Hb - 2nd screen
                    labInputType = "hbInput";
                    hbFormfieldId = new Long(1858);
                    break;
                case 2927:  // Cervical Smear
                    enumFormFieldId = new Long(207);
                    break;
                case 2928:  // Sickle Cell Screen
                    enumFormFieldId = new Long(1460);
                    break;
                case 2929:  // Malaria
                    enumFormFieldId = new Long(1462);
                    break;
                case 3042:  // CD4 count
                    labInputType = "hbInput";
                    hbFormfieldId = new Long(2004);
                    break;
            }

            if (labInputType.equals("labSelect")) {
                if (pageItemId == 3861) {
                    updateRecord = "updateRecordChart('" + inputType + "', "
                            + formFieldId + " ," + pageItemId + " ," + formId + ", " + encounterId + " , '" + widgetType + "','1858');";
                } else {
                    updateRecord = "updateRecordChart('" + inputType + "', "
                            + formFieldId + " ," + pageItemId + " ," + formId + ", " + encounterId + " , '" + widgetType + "','');";
                }

                Set enumList = null;
                if ((!visibleEnumIdTrigger1.equals("0")) && (new Long(visibleEnumIdTrigger1).intValue() > 0) && (formField.getId().intValue() != 1677))
                {
                    sbuf.append("<select id=\"" + widgetName + "\" name=\"field" + formFieldId + "\"" + WidgetUtils.getEventHandler("onchange", updateRecord, dep1, encounterId, pageItem) + ">\n");
                } else if ((!visibleEnumIdTrigger1.equals("0")) && new Long(visibleEnumIdTrigger2).intValue() > 0) {
                    sbuf.append("<select id=\"" + widgetName + "\" name=\"field" + formFieldId + "\"" + getEventHandler("onchange", dep2, updateRecord, encounterId, pageItem) + ">\n");
                } else {
                    sbuf.append("<select id=\"" + widgetName + "\" name=\"field" + formFieldId + "\"" + getEventHandler("onchange", updateRecord, encounterId) + ">\n");
                }
                sbuf.append("   <option value=\"\">No Information</option>\n");

                TreeMap enumMap = (TreeMap) DynaSiteObjects.getFieldEnumerationsByField().get(enumFormFieldId);
                Set fieldEnumSet = enumMap.entrySet();
                enumList = new TreeSet(new DisplayOrderComparator());
                for (Iterator iterator = fieldEnumSet.iterator(); iterator.hasNext();) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    FieldEnumeration fieldEnumeration = (FieldEnumeration) entry.getValue();
                    enumList.add(fieldEnumeration);
                }
                for (Iterator iterator = enumList.iterator(); iterator.hasNext();) {
                    FieldEnumeration fieldEnumeration = (FieldEnumeration) iterator.next();
                    if (fieldEnumeration.isEnabled()) {
                        if (value.equals(fieldEnumeration.getId().toString())) {
                            sbuf.append("   <option value=\"" + fieldEnumeration.getId() + "\" selected>" +
                                    fieldEnumeration.getEnumeration() + "</option>\n");
                        } else {
                            sbuf.append("   <option value=\"" + fieldEnumeration.getId() + "\">" +
                                    fieldEnumeration.getEnumeration() + "</option>\n");
                        }
                    }
                }
                sbuf.append("</select>\n");

            } else if (labInputType.equals("hbInput")) {
                PageItem hbPageItem=null;
                if (labTypeId.intValue() == 3042) {	//CD4 count
                   //  formFieldId = Long.valueOf(2004);
                    hbPageItem = (PageItem) DynaSiteObjects.getPageItems().get(new Long(4164));
                } else {
                    hbPageItem = (PageItem) DynaSiteObjects.getPageItems().get(new Long(3872));
                }
                inputType = hbPageItem.getInputType();
                pageItemId = hbPageItem.getId();
                if (pageItemId == 3861) {
                    updateRecord = "updateRecordChart('" + inputType + "', "
                            + formFieldId + " ," + pageItemId + " ," + formId + ", " + encounterId + " , '" + widgetType + "','1858');";
                } else if (pageItemId == 4164) {
                    updateRecord = "updateRecordChart('" + inputType + "', "
                            + formFieldId + " ," + pageItemId + " ," + formId + ", " + encounterId + " , '" + widgetType + "','1858');";
                } else {
                    updateRecord = "updateRecordChart('" + inputType + "', "
                            + hbFormfieldId + " ," + pageItemId + " ," + formId + ", " + encounterId + " , '" + widgetType + "'," + formFieldId + ");";
                }
                if ((hbPageItem.getSize() != null) & (hbPageItem.getSize().intValue() > 0)) {
                    sbuf.append("<input id=\"" + widgetName + "\" type=\"text\" name=\"field" + hbFormfieldId + "\" size=\"" + hbPageItem.getSize() + "\" maxlength=\"" + hbPageItem.getMaxlength() + "\"" + getEventHandler("onchange", updateRecord, encounterId) + "/>\n");
                } else {
                    sbuf.append("<input id=\"" + widgetName + "\" type=\"text\" name=\"field" + hbFormfieldId + "\" size=\"20\" maxlength=\"255\"" + getEventHandler("onchange", updateRecord, encounterId) + "/>\n");
                }
            }
            item = sbuf.toString();
        } else if (inputType.equals("textarea")) {
            Integer cols = pageItem.getCols();
            Integer rows = pageItem.getRows();
            if (cols == 0) {
                cols = 32;
            }
            if (rows == 0) {
                rows = 2;
            }
            if (!value.trim().equals("")) {
                sbuf.append("<textarea id=\"" + widgetName + "\" name=\"" + widgetName + "\" cols=\"" + cols + "\" rows=\"" + rows + "\"" + getEventHandler("onchange", updateRecord, encounterId) + ">" + value + "</textarea>\n");
            } else {
                sbuf.append("<textarea id=\"" + widgetName + "\" name=\"" + widgetName + "\" cols=\"" + cols + "\" rows=\"" + rows + "\"" + getEventHandler("onchange", updateRecord, encounterId) + "/>\n");
            }
            item = sbuf.toString();

        } else if (inputType.equals("checkbox") || inputType.equals("checkbox_dwr")) {
            if (value.equals("Yes")) {
                sbuf.append("<input type=\"checkbox\" checked id=\"" + widgetName + "\" name=\"field" + formFieldId + "\" " + getEventHandler("onchange", updateRecord, encounterId) + ">" + "\n");
            } else if (value.equals("1")) {
                sbuf.append("<input type=\"checkbox\" checked id=\"" + widgetName + "\" name=\"field" + formFieldId + "\" " + getEventHandler("onchange", updateRecord, encounterId) + ">" + "\n");
            } else {
                sbuf.append("<input type=\"checkbox\" id=\"" + widgetName + "\" name=\"field" + formFieldId + "\" " + getEventHandler("onchange", updateRecord, encounterId) + ">" + "\n");
            }
            item = sbuf.toString();

        } else if (inputType.equals("text") || inputType.equals("text-dwr")) {
        	if (formField.getType().equals("Time")) {
        		String hourValue = "";
        		String minuteValue = "";
        		String hourPresetValue = "";
        		String minutePresetValue = "";
        		String[] result = value.split(":");
        		hourPresetValue = result[0];
        		minutePresetValue = result[1];
        		sbuf.append("<select id=\"hour"+ formFieldId + "\" onchange=\"calcTime(\'" + formFieldId + "\',\'inputWidget\')\"><option value=\"\">--</option>");
        		int i = 0;
        		while (i <24) {
					if (i<10) {
						hourValue = "0" + i;
						//sbuf.append("<c:set var=\"hourValue\" value=\"0" + i + "\"/>");
					} else {
						hourValue = "" + i;
						//sbuf.append("<c:set var=\"hourValue\" value=\"0\"/>");
					}
					if (hourPresetValue.equals(hourValue)) {
						sbuf.append("<option value=\"" + hourValue + "\" selected=\"selected\">" + hourValue + "</option>");
					} else {
						sbuf.append("<option value=\"" + hourValue + "\">" + hourValue + "</option>");
					}
					i++;
				}
        		sbuf.append("</select>");
        		sbuf.append("<select id=\"minute"+ formFieldId + "\" onchange=\"calcTime(\'" + formFieldId + "\',\'inputWidget\')\"><option value=\"\">--</option>");
        		i = 0;
        		while (i <60) {
					if (i<10) {
						minuteValue = "0" + i;
						//sbuf.append("<c:set var=\"hourValue\" value=\"0" + i + "\"/>");
					} else {
						minuteValue = "" + i;
						//sbuf.append("<c:set var=\"hourValue\" value=\"0\"/>");
					}
					if (minutePresetValue.equals(minuteValue)) {
						sbuf.append("<option value=\"" + minuteValue + "\" selected=\"selected\">" + minuteValue + "</option>");
					} else {
						sbuf.append("<option value=\"" + minuteValue + "\">" + minuteValue + "</option>");
					}
					i++;
				}
        		sbuf.append("</select>");
        		sbuf.append("<input id=\"" + widgetName + "\" type=\"hidden\" name=\"field" + formFieldId + "\" /><a href=\"#\" onclick=\"setTimeField(\'" + formFieldId + "\',\'inputWidget\');\">(Current Time)</a>");
                sbuf.append("<input type=\"button\" name=\"_add\" value=\"Change\"" + getEventHandler("onclick", updateRecord, encounterId) + ">\n");
                //sbuf.append("<input id=\"" + widgetName + "\" type=\"text\" name=\"field" + formFieldId + "\" size=\"20\" maxlength=\"255\" value=\"" + value + "\"" + getEventHandler("onchange", updateRecord, encounterId) + "/>\n");
        	} else {
        		if ((pageItem.getSize() != null) && (pageItem.getSize().intValue() > 0)) {
                	if (pageItem.getMaxlength() != null && pageItem.getMaxlength().intValue() >0) {
                        sbuf.append("<input id=\"" + widgetName + "\" type=\"text\" name=\"field" + formFieldId + "\" size=\"" + pageItem.getSize() + "\" maxlength=\"" + pageItem.getMaxlength() + "\" value=\"" + value + "\"" + getEventHandler("onchange", updateRecord, encounterId) + "/>\n");
                	} else {
                        sbuf.append("<input id=\"" + widgetName + "\" type=\"text\" name=\"field" + formFieldId + "\" size=\"" + pageItem.getSize() + "\" maxlength=\"255\" value=\"" + value + "\"" + getEventHandler("onchange", updateRecord, encounterId) + "/>\n");
                	}
                } else {
                    sbuf.append("<input id=\"" + widgetName + "\" type=\"text\" name=\"field" + formFieldId + "\" size=\"20\" maxlength=\"255\" value=\"" + value + "\"" + getEventHandler("onchange", updateRecord, encounterId) + "/>\n");
                }
        	}
            // sbuf.append("<input type=\"button\" name=\"_add\" value=\"Change\"" + onClick + ">\n");
            item = sbuf.toString();

        } else if (inputType.equals("month_no_label")) {
            if ((pageItem.getSize() != null) && (pageItem.getSize().intValue() > 0)) {
                sbuf.append("<input id=\"" + widgetName + "\" type=\"text\" name=\"field" + formFieldId + "\" size=\"" + pageItem.getSize() + "\" maxlength=\"" + pageItem.getMaxlength() + "\"" + getEventHandler("onchange", updateRecord, encounterId) + "/>\n");
            } else {
                sbuf.append("<input id=\"" + widgetName + "\" type=\"text\" name=\"field" + formFieldId + "\" size=\"20\" maxlength=\"255\"" + getEventHandler("onchange", updateRecord, encounterId) + "/>\n");
            }
            // sbuf.append("<input type=\"button\" name=\"_add\" value=\"Change\"" + onClick + ">\n");
            item = sbuf.toString();

        } else if (inputType.equals("Yes/No")) {
            if (value.equals("true")) {
                sbuf.append("<input type=\"radio\" name=\"field" + formFieldId + "\" id=\"fieldY" + formFieldId + "\" value=\"1\" checked " + getEventHandler("onclick", updateRecordIdNameY, encounterId) + "/><label for=\"" + formFieldId + "\">Yes</label>\n");
                sbuf.append("<input type=\"radio\" name=\"field" + formFieldId + "\" id=\"fieldN" + formFieldId + "\" value=\"0\" " + getEventHandler("onclick", updateRecordIdNameN, encounterId) + "/><label for=\"" + formFieldId + "\">No</label>\n");
            } else if (value.equals("Yes")) {
                sbuf.append("<input type=\"radio\" name=\"field" + formFieldId + "\" id=\"fieldY" + formFieldId + "\" value=\"1\" checked " + getEventHandler("onclick", updateRecordIdNameY, encounterId) + "/><label for=\"" + formFieldId + "\">Yes</label>\n");
                sbuf.append("<input type=\"radio\" name=\"field" + formFieldId + "\" id=\"fieldN" + formFieldId + "\" value=\"0\" " + getEventHandler("onclick", updateRecordIdNameN, encounterId) + "/><label for=\"" + formFieldId + "\">No</label>\n");
            } else if (value.equals("false")) {
                sbuf.append("<input type=\"radio\" name=\"field" + formFieldId + "\" id=\"fieldY" + formFieldId + "\" value=\"1\" " + getEventHandler("onclick", updateRecordIdNameY, encounterId) + "/><label for=\"" + formFieldId + "\">Yes</label>\n");
                sbuf.append("<input type=\"radio\" name=\"field" + formFieldId + "\" id=\"fieldN" + formFieldId + "\" value=\"0\" checked " + getEventHandler("onclick", updateRecordIdNameN, encounterId) + "/><label for=\"" + formFieldId + "\">No</label>\n");
            } else if (value.equals("No")) {
                sbuf.append("<input type=\"radio\" name=\"field" + formFieldId + "\" id=\"fieldY" + formFieldId + "\" value=\"1\" " + getEventHandler("onclick", updateRecordIdNameY, encounterId) + "/><label for=\"" + formFieldId + "\">Yes</label>\n");
                sbuf.append("<input type=\"radio\" name=\"field" + formFieldId + "\" id=\"fieldN" + formFieldId + "\" value=\"0\" checked " + getEventHandler("onclick", updateRecordIdNameN, encounterId) + "/><label for=\"" + formFieldId + "\">No</label>\n");
            } else {
                sbuf.append("<input type=\"radio\" name=\"field" + formFieldId + "\" id=\"fieldY" + formFieldId + "\" value=\"1\" " + getEventHandler("onclick", updateRecordIdNameY, encounterId) + "/><label for=\"" + formFieldId + "\">Yes</label>\n");
                sbuf.append("<input type=\"radio\" name=\"field" + formFieldId + "\" id=\"fieldN" + formFieldId + "\" value=\"0\" " + getEventHandler("onclick", updateRecordIdNameN, encounterId) + "/><label for=\"" + formFieldId + "\">No</label>\n");
            }
            item = sbuf.toString();
        } else if (inputType.equals("sex")) {
            if (value.equals("Female")) {
                sbuf.append("<input type=\"radio\" name=\"field" + formFieldId + "\" id=\"fieldY" + formFieldId + "\" value=\"1\" checked " + getEventHandler("onclick", updateRecordIdNameY, encounterId) + "/><label for=\"" + formFieldId + "\">Female</label>\n");
                sbuf.append("<input type=\"radio\" name=\"field" + formFieldId + "\" id=\"fieldN" + formFieldId + "\" value=\"2\" " + getEventHandler("onclick", updateRecordIdNameN, encounterId) + "/><label for=\"" + formFieldId + "\">Male</label>\n");
            } else if (value.equals("Male")) {
                sbuf.append("<input type=\"radio\" name=\"field" + formFieldId + "\" id=\"fieldY" + formFieldId + "\" value=\"1\" " + getEventHandler("onclick", updateRecordIdNameY, encounterId) + "/><label for=\"" + formFieldId + "\">Female</label>\n");
                sbuf.append("<input type=\"radio\" name=\"field" + formFieldId + "\" id=\"fieldN" + formFieldId + "\" value=\"2\" checked " + getEventHandler("onclick", updateRecordIdNameN, encounterId) + "/><label for=\"" + formFieldId + "\">Male</label>\n");
            } else {
                sbuf.append("<input type=\"radio\" name=\"field" + formFieldId + "\" id=\"fieldY" + formFieldId + "\" value=\"1\" " + getEventHandler("onclick", updateRecordIdNameY, encounterId) + "/><label for=\"" + formFieldId + "\">Female</label>\n");
                sbuf.append("<input type=\"radio\" name=\"field" + formFieldId + "\" id=\"fieldN" + formFieldId + "\" value=\"2\" " + getEventHandler("onclick", updateRecordIdNameN, encounterId) + "/><label for=\"" + formFieldId + "\">Male</label>\n");
            }
            item = sbuf.toString();
        } else if (inputType.equals("apgar")) {
            sbuf.append("<p>Click a value from each row to calculate the Apgar score.</p>");
            sbuf.append("<table border=1 cellspacing=\"0\" cellpadding=2 id=\"" + formFieldId + "apgar\">\n" +
                    "\t<tr bgcolor=\"#99ccff\" id=\"" + formFieldId + "min\">\n" +
                    "\t\t<th>Signs</th>\n" +
                    "\t\t<th>0</th>\n" +
                    "\t\t<th>1</th>\n" +
                    "\n" +
                    "\t\t<th>2</th>\n" +
                    "\t\t<th id=\"" + formFieldId + "1min\" style=\"background-color: #0033cc; color: White;\" onMouseOver=\"this.style.cursor='pointer'\" onClick=\"whatMin(this.id);\"><br>score</th>\n" +
                    "\t</tr>\n" +
                    "\n" +
                    "\t<tr id=\"" + formFieldId + "h\">\n" +
                    "\t\t<th align=right>Heart Rate</th>\n" +
                    "\n" +
                    "\t\t<td align=center id=\"" + formFieldId + "h0\" onMouseOver=\"bgclr(this,1); this.style.cursor='pointer'\" onMouseOut=\"bgclr(this,0);\" onClick=\"bgclr(this,2," + formFieldId + ");\">Absent</td>\n" +
                    "\t\t<td align=center id=\"" + formFieldId + "h1\" onMouseOver=\"bgclr(this,1); this.style.cursor='pointer'\" onMouseOut=\"bgclr(this,0);\" onClick=\"bgclr(this,2," + formFieldId + ");\">Below 100</td>\n" +
                    "\t\t<td align=center id=\"" + formFieldId + "h2\" onMouseOver=\"bgclr(this,1); this.style.cursor='pointer'\" onMouseOut=\"bgclr(this,0);\" onClick=\"bgclr(this,2," + formFieldId + ");\">Above 100</td>\n" +
                    "\t\t<td class=\"score\" id=\"" + formFieldId + "1h\" onClick=\"whatMin(this.id," + formFieldId + ");\">&nbsp;</td>\n" +
                    "\t</tr>\n" +
                    "\n" +
                    "\t<tr id=\"" + formFieldId + "r\">\n" +
                    "\t\t<th align=right>Respiratory Effort</th>\n" +
                    "\t\t<td align=center id=\"" + formFieldId + "r0\" onMouseOver=\"bgclr(this,1); this.style.cursor='pointer'\" onMouseOut=\"bgclr(this,0);\" onClick=\"bgclr(this,2," + formFieldId + ");\">Absent</td>\n" +
                    "\n" +
                    "\t\t<td align=center id=\"" + formFieldId + "r1\" onMouseOver=\"bgclr(this,1); this.style.cursor='pointer'\" onMouseOut=\"bgclr(this,0);\" onClick=\"bgclr(this,2," + formFieldId + ");\">Slow, irregular</td>\n" +
                    "\t\t<td align=center id=\"" + formFieldId + "r2\" onMouseOver=\"bgclr(this,1); this.style.cursor='pointer'\" onMouseOut=\"bgclr(this,0);\" onClick=\"bgclr(this,2," + formFieldId + ");\">Good, crying</td>\n" +
                    "\t\t<td class=\"score\" id=\"" + formFieldId + "1r\" onClick=\"whatMin(this.id," + formFieldId + ");\">&nbsp;</td>\n" +
                    "\t</tr>\n" +
                    "\n" +
                    "\t<tr id=\"" + formFieldId + "m\">\n" +
                    "\t\t<th align=right>Muscle Tone</th>\n" +
                    "\t\t<td align=center id=\"" + formFieldId + "m0\" onMouseOver=\"bgclr(this,1); this.style.cursor='pointer'\" onMouseOut=\"bgclr(this,0);\" onClick=\"bgclr(this,2," + formFieldId + ");\">Nil (Limp)</td>\n" +
                    "\t\t<td align=center id=\"" + formFieldId + "m1\" onMouseOver=\"bgclr(this,1); this.style.cursor='pointer'\" onMouseOut=\"bgclr(this,0);\" onClick=\"bgclr(this,2," + formFieldId + ");\">Some flexion<br>of extremities</td>\n" +
                    "\n" +
                    "\t\t<td align=center id=\"" + formFieldId + "m2\" onMouseOver=\"bgclr(this,1); this.style.cursor='pointer'\" onMouseOut=\"bgclr(this,0);\" onClick=\"bgclr(this,2," + formFieldId + ");\">Active motion</td>\n" +
                    "\t\t<td class=\"score\" id=\"" + formFieldId + "1m\" onClick=\"whatMin(this.id," + formFieldId + ");\">&nbsp;</td>\n" +
                    "\n" +
                    "\t</tr>\n" +
                    "\n" +
                    "\t<tr id=\"" + formFieldId + "x\">\n" +
                    "\t\t<th align=right>Reflex irritability</th>\n" +
                    "\t\t<td align=center id=\"" + formFieldId + "x0\" onMouseOver=\"bgclr(this,1); this.style.cursor='pointer'\" onMouseOut=\"bgclr(this,0);\" onClick=\"bgclr(this,2," + formFieldId + ");\">No response</td>\n" +
                    "\n" +
                    "\t\t<td align=center id=\"" + formFieldId + "x1\" onMouseOver=\"bgclr(this,1); this.style.cursor='pointer'\" onMouseOut=\"bgclr(this,0);\" onClick=\"bgclr(this,2," + formFieldId + ");\">Grimace</td>\n" +
                    "\t\t<td align=center id=\"" + formFieldId + "x2\" onMouseOver=\"bgclr(this,1); this.style.cursor='pointer'\" onMouseOut=\"bgclr(this,0);\" onClick=\"bgclr(this,2," + formFieldId + ");\">Vigorous Cry</td>\n" +
                    "\t\t<td class=\"score\" id=\"" + formFieldId + "1x\" onClick=\"whatMin(this.id," + formFieldId + ");\">&nbsp;</td>\n" +
                    "\t</tr>\n" +
                    "\n" +
                    "\t<tr id=\"" + formFieldId + "c\">\n" +
                    "\t\t<th align=right>Color</th>\n" +
                    "\t\t<td align=center id=\"" + formFieldId + "c0\" onMouseOver=\"bgclr(this,1); this.style.cursor='pointer'\" onMouseOut=\"bgclr(this,0);\" onClick=\"bgclr(this,2," + formFieldId + ");\">Blue, pale</td>\n" +
                    "\t\t<td align=center id=\"" + formFieldId + "c1\" onMouseOver=\"bgclr(this,1); this.style.cursor='pointer'\" onMouseOut=\"bgclr(this,0);\" onClick=\"bgclr(this,2," + formFieldId + ");\">Body pink,<br>extremities blue</td>\n" +
                    "\n" +
                    "\t\t<td align=center id=\"" + formFieldId + "c2\" onMouseOver=\"bgclr(this,1); this.style.cursor='pointer'\" onMouseOut=\"bgclr(this,0);\" onClick=\"bgclr(this,2," + formFieldId + ");\">Completely pink</td>\n" +
                    "\t\t<td class=\"score\" id=\"" + formFieldId + "1c\" onClick=\"whatMin(this.id," + formFieldId + ");\">&nbsp;</td>\n" +
                    "\t</tr>\n" +
                    "\n" +
                    "\t<tr id=\"" + formFieldId + "total\">\n" +
                    "\t\t<th height=\"44px\" colspan=4 align=right>\n" +
                    "\t\t\tTOTAL SCORE :&nbsp;\n" +
                    "\t\t</th>\n" +
                    "\t\t<td class=\"score\" id=\"" + formFieldId + "1score\" onClick=\"whatMin(this.id," + formFieldId + ");\">&nbsp;</td>\n" +
                    "\t</tr>\n" +
                    "</table>");
            sbuf.append("<input type=\"button\" name=\"_add\" value=\"Change\"" + getEventHandler("onclick", updateRecord, encounterId) + ">\n");
            sbuf.append("<input id=\"inputWidget" + formFieldId + "\" type=\"text\" name=\"field" + formFieldId + "\" size=\"20\" maxlength=\"255\"/>\n");
            // sbuf.append("<input type=\"button\" name=\"_add\" value=\"Change\"" + onClick + ">\n");
            item = sbuf.toString();
        }  else if (inputType.equals("fundal_height")) {
             sbuf.append("<select id=\"" + widgetName + "\" name=\"field" + formFieldId + "\"" + getEventHandler("onchange", updateRecord, encounterId) + ">\n");
            sbuf.append("   <option value=\"\"> -- </option>\n");   // routine ante chart - use " -- " for not recorded.
             if (value.equals("6")) {
                 sbuf.append("   <option value=\"6\" selected><12</option>\n");
             } else {
                 sbuf.append("   <option value=\"6\"><12</option>\n");
             }

            for (int i = 12; i < 55; i++) {
                if (value.equals(String.valueOf(i))) {
                    sbuf.append("   <option value=\"" + i + "\" selected>" + i + "</option>\n");
                } else {
                    sbuf.append("   <option value=\"" + i + "\">" +i + "</option>\n");
                }
            }
            sbuf.append("</select>\n");
            item = sbuf.toString();

           //    sbuf.append("<input id=\"" + widgetName + "\" type=\"text\" name=\"field" + formFieldId + "\" size=\"3\" maxlength=\"3\" value=\"" + value + "\"" + getEventHandler("onchange", updateRecord, encounterId) + "/>\n");
        //  item = sbuf.toString();
        }  else if (inputType.equals("hidden-empty")) {
        	sbuf.append("Editing forbidden for this value.\n");
            item = sbuf.toString();
        } else {
        	if (inputType.startsWith("SiteId")) {	//Encounter record metadata
	            List sites = null;
	            sites = DynaSiteObjects.getClinics();
	            sbuf.append("<select id=\"" + widgetName + "\" name=\"field" + inputType + "\"" + getEventHandler("onchange", updateRecord, encounterId) + ">\n");
	            sbuf.append("   <option value=\"\">No Information</option>\n");
	            for (int i = 0; i < sites.size(); i++) {
	                Site site = (Site) sites.get(i);
	                if (site.getInactive() == null) {
	                    String siteId = site.getId().toString();
	                    if (value.equals(site.getSiteAlphaId())) {
	                        sbuf.append("   <option value=\"" + siteId + "\" selected>" +
	                                site.getName() + "</option>\n");
	                    } else {
	                        sbuf.append("   <option value=\"" + siteId + "\">" +
	                                site.getName() + "</option>\n");
	                    }
	                }
	            }
	            sbuf.append("</select>\n");
	            item = sbuf.toString();
        	} else {
        		sbuf.append("*tbd* " + inputType + "\n");
                item = sbuf.toString();
        	}
        }
        return item;
    }

    private static void renderSelect(String visibleEnumIdTrigger1, StringBuffer sbuf, String widgetName, Long formFieldId, String updateRecord, String dep1, Long encounterId, PageItem pageItem, String visibleEnumIdTrigger2, String dep2, String value, Set enumList) {
        if ((!visibleEnumIdTrigger1.equals("0")) && (new Long(visibleEnumIdTrigger1).intValue() > 0) && (formFieldId.intValue() != 1677))
        {
            sbuf.append("<select id=\"" + widgetName + "\" name=\"field" + formFieldId + "\"" + WidgetUtils.getEventHandler("onchange", updateRecord, dep1, encounterId, pageItem) + ">\n");
        } else if ((!visibleEnumIdTrigger1.equals("0")) && new Long(visibleEnumIdTrigger2).intValue() > 0) {
            sbuf.append("<select id=\"" + widgetName + "\" name=\"field" + formFieldId + "\"" + getEventHandler("onchange", dep2, updateRecord, encounterId, pageItem) + ">\n");
        } else {
            sbuf.append("<select id=\"" + widgetName + "\" name=\"field" + formFieldId + "\"" + getEventHandler("onchange", updateRecord, encounterId) + ">\n");
        }
        sbuf.append("   <option value=\"\">No Information</option>\n");
        // Set enumList = formField.getEnumerations();
        for (Iterator iterator = enumList.iterator(); iterator.hasNext();) {
            FieldEnumeration fieldEnumeration = (FieldEnumeration) iterator.next();
            if (fieldEnumeration.isEnabled()) {
                if (value.equals(fieldEnumeration.getEnumeration())) {
                    sbuf.append("   <option value=\"" + fieldEnumeration.getId() + "\" selected>" +
                            fieldEnumeration.getEnumeration() + "</option>\n");
                } else {
                    sbuf.append("   <option value=\"" + fieldEnumeration.getId() + "\">" +
                            fieldEnumeration.getEnumeration() + "</option>\n");
                }
            }
        }
        sbuf.append("</select>\n");
    }

    /**
     * For pageItems that have dependencies
     *
     * @param eventName
     * @param dep
     * @param update
     * @param encounterId
     * @param pageItem
     * @return eventHandler string
     */
    private static String getEventHandler(String eventName, String dep, String update, Long encounterId, PageItem pageItem) {

        // Choices for update are "onclick, onmouseout, onchange"

        String eventHandler = null;
        if (encounterId.intValue() != 0) {
            eventHandler = " " + eventName + "=\"" + dep + " " + update + "\"";
        } else {
            eventHandler = " " + eventName + "=\"" + dep + "\"";
        }

        return eventHandler;
    }

    /**
     * For pageItems that do *not* have dependencies
     *
     * @param eventName
     * @param update
     * @param encounterId
     * @return eventHandler string
     */
    private static String getEventHandler(String eventName, String update, Long encounterId) {

        // Choices for update are "onclick, onmouseout, onchange"

        String eventHandler = "";
        if (encounterId.intValue() != 0) {
            eventHandler = " " + eventName + "=\"" + update + "\"";
        }

        return eventHandler;
    }

    public static String getGroups(Connection conn, String username) throws SQLException, ObjectNotFoundException, ServletException {
        String item = null;
        StringBuffer sbuf = new StringBuffer();
        sbuf.append("<select id='groups" + username + "' onChange=\"modifyUserGroup('" + username + "')\">\n");
        //  <option value="${group.id}" onmouseup="insertUserGroup('${user.id}', ${group.id})">${group.name}</option>
        sbuf.append("   <option value=\"\">Select Group</option>\n");

        List groups = UserDAO.getAllGroups(conn);
        for (int i = 0; i < groups.size(); i++) {
            UserGroup group = (UserGroup) groups.get(i);
//            sbuf.append("   <option value=\"" + group.getId() + "\" onChange=\"modifyUserGroup('" + username + "', " + group.getId() + ")\">" + group.getName() + "</option>\n");
            sbuf.append("   <option value=\"" + group.getId() + "\">" + group.getName() + "</option>\n");
        }
        sbuf.append("</select>\n");
        item = sbuf.toString();
        return item;
    }

    /**
     * uses enums from ABO Group, Rhesus, Cervical Smear, Sickle Cell Screen, and Malaria Test Results
     *
     * @return list
     */
    public static List getDynaSiteLabEnums() {
        List list = new ArrayList();
        TreeMap rawMap = (TreeMap) DynaSiteObjects.getFieldEnumerationsByField().get((long) 738);   // switched from 193
        Set rawSet = rawMap.entrySet();
        for (Iterator iterator = rawSet.iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Long enumId = (Long) entry.getKey();
            FieldEnumeration fieldEnumeration = (FieldEnumeration) entry.getValue();
            if (enumId.intValue() == 1664) {
                // don't add it to the list
            } else {
                list.add(fieldEnumeration);
            }
        }

        rawMap = (TreeMap) DynaSiteObjects.getFieldEnumerationsByField().get((long) 196);
        rawSet = rawMap.entrySet();
        for (Iterator iterator = rawSet.iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Long enumId = (Long) entry.getKey();
            FieldEnumeration fieldEnumeration = (FieldEnumeration) entry.getValue();
            if (enumId.intValue() == 1115) {
                // don't add it to the list
            } else {
                list.add(fieldEnumeration);
            }
        }

        rawMap = (TreeMap) DynaSiteObjects.getFieldEnumerationsByField().get((long) 207);
        rawSet = rawMap.entrySet();
        for (Iterator iterator = rawSet.iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Long enumId = (Long) entry.getKey();
            FieldEnumeration fieldEnumeration = (FieldEnumeration) entry.getValue();
            if (enumId.intValue() == 111) {
                // don't add it to the list
            } else {
                list.add(fieldEnumeration);
            }
        }

        rawMap = (TreeMap) DynaSiteObjects.getFieldEnumerationsByField().get((long) 1460);
        rawSet = rawMap.entrySet();
        for (Iterator iterator = rawSet.iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();
            FieldEnumeration fieldEnumeration = (FieldEnumeration) entry.getValue();
            list.add(fieldEnumeration);
        }

        rawMap = (TreeMap) DynaSiteObjects.getFieldEnumerationsByField().get((long) 1462);
        rawSet = rawMap.entrySet();
        for (Iterator iterator = rawSet.iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();
            FieldEnumeration fieldEnumeration = (FieldEnumeration) entry.getValue();
            list.add(fieldEnumeration);
        }
        return list;
    }

    /**
     * @return Drug enums list from field enums 1562 and 1584 - Malaria Drug and Deworming Drug
     */
    public static List getDynaSiteDrugEnums() {
        List list = new ArrayList();
        TreeMap rawMap = (TreeMap) DynaSiteObjects.getFieldEnumerationsByField().get((long) 1582);
        Set rawSet = rawMap.entrySet();
        for (Iterator iterator = rawSet.iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();
            FieldEnumeration fieldEnumeration = (FieldEnumeration) entry.getValue();
            list.add(fieldEnumeration);
        }

        rawMap = (TreeMap) DynaSiteObjects.getFieldEnumerationsByField().get((long) 1584);
        rawSet = rawMap.entrySet();
        for (Iterator iterator = rawSet.iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();
            FieldEnumeration fieldEnumeration = (FieldEnumeration) entry.getValue();
            list.add(fieldEnumeration);
        }
        return list;
    }
}
