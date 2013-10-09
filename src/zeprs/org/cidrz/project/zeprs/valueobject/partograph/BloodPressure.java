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
 * @hibernate.class table="parto_blood_pressure"
 * mutable="true"
 */

public class BloodPressure implements Identifiable, org.cidrz.webapp.dynasite.valueobject.Auditable, BaseRecord {
// ------------------------------ FIELDS ------------------------------

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
    private Integer systolic1;
    private Integer diastolic1;

    private Time timeObservation2;
    private Integer systolic2;
    private Integer diastolic2;

    private Time timeObservation3;
    private Integer systolic3;
    private Integer diastolic3;

    private Time timeObservation4;
    private Integer systolic4;
    private Integer diastolic4;

    private Time timeObservation5;
    private Integer systolic5;
    private Integer diastolic5;

    private Time timeObservation6;
    private Integer systolic6;
    private Integer diastolic6;

    private Time timeObservation7;
    private Integer systolic7;
    private Integer diastolic7;

    private Time timeObservation8;
    private Integer systolic8;
    private Integer diastolic8;

    private Time timeObservation9;
    private Integer systolic9;
    private Integer diastolic9;

    private Time timeObservation10;
    private Integer systolic10;
    private Integer diastolic10;

    private Time timeObservation11;
    private Integer systolic11;
    private Integer diastolic11;

    private Time timeObservation12;
    private Integer systolic12;
    private Integer diastolic12;

    private Time timeObservation13;
    private Integer systolic13;
    private Integer diastolic13;

    private Time timeObservation14;
    private Integer systolic14;
    private Integer diastolic14;

    private Time timeObservation15;
    private Integer systolic15;
    private Integer diastolic15;

    private Time timeObservation16;
    private Integer systolic16;
    private Integer diastolic16;

    private Time timeObservation17;
    private Integer systolic17;
    private Integer diastolic17;

    private Time timeObservation18;
    private Integer systolic18;
    private Integer diastolic18;

    private Time timeObservation19;
    private Integer systolic19;
    private Integer diastolic19;

    private Time timeObservation20;
    private Integer systolic20;
    private Integer diastolic20;

    private Time timeObservation21;
    private Integer systolic21;
    private Integer diastolic21;

    private Time timeObservation22;
    private Integer systolic22;
    private Integer diastolic22;

    private Time timeObservation23;
    private Integer systolic23;
    private Integer diastolic23;

    private Time timeObservation24;
    private Integer systolic24;
    private Integer diastolic24;

    private Time timeObservation25;
    private Integer systolic25;
    private Integer diastolic25;

    private Time timeObservation26;
    private Integer systolic26;
    private Integer diastolic26;

    private Time timeObservation27;
    private Integer systolic27;
    private Integer diastolic27;

    private Time timeObservation28;
    private Integer systolic28;
    private Integer diastolic28;

    private Time timeObservation29;
    private Integer systolic29;
    private Integer diastolic29;

    private Time timeObservation30;
    private Integer systolic30;
    private Integer diastolic30;

    private Time timeObservation31;
    private Integer systolic31;
    private Integer diastolic31;

    private Time timeObservation32;
    private Integer systolic32;
    private Integer diastolic32;

    private Time timeObservation33;
    private Integer systolic33;
    private Integer diastolic33;

    private Time timeObservation34;
    private Integer systolic34;
    private Integer diastolic34;

    private Time timeObservation35;
    private Integer systolic35;
    private Integer diastolic35;

    private Time timeObservation36;
    private Integer systolic36;
    private Integer diastolic36;

    private Time timeObservation37;
    private Integer systolic37;
    private Integer diastolic37;

    private Time timeObservation38;
    private Integer systolic38;
    private Integer diastolic38;

    private Time timeObservation39;
    private Integer systolic39;
    private Integer diastolic39;

    private Time timeObservation40;
    private Integer systolic40;
    private Integer diastolic40;

    private Time timeObservation41;
    private Integer systolic41;
    private Integer diastolic41;

    private Time timeObservation42;
    private Integer systolic42;
    private Integer diastolic42;

    private Time timeObservation43;
    private Integer systolic43;
    private Integer diastolic43;

    private Time timeObservation44;
    private Integer systolic44;
    private Integer diastolic44;

    private Time timeObservation45;
    private Integer systolic45;
    private Integer diastolic45;

    private Time timeObservation46;
    private Integer systolic46;
    private Integer diastolic46;

    private Time timeObservation47;
    private Integer systolic47;
    private Integer diastolic47;

    private Time timeObservation48;
    private Integer systolic48;
    private Integer diastolic48;




// --------------------- GETTER / SETTER METHODS ---------------------

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
     * @hibernate.property column="systolic_1"
     */
    public Integer getSystolic1() {
        return systolic1;
    }

    public void setSystolic1(Integer systolic1) {
        this.systolic1 = systolic1;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_1"
     */
    public Integer getDiastolic1() {
        return diastolic1;
    }

    public void setDiastolic1(Integer diastolic1) {
        this.diastolic1 = diastolic1;
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
     * @hibernate.property column="systolic_2"
     */
    public Integer getSystolic2() {
        return systolic2;
    }

    public void setSystolic2(Integer systolic2) {
        this.systolic2 = systolic2;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_2"
     */
    public Integer getDiastolic2() {
        return diastolic2;
    }

    public void setDiastolic2(Integer diastolic2) {
        this.diastolic2 = diastolic2;
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
     * @hibernate.property column="systolic_3"
     */
    public Integer getSystolic3() {
        return systolic3;
    }

    public void setSystolic3(Integer systolic3) {
        this.systolic3 = systolic3;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_3"
     */
    public Integer getDiastolic3() {
        return diastolic3;
    }

    public void setDiastolic3(Integer diastolic3) {
        this.diastolic3 = diastolic3;
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
     * @hibernate.property column="systolic_4"
     */
    public Integer getSystolic4() {
        return systolic4;
    }

    public void setSystolic4(Integer systolic4) {
        this.systolic4 = systolic4;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_4"
     */
    public Integer getDiastolic4() {
        return diastolic4;
    }

    public void setDiastolic4(Integer diastolic4) {
        this.diastolic4 = diastolic4;
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
     * @hibernate.property column="systolic_5"
     */
    public Integer getSystolic5() {
        return systolic5;
    }

    public void setSystolic5(Integer systolic5) {
        this.systolic5 = systolic5;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_5"
     */
    public Integer getDiastolic5() {
        return diastolic5;
    }

    public void setDiastolic5(Integer diastolic5) {
        this.diastolic5 = diastolic5;
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
     * @hibernate.property column="systolic_6"
     */
    public Integer getSystolic6() {
        return systolic6;
    }

    public void setSystolic6(Integer systolic6) {
        this.systolic6 = systolic6;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_6"
     */
    public Integer getDiastolic6() {
        return diastolic6;
    }

    public void setDiastolic6(Integer diastolic6) {
        this.diastolic6 = diastolic6;
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
     * @hibernate.property column="systolic_7"
     */
    public Integer getSystolic7() {
        return systolic7;
    }

    public void setSystolic7(Integer systolic7) {
        this.systolic7 = systolic7;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_7"
     */
    public Integer getDiastolic7() {
        return diastolic7;
    }

    public void setDiastolic7(Integer diastolic7) {
        this.diastolic7 = diastolic7;
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
     * @hibernate.property column="systolic_8"
     */
    public Integer getSystolic8() {
        return systolic8;
    }

    public void setSystolic8(Integer systolic8) {
        this.systolic8 = systolic8;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_8"
     */
    public Integer getDiastolic8() {
        return diastolic8;
    }

    public void setDiastolic8(Integer diastolic8) {
        this.diastolic8 = diastolic8;
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
     * @hibernate.property column="systolic_9"
     */
    public Integer getSystolic9() {
        return systolic9;
    }

    public void setSystolic9(Integer systolic9) {
        this.systolic9 = systolic9;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_9"
     */
    public Integer getDiastolic9() {
        return diastolic9;
    }

    public void setDiastolic9(Integer diastolic9) {
        this.diastolic9 = diastolic9;
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
     * @hibernate.property column="systolic_10"
     */
    public Integer getSystolic10() {
        return systolic10;
    }

    public void setSystolic10(Integer systolic10) {
        this.systolic10 = systolic10;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_10"
     */
    public Integer getDiastolic10() {
        return diastolic10;
    }

    public void setDiastolic10(Integer diastolic10) {
        this.diastolic10 = diastolic10;
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
     * @hibernate.property column="systolic_11"
     */
    public Integer getSystolic11() {
        return systolic11;
    }

    public void setSystolic11(Integer systolic11) {
        this.systolic11 = systolic11;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_11"
     */
    public Integer getDiastolic11() {
        return diastolic11;
    }

    public void setDiastolic11(Integer diastolic11) {
        this.diastolic11 = diastolic11;
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
     * @hibernate.property column="systolic_12"
     */
    public Integer getSystolic12() {
        return systolic12;
    }

    public void setSystolic12(Integer systolic12) {
        this.systolic12 = systolic12;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_12"
     */
    public Integer getDiastolic12() {
        return diastolic12;
    }

    public void setDiastolic12(Integer diastolic12) {
        this.diastolic12 = diastolic12;
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
     * @hibernate.property column="systolic_13"
     */
    public Integer getSystolic13() {
        return systolic13;
    }

    public void setSystolic13(Integer systolic13) {
        this.systolic13 = systolic13;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_13"
     */
    public Integer getDiastolic13() {
        return diastolic13;
    }

    public void setDiastolic13(Integer diastolic13) {
        this.diastolic13 = diastolic13;
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
     * @hibernate.property column="systolic_14"
     */
    public Integer getSystolic14() {
        return systolic14;
    }

    public void setSystolic14(Integer systolic14) {
        this.systolic14 = systolic14;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_14"
     */
    public Integer getDiastolic14() {
        return diastolic14;
    }

    public void setDiastolic14(Integer diastolic14) {
        this.diastolic14 = diastolic14;
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
     * @hibernate.property column="systolic_15"
     */
    public Integer getSystolic15() {
        return systolic15;
    }

    public void setSystolic15(Integer systolic15) {
        this.systolic15 = systolic15;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_15"
     */
    public Integer getDiastolic15() {
        return diastolic15;
    }

    public void setDiastolic15(Integer diastolic15) {
        this.diastolic15 = diastolic15;
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
     * @hibernate.property column="systolic_16"
     */
    public Integer getSystolic16() {
        return systolic16;
    }

    public void setSystolic16(Integer systolic16) {
        this.systolic16 = systolic16;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_16"
     */
    public Integer getDiastolic16() {
        return diastolic16;
    }

    public void setDiastolic16(Integer diastolic16) {
        this.diastolic16 = diastolic16;
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
     * @hibernate.property column="systolic_17"
     */
    public Integer getSystolic17() {
        return systolic17;
    }

    public void setSystolic17(Integer systolic17) {
        this.systolic17 = systolic17;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_17"
     */
    public Integer getDiastolic17() {
        return diastolic17;
    }

    public void setDiastolic17(Integer diastolic17) {
        this.diastolic17 = diastolic17;
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
     * @hibernate.property column="systolic_18"
     */
    public Integer getSystolic18() {
        return systolic18;
    }

    public void setSystolic18(Integer systolic18) {
        this.systolic18 = systolic18;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_18"
     */
    public Integer getDiastolic18() {
        return diastolic18;
    }

    public void setDiastolic18(Integer diastolic18) {
        this.diastolic18 = diastolic18;
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
     * @hibernate.property column="systolic_19"
     */
    public Integer getSystolic19() {
        return systolic19;
    }

    public void setSystolic19(Integer systolic19) {
        this.systolic19 = systolic19;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_19"
     */
    public Integer getDiastolic19() {
        return diastolic19;
    }

    public void setDiastolic19(Integer diastolic19) {
        this.diastolic19 = diastolic19;
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
     * @hibernate.property column="systolic_20"
     */
    public Integer getSystolic20() {
        return systolic20;
    }

    public void setSystolic20(Integer systolic20) {
        this.systolic20 = systolic20;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_20"
     */
    public Integer getDiastolic20() {
        return diastolic20;
    }

    public void setDiastolic20(Integer diastolic20) {
        this.diastolic20 = diastolic20;
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
     * @hibernate.property column="systolic_21"
     */
    public Integer getSystolic21() {
        return systolic21;
    }

    public void setSystolic21(Integer systolic21) {
        this.systolic21 = systolic21;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_21"
     */
    public Integer getDiastolic21() {
        return diastolic21;
    }

    public void setDiastolic21(Integer diastolic21) {
        this.diastolic21 = diastolic21;
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
     * @hibernate.property column="systolic_22"
     */
    public Integer getSystolic22() {
        return systolic22;
    }

    public void setSystolic22(Integer systolic22) {
        this.systolic22 = systolic22;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_22"
     */
    public Integer getDiastolic22() {
        return diastolic22;
    }

    public void setDiastolic22(Integer diastolic22) {
        this.diastolic22 = diastolic22;
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
     * @hibernate.property column="systolic_23"
     */
    public Integer getSystolic23() {
        return systolic23;
    }

    public void setSystolic23(Integer systolic23) {
        this.systolic23 = systolic23;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_23"
     */
    public Integer getDiastolic23() {
        return diastolic23;
    }

    public void setDiastolic23(Integer diastolic23) {
        this.diastolic23 = diastolic23;
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
     * @hibernate.property column="systolic_24"
     */
    public Integer getSystolic24() {
        return systolic24;
    }

    public void setSystolic24(Integer systolic24) {
        this.systolic24 = systolic24;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_24"
     */
    public Integer getDiastolic24() {
        return diastolic24;
    }

    public void setDiastolic24(Integer diastolic24) {
        this.diastolic24 = diastolic24;
    }

    /**
     * @return
     * @hibernate.property column="time_25"
     */
    public Time getTimeObservation25() {
        return timeObservation25;
    }

    public void setTimeObservation25(Time timeObservation25) {
        this.timeObservation25 = timeObservation25;
    }

    /**
     * @return
     * @hibernate.property column="systolic_25"
     */
    public Integer getSystolic25() {
        return systolic25;
    }

    public void setSystolic25(Integer systolic25) {
        this.systolic25 = systolic25;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_25"
     */
    public Integer getDiastolic25() {
        return diastolic25;
    }

    public void setDiastolic25(Integer diastolic25) {
        this.diastolic25 = diastolic25;
    }

    /**
     * @return
     * @hibernate.property column="time_26"
     */
    public Time getTimeObservation26() {
        return timeObservation26;
    }

    public void setTimeObservation26(Time timeObservation26) {
        this.timeObservation26 = timeObservation26;
    }

    /**
     * @return
     * @hibernate.property column="systolic_26"
     */
    public Integer getSystolic26() {
        return systolic26;
    }

    public void setSystolic26(Integer systolic26) {
        this.systolic26 = systolic26;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_26"
     */
    public Integer getDiastolic26() {
        return diastolic26;
    }

    public void setDiastolic26(Integer diastolic26) {
        this.diastolic26 = diastolic26;
    }

    /**
     * @return
     * @hibernate.property column="time_27"
     */
    public Time getTimeObservation27() {
        return timeObservation27;
    }

    public void setTimeObservation27(Time timeObservation27) {
        this.timeObservation27 = timeObservation27;
    }

    /**
     * @return
     * @hibernate.property column="systolic_27"
     */
    public Integer getSystolic27() {
        return systolic27;
    }

    public void setSystolic27(Integer systolic27) {
        this.systolic27 = systolic27;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_27"
     */
    public Integer getDiastolic27() {
        return diastolic27;
    }

    public void setDiastolic27(Integer diastolic27) {
        this.diastolic27 = diastolic27;
    }

    /**
     * @return
     * @hibernate.property column="time_28"
     */
    public Time getTimeObservation28() {
        return timeObservation28;
    }

    public void setTimeObservation28(Time timeObservation28) {
        this.timeObservation28 = timeObservation28;
    }

    /**
     * @return
     * @hibernate.property column="systolic_28"
     */
    public Integer getSystolic28() {
        return systolic28;
    }

    public void setSystolic28(Integer systolic28) {
        this.systolic28 = systolic28;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_28"
     */
    public Integer getDiastolic28() {
        return diastolic28;
    }

    public void setDiastolic28(Integer diastolic28) {
        this.diastolic28 = diastolic28;
    }

    /**
     * @return
     * @hibernate.property column="time_29"
     */
    public Time getTimeObservation29() {
        return timeObservation29;
    }

    public void setTimeObservation29(Time timeObservation29) {
        this.timeObservation29 = timeObservation29;
    }

    /**
     * @return
     * @hibernate.property column="systolic_29"
     */
    public Integer getSystolic29() {
        return systolic29;
    }

    public void setSystolic29(Integer systolic29) {
        this.systolic29 = systolic29;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_29"
     */
    public Integer getDiastolic29() {
        return diastolic29;
    }

    public void setDiastolic29(Integer diastolic29) {
        this.diastolic29 = diastolic29;
    }

    /**
     * @return
     * @hibernate.property column="time_30"
     */
    public Time getTimeObservation30() {
        return timeObservation30;
    }

    public void setTimeObservation30(Time timeObservation30) {
        this.timeObservation30 = timeObservation30;
    }

    /**
     * @return
     * @hibernate.property column="systolic_30"
     */
    public Integer getSystolic30() {
        return systolic30;
    }

    public void setSystolic30(Integer systolic30) {
        this.systolic30 = systolic30;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_30"
     */
    public Integer getDiastolic30() {
        return diastolic30;
    }

    public void setDiastolic30(Integer diastolic30) {
        this.diastolic30 = diastolic30;
    }

    /**
     * @return
     * @hibernate.property column="time_31"
     */
    public Time getTimeObservation31() {
        return timeObservation31;
    }

    public void setTimeObservation31(Time timeObservation31) {
        this.timeObservation31 = timeObservation31;
    }

    /**
     * @return
     * @hibernate.property column="systolic_31"
     */
    public Integer getSystolic31() {
        return systolic31;
    }

    public void setSystolic31(Integer systolic31) {
        this.systolic31 = systolic31;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_31"
     */
    public Integer getDiastolic31() {
        return diastolic31;
    }

    public void setDiastolic31(Integer diastolic31) {
        this.diastolic31 = diastolic31;
    }

    /**
     * @return
     * @hibernate.property column="time_32"
     */
    public Time getTimeObservation32() {
        return timeObservation32;
    }

    public void setTimeObservation32(Time timeObservation32) {
        this.timeObservation32 = timeObservation32;
    }

    /**
     * @return
     * @hibernate.property column="systolic_32"
     */
    public Integer getSystolic32() {
        return systolic32;
    }

    public void setSystolic32(Integer systolic32) {
        this.systolic32 = systolic32;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_32"
     */
    public Integer getDiastolic32() {
        return diastolic32;
    }

    public void setDiastolic32(Integer diastolic32) {
        this.diastolic32 = diastolic32;
    }

    /**
     * @return
     * @hibernate.property column="time_33"
     */
    public Time getTimeObservation33() {
        return timeObservation33;
    }

    public void setTimeObservation33(Time timeObservation33) {
        this.timeObservation33 = timeObservation33;
    }

    /**
     * @return
     * @hibernate.property column="systolic_33"
     */
    public Integer getSystolic33() {
        return systolic33;
    }

    public void setSystolic33(Integer systolic33) {
        this.systolic33 = systolic33;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_33"
     */
    public Integer getDiastolic33() {
        return diastolic33;
    }

    public void setDiastolic33(Integer diastolic33) {
        this.diastolic33 = diastolic33;
    }

    /**
     * @return
     * @hibernate.property column="time_34"
     */
    public Time getTimeObservation34() {
        return timeObservation34;
    }

    public void setTimeObservation34(Time timeObservation34) {
        this.timeObservation34 = timeObservation34;
    }

    /**
     * @return
     * @hibernate.property column="systolic_34"
     */
    public Integer getSystolic34() {
        return systolic34;
    }

    public void setSystolic34(Integer systolic34) {
        this.systolic34 = systolic34;
    }

    /**
     * @return
     * @hibernate.property column="diastolic_34"
     */
    public Integer getDiastolic34() {
        return diastolic34;
    }

    public void setDiastolic34(Integer diastolic34) {
        this.diastolic34 = diastolic34;
    }

      /**
     * @return
     * @hibernate.property column="time_35"
     */
    public Time getTimeObservation35() {
        return timeObservation35;
    }

    public void setTimeObservation35(Time timeObservation35) {
        this.timeObservation35 = timeObservation35;
    }

     /**
     * @return
     * @hibernate.property column="systolic_35"
     */

    public Integer getSystolic35() {
        return systolic35;
    }

    public void setSystolic35(Integer systolic35) {
        this.systolic35 = systolic35;
    }

     /**
     * @return
     * @hibernate.property column="diastolic_35"
     */
    public Integer getDiastolic35() {
        return diastolic35;
    }

    public void setDiastolic35(Integer diastolic35) {
        this.diastolic35 = diastolic35;
    }

     /**
     * @return
     * @hibernate.property column="time_36"
     */
    public Time getTimeObservation36() {
        return timeObservation36;
    }

    public void setTimeObservation36(Time timeObservation36) {
        this.timeObservation36 = timeObservation36;
    }

     /**
     * @return
     * @hibernate.property column="systolic_36"
     */
    public Integer getSystolic36() {
        return systolic36;
    }

    public void setSystolic36(Integer systolic36) {
        this.systolic36 = systolic36;
    }

     /**
     * @return
     * @hibernate.property column="diastolic_36"
     */
    public Integer getDiastolic36() {
        return diastolic36;
    }

    public void setDiastolic36(Integer diastolic36) {
        this.diastolic36 = diastolic36;
    }

     /**
     * @return
     * @hibernate.property column="time_37"
     */
    public Time getTimeObservation37() {
        return timeObservation37;
    }

    public void setTimeObservation37(Time timeObservation37) {
        this.timeObservation37 = timeObservation37;
    }

     /**
     * @return
     * @hibernate.property column="systolic_37"
     */
    public Integer getSystolic37() {
        return systolic37;
    }

    public void setSystolic37(Integer systolic37) {
        this.systolic37 = systolic37;
    }


     /**
     * @return
     * @hibernate.property column="diastolic_37"
     */
    public Integer getDiastolic37() {
        return diastolic37;
    }

    public void setDiastolic37(Integer diastolic37) {
        this.diastolic37 = diastolic37;
    }

     /**
     * @return
     * @hibernate.property column="time_38"
     */
    public Time getTimeObservation38() {
        return timeObservation38;
    }

    public void setTimeObservation38(Time timeObservation38) {
        this.timeObservation38 = timeObservation38;
    }

     /**
     * @return
     * @hibernate.property column="systolic_38"
     */
    public Integer getSystolic38() {
        return systolic38;
    }

    public void setSystolic38(Integer systolic38) {
        this.systolic38 = systolic38;
    }

     /**
     * @return
     * @hibernate.property column="diastolic_38"
     */
    public Integer getDiastolic38() {
        return diastolic38;
    }

    public void setDiastolic38(Integer diastolic38) {
        this.diastolic38 = diastolic38;
    }

     /**
     * @return
     * @hibernate.property column="time_39"
     */
    public Time getTimeObservation39() {
        return timeObservation39;
    }

    public void setTimeObservation39(Time timeObservation39) {
        this.timeObservation39 = timeObservation39;
    }

     /**
     * @return
     * @hibernate.property column="systolic_39"
     */
    public Integer getSystolic39() {
        return systolic39;
    }

    public void setSystolic39(Integer systolic39) {
        this.systolic39 = systolic39;
    }

     /**
     * @return
     * @hibernate.property column="diastolic_39"
     */
    public Integer getDiastolic39() {
        return diastolic39;
    }

    public void setDiastolic39(Integer diastolic39) {
        this.diastolic39 = diastolic39;
    }

     /**
     * @return
     * @hibernate.property column="time_40"
     */
    public Time getTimeObservation40() {
        return timeObservation40;
    }

    public void setTimeObservation40(Time timeObservation40) {
        this.timeObservation40 = timeObservation40;
    }

     /**
     * @return
     * @hibernate.property column="systolic_40"
     */
    public Integer getSystolic40() {
        return systolic40;
    }

    public void setSystolic40(Integer systolic40) {
        this.systolic40 = systolic40;
    }


     /**
     * @return
     * @hibernate.property column="diastolic_40"
     */
    public Integer getDiastolic40() {
        return diastolic40;
    }

    public void setDiastolic40(Integer diastolic40) {
        this.diastolic40 = diastolic40;
    }

     /**
     * @return
     * @hibernate.property column="time_41"
     */
    public Time getTimeObservation41() {
        return timeObservation41;
    }

    public void setTimeObservation41(Time timeObservation41) {
        this.timeObservation41 = timeObservation41;
    }

     /**
     * @return
     * @hibernate.property column="systolic_41"
     */
    public Integer getSystolic41() {
        return systolic41;
    }

    public void setSystolic41(Integer systolic41) {
        this.systolic41 = systolic41;
    }


     /**
     * @return
     * @hibernate.property column="diastolic_41"
     */
    public Integer getDiastolic41() {
        return diastolic41;
    }

    public void setDiastolic41(Integer diastolic41) {
        this.diastolic41 = diastolic41;
    }

     /**
     * @return
     * @hibernate.property column="time_42"
     */
    public Time getTimeObservation42() {
        return timeObservation42;
    }

    public void setTimeObservation42(Time timeObservation42) {
        this.timeObservation42 = timeObservation42;
    }

     /**
     * @return
     * @hibernate.property column="systolic_42"
     */
    public Integer getSystolic42() {
        return systolic42;
    }

    public void setSystolic42(Integer systolic42) {
        this.systolic42 = systolic42;
    }

     /**
     * @return
     * @hibernate.property column="diastolic_42"
     */
    public Integer getDiastolic42() {
        return diastolic42;
    }

    public void setDiastolic42(Integer diastolic42) {
        this.diastolic42 = diastolic42;
    }

     /**
     * @return
     * @hibernate.property column="time_43"
     */
    public Time getTimeObservation43() {
        return timeObservation43;
    }

    public void setTimeObservation43(Time timeObservation43) {
        this.timeObservation43 = timeObservation43;
    }

     /**
     * @return
     * @hibernate.property column="systolic_43"
     */
    public Integer getSystolic43() {
        return systolic43;
    }

    public void setSystolic43(Integer systolic43) {
        this.systolic43 = systolic43;
    }

     /**
     * @return
     * @hibernate.property column="diastolic_43"
     */
    public Integer getDiastolic43() {
        return diastolic43;
    }

    public void setDiastolic43(Integer diastolic43) {
        this.diastolic43 = diastolic43;
    }

     /**
     * @return
     * @hibernate.property column="time_44"
     */
    public Time getTimeObservation44() {
        return timeObservation44;
    }

    public void setTimeObservation44(Time timeObservation44) {
        this.timeObservation44 = timeObservation44;
    }

     /**
     * @return
     * @hibernate.property column="systolic_44"
     */
    public Integer getSystolic44() {
        return systolic44;
    }

    public void setSystolic44(Integer systolic44) {
        this.systolic44 = systolic44;
    }

     /**
     * @return
     * @hibernate.property column="diastolic_44"
     */
    public Integer getDiastolic44() {
        return diastolic44;
    }

    public void setDiastolic44(Integer diastolic44) {
        this.diastolic44 = diastolic44;
    }

     /**
     * @return
     * @hibernate.property column="time_45"
     */
    public Time getTimeObservation45() {
        return timeObservation45;
    }

    public void setTimeObservation45(Time timeObservation45) {
        this.timeObservation45 = timeObservation45;
    }

     /**
     * @return
     * @hibernate.property column="systolic_45"
     */
    public Integer getSystolic45() {
        return systolic45;
    }

    public void setSystolic45(Integer systolic45) {
        this.systolic45 = systolic45;
    }

     /**
     * @return
     * @hibernate.property column="diastolic_45"
     */
    public Integer getDiastolic45() {
        return diastolic45;
    }

    public void setDiastolic45(Integer diastolic45) {
        this.diastolic45 = diastolic45;
    }

     /**
     * @return
     * @hibernate.property column="time_46"
     */
    public Time getTimeObservation46() {
        return timeObservation46;
    }

    public void setTimeObservation46(Time timeObservation46) {
        this.timeObservation46 = timeObservation46;
    }

     /**
     * @return
     * @hibernate.property column="systolic_46"
     */
    public Integer getSystolic46() {
        return systolic46;
    }

    public void setSystolic46(Integer systolic46) {
        this.systolic46 = systolic46;
    }

     /**
     * @return
     * @hibernate.property column="diastolic_46"
     */
    public Integer getDiastolic46() {
        return diastolic46;
    }

    public void setDiastolic46(Integer diastolic46) {
        this.diastolic46 = diastolic46;
    }

     /**
     * @return
     * @hibernate.property column="time_47"
     */
    public Time getTimeObservation47() {
        return timeObservation47;
    }

    public void setTimeObservation47(Time timeObservation47) {
        this.timeObservation47 = timeObservation47;
    }

     /**
     * @return
     * @hibernate.property column="systolic_47"
     */
    public Integer getSystolic47() {
        return systolic47;
    }

    public void setSystolic47(Integer systolic47) {
        this.systolic47 = systolic47;
    }

     /**
     * @return
     * @hibernate.property column="diastolic_47"
     */
    public Integer getDiastolic47() {
        return diastolic47;
    }

    public void setDiastolic47(Integer diastolic47) {
        this.diastolic47 = diastolic47;
    }

     /**
     * @return
     * @hibernate.property column="time_48"
     */
    public Time getTimeObservation48() {
        return timeObservation48;
    }

    public void setTimeObservation48(Time timeObservation48) {
        this.timeObservation48 = timeObservation48;
    }

     /**
     * @return
     * @hibernate.property column="systolic_48"
     */
    public Integer getSystolic48() {
        return systolic48;
    }

    public void setSystolic48(Integer systolic48) {
        this.systolic48 = systolic48;
    }

     /**
     * @return
     * @hibernate.property column="diastolic_48"
     */
    public Integer getDiastolic48() {
        return diastolic48;
    }

    public void setDiastolic48(Integer diastolic48) {
        this.diastolic48 = diastolic48;
    }


}
