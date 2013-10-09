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
 * @hibernate.class table="parto_oxytocin"
 * mutable="true"
 */

public class Oxytocin implements BaseRecord, Identifiable, org.cidrz.webapp.dynasite.valueobject.Auditable {

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
    private Integer oxytocin1;

    private Time timeObservation2;
    private Integer oxytocin2;

    private Time timeObservation3;
    private Integer oxytocin3;

    private Time timeObservation4;
    private Integer oxytocin4;

    private Time timeObservation5;
    private Integer oxytocin5;

    private Time timeObservation6;
    private Integer oxytocin6;

    private Time timeObservation7;
    private Integer oxytocin7;

    private Time timeObservation8;
    private Integer oxytocin8;

    private Time timeObservation9;
    private Integer oxytocin9;

    private Time timeObservation10;
    private Integer oxytocin10;

    private Time timeObservation11;
    private Integer oxytocin11;

    private Time timeObservation12;
    private Integer oxytocin12;

    private Time timeObservation13;
    private Integer oxytocin13;

    private Time timeObservation14;
    private Integer oxytocin14;

    private Time timeObservation15;
    private Integer oxytocin15;

    private Time timeObservation16;
    private Integer oxytocin16;

    private Time timeObservation17;
    private Integer oxytocin17;

    private Time timeObservation18;
    private Integer oxytocin18;

    private Time timeObservation19;
    private Integer oxytocin19;

    private Time timeObservation20;
    private Integer oxytocin20;

    private Time timeObservation21;
    private Integer oxytocin21;

    private Time timeObservation22;
    private Integer oxytocin22;

    private Time timeObservation23;
    private Integer oxytocin23;

    private Time timeObservation24;
    private Integer oxytocin24;

    private Time timeObservation25;
    private Integer oxytocin25;

    private Time timeObservation26;
    private Integer oxytocin26;

    private Time timeObservation27;
    private Integer oxytocin27;

    private Time timeObservation28;
    private Integer oxytocin28;

    private Time timeObservation29;
    private Integer oxytocin29;

    private Time timeObservation30;
    private Integer oxytocin30;

    private Time timeObservation31;
    private Integer oxytocin31;

    private Time timeObservation32;
    private Integer oxytocin32;

    // begin new fields

    private Time timeObservation33;
    private Integer oxytocin33;

    private Time timeObservation34;
    private Integer oxytocin34;

    private Time timeObservation35;
    private Integer oxytocin35;

    private Time timeObservation36;
    private Integer oxytocin36;

    private Time timeObservation37;
    private Integer oxytocin37;

    private Time timeObservation38;
    private Integer oxytocin38;

    private Time timeObservation39;
    private Integer oxytocin39;

    private Time timeObservation40;
    private Integer oxytocin40;

    private Time timeObservation41;
    private Integer oxytocin41;

    private Time timeObservation42;
    private Integer oxytocin42;

    private Time timeObservation43;
    private Integer oxytocin43;

    private Time timeObservation44;
    private Integer oxytocin44;

    private Time timeObservation45;
    private Integer oxytocin45;

    private Time timeObservation46;
    private Integer oxytocin46;

    private Time timeObservation47;
    private Integer oxytocin47;

    private Time timeObservation48;
    private Integer oxytocin48;

    // Begin Oxytocin Drops

    private Time timeObservation49;
    private Integer oxytocinDrops49;

    private Time timeObservation50;
    private Integer oxytocinDrops50;

    private Time timeObservation51;
    private Integer oxytocinDrops51;

    private Time timeObservation52;
    private Integer oxytocinDrops52;

    private Time timeObservation53;
    private Integer oxytocinDrops53;

    private Time timeObservation54;
    private Integer oxytocinDrops54;

    private Time timeObservation55;
    private Integer oxytocinDrops55;

    private Time timeObservation56;
    private Integer oxytocinDrops56;

    private Time timeObservation57;
    private Integer oxytocinDrops57;

    private Time timeObservation58;
    private Integer oxytocinDrops58;

    private Time timeObservation59;
    private Integer oxytocinDrops59;

    private Time timeObservation60;
    private Integer oxytocinDrops60;

    private Time timeObservation61;
    private Integer oxytocinDrops61;

    private Time timeObservation62;
    private Integer oxytocinDrops62;

    private Time timeObservation63;
    private Integer oxytocinDrops63;

    private Time timeObservation64;
    private Integer oxytocinDrops64;

    private Time timeObservation65;
    private Integer oxytocinDrops65;

    private Time timeObservation66;
    private Integer oxytocinDrops66;

    private Time timeObservation67;
    private Integer oxytocinDrops67;

    private Time timeObservation68;
    private Integer oxytocinDrops68;

    private Time timeObservation69;
    private Integer oxytocinDrops69;

    private Time timeObservation70;
    private Integer oxytocinDrops70;

    private Time timeObservation71;
    private Integer oxytocinDrops71;

    private Time timeObservation72;
    private Integer oxytocinDrops72;

    private Time timeObservation73;
    private Integer oxytocinDrops73;

    private Time timeObservation74;
    private Integer oxytocinDrops74;

    private Time timeObservation75;
    private Integer oxytocinDrops75;

    private Time timeObservation76;
    private Integer oxytocinDrops76;

    private Time timeObservation77;
    private Integer oxytocinDrops77;

    private Time timeObservation78;
    private Integer oxytocinDrops78;

    private Time timeObservation79;
    private Integer oxytocinDrops79;

    private Time timeObservation80;
    private Integer oxytocinDrops80;

    private Time timeObservation81;
    private Integer oxytocinDrops81;

    private Time timeObservation82;
    private Integer oxytocinDrops82;

    private Time timeObservation83;
    private Integer oxytocinDrops83;

    private Time timeObservation84;
    private Integer oxytocinDrops84;

    private Time timeObservation85;
    private Integer oxytocinDrops85;

    private Time timeObservation86;
    private Integer oxytocinDrops86;

    private Time timeObservation87;
    private Integer oxytocinDrops87;

    private Time timeObservation88;
    private Integer oxytocinDrops88;

    private Time timeObservation89;
    private Integer oxytocinDrops89;

    private Time timeObservation90;
    private Integer oxytocinDrops90;

    private Time timeObservation91;
    private Integer oxytocinDrops91;

    private Time timeObservation92;
    private Integer oxytocinDrops92;

    private Time timeObservation93;
    private Integer oxytocinDrops93;

    private Time timeObservation94;
    private Integer oxytocinDrops94;

    private Time timeObservation95;
    private Integer oxytocinDrops95;

    private Time timeObservation96;
    private Integer oxytocinDrops96;


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
     * @hibernate.property length="4" column="oxytocin_1"
     */
    public Integer getOxytocin1() {
        return oxytocin1;
    }

    public void setOxytocin1(Integer oxytocin1) {
        this.oxytocin1 = oxytocin1;
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
     * @hibernate.property length="4" column="oxytocin_2"
     */
    public Integer getOxytocin2() {
        return oxytocin2;
    }

    public void setOxytocin2(Integer oxytocin2) {
        this.oxytocin2 = oxytocin2;
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
     * @hibernate.property length="4" column="oxytocin_3"
     */
    public Integer getOxytocin3() {
        return oxytocin3;
    }

    public void setOxytocin3(Integer oxytocin3) {
        this.oxytocin3 = oxytocin3;
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
     * @hibernate.property length="4" column="oxytocin_4"
     */
    public Integer getOxytocin4() {
        return oxytocin4;
    }

    public void setOxytocin4(Integer oxytocin4) {
        this.oxytocin4 = oxytocin4;
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
     * @hibernate.property length="4" column="oxytocin_5"
     */
    public Integer getOxytocin5() {
        return oxytocin5;
    }

    public void setOxytocin5(Integer oxytocin5) {
        this.oxytocin5 = oxytocin5;
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
     * @hibernate.property length="4" column="oxytocin_6"
     */
    public Integer getOxytocin6() {
        return oxytocin6;
    }

    public void setOxytocin6(Integer oxytocin6) {
        this.oxytocin6 = oxytocin6;
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
     * @hibernate.property length="4" column="oxytocin_7"
     */
    public Integer getOxytocin7() {
        return oxytocin7;
    }

    public void setOxytocin7(Integer oxytocin7) {
        this.oxytocin7 = oxytocin7;
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
     * @hibernate.property length="4" column="oxytocin_8"
     */
    public Integer getOxytocin8() {
        return oxytocin8;
    }

    public void setOxytocin8(Integer oxytocin8) {
        this.oxytocin8 = oxytocin8;
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
     * @hibernate.property length="4" column="oxytocin_9"
     */
    public Integer getOxytocin9() {
        return oxytocin9;
    }

    public void setOxytocin9(Integer oxytocin9) {
        this.oxytocin9 = oxytocin9;
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
     * @hibernate.property length="4" column="oxytocin_10"
     */
    public Integer getOxytocin10() {
        return oxytocin10;
    }

    public void setOxytocin10(Integer oxytocin10) {
        this.oxytocin10 = oxytocin10;
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
     * @hibernate.property length="4" column="oxytocin_11"
     */
    public Integer getOxytocin11() {
        return oxytocin11;
    }

    public void setOxytocin11(Integer oxytocin11) {
        this.oxytocin11 = oxytocin11;
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
     * @hibernate.property length="4" column="oxytocin_12"
     */
    public Integer getOxytocin12() {
        return oxytocin12;
    }

    public void setOxytocin12(Integer oxytocin12) {
        this.oxytocin12 = oxytocin12;
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
     * @hibernate.property length="4" column="oxytocin_13"
     */
    public Integer getOxytocin13() {
        return oxytocin13;
    }

    public void setOxytocin13(Integer oxytocin13) {
        this.oxytocin13 = oxytocin13;
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
     * @hibernate.property length="4" column="oxytocin_14"
     */
    public Integer getOxytocin14() {
        return oxytocin14;
    }

    public void setOxytocin14(Integer oxytocin14) {
        this.oxytocin14 = oxytocin14;
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
     * @hibernate.property length="4" column="oxytocin_15"
     */
    public Integer getOxytocin15() {
        return oxytocin15;
    }

    public void setOxytocin15(Integer oxytocin15) {
        this.oxytocin15 = oxytocin15;
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
     * @hibernate.property length="4" column="oxytocin_16"
     */
    public Integer getOxytocin16() {
        return oxytocin16;
    }

    public void setOxytocin16(Integer oxytocin16) {
        this.oxytocin16 = oxytocin16;
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
     * @hibernate.property length="4" column="oxytocin_17"
     */
    public Integer getOxytocin17() {
        return oxytocin17;
    }

    public void setOxytocin17(Integer oxytocin17) {
        this.oxytocin17 = oxytocin17;
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
     * @hibernate.property length="4" column="oxytocin_18"
     */
    public Integer getOxytocin18() {
        return oxytocin18;
    }

    public void setOxytocin18(Integer oxytocin18) {
        this.oxytocin18 = oxytocin18;
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
     * @hibernate.property length="4" column="oxytocin_19"
     */
    public Integer getOxytocin19() {
        return oxytocin19;
    }

    public void setOxytocin19(Integer oxytocin19) {
        this.oxytocin19 = oxytocin19;
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
     * @hibernate.property length="4" column="oxytocin_20"
     */
    public Integer getOxytocin20() {
        return oxytocin20;
    }

    public void setOxytocin20(Integer oxytocin20) {
        this.oxytocin20 = oxytocin20;
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
     * @hibernate.property length="4" column="oxytocin_21"
     */
    public Integer getOxytocin21() {
        return oxytocin21;
    }

    public void setOxytocin21(Integer oxytocin21) {
        this.oxytocin21 = oxytocin21;
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
     * @hibernate.property length="4" column="oxytocin_22"
     */
    public Integer getOxytocin22() {
        return oxytocin22;
    }

    public void setOxytocin22(Integer oxytocin22) {
        this.oxytocin22 = oxytocin22;
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
     * @hibernate.property length="4" column="oxytocin_23"
     */
    public Integer getOxytocin23() {
        return oxytocin23;
    }

    public void setOxytocin23(Integer oxytocin23) {
        this.oxytocin23 = oxytocin23;
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
     * @hibernate.property length="4" column="oxytocin_24"
     */
    public Integer getOxytocin24() {
        return oxytocin24;
    }

    public void setOxytocin24(Integer oxytocin24) {
        this.oxytocin24 = oxytocin24;
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
     * @hibernate.property length="4" column="oxytocin_25"
     */
    public Integer getOxytocin25() {
        return oxytocin25;
    }

    public void setOxytocin25(Integer oxytocin25) {
        this.oxytocin25 = oxytocin25;
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
     * @hibernate.property length="4" column="oxytocin_26"
     */
    public Integer getOxytocin26() {
        return oxytocin26;
    }

    public void setOxytocin26(Integer oxytocin26) {
        this.oxytocin26 = oxytocin26;
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
     * @hibernate.property length="4" column="oxytocin_27"
     */
    public Integer getOxytocin27() {
        return oxytocin27;
    }

    public void setOxytocin27(Integer oxytocin27) {
        this.oxytocin27 = oxytocin27;
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
     * @hibernate.property length="4" column="oxytocin_28"
     */
    public Integer getOxytocin28() {
        return oxytocin28;
    }

    public void setOxytocin28(Integer oxytocin28) {
        this.oxytocin28 = oxytocin28;
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
     * @hibernate.property length="4" column="oxytocin_29"
     */
    public Integer getOxytocin29() {
        return oxytocin29;
    }

    public void setOxytocin29(Integer oxytocin29) {
        this.oxytocin29 = oxytocin29;
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
     * @hibernate.property length="4" column="oxytocin_30"
     */
    public Integer getOxytocin30() {
        return oxytocin30;
    }

    public void setOxytocin30(Integer oxytocin30) {
        this.oxytocin30 = oxytocin30;
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
     * @hibernate.property length="4" column="oxytocin_31"
     */
    public Integer getOxytocin31() {
        return oxytocin31;
    }

    public void setOxytocin31(Integer oxytocin31) {
        this.oxytocin31 = oxytocin31;
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
     * @hibernate.property length="4" column="oxytocin_32"
     */
    public Integer getOxytocin32() {
        return oxytocin32;
    }

    public void setOxytocin32(Integer oxytocin32) {
        this.oxytocin32 = oxytocin32;
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
     * @hibernate.property length="4" column="oxytocin_33"
     */
    public Integer getOxytocin33() {
        return oxytocin33;
    }

    public void setOxytocin33(Integer oxytocin33) {
        this.oxytocin33 = oxytocin33;
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
     * @hibernate.property length="4" column="oxytocin_34"
     */
    public Integer getOxytocin34() {
        return oxytocin34;
    }

    public void setOxytocin34(Integer oxytocin34) {
        this.oxytocin34 = oxytocin34;
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
     * @hibernate.property length="4" column="oxytocin_35"
     */
    public Integer getOxytocin35() {
        return oxytocin35;
    }

    public void setOxytocin35(Integer oxytocin35) {
        this.oxytocin35 = oxytocin35;
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
     * @hibernate.property length="4" column="oxytocin_36"
     */
    public Integer getOxytocin36() {
        return oxytocin36;
    }

    public void setOxytocin36(Integer oxytocin36) {
        this.oxytocin36 = oxytocin36;
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
     * @hibernate.property length="4" column="oxytocin_37"
     */
    public Integer getOxytocin37() {
        return oxytocin37;
    }

    public void setOxytocin37(Integer oxytocin37) {
        this.oxytocin37 = oxytocin37;
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
     * @hibernate.property length="4" column="oxytocin_38"
     */
    public Integer getOxytocin38() {
        return oxytocin38;
    }

    public void setOxytocin38(Integer oxytocin38) {
        this.oxytocin38 = oxytocin38;
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
     * @hibernate.property length="4" column="oxytocin_39"
     */
    public Integer getOxytocin39() {
        return oxytocin39;
    }

    public void setOxytocin39(Integer oxytocin39) {
        this.oxytocin39 = oxytocin39;
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
     * @hibernate.property length="4" column="oxytocin_40"
     */
    public Integer getOxytocin40() {
        return oxytocin40;
    }

    public void setOxytocin40(Integer oxytocin40) {
        this.oxytocin40 = oxytocin40;
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
     * @hibernate.property length="4" column="oxytocin_41"
     */
    public Integer getOxytocin41() {
        return oxytocin41;
    }

    public void setOxytocin41(Integer oxytocin41) {
        this.oxytocin41 = oxytocin41;
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
     * @hibernate.property length="4" column="oxytocin_42"
     */
    public Integer getOxytocin42() {
        return oxytocin42;
    }

    public void setOxytocin42(Integer oxytocin42) {
        this.oxytocin42 = oxytocin42;
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
     * @hibernate.property length="4" column="oxytocin_43"
     */
    public Integer getOxytocin43() {
        return oxytocin43;
    }

    public void setOxytocin43(Integer oxytocin43) {
        this.oxytocin43 = oxytocin43;
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
     * @hibernate.property length="4" column="oxytocin_44"
     */
    public Integer getOxytocin44() {
        return oxytocin44;
    }

    public void setOxytocin44(Integer oxytocin44) {
        this.oxytocin44 = oxytocin44;
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
     * @hibernate.property length="4" column="oxytocin_45"
     */
    public Integer getOxytocin45() {
        return oxytocin45;
    }

    public void setOxytocin45(Integer oxytocin45) {
        this.oxytocin45 = oxytocin45;
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
     * @hibernate.property length="4" column="oxytocin_46"
     */
    public Integer getOxytocin46() {
        return oxytocin46;
    }

    public void setOxytocin46(Integer oxytocin46) {
        this.oxytocin46 = oxytocin46;
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
     * @hibernate.property length="4" column="oxytocin_47"
     */
    public Integer getOxytocin47() {
        return oxytocin47;
    }

    public void setOxytocin47(Integer oxytocin47) {
        this.oxytocin47 = oxytocin47;
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
     * @hibernate.property length="4" column="oxytocin_48"
     */
    public Integer getOxytocin48() {
        return oxytocin48;
    }

    public void setOxytocin48(Integer oxytocin48) {
        this.oxytocin48 = oxytocin48;
    }

    /**
     * @return
     * @hibernate.property column="time_49"
     */
    public Time getTimeObservation49() {
        return timeObservation49;
    }

    public void setTimeObservation49(Time timeObservation49) {
        this.timeObservation49 = timeObservation49;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_49"
     */
    public Integer getOxytocinDrops49() {
        return oxytocinDrops49;
    }

    public void setOxytocinDrops49(Integer oxytocinDrops49) {
        this.oxytocinDrops49 = oxytocinDrops49;
    }

    /**
     * @return
     * @hibernate.property column="time_50"
     */
    public Time getTimeObservation50() {
        return timeObservation50;
    }

    public void setTimeObservation50(Time timeObservation50) {
        this.timeObservation50 = timeObservation50;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_50"
     */
    public Integer getOxytocinDrops50() {
        return oxytocinDrops50;
    }

    public void setOxytocinDrops50(Integer oxytocinDrops50) {
        this.oxytocinDrops50 = oxytocinDrops50;
    }

    /**
     * @return
     * @hibernate.property column="time_51"
     */
    public Time getTimeObservation51() {
        return timeObservation51;
    }

    public void setTimeObservation51(Time timeObservation51) {
        this.timeObservation51 = timeObservation51;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_51"
     */
    public Integer getOxytocinDrops51() {
        return oxytocinDrops51;
    }

    public void setOxytocinDrops51(Integer oxytocinDrops51) {
        this.oxytocinDrops51 = oxytocinDrops51;
    }

    /**
     * @return
     * @hibernate.property column="time_52"
     */
    public Time getTimeObservation52() {
        return timeObservation52;
    }

    public void setTimeObservation52(Time timeObservation52) {
        this.timeObservation52 = timeObservation52;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_52"
     */
    public Integer getOxytocinDrops52() {
        return oxytocinDrops52;
    }

    public void setOxytocinDrops52(Integer oxytocinDrops52) {
        this.oxytocinDrops52 = oxytocinDrops52;
    }

    /**
     * @return
     * @hibernate.property column="time_53"
     */
    public Time getTimeObservation53() {
        return timeObservation53;
    }

    public void setTimeObservation53(Time timeObservation53) {
        this.timeObservation53 = timeObservation53;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_53"
     */
    public Integer getOxytocinDrops53() {
        return oxytocinDrops53;
    }

    public void setOxytocinDrops53(Integer oxytocinDrops53) {
        this.oxytocinDrops53 = oxytocinDrops53;
    }

    /**
     * @return
     * @hibernate.property column="time_54"
     */
    public Time getTimeObservation54() {
        return timeObservation54;
    }

    public void setTimeObservation54(Time timeObservation54) {
        this.timeObservation54 = timeObservation54;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_54"
     */
    public Integer getOxytocinDrops54() {
        return oxytocinDrops54;
    }

    public void setOxytocinDrops54(Integer oxytocinDrops54) {
        this.oxytocinDrops54 = oxytocinDrops54;
    }

    /**
     * @return
     * @hibernate.property column="time_55"
     */
    public Time getTimeObservation55() {
        return timeObservation55;
    }

    public void setTimeObservation55(Time timeObservation55) {
        this.timeObservation55 = timeObservation55;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_55"
     */
    public Integer getOxytocinDrops55() {
        return oxytocinDrops55;
    }

    public void setOxytocinDrops55(Integer oxytocinDrops55) {
        this.oxytocinDrops55 = oxytocinDrops55;
    }

    /**
     * @return
     * @hibernate.property column="time_56"
     */
    public Time getTimeObservation56() {
        return timeObservation56;
    }

    public void setTimeObservation56(Time timeObservation56) {
        this.timeObservation56 = timeObservation56;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_56"
     */
    public Integer getOxytocinDrops56() {
        return oxytocinDrops56;
    }

    public void setOxytocinDrops56(Integer oxytocinDrops56) {
        this.oxytocinDrops56 = oxytocinDrops56;
    }

    /**
     * @return
     * @hibernate.property column="time_57"
     */
    public Time getTimeObservation57() {
        return timeObservation57;
    }

    public void setTimeObservation57(Time timeObservation57) {
        this.timeObservation57 = timeObservation57;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_57"
     */
    public Integer getOxytocinDrops57() {
        return oxytocinDrops57;
    }

    public void setOxytocinDrops57(Integer oxytocinDrops57) {
        this.oxytocinDrops57 = oxytocinDrops57;
    }

    /**
     * @return
     * @hibernate.property column="time_58"
     */
    public Time getTimeObservation58() {
        return timeObservation58;
    }

    public void setTimeObservation58(Time timeObservation58) {
        this.timeObservation58 = timeObservation58;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_58"
     */
    public Integer getOxytocinDrops58() {
        return oxytocinDrops58;
    }

    public void setOxytocinDrops58(Integer oxytocinDrops58) {
        this.oxytocinDrops58 = oxytocinDrops58;
    }

    /**
     * @return
     * @hibernate.property column="time_59"
     */
    public Time getTimeObservation59() {
        return timeObservation59;
    }

    public void setTimeObservation59(Time timeObservation59) {
        this.timeObservation59 = timeObservation59;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_59"
     */
    public Integer getOxytocinDrops59() {
        return oxytocinDrops59;
    }

    public void setOxytocinDrops59(Integer oxytocinDrops59) {
        this.oxytocinDrops59 = oxytocinDrops59;
    }

    /**
     * @return
     * @hibernate.property column="time_60"
     */
    public Time getTimeObservation60() {
        return timeObservation60;
    }

    public void setTimeObservation60(Time timeObservation60) {
        this.timeObservation60 = timeObservation60;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_60"
     */
    public Integer getOxytocinDrops60() {
        return oxytocinDrops60;
    }

    public void setOxytocinDrops60(Integer oxytocinDrops60) {
        this.oxytocinDrops60 = oxytocinDrops60;
    }

    /**
     * @return
     * @hibernate.property column="time_61"
     */
    public Time getTimeObservation61() {
        return timeObservation61;
    }

    public void setTimeObservation61(Time timeObservation61) {
        this.timeObservation61 = timeObservation61;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_61"
     */
    public Integer getOxytocinDrops61() {
        return oxytocinDrops61;
    }

    public void setOxytocinDrops61(Integer oxytocinDrops61) {
        this.oxytocinDrops61 = oxytocinDrops61;
    }

    /**
     * @return
     * @hibernate.property column="time_62"
     */
    public Time getTimeObservation62() {
        return timeObservation62;
    }

    public void setTimeObservation62(Time timeObservation62) {
        this.timeObservation62 = timeObservation62;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_62"
     */
    public Integer getOxytocinDrops62() {
        return oxytocinDrops62;
    }

    public void setOxytocinDrops62(Integer oxytocinDrops62) {
        this.oxytocinDrops62 = oxytocinDrops62;
    }

    /**
     * @return
     * @hibernate.property column="time_63"
     */
    public Time getTimeObservation63() {
        return timeObservation63;
    }

    public void setTimeObservation63(Time timeObservation63) {
        this.timeObservation63 = timeObservation63;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_63"
     */
    public Integer getOxytocinDrops63() {
        return oxytocinDrops63;
    }

    public void setOxytocinDrops63(Integer oxytocinDrops63) {
        this.oxytocinDrops63 = oxytocinDrops63;
    }

    /**
     * @return
     * @hibernate.property column="time_64"
     */
    public Time getTimeObservation64() {
        return timeObservation64;
    }

    public void setTimeObservation64(Time timeObservation64) {
        this.timeObservation64 = timeObservation64;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_64"
     */
    public Integer getOxytocinDrops64() {
        return oxytocinDrops64;
    }

    public void setOxytocinDrops64(Integer oxytocinDrops64) {
        this.oxytocinDrops64 = oxytocinDrops64;
    }

    /**
     * @return
     * @hibernate.property column="time_65"
     */
    public Time getTimeObservation65() {
        return timeObservation65;
    }

    public void setTimeObservation65(Time timeObservation65) {
        this.timeObservation65 = timeObservation65;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_65"
     */
    public Integer getOxytocinDrops65() {
        return oxytocinDrops65;
    }

    public void setOxytocinDrops65(Integer oxytocinDrops65) {
        this.oxytocinDrops65 = oxytocinDrops65;
    }

    /**
     * @return
     * @hibernate.property column="time_66"
     */
    public Time getTimeObservation66() {
        return timeObservation66;
    }

    public void setTimeObservation66(Time timeObservation66) {
        this.timeObservation66 = timeObservation66;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_66"
     */
    public Integer getOxytocinDrops66() {
        return oxytocinDrops66;
    }

    public void setOxytocinDrops66(Integer oxytocinDrops66) {
        this.oxytocinDrops66 = oxytocinDrops66;
    }

    /**
     * @return
     * @hibernate.property column="time_67"
     */
    public Time getTimeObservation67() {
        return timeObservation67;
    }

    public void setTimeObservation67(Time timeObservation67) {
        this.timeObservation67 = timeObservation67;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_67"
     */
    public Integer getOxytocinDrops67() {
        return oxytocinDrops67;
    }

    public void setOxytocinDrops67(Integer oxytocinDrops67) {
        this.oxytocinDrops67 = oxytocinDrops67;
    }

    /**
     * @return
     * @hibernate.property column="time_68"
     */
    public Time getTimeObservation68() {
        return timeObservation68;
    }

    public void setTimeObservation68(Time timeObservation68) {
        this.timeObservation68 = timeObservation68;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_68"
     */
    public Integer getOxytocinDrops68() {
        return oxytocinDrops68;
    }

    public void setOxytocinDrops68(Integer oxytocinDrops68) {
        this.oxytocinDrops68 = oxytocinDrops68;
    }

    /**
     * @return
     * @hibernate.property column="time_69"
     */
    public Time getTimeObservation69() {
        return timeObservation69;
    }

    public void setTimeObservation69(Time timeObservation69) {
        this.timeObservation69 = timeObservation69;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_69"
     */
    public Integer getOxytocinDrops69() {
        return oxytocinDrops69;
    }

    public void setOxytocinDrops69(Integer oxytocinDrops69) {
        this.oxytocinDrops69 = oxytocinDrops69;
    }


    /**
     * @return
     * @hibernate.property column="time_70"
     */
    public Time getTimeObservation70() {
        return timeObservation70;
    }

    public void setTimeObservation70(Time timeObservation70) {
        this.timeObservation70 = timeObservation70;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_70"
     */
    public Integer getOxytocinDrops70() {
        return oxytocinDrops70;
    }

    public void setOxytocinDrops70(Integer oxytocinDrops70) {
        this.oxytocinDrops70 = oxytocinDrops70;
    }


    /**
     * @return
     * @hibernate.property column="time_71"
     */
    public Time getTimeObservation71() {
        return timeObservation71;
    }

    public void setTimeObservation71(Time timeObservation71) {
        this.timeObservation71 = timeObservation71;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_71"
     */
    public Integer getOxytocinDrops71() {
        return oxytocinDrops71;
    }

    public void setOxytocinDrops71(Integer oxytocinDrops71) {
        this.oxytocinDrops71 = oxytocinDrops71;
    }


    /**
     * @return
     * @hibernate.property column="time_72"
     */
    public Time getTimeObservation72() {
        return timeObservation72;
    }

    public void setTimeObservation72(Time timeObservation72) {
        this.timeObservation72 = timeObservation72;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_72"
     */
    public Integer getOxytocinDrops72() {
        return oxytocinDrops72;
    }

    public void setOxytocinDrops72(Integer oxytocinDrops72) {
        this.oxytocinDrops72 = oxytocinDrops72;
    }


    /**
     * @return
     * @hibernate.property column="time_73"
     */
    public Time getTimeObservation73() {
        return timeObservation73;
    }

    public void setTimeObservation73(Time timeObservation73) {
        this.timeObservation73 = timeObservation73;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_73"
     */
    public Integer getOxytocinDrops73() {
        return oxytocinDrops73;
    }

    public void setOxytocinDrops73(Integer oxytocinDrops73) {
        this.oxytocinDrops73 = oxytocinDrops73;
    }


    /**
     * @return
     * @hibernate.property column="time_74"
     */
    public Time getTimeObservation74() {
        return timeObservation74;
    }

    public void setTimeObservation74(Time timeObservation74) {
        this.timeObservation74 = timeObservation74;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_74"
     */
    public Integer getOxytocinDrops74() {
        return oxytocinDrops74;
    }

    public void setOxytocinDrops74(Integer oxytocinDrops74) {
        this.oxytocinDrops74 = oxytocinDrops74;
    }


    /**
     * @return
     * @hibernate.property column="time_75"
     */
    public Time getTimeObservation75() {
        return timeObservation75;
    }

    public void setTimeObservation75(Time timeObservation75) {
        this.timeObservation75 = timeObservation75;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_75"
     */
    public Integer getOxytocinDrops75() {
        return oxytocinDrops75;
    }

    public void setOxytocinDrops75(Integer oxytocinDrops75) {
        this.oxytocinDrops75 = oxytocinDrops75;
    }


    /**
     * @return
     * @hibernate.property column="time_76"
     */
    public Time getTimeObservation76() {
        return timeObservation76;
    }

    public void setTimeObservation76(Time timeObservation76) {
        this.timeObservation76 = timeObservation76;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_76"
     */
    public Integer getOxytocinDrops76() {
        return oxytocinDrops76;
    }

    public void setOxytocinDrops76(Integer oxytocinDrops76) {
        this.oxytocinDrops76 = oxytocinDrops76;
    }


    /**
     * @return
     * @hibernate.property column="time_77"
     */
    public Time getTimeObservation77() {
        return timeObservation77;
    }

    public void setTimeObservation77(Time timeObservation77) {
        this.timeObservation77 = timeObservation77;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_77"
     */
    public Integer getOxytocinDrops77() {
        return oxytocinDrops77;
    }

    public void setOxytocinDrops77(Integer oxytocinDrops77) {
        this.oxytocinDrops77 = oxytocinDrops77;
    }


    /**
     * @return
     * @hibernate.property column="time_78"
     */
    public Time getTimeObservation78() {
        return timeObservation78;
    }

    public void setTimeObservation78(Time timeObservation78) {
        this.timeObservation78 = timeObservation78;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_78"
     */
    public Integer getOxytocinDrops78() {
        return oxytocinDrops78;
    }

    public void setOxytocinDrops78(Integer oxytocinDrops78) {
        this.oxytocinDrops78 = oxytocinDrops78;
    }


    /**
     * @return
     * @hibernate.property column="time_79"
     */
    public Time getTimeObservation79() {
        return timeObservation79;
    }

    public void setTimeObservation79(Time timeObservation79) {
        this.timeObservation79 = timeObservation79;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_79"
     */
    public Integer getOxytocinDrops79() {
        return oxytocinDrops79;
    }

    public void setOxytocinDrops79(Integer oxytocinDrops79) {
        this.oxytocinDrops79 = oxytocinDrops79;
    }


    /**
     * @return
     * @hibernate.property column="time_80"
     */
    public Time getTimeObservation80() {
        return timeObservation80;
    }

    public void setTimeObservation80(Time timeObservation80) {
        this.timeObservation80 = timeObservation80;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_80"
     */
    public Integer getOxytocinDrops80() {
        return oxytocinDrops80;
    }

    public void setOxytocinDrops80(Integer oxytocinDrops80) {
        this.oxytocinDrops80 = oxytocinDrops80;
    }


    /**
     * @return
     * @hibernate.property column="time_81"
     */
    public Time getTimeObservation81() {
        return timeObservation81;
    }

    public void setTimeObservation81(Time timeObservation81) {
        this.timeObservation81 = timeObservation81;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_81"
     */
    public Integer getOxytocinDrops81() {
        return oxytocinDrops81;
    }

    public void setOxytocinDrops81(Integer oxytocinDrops81) {
        this.oxytocinDrops81 = oxytocinDrops81;
    }


    /**
     * @return
     * @hibernate.property column="time_82"
     */
    public Time getTimeObservation82() {
        return timeObservation82;
    }

    public void setTimeObservation82(Time timeObservation82) {
        this.timeObservation82 = timeObservation82;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_82"
     */
    public Integer getOxytocinDrops82() {
        return oxytocinDrops82;
    }

    public void setOxytocinDrops82(Integer oxytocinDrops82) {
        this.oxytocinDrops82 = oxytocinDrops82;
    }


    /**
     * @return
     * @hibernate.property column="time_83"
     */
    public Time getTimeObservation83() {
        return timeObservation83;
    }

    public void setTimeObservation83(Time timeObservation83) {
        this.timeObservation83 = timeObservation83;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_83"
     */
    public Integer getOxytocinDrops83() {
        return oxytocinDrops83;
    }

    public void setOxytocinDrops83(Integer oxytocinDrops83) {
        this.oxytocinDrops83 = oxytocinDrops83;
    }


    /**
     * @return
     * @hibernate.property column="time_84"
     */
    public Time getTimeObservation84() {
        return timeObservation84;
    }

    public void setTimeObservation84(Time timeObservation84) {
        this.timeObservation84 = timeObservation84;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_84"
     */
    public Integer getOxytocinDrops84() {
        return oxytocinDrops84;
    }

    public void setOxytocinDrops84(Integer oxytocinDrops84) {
        this.oxytocinDrops84 = oxytocinDrops84;
    }


    /**
     * @return
     * @hibernate.property column="time_85"
     */
    public Time getTimeObservation85() {
        return timeObservation85;
    }

    public void setTimeObservation85(Time timeObservation85) {
        this.timeObservation85 = timeObservation85;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_85"
     */
    public Integer getOxytocinDrops85() {
        return oxytocinDrops85;
    }

    public void setOxytocinDrops85(Integer oxytocinDrops85) {
        this.oxytocinDrops85 = oxytocinDrops85;
    }


    /**
     * @return
     * @hibernate.property column="time_86"
     */
    public Time getTimeObservation86() {
        return timeObservation86;
    }

    public void setTimeObservation86(Time timeObservation86) {
        this.timeObservation86 = timeObservation86;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_86"
     */
    public Integer getOxytocinDrops86() {
        return oxytocinDrops86;
    }

    public void setOxytocinDrops86(Integer oxytocinDrops86) {
        this.oxytocinDrops86 = oxytocinDrops86;
    }


    /**
     * @return
     * @hibernate.property column="time_87"
     */
    public Time getTimeObservation87() {
        return timeObservation87;
    }

    public void setTimeObservation87(Time timeObservation87) {
        this.timeObservation87 = timeObservation87;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_87"
     */
    public Integer getOxytocinDrops87() {
        return oxytocinDrops87;
    }

    public void setOxytocinDrops87(Integer oxytocinDrops87) {
        this.oxytocinDrops87 = oxytocinDrops87;
    }


    /**
     * @return
     * @hibernate.property column="time_88"
     */
    public Time getTimeObservation88() {
        return timeObservation88;
    }

    public void setTimeObservation88(Time timeObservation88) {
        this.timeObservation88 = timeObservation88;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_88"
     */
    public Integer getOxytocinDrops88() {
        return oxytocinDrops88;
    }

    public void setOxytocinDrops88(Integer oxytocinDrops88) {
        this.oxytocinDrops88 = oxytocinDrops88;
    }


    /**
     * @return
     * @hibernate.property column="time_89"
     */
    public Time getTimeObservation89() {
        return timeObservation89;
    }

    public void setTimeObservation89(Time timeObservation89) {
        this.timeObservation89 = timeObservation89;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_89"
     */
    public Integer getOxytocinDrops89() {
        return oxytocinDrops89;
    }

    public void setOxytocinDrops89(Integer oxytocinDrops89) {
        this.oxytocinDrops89 = oxytocinDrops89;
    }


    /**
     * @return
     * @hibernate.property column="time_90"
     */
    public Time getTimeObservation90() {
        return timeObservation90;
    }

    public void setTimeObservation90(Time timeObservation90) {
        this.timeObservation90 = timeObservation90;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_90"
     */
    public Integer getOxytocinDrops90() {
        return oxytocinDrops90;
    }

    public void setOxytocinDrops90(Integer oxytocinDrops90) {
        this.oxytocinDrops90 = oxytocinDrops90;
    }


    /**
     * @return
     * @hibernate.property column="time_91"
     */
    public Time getTimeObservation91() {
        return timeObservation91;
    }

    public void setTimeObservation91(Time timeObservation91) {
        this.timeObservation91 = timeObservation91;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_91"
     */
    public Integer getOxytocinDrops91() {
        return oxytocinDrops91;
    }

    public void setOxytocinDrops91(Integer oxytocinDrops91) {
        this.oxytocinDrops91 = oxytocinDrops91;
    }


    /**
     * @return
     * @hibernate.property column="time_92"
     */
    public Time getTimeObservation92() {
        return timeObservation92;
    }

    public void setTimeObservation92(Time timeObservation92) {
        this.timeObservation92 = timeObservation92;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_92"
     */
    public Integer getOxytocinDrops92() {
        return oxytocinDrops92;
    }

    public void setOxytocinDrops92(Integer oxytocinDrops92) {
        this.oxytocinDrops92 = oxytocinDrops92;
    }


    /**
     * @return
     * @hibernate.property column="time_93"
     */
    public Time getTimeObservation93() {
        return timeObservation93;
    }

    public void setTimeObservation93(Time timeObservation93) {
        this.timeObservation93 = timeObservation93;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_93"
     */
    public Integer getOxytocinDrops93() {
        return oxytocinDrops93;
    }

    public void setOxytocinDrops93(Integer oxytocinDrops93) {
        this.oxytocinDrops93 = oxytocinDrops93;
    }


    /**
     * @return
     * @hibernate.property column="time_94"
     */
    public Time getTimeObservation94() {
        return timeObservation94;
    }

    public void setTimeObservation94(Time timeObservation94) {
        this.timeObservation94 = timeObservation94;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_94"
     */
    public Integer getOxytocinDrops94() {
        return oxytocinDrops94;
    }

    public void setOxytocinDrops94(Integer oxytocinDrops94) {
        this.oxytocinDrops94 = oxytocinDrops94;
    }


    /**
     * @return
     * @hibernate.property column="time_95"
     */
    public Time getTimeObservation95() {
        return timeObservation95;
    }

    public void setTimeObservation95(Time timeObservation95) {
        this.timeObservation95 = timeObservation95;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_95"
     */
    public Integer getOxytocinDrops95() {
        return oxytocinDrops95;
    }

    public void setOxytocinDrops95(Integer oxytocinDrops95) {
        this.oxytocinDrops95 = oxytocinDrops95;
    }


    /**
     * @return
     * @hibernate.property column="time_96"
     */
    public Time getTimeObservation96() {
        return timeObservation96;
    }

    public void setTimeObservation96(Time timeObservation96) {
        this.timeObservation96 = timeObservation96;
    }


    /**
     * @return
     * @hibernate.property length="4" column="oxytocin_96"
     */
    public Integer getOxytocinDrops96() {
        return oxytocinDrops96;
    }

    public void setOxytocinDrops96(Integer oxytocinDrops96) {
        this.oxytocinDrops96 = oxytocinDrops96;
    }


}
