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
 * @hibernate.class table="parto_vaginal_exam"
 * mutable="true"
 */

public class VaginalExamParto implements BaseRecord, Identifiable, org.cidrz.webapp.dynasite.valueobject.Auditable {

    private Long id;
    private Long patientId;
    private Long pregnancyId;
    private Date dateVisit;
    private org.cidrz.webapp.dynasite.valueobject.AuditInfo auditInfo;

    private Time timeObservation1;
    private String station1;
    private String cord1;
    private String position1;
    private String vulva1;
    private String vagina1;

    private Time timeObservation2;
    private String station2;
    private String cord2;
    private String position2;
    private String vulva2;
    private String vagina2;


    private Time timeObservation3;
    private String station3;
    private String cord3;
    private String position3;
    private String vulva3;
    private String vagina3;

    private Time timeObservation4;
    private String station4;
    private String cord4;
    private String position4;
    private String vulva4;
    private String vagina4;

    private Time timeObservation5;
    private String station5;
    private String cord5;
    private String position5;
    private String vulva5;
    private String vagina5;

    private Time timeObservation6;
    private String station6;
    private String cord6;
    private String position6;
    private String vulva6;
    private String vagina6;
    private Timestamp lastModified;
    private String createdBy;
    private String lastModifiedBy;
    private Long siteId;
    private Timestamp created;
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
     * @hibernate.property column="station_1"
     */
    public String getStation1() {
        return station1;
    }

    public void setStation1(String station1) {
        this.station1 = station1;
    }

    /**
     * @return
     * @hibernate.property column="cord_1"
     */
    public String getCord1() {
        return cord1;
    }

    public void setCord1(String cord1) {
        this.cord1 = cord1;
    }


    /**
     * @return
     * @hibernate.property column="position_1"
     */
    public String getPosition1() {
        return position1;
    }

    public void setPosition1(String position1) {
        this.position1 = position1;
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
     * @hibernate.property column="station_2"
     */
    public String getStation2() {
        return station2;
    }

    public void setStation2(String station2) {
        this.station2 = station2;
    }

    /**
     * @return
     * @hibernate.property column="cord_2"
     */
    public String getCord2() {
        return cord2;
    }

    public void setCord2(String cord2) {
        this.cord2 = cord2;
    }

    /**
     * @return
     * @hibernate.property column="position_2"
     */

    public String getPosition2() {
        return position2;
    }

    public void setPosition2(String position2) {
        this.position2 = position2;
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
     * @hibernate.property column="station_3"
     */
    public String getStation3() {
        return station3;
    }

    public void setStation3(String station3) {
        this.station3 = station3;
    }

    /**
     * @return
     * @hibernate.property column="cord_3"
     */
    public String getCord3() {
        return cord3;
    }

    public void setCord3(String cord3) {
        this.cord3 = cord3;
    }

    /**
     * @return
     * @hibernate.property column="position_3"
     */
    public String getPosition3() {
        return position3;
    }

    public void setPosition3(String position3) {
        this.position3 = position3;
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
     * @hibernate.property column="station_4"
     */
    public String getStation4() {
        return station4;
    }

    public void setStation4(String station4) {
        this.station4 = station4;
    }

    /**
     * @return
     * @hibernate.property column="cord_4"
     */
    public String getCord4() {
        return cord4;
    }

    public void setCord4(String cord4) {
        this.cord4 = cord4;
    }

    /**
     * @return
     * @hibernate.property column="position_4"
     */
    public String getPosition4() {
        return position4;
    }

    public void setPosition4(String position4) {
        this.position4 = position4;
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
     * @hibernate.property column="station_5"
     */
    public String getStation5() {
        return station5;
    }

    public void setStation5(String station5) {
        this.station5 = station5;
    }

    /**
     * @return
     * @hibernate.property column="cord_5"
     */
    public String getCord5() {
        return cord5;
    }

    public void setCord5(String cord5) {
        this.cord5 = cord5;
    }

    /**
     * @return
     * @hibernate.property column="position_5"
     */
    public String getPosition5() {
        return position5;
    }

    public void setPosition5(String position5) {
        this.position5 = position5;
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
     * @hibernate.property column="station_6"
     */
    public String getStation6() {
        return station6;
    }

    public void setStation6(String station6) {
        this.station6 = station6;
    }

    /**
     * @return
     * @hibernate.property column="cord_6"
     */
    public String getCord6() {
        return cord6;
    }

    public void setCord6(String cord6) {
        this.cord6 = cord6;
    }

    /**
     * @return
     * @hibernate.property column="position_6"
     */
    public String getPosition6() {
        return position6;
    }

    public void setPosition6(String position6) {
        this.position6 = position6;
    }

    /**
     * @return
     * @hibernate.property column="vulva_1"
     */
    public String getVulva1() {
        return vulva1;
    }

    public void setVulva1(String vulva1) {
        this.vulva1 = vulva1;
    }

    /**
     * @return
     * @hibernate.property column="vagina_1"
     */
    public String getVagina1() {
        return vagina1;
    }

    public void setVagina1(String vagina1) {
        this.vagina1 = vagina1;
    }

    /**
     * @return
     * @hibernate.property column="vulva_2"
     */
    public String getVulva2() {
        return vulva2;
    }

    public void setVulva2(String vulva2) {
        this.vulva2 = vulva2;
    }

    /**
     * @return
     * @hibernate.property column="vagina_2"
     */
    public String getVagina2() {
        return vagina2;
    }

    public void setVagina2(String vagina2) {
        this.vagina2 = vagina2;
    }

    /**
     * @return
     * @hibernate.property column="vulva_3"
     */
    public String getVulva3() {
        return vulva3;
    }

    public void setVulva3(String vulva3) {
        this.vulva3 = vulva3;
    }

    /**
     * @return
     * @hibernate.property column="vagina_3"
     */
    public String getVagina3() {
        return vagina3;
    }

    public void setVagina3(String vagina3) {
        this.vagina3 = vagina3;
    }

    /**
     * @return
     * @hibernate.property column="vulva_4"
     */
    public String getVulva4() {
        return vulva4;
    }

    public void setVulva4(String vulva4) {
        this.vulva4 = vulva4;
    }

    /**
     * @return
     * @hibernate.property column="vagina_4"
     */
    public String getVagina4() {
        return vagina4;
    }

    public void setVagina4(String vagina4) {
        this.vagina4 = vagina4;
    }

    /**
     * @return
     * @hibernate.property column="vulva_5"
     */
    public String getVulva5() {
        return vulva5;
    }

    public void setVulva5(String vulva5) {
        this.vulva5 = vulva5;
    }

    /**
     * @return
     * @hibernate.property column="vagina_5"
     */
    public String getVagina5() {
        return vagina5;
    }

    public void setVagina5(String vagina5) {
        this.vagina5 = vagina5;
    }

    /**
     * @return
     * @hibernate.property column="vulva_6"
     */
    public String getVulva6() {
        return vulva6;
    }

    public void setVulva6(String vulva6) {
        this.vulva6 = vulva6;
    }

    /**
     * @return
     * @hibernate.property column="vagina_6"
     */
    public String getVagina6() {
        return vagina6;
    }

    public void setVagina6(String vagina6) {
        this.vagina6 = vagina6;
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
