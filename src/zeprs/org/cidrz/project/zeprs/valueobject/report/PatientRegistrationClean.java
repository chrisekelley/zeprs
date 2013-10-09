/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.project.zeprs.valueobject.report;

import org.cidrz.project.zeprs.valueobject.EncounterData;

/**
 * @hibernate.joined-subclass table="patientregistration"
 * @hibernate.joined-subclass-key column="id"
 */
public class PatientRegistrationClean extends EncounterData {

    private String surname;
    private String forenames;
    private String nrcNo;
    private String obstetricRecordNumber;
    private String ageAtFirstAttendence;
    private String birthDate;
    private String educationStatus;
    private String address1;
    private String address2;
    private String dateOfResidenceChange;
    private String maritalStatus;
    private String occupation;
    private String occupationOther;
    private String nearbyPlaceWorship;
    private String religion;
    private String religionOther;
    private String surnameFather;
    private String forenamesFather;
    private String surnameHusband;
    private String forenamesHusband;
    private String occupationHusband;
    private String telNoHusband;
    private String surnameGuardian;
    private String forenamesGuardian;
    private String surnameEmergContact;
    private String forenameEemergContact;
    private String addressEmergContact;
    private String telNoEmergContact;
    private int currentAge; // calcualted value
    private String timeOfBirth; // from newborn eval
    private String sex;
    private Integer height; // from patient table
    private String patientIdNumber;  // safe motherhood number
    private String uthReferralType;
    private Integer uthReferralTypeId;
    private String patientPhone;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getForenames() {
        return forenames;
    }

    public void setForenames(String forenames) {
        this.forenames = forenames;
    }

    public String getNrcNo() {
        return nrcNo;
    }

    public void setNrcNo(String nrcNo) {
        this.nrcNo = nrcNo;
    }

    public String getObstetricRecordNumber() {
        return obstetricRecordNumber;
    }

    public void setObstetricRecordNumber(String obstetricRecordNumber) {
        this.obstetricRecordNumber = obstetricRecordNumber;
    }

    public String getAgeAtFirstAttendence() {
        return ageAtFirstAttendence;
    }

    public void setAgeAtFirstAttendence(String ageAtFirstAttendence) {
        this.ageAtFirstAttendence = ageAtFirstAttendence;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEducationStatus() {
        return educationStatus;
    }

    public void setEducationStatus(String educationStatus) {
        this.educationStatus = educationStatus;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getDateOfResidenceChange() {
        return dateOfResidenceChange;
    }

    public void setDateOfResidenceChange(String dateOfResidenceChange) {
        this.dateOfResidenceChange = dateOfResidenceChange;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getOccupationOther() {
        return occupationOther;
    }

    public void setOccupationOther(String occupationOther) {
        this.occupationOther = occupationOther;
    }

    public String getNearbyPlaceWorship() {
        return nearbyPlaceWorship;
    }

    public void setNearbyPlaceWorship(String nearbyPlaceWorship) {
        this.nearbyPlaceWorship = nearbyPlaceWorship;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getReligionOther() {
        return religionOther;
    }

    public void setReligionOther(String religionOther) {
        this.religionOther = religionOther;
    }

    public String getSurnameFather() {
        return surnameFather;
    }

    public void setSurnameFather(String surnameFather) {
        this.surnameFather = surnameFather;
    }

    public String getForenamesFather() {
        return forenamesFather;
    }

    public void setForenamesFather(String forenamesFather) {
        this.forenamesFather = forenamesFather;
    }

    public String getSurnameHusband() {
        return surnameHusband;
    }

    public void setSurnameHusband(String surnameHusband) {
        this.surnameHusband = surnameHusband;
    }

    public String getForenamesHusband() {
        return forenamesHusband;
    }

    public void setForenamesHusband(String forenamesHusband) {
        this.forenamesHusband = forenamesHusband;
    }

    public String getOccupationHusband() {
        return occupationHusband;
    }

    public void setOccupationHusband(String occupationHusband) {
        this.occupationHusband = occupationHusband;
    }

    public String getTelNoHusband() {
        return telNoHusband;
    }

    public void setTelNoHusband(String telNoHusband) {
        this.telNoHusband = telNoHusband;
    }

    public String getSurnameGuardian() {
        return surnameGuardian;
    }

    public void setSurnameGuardian(String surnameGuardian) {
        this.surnameGuardian = surnameGuardian;
    }

    public String getForenamesGuardian() {
        return forenamesGuardian;
    }

    public void setForenamesGuardian(String forenamesGuardian) {
        this.forenamesGuardian = forenamesGuardian;
    }

    public String getSurnameEmergContact() {
        return surnameEmergContact;
    }

    public void setSurnameEmergContact(String surnameEmergContact) {
        this.surnameEmergContact = surnameEmergContact;
    }

    public String getForenameEemergContact() {
        return forenameEemergContact;
    }

    public void setForenameEemergContact(String forenameEemergContact) {
        this.forenameEemergContact = forenameEemergContact;
    }

    public String getAddressEmergContact() {
        return addressEmergContact;
    }

    public void setAddressEmergContact(String addressEmergContact) {
        this.addressEmergContact = addressEmergContact;
    }

    public String getTelNoEmergContact() {
        return telNoEmergContact;
    }

    public void setTelNoEmergContact(String telNoEmergContact) {
        this.telNoEmergContact = telNoEmergContact;
    }

    public int getCurrentAge() {
        return currentAge;
    }

    public void setCurrentAge(int currentAge) {
        this.currentAge = currentAge;
    }

    public String getTimeOfBirth() {
        return timeOfBirth;
    }

    public void setTimeOfBirth(String timeOfBirth) {
        this.timeOfBirth = timeOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public String getUthReferralType() {
        return uthReferralType;
    }

    public void setUthReferralType(String uthReferralType) {
        this.uthReferralType = uthReferralType;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getPatientIdNumber() {
        return patientIdNumber;
    }

    public void setPatientIdNumber(String patientIdNumber) {
        this.patientIdNumber = patientIdNumber;
    }

    public Integer getUthReferralTypeId() {
        return uthReferralTypeId;
    }

    public void setUthReferralTypeId(Integer uthReferralTypeId) {
        this.uthReferralTypeId = uthReferralTypeId;
    }

	public String getPatientPhone() {
		return patientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}

}
