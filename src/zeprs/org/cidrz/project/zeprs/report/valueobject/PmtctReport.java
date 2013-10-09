package org.cidrz.project.zeprs.report.valueobject;

import org.cidrz.project.zeprs.valueobject.report.gen.SmCounselingVisitReport;

public class PmtctReport extends SmCounselingVisitReport {

	private String regimen_visit_dateR;
	private String receivedRegimenR;
	private String regimenR;
	private String days_of_treatmentR;
	private String cd4testedR;
	private String who_stageR;
	private String referred_art_clinicR;
	private String enrolled_in_artR;
	private String clinic_enrolled_artR;

	public String getRegimen_visit_dateR() {
		return regimen_visit_dateR;
	}
	public void setRegimen_visit_dateR(String regimenVisitDateR) {
		regimen_visit_dateR = regimenVisitDateR;
	}
	public String getReceivedRegimenR() {
		return receivedRegimenR;
	}
	public void setReceivedRegimenR(String receivedRegimenR) {
		this.receivedRegimenR = receivedRegimenR;
	}
	public String getRegimenR() {
		return regimenR;
	}
	public void setRegimenR(String regimenR) {
		this.regimenR = regimenR;
	}
	public String getDays_of_treatmentR() {
		return days_of_treatmentR;
	}
	public void setDays_of_treatmentR(String daysOfTreatmentR) {
		days_of_treatmentR = daysOfTreatmentR;
	}
	public String getCd4testedR() {
		return cd4testedR;
	}
	public void setCd4testedR(String cd4testedR) {
		this.cd4testedR = cd4testedR;
	}
	public String getWho_stageR() {
		return who_stageR;
	}
	public void setWho_stageR(String whoStageR) {
		who_stageR = whoStageR;
	}
	public String getReferred_art_clinicR() {
		return referred_art_clinicR;
	}
	public void setReferred_art_clinicR(String referredArtClinicR) {
		referred_art_clinicR = referredArtClinicR;
	}
	public String getEnrolled_in_artR() {
		return enrolled_in_artR;
	}
	public void setEnrolled_in_artR(String enrolledInArtR) {
		enrolled_in_artR = enrolledInArtR;
	}
	public String getClinic_enrolled_artR() {
		return clinic_enrolled_artR;
	}
	public void setClinic_enrolled_artR(String clinicEnrolledArtR) {
		clinic_enrolled_artR = clinicEnrolledArtR;
	}

}
