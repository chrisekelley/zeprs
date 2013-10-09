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


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.valueobject.gen.PartographStatus;
import org.cidrz.webapp.dynasite.logic.EncounterConfirmation;
import org.cidrz.project.zeprs.valueobject.EncounterData;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Calendar;


public class SessionPatient implements Identifiable {
    /**
     * Commons Logging instance.
     */
    private transient Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    private Long id;
    private String firstName;
    private String surname;
    private String nrcNumber;
    private String districtPatientid;
    private Address address;
    private AuditInfo auditInfo;
    private String obstetricRecordnumber;
    private Integer sex;
    private Integer ageAtFirstVisit;
    private Date birthdate;
    private Time timeOfBirth;
    private Long currentEgaDaysEncounterId;
    private Integer currentEgaDaysDB;
    private Integer currentEgaCalc;
    private java.util.Date currentEgaDateCalc;
    private Date currentLmpDate;
    private Long currentLmpDateEncounterId;
    private Long parentId;
    private Long currentPregnancyId;
    private Date datePregnancyBegin;
    private Date datePregnancyEnd;
    private Long pregnancyBeginEncounterId;
    private Long pregnancyEndEncounterId;
    private Long currentFlowId;
    private String currentFlowName;
    private Long currentFlowEncounterId;
    private Long currentFormId;
    private PartographStatus partographStatus;
    private List children = new ArrayList();
    private EncounterConfirmation confirmation;
    private Boolean motherStatus = Boolean.TRUE;
    private Pregnancy pregnancy;
    private Timestamp lastModified;
    private Patient mother;
    private Boolean deliveryCompleted;
    private Boolean noPreviousPregnancies;
    private Byte hivPositive;
    private boolean motherHivPositive;
    private List longTermProblems;
    private Date dateActiveLabour;
    private String firm;    // UTH firm - A,B,C,or D
    private Byte disableLabImport;
    private HashMap<String,EncounterData> encounterStatusMap;	//Map of encounters for patient status; identifier:EncounterData record
    private String uuid;	// unique identifier for the patient.
    private String parentUuid;	// unique identifier for the parent.

    public SessionPatient() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public AuditInfo getAuditInfo() {
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

    public Long getCurrentEgaDaysEncounterId() {
        return currentEgaDaysEncounterId;
    }

    public void setCurrentEgaDaysEncounterId(Long currentEgaDaysEncounterId) {
        this.currentEgaDaysEncounterId = currentEgaDaysEncounterId;
    }

    /**
     * EGA from database
     * @return currentEgaDaysDB
     */
    public Integer getCurrentEgaDaysDB() {
        return currentEgaDaysDB;
    }

    public void setCurrentEgaDaysDB(Integer currentEgaDaysDB) {
        this.currentEgaDaysDB = currentEgaDaysDB;
    }

    /**
     * EGA Interger in days calculated to today's date
     * @return currentEgaCalc
     */
    public Integer getCurrentEgaCalc() {
        return currentEgaCalc;
    }

    public void setCurrentEgaCalc(Integer currentEgaCalc) {
        this.currentEgaCalc = currentEgaCalc;
    }

    /**
     * EGA Date calculated to today's date
     * @return currentEgaDateCalc
     */

    public java.util.Date getCurrentEgaDateCalc() {
        currentEgaDateCalc = null;
        java.util.Date recent_lmp = currentLmpDate;
        GregorianCalendar eddCal = new GregorianCalendar();
        if (recent_lmp != null) {
        eddCal.setTime(recent_lmp);
        eddCal.add(Calendar.DATE, 280);
        currentEgaDateCalc = eddCal.getTime();
        }
        return currentEgaDateCalc;
    }

    public void setCurrentEgaDateCalc(java.util.Date currentEgaDateCalc) {
        this.currentEgaDateCalc = currentEgaDateCalc;
    }

    /**
     * LMP from db
     * @return currentLmpDate
     */
    public Date getCurrentLmpDate() {
        return currentLmpDate;
    }

    public void setCurrentLmpDate(Date currentLmpDate) {
        this.currentLmpDate = currentLmpDate;
    }

    public Long getCurrentLmpDateEncounterId() {
        return currentLmpDateEncounterId;
    }

    public void setCurrentLmpDateEncounterId(Long currentLmpDateEncounterId) {
        this.currentLmpDateEncounterId = currentLmpDateEncounterId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getCurrentPregnancyId() {
        return currentPregnancyId;
    }

    public void setCurrentPregnancyId(Long currentPregnancyId) {
        this.currentPregnancyId = currentPregnancyId;
    }

    public Date getDatePregnancyBegin() {
        return datePregnancyBegin;
    }

    public void setDatePregnancyBegin(Date datePregnancyBegin) {
        this.datePregnancyBegin = datePregnancyBegin;
    }


    public Date getDatePregnancyEnd() {
        return datePregnancyEnd;
    }

    public void setDatePregnancyEnd(Date datePregnancyEnd) {
        this.datePregnancyEnd = datePregnancyEnd;
    }

    public Long getPregnancyBeginEncounterId() {
        return pregnancyBeginEncounterId;
    }

    public void setPregnancyBeginEncounterId(Long pregnancyBeginEncounterId) {
        this.pregnancyBeginEncounterId = pregnancyBeginEncounterId;
    }

    public Long getPregnancyEndEncounterId() {
        return pregnancyEndEncounterId;
    }

    public void setPregnancyEndEncounterId(Long pregnancyEndEncounterId) {
        this.pregnancyEndEncounterId = pregnancyEndEncounterId;
    }

    public Long getCurrentFlowId() {
        return currentFlowId;
    }

    public void setCurrentFlowId(Long currentFlowId) {
        this.currentFlowId = currentFlowId;
    }

    public String getCurrentFlowName() {
        return currentFlowName;
    }

    public void setCurrentFlowName(String currentFlowName) {
        this.currentFlowName = currentFlowName;
    }

    public Long getCurrentFlowEncounterId() {
        return currentFlowEncounterId;
    }

    public void setCurrentFlowEncounterId(Long currentFlowEncounterId) {
        this.currentFlowEncounterId = currentFlowEncounterId;
    }

    public Long getCurrentFormId() {
        return currentFormId;
    }

    public void setCurrentFormId(Long currentFormId) {
        this.currentFormId = currentFormId;
    }

    public PartographStatus getPartographStatus() {
        return partographStatus;
    }

    public void setPartographStatus(PartographStatus partographStatus) {
        this.partographStatus = partographStatus;
    }

    public List getChildren() {
        return children;
    }

    public void setChildren(List children) {
        this.children = children;
    }

    public EncounterConfirmation getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(EncounterConfirmation confirmation) {
        this.confirmation = confirmation;
    }

    public Boolean getMotherStatus() {
        return motherStatus;
    }

    public void setMotherStatus(Boolean motherStatus) {
        this.motherStatus = motherStatus;
    }

/*    public Pregnancy getPregnancy() {
        pregnancy = new Pregnancy();
        pregnancy.setId(currentPregnancyId);
        pregnancy.setDatePregnancyBegin(datePregnancyBegin);
        pregnancy.setDatePregnancyEnd(datePregnancyEnd);
        return pregnancy;
    }*/

	public Pregnancy getPregnancy() {
		return pregnancy;
	}

    public void setPregnancy(Pregnancy pregnancy) {
        this.pregnancy = pregnancy;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * This is set by SessionPatientDAO.updateSessionPatient
     * @return Patient
     */
    public Patient getMother() {
        return mother;
    }

    public void setMother(Patient mother) {
        this.mother = mother;
    }

    public Boolean getDeliveryCompleted() {
        return deliveryCompleted;
    }

    public void setDeliveryCompleted(Boolean deliveryCompleted) {
        this.deliveryCompleted = deliveryCompleted;
    }

    public Boolean getNoPreviousPregnancies() {
        return noPreviousPregnancies;
    }

    public void setNoPreviousPregnancies(Boolean noPreviousPregnancies) {
        this.noPreviousPregnancies = noPreviousPregnancies;
    }

    public Byte getHivPositive() {
        return hivPositive;
    }

    public void setHivPositive(Byte hivPositive) {
        this.hivPositive = hivPositive;
    }

    public boolean isMotherHivPositive() {
        return motherHivPositive;
    }

    public void setMotherHivPositive(boolean motherHivPositive) {
        this.motherHivPositive = motherHivPositive;
    }

    public List getLongTermProblems() {
        return longTermProblems;
    }

    public void setLongTermProblems(List longTermProblems) {
        this.longTermProblems = longTermProblems;
    }

    public Date getDateActiveLabour() {
        return dateActiveLabour;
    }

    public void setDateActiveLabour(Date dateActiveLabour) {
        this.dateActiveLabour = dateActiveLabour;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    /**
	 * @return the disableLabImport
	 */
	public Byte getDisableLabImport() {
		return disableLabImport;
	}

	/**
	 * @param disableLabImport the disableLabImport to set
	 */
	public void setDisableLabImport(Byte disableLabImport) {
		this.disableLabImport = disableLabImport;
	}

	public HashMap<String, EncounterData> getEncounterStatusMap() {
		return encounterStatusMap;
	}

	public void setEncounterStatusMap(
			HashMap<String, EncounterData> encounterStatusMap) {
		this.encounterStatusMap = encounterStatusMap;
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

        final SessionPatient that = (SessionPatient) o;

        if (motherHivPositive != that.motherHivPositive) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (ageAtFirstVisit != null ? !ageAtFirstVisit.equals(that.ageAtFirstVisit) : that.ageAtFirstVisit != null)
            return false;
        if (birthdate != null ? !birthdate.equals(that.birthdate) : that.birthdate != null) return false;
        if (currentEgaCalc != null ? !currentEgaCalc.equals(that.currentEgaCalc) : that.currentEgaCalc != null)
            return false;
        if (currentEgaDateCalc != null ? !currentEgaDateCalc.equals(that.currentEgaDateCalc) : that.currentEgaDateCalc != null)
            return false;
        if (currentEgaDaysDB != null ? !currentEgaDaysDB.equals(that.currentEgaDaysDB) : that.currentEgaDaysDB != null)
            return false;
        if (currentFlowName != null ? !currentFlowName.equals(that.currentFlowName) : that.currentFlowName != null)
            return false;
        if (currentFormId != null ? !currentFormId.equals(that.currentFormId) : that.currentFormId != null) return false;
        if (currentLmpDate != null ? !currentLmpDate.equals(that.currentLmpDate) : that.currentLmpDate != null)
            return false;
        if (dateActiveLabour != null ? !dateActiveLabour.equals(that.dateActiveLabour) : that.dateActiveLabour != null)
            return false;
        if (datePregnancyBegin != null ? !datePregnancyBegin.equals(that.datePregnancyBegin) : that.datePregnancyBegin != null)
            return false;
        if (datePregnancyEnd != null ? !datePregnancyEnd.equals(that.datePregnancyEnd) : that.datePregnancyEnd != null)
            return false;
        if (deliveryCompleted != null ? !deliveryCompleted.equals(that.deliveryCompleted) : that.deliveryCompleted != null)
            return false;
        if (districtPatientid != null ? !districtPatientid.equals(that.districtPatientid) : that.districtPatientid != null)
            return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (hivPositive != null ? !hivPositive.equals(that.hivPositive) : that.hivPositive != null) return false;
        if (lastModified != null ? !lastModified.equals(that.lastModified) : that.lastModified != null) return false;
        if (motherStatus != null ? !motherStatus.equals(that.motherStatus) : that.motherStatus != null) return false;
        if (noPreviousPregnancies != null ? !noPreviousPregnancies.equals(that.noPreviousPregnancies) : that.noPreviousPregnancies != null)
            return false;
        if (nrcNumber != null ? !nrcNumber.equals(that.nrcNumber) : that.nrcNumber != null) return false;
        if (obstetricRecordnumber != null ? !obstetricRecordnumber.equals(that.obstetricRecordnumber) : that.obstetricRecordnumber != null)
            return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (timeOfBirth != null ? !timeOfBirth.equals(that.timeOfBirth) : that.timeOfBirth != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (firstName != null ? firstName.hashCode() : 0);
        result = 29 * result + (surname != null ? surname.hashCode() : 0);
        result = 29 * result + (nrcNumber != null ? nrcNumber.hashCode() : 0);
        result = 29 * result + (districtPatientid != null ? districtPatientid.hashCode() : 0);
        result = 29 * result + (address != null ? address.hashCode() : 0);
        result = 29 * result + (obstetricRecordnumber != null ? obstetricRecordnumber.hashCode() : 0);
        result = 29 * result + (sex != null ? sex.hashCode() : 0);
        result = 29 * result + (ageAtFirstVisit != null ? ageAtFirstVisit.hashCode() : 0);
        result = 29 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 29 * result + (timeOfBirth != null ? timeOfBirth.hashCode() : 0);
        result = 29 * result + (currentEgaDaysDB != null ? currentEgaDaysDB.hashCode() : 0);
        result = 29 * result + (currentEgaCalc != null ? currentEgaCalc.hashCode() : 0);
        result = 29 * result + (currentEgaDateCalc != null ? currentEgaDateCalc.hashCode() : 0);
        result = 29 * result + (currentLmpDate != null ? currentLmpDate.hashCode() : 0);
        result = 29 * result + (datePregnancyBegin != null ? datePregnancyBegin.hashCode() : 0);
        result = 29 * result + (datePregnancyEnd != null ? datePregnancyEnd.hashCode() : 0);
        result = 29 * result + (currentFlowName != null ? currentFlowName.hashCode() : 0);
        result = 29 * result + (currentFormId != null ? currentFormId.hashCode() : 0);
        result = 29 * result + (motherStatus != null ? motherStatus.hashCode() : 0);
        result = 29 * result + (lastModified != null ? lastModified.hashCode() : 0);
        result = 29 * result + (deliveryCompleted != null ? deliveryCompleted.hashCode() : 0);
        result = 29 * result + (noPreviousPregnancies != null ? noPreviousPregnancies.hashCode() : 0);
        result = 29 * result + (hivPositive != null ? hivPositive.hashCode() : 0);
        result = 29 * result + (motherHivPositive ? 1 : 0);
        result = 29 * result + (dateActiveLabour != null ? dateActiveLabour.hashCode() : 0);
        return result;
    }
}
