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
 * @hibernate.class table="parto_moulding"
 * mutable="true"
 */

public class Moulding implements BaseRecord, Identifiable, org.cidrz.webapp.dynasite.valueobject.Auditable {

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
    private String moulding1;
    private Time timeObservation2;
    private String moulding2;
    private Time timeObservation3;
    private String moulding3;
    private Time timeObservation4;
    private String moulding4;
    private Time timeObservation5;
    private String moulding5;
    private Time timeObservation6;
    private String moulding6;
    private Time timeObservation7;
    private String moulding7;
    private Time timeObservation8;
    private String moulding8;
    private Time timeObservation9;
    private String moulding9;
    private Time timeObservation10;
    private String moulding10;

    private Time timeObservation11;
    private String moulding11;
    private Time timeObservation12;
    private String moulding12;
    private Time timeObservation13;
    private String moulding13;
    private Time timeObservation14;
    private String moulding14;
    private Time timeObservation15;
    private String moulding15;
    private Time timeObservation16;
    private String moulding16;
    private Time timeObservation17;
    private String moulding17;
    private Time timeObservation18;
    private String moulding18;
    private Time timeObservation19;
    private String moulding19;
    private Time timeObservation20;
    private String moulding20;

    private Time timeObservation21;
    private String moulding21;
    private Time timeObservation22;
    private String moulding22;
    private Time timeObservation23;
    private String moulding23;
    private Time timeObservation24;
    private String moulding24;
    private Time timeObservation25;
    private String moulding25;
    private Time timeObservation26;
    private String moulding26;
    private Time timeObservation27;
    private String moulding27;
    private Time timeObservation28;
    private String moulding28;
    private Time timeObservation29;
    private String moulding29;
    private Time timeObservation30;
    private String moulding30;

    private Time timeObservation31;
    private String moulding31;
    private Time timeObservation32;
    private String moulding32;
    private Time timeObservation33;
    private String moulding33;
    private Time timeObservation34;
    private String moulding34;
    private Time timeObservation35;
    private String moulding35;
    private Time timeObservation36;
    private String moulding36;
    private Time timeObservation37;
    private String moulding37;
    private Time timeObservation38;
    private String moulding38;
    private Time timeObservation39;
    private String moulding39;
    private Time timeObservation40;
    private String moulding40;
    private Time timeObservation41;
    private String moulding41;
    private Time timeObservation42;
    private String moulding42;
    private Time timeObservation43;
    private String moulding43;
    private Time timeObservation44;
    private String moulding44;
    private Time timeObservation45;
    private String moulding45;
    private Time timeObservation46;
    private String moulding46;
    private Time timeObservation47;
    private String moulding47;
    private Time timeObservation48;
    private String moulding48;


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
     * @hibernate.property length="4" column="moulding_1"
     */
    public String getMoulding1() {
        return moulding1;
    }

    public void setMoulding1(String moulding1) {
        this.moulding1 = moulding1;
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
     * @hibernate.property length="4" column="moulding_2"
     */

    public String getMoulding2() {
        return moulding2;
    }

    public void setMoulding2(String moulding2) {
        this.moulding2 = moulding2;
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
     * @hibernate.property length="4" column="moulding_3"
     */

    public String getMoulding3() {
        return moulding3;
    }

    public void setMoulding3(String moulding3) {
        this.moulding3 = moulding3;
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
     * @hibernate.property length="4" column="moulding_4"
     */

    public String getMoulding4() {
        return moulding4;
    }

    public void setMoulding4(String moulding4) {
        this.moulding4 = moulding4;
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
     * @hibernate.property length="4" column="moulding_5"
     */

    public String getMoulding5() {
        return moulding5;
    }

    public void setMoulding5(String moulding5) {
        this.moulding5 = moulding5;
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
     * @hibernate.property length="4" column="moulding_6"
     */

    public String getMoulding6() {
        return moulding6;
    }

    public void setMoulding6(String moulding6) {
        this.moulding6 = moulding6;
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
     * @hibernate.property length="4" column="moulding_7"
     */

    public String getMoulding7() {
        return moulding7;
    }

    public void setMoulding7(String moulding7) {
        this.moulding7 = moulding7;
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
     * @hibernate.property length="4" column="moulding_8"
     */

    public String getMoulding8() {
        return moulding8;
    }

    public void setMoulding8(String moulding8) {
        this.moulding8 = moulding8;
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
     * @hibernate.property length="4" column="moulding_9"
     */

    public String getMoulding9() {
        return moulding9;
    }

    public void setMoulding9(String moulding9) {
        this.moulding9 = moulding9;
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
     * @hibernate.property length="4" column="moulding_10"
     */

    public String getMoulding10() {
        return moulding10;
    }

    public void setMoulding10(String moulding10) {
        this.moulding10 = moulding10;
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
     * @hibernate.property length="4" column="moulding_11"
     */

    public String getMoulding11() {
        return moulding11;
    }

    public void setMoulding11(String moulding11) {
        this.moulding11 = moulding11;
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
     * @hibernate.property length="4" column="moulding_12"
     */

    public String getMoulding12() {
        return moulding12;
    }

    public void setMoulding12(String moulding12) {
        this.moulding12 = moulding12;
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
     * @hibernate.property length="4" column="moulding_13"
     */

    public String getMoulding13() {
        return moulding13;
    }

    public void setMoulding13(String moulding13) {
        this.moulding13 = moulding13;
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
     * @hibernate.property length="4" column="moulding_14"
     */

    public String getMoulding14() {
        return moulding14;
    }

    public void setMoulding14(String moulding14) {
        this.moulding14 = moulding14;
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
     * @hibernate.property length="4" column="moulding_15"
     */

    public String getMoulding15() {
        return moulding15;
    }

    public void setMoulding15(String moulding15) {
        this.moulding15 = moulding15;
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
     * @hibernate.property length="4" column="moulding_16"
     */

    public String getMoulding16() {
        return moulding16;
    }

    public void setMoulding16(String moulding16) {
        this.moulding16 = moulding16;
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
     * @hibernate.property length="4" column="moulding_17"
     */

    public String getMoulding17() {
        return moulding17;
    }

    public void setMoulding17(String moulding17) {
        this.moulding17 = moulding17;
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
     * @hibernate.property length="4" column="moulding_18"
     */

    public String getMoulding18() {
        return moulding18;
    }

    public void setMoulding18(String moulding18) {
        this.moulding18 = moulding18;
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
     * @hibernate.property length="4" column="moulding_19"
     */

    public String getMoulding19() {
        return moulding19;
    }

    public void setMoulding19(String moulding19) {
        this.moulding19 = moulding19;
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
     * @hibernate.property length="4" column="moulding_20"
     */

    public String getMoulding20() {
        return moulding20;
    }

    public void setMoulding20(String moulding20) {
        this.moulding20 = moulding20;
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
     * @hibernate.property length="4" column="moulding_21"
     */

    public String getMoulding21() {
        return moulding21;
    }

    public void setMoulding21(String moulding21) {
        this.moulding21 = moulding21;
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
     * @hibernate.property length="4" column="moulding_22"
     */

    public String getMoulding22() {
        return moulding22;
    }

    public void setMoulding22(String moulding22) {
        this.moulding22 = moulding22;
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
     * @hibernate.property length="4" column="moulding_23"
     */

    public String getMoulding23() {
        return moulding23;
    }

    public void setMoulding23(String moulding23) {
        this.moulding23 = moulding23;
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
     * @hibernate.property length="4" column="moulding_24"
     */

    public String getMoulding24() {
        return moulding24;
    }

    public void setMoulding24(String moulding24) {
        this.moulding24 = moulding24;
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
     * @hibernate.property length="4" column="moulding_25"
     */

    public String getMoulding25() {
        return moulding25;
    }

    public void setMoulding25(String moulding25) {
        this.moulding25 = moulding25;
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
     * @hibernate.property length="4" column="moulding_26"
     */

    public String getMoulding26() {
        return moulding26;
    }

    public void setMoulding26(String moulding26) {
        this.moulding26 = moulding26;
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
     * @hibernate.property length="4" column="moulding_27"
     */

    public String getMoulding27() {
        return moulding27;
    }

    public void setMoulding27(String moulding27) {
        this.moulding27 = moulding27;
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
     * @hibernate.property length="4" column="moulding_28"
     */

    public String getMoulding28() {
        return moulding28;
    }

    public void setMoulding28(String moulding28) {
        this.moulding28 = moulding28;
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
     * @hibernate.property length="4" column="moulding_29"
     */

    public String getMoulding29() {
        return moulding29;
    }

    public void setMoulding29(String moulding29) {
        this.moulding29 = moulding29;
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
     * @hibernate.property length="4" column="moulding_30"
     */

    public String getMoulding30() {
        return moulding30;
    }

    public void setMoulding30(String moulding30) {
        this.moulding30 = moulding30;
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
     * @hibernate.property length="4" column="moulding_31"
     */

    public String getMoulding31() {
        return moulding31;
    }

    public void setMoulding31(String moulding31) {
        this.moulding31 = moulding31;
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
     * @hibernate.property length="4" column="moulding_32"
     */

    public String getMoulding32() {
        return moulding32;
    }

    public void setMoulding32(String moulding32) {
        this.moulding32 = moulding32;
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
     * @hibernate.property length="4" column="moulding_33"
     */

    public String getMoulding33() {
        return moulding33;
    }

    public void setMoulding33(String moulding33) {
        this.moulding33 = moulding33;
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
     * @hibernate.property length="4" column="moulding_34"
     */

    public String getMoulding34() {
        return moulding34;
    }

    public void setMoulding34(String moulding34) {
        this.moulding34 = moulding34;
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
     * @hibernate.property length="4" column="moulding_35"
     */
    public String getMoulding35() {
        return moulding35;
    }

    public void setMoulding35(String moulding35) {
        this.moulding35 = moulding35;
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
     * @hibernate.property length="4" column="moulding_36"
     */
    public String getMoulding36() {
        return moulding36;
    }

    public void setMoulding36(String moulding36) {
        this.moulding36 = moulding36;
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
     * @hibernate.property length="4" column="moulding_37"
     */
    public String getMoulding37() {
        return moulding37;
    }

    public void setMoulding37(String moulding37) {
        this.moulding37 = moulding37;
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
     * @hibernate.property length="4" column="moulding_38"
     */
    public String getMoulding38() {
        return moulding38;
    }

    public void setMoulding38(String moulding38) {
        this.moulding38 = moulding38;
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
     * @hibernate.property length="4" column="moulding_39"
     */
    public String getMoulding39() {
        return moulding39;
    }

    public void setMoulding39(String moulding39) {
        this.moulding39 = moulding39;
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
     * @hibernate.property length="4" column="moulding_40"
     */
    public String getMoulding40() {
        return moulding40;
    }

    public void setMoulding40(String moulding40) {
        this.moulding40 = moulding40;
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
     * @hibernate.property length="4" column="moulding_41"
     */
    public String getMoulding41() {
        return moulding41;
    }

    public void setMoulding41(String moulding41) {
        this.moulding41 = moulding41;
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
     * @hibernate.property length="4" column="moulding_42"
     */
    public String getMoulding42() {
        return moulding42;
    }

    public void setMoulding42(String moulding42) {
        this.moulding42 = moulding42;
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
     * @hibernate.property length="4" column="moulding_43"
     */
    public String getMoulding43() {
        return moulding43;
    }

    public void setMoulding43(String moulding43) {
        this.moulding43 = moulding43;
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
     * @hibernate.property length="4" column="moulding_44"
     */
    public String getMoulding44() {
        return moulding44;
    }

    public void setMoulding44(String moulding44) {
        this.moulding44 = moulding44;
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
     * @hibernate.property length="4" column="moulding_45"
     */
    public String getMoulding45() {
        return moulding45;
    }

    public void setMoulding45(String moulding45) {
        this.moulding45 = moulding45;
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
     * @hibernate.property length="4" column="moulding_46"
     */
    public String getMoulding46() {
        return moulding46;
    }

    public void setMoulding46(String moulding46) {
        this.moulding46 = moulding46;
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
     * @hibernate.property length="4" column="moulding_47"
     */
    public String getMoulding47() {
        return moulding47;
    }

    public void setMoulding47(String moulding47) {
        this.moulding47 = moulding47;
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
     * @hibernate.property length="4" column="moulding_48"
     */
    public String getMoulding48() {
        return moulding48;
    }

    public void setMoulding48(String moulding48) {
        this.moulding48 = moulding48;
    }


}
