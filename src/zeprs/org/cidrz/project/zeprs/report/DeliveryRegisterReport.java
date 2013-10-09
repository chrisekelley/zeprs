/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

/*
 * Created on Apr 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.cidrz.project.zeprs.report;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.report.valueobject.CurrentPregnancyStatus;
import org.cidrz.project.zeprs.valueobject.gen.DeliverySum;
import org.cidrz.project.zeprs.valueobject.report.PatientRegistrationClean;
import org.cidrz.project.zeprs.valueobject.report.gen.DeliverySumReport;
import org.cidrz.project.zeprs.valueobject.report.gen.NewbornEvalReport;
import org.cidrz.webapp.dynasite.dao.DemographicsDAO;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.dao.reports.ReportDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.valueobject.Patient;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ericl
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class DeliveryRegisterReport extends ZEPRSRegister {

	/**
	 * Commons Logging instance.
	 */
	private static Log log = LogFactory.getFactory().getInstance(DeliveryRegisterReport.class);

	private ArrayList patients = new ArrayList();
	private String reportMonth;
	private String reportYear;
	private HashMap patientMap;

	DeliveryRegisterReport() {

		reportMonth = null;
		reportYear = null;
	}

	/**
	 * @return Returns the reportMonth.
	 */
	public String getReportMonth() {
		return reportMonth;
	}

	/**
	 * @param reportMonth The reportMonth to set.
	 */
	public void setReportMonth(String reportMonth) {
		this.reportMonth = reportMonth;
	}

	/**
	 * @return Returns the reportYear.
	 */
	public String getReportYear() {
		return reportYear;
	}

	/**
	 * @param reportYear The reportYear to set.
	 */
	public void setReportYear(String reportYear) {
		this.reportYear = reportYear;
	}

	/**
	 * @return Returns the siteId from the super class.
	 */
	public int getSiteId() {
		return super.getSiteId();
	}

	/**
	 * @return Returns the siteName from the super class.
	 */
	public String getSiteName() {
		return super.getSiteName();
	}

	/**
	 * @return Returns the patients.
	 */
	public ArrayList getPatients() {
		return patients;
	}

	public void setPatients(ArrayList patients) {
		this.patients = patients;
	}

	/**
	 * @param patient The patients to set.
	 */
	public void addPatient(DeliveryRegisterPatient patient) {
		if (patients == null) {
			patients = new ArrayList();
		}
		patients.add(patient);
	}

	// patient map is (patientId, patientVisitList)
	public HashMap getPatientMap() {
		return patientMap;
	}

	public void setPatientMap(HashMap patientMap) {
		this.patientMap = patientMap;
	}

	public void getPatientRegister(Date beginDate, Date endDate, int siteId) {

		Connection conn = null;
		try {
			conn = ZEPRSUtils.getZEPRSConnection();


			List records = new ArrayList();
			try {
				records = ReportDAO.getAll(conn, beginDate, endDate, siteId, 66, DeliverySumReport.class);
				for (int i = 0; i < records.size(); i++) {
					DeliverySumReport deliverySumReport = (DeliverySumReport) records.get(i);
					DeliveryRegisterPatient drPatient = new DeliveryRegisterPatient();
					drPatient.setDeliverySumReport(deliverySumReport);
					// Now get the patient id and pregnancy id for this delivery
					Long patientId = deliverySumReport.getPatientId();
					Long pregnancyId = deliverySumReport.getPregnancyId();
					String ega = null;
					try {
						ega = PatientRecordUtils.getEgaDelivery(conn, patientId.intValue(), deliverySumReport.getDateVisit());
						drPatient.setEga(ega);
					} catch (ObjectNotFoundException e) {
						// ok, but rare.
					}
					PatientRegistrationClean pr = null;
					try {
						pr = (PatientRegistrationClean) DemographicsDAO.getOne(conn, patientId);
						if (pr.getBirthDate() != null) {
							Date birthDate = Date.valueOf(pr.getBirthDate());
							int age = ZEPRSUtils.getCurrentAge(birthDate);
							pr.setCurrentAge(age);
						}
						Patient patient = PatientDAO.getOne(conn, patientId);
						String zeprsId = patient.getDistrictPatientid();
						if (pr.getPatientIdNumber() == null) {
							pr.setPatientIdNumber(zeprsId);
						}
						drPatient.setPatientRegistration(pr);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (ObjectNotFoundException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					CurrentPregnancyStatus currentPregnancyStatus = new CurrentPregnancyStatus();
					try {
						Integer parity = PatientRecordUtils.getParity(conn, patientId);
						currentPregnancyStatus.setParity(parity);
						Integer gravida = PatientRecordUtils.getGravida(conn, patientId);
						currentPregnancyStatus.setGravida(gravida);
					} catch (SQLException e) {
						log.error(e);
					} catch (ServletException e) {
						log.error(e);
					}
					drPatient.setCurrentPregnancyStatus(currentPregnancyStatus);
					List children = PatientDAO.getChildren(conn, patientId, pregnancyId);
					for (int j = 0; j < children.size(); j++) {
						Patient child = (Patient) children.get(j);
						Long childId = child.getId();
						try {
							NewbornEvalReport newbornEval = (NewbornEvalReport) EncountersDAO.getResolvedOne(conn, childId, (long) 23, NewbornEvalReport.class);
							if (newbornEval.getDate_of_birthR() != null) {
								drPatient.setDateLastInfantDelivered(Date.valueOf(newbornEval.getDate_of_birthR()));
							}
							if (newbornEval.getTime_of_birth_1514R() != null) {
								drPatient.setTimeLastInfantDelivered(Time.valueOf(newbornEval.getTime_of_birth_1514R()));
							}
							DeliveryRegisterPatient infant = new DeliveryRegisterPatient();
							infant.setNewbornEvalReport(newbornEval);
							infant.setPatientId(childId);
							drPatient.addBaby(infant);
						} catch (ObjectNotFoundException e) {
							// log.error(e);
						}
					}

					long durationTotal = 0;
					// getDurationOfLabour takes a DeliverySum, not DeliverySumReport...
					DeliverySum deliverySumOne = null;
					try {
						deliverySumOne = (DeliverySum) EncountersDAO.getOne(conn, patientId, pregnancyId, new Long("66"));
						if (drPatient.getDateLastInfantDelivered() != null && drPatient.getTimeLastInfantDelivered() != null)
						{
							durationTotal = PatientRecordUtils.getDurationOfLabour(deliverySumOne, drPatient.getDateLastInfantDelivered(), drPatient.getTimeLastInfantDelivered());
						}
						drPatient.setDurationOfLabour(durationTotal);
					} catch (ObjectNotFoundException e) {
						log.debug("Error on DeliveryRegisterReport: Patient" + patientId + " does not have a Delivery Summary record.");
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					patients.add(drPatient);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (ServletException e) {
			log.error(e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
