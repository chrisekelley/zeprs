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
 * @hibernate.class table="parto_pulse"
 * mutable="true"
 */

public class Pulse implements BaseRecord, Identifiable, org.cidrz.webapp.dynasite.valueobject.Auditable {

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
    private Integer pulse1;
    private Time timeObservation2;
    private Integer pulse2;
    private Time timeObservation3;
    private Integer pulse3;
    private Time timeObservation4;
    private Integer pulse4;
    private Time timeObservation5;
    private Integer pulse5;
    private Time timeObservation6;
    private Integer pulse6;
    private Time timeObservation7;
    private Integer pulse7;
    private Time timeObservation8;
    private Integer pulse8;
    private Time timeObservation9;
    private Integer pulse9;
    private Time timeObservation10;
    private Integer pulse10;

    private Time timeObservation11;
    private Integer pulse11;
    private Time timeObservation12;
    private Integer pulse12;
    private Time timeObservation13;
    private Integer pulse13;
    private Time timeObservation14;
    private Integer pulse14;
    private Time timeObservation15;
    private Integer pulse15;
    private Time timeObservation16;
    private Integer pulse16;
    private Time timeObservation17;
    private Integer pulse17;
    private Time timeObservation18;
    private Integer pulse18;
    private Time timeObservation19;
    private Integer pulse19;
    private Time timeObservation20;
    private Integer pulse20;

    private Time timeObservation21;
    private Integer pulse21;
    private Time timeObservation22;
    private Integer pulse22;
    private Time timeObservation23;
    private Integer pulse23;
    private Time timeObservation24;
    private Integer pulse24;
    private Time timeObservation25;
    private Integer pulse25;
    private Time timeObservation26;
    private Integer pulse26;
    private Time timeObservation27;
    private Integer pulse27;
    private Time timeObservation28;
    private Integer pulse28;
    private Time timeObservation29;
    private Integer pulse29;
    private Time timeObservation30;
    private Integer pulse30;

    private Time timeObservation31;
    private Integer pulse31;
    private Time timeObservation32;
    private Integer pulse32;
    private Time timeObservation33;
    private Integer pulse33;
    private Time timeObservation34;
    private Integer pulse34;
    private Time timeObservation35;
    private Integer pulse35;
    private Time timeObservation36;
    private Integer pulse36;
    private Time timeObservation37;
    private Integer pulse37;
    private Time timeObservation38;
    private Integer pulse38;
    private Time timeObservation39;
    private Integer pulse39;
    private Time timeObservation40;
    private Integer pulse40;

    private Time timeObservation41;
    private Integer pulse41;
    private Time timeObservation42;
    private Integer pulse42;
    private Time timeObservation43;
    private Integer pulse43;
    private Time timeObservation44;
    private Integer pulse44;
    private Time timeObservation45;
    private Integer pulse45;
    private Time timeObservation46;
    private Integer pulse46;
    private Time timeObservation47;
    private Integer pulse47;
    private Time timeObservation48;
    private Integer pulse48;


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
     * @hibernate.property column="pulse_1"
     */
    public Integer getPulse1() {
        return pulse1;
    }

    public void setPulse1(Integer pulse1) {
        this.pulse1 = pulse1;
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
     * @hibernate.property column="pulse_2"
     */

    public Integer getPulse2() {
        return pulse2;
    }

    public void setPulse2(Integer pulse2) {
        this.pulse2 = pulse2;
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
     * @hibernate.property column="pulse_3"
     */

    public Integer getPulse3() {
        return pulse3;
    }

    public void setPulse3(Integer pulse3) {
        this.pulse3 = pulse3;
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
     * @hibernate.property column="pulse_4"
     */

    public Integer getPulse4() {
        return pulse4;
    }

    public void setPulse4(Integer pulse4) {
        this.pulse4 = pulse4;
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
     * @hibernate.property column="pulse_5"
     */

    public Integer getPulse5() {
        return pulse5;
    }

    public void setPulse5(Integer pulse5) {
        this.pulse5 = pulse5;
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
     * @hibernate.property column="pulse_6"
     */

    public Integer getPulse6() {
        return pulse6;
    }

    public void setPulse6(Integer pulse6) {
        this.pulse6 = pulse6;
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
     * @hibernate.property column="pulse_7"
     */

    public Integer getPulse7() {
        return pulse7;
    }

    public void setPulse7(Integer pulse7) {
        this.pulse7 = pulse7;
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
     * @hibernate.property column="pulse_8"
     */

    public Integer getPulse8() {
        return pulse8;
    }

    public void setPulse8(Integer pulse8) {
        this.pulse8 = pulse8;
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
     * @hibernate.property column="pulse_9"
     */

    public Integer getPulse9() {
        return pulse9;
    }

    public void setPulse9(Integer pulse9) {
        this.pulse9 = pulse9;
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
     * @hibernate.property column="pulse_10"
     */

    public Integer getPulse10() {
        return pulse10;
    }

    public void setPulse10(Integer pulse10) {
        this.pulse10 = pulse10;
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
     * @hibernate.property column="pulse_11"
     */

    public Integer getPulse11() {
        return pulse11;
    }

    public void setPulse11(Integer pulse11) {
        this.pulse11 = pulse11;
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
     * @hibernate.property column="pulse_12"
     */

    public Integer getPulse12() {
        return pulse12;
    }

    public void setPulse12(Integer pulse12) {
        this.pulse12 = pulse12;
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
     * @hibernate.property column="pulse_13"
     */

    public Integer getPulse13() {
        return pulse13;
    }

    public void setPulse13(Integer pulse13) {
        this.pulse13 = pulse13;
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
     * @hibernate.property column="pulse_14"
     */

    public Integer getPulse14() {
        return pulse14;
    }

    public void setPulse14(Integer pulse14) {
        this.pulse14 = pulse14;
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
     * @hibernate.property column="pulse_15"
     */

    public Integer getPulse15() {
        return pulse15;
    }

    public void setPulse15(Integer pulse15) {
        this.pulse15 = pulse15;
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
     * @hibernate.property column="pulse_16"
     */

    public Integer getPulse16() {
        return pulse16;
    }

    public void setPulse16(Integer pulse16) {
        this.pulse16 = pulse16;
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
     * @hibernate.property column="pulse_17"
     */

    public Integer getPulse17() {
        return pulse17;
    }

    public void setPulse17(Integer pulse17) {
        this.pulse17 = pulse17;
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
     * @hibernate.property column="pulse_18"
     */

    public Integer getPulse18() {
        return pulse18;
    }

    public void setPulse18(Integer pulse18) {
        this.pulse18 = pulse18;
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
     * @hibernate.property column="pulse_19"
     */

    public Integer getPulse19() {
        return pulse19;
    }

    public void setPulse19(Integer pulse19) {
        this.pulse19 = pulse19;
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
     * @hibernate.property column="pulse_20"
     */

    public Integer getPulse20() {
        return pulse20;
    }

    public void setPulse20(Integer pulse20) {
        this.pulse20 = pulse20;
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
     * @hibernate.property column="pulse_21"
     */

    public Integer getPulse21() {
        return pulse21;
    }

    public void setPulse21(Integer pulse21) {
        this.pulse21 = pulse21;
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
     * @hibernate.property column="pulse_22"
     */

    public Integer getPulse22() {
        return pulse22;
    }

    public void setPulse22(Integer pulse22) {
        this.pulse22 = pulse22;
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
     * @hibernate.property column="pulse_23"
     */

    public Integer getPulse23() {
        return pulse23;
    }

    public void setPulse23(Integer pulse23) {
        this.pulse23 = pulse23;
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
     * @hibernate.property column="pulse_24"
     */

    public Integer getPulse24() {
        return pulse24;
    }

    public void setPulse24(Integer pulse24) {
        this.pulse24 = pulse24;
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
     * @hibernate.property column="pulse_25"
     */

    public Integer getPulse25() {
        return pulse25;
    }

    public void setPulse25(Integer pulse25) {
        this.pulse25 = pulse25;
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
     * @hibernate.property column="pulse_26"
     */

    public Integer getPulse26() {
        return pulse26;
    }

    public void setPulse26(Integer pulse26) {
        this.pulse26 = pulse26;
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
     * @hibernate.property column="pulse_27"
     */

    public Integer getPulse27() {
        return pulse27;
    }

    public void setPulse27(Integer pulse27) {
        this.pulse27 = pulse27;
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
     * @hibernate.property column="pulse_28"
     */

    public Integer getPulse28() {
        return pulse28;
    }

    public void setPulse28(Integer pulse28) {
        this.pulse28 = pulse28;
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
     * @hibernate.property column="pulse_29"
     */

    public Integer getPulse29() {
        return pulse29;
    }

    public void setPulse29(Integer pulse29) {
        this.pulse29 = pulse29;
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
     * @hibernate.property column="pulse_30"
     */

    public Integer getPulse30() {
        return pulse30;
    }

    public void setPulse30(Integer pulse30) {
        this.pulse30 = pulse30;
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
     * @hibernate.property column="pulse_31"
     */

    public Integer getPulse31() {
        return pulse31;
    }

    public void setPulse31(Integer pulse31) {
        this.pulse31 = pulse31;
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
     * @hibernate.property column="pulse_32"
     */

    public Integer getPulse32() {
        return pulse32;
    }

    public void setPulse32(Integer pulse32) {
        this.pulse32 = pulse32;
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
     * @hibernate.property column="pulse_33"
     */

    public Integer getPulse33() {
        return pulse33;
    }

    public void setPulse33(Integer pulse33) {
        this.pulse33 = pulse33;
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
     * @hibernate.property column="pulse_34"
     */

    public Integer getPulse34() {
        return pulse34;
    }

    public void setPulse34(Integer pulse34) {
        this.pulse34 = pulse34;
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
     * @hibernate.property column="pulse_35"
     */
    public Integer getPulse35() {
        return pulse35;
    }

    public void setPulse35(Integer pulse35) {
        this.pulse35 = pulse35;
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
     * @hibernate.property column="pulse_36"
     */
    public Integer getPulse36() {
        return pulse36;
    }

    public void setPulse36(Integer pulse36) {
        this.pulse36 = pulse36;
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
     * @hibernate.property column="pulse_37"
     */
    public Integer getPulse37() {
        return pulse37;
    }

    public void setPulse37(Integer pulse37) {
        this.pulse37 = pulse37;
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
     * @hibernate.property column="pulse_38"
     */
    public Integer getPulse38() {
        return pulse38;
    }

    public void setPulse38(Integer pulse38) {
        this.pulse38 = pulse38;
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
     * @hibernate.property column="pulse_39"
     */
    public Integer getPulse39() {
        return pulse39;
    }

    public void setPulse39(Integer pulse39) {
        this.pulse39 = pulse39;
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
     * @hibernate.property column="pulse_40"
     */
    public Integer getPulse40() {
        return pulse40;
    }

    public void setPulse40(Integer pulse40) {
        this.pulse40 = pulse40;
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
     * @hibernate.property column="pulse_41"
     */
    public Integer getPulse41() {
        return pulse41;
    }

    public void setPulse41(Integer pulse41) {
        this.pulse41 = pulse41;
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
     * @hibernate.property column="pulse_42"
     */
    public Integer getPulse42() {
        return pulse42;
    }

    public void setPulse42(Integer pulse42) {
        this.pulse42 = pulse42;
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
     * @hibernate.property column="pulse_43"
     */
    public Integer getPulse43() {
        return pulse43;
    }

    public void setPulse43(Integer pulse43) {
        this.pulse43 = pulse43;
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
     * @hibernate.property column="pulse_44"
     */
    public Integer getPulse44() {
        return pulse44;
    }

    public void setPulse44(Integer pulse44) {
        this.pulse44 = pulse44;
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
     * @hibernate.property column="pulse_45"
     */
    public Integer getPulse45() {
        return pulse45;
    }

    public void setPulse45(Integer pulse45) {
        this.pulse45 = pulse45;
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
     * @hibernate.property column="pulse_46"
     */
    public Integer getPulse46() {
        return pulse46;
    }

    public void setPulse46(Integer pulse46) {
        this.pulse46 = pulse46;
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
     * @hibernate.property column="pulse_47"
     */
    public Integer getPulse47() {
        return pulse47;
    }

    public void setPulse47(Integer pulse47) {
        this.pulse47 = pulse47;
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
     * @hibernate.property column="pulse_48"
     */
    public Integer getPulse48() {
        return pulse48;
    }

    public void setPulse48(Integer pulse48) {
        this.pulse48 = pulse48;
    }


}
