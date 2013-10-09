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
public class BirthRecordPatient {

    private Long patientId;
    private PatientRegistrationClean infantPatientRegistration;
    private PatientRegistrationClean motherPatientRegistration;
    private String placeOfBirth;
    private String sex;

    private Long currentPregnancyId;

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

    public PatientRegistrationClean getInfantPatientRegistration() {
        return infantPatientRegistration;
    }

    public void setInfantPatientRegistration(PatientRegistrationClean infantPatientRegistration) {
        this.infantPatientRegistration = infantPatientRegistration;
    }

    public PatientRegistrationClean getMotherPatientRegistration() {
        return motherPatientRegistration;
    }

    public void setMotherPatientRegistration(PatientRegistrationClean motherPatientRegistration) {
        this.motherPatientRegistration = motherPatientRegistration;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
