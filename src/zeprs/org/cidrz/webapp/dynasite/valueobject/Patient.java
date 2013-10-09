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

import org.apache.struts.validator.DynaValidatorForm;
import org.cidrz.project.zeprs.valueobject.BaseEncounter;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.project.zeprs.valueobject.gen.PatientRegistration;
import org.cidrz.project.zeprs.valueobject.report.PatientStatusReport;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.exception.PopulationException;
import org.cidrz.webapp.dynasite.logic.PersistenceManagerFactory;
import org.cidrz.webapp.dynasite.utils.BeanPopulator;

import java.security.Principal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

/**
 * @hibernate.class table="patient"
 * lazy="true"
 * mutable="true"
 */

public class Patient implements Identifiable, Auditable {

    private Long id;
    private String firstName;
    private String surname;
    private String nrcNumber;
    private String districtPatientid;
    private Address address;
   //  private Set encounters = new HashSet();
    private Set encounterData = new HashSet();
    private Set outcomes = new HashSet();
    private AuditInfo auditInfo;
    private String obstetricRecordnumber;
    private TaskList taskList;
    //private Set trackingData = new HashSet();
    private Set children = new TreeSet();
    private List childrenThisPregnancy;    // children in current pregnancy only.
    private Long parent;
    private Long parentId;
    private String parentDistrictPatientid;
    private Integer sex;
    private Integer ageAtFirstVisit;
    private java.sql.Date birthdate;
    private Time timeOfBirth;
    private PatientStatusReport patientStatusreport;
    // private Set pregnancies = new TreeSet();
    private Integer sequenceNumber;
    private Long pregnancyId;   // used to distinguish infants born in different pregnancies
    private Boolean dead;
    private Byte hivPositive; // 0 - NR, 1 - R, 2 - Indeterminate
    private Date dateDeath;
    private Long encounterIdDeath;
    private Integer height;
    private SessionPatient sessionPatient;  // used for XML records
    private List prevPregnancies;  // used for XML records
    private List<Pregnancy> pregnancyList; // used for XML records
    private PatientRegistration patientRegistration;    // used for XML records
    private Long importPatientId;   // patient Id imported
    private String importSite;  // site data imported from
    private Timestamp importDate;    // date imported
    private List encounterRecordChanges;    // used for XML records
    private Integer totalEncounters;    // used for XML records
    private Timestamp lastCreatedEncounter;    //used for XML records
    private List comments;  // used for XML records - comments over the life of the patient.
    private List problems;  // used for XML records - problems over the life of the patient
    private List encounterDeletions;    // used for XML records - list of encounters that have been deleted.
    private List outcomeDeletions;      // used for XML records - list of outcomes that have been deleted.
    private List problemDeletions;      // used for XML records - list of problems that have been deleted.
    private Long siteId;    // site or clinic the patient "belongs" to. This is set in demographics form.
    private Timestamp lastModified;
    private Timestamp created;
    private String lastModifiedBy;
    private String createdBy;
    private String uuid;	// unique identifier for the patient.
    private String parentUuid;	// unique identifier for the parent.

    public Patient() {
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
     * @hibernate.property
     * @hibernate.column name="first_name"
     * not-null="true"
     * index="IDX_FIRST_NAME"
     */
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return
     * @hibernate.property
     * @hibernate.column name="surname"
     * not-null="true"
     * index="IDX_SURNAME"
     */
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return
     * @hibernate.property
     * @hibernate.column name="nrc_number"
     * not-null="false"
     * index="IDX_NRC_NUMBER"
     */
    public String getNrcNumber() {
        return nrcNumber;
    }

    public void setNrcNumber(String nrcNumber) {
        this.nrcNumber = nrcNumber;
    }


    /**
     * @return
     * @hibernate.property
     * @hibernate.column name="district_patient_id"
     * not-null="false"
     * index="IDX_DISTRICT_PATIENT_ID"
     */

    public String getDistrictPatientid() {
        return districtPatientid;
    }

    public void setDistrictPatientid(String districtPatientid) {
        this.districtPatientid = districtPatientid;
    }


    /**
     * @return
     * @hibernate.many-to-one column="address_id"
     * cascade="all"
     */
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return
     * @hibernate.set inverse="true"
     * lazy="true"
     * cascade="save-update"
     * outer-join="false"
     * order-by="created desc"
     * @hibernate.key column="patient_id"
     * @hibernate.one-to-many class="org.cidrz.project.zeprs.valueobject.EncounterData"
     */
    public Set getEncounterData() {
        return encounterData;
    }

    public void setEncounterData(Set encounterData) {
        this.encounterData = encounterData;
    }

    /**
     * @return
     * @hibernate.set inverse="true"
     * lazy="true"
     * cascade="none"
     * order-by="created desc"
     * @hibernate.key column="patient_id"
     * @hibernate.one-to-many class="org.cidrz.webapp.dynasite.rules.Outcome"
     */
    public Set getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(Set outcomes) {
        this.outcomes = outcomes;
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

    /*public Set getTrackingData() {

        //Set trackingData = new ArrayList();
        int field_id = 27;
        int tracking_fields[] = {224, 225,226,228,232,235,242,243,244};
        //List tracking_encounters = new ArrayList();
        Iterator encounters = this.getEncounters().iterator();
        for (int i = 0; i < tracking_fields.length; i++) {
            int tracking_field = tracking_fields[i];
            while (encounters.hasNext()) {
            EncounterRecord encounter = (EncounterRecord) encounters.next();
            List values = new ArrayList(encounter.getEncounterValues());
            EncounterValue value;
            for (int j = 0; j < values.size(); j++) {
                value = (EncounterValue) values.get(j);
                if (value.getField().getId().equals(new Long(tracking_field))) {
                    trackingData.add(value);
                }
            }
        }
        }


        return trackingData;
        // throw new ObjectNotFoundException();
    }*/


    /**
     * @return
     * @hibernate.property column="obstetric_record_number"
     * not-null="false"
     */
    public String getObstetricRecordnumber() {
        return obstetricRecordnumber;
    }

    public void setObstetricRecordnumber(String obstetricRecordnumber) {
        this.obstetricRecordnumber = obstetricRecordnumber;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * @deprecated - use the list instead...
     * @return
     * @hibernate.set inverse="true"
     * lazy="false"
     * cascade="save-update"
     * order-by="created desc"
     * @hibernate.key column="parent_id"
     * @hibernate.one-to-many class="org.cidrz.webapp.dynasite.valueobject.Patient"
     */

    public Set getChildren() {
        return children;
    }

    /**
     * @deprecated - use the list instead...
     * @param children
     */
    public void setChildren(Set children) {
        this.children = children;
    }

    public List getChildrenThisPregnancy() {
        return childrenThisPregnancy;
    }

    public void setChildrenThisPregnancy(List childrenThisPregnancy) {
        this.childrenThisPregnancy = childrenThisPregnancy;
    }

    /**
     * @return
     * @hibernate.many-to-one column="parent_id"
     * class="org.cidrz.webapp.dynasite.valueobject.Patient"
     * cascade="none"
     */
    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }


    // parent_id
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

     /**
     * @return
     * @hibernate.property
     * @hibernate.column name="parent_district_id"
     */
    public String getParentDistrictPatientid() {
        return parentDistrictPatientid;
    }

    public void setParentDistrictPatientid(String parentDistrictPatientid) {
        this.parentDistrictPatientid = parentDistrictPatientid;
    }

    /**
     * @return
     * @hibernate.property
     * @hibernate.column name="sex"
     */
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * @return
     * @hibernate.property
     * @hibernate.column name="age_at_first_visit"
     */
    public Integer getAgeAtFirstVisit() {
        return ageAtFirstVisit;
    }

    public void setAgeAtFirstVisit(Integer ageAtFirstVisit) {
        this.ageAtFirstVisit = ageAtFirstVisit;
    }

    /**
     * @return
     * @hibernate.property
     * @hibernate.column name="birthdate"
     */
    public java.sql.Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * @return
     * @hibernate.property
     * @hibernate.column name="time_of_birth"
     * not-null="false"
     */

    public Time getTimeOfBirth() {
        return timeOfBirth;
    }

    public void setTimeOfBirth(Time timeOfBirth) {
        this.timeOfBirth = timeOfBirth;
    }

    /**
     * @return
     * @hibernate.one-to-one class="org.cidrz.project.zeprs.valueobject.report.PatientStatusReport"
     * cascade="all"
     */

    public PatientStatusReport getPatientStatusreport() {
        return patientStatusreport;
    }

    public void setPatientStatusreport(PatientStatusReport patientStatusreport) {
        this.patientStatusreport = patientStatusreport;
    }

    /**
     * Sequence Number of newborn in case of multiple fetuses
     *
     * @return sequenceNumber
     */
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * Used to distinguish infants born indifferent pregnancies
     * // pregnancy_id
     * @return pregnancyId
     */
    public Long getPregnancyId() {
        return pregnancyId;
    }

    public void setPregnancyId(Long pregnancyId) {
        this.pregnancyId = pregnancyId;
    }

    /**
     * Get this value from Maternal discharge Summary - Maternal death selected in Disposition field
     *
     * @return
     * @hibernate.property
     * @hibernate.column name="dead"
     * not-null="false"
     */
    public Boolean getDead() {
        return dead;
    }

    public void setDead(Boolean dead) {
        this.dead = dead;
    }

    /**
     * @return
     * @hibernate.property
     * @hibernate.column name="hiv_positive"
     * not-null="false"
     */

    public Byte getHivPositive() {
        return hivPositive;
    }

    public void setHivPositive(Byte hivPositive) {
        this.hivPositive = hivPositive;
    }

    /**
     * @return
     * @hibernate.property
     * @hibernate.column name="date_death"
     * not-null="false"
     */
    public Date getDateDeath() {
        return dateDeath;
    }

    public void setDateDeath(Date dateDeath) {
        this.dateDeath = dateDeath;
    }

    /**
     * @return
     * @hibernate.property
     * @hibernate.column name="death_encounter_id"
     * not-null="false"
     */
    public Long getEncounterIdDeath() {
        return encounterIdDeath;
    }

    public void setEncounterIdDeath(Long encounterIdDeath) {
        this.encounterIdDeath = encounterIdDeath;
    }

    /**
     * @return
     * @hibernate.property
     * @hibernate.column name="height"
     * not-null="false"
     */

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public SessionPatient getSessionPatient() {
        return sessionPatient;
    }

    public void setSessionPatient(SessionPatient sessionPatient) {
        this.sessionPatient = sessionPatient;
    }

    public List getPrevPregnancies() {
        return prevPregnancies;
    }

    public void setPrevPregnancies(List prevPregnancies) {
        this.prevPregnancies = prevPregnancies;
    }

    public List<Pregnancy> getPregnancyList() {
		return pregnancyList;
	}

	public void setPregnancyList(List<Pregnancy> pregnancyList) {
		this.pregnancyList = pregnancyList;
	}

	public PatientRegistration getPatientRegistration() {
        return patientRegistration;
    }

    public void setPatientRegistration(PatientRegistration patientRegistration) {
        this.patientRegistration = patientRegistration;
    }

    /**
     * @return
     * @hibernate.property
     * @hibernate.column name="import_patient_id"
     * not-null="false"
     */
    public Long getImportPatientId() {
        return importPatientId;
    }

    public void setImportPatientId(Long importPatientId) {
        this.importPatientId = importPatientId;
    }

    /**
     * @return
     * @hibernate.property
     * @hibernate.column name="import_site"
     * not-null="false"
     */
    public String getImportSite() {
        return importSite;
    }

    public void setImportSite(String importSite) {
        this.importSite = importSite;
    }

    /**
     * @return
     * @hibernate.property
     * @hibernate.column name="import_date"
     * not-null="false"
     */
    public Timestamp getImportDate() {
        return importDate;
    }

    public void setImportDate(Timestamp importDate) {
        this.importDate = importDate;
    }

    public List getEncounterRecordChanges() {
        return encounterRecordChanges;
    }

    public void setEncounterRecordChanges(List encounterRecordChanges) {
        this.encounterRecordChanges = encounterRecordChanges;
    }

    public Integer getTotalEncounters() {
        return totalEncounters;
    }

    public void setTotalEncounters(Integer totalEncounters) {
        this.totalEncounters = totalEncounters;
    }

    public Timestamp getLastCreatedEncounter() {
        return lastCreatedEncounter;
    }

    public void setLastCreatedEncounter(Timestamp lastCreatedEncounter) {
        this.lastCreatedEncounter = lastCreatedEncounter;
    }

    public List getComments() {
        return comments;
    }

    public void setComments(List comments) {
        this.comments = comments;
    }

    public List getProblems() {
        return problems;
    }

    public void setProblems(List problems) {
        this.problems = problems;
    }

    public List getEncounterDeletions() {
        return encounterDeletions;
    }

    public void setEncounterDeletions(List encounterDeletions) {
        this.encounterDeletions = encounterDeletions;
    }

    public List getOutcomeDeletions() {
        return outcomeDeletions;
    }

    public void setOutcomeDeletions(List outcomeDeletions) {
        this.outcomeDeletions = outcomeDeletions;
    }

    public List getProblemDeletions() {
        return problemDeletions;
    }

    public void setProblemDeletions(List problemDeletions) {
        this.problemDeletions = problemDeletions;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
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

    public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getParentUuid() {
		return parentUuid;
	}

	public void setParentUuid(String parentUuid) {
		this.parentUuid = parentUuid;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Patient patient = (Patient) o;

        if (address != null ? !address.equals(patient.address) : patient.address != null) return false;
        if (ageAtFirstVisit != null ? !ageAtFirstVisit.equals(patient.ageAtFirstVisit) : patient.ageAtFirstVisit != null)
            return false;
        if (auditInfo != null ? !auditInfo.equals(patient.auditInfo) : patient.auditInfo != null) return false;
        if (birthdate != null ? !birthdate.equals(patient.birthdate) : patient.birthdate != null) return false;
        if (children != null ? !children.equals(patient.children) : patient.children != null) return false;
        if (childrenThisPregnancy != null ? !childrenThisPregnancy.equals(patient.childrenThisPregnancy) : patient.childrenThisPregnancy != null)
            return false;
        if (dateDeath != null ? !dateDeath.equals(patient.dateDeath) : patient.dateDeath != null) return false;
        if (dead != null ? !dead.equals(patient.dead) : patient.dead != null) return false;
        if (districtPatientid != null ? !districtPatientid.equals(patient.districtPatientid) : patient.districtPatientid != null)
            return false;
        if (encounterData != null ? !encounterData.equals(patient.encounterData) : patient.encounterData != null)
            return false;
        if (encounterIdDeath != null ? !encounterIdDeath.equals(patient.encounterIdDeath) : patient.encounterIdDeath != null)
            return false;
        if (firstName != null ? !firstName.equals(patient.firstName) : patient.firstName != null) return false;
        if (height != null ? !height.equals(patient.height) : patient.height != null) return false;
        if (hivPositive != null ? !hivPositive.equals(patient.hivPositive) : patient.hivPositive != null) return false;
        if (importDate != null ? !importDate.equals(patient.importDate) : patient.importDate != null) return false;
        if (importSite != null ? !importSite.equals(patient.importSite) : patient.importSite != null) return false;
        if (nrcNumber != null ? !nrcNumber.equals(patient.nrcNumber) : patient.nrcNumber != null) return false;
        if (obstetricRecordnumber != null ? !obstetricRecordnumber.equals(patient.obstetricRecordnumber) : patient.obstetricRecordnumber != null)
            return false;
        if (outcomes != null ? !outcomes.equals(patient.outcomes) : patient.outcomes != null) return false;
        if (parent != null ? !parent.equals(patient.parent) : patient.parent != null) return false;
        if (parentDistrictPatientid != null ? !parentDistrictPatientid.equals(patient.parentDistrictPatientid) : patient.parentDistrictPatientid != null)
            return false;
        if (parentId != null ? !parentId.equals(patient.parentId) : patient.parentId != null) return false;
        if (patientRegistration != null ? !patientRegistration.equals(patient.patientRegistration) : patient.patientRegistration != null)
            return false;
        if (patientStatusreport != null ? !patientStatusreport.equals(patient.patientStatusreport) : patient.patientStatusreport != null)
            return false;
        if (pregnancyId != null ? !pregnancyId.equals(patient.pregnancyId) : patient.pregnancyId != null) return false;
        if (pregnancyList != null ? !pregnancyList.equals(patient.pregnancyList) : patient.pregnancyList != null)
            return false;
        if (prevPregnancies != null ? !prevPregnancies.equals(patient.prevPregnancies) : patient.prevPregnancies != null)
            return false;
        if (problems != null ? !problems.equals(patient.problems) : patient.problems != null) return false;
        if (sequenceNumber != null ? !sequenceNumber.equals(patient.sequenceNumber) : patient.sequenceNumber != null)
            return false;
        if (sessionPatient != null ? !sessionPatient.equals(patient.sessionPatient) : patient.sessionPatient != null)
            return false;
        if (sex != null ? !sex.equals(patient.sex) : patient.sex != null) return false;
        if (surname != null ? !surname.equals(patient.surname) : patient.surname != null) return false;
        if (taskList != null ? !taskList.equals(patient.taskList) : patient.taskList != null) return false;
        if (timeOfBirth != null ? !timeOfBirth.equals(patient.timeOfBirth) : patient.timeOfBirth != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (firstName != null ? firstName.hashCode() : 0);
        result = 29 * result + (surname != null ? surname.hashCode() : 0);
        result = 29 * result + (nrcNumber != null ? nrcNumber.hashCode() : 0);
        result = 29 * result + (districtPatientid != null ? districtPatientid.hashCode() : 0);
        result = 29 * result + (address != null ? address.hashCode() : 0);
        result = 29 * result + (encounterData != null ? encounterData.hashCode() : 0);
        result = 29 * result + (outcomes != null ? outcomes.hashCode() : 0);
        result = 29 * result + (auditInfo != null ? auditInfo.hashCode() : 0);
        result = 29 * result + (obstetricRecordnumber != null ? obstetricRecordnumber.hashCode() : 0);
        result = 29 * result + (taskList != null ? taskList.hashCode() : 0);
        result = 29 * result + (problems != null ? problems.hashCode() : 0);
        result = 29 * result + (children != null ? children.hashCode() : 0);
        result = 29 * result + (childrenThisPregnancy != null ? childrenThisPregnancy.hashCode() : 0);
        result = 29 * result + (parent != null ? parent.hashCode() : 0);
        result = 29 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 29 * result + (parentDistrictPatientid != null ? parentDistrictPatientid.hashCode() : 0);
        result = 29 * result + (sex != null ? sex.hashCode() : 0);
        result = 29 * result + (ageAtFirstVisit != null ? ageAtFirstVisit.hashCode() : 0);
        result = 29 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 29 * result + (timeOfBirth != null ? timeOfBirth.hashCode() : 0);
        result = 29 * result + (patientStatusreport != null ? patientStatusreport.hashCode() : 0);
        result = 29 * result + (sequenceNumber != null ? sequenceNumber.hashCode() : 0);
        result = 29 * result + (pregnancyId != null ? pregnancyId.hashCode() : 0);
        result = 29 * result + (dead != null ? dead.hashCode() : 0);
        result = 29 * result + (hivPositive != null ? hivPositive.hashCode() : 0);
        result = 29 * result + (dateDeath != null ? dateDeath.hashCode() : 0);
        result = 29 * result + (encounterIdDeath != null ? encounterIdDeath.hashCode() : 0);
        result = 29 * result + (height != null ? height.hashCode() : 0);
        result = 29 * result + (sessionPatient != null ? sessionPatient.hashCode() : 0);
        result = 29 * result + (prevPregnancies != null ? prevPregnancies.hashCode() : 0);
        result = 29 * result + (pregnancyList != null ? pregnancyList.hashCode() : 0);
        result = 29 * result + (patientRegistration != null ? patientRegistration.hashCode() : 0);
        result = 29 * result + (importSite != null ? importSite.hashCode() : 0);
        result = 29 * result + (importDate != null ? importDate.hashCode() : 0);
        return result;
    }

}
