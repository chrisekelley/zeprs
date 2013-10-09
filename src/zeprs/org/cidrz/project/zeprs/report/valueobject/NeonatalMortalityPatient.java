/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.project.zeprs.report.valueobject;

import org.cidrz.project.zeprs.valueobject.report.PatientRegistrationClean;

import java.sql.Date;
import java.sql.Time;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Nov 28, 2005
 *         Time: 5:00:40 PM
 */
public class NeonatalMortalityPatient {

    private Long patientId;
    private PatientRegistrationClean infantData;
    private PatientRegistrationClean motherData;
    private CurrentPregnancyStatus currentPregnancyStatus;  // values for ega, edd, and lmp
    private Long parentId;
    private int motherParity;
    private int numberMotherAncVisits;
    private String motherAncClinics;
    private Date admissionLabour;
    // observations - latentFirstStageLabour table
    private String bpMother;  // bp_systolic_224 bp_diastolic_225
    private Integer dilatation; // cervix_dilatation325
    private String fhr; // foetal_heart_rate_230
    // newborn eval - newbornEval table
    private Float birthweight; // weight_at_birth_491
    private Date birthDate;
    private Time birthtime;
    private String nurseAdmitting;
    private String nurseDelivering;
    private boolean rprTested;
    private boolean rprTreated;
    private String egaFirstVisit;
    private String outcome;
    private Long labourAdmissionEncounterId;
    private Long currentPregnancyId;
    private String egaInfant;
    private String rprResults;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getCurrentPregnancyId() {
        return currentPregnancyId;
    }

    public void setCurrentPregnancyId(Long currentPregnancyId) {
        this.currentPregnancyId = currentPregnancyId;
    }

    public PatientRegistrationClean getInfantData() {
        return infantData;
    }

    public void setInfantData(PatientRegistrationClean infantData) {
        this.infantData = infantData;
    }

    public PatientRegistrationClean getMotherData() {
        return motherData;
    }

    public void setMotherData(PatientRegistrationClean motherData) {
        this.motherData = motherData;
    }

    public CurrentPregnancyStatus getCurrentPregnancyStatus() {
        return currentPregnancyStatus;
    }

    public void setCurrentPregnancyStatus(CurrentPregnancyStatus currentPregnancyStatus) {
        this.currentPregnancyStatus = currentPregnancyStatus;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public int getMotherParity() {
        return motherParity;
    }

    public void setMotherParity(int motherParity) {
        this.motherParity = motherParity;
    }



    public int getNumberMotherAncVisits() {
        return numberMotherAncVisits;
    }

    public void setNumberMotherAncVisits(int numberMotherAncVisits) {
        this.numberMotherAncVisits = numberMotherAncVisits;
    }

    public String getMotherAncClinics() {
        return motherAncClinics;
    }

    public void setMotherAncClinics(String motherAncClinics) {
        this.motherAncClinics = motherAncClinics;
    }

    public Date getAdmissionLabour() {
        return admissionLabour;
    }

    public void setAdmissionLabour(Date admissionLabour) {
        this.admissionLabour = admissionLabour;
    }

    public String getBpMother() {
        return bpMother;
    }

    public void setBpMother(String bpMother) {
        this.bpMother = bpMother;
    }

    public Integer getDilatation() {
        return dilatation;
    }

    public void setDilatation(Integer dilatation) {
        this.dilatation = dilatation;
    }

    public String getFhr() {
        return fhr;
    }

    public void setFhr(String fhr) {
        this.fhr = fhr;
    }

    public Float getBirthweight() {
        return birthweight;
    }

    public void setBirthweight(Float birthweight) {
        this.birthweight = birthweight;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Time getBirthtime() {
        return birthtime;
    }

    public void setBirthtime(Time birthtime) {
        this.birthtime = birthtime;
    }

    public String getNurseAdmitting() {
        return nurseAdmitting;
    }

    public void setNurseAdmitting(String nurseAdmitting) {
        this.nurseAdmitting = nurseAdmitting;
    }

    public String getNurseDelivering() {
        return nurseDelivering;
    }

    public void setNurseDelivering(String nurseDelivering) {
        this.nurseDelivering = nurseDelivering;
    }

    public boolean isRprTested() {
        return rprTested;
    }

    public void setRprTested(boolean rprTested) {
        this.rprTested = rprTested;
    }

    public boolean isRprTreated() {
        return rprTreated;
    }

    public void setRprTreated(boolean rprTreated) {
        this.rprTreated = rprTreated;
    }

    public String getEgaFirstVisit() {
        return egaFirstVisit;
    }

    public void setEgaFirstVisit(String egaFirstVisit) {
        this.egaFirstVisit = egaFirstVisit;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public Long getLabourAdmissionEncounterId() {
        return labourAdmissionEncounterId;
    }

    public void setLabourAdmissionEncounterId(Long labourAdmissionEncounterId) {
        this.labourAdmissionEncounterId = labourAdmissionEncounterId;
    }

	/**
	 * @return the egaInfant
	 */
	public String getEgaInfant() {
		return egaInfant;
	}

	/**
	 * @param egaInfant the egaInfant to set
	 */
	public void setEgaInfant(String egaInfant) {
		this.egaInfant = egaInfant;
	}

	/**
	 * @return the rprResults
	 */
	public String getRprResults() {
		return rprResults;
	}

	/**
	 * @param rprResults the rprResults to set
	 */
	public void setRprResults(String rprResults) {
		this.rprResults = rprResults;
	}


}
