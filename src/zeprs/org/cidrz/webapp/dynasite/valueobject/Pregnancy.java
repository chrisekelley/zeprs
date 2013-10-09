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

import org.cidrz.project.zeprs.valueobject.EncounterData;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Jan 24, 2005
 * Time: 8:51:07 AM
 */

/**
 * @hibernate.class table="pregnancy"  lazy="false"
 * mutable="true"
 */

public class Pregnancy implements org.cidrz.webapp.dynasite.valueobject.Identifiable, Auditable, Recordable {

    private Long id;
    private Long patientId;
    private Date datePregnancyBegin;
    // private EncounterData pregnancyBeginEncounter;
    private Long pregnancyBeginEncounterId;
    private String pregnancyBeginEncounterUuid;	// unique identifier for the pregnancyBeginEncounter.
    private Date datePregnancyEnd;
    //private EncounterData pregnancyEndEncounter;
    private Long pregnancyEndEncounterId;
    private String pregnancyEndEncounterUuid;	// unique identifier for the pregnancyEndEncounter.
    private AuditInfo auditInfo;
    private List encounters;
    private List activeProblems;
    private List inActiveProblems;
    private Long labourAdmissionEncounterId;
    private String labourAdmissionEncounterUuid;	// unique identifier for the labourAdmissionEncounter.
    private Date dateLabourAdmission;
    private List modifiedValues;   // List of values from encounter_value_archive
    private List infants = new ArrayList();   // list of infants and infant records for this pregnancy
    private Partograph partograph;
    private Map encounterDateMap;   // used in xml record output. Map of importEncounterId:lastModified
    private Long importPregnancyId;	// This is really not used much anymore now that we've got uuid's.
    private String uuid;	// unique identifier for the pregnancy.
    // recordables
    private Timestamp lastModified;
    private Timestamp created;
    private String lastModifiedBy;
    private String createdBy;
    private Long siteId;
    private Long createdSiteId;	// used for xml import process



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
     * @hibernate.property
     * @hibernate.column name="date_pregnancy_begin"
     */
    public Date getDatePregnancyBegin() {
        return datePregnancyBegin;
    }

    public void setDatePregnancyBegin(Date datePregnancyBegin) {
        this.datePregnancyBegin = datePregnancyBegin;
    }

    /**
     * @return
     * @hibernate.property
     * @hibernate.column name="date_pregnancy_end"
     */
    public Date getDatePregnancyEnd() {
        return datePregnancyEnd;
    }

    public void setDatePregnancyEnd(Date datePregnancyEnd) {
        this.datePregnancyEnd = datePregnancyEnd;
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
     * @return List of encounters in this pregnancy
     */
    public List getEncounters() {
        return encounters;
    }

    public void setEncounters(List encounters) {
        this.encounters = encounters;
    }

    public List getActiveProblems() {
        return activeProblems;
    }

    public void setActiveProblems(List activeProblems) {
        this.activeProblems = activeProblems;
    }

    public List getInActiveProblems() {
        return inActiveProblems;
    }

    public void setInActiveProblems(List inActiveProblems) {
        this.inActiveProblems = inActiveProblems;
    }

	/**
     * @return Date of labour admission
     * @hibernate.property
     * @hibernate.column name="date_labour_admission"
     */
    public Date getDateLabourAdmission() {
        return dateLabourAdmission;
    }

    public void setDateLabourAdmission(Date dateLabourAdmission) {
        this.dateLabourAdmission = dateLabourAdmission;
    }

    public List getModifiedValues() {
        return modifiedValues;
    }

    public void setModifiedValues(List modifiedValues) {
        this.modifiedValues = modifiedValues;
    }

    public List getInfants() {
        return infants;
    }

    public void setInfants(List infants) {
        this.infants = infants;
    }

    public Partograph getPartograph() {
        return partograph;
    }

    public void setPartograph(Partograph partograph) {
        this.partograph = partograph;
    }

    public Map getEncounterDateMap() {
        return encounterDateMap;
    }

    public void setEncounterDateMap(Map encounterDateMap) {
        this.encounterDateMap = encounterDateMap;
    }

    /**
     * @return imported pregnancy id
     * @hibernate.property
     * @hibernate.column name="import_pregnancy_id"
     */
    public Long getImportPregnancyId() {
        return importPregnancyId;
    }

    public void setImportPregnancyId(Long importPregnancyId) {
        this.importPregnancyId = importPregnancyId;
    }

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	public Long getCreatedSiteId() {
		return createdSiteId;
	}

	public void setCreatedSiteId(Long createdSiteId) {
		this.createdSiteId = createdSiteId;
	}

	public String getPregnancyBeginEncounterUuid() {
		return pregnancyBeginEncounterUuid;
	}

	public void setPregnancyBeginEncounterUuid(String pregnancyBeginEncounterUuid) {
		this.pregnancyBeginEncounterUuid = pregnancyBeginEncounterUuid;
	}

	public String getPregnancyEndEncounterUuid() {
		return pregnancyEndEncounterUuid;
	}

	public void setPregnancyEndEncounterUuid(String pregnancyEndEncounterUuid) {
		this.pregnancyEndEncounterUuid = pregnancyEndEncounterUuid;
	}

	public String getLabourAdmissionEncounterUuid() {
		return labourAdmissionEncounterUuid;
	}

	public void setLabourAdmissionEncounterUuid(String labourAdmissionEncounterUuid) {
		this.labourAdmissionEncounterUuid = labourAdmissionEncounterUuid;
	}

	/**
     * @return Id of pregnancy_begin_encounter
     * @hibernate.property
     * @hibernate.column name="pregnancy_begin_encounter_id"
     */
	public Long getPregnancyBeginEncounterId() {
		return pregnancyBeginEncounterId;
	}

	public void setPregnancyBeginEncounterId(Long pregnancyBeginEncounterId) {
		this.pregnancyBeginEncounterId = pregnancyBeginEncounterId;
	}

	/**
     * @return Id of pregnancy_end_encounter
     * @hibernate.property
     * @hibernate.column name="pregnancy_end_encounter_id"
     */
	public Long getPregnancyEndEncounterId() {
		return pregnancyEndEncounterId;
	}

	public void setPregnancyEndEncounterId(Long pregnancyEndEncounterId) {
		this.pregnancyEndEncounterId = pregnancyEndEncounterId;
	}


	/**
     * @return Id of labour_admission_encounter
     * @hibernate.property
     * @hibernate.column name="labour_admission_encounter_id"
     */
	public Long getLabourAdmissionEncounterId() {
		return labourAdmissionEncounterId;
	}

	public void setLabourAdmissionEncounterId(Long labourAdmissionEncounterId) {
		this.labourAdmissionEncounterId = labourAdmissionEncounterId;
	}



}
