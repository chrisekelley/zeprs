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
import org.cidrz.webapp.dynasite.valueobject.AuditInfo;
import org.cidrz.webapp.dynasite.utils.AuditInfoLastModifiedComparator;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Each patient has a set of problems.
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Oct 24, 2004
 * Time: 3:56:18 PM
 */

/**
 * @hibernate.class table="problem"
 * mutable="true"
 * lazy="true
 */

public class Problem extends AuditInfoLastModifiedComparator implements Identifiable, org.cidrz.webapp.dynasite.valueobject.Auditable, Recordable {

    private Long id;
    private Long patientId;
    private String problemName;
    private boolean active = true;
    private Date onsetDate;
    private List comments;
    private AuditInfo auditInfo;
    private Long pregnancyId;
    private Timestamp lastModified;
    private Timestamp created;
    private String lastModifiedBy;
    private String createdBy;
    private Long siteId;
    private Comment comment;
    private Long importProblemId;
    private String lastModifiedByName;
    private String uuid;
    private String patientUuid;
    private String pregnancyUuid;

    public Problem() {
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
     * @hibernate.property column="problemName"
     * not-null="true"
     */

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    /**
     * @return
     * @hibernate.property column="active"
     */
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return
     * @hibernate.property column="onset_date"
     * not-null="true"
     */
    public Date getOnsetDate() {
        return onsetDate;
    }

    public void setOnsetDate(Date onsetDate) {
        this.onsetDate = onsetDate;
    }

    public List getComments() {
        return comments;
    }

    public void setComments(List comments) {
        this.comments = comments;
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
     * @hibernate.property column="pregnancy_id"
     */

    public Long getPregnancyId() {
        return pregnancyId;
    }

    public void setPregnancyId(Long pregnancyId) {
        this.pregnancyId = pregnancyId;
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

    /**
     * This is used when creating a new Problem - stores the small bit of comment data - action/plan
     * @return
     */
    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    /**
     * @return
     * @hibernate.property column="import_problem_id"
     */
    public Long getImportProblemId() {
        return importProblemId;
    }

    public void setImportProblemId(Long importProblemId) {
        this.importProblemId = importProblemId;
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

}
