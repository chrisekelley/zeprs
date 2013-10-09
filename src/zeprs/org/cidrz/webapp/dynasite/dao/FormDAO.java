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

// import ca.sandoval.util.PropertiesUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.dbutils.QueryLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.project.zeprs.valueobject.gen.*;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.DateUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.utils.XmlUtils;
import org.cidrz.webapp.dynasite.valueobject.*;
import javax.servlet.ServletException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Provide objects about forms
 *
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jun 8, 2005
 *         Time: 7:09:58 PM
 */
public class FormDAO {

    /**
     * Commons Logging instance.
     */

    private static Log log = LogFactory.getFactory().getInstance(FormDAO.class);

    /**
     * Create record. Create a transaction to ensure data integrity, create/set pregnancyId,
     * create the encounter, handle updates to patient_status and (if form 1, pregnancy)
     * Update pregnancy if this is observations or prob/labour form that triggers admission
     * Update referral status if this is a UTH-related form
     * Pregnancy conclusion form - creates a record in prevPregnancies
     * Extended Lab Tests - update extendedLabId in labtest table
     * Update values in patient table
     * Commit transaction
     *
     * @param conn
     * @param vo - must have created/modified info already initialized
     * @param formDef
     * @param currentFlowId
     * @param isImported - if record is imported as part of sync process
     */
    public static EncounterData create(Connection conn, EncounterData vo, String userName, Long siteId, Form formDef, Long currentFlowId, Boolean isImported) throws Exception {

        Map queries = QueryLoader.instance().load("/" + Constants.SQL_PATIENT_PROPERTIES);
        Map genQueries = QueryLoader.instance().load("/" + Constants.SQL_DEMO_PROPERTIES);
        Long patientId = null;
        Long pregnancyId = null;
        Long encounterId = null;
        Boolean newPregnancy = null;

        // start the transaction
        String sql = "START TRANSACTION;";
        // ArrayList array = new ArrayList();
        try {
            DatabaseUtils.create(conn, sql);

            /**
             * Either create new pregnancy or set the pregnancyId
             */
            if (vo.getFormId().intValue() == 1) {
                currentFlowId = new Long("9");
                Date dateVisit = vo.getDateVisit();
                patientId = createPatient(conn, queries, vo, userName, siteId, currentFlowId);   // Create record in patient and patient_status tables and get patientId
                // add patientId to the encounter object
                vo.setPatientId(patientId);
                // See if the pregnancy is already in the system
                // It may have already been imported by a patient record that was imported before this record.
                // In the case of an infant, the infant record may have already been imported before the mother.
                Pregnancy pregnancy = vo.getPregnancy();
                if (pregnancy != null) {
                    String pregnancyUuid = pregnancy.getUuid();
                	Pregnancy imported;
					try {
						imported = PregnancyDAO.getOne(conn, pregnancyUuid);
						pregnancyId = imported.getId();
                    	// update the pregnancy w/ the patientId in case the pregnancy was created from the infant record.
                    	PregnancyDAO.updatePatientId(conn, pregnancyUuid, patientId);
					} catch (ObjectNotFoundException e) {
                        //pregnancyId = PregnancyDAO.createPregnancy(conn, queries, vo, patientId, dateVisit, userName, siteId);
						pregnancyId = PregnancyDAO.importPregnancy(conn, queries, pregnancy, patientId);
					}
                } else {
                    pregnancyId = PregnancyDAO.createPregnancy(conn, queries, vo, patientId, dateVisit, userName, siteId);
                }

                // add pregnancyId to encounter object
                vo.setPregnancyId(pregnancyId);
                // now we can insert the encounter, and then update encounter id fields for patient_status and pregnancy records
            }

            // new pregnancy for a patient already in the system
            if ((vo.getPregnancyId() == null) || vo.getPregnancyId().intValue() == -1) {
                currentFlowId = new Long("2");
                newPregnancy = Boolean.TRUE;
                patientId = vo.getPatientId();
                Date dateVisit = vo.getDateVisit();
                // create the pregnancy
                pregnancyId = PregnancyDAO.createPregnancy(conn, queries, vo, patientId, dateVisit, userName, siteId);
                // add pregnancyId to encounter object
                vo.setPregnancyId(pregnancyId);
            }

            /**
             * Persist the encounter
             */
            encounterId = createEncounter(conn, genQueries, vo, formDef, userName, siteId, null);

            vo.setId(encounterId);
            // todo: the vo's siteId is probably already set. Delete next line?
            vo.setSiteId(siteId);

            /**
             * Handle updates to patient_status and (if form 1, pregnancy)
             */
            if ((vo.getFormId().intValue() == 1) || (newPregnancy == Boolean.TRUE)) {
                // Update pregnancy
                PregnancyDAO.updatePregnancyBeginData(conn, queries, encounterId, pregnancyId, vo.getUuid());
                Long thisPatientId = vo.getPatientId();
                Pregnancy pregnancy = PregnancyDAO.getOne(conn, pregnancyId);
            	vo.setPregnancy(pregnancy);
                // Update patient_status
                if (formDef.getId().intValue() == 82) {   // preg dating
                    thisPatientId = vo.getPatientId();
                    currentFlowId = new Long("2");  // History
                    PatientStatusDAO.updatePatientStatusNewPregDating(conn, queries, vo, pregnancyId, currentFlowId, encounterId, userName, siteId, thisPatientId);
                } else if (formDef.getId().intValue() == 1) {   // patient registration
                    PatientStatusDAO.updatePatientStatusPatientReg(conn, queries, vo, pregnancyId, encounterId, thisPatientId, userName, siteId, currentFlowId);
                } else {
                    PatientStatusDAO.updatePatientStatusNewPreg(conn, queries, vo, pregnancyId, encounterId, thisPatientId, userName, siteId, currentFlowId);
                }
            } else {
                // Update patient_status for other forms
                String sqlUpdateStatus = (String) queries.get("SQL_MODIFY_STATUS");
                currentFlowId = vo.getFlowId();
                Long thisPatientId = vo.getPatientId();
                if (pregnancyId == null) {
                	pregnancyId = vo.getPregnancyId();
                }
                Pregnancy pregnancy = PregnancyDAO.getOne(conn, pregnancyId);
            	vo.setPregnancy(pregnancy);
                // Pregnancy Dating form
                if (formDef.getId().intValue() == 82) {
                    thisPatientId = vo.getPatientId();
                    PregnancyDating pregDat = (PregnancyDating) vo;
                    PatientStatusDAO.updatePatientStatusPregDating(conn, queries, vo, currentFlowId, encounterId, userName, siteId, thisPatientId);
                } else if ((currentFlowId.intValue() == 2) || (currentFlowId.intValue() == 9))
                {     // if history or new patient
                    PatientStatusDAO.updatePatientStatus(conn, vo, currentFlowId, encounterId, userName, siteId, thisPatientId, sqlUpdateStatus);
                } else {
                    if ((vo.getFlowId().intValue() != 2) & (vo.getFlowId().intValue() != 6) & (vo.getFlowId().intValue() != 8) & (vo.getFlowId().intValue() != 102) & (vo.getFlowId().intValue() != 103) & (vo.getFlowId().intValue() != 100))
                    {
                        // if not history, labs, ultrasound, uth,  or unassigned
                        PatientStatusDAO.updatePatientStatus(conn, vo, currentFlowId, encounterId, userName, siteId, thisPatientId, sqlUpdateStatus);
                    } else {
                        patientId = vo.getPatientId();
                        PatientStatusDAO.touchPatientStatus(conn, vo, userName, siteId, patientId);
                    }
                }
            }
            int formId = formDef.getId().intValue();

            /**
             * Update pregnancy if this is observations or prob/labour form that triggers admission
             */

            if (formId == 65) {
                Date dateVisit = vo.getDateVisit();
                ProbLabor probLabour = (ProbLabor) vo;
                if (probLabour.getField325() != null && probLabour.getField325().intValue() >= 4) {
                    updatePregnancyLabourAdmission(conn, queries, vo, encounterId, dateVisit, vo.getPregnancyId());
                }
            } else if (formId == 17) {
                Date dateVisit = vo.getDateVisit();
                LatentFirstStageLabour observations = (LatentFirstStageLabour) vo;
                if (observations.getField325() != null && observations.getField325().intValue() >= 4) {
                    updatePregnancyLabourAdmission(conn, queries, vo, encounterId, dateVisit, vo.getPregnancyId());
                }
            }

            /**
             * Update referral status if this is a UTH-related form
             */

            if ((formId == 74) || (formId == 63) || (formId == 76)) {
                // UTH ante and postnatal summary discharge forms
                if (vo.getReferralId() != null) {
                    Long referralId = vo.getReferralId();
                    OutcomeDAO.discharge(conn, vo.getLastModified(), referralId, vo.getLastModifiedBy(), vo.getSiteId());
                }
            }

            /**
             * Pregnancy conclusion form - creates a record in prevPregnancies
             */
            if (formId == 71) {   //Pregnancy conclusion form
                patientId = vo.getPatientId();
                pregnancyId = vo.getPregnancyId();
                if (isImported == null) {
                    createPregnancyRecord(conn, genQueries, vo, formDef, userName, encounterId, userName, siteId, patientId, pregnancyId);
                } else {
                	// no longer needed since we're saving the pregnancy upon import.
                    /*PregnancyEnd pregnancyEnd = (PregnancyEnd) vo;
                    Date pregnancyEndDate = pregnancyEnd.getField1369();
                    Long thisPregnancyId = vo.getPregnancyId();
                    endPregnancy(conn, vo, pregnancyEndDate, encounterId, thisPregnancyId);*/
                }
            }

            /**
             * Extended Lab Tests - update extendedLabId in labtest table
             */

	        if (formId >=101 && formId <=104) {
	            updateLabTest(conn, vo, encounterId, userName, siteId, patientId, pregnancyId);
	        }

            /**
             * Update values in patient table
             */

            // update misc values
            java.sql.Date dateVisit = vo.getDateVisit();
            patientId = vo.getPatientId();
            if (formId == 40) {   //Still Birth Delivery Record form - updates dead col in patient table.
                updateDead(conn, patientId, dateVisit, encounterId, true);
            }

            // It would be nicer to loop through all of the field id's in the encounter and then run them through updatePatientValues;
            // but for the sake of efficiency, I'm hardcoding part of this. If you add cases to updatePatientValues,
            // be sure to update the following list.

            FormDAO.updatePatientValues(conn, patientId, vo, formId, dateVisit, encounterId);

            /**
             * Commit the transaction
             */
            sql = "COMMIT";
            DatabaseUtils.create(conn, sql);
        } catch (Exception e) {
            sql = "ROLLBACK";
            DatabaseUtils.create(conn, sql);
            log.error(e);
            e.printStackTrace();
            throw new SQLException("Error saving this record.", e.getMessage());
        }
        return vo;
    }

    /**
     * Updates extended_test_id in labtest.
     * @param conn
     * @param vo
     * @param encounterId
     * @param userName
     * @param siteId
     * @param patientId
     * @param pregnancyId
     */
    private static void updateLabTest(Connection conn, EncounterData vo, Long encounterId, String userName, Long siteId, Long patientId, Long pregnancyId) {
        // Timestamp lastModified = vo.getLastModified();
        String labTestSql = "UPDATE labtest SET extended_test_id=? " +
                "WHERE id=?";
        Long labTestId = null;
        switch (vo.getFormId().intValue()) {
            case 101:
                Hemotology hemotology = (Hemotology) vo;
                labTestId = hemotology.getField2037();
                break;
            case 102:
                Chemistry chemistry = (Chemistry) vo;
                labTestId = chemistry.getField2037();
                break;
            case 103:
                Liverfunction liverfunction = (Liverfunction) vo;
                labTestId = liverfunction.getField2037();
                break;
            case 104:
                Urinalysis urinalysis = (Urinalysis) vo;
                labTestId = urinalysis.getField2037();
                break;
        }
        ArrayList values = new ArrayList();
        /*values.add(lastModified);
        values.add(userName);
        values.add(siteId);*/
        values.add(encounterId);
        values.add(labTestId);
        try {
            DatabaseUtils.create(conn, labTestSql, values.toArray());
            // update modified in labtest table for this encounter.
            EncountersDAO.touchEncounterTable(conn, userName, siteId, labTestId);
        } catch (SQLException e) {
            log.error(e);
        } catch (ServletException e) {
            log.error(e);
        }

    }


    /**
     * @param formId
     * @param vo
     * @param conn
     * @throws SQLException
     * @deprecated
     */
    public static void updatePatientValues(int formId, EncounterData vo, Connection conn) throws SQLException {
        Long patientId;
        if (formId == 68) {   //Maternal Discharge Summary form - updates dead col in patient table.
            patientId = vo.getPatientId();
            MaternalDischarge discharge = (MaternalDischarge) vo;
            Integer disposition = discharge.getField1654();
            if (disposition != null) {
                if (disposition.intValue() == 2819) {  // set to true
                    updateDead(conn, patientId, discharge.getDateVisit(), discharge.getId(), true);
                }
            }
        }

        if (formId == 40) {   //Still Birth Delivery Record form - updates dead col in patient table.
            patientId = vo.getPatientId();
            updateDead(conn, patientId, vo.getDateVisit(), vo.getId(), true);
        }
        if (formId == 86) {   //Postnatal Infant Visit form - updates dead col in patient table.
            patientId = vo.getPatientId();
            PostnatalInfant postnatalVisit = (PostnatalInfant) vo;
            Integer status = postnatalVisit.getField1470(); // Infant Status field
            if (status != null) {
                if (status.intValue() == 2743) {  // dead - set to true
                    updateDead(conn, patientId, vo.getDateVisit(), vo.getId(), true);
                }
            }
        }
        if (formId == 32) {   //Infant Problem/Postnatal Visit - updates dead col in patient table.
            patientId = vo.getPatientId();
            ProbPostnatalInfant postnatalVisit = (ProbPostnatalInfant) vo;
            Integer status = postnatalVisit.getField1470(); // Infant Status field
            if (status != null) {
                if (status.intValue() == 2743) {  // dead - set to true
                    updateDead(conn, patientId, vo.getDateVisit(), vo.getId(), true);
                }
            }
        }

        if (formId == 70) {   //Med/surg history - updates hiv col in patient table.
            patientId = vo.getPatientId();
            MedSurgHist visit = (MedSurgHist) vo;
            Byte status = visit.getField1929(); // hiv
            if (status != null) {
                updateHiv(conn, patientId, status);
            }
        }

        if (formId == 91) {   //SM counsel visit - updates hiv col in patient table.
            patientId = vo.getPatientId();
            SmCounselingVisit visit = (SmCounselingVisit) vo;
            Byte status = null; // hiv
            Integer hivResult = visit.getField1866();
            if (hivResult != null) {
                if (hivResult.intValue() == 2940) {   //R - reactive
                    status = 1;
                } else if (hivResult.intValue() == 2939) {   // NR - non-reactive
                    status = 0;
                } else if (hivResult.intValue() == 2941) {    // I - indeterminate
                    status = 2;
                }
            }
            if (status != null) {
                updateHiv(conn, patientId, status);
            }
        }

        if (formId == 92) {   //SM Care visit - updates hiv col in patient table.
            patientId = vo.getPatientId();
            SafeMotherhoodCare visit = (SafeMotherhoodCare) vo;
            Byte status = null; // hiv
            Integer hivResult = visit.getField1866();
            if (hivResult != null) {
                if (hivResult.intValue() == 2940) {   //R - reactive
                    status = 1;
                } else if (hivResult.intValue() == 2939) {   // NR - non-reactive
                    status = 0;
                } else if (hivResult.intValue() == 2941) {    // I - indeterminate
                    status = 2;
                }
            }
            if (status != null) {
                updateHiv(conn, patientId, status);
            }
        }
    }

    /**
     * Updates values in patient table depending on the form.
     * This is used when posting new records. Finds the field in the form that needs special handling.
     * Updates use the simpler updatePatientValues since they updates only a single field.
     * @param conn
     * @param patientId
     * @param vo
     * @param formId
     * @param dateVisit
     * @param encounterId
     * @throws SQLException
     */
    public static void updatePatientValues(Connection conn, Long patientId, EncounterData vo, int formId, Date dateVisit, Long encounterId) throws SQLException {
    	if (formId == 1) {   //PatientRegistration
            patientId = vo.getPatientId();
            PatientRegistration patientRegistration = (PatientRegistration) vo;
            String alternateId = patientRegistration.getField2145(); // Alternate Id field
            if (alternateId != null) {
                FormDAO.updatePatientValues(conn, patientId, 2145, alternateId, dateVisit, encounterId);
            }
            Byte disableLabImport = patientRegistration.getField2144();	// Disable Lab import field
            if (disableLabImport != null) {
                FormDAO.updatePatientValues(conn, patientId, 2144, disableLabImport.intValue(), dateVisit, encounterId);
            }
        }

        if (formId == 32) {   //Infant postnatal prob Form - updates dead col in patient table.
        	patientId = vo.getPatientId();
        	ProbPostnatalInfant visit = (ProbPostnatalInfant) vo;
        	Boolean status = visit.getField2151(); // Infant death field
        	if (status != null) {
        		if (status == true) {
        		FormDAO.updatePatientValues(conn, patientId, 2151, 1, dateVisit, encounterId);
        		}
        	}
        }

    	if (formId == 68) {   //Maternal Discharge Summary form - updates dead col in patient table.
            patientId = vo.getPatientId();
            MaternalDischarge discharge = (MaternalDischarge) vo;
            Integer disposition = discharge.getField1654();
            if (disposition != null) {
                FormDAO.updatePatientValues(conn, patientId, 1654, disposition.intValue(), dateVisit, encounterId);
            }
        }

        if (formId == 86) {   //Postnatal Infant Visit form - updates dead col in patient table.
            patientId = vo.getPatientId();
            PostnatalInfant postnatalVisit = (PostnatalInfant) vo;
            Integer status = postnatalVisit.getField1470(); // Infant Status field
            if (status != null) {
                FormDAO.updatePatientValues(conn, patientId, 1470, status.intValue(), dateVisit, encounterId);
            }
        }
        if (formId == 32) {   //Infant Problem/Postnatal Visit - updates dead col in patient table.
            patientId = vo.getPatientId();
            ProbPostnatalInfant postnatalVisit = (ProbPostnatalInfant) vo;
            Integer status = postnatalVisit.getField1470(); // Infant Status field
            if (status != null) {
                FormDAO.updatePatientValues(conn, patientId, 1470, status.intValue(), dateVisit, encounterId);
            }
        }

        if (formId == 70) {   //Med/surg history - updates hiv col in patient table.
            patientId = vo.getPatientId();
            MedSurgHist visit = (MedSurgHist) vo;
            Byte status = visit.getField1929(); // hiv
            if (status != null) {
                FormDAO.updatePatientValues(conn, patientId, 1929, status.intValue(), dateVisit, encounterId);
            }
        }

        if (formId == 76) {   //NICU Hospitalization Summary Form - updates dead col in patient table.
            patientId = vo.getPatientId();
            NicuSummary nicuSummary = (NicuSummary) vo;
            Integer status = nicuSummary.getField820(); // Infant Status field
            if (status != null) {
                FormDAO.updatePatientValues(conn, patientId, 820, status.intValue(), dateVisit, encounterId);
            }
        }

        if (formId == 78) {   //maternal postnatal prob Form - updates dead col in patient table.
        	patientId = vo.getPatientId();
        	ProbPostnatal visit = (ProbPostnatal) vo;
        	Boolean status = visit.getField2150(); // Maternal death field
        	if (status != null) {
        		if (status == true) {
        		FormDAO.updatePatientValues(conn, patientId, 2150, 1, dateVisit, encounterId);
        		}
        	}
        }

        if (formId == 77 || formId == 78 || formId == 65) {
            // Initial visit/postnatal maternal prob/prob/labour - updates height col in patient table.
            patientId = vo.getPatientId();
            Integer height = null;
            if (formId == 77) {
                InitialVisit visit = (InitialVisit) vo;
                height = visit.getField159();  // height
            } else if (formId == 78) {
                ProbPostnatal visit = (ProbPostnatal) vo;
                height = visit.getField159();  // height
            } else {
                ProbLabor visit = (ProbLabor) vo;
                height = visit.getField159(); // height
            }
            if (height != null) {
                FormDAO.updatePatientValues(conn, patientId, 159, height.intValue(), dateVisit, encounterId);
            }
        }

        if (formId == 91 || formId == 92 || formId == 66 || formId == 86) {
        	//SM counsel visit, delivery sum, or postnatal infant visit - updates hiv col in patient table.
            patientId = vo.getPatientId();
            Integer hivResult = null;
            if (formId == 91) {
                SmCounselingVisit visit = (SmCounselingVisit) vo;
                hivResult = visit.getField1866();
            } else if (formId == 66) {
            	DeliverySum visit = (DeliverySum) vo;
            	hivResult = visit.getField1866();
            } else if (formId == 86) {
            	PostnatalInfant visit = (PostnatalInfant) vo;
            	hivResult = visit.getField1866();
            } else {
                SafeMotherhoodCare visit = (SafeMotherhoodCare) vo;
                hivResult = visit.getField1866();
            }
            if (hivResult != null) {
                FormDAO.updatePatientValues(conn, patientId, 1866, hivResult.intValue(), dateVisit, encounterId);
            }
        }
    }

    /**
     * Provides links to methods that update values in the patient table
     * @param conn
     * @param patientId
     * @param formFieldId
     * @param value
     * @param dateVisit
     * @param encounterId
     * @throws SQLException
     */
    public static void updatePatientValues(Connection conn, Long patientId, int formFieldId, Object value, Date dateVisit, Long encounterId) throws SQLException {
    	Integer valueInt = null;
    	String valueStr = null;
    	if (value.getClass().equals(String.class)) {
    		valueStr = (String) value;
    		try {
				valueInt = Integer.parseInt((String)value);
			} catch (NumberFormatException e) {
				// ignore
			}
    	} else if (value.getClass().equals(Integer.class)) {
    		valueInt =  (Integer) value;
    	}
        switch (formFieldId) {
            case 490:  // sex change?
            	//valueInt = Integer.valueOf((String)value);
                updateSex(conn, patientId, valueInt);
                break;
            case 159:  // height
            	//valueInt = Integer.valueOf((String)value);
                updateHeight(conn, patientId, valueInt);
                break;
             case 820:	// Infant status field in NICU Summary form
            	//valueInt = Integer.valueOf((String)value);
                if (valueInt == 968) {  // set to true
                    updateDead(conn, patientId, dateVisit, encounterId, true);
                } else {
                	updateDead(conn, patientId, dateVisit, encounterId, false);
                }
                break;
            case 1654: // Maternal death form, Disposition field
            	//valueInt = Integer.valueOf((String)value);
                if (valueInt == 2819) {
                    updateDead(conn, patientId, dateVisit, encounterId, true);
                } else {
                	updateDead(conn, patientId, dateVisit, encounterId, false);
                }
                break;
            case 1470:	// Infant status field
                // if (formId == 86) {   //Postnatal Infant Visit form - updates dead col in patient table.
                // if (formId == 32) {   //Infant Problem/Postnatal Visit - updates dead col in patient table.
            	//valueInt = Integer.valueOf((String)value);
                if (valueInt == 2743) {  // set to true
                    updateDead(conn, patientId, dateVisit, encounterId, true);
                } else {
                	updateDead(conn, patientId, dateVisit, encounterId, false);
                }
                break;
            case 1929:
                // if (formId == 70) {   //Med/surg history - updates hiv col in patient table.
            	//int valueInt = Integer.valueOf((String)value);
                //Byte status = Byte.valueOf(Byte.toString((byte) valueInt.intValue())); // hiv
                //Byte status = Byte.valueOf(valueInt); // hiv
                Byte status = valueInt.byteValue();
                updateHiv(conn, patientId, status);
                break;
            case 1866:
                //  if (formId == 91) {   //SM counsel visit - updates hiv col in patient table.
                // if (formId == 92) {   //SM Care visit - updates hiv col in patient table.
            	// Also used in formId == 66 - Delivery summary
                status = null; // hiv
                //valueInt = Integer.valueOf((String)value);
                if (valueInt == 2940) {   //R - reactive
                    status = 1;
                } else if (valueInt == 2939) {   // NR - non-reactive
                    status = 0;
                } else if (valueInt == 2941) {    // I - indeterminate
                    status = 2;
                }
                if (status != null) {
                    updateHiv(conn, patientId, status);
                }
                break;
            case 2150: // Maternal Postnatal prob form, Maternal death field
                if (valueInt == 1) {
                    updateDead(conn, patientId, dateVisit, encounterId, true);
                } else {
                	updateDead(conn, patientId, dateVisit, encounterId, false);
                }
                break;
            case 2151: // Infant Postnatal prob form, Infant death field
            	if (valueInt == 1) {
            		updateDead(conn, patientId, dateVisit, encounterId, true);
            	} else {
            		updateDead(conn, patientId, dateVisit, encounterId, false);
            	}
            	break;
            case 2145:
                updateAlternateId(conn, patientId, valueStr);
                break;
            case 2144:
            	updateDisableLabImport(conn, patientId, valueInt);
            	break;
        }
    }

    /**
     * Some of the SM Care forms do not use field1 for dateVisit. Updates dateVisit using the correct field.
     * @param conn
     * @param patientId
     * @param formFieldId
     * @param value
     * @param dateVisit
     * @param encounterId
     * @throws SQLException
     */
    public static void updateEncounterData(Connection conn, Long patientId, int formFieldId, String value, Date dateVisit, Long encounterId) throws SQLException {

        switch (formFieldId) {
            case 1882:
                dateVisit = java.sql.Date.valueOf(value);
                updateDateVisit(conn, dateVisit, encounterId);
                break;
            case 1881:
                dateVisit = java.sql.Date.valueOf(value);
                updateDateVisit(conn, dateVisit, encounterId);
                break;
            case 1852:
                dateVisit = java.sql.Date.valueOf(value);
                updateDateVisit(conn, dateVisit, encounterId);
                break;
            case 1562:
                dateVisit = java.sql.Date.valueOf(value);
                updateDateVisit(conn, dateVisit, encounterId);
                break;
            case 1844:
                dateVisit = java.sql.Date.valueOf(value);
                updateDateVisit(conn, dateVisit, encounterId);
                break;
        }
    }

    /**
     * Update dead status in patient table
     * @param conn
     * @param patientId
     * @param dateDeath
     * @param encounterId
     * @param isDead
     * @throws SQLException
     */
    private static void updateDead(Connection conn, Long patientId, Date dateDeath, Long encounterId, Boolean isDead) throws SQLException {
        ArrayList patientValues = new ArrayList();
        patientValues.add(dateDeath);
        patientValues.add(encounterId);
        patientValues.add(patientId);
        String sql = null;
        if (isDead == Boolean.TRUE) {
        	sql = "UPDATE patient SET dead=1, date_death=?, death_encounter_id=? WHERE id=?;";
        } else {
        	sql = "UPDATE patient SET dead=0, date_death=?, death_encounter_id=? WHERE id=?;";
        }

        DatabaseUtils.create(conn, sql, patientValues.toArray());
    }

    /**
     * Updates hiv_positive in patient table.
     * @param conn
     * @param patientId
     * @param status
     * @throws SQLException
     */
    private static void updateHiv(Connection conn, Long patientId, Byte status) throws SQLException {
        ArrayList patientValues = new ArrayList();
        patientValues.add(status);
        patientValues.add(patientId);
        String sql = "UPDATE patient SET hiv_positive=? WHERE id=?;";
        DatabaseUtils.create(conn, sql, patientValues.toArray());
    }

    /**
     * Updates sex in patient table.
     * @param conn
     * @param patientId
     * @param sex
     * @throws SQLException
     */
    private static void updateSex(Connection conn, Long patientId, Integer sex) throws SQLException {
        ArrayList patientValues = new ArrayList();
        patientValues.add(sex);
        patientValues.add(patientId);
        String sql = "UPDATE patient SET sex=? WHERE id=?;";
        DatabaseUtils.create(conn, sql, patientValues.toArray());
    }

    /**
     * Updates height in patient table.
     * @param conn
     * @param patientId
     * @param height
     * @throws SQLException
     */
    private static void updateHeight(Connection conn, Long patientId, Integer height) throws SQLException {
        ArrayList patientValues = new ArrayList();
        patientValues.add(height);
        patientValues.add(patientId);
        String sql = "UPDATE patient SET height=? WHERE id=?;";
        DatabaseUtils.create(conn, sql, patientValues.toArray());
    }

    /**
     * Updates date_visit in encounter table.
     * @param conn
     * @param dateVisit
     * @param encounterId
     * @throws SQLException
     */
    private static void updateDateVisit(Connection conn, Date dateVisit, Long encounterId) throws SQLException {
        ArrayList patientValues = new ArrayList();
        patientValues.add(dateVisit);
        patientValues.add(encounterId);
        String sql = "UPDATE encounter SET date_visit=? WHERE id=?;";
        DatabaseUtils.create(conn, sql, patientValues.toArray());
    }

    /**
     * Updates alternate_id in patient table.
     * @param conn
     * @param patientId
     * @param status
     * @throws SQLException
     */
    private static void updateAlternateId(Connection conn, Long patientId, String alternateId) throws SQLException {
        ArrayList patientValues = new ArrayList();
        patientValues.add(alternateId);
        patientValues.add(patientId);
        String sql = "UPDATE patient SET alternate_id=? WHERE id=?;";
        DatabaseUtils.create(conn, sql, patientValues.toArray());
    }

    /**
     * Updates disable_lab_import in patient table.
     * @param conn
     * @param patientId
     * @param status
     * @throws SQLException
     */
    private static void updateDisableLabImport(Connection conn, Long patientId, int disableLabImport) throws SQLException {
    	ArrayList patientValues = new ArrayList();
    	patientValues.add(disableLabImport);
    	patientValues.add(patientId);
    	String sql = "UPDATE patient SET disable_lab_import=? WHERE id=?;";
    	DatabaseUtils.create(conn, sql, patientValues.toArray());
    }

    /**
     * Persists an encounter. Inserts a new record into the encounter table and the associated form table.
     * @param conn
     * @param genQueries
     * @param vo
     * @param formDef
     * @param userName
     * @param siteId
     * @param formBean TODO
     * @return id of new encounter
     * @throws Exception
     */
    public static Long createEncounter(Connection conn, Map genQueries, EncounterData vo, Form formDef, String userName, Long siteId, Map formBean) throws Exception {
        String encSQL = null;
        Timestamp created = new Timestamp(System.currentTimeMillis());
        if (vo.getCreated() != null) {
            created = vo.getCreated();
        }
        String createdBy = userName;
        if (vo.getCreatedBy() != null) {
            createdBy = vo.getCreatedBy();
        }
        Timestamp lastModified = new Timestamp(System.currentTimeMillis());
        if (vo.getLastModified() != null) {
            lastModified = vo.getLastModified();
        }
        String lastModifiedBy = userName;
        if (vo.getLastModifiedBy() != null) {
            lastModifiedBy = vo.getLastModifiedBy();
        }
        Long createdSiteId = siteId;
        if (vo.getCreatedSiteId() != null) {
            createdSiteId = vo.getCreatedSiteId();
        }
        Long currentSiteId = siteId;
        if (vo.getSiteId() != null) {
            currentSiteId = vo.getSiteId();
        }

        String uuidStr = null;
        if (vo.getUuid() != null) {
        	uuidStr = vo.getUuid();
        } else {
        	// create the uuid for the patient
        	UUID uuid = UUID.randomUUID();
        	uuidStr = uuid.toString();
        	vo.setUuid(uuidStr);
        }

        if (vo.getReferralId() != null) {
            encSQL = "INSERT INTO encounter " +
                    "set patient_id=" + vo.getPatientId() +
                    ", form_id=" + vo.getFormId() +
                    ", flow_id=" + vo.getFlowId() +
                    ", date_visit='" + vo.getDateVisit() +
                    "', pregnancy_id=" + vo.getPregnancyId() +
                    ", last_modified='" + lastModified +
                    "', created='" + created +
                    "', last_modified_by='" + lastModifiedBy +
                    "', created_by='" + createdBy +
                    "', site_id=" + currentSiteId +
                    ", created_site_id=" + createdSiteId +
                    ", referral_id=" + vo.getReferralId() +
                    ", import_encounter_id=" + vo.getImportEncounterId() +
                    ", uuid='" + uuidStr +
                    "'; ";
        } else {
            encSQL = "INSERT INTO encounter " +
                    "set patient_id=" + vo.getPatientId() +
                    ", form_id=" + vo.getFormId() +
                    ", flow_id=" + vo.getFlowId() +
                    ", date_visit='" + vo.getDateVisit() +
                    "', pregnancy_id=" + vo.getPregnancyId() +
                    ", last_modified='" + lastModified +
                    "', created='" + created +
                    "', last_modified_by='" + lastModifiedBy +
                    "', created_by='" + createdBy +
                    "', site_id=" + currentSiteId +
                    ", created_site_id=" + createdSiteId +
                    ", import_encounter_id=" + vo.getImportEncounterId() +
                    ", uuid='" + uuidStr +
                    "'; ";
        }
        Long encounterId;
        String sqlCreateEncounter = (String) genQueries.get(Constants.SQL_CREATE + vo.getFormId());
        // todo: generate the object creation code along w/ the sql code
        ArrayList values = new ArrayList();
        FormField formField = null;
        Iterator dbPageItems = formDef.getPageItems().iterator();
        while (dbPageItems.hasNext()) {
            PageItem pageItem = (PageItem) dbPageItems.next();
            formField = pageItem.getForm_field();
            if (formField.isEnabled()) {
                if (!formField.getType().equals("Display") && !pageItem.getInputType().equals("multiselect_enum")) {
                    String fieldName = "field" + formField.getId();
                    String voValue = null;
                    try {
                        voValue = BeanUtils.getProperty(vo, fieldName);
                    } catch (ConversionException e) {
                        log.error(e);
                        voValue = null;
                    } catch (NullPointerException e) {
                        log.error(e);
                        voValue = null;
                    }
                    Object preparedValue = null;
                    if (voValue != null) {
                        try {
                            // need to transform Booleans
                            preparedValue = PatientRecordUtils.getPreparedValue(pageItem, voValue);
                        } catch (Exception e) {
                            log.error(e);
                        }
                    }
                    values.add(preparedValue);
                }
            }
        }
        // log.debug("values: " + values);
        Object[] params2 = values.toArray();
        encounterId = null;
        try {
            encounterId = (Long) DatabaseUtils.create(conn, encSQL, sqlCreateEncounter, params2);
        } catch (ConversionException e) {
            log.error(e);
        }
        return encounterId;
    }

    /**
     * Creates records in patient and patient_status tables for a new patient record.
     * @param conn
     * @param queries
     * @param vo
     * @param userName
     * @param siteId
     * @param currentFlowId
     * @return
     * @throws ServletException
     * @throws SQLException
     */
    private static Long createPatient(Connection conn, Map queries, EncounterData vo, String userName, Long siteId, Long currentFlowId) throws ServletException, SQLException {
        Long patientId;
        String sqlCreatePatient = (String) queries.get("SQL_CREATE_PATIENT");
        // create the uuids for the patient
        String uuidStr =  null;
        String parentUuidStr =  null;
        // first prepare the patient value array
        PatientRegistration pr = (PatientRegistration) vo;
        SessionPatient sessionPatient = vo.getSessionPatient();
        if (sessionPatient != null) {
        	uuidStr = sessionPatient.getUuid();
        	parentUuidStr = sessionPatient.getParentUuid();
        } else {
        	UUID uuid = UUID.randomUUID();
            uuidStr = uuid.toString();
        }
        ArrayList patientValues = new ArrayList();
        // add first_name to patientValues
        patientValues.add(pr.getField7());
        // add surname to patientValues
        patientValues.add(pr.getField6());
        // add nrc_number to patientValues
        patientValues.add(pr.getField9());
        // add patient_id_number to patientValues
        patientValues.add(pr.getField1513());
        // add obstetric_record_number to patientValues
        patientValues.add(pr.getField1134());
        // add age_at_first_visit
        patientValues.add(pr.getField1135());
        // add birthdate to patientValues
        patientValues.add(pr.getField17());
        AddAuditInfo(vo, patientValues, userName, siteId);
        // parentid
        patientValues.add(null);
        // sex
        patientValues.add(pr.getField490());
        // uuid
        patientValues.add(uuidStr);
        // parent uuid
        patientValues.add(parentUuidStr);
        // now create the patient
        patientId = (Long) DatabaseUtils.create(conn, sqlCreatePatient, patientValues.toArray());

        // create the entry in patient_status table
        PatientStatusDAO.save(queries, patientId, currentFlowId, vo, userName, siteId, conn);
        return patientId;
    }

    /**
     * Updates values in patient_status and pregnancy for end of pregnancy.
     * Persists instance of PregnancyEnd, a summary of the current ZEPRS pregnancy, in PreviousPregnancies
     * @param conn
     * @param genQueries
     * @param vo
     * @param formDef
     * @param name
     * @param id
     * @param userName
     * @param siteId
     * @param patientId
     * @param pregnancyId
     * @throws Exception
     */
    private static void createPregnancyRecord(Connection conn, Map genQueries, EncounterData vo, Form formDef, String name, Long id, String userName, Long siteId, Long patientId, Long pregnancyId) throws Exception {
        ArrayList values;
        Long encounterId = vo.getId();
        Long thisPatientId = vo.getPatientId();
        PregnancyEnd pregnancyEnd = (PregnancyEnd) vo;
        Date pregnancyEndDate = pregnancyEnd.getField1369();
        Long thisPregnancyId = vo.getPregnancyId();
        purgePatientStatusValues(conn, vo, pregnancyEndDate, encounterId, thisPregnancyId, thisPatientId, userName, siteId);
        endPregnancy(conn, vo, pregnancyEndDate, encounterId, thisPregnancyId);
        // PrevPregnancies prevPreg = new PrevPregnancies();
        // create a new prevPregnancy
        values = new ArrayList();
        String year = DateUtils.getYear(vo.getDateVisit());
        String month = DateUtils.getMonth(vo.getDateVisit());
        Integer field50 = null; // Place of Delivery
        Integer field52 = null; // Pregnancy Course
        Integer field53 = null; // Outcome of Pregnancy
        Integer field54 = null;   // If Died Before 5 Years, Cause
        Integer field447 = null;  // Mode of Delivery
        Integer field1755 = null;  // Type of labour
        Integer field60 = null;  // Indication for C/S Forceps/Vacuum
        Integer field62 = null;  // Duration of Labor
        Integer field66 = null;  // Postpartum Infection
        Integer field63 = null;  // Num. Fetuses this pregnancy
        Float field65 = null;  // Birth Weight Infant 1
        Integer field1747 = null;  // Sex Infant 1   female: 1; male: 2
        Float field1244 = null;  // Birth Weight Infant 2
        Integer field1748 = null;  // Sex Infant 2   female: 1; male: 2
        Float field1245 = null;  // Birth Weight Infant 3
        Integer field1749 = null;  // Sex Infant 3   female: 1; male: 2
        Byte field1753 = null;  // PPH
        Byte field1754 = null;  // APH
        Byte field1756 = null;  // Eclampsia
        String field1926 = null;  // Other Complications - renamed to Comments

        // get a list of problem/labour visits
        List probLabor = EncountersDAO.getAll(conn, patientId, pregnancyId, new Long("65"), ProbLabor.class);
        for (int i = 0; i < probLabor.size(); i++) {
            ProbLabor labor = (ProbLabor) probLabor.get(i);
            if (labor.getField1758() != null) {    // Miscarriage
                field53 = 1830;
            }
            if (labor.getField1755() != null) {    //  Type of labour
                field1755 = labor.getField1755();
            }
            if (labor.getField1756() != null) {    //  Eclampsia
                field1756 = labor.getField1756();
            }
        }
        // get a list of problem/postnatal  visits
        List probPostnatal = EncountersDAO.getAll(conn, patientId, pregnancyId, new Long("63"), ProbPostnatal.class);
        for (int i = 0; i < probPostnatal.size(); i++) {
            ProbPostnatal probPostnatalVisit = (ProbPostnatal) probPostnatal.get(i);
            if (probPostnatalVisit.getField1756() != null) {    //  Eclampsia
                field1756 = probPostnatalVisit.getField1756();
            }
        }

        // get a list of antenatal visits
        List anteSum = EncountersDAO.getAll(conn, patientId, pregnancyId, new Long("63"), Antesum.class);
        for (int i = 0; i < anteSum.size(); i++) {
            Antesum antesum = (Antesum) anteSum.get(i);
            if (antesum.getField932() != null) {
                field53 = 1830;
            }
            if (antesum.getField1754() != null) {
                field1754 = antesum.getField1754(); // APH
            }
        }
        // get a list of postnatal visits
        List postnatalHosp = EncountersDAO.getAll(conn, patientId, pregnancyId, new Long("74"), PostnatalHosp.class);
        for (int i = 0; i < postnatalHosp.size(); i++) {
            PostnatalHosp postnatalHospVisit = (PostnatalHosp) anteSum.get(i);
            if (postnatalHospVisit.getField1753() != null) {
                field1753 = postnatalHospVisit.getField1753();
            }
        }

        DeliverySum deliverySum = null;
        try {
            deliverySum = (DeliverySum) EncountersDAO.getOne(conn, patientId, pregnancyId, new Long("66"));
            if (deliverySum != null) {
                if (deliverySum.getField447() != null) {
                    field447 = deliverySum.getField447();  // Mode of Delivery
                }
                if (deliverySum.getField60() != null) {
                    field60 = deliverySum.getField60();  // Indication for C/S Forceps/Vacuum
                }
                if (deliverySum.getField1753() != null) {  // PPH
                    field1753 = deliverySum.getField1753();
                }
                if (deliverySum.getField468() != null) {  // If Complications, Describe
                    field1926 = "Complications: " + deliverySum.getField468();
                }
                Long birthSiteId = deliverySum.getSiteId();
                if (birthSiteId != null) {
                    Site site = (Site) DynaSiteObjects.getClinicMap().get(birthSiteId);
                    Integer siteTypeId = site.getSiteTypeId();
                    if ((siteTypeId) == 1) {
                        field50 = 19; // Clinic
                    } else if ((siteTypeId) == 2) {
                        field50 = 553; // Clinic
                    }
                }
                //  if (deliverySum.getField421() != null)   // Latent phase duration - cannot use - not integer (just prolonged and normal)
            }
        } catch (ObjectNotFoundException e) {
            // it's ok - form not yet submitted
        }

        // Postpartum Infection
        MaternalDischarge maternalDischarge = null;
        try {
            maternalDischarge = (MaternalDischarge) EncountersDAO.getOne(conn, patientId, pregnancyId, new Long("68"));
            if (maternalDischarge != null) {
                if (maternalDischarge.getField66() != null) {
                    field66 = maternalDischarge.getField66();
                }
            }
        } catch (ObjectNotFoundException e) {
            // it's ok - form not yet submitted
        }

        // get a list of newborns from this pregnancy and see if any misscarried, get num. fetuses
        List children = PatientDAO.getChildren(conn, patientId, pregnancyId);
        if (children != null) {
            if (children.size() == 1) {
                field63 = 28; // Singleton
            } else if (children.size() == 2) {
                field63 = 562; // Twins
            } else if (children.size() == 3) {
                field63 = 1088; // Triplets
            } else if (children.size() > 3) {
                field63 = 1415; // Quadruplet and Over
            }
        }

        for (int i = 0; i < children.size(); i++) {
            Patient child = (Patient) children.get(i);
            // get the date of birth
            Date birthdate = child.getBirthdate();
            Time birthTime = child.getTimeOfBirth();
            List postnatalInfantVisits = EncountersDAO.getAll(conn, child.getId(), pregnancyId, new Long("86"), PostnatalInfant.class);
            Integer status;
            Date deathDate = null;

            // see if stillbirth record submitted and what type of stillbith
            StillbirthDeliveryRecord sdr = null;
            try {
                sdr = (StillbirthDeliveryRecord) EncountersDAO.getOne(conn, child.getId(), pregnancyId, (long) 40);
                Integer sbType = sdr.getField858();
                if (sbType != null) {
                    if (sbType == 455) {
                        field53 = 1083; // fsb
                    } else if (sbType == 986) {
                        field53 = 1411; // msb
                    }
                }
            } catch (ObjectNotFoundException e) {
                // it's ok - form not yet submitted
            }
            // we'll check if each value is null, so that we don't unnecessarily hit the db in the event of multiple fetuses
            NewbornEval newbornEval = null;
            try {
                newbornEval = (NewbornEval) EncountersDAO.getOne(conn, child.getId(), pregnancyId, new Long("23"));
                if (newbornEval.getField1180() != null) {
                    if (newbornEval.getField1180() == 1) {
                        deathDate = birthdate;
                    }
                }

                if (field54 == null) {
                    if (newbornEval.getField495() != null) {
                        if (newbornEval.getField495() == 2023) { //Pneumonia
                            field54 = 22;
                        } else if (newbornEval.getField495() == 2128) { //Meningitis
                            field54 = 1831;
                        } else if (newbornEval.getField495() == 822) { //Prematurity
                            field54 = 2088;
                        }
                    }
                }
                /*
                // 5/1/2007: Disabled field "If Premature, Number of  wks Since Gestation"
                // replaced with ega field
                if (newbornEval.getField488() != null) {
                    field52 = 554;  // Pregnancy Course
                }*/

                // field491  weight
                // set the weight and sex for each infant
                if (i == 0) {
                    field65 = newbornEval.getField491();  // Birth Weight Infant 1
                    field1747 = newbornEval.getField490();  // Sex Infant 1   female: 1; male: 2
                } else if (i == 1) {
                    field1244 = newbornEval.getField491();  // Birth Weight Infant 2
                    field1748 = newbornEval.getField490();  // Sex Infant 2   female: 1; male: 2
                } else if (i == 2) {
                    field1245 = newbornEval.getField491();  // Birth Weight Infant 3
                    field1749 = newbornEval.getField490();  // Sex Infant 3   female: 1; male: 2
                }
            } catch (ObjectNotFoundException e) {
                // it's ok - form not yet submitted
            } catch (NullPointerException e) {
                log.error(e);
            }

            if (deathDate == null) {
                for (int j = 0; j < postnatalInfantVisits.size(); j++) {
                    PostnatalInfant postnatalInfantVisit = (PostnatalInfant) postnatalInfantVisits.get(j);
                    status = postnatalInfantVisit.getField1470();
                    if (status != null) {
                        if (status == 2742) {
                            // alive
                        } else if (status == 2743) {
                            deathDate = postnatalInfantVisit.getDateVisit();
                            // dead
                        }
                    }
                }
            }

            if (deathDate != null) {
                long daysDeathAfterBirth = DateUtils.calculateDays(birthdate, deathDate);
                if (daysDeathAfterBirth < 8) {
                    field53 = 2087;
                } else if ((daysDeathAfterBirth > 8) & (daysDeathAfterBirth < 29)) {
                    field53 = 2183;
                } else if (daysDeathAfterBirth > 28)
                {  // only testing up to a year - assuming that pregnancy record will be closed before then.
                    field53 = 2256;
                }
            }
            if (field62 == null) {
                if (deliverySum != null) {
                    long durationTotal = PatientRecordUtils.getDurationOfLabour(deliverySum, birthdate, birthTime);
                    if (durationTotal > 0) {
                        field62 = Integer.valueOf(String.valueOf(durationTotal));
                    }
                }
            }

            // check UTH Neonatal Case Record for Indication for C/S Forceps/Vacuum and duration
            try {
                UthNeonatalRecord uthRecord = (UthNeonatalRecord) EncountersDAO.getOne(conn, child.getId(), pregnancyId, new Long("33"));
                if (uthRecord.getField60() != null) {
                    field60 = uthRecord.getField60();
                }
                if (field62 == null) {
                    long durationStage1 = 0;
                    long durationStage2 = 0;
                    long durationTotal = 0;
                    if (uthRecord.getField748() != null) {
                        durationStage1 = uthRecord.getField748();
                    }
                    if (uthRecord.getField749() != null) {
                        durationStage2 = uthRecord.getField749();
                    }
                    durationTotal = durationStage1 + durationStage2;
                    if (durationTotal > 0) {
                        field62 = Integer.valueOf(String.valueOf(durationTotal));
                    }
                }
                if (uthRecord.getField50() != null) {
                    field50 = uthRecord.getField50();
                }
            } catch (ObjectNotFoundException e) {
                // it's ok
            }
        }
        if (field53 == null) { // if we still do not have a result, set to alive
            field53 = 21;
        }

        values.add(pregnancyId);         // pregnancyId
        values.add(year);         // year_of_delivery_51
        values.add(month);        // month_of_delivery
        values.add(field50);      // Place of Delivery
        values.add(null);         // place_delivery_other
        values.add(field52);      // Pregnancy Course
        values.add(field53);      // Outcome of Pregnancy
        values.add(field54);      // If Died Before 5 Years, Cause
        values.add(null);         // other_cause_death_55
        values.add(null);         // if_died_before_5_hiv_56
        values.add(null);         // if_tested_result_57
        values.add(field447);     // Mode of Delivery
        values.add(field1755);    // Type of labour
        values.add(field60);      // Indication for C/S Forceps/Vacuum
        values.add(field62);      // Duration of Labor
        values.add(field66);      // Postpartum Infection
        values.add(field63);      // Num. Fetuses this pregnancy
        values.add(field65);      // Birth Weight Infant 1
        values.add(field1747);    // Sex Infant 1   female: 1; male: 2
        values.add(field1244);    // Birth Weight Infant 2
        values.add(field1748);    // Sex Infant 2   female: 1; male: 2
        values.add(field1245);    // Birth Weight Infant 3
        values.add(field1749);    // Sex Infant 3   female: 1; male: 2
        values.add(field1756);    // Eclampsia
        values.add(field1753);    // PPH
        values.add(field1754);    // APH
        values.add(field1926);    // Other Complications - renamed to Comments

    	// create the uuid for the patient
    	UUID uuid = UUID.randomUUID();
    	String uuidStr = uuid.toString();

        String sqlCreateEncounter = (String) genQueries.get("SQL_CREATE2");
        String encSQL = "INSERT INTO encounter " +
                "set patient_id=" + vo.getPatientId() +
                ", form_id=2" +
                ", flow_id=" + vo.getFlowId() +
                ", date_visit='" + vo.getDateVisit() +
                "', pregnancy_id=" + vo.getPregnancyId() +
                ", last_modified='" + new Timestamp(System.currentTimeMillis()) +
                "', created='" + new Timestamp(System.currentTimeMillis()) +
                "', last_modified_by='" + userName +
                "', created_by='" + userName +
                "', site_id=" + siteId +
        		", created_site_id=" + siteId +
        		", import_encounter_id=" + vo.getImportEncounterId() +
        		", uuid='" + uuidStr +
        		"'; ";

        try {
            DatabaseUtils.create(conn, encSQL, sqlCreateEncounter, values.toArray());
        } catch (Exception e) {
            log.error(e);
        }

        // encounterId = createEncounter(genQueries, vo, formDef, userName, siteId);
    }

    /**
     * Updates values such as encounterId in pregnancy table for a new pregnancy.
     * @param conn
     * @param queries
     * @param encounterId
     * @param pregnancyId
     * @throws Exception
     */
    private static void updatePregnancy(Connection conn, Map queries, Long encounterId, Long pregnancyId) throws Exception {
        String sqlUpdatePregnancy = (String) queries.get("SQL_MODIFY_PREGNANCY");
        ArrayList updatePregnancyValues = new ArrayList();
        // add encounterId to pregnancy - pregnancy_begin_encounter_id=?,
        updatePregnancyValues.add(encounterId);
        // add last_modified to pregnancy - last_modified=?
        updatePregnancyValues.add(new Timestamp(System.currentTimeMillis()));
        // set id field for pregnancy - WHERE id=?;
        updatePregnancyValues.add(pregnancyId);
        DatabaseUtils.update(conn, sqlUpdatePregnancy, updatePregnancyValues.toArray());
    }

    /**
     * Sets fields in pregnancy table to record beginning of labour (dilatation >=4cm)
     * @param conn
     * @param queries
     * @param vo
     * @param encounterId
     * @param dateAdmission
     * @param pregnancyId
     * @throws Exception
     */
    private static void updatePregnancyLabourAdmission(Connection conn, Map queries, EncounterData vo, Long encounterId, Date dateAdmission, Long pregnancyId) throws Exception {
        Timestamp lastModified = new Timestamp(System.currentTimeMillis());
        if (vo.getLastModified() != null) {
            lastModified = vo.getLastModified();
        }
        String sqlUpdatePregnancy = (String) queries.get("SQL_MODIFY_PREGNANCY_LABOUR");
        ArrayList updatePregnancyValues = new ArrayList();
        // add encounterId to pregnancy - labour_admission_encounter_id=?,
        updatePregnancyValues.add(encounterId);
        // add dateAdmission to pregnancy - date_labour_admission=?
        updatePregnancyValues.add(dateAdmission);
        // add uuid to pregnancy- labour_admission_encounter_uuid
        updatePregnancyValues.add(vo.getUuid());
        // add last_modified to pregnancy - last_modified=?
        updatePregnancyValues.add(lastModified);
        // set id field for pregnancy - WHERE id=?;
        updatePregnancyValues.add(pregnancyId);
        DatabaseUtils.update(conn, sqlUpdatePregnancy, updatePregnancyValues.toArray());
    }

    /**
     * Sets patient_status table values to null.
     * @param conn
     * @param vo
     * @param pregnancyEndDate
     * @param encounterId
     * @param thisPregnancyId
     * @param thisPatientId
     * @param userName
     * @param siteId
     * @throws Exception
     */
    protected static void purgePatientStatusValues(Connection conn, EncounterData vo, Date pregnancyEndDate, Long encounterId, Long thisPregnancyId, Long thisPatientId, String userName, Long siteId) throws Exception {

        // now reset some status fields
        String sqlPregnancyConclusion = "UPDATE patient_status SET current_ega_days=?, " +
                "current_ega_days_encounter_id=?, current_lmp_date=?, current_lmp_date_encounter_id=?, " +
                "current_flow=?, current_flow_encounter_id=?, current_pregnancy_id=?, current_pregnancy_encounter_id=?, " +
                "last_modified=?, last_modified_by=?, site_id=? " +
                "WHERE id=?;";
        ArrayList updatePregDatStatusValues = new ArrayList();
        // ega
        updatePregDatStatusValues.add(null);
        updatePregDatStatusValues.add(null);
        // lmp
        updatePregDatStatusValues.add(null);
        updatePregDatStatusValues.add(null);
        // flow
        updatePregDatStatusValues.add(null);
        updatePregDatStatusValues.add(null);
        // pregnancy id
        updatePregDatStatusValues.add(null);
        updatePregDatStatusValues.add(null);
        addModifiedAuditInfo(vo, updatePregDatStatusValues, userName, siteId);
        // id field for statusValues
        updatePregDatStatusValues.add(thisPatientId);
        try {
            DatabaseUtils.update(conn, sqlPregnancyConclusion, updatePregDatStatusValues.toArray());
        } catch (Exception e) {
            log.error(e);
        }
    }

	/**
	 * Updates values in pregnancy table
	 * Used when concluding a pregnancy.
	 * @param conn
	 * @param vo
	 * @param pregnancyEndDate
	 * @param encounterId
	 * @param thisPregnancyId
	 */
	private static void endPregnancy(Connection conn, EncounterData vo,
			Date pregnancyEndDate, Long encounterId, Long thisPregnancyId) {
		String sqlPregnancy = "UPDATE pregnancy SET date_pregnancy_end=?, " +
                "pregnancy_end_encounter_id=?, pregnancy_end_encounter_uuid=? WHERE id=?;";
        ArrayList updatePregnancyEndValues = new ArrayList();
        updatePregnancyEndValues.add(pregnancyEndDate);
        updatePregnancyEndValues.add(encounterId);
        updatePregnancyEndValues.add(vo.getUuid());
        // id field for statusValues
        updatePregnancyEndValues.add(thisPregnancyId);
        try {
            DatabaseUtils.update(conn, sqlPregnancy, updatePregnancyEndValues.toArray());
        } catch (Exception e) {
            log.error(e);
        }
	}

    /**
     * Persists instance of PatientRegistration, NewbornRecord
     * @param conn
     * @param vo
     * @param userName
     * @param siteId
     * @param formDef
     * @param parent_id
     * @param infant
     * @param weightF
     * @param egaWeeks
     * @return
     * @throws Exception
     */
    public static EncounterData createNewborn(Connection conn, EncounterData vo, String userName, Long siteId, Form formDef, Long parent_id, Patient infant, Float weightF, long egaWeeks) throws Exception {

        Map queries = QueryLoader.instance().load("/" + Constants.SQL_PATIENT_PROPERTIES);
        Map genQueries = QueryLoader.instance().load("/" + Constants.SQL_DEMO_PROPERTIES);

        // Create record in the database via DBUtil

        Long patientId = null;
        Long pregnancyId = null;
        Long encounterId = null;
        if (vo.getFormId().intValue() == 1) {
            String sqlCreatePatient = (String) queries.get("SQL_CREATE_NEWBORN");
            String sqlCreateStatus = (String) queries.get("SQL_CREATE_STATUS");
            // String sqlCreatePregnancy = (String) queries.get("SQL_CREATE_PREGNANCY");

            // create the uuid for the patient
            UUID uuid = UUID.randomUUID();
            String uuidStr = uuid.toString();
            // first prepare the patient value array
            PatientRegistration pr = (PatientRegistration) vo;
            ArrayList patientValues = new ArrayList();
            // add first_name to patientValues
            patientValues.add(pr.getField7());
            // add surname to patientValues
            patientValues.add(pr.getField6());
            // add nrc_number to patientValues
            patientValues.add(pr.getField9());
            // add patient_id_number to patientValues
            patientValues.add(pr.getField1513());
            // add obstetric_record_number to patientValues
            patientValues.add(pr.getField1134());
            // add age_at_first_visit
            patientValues.add(pr.getField1135());
            // add birthdate to patientValues
            patientValues.add(infant.getBirthdate());
            AddAuditInfo(vo, patientValues, userName, siteId);
            patientValues.add(parent_id);
            patientValues.add(infant.getSex());
            Time birthtime = infant.getTimeOfBirth();
            if (birthtime != null) {
                patientValues.add(infant.getTimeOfBirth());
            } else {
                patientValues.add(null);
            }
            // associate this infant w/ this pregnancy
            patientValues.add(infant.getPregnancyId());
            patientValues.add(infant.getParentDistrictPatientid());
            // uuid
            patientValues.add(uuidStr);
            // parent uuid
            patientValues.add(infant.getParentUuid());
            // now create the patient
            try {
                patientId = (Long) DatabaseUtils.create(conn, sqlCreatePatient, patientValues.toArray());
            } catch (SQLException e) {
                log.error(e);
            }

            // add patientId to the encounter object
            vo.setPatientId(patientId);

            ArrayList statusValues = new ArrayList();
            // add patient_id to statusValues
            statusValues.add(patientId);
            // add current_flow to statusValues
            statusValues.add(new Long("4"));
            AddAuditInfo(vo, statusValues, userName, siteId);

            // insert patient_status
            DatabaseUtils.create(conn, sqlCreateStatus, statusValues.toArray());

            // add pregnancyId to encounter object
            pregnancyId = infant.getPatientStatusreport().getCurrentPregnancyId();
            vo.setPregnancyId(pregnancyId);

            // now we can insert the encounter, and then update encounter id fields for patient_status and pregnancy records
        }

        // You can find the value used for an AUTO_INCREMENT column by using the LAST_INSERT_ID()
        String uuidStr = null;
        if (vo.getUuid() == null) {
            UUID uuid = UUID.randomUUID();
            uuidStr = uuid.toString();
            vo.setUuid(uuidStr);
        } else {
        	uuidStr = vo.getUuid();
        }
        String encSQL = "INSERT INTO encounter " +
                "set patient_id=" + vo.getPatientId() +
                ", form_id=" + vo.getFormId() +
                ", flow_id=" + vo.getFlowId() +
                ", date_visit='" + vo.getDateVisit() +
                "', pregnancy_id=" + vo.getPregnancyId() +
                ", last_modified='" + new Timestamp(System.currentTimeMillis()) +
                "', created='" + new Timestamp(System.currentTimeMillis()) +
                "', last_modified_by='" + userName +
                "', created_by='" + userName +
                "', site_id=" + siteId +
                ", created_site_id=" + siteId +
                ", uuid='" + uuidStr +
                "'; ";
        String sqlCreateEncounter = (String) genQueries.get(Constants.SQL_CREATE + vo.getFormId());

        ArrayList values = new ArrayList();
        Form form = formDef;
        FormField formField = null;
        Iterator dbPageItems = form.getPageItems().iterator();
        while (dbPageItems.hasNext()) {
            PageItem pageItem = (PageItem) dbPageItems.next();
            formField = pageItem.getForm_field();
            if (formField.isEnabled()) {
                if (!formField.getType().equals("Display")) {
                    if (formField.getType().equals("Boolean")) {
                        String fieldName = "field" + formField.getId();
                        String voValue = BeanUtils.getProperty(vo, fieldName);
                        values.add(Boolean.valueOf(voValue));
                    } else {
                        String fieldName = "field" + formField.getId();
                        values.add(BeanUtils.getProperty(vo, fieldName));
                    }

                }
            }
        }
        Object[] params2 = values.toArray();
        encounterId = (Long) DatabaseUtils.create(conn, encSQL, sqlCreateEncounter, params2);
        vo.setId(encounterId);
        vo.setSiteId(siteId);

        // now persist this Newborn Record encounter
        // todo: Now that this is saving to a normal form/table (newbornrecord), we can use one of our methods to cut down on the custom code below.

        UUID uuid = UUID.randomUUID();
        uuidStr = uuid.toString();
        vo.setUuid(uuidStr);

        String encNewbornRecordSql = "INSERT INTO encounter " +
                "set patient_id=" + vo.getPatientId() +
                ", form_id=109" +
                ", flow_id=4" +
                ", date_visit='" + vo.getDateVisit() +
                "', pregnancy_id=" + vo.getPregnancyId() +
                ", last_modified='" + new Timestamp(System.currentTimeMillis()) +
                "', created='" + new Timestamp(System.currentTimeMillis()) +
                "', last_modified_by='" + userName +
                "', created_by='" + userName +
                "', site_id=" + siteId +
                ", created_site_id=" + siteId +
                ", uuid='" + uuidStr +
                "'; ";

        String newbornRecordSql = "INSERT INTO newbornrecord(id, date_of_birth, time_of_birth_1514, sequence_num_489, " +
                " sex_490, weight_at_birth_491, ega_weeks) VALUES (LAST_INSERT_ID(), ? , ? , ? , ?, ?, ? ) ";
        ArrayList newEvalValues = new ArrayList();
        newEvalValues.add(infant.getBirthdate());
        newEvalValues.add(infant.getTimeOfBirth());
        newEvalValues.add(infant.getSequenceNumber());
        newEvalValues.add(infant.getSex());
        newEvalValues.add(weightF);
        newEvalValues.add(egaWeeks);

        Object[] params3 = newEvalValues.toArray();
        encounterId = (Long) DatabaseUtils.create(conn, encNewbornRecordSql, newbornRecordSql, params3);
        Long thisPatientId = vo.getPatientId();
        Long currentFlowId = new Long("4");
        // Update patient_status
        Pregnancy pregnancy = PregnancyDAO.getOne(conn, pregnancyId);
    	vo.setPregnancy(pregnancy);
        PatientStatusDAO.updatePatientStatusNewPreg(conn, queries, vo, pregnancyId, encounterId, thisPatientId, userName, siteId, currentFlowId);
        return vo;
    }

    /**
     * Deletes records from patientregistration, newbornrecord, newborneval, and encounter for infantId
     * @param conn
     * @param infantId
     * @return
     * @throws Exception
     */
    public static String deleteNewborn(Connection conn, Long infantId) throws Exception {
        String result = "Newborn deleted.";
        // delete the associated patient_status table
        String sql = "delete from patient_status where id=?";
        ArrayList values = new ArrayList();
        // add infantId to values
        values.add(infantId);
        DatabaseUtils.update(conn, sql, values.toArray());
        // delete the patient registration form
        sql = "DELETE patientregistration FROM patientregistration, encounter " +
                "WHERE encounter.id = patientregistration.id " +
                "AND encounter.patient_id=?";
        DatabaseUtils.update(conn, sql, values.toArray());
        // delete newborn record form
        sql = "delete newbornrecord from newbornrecord, encounter " +
                "WHERE encounter.id = newbornrecord.id " +
                "AND encounter.patient_id=?";
        DatabaseUtils.update(conn, sql, values.toArray());
        // delete newborn eval form
        sql = "delete newborneval from newborneval, encounter " +
        "WHERE encounter.id = newborneval.id " +
        "AND encounter.patient_id=?";
        DatabaseUtils.update(conn, sql, values.toArray());
        // delete newborn eval form
        sql = "delete encounter from encounter " +
                "WHERE encounter.patient_id=?";
        DatabaseUtils.update(conn, sql, values.toArray());
        // delete the infant from the patient table
        sql = "delete from patient where id=?";
        DatabaseUtils.update(conn, sql, values.toArray());
        // update the mother's infants
        return result;
    }

    /**
     * Deletes ultrasoundfetuseval record for encounterId.
     * @param conn
     * @param encounterId
     * @return
     * @throws Exception
     */
    public static String deleteFetusEval(Connection conn, Long encounterId) throws Exception {
        String result = "Fetus Evaluation deleted.";
        // delete the associated patient_status table
        String sql = null;
        ArrayList values = new ArrayList();
        // add infantId to values
        values.add(encounterId);
        // delete the patient registration form
        sql = "DELETE ultrasoundfetuseval FROM ultrasoundfetuseval, encounter " +
                "WHERE encounter.id = ultrasoundfetuseval.id " +
                "AND encounter.id=?";
        DatabaseUtils.update(conn, sql, values.toArray());
        return result;
    }

    /**
     * This should not be used for patient records - use the one that takes the vo
     * @param values
     * @param userName
     * @param siteId
     */
    protected static void AddAuditInfo(ArrayList values, String userName, Long siteId) {
        // last modified
        values.add(new Timestamp(System.currentTimeMillis()));
        // created
        values.add(new Timestamp(System.currentTimeMillis()));
        // last_modified_by
        values.add(userName);
        // created_by
        values.add(userName);
        // site_id
        values.add(siteId);
    }

    /**
     * Use this for patient records.
     * This will preserve the created/modified timestamps, user, and site id data if they are already populated.
     * @param vo
     * @param values
     * @param userName
     * @param siteId
     */
    protected static void AddAuditInfo(EncounterData vo, ArrayList values, String userName, Long siteId) {
        Timestamp created = new Timestamp(System.currentTimeMillis());
        String createdBy = userName;
        Timestamp lastModified = new Timestamp(System.currentTimeMillis());
        String lastModifiedBy = userName;
        Long currentSiteId = siteId;

        if (vo != null) {
            if (vo.getCreated() != null) {
                created = vo.getCreated();
            }

            if (vo.getCreatedBy() != null) {
                createdBy = vo.getCreatedBy();
            }

            if (vo.getLastModified() != null) {
                lastModified = vo.getLastModified();
            }

            if (vo.getLastModifiedBy() != null) {
                lastModifiedBy = vo.getLastModifiedBy();
            }

            if (vo.getSiteId() != null) {
                currentSiteId = vo.getSiteId();
            }
        } else {
            log.debug("vo is null - values: " + values);
        }

        // last modified
        values.add(lastModified);
        // created
        values.add(created);
        // last_modified_by
        values.add(lastModifiedBy);
        // created_by
        values.add(createdBy);
        // site_id
        values.add(currentSiteId);
    }

    /**
     * Adds lastModified and siteId info to the values ArrayList.
     * @param vo
     * @param values
     * @param userName
     * @param siteId
     */
    protected static void addModifiedAuditInfo(EncounterData vo, ArrayList values, String userName, Long siteId) {
        Timestamp lastModified = new Timestamp(System.currentTimeMillis());
        if (vo.getLastModified() != null) {
            lastModified = vo.getLastModified();
        }
        String lastModifiedBy = userName;
        if (vo.getLastModifiedBy() != null) {
            lastModifiedBy = vo.getLastModifiedBy();
        }
        Long currentSiteId = siteId;
        if (vo.getSiteId() != null) {
            currentSiteId = vo.getSiteId();
        }
        // last modified
        values.add(lastModified);
        // last_modified_by
        values.add(lastModifiedBy);
        // site_id
        values.add(currentSiteId);
    }

    /**
     * Persist a form.
     * @param form
     * @param userName
     * @param siteId
     * @return formId
     * @throws Exception
     */
    public static Long saveNewForm(Form form, String userName, Long siteId) throws Exception {
        Connection conn = DatabaseUtils.getAdminConnection();
        String name = form.getName();
        String label = form.getLabel();
        boolean reauth = form.isRequireReauth();
        boolean requirePatient = form.isRequirePatient();
        boolean enabled = form.isEnabled();
        Timestamp lastModified = new Timestamp(System.currentTimeMillis());
        Timestamp created = new Timestamp(System.currentTimeMillis());
        Long flowId = form.getFlowId();
        Integer flowOrder = form.getFlowOrder();
        Long FormTypeId = form.getFormTypeId();
        Integer maxSubmissions = form.getMaxSubmissions();
        String sql = "insert into admin.form (name, label, require_reauth, require_patient, is_enabled, " +
                "last_modified, created, last_modified_by, created_by, site_id, " +
                "flow_id, flow_order, form_type_id, max_submissions) " +
                "values ('" +name + "','" +  label + "'," + reauth + "," + requirePatient + "," + enabled +
                ",'" + lastModified +  "','" + created + "','" + userName + "','" + userName + "'," + siteId +
                "," + flowId + "," + flowOrder + "," + FormTypeId + "," + maxSubmissions + ")";
        DatabaseUtils.create(conn, sql);
        log.debug("New form. SQL: " + sql);
        String tableName = form.getName();
        String fkName =  "FK" + tableName;
        sql = "CREATE TABLE zeprs." + tableName + " (\n" +
                "  id bigint(20) NOT NULL default '0',\n" +
                "  PRIMARY KEY  (`id`),\n" +
                "  KEY " + fkName + " (id),\n" +
                "  CONSTRAINT " + fkName + " FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
        DatabaseUtils.create(conn, sql);
        String idSql = "SELECT LAST_INSERT_ID() AS id;";
        Long formId = (Long) DatabaseUtils.getScalar(conn, idSql);
        ApplicationUpdate appUpdate = new ApplicationUpdate();
        Timestamp datePosted = new Timestamp(System.currentTimeMillis());
        String type = "S";
        appUpdate.setDateposted(datePosted);
        appUpdate.setJob(sql);
        appUpdate.setType(type);
        Long id = (Long) ApplicationUpdateDAO.save(conn, "admin", null, datePosted, null, type, sql);
        appUpdate.setId(id);
        // XML version is a safety in case the admin db get wiped out.
        XmlUtils.save(appUpdate, Constants.ARCHIVE_PATH_LOGS_APPUPDATES + "/update" + id + ".xml");
        log.debug("New table. SQL: " + sql);
        // now create table for zeprsdemo
        sql = "CREATE TABLE zeprsdemo." + tableName + " (\n" +
                "  id bigint(20) NOT NULL default '0',\n" +
                "  PRIMARY KEY  (`id`),\n" +
                "  KEY " + fkName + " (id),\n" +
                "  CONSTRAINT " + fkName + " FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
        DatabaseUtils.create(conn, sql);
        appUpdate = new ApplicationUpdate();
        appUpdate.setDateposted(datePosted);
        appUpdate.setJob(sql);
        appUpdate.setType(type);
        id = (Long) ApplicationUpdateDAO.save(conn, "admin", null, datePosted, null, type, sql);
        appUpdate.setId(id);
        // XML version is a safety in case the admin db get wiped out.
        XmlUtils.save(appUpdate, Constants.ARCHIVE_PATH_LOGS_APPUPDATES + "/update" + id + ".xml");
        log.debug("New table. SQL: " + sql);
        // create the table
       /* FormField formField = new FormField();
        formField.setEnabled(true);
        formField.setLabel("BEGIN TABLE");
        formField.setType("Display");*/
        PageItem pageItem = new PageItem();
        pageItem.setInputType("display-tbl-begin");
        pageItem.setFormFieldId(new Long("1427"));
        pageItem.setCols(2);
        pageItem.setVisible(true);
        pageItem.setFormId(formId);
        sql = "insert into admin.page_item (form_field_id, display_order, input_type, close_row, column_number, " +
        "last_modified, created, last_modified_by, created_by, site_id, " +
        "size, maxlength, rows, cols, visible, visible_enum_id_trigger1, visible_dependencies1, " +
        "visible_enum_id_trigger2, visible_dependencies2, selected_enum, form_id, colspan) " +
        "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ArrayList values = new ArrayList();
		values.add(pageItem.getFormFieldId());
		values.add(pageItem.getDisplayOrder());
		values.add(pageItem.getInputType());
		values.add(pageItem.isCloseRow());
		values.add(pageItem.getColumnNumber());
		FormDAO.AddAuditInfo(values, userName, siteId);
		values.add(pageItem.getSize());
		values.add(pageItem.getMaxlength());
		values.add(pageItem.getRows());
		values.add(pageItem.getCols());
		values.add(pageItem.isVisible());
		values.add(pageItem.getVisibleEnumIdTrigger1());
		values.add(pageItem.getVisibleDependencies1());
		values.add(pageItem.getVisibleEnumIdTrigger2());
		values.add(pageItem.getVisibleDependencies2());
		values.add(pageItem.getSelectedEnum());
		values.add(pageItem.getFormId());
		values.add(pageItem.getColspan());
		Long pageItemId = (Long) DatabaseUtils.create(conn, sql, values.toArray());

		pageItem = new PageItem();
        pageItem.setInputType("display-tbl-end");
        pageItem.setFormFieldId(new Long("1428"));
        pageItem.setCols(null);
        pageItem.setVisible(true);
        pageItem.setFormId(formId);
        sql = "insert into admin.page_item (form_field_id, display_order, input_type, close_row, column_number, " +
        "last_modified, created, last_modified_by, created_by, site_id, " +
        "size, maxlength, rows, cols, visible, visible_enum_id_trigger1, visible_dependencies1, " +
        "visible_enum_id_trigger2, visible_dependencies2, selected_enum, form_id, colspan) " +
        "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        values = new ArrayList();
		values.add(pageItem.getFormFieldId());
		values.add(pageItem.getDisplayOrder());
		values.add(pageItem.getInputType());
		values.add(pageItem.isCloseRow());
		values.add(pageItem.getColumnNumber());
		FormDAO.AddAuditInfo(values, userName, siteId);
		values.add(pageItem.getSize());
		values.add(pageItem.getMaxlength());
		values.add(pageItem.getRows());
		values.add(pageItem.getCols());
		values.add(pageItem.isVisible());
		values.add(pageItem.getVisibleEnumIdTrigger1());
		values.add(pageItem.getVisibleDependencies1());
		values.add(pageItem.getVisibleEnumIdTrigger2());
		values.add(pageItem.getVisibleDependencies2());
		values.add(pageItem.getSelectedEnum());
		values.add(pageItem.getFormId());
		values.add(pageItem.getColspan());
		pageItemId = (Long) DatabaseUtils.create(conn, sql, values.toArray());
		return formId;
    }

    /**
     * Update a form
     * unused
     * @param form
     * @param userName
     * @param siteId
     * @throws Exception
     */
    public static void updateForm(Form form, String userName, Long siteId) throws Exception {
        Connection conn = DatabaseUtils.getAdminConnection();
        String sql = "update form set name=?, label=?, require_reauth=?, require_patient=?, is_enabled=?, " +
                "last_modified=?, created=?, last_modified_by=?, created_by=?, site_id=?, " +
                "flow_id=?, flow_order=?, form_type_id=?, max_submissions=? where id=?";
        ArrayList values = new ArrayList();
        values.add(form.getName());
        values.add(form.getLabel());
        values.add(form.isRequireReauth());
        values.add(form.isRequirePatient());
        values.add(form.isEnabled());
        AddAuditInfo(values, userName, siteId);
        values.add(form.getFlowId());
        values.add(form.getFlowOrder());
        values.add(form.getFormTypeId());
        values.add(form.getMaxSubmissions());
        values.add(form.getId());
        DatabaseUtils.update(conn, sql, values.toArray());
    }
}