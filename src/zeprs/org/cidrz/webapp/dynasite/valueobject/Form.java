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

import org.cidrz.webapp.dynasite.valueobject.AuditInfo;
import org.cidrz.webapp.dynasite.utils.sort.DisplayOrderComparator;
import org.cidrz.webapp.dynasite.rules.RuleProvider;
import org.cidrz.webapp.dynasite.rules.RuleUtils;
import org.rti.zcore.FormDomain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.io.Serializable;

/**
 * @hibernate.class table="form" lazy="false"
 * @hibernate.cache usage="read-write"
 */

public class Form implements RuleProvider, Identifiable, org.cidrz.webapp.dynasite.valueobject.Auditable, Configuration, FlowOrderable, Serializable {
    private Long id;
    private String name;
    private String label;
    private boolean requireReauth;
    private boolean requirePatient;
    private boolean enabled;
    private AuditInfo auditInfo;
    private List ruleDefinitions = new ArrayList();
    private Flow flow;
    private Long flowId;
    private Integer flowOrder;
    private FormType formType;
    private Long formTypeId;
    private Set pageItems = new TreeSet(new DisplayOrderComparator());
    private Set formfields = new TreeSet(new DisplayOrderComparator());
    private Integer submissions = new Integer(0);
    private Integer maxSubmissions;
    private Long recordCount;	// count of records that use this field.
    private Integer recordsPerEncounter;	// number of records in bridge table per encounter. Used in Patient Bridge forms.
    private Boolean resizedForPatientBridge;	// True if system has already created additional pageItems for a Patient Bridge form.
    private String globalIdentifierName;	// unique identifier for the form.
    private Long importId;
    private String classname;	// set during app startup by Dynasite
    private FormDomain formDomain;	//mother, infant, both, etc.
    private Long formDomainId;
    private Boolean startNewEvent;	// submission of this form means that a new Event must be starting
    private String eventType;
    private String importType;
    private String locale;
    private ArrayList<String> localeList = new ArrayList<String>();
    private Long siteId;
    private String superClass;
    private boolean demographicsForm;


    public Form() {
        }

    public Form(Long id, String name, String label, boolean requireReauth, boolean requirePatient, boolean enabled, AuditInfo auditInfo, List ruleDefinitions, Flow flow, Long flowId, Integer flowOrder, FormType formType, Long formTypeId, Set pageItems, Set formfields, Integer submissions, Integer maxSubmissions) {
        this.id = id;
        this.name = name;
        this.label = label;
        this.requireReauth = requireReauth;
        this.requirePatient = requirePatient;
        this.enabled = enabled;
        this.auditInfo = auditInfo;
        this.ruleDefinitions = ruleDefinitions;
        this.flow = flow;
        this.flowId = flowId;
        this.flowOrder = flowOrder;
        this.formType = formType;
        this.formTypeId = formTypeId;
        this.pageItems = pageItems;
        this.formfields = formfields;
        this.submissions = submissions;
        this.maxSubmissions = maxSubmissions;
    }

    //Form encounterForm = new Form();

    /**
     * @return
     * @hibernate.id unsaved-value="0"
     * generator-class="identity"
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return table name in database
     * @hibernate.property column="name"
     * not-null="true"
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     * @hibernate.property column="label"
     * not-null="true"
     */
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return
     * @hibernate.property column="require_reauth"
     * not-null="true"
     */
    public boolean isRequireReauth() {
        return requireReauth;
    }

    public void setRequireReauth(boolean requireReauth) {
        this.requireReauth = requireReauth;
    }

    /**
     * @return
     * @hibernate.property column="require_patient"
     * not-null="true"
     */
    public boolean isRequirePatient() {
        return requirePatient;
    }

    public void setRequirePatient(boolean requirePatient) {
        this.requirePatient = requirePatient;
    }

    /**
     * @return
     * @hibernate.property column="is_enabled"
     * not-null="true"
     */
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @hibernate.component
     * class="org.cidrz.webapp.dynasite.valueobject.AuditInfo"
     */
    public AuditInfo getAuditInfo() {
        if (auditInfo == null) {
            auditInfo = new AuditInfo();
        }
        return auditInfo;
    }

    public void setAuditInfo(AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }

    public List getRules() {
        List ruleDefs = this.getRuleDefinitions();
        return RuleUtils.getRules(ruleDefs);
    }

    /**
     * @return
     * @hibernate.bag inverse="true"
     * lazy="false"
     * @hibernate.key column="form_id"
     * @hibernate.one-to-many class="org.cidrz.webapp.dynasite.valueobject.RuleDefinition"
     */
    public List getRuleDefinitions() {
        return ruleDefinitions;
    }

    public void setRuleDefinitions(List ruleDefinitions) {
        this.ruleDefinitions = ruleDefinitions;
    }

    public Flow getFlow() {
        return flow;
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }

      /**
     * @return
     * @hibernate.property column="flow_id"
     * not-null="false"
     */
    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    /**
     * @return
     * @hibernate.property column="flow_order"
     * not-null="false"
     */

    public Integer getFlowOrder() {
        return flowOrder;
    }

    public void setFlowOrder(Integer flowOrder) {
        this.flowOrder = flowOrder;
    }

    public FormType getFormType() {
        return formType;
    }

    public void setFormType(FormType formType) {
        this.formType = formType;
    }

    /**
     * @return
     * @hibernate.property column="form_type_id"
     * not-null="false"
     */

    public Long getFormTypeId() {
        return formTypeId;
    }

    public void setFormTypeId(Long formTypeId) {
        this.formTypeId = formTypeId;
    }

    /**
     * Use this to display all of the form widgets - not for form field processings, like when you persist an encounter.
     * Otherwise, use formfields.
     *
     * @return
     * @hibernate.set inverse="true"
     * lazy="true"
     * cascade="save-update"
     * sort="org.cidrz.webapp.dynasite.utils.sort.DisplayOrderComparator"
     * @hibernate.key column="form_id"
     * @hibernate.one-to-many class="org.cidrz.webapp.dynasite.valueobject.PageItem"
     * @hibernate.cache usage="read-write"
     */

    public Set getPageItems() {
        return pageItems;
    }

    public void setPageItems(Set pageItems) {
        this.pageItems = pageItems;
    }

    /**
     * this is used for form field processing - it does not include display widgets.
     *
     * @return
     * @hibernate.set inverse="true"
     * lazy="true"
     * where="input_type Not Like 'display%'"
     * sort="org.cidrz.webapp.dynasite.utils.sort.DisplayOrderComparator"
     * @hibernate.key column="form_id"
     * @hibernate.one-to-many class="org.cidrz.webapp.dynasite.valueobject.PageItem"
     * @hibernate.cache usage="read-write"
     */

    public Set getFormfields() {
        return formfields;
    }

    public void setFormfields(Set formfields) {
        this.formfields = formfields;
    }

    /**
     * do not need to persist this property.
     * @return submissions
     */
    public Integer getSubmissions() {
        return submissions;
    }

    public void setSubmissions(Integer submissions) {
        this.submissions = submissions;
    }

    /**
     * @return
     * @hibernate.property column="max_submissions"
     * not-null="false"
     */

    public Integer getMaxSubmissions() {
        return maxSubmissions;
    }

    public void setMaxSubmissions(Integer maxSubmissions) {
        this.maxSubmissions = maxSubmissions;
    }

    /**
     *
     * @return Number of records in this table.
     */
	public Long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}

	public String getGlobalIdentifierName() {
		return globalIdentifierName;
	}

	public void setGlobalIdentifierName(String globalIdentifierName) {
		this.globalIdentifierName = globalIdentifierName;
	}
	
	 /**
     * @return
     * @hibernate.property column="import_id"
     */
	public Long getImportId() {
		return importId;
	}

	public void setImportId(Long importId) {
		this.importId = importId;
	}

	public Long getFormDomainId() {
		return formDomainId;
	}

	public void setFormDomainId(Long formDomainId) {
		this.formDomainId = formDomainId;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public Integer getRecordsPerEncounter() {
		return recordsPerEncounter;
	}

	public void setRecordsPerEncounter(Integer recordsPerEncounter) {
		this.recordsPerEncounter = recordsPerEncounter;
	}

	public Boolean getResizedForPatientBridge() {
		return resizedForPatientBridge;
	}

	public void setResizedForPatientBridge(Boolean resizedForPatientBridge) {
		this.resizedForPatientBridge = resizedForPatientBridge;
	}

	public FormDomain getFormDomain() {
		return formDomain;
	}

	public void setFormDomain(FormDomain formDomain) {
		this.formDomain = formDomain;
	}

	public Boolean getStartNewEvent() {
		return startNewEvent;
	}

	public void setStartNewEvent(Boolean startNewEvent) {
		this.startNewEvent = startNewEvent;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getImportType() {
		return importType;
	}

	public void setImportType(String importType) {
		this.importType = importType;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public ArrayList<String> getLocaleList() {
		return localeList;
	}

	public void setLocaleList(ArrayList<String> localeList) {
		this.localeList = localeList;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public String getSuperClass() {
		return superClass;
	}

	public void setSuperClass(String superClass) {
		this.superClass = superClass;
	}

	public boolean isDemographicsForm() {
		return demographicsForm;
	}

	public void setDemographicsForm(boolean demographicsForm) {
		this.demographicsForm = demographicsForm;
	}



}

 class FormProxy extends Form {

     public FormProxy() {
     }

     public FormProxy(Long id, String name, String label, boolean requireReauth, boolean requirePatient, boolean enabled, AuditInfo auditInfo, List ruleDefinitions, Flow flow, Long flowId, Integer flowOrder, FormType formType, Long formTypeId, Set pageItems, Set formfields, Integer submissions, Integer maxSubmissions) {
         super(id, name, label, requireReauth, requirePatient, enabled, auditInfo, ruleDefinitions, flow, flowId, flowOrder, formType, formTypeId, pageItems, formfields, submissions, maxSubmissions);
     }
 }
