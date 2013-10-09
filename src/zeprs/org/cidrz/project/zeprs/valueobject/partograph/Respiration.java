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
 * @hibernate.class table="parto_respiration"
 * mutable="true"
 */

public class Respiration implements BaseRecord, Identifiable, org.cidrz.webapp.dynasite.valueobject.Auditable {

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
    private Float respiration1;
    private Time timeObservation2;
    private Float respiration2;
    private Time timeObservation3;
    private Float respiration3;
    private Time timeObservation4;
    private Float respiration4;
    private Time timeObservation5;
    private Float respiration5;
    private Time timeObservation6;
    private Float respiration6;
    private Time timeObservation7;
    private Float respiration7;
    private Time timeObservation8;
    private Float respiration8;
    private Time timeObservation9;
    private Float respiration9;
    private Time timeObservation10;
    private Float respiration10;

    private Time timeObservation11;
    private Float respiration11;
    private Time timeObservation12;
    private Float respiration12;
    private Time timeObservation13;
    private Float respiration13;
    private Time timeObservation14;
    private Float respiration14;
    private Time timeObservation15;
    private Float respiration15;
    private Time timeObservation16;
    private Float respiration16;
    private Time timeObservation17;
    private Float respiration17;
    private Time timeObservation18;
    private Float respiration18;
    private Time timeObservation19;
    private Float respiration19;
    private Time timeObservation20;
    private Float respiration20;

    private Time timeObservation21;
    private Float respiration21;
    private Time timeObservation22;
    private Float respiration22;
    private Time timeObservation23;
    private Float respiration23;
    private Time timeObservation24;
    private Float respiration24;


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
     * @hibernate.property column="respiration_1"
     */
    public Float getRespiration1() {
        return respiration1;
    }

    public void setRespiration1(Float respiration1) {
        this.respiration1 = respiration1;
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
     * @hibernate.property column="respiration_2"
     */

    public Float getRespiration2() {
        return respiration2;
    }

    public void setRespiration2(Float respiration2) {
        this.respiration2 = respiration2;
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
     * @hibernate.property column="respiration_3"
     */

    public Float getRespiration3() {
        return respiration3;
    }

    public void setRespiration3(Float respiration3) {
        this.respiration3 = respiration3;
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
     * @hibernate.property column="respiration_4"
     */

    public Float getRespiration4() {
        return respiration4;
    }

    public void setRespiration4(Float respiration4) {
        this.respiration4 = respiration4;
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
     * @hibernate.property column="respiration_5"
     */

    public Float getRespiration5() {
        return respiration5;
    }

    public void setRespiration5(Float respiration5) {
        this.respiration5 = respiration5;
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
     * @hibernate.property column="respiration_6"
     */

    public Float getRespiration6() {
        return respiration6;
    }

    public void setRespiration6(Float respiration6) {
        this.respiration6 = respiration6;
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
     * @hibernate.property column="respiration_7"
     */

    public Float getRespiration7() {
        return respiration7;
    }

    public void setRespiration7(Float respiration7) {
        this.respiration7 = respiration7;
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
     * @hibernate.property column="respiration_8"
     */

    public Float getRespiration8() {
        return respiration8;
    }

    public void setRespiration8(Float respiration8) {
        this.respiration8 = respiration8;
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
     * @hibernate.property column="respiration_9"
     */

    public Float getRespiration9() {
        return respiration9;
    }

    public void setRespiration9(Float respiration9) {
        this.respiration9 = respiration9;
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
     * @hibernate.property column="respiration_10"
     */

    public Float getRespiration10() {
        return respiration10;
    }

    public void setRespiration10(Float respiration10) {
        this.respiration10 = respiration10;
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
     * @hibernate.property column="respiration_11"
     */

    public Float getRespiration11() {
        return respiration11;
    }

    public void setRespiration11(Float respiration11) {
        this.respiration11 = respiration11;
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
     * @hibernate.property column="respiration_12"
     */

    public Float getRespiration12() {
        return respiration12;
    }

    public void setRespiration12(Float respiration12) {
        this.respiration12 = respiration12;
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
     * @hibernate.property column="respiration_13"
     */

    public Float getRespiration13() {
        return respiration13;
    }

    public void setRespiration13(Float respiration13) {
        this.respiration13 = respiration13;
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
     * @hibernate.property column="respiration_14"
     */

    public Float getRespiration14() {
        return respiration14;
    }

    public void setRespiration14(Float respiration14) {
        this.respiration14 = respiration14;
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
     * @hibernate.property column="respiration_15"
     */

    public Float getRespiration15() {
        return respiration15;
    }

    public void setRespiration15(Float respiration15) {
        this.respiration15 = respiration15;
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
     * @hibernate.property column="respiration_16"
     */

    public Float getRespiration16() {
        return respiration16;
    }

    public void setRespiration16(Float respiration16) {
        this.respiration16 = respiration16;
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
     * @hibernate.property column="respiration_17"
     */

    public Float getRespiration17() {
        return respiration17;
    }

    public void setRespiration17(Float respiration17) {
        this.respiration17 = respiration17;
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
     * @hibernate.property column="respiration_18"
     */
    public Float getRespiration18() {
        return respiration18;
    }

    public void setRespiration18(Float respiration18) {
        this.respiration18 = respiration18;
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
     * @hibernate.property column="respiration_19"
     */
    public Float getRespiration19() {
        return respiration19;
    }

    public void setRespiration19(Float respiration19) {
        this.respiration19 = respiration19;
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
     * @hibernate.property column="respiration_20"
     */
    public Float getRespiration20() {
        return respiration20;
    }

    public void setRespiration20(Float respiration20) {
        this.respiration20 = respiration20;
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
     * @hibernate.property column="respiration_21"
     */
    public Float getRespiration21() {
        return respiration21;
    }

    public void setRespiration21(Float respiration21) {
        this.respiration21 = respiration21;
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
     * @hibernate.property column="respiration_22"
     */
    public Float getRespiration22() {
        return respiration22;
    }

    public void setRespiration22(Float respiration22) {
        this.respiration22 = respiration22;
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
     * @hibernate.property column="respiration_23"
     */
    public Float getRespiration23() {
        return respiration23;
    }

    public void setRespiration23(Float respiration23) {
        this.respiration23 = respiration23;
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
     * @hibernate.property column="respiration_24"
     */
    public Float getRespiration24() {
        return respiration24;
    }

    public void setRespiration24(Float respiration24) {
        this.respiration24 = respiration24;
    }

}
