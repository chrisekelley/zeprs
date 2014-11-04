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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.DynaActionForm;
import org.cidrz.project.zeprs.valueobject.BaseEncounter;
import org.cidrz.webapp.dynasite.dao.FormDisplayDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.exception.PopulationException;
import org.cidrz.webapp.dynasite.logic.PersistenceManagerFactory;
import org.cidrz.webapp.dynasite.rules.Outcome;
import org.cidrz.webapp.dynasite.rules.impl.EncounterOutcome;
import org.cidrz.webapp.dynasite.rules.impl.InfoOutcome;
import org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome;
import org.cidrz.webapp.dynasite.valueobject.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Custom object population utilities. Primary function is to allow fast and reliable
 * translation between business objects and Struts form beans.
 */


public class BeanPopulator {

    protected static Log log = LogFactory.getFactory().getInstance(BeanPopulator.class.getName());

    /**
     * Populates an object from the values in a DynaActionForm
     *
     * @param src  The DynaActionForm to copy from
     * @param dest The object you are trying to populate.
     * @throws org.cidrz.webapp.dynasite.exception.PopulationException
     *
     */

    public static void populate(DynaActionForm src, Object dest) throws PopulationException, PersistenceException, ObjectNotFoundException {
        BeanPopulator.populate(src.getMap(), dest);
    }

    /**
     * Populates an object from the values in a Map
     *
     * @param src  The Map to copy from
     * @param dest The object you are trying to populate.
     * @throws PopulationException
     */
    public static void populate(Map src, Object dest) throws PopulationException, ObjectNotFoundException, PersistenceException {

        try {
            if (dest instanceof Form) {
                BeanPopulator.populate(src, (Form) dest);
            } else if (dest instanceof FormField) {
                BeanPopulator.populate(src, (FormField) dest);
            } else if (dest instanceof PageItem) {
                BeanPopulator.populate(src, (PageItem) dest);
            } else if (dest instanceof FieldEnumeration) {
                BeanPopulator.populate(src, (FieldEnumeration) dest);
            } else if (dest instanceof RuleDefinition) {
                BeanPopulator.populate(src, (RuleDefinition) dest);
            } else if (dest instanceof Problem) {
                BeanPopulator.populate(src, (Problem) dest);
            } else if (dest instanceof Comment) {
                BeanPopulator.populate(src, (Comment) dest);
            } else if (dest instanceof Outcome) {
                BeanPopulator.populate(src, (Outcome) dest);
            } else if (dest instanceof LiveReportField) {
                BeanPopulator.populate(src, (LiveReportField) dest);
            } else if (dest instanceof LiveReport) {
                BeanPopulator.populate(src, (LiveReport) dest);
            } else if (dest instanceof BaseEncounter) {
                BeanPopulator.populate(src, (BaseEncounter) dest);
            } else {
                BeanUtils.copyProperties(dest, src);
            }
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
            log.error(e);
            throw new PopulationException();
        }
    }

    /* private static void populate(Map src, BaseEncounter dest) throws PopulationException, PersistenceException, ObjectNotFoundException, IllegalAccessException, InvocationTargetException {
         BeanUtils.copyProperties(dest, src);
     }*/


    private static void populate(Map src, LiveReportField dest) throws PersistenceException, ObjectNotFoundException {
        if (dest.getId() == null || Identifiable.NEW.equals(dest.getId())) {
            // try {
            Long fieldId = (Long) src.get("formField");
            FormField formField = (FormField) PersistenceManagerFactory.getInstance(FormField.class).getOne(fieldId);
            dest.setForm_field(formField);
            Long pageItemId = (Long) src.get("pageItem");
            PageItem pageItem = (PageItem) PersistenceManagerFactory.getInstance(PageItem.class).getOne(pageItemId);
            dest.setPageItem(pageItem);
            Long formId = (Long) src.get("formId");
            Form form = (Form) PersistenceManagerFactory.getInstance(Form.class).getOne(formId);
            dest.setForm(form);
            String reportProperty = (String) src.get("reportProperty");
            dest.setReportProperty(reportProperty);
            Long liveReportId = (Long) src.get("liveReport");
            LiveReport liveReport = (LiveReport) PersistenceManagerFactory.getInstance(LiveReport.class).getOne(liveReportId);
            dest.setLiveReport(liveReport);
            Boolean shared = (Boolean) src.get("shared");
            dest.setShared(shared.booleanValue());
        }
    }

    public static Map populate(Map src, LiveReport liveReport) throws PopulationException, IllegalAccessException, InvocationTargetException, PersistenceException, ObjectNotFoundException {

        Map properties = new HashMap();
        String key;
        Iterator i = src.keySet().iterator();
        while (i.hasNext()) {
            key = (String) i.next();
            if (key.startsWith("field")) {
                Long fieldId = Long.valueOf(key.substring(5));
                // Check the reportList. If this field is in one of the reports, add to its hash.
                Set reportFields = liveReport.getFields();
                for (Iterator reportField = reportFields.iterator(); reportField.hasNext();) {
                    LiveReportField liveReportField = (LiveReportField) reportField.next();
                    Long reportFieldId = liveReportField.getForm_field().getId();
                    String reportProperty = liveReportField.getReportProperty();

                    if (fieldId.equals(reportFieldId)) {
                        if (!src.get(key).equals("")) {
                            if (liveReportField.getForm_field().getType().equals("Enum")) {
                                String reportEnumProperty = reportProperty.replaceAll("Enum", "");
                                Iterator fenums = liveReportField.getPageItem().getForm_field().getEnumerations().iterator();
                                Map enumsById = new HashMap();
                                FieldEnumeration fenum;
                                while (fenums.hasNext()) {
                                    fenum = (FieldEnumeration) fenums.next();
                                    enumsById.put(fenum.getId().toString(), fenum);
                                }
                                FieldEnumeration fieldEnumeration = (FieldEnumeration) enumsById.get(src.get(key));
                                String enumValue = fieldEnumeration.getEnumeration();
                                Integer enumOrder = fieldEnumeration.getDisplayOrder();
                                properties.put(reportEnumProperty + "Value", enumValue);
                                properties.put(reportEnumProperty + "Order", enumOrder);
                            }
                            properties.put(reportProperty, src.get(key));
                        }
                    }
                }
            }
        }

        return properties;
    }

    private static void populate(Map src, Comment dest) {
        Long patientId = (Long) src.get("patientId");
        // Patient patient = (Patient) PersistenceManagerFactory.getInstance(Patient.class).getOne(patientId);
        dest.setPatientId(patientId);

        Long pregnancyId = (Long) src.get("pregnancyId");
        //Pregnancy pregnancy = (Pregnancy) PersistenceManagerFactory.getInstance(Pregnancy.class).getOne(pregnancyId);
        dest.setPregnancyId(pregnancyId);

        Long problemId = (Long) src.get("problemId");
        if (problemId != null) {
            // Problem problem = (Problem) PersistenceManagerFactory.getInstance(Problem.class).getOne(problemId);
            dest.setProblemId(problemId);
        }
        Long outcomeId = (Long) src.get("outcomeId");
        if (outcomeId != null) {
            // Outcome outcome = (Outcome) PersistenceManagerFactory.getInstance(Outcome.class).getOne(outcomeId);
            dest.setOutcomeId(outcomeId);
        }
        Long encounterId = (Long) src.get("encounterId");
        if (encounterId != null) {
            // EncounterRecord encounter = (EncounterRecord) PersistenceManagerFactory.getInstance(EncounterRecord.class).getOne(encounterId);
            dest.setEncounterId(encounterId);
        }
        dest.setActionPlan(src.get("actionPlan").toString());
        dest.setCommentText(src.get("commentText").toString());
    }


    private static void populate(Map src, Problem dest) {
        try {
            Iterator i = src.keySet().iterator();
            String key;
            //String user = dest.getAuditInfo().getCreatedBy().getId();
            //process the onsetDate field
            String onsetDate = null;
            Long patientId = (Long) src.get("patientId");
            // Patient patient = (Patient) PersistenceManagerFactory.getInstance(Patient.class).getOne(patientId);
            dest.setPatientId(patientId);
            Long pregnancyId = (Long) src.get("pregnancyId");
            // Pregnancy pregnancy = (Pregnancy) PersistenceManagerFactory.getInstance(Pregnancy.class).getOne(pregnancyId);
            dest.setPregnancyId(pregnancyId);
            Comment comment = new Comment();
            comment.setPatientId(patientId);
            comment.setPregnancyId(pregnancyId);
            StringBuffer actionplan = new StringBuffer();
            String status = "active";
            if (src.get("active").toString().equals("false")) {
                status = "inactive";
            }
            if (Boolean.valueOf(dest.isActive()) != Boolean.valueOf(src.get("active").toString())) {
                actionplan.append("Change of status to <strong>");
                actionplan.append(status);
                actionplan.append("</strong><br/> " + src.get("actionPlan").toString());
            } else {
                actionplan.append(src.get("actionPlan").toString());
            }
            comment.setActionPlan(actionplan.toString());
            comment.setCommentText(src.get("commentText").toString());
            /*org.cidrz.webapp.dynasite.valueobject.AuditInfo info = ((org.cidrz.webapp.dynasite.valueobject.Auditable) comment).getAuditInfo();
            info.setLastModified(new Timestamp(System.currentTimeMillis()));
            User userInfo = (User) PersistenceManagerFactory.getInstance(User.class).getUser(user);
            info.setLastModifiedBy(userInfo);
            if (info.getCreated() == null) {
                info.setCreated(info.getLastModified());
                info.setCreatedBy(info.getLastModifiedBy());
            }
            comment.setAuditInfo(info);*/
            try {
                comment.setAuditInfo(dest.getAuditInfo());
            } catch (Exception e) {
                log.error(e);
            }
            // comment.setProblem(dest);
            if (src.get("id").toString().equals("0")) {
                comment.setProblem(dest);
            } else {
                Long problemId = (Long) src.get("id");
                // need to get the whole problem - not lazily
                // Problem problem = (Problem) PersistenceManagerFactory.getInstance(Problem.class).getOne(problemId);
                comment.setProblemId(problemId);
            }
            dest.setComment(comment);
            // dest.getComments().add(comment);
            //Long formId = (Long) src.get("formId");
            //    Form form = (Form) PersistenceManagerFactory.getInstance(Form.class).getOne(formId);
            while (i.hasNext()) {
                key = (String) i.next();
                if (key.equals("onsetDate")) {
                    if (!key.equals("")) {
                        String dateValue = src.get("onsetDate").toString();
                        BeanUtils.copyProperty(dest, "onsetDate", dateValue);
                    }
                } else {
                    BeanUtils.copyProperty(dest, key, src.get(key));
                }
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private static void populate(Map src, Outcome dest) throws PopulationException {
        try {
            Iterator i = src.keySet().iterator();
            String key;
            User user = dest.getAuditInfo().getCreatedBy();
            //process the onsetDate field
            String onsetDate = null;
            Long patientId = (Long) src.get("patientId");
            // Patient patient = (Patient) PersistenceManagerFactory.getInstance(Patient.class).getOne(patientId);
            dest.setPatientId(patientId);
            Comment comment = new Comment();
            comment.setPatientId(patientId);
            StringBuffer actionplan = new StringBuffer();
            actionplan.append(src.get("actionPlan").toString());
            String status = "active";
            if (src.get("active").toString().equals("false")) {
                status = "inactive";
            }
            if (Boolean.valueOf(dest.isActive()) != Boolean.valueOf(src.get("active").toString())) {
                actionplan.setLength(0);
                actionplan.append("Change of status to <strong>");
                actionplan.append(status);
                actionplan.append("</strong><br/> " + src.get("actionPlan").toString());
            }
            comment.setActionPlan(actionplan.toString());
            comment.setCommentText(src.get("commentText").toString());
            AuditInfo info = ((Auditable) comment).getAuditInfo();
            info.setLastModified(new Timestamp(System.currentTimeMillis()));
            info.setLastModifiedBy(user);
            if (info.getCreated() == null) {
                info.setCreated(info.getLastModified());
                info.setCreatedBy(info.getLastModifiedBy());
            }
            comment.setAuditInfo(info);
            // comment.setProblem(dest);
            if (src.get("id").toString().equals("0")) {
                comment.setOutcome(dest);
            } else {
                Long outcomeId = (Long) src.get("id");
                // need to get the whole problem - not lazily
                // Outcome outcome = (Outcome) PersistenceManagerFactory.getInstance(Outcome.class).getOne(outcomeId);
                comment.setOutcomeId(outcomeId);
                List comments = new ArrayList();
                comments.add(comments);
                dest.setComments(comments);
               //  dest.getComments().add(comment);
                //Long formId = (Long) src.get("formId");
                //    Form form = (Form) PersistenceManagerFactory.getInstance(Form.class).getOne(formId);
                while (i.hasNext()) {
                    key = (String) i.next();
                    BeanUtils.copyProperty(dest, key, src.get(key));
                }
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private static void populate(Map src, FormField dest) throws PopulationException {
        if (dest.getId() == null || Identifiable.NEW.equals(dest.getId())) {
            Long formId = (Long) src.get("formId");
            // CEKelley 082302994 - don't need
            // Form form = (Form) PersistenceManagerFactory.getInstance(Form.class).getOne(formId);
            //dest.getForms().add(form);
        }

        try {
            BeanUtils.copyProperties(dest, src);
            String rawEnumOrder = (String) src.get("_enumerationOrder");
            if (rawEnumOrder != null && !rawEnumOrder.equals("")) {
                String[] enumOrder = rawEnumOrder.split(",");
                Iterator enums = dest.getEnumerations().iterator();
                Map enumsById = new HashMap();
                FieldEnumeration fenum;
                while (enums.hasNext()) {
                    fenum = (FieldEnumeration) enums.next();
                    enumsById.put(fenum.getId().toString(), fenum);
                }

                for (int i = 0; i < enumOrder.length; i++) {
                    String id = enumOrder[i];
                    fenum = (FieldEnumeration) enumsById.get(id);
                    fenum.setDisplayOrder(new Integer(i));
                }
            }

            String[] rawDependencies1 = (String[]) src.get("_dependencies1");
            if (rawDependencies1 != null && !rawDependencies1.equals("")) {
                //String[] dependencies = rawDependencies1.split(",");
                // HashSet depSet = new HashSet();
                StringBuffer visibleDependencies = new StringBuffer();
                // DisplayHint dep = new DisplayHint();
                for (int j = 0; j < rawDependencies1.length; j++) {
                    String dependency = rawDependencies1[j];
                    visibleDependencies.append(dependency);
                    //HashSet depSet = new HashSet();
                    //FieldVisibilityDependencies dep = new FieldVisibilityDependencies();
                    //Long id = (Long) src.get("id");
                    //dep.setDisplayId(id);
                    // dep.setFieldId(Long.decode(dependency));
                    //depSet.add(dep);
                    if (j < rawDependencies1.length - 1) {
                        visibleDependencies.append(",");
                    }
                }
                // dep.setVisibleDependencies1(visibleDependencies.toString());
                // dest.setVisibleDependencies1(visibleDependencies.toString());
            }

        } catch (IllegalAccessException e) {
            throw new PopulationException();
        } catch (InvocationTargetException e) {
            throw new PopulationException();
        }

    }


    private static void populate(Map src, PageItem dest) throws PopulationException {
        if (dest.getId() == null || Identifiable.NEW.equals(dest.getId())) {
            //Long formId = (Long) src.get("formId");
        	Connection conn = null;
            try {
                Long formId = (Long) src.get("formId");
                // Form form = (Form) DynaSiteObjects.getForms().get(formId);
                // Get a fresh form
                conn = DatabaseUtils.getAdminConnection();
                Form form = FormDisplayDAO.getFormGraph(conn, formId);
                dest.setForm(form);
                Integer display_order = dest.getForm().getPageItems().size();
                dest.setDisplayOrder(display_order);
            } catch (Exception e) {
                throw new PopulationException();
            } finally {
            	try {
					if (conn != null && !conn.isClosed()) {
					    conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
        try {

            // this is a hack - I need to grab the enums before Beanutils makes them go away...
            Set FieldEnums = dest.getForm_field().getEnumerations();
            BeanUtils.copyProperties(dest, src);

            if (!src.get("newSharedField").equals("")) {
                Long newSharedField = new Long((String) src.get("newSharedField"));
                if (newSharedField.intValue() != dest.getForm_field().getId().intValue()) {
                    try {
                        FormField formField = (FormField) PersistenceManagerFactory.getInstance(FormField.class).getOne(newSharedField);
                        dest.setForm_field(formField);
                    } catch (PersistenceException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } catch (ObjectNotFoundException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                } else {
                    dest.getForm_field().setEnumerations(FieldEnums);
                    String rawEnumOrder = (String) src.get("_enumerationOrder");
                    if (rawEnumOrder != null && !rawEnumOrder.equals("")) {
                        String[] enumOrder = rawEnumOrder.split(",");
                        Iterator enums = dest.getForm_field().getEnumerations().iterator();
                        Map enumsById = new HashMap();
                        FieldEnumeration fenum;
                        while (enums.hasNext()) {
                            fenum = (FieldEnumeration) enums.next();
                            enumsById.put(fenum.getId().toString(), fenum);
                        }

                        for (int i = 0; i < enumOrder.length; i++) {
                            String id = enumOrder[i];
                            fenum = (FieldEnumeration) enumsById.get(id);
                            fenum.setDisplayOrder(new Integer(i));
                        }
                    }
                }
            }

            String[] rawDependencies1 = (String[]) src.get("_dependencies1");
            if (rawDependencies1 != null && !rawDependencies1.equals("")) {
                StringBuffer visibleDependencies1 = getVisibleDeps(rawDependencies1);
                dest.setVisibleDependencies1(visibleDependencies1.toString());
            }

            String[] rawDependencies2 = (String[]) src.get("_dependencies2");
            if (rawDependencies2 != null && !rawDependencies2.equals("")) {
                StringBuffer visibleDependencies2 = getVisibleDeps(rawDependencies2);
                dest.setVisibleDependencies2(visibleDependencies2.toString());
            }

        } catch (IllegalAccessException e) {
            throw new PopulationException();
        } catch (InvocationTargetException e) {
            throw new PopulationException();
        }

    }

    private static StringBuffer getVisibleDeps(String[] rawDependencies1) {
        StringBuffer visibleDependencies1 = new StringBuffer();
        for (int j = 0; j < rawDependencies1.length; j++) {
            String dependency1 = rawDependencies1[j];
            visibleDependencies1.append(dependency1);
            if (j < rawDependencies1.length - 1) {
                visibleDependencies1.append(",");
            }
        }
        return visibleDependencies1;
    }


    private static void populate(Map src, FieldEnumeration dest) throws PopulationException {
        if (dest.getId() == null || Identifiable.NEW.equals(dest.getId())) {
            try {
                Long fieldId = (Long) src.get("fieldId");
                FormField field = (FormField) PersistenceManagerFactory.getInstance(FormField.class).getOne(fieldId);
                dest.setField(field);
            } catch (PersistenceException e) {
                throw new PopulationException();
            } catch (ObjectNotFoundException e) {
                throw new PopulationException();
            }
        }
        try {
            BeanUtils.copyProperties(dest, src);
        } catch (IllegalAccessException e) {
            throw new PopulationException();
        } catch (InvocationTargetException e) {
            throw new PopulationException();
        }
    }

    private static void populate(Map src, Form dest) throws PopulationException {
        try {
            BeanUtils.copyProperties(dest, src);
            String rawFieldOrder = (String) src.get("_fieldOrder");
            if (rawFieldOrder != null && !rawFieldOrder.equals("")) {
                String[] fieldOrder = rawFieldOrder.split(",");
                Iterator pageItems = dest.getPageItems().iterator();
                PageItem pageItem;
                Map fieldsById = new HashMap();
                while (pageItems.hasNext()) {
                    pageItem = (PageItem) pageItems.next();
                    fieldsById.put(pageItem.getId().toString(), pageItem);
                }

                for (int i = 0; i < fieldOrder.length; i++) {
                    String id = fieldOrder[i];
                    pageItem = (PageItem) fieldsById.get(id);
                    if (pageItem == null) {
                        // Looks like we have a new shared field. We need to create a pageItem out of it.
                        // new shared fields have "new" prepended to them - need to extract this...
                        PageItem page_item = new PageItem();
                        String fieldStr = id.substring(3);
                        Long fieldId = new Long(fieldStr);
                        FormField form_field = (FormField) PersistenceManagerFactory.getInstance(FormField.class).getOne(fieldId);
                        Set page_items = form_field.getPageItems();
                        PageItem master_pi = null;
                        for (Iterator iterator = page_items.iterator(); iterator.hasNext();) {
                            master_pi = (PageItem) iterator.next();
                        }
                        page_item.setForm(dest);
                        page_item.setForm_field(form_field);
                        page_item.setInputType(master_pi.getInputType());
                        page_item.setVisible(master_pi.isVisible());
                        page_item.setDisplayOrder(new Integer(i));
                        page_item.setCols(master_pi.getCols());
                        page_item.setColspan(master_pi.getColspan());
                        page_item.setMaxlength(master_pi.getMaxlength());
                        page_item.setSize(master_pi.getSize());
                        page_item.setRows(master_pi.getRows());
                        page_item.setCols(master_pi.getCols());
                        page_item.setColumnNumber(master_pi.getColumnNumber());
                        page_item.setColspan(master_pi.getColspan());
                        page_item.setCloseRow(master_pi.isCloseRow());
                        dest.getPageItems().add(page_item);
                    } else {
                        pageItem.setDisplayOrder(new Integer(i));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new PopulationException();
        } catch (InvocationTargetException e) {
            throw new PopulationException();
        } catch (ObjectNotFoundException e) {
            throw new PopulationException();
        } catch (PersistenceException e) {
            throw new PopulationException();
        }
    }

    private static void populate(Map src, RuleDefinition dest) throws PopulationException {
        if (dest.getId() == null || Identifiable.NEW.equals(dest.getId())) {
            Long providerId = (Long) src.get("providerId");
            String providerClass = (String) src.get("providerClass");
            if (Form.class.getName().equals(providerClass)) {
                // Form form = (Form) PersistenceManagerFactory.getInstance(Form.class).getOne(providerId);
                dest.setFormId(providerId);
            } else if (FormField.class.getName().equals(providerClass)) {
                // FormField field = (FormField) PersistenceManagerFactory.getInstance(FormField.class).getOne(providerId);
                dest.setFieldId(providerId);
            }
        }
        try {
            dest.getRuleParams().clear();
            BeanUtils.copyProperties(dest, src);
            dest.getRuleParams().put("operator", src.get("operator"));
            dest.getRuleParams().put("operand", src.get("operand"));
            /*if (ScriptRule.class.getName().equals(dest.getRuleClass())) {
                dest.getRuleParams().put("expr", src.get("expr"));
            } else if (SimpleComparison.class.getName().equals(dest.getRuleClass())) {
                dest.getRuleParams().put("operator", src.get("operator"));
                dest.getRuleParams().put("operand", src.get("operand"));
            }*/

            /*if (EncounterOutcome.class.getName().equals(dest.getOutcomeClass())) {
                dest.getOutcomeParams().put("formId", src.get("outcomeArg"));
            } else if (ReferralOutcome.class.getName().equals(dest.getOutcomeClass())) {
                dest.getOutcomeParams().put("reason", src.get("outcomeArg"));
            } else if (InfoOutcome.class.getName().equals(dest.getOutcomeClass())) {
                dest.getOutcomeParams().put("message", src.get("outcomeArg"));
            }*/

            if (EncounterOutcome.class.getName().equals(dest.getOutcomeClass())) {
                dest.getRuleParams().put("formId", src.get("outcomeArg"));                
                Long formId = new Long((String) src.get("outcomeArg"));
                dest.setFormId(formId);
            } else if (ReferralOutcome.class.getName().equals(dest.getOutcomeClass())) {
                dest.getRuleParams().put("reason", src.get("outcomeArg"));
                dest.setMessage((String) src.get("outcomeArg"));
            } else if (InfoOutcome.class.getName().equals(dest.getOutcomeClass())) {
                dest.getRuleParams().put("message", src.get("outcomeArg"));
                dest.setMessage((String) src.get("outcomeArg"));
            }
        } catch (IllegalAccessException e) {
            throw new PopulationException();
        } catch (InvocationTargetException e) {
            throw new PopulationException();
        }
    }


    /**
     * Populates a DynaActionForm from the values in an object
     *
     * @param src  The object to copy from
     * @param dest The DynaActionForm you are trying to populate.
     * @throws PopulationException
     */
    public static void populate(Object src, DynaActionForm dest) throws PopulationException {
        try {
            if (src instanceof RuleDefinition) {
                populate((RuleDefinition) src, dest);
            } else if (src instanceof FormField) {
                populate((FormField) src, dest);
            } else if (src instanceof PageItem) {
                populate((PageItem) src, dest);
            } else if (src instanceof Form) {
                populate((Form) src, dest);
            } else {
                BeanUtils.copyProperties(dest, src);
            }
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
            //todo log
            throw new PopulationException();
        }
    }

    private static void populate(RuleDefinition src, DynaActionForm dest) throws PopulationException {
        try {
            BeanUtils.copyProperties(dest, src);
            BeanUtils.copyProperties(dest, src.getRuleParams());
            if (src.getRuleParams().get("formId") != null) {
                dest.set("outcomeArg", src.getRuleParams().get("formId"));
            } else if (src.getRuleParams().get("message") != null) {
                dest.set("outcomeArg", src.getRuleParams().get("message"));
            } else if (src.getRuleParams().get("reason") != null) {
                dest.set("outcomeArg", src.getRuleParams().get("reason"));
            }

        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
            //todo log
            throw new PopulationException();
        }
    }

    private static void populate(Form src, DynaActionForm dest) throws PopulationException {
        try {
            BeanUtils.copyProperties(dest, src);
            //Iterator fields = src.getPageItems().iterator();
            Iterator pageItems = src.getPageItems().iterator();
            StringBuffer fieldOrder = new StringBuffer();
            boolean first = true;
            FormField field;
            while (pageItems.hasNext()) {
                //field = (FormField) fields.next();
                PageItem pageItem = (PageItem) pageItems.next();
                //field = pageItem.getForm_field();
                if (!first) {
                    fieldOrder.append(",");
                } else {
                    first = false;
                }
                fieldOrder.append(pageItem.getId().toString());
            }
            dest.set("_fieldOrder", fieldOrder.toString());
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
            //todo log
            throw new PopulationException();
        }
    }

    private static void populate(FormField src, DynaActionForm dest) throws PopulationException {
        try {
            BeanUtils.copyProperties(dest, src);
            Iterator enumerations = src.getEnumerations().iterator();
            StringBuffer enumerationOrder = new StringBuffer();
            boolean first = true;
            FieldEnumeration enumeration;
            while (enumerations.hasNext()) {
                enumeration = (FieldEnumeration) enumerations.next();
                if (!first) {
                    enumerationOrder.append(",");
                } else {
                    first = false;
                }

                enumerationOrder.append(enumeration.getId().toString());
            }
            dest.set("_enumerationOrder", enumerationOrder.toString());
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
            //todo log
            throw new PopulationException();
        }
    }

    private static void populate(PageItem src, DynaActionForm dest) throws PopulationException {
        try {
            BeanUtils.copyProperties(dest, src);
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
            //todo log
            throw new PopulationException();
        }
    }

    /**
     * @param dateValue
     * @return
     * @deprecated
     */
    public static String fixDate(String dateValue) {
        String[] fixedValue = dateValue.split("/");
        String dayValue = fixedValue[0];
        String monthValue = fixedValue[1];
        String yearValue = fixedValue[2];
        String fixedValue2 = yearValue + "-" + monthValue + "-" + dayValue;
        return fixedValue2;
    }


}
