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

import org.apache.commons.beanutils.BeanUtils;
import org.cidrz.webapp.dynasite.rules.Outcome;
import org.cidrz.webapp.dynasite.rules.Rule;
import org.cidrz.webapp.dynasite.rules.impl.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @hibernate.class table="rule_definition"
 * lazy="false"
 * mutable="true"
 */
public class RuleDefinition implements Identifiable, Auditable, Configuration, Serializable {
    private Long id;
    private String ruleClass;
    private String outcomeClass;
    private Form form;
    private Long formId;
    private FormField field;
    private Long fieldId;
    private boolean enabled;
    private AuditInfo auditInfo;
    private Map ruleParams = new HashMap();
    private Map outcomeParams = new HashMap();
    private String fieldName;
    private String message;
    private String operand;
    private String operator;
    private boolean allPregnancies;
    private Long importId;

    public RuleDefinition() {
    }

    public RuleDefinition(Long id, String ruleClass, String outcomeClass, Form form, Long formId, FormField field, Long fieldId, boolean enabled, AuditInfo auditInfo, Map ruleParams, Map outcomeParams, String fieldName, String message, String operand, String operator, boolean allPregnancies) {
        this.id = id;
        this.ruleClass = ruleClass;
        this.outcomeClass = outcomeClass;
        this.form = form;
        this.formId = formId;
        this.field = field;
        this.fieldId = fieldId;
        this.enabled = enabled;
        this.auditInfo = auditInfo;
        this.ruleParams = ruleParams;
        this.outcomeParams = outcomeParams;
        this.fieldName = fieldName;
        this.message = message;
        this.operand = operand;
        this.operator = operator;
        this.allPregnancies = allPregnancies;
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
     * @hibernate.property column="rule_class"
     * not-null="true"
     */
    public String getRuleClass() {
        return ruleClass;
    }

    public void setRuleClass(String ruleClass) {
        this.ruleClass = ruleClass;
    }

    /**
     * @return
     * @hibernate.property column="outcome_class"
     * not-null="true"
     */
    public String getOutcomeClass() {
        return outcomeClass;
    }

    public void setOutcomeClass(String outcomeClass) {
        this.outcomeClass = outcomeClass;
    }

    /**
     * @return
     * @hibernate.many-to-one column="form_id"
     * cascade="none"
     */
    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }


    public FormField getField() {
        return field;
    }

    public void setField(FormField field) {
        this.field = field;
    }

    /**
     * @return
     * @hibernate.property column="field_id"
     */
    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
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

    /**
     * @hibernate.map table="rule_definition_param"
     * lazy="false"
     * cascade="all"
     * @hibernate.key column="rule_definition_id"
     * @hibernate.index column="param_name"
     * type="java.lang.String"
     * @hibernate.element column="param_value"
     * type="java.lang.String"
     */
    public Map getRuleParams() {
        return ruleParams;
    }

    public void setRuleParams(Map ruleParams) {
        this.ruleParams = ruleParams;
    }

    /**
     * @deprecated Outcome params are now stored in getRuleParams
     */
    public Map getOutcomeParams() {
        return getRuleParams();
    }

    public Rule getInstance() throws Exception {
        Rule ruleInstance = (Rule) Class.forName(this.getRuleClass()).newInstance();
        Outcome outcomeInstance = (Outcome) Class.forName(this.getOutcomeClass()).newInstance();
        ruleInstance.setOutcome(outcomeInstance);
        ruleInstance.setRuleDefinition(this);
        ruleParams = getRuleParams();
        outcomeParams = getRuleParams();
        if (ruleInstance.getClass().equals(SimpleComparison.class)) {
            float operand = new Float(ruleInstance.getRuleDefinition().getOperand());
            BeanUtils.copyProperty(ruleInstance, "operand", operand);
            BeanUtils.copyProperty(ruleInstance, "operator", ruleInstance.getRuleDefinition().getOperator());
        } else if (ruleInstance.getClass().equals(ScriptRule.class)) {
            String operand = ruleInstance.getRuleDefinition().getOperand();
            BeanUtils.copyProperty(ruleInstance, "operand", operand);
        }
        if (outcomeInstance.getClass().equals(EncounterOutcome.class)) {
            Integer requiredFormId = ruleInstance.getRuleDefinition().getFormId().intValue();
            BeanUtils.copyProperty(outcomeInstance, "requiredFormId", requiredFormId);
        } else if (outcomeInstance.getClass().equals(InfoOutcome.class)) {
            String message = ruleInstance.getRuleDefinition().getMessage();
            BeanUtils.copyProperty(outcomeInstance, "message", message);
        } else if (outcomeInstance.getClass().equals(ReferralOutcome.class)) {
            String reason = ruleInstance.getRuleDefinition().getMessage();
            BeanUtils.copyProperty(outcomeInstance, "reason", reason);
        }
        //BeanUtils.populate(ruleInstance, ruleParams);
        BeanUtils.populate(outcomeInstance, outcomeParams);
        return ruleInstance;
    }

    // For display of rule list in Admin
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * @return
     * @hibernate.property column="message"
     */
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return
     * @hibernate.property column="operand"
     */
    public String getOperand() {
        return operand;
    }

    public void setOperand(String operand) {
        this.operand = operand;
    }

    /**
     * @return
     * @hibernate.property column="operator"
     */
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * Does rule persist for all pregs?
     * @return
     * @hibernate.property column="all_pregs"
     */
    public boolean isAllPregnancies() {
        return allPregnancies;
    }

    public void setAllPregnancies(boolean allPregnancies) {
        this.allPregnancies = allPregnancies;
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

}
