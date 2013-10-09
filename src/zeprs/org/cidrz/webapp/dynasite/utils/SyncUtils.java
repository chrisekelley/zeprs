package org.cidrz.webapp.dynasite.utils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import javax.servlet.ServletException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.dbutils.QueryLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.project.zeprs.valueobject.EncounterDataArchive;
import org.cidrz.project.zeprs.valueobject.gen.PartographStatus;
import org.cidrz.project.zeprs.valueobject.gen.ReferralReasons;
import org.cidrz.project.zeprs.valueobject.report.PatientStatusReport;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.dao.CommentDAO;
import org.cidrz.webapp.dynasite.dao.EncounterArchiveDAO;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.dao.OutcomeArchiveDAO;
import org.cidrz.webapp.dynasite.dao.OutcomeDAO;
import org.cidrz.webapp.dynasite.dao.PatientStatusDAO;
import org.cidrz.webapp.dynasite.dao.PregnancyDAO;
import org.cidrz.webapp.dynasite.dao.ProblemArchiveDAO;
import org.cidrz.webapp.dynasite.dao.ProblemDAO;
import org.cidrz.webapp.dynasite.dao.partograph.PartographDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.rules.Outcome;
import org.cidrz.webapp.dynasite.rules.OutcomeArchive;
import org.cidrz.webapp.dynasite.rules.impl.OutcomeImpl;
import org.cidrz.webapp.dynasite.utils.sort.DateVisitOrderComparator;
import org.cidrz.webapp.dynasite.valueobject.Comment;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.Partograph;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.valueobject.Pregnancy;
import org.cidrz.webapp.dynasite.valueobject.Problem;
import org.cidrz.webapp.dynasite.valueobject.ProblemArchive;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;
import org.cidrz.webapp.dynasite.valueobject.Site;

public class SyncUtils {

    /**
     * Commons Logging instance.
     */
	private static Log log = LogFactory.getFactory().getInstance(SyncUtils.class);

	/**
	 * Updates patient record from data imported from remote site.
	 * This is for a patient that already exists on the system.
	 * @param conn
	 * @param dupSP - sessionPatient of local patient
	 * @param patient - patient being imported
	 * @param duplicateId - id of local patient
	 * @param comments - messages from import process
	 * @param view
	 * @throws Exception
	 * @throws InvocationTargetException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 */
	public static void updatePatientRecord(Connection conn, SessionPatient dupSP, Patient patient, Long duplicateId, StringBuffer comments, Boolean view) throws InstantiationException, ClassNotFoundException, InvocationTargetException, Exception {
	    Boolean update = false;
	    Boolean isDirty = false;
	    Boolean thisIsDirty = false;
	    Map queries = null;
	    try {
	        queries = QueryLoader.instance().load("/" + Constants.SQL_PATIENT_PROPERTIES);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    // log.debug("Record change for patient: " + patient.getDistrictPatientid() + " ( id: " + duplicateId + ")" + " eq test: " + eq);
	    // First update encounter values of current encounters.
	    Long importedPatientId = patient.getId();
	    int updatesNum = 0;
	    comments.append("Patient ").append(patient.getDistrictPatientid()).append(" update: ");
	    // add any new encounters
	    HashMap currentMap = new HashMap();  // create a hashmap that stores the uuid and created_date, if any
	    List currentEncounters = EncountersDAO.getAll(conn, duplicateId);
	    for (int i = 0; i < currentEncounters.size(); i++) {
	        EncounterData encounterData = (EncounterData) currentEncounters.get(i);
	        currentMap.put(encounterData.getUuid(), encounterData.getCreated());
	    }
	    int currentEncountersSize = currentEncounters.size();
	    if (view) {
	        log.debug("currentEncountersSize: " + currentEncountersSize);
	    }
	    int importedEncountersSize = 0;
	    int newRecordsNum = 0;
	    Map newIdMap = new WeakHashMap();
	    List importPregnancies = patient.getPregnancyList();
	    // log.debug("Processing encounters for " + patient.getDistrictPatientid());
	    for (int i = 0; i < importPregnancies.size(); i++) {
	        Pregnancy pregnancy = (Pregnancy) importPregnancies.get(i);
	        List importEncounters = pregnancy.getEncounters();
	        DateVisitOrderComparator doc = new DateVisitOrderComparator();
	        Collections.sort(importEncounters, doc);
	        importedEncountersSize = importEncounters.size();
	        if (view) {
	            log.debug("importedEncountersSize: " + importedEncountersSize);
	        }
	        for (int j = 0; j < importEncounters.size(); j++) {
	            EncounterData encounterData = (EncounterData) importEncounters.get(j);
	            //log.debug("encounter id: " + encounterData + " created: " + encounterData.getCreated() + " site: " + encounterData.getSiteId() + " formId: " + encounterData.getFormId());
	            String uuid = encounterData.getUuid();
	            if (!currentMap.containsKey(uuid)) {
	                update = true;
	                if (!view) {
	                    SyncUtils.importEncounter(encounterData, duplicateId, dupSP.getCurrentPregnancyId(), conn, encounterData.getCreatedBy(), newIdMap);
	                }
	                newRecordsNum ++;
	            }
	        }
	        importedEncountersSize += importEncounters.size();
	        if (view) {
	             log.debug("importedEncountersSize: " + importedEncountersSize);
	        }
	        //todo: check lastModified date in partographStatus
	        if (patient.getParentId() == null) {
	            Long currentPregnancyId = dupSP.getCurrentPregnancyId();
	            Partograph importedPartograph = pregnancy.getPartograph();
	            try {
	                Partograph currentPartograph = PartographDAO.getPartograph(conn, duplicateId, currentPregnancyId);
	                if (view) {
	                    if (currentPartograph.getPartographStatus() != null) {
	                        String status = "currentPartograph last Mod: " + currentPartograph.getPartographStatus().getLastModified();
	                        log.debug(status);
	                    }
	                    if (importedPartograph.getPartographStatus() != null) {
	                        String status = "importedPartograph last Mod: " + importedPartograph.getPartographStatus().getLastModified();
	                        log.debug(status);
	                    }
	                } else {
	                    Object result = PartographDAO.update(conn, duplicateId, pregnancy.getId(), importedPartograph, currentPartograph, currentPregnancyId, comments);
	                    if (result != null) {
	                        update = Boolean.TRUE;
	                        PartographStatus partoStatus = currentPartograph.getPartographStatus();
	                        Long siteId = partoStatus.getSiteId();
	                        String username = partoStatus.getLastModifiedBy();
	                        PatientStatusDAO.touchPatientStatus(conn, null, username, siteId, duplicateId);
	                    }
	                }
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        thisIsDirty = processOutcomes(conn, dupSP, duplicateId, comments, view, pregnancy);
	        isDirty = thisIsDirty=true ? true:false;
	    }
	    currentMap.clear();
	    if (update) {
	        comments.append(newRecordsNum).append(" new records. ");
	    }

	    try {
	        updatesNum += XmlUtils.updateEncounterValues(conn, duplicateId, patient, dupSP, view);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    if (updatesNum > 0) {
	        comments.append(updatesNum).append(" values have been modified in patient record. ");
	    }

	    // Add new problems
	    thisIsDirty = processProblems(conn, dupSP, patient, duplicateId, comments, view);
        isDirty = thisIsDirty=true ? true:false;

	    // Add new comments
        thisIsDirty = processComments(conn, patient, duplicateId, comments, view);
        isDirty = thisIsDirty=true ? true:false;

	    if (patient.getOutcomeDeletions() != null) {
	    	thisIsDirty = processOutcomeDeletions(conn, patient, comments, duplicateId, view);
	        isDirty = thisIsDirty=true ? true:false;
	    }
	    if (patient.getProblemDeletions() != null) {
	    	thisIsDirty = processProblemDeletions(conn, patient, comments, duplicateId, view);
	        isDirty = thisIsDirty=true ? true:false;
	    }
	    if (patient.getEncounterDeletions() != null) {
	    	thisIsDirty = processEncounterDeletions(conn, patient, comments, duplicateId, view);
	        isDirty = thisIsDirty=true ? true:false;
	    }

	    if ((newIdMap.size() > 0) || (isDirty == true) || (updatesNum > 0)) {
	        if (view) {
	            log.debug("New id map: " + newIdMap.toString() + " isDirty: " + isDirty);
	        } else {
	            //PatientStatusDAO.update(patient, newIdMap, dupSP.getCurrentPregnancyId(), conn, queries, duplicateId);
	            PatientStatusReport psr = patient.getPatientStatusreport();
	            EncounterData enc = new EncounterData();
	            enc.setLastModified(psr.getLastModified());
	            enc.setLastModifiedBy(psr.getLastModifiedBy());
	            enc.setSiteId(psr.getSiteId());
	            PatientStatusDAO.touchPatientStatus(conn, enc, psr.getLastModifiedBy(), psr.getSiteId(), duplicateId);
	        }
	    } else {
	        String message = " No new records, updated, or deletions.";
	        comments.append(message);
	        log.debug(message);
	    }
	}

	/**
	 * Imports comments
	 * @param conn
	 * @param patient
	 * @param duplicateId
	 * @param comments
	 * @param view
	 * @return TODO
	 * @throws SQLException
	 * @throws ServletException
	 */
	private static Boolean processComments(Connection conn, Patient patient, Long duplicateId, StringBuffer comments,
			Boolean view) throws SQLException, ServletException {
        Boolean isDirty = false;
        int currentCommentsSize = 0;
	    List<Comment> currentComments = CommentDAO.getAll(conn, duplicateId);
	    if (currentComments != null) {
	        currentCommentsSize = currentComments.size();
	    }
	    int importedCommentsSize = 0;
	    List<Comment> importComments = patient.getComments();
	    if (importComments != null) {
	        importedCommentsSize = importComments.size();
	    }

	    Map<String,String> currentCommentMap = new HashMap(); // create a hashmap that stores the uuid
	    if (currentComments != null) {
	    	// loop through the current items and pop them into Map
	    	for (Comment item : currentComments) {
	    		String uuid = item.getUuid();
	    		currentCommentMap.put(uuid, uuid);
	    	}
	    }

	    if (view) {
	        log.debug(" currentCommentsSize: " + currentCommentsSize + " importedCommentsSize: " + importedCommentsSize);
	    } else {
	    	for (int i = 0; i < importComments.size(); i++) {
	    		Comment item = (Comment) importComments.get(i);
	    		item.setImportCommentId(item.getId());
	    		String uuid = item.getUuid();
	    		if (currentCommentMap.get(uuid) == null) {
	    			XmlUtils.saveImportedComment(item, conn, comments);
	    		}
    			isDirty = true;
	    	}
	    }
	    currentCommentMap.clear();
	    return isDirty;
	}

	/**
	 * Imports problems
	 * @param conn
	 * @param dupSP
	 * @param patient
	 * @param duplicateId
	 * @param comments
	 * @param view
	 * @return TODO
	 * @throws SQLException
	 * @throws ServletException
	 */
	private static Boolean processProblems(Connection conn, SessionPatient dupSP,
			Patient patient, Long duplicateId, StringBuffer comments,
			Boolean view) throws SQLException, ServletException {
        Boolean isDirty = false;
        int currentProblemsSize = 0;
	    List<Problem> currentProblems = ProblemDAO.getAll(conn, duplicateId);
	    if (currentProblems != null) {
	        currentProblemsSize = currentProblems.size();
	    }
	    int importedProblemsSize = 0;
	    List<Problem> importProblems = patient.getProblems();
	    if (importProblems != null) {
	        importedProblemsSize = importProblems.size();
	    }

	    Map<String,String> currentProblemMap = new HashMap(); // create a hashmap that stores the uuid
	    if (currentProblems != null) {
	    	currentProblemsSize = currentProblems.size();
	    	// loop through the current problems and pop them into currentProblemMap
	    	for (Problem problem : currentProblems) {
	    		String problemUuid = problem.getUuid();
	    		currentProblemMap.put(problemUuid, problemUuid);
	    	}
	    }

	    if (view) {
	    	log.debug(" currentProblemsSize: " + currentProblemsSize + " importedProblemsSize: " + importedProblemsSize);
	    } else {
	    	for (int i = 0; i < importProblems.size(); i++) {
	    		Problem problem = (Problem) importProblems.get(i);
	    		problem.setImportProblemId(problem.getId());
	    		problem.setPatientId(duplicateId);
	    		problem.setPregnancyId(dupSP.getCurrentPregnancyId());
	    		String problemUuid = problem.getUuid();
	    		if (currentProblemMap.get(problemUuid) == null) {
	    			Long id = ProblemDAO.save(conn, problem, problem.getCreatedBy(), problem.getSiteId(), problem.getUuid(), problem.getPatientUuid(), problem.getPregnancyUuid());
	    			comments.append(" new problem: id ").append(id);
	    		}
    			isDirty = true;
	    	}
	    }
	    currentProblemMap.clear();
	    return isDirty;
	}

	/**
	 * Import new Outcomes
	 * @param conn
	 * @param dupSP
	 * @param duplicateId
	 * @param comments
	 * @param view
	 * @param pregnancy
	 * @return TODO
	 * @throws SQLException
	 * @throws ServletException
	 * @throws ObjectNotFoundException
	 */
	private static Boolean processOutcomes(Connection conn, SessionPatient dupSP, Long duplicateId, StringBuffer comments,
			Boolean view, Pregnancy pregnancy) throws SQLException, ServletException, ObjectNotFoundException {
        Boolean isDirty = false;
        String importedPregnancyUuid = pregnancy.getUuid();
		Pregnancy localPregnancy;
		try {
			localPregnancy = PregnancyDAO.getOne(conn, importedPregnancyUuid);
			// Add new outcomes
			int currentOutcomesSize = 0;
			List<OutcomeImpl> currentOutcomes = OutcomeDAO.getAll(conn, duplicateId, localPregnancy.getId());
			Map currentOutcomeMap = new HashMap(); // create a hashmap that stores the uuid
			if (currentOutcomes != null) {
				currentOutcomesSize = currentOutcomes.size();
				// loop through the current problems and pop them into currentOutcomeMap
				for (OutcomeImpl outcomeImpl : currentOutcomes) {
					String outcomeUuid = outcomeImpl.getOutcomeUuid();
					currentOutcomeMap.put(outcomeUuid, outcomeUuid);
				}
			}
			int importedOutcomesSize = 0;
			List importOutcomes = pregnancy.getActiveProblems();
			if (importOutcomes != null) {
				importedOutcomesSize = importOutcomes.size();
			}

			if (view) {
			    log.debug(" currentOutcomesSize: " + currentOutcomesSize + " importedOutcomesSize: " + importedOutcomesSize);
			} else {
				for (int j = 0; j < importOutcomes.size(); j++) {
					Class superClazz = importOutcomes.get(j).getClass().getSuperclass();
					if (superClazz.equals(Outcome.class)) {
						Outcome outcome = (Outcome) importOutcomes.get(j);
						outcome.setImportOutcomeId(outcome.getId());
						outcome.setPatientId(duplicateId);
						outcome.setPregnancyId(dupSP.getCurrentPregnancyId());
						String outcomeUuid = outcome.getOutcomeUuid();
						if (currentOutcomeMap.get(outcomeUuid) == null) {
							// do not have this outcome locally.
							try {
								// Fetch the id for the imported encounter.
								EncounterData encounter = (EncounterData) EncountersDAO.getOneByImportedId(conn, outcome.getEncounterId(), duplicateId, outcome.getSiteId());
								Long newId = encounter.getId();
								outcome.setEncounterId(newId);
								Long id = OutcomeDAO.save(conn, outcome, outcome.getCreatedBy(), outcome.getSiteId());
								comments.append(" new outcome: id ").append(id);
							} catch (ObjectNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
	        			isDirty = true;
					}
				}
			}
			currentOutcomeMap.clear();
		} catch (ObjectNotFoundException e1) {
			log.debug("Aborting Outcome processing: LocalPregnancy not found for importedPregnancyUuid: " + importedPregnancyUuid + "Local patient id: " + duplicateId + " zeors id: " + dupSP.getDistrictPatientid());
		}
		return isDirty;
	}

	/**
	 * Import a single encounter created at a remote site into a current patient's record
	 * Current patient is the patient in the master record - on the main ZEPRS server
	 * @param encounter
	 * @param patientId - current patient's id
	 * @param pregnancyId - current patient's preg. id.
	 * @param conn
	 * @param username
	 * @param newIdMap - HashMap to keep track of new id's
	 * @throws Exception
	 * @throws ServletException
	 * @throws SQLException
	 * @throws InvocationTargetException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 */
	public static void importEncounter(EncounterData encounter, Long patientId, Long pregnancyId, Connection conn, String username, Map newIdMap) throws InstantiationException, ClassNotFoundException, InvocationTargetException, SQLException, ServletException, Exception {
	    Form formDef;
	    Long siteId;
	    EncounterData vo;
	    if (encounter.getFormId().intValue() != 1) {
	        if (encounter.getFormId().intValue() == 94) {  // need to set encounter_id for referring encounter.
	            ReferralReasons referralReasons = (ReferralReasons) encounter;
	            //Long newRefEnc = (Long) newIdMap.get(referralReasons.getField1917());
	            Long newRefEnc;
				try {
					// use the encounter_uuid of the referral to set the encounter_id
					String encounterUuid = referralReasons.getField2153();
					newRefEnc = EncountersDAO.checkEncounterUuid(conn, encounterUuid);
	                referralReasons.setField1917(newRefEnc.intValue());
				} catch (Exception e1) {
	                log.debug("New referral encounter id not available. Patient: " + patientId + " encounterID: " + referralReasons.getId() + " encounter.getUuid(): " + encounter.getUuid() + " referralReasons.getField1917(): " + referralReasons.getField1917());
				}
	        }
	        encounter.setImportEncounterId(encounter.getId());
	        encounter.setPatientId(patientId);
	        encounter.setPregnancyId(pregnancyId);
	        formDef = (Form) DynaSiteObjects.getForms().get(encounter.getFormId());
	        siteId = encounter.getSiteId();
	        vo = PopulatePatientRecord.importForm(conn, encounter, formDef, siteId, username);
	        newIdMap.put(vo.getImportEncounterId(), vo.getId());
	    }
	}

    /**
     * Loops through deleted items passed from the patient object imported from xml and deletes them
     * @param conn
     * @param patient
     * @param comments
     * @param duplicateId
     * @param view
     * @return isDirty - if patient status needs to be updated.
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     * @throws ObjectNotFoundException
     */
    static Boolean processOutcomeDeletions(Connection conn, Patient patient, StringBuffer comments, Long duplicateId, Boolean view) throws SQLException, ServletException, ObjectNotFoundException, IOException {
        Boolean isDirty = false;
    	List<OutcomeArchive> deletions = patient.getOutcomeDeletions();
        List<OutcomeArchive> currentDeletions = OutcomeArchiveDAO.getAll(conn, duplicateId);
        int importDelSize = 0;
        int currentDelSize = 0;
        if (deletions != null) {
            importDelSize = deletions.size();
        }
        if (currentDeletions != null) {
            currentDelSize = currentDeletions.size();
        }

        Map<String,String> currentItemMap = new HashMap(); // create a hashmap that stores the uuid
        if (currentDeletions != null) {
        	// loop through the current items and pop them into the Map
        	for (OutcomeArchive item : currentDeletions) {
        		String uuid = item.getUuid();
        		currentItemMap.put(uuid, uuid);
        	}
        }

        if (view) {
        	log.debug("Current Outcome Dels: " + currentDelSize + " Import Outcome Dels" + importDelSize);
        } else {
        	if (deletions != null) {
        		int diff = importDelSize - currentDelSize;
        		comments.append(" Processing " + diff + " Outcome deletions. ");
        		for (int i = 0; i < importDelSize; i++) {
        			OutcomeArchive archive = (OutcomeArchive) deletions.get(i);
        			Site site = (Site) DynaSiteObjects.getClinicMap().get(archive.getSiteId());
        			String uuid = archive.getUuid();
        			if (currentItemMap.get(uuid) == null) {
        				comments.append(" deleting outcome id:  ").append(archive.getId());
        				PatientRecordUtils.deleteOutcome(conn, uuid, archive.getCreatedBy(), site, archive, patient, duplicateId);
        				isDirty = true;
        			}
        		}
        	}
        }
        currentItemMap.clear();
        return isDirty;
    }

	/**
	 * Loops through deleted items passed from the patient object imported from xml and deletes them
	 * @param conn
	 * @param patient
	 * @param comments
	 * @param duplicateId
	 * @param view
	 * @return TODO
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 * @throws ObjectNotFoundException
	 */
	static Boolean processProblemDeletions(Connection conn, Patient patient, StringBuffer comments, Long duplicateId, Boolean view) throws SQLException, ServletException, ObjectNotFoundException, IOException {
        Boolean isDirty = false;
        List<ProblemArchive> deletions = patient.getProblemDeletions();
	    List<ProblemArchive> currentDeletions = ProblemArchiveDAO.getAll(conn, duplicateId);
	    int importDelSize = 0;
	    int currentDelSize = 0;
	    if (deletions != null) {
	        importDelSize = deletions.size();
	    }
	    if (currentDeletions != null) {
	        currentDelSize = currentDeletions.size();
	    }

	    Map<String,String> currentItemMap = new HashMap(); // create a hashmap that stores the uuid
        if (currentDeletions != null) {
        	// loop through the current items and pop them into the Map
        	for (ProblemArchive item : currentDeletions) {
        		String uuid = item.getUuid();
        		currentItemMap.put(uuid, uuid);
        	}
        }

	    if (view) {
	        log.debug("Current Problem Dels: " + currentDelSize + " Import Problem Dels" + importDelSize);
	    } else {
	    	if (deletions != null) {
	    		int diff = deletions.size() - currentDeletions.size();
	    		comments.append(" Processing " + diff + " Problem deletions. ");
	    		for (int i = 0; i < deletions.size(); i++) {
	    			ProblemArchive archive = (ProblemArchive) deletions.get(i);
	    			Site site = (Site) DynaSiteObjects.getClinicMap().get(archive.getSiteId());
	    			String uuid = archive.getUuid();
	    			if (currentItemMap.get(uuid) == null) {
	    				comments.append(" deleting Problem id:  ").append(archive.getId());
	    				PatientRecordUtils.deleteProblem(conn, uuid, archive.getCreatedBy(), site, archive, patient, duplicateId);
	    			}
        			isDirty = true;
	    		}
	    	}
	    }
        currentItemMap.clear();
        return isDirty;
	}

	/**
	 * Loop through encounter deletions list and process them.
	 * @param conn
	 * @param patient
	 * @param comments
	 * @param duplicateId
	 * @param view
	 * @return TODO
	 * @throws Exception
	 */
	static Boolean processEncounterDeletions(Connection conn, Patient patient, StringBuffer comments, Long duplicateId, Boolean view) throws Exception {
        Boolean isDirty = false;
	    List<EncounterDataArchive> deletions = patient.getEncounterDeletions();
	    List<EncounterDataArchive> currentDeletions = EncounterArchiveDAO.getAll(conn, duplicateId);
	    int importDelSize = 0;
	    int currentDelSize = 0;
	    if (deletions != null) {
	        importDelSize = deletions.size();
	    }
	    if (currentDeletions != null) {
	        currentDelSize = currentDeletions.size();
	    }

	    Map<String,String> currentItemMap = new HashMap(); // create a hashmap that stores the uuid
        if (currentDeletions != null) {
        	// loop through the current items and pop them into the Map
        	for (EncounterDataArchive item : currentDeletions) {
        		String uuid = item.getUuid();
        		currentItemMap.put(uuid, uuid);
        	}
        }
	    if (view) {
	    	log.debug("Current Encounter Dels: " + currentDelSize + " Import Encounter Dels" + importDelSize);
	    } else {
	    	if (deletions != null) {
	    		int diff = deletions.size() - currentDeletions.size();
	    		comments.append(" Processing " + diff + " Problem deletions. ");
	    		for (int i = 0; i < deletions.size(); i++) {
	    			EncounterDataArchive encounterArchive = (EncounterDataArchive) deletions.get(i);
	    			Site site = (Site) DynaSiteObjects.getClinicMap().get(encounterArchive.getSiteId());
	    			String uuid = encounterArchive.getUuid();
	    			if (currentItemMap.get(uuid) == null) {
	    		    	Long encounterId;
						try {
							encounterId = EncountersDAO.checkEncounterUuid(conn, uuid);
							comments.append(" Deleting encounter id:  ").append(encounterArchive.getId());
		    				EncounterData vo = new EncounterData();
		    				vo.setLastModified(encounterArchive.getLastModified());
		    				vo.setLastModifiedBy(encounterArchive.getLastModifiedBy());
		    				vo.setSiteId(encounterArchive.getSiteId());
		    				PatientRecordUtils.deleteEncounter(conn, encounterArchive.getFormId(), encounterId, encounterArchive.getLastModifiedBy(), site, vo);
						} catch (ObjectNotFoundException e) {
							EncounterData encounter = new EncounterData();
							try {
								BeanUtils.copyProperties(encounter, encounterArchive);
							} catch (ConversionException e1) {
								// log.debug("unable to copy value: " + e);
							}
							encounter.setUuid(uuid);
							encounter.setPatientId(duplicateId);
					        String siteAbbrev = site.getAbbreviation();
					        String fileName = org.cidrz.webapp.dynasite.Constants.ARCHIVE_PATH + siteAbbrev + "/deletions/" + "enc" + uuid + ".xml";
							XmlUtils.save(encounter, fileName);
					        Long id = EncounterArchiveDAO.saveArchive(conn, encounterArchive.getCreatedBy(), encounter, encounterArchive.getLastModified());
						}

	    			}
        			isDirty = true;
	    		}
	    	}
	    }
        currentItemMap.clear();
        return isDirty;
	}

}
