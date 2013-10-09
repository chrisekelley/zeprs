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

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Nov 28, 2005
 *         Time: 5:00:40 PM
 */
public class IntegratedVctPatient {

    private Long patientId;
    private String smRegisterNumber;
    private boolean couple;
    private boolean newAncClient;
    private boolean repeatAncClient;
    private boolean tookTestThisPeriod;
    private boolean tookTestFirstVisit;
    private boolean tookTestRevisit;
    private String testResult;
    private boolean consentedToArv;
    private boolean givenArv;
    private boolean optedFor6MonthsEbf;
    private boolean babyTest18months;
    private Integer babyTestResult;
    private boolean ageGroupUnder5;
    private boolean ageGroup1_14;
    private boolean ageGroup15_19;
    private boolean ageGroup20_24;
    private boolean ageGroup25_34;
    private boolean ageGroup35_39;
    private boolean ageGroup40_44;
    private boolean ageGroup45_49;
    private boolean ageGroup50plus;
    private boolean maritalStatus_Single_M;
    private boolean maritalStatus_Single_F;
    private boolean maritalStatus_Married_M;
    private boolean maritalStatus_Married_F;
    private boolean postTest_ResultsAvailableSameDay;
    private boolean postTest_ResultsCollected;
    private boolean postTest_AttendedPostTestCounseling;
    private boolean postTest_ReferredforArtService;
    private Long currentPregnancyId;



    public String getSmRegisterNumber() {
        return smRegisterNumber;
    }

    public void setSmRegisterNumber(String smRegisterNumber) {
        this.smRegisterNumber = smRegisterNumber;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public boolean isCouple() {
        return couple;
    }

    public void setCouple(boolean couple) {
        this.couple = couple;
    }

    public boolean isNewAncClient() {
        return newAncClient;
    }

    public void setNewAncClient(boolean newAncClient) {
        this.newAncClient = newAncClient;
    }

    public boolean isRepeatAncClient() {
        return repeatAncClient;
    }

    public void setRepeatAncClient(boolean repeatAncClient) {
        this.repeatAncClient = repeatAncClient;
    }

    public boolean isTookTestThisPeriod() {
        return tookTestThisPeriod;
    }

    public void setTookTestThisPeriod(boolean tookTestThisPeriod) {
        this.tookTestThisPeriod = tookTestThisPeriod;
    }

    public boolean isTookTestFirstVisit() {
        return tookTestFirstVisit;
    }

    public void setTookTestFirstVisit(boolean tookTestFirstVisit) {
        this.tookTestFirstVisit = tookTestFirstVisit;
    }

    public boolean isTookTestRevisit() {
        return tookTestRevisit;
    }

    public void setTookTestRevisit(boolean tookTestRevisit) {
        this.tookTestRevisit = tookTestRevisit;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public boolean isConsentedToArv() {
        return consentedToArv;
    }

    public void setConsentedToArv(boolean consentedToArv) {
        this.consentedToArv = consentedToArv;
    }

    public boolean isGivenArv() {
        return givenArv;
    }

    public void setGivenArv(boolean givenArv) {
        this.givenArv = givenArv;
    }

    public boolean isOptedFor6MonthsEbf() {
        return optedFor6MonthsEbf;
    }

    public void setOptedFor6MonthsEbf(boolean optedFor6MonthsEbf) {
        this.optedFor6MonthsEbf = optedFor6MonthsEbf;
    }

    public boolean isBabyTest18months() {
        return babyTest18months;
    }

    public void setBabyTest18months(boolean babyTest18months) {
        this.babyTest18months = babyTest18months;
    }

    public Integer getBabyTestResult() {
        return babyTestResult;
    }

    public void setBabyTestResult(Integer babyTestResult) {
        this.babyTestResult = babyTestResult;
    }

    public boolean isAgeGroupUnder5() {
        return ageGroupUnder5;
    }

    public void setAgeGroupUnder5(boolean ageGroupUnder5) {
        this.ageGroupUnder5 = ageGroupUnder5;
    }

    public boolean isAgeGroup1_14() {
        return ageGroup1_14;
    }

    public void setAgeGroup1_14(boolean ageGroup1_14) {
        this.ageGroup1_14 = ageGroup1_14;
    }

    public boolean isAgeGroup15_19() {
        return ageGroup15_19;
    }

    public void setAgeGroup15_19(boolean ageGroup15_19) {
        this.ageGroup15_19 = ageGroup15_19;
    }

    public boolean isAgeGroup20_24() {
        return ageGroup20_24;
    }

    public void setAgeGroup20_24(boolean ageGroup20_24) {
        this.ageGroup20_24 = ageGroup20_24;
    }

    public boolean isAgeGroup25_34() {
        return ageGroup25_34;
    }

    public void setAgeGroup25_34(boolean ageGroup25_34) {
        this.ageGroup25_34 = ageGroup25_34;
    }

    public boolean isAgeGroup35_39() {
        return ageGroup35_39;
    }

    public void setAgeGroup35_39(boolean ageGroup35_39) {
        this.ageGroup35_39 = ageGroup35_39;
    }

    public boolean isAgeGroup40_44() {
        return ageGroup40_44;
    }

    public void setAgeGroup40_44(boolean ageGroup40_44) {
        this.ageGroup40_44 = ageGroup40_44;
    }

    public boolean isAgeGroup45_49() {
        return ageGroup45_49;
    }

    public void setAgeGroup45_49(boolean ageGroup45_49) {
        this.ageGroup45_49 = ageGroup45_49;
    }

    public boolean isAgeGroup50plus() {
        return ageGroup50plus;
    }

    public void setAgeGroup50plus(boolean ageGroup50plus) {
        this.ageGroup50plus = ageGroup50plus;
    }

    public boolean isMaritalStatus_Single_M() {
        return maritalStatus_Single_M;
    }

    public void setMaritalStatus_Single_M(boolean maritalStatus_Single_M) {
        this.maritalStatus_Single_M = maritalStatus_Single_M;
    }

    public boolean isMaritalStatus_Single_F() {
        return maritalStatus_Single_F;
    }

    public void setMaritalStatus_Single_F(boolean maritalStatus_Single_F) {
        this.maritalStatus_Single_F = maritalStatus_Single_F;
    }

    public boolean isMaritalStatus_Married_M() {
        return maritalStatus_Married_M;
    }

    public void setMaritalStatus_Married_M(boolean maritalStatus_Married_M) {
        this.maritalStatus_Married_M = maritalStatus_Married_M;
    }

    public boolean isMaritalStatus_Married_F() {
        return maritalStatus_Married_F;
    }

    public void setMaritalStatus_Married_F(boolean maritalStatus_Married_F) {
        this.maritalStatus_Married_F = maritalStatus_Married_F;
    }

    public boolean isPostTest_ResultsAvailableSameDay() {
        return postTest_ResultsAvailableSameDay;
    }

    public void setPostTest_ResultsAvailableSameDay(boolean postTest_ResultsAvailableSameDay) {
        this.postTest_ResultsAvailableSameDay = postTest_ResultsAvailableSameDay;
    }

    public boolean isPostTest_ResultsCollected() {
        return postTest_ResultsCollected;
    }

    public void setPostTest_ResultsCollected(boolean postTest_ResultsCollected) {
        this.postTest_ResultsCollected = postTest_ResultsCollected;
    }

    public boolean isPostTest_AttendedPostTestCounseling() {
        return postTest_AttendedPostTestCounseling;
    }

    public void setPostTest_AttendedPostTestCounseling(boolean postTest_AttendedPostTestCounseling) {
        this.postTest_AttendedPostTestCounseling = postTest_AttendedPostTestCounseling;
    }

    public boolean isPostTest_ReferredforArtService() {
        return postTest_ReferredforArtService;
    }

    public void setPostTest_ReferredforArtService(boolean postTest_ReferredforArtService) {
        this.postTest_ReferredforArtService = postTest_ReferredforArtService;
    }

    public Long getCurrentPregnancyId() {
        return currentPregnancyId;
    }

    public void setCurrentPregnancyId(Long currentPregnancyId) {
        this.currentPregnancyId = currentPregnancyId;
    }
}
