package org.cidrz.project.zeprs.report.valueobject;

import java.util.ArrayList;
import java.util.List;

import org.cidrz.project.zeprs.valueobject.report.gen.LabTestReport;
import org.cidrz.project.zeprs.valueobject.report.gen.PostnatalInfantReport;

public class ChildVisitReport extends PostnatalInfantReport {

	private String initial_nevirapine_doseR;
	private String breast_feeding_well_518R;
	private String rbd_home_regimenR;
	private String dosage_nevirapine_555R;
	private String treatment_on_discharge_562R;
	private String problems_comments_557R;
	private String birth_record_given_561R;
	private String urine_passed_1181R;
	private String immunisation_desc_556R;
    private List<LabTestReport> labs = new ArrayList();


	public String getInitial_nevirapine_doseR() {
		return initial_nevirapine_doseR;
	}
	public void setInitial_nevirapine_doseR(String initialNevirapineDoseR) {
		initial_nevirapine_doseR = initialNevirapineDoseR;
	}
	public String getBreast_feeding_well_518R() {
		return breast_feeding_well_518R;
	}
	public void setBreast_feeding_well_518R(String breastFeedingWell_518R) {
		breast_feeding_well_518R = breastFeedingWell_518R;
	}
	public String getRbd_home_regimenR() {
		return rbd_home_regimenR;
	}
	public void setRbd_home_regimenR(String rbdHomeRegimenR) {
		rbd_home_regimenR = rbdHomeRegimenR;
	}
	public String getDosage_nevirapine_555R() {
		return dosage_nevirapine_555R;
	}
	public void setDosage_nevirapine_555R(String dosageNevirapine_555R) {
		dosage_nevirapine_555R = dosageNevirapine_555R;
	}
	public String getTreatment_on_discharge_562R() {
		return treatment_on_discharge_562R;
	}
	public void setTreatment_on_discharge_562R(String treatmentOnDischarge_562R) {
		treatment_on_discharge_562R = treatmentOnDischarge_562R;
	}
	public String getProblems_comments_557R() {
		return problems_comments_557R;
	}
	public void setProblems_comments_557R(String problemsComments_557R) {
		problems_comments_557R = problemsComments_557R;
	}
	public String getBirth_record_given_561R() {
		return birth_record_given_561R;
	}
	public void setBirth_record_given_561R(String birthRecordGiven_561R) {
		birth_record_given_561R = birthRecordGiven_561R;
	}
	public String getUrine_passed_1181R() {
		return urine_passed_1181R;
	}
	public void setUrine_passed_1181R(String urinePassed_1181R) {
		urine_passed_1181R = urinePassed_1181R;
	}
	public List<LabTestReport> getLabs() {
		return labs;
	}
	public void setLabs(List<LabTestReport> labs) {
		this.labs = labs;
	}
	public String getImmunisation_desc_556R() {
		return immunisation_desc_556R;
	}
	public void setImmunisation_desc_556R(String immunisationDesc_556R) {
		immunisation_desc_556R = immunisationDesc_556R;
	}

}
