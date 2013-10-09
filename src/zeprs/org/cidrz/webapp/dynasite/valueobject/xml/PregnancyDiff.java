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

import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.webapp.dynasite.valueobject.AuditInfo;
import org.cidrz.webapp.dynasite.valueobject.Auditable;
import org.cidrz.webapp.dynasite.valueobject.Partograph;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PregnancyDiff implements Auditable {

    private Long patientId;
    private Date datePregnancyBegin;
    private EncounterData pregnancyBeginEncounter;
    private Date datePregnancyEnd;
    private EncounterDataDiff pregnancyEndEncounter;
    private AuditInfo auditInfo;
    private List encounters;
    private List activeProblems;
    private List inActiveProblems;
    private EncounterDataDiff labourAdmissionEncounter;
    private Date dateLabourAdmission;
    private List modifiedValues;   // List of values from encounter_value_archive
    private List infants = new ArrayList();   // list of infants and infant records for this pregnancy
    private Partograph partograph;
    private Map encounterDateMap;   // used in xml record output. Map of importEncounterId:lastModified

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Date getDatePregnancyBegin() {
        return datePregnancyBegin;
    }

    public void setDatePregnancyBegin(Date datePregnancyBegin) {
        this.datePregnancyBegin = datePregnancyBegin;
    }

    public EncounterData getPregnancyBeginEncounter() {
        return pregnancyBeginEncounter;
    }

    public void setPregnancyBeginEncounter(EncounterData pregnancyBeginEncounter) {
        this.pregnancyBeginEncounter = pregnancyBeginEncounter;
    }

    public Date getDatePregnancyEnd() {
        return datePregnancyEnd;
    }

    public void setDatePregnancyEnd(Date datePregnancyEnd) {
        this.datePregnancyEnd = datePregnancyEnd;
    }

    public EncounterDataDiff getPregnancyEndEncounter() {
        return pregnancyEndEncounter;
    }

    public void setPregnancyEndEncounter(EncounterDataDiff pregnancyEndEncounter) {
        this.pregnancyEndEncounter = pregnancyEndEncounter;
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

    public List getEncounters() {
        return encounters;
    }

    public void setEncounters(List encounters) {
        this.encounters = encounters;
    }

    public List getActiveProblems() {
        return activeProblems;
    }

    public void setActiveProblems(List activeProblems) {
        this.activeProblems = activeProblems;
    }

    public List getInActiveProblems() {
        return inActiveProblems;
    }

    public void setInActiveProblems(List inActiveProblems) {
        this.inActiveProblems = inActiveProblems;
    }

    public EncounterDataDiff getLabourAdmissionEncounter() {
        return labourAdmissionEncounter;
    }

    public void setLabourAdmissionEncounter(EncounterDataDiff labourAdmissionEncounter) {
        this.labourAdmissionEncounter = labourAdmissionEncounter;
    }

    public Date getDateLabourAdmission() {
        return dateLabourAdmission;
    }

    public void setDateLabourAdmission(Date dateLabourAdmission) {
        this.dateLabourAdmission = dateLabourAdmission;
    }

    public List getModifiedValues() {
        return modifiedValues;
    }

    public void setModifiedValues(List modifiedValues) {
        this.modifiedValues = modifiedValues;
    }

    public List getInfants() {
        return infants;
    }

    public void setInfants(List infants) {
        this.infants = infants;
    }

    public Partograph getPartograph() {
        return partograph;
    }

    public void setPartograph(Partograph partograph) {
        this.partograph = partograph;
    }

    public Map getEncounterDateMap() {
        return encounterDateMap;
    }

    public void setEncounterDateMap(Map encounterDateMap) {
        this.encounterDateMap = encounterDateMap;
    }

}
