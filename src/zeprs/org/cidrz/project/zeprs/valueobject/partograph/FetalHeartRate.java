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
 * @hibernate.class table="parto_fetal_hr"
 * mutable="true"
 */

public class FetalHeartRate implements BaseRecord, Identifiable, org.cidrz.webapp.dynasite.valueobject.Auditable {

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
    private Integer fetalHeartRate1;
    private Time timeObservation2;
    private Integer fetalHeartRate2;
    private Time timeObservation3;
    private Integer fetalHeartRate3;
    private Time timeObservation4;
    private Integer fetalHeartRate4;
    private Time timeObservation5;
    private Integer fetalHeartRate5;
    private Time timeObservation6;
    private Integer fetalHeartRate6;
    private Time timeObservation7;
    private Integer fetalHeartRate7;
    private Time timeObservation8;
    private Integer fetalHeartRate8;
    private Time timeObservation9;
    private Integer fetalHeartRate9;
    private Time timeObservation10;
    private Integer fetalHeartRate10;

    private Time timeObservation11;
    private Integer fetalHeartRate11;
    private Time timeObservation12;
    private Integer fetalHeartRate12;
    private Time timeObservation13;
    private Integer fetalHeartRate13;
    private Time timeObservation14;
    private Integer fetalHeartRate14;
    private Time timeObservation15;
    private Integer fetalHeartRate15;
    private Time timeObservation16;
    private Integer fetalHeartRate16;
    private Time timeObservation17;
    private Integer fetalHeartRate17;
    private Time timeObservation18;
    private Integer fetalHeartRate18;
    private Time timeObservation19;
    private Integer fetalHeartRate19;
    private Time timeObservation20;
    private Integer fetalHeartRate20;

    private Time timeObservation21;
    private Integer fetalHeartRate21;
    private Time timeObservation22;
    private Integer fetalHeartRate22;
    private Time timeObservation23;
    private Integer fetalHeartRate23;
    private Time timeObservation24;
    private Integer fetalHeartRate24;
    private Time timeObservation25;
    private Integer fetalHeartRate25;
    private Time timeObservation26;
    private Integer fetalHeartRate26;
    private Time timeObservation27;
    private Integer fetalHeartRate27;
    private Time timeObservation28;
    private Integer fetalHeartRate28;
    private Time timeObservation29;
    private Integer fetalHeartRate29;
    private Time timeObservation30;
    private Integer fetalHeartRate30;

    private Time timeObservation31;
    private Integer fetalHeartRate31;
    private Time timeObservation32;
    private Integer fetalHeartRate32;
    private Time timeObservation33;
    private Integer fetalHeartRate33;
    private Time timeObservation34;
    private Integer fetalHeartRate34;
    private Time timeObservation35;
    private Integer fetalHeartRate35;
    private Time timeObservation36;
    private Integer fetalHeartRate36;
    private Time timeObservation37;
    private Integer fetalHeartRate37;
    private Time timeObservation38;
    private Integer fetalHeartRate38;
    private Time timeObservation39;
    private Integer fetalHeartRate39;
    private Time timeObservation40;
    private Integer fetalHeartRate40;
    private Time timeObservation41;
    private Integer fetalHeartRate41;
    private Time timeObservation42;
    private Integer fetalHeartRate42;
    private Time timeObservation43;
    private Integer fetalHeartRate43;
    private Time timeObservation44;
    private Integer fetalHeartRate44;
    private Time timeObservation45;
    private Integer fetalHeartRate45;
    private Time timeObservation46;
    private Integer fetalHeartRate46;
    private Time timeObservation47;
    private Integer fetalHeartRate47;
    private Time timeObservation48;
    private Integer fetalHeartRate48;
    private Time timeObservation49;
    private Integer fetalHeartRate49;
    private Time timeObservation50;
    private Integer fetalHeartRate50;

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
     * @hibernate.property column="fetal_hr_1"
     */
    public Integer getFetalHeartRate1() {
        return fetalHeartRate1;
    }

    public void setFetalHeartRate1(Integer fetalHeartRate1) {
        this.fetalHeartRate1 = fetalHeartRate1;
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
     * @hibernate.property column="fetal_hr_2"
     */

    public Integer getFetalHeartRate2() {
        return fetalHeartRate2;
    }

    public void setFetalHeartRate2(Integer fetalHeartRate2) {
        this.fetalHeartRate2 = fetalHeartRate2;
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
     * @hibernate.property column="fetal_hr_3"
     */

    public Integer getFetalHeartRate3() {
        return fetalHeartRate3;
    }

    public void setFetalHeartRate3(Integer fetalHeartRate3) {
        this.fetalHeartRate3 = fetalHeartRate3;
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
     * @hibernate.property column="fetal_hr_4"
     */

    public Integer getFetalHeartRate4() {
        return fetalHeartRate4;
    }

    public void setFetalHeartRate4(Integer fetalHeartRate4) {
        this.fetalHeartRate4 = fetalHeartRate4;
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
     * @hibernate.property column="fetal_hr_5"
     */

    public Integer getFetalHeartRate5() {
        return fetalHeartRate5;
    }

    public void setFetalHeartRate5(Integer fetalHeartRate5) {
        this.fetalHeartRate5 = fetalHeartRate5;
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
     * @hibernate.property column="fetal_hr_6"
     */

    public Integer getFetalHeartRate6() {
        return fetalHeartRate6;
    }

    public void setFetalHeartRate6(Integer fetalHeartRate6) {
        this.fetalHeartRate6 = fetalHeartRate6;
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
     * @hibernate.property column="fetal_hr_7"
     */

    public Integer getFetalHeartRate7() {
        return fetalHeartRate7;
    }

    public void setFetalHeartRate7(Integer fetalHeartRate7) {
        this.fetalHeartRate7 = fetalHeartRate7;
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
     * @hibernate.property column="fetal_hr_8"
     */

    public Integer getFetalHeartRate8() {
        return fetalHeartRate8;
    }

    public void setFetalHeartRate8(Integer fetalHeartRate8) {
        this.fetalHeartRate8 = fetalHeartRate8;
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
     * @hibernate.property column="fetal_hr_9"
     */

    public Integer getFetalHeartRate9() {
        return fetalHeartRate9;
    }

    public void setFetalHeartRate9(Integer fetalHeartRate9) {
        this.fetalHeartRate9 = fetalHeartRate9;
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
     * @hibernate.property column="fetal_hr_10"
     */

    public Integer getFetalHeartRate10() {
        return fetalHeartRate10;
    }

    public void setFetalHeartRate10(Integer fetalHeartRate10) {
        this.fetalHeartRate10 = fetalHeartRate10;
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
     * @hibernate.property column="fetal_hr_11"
     */

    public Integer getFetalHeartRate11() {
        return fetalHeartRate11;
    }

    public void setFetalHeartRate11(Integer fetalHeartRate11) {
        this.fetalHeartRate11 = fetalHeartRate11;
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
     * @hibernate.property column="fetal_hr_12"
     */

    public Integer getFetalHeartRate12() {
        return fetalHeartRate12;
    }

    public void setFetalHeartRate12(Integer fetalHeartRate12) {
        this.fetalHeartRate12 = fetalHeartRate12;
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
     * @hibernate.property column="fetal_hr_13"
     */

    public Integer getFetalHeartRate13() {
        return fetalHeartRate13;
    }

    public void setFetalHeartRate13(Integer fetalHeartRate13) {
        this.fetalHeartRate13 = fetalHeartRate13;
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
     * @hibernate.property column="fetal_hr_14"
     */

    public Integer getFetalHeartRate14() {
        return fetalHeartRate14;
    }

    public void setFetalHeartRate14(Integer fetalHeartRate14) {
        this.fetalHeartRate14 = fetalHeartRate14;
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
     * @hibernate.property column="fetal_hr_15"
     */

    public Integer getFetalHeartRate15() {
        return fetalHeartRate15;
    }

    public void setFetalHeartRate15(Integer fetalHeartRate15) {
        this.fetalHeartRate15 = fetalHeartRate15;
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
     * @hibernate.property column="fetal_hr_16"
     */

    public Integer getFetalHeartRate16() {
        return fetalHeartRate16;
    }

    public void setFetalHeartRate16(Integer fetalHeartRate16) {
        this.fetalHeartRate16 = fetalHeartRate16;
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
     * @hibernate.property column="fetal_hr_17"
     */

    public Integer getFetalHeartRate17() {
        return fetalHeartRate17;
    }

    public void setFetalHeartRate17(Integer fetalHeartRate17) {
        this.fetalHeartRate17 = fetalHeartRate17;
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
     * @hibernate.property column="fetal_hr_18"
     */

    public Integer getFetalHeartRate18() {
        return fetalHeartRate18;
    }

    public void setFetalHeartRate18(Integer fetalHeartRate18) {
        this.fetalHeartRate18 = fetalHeartRate18;
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
     * @hibernate.property column="fetal_hr_19"
     */

    public Integer getFetalHeartRate19() {
        return fetalHeartRate19;
    }

    public void setFetalHeartRate19(Integer fetalHeartRate19) {
        this.fetalHeartRate19 = fetalHeartRate19;
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
     * @hibernate.property column="fetal_hr_20"
     */

    public Integer getFetalHeartRate20() {
        return fetalHeartRate20;
    }

    public void setFetalHeartRate20(Integer fetalHeartRate20) {
        this.fetalHeartRate20 = fetalHeartRate20;
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
     * @hibernate.property column="fetal_hr_21"
     */

    public Integer getFetalHeartRate21() {
        return fetalHeartRate21;
    }

    public void setFetalHeartRate21(Integer fetalHeartRate21) {
        this.fetalHeartRate21 = fetalHeartRate21;
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
     * @hibernate.property column="fetal_hr_22"
     */

    public Integer getFetalHeartRate22() {
        return fetalHeartRate22;
    }

    public void setFetalHeartRate22(Integer fetalHeartRate22) {
        this.fetalHeartRate22 = fetalHeartRate22;
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
     * @hibernate.property column="fetal_hr_23"
     */

    public Integer getFetalHeartRate23() {
        return fetalHeartRate23;
    }

    public void setFetalHeartRate23(Integer fetalHeartRate23) {
        this.fetalHeartRate23 = fetalHeartRate23;
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
     * @hibernate.property column="fetal_hr_24"
     */

    public Integer getFetalHeartRate24() {
        return fetalHeartRate24;
    }

    public void setFetalHeartRate24(Integer fetalHeartRate24) {
        this.fetalHeartRate24 = fetalHeartRate24;
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
     * @hibernate.property column="fetal_hr_25"
     */

    public Integer getFetalHeartRate25() {
        return fetalHeartRate25;
    }

    public void setFetalHeartRate25(Integer fetalHeartRate25) {
        this.fetalHeartRate25 = fetalHeartRate25;
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
     * @hibernate.property column="fetal_hr_26"
     */

    public Integer getFetalHeartRate26() {
        return fetalHeartRate26;
    }

    public void setFetalHeartRate26(Integer fetalHeartRate26) {
        this.fetalHeartRate26 = fetalHeartRate26;
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
     * @hibernate.property column="fetal_hr_27"
     */

    public Integer getFetalHeartRate27() {
        return fetalHeartRate27;
    }

    public void setFetalHeartRate27(Integer fetalHeartRate27) {
        this.fetalHeartRate27 = fetalHeartRate27;
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
     * @hibernate.property column="fetal_hr_28"
     */

    public Integer getFetalHeartRate28() {
        return fetalHeartRate28;
    }

    public void setFetalHeartRate28(Integer fetalHeartRate28) {
        this.fetalHeartRate28 = fetalHeartRate28;
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
     * @hibernate.property column="fetal_hr_29"
     */

    public Integer getFetalHeartRate29() {
        return fetalHeartRate29;
    }

    public void setFetalHeartRate29(Integer fetalHeartRate29) {
        this.fetalHeartRate29 = fetalHeartRate29;
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
     * @hibernate.property column="fetal_hr_30"
     */

    public Integer getFetalHeartRate30() {
        return fetalHeartRate30;
    }

    public void setFetalHeartRate30(Integer fetalHeartRate30) {
        this.fetalHeartRate30 = fetalHeartRate30;
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
     * @hibernate.property column="fetal_hr_31"
     */

    public Integer getFetalHeartRate31() {
        return fetalHeartRate31;
    }

    public void setFetalHeartRate31(Integer fetalHeartRate31) {
        this.fetalHeartRate31 = fetalHeartRate31;
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
     * @hibernate.property column="fetal_hr_32"
     */

    public Integer getFetalHeartRate32() {
        return fetalHeartRate32;
    }

    public void setFetalHeartRate32(Integer fetalHeartRate32) {
        this.fetalHeartRate32 = fetalHeartRate32;
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
     * @hibernate.property column="fetal_hr_33"
     */

    public Integer getFetalHeartRate33() {
        return fetalHeartRate33;
    }

    public void setFetalHeartRate33(Integer fetalHeartRate33) {
        this.fetalHeartRate33 = fetalHeartRate33;
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
     * @hibernate.property column="fetal_hr_34"
     */

    public Integer getFetalHeartRate34() {
        return fetalHeartRate34;
    }

    public void setFetalHeartRate34(Integer fetalHeartRate34) {
        this.fetalHeartRate34 = fetalHeartRate34;
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
     * @hibernate.property column="fetal_hr_35"
     */
    public Integer getFetalHeartRate35() {
        return fetalHeartRate35;
    }

    public void setFetalHeartRate35(Integer fetalHeartRate35) {
        this.fetalHeartRate35 = fetalHeartRate35;
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
     * @hibernate.property column="fetal_hr_36"
     */
    public Integer getFetalHeartRate36() {
        return fetalHeartRate36;
    }

    public void setFetalHeartRate36(Integer fetalHeartRate36) {
        this.fetalHeartRate36 = fetalHeartRate36;
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
     * @hibernate.property column="fetal_hr_37"
     */
    public Integer getFetalHeartRate37() {
        return fetalHeartRate37;
    }

    public void setFetalHeartRate37(Integer fetalHeartRate37) {
        this.fetalHeartRate37 = fetalHeartRate37;
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
     * @hibernate.property column="fetal_hr_38"
     */
    public Integer getFetalHeartRate38() {
        return fetalHeartRate38;
    }

    public void setFetalHeartRate38(Integer fetalHeartRate38) {
        this.fetalHeartRate38 = fetalHeartRate38;
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
     * @hibernate.property column="fetal_hr_39"
     */
    public Integer getFetalHeartRate39() {
        return fetalHeartRate39;
    }

    public void setFetalHeartRate39(Integer fetalHeartRate39) {
        this.fetalHeartRate39 = fetalHeartRate39;
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
     * @hibernate.property column="fetal_hr_40"
     */
    public Integer getFetalHeartRate40() {
        return fetalHeartRate40;
    }

    public void setFetalHeartRate40(Integer fetalHeartRate40) {
        this.fetalHeartRate40 = fetalHeartRate40;
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
     * @hibernate.property column="fetal_hr_41"
     */
    public Integer getFetalHeartRate41() {
        return fetalHeartRate41;
    }

    public void setFetalHeartRate41(Integer fetalHeartRate41) {
        this.fetalHeartRate41 = fetalHeartRate41;
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
     * @hibernate.property column="fetal_hr_42"
     */
    public Integer getFetalHeartRate42() {
        return fetalHeartRate42;
    }

    public void setFetalHeartRate42(Integer fetalHeartRate42) {
        this.fetalHeartRate42 = fetalHeartRate42;
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
     * @hibernate.property column="fetal_hr_43"
     */
    public Integer getFetalHeartRate43() {
        return fetalHeartRate43;
    }

    public void setFetalHeartRate43(Integer fetalHeartRate43) {
        this.fetalHeartRate43 = fetalHeartRate43;
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
     * @hibernate.property column="fetal_hr_44"
     */
    public Integer getFetalHeartRate44() {
        return fetalHeartRate44;
    }

    public void setFetalHeartRate44(Integer fetalHeartRate44) {
        this.fetalHeartRate44 = fetalHeartRate44;
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
     * @hibernate.property column="fetal_hr_45"
     */
    public Integer getFetalHeartRate45() {
        return fetalHeartRate45;
    }

    public void setFetalHeartRate45(Integer fetalHeartRate45) {
        this.fetalHeartRate45 = fetalHeartRate45;
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
     * @hibernate.property column="fetal_hr_46"
     */
    public Integer getFetalHeartRate46() {
        return fetalHeartRate46;
    }

    public void setFetalHeartRate46(Integer fetalHeartRate46) {
        this.fetalHeartRate46 = fetalHeartRate46;
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
     * @hibernate.property column="fetal_hr_47"
     */
    public Integer getFetalHeartRate47() {
        return fetalHeartRate47;
    }

    public void setFetalHeartRate47(Integer fetalHeartRate47) {
        this.fetalHeartRate47 = fetalHeartRate47;
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
     * @hibernate.property column="fetal_hr_48"
     */
    public Integer getFetalHeartRate48() {
        return fetalHeartRate48;
    }

    public void setFetalHeartRate48(Integer fetalHeartRate48) {
        this.fetalHeartRate48 = fetalHeartRate48;
    }

}
