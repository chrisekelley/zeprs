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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cidrz.project.zeprs.valueobject.report.PatientRegistrationClean;
import org.cidrz.project.zeprs.valueobject.report.gen.DeliverySumReport;
import org.cidrz.project.zeprs.valueobject.report.gen.InitialVisitReport;
import org.cidrz.project.zeprs.valueobject.report.gen.LabTestReport;
import org.cidrz.project.zeprs.valueobject.report.gen.MaternalDischargeReport;
import org.cidrz.project.zeprs.valueobject.report.gen.MedSurgHistReport;
import org.cidrz.project.zeprs.valueobject.report.gen.SafeMotherhoodCareReport;
import org.cidrz.webapp.dynasite.rules.impl.OutcomeImpl;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Dec 6, 2005
 *         Time: 11:13:16 AM
 */
public class AntenatalHistory {

    private PatientRegistrationClean patientRegistration;
    private MedSurgHistReport medicalSugHistory;
    private CurrentPregnancyStatus currentPregnancyStatus;
    private List pregnancies;
    private List<LabTestReport> labTests;
    private List rprScreens;
    private SafeMotherhoodCareReport smc;
    private List prevPregs;
    private List anteVisits;
    private DeliverySumReport deliverySum;
    private MaternalDischargeReport maternalDischarge;
    private List puerperium;
    private List newbornEvals;
    private Map encounterMap = new HashMap();
    private Map postnatalMap = new HashMap();
    private Map infantMap = new HashMap();
    private List<DrugsDispensed> drugs;
    private HivStamp hivStamp;
    private String medicalSugHistoryStr;
    private String currentMedicineStr;
    private String reportDate;
    private String probLaborVisits;
    private InitialVisitReport initialVisit;
    private List<RegimenLabDrugsReport> ancRegLabDrugRecords;
    private List<OutcomeImpl> referrals;
    private List<PmtctReport> councelVisits;

    public PatientRegistrationClean getPatientRegistration() {
        return patientRegistration;
    }

    public void setPatientRegistration(PatientRegistrationClean patientRegistration) {
        this.patientRegistration = patientRegistration;
    }

    public MedSurgHistReport getMedicalSugHistory() {
        return medicalSugHistory;
    }

    public void setMedicalSugHistory(MedSurgHistReport medicalSugHistory) {
        this.medicalSugHistory = medicalSugHistory;
    }

    public List getPregnancies() {
        return pregnancies;
    }

    public void setPregnancies(List pregnancies) {
        this.pregnancies = pregnancies;
    }

    public Map getEncounterMap() {
        return encounterMap;
    }

    public void setEncounterMap(Map encounterMap) {
        this.encounterMap = encounterMap;
    }

    public Map getPostnatalMap() {
        return postnatalMap;
    }

    public void setPostnatalMap(Map postnatalMap) {
        this.postnatalMap = postnatalMap;
    }

    public Map getInfantMap() {
        return infantMap;
    }

    public void setInfantMap(Map infantMap) {
        this.infantMap = infantMap;
    }

    public List getRprScreens() {
        return rprScreens;
    }

    public void setRprScreens(List rprScreens) {
        this.rprScreens = rprScreens;
    }

    public SafeMotherhoodCareReport getSmc() {
        return smc;
    }

    public void setSmc(SafeMotherhoodCareReport smc) {
        this.smc = smc;
    }

    public List getPrevPregs() {
        return prevPregs;
    }

    public void setPrevPregs(List prevPregs) {
        this.prevPregs = prevPregs;
    }

    public List getAnteVisits() {
        return anteVisits;
    }

    public void setAnteVisits(List anteVisits) {
        this.anteVisits = anteVisits;
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

    public List getNewbornEvals() {
        return newbornEvals;
    }

    public void setNewbornEvals(List newbornEvals) {
        this.newbornEvals = newbornEvals;
    }

    public HivStamp getHivStamp() {
        return hivStamp;
    }

    public void setHivStamp(HivStamp hivStamp) {
        this.hivStamp = hivStamp;
    }

    public String getMedicalSugHistoryStr() {
        return medicalSugHistoryStr;
    }

    public void setMedicalSugHistoryStr(String medicalSugHistoryStr) {
        this.medicalSugHistoryStr = medicalSugHistoryStr;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getProbLaborVisits() {
        return probLaborVisits;
    }

    public void setProbLaborVisits(String probLaborVisits) {
        this.probLaborVisits = probLaborVisits;
    }

	public String getCurrentMedicineStr() {
		return currentMedicineStr;
	}

	public void setCurrentMedicineStr(String currentMedicineStr) {
		this.currentMedicineStr = currentMedicineStr;
	}

	public CurrentPregnancyStatus getCurrentPregnancyStatus() {
		return currentPregnancyStatus;
	}

	public void setCurrentPregnancyStatus(CurrentPregnancyStatus currentPregnancyStatus) {
		this.currentPregnancyStatus = currentPregnancyStatus;
	}

	public InitialVisitReport getInitialVisit() {
		return initialVisit;
	}

	public void setInitialVisit(InitialVisitReport initialVisit) {
		this.initialVisit = initialVisit;
	}

	public List<DrugsDispensed> getDrugs() {
		return drugs;
	}

	public void setDrugs(List<DrugsDispensed> drugs) {
		this.drugs = drugs;
	}

	public List<RegimenLabDrugsReport> getAncRegLabDrugRecords() {
		return ancRegLabDrugRecords;
	}

	public void setAncRegLabDrugRecords(List<RegimenLabDrugsReport> ancRegLabDrugRecords) {
		this.ancRegLabDrugRecords = ancRegLabDrugRecords;
	}

	public List<OutcomeImpl> getReferrals() {
		return referrals;
	}

	public void setReferrals(List<OutcomeImpl> referrals) {
		this.referrals = referrals;
	}

	public List<PmtctReport> getCouncelVisits() {
		return councelVisits;
	}

	public void setCouncelVisits(List<PmtctReport> councelVisits) {
		this.councelVisits = councelVisits;
	}

	public List<LabTestReport> getLabTests() {
		return labTests;
	}

	public void setLabTests(List<LabTestReport> labTests) {
		this.labTests = labTests;
	}

}