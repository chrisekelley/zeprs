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
 * @hibernate.class table="parto_descent"
 * mutable="true"
 */

public class Descent implements BaseRecord, Identifiable, org.cidrz.webapp.dynasite.valueobject.Auditable {

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
    private Integer descent1;
    private Time timeObservation2;
    private Integer descent2;
    private Time timeObservation3;
    private Integer descent3;
    private Time timeObservation4;
    private Integer descent4;
    private Time timeObservation5;
    private Integer descent5;
    private Time timeObservation6;
    private Integer descent6;
    private Time timeObservation7;
    private Integer descent7;
    private Time timeObservation8;
    private Integer descent8;
    private Time timeObservation9;
    private Integer descent9;
    private Time timeObservation10;
    private Integer descent10;

    private Time timeObservation11;
    private Integer descent11;
    private Time timeObservation12;
    private Integer descent12;
    private Time timeObservation13;
    private Integer descent13;
    private Time timeObservation14;
    private Integer descent14;
    private Time timeObservation15;
    private Integer descent15;
    private Time timeObservation16;
    private Integer descent16;
    private Time timeObservation17;
    private Integer descent17;
    private Time timeObservation18;
    private Integer descent18;
    private Time timeObservation19;
    private Integer descent19;
    private Time timeObservation20;
    private Integer descent20;
    private Time timeObservation21;
    private Integer descent21;
    private Time timeObservation22;
    private Integer descent22;
    private Time timeObservation23;
    private Integer descent23;
    private Time timeObservation24;
    private Integer descent24;


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
     * @hibernate.property column="descent_1"
     */
    public Integer getDescent1() {
        return descent1;
    }

    public void setDescent1(Integer descent1) {
        this.descent1 = descent1;
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
     * @hibernate.property column="descent_2"
     */

    public Integer getDescent2() {
        return descent2;
    }

    public void setDescent2(Integer descent2) {
        this.descent2 = descent2;
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
     * @hibernate.property column="descent_3"
     */

    public Integer getDescent3() {
        return descent3;
    }

    public void setDescent3(Integer descent3) {
        this.descent3 = descent3;
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
     * @hibernate.property column="descent_4"
     */

    public Integer getDescent4() {
        return descent4;
    }

    public void setDescent4(Integer descent4) {
        this.descent4 = descent4;
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
     * @hibernate.property column="descent_5"
     */

    public Integer getDescent5() {
        return descent5;
    }

    public void setDescent5(Integer descent5) {
        this.descent5 = descent5;
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
     * @hibernate.property column="descent_6"
     */

    public Integer getDescent6() {
        return descent6;
    }

    public void setDescent6(Integer descent6) {
        this.descent6 = descent6;
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
     * @hibernate.property column="descent_7"
     */

    public Integer getDescent7() {
        return descent7;
    }

    public void setDescent7(Integer descent7) {
        this.descent7 = descent7;
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
     * @hibernate.property column="descent_8"
     */

    public Integer getDescent8() {
        return descent8;
    }

    public void setDescent8(Integer descent8) {
        this.descent8 = descent8;
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
     * @hibernate.property column="descent_9"
     */

    public Integer getDescent9() {
        return descent9;
    }

    public void setDescent9(Integer descent9) {
        this.descent9 = descent9;
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
     * @hibernate.property column="descent_10"
     */

    public Integer getDescent10() {
        return descent10;
    }

    public void setDescent10(Integer descent10) {
        this.descent10 = descent10;
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
     * @hibernate.property column="descent_11"
     */

    public Integer getDescent11() {
        return descent11;
    }

    public void setDescent11(Integer descent11) {
        this.descent11 = descent11;
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
     * @hibernate.property column="descent_12"
     */

    public Integer getDescent12() {
        return descent12;
    }

    public void setDescent12(Integer descent12) {
        this.descent12 = descent12;
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
     * @hibernate.property column="descent_13"
     */

    public Integer getDescent13() {
        return descent13;
    }

    public void setDescent13(Integer descent13) {
        this.descent13 = descent13;
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
     * @hibernate.property column="descent_14"
     */

    public Integer getDescent14() {
        return descent14;
    }

    public void setDescent14(Integer descent14) {
        this.descent14 = descent14;
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
     * @hibernate.property column="descent_15"
     */

    public Integer getDescent15() {
        return descent15;
    }

    public void setDescent15(Integer descent15) {
        this.descent15 = descent15;
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
     * @hibernate.property column="descent_16"
     */

    public Integer getDescent16() {
        return descent16;
    }

    public void setDescent16(Integer descent16) {
        this.descent16 = descent16;
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
     * @hibernate.property column="descent_17"
     */

    public Integer getDescent17() {
        return descent17;
    }

    public void setDescent17(Integer descent17) {
        this.descent17 = descent17;
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
     * @hibernate.property column="descent_18"
     */
    public Integer getDescent18() {
        return descent18;
    }

    public void setDescent18(Integer descent18) {
        this.descent18 = descent18;
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
     * @hibernate.property column="descent_19"
     */
    public Integer getDescent19() {
        return descent19;
    }

    public void setDescent19(Integer descent19) {
        this.descent19 = descent19;
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
     * @hibernate.property column="descent_20"
     */
    public Integer getDescent20() {
        return descent20;
    }

    public void setDescent20(Integer descent20) {
        this.descent20 = descent20;
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
     * @hibernate.property column="descent_21"
     */
    public Integer getDescent21() {
        return descent21;
    }

    public void setDescent21(Integer descent21) {
        this.descent21 = descent21;
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
     * @hibernate.property column="descent_22"
     */
    public Integer getDescent22() {
        return descent22;
    }

    public void setDescent22(Integer descent22) {
        this.descent22 = descent22;
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
     * @hibernate.property column="descent_23"
     */
    public Integer getDescent23() {
        return descent23;
    }

    public void setDescent23(Integer descent23) {
        this.descent23 = descent23;
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
     * @hibernate.property column="descent_24"
     */
    public Integer getDescent24() {
        return descent24;
    }

    public void setDescent24(Integer descent24) {
        this.descent24 = descent24;
    }

}
