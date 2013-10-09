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

import org.cidrz.webapp.dynasite.rules.Outcome;

import java.sql.Timestamp;

/**
 * Each problem has a set of comments.
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Oct 24, 2004
 * Time: 4:10:08 PM
 */

/**
 * @hibernate.class table="comment"
 * mutable="true"
 */
public class Comment implements Identifiable, Auditable, Recordable {

    private Long id;
    private Patient patient;
    private Long patientId;
    private Problem problem;
    private Long problemId;
    private String commentText;
    private String actionPlan;
    private AuditInfo auditInfo;
    private Outcome outcome;
    private Long outcomeId;
    private Long encounterId;
    private Pregnancy pregnancy;
    private Long pregnancyId;
    // recordables
    private Timestamp lastModified;
    private Timestamp created;
    private String lastModifiedBy;
    private String createdBy;
    private Long siteId;
    private Long importCommentId;
    private String lastModifiedByName;
    private String uuid;
    private String encounterUuid;
    private String patientUuid;
    private String pregnancyUuid;
    private String problemUuid;
    private String outcomeUuid;

    public Comment() {
        super();
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
     * @hibernate.many-to-one column="patient_id"
     * cascade="none"
     */

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * @return
     * @hibernate.many-to-one column="problem_id"
     * cascade="none"
     */

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    /**
     * @return
     * @hibernate.property
     * @hibernate.column name="comment_text" sql-type="text"
     * not-null="true"
     */

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    /**
     * @return
     * @hibernate.property
     * @hibernate.column name="action_plan" sql-type="text"
     */

    public String getActionPlan() {
        return actionPlan;
    }

    public void setActionPlan(String actionPlan) {
        this.actionPlan = actionPlan;
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
     * @hibernate.many-to-one column="outcome_id"
     * cascade="none"
     */

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    /**
     * @return
     * @hibernate.many-to-one column="pregnancy_id"
     * cascade="none"
     */

    public Pregnancy getPregnancy() {
        return pregnancy;
    }

    public void setPregnancy(Pregnancy pregnancy) {
        this.pregnancy = pregnancy;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    // recordables

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

    public Long getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(Long encounterId) {
        this.encounterId = encounterId;
    }

    public Long getPregnancyId() {
        return pregnancyId;
    }

    public void setPregnancyId(Long pregnancyId) {
        this.pregnancyId = pregnancyId;
    }

    public Long getOutcomeId() {
        return outcomeId;
    }

    public void setOutcomeId(Long outcomeId) {
        this.outcomeId = outcomeId;
    }


    public Long getImportCommentId() {
        return importCommentId;
    }

    public void setImportCommentId(Long importCommentId) {
        this.importCommentId = importCommentId;
    }

    public String getLastModifiedByName() {
        return lastModifiedByName;
    }

    public void setLastModifiedByName(String lastModifiedByName) {
        this.lastModifiedByName = lastModifiedByName;
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

	public String getProblemUuid() {
		return problemUuid;
	}

	public void setProblemUuid(String problemUuid) {
		this.problemUuid = problemUuid;
	}

	public String getOutcomeUuid() {
		return outcomeUuid;
	}

	public void setOutcomeUuid(String outcomeUuid) {
		this.outcomeUuid = outcomeUuid;
	}
}
