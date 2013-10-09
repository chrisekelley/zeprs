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
 * Created on Apr 1, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.cidrz.project.zeprs.report;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.report.valueobject.PregnancyReport;
import org.cidrz.project.zeprs.report.valueobject.NewbornReport;
import org.cidrz.project.zeprs.valueobject.report.gen.*;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.dao.reports.ReportDAO;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;

import javax.servlet.ServletException;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 * @author ericl
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class ZEPRSSharedItems {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(ZEPRSSharedItems.class);

    /**
     * @deprecated Queries an obsolete class
     * @param beginDate
     * @param endDate
     * @param siteID
     * @param conn
     * @return
     */
    static protected int getTotalAdmissions(Date beginDate, Date endDate, int siteID, Connection conn) {

        int count = 0;

        try {
            // First, get the form id for the Initial Physical Exam form
            // int formID = ZEPRSUtils.getFormID("Admission at Labour");

            // use delivery summary instead
            int formID = 11;

            // Return the number of times this form was submitted during the time frame in question
            count = ZEPRSUtils.getEncountersCount(formID, "admissionlabour", beginDate, endDate, siteID, conn);
        } catch (Exception e) {
            log.error(e);
        }

        return count;
    }

    static protected int getTotalVisits(Date beginDate, Date endDate, int siteID, Connection conn) {

        int count = 0;

        try {
            count = ZEPRSUtils.getEncountersCount(beginDate, endDate, siteID, conn);

        } catch (Exception e) {
            log.error(e);
        }

        return count;
    }

    static protected int getTotalFirstAnteAttendances(Date beginDate, Date endDate, int siteID, Connection conn) {

        int count = 0;

        try {
            // First, get the form id for the Initial Physical Exam form
           //  int formID = ZEPRSUtils.getFormID("Initial Physical Exam");
            int formID = 77;

            // Return the number of times this form was submitted during the time frame in question
            count = ZEPRSUtils.getEncountersCount(formID, "initialvisit", beginDate, endDate, siteID, conn);
        } catch (Exception e) {
            log.error(e);
        }
        return count;
    }

    static protected int getTotalAnteReattendances(Date beginDate, Date endDate, int siteID, Connection conn) {

        int count = 0;

        try {
            // First, get the form id for the Antenatal Record Exam and Subsequent Visits form
            // int formID = ZEPRSUtils.getFormID("Antenatal Record Exam and Subsequent Visits");
            int formID = 80;

            // Next, determine how many times the Anenatal Record Exam and Subsequent Visits
            // form was submitted
            //count = ZEPRSUtils.getEncountersCount(formID, "routineante", beginDate, endDate, siteID, conn);

            // Get the id's of patients who have had routine ante visits during the time period

             ResultSet rs = null;

            try {
                String sql = "SELECT patient_id FROM encounter\n" +
                        "WHERE form_id = ? AND date_visit >= ? AND date_visit <= ? AND site_id = ? GROUP BY patient_id";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, Integer.toString(formID));
                ps.setDate(2, beginDate);
                ps.setDate(3, endDate);
                ps.setInt(4, siteID);

                rs = ps.executeQuery();

                while (rs.next()) {
                    int patientId = rs.getInt(1);
                    // how many routine ante visits have they had? if >1, then it's a re-attendance
                    sql = "SELECT count(*) FROM encounter\n" +
                            "WHERE form_id = ? AND site_id = ? AND patient_id=?";
                    ps = conn.prepareStatement(sql);
                    ps.setInt(1, formID);
                    ps.setInt(2, siteID);
                    ps.setInt(3, patientId);
                    ResultSet rs2 = ps.executeQuery();
                    while (rs2.next()) {
                        int visits = rs2.getInt(1);
                        if (visits > 1) {
                            count = count+1;
                        }
                    }
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


            return count;


        } catch (Exception e) {
            log.error(e);
        }

        return count;
    }

    protected static int getTotalDischarges(Date beginDate, Date endDate, int siteID, Connection conn) {

        int count = 0;

        try {
            // First, get the form id for the Maternal Discharge Summary form
            // int formID = ZEPRSUtils.getFormID("Maternal Discharge Summary");
            int formID = 68;

            // Next, determine how many times the Maternal Discharge Summary form
            // was submitted for the appropriate time period
            count = ZEPRSUtils.getEncountersCount(formID, "maternaldischarge", beginDate, endDate, siteID, conn);
        } catch (Exception e) {
            log.error(e);
        }

        return count;
    }

    protected static int getTotalReferrals(Date beginDate, Date endDate, int siteID, Connection conn) {

        int count = 0;

        try {
            // First, get the form id for the Delivery Summary form
            // int formID = ZEPRSUtils.getFormID("Delivery Summary");
            int formID = 81;
            // Next, determine how many times the Delivery Summary form
            // was submitted for the appropriate time period
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "puerperium", beginDate, endDate, siteID, conn, false);
            // loop through all records to count the number of maternal deaths
            while (rs.next()) {
                if (rs.getInt("disposition") == Constants.REFER_TO_UTH_PUER) {
                    count++;
                }
            }
        } catch (ServletException e) {
            log.error(e);
        } catch (SQLException e) {
            log.error(e);
        }


        try {
            int formID = 68;
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "maternaldischarge", beginDate, endDate, siteID, conn, false);
            while (rs.next()) {
                if (rs.getInt("maternal_summary_discharge") == Constants.REFER_TO_UTH_DISCH) {
                    count++;
                }
            }
        } catch (ServletException e) {
            log.error(e);
        } catch (SQLException e) {
            log.error(e);
        }

        return count;
    }

    /*public static int getTotalBBABirths(Date beginDate, Date endDate, int siteID, Connection conn) {

        int count = 0;

        try {
            // First, get the form id for the Newborn Evaluation form
            // int formID = ZEPRSUtils.getFormID("Newborn Evaluation");
            int formID = 23;

            // Next, determine how many times the Newborn Evaluation form
            // was submitted for the appropriate time period
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "newborneval", beginDate, endDate, siteID, conn, true);

            // loop through all records to count the number live births
            while (rs.next()) {
                if (rs.getBoolean("born_at_home")) {
                    count++;
                }
            }
        } catch (Exception e) {
            // TBD
        }

        return count;
    }*/

    static protected int getTotalFirstPostAttendances(Date beginDate, Date endDate, int siteID, Connection conn) {

        int count = 0;

        try {
            // Connection conn = ZEPRSUtils.getZEPRSConnection();

            // First, get the form id for the Postnatal Maternal Visit form
            // int formID = ZEPRSUtils.getFormID("Postnatal Maternal Visit");
            int formID = 28;

            // Next, retrieve all encounters associated with this form
            // for the time period in question
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "postnatalmaternalvisit", beginDate, endDate, siteID, conn, true);

            // loop through all records and eliminate any that are not
            // the first postnatal attendance for this patient
            int prevPatientID = 0;
            int firstPostnatalCount = 0;
            while (rs.next()) {

                // for any new patiend id, check to see if this patient
                // was associated with a previous Postnatal Maternal Visit
                //
                // limit check to those associated with THIS pregnancy
                int patientID = rs.getInt("patient_id");
                int pregnancyId =  rs.getInt("pregnancy_id");
                if (patientID != prevPatientID) {

                    String sql = "SELECT count(*) FROM encounter " +
                            "WHERE form_id = ? AND pregnancy_id = ? AND date_visit < ?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setInt(1, formID);
                    ps.setInt(2, pregnancyId);
                    ps.setDate(3, beginDate);
                    ResultSet rs2 = ps.executeQuery();

                    // any result that comes back implies that this patient
                    // has had a postnatal visit before this time period
                    if (!rs2.next()) {
                        firstPostnatalCount++;
                    }
                }
            }
            rs.close();
            return firstPostnatalCount;
        } catch (Exception e) {
            // TBD
        }

        return count;
    }

    static protected int getTotalPostAttendances(Date beginDate, Date endDate, int siteID, Connection conn) {

        int count = 0;
        try {
            // First, get the form id for the Postnatal Maternal Visit form
            int formID = 28;
            // Next, retrieve all encounters associated with this form
            // for the time period in question
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "postnatalmaternalvisit", beginDate, endDate, siteID, conn, true);
            while (rs.next()) {
                count++;
            }
            rs.close();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    static protected int getTotalMaternalVisitUth(Date beginDate, Date endDate, Connection conn) {

        int count = 0;
        try {
            // First, get the form id for the Postnatal Maternal Visit form
            int formID = 28;
            // Next, retrieve all encounters associated with this form
            // for the time period in question
            ResultSet rs = ZEPRSUtils.getEncountersUth("postnatalmaternalvisit", beginDate, endDate, conn, false);
            while (rs.next()) {
                count++;
            }
            rs.close();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    static protected int getTotalPostnatalVisitUth(Date beginDate, Date endDate, Connection conn) {

        int count = 0;
        try {
            // First, get the form id for the Postnatal Maternal Visit form
            int formID = 28;
            // Next, retrieve all encounters associated with this form
            // for the time period in question
            ResultSet rs = ZEPRSUtils.getEncountersUth("postnatalhosp", beginDate, endDate, conn, false);
            while (rs.next()) {
                count++;
            }
            rs.close();
            return count;
        } catch (Exception e) {
            log.error(e);
        }
        return count;
    }

    public static int getTotalLiveBirths(Date beginDate, Date endDate, int siteID, Connection conn) {

        int count = 0;

        try {
            // First, get the form id for the Newborn Evaluation form
            // int formID = ZEPRSUtils.getFormID("Newborn Evaluation");
            int formID = 23;

            // Next, determine how many times the Newborn Evaluation form
            // was submitted for the appropriate time period
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "newborneval", beginDate, endDate, siteID, conn, true);

            // loop through all records to count the number live births
            while (rs.next()) {
                if ((rs.getInt("alive_sb_493") != Constants.FRESH_STILL_BIRTH) && (rs.getInt("alive_sb_493") != Constants.MACERATED_STILL_BIRTH)) {
                    count++;
                }
            }
        } catch (Exception e) {
            // TBD
        }

        return count;
    }

    protected static int getTotalFreshStillBirths(Date beginDate, Date endDate, int siteID, Connection conn) {

        int count = 0;

        try {
            // First, get the form id for the Newborn Evaluation form
            // int formID = ZEPRSUtils.getFormID("Newborn Evaluation");
            int formID = 23;

            // Next, determine how many times the Newborn Evaluation form
            // was submitted for the appropriate time period
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "newborneval", beginDate, endDate, siteID, conn, true);

            // loop through all records to count the number fresh still births
            while (rs.next()) {
                if (rs.getInt("alive_sb_493") == Constants.FRESH_STILL_BIRTH) {
                    count++;
                }
            }
        } catch (Exception e) {
            log.error(e);
        }

        return count;
    }

    protected static int getTotalMaceratedStillBirths(Date beginDate, Date endDate, int siteID, Connection conn) {

        int count = 0;

        try {
            // First, get the form id for the Newborn Evaluation form
            // int formID = ZEPRSUtils.getFormID("Newborn Evaluation");
            int formID = 23;

            // Next, determine how many times the Newborn Evaluation form
            // was submitted for the appropriate time period
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "newborneval", beginDate, endDate, siteID, conn, true);

            // loop through all records to count the number macerated still births
            while (rs.next()) {
                if (rs.getInt("alive_sb_493") == Constants.MACERATED_STILL_BIRTH) {
                    count++;
                }
            }
        } catch (Exception e) {
            log.error(e);
        }

        return count;
    }

    protected static int getTotalPrematureBirths(Date beginDate, Date endDate, int siteID, Connection conn) {

        int count = 0;

        try {
            // First, get the form id for the Newborn Evaluation form
            // int formID = ZEPRSUtils.getFormID("Newborn Evaluation");
            int formID = 23;

            // Next, determine how many times the Newborn Evaluation form
            // was submitted for the appropriate time period
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "newborneval", beginDate, endDate, siteID, conn, true);

            // loop through all records to count the number premature births
            while (rs.next()) {
                if (rs.getInt("if_premature_num_weeks_gest_488") != 0) {
                    count++;
                }
            }
        } catch (Exception e) {
            log.error(e);
        }

        return count;
    }


    /*public static int getTotalBreechBirths(Date beginDate, Date endDate, int siteID, Connection conn) {

        int count = 0;

        try {
            // First, get the form id for the Newborn Evaluation form
           //  int formID = ZEPRSUtils.getFormID("Delivery Summary");
            int formID = 66;
            // Next, determine how many times the Newborn Evaluation form
            // was submitted for the appropriate time period
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "deliverysum", beginDate, endDate, siteID, conn, true);

            // loop through all records to count the number assisted breech delivery
            while (rs.next()) {
                if (rs.getInt("mode_of_delivery_447") == Constants.ASSISTED_BREECH_DELIVERY) {
                    count++;
                }
            }
        } catch (Exception e) {
            log.error(e);
        }

        return count;
    }*/

    /**
     * @deprecated Does not query all of the necessary forms. Use dead field from patient table instead.
     * @param beginDate
     * @param endDate
     * @param siteID
     * @param conn
     * @return
     */
    protected static int getTotalNeonatalDeathsDep(Date beginDate, Date endDate, int siteID, Connection conn) {

        int count = 0;

        try {
            // First, get the form id for the Newborn Evaluation form
            // int formID = ZEPRSUtils.getFormID("Newborn Evaluation");
            int formID = 23;

            // Next, determine how many times the Newborn Evaluation form
            // was submitted for the appropriate time period
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "newborneval", beginDate, endDate, siteID, conn, true);

            // loop through all records to count the number neonatal deaths
            while (rs.next()) {
                if (rs.getBoolean("neonatal_dea_1180")) {
                    count++;
                }
            }
        } catch (Exception e) {
            log.error(e);
        }

        return count;
    }

    /**
     * @deprecated Does not query enough records - use dead field from patient table
     * @param beginDate
     * @param endDate
     * @param siteID
     * @param conn
     * @return
     */
    protected static int getTotalMaternalDeathsDep(Date beginDate, Date endDate, int siteID, Connection conn) {

        int count = 0;

        try {
            // First, get the form id for the Maternal Discharge Summary form
            // int formID = ZEPRSUtils.getFormID("Maternal Discharge Summary");
            int formID = 68;
            // Next, determine how many times the Maternal Discharge Summary form
            // was submitted for the appropriate time period
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "maternal_disch_sum", beginDate, endDate, siteID, conn, true);

            // loop through all records to count the number of maternal deaths
            while (rs.next()) {
                if (rs.getInt("outcome_of_mother") == Constants.MATERNAL_DEATH) {
                    count++;
                }
            }
        } catch (Exception e) {
            // TBD
        }

        return count;
    }

    protected static int getTotalNormalDeliveries(Date beginDate, Date endDate, int siteID, Connection conn) {

        int count = 0;

        try {
            // First, get the form id for the Delivery Summary form
            // int formID = ZEPRSUtils.getFormID("Delivery Summary");
            int formID = 66;

            // Next, determine how many times the Maternal Discharge Summary form
            // was submitted for the appropriate time period
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "deliverysum", beginDate, endDate, siteID, conn, true);

            // loop through all records to count the number of maternal deaths
            while (rs.next()) {
                if (rs.getInt("mode_of_delivery_447") == Constants.SPONTANEOUS_VAGINAL) {
                    count++;
                }
            }
        } catch (Exception e) {
            // TBD
        }

        return count;
    }

    protected static int getTotalDeliveries(Date beginDate, Date endDate, int siteID, Connection conn) {

        int count = 0;
        try {
            // First, get the form id for the Delivery Summary form
            int formID = 66;

            // Next, determine how many times the Delivery Summary form
            // was submitted for the appropriate time period
            count = ZEPRSUtils.getEncountersCount(formID, "deliverysum", beginDate, endDate, siteID, conn);
        } catch (Exception e) {
            log.error(e);
        }

        return count;
    }

    protected static int getTotalComplicatedDeliveries(Date beginDate, Date endDate, int siteID, Connection conn) {

        int count = 0;

        try {
            // First, get the form id for the Delivery Summary form
            // int formID = ZEPRSUtils.getFormID("Delivery Summary");
            int formID = 66;
            // Next, determine how many times the Delivery Summary form
            // was submitted for the appropriate time period
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "deliverysum", beginDate, endDate, siteID, conn, true);

            // loop through all records to count the number of maternal deaths
            while (rs.next()) {
                if (rs.getInt("mode_of_delivery_447") != Constants.SPONTANEOUS_VAGINAL) {
                    count++;
                }
            }
        } catch (Exception e) {
            // TBD
        }

        return count;
    }

    protected static int getTotalLowBirthWeights(Date beginDate, Date endDate, int siteID, Connection conn) {

        int count = 0;

        try {
            // First, get the form id for the Newborn Evaluationform
            // int formID = ZEPRSUtils.getFormID("Newborn Evaluation");
            int formID = 23;

            // Next, determine how many times the Maternal Discharge Summary form
            // was submitted for the appropriate time period
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "newborneval", beginDate, endDate, siteID, conn, true);

            // loop through all records to count the number of maternal deaths
            while (rs.next()) {
                if (rs.getInt("weight_at_birth_491") < Constants.MAX_LOW_BIRTH_WEIGHT) {
                    count++;
                }
            }
        } catch (Exception e) {
            // TBD
        }

        return count;
    }

    protected static int getTotalRPRTests(Date beginDate, Date endDate, int siteID, Connection conn) {

        int count = 0;

        try {
            // First, get the form id for the Laboratory Results form
            // int formID = ZEPRSUtils.getFormID("Laboratory Results");

            // Get this data from Safe Motherohod, not lab
            int formID = 4;

            // Next, determine how many times the Laboratory Results form
            // was submitted for the appropriate time period
            // TODO: Distinguish between Test 1 and Test 2
            // ResultSet rs = ZEPRSUtils.getEncounters(formID, "labresults", beginDate, endDate, siteID, conn);
            // ResultSet rs = ZEPRSUtils.getEncounters(formID, "safemotherhood", beginDate, endDate, siteID, conn);
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "safemotherhood", beginDate, endDate, siteID, "rpr1_date", "rpr2_date", conn);
            while (rs.next()) {
                // int rprResult = rs.getInt("rpr_result_199");
                int rprResult1 = rs.getInt("rpr1_result");
                 int rprResult2 = rs.getInt("rpr2_result");
                if (rprResult1 != 0 && rprResult1 != Constants.RPR_NOT_DONE) {
                    count++;
                } else if (rprResult2 != 0 && rprResult2 != Constants.RPR_NOT_DONE) {
                    count++;
                }
            }

        } catch (Exception e) {
            // TBD
        }

        return count;
    }

    protected static int getTotalRPRPositive(Date beginDate, Date endDate, int siteID, Connection conn) {

        int count = 0;

        try {
            // First, get the form id for the Laboratory Results form
           //  int formID = ZEPRSUtils.getFormID("Laboratory Results");

            // Get this data from Safe Motherohod, not lab
            int formID = 4;

            // Next, determine how many times the Laboratory Results form
            // was submitted for the appropriate time period
            // TODO: Distinguish between Test 1 and Test 2
            // ResultSet rs = ZEPRSUtils.getEncounters(formID, "safemotherhood", beginDate, endDate, siteID, conn);
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "safemotherhood", beginDate, endDate, siteID, "rpr1_date", "rpr2_date", conn);
            while (rs.next()) {
                // int rprResult = rs.getInt("rpr_result_199");
                int rprResult1 = rs.getInt("rpr1_result");
                int rprResult2 = rs.getInt("rpr2_result");
                if (rprResult1 != 0 && rprResult1 != Constants.RPR_NOT_DONE) {
                    if (rprResult1 == Constants.RPR_REACTIVE_POSITIVE) {
                        count++;
                    }
                } else if (rprResult2 != 0 && rprResult2 != Constants.RPR_NOT_DONE) {
                    if (rprResult2 == Constants.RPR_REACTIVE_POSITIVE) {
                        count++;
                    }
                }
            }
        } catch (Exception e) {
            // TBD
        }

        return count;
    }

    protected static int getTotalRPRPositiveTreated(Date beginDate, Date endDate, int siteID, Connection conn) {

        int count = 0;

        try {
            // First, get the form id for the Laboratory Results form
            // int formID = ZEPRSUtils.getFormID("Laboratory Results");

            // Get this data from Safe Motherohod, not lab
            int formID = 4;

            // Next, determine how many times the Laboratory Results form
            // was submitted for the appropriate time period
            // TODO: Distinguish between Test 1 and Test 2
            // ResultSet rs = ZEPRSUtils.getEncounters(formID, "labresults", beginDate, endDate, siteID, conn);
            // ResultSet rs = ZEPRSUtils.getEncounters(formID, "safemotherhood", beginDate, endDate, siteID, conn);
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "safemotherhood", beginDate, endDate, siteID, "rpr1_treatment_date", "rpr2_treatment_date", conn);

            while (rs.next()) {
                // int rprTreatment = rs.getInt("if_reative_treatment_202");
                Date rprTreatmentDate1 = rs.getDate("rpr1_treatment_date");
                Date rprTreatmentDate2 = rs.getDate("rpr2_treatment_date");
                if (rprTreatmentDate1 != null) {
                    count++;
                }
                if (rprTreatmentDate2 != null) {
                    count++;
                }
            }
        } catch (Exception e) {
            // TBD
        }

        return count;
    }

    protected static int getTotalHBTests(Date beginDate, Date endDate, int siteID, Connection conn) {

        int hb = 0;

        try {
            // First, get the form id for the Laboratory Results form
            // int formID = ZEPRSUtils.getFormID("Laboratory Results");

            int formID = 87;

            // Next, determine how many times the Laboratory Results form
            // was submitted for the appropriate time period

            ResultSet rs = ZEPRSUtils.getEncounters(formID, "labtest ", beginDate, endDate, siteID, conn, true);

            while (rs.next()) {
                int hbNumeric = rs.getInt("resultsNumeric");
                if (hbNumeric != 0) {
                    hb++;
                }
            }

            // rs = ZEPRSUtils.getEncounters(formID, "safemotherhood", beginDate, endDate, siteID, "hb2_date", conn);

            rs.close();
        } catch (Exception e) {
            // TBD
        }

        return hb;
    }

    protected static int getTotalHBBelow10g(Date beginDate, Date endDate, int siteID, Connection conn) {

        int count = 0;

        try {
            // First, get the form id for the Laboratory Results form
            // int formID = ZEPRSUtils.getFormID("Laboratory Results");
           // Get this data from Safe Motherohod, not lab
            int formID = 4;

            // Next, determine how many times the Laboratory Results form
            // was submitted for the appropriate time period
            // ResultSet rs = ZEPRSUtils.getEncounters(formID, "safemotherhood", beginDate, endDate, siteID, "hb1_date", conn);
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "safemotherhood", beginDate, endDate, siteID, "hb1_date", "hb2_date", conn);

            while (rs.next()) {
                int hb1 = rs.getInt("hb1_result");
                int hb2 = rs.getInt("hb2_result");
                if (hb1 != 0) {
                    if (hb1 < 10) {
                        count++;
                    }
                } else if (hb2 != 0) {
                    if (hb2 < 10) {
                        count++;
                    }
                }
            }

            rs.close();
        } catch (Exception e) {
            log.error(e);
        }

        return count;
    }



    protected static ResultSet getNeonatalDeaths(Date beginDate, Date endDate, int siteID, Connection conn) {
        ResultSet rs = null;

        try {
            // Retrieve all Encounter records for this form for mothers

            if (siteID == 0) {
                String sql = "SELECT DISTINCT encounter.patient_id, patient.parent_id, encounter.pregnancy_id, labour_admission_encounter_id " +
                        "FROM encounter, patient, pregnancy " +
                        "WHERE encounter.patient_id = patient.id " +
                        "AND encounter.pregnancy_id = pregnancy.id " +
                        "AND date_visit >= ? " +
                        "AND date_visit <= ? " +
                        "AND patient.parent_id IS NOT NULL " +
                        "AND dead=1";

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDate(1, beginDate);
                ps.setDate(2, endDate);
                rs = ps.executeQuery();
            } else {
                String sql = "SELECT DISTINCT encounter.patient_id, patient.parent_id, encounter.pregnancy_id, labour_admission_encounter_id " +
                        "FROM encounter, patient, pregnancy " +
                        "WHERE encounter.patient_id = patient.id " +
                        "AND encounter.pregnancy_id = pregnancy.id " +
                        "AND date_visit >= ? " +
                        "AND date_visit <= ? " +
                        "AND patient.parent_id IS NOT NULL " +
                        "AND encounter.site_id = ? " +
                        "AND dead=1";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDate(1, beginDate);
                ps.setDate(2, endDate);
                ps.setInt(3, siteID);
                rs = ps.executeQuery();
            }
        } catch (Exception ex) {
            log.error(ex);
        }

        return rs;
    }

    protected static int getTotalNeonatalDeaths(Date beginDate, Date endDate, int siteID, Connection conn) {
        ResultSet rs = null;
        int count = 0;
        try {
            // Retrieve all Encounter records for this form for mothers

            if (siteID == 0) {
                String sql = "SELECT COUNT(DISTINCT(encounter.patient_id), patient.parent_id, encounter.pregnancy_id, labour_admission_encounter_id) " +
                        "FROM encounter, patient, pregnancy " +
                        "WHERE encounter.patient_id = patient.id " +
                        "AND encounter.pregnancy_id = pregnancy.id " +
                        "AND date_visit >= ? " +
                        "AND date_visit <= ? " +
                        "AND patient.parent_id IS NOT NULL " +
                        "AND dead=1";

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDate(1, beginDate);
                ps.setDate(2, endDate);
                rs = ps.executeQuery();
            } else {
                String sql = "SELECT COUNT(DISTINCT(encounter.patient_id), patient.parent_id, encounter.pregnancy_id, labour_admission_encounter_id) " +
                        "FROM encounter, patient, pregnancy " +
                        "WHERE encounter.patient_id = patient.id " +
                        "AND encounter.pregnancy_id = pregnancy.id " +
                        "AND date_visit >= ? " +
                        "AND date_visit <= ? " +
                        "AND patient.parent_id IS NOT NULL " +
                        "AND encounter.site_id = ? " +
                        "AND dead=1";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDate(1, beginDate);
                ps.setDate(2, endDate);
                ps.setInt(3, siteID);
                rs = ps.executeQuery();
            }
            while (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception ex) {
            log.error(ex);
        }

        return count;
    }

    protected static int getTotalNeonatalDeathsUTH(Date beginDate, Date endDate, Connection conn) {
        ResultSet rs = null;
        int count = 0;
        try {
                String sql = "SELECT COUNT(DISTINCT(encounter.patient_id), patient.parent_id, encounter.pregnancy_id, labour_admission_encounter_id) " +
                        "FROM encounter, patient, pregnancy, site " +
                        "WHERE encounter.patient_id = patient.id " +
                        "AND encounter.pregnancy_id = pregnancy.id " +
                        "AND site.id = encounter.site_id " +
                        "AND date_visit >= ? " +
                        "AND date_visit <= ? " +
                        "AND patient.parent_id IS NOT NULL " +
                        "AND dead=1";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDate(1, beginDate);
                ps.setDate(2, endDate);
                rs = ps.executeQuery();

            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception ex) {
            log.error(ex);
        }

        return count;
    }

    protected static int getTotalMaternalDeaths(Date beginDate, Date endDate, int siteID, Connection conn) {
        ResultSet rs = null;
        int count = 0;
        try {
            // Retrieve all Encounter records for this form for mothers

            if (siteID == 0) {
                String sql = "SELECT COUNT(DISTINCT(encounter.patient_id), encounter.pregnancy_id, labour_admission_encounter_id) " +
                        "FROM encounter, patient, pregnancy " +
                        "WHERE encounter.patient_id = patient.id " +
                        "AND encounter.pregnancy_id = pregnancy.id " +
                        "AND date_death >= ? " +
                        "AND date_death <= ? " +
                        "AND patient.parent_id IS NULL " +
                        "AND dead=1";

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDate(1, beginDate);
                ps.setDate(2, endDate);
                rs = ps.executeQuery();
            } else {
                String sql = "SELECT COUNT(DISTINCT(encounter.patient_id), encounter.pregnancy_id, labour_admission_encounter_id) " +
                        "FROM encounter, patient, pregnancy " +
                        "WHERE encounter.patient_id = patient.id " +
                        "AND encounter.pregnancy_id = pregnancy.id " +
                        "AND date_death >= ? " +
                        "AND date_death <= ? " +
                        "AND patient.parent_id IS NULL " +
                        "AND encounter.site_id = ? " +
                        "AND dead=1";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDate(1, beginDate);
                ps.setDate(2, endDate);
                ps.setInt(3, siteID);
                rs = ps.executeQuery();
            }
            while (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception ex) {
            log.error(ex);
        }

        return count;
    }

    protected static int getTotalMaternalDeathsUTH(Date beginDate, Date endDate, Connection conn) {
        ResultSet rs = null;
        int count = 0;
        try {

                String sql = "SELECT COUNT(DISTINCT(encounter.patient_id), encounter.pregnancy_id, labour_admission_encounter_id) " +
                        "FROM encounter, patient, pregnancy, site " +
                        "WHERE encounter.patient_id = patient.id " +
                        "AND encounter.pregnancy_id = pregnancy.id " +
                        "AND site.id = encounter.site_id " +
                        "AND date_death >= ? " +
                        "AND date_death <= ? " +
                        "AND patient.parent_id IS NULL " +
                        "AND site_type_id = 2 " +
                        "AND dead=1";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDate(1, beginDate);
                ps.setDate(2, endDate);
                rs = ps.executeQuery();

            while (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception ex) {
            log.error(ex);
        }

        return count;
    }

    protected static int getTotalDeaths(Date beginDate, Date endDate, int siteID, Connection conn) {
        ResultSet rs = null;
        int count = 0;
        try {
            // Retrieve all Encounter records for this form for mothers

            if (siteID == 0) {
                String sql = "SELECT COUNT(DISTINCT(encounter.patient_id), encounter.pregnancy_id, labour_admission_encounter_id) " +
                        "FROM encounter, patient, pregnancy " +
                        "WHERE encounter.patient_id = patient.id " +
                        "AND encounter.pregnancy_id = pregnancy.id " +
                        "AND date_death >= ? " +
                        "AND date_death <= ? " +
                        "AND dead=1";

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDate(1, beginDate);
                ps.setDate(2, endDate);
                rs = ps.executeQuery();
            } else {
                String sql = "SELECT COUNT(DISTINCT(encounter.patient_id), encounter.pregnancy_id, labour_admission_encounter_id) " +
                        "FROM encounter, patient, pregnancy " +
                        "WHERE encounter.patient_id = patient.id " +
                        "AND encounter.pregnancy_id = pregnancy.id " +
                        "AND date_death >= ? " +
                        "AND date_death <= ? " +
                        "AND encounter.site_id = ? " +
                        "AND dead=1";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDate(1, beginDate);
                ps.setDate(2, endDate);
                ps.setInt(3, siteID);
                rs = ps.executeQuery();
            }
            while (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception ex) {
            log.error(ex);
        }

        return count;
    }

    /**
     * Get deliveries based on dateVisit in deliverysum
     * @param beginDate
     * @param endDate
     * @param siteID
     * @param orderBy
     * @param conn
     * @return
     */
    protected static ResultSet getDeliveries(Date beginDate, Date endDate, int siteID, String orderBy, Connection conn) {

        ResultSet deliveries = null;
        /*try {
            // First, get the form id for the Delivery Summary form
            int formID = 66;
            deliveries = ZEPRSUtils.getEncountersGroupBy(formID, "deliverysum", beginDate, endDate, siteID, groupBy, conn);
        } catch (Exception e) {
            log.error(e);
        }*/

            // Retrieve all Encounter records for this form
        try {
            // First, get the form id for the Delivery Summary form
            int formID = 66;
            String sql = "SELECT * FROM encounter, deliverysum\n" +
                    "WHERE encounter.id = deliverysum.id\n" +
                    "AND form_id = ? AND date_visit >= ? AND date_visit <= ? AND site_id = ?\n" +
                    "ORDER BY " + orderBy;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Integer.toString(formID));
            ps.setDate(2, beginDate);
            ps.setDate(3, endDate);
            ps.setInt(4, siteID);
            deliveries = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deliveries;
    }

    /**
     * Get newborns based on date_of_birth in newborneval
     * @param beginDate
     * @param endDate
     * @param siteID
     * @param orderBy
     * @param conn
     * @return
     */
    protected static ResultSet getNewborns(Date beginDate, Date endDate, int siteID, Connection conn) {

    	ResultSet newborns = null;

    	// Retrieve all Encounter records for this form
    	try {
    		String sql = "SELECT encounter.patient_id, encounter.pregnancy_id,\n" +
    		"patient.parent_id, patient.dead,\n" +
    		"newborneval.date_of_birth, newborneval.alive_sb_493\n" +
    		"FROM encounter, newborneval, patient\n" +
    		"WHERE encounter.id = newborneval.id\n" +
    		"AND encounter.patient_id = patient.id\n" +
    		"AND date_of_birth >= ? AND date_of_birth <= ? AND encounter.site_id = ?\n" +
    		"ORDER BY date_of_birth, encounter.id";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setDate(1, beginDate);
    		ps.setDate(2, endDate);
    		ps.setInt(3, siteID);
    		newborns = ps.executeQuery();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return newborns;
    }

    /**
     * Get fields used by DeliverySummarySheet from deliverysum table
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @return
     */
    protected static ResultSet getDelivery(Connection conn, int patientId, int pregnancyId) {

        ResultSet deliveries = null;

            // Retrieve all Encounter records for this form
        try {
            String sql = "SELECT patient_id, given_nvp_tablets_1223, regimen, date_visit, " +
            		"uterotonic_med_given, hiv_tested_in_labour\n" +
            		"FROM encounter, deliverysum\n" +
                    "WHERE encounter.id = deliverysum.id\n" +
                    "AND patient_id = ? AND pregnancy_id >= ?\n";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, patientId);
            ps.setInt(2, pregnancyId);
            deliveries = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deliveries;
    }

    /**
     * return the patient table - useful when you need to check dead or hiv
     * @param patientID
     * @param conn
     * @return patient table
     */
    protected static ResultSet getPatient(int patientID, Connection conn) {
        ResultSet rs = null;
        int count = 0;
        try {
            // Retrieve all Encounter records for this form for mothers

                String sql = "SELECT * \n" +
                        "FROM patient " +
                        "WHERE id = ? ";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, patientID);
                rs = ps.executeQuery();


        } catch (Exception ex) {
            log.error(ex);
        }

        return rs;
    }

    public static void getInfantSummary(Connection conn, Long patientId, Long pregnancyId, PregnancyReport pregnancyReport) {
        List newborns = null;
        List children = PatientDAO.getChildren(conn, patientId, pregnancyId);
        if (children.size() > 0) {
            newborns = new ArrayList();
            pregnancyReport.setNewborns(newborns);
        }
        for (int j = 0; j < children.size(); j++) {
            Patient child = (Patient) children.get(j);
            Long childId = child.getId();
            NewbornReport newborn = new NewbornReport();
            try {
                NewbornEvalReport newbornEval = (NewbornEvalReport) EncountersDAO.getResolvedOne(conn, childId, pregnancyId, (long) 23, NewbornEvalReport.class);
                newborn.setNewbornEval(newbornEval);
                if (newbornEval.getDate_of_birthR() != null) {
                   pregnancyReport.setDateLastInfantDelivered(Date.valueOf(newbornEval.getDate_of_birthR()));
                }
                if (newbornEval.getTime_of_birth_1514R() != null) {
                   pregnancyReport.setTimeLastInfantDelivered(Time.valueOf(newbornEval.getTime_of_birth_1514R()));
                }
            } catch (ObjectNotFoundException e) {
                log.error(e);
            }
            try {
                InfantDischargeSummaryReport discharge = (InfantDischargeSummaryReport) EncountersDAO.getResolvedOne(conn, childId, pregnancyId, (long) 84, InfantDischargeSummaryReport.class);
                newborn.setInfantDischarge(discharge);
            } catch (ObjectNotFoundException e) {
                // it's ok
            }
            try {
                StillbirthDeliveryRecordReport stillbirth = (StillbirthDeliveryRecordReport) EncountersDAO.getResolvedOne(conn, childId, pregnancyId, (long) 40, StillbirthDeliveryRecordReport.class);
                newborn.setStillbirth(stillbirth);
            } catch (ObjectNotFoundException e) {
                // it's ok
            }

            List postnatalInfantVisits = ReportDAO.getAllResolved(conn, childId, pregnancyId, (long) 86, PostnatalInfantReport.class);
            newborn.setPostnatalVisits(postnatalInfantVisits);
            newborns.add(newborn);
        }
        List postnatalMaternalVisits = ReportDAO.getAllResolved(conn, patientId, pregnancyId, (long) 28, PostnatalMaternalVisitReport.class);
        pregnancyReport.setPostVisits(postnatalMaternalVisits);
    }
}
