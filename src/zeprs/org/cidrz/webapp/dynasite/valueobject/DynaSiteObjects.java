/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.valueobject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.valueobject.partograph.PartographMapping;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.utils.StringManipulation;
import org.cidrz.webapp.dynasite.utils.XmlUtils;
import org.cidrz.webapp.dynasite.utils.sort.DisplayOrderComparator;
import org.cidrz.webapp.dynasite.utils.sort.FlowOrderComparator;
import org.dom4j.DocumentException;
import org.rti.zcore.ApplicationDefinition;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Persistent objects cached for efficient page rendering
 *
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jul 4, 2005
 *         Time: 8:43:50 AM
 */
public class DynaSiteObjects {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(DynaSiteObjects.class);

    // private static final DynaSiteObjects dynaSiteObjects = new DynaSiteObjects();

    private static List drugs = null;
    private static HashMap drugMap = null;
    private static TreeMap clinicMap = null;
    private static TreeMap clinicKeyAlphaMap = null;
    private static List siteList = null;
    private static List<Site> clinics = null;
    private static List allSites = null;
    private static List districts = null;
    private static TreeMap districtsMap = null;
    private static List immunizations = null;
    private static List flows = null;
    private static List<FormType> formTypes = null;
    private static HashMap flowMap = null;
    private static HashMap fieldEnumerations = null;
    private static HashMap fieldEnumerationsByField = null;
    private static HashMap partoFields = null;
    private static HashMap partoTables = null;
    private static HashMap partoQueries;
    private static HashMap pageItems = null;
    private static HashMap forms = null;
    private static HashMap formSections = null;
    private static HashMap formDependencies = null;
    private static HashMap collapsingSections = null;
    private static HashMap collapsingDependencies = null;
    private static HashMap fieldToPageItem = null;
    private static HashMap fieldToForms = null;
    private static HashMap formNames = null;
    private static HashMap<String,Long> formNameMap = null;	//classname:formId
    private static HashMap<Long,String> formIdClassNameMap = null;
    private static HashMap rules = null;
    private static HashMap rulesToForms = null;
    private static HashMap ruleMap = null;
    private static HashMap allPregnanciesRules = null;
    private static List activefields = null;    // used for queryBuilder
    private static HashMap tasksForFlow = null;
    private static HashMap formIdToClassNames;
    private static String path = Constants.DYNASITE_XML_PATH;
    private static HashMap statusMap = new HashMap();   // used to store status values used during runtime (RSS, Quartz).
														// RSS import and export use the same value, RSS-Gen-date, so that their processes won't collide or leave the patient record
    private static Boolean dev;
    private static HashMap<String,String> masterArchiveIndex;
    private static ApplicationDefinition applicationDefinition;
    private static ArrayList<String> localeDisplayName;
    private static ArrayList<Locale> localeList;
    private static HashMap<String,String> localeDisplayMap;
    private static HashMap<String,String> localeMessageMap;	//primarily used by DWR AJAX calls to access locale properties.
    private static ArrayList<MenuItem> menuItemList;
    private static HashMap<String,Boolean> propertyFileReloadMap = new HashMap<String,Boolean>();	//stores name of property file and if it needs to be reloaded.
    private static Boolean problemListEnabled;	// display problem list?
    private static String webDavArchiveFolderDate;	// has the folder hierarchy for the current date been created? what is it?
    private static Server proxyServer;
    private static Boolean needProxyAuthentication;

    /*private DynaSiteObjects() {
        log.debug("singleton object created");
    }

    public static DynaSiteObjects getInstance() {
        return dynaSiteObjects;
    }*/

    public static List getDrugs() {
        if (drugs == null) {
            drugs = new ArrayList();
            try {
                // drugs = DrugsDAO.getAll();
                drugs = (List) XmlUtils.getOne(path + "Drugs.xml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return drugs;
    }

    public static HashMap getDrugMap() {
        if (drugMap == null) {
            drugMap = new HashMap();
            List drugs = getDrugs();
            for (int i = 0; i < drugs.size(); i++) {
                Drugs drug = (Drugs) drugs.get(i);
                drugMap.put(drug.getId(), drug);
            }
        }
        return drugMap;
    }

    /**
     * @return Alpha ordered list of Lusaka (5040) clinicMap in format: site.getId(), site
     * Includes ZEPRS master.
     */
    public static TreeMap getClinicMap() {
        if (clinicMap == null) {
            clinicMap = new TreeMap();
            try {
                // List siteList = SiteDAO.getClinicMap();
                List siteList = (List) XmlUtils.getOne(path + "Sites.xml");
                for (int i = 0; i < siteList.size(); i++) {
                    Site site = (Site) siteList.get(i);
                    clinicMap.put(site.getId(), site);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return clinicMap;
    }

    /**
     * Query sitelist by siteAlphaId
     * @return Alpha ordered list of Lusaka (5040) clinicMap in format: site.getSiteAlphaId(), site
     */
    public static TreeMap getClinicKeyAlphaMap() {
    	if (clinicKeyAlphaMap == null) {
    		clinicKeyAlphaMap = new TreeMap();
    		try {
    			// List siteList = SiteDAO.getClinicMap();
    			List siteList = (List) XmlUtils.getOne(path + "Sites.xml");
    			for (int i = 0; i < siteList.size(); i++) {
    				Site site = (Site) siteList.get(i);
    				clinicKeyAlphaMap.put(site.getSiteAlphaId(), site);
    			}
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	return clinicKeyAlphaMap;
    }

    /**
     *  List of all sites - includes ZEPRS master. If you want a list of clinics, use getClinics() instead.
     * @return List of all sites
     */
    public static List getSiteList() {
        if (siteList == null) {
            siteList = new ArrayList();
            try {
                siteList = (List) XmlUtils.getOne(path + "Sites.xml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return siteList;
    }

    /**
     * @return All clinics, Ordered alpha
     */
    public static List<Site> getClinics() {
        if (clinics == null) {
            clinics = new ArrayList();
            try {
                // clinics = SiteDAO.getClinics();
                clinics = (List) XmlUtils.getOne(path + "Clinics.xml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return clinics;
    }

    public static List getDistricts() {
        if (districts == null) {
            districts = new ArrayList();
            try {
                // districts = DistrictDAO.getAll("districtId");
                districts = (List) XmlUtils.getOne(path + "Districts.xml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return districts;
    }

    /**
     * @return TreeMap of districts
     */
    public static TreeMap getDistrictsMap() {
        if (districtsMap == null) {
            districtsMap = new TreeMap();
            // List siteList = SiteDAO.getClinicMap();
            // List siteList = (List) XmlUtils.getOne(path + "Sites.xml");
            for (int i = 0; i < districts.size(); i++) {
                District district = (District) districts.get(i);
                districtsMap.put(district.getId(), district);
            }
        }
        return districtsMap;
    }

    public static List getImmunizations() {
        if (immunizations == null) {
            immunizations = new ArrayList();
            try {
                // immunizations = ImmunizationDAO.getAll();
                immunizations = (List) XmlUtils.getOne(path + "Immunizations.xml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return immunizations;
    }

    public static List getFlows() {
        if (flows == null) {
            flows = new ArrayList();
            try {
                //flows = FlowDAO.getAll();
                flows = (List) XmlUtils.getOne(path + "Flows.xml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flows;
    }
    
    /**
     * Provides a FormType list.
     * @return
     */
    public static List<FormType> getFormTypes() {
    	if (formTypes == null) {
    		formTypes = new ArrayList<FormType>();
    		try {
    			//flows = FlowDAO.getAll();
    			formTypes = (List<FormType>) XmlUtils.getOne(path + "FormTypes.xml", null, null);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	return formTypes;
    }

    public static HashMap getFlowMap() {
        if (flowMap == null) {
            flowMap = new HashMap();
            List flows = getFlows();
            for (int i = 0; i < flows.size(); i++) {
                Flow flow = (Flow) flows.get(i);
                flowMap.put(flow.getId(), flow);
            }
        }
        return flowMap;
    }

    public static void setFlowMap(HashMap flowMap) {
        DynaSiteObjects.flowMap = flowMap;
    }

    // query by enum id, returns FieldEnumeration
    public static HashMap getFieldEnumerations() {
        if (fieldEnumerations == null) {
            fieldEnumerations = new HashMap();
            List fieldEnumListDAO = null;
            List fieldEnumList = null;
            //
            try {
                // fieldEnumListDAO = FieldEnumerationDAO.getAll();
                // log.debug("fieldEnumListDAO size:" + fieldEnumListDAO.size());
                fieldEnumList = (List) XmlUtils.getOne(path + "Fields.xml");
                //log.debug("fieldEnumList size:" + fieldEnumList.size());
                for (int i = 0; i < fieldEnumList.size(); i++) {
                    FieldEnumeration fieldEnum = (FieldEnumeration) fieldEnumList.get(i);
                    fieldEnumerations.put(fieldEnum.getId(), fieldEnum);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fieldEnumerations;
    }

    public static void setFieldEnumerations(HashMap fieldEnumerations) {
        DynaSiteObjects.fieldEnumerations = fieldEnumerations;
    }

    /**
     * @return sorted map of fields, indexed by field id
     */
    public static HashMap getFieldEnumerationsByField() {
        if (fieldEnumerationsByField == null) {
            fieldEnumerationsByField = new HashMap();
            Set fieldSet = getFieldEnumerations().keySet();
            for (Iterator iterator = fieldSet.iterator(); iterator.hasNext();) {
                /*Map.Entry entry = null;
                try {
                    entry = (Map.Entry) iterator.next();
                } catch (Exception e) {
                    log.error(e);
                }*/
                Long fieldEnumId = null;
                try {
                    fieldEnumId = (Long) iterator.next();
                } catch (Exception e) {
                    log.error(e);
                }
                FieldEnumeration fieldEnum = (FieldEnumeration) fieldEnumerations.get(fieldEnumId);
                try {
                    TreeMap fieldMap = (TreeMap) fieldEnumerationsByField.get(fieldEnum.getFieldId());
                    fieldMap.put(fieldEnum.getId(), fieldEnum);
                } catch (Exception e) {
                    // not found - create a new field map
                    TreeMap fieldMap = new TreeMap();
                    fieldMap.put(fieldEnum.getId(), fieldEnum);
                    fieldEnumerationsByField.put(fieldEnum.getFieldId(), fieldMap);
                }
            }
            // log.info("FieldEnumerationsByFi//eld size: " + fieldEnumerationsByField.size());
        }
        return fieldEnumerationsByField;
    }

    public static void setFieldEnumerationsByField(HashMap fieldEnumerationsByField) {
        DynaSiteObjects.fieldEnumerationsByField = fieldEnumerationsByField;
    }

    public static HashMap getPartoFields() {
        if (partoFields == null) {
            partoFields = new HashMap();
            try {
                partoFields = PartographMapping.getPartoFields();
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return partoFields;
    }

    public static HashMap getPartoTables() {
        if (partoTables == null) {
            partoTables = new HashMap();
            try {
                partoTables = PartographMapping.getPartoTables();
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return partoTables;
    }

    public static HashMap getPartoQueries() {
        if (partoQueries == null) {
            partoQueries = new HashMap();
            List classNames = PartographMapping.partoClassNameList();
            for (int i = 0; i < classNames.size(); i++) {
                String className = (String) classNames.get(i);
                String table = (String) DynaSiteObjects.getPartoTables().get("org.cidrz.project.zeprs.valueobject.partograph." + className);
                HashMap map = (HashMap) DynaSiteObjects.getPartoFields().get(className);
                Set mapSet = map.entrySet();
                StringBuffer sql = new StringBuffer();
                sql.append("INSERT INTO " + table + " SET ");
                int cnt = 0;
                for (Iterator iterator = mapSet.iterator(); iterator.hasNext();) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    cnt ++;
                    String column = (String) entry.getValue();
                    if (cnt == 1) {
                       sql.append(column + "=?");
                    } else {
                        sql.append(", " + column + "=?");
                    }
                }
                partoQueries.put(className, sql.toString());
            }
        }
        return partoQueries;
    }

    /**
     * To fetch a pageItem -
     * Useage: PageItem pageItem = (PageItem) DynaSiteObjects.getPageItems().get(pageItemId);
     *
     * @return Hashmap of pageItem.getId(), pageItem
     */
    public static HashMap getPageItems() {
        if (pageItems == null) {
            // log.debug("Populating pageItems from DynaSiteObjects.getPageItems");
            pageItems = new HashMap();
            Set formList = forms.keySet();
            for (Iterator iterator = formList.iterator(); iterator.hasNext();) {
                Long formId = (Long) iterator.next();
                Form form = (Form) forms.get(formId);
                Set pageItemList = form.getPageItems();
                for (Iterator iterator1 = pageItemList.iterator(); iterator1.hasNext();) {
                    PageItem pageItem = (PageItem) iterator1.next();
                    pageItems.put(pageItem.getId(), pageItem);
                }
            }
            // log.debug("PageItems size: " + pageItems.size());
        }
        return pageItems;
    }

    /**
     * This is only used for unit tests. Normally you should allow getPageItems to setup the pageItems in the servlet init;
     * otherwise you may not have a full HashMap of pageItems.
     *
     * @param pageItems
     */
    public static void setPageItems(HashMap pageItems) {
        DynaSiteObjects.pageItems = pageItems;
    }
    
    /**
     * populates formNameMap - <String>classname:<Long>formId
     * also sets classname in Form.
     * also populates formIdClassNameMap
     * @return
     */
    public static HashMap getFormNameMap() {
    	if (formNameMap == null) {
    		// log.debug("Populating pageItems from DynaSiteObjects.getPageItems");
    		formNameMap = new HashMap<String,Long>();
    		formIdClassNameMap = new HashMap<Long,String>();
    		Set formList = forms.keySet();
    		for (Iterator iterator = formList.iterator(); iterator.hasNext();) {
    			Long formId = (Long) iterator.next();
    			Form form = (Form) forms.get(formId);
    	        String classname = StringManipulation.fixClassname(form.getName());
    	        form.setClassname(classname);
    	        formNameMap.put(classname, formId);
    	        formIdClassNameMap.put(formId, classname);
    		}
    	}
    	return formNameMap;
    }
    

    /**
	 * @return the formIdClassNameMap - formId, classname
	 */
	public static HashMap<Long, String> getFormIdClassNameMap() {
		return formIdClassNameMap;
	}

	/**
	 * @param formIdClassNameMap the formIdClassNameMap to set
	 */
	public static void setFormIdClassNameMap(HashMap<Long, String> formIdClassNameMap) {
		DynaSiteObjects.formIdClassNameMap = formIdClassNameMap;
	}

    /**
     * Populates both forms and pageItem Maps
     * Usage:
     * Form encounterForm = (Form) DynaSiteObjects.getForms().get(new Long("66"));
     *
     * @return HashMap
     */
    public static HashMap getForms() {
        if (forms == null) {
            // log.debug("Populating forms from DynaSiteObjects.getForms");
            forms = new HashMap();
            try {
                // List formList = FormDisplayDAO.getAllFormIds();
                List formList = (List) XmlUtils.getOne(path + "Forms.xml");
                for (Iterator iterator = formList.iterator(); iterator.hasNext();) {
                    Form form = (Form) iterator.next();
                    // Form wholeForm = (Form) FormDisplayDAO.getFormGraph(form.getId());
                    String formName = StringManipulation.fixClassname(form.getName());
                    Form wholeForm = null;
					try {
						wholeForm = (Form) XmlUtils.getOne(path + formName + ".xml");
						forms.put(wholeForm.getId(), wholeForm);
					} catch (FileNotFoundException e) {
						log.debug("Error - Missing form: " + formName);
					}
                }
                // log.debug("Forms size= " + forms.size());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return forms;
    }

    /**
     * This is only used for unit tests. Normally you should allow getForms to setup the forms in the servlet init;
     * otherwise you may not have a full HashMap of forms.
     *
     * @param forms
     */
    public static void setForms(HashMap forms) {
        DynaSiteObjects.forms = forms;
    }

    /**
     * Loop through the forms and find pageItems that have formDependencies.
     * This is used to make hidden fields within a table visible when viewing a record.
     * The section in each form is the master items that has hidden formDependencies.
     * Each forms HashMap has (pageItem.id, section)
     *
     * @return HashMap
     */
    public static HashMap getFormSections() {
        if (formSections == null) {
            formSections = new HashMap();
            Set formList = forms.entrySet();
            for (Iterator iterator = formList.iterator(); iterator.hasNext();) {
                HashMap section = new HashMap();
                Map.Entry entry = (Map.Entry) iterator.next();
                Form form = (Form) entry.getValue();
                Set pageItems = form.getPageItems();
                PageItem master = null;
                Long masterId = null;
                HashMap formDeps = (HashMap) DynaSiteObjects.getFormDependencies().get(form.getId());
                ArrayList list = new ArrayList();
                for (Iterator iterator1 = pageItems.iterator(); iterator1.hasNext();) {
                    PageItem pageItem = (PageItem) iterator1.next();
                    FormField formfield = pageItem.getForm_field();
                    String type = pageItem.getInputType();
                    //String formDependencies = null;
                    if (formfield.getType().equals("Display")) {
                        // some fields are hidden, and dependent upon other fields
                        if (type.equals("display-tbl-begin")) {
                            // start adding items to the list, using this id as the key for the map
                            master = pageItem;
                            masterId = master.getId();
                            list = new ArrayList();
                        } else if (type.equals("display-tbl-end")) {
                            // clear out the master 'til the next display-tbl-begin appears
                            section.put(masterId, list);
                            master = null;
                        }
                    }
                    if (master != null) {
                        try {
                            // use the dep id to see if it is a key for the master hash
                            // the formField id is used for dep
                            // Long dep1 = (Long) master.getDempMap1().get(pageItem.getId());
                            Long dep1 = (Long) formDeps.get(formfield.getId());
                            try {
                                // section.put(dep1, masterId);
                                // section.put(pageItem.getId(), masterId);
                                list.add(pageItem.getId());
                            } catch (Exception e) {
                                log.error(e);
                            }
                        } catch (Exception e) {
                            // it's ok - this is not a dependency of this master
                        }
                    }
                }
                // add the section to the map
                formSections.put(form.getId(), section);

                // now let's handle other dependencies.
                // Prime the field-to-id mapping
                HashMap formFieldMap = (HashMap) DynaSiteObjects.getFieldToPageItem().get(form.getId());
                for (Iterator iterator1 = pageItems.iterator(); iterator1.hasNext();) {
                    PageItem pageItem = (PageItem) iterator1.next();
                    //FormField formfield = pageItem.getForm_field();
                   // String type = pageItem.getInputType();

                    if (pageItem.getVisibleDependencies1() != null) {
                        list = new ArrayList();
                        if (pageItem.getVisibleDependencies1().length() != 0) {
                            master = pageItem;
                            masterId = master.getId();
                            // formDependencies = master.getVisibleDependencies1();
                            String deps1 = master.getVisibleDependencies1();
                            if (deps1 != null) {
                                if (deps1.length() != 0) {
                                    for (StringTokenizer stringTokenizer = new StringTokenizer(deps1, ","); stringTokenizer.hasMoreTokens();) {
                                        Long dep = Long.decode(stringTokenizer.nextToken());
                                        //dependency.put(dep, masterId);
                                        // Now we need to convert this to a pageItem Id
                                        Long pid = (Long) formFieldMap.get(dep);
                                        list.add(pid);
                                    }
                                    section.put(masterId, list);
                                }
                            }
                        }
                    }
                    /*if ((master != null) & (!pageItem.isVisible())) {
                        try {
                            // use the dep id to see if it is a key for the master hash
                            // the formField id is used for dep
                            // Long dep1 = (Long) master.getDempMap1().get(pageItem.getId());
                            Long dep1 = (Long) formDeps.get(formfield.getId());
                            try {
                                // section.put(dep1, masterId);
                                // section.put(pageItem.getId(), masterId);
                                list.add(pageItem.getId());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } catch (Exception e) {
                            // it's ok - this is not a dependency of this master
                        }
                    }*/
                }
                // add the section to the map
                formSections.put(form.getId(), section);
            }
        }

        return formSections;
    }

    /**
     * Loop through the forms and find pageItems that have formDependencies.
     * This is used to make hidden fields within a table visible when viewing a record.
     * The section in each form is the master items that has hidden formDependencies.
     * Each forms HashMap has (pageItem.id, section)
     *
     * @return HashMap
     */
    public static HashMap getCollapsingSections() {
        if (collapsingSections == null) {
            collapsingSections = new HashMap();
            Set formList = forms.entrySet();
            for (Iterator iterator = formList.iterator(); iterator.hasNext();) {
                HashMap section = new HashMap();
                Map.Entry entry = (Map.Entry) iterator.next();
                Form form = (Form) entry.getValue();
                Set pageItems = form.getPageItems();
                PageItem master = null;
                Long masterId = null;
                HashMap formDeps = (HashMap) DynaSiteObjects.getFormDependencies().get(form.getId());
                ArrayList list = new ArrayList();
                PageItem collaspingMaster = null;  // for Collpasing section headers
                Long collaspingMasterId = null;
                ArrayList collaspingMasterList = new ArrayList();
                for (Iterator iterator1 = pageItems.iterator(); iterator1.hasNext();) {
                    PageItem pageItem = (PageItem) iterator1.next();
                    FormField formfield = pageItem.getForm_field();
                    String type = pageItem.getInputType();
                    //String formDependencies = null;
                    if (formfield.getType().equals("Display")) {
                        // some fields are hidden, and dependent upon other fields
                        if (type.equals("collapsing-display-header-begin")) {
                            // add the id of the table dependent on this link
                            collaspingMaster = pageItem;
                            collaspingMasterId = collaspingMaster.getId();
                            collaspingMasterList = new ArrayList();
                        } else if (type.equals("display-tbl-end")) {
                            // clear out the master 'til the next display-tbl-begin appears
                            if (collaspingMaster != null) {
                                section.put(collaspingMasterId, collaspingMasterList);
                                master = null;
                            }
                        }
                    }

                    if (collaspingMaster != null) {
                        try {
                            // use the dep id to see if it is a key for the master hash
                            // the formField id is used for dep
                            // Long dep1 = (Long) master.getDempMap1().get(pageItem.getId());
                            // Long dep1 = (Long) formDeps.get(formfield.getId());
                            try {
                                // section.put(dep1, masterId);
                                // section.put(pageItem.getId(), masterId);
                                collaspingMasterList.add(pageItem.getId());
                            } catch (Exception e) {
                                log.error(e);
                            }
                        } catch (Exception e) {
                            // it's ok - this is not a dependency of this master
                        }
                    }
                }
                // add the section to the map
                collapsingSections.put(form.getId(), section);
            }
        }

        return collapsingSections;
    }

    // HashMap of all visibleDependencies in format dep, masterId
    // This helps us find the section a field is in
    // Note that dep is formfield id, not pageItem id!!!
    public static HashMap getFormDependencies() {
        if (formDependencies == null) {
            formDependencies = new HashMap();
            Set formList = forms.entrySet();
            for (Iterator iterator = formList.iterator(); iterator.hasNext();) {
                HashMap dependency = new HashMap();
                Map.Entry entry = (Map.Entry) iterator.next();
                Form form = (Form) entry.getValue();
                Set pageItems = form.getPageItems();
                PageItem master = null;
                Long masterId = null;
                PageItem collaspingMaster = null;  // for Collpasing section headers
                Long collaspingMasterId = null;
                // ArrayList collaspingMasterList = new ArrayList();
                // First put in the table sections
                for (Iterator iterator1 = pageItems.iterator(); iterator1.hasNext();) {
                    PageItem pageItem = (PageItem) iterator1.next();
                    FormField formfield = pageItem.getForm_field();
                    String type = pageItem.getInputType();
                    //String formDependencies = null;

                    // First setup the sections
                    if (formfield.getType().equals("Display")) {
                        if (type.equals("display-tbl-begin")) {
                            // start adding items to the list, using this id as the key for the map
                            master = pageItem;
                            masterId = master.getId();
                        } else if (type.equals("display-tbl-end")) {
                            // clear out the master 'til the next display-tbl-begin appears
                            master = null;
                        }
                    }
                    // add the fields to each section
                    if (master != null) {
                        dependency.put(formfield.getId(), masterId);
                    }
                }

                // Now put in the visibleDependencies
                /*for (Iterator iterator2 = pageItems.iterator(); iterator2.hasNext();) {
                    PageItem pageItem = (PageItem) iterator2.next();
                    Long masterId = pageItem.getId();
                    String deps1 = pageItem.getVisibleDependencies1();
                    if (deps1 != null) {
                        if (deps1.length() != 0) {
                            for (StringTokenizer stringTokenizer = new StringTokenizer(deps1, ","); stringTokenizer.hasMoreTokens();) {
                                Long dep = Long.decode(stringTokenizer.nextToken());
                                dependency.put(dep, masterId);
                            }
                        }
                    }
                }*/
                //add dep to map
                formDependencies.put(form.getId(), dependency);
            }

        }
        return formDependencies;
    }

    // HashMap of all visibleDependencies in format dep, masterId
    // This helps us find the section a dependent table or field is in
    // Note that dep is formfield id, not pageItem id!!!
    public static HashMap getCollapsingDependencies() {
        if (collapsingDependencies == null) {
            collapsingDependencies = new HashMap();
            Set formList = forms.entrySet();
            for (Iterator iterator = formList.iterator(); iterator.hasNext();) {
                HashMap dependency = new HashMap();
                Map.Entry entry = (Map.Entry) iterator.next();
                Form form = (Form) entry.getValue();
                Set pageItems = form.getPageItems();
                PageItem collaspingMaster = null;  // for Collpasing section headers
                Long collaspingMasterId = null;
                // ArrayList collaspingMasterList = new ArrayList();
                // First put in the table sections
                for (Iterator iterator1 = pageItems.iterator(); iterator1.hasNext();) {
                    PageItem pageItem = (PageItem) iterator1.next();
                    FormField formfield = pageItem.getForm_field();
                    String type = pageItem.getInputType();
                    //String formDependencies = null;

                    // First setup the sections
                    if (formfield.getType().equals("Display")) {
                        if (type.equals("collapsing-display-header-begin")) {
                            // add the id of the table dependent on this link
                            collaspingMaster = pageItem;
                            collaspingMasterId = collaspingMaster.getId();
                            // collaspingMasterList = new ArrayList();
                        } else if (type.equals("display-tbl-end")) {
                            // clear out the master 'til the next collapsing-display-header-begin appears
                            if (collaspingMaster != null) {
                                collaspingMaster = null;
                            }
                        }
                    }
                    // add the fields to each section
                    if (collaspingMaster != null) {
                        dependency.put(formfield.getId(), collaspingMasterId);
                    }
                    // }
                }
                //add dep to map
                collapsingDependencies.put(form.getId(), dependency);
            }

        }
        return collapsingDependencies;
    }

    /**
     * Provides map for forms, with inner map for formfields to pageitem. Format: formId, (formFieldId,pageItemIDMap)
     *
     * @return Format: (formId, (formFieldId, Map of pageItem id's))
     */
    public static HashMap getFieldToPageItem() {
        if (fieldToPageItem == null) {
            fieldToPageItem = new HashMap();
            Set formList = forms.entrySet();
            for (Iterator iterator = formList.iterator(); iterator.hasNext();) {
                HashMap fieldMap = new HashMap();
                Map.Entry entry = (Map.Entry) iterator.next();
                Form form = (Form) entry.getValue();
                Set pageItems = form.getPageItems();
                for (Iterator iterator1 = pageItems.iterator(); iterator1.hasNext();) {
                    PageItem pageItem = (PageItem) iterator1.next();
                    FormField formField = pageItem.getForm_field();
                    fieldMap.put(formField.getId(), pageItem.getId());
                }
                fieldToPageItem.put(form.getId(), fieldMap);
            }
        }
        return fieldToPageItem;
    }



    /**
     * Provides list of forms that are provided by a field.
	 * @return the list fieldToForms
	 */
	public static HashMap getFieldToForms() {
		if (fieldToForms == null) {
			fieldToForms = new HashMap();
            HashMap pageItems = getPageItems();
            Collection pageSet = pageItems.values();
            for (Iterator iterator = pageSet.iterator(); iterator.hasNext();) {
            	//HashMap pageItemMap = iterator.next();
				PageItem pageItem = (PageItem) iterator.next();
				Long formFieldId = pageItem.getFormFieldId();
				Long formId = pageItem.getFormId();
				List formIdList = (List) fieldToForms.get(formFieldId);
				if (formIdList != null) {
					formIdList.add(formId);
				} else {
					formIdList = new ArrayList();
					formIdList.add(formId);
				}
				fieldToForms.put(formFieldId, formIdList);
			}
        }
		return fieldToForms;
	}

	/**
	 * @param fieldToForms the fieldToForms to set
	 */
	public static void setFieldToForms(HashMap fieldToForms) {
		DynaSiteObjects.fieldToForms = fieldToForms;
	}

	/**
     * Resolves special form id's to their proper url name.
     *
     * @return HashMap of form+id, long name
     */
    public static HashMap getFormNames() {

        if (formNames == null) {
            formNames = new HashMap();
            formNames.put("form1", "demographics");
            formNames.put("form2", "previousPregnancies");
            formNames.put("form4", "patientSafeMother");
            /*formNames.put("form63", "UTHSummary");
            formNames.put("form74", "UTHSummary");*/
            // formNames.put("form68", "maternalDischargeAction");
            formNames.put("form17", "labourObservations");
            formNames.put("form55", "currentMedicine");
            formNames.put("form79", "partograph");
            formNames.put("form80", "patientAnte");
            formNames.put("form81", "patientPuer");
            formNames.put("form82", "pregnancyDating");
            formNames.put("form91", "counsel");
            formNames.put("form88", "drugs");
            formNames.put("form90", "rpr");
            formNames.put("form87", "labs");
            formNames.put("form89", "arv");
        }
        return formNames;
    }

    /**
     * Provides a list of rules for a formFieldId.
     * @return Map of rules(formFieldId, list);
     */
    public static HashMap getRules() {

        if (rules == null) {
            rules = new HashMap();
            try {
                // rules = RuleDefinitionDAO.getAll();
                rules = (HashMap) XmlUtils.getOne(path + "Rules.xml");
            } catch (IOException e) {
                e.printStackTrace();
            }
            // log.info("Rules size: " + rules.size());
        }
        return rules;
    }

    /**
     * Provides a map of rules - ruleDefinition.getId(), ruleDefinition
     * @return Map of ruleDefinitions
     */
    public static HashMap getRuleMap() {

        if (ruleMap == null) {
            ruleMap = new HashMap();
            Set ruleSet = rules.entrySet();
            for (Iterator iterator = ruleSet.iterator(); iterator.hasNext();) {
                Map.Entry entryMap = (Map.Entry) iterator.next();
                List rules = (List) entryMap.getValue();
                for (int i = 0; i < rules.size(); i++) {
                    RuleDefinition ruleDefinition = (RuleDefinition) rules.get(i);
                    ruleMap.put(ruleDefinition.getId(), ruleDefinition);
                }
            }
        }
        return ruleMap;
    }

    /**
     *
     * @return Map of rules that, if triggered, are displayed for all pregnancies
     */
    public static HashMap getAllPregnanciesRules() {

        if (allPregnanciesRules == null) {
            allPregnanciesRules = new HashMap();
            Set ruleSet = rules.entrySet();
            for (Iterator iterator = ruleSet.iterator(); iterator.hasNext();) {
                Map.Entry entryMap = (Map.Entry) iterator.next();
                List rules = (List) entryMap.getValue();
                for (int i = 0; i < rules.size(); i++) {
                    RuleDefinition ruleDefinition = (RuleDefinition) rules.get(i);
                    if (ruleDefinition.isAllPregnancies() == true) {
                        allPregnanciesRules.put(ruleDefinition.getId(), ruleDefinition);
                    }
                }
            }
        }
        return allPregnanciesRules;
    }

    /**
     * Provides a list of formId, formFieldIdList of the field id's that have rules.
     * @return
     */
    public static HashMap getRulesToForms() {

        if (rulesToForms == null) {
            rulesToForms = new HashMap();
            // for each form , make a list of the field id's that have rules.
            // loop through form -> formfieldid
            Set formList = forms.entrySet();
            for (Iterator iterator = formList.iterator(); iterator.hasNext();) {
                Map.Entry entry = (Map.Entry) iterator.next();
                Form form = (Form) entry.getValue();
                Long formId = form.getId();
                Set pageItems = form.getPageItems();
                for (Iterator iterator1 = pageItems.iterator(); iterator1.hasNext();) {
                    PageItem pageItem = (PageItem) iterator1.next();
                    FormField formField = pageItem.getForm_field();
                    Long formfieldId = formField.getId();
                    if (formField.getRuleDefinitions().size() != 0) {
                        List formFieldIdList = null;
                        if (rulesToForms.get(formId) != null) {
                            formFieldIdList = (List) rulesToForms.get(formId);
                            formFieldIdList.add(formfieldId);
                        } else {
                            formFieldIdList = new ArrayList();
                            formFieldIdList.add(formfieldId);
                            rulesToForms.put(formId, formFieldIdList);
                        }
                    }
                }
            }
        }
        return rulesToForms;
    }

    public static void setRulesToForms(HashMap rulesToForms) {
        DynaSiteObjects.rulesToForms = rulesToForms;
    }

    /**
     * This is only used for generating xml files, in case rules have been manipulated outside the db.
     *
     * @param rules
     */
    public static void setRules(HashMap rules) {
        DynaSiteObjects.rules = rules;
    }

    public static List getActivefields() {
        if (activefields == null) {
            activefields = new ArrayList();
            try {
                activefields = (List) XmlUtils.getOne(path + "ActiveFields.xml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return activefields;
    }

    public static void setActivefields(List activefields) {
        DynaSiteObjects.activefields = activefields;
    }

    /**
     * Gets a list of tasks for this flow.
     * @return list of tasks for this flow
     */
    public static HashMap getTasksForFlow() {

        if (tasksForFlow == null) {
            tasksForFlow = new HashMap();
            Set formList = DynaSiteObjects.getForms().entrySet();
            for (Iterator iterator = formList.iterator(); iterator.hasNext();) {
                Map.Entry entry = (Map.Entry) iterator.next();
                Form form = (Form) entry.getValue();
                //   for (int i = 0; i < forms.size(); i++) {
                //      Form form = (Form) forms.get(i);
                if (form.isEnabled() == true) {
                    Long flowId = form.getFlowId();
                    List tasks = (List) tasksForFlow.get(flowId);
                    if (tasks == null) {
                        tasks = new ArrayList();
                        tasksForFlow.put(flowId, tasks);
                    }
                    Task task = new Task();
                    task.setLabel(form.getLabel());
                    task.setFormId(form.getId());
                    task.setMaxSubmissions(form.getMaxSubmissions());
                    task.setTaskType(form.getFormTypeId().intValue());
                    task.setFlowOrder(form.getFlowOrder());
                    task.setFlowId(flowId);
                    tasks.add(task);
                    FlowOrderComparator foc = new FlowOrderComparator();
                    Collections.sort(tasks, foc);
                }
            }
        }
        return tasksForFlow;
    }

    /**
     * This returns dynasite classnames for regular dynasite classes and report classes.
     *  Provides HashMap in format "form" + formId for regular dynasite classes, and "report" + formId for report classes.
     * @return HashMap in format formId, Classname
     */
    public static HashMap getFormIdToClassNames() {
        if (formIdToClassNames == null) {
            formIdToClassNames = new HashMap();
            Set formList = DynaSiteObjects.getForms().entrySet();
            for (Iterator iterator = formList.iterator(); iterator.hasNext();) {
                Map.Entry entry = (Map.Entry) iterator.next();
                Form form = (Form) entry.getValue();
                if (form.isEnabled() == true) {
                    String className = "org.cidrz.project.zeprs.valueobject.gen." + StringManipulation.fixClassname(form.getName());
                    String classNameR = "org.cidrz.project.zeprs.valueobject.report.gen." + StringManipulation.fixClassname(form.getName() + "Report");
                    formIdToClassNames.put("form" + form.getId(), className);
                    formIdToClassNames.put("report" + form.getId(), classNameR);
                }
            }
        }
        return formIdToClassNames;
    }

    public static HashMap getStatusMap() {
        return statusMap;
    }

    public static void setStatusMap(HashMap statusMap) {
        DynaSiteObjects.statusMap = statusMap;
    }

	/**
	 * @return Is the system running in a dev configuration?
	 */
	public static Boolean getDev() {
		return dev;
	}

	/**
	 * @param dev set if the system is running in a dev configuration
	 */
	public static void setDev(Boolean dev) {
		DynaSiteObjects.dev = dev;
	}

	/**
	 * @return the masterArchiveIndex
	 */
	public static HashMap<String, String> getMasterArchiveIndex() {

		if (masterArchiveIndex == null) {
			masterArchiveIndex = new HashMap<String,String>();
            try {
            	masterArchiveIndex = (HashMap) XmlUtils.getOne(Constants.MASTER_ARCHIVE_INDEX_XML,null);
            	//log.debug("masterArchiveIndex size: " + masterArchiveIndex.size());
            } catch (IOException e) {
                log.debug(Constants.MASTER_ARCHIVE_INDEX_XML + " not available.");
            }
        }

		return masterArchiveIndex;
	}

	/**
	 * @param masterArchiveIndex the masterArchiveIndex to set
	 */
	public static void setMasterArchiveIndex(HashMap<String, String> masterArchiveIndex) {
		DynaSiteObjects.masterArchiveIndex = masterArchiveIndex;
	}
	
	/**
	 * Populates locale-related lists and maps.
	 * @return
	 */
//	public static ApplicationDefinition getApplicationDefinition() {
//		ArrayList<String> localeStringList = null;
//		if (applicationDefinition == null)
//			try {
//				applicationDefinition = (ApplicationDefinition) XmlUtils.getOne(Constants.DYNASITE_XML_PATH + Constants.APPLICATION_DEFINITION_FILENAME, "json", ApplicationDefinition.class);
//				localeDisplayName = new ArrayList<String>();
//				localeDisplayMap = new HashMap<String,String>();
//
//				localeStringList = applicationDefinition.getLocalList();
//				for (String locales : localeStringList) {
//					Locale locale = null;
//					if(locales.contains("_")) {
//						if(locales.charAt(2) == '_') {
//							locale = new Locale(locales.substring(0, 2),locales.substring(3, 5) );
//							if( (locales.substring(5, locales.length())).contains("_")) {
//								if(locales.charAt(5) == '_')
//									locale = new Locale(locales.substring(0, 2),locales.substring(3, 5), locales.substring(6, 8)  );
//							}
//						}
//					} else {
//						locale = new Locale(locales);
//					}
//					if (localeList == null) {
//						localeList = new ArrayList<Locale>();
//					}
//					localeList.add(locale);
//					localeDisplayName.add(locale.getDisplayName());
//					localeDisplayMap.put(locales, locale.getDisplayName());
//				}
//			} catch (FileNotFoundException e) {
//				//log.debug(Constants.DYNASITE_XML_PATH + Constants.APPLICATION_DEFINITION_FILENAME + " not found.");
//			} catch (IOException e) {
//				log.debug("Problem with " + Constants.DYNASITE_XML_PATH + Constants.APPLICATION_DEFINITION_FILENAME + ": " + e);
//			}
//		return applicationDefinition;
//	}

	public static void setApplicationDefinition(ApplicationDefinition applicationDefinition)
	{
		DynaSiteObjects.applicationDefinition = applicationDefinition;
	}

	public static ArrayList<String> getLocaleDisplayName() {
		return localeDisplayName;
	}

	public static void setLocaleDisplayName(ArrayList<String> localeDisplayName) {
		DynaSiteObjects.localeDisplayName = localeDisplayName;
	}

	/**
	 * @return the localeDisplayMap
	 */
	public static HashMap<String, String> getLocaleDisplayMap() {
		return localeDisplayMap;
	}

	/**
	 * @param localeDisplayMap the localeDisplayMap to set
	 */
	public static void setLocaleDisplayMap(HashMap<String, String> localeDisplayMap) {
		DynaSiteObjects.localeDisplayMap = localeDisplayMap;
	}

	/**
	 * @return the localeMessageMap
	 */
	public static HashMap<String, String> getLocaleMessageMap() {
		if (localeMessageMap == null) {
        	localeMessageMap = new HashMap<String,String>();
        }
		return localeMessageMap;
	}

	/**
	 * @param localeMessageMap the localeMessageMap to set
	 */
	public static void setLocaleMessageMap(HashMap<String, String> localeMessageMap) {
		DynaSiteObjects.localeMessageMap = localeMessageMap;
	}

//	public static ArrayList<MenuItem> getMenuItemList() {
//		if (menuItemList == null)
//			try {
//				menuItemList = (ArrayList<MenuItem>) XmlUtils.getOne(Constants.DYNASITE_XML_PATH + Constants.MENUITEM_LIST_FILENAME, "json", null);
//				DisplayOrderComparator doc = new DisplayOrderComparator();
//		        Collections.sort(menuItemList, doc);
//			} catch (FileNotFoundException e) {
//				// not created yet
//			} catch (IOException e) {
//				log.debug(e);
//			}
//		return menuItemList;
//	}

	public static void setMenuItemList(ArrayList<MenuItem> newMenuItemList) {
		menuItemList = newMenuItemList;
	}

	/**
	 * @return the propertyFileReloadMap
	 */
	public static HashMap<String, Boolean> getPropertyFileReloadMap() {
		return propertyFileReloadMap;
	}

	/**
	 * @param propertyFileReloadMap the propertyFileReloadMap to set
	 */
	public static void setPropertyFileReloadMap(HashMap<String, Boolean> propertyFileReloadMap) {
		DynaSiteObjects.propertyFileReloadMap = propertyFileReloadMap;
	}

	/**
	 * @return the localeList
	 */
	public static ArrayList<Locale> getLocaleList() {
		return localeList;
	}

	/**
	 * @param localeList the localeList to set
	 */
	public static void setLocaleList(ArrayList<Locale> localeList) {
		DynaSiteObjects.localeList = localeList;
	}

	/**
	 * @return the problemListEnabled
	 */
	public static Boolean getProblemListEnabled() {
		if (problemListEnabled == null) {
			String enabled = Constants.getProperties("problemList.enabled",Constants.APP_PROPERTIES);
			if (enabled != null) {
				try {
					problemListEnabled = Boolean.valueOf(enabled);
				} catch (Exception e) {
					log.debug("Incorrect setting in application.properties: problemList.enabled should be either true or false.");
				}
			}
		}
		return problemListEnabled;
	}

	/**
	 * @param problemListEnabled the problemListEnabled to set
	 */
	public static void setProblemListEnabled(Boolean problemListEnabled) {
		DynaSiteObjects.problemListEnabled = problemListEnabled;
	}

	/**
	 * @return the webDavArchiveFolderDate
	 */
	public static String getWebDavArchiveFolderDate() {
		return webDavArchiveFolderDate;
	}

	/**
	 * @param webDavArchiveFolderDate the webDavArchiveFolderDate to set
	 */
	public static void setWebDavArchiveFolderDate(String webDavArchiveFolderDate) {
		DynaSiteObjects.webDavArchiveFolderDate = webDavArchiveFolderDate;
	}

	/**
	 * @return the proxyServer
	 */
//	public static Server getProxyServer() {
//		if (proxyServer == null) {
//			File name = new File(Constants.ARCHIVE_PATH_SERVERS);
//	    	String[] directory = name.list();
//	    	//ArrayList<String> proxyDirectory = new ArrayList<String>();
//	    	for (String serverConfigFile : directory) {
//	    		Server serverConfig;
//				try {
//					serverConfig = (Server) XmlUtils.getOne(Constants.ARCHIVE_PATH_SERVERS + serverConfigFile, Constants.SYNC_FORMAT, Server.class);
//					String serverType = serverConfig.getServerType();
//					Boolean enabled = serverConfig.getEnabled();
//		    		if ((serverType.equals("proxy")) && ((enabled != null) && (enabled == Boolean.TRUE))) {
//		    			proxyServer = serverConfig;
//		    		}
//				} catch (FileNotFoundException e) {
//					log.debug(e);
//				} catch (IOException e) {
//					log.debug(e);
//				}
//			}
//		}
//		return proxyServer;
//	}

	/**
	 * @param proxyServer the proxyServer to set
	 */
	public static void setProxyServer(Server proxyServer) {
		DynaSiteObjects.proxyServer = proxyServer;
	}

	public static Boolean getNeedProxyAuthentication() {
		return needProxyAuthentication;
	}

	public static void setNeedProxyAuthentication(Boolean needProxyAuthentication) {
		DynaSiteObjects.needProxyAuthentication = needProxyAuthentication;
	}


}
