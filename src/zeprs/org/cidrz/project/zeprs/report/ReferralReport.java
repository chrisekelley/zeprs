/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.project.zeprs.report;

import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.project.zeprs.valueobject.report.BaseReport;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.valueobject.Patient;

import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Nov 19, 2004
 * Time: 2:00:37 PM
 */

/**
 * @hibernate.class table="referral"
 * mutable="true"
 */

public class ReferralReport implements Identifiable, BaseReport {
    private Long id;
    private EncounterData encounter;
    private Patient patient;
    private Date dateOfReferral;
    private String referredFrom;
    private String referredTo;


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
     * @hibernate.many-to-one column="encounter_id"
     * cascade="none"
     */

    public EncounterData getEncounter() {
        return encounter;
    }

    public void setEncounter(EncounterData encounter) {
        this.encounter = encounter;
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
     * @hibernate.property column="date_of_referral"
     * not-null="false"
     */
    public Date getDateOfReferral() {
        return dateOfReferral;
    }

    public void setDateOfReferral(Date dateOfReferral) {
        this.dateOfReferral = dateOfReferral;
    }

    /**
     * @return
     * @hibernate.property column="referred_from"
     * not-null="false"
     */
    public String getReferredFrom() {
        return referredFrom;
    }

    public void setReferredFrom(String referredFrom) {
        this.referredFrom = referredFrom;
    }

    /**
     * @return
     * @hibernate.property column="referred_to"
     * not-null="false"
     */

    public String getReferredTo() {
        return referredTo;
    }

    public void setReferredTo(String referredTo) {
        this.referredTo = referredTo;
    }

}
