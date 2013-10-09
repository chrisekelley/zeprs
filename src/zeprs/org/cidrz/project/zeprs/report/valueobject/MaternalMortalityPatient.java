/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.project.zeprs.report.valueobject;

import org.cidrz.project.zeprs.valueobject.report.PatientRegistrationClean;

import java.sql.Date;
import java.sql.Time;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Nov 28, 2005
 *         Time: 5:00:40 PM
 */
public class MaternalMortalityPatient {

    private Long patientId;
    private PatientRegistrationClean patientRegistration;
    private Date dateDeath;
    private Time timeDeath;
    private String causeOfDeath;
    private boolean postMortemConfirmation;
    private Date removalOfBody;
    private String comments;

    private Long currentPregnancyId;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getCurrentPregnancyId() {
        return currentPregnancyId;
    }

    public void setCurrentPregnancyId(Long currentPregnancyId) {
        this.currentPregnancyId = currentPregnancyId;
    }

    public PatientRegistrationClean getPatientRegistration() {
        return patientRegistration;
    }

    public void setPatientRegistration(PatientRegistrationClean patientRegistration) {
        this.patientRegistration = patientRegistration;
    }

    public Date getDateDeath() {
        return dateDeath;
    }

    public void setDateDeath(Date dateDeath) {
        this.dateDeath = dateDeath;
    }

    public Time getTimeDeath() {
        return timeDeath;
    }

    public void setTimeDeath(Time timeDeath) {
        this.timeDeath = timeDeath;
    }

    public String getCauseOfDeath() {
        return causeOfDeath;
    }

    public void setCauseOfDeath(String causeOfDeath) {
        this.causeOfDeath = causeOfDeath;
    }

    public boolean isPostMortemConfirmation() {
        return postMortemConfirmation;
    }

    public void setPostMortemConfirmation(boolean postMortemConfirmation) {
        this.postMortemConfirmation = postMortemConfirmation;
    }

    public Date getRemovalOfBody() {
        return removalOfBody;
    }

    public void setRemovalOfBody(Date removalOfBody) {
        this.removalOfBody = removalOfBody;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
