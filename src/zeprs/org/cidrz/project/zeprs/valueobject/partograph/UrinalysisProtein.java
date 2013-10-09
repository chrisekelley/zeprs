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
 * @hibernate.class table="parto_urinalysis_protein"
 * mutable="true"
 */

public class UrinalysisProtein implements BaseRecord, Identifiable, org.cidrz.webapp.dynasite.valueobject.Auditable {

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
    private String urinalysisProtein1;
    private Time timeObservation2;
    private String urinalysisProtein2;
    private Time timeObservation3;
    private String urinalysisProtein3;
    private Time timeObservation4;
    private String urinalysisProtein4;
    private Time timeObservation5;
    private String urinalysisProtein5;
    private Time timeObservation6;
    private String urinalysisProtein6;
    private Time timeObservation7;
    private String urinalysisProtein7;
    private Time timeObservation8;
    private String urinalysisProtein8;
    private Time timeObservation9;
    private String urinalysisProtein9;
    private Time timeObservation10;
    private String urinalysisProtein10;

    private Time timeObservation11;
    private String urinalysisProtein11;
    private Time timeObservation12;
    private String urinalysisProtein12;
    private Time timeObservation13;
    private String urinalysisProtein13;
    private Time timeObservation14;
    private String urinalysisProtein14;
    private Time timeObservation15;
    private String urinalysisProtein15;
    private Time timeObservation16;
    private String urinalysisProtein16;
    private Time timeObservation17;
    private String urinalysisProtein17;
    private Time timeObservation18;
    private String urinalysisProtein18;
    private Time timeObservation19;
    private String urinalysisProtein19;
    private Time timeObservation20;
    private String urinalysisProtein20;

    private Time timeObservation21;
    private String urinalysisProtein21;
    private Time timeObservation22;
    private String urinalysisProtein22;
    private Time timeObservation23;
    private String urinalysisProtein23;
    private Time timeObservation24;
    private String urinalysisProtein24;

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
     * @hibernate.property length="4" column="urinalysisProtein_1"
     */
    public String getUrinalysisProtein1() {
        return urinalysisProtein1;
    }

    public void setUrinalysisProtein1(String urinalysisProtein1) {
        this.urinalysisProtein1 = urinalysisProtein1;
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
     * @hibernate.property length="4" column="urinalysisProtein_2"
     */

    public String getUrinalysisProtein2() {
        return urinalysisProtein2;
    }

    public void setUrinalysisProtein2(String urinalysisProtein2) {
        this.urinalysisProtein2 = urinalysisProtein2;
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
     * @hibernate.property length="4" column="urinalysisProtein_3"
     */

    public String getUrinalysisProtein3() {
        return urinalysisProtein3;
    }

    public void setUrinalysisProtein3(String urinalysisProtein3) {
        this.urinalysisProtein3 = urinalysisProtein3;
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
     * @hibernate.property length="4" column="urinalysisProtein_4"
     */

    public String getUrinalysisProtein4() {
        return urinalysisProtein4;
    }

    public void setUrinalysisProtein4(String urinalysisProtein4) {
        this.urinalysisProtein4 = urinalysisProtein4;
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
     * @hibernate.property length="4" column="urinalysisProtein_5"
     */

    public String getUrinalysisProtein5() {
        return urinalysisProtein5;
    }

    public void setUrinalysisProtein5(String urinalysisProtein5) {
        this.urinalysisProtein5 = urinalysisProtein5;
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
     * @hibernate.property length="4" column="urinalysisProtein_6"
     */

    public String getUrinalysisProtein6() {
        return urinalysisProtein6;
    }

    public void setUrinalysisProtein6(String urinalysisProtein6) {
        this.urinalysisProtein6 = urinalysisProtein6;
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
     * @hibernate.property length="4" column="urinalysisProtein_7"
     */

    public String getUrinalysisProtein7() {
        return urinalysisProtein7;
    }

    public void setUrinalysisProtein7(String urinalysisProtein7) {
        this.urinalysisProtein7 = urinalysisProtein7;
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
     * @hibernate.property length="4" column="urinalysisProtein_8"
     */

    public String getUrinalysisProtein8() {
        return urinalysisProtein8;
    }

    public void setUrinalysisProtein8(String urinalysisProtein8) {
        this.urinalysisProtein8 = urinalysisProtein8;
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
     * @hibernate.property length="4" column="urinalysisProtein_9"
     */

    public String getUrinalysisProtein9() {
        return urinalysisProtein9;
    }

    public void setUrinalysisProtein9(String urinalysisProtein9) {
        this.urinalysisProtein9 = urinalysisProtein9;
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
     * @hibernate.property length="4" column="urinalysisProtein_10"
     */

    public String getUrinalysisProtein10() {
        return urinalysisProtein10;
    }

    public void setUrinalysisProtein10(String urinalysisProtein10) {
        this.urinalysisProtein10 = urinalysisProtein10;
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
     * @hibernate.property length="4" column="urinalysisProtein_11"
     */

    public String getUrinalysisProtein11() {
        return urinalysisProtein11;
    }

    public void setUrinalysisProtein11(String urinalysisProtein11) {
        this.urinalysisProtein11 = urinalysisProtein11;
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
     * @hibernate.property length="4" column="urinalysisProtein_12"
     */

    public String getUrinalysisProtein12() {
        return urinalysisProtein12;
    }

    public void setUrinalysisProtein12(String urinalysisProtein12) {
        this.urinalysisProtein12 = urinalysisProtein12;
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
     * @hibernate.property length="4" column="urinalysisProtein_13"
     */

    public String getUrinalysisProtein13() {
        return urinalysisProtein13;
    }

    public void setUrinalysisProtein13(String urinalysisProtein13) {
        this.urinalysisProtein13 = urinalysisProtein13;
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
     * @hibernate.property length="4" column="urinalysisProtein_14"
     */

    public String getUrinalysisProtein14() {
        return urinalysisProtein14;
    }

    public void setUrinalysisProtein14(String urinalysisProtein14) {
        this.urinalysisProtein14 = urinalysisProtein14;
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
     * @hibernate.property length="4" column="urinalysisProtein_15"
     */

    public String getUrinalysisProtein15() {
        return urinalysisProtein15;
    }

    public void setUrinalysisProtein15(String urinalysisProtein15) {
        this.urinalysisProtein15 = urinalysisProtein15;
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
     * @hibernate.property length="4" column="urinalysisProtein_16"
     */

    public String getUrinalysisProtein16() {
        return urinalysisProtein16;
    }

    public void setUrinalysisProtein16(String urinalysisProtein16) {
        this.urinalysisProtein16 = urinalysisProtein16;
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
     * @hibernate.property length="4" column="urinalysisProtein_17"
     */

    public String getUrinalysisProtein17() {
        return urinalysisProtein17;
    }

    public void setUrinalysisProtein17(String urinalysisProtein17) {
        this.urinalysisProtein17 = urinalysisProtein17;
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
     * @hibernate.property length="4" column="urinalysisProtein_18"
     */
    public String getUrinalysisProtein18() {
        return urinalysisProtein18;
    }

    public void setUrinalysisProtein18(String urinalysisProtein18) {
        this.urinalysisProtein18 = urinalysisProtein18;
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
     * @hibernate.property length="4" column="urinalysisProtein_19"
     */
    public String getUrinalysisProtein19() {
        return urinalysisProtein19;
    }

    public void setUrinalysisProtein19(String urinalysisProtein19) {
        this.urinalysisProtein19 = urinalysisProtein19;
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
     * @hibernate.property length="4" column="urinalysisProtein_20"
     */
    public String getUrinalysisProtein20() {
        return urinalysisProtein20;
    }

    public void setUrinalysisProtein20(String urinalysisProtein20) {
        this.urinalysisProtein20 = urinalysisProtein20;
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
     * @hibernate.property length="4" column="urinalysisProtein_21"
     */
    public String getUrinalysisProtein21() {
        return urinalysisProtein21;
    }

    public void setUrinalysisProtein21(String urinalysisProtein21) {
        this.urinalysisProtein21 = urinalysisProtein21;
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
     * @hibernate.property length="4" column="urinalysisProtein_22"
     */
    public String getUrinalysisProtein22() {
        return urinalysisProtein22;
    }

    public void setUrinalysisProtein22(String urinalysisProtein22) {
        this.urinalysisProtein22 = urinalysisProtein22;
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
     * @hibernate.property length="4" column="urinalysisProtein_23"
     */
    public String getUrinalysisProtein23() {
        return urinalysisProtein23;
    }

    public void setUrinalysisProtein23(String urinalysisProtein23) {
        this.urinalysisProtein23 = urinalysisProtein23;
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
     * @hibernate.property length="4" column="urinalysisProtein_24"
     */
    public String getUrinalysisProtein24() {
        return urinalysisProtein24;
    }

    public void setUrinalysisProtein24(String urinalysisProtein24) {
        this.urinalysisProtein24 = urinalysisProtein24;
    }

}
