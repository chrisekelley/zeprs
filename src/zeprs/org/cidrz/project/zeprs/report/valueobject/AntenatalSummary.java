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
 *         Date: Jan 14, 2006
 *         Time: 6:27:12 PM
 */
public class AntenatalSummary {

    private int id;
	private int newANCClient;
    private int totalANCAttendees;
    private int expectedDeliveries;
    private int preTestCounseled;
    private int hivTested;
    private int refusedTesting;
    private int receivedResults;
    private int uncollectedResults;
    private int hivPositive;
    private int hivNegative;
    private int hivIndeterminate;
    private int maternalNVP;
    private int infantNVP;
    private int rprTested;
    private int rprPositive;
    private int rprNegative;

    private int revisits;
    private int counselledNew;
    private int counselledRevisits;
    private int referredArt;
    private int maternalAztNewStart;
    private int maternalAztRefills;
    private int syphilisTreated;
    private int hgbDone;
    private int hgbLT10;
    private int sp1;
    private int sp2;
    private int sp3;
    private int tt1;
    private int tt2;
    private int tt3;
    private int tt4;
    private int tt5;
    private int ttProtected;
    private int ttComplete;
    private int firstVisit1stTrim;
    private int firstVisit2ndTrim;
    private int firstVisit3rdTrim;
    private int vermoxGiven;
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

    public int getPreTestCounseled() {
        return preTestCounseled;
    }

    public void setPreTestCounseled(int preTestCounseled) {
        this.preTestCounseled = preTestCounseled;
    }

    public int getHivTested() {
        return hivTested;
    }

    public void setHivTested(int hivTested) {
        this.hivTested = hivTested;
    }

    public int getRefusedTesting() {
        return refusedTesting;
    }

    public void setRefusedTesting(int refusedTesting) {
        this.refusedTesting = refusedTesting;
    }

    public int getReceivedResults() {
        return receivedResults;
    }

    public void setReceivedResults(int receivedResults) {
        this.receivedResults = receivedResults;
    }

    public int getUncollectedResults() {
        return uncollectedResults;
    }

    public void setUncollectedResults(int uncollectedResults) {
        this.uncollectedResults = uncollectedResults;
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

    public int getRprTested() {
        return rprTested;
    }

    public void setRprTested(int rprTested) {
        this.rprTested = rprTested;
    }

    public int getRprPositive() {
        return rprPositive;
    }

    public void setRprPositive(int rprPositive) {
        this.rprPositive = rprPositive;
    }

    public int getRprNegative() {
        return rprNegative;
    }

    public void setRprNegative(int rprNegative) {
        this.rprNegative = rprNegative;
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

    /**
	 * @return the maternalAztNewStart
	 */
	public int getMaternalAztNewStart() {
		return maternalAztNewStart;
	}

	/**
	 * @param maternalAztNewStart the maternalAztNewStart to set
	 */
	public void setMaternalAztNewStart(int maternalAztNewStart) {
		this.maternalAztNewStart = maternalAztNewStart;
	}

	/**
	 * @return the maternalAztRefills
	 */
	public int getMaternalAztRefills() {
		return maternalAztRefills;
	}

	/**
	 * @param maternalAztRefills the maternalAztRefills to set
	 */
	public void setMaternalAztRefills(int maternalAztRefills) {
		this.maternalAztRefills = maternalAztRefills;
	}

	public int getSyphilisTreated() {
        return syphilisTreated;
    }

    public void setSyphilisTreated(int syphilisTreated) {
        this.syphilisTreated = syphilisTreated;
    }

    public int getHgbDone() {
        return hgbDone;
    }

    public void setHgbDone(int hgbDone) {
        this.hgbDone = hgbDone;
    }

    public int getHgbLT10() {
        return hgbLT10;
    }

    public void setHgbLT10(int hgbLT10) {
        this.hgbLT10 = hgbLT10;
    }

    public int getSp1() {
        return sp1;
    }

    public void setSp1(int sp1) {
        this.sp1 = sp1;
    }

    public int getSp2() {
        return sp2;
    }

    public void setSp2(int sp2) {
        this.sp2 = sp2;
    }

    public int getSp3() {
        return sp3;
    }

    public void setSp3(int sp3) {
        this.sp3 = sp3;
    }

    public int getTt1() {
        return tt1;
    }

    public void setTt1(int tt1) {
        this.tt1 = tt1;
    }

    public int getTt2() {
        return tt2;
    }

    public void setTt2(int tt2) {
        this.tt2 = tt2;
    }

    public int getTt3() {
        return tt3;
    }

    public void setTt3(int tt3) {
        this.tt3 = tt3;
    }

    public int getTt4() {
        return tt4;
    }

    public void setTt4(int tt4) {
        this.tt4 = tt4;
    }

    public int getTt5() {
        return tt5;
    }

    public void setTt5(int tt5) {
        this.tt5 = tt5;
    }

    public int getTtProtected() {
        return ttProtected;
    }

    public void setTtProtected(int ttProtected) {
        this.ttProtected = ttProtected;
    }

    public int getTtComplete() {
        return ttComplete;
    }

    public void setTtComplete(int ttComplete) {
        this.ttComplete = ttComplete;
    }

    public int getFirstVisit1stTrim() {
        return firstVisit1stTrim;
    }

    public void setFirstVisit1stTrim(int firstVisit1stTrim) {
        this.firstVisit1stTrim = firstVisit1stTrim;
    }

    public int getFirstVisit2ndTrim() {
        return firstVisit2ndTrim;
    }

    public void setFirstVisit2ndTrim(int firstVisit2ndTrim) {
        this.firstVisit2ndTrim = firstVisit2ndTrim;
    }

    public int getFirstVisit3rdTrim() {
        return firstVisit3rdTrim;
    }

    public void setFirstVisit3rdTrim(int firstVisit3rdTrim) {
        this.firstVisit3rdTrim = firstVisit3rdTrim;
    }

    public int getVermoxGiven() {
        return vermoxGiven;
    }

    public void setVermoxGiven(int vermoxGiven) {
        this.vermoxGiven = vermoxGiven;
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
}
