/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.struts.action;

import java.security.Principal;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.project.zeprs.report.AntenatalHistoryReport;
import org.cidrz.project.zeprs.report.PostnatalHistoryReport;
import org.cidrz.project.zeprs.report.PostnatalMaternal;
import org.cidrz.project.zeprs.report.valueobject.AntenatalHistory;
import org.cidrz.project.zeprs.report.valueobject.ChildVisitReport;
import org.cidrz.project.zeprs.report.valueobject.DrugsDispensed;
import org.cidrz.project.zeprs.report.valueobject.PmtctReport;
import org.cidrz.project.zeprs.report.valueobject.PostnatalHistory;
import org.cidrz.project.zeprs.report.valueobject.RegimenLabDrugsReport;
import org.cidrz.project.zeprs.valueobject.report.gen.ArvRegimenReport;
import org.cidrz.project.zeprs.valueobject.report.gen.InfantDischargeSummaryReport;
import org.cidrz.project.zeprs.valueobject.report.gen.LabTestReport;
import org.cidrz.project.zeprs.valueobject.report.gen.PostnatalInfantReport;
import org.cidrz.project.zeprs.valueobject.report.gen.ProbPostnatalReport;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.dao.LabTestDAO;
import org.cidrz.webapp.dynasite.dao.OutcomeDAO;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.dao.reports.ReportDAO;
import org.cidrz.webapp.dynasite.rules.impl.OutcomeImpl;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.utils.StringManipulation;
import org.cidrz.webapp.dynasite.utils.sort.DateVisitOrderComparator;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.FormField;
import org.cidrz.webapp.dynasite.valueobject.PageItem;
import org.cidrz.webapp.dynasite.valueobject.Patient;

public class SafeMotherhoodClinicCardAction extends BaseAction {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(SafeMotherhoodClinicCardAction.class);

    protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	// retrieve current settings from the session
        HttpSession session = request.getSession(true);
        Principal user = request.getUserPrincipal();
        String username = user.getName();
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            Long patientId = null;
            try {
                patientId = Long.valueOf(request.getParameter("patientId"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            DateVisitOrderComparator doc = new DateVisitOrderComparator();
            AntenatalHistory ante = AntenatalHistoryReport.generateAntenatalHistory(conn, patientId);
            PostnatalHistory post = null;
            try {
                post = PostnatalHistoryReport.generatePostnatalHistory(conn, patientId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        	// ${post.pregnancyReport.deliverySum.dateVisit}
            
            Long currentPregnancyID = PatientRecordUtils.getPregnancyId(conn, patientId);
            List<OutcomeImpl> anteReferrals = new ArrayList<OutcomeImpl>();
            List<OutcomeImpl> postReferrals = new ArrayList<OutcomeImpl>();
            List<OutcomeImpl> allReferrals = OutcomeDAO.getReferrals(conn, patientId, currentPregnancyID);
            Date deliveryDateVisit = null;
            if (post.getPregnancyReport().getDeliverySum() != null) {
                deliveryDateVisit = post.getPregnancyReport().getDeliverySum().getDateVisit();
            }
            // Filter out any referrals before delivery.
            for (OutcomeImpl outcomeImpl : allReferrals) {
				Date admissionDate = outcomeImpl.getUthAdmissionDate();
				if (admissionDate != null) {
					if (deliveryDateVisit != null) {
						if (admissionDate.getTime() < deliveryDateVisit.getTime()) {
							anteReferrals.add(outcomeImpl);
						} else {
							postReferrals.add(outcomeImpl);
						}
					} else {
						anteReferrals.add(outcomeImpl);
					}
				}
			}

            // Initialise the drugs list which will populate postnatal
            if (deliveryDateVisit != null) {
            	List<DrugsDispensed> postnatalDrugs = PostnatalHistoryReport.assemblePostnatalDrugs(conn, patientId, deliveryDateVisit);
    	        Collections.sort(postnatalDrugs, doc);
                post.setPostnatalDrugs(postnatalDrugs);
            }
            
            HashMap<Date,RegimenLabDrugsReport> regLabDrugRecordsDateMap = new HashMap<Date,RegimenLabDrugsReport>();
            List<RegimenLabDrugsReport> anteRegLabDrugRecords = new  ArrayList<RegimenLabDrugsReport>();
            List<RegimenLabDrugsReport> postRegLabDrugRecords = new  ArrayList<RegimenLabDrugsReport>();
            HashMap<Long,Long> regLabDrugRecordsIdMap = new HashMap<Long,Long>();

            // Populate anteRegLabDrugRecords or postRegLabDrugRecords with records from ArvRegimenReport (form 89)
            List<ArvRegimenReport> arvRegimens = ReportDAO.getAll(conn, patientId, new Long("89"), ArvRegimenReport.class);
            for (ArvRegimenReport arvRegimen : arvRegimens) {
            	Date arvRegimenDateVisit = arvRegimen.getDateVisit();
            	if (arvRegimenDateVisit != null) {
            		RegimenLabDrugsReport regimenLabDrugsReport = new RegimenLabDrugsReport();
            		try {
						BeanUtils.copyProperties(regimenLabDrugsReport, arvRegimen);
					} catch (ConversionException e) {
						// it's ok
					}
            		regLabDrugRecordsDateMap.put(arvRegimenDateVisit, regimenLabDrugsReport);
            		if ((deliveryDateVisit == null) || (arvRegimenDateVisit.getTime() < deliveryDateVisit.getTime())) {
	        			anteRegLabDrugRecords.add(regimenLabDrugsReport);
	        			regLabDrugRecordsIdMap.put(regimenLabDrugsReport.getId(), regimenLabDrugsReport.getId());
					} else {
	        			postRegLabDrugRecords.add(regimenLabDrugsReport);
	        			regLabDrugRecordsIdMap.put(regimenLabDrugsReport.getId(), regimenLabDrugsReport.getId());
					}
            	} else {
            		log.debug("arvRegimenDateVisit is null");
            	}
            }

            // populate with antenatal drugs
            for (DrugsDispensed drugRecord : ante.getDrugs()) {
            	Date drugDatevisit = drugRecord.getDateDispensed();
            	if (drugDatevisit != null) {
            		RegimenLabDrugsReport regimenLabDrugsReport = regLabDrugRecordsDateMap.get(drugDatevisit);
            		if (regimenLabDrugsReport != null) {
            			BeanUtils.copyProperties(regimenLabDrugsReport, drugRecord);
            		} else {
            			regimenLabDrugsReport = new RegimenLabDrugsReport();
            			regimenLabDrugsReport.setId(drugRecord.getId());
            			//log.debug("date: " + drugRecord.getDateDispensed());
            			regimenLabDrugsReport.setDateVisit(drugRecord.getDateDispensed());
            			regLabDrugRecordsDateMap.put(drugDatevisit, regimenLabDrugsReport);
            			try {
            				BeanUtils.copyProperties(regimenLabDrugsReport, drugRecord);
            			} catch (ConversionException e) {
            				//log.debug(e);
            			}
                		if (regLabDrugRecordsIdMap.get(regimenLabDrugsReport.getId()) == null) {
                			//log.debug("enc: " + regimenLabDrugsReport.getId());
                			regLabDrugRecordsIdMap.put(regimenLabDrugsReport.getId(), regimenLabDrugsReport.getId());
                			anteRegLabDrugRecords.add(regimenLabDrugsReport);
                		} else {
                			//log.debug("skip: " + regimenLabDrugsReport.getId());
                		}
            		}
            	} else {
            		log.debug("drugDatevisit is null");
            	}
            }

           for (OutcomeImpl referral : anteReferrals) {
            	Date referralDate = referral.getUthAdmissionDate();
            	if (referralDate != null) {
            		RegimenLabDrugsReport regimenLabDrugsReport = regLabDrugRecordsDateMap.get(referralDate);
            		if (regimenLabDrugsReport != null) {
            			regimenLabDrugsReport.setReferralReason(referral.getReason());
            			regimenLabDrugsReport.setInstitution("UTH");
            		} else {
            			regimenLabDrugsReport = new RegimenLabDrugsReport();
            			regimenLabDrugsReport.setDateVisit(referralDate);
            			regimenLabDrugsReport.setReferralReason(referral.getReason());
            			regimenLabDrugsReport.setInstitution("UTH");
            			regLabDrugRecordsDateMap.put(referralDate, regimenLabDrugsReport);
            			anteRegLabDrugRecords.add(regimenLabDrugsReport);
            		}
            	} else {
            		log.debug("referralDate is null");
            	}
            }

            // populate labs LabTests
            for (LabTestReport labTestReport : ante.getLabTests()) {
            	Date labTestDateVisit = labTestReport.getDateVisit();
            	if (labTestDateVisit != null) {
            		RegimenLabDrugsReport regimenLabDrugsReport = regLabDrugRecordsDateMap.get(labTestDateVisit);
            		if (regimenLabDrugsReport != null) {
            			regimenLabDrugsReport.getLabs().add(labTestReport);
            		} else {
            			regimenLabDrugsReport = new RegimenLabDrugsReport();
            			regimenLabDrugsReport.setDateVisit(labTestDateVisit);
            			log.debug("labTestDateVisit: " + labTestDateVisit);
            			regimenLabDrugsReport.getLabs().add(labTestReport);
            			regLabDrugRecordsDateMap.put(labTestDateVisit, regimenLabDrugsReport);
                		anteRegLabDrugRecords.add(regimenLabDrugsReport);
            		}
            	} else {
            		log.debug("labTestDateVisit is null");
            	}
            }


            // Now process postnatal
            // populate with postnatal drugs
            if (post.getPostnatalDrugs() != null) {
                for (DrugsDispensed drugRecord : post.getPostnatalDrugs()) {
                	Date drugDatevisit = drugRecord.getDateDispensed();
                	if (drugDatevisit != null) {
                    	RegimenLabDrugsReport regimenLabDrugsReport = regLabDrugRecordsDateMap.get(drugDatevisit);
                		if (regimenLabDrugsReport != null) {
                			BeanUtils.copyProperties(regimenLabDrugsReport, drugRecord);
                		} else {
                			regimenLabDrugsReport = new RegimenLabDrugsReport();
                			regimenLabDrugsReport.setDateVisit(drugRecord.getDateDispensed());
                			regLabDrugRecordsDateMap.put(drugDatevisit, regimenLabDrugsReport);
                			try {
                				BeanUtils.copyProperties(regimenLabDrugsReport, drugRecord);
                			} catch (ConversionException e) {
                				//log.debug(e);
                			}
                			postRegLabDrugRecords.add(regimenLabDrugsReport);
                		}
                	} else {
                		log.debug("drugDatevisit is null");
                	}
                }
            }

            for (OutcomeImpl referral : postReferrals) {
    			Date referralDate = referral.getUthAdmissionDate();
    			if (referralDate != null) {
        			RegimenLabDrugsReport regimenLabDrugsReport = regLabDrugRecordsDateMap.get(referralDate);
    				if (regimenLabDrugsReport != null) {
        				regimenLabDrugsReport.setReferralReason(referral.getReason());
        				regimenLabDrugsReport.setInstitution("UTH");
    				} else {
    					regimenLabDrugsReport = new RegimenLabDrugsReport();
    					regimenLabDrugsReport.setDateVisit(referralDate);
    					regimenLabDrugsReport.setReferralReason(referral.getReason());
    					regimenLabDrugsReport.setInstitution("UTH");
    					regLabDrugRecordsDateMap.put(referralDate, regimenLabDrugsReport);
            			postRegLabDrugRecords.add(regimenLabDrugsReport);
    				}
    			} else {
            		log.debug("referralDate is null");
            	}
    		}

            List<LabTestReport> postnatalLabTests = new ArrayList<LabTestReport>();
    		List<LabTestReport> labVisits = ReportDAO.getAll(conn, patientId, new Long("87"), LabTestReport.class);
    		for (int i = 0; i < labVisits.size(); i++) {
    			LabTestReport labTestReport = (LabTestReport) labVisits.get(i);
    			Date labDateVisit = labTestReport.getDateVisit();
    			boolean addToLabTests = false;
    			if (deliveryDateVisit != null) {
    				if (labDateVisit != null) {
    					if (labDateVisit.getTime() >= deliveryDateVisit.getTime()) {
    						addToLabTests = true;
    					}
    				}
    			}
    			if (addToLabTests) {
    				String test = labTestReport.getLabTypeR();
    				String result = labTestReport.getResultsR();
    				String dateResults = labTestReport.getDateLabResultsR();
    				if (dateResults != null) {
        				labTestReport.setDateLabResultsR(dateResults.replace("00:00:00.0", ""));
            		}
    				if (test != null && test.equals("Blood Type")) {
    					ante.getCurrentPregnancyStatus().setAboGroup(result);
    				} else if (test != null && test.equals("Rhesus Status")) {
    					ante.getCurrentPregnancyStatus().setRhesus(result);
    				} else if (test != null && test.equals("CD4 Count")) {
    					labTestReport.setResultsR(labTestReport.getCd4countR());
    				}

    				if (labTestReport.getExtended_test_idR() != null) {
    					Long extendedLabId = Long.valueOf(labTestReport.getExtended_test_idR());
    					String labType = labTestReport.getLabTypeR();
    					String extendedResults = LabTestDAO.getExtendedLabResults(conn, extendedLabId, labType);
    					if (extendedResults != null) {
    						labTestReport.setResultsR(extendedResults);
    					}
    				}
    				postnatalLabTests.add(labTestReport);
    			}
    		}
    		// populate labs LabTests
    		//List<LabTestReport> anteLabs = new ArrayList();
    		for (LabTestReport labTestReport : postnatalLabTests) {
    			Date labTestDateVisit = labTestReport.getDateVisit();
    			if (labTestDateVisit != null) {
        			RegimenLabDrugsReport regimenLabDrugsReport = regLabDrugRecordsDateMap.get(labTestDateVisit);
    				if (regimenLabDrugsReport != null) {
    					regimenLabDrugsReport.getLabs().add(labTestReport);
    				} else {
    					regimenLabDrugsReport = new RegimenLabDrugsReport();
    					regimenLabDrugsReport.setDateVisit(labTestDateVisit);
    					regimenLabDrugsReport.getLabs().add(labTestReport);
    					regLabDrugRecordsDateMap.put(labTestDateVisit, regimenLabDrugsReport);
    					postRegLabDrugRecords.add(regimenLabDrugsReport);
    				}
    			} else {
            		log.debug("labTestDateVisit is null");
            	}
    		}

	        Collections.sort(anteRegLabDrugRecords, doc);
            ante.setAncRegLabDrugRecords(anteRegLabDrugRecords);
            Collections.sort(postRegLabDrugRecords, doc);
            post.setPostRegLabDrugRecords(postRegLabDrugRecords);

            List<PmtctReport> newCouncelVisits = new  ArrayList<PmtctReport>();
            List<PmtctReport> councelVisits = ReportDAO.getAll(conn, patientId, new Long("91"), PmtctReport.class);
            List<ArvRegimenReport> regimenVisits = ReportDAO.getAll(conn, patientId, new Long("89"), ArvRegimenReport.class);
            for (PmtctReport councelVisit : councelVisits) {
            	Date dateVisit = councelVisit.getDateVisit();
            	for (ArvRegimenReport regimenVisit : regimenVisits) {
            		Date regDatevisit = regimenVisit.getDateVisit();
            		if ((regDatevisit != null) && (dateVisit.getTime() == regDatevisit.getTime())) {
            			BeanUtils.copyProperties(councelVisit, regimenVisit);
            		} else {
            			if (regDatevisit != null) {
            				PmtctReport newReport = new PmtctReport();
                			newReport.setDateVisit(regDatevisit);
                			try {
    							BeanUtils.copyProperties(newReport, regimenVisit);
    						} catch (ConversionException e) {
    							//log.debug(e);
    						}
    						newCouncelVisits.add(newReport);
            			}
            		}
            	}
            }
            councelVisits.addAll(newCouncelVisits);
            ante.setCouncelVisits(councelVisits);

            request.setAttribute("ante", ante);

            List<PostnatalMaternal> postnatalVisits = new  ArrayList<PostnatalMaternal>();
            //Date puerDateVisit = post.getPregnancyReport().getPuerperium().getDateVisit();
            PostnatalMaternal newPmv = new PostnatalMaternal();
            
            if (post.getPregnancyReport().getPuerperium() != null) {
            	try {
    				BeanUtils.copyProperties(newPmv, post.getPregnancyReport().getPuerperium());
    			} catch (Exception e) {
    				log.debug(e);
    			}
            }

			newPmv.setPostnatal_visit_601R("Puerperium");

			postnatalVisits.add(newPmv);

            List<PostnatalMaternal> newPmvl = new  ArrayList<PostnatalMaternal>();
            List<PostnatalMaternal> pmvl = ReportDAO.getAll(conn, patientId, new Long("28"), PostnatalMaternal.class);
            for (PostnatalMaternal pmv : pmvl) {
            	Date dateVisit = pmv.getDateVisit();
            	/*Date puerDateVisit = post.getPregnancyReport().getPuerperium().getDateVisit();
            	if (dateVisit.getTime() == puerDateVisit.getTime()) {
            		BeanUtils.copyProperties(councelVisit, regimenVisit);
            	}*/
			}
            postnatalVisits.addAll(pmvl);

            List<ProbPostnatalReport> probMaternalList = ReportDAO.getAll(conn, patientId, new Long("78"), ProbPostnatalReport.class);
            Form encounterForm = (Form) DynaSiteObjects.getForms().get((long) 78);
            PageItem diagnosisBegin = (PageItem) DynaSiteObjects.getPageItems().get(new Long("3124"));
            PageItem diagnosisEnd = (PageItem) DynaSiteObjects.getPageItems().get(new Long("4365"));
            int startField = diagnosisBegin.getDisplayOrder();  // Malaria
            int endField = diagnosisEnd.getDisplayOrder();      // Maternal Death, Postnatal
            Set pageItems = encounterForm.getPageItems();
            for (ProbPostnatalReport probPostnatalReport : probMaternalList) {
            	StringBuffer sbuf = new StringBuffer();
                StringBuffer sbufDis = new StringBuffer();
                Map encMap = probPostnatalReport.getEncounterMap();
            	newPmv = new PostnatalMaternal();
            	try {
    				BeanUtils.copyProperties(newPmv, probPostnatalReport);
    			} catch (Exception e) {
    				log.debug(e);
    			}
				newPmv.setPostnatal_visit_601R("Problem");

    			for (Iterator iterator = pageItems.iterator(); iterator.hasNext();) {
    				PageItem pageItem = (PageItem) iterator.next();
    				FormField formField = pageItem.getForm_field();
    				if (!formField.getType().equals("Display")) {
    				/*Long formFieldId = formField.getId();
                    String key = "field" + formFieldId;*/
    					String key = StringManipulation.firstCharToLowerCase(formField.getStarSchemaName()) + "R";
    					Integer displayOrder = pageItem.getDisplayOrder();
    					if ((displayOrder > startField) & (displayOrder < endField)) {
    						String value = (String) encMap.get(key);
    						// sbuf.append(", ").append(formField.getLabel()).append(": ").append(value);
    						if (value != null && value.equals("Yes")) {
    							if (sbuf.length() == 0) {
    								sbuf.append("Diagnoses: ").append(formField.getLabel());
    							} else {
    								sbuf.append(", ").append(formField.getLabel());
    							}
    						}
    					}
    				}
                }
                if (probPostnatalReport.getDiag_otherR() != null) {
                    sbuf.append(" Other diagnosis: " + probPostnatalReport.getDiag_otherR());
                }
                newPmv.setDiagnosis(sbuf.toString());
				postnatalVisits.add(newPmv);
			}

	        Collections.sort(postnatalVisits, doc);
            post.setPostnatalMaternalVisits(postnatalVisits);

            List<ChildVisitReport> childVisitReportList = new ArrayList<ChildVisitReport>();

            List<Patient> children = PatientDAO.getChildren(conn, patientId);
            for (Patient child : children) {
            	Long childId = child.getId();

                HashMap<Date,ChildVisitReport> childVisitReportMap = new HashMap<Date,ChildVisitReport>();

                InfantDischargeSummaryReport infantDischargeSummaryReport = null;
				try {
					infantDischargeSummaryReport = (InfantDischargeSummaryReport) EncountersDAO.getResolvedOne(conn, childId, (long) 84, InfantDischargeSummaryReport.class);
				} catch (Exception e1) {
					// no report.
				}
                ChildVisitReport childVisitReport = new ChildVisitReport();
                try {
    				BeanUtils.copyProperties(childVisitReport, infantDischargeSummaryReport);
    			} catch (Exception e) {
    				log.debug(e);
    			}
				childVisitReport.setPostnatal_visit_601R("Discharge");
				childVisitReportList.add(childVisitReport);
				childVisitReportMap.put(childVisitReport.getDateVisit(), childVisitReport);

    			List<PostnatalInfantReport> postnatalInfantVisits = ReportDAO.getAll(conn, childId, new Long("86"), PostnatalInfantReport.class);
    			for (PostnatalInfantReport postnatalInfantReport : postnatalInfantVisits) {
    				childVisitReport = new ChildVisitReport();
    				try {
    					BeanUtils.copyProperties(childVisitReport, postnatalInfantReport);
    				} catch (ConversionException e) {
    					// it's ok
    				}
					childVisitReportList.add(childVisitReport);
					childVisitReportMap.put(childVisitReport.getDateVisit(), childVisitReport);
    			}

    			List<LabTestReport> childLabTests = new ArrayList<LabTestReport>();
        		List<LabTestReport> childLabVisits = ReportDAO.getAll(conn, childId, new Long("87"), LabTestReport.class);
        		for (int i = 0; i < childLabVisits.size(); i++) {
        			LabTestReport labTestReport = (LabTestReport) childLabVisits.get(i);
        			Date labDateVisit = labTestReport.getDateVisit();
        			boolean addToLabTests = false;
    				if (labDateVisit != null) {
    					addToLabTests = true;
    				} else {
    					log.debug("labDateVisit is null.");
    				}
        			if (addToLabTests) {
        				String test = labTestReport.getLabTypeR();
        				String result = labTestReport.getResultsR();
        				String dateResults = labTestReport.getDateLabResultsR();
        				if (dateResults != null) {
            				labTestReport.setDateLabResultsR(dateResults.replace("00:00:00.0", ""));
                		}
        				if (test != null && test.equals("CD4 Count")) {
        					labTestReport.setResultsR(labTestReport.getCd4countR());
        				}

        				if (labTestReport.getExtended_test_idR() != null) {
        					Long extendedLabId = Long.valueOf(labTestReport.getExtended_test_idR());
        					String labType = labTestReport.getLabTypeR();
        					String extendedResults = LabTestDAO.getExtendedLabResults(conn, extendedLabId, labType);
        					if (extendedResults != null) {
        						labTestReport.setResultsR(extendedResults);
        					}
        				}
        				childLabTests.add(labTestReport);
        			}
        		}
        		// populate labs LabTests
        		//List<LabTestReport> anteLabs = new ArrayList();
        		for (LabTestReport labTestReport : childLabTests) {
        			Date labTestDateVisit = labTestReport.getDateVisit();
        			if (labTestDateVisit != null) {
        				childVisitReport = childVisitReportMap.get(labTestDateVisit);
        				if (childVisitReport != null) {
        					childVisitReport.getLabs().add(labTestReport);
        				} else {
        					childVisitReport = new ChildVisitReport();
        					childVisitReport.setDateVisit(labTestDateVisit);
        					childVisitReport.getLabs().add(labTestReport);
        					childVisitReportMap.put(labTestDateVisit, childVisitReport);
        					childVisitReportList.add(childVisitReport);
        				}
        			} else {
                		log.debug("labTestDateVisit is null");
                	}
        		}
			}

            if (childVisitReportList != null) {
                Collections.sort(childVisitReportList, doc);
            }

            request.setAttribute("childVisitReportList", childVisitReportList);
            request.setAttribute("post", post);
            request.setAttribute("children", children);
        } catch (ServletException e) {
            log.error(e);
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        return mapping.findForward("success");
    }
}
