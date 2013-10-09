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

import java.sql.Date;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Nov 29, 2005
 *         Time: 11:25:11 AM
 */
public class SafeMotherhoodEncounter {
    private Date dateVisit;
    private String labHaemoglobin1;
    private String labHaemoglobin2;
    private String labUrineProtein;
    private String labRPR1;
    private String labRPR2;
    private boolean treatmentAntihelmenthes;   // de-worming meds
    private boolean treatmentSP1;
    private boolean treatmentSP2;
    private boolean treatmentSP3;
    private boolean treatmentFolate;
    private boolean treatmentIron;
    private boolean treatmentMultivitamin;
    private boolean treatmentBPenicillin;
    private boolean treatmentErythromycin;
    private boolean treatmentMalariaSM1;
    private boolean treatmentMalariaSM2;
    private boolean treatmentMalariaSM3;
    private boolean itnUsed;
    private String antenatalReferralFrom;
    private String antenatalReferralTo;
    private String attendedBy;
    private String remarks;
    private Date datePostnatalVisitSixDays;
    private Date datePostnatalVisitSixWeeks;
    private String postnatalMotherBp;
    private String postnatalMotherTemperature;
    private String postnatalMotherPulse;
    private boolean postnatalMotherUrine;
    private String postnatalMotherPallor;
    private String postnatalMotherUterusContracted;
    private String postnatalMotherLochia;
    private String postnatalMotherBreastCondition;
    private String postnatalMotherITNUse;
    private String postnatalBabyFeeds;
    private String postnatalBabyCordStump;
    private String postnatalBabyTemperature;
    private String postnatalBabyRespiration;
    private String postnatalBabyWeight;
    private String postnatalReferralFrom;
    private String postnatalReferralTo;
    private boolean familyPlanningCounseling;
    private String familyPlanningMethods;
    private String familyPlanningAttendedBy;
    private String familyPlanningRemarks;
    private String staffName;

    public Date getDateVisit() {
        return dateVisit;
    }

    public void setDateVisit(Date dateVisit) {
        this.dateVisit = dateVisit;
    }

    public String getLabHaemoglobin1() {
        return labHaemoglobin1;
    }

    public void setLabHaemoglobin1(String labHaemoglobin1) {
        this.labHaemoglobin1 = labHaemoglobin1;
    }

    public String getLabHaemoglobin2() {
        return labHaemoglobin2;
    }

    public void setLabHaemoglobin2(String labHaemoglobin2) {
        this.labHaemoglobin2 = labHaemoglobin2;
    }

    public String getLabUrineProtein() {
        return labUrineProtein;
    }

    public void setLabUrineProtein(String labUrineProtein) {
        this.labUrineProtein = labUrineProtein;
    }

    public String getLabRPR1() {
        return labRPR1;
    }

    public void setLabRPR1(String labRPR1) {
        this.labRPR1 = labRPR1;
    }

    public String getLabRPR2() {
        return labRPR2;
    }

    public void setLabRPR2(String labRPR2) {
        this.labRPR2 = labRPR2;
    }

    public boolean isTreatmentAntihelmenthes() {
        return treatmentAntihelmenthes;
    }

    public void setTreatmentAntihelmenthes(boolean treatmentAntihelmenthes) {
        this.treatmentAntihelmenthes = treatmentAntihelmenthes;
    }

    public boolean isTreatmentSP1() {
        return treatmentSP1;
    }

    public void setTreatmentSP1(boolean treatmentSP1) {
        this.treatmentSP1 = treatmentSP1;
    }

    public boolean isTreatmentSP2() {
        return treatmentSP2;
    }

    public void setTreatmentSP2(boolean treatmentSP2) {
        this.treatmentSP2 = treatmentSP2;
    }

    public boolean isTreatmentSP3() {
        return treatmentSP3;
    }

    public void setTreatmentSP3(boolean treatmentSP3) {
        this.treatmentSP3 = treatmentSP3;
    }

    public boolean isTreatmentFolate() {
        return treatmentFolate;
    }

    public void setTreatmentFolate(boolean treatmentFolate) {
        this.treatmentFolate = treatmentFolate;
    }

    public boolean isTreatmentIron() {
        return treatmentIron;
    }

    public void setTreatmentIron(boolean treatmentIron) {
        this.treatmentIron = treatmentIron;
    }

    public boolean isTreatmentMultivitamin() {
        return treatmentMultivitamin;
    }

    public void setTreatmentMultivitamin(boolean treatmentMultivitamin) {
        this.treatmentMultivitamin = treatmentMultivitamin;
    }

    public boolean isTreatmentBPenicillin() {
        return treatmentBPenicillin;
    }

    public void setTreatmentBPenicillin(boolean treatmentBPenicillin) {
        this.treatmentBPenicillin = treatmentBPenicillin;
    }

    public boolean isTreatmentErythromycin() {
        return treatmentErythromycin;
    }

    public void setTreatmentErythromycin(boolean treatmentErythromycin) {
        this.treatmentErythromycin = treatmentErythromycin;
    }

    public boolean isTreatmentMalariaSM1() {
        return treatmentMalariaSM1;
    }

    public void setTreatmentMalariaSM1(boolean treatmentMalariaSM1) {
        this.treatmentMalariaSM1 = treatmentMalariaSM1;
    }

    public boolean isTreatmentMalariaSM2() {
        return treatmentMalariaSM2;
    }

    public void setTreatmentMalariaSM2(boolean treatmentMalariaSM2) {
        this.treatmentMalariaSM2 = treatmentMalariaSM2;
    }

    public boolean isTreatmentMalariaSM3() {
        return treatmentMalariaSM3;
    }

    public void setTreatmentMalariaSM3(boolean treatmentMalariaSM3) {
        this.treatmentMalariaSM3 = treatmentMalariaSM3;
    }

    public String getAntenatalReferralFrom() {
        return antenatalReferralFrom;
    }

    public void setAntenatalReferralFrom(String antenatalReferralFrom) {
        this.antenatalReferralFrom = antenatalReferralFrom;
    }

    public String getAntenatalReferralTo() {
        return antenatalReferralTo;
    }

    public void setAntenatalReferralTo(String antenatalReferralTo) {
        this.antenatalReferralTo = antenatalReferralTo;
    }

    public String getAttendedBy() {
        return attendedBy;
    }

    public void setAttendedBy(String attendedBy) {
        this.attendedBy = attendedBy;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getDatePostnatalVisitSixDays() {
        return datePostnatalVisitSixDays;
    }

    public void setDatePostnatalVisitSixDays(Date datePostnatalVisitSixDays) {
        this.datePostnatalVisitSixDays = datePostnatalVisitSixDays;
    }

    public Date getDatePostnatalVisitSixWeeks() {
        return datePostnatalVisitSixWeeks;
    }

    public void setDatePostnatalVisitSixWeeks(Date datePostnatalVisitSixWeeks) {
        this.datePostnatalVisitSixWeeks = datePostnatalVisitSixWeeks;
    }

    public String getPostnatalMotherBp() {
        return postnatalMotherBp;
    }

    public void setPostnatalMotherBp(String postnatalMotherBp) {
        this.postnatalMotherBp = postnatalMotherBp;
    }

    public String getPostnatalMotherTemperature() {
        return postnatalMotherTemperature;
    }

    public void setPostnatalMotherTemperature(String postnatalMotherTemperature) {
        this.postnatalMotherTemperature = postnatalMotherTemperature;
    }

    public String getPostnatalMotherPulse() {
        return postnatalMotherPulse;
    }

    public void setPostnatalMotherPulse(String postnatalMotherPulse) {
        this.postnatalMotherPulse = postnatalMotherPulse;
    }

    public boolean isPostnatalMotherUrine() {
        return postnatalMotherUrine;
    }

    public void setPostnatalMotherUrine(boolean postnatalMotherUrine) {
        this.postnatalMotherUrine = postnatalMotherUrine;
    }

    public String getPostnatalMotherPallor() {
        return postnatalMotherPallor;
    }

    public void setPostnatalMotherPallor(String postnatalMotherPallor) {
        this.postnatalMotherPallor = postnatalMotherPallor;
    }

    public String getPostnatalMotherUterusContracted() {
        return postnatalMotherUterusContracted;
    }

    public void setPostnatalMotherUterusContracted(String postnatalMotherUterusContracted) {
        this.postnatalMotherUterusContracted = postnatalMotherUterusContracted;
    }

    public String getPostnatalMotherLochia() {
        return postnatalMotherLochia;
    }

    public void setPostnatalMotherLochia(String postnatalMotherLochia) {
        this.postnatalMotherLochia = postnatalMotherLochia;
    }

    public String getPostnatalMotherBreastCondition() {
        return postnatalMotherBreastCondition;
    }

    public void setPostnatalMotherBreastCondition(String postnatalMotherBreastCondition) {
        this.postnatalMotherBreastCondition = postnatalMotherBreastCondition;
    }

    public String getPostnatalMotherITNUse() {
        return postnatalMotherITNUse;
    }

    public void setPostnatalMotherITNUse(String postnatalMotherITNUse) {
        this.postnatalMotherITNUse = postnatalMotherITNUse;
    }

    public String getPostnatalBabyFeeds() {
        return postnatalBabyFeeds;
    }

    public void setPostnatalBabyFeeds(String postnatalBabyFeeds) {
        this.postnatalBabyFeeds = postnatalBabyFeeds;
    }

    public String getPostnatalBabyCordStump() {
        return postnatalBabyCordStump;
    }

    public void setPostnatalBabyCordStump(String postnatalBabyCordStump) {
        this.postnatalBabyCordStump = postnatalBabyCordStump;
    }

    public String getPostnatalBabyTemperature() {
        return postnatalBabyTemperature;
    }

    public void setPostnatalBabyTemperature(String postnatalBabyTemperature) {
        this.postnatalBabyTemperature = postnatalBabyTemperature;
    }

    public String getPostnatalBabyRespiration() {
        return postnatalBabyRespiration;
    }

    public void setPostnatalBabyRespiration(String postnatalBabyRespiration) {
        this.postnatalBabyRespiration = postnatalBabyRespiration;
    }

    public String getPostnatalBabyWeight() {
        return postnatalBabyWeight;
    }

    public void setPostnatalBabyWeight(String postnatalBabyWeight) {
        this.postnatalBabyWeight = postnatalBabyWeight;
    }

    public String getPostnatalReferralFrom() {
        return postnatalReferralFrom;
    }

    public void setPostnatalReferralFrom(String postnatalReferralFrom) {
        this.postnatalReferralFrom = postnatalReferralFrom;
    }

    public String getPostnatalReferralTo() {
        return postnatalReferralTo;
    }

    public void setPostnatalReferralTo(String postnatalReferralTo) {
        this.postnatalReferralTo = postnatalReferralTo;
    }

    public boolean isItnUsed() {
        return itnUsed;
    }

    public void setItnUsed(boolean itnUsed) {
        this.itnUsed = itnUsed;
    }

    public boolean isFamilyPlanningCounseling() {
        return familyPlanningCounseling;
    }

    public void setFamilyPlanningCounseling(boolean familyPlanningCounseling) {
        this.familyPlanningCounseling = familyPlanningCounseling;
    }

    public String getFamilyPlanningMethods() {
        return familyPlanningMethods;
    }

    public void setFamilyPlanningMethods(String familyPlanningMethods) {
        this.familyPlanningMethods = familyPlanningMethods;
    }

    public String getFamilyPlanningAttendedBy() {
        return familyPlanningAttendedBy;
    }

    public void setFamilyPlanningAttendedBy(String familyPlanningAttendedBy) {
        this.familyPlanningAttendedBy = familyPlanningAttendedBy;
    }

    public String getFamilyPlanningRemarks() {
        return familyPlanningRemarks;
    }

    public void setFamilyPlanningRemarks(String familyPlanningRemarks) {
        this.familyPlanningRemarks = familyPlanningRemarks;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
}
