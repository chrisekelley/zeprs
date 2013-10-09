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
 * @hibernate.class table="parto_urinalysis_acetone"
 * mutable="true"
 */

public class UrinalysisAcetone implements BaseRecord, Identifiable, org.cidrz.webapp.dynasite.valueobject.Auditable {

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
    private String urinalysisAcetone1;
    private Time timeObservation2;
    private String urinalysisAcetone2;
    private Time timeObservation3;
    private String urinalysisAcetone3;
    private Time timeObservation4;
    private String urinalysisAcetone4;
    private Time timeObservation5;
    private String urinalysisAcetone5;
    private Time timeObservation6;
    private String urinalysisAcetone6;
    private Time timeObservation7;
    private String urinalysisAcetone7;
    private Time timeObservation8;
    private String urinalysisAcetone8;
    private Time timeObservation9;
    private String urinalysisAcetone9;
    private Time timeObservation10;
    private String urinalysisAcetone10;

    private Time timeObservation11;
    private String urinalysisAcetone11;
    private Time timeObservation12;
    private String urinalysisAcetone12;
    private Time timeObservation13;
    private String urinalysisAcetone13;
    private Time timeObservation14;
    private String urinalysisAcetone14;
    private Time timeObservation15;
    private String urinalysisAcetone15;
    private Time timeObservation16;
    private String urinalysisAcetone16;
    private Time timeObservation17;
    private String urinalysisAcetone17;
    private Time timeObservation18;
    private String urinalysisAcetone18;
    private Time timeObservation19;
    private String urinalysisAcetone19;
    private Time timeObservation20;
    private String urinalysisAcetone20;

    private Time timeObservation21;
    private String urinalysisAcetone21;
    private Time timeObservation22;
    private String urinalysisAcetone22;
    private Time timeObservation23;
    private String urinalysisAcetone23;
    private Time timeObservation24;
    private String urinalysisAcetone24;

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
     * @hibernate.property length="4" column="urinalysisAcetone_1"
     */
    public String getUrinalysisAcetone1() {
        return urinalysisAcetone1;
    }

    public void setUrinalysisAcetone1(String urinalysisAcetone1) {
        this.urinalysisAcetone1 = urinalysisAcetone1;
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
     * @hibernate.property length="4" column="urinalysisAcetone_2"
     */

    public String getUrinalysisAcetone2() {
        return urinalysisAcetone2;
    }

    public void setUrinalysisAcetone2(String urinalysisAcetone2) {
        this.urinalysisAcetone2 = urinalysisAcetone2;
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
     * @hibernate.property length="4" column="urinalysisAcetone_3"
     */

    public String getUrinalysisAcetone3() {
        return urinalysisAcetone3;
    }

    public void setUrinalysisAcetone3(String urinalysisAcetone3) {
        this.urinalysisAcetone3 = urinalysisAcetone3;
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
     * @hibernate.property length="4" column="urinalysisAcetone_4"
     */

    public String getUrinalysisAcetone4() {
        return urinalysisAcetone4;
    }

    public void setUrinalysisAcetone4(String urinalysisAcetone4) {
        this.urinalysisAcetone4 = urinalysisAcetone4;
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
     * @hibernate.property length="4" column="urinalysisAcetone_5"
     */

    public String getUrinalysisAcetone5() {
        return urinalysisAcetone5;
    }

    public void setUrinalysisAcetone5(String urinalysisAcetone5) {
        this.urinalysisAcetone5 = urinalysisAcetone5;
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
     * @hibernate.property length="4" column="urinalysisAcetone_6"
     */

    public String getUrinalysisAcetone6() {
        return urinalysisAcetone6;
    }

    public void setUrinalysisAcetone6(String urinalysisAcetone6) {
        this.urinalysisAcetone6 = urinalysisAcetone6;
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
     * @hibernate.property length="4" column="urinalysisAcetone_7"
     */

    public String getUrinalysisAcetone7() {
        return urinalysisAcetone7;
    }

    public void setUrinalysisAcetone7(String urinalysisAcetone7) {
        this.urinalysisAcetone7 = urinalysisAcetone7;
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
     * @hibernate.property length="4" column="urinalysisAcetone_8"
     */

    public String getUrinalysisAcetone8() {
        return urinalysisAcetone8;
    }

    public void setUrinalysisAcetone8(String urinalysisAcetone8) {
        this.urinalysisAcetone8 = urinalysisAcetone8;
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
     * @hibernate.property length="4" column="urinalysisAcetone_9"
     */

    public String getUrinalysisAcetone9() {
        return urinalysisAcetone9;
    }

    public void setUrinalysisAcetone9(String urinalysisAcetone9) {
        this.urinalysisAcetone9 = urinalysisAcetone9;
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
     * @hibernate.property length="4" column="urinalysisAcetone_10"
     */

    public String getUrinalysisAcetone10() {
        return urinalysisAcetone10;
    }

    public void setUrinalysisAcetone10(String urinalysisAcetone10) {
        this.urinalysisAcetone10 = urinalysisAcetone10;
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
     * @hibernate.property length="4" column="urinalysisAcetone_11"
     */

    public String getUrinalysisAcetone11() {
        return urinalysisAcetone11;
    }

    public void setUrinalysisAcetone11(String urinalysisAcetone11) {
        this.urinalysisAcetone11 = urinalysisAcetone11;
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
     * @hibernate.property length="4" column="urinalysisAcetone_12"
     */

    public String getUrinalysisAcetone12() {
        return urinalysisAcetone12;
    }

    public void setUrinalysisAcetone12(String urinalysisAcetone12) {
        this.urinalysisAcetone12 = urinalysisAcetone12;
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
     * @hibernate.property length="4" column="urinalysisAcetone_13"
     */

    public String getUrinalysisAcetone13() {
        return urinalysisAcetone13;
    }

    public void setUrinalysisAcetone13(String urinalysisAcetone13) {
        this.urinalysisAcetone13 = urinalysisAcetone13;
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
     * @hibernate.property length="4" column="urinalysisAcetone_14"
     */

    public String getUrinalysisAcetone14() {
        return urinalysisAcetone14;
    }

    public void setUrinalysisAcetone14(String urinalysisAcetone14) {
        this.urinalysisAcetone14 = urinalysisAcetone14;
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
     * @hibernate.property length="4" column="urinalysisAcetone_15"
     */

    public String getUrinalysisAcetone15() {
        return urinalysisAcetone15;
    }

    public void setUrinalysisAcetone15(String urinalysisAcetone15) {
        this.urinalysisAcetone15 = urinalysisAcetone15;
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
     * @hibernate.property length="4" column="urinalysisAcetone_16"
     */

    public String getUrinalysisAcetone16() {
        return urinalysisAcetone16;
    }

    public void setUrinalysisAcetone16(String urinalysisAcetone16) {
        this.urinalysisAcetone16 = urinalysisAcetone16;
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
     * @hibernate.property length="4" column="urinalysisAcetone_17"
     */

    public String getUrinalysisAcetone17() {
        return urinalysisAcetone17;
    }

    public void setUrinalysisAcetone17(String urinalysisAcetone17) {
        this.urinalysisAcetone17 = urinalysisAcetone17;
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
     * @hibernate.property length="4" column="urinalysisAcetone_18"
     */
    public String getUrinalysisAcetone18() {
        return urinalysisAcetone18;
    }

    public void setUrinalysisAcetone18(String urinalysisAcetone18) {
        this.urinalysisAcetone18 = urinalysisAcetone18;
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
     * @hibernate.property length="4" column="urinalysisAcetone_19"
     */
    public String getUrinalysisAcetone19() {
        return urinalysisAcetone19;
    }

    public void setUrinalysisAcetone19(String urinalysisAcetone19) {
        this.urinalysisAcetone19 = urinalysisAcetone19;
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
     * @hibernate.property length="4" column="urinalysisAcetone_20"
     */
    public String getUrinalysisAcetone20() {
        return urinalysisAcetone20;
    }

    public void setUrinalysisAcetone20(String urinalysisAcetone20) {
        this.urinalysisAcetone20 = urinalysisAcetone20;
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
     * @hibernate.property length="4" column="urinalysisAcetone_21"
     */
    public String getUrinalysisAcetone21() {
        return urinalysisAcetone21;
    }

    public void setUrinalysisAcetone21(String urinalysisAcetone21) {
        this.urinalysisAcetone21 = urinalysisAcetone21;
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
     * @hibernate.property length="4" column="urinalysisAcetone_22"
     */
    public String getUrinalysisAcetone22() {
        return urinalysisAcetone22;
    }

    public void setUrinalysisAcetone22(String urinalysisAcetone22) {
        this.urinalysisAcetone22 = urinalysisAcetone22;
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
     * @hibernate.property length="4" column="urinalysisAcetone_23"
     */
    public String getUrinalysisAcetone23() {
        return urinalysisAcetone23;
    }

    public void setUrinalysisAcetone23(String urinalysisAcetone23) {
        this.urinalysisAcetone23 = urinalysisAcetone23;
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
     * @hibernate.property length="4" column="urinalysisAcetone_24"
     */
    public String getUrinalysisAcetone24() {
        return urinalysisAcetone24;
    }

    public void setUrinalysisAcetone24(String urinalysisAcetone24) {
        this.urinalysisAcetone24 = urinalysisAcetone24;
    }


}
