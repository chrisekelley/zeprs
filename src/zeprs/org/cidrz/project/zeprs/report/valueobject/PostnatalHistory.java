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

import java.util.List;

import org.cidrz.project.zeprs.report.PostnatalMaternal;
import org.cidrz.project.zeprs.valueobject.report.PatientRegistrationClean;
import org.cidrz.project.zeprs.valueobject.report.gen.DeliverySumReport;
import org.cidrz.project.zeprs.valueobject.report.gen.MaternalDischargeReport;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Feb 9, 2006
 *         Time: 4:56:18 PM
 */
public class PostnatalHistory {

    private PatientRegistrationClean patientRegistration;

    private DeliverySumReport deliverySum;
    private MaternalDischargeReport maternalDischarge;
    private List puerperium;
    private List postnatalVisits;
    private String reportDate;
    private PregnancyReport pregnancyReport;
    private CurrentPregnancyStatus currentPregnancyStatus;
    private String reportPrinted;
    private HivStamp hivStamp;
    private List<PostnatalMaternal> postnatalMaternalVisits;
    private List<DrugsDispensed> postnatalDrugs;
    private List<RegimenLabDrugsReport> postRegLabDrugRecords;

    public PatientRegistrationClean getPatientRegistration() {
        return patientRegistration;
    }

    public void setPatientRegistration(PatientRegistrationClean patientRegistration) {
        this.patientRegistration = patientRegistration;
    }

    public DeliverySumReport getDeliverySum() {
        return deliverySum;
    }

    public void setDeliverySum(DeliverySumReport deliverySum) {
        this.deliverySum = deliverySum;
    }

    public MaternalDischargeReport getMaternalDischarge() {
        return maternalDischarge;
    }

    public void setMaternalDischarge(MaternalDischargeReport maternalDischarge) {
        this.maternalDischarge = maternalDischarge;
    }

    public List getPuerperium() {
        return puerperium;
    }

    public void setPuerperium(List puerperium) {
        this.puerperium = puerperium;
    }

    public List getPostnatalVisits() {
        return postnatalVisits;
    }

    public void setPostnatalVisits(List postnatalVisits) {
        this.postnatalVisits = postnatalVisits;
    }

    public PregnancyReport getPregnancyReport() {
        return pregnancyReport;
    }

    public void setPregnancyReport(PregnancyReport pregnancyReport) {
        this.pregnancyReport = pregnancyReport;
    }

    public CurrentPregnancyStatus getCurrentPregnancyStatus() {
        return currentPregnancyStatus;
    }

    public void setCurrentPregnancyStatus(CurrentPregnancyStatus currentPregnancyStatus) {
        this.currentPregnancyStatus = currentPregnancyStatus;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportPrinted() {
        return reportPrinted;
    }

    public void setReportPrinted(String reportPrinted) {
        this.reportPrinted = reportPrinted;
    }

    public HivStamp getHivStamp() {
        return hivStamp;
    }

    public void setHivStamp(HivStamp hivStamp) {
        this.hivStamp = hivStamp;
    }

	public List<PostnatalMaternal> getPostnatalMaternalVisits() {
		return postnatalMaternalVisits;
	}

	public void setPostnatalMaternalVisits(List<PostnatalMaternal> postnatalMaternalVisits) {
		this.postnatalMaternalVisits = postnatalMaternalVisits;
	}

	public List<DrugsDispensed> getPostnatalDrugs() {
		return postnatalDrugs;
	}

	public void setPostnatalDrugs(List<DrugsDispensed> postnatalDrugs) {
		this.postnatalDrugs = postnatalDrugs;
	}

	public List<RegimenLabDrugsReport> getPostRegLabDrugRecords() {
		return postRegLabDrugRecords;
	}

	public void setPostRegLabDrugRecords(List<RegimenLabDrugsReport> postRegLabDrugRecords) {
		this.postRegLabDrugRecords = postRegLabDrugRecords;
	}

}
