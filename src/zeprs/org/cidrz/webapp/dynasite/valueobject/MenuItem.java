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

import org.cidrz.webapp.dynasite.valueobject.Identifiable;
import org.cidrz.webapp.dynasite.valueobject.Auditable;
import org.cidrz.webapp.dynasite.valueobject.AuditInfo;
import org.cidrz.webapp.dynasite.valueobject.Configuration;


/**
 * @hibernate.class table="menu_item"
 * mutable="true"
 */
public class MenuItem implements Identifiable, Auditable, Configuration {
    public static final String TYPE_MENU = "MENU";
    public static final String TYPE_ENCOUNTER = "ENCOUNTER";
    public static final String TYPE_FORWARD = "FORWARD";
    public static final String TYPE_LINK = "LINK";
    public static final String TYPE_SEARCH = "SEARCH";

    private Long id;
    private Long parentId;
    private String label;
    private String description;
    private String type;
    private Long targetId;
    private String textTarget;
    private Integer displayOrder;
    private boolean requirePatient;
    private boolean enabled;
    private String role;
    private AuditInfo auditInfo;

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
     * @hibernate.property column="target_id"
     * not-null="false"
     */
    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    /**
     * @return
     * @hibernate.property column="text_target"
     * not-null="false"
     */
    public String getTextTarget() {
        return textTarget;
    }

    public void setTextTarget(String textTarget) {
        this.textTarget = textTarget;
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
     * @return
     * @hibernate.property column="role"
     * not-null="false"
     */
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
     * @hibernate.property column="parent_id"
     * not-null="false"
     */
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * @return
     * @hibernate.property column="description"
     * not-null="false"
     */

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
