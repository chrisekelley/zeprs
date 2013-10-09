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
 * @hibernate.class table="parto_liquor"
 * mutable="true"
 */

public class Liquor implements BaseRecord, Identifiable, org.cidrz.webapp.dynasite.valueobject.Auditable {

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
    private String liquor1;
    private Time timeObservation2;
    private String liquor2;
    private Time timeObservation3;
    private String liquor3;
    private Time timeObservation4;
    private String liquor4;
    private Time timeObservation5;
    private String liquor5;
    private Time timeObservation6;
    private String liquor6;
    private Time timeObservation7;
    private String liquor7;
    private Time timeObservation8;
    private String liquor8;
    private Time timeObservation9;
    private String liquor9;
    private Time timeObservation10;
    private String liquor10;

    private Time timeObservation11;
    private String liquor11;
    private Time timeObservation12;
    private String liquor12;
    private Time timeObservation13;
    private String liquor13;
    private Time timeObservation14;
    private String liquor14;
    private Time timeObservation15;
    private String liquor15;
    private Time timeObservation16;
    private String liquor16;
    private Time timeObservation17;
    private String liquor17;
    private Time timeObservation18;
    private String liquor18;
    private Time timeObservation19;
    private String liquor19;
    private Time timeObservation20;
    private String liquor20;

    private Time timeObservation21;
    private String liquor21;
    private Time timeObservation22;
    private String liquor22;
    private Time timeObservation23;
    private String liquor23;
    private Time timeObservation24;
    private String liquor24;
    private Time timeObservation25;
    private String liquor25;
    private Time timeObservation26;
    private String liquor26;
    private Time timeObservation27;
    private String liquor27;
    private Time timeObservation28;
    private String liquor28;
    private Time timeObservation29;
    private String liquor29;
    private Time timeObservation30;
    private String liquor30;

    private Time timeObservation31;
    private String liquor31;
    private Time timeObservation32;
    private String liquor32;
    private Time timeObservation33;
    private String liquor33;
    private Time timeObservation34;
    private String liquor34;
    private Time timeObservation35;
    private String liquor35;
    private Time timeObservation36;
    private String liquor36;
    private Time timeObservation37;
    private String liquor37;
    private Time timeObservation38;
    private String liquor38;
    private Time timeObservation39;
    private String liquor39;
    private Time timeObservation40;
    private String liquor40;
    private Time timeObservation41;
    private String liquor41;
    private Time timeObservation42;
    private String liquor42;
    private Time timeObservation43;
    private String liquor43;
    private Time timeObservation44;
    private String liquor44;
    private Time timeObservation45;
    private String liquor45;
    private Time timeObservation46;
    private String liquor46;
    private Time timeObservation47;
    private String liquor47;
    private Time timeObservation48;
    private String liquor48;


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
     * @hibernate.property length="4" column="liquor_1"
     */
    public String getLiquor1() {
        return liquor1;
    }

    public void setLiquor1(String liquor1) {
        this.liquor1 = liquor1;
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
     * @hibernate.property length="4" column="liquor_2"
     */

    public String getLiquor2() {
        return liquor2;
    }

    public void setLiquor2(String liquor2) {
        this.liquor2 = liquor2;
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
     * @hibernate.property length="4" column="liquor_3"
     */

    public String getLiquor3() {
        return liquor3;
    }

    public void setLiquor3(String liquor3) {
        this.liquor3 = liquor3;
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
     * @hibernate.property length="4" column="liquor_4"
     */

    public String getLiquor4() {
        return liquor4;
    }

    public void setLiquor4(String liquor4) {
        this.liquor4 = liquor4;
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
     * @hibernate.property length="4" column="liquor_5"
     */

    public String getLiquor5() {
        return liquor5;
    }

    public void setLiquor5(String liquor5) {
        this.liquor5 = liquor5;
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
     * @hibernate.property length="4" column="liquor_6"
     */

    public String getLiquor6() {
        return liquor6;
    }

    public void setLiquor6(String liquor6) {
        this.liquor6 = liquor6;
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
     * @hibernate.property length="4" column="liquor_7"
     */

    public String getLiquor7() {
        return liquor7;
    }

    public void setLiquor7(String liquor7) {
        this.liquor7 = liquor7;
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
     * @hibernate.property length="4" column="liquor_8"
     */

    public String getLiquor8() {
        return liquor8;
    }

    public void setLiquor8(String liquor8) {
        this.liquor8 = liquor8;
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
     * @hibernate.property length="4" column="liquor_9"
     */

    public String getLiquor9() {
        return liquor9;
    }

    public void setLiquor9(String liquor9) {
        this.liquor9 = liquor9;
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
     * @hibernate.property length="4" column="liquor_10"
     */

    public String getLiquor10() {
        return liquor10;
    }

    public void setLiquor10(String liquor10) {
        this.liquor10 = liquor10;
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
     * @hibernate.property length="4" column="liquor_11"
     */

    public String getLiquor11() {
        return liquor11;
    }

    public void setLiquor11(String liquor11) {
        this.liquor11 = liquor11;
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
     * @hibernate.property length="4" column="liquor_12"
     */

    public String getLiquor12() {
        return liquor12;
    }

    public void setLiquor12(String liquor12) {
        this.liquor12 = liquor12;
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
     * @hibernate.property length="4" column="liquor_13"
     */

    public String getLiquor13() {
        return liquor13;
    }

    public void setLiquor13(String liquor13) {
        this.liquor13 = liquor13;
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
     * @hibernate.property length="4" column="liquor_14"
     */

    public String getLiquor14() {
        return liquor14;
    }

    public void setLiquor14(String liquor14) {
        this.liquor14 = liquor14;
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
     * @hibernate.property length="4" column="liquor_15"
     */

    public String getLiquor15() {
        return liquor15;
    }

    public void setLiquor15(String liquor15) {
        this.liquor15 = liquor15;
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
     * @hibernate.property length="4" column="liquor_16"
     */

    public String getLiquor16() {
        return liquor16;
    }

    public void setLiquor16(String liquor16) {
        this.liquor16 = liquor16;
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
     * @hibernate.property length="4" column="liquor_17"
     */

    public String getLiquor17() {
        return liquor17;
    }

    public void setLiquor17(String liquor17) {
        this.liquor17 = liquor17;
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
     * @hibernate.property length="4" column="liquor_18"
     */

    public String getLiquor18() {
        return liquor18;
    }

    public void setLiquor18(String liquor18) {
        this.liquor18 = liquor18;
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
     * @hibernate.property length="4" column="liquor_19"
     */

    public String getLiquor19() {
        return liquor19;
    }

    public void setLiquor19(String liquor19) {
        this.liquor19 = liquor19;
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
     * @hibernate.property length="4" column="liquor_20"
     */

    public String getLiquor20() {
        return liquor20;
    }

    public void setLiquor20(String liquor20) {
        this.liquor20 = liquor20;
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
     * @hibernate.property length="4" column="liquor_21"
     */

    public String getLiquor21() {
        return liquor21;
    }

    public void setLiquor21(String liquor21) {
        this.liquor21 = liquor21;
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
     * @hibernate.property length="4" column="liquor_22"
     */

    public String getLiquor22() {
        return liquor22;
    }

    public void setLiquor22(String liquor22) {
        this.liquor22 = liquor22;
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
     * @hibernate.property length="4" column="liquor_23"
     */

    public String getLiquor23() {
        return liquor23;
    }

    public void setLiquor23(String liquor23) {
        this.liquor23 = liquor23;
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
     * @hibernate.property length="4" column="liquor_24"
     */

    public String getLiquor24() {
        return liquor24;
    }

    public void setLiquor24(String liquor24) {
        this.liquor24 = liquor24;
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
     * @hibernate.property length="4" column="liquor_25"
     */

    public String getLiquor25() {
        return liquor25;
    }

    public void setLiquor25(String liquor25) {
        this.liquor25 = liquor25;
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
     * @hibernate.property length="4" column="liquor_26"
     */

    public String getLiquor26() {
        return liquor26;
    }

    public void setLiquor26(String liquor26) {
        this.liquor26 = liquor26;
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
     * @hibernate.property length="4" column="liquor_27"
     */

    public String getLiquor27() {
        return liquor27;
    }

    public void setLiquor27(String liquor27) {
        this.liquor27 = liquor27;
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
     * @hibernate.property length="4" column="liquor_28"
     */

    public String getLiquor28() {
        return liquor28;
    }

    public void setLiquor28(String liquor28) {
        this.liquor28 = liquor28;
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
     * @hibernate.property length="4" column="liquor_29"
     */

    public String getLiquor29() {
        return liquor29;
    }

    public void setLiquor29(String liquor29) {
        this.liquor29 = liquor29;
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
     * @hibernate.property length="4" column="liquor_30"
     */

    public String getLiquor30() {
        return liquor30;
    }

    public void setLiquor30(String liquor30) {
        this.liquor30 = liquor30;
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
     * @hibernate.property length="4" column="liquor_31"
     */

    public String getLiquor31() {
        return liquor31;
    }

    public void setLiquor31(String liquor31) {
        this.liquor31 = liquor31;
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
     * @hibernate.property length="4" column="liquor_32"
     */

    public String getLiquor32() {
        return liquor32;
    }

    public void setLiquor32(String liquor32) {
        this.liquor32 = liquor32;
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
     * @hibernate.property length="4" column="liquor_33"
     */

    public String getLiquor33() {
        return liquor33;
    }

    public void setLiquor33(String liquor33) {
        this.liquor33 = liquor33;
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
     * @hibernate.property length="4" column="liquor_34"
     */

    public String getLiquor34() {
        return liquor34;
    }

    public void setLiquor34(String liquor34) {
        this.liquor34 = liquor34;
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
     * @hibernate.property length="4" column="liquor_35"
     */
    public String getLiquor35() {
        return liquor35;
    }

    public void setLiquor35(String liquor35) {
        this.liquor35 = liquor35;
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
     * @hibernate.property length="4" column="liquor_36"
     */
    public String getLiquor36() {
        return liquor36;
    }

    public void setLiquor36(String liquor36) {
        this.liquor36 = liquor36;
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
     * @hibernate.property length="4" column="liquor_37"
     */
    public String getLiquor37() {
        return liquor37;
    }

    public void setLiquor37(String liquor37) {
        this.liquor37 = liquor37;
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
     * @hibernate.property length="4" column="liquor_38"
     */
    public String getLiquor38() {
        return liquor38;
    }

    public void setLiquor38(String liquor38) {
        this.liquor38 = liquor38;
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
     * @hibernate.property length="4" column="liquor_39"
     */
    public String getLiquor39() {
        return liquor39;
    }

    public void setLiquor39(String liquor39) {
        this.liquor39 = liquor39;
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
     * @hibernate.property length="4" column="liquor_40"
     */
    public String getLiquor40() {
        return liquor40;
    }

    public void setLiquor40(String liquor40) {
        this.liquor40 = liquor40;
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
     * @hibernate.property length="4" column="liquor_41"
     */
    public String getLiquor41() {
        return liquor41;
    }

    public void setLiquor41(String liquor41) {
        this.liquor41 = liquor41;
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
     * @hibernate.property length="4" column="liquor_42"
     */
    public String getLiquor42() {
        return liquor42;
    }

    public void setLiquor42(String liquor42) {
        this.liquor42 = liquor42;
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
     * @hibernate.property length="4" column="liquor_43"
     */
    public String getLiquor43() {
        return liquor43;
    }

    public void setLiquor43(String liquor43) {
        this.liquor43 = liquor43;
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
     * @hibernate.property length="4" column="liquor_44"
     */
    public String getLiquor44() {
        return liquor44;
    }

    public void setLiquor44(String liquor44) {
        this.liquor44 = liquor44;
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
     * @hibernate.property length="4" column="liquor_45"
     */
    public String getLiquor45() {
        return liquor45;
    }

    public void setLiquor45(String liquor45) {
        this.liquor45 = liquor45;
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
     * @hibernate.property length="4" column="liquor_46"
     */
    public String getLiquor46() {
        return liquor46;
    }

    public void setLiquor46(String liquor46) {
        this.liquor46 = liquor46;
    }

     /**
     * @return
     * @hibernate.property  column="time_47"
     */
    public Time getTimeObservation47() {
        return timeObservation47;
    }

    public void setTimeObservation47(Time timeObservation47) {
        this.timeObservation47 = timeObservation47;
    }

    /**
     * @return
     * @hibernate.property length="4"  column="liquor_47"
     */
    public String getLiquor47() {
        return liquor47;
    }

    public void setLiquor47(String liquor47) {
        this.liquor47 = liquor47;
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
     * @hibernate.property length="4"  column="liquor_48"
     */
    public String getLiquor48() {
        return liquor48;
    }

    public void setLiquor48(String liquor48) {
        this.liquor48 = liquor48;
    }


}
