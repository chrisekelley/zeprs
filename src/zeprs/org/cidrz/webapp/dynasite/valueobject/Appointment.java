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
import org.cidrz.project.zeprs.valueobject.report.BaseReport;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;
import org.cidrz.webapp.dynasite.valueobject.Patient;

import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Nov 5, 2004
 * Time: 12:21:35 PM
 */

/**
 * @hibernate.class table="appointment"
 * mutable="true"
 */

public class Appointment implements Identifiable, BaseReport {

    private Long id;
    private EncounterData encounter;
    private Patient patient;
    private Long appointmentTypeId;
    private String comment;
    private Date visitDate;
    private Date appointmentDate;
    private Long attendanceId;

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
     * @hibernate.property column="appt_type_id"
     */

    public Long getAppointmentTypeId() {
        return appointmentTypeId;
    }

    public void setAppointmentTypeId(Long appointmentTypeId) {
        this.appointmentTypeId = appointmentTypeId;
    }

    /**
     * @return
     * @hibernate.property column="comment"
     */

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    /**
     * @return
     * @hibernate.property column="visit_date"
     */

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    /**
     * @return
     * @hibernate.property column="appointment_date"
     */

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    /**
     * @return
     * @hibernate.property column="attendance_id"
     */

    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }
}
