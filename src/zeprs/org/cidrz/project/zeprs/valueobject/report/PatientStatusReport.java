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
import org.cidrz.webapp.dynasite.valueobject.*;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Jan 8, 2005
 * Time: 2:28:06 PM
 */

/**
 * @hibernate.class table="patient_status"
 * mutable="true"
 */

public class PatientStatusReport implements Identifiable, Auditable {

    private Long id;
    private Patient patient;
    private Flow currentFlow;
    private Long currentFlowId;
    private EncounterData currentFlowEncounter;
    private Long currentFlowEncounterId;
    private String currentFlowEncounterUuid;
    private Long currentPregnancyId;
    private String currentPregnancyUuid;
    private EncounterData currentPregnancyEncounter;
    private Long currentPregnancyEncounterId;
    private String currentPregnancyEncounterUuid;
    private Date currentLmpDate;
    private EncounterData currentLmpDateEncounter;
    private Long currentLmpDateEncounterId;
    private String currentLmpDateEncounterUuid;
    private Integer currentEgaDays;
    private EncounterData currentEgaDaysEncounter;
    private Long currentEgaDaysEncounterId;
    private String currentEgaDaysEncounterUuid;
    private AuditInfo auditInfo;
        // recordables
    private Timestamp lastModified;
    private Timestamp created;
    private String lastModifiedBy;
    private String createdBy;
    private Long siteId;
    private Boolean noPreviousPregnancies;
    private String firm;    // UTH firm - A,B,C,or D
    // private Integer egaToday;

    /**
     * @return
     * @hibernate.id unsaved-value="0" generator-class="foreign"
     * @hibernate.generator-param name="property" value="patient"
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return
     * @hibernate.one-to-one class="org.cidrz.webapp.dynasite.valueobject.Patient"
     * constrained="true"
     */
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * @return
     * @hibernate.property column="current_flow"
     */
    public Flow getCurrentFlow() {
        return currentFlow;
    }

    public void setCurrentFlow(Flow currentFlow) {
        this.currentFlow = currentFlow;
    }

    /**
     * @return
     * @hibernate.many-to-one column="current_flow_encounter_id"
     * cascade="none"
     * outer-join="false"
     */

    public EncounterData getCurrentFlowEncounter() {
        return currentFlowEncounter;
    }

    public void setCurrentFlowEncounter(EncounterData currentFlowEncounter) {
        this.currentFlowEncounter = currentFlowEncounter;
    }

    /**
     * @return
     * @hibernate.property column="current_pregnancy_id"
     */

    public Long getCurrentPregnancyId() {
        return currentPregnancyId;
    }

    public void setCurrentPregnancyId(Long currentPregnancyId) {
        this.currentPregnancyId = currentPregnancyId;
    }

    /**
     * @return
     * @hibernate.many-to-one column="current_pregnancy_encounter_id"
     * cascade="none"
     * outer-join="false"
     */

    public EncounterData getCurrentPregnancyEncounter() {
        return currentPregnancyEncounter;
    }

    public void setCurrentPregnancyEncounter(EncounterData currentPregnancyEncounter) {
        this.currentPregnancyEncounter = currentPregnancyEncounter;
    }

    /**
     * @return
     * @hibernate.property column="current_lmp_date"
     * not-null="false"
     */

    public Date getCurrentLmpDate() {
        return currentLmpDate;
    }

    public void setCurrentLmpDate(Date currentLmpDate) {
        this.currentLmpDate = currentLmpDate;
    }

    /**
     * @return
     * @hibernate.many-to-one column="current_lmp_date_encounter_id"
     * cascade="none"
     * outer-join="false"
     */

    public EncounterData getCurrentLmpDateEncounter() {
        return currentLmpDateEncounter;
    }

    public void setCurrentLmpDateEncounter(EncounterData currentLmpDateEncounter) {
        this.currentLmpDateEncounter = currentLmpDateEncounter;
    }

    /**
     * @return
     * @hibernate.property column="current_ega_days"
     * not-null="false"
     */

    public Integer getCurrentEgaDays() {
        return currentEgaDays;
    }

    public void setCurrentEgaDays(Integer currentEgaDays) {
        this.currentEgaDays = currentEgaDays;
    }

    /**
     * @return
     * @hibernate.many-to-one column="current_ega_days_encounter_id"
     * cascade="none"
     * outer-join="false"
     */

    public EncounterData getCurrentEgaDaysEncounter() {
        return currentEgaDaysEncounter;
    }

    public void setCurrentEgaDaysEncounter(EncounterData currentEgaDaysEncounter) {
        this.currentEgaDaysEncounter = currentEgaDaysEncounter;
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

    /**
     * @return
     * @hibernate.property column="no_prev_pregnancies"
     * not-null="false"
     */

    public Boolean getNoPreviousPregnancies() {
        return noPreviousPregnancies;
    }

    public void setNoPreviousPregnancies(Boolean noPreviousPregnancies) {
        this.noPreviousPregnancies = noPreviousPregnancies;
    }

    // Recordables
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

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public Long getCurrentFlowId() {
        return currentFlowId;
    }

    public void setCurrentFlowId(Long currentFlowId) {
        this.currentFlowId = currentFlowId;
    }

    public Long getCurrentFlowEncounterId() {
        return currentFlowEncounterId;
    }

    public void setCurrentFlowEncounterId(Long currentFlowEncounterId) {
        this.currentFlowEncounterId = currentFlowEncounterId;
    }

    public Long getCurrentPregnancyEncounterId() {
        return currentPregnancyEncounterId;
    }

    public void setCurrentPregnancyEncounterId(Long currentPregnancyEncounterId) {
        this.currentPregnancyEncounterId = currentPregnancyEncounterId;
    }

    public Long getCurrentLmpDateEncounterId() {
        return currentLmpDateEncounterId;
    }

    public void setCurrentLmpDateEncounterId(Long currentLmpDateEncounterId) {
        this.currentLmpDateEncounterId = currentLmpDateEncounterId;
    }

    public Long getCurrentEgaDaysEncounterId() {
        return currentEgaDaysEncounterId;
    }

    public void setCurrentEgaDaysEncounterId(Long currentEgaDaysEncounterId) {
        this.currentEgaDaysEncounterId = currentEgaDaysEncounterId;
    }


    /**
     * Calculate the EDD Date based on currentLMPDate
     * @return eddDate
     */

    public java.util.Date calcEddDate() {
        java.util.Date recent_lmp = currentLmpDate;
        GregorianCalendar eddCal = new GregorianCalendar();
        eddCal.setTime(recent_lmp);
        //eddCal.add(Calendar.DATE, 7);
        eddCal.add(Calendar.DATE, 280);
        //eddCal.add(Calendar.MONTH, 9);
        java.util.Date eddDate = eddCal.getTime();
        return eddDate;
    }

    /**
     * Calculate EGA based on today's date
     * @return egaToday
     */

    public long calcEgaToday() throws PersistenceException, ObjectNotFoundException {
        long egaToday = currentEgaDays.longValue();
        // if patient is not yet in Delivery Summary flow, calculate current ega
        // otherwise, just return the last value for currentEgaDays.
        if (currentFlow.getFlowOrder().intValue() < 5) {
            EncounterData encounter = (EncounterData) currentEgaDaysEncounter;
            java.util.Date dateVisit = encounter.getDateVisit();
            GregorianCalendar egaCalendar = new GregorianCalendar();
            egaCalendar.setTime(dateVisit);
            GregorianCalendar today = new GregorianCalendar();
            java.util.Date d1 = egaCalendar.getTime();
            java.util.Date d2 = today.getTime();
            long l1 = d1.getTime();
            long l2 = d2.getTime();
            // there are 86400 seconds in a day
            long diffSeconds = (l2 - l1) / 1000;
            long diffDays = diffSeconds / 86400;
            Integer egaDateDiff = new Integer(String.valueOf(diffDays));
            egaToday = egaToday + diffDays;
        }
        return egaToday;
    }

	public String getCurrentFlowEncounterUuid() {
		return currentFlowEncounterUuid;
	}

	public void setCurrentFlowEncounterUuid(String currentFlowEncounterUuid) {
		this.currentFlowEncounterUuid = currentFlowEncounterUuid;
	}

	public String getCurrentPregnancyUuid() {
		return currentPregnancyUuid;
	}

	public void setCurrentPregnancyUuid(String currentPregnancyUuid) {
		this.currentPregnancyUuid = currentPregnancyUuid;
	}

	public String getCurrentPregnancyEncounterUuid() {
		return currentPregnancyEncounterUuid;
	}

	public void setCurrentPregnancyEncounterUuid(
			String currentPregnancyEncounterUuid) {
		this.currentPregnancyEncounterUuid = currentPregnancyEncounterUuid;
	}

	public String getCurrentLmpDateEncounterUuid() {
		return currentLmpDateEncounterUuid;
	}

	public void setCurrentLmpDateEncounterUuid(String currentLmpDateEncounterUuid) {
		this.currentLmpDateEncounterUuid = currentLmpDateEncounterUuid;
	}

	public String getCurrentEgaDaysEncounterUuid() {
		return currentEgaDaysEncounterUuid;
	}

	public void setCurrentEgaDaysEncounterUuid(String currentEgaDaysEncounterUuid) {
		this.currentEgaDaysEncounterUuid = currentEgaDaysEncounterUuid;
	}


}