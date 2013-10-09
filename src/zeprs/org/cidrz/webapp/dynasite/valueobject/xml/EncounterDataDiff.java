/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.valueobject.xml;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.valueobject.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class EncounterDataDiff implements Auditable, Recordable, SessionPatientRecord {
    /**
     * Commons Logging instance.
     */
    private transient Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

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
    private List outcomes;  // used in xml record generation when deleting an encounter.

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }


    public EncounterDataDiff() {
        super();
    }


    public Long getFormFieldId() {
        return null;
    }

    public AuditInfo getAuditInfo() {
        if (auditInfo == null) {
            auditInfo = new AuditInfo();
        }
        return auditInfo;
    }

    public void setAuditInfo(AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public Date getDateVisit() {
        return dateVisit;
    }

    public void setDateVisit(Date dateVisit) {
        this.dateVisit = dateVisit;
    }

    public Long getPregnancyId() {
        return pregnancyId;
    }

    public void setPregnancyId(Long pregnancyId) {
        this.pregnancyId = pregnancyId;
    }

    public Map getEncounterMap() {
        return encounterMap;
    }

    public void setEncounterMap(Map encounterMap) {
        this.encounterMap = encounterMap;
    }

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

    public Long getCreatedSiteId() {
        return createdSiteId;
    }

    public void setCreatedSiteId(Long createdSiteId) {
        this.createdSiteId = createdSiteId;
    }

    public Long getImportEncounterId() {
        return importEncounterId;
    }

    public void setImportEncounterId(Long importEncounterId) {
        this.importEncounterId = importEncounterId;
    }

    public List getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(List outcomes) {
        this.outcomes = outcomes;
    }

    public boolean isMotherHivPositive() {
        if (sessionPatient != null) {
            return sessionPatient.isMotherHivPositive();
        } else {
            return false;
        }
    }
}
