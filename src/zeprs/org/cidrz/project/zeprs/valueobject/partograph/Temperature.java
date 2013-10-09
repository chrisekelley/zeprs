/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.project.zeprs.valueobject.partograph;

import org.cidrz.project.zeprs.valueobject.BaseRecord;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Mar 9, 2005
 * Time: 11:12:34 AM
 */

/**
 * @hibernate.class table="parto_temperature"
 * mutable="true"
 */

public class Temperature implements BaseRecord, Identifiable, org.cidrz.webapp.dynasite.valueobject.Auditable {

    private Long id;
    private Long patientId;
    private Long pregnancyId;
    private Date dateVisit;
    private org.cidrz.webapp.dynasite.valueobject.AuditInfo auditInfo;
    private Timestamp created;
    private String createdBy;
    private Timestamp lastModified;
    private String lastModifiedBy;
    private Long siteId;
    private Long importId;
    private Time timeObservation1;
    private Float temperature1;
    private Time timeObservation2;
    private Float temperature2;
    private Time timeObservation3;
    private Float temperature3;
    private Time timeObservation4;
    private Float temperature4;
    private Time timeObservation5;
    private Float temperature5;
    private Time timeObservation6;
    private Float temperature6;
    private Time timeObservation7;
    private Float temperature7;
    private Time timeObservation8;
    private Float temperature8;
    private Time timeObservation9;
    private Float temperature9;
    private Time timeObservation10;
    private Float temperature10;

    private Time timeObservation11;
    private Float temperature11;
    private Time timeObservation12;
    private Float temperature12;
    private Time timeObservation13;
    private Float temperature13;
    private Time timeObservation14;
    private Float temperature14;
    private Time timeObservation15;
    private Float temperature15;
    private Time timeObservation16;
    private Float temperature16;
    private Time timeObservation17;
    private Float temperature17;
    private Time timeObservation18;
    private Float temperature18;
    private Time timeObservation19;
    private Float temperature19;
    private Time timeObservation20;
    private Float temperature20;
    private Time timeObservation21;
    private Float temperature21;
    private Time timeObservation22;
    private Float temperature22;
    private Time timeObservation23;
    private Float temperature23;
    private Time timeObservation24;
    private Float temperature24;


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
     * @hibernate.property column="patient_id"
     */
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    /**
     * @return
     * @hibernate.property column="pregnancy_id"
     */

    public Long getPregnancyId() {
        return pregnancyId;
    }

    public void setPregnancyId(Long pregnancyId) {
        this.pregnancyId = pregnancyId;
    }

    /**
     * @return
     * @hibernate.property column="date_visit"
     * not-null="true"
     */

    public Date getDateVisit() {
        return dateVisit;
    }

    public void setDateVisit(Date dateVisit) {
        this.dateVisit = dateVisit;
    }

    /**
     * formerly orm mapping
     */
    public org.cidrz.webapp.dynasite.valueobject.AuditInfo getAuditInfo() {
        if (auditInfo == null) {
            auditInfo = new org.cidrz.webapp.dynasite.valueobject.AuditInfo();
        }
        return auditInfo;
    }

    public void setAuditInfo(org.cidrz.webapp.dynasite.valueobject.AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }



    /**
     * @return
     * @hibernate.property column="created"
     */
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    /**
     * @return
     * @hibernate.property column="created_by"
     */
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return
     * @hibernate.property column="last_modified"
     */
    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * @return
     * @hibernate.property column="last_modified_by"
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * @return
     * @hibernate.property column="site_id"
     */
    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    /**
     * @return
     * @hibernate.property column="import_id"
     */
    public Long getImportId() {
        return importId;
    }

    public void setImportId(Long importId) {
        this.importId = importId;
    }


    /**
     * @return
     * @hibernate.property column="time_1"
     */
    public Time getTimeObservation1() {
        return timeObservation1;
    }

    public void setTimeObservation1(Time timeObservation1) {
        this.timeObservation1 = timeObservation1;
    }

    /**
     * @return
     * @hibernate.property column="temperature_1"
     */
    public Float getTemperature1() {
        return temperature1;
    }

    public void setTemperature1(Float temperature1) {
        this.temperature1 = temperature1;
    }

    /**
     * @return
     * @hibernate.property column="time_2"
     */

    public Time getTimeObservation2() {
        return timeObservation2;
    }

    public void setTimeObservation2(Time timeObservation2) {
        this.timeObservation2 = timeObservation2;
    }

    /**
     * @return
     * @hibernate.property column="temperature_2"
     */

    public Float getTemperature2() {
        return temperature2;
    }

    public void setTemperature2(Float temperature2) {
        this.temperature2 = temperature2;
    }


    /**
     * @return
     * @hibernate.property column="time_3"
     */

    public Time getTimeObservation3() {
        return timeObservation3;
    }

    public void setTimeObservation3(Time timeObservation3) {
        this.timeObservation3 = timeObservation3;
    }

    /**
     * @return
     * @hibernate.property column="temperature_3"
     */

    public Float getTemperature3() {
        return temperature3;
    }

    public void setTemperature3(Float temperature3) {
        this.temperature3 = temperature3;
    }


    /**
     * @return
     * @hibernate.property column="time_4"
     */

    public Time getTimeObservation4() {
        return timeObservation4;
    }

    public void setTimeObservation4(Time timeObservation4) {
        this.timeObservation4 = timeObservation4;
    }

    /**
     * @return
     * @hibernate.property column="temperature_4"
     */

    public Float getTemperature4() {
        return temperature4;
    }

    public void setTemperature4(Float temperature4) {
        this.temperature4 = temperature4;
    }


    /**
     * @return
     * @hibernate.property column="time_5"
     */

    public Time getTimeObservation5() {
        return timeObservation5;
    }

    public void setTimeObservation5(Time timeObservation5) {
        this.timeObservation5 = timeObservation5;
    }

    /**
     * @return
     * @hibernate.property column="temperature_5"
     */

    public Float getTemperature5() {
        return temperature5;
    }

    public void setTemperature5(Float temperature5) {
        this.temperature5 = temperature5;
    }


    /**
     * @return
     * @hibernate.property column="time_6"
     */

    public Time getTimeObservation6() {
        return timeObservation6;
    }

    public void setTimeObservation6(Time timeObservation6) {
        this.timeObservation6 = timeObservation6;
    }

    /**
     * @return
     * @hibernate.property column="temperature_6"
     */

    public Float getTemperature6() {
        return temperature6;
    }

    public void setTemperature6(Float temperature6) {
        this.temperature6 = temperature6;
    }


    /**
     * @return
     * @hibernate.property column="time_7"
     */

    public Time getTimeObservation7() {
        return timeObservation7;
    }

    public void setTimeObservation7(Time timeObservation7) {
        this.timeObservation7 = timeObservation7;
    }

    /**
     * @return
     * @hibernate.property column="temperature_7"
     */

    public Float getTemperature7() {
        return temperature7;
    }

    public void setTemperature7(Float temperature7) {
        this.temperature7 = temperature7;
    }


    /**
     * @return
     * @hibernate.property column="time_8"
     */

    public Time getTimeObservation8() {
        return timeObservation8;
    }

    public void setTimeObservation8(Time timeObservation8) {
        this.timeObservation8 = timeObservation8;
    }

    /**
     * @return
     * @hibernate.property column="temperature_8"
     */

    public Float getTemperature8() {
        return temperature8;
    }

    public void setTemperature8(Float temperature8) {
        this.temperature8 = temperature8;
    }


    /**
     * @return
     * @hibernate.property column="time_9"
     */

    public Time getTimeObservation9() {
        return timeObservation9;
    }

    public void setTimeObservation9(Time timeObservation9) {
        this.timeObservation9 = timeObservation9;
    }

    /**
     * @return
     * @hibernate.property column="temperature_9"
     */

    public Float getTemperature9() {
        return temperature9;
    }

    public void setTemperature9(Float temperature9) {
        this.temperature9 = temperature9;
    }


    /**
     * @return
     * @hibernate.property column="time_10"
     */

    public Time getTimeObservation10() {
        return timeObservation10;
    }

    public void setTimeObservation10(Time timeObservation10) {
        this.timeObservation10 = timeObservation10;
    }

    /**
     * @return
     * @hibernate.property column="temperature_10"
     */

    public Float getTemperature10() {
        return temperature10;
    }

    public void setTemperature10(Float temperature10) {
        this.temperature10 = temperature10;
    }


    /**
     * @return
     * @hibernate.property column="time_11"
     */

    public Time getTimeObservation11() {
        return timeObservation11;
    }

    public void setTimeObservation11(Time timeObservation11) {
        this.timeObservation11 = timeObservation11;
    }

    /**
     * @return
     * @hibernate.property column="temperature_11"
     */

    public Float getTemperature11() {
        return temperature11;
    }

    public void setTemperature11(Float temperature11) {
        this.temperature11 = temperature11;
    }


    /**
     * @return
     * @hibernate.property column="time_12"
     */

    public Time getTimeObservation12() {
        return timeObservation12;
    }

    public void setTimeObservation12(Time timeObservation12) {
        this.timeObservation12 = timeObservation12;
    }

    /**
     * @return
     * @hibernate.property column="temperature_12"
     */

    public Float getTemperature12() {
        return temperature12;
    }

    public void setTemperature12(Float temperature12) {
        this.temperature12 = temperature12;
    }


    /**
     * @return
     * @hibernate.property column="time_13"
     */

    public Time getTimeObservation13() {
        return timeObservation13;
    }

    public void setTimeObservation13(Time timeObservation13) {
        this.timeObservation13 = timeObservation13;
    }

    /**
     * @return
     * @hibernate.property column="temperature_13"
     */

    public Float getTemperature13() {
        return temperature13;
    }

    public void setTemperature13(Float temperature13) {
        this.temperature13 = temperature13;
    }


    /**
     * @return
     * @hibernate.property column="time_14"
     */

    public Time getTimeObservation14() {
        return timeObservation14;
    }

    public void setTimeObservation14(Time timeObservation14) {
        this.timeObservation14 = timeObservation14;
    }

    /**
     * @return
     * @hibernate.property column="temperature_14"
     */

    public Float getTemperature14() {
        return temperature14;
    }

    public void setTemperature14(Float temperature14) {
        this.temperature14 = temperature14;
    }


    /**
     * @return
     * @hibernate.property column="time_15"
     */

    public Time getTimeObservation15() {
        return timeObservation15;
    }

    public void setTimeObservation15(Time timeObservation15) {
        this.timeObservation15 = timeObservation15;
    }

    /**
     * @return
     * @hibernate.property column="temperature_15"
     */

    public Float getTemperature15() {
        return temperature15;
    }

    public void setTemperature15(Float temperature15) {
        this.temperature15 = temperature15;
    }


    /**
     * @return
     * @hibernate.property column="time_16"
     */

    public Time getTimeObservation16() {
        return timeObservation16;
    }

    public void setTimeObservation16(Time timeObservation16) {
        this.timeObservation16 = timeObservation16;
    }

    /**
     * @return
     * @hibernate.property column="temperature_16"
     */

    public Float getTemperature16() {
        return temperature16;
    }

    public void setTemperature16(Float temperature16) {
        this.temperature16 = temperature16;
    }


    /**
     * @return
     * @hibernate.property column="time_17"
     */

    public Time getTimeObservation17() {
        return timeObservation17;
    }

    public void setTimeObservation17(Time timeObservation17) {
        this.timeObservation17 = timeObservation17;
    }

    /**
     * @return
     * @hibernate.property column="temperature_17"
     */

    public Float getTemperature17() {
        return temperature17;
    }

    public void setTemperature17(Float temperature17) {
        this.temperature17 = temperature17;
    }

     /**
     * @return
     * @hibernate.property column="time_18"
     */
    public Time getTimeObservation18() {
        return timeObservation18;
    }

    public void setTimeObservation18(Time timeObservation18) {
        this.timeObservation18 = timeObservation18;
    }

     /**
     * @return
     * @hibernate.property column="temperature_18"
     */
    public Float getTemperature18() {
        return temperature18;
    }

    public void setTemperature18(Float temperature18) {
        this.temperature18 = temperature18;
    }

     /**
     * @return
     * @hibernate.property column="time_19"
     */
    public Time getTimeObservation19() {
        return timeObservation19;
    }

    public void setTimeObservation19(Time timeObservation19) {
        this.timeObservation19 = timeObservation19;
    }

     /**
     * @return
     * @hibernate.property column="temperature_19"
     */
    public Float getTemperature19() {
        return temperature19;
    }

    public void setTemperature19(Float temperature19) {
        this.temperature19 = temperature19;
    }

     /**
     * @return
     * @hibernate.property column="time_20"
     */
    public Time getTimeObservation20() {
        return timeObservation20;
    }

    public void setTimeObservation20(Time timeObservation20) {
        this.timeObservation20 = timeObservation20;
    }

     /**
     * @return
     * @hibernate.property column="temperature_20"
     */
    public Float getTemperature20() {
        return temperature20;
    }

    public void setTemperature20(Float temperature20) {
        this.temperature20 = temperature20;
    }

     /**
     * @return
     * @hibernate.property column="time_21"
     */
    public Time getTimeObservation21() {
        return timeObservation21;
    }

    public void setTimeObservation21(Time timeObservation21) {
        this.timeObservation21 = timeObservation21;
    }

     /**
     * @return
     * @hibernate.property column="temperature_21"
     */
    public Float getTemperature21() {
        return temperature21;
    }

    public void setTemperature21(Float temperature21) {
        this.temperature21 = temperature21;
    }

     /**
     * @return
     * @hibernate.property column="time_22"
     */
    public Time getTimeObservation22() {
        return timeObservation22;
    }

    public void setTimeObservation22(Time timeObservation22) {
        this.timeObservation22 = timeObservation22;
    }

     /**
     * @return
     * @hibernate.property column="temperature_22"
     */
    public Float getTemperature22() {
        return temperature22;
    }

    public void setTemperature22(Float temperature22) {
        this.temperature22 = temperature22;
    }

     /**
     * @return
     * @hibernate.property column="time_23"
     */
    public Time getTimeObservation23() {
        return timeObservation23;
    }

    public void setTimeObservation23(Time timeObservation23) {
        this.timeObservation23 = timeObservation23;
    }

     /**
     * @return
     * @hibernate.property column="temperature_23"
     */
    public Float getTemperature23() {
        return temperature23;
    }

    public void setTemperature23(Float temperature23) {
        this.temperature23 = temperature23;
    }

     /**
     * @return
     * @hibernate.property column="time_24"
     */
    public Time getTimeObservation24() {
        return timeObservation24;
    }

    public void setTimeObservation24(Time timeObservation24) {
        this.timeObservation24 = timeObservation24;
    }

     /**
     * @return
     * @hibernate.property column="temperature_24"
     */
    public Float getTemperature24() {
        return temperature24;
    }

    public void setTemperature24(Float temperature24) {
        this.temperature24 = temperature24;
    }


}
