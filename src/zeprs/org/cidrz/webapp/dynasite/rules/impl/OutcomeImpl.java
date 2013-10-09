/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.rules.impl;

import org.cidrz.project.zeprs.valueobject.BaseEncounter;
import org.cidrz.webapp.dynasite.rules.Outcome;
import org.cidrz.webapp.dynasite.valueobject.AuditInfo;
import org.cidrz.webapp.dynasite.valueobject.Auditable;
import org.cidrz.webapp.dynasite.valueobject.Recordable;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


public class OutcomeImpl extends Outcome implements Auditable, Recordable {
    private Long id;
    private Long encounterId;
    private Long patientId;
    private boolean active = true;
    private AuditInfo auditInfo;
    //private Set comments = new TreeSet();
    private List comments;
    private Long pregnancyId;
    private Long formId;
    private Long formFieldId;
    private Long ruleDefinitionId;
    private String outcomeType;
    private Integer requiredFormId;
    private String message;
    private String reason;
    private String priority;
    private String transport;
    private Date uthAdmissionDate;
    private String arrivalCondition;
    private Integer wardId;
    private Boolean longTermProblem;
    // recordables
    private Timestamp lastModified;
    private Timestamp created;
    private String lastModifiedBy;
    private String lastModifiedByName;
    private String createdBy;
    private Long siteId;
    // additional
    private Long dischargeId;
    private Date dischargeDate;
    private Long referringSiteId;
    private String referringSiteName;
    private String patientName;
    private Long importOutcomeId;
    private Long importEncounterId;
    private String firm;
    private String encounterUuid;
    private String patientUuid;
    private String pregnancyUuid;
    private String outcomeUuid;


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public Long getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(Long encounterId) {
        this.encounterId = encounterId;
    }


    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public void setEncounter(BaseEncounter encounter) {
        this.encounterId = encounter.getId();
        this.patientId = encounter.getPatientId();
        this.formId = encounter.getFormId();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public List getComments() {
        return comments;
    }

    public void setComments(List comments) {
        this.comments = comments;
    }


    public Long getPregnancyId() {
        return pregnancyId;
    }

    public void setPregnancyId(Long pregnancyId) {
        this.pregnancyId = pregnancyId;
    }


    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }


    public Long getFormFieldId() {
        return formFieldId;
    }

    public void setFormFieldId(Long formFieldId) {
        this.formFieldId = formFieldId;
    }


    public Long getRuleDefinitionId() {
        return ruleDefinitionId;
    }

    public void setRuleDefinitionId(Long ruleDefinitionId) {
        this.ruleDefinitionId = ruleDefinitionId;
    }

    public String getOutcomeType() {
        return outcomeType;
    }

    public void setOutcomeType(String outcomeType) {
        this.outcomeType = outcomeType;
    }

    public Integer getRequiredFormId() {
        return requiredFormId;
    }

    public void setRequiredFormId(Integer requiredFormId) {
        this.requiredFormId = requiredFormId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

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

    public Long getDischargeId() {
        return dischargeId;
    }

    public void setDischargeId(Long dischargeId) {
        this.dischargeId = dischargeId;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public Date getUthAdmissionDate() {
        return uthAdmissionDate;
    }

    public void setUthAdmissionDate(Date uthAdmissionDate) {
        this.uthAdmissionDate = uthAdmissionDate;
    }

    public String getArrivalCondition() {
        return arrivalCondition;
    }

    public void setArrivalCondition(String arrivalCondition) {
        this.arrivalCondition = arrivalCondition;
    }

    public Integer getWardId() {
        return wardId;
    }

    public void setWardId(Integer wardId) {
        this.wardId = wardId;
    }

    public Long getReferringSiteId() {
        return referringSiteId;
    }

    public void setReferringSiteId(Long referringSiteId) {
        this.referringSiteId = referringSiteId;
    }

    public String getReferringSiteName() {
        return referringSiteName;
    }

    public void setReferringSiteName(String referringSiteName) {
        this.referringSiteName = referringSiteName;
    }

    public Boolean getLongTermProblem() {
        return longTermProblem;
    }

    public void setLongTermProblem(Boolean longTermProblem) {
        this.longTermProblem = longTermProblem;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Long getImportOutcomeId() {
        return importOutcomeId;
    }

    public void setImportOutcomeId(Long importOutcomeId) {
        this.importOutcomeId = importOutcomeId;
    }

    public Long getImportEncounterId() {
        return importEncounterId;
    }

    public void setImportEncounterId(Long importEncounterId) {
        this.importEncounterId = importEncounterId;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getLastModifiedByName() {
        return lastModifiedByName;
    }

    public void setLastModifiedByName(String lastModifiedByName) {
        this.lastModifiedByName = lastModifiedByName;
    }

	public String getEncounterUuid() {
		return encounterUuid;
	}

	public void setEncounterUuid(String encounterUuid) {
		this.encounterUuid = encounterUuid;
	}

	public String getPatientUuid() {
		return patientUuid;
	}

	public void setPatientUuid(String patientUuid) {
		this.patientUuid = patientUuid;
	}

	public String getPregnancyUuid() {
		return pregnancyUuid;
	}

	public void setPregnancyUuid(String pregnancyUuid) {
		this.pregnancyUuid = pregnancyUuid;
	}

	public String getOutcomeUuid() {
		return outcomeUuid;
	}

	public void setOutcomeUuid(String outcomeUuid) {
		this.outcomeUuid = outcomeUuid;
	}

}
