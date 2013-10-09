/**
 *
 */
package org.cidrz.webapp.dynasite.utils.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.dbutils.QueryLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.valueobject.gen.LabTest;
import org.cidrz.project.zeprs.valueobject.lims.LastRecordImported;
import org.cidrz.project.zeprs.valueobject.lims.impl.PatientObservations;
import org.cidrz.project.zeprs.valueobject.lims.utils.ImportRecord;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.dao.FormDAO;
import org.cidrz.webapp.dynasite.dao.LabTestDAO;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.dao.PatientStatusDAO;
import org.cidrz.webapp.dynasite.dao.PregnancyDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.utils.XmlUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;

/**
 * @author ckelley
 * Imports records from LIMS lab system
 */
public class ImportLimsRecords {

	/**
	 * Commons Logging instance.
	 */
	private static Log log = LogFactory.getFactory().getInstance(ImportLimsRecords.class);

	/**
	 * Import records from LIMS into ZEPRS.
	 * Import only cd4 and hgb records.
	 * @param previewRecords - list records to be imported; do not persist.
	 * @param zeprsOnly - List only ZEPRS patients - many patients in LIMS do not have records in ZEPRS.
	 * @param populateList - If it's a Quartz process (automated cron job), do not populate the list
	 * @return TODO
	 * @throws Exception Error during method
	 */
	public static ArrayList<LabTest> importRecords(Boolean previewRecords, Boolean zeprsOnly, Boolean populateList) throws Exception {
		ArrayList<LabTest> list = new ArrayList<LabTest>();
		String info = "This file records data about the most recent record imported into ZEPRS from LIMS.";
		//String informationId = null;
		String observationId = null;
		Timestamp observationDate = null;
		Timestamp originalObservationDate = null;
		Timestamp dateCompletedImport = null;
		String username = "lims";
		Long siteId = new Long(47);
		Boolean fetchPatientNames = false;
		fetchPatientNames = true;
		// setup connections
		Connection limsConn = DatabaseUtils.getLimsConnection();
		Connection conn = DatabaseUtils.getZEPRSConnection("zepadmin");
		// setup structure to hold array natID:labTests
		HashMap<String,List<LabTest>> patientLabTestsMap = new HashMap<String,List<LabTest>>();
		// setup structure to hold array natIDt:labTests
		HashMap<String,SessionPatient> patientMap = new HashMap<String,SessionPatient>();
		// fetch id of most recent LIMS record
		String fileName = Constants.ARCHIVE_PATH + "lims.xml";
		File limsFile = new File(fileName);
		Long fileLastModified = limsFile.lastModified();
		LastRecordImported record = null;
		try {
			record = (LastRecordImported) XmlUtils.getOne(fileName);
			//informationId = record.getInformationId();
			observationId = record.getObservationId();
			observationDate = record.getObservationDate();
			originalObservationDate = record.getObservationDate();
			dateCompletedImport = record.getDateCompletedImport();
		} catch (FileNotFoundException e) {
			log.debug("No lims xml file - importing entire lims db.");
		} catch (IOException e) {
			log.debug("No lims xml file - importing entire lims db.");
		}
		// Fetch the form object - used for persisting new forms
		Long formId = new Long("87");
		Long flowId = new Long("102");
		Form formDef = (Form) DynaSiteObjects.getForms().get(formId);
		int countRecordsImported = 0;
		log.debug("Starting LIMS import");

		// query database for list of Observations
		List<ImportRecord> observations = LabTestDAO.getLimsRecent(limsConn, originalObservationDate);
		if (observations != null) {
			// now insert these records into ZEPRS.
			Map genQueries = QueryLoader.instance().load("/" + Constants.SQL_DEMO_PROPERTIES);
			for (Iterator iterator = observations.iterator(); iterator.hasNext();) {
				ImportRecord importRecord = (ImportRecord) iterator.next();
				observationId = importRecord.getId();
				String natID = importRecord.getNatID();
				String firstName = importRecord.getFirstName();
				String surname = importRecord.getSurname();
				Date collection = importRecord.getCollection();
				String observationIdentifier = importRecord.getObservationIdentifier();
				String observationValue = importRecord.getObservationValue();
				observationDate = importRecord.getObservationDate();
				StringBuffer commentsSbuf = new StringBuffer();
				// find the current pregnancy id and patient id
				// Fetch sessionpatient from the map
				SessionPatient sessionPatient = patientMap.get(natID);
				Long patientId = null;
				Long pregnancyId = null;
				log.debug("Importing observationId: " + observationId + " surname: " + surname + " collection: " + collection + " observationIdentifier: " + observationIdentifier );
				if (sessionPatient == null) {
					try {
						sessionPatient = PatientRecordUtils.getLimsImportPatient(conn, natID, fetchPatientNames);
						if (sessionPatient.getHivPositive() == null || sessionPatient.getHivPositive() !=1) {
							sessionPatient.setDisableLabImport(Byte.valueOf("1"));
						}
						String zeprsId = sessionPatient.getDistrictPatientid();
						int id = Integer.valueOf(zeprsId.substring(9,14));
						if (id < 1000) {
							sessionPatient.setDisableLabImport(Byte.valueOf("1"));
						}
						//log.debug("sessionPatient id: " + sessionPatient.getId());
					} catch (ObjectNotFoundException e) {
						// don't include this patient
						sessionPatient = new SessionPatient();
						sessionPatient.setDistrictPatientid(natID);
					}
					patientMap.put(natID, sessionPatient);
				}
				if (sessionPatient.getDisableLabImport() == null || (sessionPatient.getDisableLabImport() != null && sessionPatient.getDisableLabImport() == 0)) {
					patientId = sessionPatient.getId();
					pregnancyId = sessionPatient.getCurrentPregnancyId();
					if (pregnancyId == null) {
						log.debug(" - Null pregnancyId for patient " + patientId + ", observationId: " + observationId + ", observationDate: " + observationDate);
					} else {
						// see if this importRecord has already been imported
						List<LabTest> duplicateLabTests = LabTestDAO.getLabTests(conn, observationId, observationDate);
						if (duplicateLabTests != null && duplicateLabTests.size() > 0) {
							log.debug(" - Duplicate lab for patient " + patientId + ", observationId: " + observationId + ", observationDate: " + observationDate);
						} else {
							// create the importedLabTest
							LabTest importedLabTest = new LabTest();
							importedLabTest.setSessionPatient(sessionPatient);
							importedLabTest.setDateVisit(collection);
							importedLabTest.setField1844(collection);
							if (observationIdentifier.equals("CD4A")) {
								importedLabTest.setField1845(3042);
								Integer value = null;
								try {
									value = Integer.valueOf(observationValue);
									importedLabTest.setField2004(value);
								} catch (NumberFormatException e) {
									if (observationValue.equals("CLOT") || (observationValue.equals("NTEST"))) {
										importedLabTest.setField2149(observationValue);
									} else {
										int valueLen = observationValue.length();
										String safeValue = null;
										if (valueLen > 220) {
											safeValue = observationValue.substring(0,220);
										} else {
											safeValue = observationValue.substring(0,valueLen);
										}
										importedLabTest.setField2149(" - Incorrect value for lab: " + safeValue);
										log.debug(" - Cannot set value for cd4 count during LIMS import. ObservationValue: "+ observationValue + " natID: " + natID + " observationIdentifier: " + observationIdentifier);
									}
								}
							} else {
								importedLabTest.setField1845(2925);
								Float valueFlt = null;
								try {
									valueFlt = Float.valueOf(observationValue);
									importedLabTest.setField1858(valueFlt);
								} catch (NumberFormatException e) {
									if (observationValue.equals("CLOT") || (observationValue.equals("NTEST"))) {
										importedLabTest.setField2149(observationValue);
									} else {
										int valueLen = observationValue.length();
										String safeValue = null;
										if (valueLen > 220) {
											safeValue = observationValue.substring(0,220);
										} else {
											safeValue = observationValue.substring(0,valueLen);
										}
										importedLabTest.setField2149(" - Incorrect value for lab: " + safeValue);
										commentsSbuf.append(" - Unusual value for observationValue: ");
										commentsSbuf.append(observationValue);
										commentsSbuf.append(". ");
										log.error(" - Cannot set value for HB during LIMS import. ObservationValue: "+ observationValue + " natID: " + natID + " observationIdentifier: " + observationIdentifier);
									}
								}
							}
							importedLabTest.setField1846(observationDate);
							if (surname != null) {
								commentsSbuf.append(surname);
							}
							if (surname != null && firstName != null) {
								commentsSbuf.append(", " + firstName);
							} else {
								if (firstName != null) {
									commentsSbuf.append(firstName);
								}
							}
							if (commentsSbuf.length() > 0) {
								importedLabTest.setField1849(commentsSbuf.toString());
							}
							importedLabTest.setField2143(observationId);
							importedLabTest.setFormId(formId);
							importedLabTest.setFlowId(flowId);
							importedLabTest.setPatientId(patientId);
							importedLabTest.setPregnancyId(pregnancyId);
							// see if there are any outstanding records for this patient
							// get a list of current labs
							List<LabTest> patientLabTests = patientLabTestsMap.get(natID);
							if (patientLabTests == null & patientId != null) {
								//patientLabTests = LabTestDAO.getAll(conn, patientId, pregnancyId);
								patientLabTests = EncountersDAO.getAll(conn, patientId, pregnancyId, new Long("87"), LabTest.class);
								patientLabTestsMap.put(natID, patientLabTests);
							}
							Boolean updated = false;
							if (patientId == null) {
								if (zeprsOnly.equals(Boolean.FALSE)) {
									if (populateList.equals(Boolean.TRUE)) {
										list.add(importedLabTest);
									}
								}
							}
							// loop through the current lab tests and fill out the correct observations.
							if (patientLabTests != null && patientLabTests.size() >0) {
								for (LabTest labTest : patientLabTests) {
									Integer lab = labTest.getField1845();
									//if (observationId != labTest.getField2143()) {
									// cd4 = 3042; hb1 = 2925; hb2 = 2926
									// check if date of lab request is within 4 days of the LIMS observation
									long dateDiff = observationDate.getTime() - labTest.getField1844().getTime();
									long fourDays = 4 * (24 * 60 * 60 * 1000);
									if (dateDiff <= fourDays) {
										switch (lab) {
										case 3042:	// cd4
											if (labTest.getField2004() == null) {
												if (observationIdentifier.equals("CD4A")) {
													try {
														importedLabTest.setId(labTest.getId());
														// used for display of preview - shows that this is a previous lab test.
														importedLabTest.setField1844(labTest.getField1844());
														if (populateList.equals(Boolean.TRUE)) {
															list.add(importedLabTest);
														}
														if (previewRecords.equals(Boolean.FALSE)) {
															countRecordsImported++;
															LabTestDAO.updateLab(conn, importedLabTest);
															EncountersDAO.touchEncounterTable(conn, username, siteId, labTest.getId());
															PatientStatusDAO.touchPatientStatus(conn, null, username, patientId);
														}
														updated = true;
													} catch (Exception e) {
														log.error(" - Error saving lims import for patient " + patientId + ", observationId: " + observationId + ", observationDate: " + observationDate);
														e.printStackTrace();
													}
												}
											}
											break;
										case 2925:	// hb1
											if (labTest.getField1858() == null) {
												if (observationIdentifier.equals("HGB")) {
													try {
														importedLabTest.setId(labTest.getId());
														if (populateList.equals(Boolean.TRUE)) {
															list.add(importedLabTest);
														}
														if (previewRecords.equals(Boolean.FALSE)) {
															countRecordsImported++;
															LabTestDAO.updateLab(conn, importedLabTest);
															EncountersDAO.touchEncounterTable(conn, username, siteId, labTest.getId());
															PatientStatusDAO.touchPatientStatus(conn, null, username, patientId);
														}
														updated = true;
													} catch (Exception e) {
														log.error(" - Error saving lims import for patient " + patientId + ", observationId: " + observationId + ", observationDate: " + observationDate);
														e.printStackTrace();
													}
												}
											}
											break;
										case 2926:	// hb2
											if (labTest.getField1858() == null) {
												if (observationIdentifier.equals("HGB")) {
													try {
														importedLabTest.setId(labTest.getId());
														if (populateList.equals(Boolean.TRUE)) {
															list.add(importedLabTest);
														}
														if (previewRecords.equals(Boolean.FALSE)) {
															countRecordsImported++;
															LabTestDAO.updateLab(conn, importedLabTest);
															EncountersDAO.touchEncounterTable(conn, username, siteId, labTest.getId());
															PatientStatusDAO.touchPatientStatus(conn, null, username, patientId);
														}
														updated = true;
													} catch (Exception e) {
														log.error(" - Error saving lims import for patient " + patientId + ", observationId: " + observationId + ", observationDate: " + observationDate);
														e.printStackTrace();
													}
												}
											}
											break;
										default:
											break;
										}
									}
								}
							}
							if (patientId != null && updated == false) {
								// create a new LabTest record.
								if (observationValue != null) {
									if (populateList.equals(Boolean.TRUE)) {
										list.add(importedLabTest);
									}
									if (previewRecords.equals(Boolean.FALSE)) {
										countRecordsImported++;
										try {
											Long encounterId = FormDAO.createEncounter(conn, genQueries, importedLabTest, formDef, username, siteId, null);
											PatientStatusDAO.touchPatientStatus(conn, null, username, patientId);
										} catch (Exception e) {
											log.error(" - Error saving lims import for patient " + patientId + ", observationId: " + observationId + ", observationDate: " + observationDate);
											e.printStackTrace();
										}
									}
									updated = true;
								}
							}
						}	// end of test for duplicate labTests
					}
				} else {
					log.debug(" - Disabled lab import for " + sessionPatient.getDistrictPatientid());
					LabTest labtest = new LabTest();
					labtest.setFirstName(sessionPatient.getFirstName());
					labtest.setSurname(sessionPatient.getSurname());
					labtest.setField1849("Disabled lab import for " + sessionPatient.getDistrictPatientid());
					if (populateList.equals(Boolean.TRUE)) {
						list.add(labtest);
					}
				}
				if (iterator.hasNext() == false) {
					if (previewRecords.equals(Boolean.FALSE)) {
						if (record == null) {
							record = new LastRecordImported();
						}
						record.setDateCompletedImport(new Timestamp(System.currentTimeMillis()));
						//record.setInformationId(informationId);
						record.setObservationId(observationId);
						record.setObservationDate(observationDate);
						// save the lims.xml file
						try {
							log.debug("Saving lims.xml file");
							XmlUtils.save(record, fileName);
						} catch (IOException e) {
							log.error("Failed to save lims.xml file.");
						}
					}
				}
			}
			if (previewRecords.equals(Boolean.FALSE)) {
				if (observationDate.getTime() == originalObservationDate.getTime()) {
					// hmmm that is strange
					if (countRecordsImported > 0) {
						limsFile = new File(fileName);
						if (fileLastModified == limsFile.lastModified()) {
							// procesisng lims imports crashed and xml file was not created.
							log.debug("LIMS import process crashed and xml file was not created.");
							log.debug("LIMS records imported: " + countRecordsImported);
							log.debug("Updating lims.xml file.");
							if (record == null) {
								record = new LastRecordImported();
							}
							record.setDateCompletedImport(new Timestamp(System.currentTimeMillis()));
							//record.setInformationId(informationId);
							record.setObservationId(observationId);
							record.setObservationDate(observationDate);
							// save the lims.xml file
							try {
								XmlUtils.save(record, fileName);
							} catch (IOException e) {
								log.error("Failed to save lims.xml file.");
							}
						}
					}
				}
			}
		}
		if (list.size() > 0) {
			String pathSep = File.separator;
			String limsLog = Constants.ARCHIVE_PATH + "logs/lims-progress.xml";
			XmlUtils.save(list, limsLog);

		}
		return list;
	}
}
