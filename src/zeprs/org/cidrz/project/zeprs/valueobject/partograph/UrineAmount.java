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
 * @hibernate.class table="parto_urine_amount"
 * mutable="true"
 */

public class UrineAmount implements BaseRecord, Identifiable, org.cidrz.webapp.dynasite.valueobject.Auditable {

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
    private Integer urineAmount1;
    private Time timeObservation2;
    private Integer urineAmount2;
    private Time timeObservation3;
    private Integer urineAmount3;
    private Time timeObservation4;
    private Integer urineAmount4;
    private Time timeObservation5;
    private Integer urineAmount5;
    private Time timeObservation6;
    private Integer urineAmount6;
    private Time timeObservation7;
    private Integer urineAmount7;
    private Time timeObservation8;
    private Integer urineAmount8;
    private Time timeObservation9;
    private Integer urineAmount9;
    private Time timeObservation10;
    private Integer urineAmount10;

    private Time timeObservation11;
    private Integer urineAmount11;
    private Time timeObservation12;
    private Integer urineAmount12;
    private Time timeObservation13;
    private Integer urineAmount13;
    private Time timeObservation14;
    private Integer urineAmount14;
    private Time timeObservation15;
    private Integer urineAmount15;
    private Time timeObservation16;
    private Integer urineAmount16;
    private Time timeObservation17;
    private Integer urineAmount17;
    private Time timeObservation18;
    private Integer urineAmount18;
    private Time timeObservation19;
    private Integer urineAmount19;
    private Time timeObservation20;
    private Integer urineAmount20;

    private Time timeObservation21;
    private Integer urineAmount21;
    private Time timeObservation22;
    private Integer urineAmount22;
    private Time timeObservation23;
    private Integer urineAmount23;
    private Time timeObservation24;
    private Integer urineAmount24;


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
     * @hibernate.property column="urineAmount_1"
     */
    public Integer getUrineAmount1() {
        return urineAmount1;
    }

    public void setUrineAmount1(Integer urineAmount1) {
        this.urineAmount1 = urineAmount1;
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
     * @hibernate.property column="urineAmount_2"
     */

    public Integer getUrineAmount2() {
        return urineAmount2;
    }

    public void setUrineAmount2(Integer urineAmount2) {
        this.urineAmount2 = urineAmount2;
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
     * @hibernate.property column="urineAmount_3"
     */

    public Integer getUrineAmount3() {
        return urineAmount3;
    }

    public void setUrineAmount3(Integer urineAmount3) {
        this.urineAmount3 = urineAmount3;
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
     * @hibernate.property column="urineAmount_4"
     */

    public Integer getUrineAmount4() {
        return urineAmount4;
    }

    public void setUrineAmount4(Integer urineAmount4) {
        this.urineAmount4 = urineAmount4;
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
     * @hibernate.property column="urineAmount_5"
     */

    public Integer getUrineAmount5() {
        return urineAmount5;
    }

    public void setUrineAmount5(Integer urineAmount5) {
        this.urineAmount5 = urineAmount5;
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
     * @hibernate.property column="urineAmount_6"
     */

    public Integer getUrineAmount6() {
        return urineAmount6;
    }

    public void setUrineAmount6(Integer urineAmount6) {
        this.urineAmount6 = urineAmount6;
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
     * @hibernate.property column="urineAmount_7"
     */

    public Integer getUrineAmount7() {
        return urineAmount7;
    }

    public void setUrineAmount7(Integer urineAmount7) {
        this.urineAmount7 = urineAmount7;
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
     * @hibernate.property column="urineAmount_8"
     */

    public Integer getUrineAmount8() {
        return urineAmount8;
    }

    public void setUrineAmount8(Integer urineAmount8) {
        this.urineAmount8 = urineAmount8;
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
     * @hibernate.property column="urineAmount_9"
     */

    public Integer getUrineAmount9() {
        return urineAmount9;
    }

    public void setUrineAmount9(Integer urineAmount9) {
        this.urineAmount9 = urineAmount9;
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
     * @hibernate.property column="urineAmount_10"
     */

    public Integer getUrineAmount10() {
        return urineAmount10;
    }

    public void setUrineAmount10(Integer urineAmount10) {
        this.urineAmount10 = urineAmount10;
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
     * @hibernate.property column="urineAmount_11"
     */

    public Integer getUrineAmount11() {
        return urineAmount11;
    }

    public void setUrineAmount11(Integer urineAmount11) {
        this.urineAmount11 = urineAmount11;
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
     * @hibernate.property column="urineAmount_12"
     */

    public Integer getUrineAmount12() {
        return urineAmount12;
    }

    public void setUrineAmount12(Integer urineAmount12) {
        this.urineAmount12 = urineAmount12;
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
     * @hibernate.property column="urineAmount_13"
     */

    public Integer getUrineAmount13() {
        return urineAmount13;
    }

    public void setUrineAmount13(Integer urineAmount13) {
        this.urineAmount13 = urineAmount13;
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
     * @hibernate.property column="urineAmount_14"
     */

    public Integer getUrineAmount14() {
        return urineAmount14;
    }

    public void setUrineAmount14(Integer urineAmount14) {
        this.urineAmount14 = urineAmount14;
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
     * @hibernate.property column="urineAmount_15"
     */

    public Integer getUrineAmount15() {
        return urineAmount15;
    }

    public void setUrineAmount15(Integer urineAmount15) {
        this.urineAmount15 = urineAmount15;
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
     * @hibernate.property column="urineAmount_16"
     */

    public Integer getUrineAmount16() {
        return urineAmount16;
    }

    public void setUrineAmount16(Integer urineAmount16) {
        this.urineAmount16 = urineAmount16;
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
     * @hibernate.property column="urineAmount_17"
     */

    public Integer getUrineAmount17() {
        return urineAmount17;
    }

    public void setUrineAmount17(Integer urineAmount17) {
        this.urineAmount17 = urineAmount17;
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
     * @hibernate.property column="urineAmount_18"
     */
    public Integer getUrineAmount18() {
        return urineAmount18;
    }

    public void setUrineAmount18(Integer urineAmount18) {
        this.urineAmount18 = urineAmount18;
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
     * @hibernate.property column="urineAmount_19"
     */
    public Integer getUrineAmount19() {
        return urineAmount19;
    }

    public void setUrineAmount19(Integer urineAmount19) {
        this.urineAmount19 = urineAmount19;
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
     * @hibernate.property column="urineAmount_20"
     */
    public Integer getUrineAmount20() {
        return urineAmount20;
    }

    public void setUrineAmount20(Integer urineAmount20) {
        this.urineAmount20 = urineAmount20;
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
     * @hibernate.property column="urineAmount_21"
     */
    public Integer getUrineAmount21() {
        return urineAmount21;
    }

    public void setUrineAmount21(Integer urineAmount21) {
        this.urineAmount21 = urineAmount21;
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
     * @hibernate.property column="urineAmount_22"
     */
    public Integer getUrineAmount22() {
        return urineAmount22;
    }

    public void setUrineAmount22(Integer urineAmount22) {
        this.urineAmount22 = urineAmount22;
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
     * @hibernate.property column="urineAmount_23"
     */
    public Integer getUrineAmount23() {
        return urineAmount23;
    }

    public void setUrineAmount23(Integer urineAmount23) {
        this.urineAmount23 = urineAmount23;
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
     * @hibernate.property column="urineAmount_24"
     */
    public Integer getUrineAmount24() {
        return urineAmount24;
    }

    public void setUrineAmount24(Integer urineAmount24) {
        this.urineAmount24 = urineAmount24;
    }
}
