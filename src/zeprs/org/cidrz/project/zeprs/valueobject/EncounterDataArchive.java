/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.project.zeprs.valueobject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.valueobject.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @hibernate.class table="encounter_archive"
 * mutable="true"
 */
public class EncounterDataArchive implements Identifiable, Auditable, Recordable {
    /**
     * Commons Logging instance.
     */
    private transient Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    private Long id;
    private Long patientId;
    private Long formId;
    private AuditInfo auditInfo;
    private Long flowId;
    private Date dateVisit;
    private Long pregnancyId;
    private transient Map encounterMap = new LinkedHashMap();
    private String formName;
    // recordables
    private Timestamp lastModified;
    private Timestamp created;
    private String lastModifiedBy;
    private String createdBy;
    private Long siteId;
    private String zeprsId;     // used in reports
    private String surname;     // used in reports
    private String firstName;   // used in reports
    private Long parentId;        // used in reports
    private List babies;     // used in reports
    private String staffName;    // used in reports
    private Long referralId;    // discharge
    private String siteName;
    private String createdByName;
    private String lastModifiedByName;
    private SessionPatient sessionPatient;  // used in rule processing
    private Long createdSiteId;
    private Long importEncounterId;
    private Timestamp dateDeleted;
    private String deletedBy;
    private String uuid;	//uuid of encounter


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
     * @hibernate.property column="form_id"
     */
    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }


    public EncounterDataArchive() {
        super();
    }


    public Long getFormFieldId() {
        return null;
    }


    /**
     * @hibernate.component class="org.cidrz.webapp.dynasite.valueobject.AuditInfo"
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
     * @hibernate.property column="flow_id"
     * not-null="true"
     */

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    /**
     * @return
     * @hibernate.property column="date_visit"
     * not-null="true"
     */

    public Date getDateVisit() {
        return dateVisit;
    }

    public void setDateVisit(Date dateVisit) {
        this.dateVisit = dateVisit;
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
     * This is useful for storing the results of getEncounterMap
     *
     * @return map of resolved values
     */

    public Map getEncounterMap() {
        return encounterMap;
    }

    public void setEncounterMap(Map encounterMap) {
        this.encounterMap = encounterMap;
    }

    /**
     * Use this when displaying patient record
     *
     * @return formName
     */
    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    // Recordables
    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getZeprsId() {
        return zeprsId;
    }

    public void setZeprsId(String zeprsId) {
        this.zeprsId = zeprsId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List getBabies() {
        return babies;
    }

    public void setBabies(List babies) {
        this.babies = babies;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    /**
     * @return
     * @hibernate.property column="referral_id"
     */

    public Long getReferralId() {
        return referralId;
    }

    public void setReferralId(Long referralId) {
        this.referralId = referralId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getLastModifiedByName() {
        return lastModifiedByName;
    }

    public void setLastModifiedByName(String lastModifiedByName) {
        this.lastModifiedByName = lastModifiedByName;
    }

    public SessionPatient getSessionPatient() {
        return sessionPatient;
    }

    public void setSessionPatient(SessionPatient sessionPatient) {
        this.sessionPatient = sessionPatient;
    }

    /**
     * @return
     * @hibernate.property column="created_site_id"
     */
    public Long getCreatedSiteId() {
        return createdSiteId;
    }

    public void setCreatedSiteId(Long createdSiteId) {
        this.createdSiteId = createdSiteId;
    }

    /**
     * @return
     * @hibernate.property column="import_encounter_id"
     */
    public Long getImportEncounterId() {
        return importEncounterId;
    }

    public void setImportEncounterId(Long importEncounterId) {
        this.importEncounterId = importEncounterId;
    }

    /**
     * @return
     * @hibernate.property column="date_deleted"
     */
    public Timestamp getDateDeleted() {
        return dateDeleted;
    }


    public void setDateDeleted(Timestamp dateDeleted) {
        this.dateDeleted = dateDeleted;
    }

    /**
     * @return
     * @hibernate.property column="deleted_by"
     */
    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
