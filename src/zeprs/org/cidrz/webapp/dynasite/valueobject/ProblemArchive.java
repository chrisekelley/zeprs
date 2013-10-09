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

import org.cidrz.webapp.dynasite.utils.AuditInfoLastModifiedComparator;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;
import java.util.TreeSet;

/**
 * @hibernate.class table="problem_archive"
 * lazy="true
 */

public class ProblemArchive extends AuditInfoLastModifiedComparator implements Identifiable, Auditable, Recordable {

    private Long id;
    private Long patientId;
    private String problemName;
    private boolean active = true;
    private Date onsetDate;
    private Set comments = new TreeSet();
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
    private Timestamp dateDeleted;
    private String deletedBy;
    private String uuid;	//uuid of problem
    private String patientUuid;
    private String pregnancyUuid;

    public ProblemArchive() {
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

    /**
     * @return
     * @hibernate.set inverse="true"
     * lazy="true"
     * cascade="all-delete-orphan"
     * order-by="created desc"
     * @hibernate.key column="problem_id"
     * @hibernate.one-to-many class="org.cidrz.webapp.dynasite.valueobject.Comment"
     */

    public Set getComments() {
        return comments;
    }

    public void setComments(Set comments) {
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
     * @return comment
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
