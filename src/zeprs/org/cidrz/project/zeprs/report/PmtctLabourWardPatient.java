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
import org.cidrz.project.zeprs.valueobject.*;
import org.cidrz.project.zeprs.valueobject.gen.*;
import org.cidrz.project.zeprs.valueobject.report.gen.NewbornEvalReport;
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
public class PmtctLabourWardPatient {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(PmtctLabourWardPatient.class);

    private String smRegisterNumber;
    private Long patientId;
    private String name;
    private String address1;
    private String address2;
    private String originCode;
    private Long encounter_id;  //Safe Motherhood form id
    private String reportMonth;
    private String reportYear;
    private Long currentPregnancyId;
    private String maritalStatus;
    private List encounters;
    private Map encounterMap = new HashMap();
    private Map postnatalMap = new HashMap();
    private Map infantMap = new HashMap();
    private boolean antenatalMotherITNUse;
    private boolean arvReceived;
    private String placeArvReceived;
    private Date dateArvReceived;
    private boolean multipleBirth;
    private String birthType;
    private String babyRegimenDelivery;
    private boolean babyDoseGiven;
    private String deliveryNurse;
    private Date dateDelivery;
    private Integer hivPositive;
    private String regimenMother;


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

    public List getEncounters() {
        return encounters;
    }

    public void setEncounters(List encounters) {
        this.encounters = encounters;
    }

    public Map getEncounterMap() {
        return encounterMap;
    }

    public void setEncounterMap(Map encounterMap) {
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

    public String getReportYear() {
        return reportYear;
    }

    public void setReportYear(String reportYear) {
        this.reportYear = reportYear;
    }

    public String getReportMonth() {
        return reportMonth;
    }

    public void setReportMonth(String reportMonth) {
        this.reportMonth = reportMonth;
    }

    public boolean isArvReceived() {
        return arvReceived;
    }

    public void setArvReceived(boolean arvReceived) {
        this.arvReceived = arvReceived;
    }

    public String getPlaceArvReceived() {
        return placeArvReceived;
    }

    public void setPlaceArvReceived(String placeArvReceived) {
        this.placeArvReceived = placeArvReceived;
    }

    public Date getDateArvReceived() {
        return dateArvReceived;
    }

    public void setDateArvReceived(Date dateArvReceived) {
        this.dateArvReceived = dateArvReceived;
    }

    public boolean isMultipleBirth() {
		return multipleBirth;
	}

	public void setMultipleBirth(boolean multipleBirth) {
		this.multipleBirth = multipleBirth;
	}

	public String getBirthType() {
        return birthType;
    }

    public void setBirthType(String birthType) {
        this.birthType = birthType;
    }

    public String getBabyRegimenDelivery() {
        return babyRegimenDelivery;
    }

    public void setBabyRegimenDelivery(String babyRegimenDelivery) {
        this.babyRegimenDelivery = babyRegimenDelivery;
    }

    public boolean isBabyDoseGiven() {
        return babyDoseGiven;
    }

    public void setBabyDoseGiven(boolean babyDoseGiven) {
        this.babyDoseGiven = babyDoseGiven;
    }

    public String getDeliveryNurse() {
        return deliveryNurse;
    }

    public void setDeliveryNurse(String deliveryNurse) {
        this.deliveryNurse = deliveryNurse;
    }

    public Date getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(Date dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    public Integer getHivPositive() {
		return hivPositive;
	}

	public void setHivPositive(Integer hivPositive) {
		this.hivPositive = hivPositive;
	}

	public String getRegimenMother() {
		return regimenMother;
	}

	public void setRegimenMother(String regimenMother) {
		this.regimenMother = regimenMother;
	}

	/**
     * Set patient demographics, SafeMotherhood care, and children
     * @param patientID
     * @param beginDate
     * @param endDate
     * @param siteID
     * @param conn
     */
    public void loadPatient(int patientID, Date beginDate, Date endDate, int siteID, Connection conn) {

        this.setPatientId((long) patientID);
        patientId = (long) patientID;
        // pregnancyId already set in super
        Long pregnancyId = this.getCurrentPregnancyId();

        try {
            getDemographics(conn, patientID, this);
        } catch (SQLException e) {
            log.error(e);
        }

        List postnatalInfantVisits = null;
        List children = null;
        try {
        	children = PatientDAO.getChildren(conn, patientId, pregnancyId);
        	if (children.size()>1) {
        		this.setMultipleBirth(true);
        	}
        	for (Iterator iterator = children.iterator(); iterator.hasNext();) {
        		Patient child = (Patient) iterator.next();
        		Long newbornId = child.getId();
        		NewbornEvalReport newbornEval;
        		try {
        			newbornEval = (NewbornEvalReport) EncountersDAO.getOneReports(conn, newbornId, pregnancyId, new Long(23));
        			if (newbornEval.getBaby_received_arvR() != null && newbornEval.getBaby_received_arvR().equals("Yes")) {
        				this.setBabyDoseGiven(true);
        			}
        			if (newbornEval.getRbd_home_regimenR() != null) {
        				this.setBabyRegimenDelivery(newbornEval.getRbd_home_regimenR());
        			}
        		} catch (ObjectNotFoundException e) {
        		}
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

    /**
     *
     * @param conn
     * @param patientID
     * @param safeMother
     * @throws SQLException
     */
    private void getDemographics(Connection conn, int patientID, PmtctLabourWardPatient safeMother) throws SQLException {
        String sql;
        PreparedStatement ps;
        ResultSet rs = null;
        ///////////////////////////////////////////////////////////////////////
        // Name, Address, Age
        ///////////////////////////////////////////////////////////////////////

        sql = "SELECT forenames_7, surname_6, residential_19, residential_20, birth_date_17, patient_id_number, hiv_positive, " +
                "marital_stat_10 " +
                "FROM encounter " +
                "JOIN (patientregistration) ON encounter.id = patientregistration.id " +
                "JOIN (patient) ON encounter.patient_id = patient.id " +
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
            Integer hivPositive = rs.getInt("hiv_positive");
            Integer maritalStatusInt = null;
            try {
                maritalStatusInt = rs.getInt("marital_stat_10");
            } catch (SQLException e) {
                log.error(e);
            }
            if (maritalStatusInt != 0) {
                try {
                    String maritalStatus = PatientRecordUtils.resolveEnum(maritalStatusInt, 0);
                    safeMother.setMaritalStatus(maritalStatus);
                } catch (Exception e) {
                    log.error(e);
                }
            }
            safeMother.setName(name);
            safeMother.setAddress1(address1);
            safeMother.setAddress2(address2);
            safeMother.setSmRegisterNumber(smNumber);
            safeMother.setHivPositive(hivPositive);
        }
    }
}
