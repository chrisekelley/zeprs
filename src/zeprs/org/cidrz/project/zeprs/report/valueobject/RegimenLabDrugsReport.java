package org.cidrz.project.zeprs.report.valueobject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.cidrz.project.zeprs.valueobject.report.gen.ArvRegimenReport;
import org.cidrz.project.zeprs.valueobject.report.gen.LabTestReport;

public class RegimenLabDrugsReport extends ArvRegimenReport {

	private String dateLabRequestR;
	private String labTypeR;
	private String dateLabResultsR;
	private String resultsR;
	private String resultsNumericR;
	private String cd4countR;
	private String exception_valueR;
	private String commentsR;
	private String extended_test_idR;
	private String lims_import_idR;
	private String dateDrugInterventionR;
	private String drugCommentsR;
	private String dispensedR;
	private String reason_not_dispensedR;
	private String drugNameR;
	private String drugTypeR;
	private String drug_type2R;
	private String drug_type3R;
	private String drug_type4R;
	private String drug_type5R;
	private String drug_type6R;
    private Date dateDispensed;
    private boolean folic;
    private boolean iron;
    private boolean deworming;
    private String malariaSp;
    private String other;
    private String referralReason;
    private String institution;
    private List<LabTestReport> labs = new ArrayList();


	public String getDateLabRequestR() {
		return dateLabRequestR;
	}
	public void setDateLabRequestR(String dateLabRequestR) {
		this.dateLabRequestR = dateLabRequestR;
	}
	public String getLabTypeR() {
		return labTypeR;
	}
	public void setLabTypeR(String labTypeR) {
		this.labTypeR = labTypeR;
	}
	public String getDateLabResultsR() {
		return dateLabResultsR;
	}
	public void setDateLabResultsR(String dateLabResultsR) {
		this.dateLabResultsR = dateLabResultsR;
	}
	public String getResultsR() {
		return resultsR;
	}
	public void setResultsR(String resultsR) {
		this.resultsR = resultsR;
	}
	public String getResultsNumericR() {
		return resultsNumericR;
	}
	public void setResultsNumericR(String resultsNumericR) {
		this.resultsNumericR = resultsNumericR;
	}
	public String getCd4countR() {
		return cd4countR;
	}
	public void setCd4countR(String cd4countR) {
		this.cd4countR = cd4countR;
	}
	public String getException_valueR() {
		return exception_valueR;
	}
	public void setException_valueR(String exceptionValueR) {
		exception_valueR = exceptionValueR;
	}
	public String getCommentsR() {
		return commentsR;
	}
	public void setCommentsR(String commentsR) {
		this.commentsR = commentsR;
	}
	public String getExtended_test_idR() {
		return extended_test_idR;
	}
	public void setExtended_test_idR(String extendedTestIdR) {
		extended_test_idR = extendedTestIdR;
	}
	public String getLims_import_idR() {
		return lims_import_idR;
	}
	public void setLims_import_idR(String limsImportIdR) {
		lims_import_idR = limsImportIdR;
	}
	public String getDateDrugInterventionR() {
		return dateDrugInterventionR;
	}
	public void setDateDrugInterventionR(String dateDrugInterventionR) {
		this.dateDrugInterventionR = dateDrugInterventionR;
	}
	public String getDrugCommentsR() {
		return drugCommentsR;
	}
	public void setDrugCommentsR(String drugCommentsR) {
		this.drugCommentsR = drugCommentsR;
	}
	public String getDispensedR() {
		return dispensedR;
	}
	public void setDispensedR(String dispensedR) {
		this.dispensedR = dispensedR;
	}
	public String getReason_not_dispensedR() {
		return reason_not_dispensedR;
	}
	public void setReason_not_dispensedR(String reasonNotDispensedR) {
		reason_not_dispensedR = reasonNotDispensedR;
	}
	public String getDrugNameR() {
		return drugNameR;
	}
	public void setDrugNameR(String drugNameR) {
		this.drugNameR = drugNameR;
	}
	public String getDrugTypeR() {
		return drugTypeR;
	}
	public void setDrugTypeR(String drugTypeR) {
		this.drugTypeR = drugTypeR;
	}
	public String getDrug_type2R() {
		return drug_type2R;
	}
	public void setDrug_type2R(String drugType2R) {
		drug_type2R = drugType2R;
	}
	public String getDrug_type3R() {
		return drug_type3R;
	}
	public void setDrug_type3R(String drugType3R) {
		drug_type3R = drugType3R;
	}
	public String getDrug_type4R() {
		return drug_type4R;
	}
	public void setDrug_type4R(String drugType4R) {
		drug_type4R = drugType4R;
	}
	public String getDrug_type5R() {
		return drug_type5R;
	}
	public void setDrug_type5R(String drugType5R) {
		drug_type5R = drugType5R;
	}
	public String getDrug_type6R() {
		return drug_type6R;
	}
	public void setDrug_type6R(String drugType6R) {
		drug_type6R = drugType6R;
	}
	public Date getDateDispensed() {
		return dateDispensed;
	}
	public void setDateDispensed(Date dateDispensed) {
		this.dateDispensed = dateDispensed;
	}
	public boolean isFolic() {
		return folic;
	}
	public void setFolic(boolean folic) {
		this.folic = folic;
	}
	public boolean isIron() {
		return iron;
	}
	public void setIron(boolean iron) {
		this.iron = iron;
	}
	public boolean isDeworming() {
		return deworming;
	}
	public void setDeworming(boolean deworming) {
		this.deworming = deworming;
	}
	public String getMalariaSp() {
		return malariaSp;
	}
	public void setMalariaSp(String malariaSp) {
		this.malariaSp = malariaSp;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getReferralReason() {
		return referralReason;
	}
	public void setReferralReason(String referralReason) {
		this.referralReason = referralReason;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public List<LabTestReport> getLabs() {
		return labs;
	}
	public void setLabs(List<LabTestReport> labs) {
		this.labs = labs;
	}

}
