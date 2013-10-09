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



}

 class FormProxy extends Form {

     public FormProxy() {
     }

     public FormProxy(Long id, String name, String label, boolean requireReauth, boolean requirePatient, boolean enabled, AuditInfo auditInfo, List ruleDefinitions, Flow flow, Long flowId, Integer flowOrder, FormType formType, Long formTypeId, Set pageItems, Set formfields, Integer submissions, Integer maxSubmissions) {
         super(id, name, label, requireReauth, requirePatient, enabled, auditInfo, ruleDefinitions, flow, flowId, flowOrder, formType, formTypeId, pageItems, formfields, submissions, maxSubmissions);
     }
 }
