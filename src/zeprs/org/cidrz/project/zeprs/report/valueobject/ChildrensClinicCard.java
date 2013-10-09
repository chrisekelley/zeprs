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
import org.cidrz.project.zeprs.valueobject.report.gen.PatientRegistrationReport;
import org.cidrz.project.zeprs.valueobject.report.gen.NewbornEvalReport;
import org.cidrz.project.zeprs.valueobject.report.gen.SafeMotherhoodCareReport;
import org.cidrz.project.zeprs.valueobject.report.gen.InfantDischargeSummaryReport;
import org.cidrz.project.zeprs.valueobject.gen.InfantDischargeSummary;

import java.util.List;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jan 16, 2006
 *         Time: 4:12:25 PM
 */
public class ChildrensClinicCard {

    private PatientRegistrationReport patientRegistration;
    private PatientRegistrationReport notherRegistration;
    private InfantDischargeSummaryReport infantDischargeSummary;
    private List postnatalInfantVisits;   // postnatalInfant
    private List probPostnatalInfantVisits;   // probPostnatalInfant
    private List siblings;  // list of previous pregnancies
    private NewbornEvalReport newbornEval;
    private SafeMotherhoodCareReport smc;

    public PatientRegistrationReport getPatientRegistration() {
        return patientRegistration;
    }

    public void setPatientRegistration(PatientRegistrationReport patientRegistration) {
        this.patientRegistration = patientRegistration;
    }

    public PatientRegistrationReport getNotherRegistration() {
        return notherRegistration;
    }

    public void setNotherRegistration(PatientRegistrationReport notherRegistration) {
        this.notherRegistration = notherRegistration;
    }

    public InfantDischargeSummaryReport getInfantDischargeSummary() {
        return infantDischargeSummary;
    }

    public void setInfantDischargeSummary(InfantDischargeSummaryReport infantDischargeSummary) {
        this.infantDischargeSummary = infantDischargeSummary;
    }

    public List getPostnatalInfantVisits() {
        return postnatalInfantVisits;
    }

    public void setPostnatalInfantVisits(List postnatalInfantVisits) {
        this.postnatalInfantVisits = postnatalInfantVisits;
    }

    public List getProbPostnatalInfantVisits() {
        return probPostnatalInfantVisits;
    }

    public void setProbPostnatalInfantVisits(List probPostnatalInfantVisits) {
        this.probPostnatalInfantVisits = probPostnatalInfantVisits;
    }

    public List getSiblings() {
        return siblings;
    }

    public void setSiblings(List siblings) {
        this.siblings = siblings;
    }

    public NewbornEvalReport getNewbornEval() {
        return newbornEval;
    }

    public void setNewbornEval(NewbornEvalReport newbornEval) {
        this.newbornEval = newbornEval;
    }

    public SafeMotherhoodCareReport getSmc() {
        return smc;
    }

    public void setSmc(SafeMotherhoodCareReport smc) {
        this.smc = smc;
    }


}
