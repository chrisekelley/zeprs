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


import org.cidrz.webapp.dynasite.rules.RuleProvider;
import org.cidrz.webapp.dynasite.rules.RuleUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @hibernate.class table="form_field"  lazy="false"
 * mutable="true"
 * @hibernate.cache usage="read-write"
 */
public class FormField implements RuleProvider, Identifiable, DisplayOrderable, org.cidrz.webapp.dynasite.valueobject.Auditable, Configuration, Serializable {
    private Long id;
    //private Set forms = new HashSet();
    private String label;
    private String type;
    private boolean required;
    private Integer minValue;
    private Integer maxValue;
    private String units;
    private Integer displayOrder = new Integer(Integer.MAX_VALUE);
    private String patientProperty;
    //private DisplayHint displayHint = new DisplayHint();
    private boolean isEnabled;
    private AuditInfo auditInfo;
    private List ruleDefinitions = new ArrayList();
    private Set enumerations = new HashSet();
    private boolean shared;
    private String starSchemaName;
    private String encounterRecordproperty;
    private Set pageItems;
    private String patientStatusproperty;
    private String patientLabproperty;

    public FormField() {
    }

    public FormField(Long id, String label, String type, boolean required, Integer minValue, Integer maxValue, String units, Integer displayOrder, String patientProperty, boolean enabled, AuditInfo auditInfo, List ruleDefinitions, Set enumerations, boolean shared, String starSchemaName, String encounterRecordproperty, Set pageItems, String patientStatusproperty, String patientLabproperty) {
        this.id = id;
        this.label = label;
        this.type = type;
        this.required = required;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.units = units;
        this.displayOrder = displayOrder;
        this.patientProperty = patientProperty;
        isEnabled = enabled;
        this.auditInfo = auditInfo;
        this.ruleDefinitions = ruleDefinitions;
        this.enumerations = enumerations;
        this.shared = shared;
        this.starSchemaName = starSchemaName;
        this.encounterRecordproperty = encounterRecordproperty;
        this.pageItems = pageItems;
        this.patientStatusproperty = patientStatusproperty;
        this.patientLabproperty = patientLabproperty;
    }

    public List getRules() {
        List ruleDefs = this.getRuleDefinitions();
        return RuleUtils.getRules(ruleDefs);
    }

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
     * @hibernate.property column="type"
     * not-null="true"
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return
     * @hibernate.property column="required"
     * not-null="true"
     */
    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    /**
     * @return
     * @hibernate.property column="min_value"
     * not-null="false"
     */
    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    /**
     * @return
     * @hibernate.property column="max_value"
     * not-null="false"
     */
    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * @return
     * @hibernate.property column="units"
     * not-null="false"
     */
    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    /**
     * @return
     * @hibernate.property column="display_order"
     * not-null="false"
     */
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    /**
     * @return
     * @hibernate.property column="patient_property"
     * not-null="false"
     */
    public String getPatientProperty() {
        return patientProperty;
    }

    public void setPatientProperty(String patientProperty) {
        this.patientProperty = patientProperty;
    }

    /**
     * @return
     * @hibernate.property column="is_enabled"
     * not-null="true"
     */
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
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

    /**
     * @return
     * @hibernate.bag inverse="true"
     * lazy="false"
     * @hibernate.key column="field_id"
     * @hibernate.one-to-many class="org.cidrz.webapp.dynasite.valueobject.RuleDefinition"
     */
    public List getRuleDefinitions() {
        return ruleDefinitions;
    }

    public void setRuleDefinitions(List ruleDefinitions) {
        this.ruleDefinitions = ruleDefinitions;
    }

    /**
     * @hibernate.set inverse="true"
     * lazy="false"
     * order-by="display_order asc"
     * cascade="save-update"
     * @hibernate.key column="field_id"
     * @hibernate.one-to-many class="org.cidrz.webapp.dynasite.valueobject.FieldEnumeration"
     * @hibernate.cache usage="read-write"
     */
    public Set getEnumerations() {
        return enumerations;
    }

    public void setEnumerations(Set enumerations) {
        this.enumerations = enumerations;
    }

    /**
     * @return
     * @hibernate.property column="shared" not-null="true"
     */
    public boolean isShared() {
        return this.shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    /**
     * @return column name in table
     * @hibernate.property column="star_schema_name"
     * not-null="false"
     */

    public String getStarSchemaName() {
        return starSchemaName;
    }

    public void setStarSchemaName(String starSchemaName) {
        this.starSchemaName = starSchemaName;
    }

    /**
     * @return
     * @hibernate.property column="encounter_record_property"
     * not-null="true"
     */

    public String getEncounterRecordproperty() {
        return encounterRecordproperty;
    }

    public void setEncounterRecordproperty(String encounterRecordproperty) {
        this.encounterRecordproperty = encounterRecordproperty;
    }

    /**
     * @hibernate.set inverse="true"
     * lazy="false"
     * order-by="id asc"
     * cascade="none"
     * @hibernate.key column="form_field_id"
     * @hibernate.one-to-many class="org.cidrz.webapp.dynasite.valueobject.PageItem"
     */
    public Set getPageItems() {
        return pageItems;
    }

    public void setPageItems(Set pageItems) {
        this.pageItems = pageItems;
    }

    /**
     * @return
     * @hibernate.property column="patient_status_property"
     * not-null="false"
     */

    public String getPatientStatusproperty() {
        return patientStatusproperty;
    }

    public void setPatientStatusproperty(String patientStatusproperty) {
        this.patientStatusproperty = patientStatusproperty;
    }

    /**
     * @return
     * @hibernate.property column="patient_lab_property"
     * not-null="false"
     */
    public String getPatientLabproperty() {
        return patientLabproperty;
    }

    public void setPatientLabproperty(String patientLabproperty) {
        this.patientLabproperty = patientLabproperty;
    }

}
