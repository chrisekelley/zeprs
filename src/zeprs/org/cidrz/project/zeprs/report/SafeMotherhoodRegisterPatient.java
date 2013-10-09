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
 * Created on Mar 31, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.cidrz.project.zeprs.report;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.report.valueobject.SafeMotherhoodEncounter;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.project.zeprs.valueobject.gen.*;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.dao.OutcomeDAO;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.dao.PregnancyDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.rules.impl.OutcomeImpl;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.valueobject.*;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;


/**
 * @author ericl
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class SafeMotherhoodRegisterPatient {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(SafeMotherhoodRegisterPatient.class);

    private String smRegisterNumber;
    private Long patientId;
    private String name;
    private String address1;
    private String address2;
    private String originCode;
    private int age;
    private int gravida;
    private int parity;
    private Date firstAttDate;
    private Long encounter_id;  //Safe Motherhood form id
    private Date lastMenstDate;
    private Date estDateDelivery;
    private String rpr;
    private boolean tt1;
    private boolean tt2;
    private boolean tt3;
    private boolean tt4;
    private boolean tt5;
    private boolean attMonth123;
    private boolean attMonth4;
    private boolean attMonth5;
    private boolean attMonth6;
    private boolean attMonth7;
    private boolean attMonth8;
    private boolean attMonth9;
    private String reportMonth;
    private String reportYear;
    private Long currentPregnancyId;
    private String maritalStatus;
    private String egaWeeks;
    private List encounters;
    private TreeMap encounterMap = new TreeMap();
    private Map postnatalMap = new HashMap();
    private Map infantMap = new HashMap();
    private boolean antenatalMotherITNUse;
    private String staffName;


    SafeMotherhoodRegisterPatient() {

        smRegisterNumber = null;
        firstAttDate = null;
        name = null;
        address1 = null;
        originCode = null;
        //age = -1;
        gravida = 0;
        parity = 0;
        lastMenstDate = null;
        estDateDelivery = null;
        rpr = null;
        tt1 = false;
        tt2 = false;
        tt3 = false;
        tt4 = false;
        tt5 = false;
        attMonth123 = false;
        attMonth4 = false;
        attMonth5 = false;
        attMonth6 = false;
        attMonth7 = false;
        attMonth8 = false;
        attMonth9 = false;

    }

    /**
     * @return Returns the address1.
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * @param address1 The address1 to set.
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * @return Returns the age.
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age The age to set.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return Returns the attMonth123.
     */
    public boolean isAttMonth123() {
        return attMonth123;
    }

    /**
     * @param attMonth123 The attMonth123 to set.
     */
    public void setAttMonth123(boolean attMonth123) {
        this.attMonth123 = attMonth123;
    }

    /**
     * @return Returns the attMonth4.
     */
    public boolean isAttMonth4() {
        return attMonth4;
    }

    /**
     * @param attMonth4 The attMonth4 to set.
     */
    public void setAttMonth4(boolean attMonth4) {
        this.attMonth4 = attMonth4;
    }

    /**
     * @return Returns the attMonth5.
     */
    public boolean isAttMonth5() {
        return attMonth5;
    }

    /**
     * @param attMonth5 The attMonth5 to set.
     */
    public void setAttMonth5(boolean attMonth5) {
        this.attMonth5 = attMonth5;
    }

    /**
     * @return Returns the attMonth6.
     */
    public boolean isAttMonth6() {
        return attMonth6;
    }

    /**
     * @param attMonth6 The attMonth6 to set.
     */
    public void setAttMonth6(boolean attMonth6) {
        this.attMonth6 = attMonth6;
    }

    /**
     * @return Returns the attMonth7.
     */
    public boolean isAttMonth7() {
        return attMonth7;
    }

    /**
     * @param attMonth7 The attMonth7 to set.
     */
    public void setAttMonth7(boolean attMonth7) {
        this.attMonth7 = attMonth7;
    }

    /**
     * @return Returns the attMonth8.
     */
    public boolean isAttMonth8() {
        return attMonth8;
    }

    /**
     * @param attMonth8 The attMonth8 to set.
     */
    public void setAttMonth8(boolean attMonth8) {
        this.attMonth8 = attMonth8;
    }

    /**
     * @return Returns the attMonth9.
     */
    public boolean isAttMonth9() {
        return attMonth9;
    }

    /**
     * @param attMonth9 The attMonth9 to set.
     */
    public void setAttMonth9(boolean attMonth9) {
        this.attMonth9 = attMonth9;
    }

    /**
     * @return Returns the estDateDelivery.
     */
    public Date getEstDateDelivery() {
        return estDateDelivery;
    }

    /**
     * @param estDateDelivery The estDateDelivery to set.
     */
    public void setEstDateDelivery(Date estDateDelivery) {
        this.estDateDelivery = estDateDelivery;
    }

    /**
     * @return Returns the firstAttDate.
     */
    public Date getFirstAttDate() {
        return firstAttDate;
    }

    /**
     * @param firstAttDate The firstAttDate to set.
     */
    public void setFirstAttDate(Date firstAttDate) {
        this.firstAttDate = firstAttDate;
    }

    /**
     * @return Returns the gravida.
     */
    public int getGravida() {
        return gravida;
    }

    /**
     * @param gravida The gravida to set.
     */
    public void setGravida(int gravida) {
        this.gravida = gravida;
    }

    /**
     * @return Returns the lastMenstDate.
     */
    public Date getLastMenstDate() {
        return lastMenstDate;
    }

    /**
     * @param lastMenstDate The lastMenstDate to set.
     */
    public void setLastMenstDate(Date lastMenstDate) {
        this.lastMenstDate = lastMenstDate;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the originCode.
     */
    public String getOriginCode() {
        return originCode;
    }

    /**
     * @param originCode The originCode to set.
     */
    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }

    /**
     * @return Returns the parity.
     */
    public int getParity() {
        return parity;
    }

    public void setParity(int parity) {
        this.parity = parity;
    }

    /**
     * @return Returns the rpr.
     */
    public String getRpr() {
        return rpr;
    }

    /**
     * @param rpr The rpr to set.
     */
    public void setRpr(String rpr) {
        this.rpr = rpr;
    }

    /**
     * @return Returns the smRegisterNumber.
     */
    public String getSmRegisterNumber() {
        return smRegisterNumber;
    }

    /**
     * @param smRegisterNumber The smRegisterNumber to set.
     */
    public void setSmRegisterNumber(String smRegisterNumber) {
        this.smRegisterNumber = smRegisterNumber;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getEncounter_id() {
        return encounter_id;
    }

    public void setEncounter_id(Long encounter_id) {
        this.encounter_id = encounter_id;
    }

    /**
     * @return Returns the tt1.
     */
    public boolean isTt1() {
        return tt1;
    }

    /**
     * @param tt1 The tt1 to set.
     */
    public void setTt1(boolean tt1) {
        this.tt1 = tt1;
    }

    /**
     * @return Returns the tt2.
     */
    public boolean isTt2() {
        return tt2;
    }

    /**
     * @param tt2 The tt2 to set.
     */
    public void setTt2(boolean tt2) {
        this.tt2 = tt2;
    }

    /**
     * @return Returns the tt3.
     */
    public boolean isTt3() {
        return tt3;
    }

    /**
     * @param tt3 The tt3 to set.
     */
    public void setTt3(boolean tt3) {
        this.tt3 = tt3;
    }

    /**
     * @return Returns the tt4.
     */
    public boolean isTt4() {
        return tt4;
    }

    /**
     * @param tt4 The tt4 to set.
     */
    public void setTt4(boolean tt4) {
        this.tt4 = tt4;
    }

    /**
     * @return Returns the tt5.
     */
    public boolean isTt5() {
        return tt5;
    }

    /**
     * @param tt5 The tt5 to set.
     */
    public void setTt5(boolean tt5) {
        this.tt5 = tt5;
    }

    public Long getCurrentPregnancyId() {
        return currentPregnancyId;
    }

    public void setCurrentPregnancyId(Long currentPregnancyId) {
        this.currentPregnancyId = currentPregnancyId;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getEgaWeeks() {
        return egaWeeks;
    }

    public void setEgaWeeks(String egaWeeks) {
        this.egaWeeks = egaWeeks;
    }

    public List getEncounters() {
        return encounters;
    }

    public void setEncounters(List encounters) {
        this.encounters = encounters;
    }

    public TreeMap getEncounterMap() {
        return encounterMap;
    }

    public void setEncounterMap(TreeMap encounterMap) {
        this.encounterMap = encounterMap;
    }

    public boolean isAntenatalMotherITNUse() {
        return antenatalMotherITNUse;
    }

    public void setAntenatalMotherITNUse(boolean antenatalMotherITNUse) {
        this.antenatalMotherITNUse = antenatalMotherITNUse;
    }

    public Map getPostnatalMap() {
        return postnatalMap;
    }

    public void setPostnatalMap(Map postnatalMap) {
        this.postnatalMap = postnatalMap;
    }

    public Map getInfantMap() {
        return infantMap;
    }

    public void setInfantMap(Map infantMap) {
        this.infantMap = infantMap;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public void loadPatient(int patientID, Date beginDate, Date endDate, int siteID, String type, Connection conn) {

        // ResultSet rs = null;
        // ResultSetMetaData rsStruc = null;
        // String sql = null;
        int formID;

        this.setPatientId((long) patientID);
        patientId = (long) patientID;

        /**
         * First get the demographics.
         */

         try {
            getDemographics(conn, patientID, this);
        } catch (SQLException e) {
            log.error(e);
        }

        /**
         * Get the current pregnancy id.
         */

        Long pregnancyId;
        pregnancyId = PatientRecordUtils.getPregnancyId(conn, (long) patientID);
        this.setCurrentPregnancyId(pregnancyId);
        if (type.equals("antenatal")) {
            /**
             * parity
             */
            try {
                parity = PatientRecordUtils.getParity(conn, patientId);
                this.setParity(parity);
                gravida = PatientRecordUtils.getGravida(conn, patientId);
                this.setGravida(gravida);
            } catch (SQLException e) {
                log.error(e);
            } catch (ServletException e) {
                log.error(e);
            }

            ///////////////////////////////////////////////////////////////////////
            // First Antenatal Attendance, Estimated Date of Delivery,
            // Last Normal Menstruation, Attendance in month of pregnancy (month 1, 2, 3)
            ///////////////////////////////////////////////////////////////////////

            // Date of first attendance
            EncounterData encounter = null;
            try {
                encounter = getFirstPatientVisit(conn, patientID, pregnancyId);
                if (encounter.getDateVisit() != null) {
                    this.setFirstAttDate(encounter.getDateVisit());
                    if (encounter.getLastModifiedByName() != null && !encounter.getLastModifiedByName().equals(""))
                        this.setStaffName(encounter.getLastModifiedByName());
                }
            } catch (ServletException e) {
                log.error(e);
            } catch (SQLException e) {
                log.error(e);
            } catch (ObjectNotFoundException e) {
                log.error(e);
            }
            ///////////////////////////////////////////////////////////////////////
            // Estimated Delivery Date
            ///////////////////////////////////////////////////////////////////////

            try {
                getEga(conn, patientID, this);
            } catch (SQLException e) {
                log.error(e);
            } catch (ServletException e) {
                log.error(e);
            } catch (ObjectNotFoundException e) {
                // it's ok
            }

            // as this is the initial physical exam, if estimated date of
            // delivery is over 6 months away, then set the attendance in
            // first 3 months of pregnancy boolean to true.

            if (this.estDateDelivery != null && this.firstAttDate != null) {
                if ((this.estDateDelivery.getTime() - this.firstAttDate.getTime()) > (1000 * 60 * 60 * 24 * 180)) {
                    this.setAttMonth123(true);
                } else {
                    this.setAttMonth123(false);
                }
            }

            List rprVisits = null;
            try {
                rprVisits = EncountersDAO.getAll(conn, patientId, pregnancyId, new Long("90"), Rpr.class);
                for (int i = 0; i < rprVisits.size(); i++) {
                    Rpr rprVisit = (Rpr) rprVisits.get(i);
                    Date dateVisit = rprVisit.getDateVisit();
                    Integer rprResult = rprVisit.getField1563();
                    Integer rprDrugInt = rprVisit.getField1565();
                    String staffName = rprVisit.getLastModifiedByName();
                    SafeMotherhoodEncounter sme = (SafeMotherhoodEncounter) this.getEncounterMap().get(dateVisit);
                    if (sme == null) {
                        sme = new SafeMotherhoodEncounter();
                        this.getEncounterMap().put(dateVisit, sme);
                        sme.setDateVisit(dateVisit);
                        if (staffName != null && !staffName.equals("")) {
                            sme.setStaffName(staffName);
                        }
                    }
                    if (rprResult != null) {
                        switch (i) {
                            case 0:
                                if (rprResult == 2784) {
                                    sme.setLabRPR1("neg");
                                } else if (rprResult == 2785) {
                                    sme.setLabRPR1("pos");
                                } else if (rprResult == 2786) {
                                    sme.setLabRPR1("N/A");
                                }
                                break;
                            case 1:
                                if (rprResult == 2784) {
                                    sme.setLabRPR2("neg");
                                } else if (rprResult == 2785) {
                                    sme.setLabRPR2("pos");
                                } else if (rprResult == 2786) {
                                    sme.setLabRPR2("N/A");
                                }
                                break;
                        }
                    }
                    if (rprDrugInt != null) {
                        switch (rprDrugInt) {
                            case 2787:
                                sme.setTreatmentBPenicillin(true);
                                break;
                            case 2788:
                                sme.setTreatmentErythromycin(true);
                                break;
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }


            List labVisits = null;
            try {
                labVisits = EncountersDAO.getAll(conn, patientId, pregnancyId, new Long("87"), LabTest.class);
                for (int i = 0; i < labVisits.size(); i++) {
                    LabTest labTest = (LabTest) labVisits.get(i);
                    Date dateVisit = labTest.getDateVisit();
                    String staffName = labTest.getLastModifiedByName();
                    SafeMotherhoodEncounter sme = (SafeMotherhoodEncounter) this.getEncounterMap().get(dateVisit);
                    if (sme == null) {
                        sme = new SafeMotherhoodEncounter();
                        this.getEncounterMap().put(dateVisit, sme);
                        sme.setDateVisit(dateVisit);
                        if (staffName != null && !staffName.equals("")) {
                            sme.setStaffName(staffName);
                        }
                    }

                    Integer labType = labTest.getField1845();
                    Float resultNumeric = labTest.getField1858();
                    if (resultNumeric != null) {
                        switch (labType) {
                            case 2925:
                                sme.setLabHaemoglobin1(resultNumeric.toString());
                                break;
                            case 2926:
                                sme.setLabHaemoglobin1(resultNumeric.toString());
                                break;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // check protein albumen tests for protein.
            List routineAnteVisits = null;
            try {
                routineAnteVisits = EncountersDAO.getAll(conn, patientId, pregnancyId, new Long("80"), RoutineAnte.class);
                for (int i = 0; i < routineAnteVisits.size(); i++) {
                    RoutineAnte routineAnte = (RoutineAnte) routineAnteVisits.get(i);
                    Integer urineProteinInt = routineAnte.getField242();
                    Integer malariaSp = routineAnte.getField1965();
                    Date dateVisit = routineAnte.getDateVisit();
                    String staffName = routineAnte.getLastModifiedByName();
                    Byte folate = routineAnte.getField1963();
                    Byte iron = routineAnte.getField1964();
                    Byte deworming = routineAnte.getField2005();
                    SafeMotherhoodEncounter sme = (SafeMotherhoodEncounter) this.getEncounterMap().get(dateVisit);
                    if (sme == null) {
                        sme = new SafeMotherhoodEncounter();
                        this.getEncounterMap().put(dateVisit, sme);
                        sme.setDateVisit(dateVisit);
                        if (staffName != null && !staffName.equals("")) {
                            sme.setStaffName(staffName);
                        }
                    }
                    if (urineProteinInt != null) {
                        String urineProtein = PatientRecordUtils.resolveEnum(urineProteinInt, 0);
                        sme.setLabUrineProtein(urineProtein);
                    }
                    if (malariaSp != null) {
                        String spValue = PatientRecordUtils.resolveEnum(malariaSp, 0);
                        if (spValue.equals("SP1")) {
                            sme.setTreatmentSP1(true);
                            //log.debug("SM!: " + sme.isTreatmentMalariaSM1());
                        } else if (spValue.equals("SP2")) {
                            sme.setTreatmentSP2(true);
                            //log.debug(sme.isTreatmentMalariaSM2());
                        } else if (spValue.equals("SP3")) {
                            sme.setTreatmentSP3(true);
                            //log.debug(sme.isTreatmentMalariaSM3());
                        }
                    }
                    if (folate != null && folate == 1) {
                        sme.setTreatmentFolate(true);
                    }
                    if (iron != null && iron == 1) {
                        sme.setTreatmentIron(true);
                    }
                    if (deworming != null && deworming == 1) {
                        sme.setTreatmentAntihelmenthes(true);
                    }
                    //this.getEncounterMap().put(dateVisit, sme);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                LatentFirstStageLabour latent = (LatentFirstStageLabour) EncountersDAO.getOne(conn, patientId, pregnancyId, new Long("17"));
                Integer urineProteinInt = latent.getField242();
                Date dateVisit = latent.getDateVisit();
                String staffName = latent.getStaffName();
                SafeMotherhoodEncounter sme = (SafeMotherhoodEncounter) this.getEncounterMap().get(dateVisit);
                if (sme == null) {
                    sme = new SafeMotherhoodEncounter();
                    this.getEncounterMap().put(dateVisit, sme);
                    sme.setDateVisit(dateVisit);
                    if (staffName != null && !staffName.equals("")) {
                        sme.setStaffName(staffName);
                    }
                }
                if (urineProteinInt != null) {
                    String urineProtein = PatientRecordUtils.resolveEnum(urineProteinInt, 0);
                    sme.setLabUrineProtein(urineProtein);
                    sme.setDateVisit(dateVisit);
                }
            } catch (ObjectNotFoundException e) {
                // it's ok
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            List drugInterventions = null;
            try {
                drugInterventions = EncountersDAO.getAll(conn, patientId, pregnancyId, new Long("88"), DrugIntervention.class);
                for (int i = 0; i < drugInterventions.size(); i++) {
                    DrugIntervention visit = (DrugIntervention) drugInterventions.get(i);
                    Integer drugType1 = visit.getField1854();
                    Integer drugType2 = visit.getField1950();
                    Integer drugType3 = visit.getField1951();
                    Integer drugType4 = visit.getField1952();
                    Integer drugType5 = visit.getField1953();
                    Integer drugType6 = visit.getField1954();
                    Byte dispensed = visit.getField1855();
                    Date dateVisit = visit.getDateVisit();
                    String staffName = visit.getLastModifiedByName();
                    SafeMotherhoodEncounter sme = (SafeMotherhoodEncounter) this.getEncounterMap().get(dateVisit);
                    if (sme == null) {
                        sme = new SafeMotherhoodEncounter();
                        this.getEncounterMap().put(dateVisit, sme);
                        if (staffName != null && !staffName.equals("")) {
                            sme.setStaffName(staffName);
                        }
                        sme.setDateVisit(dateVisit);
                    }

                    if (drugType1 != null) {
                        resolveDrugTypeEnum(drugType1, dispensed, sme);
                    }
                    if (drugType2 != null) {
                        resolveDrugTypeEnum(drugType2, dispensed, sme);
                    }
                    if (drugType3 != null) {
                        resolveDrugTypeEnum(drugType3, dispensed, sme);
                    }
                    if (drugType4 != null) {
                        resolveDrugTypeEnum(drugType4, dispensed, sme);
                    }
                    if (drugType5 != null) {
                        resolveDrugTypeEnum(drugType5, dispensed, sme);
                    }
                    if (drugType6 != null) {
                        resolveDrugTypeEnum(drugType6, dispensed, sme);
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            ///////////////////////////////////////////////////////////////////////
            // TT Immunisation
            ///////////////////////////////////////////////////////////////////////

            formID = 92;
            // Next, get all patient encounters for this form
            SafeMotherhoodCare sMc = null;
            try {
                sMc = (SafeMotherhoodCare) EncountersDAO.getMostRecentRecord(conn, new Long(patientID), pregnancyId, new Long(formID), SafeMotherhoodCare.class);
                if (sMc.getField1887() != null && sMc.getField1887() == 1) {
                    this.setTt1(true);
                }
                if (sMc.getField1888() != null && sMc.getField1888() == 1) {
                    this.setTt2(true);
                }
                if (sMc.getField1889() != null && sMc.getField1889() == 1) {
                    this.setTt3(true);
                }
                if (sMc.getField1890() != null && sMc.getField1890() == 1) {
                    this.setTt4(true);
                }
                if (sMc.getField1891() != null && sMc.getField1891() == 1) {
                    this.setTt5(true);
                }
                if (sMc.getField1734() != null && sMc.getField1734() == 1) {
                    this.setAntenatalMotherITNUse(true);
                }
            } catch (IOException e) {
                log.error(e);
            } catch (ServletException e) {
                log.error(e);
            } catch (SQLException e) {
                log.error(e);
            } catch (NullPointerException e) {
                log.error(e);
            } catch (ObjectNotFoundException e) {
                // it's ok
            }

            /**
             * Referrals
             */
             List referrals = null;
            try {
                referrals = OutcomeDAO.getReferrals(conn, patientId, pregnancyId);
                for (int i = 0; i < referrals.size(); i++) {
                    OutcomeImpl outcome = (OutcomeImpl) referrals.get(i);
                    Date dateVisit = new Date(outcome.getCreated().getTime());
                    String staffName = outcome.getLastModifiedBy();
                    SafeMotherhoodEncounter sme = (SafeMotherhoodEncounter) this.getEncounterMap().get(dateVisit);
                    if (sme == null) {
                        sme = new SafeMotherhoodEncounter();
                        this.getEncounterMap().put(dateVisit, sme);
                        sme.setDateVisit(dateVisit);
                        if (staffName != null && !staffName.equals("")) {
                            sme.setStaffName(staffName);
                        }
                    }
                    Site referringSite = (Site) DynaSiteObjects.getClinicMap().get(outcome.getReferringSiteId());
                    sme.setAntenatalReferralFrom(referringSite.getName());
                    sme.setAttendedBy(outcome.getLastModifiedBy());
                    if (outcome.getReferringSiteId() != outcome.getSiteId()) {
                        Site admittingSite = null;
                        Long siteId = outcome.getSiteId();
                        if (siteId != null) {
                            try {
                                admittingSite = (Site) DynaSiteObjects.getClinicMap().get(siteId);
                                sme.setAntenatalReferralTo(admittingSite.getName());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (type.equals("postnatal")) {


            List postnatalMaternalVisits = null;
            try {
                postnatalMaternalVisits = EncountersDAO.getAll(conn, patientId, pregnancyId, new Long("28"), PostnatalMaternalVisit.class);
                for (int i = 0; i < postnatalMaternalVisits.size(); i++) {
                    PostnatalMaternalVisit visit = (PostnatalMaternalVisit) postnatalMaternalVisits.get(i);
                    Date dateVisit = visit.getDateVisit();
                    SafeMotherhoodEncounter sme = (SafeMotherhoodEncounter) this.getPostnatalMap().get(dateVisit);
                    if (sme == null) {
                        sme = new SafeMotherhoodEncounter();
                        this.getPostnatalMap().put(dateVisit, sme);
                    }
                    Integer urineProteinInt = visit.getField242();
                    Integer postnatalMotherBpSys = visit.getField224();
                    Integer postnatalMotherBpDias = visit.getField225();
                    Integer postnatalMotherPulseInt = visit.getField171();
                    Integer postnatalMotherUrineInt = visit.getField240();
                    Integer postnatalMotherUterusContractedInt = visit.getField187();
                    Integer postnatalMotherLochiaFlowInt = visit.getField645();
                    Integer postnatalMotherLochiaColourInt = visit.getField646();
                    Integer postnatalMotherLochiaOdorInt = visit.getField647();
                    Integer postnatalMotherBreastConditionInt = visit.getField166();
                    switch (i) {
                        case 0:
                            sme.setDatePostnatalVisitSixDays(dateVisit);
                            break;
                        case 1:
                            sme.setDatePostnatalVisitSixWeeks(dateVisit);
                            break;
                    }

                    if (urineProteinInt != null) {
                        String urineProtein = PatientRecordUtils.resolveEnum(urineProteinInt, 0);
                        sme.setLabUrineProtein(urineProtein);
                    }
                    if (postnatalMotherBpSys != null && postnatalMotherBpDias != null) {
                        String postnatalMotherBpSysRes = PatientRecordUtils.resolveEnum(postnatalMotherBpSys, 0);
                        String postnatalMotherBpDiasRes = PatientRecordUtils.resolveEnum(postnatalMotherBpDias, 0);
                        String postnatalMotherBp = postnatalMotherBpSysRes + "/" + postnatalMotherBpDiasRes;
                        sme.setPostnatalMotherBp(postnatalMotherBp);
                    }
                    if (postnatalMotherPulseInt != null) {
                        String postnatalMotherPulse = PatientRecordUtils.resolveEnum(postnatalMotherPulseInt, 0);
                        sme.setPostnatalMotherPulse(postnatalMotherPulse);
                    }
                    if (postnatalMotherUrineInt != null && postnatalMotherUrineInt == 126) {
                        sme.setPostnatalMotherUrine(true);
                    }
                    if (postnatalMotherUterusContractedInt != null) {
                        String postnatalMotherUterusContracted = PatientRecordUtils.resolveEnum(postnatalMotherUterusContractedInt, 0);
                        sme.setPostnatalMotherUterusContracted(postnatalMotherUterusContracted);
                    }
                    String postnatalMotherLochia = "";
                    String postnatalMotherLochiaFlow = "";
                    if (postnatalMotherLochiaFlowInt != null) {
                        postnatalMotherLochiaFlow = PatientRecordUtils.resolveEnum(postnatalMotherLochiaFlowInt, 0);
                        postnatalMotherLochia = "Flow: " + postnatalMotherLochiaFlow;
                    }
                    String postnatalMotherLochiaColour = "";
                    if (postnatalMotherLochiaColourInt != null) {
                        postnatalMotherLochiaColour = PatientRecordUtils.resolveEnum(postnatalMotherLochiaColourInt, 0);
                        postnatalMotherLochia = postnatalMotherLochia + " Color: " + postnatalMotherLochiaColour;
                    }
                    String postnatalMotherLochiaOdor = "";
                    if (postnatalMotherLochiaOdorInt != null) {
                        postnatalMotherLochiaOdor = PatientRecordUtils.resolveEnum(postnatalMotherLochiaOdorInt, 0);
                        postnatalMotherLochia = postnatalMotherLochia + " Odor: " + postnatalMotherLochiaOdor;
                    }
                    sme.setPostnatalMotherLochia(postnatalMotherLochia);

                    if (postnatalMotherBreastConditionInt != null) {
                        String postnatalMotherBreastCondition = PatientRecordUtils.resolveEnum(postnatalMotherBreastConditionInt, 0);
                        sme.setPostnatalMotherBreastCondition(postnatalMotherBreastCondition);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }


            List postnatalInfantVisits = null;
            List children = null;
            try {
                children = PatientDAO.getChildren(conn, patientId, pregnancyId);
                for (int i = 0; i < children.size(); i++) {
                    Patient child = (Patient) children.get(i);
                    try {
                        postnatalInfantVisits = EncountersDAO.getAll(conn, child.getId(), pregnancyId, new Long("86"), PostnatalInfant.class);
                        for (int j = 0; j < postnatalInfantVisits.size(); j++) {
                            PostnatalInfant postnatalInfant = (PostnatalInfant) postnatalInfantVisits.get(j);
                            Date dateVisit = postnatalInfant.getDateVisit();
                            SafeMotherhoodEncounter sme = (SafeMotherhoodEncounter) this.getPostnatalMap().get(dateVisit);
                            if (sme == null) {
                                sme = new SafeMotherhoodEncounter();
                                this.getPostnatalMap().put(dateVisit, sme);
                            }
                            Integer postnatalBabyFeedsInt = postnatalInfant.getField1541();
                            Integer postnatalBabyCordStumpInt = postnatalInfant.getField694();
                            Float postnatalBabyTemperature = postnatalInfant.getField680();
                            Float postnatalBabyWeight = postnatalInfant.getField679();

                            if (postnatalBabyFeedsInt != null) {
                                String postnatalBabyFeeds = PatientRecordUtils.resolveEnum(postnatalBabyFeedsInt, 0);
                                sme.setPostnatalBabyFeeds(postnatalBabyFeeds);
                            }
                            if (postnatalBabyCordStumpInt != null) {
                                String postnatalBabyCordStump = PatientRecordUtils.resolveEnum(postnatalBabyCordStumpInt, 0);
                                sme.setPostnatalBabyCordStump(postnatalBabyCordStump);
                            }
                            if (postnatalBabyTemperature != null) {
                                sme.setPostnatalBabyTemperature(postnatalBabyTemperature.toString());
                            }
                            if (postnatalBabyWeight != null) {
                                sme.setPostnatalBabyWeight(postnatalBabyWeight.toString());
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ServletException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // convert encounterMap to list
        //Object keys[] = this.getEncounterMap().values().toArray();
        Set encounterSet = this.getEncounterMap().entrySet();
        List encounterList = new ArrayList();
        for (Iterator iterator = encounterSet.iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();
            SafeMotherhoodEncounter sme = (SafeMotherhoodEncounter) entry.getValue();
            encounterList.add(sme);
        }
        //List l = Arrays.asList(keys);
        this.setEncounters(encounterList);
    }

    protected void resolveDrugTypeEnum(Integer drugType, Byte dispensed, SafeMotherhoodEncounter sme) {
        switch (drugType) {
            case 2930:
                if (dispensed != null && dispensed.intValue() == 1) {
                    sme.setTreatmentIron(true);
                }
                break;
            case 2931:
                if (dispensed != null && dispensed.intValue() == 1) {
                    sme.setTreatmentFolate(true);
                }
                break;
            case 2933:   // deworming meds
                if (dispensed != null && dispensed.intValue() == 1) {
                    sme.setTreatmentAntihelmenthes(true);
                }
                break;
            case 2932: // sm1
                if (dispensed != null && dispensed.intValue() == 1) {
                    sme.setTreatmentMalariaSM1(true);
                }
            case 2979: // sm2
                if (dispensed != null && dispensed.intValue() == 1) {
                    sme.setTreatmentMalariaSM1(true);
                }
            case 2980: // sm2
                if (dispensed != null && dispensed.intValue() == 1) {
                    sme.setTreatmentMalariaSM1(true);
                }
                break;

            case 2977:   // Multivitamin
                if (dispensed != null && dispensed.intValue() == 1) {
                    sme.setTreatmentMultivitamin(true);
                }
                break;
        }
    }

    public void loadPostnatal(int patientID, Date beginDate, Date endDate, int siteID, Connection conn) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsStruc = null;
        String sql = null;
        int formID = 0;

        this.setPatientId((long) patientID);
        patientId = (long) patientID;

        /*try {
            List pregnancies = PregnancyDAO.getPatientPregnancies(patientId);
        } catch (SQLException e) {
            log.error(e);
        } catch (ServletException e) {
            log.error(e);
        }*/
        Long pregnancyId = null;
        //  try {

        /**
         * First get the current pregnancy id
         */

        SessionPatient currPreg = null;
        try {
            currPreg = PatientRecordUtils.getSessionPatientPregnancy(conn, (long) patientID);
            pregnancyId = currPreg.getCurrentPregnancyId();
        } catch (ServletException e) {
            log.error(e);
        } catch (SQLException e) {
            log.error(e);
        } catch (ObjectNotFoundException e) {
            List pregnancies = null;
            try {
                pregnancies = PregnancyDAO.getPatientPregnancies(conn, (long) patientID);
                for (int i = 0; i < pregnancies.size(); i++) {
                    Pregnancy pregnancy = (Pregnancy) pregnancies.get(i);
                    pregnancyId = pregnancy.getId();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ServletException e1) {
                e1.printStackTrace();
            }

        }
        this.setCurrentPregnancyId(pregnancyId);

        try {
            getDemographics(conn, patientID, this);
        } catch (SQLException e) {
            log.error(e);
        }

        List postnatalMaternalVisits = null;
        try {
            postnatalMaternalVisits = EncountersDAO.getAll(conn, patientId, pregnancyId, new Long("28"), PostnatalMaternalVisit.class);
            for (int i = 0; i < postnatalMaternalVisits.size(); i++) {
                PostnatalMaternalVisit visit = (PostnatalMaternalVisit) postnatalMaternalVisits.get(i);
                Date dateVisit = visit.getDateVisit();
                SafeMotherhoodEncounter sme = (SafeMotherhoodEncounter) this.getEncounterMap().get(dateVisit);
                if (sme == null) {
                    sme = new SafeMotherhoodEncounter();
                    this.getPostnatalMap().put(dateVisit, sme);
                }
                Integer urineProteinInt = visit.getField242();
                Integer postnatalMotherBpSys = visit.getField224();
                Integer postnatalMotherBpDias = visit.getField225();
                Integer postnatalMotherPulseInt = visit.getField171();
                Integer postnatalMotherUrineInt = visit.getField240();
                Integer postnatalMotherUterusContractedInt = visit.getField187();
                Integer postnatalMotherLochiaFlowInt = visit.getField645();
                Integer postnatalMotherLochiaColourInt = visit.getField646();
                Integer postnatalMotherLochiaOdorInt = visit.getField647();
                Integer postnatalMotherBreastConditionInt = visit.getField166();
                switch (i) {
                    case 0:
                        sme.setDatePostnatalVisitSixDays(dateVisit);
                        break;
                    case 1:
                        sme.setDatePostnatalVisitSixWeeks(dateVisit);
                        break;
                }

                if (urineProteinInt != null) {
                    String urineProtein = PatientRecordUtils.resolveEnum(urineProteinInt, 0);
                    sme.setLabUrineProtein(urineProtein);
                }
                if (postnatalMotherBpSys != null && postnatalMotherBpDias != null) {
                    String postnatalMotherBp = postnatalMotherBpSys + "/" + postnatalMotherBpDias;
                    sme.setPostnatalMotherBp(postnatalMotherBp);
                }
                if (postnatalMotherPulseInt != null) {
                    String postnatalMotherPulse = PatientRecordUtils.resolveEnum(postnatalMotherPulseInt, 0);
                    sme.setPostnatalMotherPulse(postnatalMotherPulse);
                }
                if (postnatalMotherUrineInt != null && postnatalMotherUrineInt == 126) {
                    sme.setPostnatalMotherUrine(true);
                }
                if (postnatalMotherUterusContractedInt != null) {
                    String postnatalMotherUterusContracted = PatientRecordUtils.resolveEnum(postnatalMotherUterusContractedInt, 0);
                    sme.setPostnatalMotherUterusContracted(postnatalMotherUterusContracted);
                }
                String postnatalMotherLochia = "";
                String postnatalMotherLochiaFlow = "";
                if (postnatalMotherLochiaFlowInt != null) {
                    postnatalMotherLochiaFlow = PatientRecordUtils.resolveEnum(postnatalMotherLochiaFlowInt, 0);
                    postnatalMotherLochia = "Flow: " + postnatalMotherLochiaFlow;
                }
                String postnatalMotherLochiaColour = "";
                if (postnatalMotherLochiaColourInt != null) {
                    postnatalMotherLochiaColour = PatientRecordUtils.resolveEnum(postnatalMotherLochiaColourInt, 0);
                    postnatalMotherLochia = postnatalMotherLochia + " Color: " + postnatalMotherLochiaColour;
                }
                String postnatalMotherLochiaOdor = "";
                if (postnatalMotherLochiaOdorInt != null) {
                    postnatalMotherLochiaOdor = PatientRecordUtils.resolveEnum(postnatalMotherLochiaOdorInt, 0);
                    postnatalMotherLochia = postnatalMotherLochia + " Odor: " + postnatalMotherLochiaOdor;
                }
                sme.setPostnatalMotherLochia(postnatalMotherLochia);

                if (postnatalMotherBreastConditionInt != null) {
                    String postnatalMotherBreastCondition = PatientRecordUtils.resolveEnum(postnatalMotherBreastConditionInt, 0);
                    sme.setPostnatalMotherBreastCondition(postnatalMotherBreastCondition);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        List postnatalInfantVisits = null;
        try {
            postnatalInfantVisits = EncountersDAO.getAll(conn, patientId, pregnancyId, new Long("86"), PostnatalInfant.class);
            for (int i = 0; i < postnatalInfantVisits.size(); i++) {
                PostnatalInfant postnatalInfant = (PostnatalInfant) postnatalInfantVisits.get(i);
                Date dateVisit = postnatalInfant.getDateVisit();
                SafeMotherhoodEncounter sme = (SafeMotherhoodEncounter) this.getEncounterMap().get(dateVisit);
                if (sme == null) {
                    sme = new SafeMotherhoodEncounter();
                    this.getInfantMap().put(dateVisit, sme);
                }
                Integer postnatalBabyFeedsInt = postnatalInfant.getField1541();
                Integer postnatalBabyCordStumpInt = postnatalInfant.getField694();
                Float postnatalBabyTemperature = postnatalInfant.getField680();
                Float postnatalBabyWeight = postnatalInfant.getField679();

                if (postnatalBabyFeedsInt != null) {
                    String postnatalBabyFeeds = PatientRecordUtils.resolveEnum(postnatalBabyFeedsInt, 0);
                    sme.setPostnatalBabyFeeds(postnatalBabyFeeds);
                }
                if (postnatalBabyCordStumpInt != null) {
                    String postnatalBabyCordStump = PatientRecordUtils.resolveEnum(postnatalBabyCordStumpInt, 0);
                    sme.setPostnatalBabyCordStump(postnatalBabyCordStump);
                }
                if (postnatalBabyTemperature != null) {
                    sme.setPostnatalBabyTemperature(postnatalBabyTemperature.toString());
                }
                if (postnatalBabyWeight != null) {
                    sme.setPostnatalBabyWeight(postnatalBabyWeight.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void getDemographics(Connection conn, int patientID, SafeMotherhoodRegisterPatient safeMother) throws SQLException {
        String sql;
        PreparedStatement ps;
        ResultSet rs = null;
        ///////////////////////////////////////////////////////////////////////
        // Name, Address, Age
        ///////////////////////////////////////////////////////////////////////

        sql = "SELECT forenames_7, surname_6, residential_19, residential_20, birth_date_17, patient_id_number, " +
                "marital_stat_10, date_visit, CONCAT_WS(', ', u2.lastname, u2.firstname) AS lastModifiedByName " +
                "FROM encounter " +
                "JOIN (patientregistration) ON encounter.id = patientregistration.id " +
                "LEFT JOIN userdata.address u2 ON u2.nickname = encounter.last_modified_by " +
                "WHERE encounter.patient_id = ?";

        ps = conn.prepareStatement(sql);
        ps.setInt(1, patientID);
        rs = ps.executeQuery();

        while (rs.next()) {
            String name = null;
            name = rs.getString("surname_6") + ", " + rs.getString("forenames_7");
            String address1 = rs.getString("residential_19");
            String address2 = rs.getString("residential_20");
            Date birthDate = rs.getDate("birth_date_17");
            String smNumber = rs.getString("patient_id_number");
            Date dateVisit  = rs.getDate("date_visit");
            String staffName = rs.getString("lastModifiedByName");
            Integer maritalStatusInt = null;
            try {
                maritalStatusInt = rs.getInt("marital_stat_10");
            } catch (SQLException e) {
                log.error(e);
            }
            if (maritalStatusInt != 0) {
                try {
                    String maritalStatus = PatientRecordUtils.resolveEnum(maritalStatusInt, 5);
                    safeMother.setMaritalStatus(maritalStatus);
                } catch (Exception e) {
                    log.error(e);
                }
            }
            safeMother.setName(name);
            safeMother.setAddress1(address1);
            safeMother.setAddress2(address2);
            safeMother.setFirstAttDate(dateVisit);
            if (staffName != null && !staffName.equals("")) {
                safeMother.setStaffName(staffName);
            }
            if (birthDate != null) {
                try {
                    safeMother.setAge(ZEPRSUtils.getCurrentAge(birthDate));
                } catch (Exception e) {
                    log.error(e + " birthDate: " + birthDate);
                }
            }
            safeMother.setSmRegisterNumber(smNumber);
        }
    }

    public static void getEga(Connection conn, int patientID, SafeMotherhoodRegisterPatient safeMother) throws SQLException, ServletException, ObjectNotFoundException {
        String sql;
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Long> values;
        // Get this data from patient_status table
        Long encounterId = null;
        Long currentFlowId = null;
        Long currentEgaDaysDb = null;
        sql = "SELECT current_lmp_date, current_ega_days, current_ega_days_encounter_id, current_flow  " +
                "FROM patient_status WHERE id = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, patientID);

        rs = ps.executeQuery();

        while (rs.next()) {
            encounterId = rs.getLong("current_ega_days_encounter_id");
            currentFlowId = rs.getLong("current_flow");
            currentEgaDaysDb = rs.getLong("current_ega_days");
        }

        long egaToday = 0;

        if (encounterId != null) {
            sql = "select encounter.id, date_visit AS datevisit, lmp_127_calculated AS field127, edd_128 AS field128 FROM pregnancydating, encounter WHERE encounter.id = pregnancydating.id and encounter.id=? ";
            // SQL_RETRIEVE82="SELECT encounter.id AS id, planned_preg_135 AS field135, contracept_practiced_136 AS field136, contraceptive_choice_137 AS field137, contraceptive_other_138 AS field138, lmp_reliability_126 AS field126, lmp_127 AS field127, edd_128 AS field128, ega_129 AS field129, quickening_130 AS field130, menstrual_history_131 AS field131, cycle_in_days_132 AS field132, uterus_size_in_days_188 AS field188, dating_method AS field1615, patientId AS patientId, form_id AS formId, flow_id AS flowId, date_visit AS dateVisit, pregnancy_id AS pregnancyId, last_modified AS lastModified, created AS created, last_modified_by AS lastModifiedBy, created_by AS createdBy, site_id AS site  FROM pregnancydating, encounter WHERE encounter.id = pregnancydating.id AND encounter.patientId=? AND encounter.pregnancy_id=?"
            values = new ArrayList<Long>();
            values.add(encounterId);
            PregnancyDating pregDate = (PregnancyDating) DatabaseUtils.getBean(conn, PregnancyDating.class, sql, values);
            if (pregDate != null) {
                Flow currentFlow = (Flow) DynaSiteObjects.getFlowMap().get(currentFlowId);
                if ((currentFlow.getFlowOrder().intValue() < 5) || currentFlow.getFlowOrder().intValue() == 101) {
                    java.util.Date dateVisit = pregDate.getDateVisit();
                    try {
                        egaToday = PatientRecordUtils.calcCurrentEga(dateVisit, currentEgaDaysDb);
                        long egaWeeks = egaToday / 7;
                        long egaDays = egaToday % 7;
                        safeMother.setEgaWeeks(egaWeeks + " " + egaDays + "/7");
                    } catch (Exception e) {
                        log.error(e);
                    }
                }
                Date edd = pregDate.getField128();
                Date lmp = pregDate.getField127();
                safeMother.setEstDateDelivery(edd);
                safeMother.setLastMenstDate(lmp);
            }
        }
    }

    public static EncounterData getFirstPatientVisit(Connection conn, int patientID, Long pregnancyId) throws ServletException, SQLException, ObjectNotFoundException {
        String sql;
        EncounterData encounter;
        /*sql = "SELECT min(date_visit) AS dateVisit, CONCAT_WS(', ', u2.lastname, u2.firstname) AS lastModifiedByName  " +
                "FROM encounter e " +
                "LEFT JOIN userdata.address u2 ON u2.nickname = e.last_modified_by " +
                "WHERE e.patient_id=? AND e.pregnancy_id=? " +
                "GROUP BY date_visit;"; */
        sql = "SELECT min(date_visit) AS dateVisit " +
                "FROM encounter e " +
                "WHERE e.patient_id=? AND e.pregnancy_id=?; ";
        ArrayList values = new ArrayList();
        values.add(patientID);
        values.add(pregnancyId);
        encounter = (EncounterData) DatabaseUtils.getBean(conn, EncounterData.class, sql, values);
        return encounter;
    }

}
