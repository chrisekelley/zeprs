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

import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.project.zeprs.valueobject.report.BaseReport;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.valueobject.Patient;

import java.sql.Date;


/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Jan 27.2005
 * Time: 2:24:37 PM
 */

/**
 * @hibernate.class table="chart"
 * mutable="true"
 */

public class ChartReport implements Identifiable, BaseReport {
    private Long id;
    private EncounterData encounter;
    private Patient patient;
    private Date lmpDate;
    private Integer egaDays;
    private Integer fundalHeight;
    private Integer aceEnum;
    private String aceValue;
    private Integer aceOrder;
    private Integer albEnum;
    private String albValue;
    private Integer albOrder;
    private Integer gluEnum;
    private String gluValue;
    private Integer gluOrder;
    private Double weight;
    private Integer hb;
    private String bpDiastolicEnum;
    private String bpDiastolicValue;
    private Integer bpDiastolicOrder;
    private String bpSystolicEnum;
    private String bpSystolicValue;
    private Integer bpSystolicOrder;
    private Integer oedemaEnum;
    private String oedemaValue;
    private Integer oedemaOrder;

    /**
     * @return
     * @hibernate.id unsaved-value="0"
     * generator-class="identity"
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    /**
     * @return
     * @hibernate.many-to-one column="encounter_id"
     * cascade="none"
     */

    public EncounterData getEncounter() {
        return encounter;
    }

    public void setEncounter(EncounterData encounter) {
        this.encounter = encounter;
    }

    /**
     * @return
     * @hibernate.many-to-one column="patient_id"
     * cascade="none"
     */

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * @return
     * @hibernate.property column="lmp_date"
     * not-null="false"
     */

    public Date getLmpDate() {
        return lmpDate;
    }

    public void setLmpDate(Date lmpDate) {
        this.lmpDate = lmpDate;
    }

    /**
     * @return
     * @hibernate.property column="ega_days"
     * not-null="false"
     */

    public Integer getEgaDays() {
        return egaDays;
    }

    public void setEgaDays(Integer egaDays) {
        this.egaDays = egaDays;
    }


    /**
     * @return
     * @hibernate.property column="fundal_height"
     * not-null="false"
     */

    public Integer getFundalHeight() {
        return fundalHeight;
    }

    public void setFundalHeight(Integer fundalHeight) {
        this.fundalHeight = fundalHeight;
    }

    /**
     * @return
     * @hibernate.property column="ace_enum_id"
     * not-null="false"
     */
    public Integer getAceEnum() {
        return aceEnum;
    }

    public void setAceEnum(Integer aceEnum) {
        this.aceEnum = aceEnum;
    }

    /**
     * @return
     * @hibernate.property column="ace_value"
     * not-null="false"
     */
    public String getAceValue() {
        return aceValue;
    }

    public void setAceValue(String aceValue) {
        this.aceValue = aceValue;
    }

    /**
     * @return
     * @hibernate.property column="ace_order"
     * not-null="false"
     */
    public Integer getAceOrder() {
        return aceOrder;
    }

    public void setAceOrder(Integer aceOrder) {
        this.aceOrder = aceOrder;
    }

    /**
     * @return
     * @hibernate.property column="alb_enunm_id"
     * not-null="false"
     */
    public Integer getAlbEnum() {
        return albEnum;
    }

    public void setAlbEnum(Integer albEnum) {
        this.albEnum = albEnum;
    }

    /**
     * @return
     * @hibernate.property column="alb_value"
     * not-null="false"
     */
    public String getAlbValue() {
        return albValue;
    }

    public void setAlbValue(String albValue) {
        this.albValue = albValue;
    }

    /**
     * @return
     * @hibernate.property column="alb_order"
     * not-null="false"
     */
    public Integer getAlbOrder() {
        return albOrder;
    }

    public void setAlbOrder(Integer albOrder) {
        this.albOrder = albOrder;
    }

    /**
     * @return
     * @hibernate.property column="glu_enum_id"
     * not-null="false"
     */
    public Integer getGluEnum() {
        return gluEnum;
    }

    public void setGluEnum(Integer gluEnum) {
        this.gluEnum = gluEnum;
    }

    /**
     * @return
     * @hibernate.property column="glu_value"
     * not-null="false"
     */

    public String getGluValue() {
        return gluValue;
    }

    public void setGluValue(String gluValue) {
        this.gluValue = gluValue;
    }

    /**
     * @return
     * @hibernate.property column="glu_order"
     * not-null="false"
     */
    public Integer getGluOrder() {
        return gluOrder;
    }

    public void setGluOrder(Integer gluOrder) {
        this.gluOrder = gluOrder;
    }

    /**
     * @return
     * @hibernate.property column="weight"
     * not-null="false"
     */
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    /**
     * @return
     * @hibernate.property column="hb"
     * not-null="false"
     */
    public Integer getHb() {
        return hb;
    }

    public void setHb(Integer hb) {
        this.hb = hb;
    }

    /**
     * @return
     * @hibernate.property column="bp_diastolic_enum_id"
     * not-null="false"
     */
    public String getBpDiastolicEnum() {
        return bpDiastolicEnum;
    }

    public void setBpDiastolicEnum(String bpDiastolicEnum) {
        this.bpDiastolicEnum = bpDiastolicEnum;
    }

    /**
     * @return
     * @hibernate.property column="bp_diastolic_value"
     * not-null="false"
     */
    public String getBpDiastolicValue() {
        return bpDiastolicValue;
    }

    public void setBpDiastolicValue(String bpDiastolicValue) {
        this.bpDiastolicValue = bpDiastolicValue;
    }

    /**
     * @return
     * @hibernate.property column="bp_diastolic_order"
     * not-null="false"
     */
    public Integer getBpDiastolicOrder() {
        return bpDiastolicOrder;
    }

    public void setBpDiastolicOrder(Integer bpDiastolicOrder) {
        this.bpDiastolicOrder = bpDiastolicOrder;
    }

    /**
     * @return
     * @hibernate.property column="bp_systolic_enum_id"
     * not-null="false"
     */
    public String getBpSystolicEnum() {
        return bpSystolicEnum;
    }

    public void setBpSystolicEnum(String bpSystolicEnum) {
        this.bpSystolicEnum = bpSystolicEnum;
    }

    /**
     * @return
     * @hibernate.property column="bp_systolic_value"
     * not-null="false"
     */
    public String getBpSystolicValue() {
        return bpSystolicValue;
    }

    public void setBpSystolicValue(String bpSystolicValue) {
        this.bpSystolicValue = bpSystolicValue;
    }

    /**
     * @return
     * @hibernate.property column="bp_systolic_order"
     * not-null="false"
     */
    public Integer getBpSystolicOrder() {
        return bpSystolicOrder;
    }

    public void setBpSystolicOrder(Integer bpSystolicOrder) {
        this.bpSystolicOrder = bpSystolicOrder;
    }

    /**
     * @return
     * @hibernate.property column="oedema_enum_id"
     * not-null="false"
     */
    public Integer getOedemaEnum() {
        return oedemaEnum;
    }

    public void setOedemaEnum(Integer oedemaEnum) {
        this.oedemaEnum = oedemaEnum;
    }

    /**
     * @return
     * @hibernate.property column="oedema_value"
     * not-null="false"
     */
    public String getOedemaValue() {
        return oedemaValue;
    }

    public void setOedemaValue(String oedemaValue) {
        this.oedemaValue = oedemaValue;
    }

    /**
     * @return
     * @hibernate.property column="oedema_order"
     * not-null="false"
     */
    public Integer getOedemaOrder() {
        return oedemaOrder;
    }

    public void setOedemaOrder(Integer oedemaOrder) {
        this.oedemaOrder = oedemaOrder;
    }
}
