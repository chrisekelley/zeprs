/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.project.zeprs.report.valueobject;

import java.sql.Date;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Dec 6, 2005
 *         Time: 3:20:28 PM
 */
public class CurrentPregnancyStatus {
    private Integer height;
    private String aboGroup;
    private String rhesus;  // enum id 2924 from labTest field1845
    private Date lmp;
    private Date edd;
    private Long ega;    // days - calculated to today's date
    private Date dateEga;   // date ega entered into record.
    private Long egaDb;     // ega from database
    private String egaWeeks;
    private Integer gravida;    // function to loop through prev pregnancies and calculate gravida
    private Integer parity;
    private Integer datingMethod;
    private Integer quickening;	//uterus_size_in_days_188

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getAboGroup() {
        return aboGroup;
    }

    public void setAboGroup(String aboGroup) {
        this.aboGroup = aboGroup;
    }

    public String getRhesus() {
        return rhesus;
    }

    public void setRhesus(String rhesus) {
        this.rhesus = rhesus;
    }

    public Date getLmp() {
        return lmp;
    }

    public void setLmp(Date lmp) {
        this.lmp = lmp;
    }

    public Date getEdd() {
        return edd;
    }

    public void setEdd(Date edd) {
        this.edd = edd;
    }

    public Long getEga() {
        return ega;
    }

    public void setEga(Long ega) {
        this.ega = ega;
    }

    public Long getEgaDb() {
        return egaDb;
    }

    public void setEgaDb(Long egaDb) {
        this.egaDb = egaDb;
    }

    public Date getDateEga() {
        return dateEga;
    }

    public void setDateEga(Date dateEga) {
        this.dateEga = dateEga;
    }

    public String getEgaWeeks() {
        return egaWeeks;
    }

    public void setEgaWeeks(String egaWeeks) {
        this.egaWeeks = egaWeeks;
    }

    public Integer getGravida() {
        return gravida;
    }

    public void setGravida(Integer gravida) {
        this.gravida = gravida;
    }

    public Integer getParity() {
        return parity;
    }

    public void setParity(Integer parity) {
        this.parity = parity;
    }

    public Integer getDatingMethod() {
        return datingMethod;
    }

    public void setDatingMethod(Integer datingMethod) {
        this.datingMethod = datingMethod;
    }

	public Integer getQuickening() {
		return quickening;
	}

	public void setQuickening(Integer quickening) {
		this.quickening = quickening;
	}
}
