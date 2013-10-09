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
 * @hibernate.class table="parto_contractions"
 * mutable="true"
 */

public class Contractions implements BaseRecord, Identifiable, org.cidrz.webapp.dynasite.valueobject.Auditable {

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
    private String contractions1;
    private Time timeObservation2;
    private String contractions2;
    private Time timeObservation3;
    private String contractions3;
    private Time timeObservation4;
    private String contractions4;
    private Time timeObservation5;
    private String contractions5;
    private Time timeObservation6;
    private String contractions6;
    private Time timeObservation7;
    private String contractions7;
    private Time timeObservation8;
    private String contractions8;
    private Time timeObservation9;
    private String contractions9;
    private Time timeObservation10;
    private String contractions10;

    private Time timeObservation11;
    private String contractions11;
    private Time timeObservation12;
    private String contractions12;
    private Time timeObservation13;
    private String contractions13;
    private Time timeObservation14;
    private String contractions14;
    private Time timeObservation15;
    private String contractions15;
    private Time timeObservation16;
    private String contractions16;
    private Time timeObservation17;
    private String contractions17;
    private Time timeObservation18;
    private String contractions18;
    private Time timeObservation19;
    private String contractions19;
    private Time timeObservation20;
    private String contractions20;

    private Time timeObservation21;
    private String contractions21;
    private Time timeObservation22;
    private String contractions22;
    private Time timeObservation23;
    private String contractions23;
    private Time timeObservation24;
    private String contractions24;
    private Time timeObservation25;
    private String contractions25;
    private Time timeObservation26;
    private String contractions26;
    private Time timeObservation27;
    private String contractions27;
    private Time timeObservation28;
    private String contractions28;
    private Time timeObservation29;
    private String contractions29;
    private Time timeObservation30;
    private String contractions30;

    private Time timeObservation31;
    private String contractions31;
    private Time timeObservation32;
    private String contractions32;
    private Time timeObservation33;
    private String contractions33;
    private Time timeObservation34;
    private String contractions34;


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
     *  formerly orm mapping
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
     * @hibernate.property length="4" column="contractions_1"
     */
    public String getContractions1() {
        return contractions1;
    }

    public void setContractions1(String contractions1) {
        this.contractions1 = contractions1;
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
     * @hibernate.property length="4" column="contractions_2"
     */

    public String getContractions2() {
        return contractions2;
    }

    public void setContractions2(String contractions2) {
        this.contractions2 = contractions2;
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
     * @hibernate.property length="4" column="contractions_3"
     */

    public String getContractions3() {
        return contractions3;
    }

    public void setContractions3(String contractions3) {
        this.contractions3 = contractions3;
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
     * @hibernate.property length="4" column="contractions_4"
     */

    public String getContractions4() {
        return contractions4;
    }

    public void setContractions4(String contractions4) {
        this.contractions4 = contractions4;
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
     * @hibernate.property length="4" column="contractions_5"
     */

    public String getContractions5() {
        return contractions5;
    }

    public void setContractions5(String contractions5) {
        this.contractions5 = contractions5;
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
     * @hibernate.property length="4" column="contractions_6"
     */

    public String getContractions6() {
        return contractions6;
    }

    public void setContractions6(String contractions6) {
        this.contractions6 = contractions6;
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
     * @hibernate.property length="4" column="contractions_7"
     */

    public String getContractions7() {
        return contractions7;
    }

    public void setContractions7(String contractions7) {
        this.contractions7 = contractions7;
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
     * @hibernate.property length="4" column="contractions_8"
     */

    public String getContractions8() {
        return contractions8;
    }

    public void setContractions8(String contractions8) {
        this.contractions8 = contractions8;
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
     * @hibernate.property length="4" column="contractions_9"
     */

    public String getContractions9() {
        return contractions9;
    }

    public void setContractions9(String contractions9) {
        this.contractions9 = contractions9;
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
     * @hibernate.property length="4" column="contractions_10"
     */

    public String getContractions10() {
        return contractions10;
    }

    public void setContractions10(String contractions10) {
        this.contractions10 = contractions10;
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
     * @hibernate.property length="4" column="contractions_11"
     */

    public String getContractions11() {
        return contractions11;
    }

    public void setContractions11(String contractions11) {
        this.contractions11 = contractions11;
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
     * @hibernate.property length="4" column="contractions_12"
     */

    public String getContractions12() {
        return contractions12;
    }

    public void setContractions12(String contractions12) {
        this.contractions12 = contractions12;
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
     * @hibernate.property length="4" column="contractions_13"
     */

    public String getContractions13() {
        return contractions13;
    }

    public void setContractions13(String contractions13) {
        this.contractions13 = contractions13;
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
     * @hibernate.property length="4" column="contractions_14"
     */

    public String getContractions14() {
        return contractions14;
    }

    public void setContractions14(String contractions14) {
        this.contractions14 = contractions14;
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
     * @hibernate.property length="4" column="contractions_15"
     */

    public String getContractions15() {
        return contractions15;
    }

    public void setContractions15(String contractions15) {
        this.contractions15 = contractions15;
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
     * @hibernate.property length="4" column="contractions_16"
     */

    public String getContractions16() {
        return contractions16;
    }

    public void setContractions16(String contractions16) {
        this.contractions16 = contractions16;
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
     * @hibernate.property length="4" column="contractions_17"
     */

    public String getContractions17() {
        return contractions17;
    }

    public void setContractions17(String contractions17) {
        this.contractions17 = contractions17;
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
     * @hibernate.property length="4" column="contractions_18"
     */

    public String getContractions18() {
        return contractions18;
    }

    public void setContractions18(String contractions18) {
        this.contractions18 = contractions18;
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
     * @hibernate.property length="4" column="contractions_19"
     */

    public String getContractions19() {
        return contractions19;
    }

    public void setContractions19(String contractions19) {
        this.contractions19 = contractions19;
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
     * @hibernate.property length="4" column="contractions_20"
     */

    public String getContractions20() {
        return contractions20;
    }

    public void setContractions20(String contractions20) {
        this.contractions20 = contractions20;
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
     * @hibernate.property length="4" column="contractions_21"
     */

    public String getContractions21() {
        return contractions21;
    }

    public void setContractions21(String contractions21) {
        this.contractions21 = contractions21;
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
     * @hibernate.property length="4" column="contractions_22"
     */

    public String getContractions22() {
        return contractions22;
    }

    public void setContractions22(String contractions22) {
        this.contractions22 = contractions22;
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
     * @hibernate.property length="4" column="contractions_23"
     */

    public String getContractions23() {
        return contractions23;
    }

    public void setContractions23(String contractions23) {
        this.contractions23 = contractions23;
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
     * @hibernate.property length="4" column="contractions_24"
     */

    public String getContractions24() {
        return contractions24;
    }

    public void setContractions24(String contractions24) {
        this.contractions24 = contractions24;
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
     * @hibernate.property length="4" column="contractions_25"
     */

    public String getContractions25() {
        return contractions25;
    }

    public void setContractions25(String contractions25) {
        this.contractions25 = contractions25;
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
     * @hibernate.property length="4" column="contractions_26"
     */

    public String getContractions26() {
        return contractions26;
    }

    public void setContractions26(String contractions26) {
        this.contractions26 = contractions26;
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
     * @hibernate.property length="4" column="contractions_27"
     */

    public String getContractions27() {
        return contractions27;
    }

    public void setContractions27(String contractions27) {
        this.contractions27 = contractions27;
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
     * @hibernate.property length="4" column="contractions_28"
     */

    public String getContractions28() {
        return contractions28;
    }

    public void setContractions28(String contractions28) {
        this.contractions28 = contractions28;
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
     * @hibernate.property length="4" column="contractions_29"
     */

    public String getContractions29() {
        return contractions29;
    }

    public void setContractions29(String contractions29) {
        this.contractions29 = contractions29;
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
     * @hibernate.property length="4" column="contractions_30"
     */

    public String getContractions30() {
        return contractions30;
    }

    public void setContractions30(String contractions30) {
        this.contractions30 = contractions30;
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
     * @hibernate.property length="4" column="contractions_31"
     */

    public String getContractions31() {
        return contractions31;
    }

    public void setContractions31(String contractions31) {
        this.contractions31 = contractions31;
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
     * @hibernate.property length="4" column="contractions_32"
     */

    public String getContractions32() {
        return contractions32;
    }

    public void setContractions32(String contractions32) {
        this.contractions32 = contractions32;
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
     * @hibernate.property length="4" column="contractions_33"
     */

    public String getContractions33() {
        return contractions33;
    }

    public void setContractions33(String contractions33) {
        this.contractions33 = contractions33;
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
     * @hibernate.property length="4" column="contractions_34"
     */

    public String getContractions34() {
        return contractions34;
    }

    public void setContractions34(String contractions34) {
        this.contractions34 = contractions34;
    }



}
