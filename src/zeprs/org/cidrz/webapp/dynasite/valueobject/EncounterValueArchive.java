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

import org.cidrz.webapp.dynasite.valueobject.Auditable;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;

import java.sql.Timestamp;
import java.sql.Date;

/**
 * @hibernate.class table="encounter_value_archive"
 * mutable="true"
 * lazy="true
 */
public class EncounterValueArchive implements Identifiable {
    private Long id;
    private Long encounterId;
    private Long pageItemId;
    private String value;
    private String previousValue;
    private Long patientId;
    private Long pregnancyId;
    private Long fieldId;
    // private AuditInfo auditInfo;
    private Timestamp lastModified;
    private Timestamp created;
    private String lastModifiedBy;
    private String createdBy;
    private Long siteId;
    private Long formId;
    private String formLabel;
    private Date dateVisit;
    private String patientName;
    private String ModUserName;
    private String uuid;
    private String encounterUuid;


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
     * @hibernate.property column="encounter_id"
     * not-null="true"
     */
    public Long getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(Long encounterId) {
        this.encounterId = encounterId;
    }


    /**
     * @return
     * @hibernate.property column="page_item_id"
     * not-null="true"
     */
    public Long getPageItemId() {
        return pageItemId;
    }

    public void setPageItemId(Long pageItemId) {
        this.pageItemId = pageItemId;
    }

    /**
     * @return
     * @hibernate.property column="value"
     * not-null="true"
     */
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return
     * @hibernate.property column="previous_value"
     * not-null="true"
     */
    public String getPreviousValue() {
        return previousValue;
    }

    public void setPreviousValue(String previousValue) {
        this.previousValue = previousValue;
    }

    /**
     * @return
     * @hibernate.property column="patient_id"
     */
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    /**
     * @return
     * @hibernate.property column="pregnancy_id"
     */
    public Long getPregnancyId() {
        return pregnancyId;
    }

    public void setPregnancyId(Long pregnancyId) {
        this.pregnancyId = pregnancyId;
    }

    /**
     * @return
     * @hibernate.property column="field_id"
     * not-null="true"
     */

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    /**
     * @hibernate.property column="last_modified"
     */
    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * @hibernate.property column="created"
     */
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    /**
     * @hibernate.property column="last_modified_by"
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * @hibernate.property column="created_by"
     */
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @hibernate.property column="site_id"
     */
    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public String getFormLabel() {
        return formLabel;
    }

    public void setFormLabel(String formLabel) {
        this.formLabel = formLabel;
    }

    public Date getDateVisit() {
        return dateVisit;
    }

    public void setDateVisit(Date dateVisit) {
        this.dateVisit = dateVisit;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getModUserName() {
        return ModUserName;
    }

    public void setModUserName(String modUserName) {
        ModUserName = modUserName;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final EncounterValueArchive that = (EncounterValueArchive) o;

        if (ModUserName != null ? !ModUserName.equals(that.ModUserName) : that.ModUserName != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (dateVisit != null ? !dateVisit.equals(that.dateVisit) : that.dateVisit != null) return false;
        if (fieldId != null ? !fieldId.equals(that.fieldId) : that.fieldId != null) return false;
        if (formId != null ? !formId.equals(that.formId) : that.formId != null) return false;
        if (formLabel != null ? !formLabel.equals(that.formLabel) : that.formLabel != null) return false;
        if (lastModified != null ? !lastModified.equals(that.lastModified) : that.lastModified != null) return false;
        if (lastModifiedBy != null ? !lastModifiedBy.equals(that.lastModifiedBy) : that.lastModifiedBy != null)
            return false;
        if (pageItemId != null ? !pageItemId.equals(that.pageItemId) : that.pageItemId != null) return false;
        if (patientName != null ? !patientName.equals(that.patientName) : that.patientName != null) return false;
        if (previousValue != null ? !previousValue.equals(that.previousValue) : that.previousValue != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (pageItemId != null ? pageItemId.hashCode() : 0);
        result = 29 * result + (value != null ? value.hashCode() : 0);
        result = 29 * result + (previousValue != null ? previousValue.hashCode() : 0);
        result = 29 * result + (fieldId != null ? fieldId.hashCode() : 0);
        result = 29 * result + (lastModified != null ? lastModified.hashCode() : 0);
        result = 29 * result + (created != null ? created.hashCode() : 0);
        result = 29 * result + (lastModifiedBy != null ? lastModifiedBy.hashCode() : 0);
        result = 29 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 29 * result + (formId != null ? formId.hashCode() : 0);
        result = 29 * result + (formLabel != null ? formLabel.hashCode() : 0);
        result = 29 * result + (dateVisit != null ? dateVisit.hashCode() : 0);
        result = 29 * result + (patientName != null ? patientName.hashCode() : 0);
        result = 29 * result + (ModUserName != null ? ModUserName.hashCode() : 0);
        return result;
    }

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getEncounterUuid() {
		return encounterUuid;
	}

	public void setEncounterUuid(String encounterUuid) {
		this.encounterUuid = encounterUuid;
	}

}