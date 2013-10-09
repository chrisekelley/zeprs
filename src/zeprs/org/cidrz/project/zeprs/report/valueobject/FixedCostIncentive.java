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

import java.sql.Date;
import java.util.List;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Oct 30, 2007
 *         Time: 2:06:12 PM
 */
public class FixedCostIncentive {

    private int id;
	private int newANCClient;
    private int totalANCAttendees;
    private int expectedDeliveries;
    private int hivTested;
    private int hivPositive;
    private int hivNegative;
    private int hivIndeterminate;
    private int maternalNVP;
    private int infantNVP;

    private int revisits;
    private int counselledNew;
    private int counselledRevisits;
    private int referredArt;
    private int maternalAztNewStart;
    private int maternalAztRefills;
    private int noRoutineAnteVisit;
    private List noRoutineAnteVisitList;
    private int noInitialVisit;
    private List noInitialVisitList;
    private int noSafeMotherhoodForm;
    private List noSafeMotherhoodFormList;
    private int hivTestedNewAnc;
    private int hivTestedRevisit;
    private List noNewbornEvalVisitList;
    private int siteId;
    private Date dateVisit;
    private int hivTestedInLabourWard;
    private int routineUterotonicGiven;
    private int deliveries;


    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public Date getDateVisit() {
		return dateVisit;
	}

	public void setDateVisit(Date dateVisit) {
		this.dateVisit = dateVisit;
	}

	public int getNewANCClient() {
        return newANCClient;
    }

    public void setNewANCClient(int newANCClient) {
        this.newANCClient = newANCClient;
    }

    public int getTotalANCAttendees() {
        return totalANCAttendees;
    }

    public void setTotalANCAttendees(int totalANCAttendees) {
        this.totalANCAttendees = totalANCAttendees;
    }

    public int getExpectedDeliveries() {
        return expectedDeliveries;
    }

    public void setExpectedDeliveries(int expectedDeliveries) {
        this.expectedDeliveries = expectedDeliveries;
    }


    public int getHivTested() {
        return hivTested;
    }

    public void setHivTested(int hivTested) {
        this.hivTested = hivTested;
    }

    public int getHivPositive() {
        return hivPositive;
    }

    public void setHivPositive(int hivPositive) {
        this.hivPositive = hivPositive;
    }

    public int getHivNegative() {
        return hivNegative;
    }

    public void setHivNegative(int hivNegative) {
        this.hivNegative = hivNegative;
    }

    public int getHivIndeterminate() {
        return hivIndeterminate;
    }

    public void setHivIndeterminate(int hivIndeterminate) {
        this.hivIndeterminate = hivIndeterminate;
    }

    public int getMaternalNVP() {
        return maternalNVP;
    }

    public void setMaternalNVP(int maternalNVP) {
        this.maternalNVP = maternalNVP;
    }

    public int getInfantNVP() {
        return infantNVP;
    }

    public void setInfantNVP(int infantNVP) {
        this.infantNVP = infantNVP;
    }

    public int getRevisits() {
        return revisits;
    }

    public void setRevisits(int revisits) {
        this.revisits = revisits;
    }

    public int getCounselledNew() {
        return counselledNew;
    }

    public void setCounselledNew(int counselledNew) {
        this.counselledNew = counselledNew;
    }

    public int getCounselledRevisits() {
        return counselledRevisits;
    }

    public void setCounselledRevisits(int counselledRevisits) {
        this.counselledRevisits = counselledRevisits;
    }

    public int getReferredArt() {
        return referredArt;
    }

    public void setReferredArt(int referredArt) {
        this.referredArt = referredArt;
    }

    public int getMaternalAztNewStart() {
		return maternalAztNewStart;
	}

	public void setMaternalAztNewStart(int maternalAztNewStart) {
		this.maternalAztNewStart = maternalAztNewStart;
	}

	public int getMaternalAztRefills() {
		return maternalAztRefills;
	}

	public void setMaternalAztRefills(int maternalAztRefills) {
		this.maternalAztRefills = maternalAztRefills;
	}

	public int getNoRoutineAnteVisit() {
        return noRoutineAnteVisit;
    }

    public void setNoRoutineAnteVisit(int noRoutineAnteVisit) {
        this.noRoutineAnteVisit = noRoutineAnteVisit;
    }

    public List getNoRoutineAnteVisitList() {
        return noRoutineAnteVisitList;
    }

    public void setNoRoutineAnteVisitList(List noRoutineAnteVisitList) {
        this.noRoutineAnteVisitList = noRoutineAnteVisitList;
    }

    public int getNoInitialVisit() {
        return noInitialVisit;
    }

    public void setNoInitialVisit(int noInitialVisit) {
        this.noInitialVisit = noInitialVisit;
    }

    public List getNoInitialVisitList() {
        return noInitialVisitList;
    }

    public void setNoInitialVisitList(List noInitialVisitList) {
        this.noInitialVisitList = noInitialVisitList;
    }

    public int getNoSafeMotherhoodForm() {
        return noSafeMotherhoodForm;
    }

    public void setNoSafeMotherhoodForm(int noSafeMotherhoodForm) {
        this.noSafeMotherhoodForm = noSafeMotherhoodForm;
    }

    public List getNoSafeMotherhoodFormList() {
        return noSafeMotherhoodFormList;
    }

    public void setNoSafeMotherhoodFormList(List noSafeMotherhoodFormList) {
        this.noSafeMotherhoodFormList = noSafeMotherhoodFormList;
    }

    public int getHivTestedNewAnc() {
        return hivTestedNewAnc;
    }

    public void setHivTestedNewAnc(int hivTestedNewAnc) {
        this.hivTestedNewAnc = hivTestedNewAnc;
    }

    public int getHivTestedRevisit() {
        return hivTestedRevisit;
    }

    public void setHivTestedRevisit(int hivTestedRevisit) {
        this.hivTestedRevisit = hivTestedRevisit;
    }

    public List getNoNewbornEvalVisitList() {
        return noNewbornEvalVisitList;
    }

    public void setNoNewbornEvalVisitList(List noNewbornEvalVisitList) {
        this.noNewbornEvalVisitList = noNewbornEvalVisitList;
    }

	public int getHivTestedInLabourWard() {
		return hivTestedInLabourWard;
	}

	public void setHivTestedInLabourWard(int hivTestedInLabourWard) {
		this.hivTestedInLabourWard = hivTestedInLabourWard;
	}

	public int getRoutineUterotonicGiven() {
		return routineUterotonicGiven;
	}

	public void setRoutineUterotonicGiven(int routineUterotonicGiven) {
		this.routineUterotonicGiven = routineUterotonicGiven;
	}

	public int getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(int deliveries) {
		this.deliveries = deliveries;
	}

}
