/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.project.zeprs.valueobject.gen.LabTest;
import org.cidrz.project.zeprs.valueobject.lims.impl.PatientInformation;
import org.cidrz.project.zeprs.valueobject.lims.impl.PatientObservations;
import org.cidrz.project.zeprs.valueobject.lims.utils.ImportRecord;
import org.cidrz.project.zeprs.valueobject.report.gen.ChemistryReport;
import org.cidrz.project.zeprs.valueobject.report.gen.HemotologyReport;
import org.cidrz.project.zeprs.valueobject.report.gen.LiverfunctionReport;
import org.cidrz.project.zeprs.valueobject.report.gen.UrinalysisReport;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.struts.action.FormDisplayAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.Task;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Nov 10, 2005
 *         Time: 3:15:02 PM
 */
public class LabTestDAO {

    /**
     * If dateLabResults is null, then it's a pending lab test.
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @return list of all pending lab studies
     * @throws ServletException
     * @throws SQLException
     */
    public static List getAll(Connection conn, Long patientId, Long pregnancyId) throws  ServletException, SQLException {
        List items;
        String sql = "SELECT l.labtype AS label, true AS incomplete, encounter.id AS encounterId, encounter.patient_id AS patientId,\n" +
                "encounter.created AS 'auditInfo.created', site.site_name AS siteName\n" +
                "FROM encounter\n" +
                "JOIN labtest l ON encounter.id = l.id\n" +
                "LEFT JOIN site ON site.id=encounter.site_id\n" +
                "WHERE encounter.patient_id=?\n" +
                "AND encounter.pregnancy_id=?\n" +
                "AND l.dateLabResults Is Null;";
        Class clazz = Task.class;
        ArrayList values = new ArrayList();
        values.add(patientId);
        values.add(pregnancyId);
        items = DatabaseUtils.getList(conn, clazz, sql, values);
        return items;
    }

    /**
     * Fetch records for this patient from the LIMS database
     * @param conn from SQL Server
     * @param zeprsId
     * @return
     * @throws ServletException
     * @throws SQLException
     */
    public static List getLims(Connection conn, String zeprsId) throws  ServletException, SQLException {
    	List items;
    	String sql = "SELECT ObservationIdentifier AS observationIdentifier, ObservationText AS observationText, " +
    			"AbnormalFlags AS abnormalFlags, ObservationValue AS observationValue,\n" +
    			"Units AS units, ReferencesRange AS referencesRange, ObservationResultStatus AS observationResultStatus\n" +
    			"ObervationDate AS observationDate, ObservationId AS observationId\n" +
    			"FROM PatientObservations\n" +
    			"WHERE natID=?";
    	Class clazz = PatientObservations.class;
    	ArrayList values = new ArrayList();
    	values.add(zeprsId);
    	items = DatabaseUtils.getList(conn, clazz, sql, values);
    	return items;
    }

    /**
     * get most recent LIMS records filtered by observationId
     * @param conn
     * @param observationId - lastRecord
     * @return list of LIMS records
     * @throws ServletException
     * @throws SQLException
     */
    public static List<PatientObservations> getLimsRecentPatientObservations(Connection conn, Long observationId) throws  ServletException, SQLException {
    	List<PatientObservations> items;
    	String sql = "SELECT ObservationIdentifier AS observationIdentifier, ObservationText AS observationText, " +
    	"AbnormalFlags AS abnormalFlags, ObservationValue AS observationValue,\n" +
    	"Units AS units, ReferencesRange AS referencesRange, ObservationResultStatus AS observationResultStatus\n"  +
		"ObervationDate AS observationDate, ObservationId AS observationId\n" +
    	"FROM PatientObservations\n" +
    	"WHERE observationId > ?";
    	Class clazz = PatientObservations.class;
    	ArrayList values = new ArrayList();
    	values.add(observationId);
    	items = DatabaseUtils.getList(conn, clazz, sql, values);
    	return items;
    }

    /**
     * Fetches the most recent PatientInformation object
     * @param conn
     * @param natID
     * @return PatientInformation object
     * @throws ServletException
     * @throws SQLException
     * @throws ObjectNotFoundException
     */
    public static PatientInformation getLimsPatientInformation(Connection conn, Long natID) throws  ServletException, SQLException, ObjectNotFoundException {
    	PatientInformation item;
    	String sql = "SELECT TOP 1 FirstName AS firstName, Surname AS surname, DOB AS dob, Sex AS sex\n" +
    	"FROM PatientInformation\n" +
    	"WHERE natID = ?\n" +
    	"ORDER BY collection DESC";
    	Class clazz = PatientInformation.class;
    	ArrayList values = new ArrayList();
    	values.add(natID);
    	item = (PatientInformation) DatabaseUtils.getBean(conn, clazz, sql, values);
    	return item;
    }


    /**
     * Get records from most recent id. filter for CD4A and if status is F (final).
     * @param conn
     * @param observationDate
     * @return
     * @throws ServletException
     * @throws SQLException
     */
   public static List<ImportRecord> getLimsRecent(Connection conn, Timestamp observationDate) throws  ServletException, SQLException {
    	List<ImportRecord> items;
    	String sql = null;
    	Class clazz = ImportRecord.class;
    	ArrayList values = new ArrayList();
    	if (observationDate == null) {
        	 sql = "SELECT po.natID AS natID, po.ID AS id, ObservationIdentifier AS observationIdentifier, ObservationText AS observationText, " +
        	"AbnormalFlags AS abnormalFlags, ObservationValue AS observationValue,\n" +
        	"Units AS units, ReferencesRange AS referencesRange, ObservationResultStatus AS observationResultStatus,\n"  +
        	"ObservationDate AS observationDate, \n" +
        	"FirstName AS firstName, Surname AS surname, DOB AS dob, Sex AS sex,\n" +
        	"Collection AS collection, Reception AS reception\n" +
        	"FROM PatientObservations po, PatientInformation pi\n" +
        	"WHERE po.id = pi.id\n" +
        	"AND observationResultStatus = 'F'\n" +
        	"AND (observationIdentifier = 'CD4A' OR observationIdentifier = 'HGB')\n" +
        	"AND observationValue != 'NTEST'\n" +
         	"ORDER BY observationDate ASC";
    	} else {
        	 sql = "SELECT po.natID AS natID, po.ID AS id, ObservationIdentifier AS observationIdentifier, ObservationText AS observationText, " +
        	"AbnormalFlags AS abnormalFlags, ObservationValue AS observationValue,\n" +
        	"Units AS units, ReferencesRange AS referencesRange, ObservationResultStatus AS observationResultStatus,\n"  +
        	"ObservationDate AS observationDate, \n" +
        	"FirstName AS firstName, Surname AS surname, DOB AS dob, Sex AS sex,\n" +
        	"Collection AS collection, Reception AS reception\n" +
        	"FROM PatientObservations po, PatientInformation pi\n" +
        	"WHERE po.id = pi.id\n" +
        	"AND observationResultStatus = 'F'\n" +
        	"AND (observationIdentifier = 'CD4A' OR observationIdentifier = 'HGB')\n" +
        	"AND observationValue != 'NTEST'\n" +
        	"AND observationDate > ? " +
        	"ORDER BY observationDate ASC";
         	values.add(observationDate);
    	}
    	items = DatabaseUtils.getList(conn, clazz, sql, values);
    	return items;
    }

   /**
    * Updates values in a lab with values from LIMS imported data.
    * @param conn
    * @param labtest
    * @return
    * @throws Exception
    */
   public static int updateLab(Connection conn, LabTest labtest) throws Exception {
	   int result = 0;
	   String sql = "Update labtest\n" +
	   		"SET dateLabResults = ?, comments = ?, resultsNumeric = ?, cd4count = ?, lims_import_id = ?, exception_value=?\n" +
	   		"WHERE id = ?";
	   ArrayList values = new ArrayList();
	   values.add(labtest.getField1846());	// dateLabResults
	   values.add(labtest.getField1849());	// comments
	   values.add(labtest.getField1858());	// resultsNumeric
	   values.add(labtest.getField2004());	// cd4count
	   values.add(labtest.getField2143());	// lims_import_id
	   values.add(labtest.getField2149());	// exception_value
	   values.add(labtest.getId());	// cd4count
	   result = DatabaseUtils.update(conn, sql, values.toArray());
	   return result;
   }


   /**
    * Fetches a list of Labtests based on date range of when the records were created in zeprs
    * @param conn
    * @param beginDate
    * @param endDate
    * @return
    * @throws ServletException
    * @throws SQLException
    */
   public static List<LabTest> getLabTests(Connection conn, Date beginDate, Date endDate) throws ServletException, SQLException {
	   List<LabTest> items;
	   String sql = "SELECT encounter.id AS id, dateLabRequest AS field1844, labType AS field1845, " +
	   "dateLabResults AS field1846, results AS field1847, resultsNumeric AS field1858, cd4count AS field2004, " +
	   "comments AS field1849, extended_test_id AS field2044, lims_import_id AS field2143, exception_value AS field2149, " +
	   "patient_id AS patientId, form_id AS formId, flow_id AS flowId, date_visit AS dateVisit, encounter.pregnancy_id AS pregnancyId, " +
	   "encounter.last_modified AS lastModified, encounter.created AS created, " +
	   "surname, first_name AS firstName, district_patient_id AS zeprsId " +
	   "FROM labtest, encounter, patient  " +
	   "WHERE encounter.id = labtest.id " +
	   "AND patient.id = encounter.patient_id " +
	   "AND lims_import_id IS NOT NULL " +
	   "AND DATE(encounter.created) >= ? AND DATE(encounter.created) <= ? " +
	   "ORDER BY created DESC";
	   ArrayList values = new ArrayList();
	   values.add(beginDate);
	   values.add(endDate);

	   items = DatabaseUtils.getList(conn, LabTest.class, sql, values);
	   return items;
   }

   /**
    * Fetches a list of labtests to test for duplicates in an import.
    * @param conn
    * @param limsImportId
    * @param dateLabResults
    * @return
    * @throws ServletException
    * @throws SQLException
    */
   public static List<LabTest> getLabTests(Connection conn, String limsImportId, Timestamp dateLabResults) throws ServletException, SQLException {
	   List<LabTest> items;
	   String sql = "SELECT encounter.id AS id, dateLabRequest AS field1844, labType AS field1845, " +
	   "dateLabResults AS field1846, results AS field1847, resultsNumeric AS field1858, cd4count AS field2004, " +
	   "comments AS field1849, extended_test_id AS field2044, lims_import_id AS field2143, exception_value AS field2149, " +
	   "patient_id AS patientId, form_id AS formId, flow_id AS flowId, date_visit AS dateVisit, encounter.pregnancy_id AS pregnancyId, " +
	   "encounter.last_modified AS lastModified, encounter.created AS created, " +
	   "surname, first_name AS firstName, district_patient_id AS zeprsId " +
	   "FROM labtest, encounter, patient " +
	   "WHERE encounter.id = labtest.id " +
	   "AND patient.id = encounter.patient_id " +
	   "AND lims_import_id = ? " +
	   "AND dateLabResults = ? " +
	   "ORDER BY created DESC";
	   ArrayList values = new ArrayList();
	   values.add(limsImportId);
	   values.add(dateLabResults);
	   items = DatabaseUtils.getList(conn, LabTest.class, sql, values);
	   return items;
   }

/**
 * @param conn
 * @param extendedLabId TODO
 * @param labType TODO
 * @param currentPregnancyStatus
 * @throws NumberFormatException
 * @returns results of extended tests
 */
public static String getExtendedLabResults(Connection conn, Long extendedLabId, String labType) throws NumberFormatException {
	String results = null;


	if (extendedLabId != null) {
		EncounterData extendedLab = null;

		Long extendedLabFormId = null;
		char labTypeChar = labType.toLowerCase().charAt(0);
		switch (labTypeChar) {
		case 'h':
			extendedLabFormId = new Long("101");
			try {
				extendedLab = (HemotologyReport) EncountersDAO.getOneReportByIdResolved(conn, extendedLabId, extendedLabFormId, HemotologyReport.class);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ObjectNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case 'c':
			extendedLabFormId = new Long("102");
			try {
				extendedLab = (ChemistryReport) EncountersDAO.getOneReportByIdResolved(conn, extendedLabId, extendedLabFormId, ChemistryReport.class);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ObjectNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case 'l':
			extendedLabFormId = new Long("103");
			try {
				extendedLab = (LiverfunctionReport) EncountersDAO.getOneReportByIdResolved(conn, extendedLabId, extendedLabFormId, LiverfunctionReport.class);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ObjectNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case 'u':
			extendedLabFormId = new Long("104");
			try {
				extendedLab = (UrinalysisReport) EncountersDAO.getOneReportByIdResolved(conn, extendedLabId, extendedLabFormId, UrinalysisReport.class);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ObjectNotFoundException e) {
				e.printStackTrace();
			}
			break;
		}
		if (extendedLab != null) {
			results = FormDisplayAction.getEncounterValues(extendedLab, ", ");
		}
	}
	return results;
}


}
