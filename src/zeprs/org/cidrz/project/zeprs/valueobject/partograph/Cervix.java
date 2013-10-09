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
 * @hibernate.class table="parto_cervix"
 * mutable="true"
 */

public class Cervix implements BaseRecord, Identifiable, org.cidrz.webapp.dynasite.valueobject.Auditable {

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
    private Integer actionStartRow;
    private Integer actionStartColumn;
    private Time timeObservation1;
    private Integer cervix1;
    private Time timeObservation2;
    private Integer cervix2;
    private Time timeObservation3;
    private Integer cervix3;
    private Time timeObservation4;
    private Integer cervix4;
    private Time timeObservation5;
    private Integer cervix5;
    private Time timeObservation6;
    private Integer cervix6;
    private Time timeObservation7;
    private Integer cervix7;
    private Time timeObservation8;
    private Integer cervix8;
    private Time timeObservation9;
    private Integer cervix9;
    private Time timeObservation10;
    private Integer cervix10;

    private Time timeObservation11;
    private Integer cervix11;
    private Time timeObservation12;
    private Integer cervix12;
    private Time timeObservation13;
    private Integer cervix13;
    private Time timeObservation14;
    private Integer cervix14;
    private Time timeObservation15;
    private Integer cervix15;
    private Time timeObservation16;
    private Integer cervix16;
    private Time timeObservation17;
    private Integer cervix17;
    private Time timeObservation18;
    private Integer cervix18;
    private Time timeObservation19;
    private Integer cervix19;
    private Time timeObservation20;
    private Integer cervix20;
    private Time timeObservation21;
    private Integer cervix21;
    private Time timeObservation22;
    private Integer cervix22;
    private Time timeObservation23;
    private Integer cervix23;
    private Time timeObservation24;
    private Integer cervix24;


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
     * @hibernate.property column="action_start_row"
     */
    public Integer getActionStartRow() {
        return actionStartRow;
    }

    public void setActionStartRow(Integer actionStartRow) {
        this.actionStartRow = actionStartRow;
    }

     /**
     * @return
     * @hibernate.property column="action_start_col"
     */
    public Integer getActionStartColumn() {
        return actionStartColumn;
    }

    public void setActionStartColumn(Integer actionStartColumn) {
        this.actionStartColumn = actionStartColumn;
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
     * @hibernate.property column="cervix_1"
     */
    public Integer getCervix1() {
        return cervix1;
    }

    public void setCervix1(Integer cervix1) {
        this.cervix1 = cervix1;
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
     * @hibernate.property column="cervix_2"
     */

    public Integer getCervix2() {
        return cervix2;
    }

    public void setCervix2(Integer cervix2) {
        this.cervix2 = cervix2;
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
     * @hibernate.property column="cervix_3"
     */

    public Integer getCervix3() {
        return cervix3;
    }

    public void setCervix3(Integer cervix3) {
        this.cervix3 = cervix3;
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
     * @hibernate.property column="cervix_4"
     */

    public Integer getCervix4() {
        return cervix4;
    }

    public void setCervix4(Integer cervix4) {
        this.cervix4 = cervix4;
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
     * @hibernate.property column="cervix_5"
     */

    public Integer getCervix5() {
        return cervix5;
    }

    public void setCervix5(Integer cervix5) {
        this.cervix5 = cervix5;
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
     * @hibernate.property column="cervix_6"
     */

    public Integer getCervix6() {
        return cervix6;
    }

    public void setCervix6(Integer cervix6) {
        this.cervix6 = cervix6;
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
     * @hibernate.property column="cervix_7"
     */

    public Integer getCervix7() {
        return cervix7;
    }

    public void setCervix7(Integer cervix7) {
        this.cervix7 = cervix7;
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
     * @hibernate.property column="cervix_8"
     */

    public Integer getCervix8() {
        return cervix8;
    }

    public void setCervix8(Integer cervix8) {
        this.cervix8 = cervix8;
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
     * @hibernate.property column="cervix_9"
     */

    public Integer getCervix9() {
        return cervix9;
    }

    public void setCervix9(Integer cervix9) {
        this.cervix9 = cervix9;
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
     * @hibernate.property column="cervix_10"
     */

    public Integer getCervix10() {
        return cervix10;
    }

    public void setCervix10(Integer cervix10) {
        this.cervix10 = cervix10;
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
     * @hibernate.property column="cervix_11"
     */

    public Integer getCervix11() {
        return cervix11;
    }

    public void setCervix11(Integer cervix11) {
        this.cervix11 = cervix11;
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
     * @hibernate.property column="cervix_12"
     */

    public Integer getCervix12() {
        return cervix12;
    }

    public void setCervix12(Integer cervix12) {
        this.cervix12 = cervix12;
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
     * @hibernate.property column="cervix_13"
     */

    public Integer getCervix13() {
        return cervix13;
    }

    public void setCervix13(Integer cervix13) {
        this.cervix13 = cervix13;
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
     * @hibernate.property column="cervix_14"
     */

    public Integer getCervix14() {
        return cervix14;
    }

    public void setCervix14(Integer cervix14) {
        this.cervix14 = cervix14;
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
     * @hibernate.property column="cervix_15"
     */

    public Integer getCervix15() {
        return cervix15;
    }

    public void setCervix15(Integer cervix15) {
        this.cervix15 = cervix15;
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
     * @hibernate.property column="cervix_16"
     */

    public Integer getCervix16() {
        return cervix16;
    }

    public void setCervix16(Integer cervix16) {
        this.cervix16 = cervix16;
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
     * @hibernate.property column="cervix_17"
     */

    public Integer getCervix17() {
        return cervix17;
    }

    public void setCervix17(Integer cervix17) {
        this.cervix17 = cervix17;
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
     * @hibernate.property column="cervix_18"
     */
    public Integer getCervix18() {
        return cervix18;
    }

    public void setCervix18(Integer cervix18) {
        this.cervix18 = cervix18;
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
     * @hibernate.property column="cervix_19"
     */
    public Integer getCervix19() {
        return cervix19;
    }

    public void setCervix19(Integer cervix19) {
        this.cervix19 = cervix19;
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
     * @hibernate.property column="cervix_20"
     */
    public Integer getCervix20() {
        return cervix20;
    }

    public void setCervix20(Integer cervix20) {
        this.cervix20 = cervix20;
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
     * @hibernate.property column="cervix_21"
     */
    public Integer getCervix21() {
        return cervix21;
    }

    public void setCervix21(Integer cervix21) {
        this.cervix21 = cervix21;
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
     * @hibernate.property column="cervix_22"
     */
    public Integer getCervix22() {
        return cervix22;
    }

    public void setCervix22(Integer cervix22) {
        this.cervix22 = cervix22;
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
     * @hibernate.property column="cervix_23"
     */
    public Integer getCervix23() {
        return cervix23;
    }

    public void setCervix23(Integer cervix23) {
        this.cervix23 = cervix23;
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
     * @hibernate.property column="cervix_24"
     */
    public Integer getCervix24() {
        return cervix24;
    }

    public void setCervix24(Integer cervix24) {
        this.cervix24 = cervix24;
    }
}
