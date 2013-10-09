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
import java.sql.Time;
import java.util.List;

import org.cidrz.project.zeprs.valueobject.report.gen.DeliverySumReport;
import org.cidrz.project.zeprs.valueobject.report.gen.MaternalDischargeReport;
import org.cidrz.project.zeprs.valueobject.report.gen.PuerperiumReport;
import org.cidrz.webapp.dynasite.valueobject.Pregnancy;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Dec 8, 2005
 *         Time: 3:06:12 PM
 */
public class PregnancyReport extends Pregnancy {


    private List anteVisits;
    private List postVisits;
    private DeliverySumReport deliverySum;
    private MaternalDischargeReport maternalDischarge;
    private PuerperiumReport puerperium;
    private List newborns;
    private String dateFirstEga;
    private String siteList;
    private String deliverySummaryEga;
    private Date dateLastInfantDelivered;  // birthDate of infant - used to calculate duration of labour
    private Time timeLastInfantDelivered;  // birthTime of infant  - used to calculate duration of labour
    private long durationOfLabour;
    private List referrals;
    private AntenatalHistory anteHistory;



    public List getAnteVisits() {
        return anteVisits;
    }

    public void setAnteVisits(List anteVisits) {
        this.anteVisits = anteVisits;
    }

    public DeliverySumReport getDeliverySum() {
        return deliverySum;
    }

    public void setDeliverySum(DeliverySumReport deliverySum) {
        this.deliverySum = deliverySum;
    }

    public MaternalDischargeReport getMaternalDischarge() {
        return maternalDischarge;
    }

    public void setMaternalDischarge(MaternalDischargeReport maternalDischarge) {
        this.maternalDischarge = maternalDischarge;
    }

    public PuerperiumReport getPuerperium() {
        return puerperium;
    }

    public void setPuerperium(PuerperiumReport puerperium) {
        this.puerperium = puerperium;
    }

    public List getNewborns() {
        return newborns;
    }

    public void setNewborns(List newborns) {
        this.newborns = newborns;
    }

    public List getPostVisits() {
        return postVisits;
    }

    public void setPostVisits(List postVisits) {
        this.postVisits = postVisits;
    }

    public String getDateFirstEga() {
        return dateFirstEga;
    }

    public void setDateFirstEga(String dateFirstEga) {
        this.dateFirstEga = dateFirstEga;
    }

    public String getSiteList() {
        return siteList;
    }

    public void setSiteList(String siteList) {
        this.siteList = siteList;
    }

    public String getDeliverySummaryEga() {
        return deliverySummaryEga;
    }

    public void setDeliverySummaryEga(String deliverySummaryEga) {
        this.deliverySummaryEga = deliverySummaryEga;
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

    public List getReferrals() {
        return referrals;
    }

    public void setReferrals(List referrals) {
        this.referrals = referrals;
    }

	/**
	 * @return the anteHistory
	 */
	public AntenatalHistory getAnteHistory() {
		return anteHistory;
	}

	/**
	 * @param anteHistory the anteHistory to set
	 */
	public void setAnteHistory(AntenatalHistory anteHistory) {
		this.anteHistory = anteHistory;
	}


}
