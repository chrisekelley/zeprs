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

import java.io.Serializable;

/**
 * @hibernate.class table="field_enumeration"  lazy="false"
 * mutable="true"
 */
public class FieldEnumeration implements Identifiable, org.cidrz.webapp.dynasite.valueobject.Auditable, DisplayOrderable, NumericValueOrderable, Configuration, Serializable {
    private Long id;
    private FormField field;
    private Long fieldId;
    private String enumeration;
    private String numericValue;
    private boolean enabled;
    private AuditInfo auditInfo;
    private String concatEnumNumeric;
    private Integer displayOrder = new Integer(Integer.MAX_VALUE);


    public FieldEnumeration() {
    }

    public FieldEnumeration(Long id, FormField field, Long fieldId, String enumeration, String numericValue, boolean enabled, AuditInfo auditInfo, String concatEnumNumeric, Integer displayOrder) {
        this.id = id;
        this.field = field;
        this.fieldId = fieldId;
        this.enumeration = enumeration;
        this.numericValue = numericValue;
        this.enabled = enabled;
        this.auditInfo = auditInfo;
        this.concatEnumNumeric = concatEnumNumeric;
        this.displayOrder = displayOrder;
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
     * @hibernate.many-to-one column="field_id"
     * cascade="none"
     */
    public FormField getField() {
        return field;
    }

    public void setField(FormField field) {
        this.field = field;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    /**
     * @return
     * @hibernate.property column="enumeration"
     * not-null="true"
     */
    public String getEnumeration() {
        return enumeration;
    }

    public void setEnumeration(String enumeration) {
        this.enumeration = enumeration;
    }

    /**
     * @return
     * @hibernate.property column="numeric_value"
     * not-null="false"
     */
    public String getNumericValue() {
        return numericValue;
    }

    public void setNumericValue(String numericValue) {
        this.numericValue = numericValue;
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
     * @return
     * @hibernate.property column="display_order"
     */
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getConcatEnumNumeric() {
        return concatEnumNumeric;
    }

    public void setConcatEnumNumeric(String concatEnumNumeric) {
        this.concatEnumNumeric = concatEnumNumeric;
    }

}
