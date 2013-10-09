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
 * Date: Feb 8, 2005
 * Time: 4:01:05 PM
 */

/**
 * @hibernate.class table="tt_immunisation"
 * mutable="true"
 */

public class TTImmunisationReport implements Identifiable, BaseReport {
    private Long id;
    private EncounterData encounter;
    private Patient patient;
    private String childhoodDTImmunisations;
    private Integer dptDoses;
    private Integer ttDoses;
    private Date ttImmunisation1;
    private Date ttImmunisation2;
    private Date ttImmunisation3;
    private Date ttImmunisation4;
    private Date ttImmunisation5;

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
     * @hibernate.property column="childhood_immun"
     * not-null="false"
     */

    public String getChildhoodDTImmunisations() {
        return childhoodDTImmunisations;
    }

    public void setChildhoodDTImmunisations(String childhoodDTImmunisations) {
        this.childhoodDTImmunisations = childhoodDTImmunisations;
    }

    /**
     * @return
     * @hibernate.property column="dpt_doses"
     * not-null="false"
     */

    public Integer getDptDoses() {
        return dptDoses;
    }

    public void setDptDoses(Integer dptDoses) {
        this.dptDoses = dptDoses;
    }

    /**
     * @return
     * @hibernate.property column="tt_doses"
     * not-null="false"
     */

    public Integer getTtDoses() {
        return ttDoses;
    }

    public void setTtDoses(Integer ttDoses) {
        this.ttDoses = ttDoses;
    }

    /**
     * @return
     * @hibernate.property column="tt_immunisation_1"
     * not-null="false"
     */

    public Date getTtImmunisation1() {
        return ttImmunisation1;
    }

    public void setTtImmunisation1(Date ttImmunisation1) {
        this.ttImmunisation1 = ttImmunisation1;
    }

    /**
     * @return
     * @hibernate.property column="tt_immunisation_2"
     * not-null="false"
     */
    public Date getTtImmunisation2() {
        return ttImmunisation2;
    }

    public void setTtImmunisation2(Date ttImmunisation2) {
        this.ttImmunisation2 = ttImmunisation2;
    }

    /**
     * @return
     * @hibernate.property column="tt_immunisation_3"
     * not-null="false"
     */
    public Date getTtImmunisation3() {
        return ttImmunisation3;
    }

    public void setTtImmunisation3(Date ttImmunisation3) {
        this.ttImmunisation3 = ttImmunisation3;
    }

    /**
     * @return
     * @hibernate.property column="tt_immunisation_4"
     * not-null="false"
     */
    public Date getTtImmunisation4() {
        return ttImmunisation4;
    }

    public void setTtImmunisation4(Date ttImmunisation4) {
        this.ttImmunisation4 = ttImmunisation4;
    }


    /**
     * @return
     * @hibernate.property column="tt_immunisation_5"
     * not-null="false"
     */
    public Date getTtImmunisation5() {
        return ttImmunisation5;
    }

    public void setTtImmunisation5(Date ttImmunisation5) {
        this.ttImmunisation5 = ttImmunisation5;
    }
}
