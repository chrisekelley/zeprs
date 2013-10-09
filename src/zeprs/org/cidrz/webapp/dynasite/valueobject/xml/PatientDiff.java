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

import org.cidrz.project.zeprs.valueobject.gen.PatientRegistration;
import org.cidrz.project.zeprs.valueobject.report.PatientStatusReport;
import org.cidrz.webapp.dynasite.valueobject.*;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class PatientDiff implements Auditable {

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
    private List pregnancyList; // used for XML records
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

    public PatientDiff() {
        super();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNrcNumber() {
        return nrcNumber;
    }

    public void setNrcNumber(String nrcNumber) {
        this.nrcNumber = nrcNumber;
    }

    public String getDistrictPatientid() {
        return districtPatientid;
    }

    public void setDistrictPatientid(String districtPatientid) {
        this.districtPatientid = districtPatientid;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set getEncounterData() {
        return encounterData;
    }

    public void setEncounterData(Set encounterData) {
        this.encounterData = encounterData;
    }

    public Set getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(Set outcomes) {
        this.outcomes = outcomes;
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

    public Set getChildren() {
        return children;
    }

    public void setChildren(Set children) {
        this.children = children;
    }

    public List getChildrenThisPregnancy() {
        return childrenThisPregnancy;
    }

    public void setChildrenThisPregnancy(List childrenThisPregnancy) {
        this.childrenThisPregnancy = childrenThisPregnancy;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentDistrictPatientid() {
        return parentDistrictPatientid;
    }

    public void setParentDistrictPatientid(String parentDistrictPatientid) {
        this.parentDistrictPatientid = parentDistrictPatientid;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAgeAtFirstVisit() {
        return ageAtFirstVisit;
    }

    public void setAgeAtFirstVisit(Integer ageAtFirstVisit) {
        this.ageAtFirstVisit = ageAtFirstVisit;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Time getTimeOfBirth() {
        return timeOfBirth;
    }

    public void setTimeOfBirth(Time timeOfBirth) {
        this.timeOfBirth = timeOfBirth;
    }

    public PatientStatusReport getPatientStatusreport() {
        return patientStatusreport;
    }

    public void setPatientStatusreport(PatientStatusReport patientStatusreport) {
        this.patientStatusreport = patientStatusreport;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Long getPregnancyId() {
        return pregnancyId;
    }

    public void setPregnancyId(Long pregnancyId) {
        this.pregnancyId = pregnancyId;
    }

    public Boolean getDead() {
        return dead;
    }

    public void setDead(Boolean dead) {
        this.dead = dead;
    }

    public Byte getHivPositive() {
        return hivPositive;
    }

    public void setHivPositive(Byte hivPositive) {
        this.hivPositive = hivPositive;
    }

    public Date getDateDeath() {
        return dateDeath;
    }

    public void setDateDeath(Date dateDeath) {
        this.dateDeath = dateDeath;
    }

    public Long getEncounterIdDeath() {
        return encounterIdDeath;
    }

    public void setEncounterIdDeath(Long encounterIdDeath) {
        this.encounterIdDeath = encounterIdDeath;
    }

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

    public List getPregnancyList() {
        return pregnancyList;
    }

    public void setPregnancyList(List pregnancyList) {
        this.pregnancyList = pregnancyList;
    }

    public PatientRegistration getPatientRegistration() {
        return patientRegistration;
    }

    public void setPatientRegistration(PatientRegistration patientRegistration) {
        this.patientRegistration = patientRegistration;
    }

    public Long getImportPatientId() {
        return importPatientId;
    }

    public void setImportPatientId(Long importPatientId) {
        this.importPatientId = importPatientId;
    }

    public String getImportSite() {
        return importSite;
    }

    public void setImportSite(String importSite) {
        this.importSite = importSite;
    }

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
}
