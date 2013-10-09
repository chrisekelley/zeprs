/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.project.zeprs.report;

import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Apr 17, 2007
 * Time: 11:50:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class ReflexPatient {

    private Long id;
    private String districtPatientId;
    private String patientName;
    private Date encounterDate;
    private Boolean cd4Done;
    private Date cd4Date;
    private Integer cd4Result;
    private Date hgbDate;
    private Float hgbResult;
    private Date regimenVisitDate;
    private String whoScreen;
    private String referralToArt;
    private String pmtctRegimen;
    private String egaWeeks;
    private Date dateNextVisit;
    private Integer siteId;
    private Integer encounterId;
    private String labType;
    private Date hivPositiveTestDate;
    private String phone;
    private String address;
    private String contact;
    private String contactPhone;
    private String artClinic;
    private String enrolled;
    private String referralToArtFirstVisit;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistrictPatientId() {
        return districtPatientId;
    }

    public void setDistrictPatientId(String districtPatientId) {
        this.districtPatientId = districtPatientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Date getEncounterDate() {
        return encounterDate;
    }

    public void setEncounterDate(Date encounterDate) {
        this.encounterDate = encounterDate;
    }

    public Boolean getCd4Done() {
        return cd4Done;
    }

    public void setCd4Done(Boolean cd4Done) {
        this.cd4Done = cd4Done;
    }

    public Date getCd4Date() {
        return cd4Date;
    }

    public void setCd4Date(Date cd4Date) {
        this.cd4Date = cd4Date;
    }

    public Integer getCd4Result() {
        return cd4Result;
    }

    public void setCd4Result(Integer cd4Result) {
        this.cd4Result = cd4Result;
    }

    public Date getHgbDate() {
        return hgbDate;
    }

    public void setHgbDate(Date hgbDate) {
        this.hgbDate = hgbDate;
    }

    public Float getHgbResult() {
        return hgbResult;
    }

    public void setHgbResult(Float hgbResult) {
        this.hgbResult = hgbResult;
    }

    public Date getRegimenVisitDate() {
        return regimenVisitDate;
    }

    public void setRegimenVisitDate(Date regimenVisitDate) {
        this.regimenVisitDate = regimenVisitDate;
    }

    public String getWhoScreen() {
        return whoScreen;
    }

    public void setWhoScreen(String whoScreen) {
        this.whoScreen = whoScreen;
    }

    public String getReferralToArt() {
        return referralToArt;
    }

    public void setReferralToArt(String referralToArt) {
        this.referralToArt = referralToArt;
    }

    public String getPmtctRegimen() {
        return pmtctRegimen;
    }

    public void setPmtctRegimen(String pmtctRegimen) {
        this.pmtctRegimen = pmtctRegimen;
    }

    public String getEgaWeeks() {
        return egaWeeks;
    }

    public void setEgaWeeks(String egaWeeks) {
        this.egaWeeks = egaWeeks;
    }

    public Date getDateNextVisit() {
        return dateNextVisit;
    }

    public void setDateNextVisit(Date dateNextVisit) {
        this.dateNextVisit = dateNextVisit;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(Integer encounterId) {
        this.encounterId = encounterId;
    }

    public String getLabType() {
        return labType;
    }

    public void setLabType(String labType) {
        this.labType = labType;
    }

    public Date getHivPositiveTestDate() {
        return hivPositiveTestDate;
    }

    public void setHivPositiveTestDate(Date hivPositiveTestDate) {
        this.hivPositiveTestDate = hivPositiveTestDate;
    }

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getArtClinic() {
		return artClinic;
	}

	public void setArtClinic(String artClinic) {
		this.artClinic = artClinic;
	}

	public String getEnrolled() {
		return enrolled;
	}

	public void setEnrolled(String enrolled) {
		this.enrolled = enrolled;
	}

	/**
	 * @return the referralToArtFirstVisit
	 */
	public String getReferralToArtFirstVisit() {
		return referralToArtFirstVisit;
	}

	/**
	 * @param referralToArtFirstVisit the referralToArtFirstVisit to set
	 */
	public void setReferralToArtFirstVisit(String referralToArtFirstVisit) {
		this.referralToArtFirstVisit = referralToArtFirstVisit;
	}



}
