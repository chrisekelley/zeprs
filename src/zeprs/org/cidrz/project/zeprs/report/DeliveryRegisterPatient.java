/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

/*
 * Created on Apr 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.cidrz.project.zeprs.report;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.report.valueobject.CurrentPregnancyStatus;
import org.cidrz.project.zeprs.valueobject.gen.DeliverySum;
import org.cidrz.project.zeprs.valueobject.gen.MaternalDischarge;
import org.cidrz.project.zeprs.valueobject.report.PatientRegistrationClean;
import org.cidrz.project.zeprs.valueobject.report.gen.DeliverySumReport;
import org.cidrz.project.zeprs.valueobject.report.gen.NewbornEvalReport;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

/**
 * @author ericl
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */

public class DeliveryRegisterPatient {

    /**
     * Commons Logging instance.
     */
    private static transient Log log = LogFactory.getFactory().getInstance(DeliveryRegisterPatient.class);


    private ArrayList babies = new ArrayList();
    private int numBabies;

    private String reportMonth;
    private String reportYear;

    private Long patientId;

    private DeliverySum deliverySum;
    private DeliverySumReport deliverySumReport;
    private MaternalDischarge maternalDischarge;
    private String ega;
    private NewbornEvalReport newbornEvalReport;
    private PatientRegistrationClean patientRegistration;
    private CurrentPregnancyStatus currentPregnancyStatus;
    private Date dateLastInfantDelivered;  // birthDate of infant - used to calculate duration of labour
    private Time timeLastInfantDelivered;  // birthTime of infant  - used to calculate duration of labour
    private long durationOfLabour;

    /**
     * @param baby The patients to set.
     */
    public void addBaby(DeliveryRegisterPatient baby) {
        if (babies == null) {
            babies = new ArrayList();
        }
        babies.add(baby);
    }

    /**
     * @return Returns the babyData.
     */
    public ArrayList getBabies() {
        return babies;
    }

    /**
     * @param babies The babyData to set.
     */
    public void setBabies(ArrayList babies) {
        this.babies = babies;
    }

    /**
     * @return Returns the numBabies.
     */
    public int getNumBabies() {
        return numBabies;
    }

    /**
     * @param numBabies The numBabies to set.
     */
    public void setNumBabies(int numBabies) {
        this.numBabies = numBabies;
    }

    /**
     * @return Returns the reportMonth.
     */
    public String getReportMonth() {
        return reportMonth;
    }

    /**
     * @param reportMonth The reportMonth to set.
     */
    public void setReportMonth(String reportMonth) {
        this.reportMonth = reportMonth;
    }

    /**
     * @return Returns the reportYear.
     */
    public String getReportYear() {
        return reportYear;
    }

    /**
     * @param reportYear The reportYear to set.
     */
    public void setReportYear(String reportYear) {
        this.reportYear = reportYear;
    }

    /**
     * @return patient id
     */
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public DeliverySum getDeliverySum() {
        return deliverySum;
    }

    public void setDeliverySum(DeliverySum deliverySum) {
        this.deliverySum = deliverySum;
    }

    public MaternalDischarge getMaternalDischarge() {
        return maternalDischarge;
    }

    public void setMaternalDischarge(MaternalDischarge maternalDischarge) {
        this.maternalDischarge = maternalDischarge;
    }

    public String getEga() {
        return ega;
    }

    public void setEga(String ega) {
        this.ega = ega;
    }

    public DeliverySumReport getDeliverySumReport() {
        return deliverySumReport;
    }

    public void setDeliverySumReport(DeliverySumReport deliverySumReport) {
        this.deliverySumReport = deliverySumReport;
    }

    public NewbornEvalReport getNewbornEvalReport() {
        return newbornEvalReport;
    }

    public void setNewbornEvalReport(NewbornEvalReport newbornEvalReport) {
        this.newbornEvalReport = newbornEvalReport;
    }

    public PatientRegistrationClean getPatientRegistration() {
        return patientRegistration;
    }

    public void setPatientRegistration(PatientRegistrationClean patientRegistration) {
        this.patientRegistration = patientRegistration;
    }

    public CurrentPregnancyStatus getCurrentPregnancyStatus() {
        return currentPregnancyStatus;
    }

    public void setCurrentPregnancyStatus(CurrentPregnancyStatus currentPregnancyStatus) {
        this.currentPregnancyStatus = currentPregnancyStatus;
    }

    public Date getDateLastInfantDelivered() {
        return dateLastInfantDelivered;
    }

    public void setDateLastInfantDelivered(Date dateLastInfantDelivered) {
        this.dateLastInfantDelivered = dateLastInfantDelivered;
    }

    public Time getTimeLastInfantDelivered() {
        return timeLastInfantDelivered;
    }

    public void setTimeLastInfantDelivered(Time timeLastInfantDelivered) {
        this.timeLastInfantDelivered = timeLastInfantDelivered;
    }

    public long getDurationOfLabour() {
        return durationOfLabour;
    }

    public void setDurationOfLabour(long durationOfLabour) {
        this.durationOfLabour = durationOfLabour;
    }


    public void loadPatient(int patientID, Date beginDate, Date endDate, int siteID, Connection conn) {
    }

}
