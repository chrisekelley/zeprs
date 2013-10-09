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

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jan 10, 2006
 *         Time: 9:54:12 AM
 */
public class DailyDeliverySummary {

    private int deliveries;   // num. Delivered in the Unit
    private int hivPosMothers;
    private int maternalNvpIngested;
    private int infantNvpIngested;
    private int women6monthEbf;    // Women Opting for EBF for 6 Months
    private int womenFormulaFeeding;  // Women Opting for Formula Feeding
    private int womenMixedFeeding;  // Women Opting for Mixed Feeding
    private int womenBreastFeeding;  // Women Opting for Breast Feeding
    private int motherAlive;
    private int motherToHospital;
    private int motherDied;
    private int infantAlive;
    private int infantDied;
    private int infantToHospital;
    private int msb;
    private int fsb;
    private int nnd;    // neonatal death
    private int routineUterotonicGiven;
    private int mothersTestedInLabour;

    public int getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(int deliveries) {
        this.deliveries = deliveries;
    }

    public int getHivPosMothers() {
        return hivPosMothers;
    }

    public void setHivPosMothers(int hivPosMothers) {
        this.hivPosMothers = hivPosMothers;
    }

    public int getMaternalNvpIngested() {
        return maternalNvpIngested;
    }

    public void setMaternalNvpIngested(int maternalNvpIngested) {
        this.maternalNvpIngested = maternalNvpIngested;
    }

    public int getInfantNvpIngested() {
        return infantNvpIngested;
    }

    public void setInfantNvpIngested(int infantNvpIngested) {
        this.infantNvpIngested = infantNvpIngested;
    }

    public int getWomen6monthEbf() {
        return women6monthEbf;
    }

    public void setWomen6monthEbf(int women6monthEbf) {
        this.women6monthEbf = women6monthEbf;
    }

    public int getWomenFormulaFeeding() {
        return womenFormulaFeeding;
    }

    public void setWomenFormulaFeeding(int womenFormulaFeeding) {
        this.womenFormulaFeeding = womenFormulaFeeding;
    }

    public int getWomenMixedFeeding() {
        return womenMixedFeeding;
    }

    public void setWomenMixedFeeding(int womenMixedFeeding) {
        this.womenMixedFeeding = womenMixedFeeding;
    }

    public int getWomenBreastFeeding() {
        return womenBreastFeeding;
    }

    public void setWomenBreastFeeding(int womenBreastFeeding) {
        this.womenBreastFeeding = womenBreastFeeding;
    }

    public int getMotherAlive() {
        return motherAlive;
    }

    public void setMotherAlive(int motherAlive) {
        this.motherAlive = motherAlive;
    }

    public int getMotherToHospital() {
        return motherToHospital;
    }

    public void setMotherToHospital(int motherToHospital) {
        this.motherToHospital = motherToHospital;
    }

    public int getMotherDied() {
        return motherDied;
    }

    public void setMotherDied(int motherDied) {
        this.motherDied = motherDied;
    }

    public int getInfantAlive() {
        return infantAlive;
    }

    public void setInfantAlive(int infantAlive) {
        this.infantAlive = infantAlive;
    }

    public int getInfantDied() {
        return infantDied;
    }

    public void setInfantDied(int infantDied) {
        this.infantDied = infantDied;
    }

    public int getInfantToHospital() {
        return infantToHospital;
    }

    public void setInfantToHospital(int infantToHospital) {
        this.infantToHospital = infantToHospital;
    }

    public int getMsb() {
        return msb;
    }

    public void setMsb(int msb) {
        this.msb = msb;
    }

    public int getFsb() {
        return fsb;
    }

    public void setFsb(int fsb) {
        this.fsb = fsb;
    }

    public int getNnd() {
        return nnd;
    }

    public void setNnd(int nnd) {
        this.nnd = nnd;
    }

	/**
	 * @return the routineUterotonicGiven
	 */
	public int getRoutineUterotonicGiven() {
		return routineUterotonicGiven;
	}

	/**
	 * @param routineUterotonicGiven the routineUterotonicGiven to set
	 */
	public void setRoutineUterotonicGiven(int routineUterotonicGiven) {
		this.routineUterotonicGiven = routineUterotonicGiven;
	}

	public int getMothersTestedInLabour() {
		return mothersTestedInLabour;
	}

	public void setMothersTestedInLabour(int mothersTestedInLabour) {
		this.mothersTestedInLabour = mothersTestedInLabour;
	}


}
