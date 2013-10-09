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
 * @hibernate.class table="parto_drugs_dispensed"
 * mutable="true"
 */

public class DrugsDispensed implements BaseRecord, Identifiable, org.cidrz.webapp.dynasite.valueobject.Auditable {

    private Long id;
    private Long patientId;
    private Long pregnancyId;
    private Date dateVisit;
    private org.cidrz.webapp.dynasite.valueobject.AuditInfo auditInfo;
    private Time timeObservation1;
    private String drugsDispensed1;
    private Time timeObservation2;
    private String drugsDispensed2;
    private Time timeObservation3;
    private String drugsDispensed3;
    private Time timeObservation4;
    private String drugsDispensed4;
    private Time timeObservation5;
    private String drugsDispensed5;
    private Time timeObservation6;
    private String drugsDispensed6;
    private Time timeObservation7;
    private String drugsDispensed7;
    private Time timeObservation8;
    private String drugsDispensed8;
    private Time timeObservation9;
    private String drugsDispensed9;
    private Time timeObservation10;
    private String drugsDispensed10;

    private Time timeObservation11;
    private String drugsDispensed11;
    private Time timeObservation12;
    private String drugsDispensed12;
    private Time timeObservation13;
    private String drugsDispensed13;
    private Time timeObservation14;
    private String drugsDispensed14;
    private Time timeObservation15;
    private String drugsDispensed15;
    private Time timeObservation16;
    private String drugsDispensed16;
    private Time timeObservation17;
    private String drugsDispensed17;
    private Timestamp created;
    private String createdBy;
    private Timestamp lastModified;
    private String lastModifiedBy;
    private Long siteId;
    private Long importId;

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
     * @hibernate.property column="drugsDispensed_1"
     */
    public String getDrugsDispensed1() {
        return drugsDispensed1;
    }

    public void setDrugsDispensed1(String drugsDispensed1) {
        this.drugsDispensed1 = drugsDispensed1;
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
     * @hibernate.property column="drugsDispensed_2"
     */

    public String getDrugsDispensed2() {
        return drugsDispensed2;
    }

    public void setDrugsDispensed2(String drugsDispensed2) {
        this.drugsDispensed2 = drugsDispensed2;
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
     * @hibernate.property column="drugsDispensed_3"
     */

    public String getDrugsDispensed3() {
        return drugsDispensed3;
    }

    public void setDrugsDispensed3(String drugsDispensed3) {
        this.drugsDispensed3 = drugsDispensed3;
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
     * @hibernate.property column="drugsDispensed_4"
     */

    public String getDrugsDispensed4() {
        return drugsDispensed4;
    }

    public void setDrugsDispensed4(String drugsDispensed4) {
        this.drugsDispensed4 = drugsDispensed4;
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
     * @hibernate.property column="drugsDispensed_5"
     */

    public String getDrugsDispensed5() {
        return drugsDispensed5;
    }

    public void setDrugsDispensed5(String drugsDispensed5) {
        this.drugsDispensed5 = drugsDispensed5;
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
     * @hibernate.property column="drugsDispensed_6"
     */

    public String getDrugsDispensed6() {
        return drugsDispensed6;
    }

    public void setDrugsDispensed6(String drugsDispensed6) {
        this.drugsDispensed6 = drugsDispensed6;
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
     * @hibernate.property column="drugsDispensed_7"
     */

    public String getDrugsDispensed7() {
        return drugsDispensed7;
    }

    public void setDrugsDispensed7(String drugsDispensed7) {
        this.drugsDispensed7 = drugsDispensed7;
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
     * @hibernate.property column="drugsDispensed_8"
     */

    public String getDrugsDispensed8() {
        return drugsDispensed8;
    }

    public void setDrugsDispensed8(String drugsDispensed8) {
        this.drugsDispensed8 = drugsDispensed8;
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
     * @hibernate.property column="drugsDispensed_9"
     */

    public String getDrugsDispensed9() {
        return drugsDispensed9;
    }

    public void setDrugsDispensed9(String drugsDispensed9) {
        this.drugsDispensed9 = drugsDispensed9;
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
     * @hibernate.property column="drugsDispensed_10"
     */

    public String getDrugsDispensed10() {
        return drugsDispensed10;
    }

    public void setDrugsDispensed10(String drugsDispensed10) {
        this.drugsDispensed10 = drugsDispensed10;
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
     * @hibernate.property column="drugsDispensed_11"
     */

    public String getDrugsDispensed11() {
        return drugsDispensed11;
    }

    public void setDrugsDispensed11(String drugsDispensed11) {
        this.drugsDispensed11 = drugsDispensed11;
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
     * @hibernate.property column="drugsDispensed_12"
     */

    public String getDrugsDispensed12() {
        return drugsDispensed12;
    }

    public void setDrugsDispensed12(String drugsDispensed12) {
        this.drugsDispensed12 = drugsDispensed12;
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
     * @hibernate.property column="drugsDispensed_13"
     */

    public String getDrugsDispensed13() {
        return drugsDispensed13;
    }

    public void setDrugsDispensed13(String drugsDispensed13) {
        this.drugsDispensed13 = drugsDispensed13;
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
     * @hibernate.property column="drugsDispensed_14"
     */

    public String getDrugsDispensed14() {
        return drugsDispensed14;
    }

    public void setDrugsDispensed14(String drugsDispensed14) {
        this.drugsDispensed14 = drugsDispensed14;
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
     * @hibernate.property column="drugsDispensed_15"
     */

    public String getDrugsDispensed15() {
        return drugsDispensed15;
    }

    public void setDrugsDispensed15(String drugsDispensed15) {
        this.drugsDispensed15 = drugsDispensed15;
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
     * @hibernate.property column="drugsDispensed_16"
     */

    public String getDrugsDispensed16() {
        return drugsDispensed16;
    }

    public void setDrugsDispensed16(String drugsDispensed16) {
        this.drugsDispensed16 = drugsDispensed16;
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
     * @hibernate.property column="drugsDispensed_17"
     */

    public String getDrugsDispensed17() {
        return drugsDispensed17;
    }

    public void setDrugsDispensed17(String drugsDispensed17) {
        this.drugsDispensed17 = drugsDispensed17;
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

}
