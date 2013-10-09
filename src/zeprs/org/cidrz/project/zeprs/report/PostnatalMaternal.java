package org.cidrz.project.zeprs.report;

import org.cidrz.project.zeprs.valueobject.report.gen.PostnatalMaternalVisitReport;

public class PostnatalMaternal extends PostnatalMaternalVisitReport {

	private String diagnosis;

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

}
