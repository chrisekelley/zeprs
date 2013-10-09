/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.valueobject;

import org.cidrz.project.zeprs.valueobject.gen.PartographStatus;
import org.cidrz.project.zeprs.valueobject.gen.DeliverySum;
import org.cidrz.project.zeprs.valueobject.partograph.*;
import org.cidrz.webapp.dynasite.utils.StringManipulation;

import java.lang.reflect.Field;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Mar 14, 2006
 *         Time: 3:08:14 PM
 */
public class Partograph {

    private Long patientId;
    private Long pregnancyId;
    private PartographStatus partographStatus;
    private FetalHeartRate fetalHeartRate;
    private Liquor liquor;
    private Moulding moulding;
    private Cervix cervix;
    private Descent descent;
    private Contractions contractions;
    private Oxytocin oxytocin;
    private DrugsDispensed drugsDispensed;
    private BloodPressure bloodPressure;
    private Pulse pulse;
    private Temperature temperature;
    private Respiration respiration;
    private UrineAmount urineAmount;
    private UrinalysisProtein urinalysisProtein;
    private UrinalysisAcetone urinalysisAcetone;
    private UrinalysisGlucose urinalysisGlucose;
    private VaginalExamParto vaginalExamParto;
    private DeliverySum delivSum;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getPregnancyId() {
        return pregnancyId;
    }

    public void setPregnancyId(Long pregnancyId) {
        this.pregnancyId = pregnancyId;
    }

    public PartographStatus getPartographStatus() {
        return partographStatus;
    }

    public void setPartographStatus(PartographStatus partographStatus) {
        this.partographStatus = partographStatus;
    }

    public FetalHeartRate getFetalHeartRate() {
        return fetalHeartRate;
    }

    public void setFetalHeartRate(FetalHeartRate fetalHeartRate) {
        this.fetalHeartRate = fetalHeartRate;
    }

    public Liquor getLiquor() {
        return liquor;
    }

    public void setLiquor(Liquor liquor) {
        this.liquor = liquor;
    }

    public Moulding getMoulding() {
        return moulding;
    }

    public void setMoulding(Moulding moulding) {
        this.moulding = moulding;
    }

    public Cervix getCervix() {
        return cervix;
    }

    public void setCervix(Cervix cervix) {
        this.cervix = cervix;
    }

    public Descent getDescent() {
        return descent;
    }

    public void setDescent(Descent descent) {
        this.descent = descent;
    }

    public Contractions getContractions() {
        return contractions;
    }

    public void setContractions(Contractions contractions) {
        this.contractions = contractions;
    }

    public Oxytocin getOxytocin() {
        return oxytocin;
    }

    public void setOxytocin(Oxytocin oxytocin) {
        this.oxytocin = oxytocin;
    }

    public DrugsDispensed getDrugsDispensed() {
        return drugsDispensed;
    }

    public void setDrugsDispensed(DrugsDispensed drugsDispensed) {
        this.drugsDispensed = drugsDispensed;
    }

    public BloodPressure getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(BloodPressure bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public Pulse getPulse() {
        return pulse;
    }

    public void setPulse(Pulse pulse) {
        this.pulse = pulse;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Respiration getRespiration() {
        return respiration;
    }

    public void setRespiration(Respiration respiration) {
        this.respiration = respiration;
    }

    public UrineAmount getUrineAmount() {
        return urineAmount;
    }

    public void setUrineAmount(UrineAmount urineAmount) {
        this.urineAmount = urineAmount;
    }

    public UrinalysisProtein getUrinalysisProtein() {
        return urinalysisProtein;
    }

    public void setUrinalysisProtein(UrinalysisProtein urinalysisProtein) {
        this.urinalysisProtein = urinalysisProtein;
    }

    public UrinalysisAcetone getUrinalysisAcetone() {
        return urinalysisAcetone;
    }

    public void setUrinalysisAcetone(UrinalysisAcetone urinalysisAcetone) {
        this.urinalysisAcetone = urinalysisAcetone;
    }

    public UrinalysisGlucose getUrinalysisGlucose() {
        return urinalysisGlucose;
    }

    public void setUrinalysisGlucose(UrinalysisGlucose urinalysisGlucose) {
        this.urinalysisGlucose = urinalysisGlucose;
    }

    public VaginalExamParto getVaginalExamParto() {
        return vaginalExamParto;
    }

    public void setVaginalExamParto(VaginalExamParto vaginalExamParto) {
        this.vaginalExamParto = vaginalExamParto;
    }

    public DeliverySum getDelivSum() {
        return delivSum;
    }

    public void setDelivSum(DeliverySum delivSum) {
        this.delivSum = delivSum;
    }

    public static Field getField(String className) throws NoSuchFieldException {
        Field field = Partograph.class.getField(StringManipulation.firstCharToLowerCase(className));
        return field;
    }

}
