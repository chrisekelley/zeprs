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
 * @hibernate.class table="parto_urinalysis_glucose"
 * mutable="true"
 */

public class UrinalysisGlucose implements BaseRecord, Identifiable, org.cidrz.webapp.dynasite.valueobject.Auditable {

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
    private String urinalysisGlucose1;
    private Time timeObservation2;
    private String urinalysisGlucose2;
    private Time timeObservation3;
    private String urinalysisGlucose3;
    private Time timeObservation4;
    private String urinalysisGlucose4;
    private Time timeObservation5;
    private String urinalysisGlucose5;
    private Time timeObservation6;
    private String urinalysisGlucose6;
    private Time timeObservation7;
    private String urinalysisGlucose7;
    private Time timeObservation8;
    private String urinalysisGlucose8;
    private Time timeObservation9;
    private String urinalysisGlucose9;
    private Time timeObservation10;
    private String urinalysisGlucose10;

    private Time timeObservation11;
    private String urinalysisGlucose11;
    private Time timeObservation12;
    private String urinalysisGlucose12;
    private Time timeObservation13;
    private String urinalysisGlucose13;
    private Time timeObservation14;
    private String urinalysisGlucose14;
    private Time timeObservation15;
    private String urinalysisGlucose15;
    private Time timeObservation16;
    private String urinalysisGlucose16;
    private Time timeObservation17;
    private String urinalysisGlucose17;
    private Time timeObservation18;
    private String urinalysisGlucose18;
    private Time timeObservation19;
    private String urinalysisGlucose19;
    private Time timeObservation20;
    private String urinalysisGlucose20;

    private Time timeObservation21;
    private String urinalysisGlucose21;
    private Time timeObservation22;
    private String urinalysisGlucose22;
    private Time timeObservation23;
    private String urinalysisGlucose23;
    private Time timeObservation24;
    private String urinalysisGlucose24;

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
     * @hibernate.property length="4" column="urinalysisGlucose_1"
     */
    public String getUrinalysisGlucose1() {
        return urinalysisGlucose1;
    }

    public void setUrinalysisGlucose1(String urinalysisGlucose1) {
        this.urinalysisGlucose1 = urinalysisGlucose1;
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
     * @hibernate.property length="4" column="urinalysisGlucose_2"
     */

    public String getUrinalysisGlucose2() {
        return urinalysisGlucose2;
    }

    public void setUrinalysisGlucose2(String urinalysisGlucose2) {
        this.urinalysisGlucose2 = urinalysisGlucose2;
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
     * @hibernate.property length="4" column="urinalysisGlucose_3"
     */

    public String getUrinalysisGlucose3() {
        return urinalysisGlucose3;
    }

    public void setUrinalysisGlucose3(String urinalysisGlucose3) {
        this.urinalysisGlucose3 = urinalysisGlucose3;
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
     * @hibernate.property length="4" column="urinalysisGlucose_4"
     */

    public String getUrinalysisGlucose4() {
        return urinalysisGlucose4;
    }

    public void setUrinalysisGlucose4(String urinalysisGlucose4) {
        this.urinalysisGlucose4 = urinalysisGlucose4;
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
     * @hibernate.property length="4" column="urinalysisGlucose_5"
     */

    public String getUrinalysisGlucose5() {
        return urinalysisGlucose5;
    }

    public void setUrinalysisGlucose5(String urinalysisGlucose5) {
        this.urinalysisGlucose5 = urinalysisGlucose5;
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
     * @hibernate.property length="4" column="urinalysisGlucose_6"
     */

    public String getUrinalysisGlucose6() {
        return urinalysisGlucose6;
    }

    public void setUrinalysisGlucose6(String urinalysisGlucose6) {
        this.urinalysisGlucose6 = urinalysisGlucose6;
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
     * @hibernate.property length="4" column="urinalysisGlucose_7"
     */

    public String getUrinalysisGlucose7() {
        return urinalysisGlucose7;
    }

    public void setUrinalysisGlucose7(String urinalysisGlucose7) {
        this.urinalysisGlucose7 = urinalysisGlucose7;
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
     * @hibernate.property length="4" column="urinalysisGlucose_8"
     */

    public String getUrinalysisGlucose8() {
        return urinalysisGlucose8;
    }

    public void setUrinalysisGlucose8(String urinalysisGlucose8) {
        this.urinalysisGlucose8 = urinalysisGlucose8;
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
     * @hibernate.property length="4" column="urinalysisGlucose_9"
     */

    public String getUrinalysisGlucose9() {
        return urinalysisGlucose9;
    }

    public void setUrinalysisGlucose9(String urinalysisGlucose9) {
        this.urinalysisGlucose9 = urinalysisGlucose9;
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
     * @hibernate.property length="4" column="urinalysisGlucose_10"
     */

    public String getUrinalysisGlucose10() {
        return urinalysisGlucose10;
    }

    public void setUrinalysisGlucose10(String urinalysisGlucose10) {
        this.urinalysisGlucose10 = urinalysisGlucose10;
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
     * @hibernate.property length="4" column="urinalysisGlucose_11"
     */

    public String getUrinalysisGlucose11() {
        return urinalysisGlucose11;
    }

    public void setUrinalysisGlucose11(String urinalysisGlucose11) {
        this.urinalysisGlucose11 = urinalysisGlucose11;
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
     * @hibernate.property length="4" column="urinalysisGlucose_12"
     */

    public String getUrinalysisGlucose12() {
        return urinalysisGlucose12;
    }

    public void setUrinalysisGlucose12(String urinalysisGlucose12) {
        this.urinalysisGlucose12 = urinalysisGlucose12;
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
     * @hibernate.property length="4" column="urinalysisGlucose_13"
     */

    public String getUrinalysisGlucose13() {
        return urinalysisGlucose13;
    }

    public void setUrinalysisGlucose13(String urinalysisGlucose13) {
        this.urinalysisGlucose13 = urinalysisGlucose13;
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
     * @hibernate.property length="4" column="urinalysisGlucose_14"
     */

    public String getUrinalysisGlucose14() {
        return urinalysisGlucose14;
    }

    public void setUrinalysisGlucose14(String urinalysisGlucose14) {
        this.urinalysisGlucose14 = urinalysisGlucose14;
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
     * @hibernate.property length="4" column="urinalysisGlucose_15"
     */

    public String getUrinalysisGlucose15() {
        return urinalysisGlucose15;
    }

    public void setUrinalysisGlucose15(String urinalysisGlucose15) {
        this.urinalysisGlucose15 = urinalysisGlucose15;
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
     * @hibernate.property length="4" column="urinalysisGlucose_16"
     */

    public String getUrinalysisGlucose16() {
        return urinalysisGlucose16;
    }

    public void setUrinalysisGlucose16(String urinalysisGlucose16) {
        this.urinalysisGlucose16 = urinalysisGlucose16;
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
     * @hibernate.property length="4" column="urinalysisGlucose_17"
     */

    public String getUrinalysisGlucose17() {
        return urinalysisGlucose17;
    }

    public void setUrinalysisGlucose17(String urinalysisGlucose17) {
        this.urinalysisGlucose17 = urinalysisGlucose17;
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
     * @hibernate.property length="4" column="urinalysisGlucose_18"
     */
    public String getUrinalysisGlucose18() {
        return urinalysisGlucose18;
    }

    public void setUrinalysisGlucose18(String urinalysisGlucose18) {
        this.urinalysisGlucose18 = urinalysisGlucose18;
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
     * @hibernate.property length="4" column="urinalysisGlucose_19"
     */
    public String getUrinalysisGlucose19() {
        return urinalysisGlucose19;
    }

    public void setUrinalysisGlucose19(String urinalysisGlucose19) {
        this.urinalysisGlucose19 = urinalysisGlucose19;
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
     * @hibernate.property length="4" column="urinalysisGlucose_20"
     */
    public String getUrinalysisGlucose20() {
        return urinalysisGlucose20;
    }

    public void setUrinalysisGlucose20(String urinalysisGlucose20) {
        this.urinalysisGlucose20 = urinalysisGlucose20;
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
     * @hibernate.property length="4" column="urinalysisGlucose_21"
     */
    public String getUrinalysisGlucose21() {
        return urinalysisGlucose21;
    }

    public void setUrinalysisGlucose21(String urinalysisGlucose21) {
        this.urinalysisGlucose21 = urinalysisGlucose21;
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
     * @hibernate.property length="4" column="urinalysisGlucose_22"
     */
    public String getUrinalysisGlucose22() {
        return urinalysisGlucose22;
    }

    public void setUrinalysisGlucose22(String urinalysisGlucose22) {
        this.urinalysisGlucose22 = urinalysisGlucose22;
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
     * @hibernate.property length="4" column="urinalysisGlucose_23"
     */
    public String getUrinalysisGlucose23() {
        return urinalysisGlucose23;
    }

    public void setUrinalysisGlucose23(String urinalysisGlucose23) {
        this.urinalysisGlucose23 = urinalysisGlucose23;
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
     * @hibernate.property length="4" column="urinalysisGlucose_24"
     */
    public String getUrinalysisGlucose24() {
        return urinalysisGlucose24;
    }

    public void setUrinalysisGlucose24(String urinalysisGlucose24) {
        this.urinalysisGlucose24 = urinalysisGlucose24;
    }

}
