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

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jan 26, 2006
 *         Time: 5:17:45 PM
 */
public class HivStamp {

    private Date pcr1;   // PCR means Patient Counseling Refused
    private Date pcr2;
    private Date pcr3;
    private Date pca;   // PCA means Patient Counseling Accepted
    private Date tr;    // TR means test refused
    private Date ta;    // TA means test accepted
    private Date statusR;    // R stands for test Reactive
    private Date statusNr;    // NR stands for Non-Reactive
    private Date statusI;    // I stands for Indeterminate
    private Date mga;    // MGA stands for Mother Given ARV’s
    private Date mra;    // MRA = Mother Refused ARV’s
    private Date iga;    // IGA – Infant Given ARV
    private Date inga;    // INGA – Infant Not Given ARV
    private Boolean ifb;    // IFB – Infant fed by Breast
    private Boolean ifr;    // IFR – Infant fed by Replacement (formula)
    private Boolean hivHistory;

    public Date getPcr1() {
        return pcr1;
    }

    public void setPcr1(Date pcr1) {
        this.pcr1 = pcr1;
    }

    public Date getPcr2() {
        return pcr2;
    }

    public void setPcr2(Date pcr2) {
        this.pcr2 = pcr2;
    }

    public Date getPcr3() {
        return pcr3;
    }

    public void setPcr3(Date pcr3) {
        this.pcr3 = pcr3;
    }

    public Date getPca() {
        return pca;
    }

    public void setPca(Date pca) {
        this.pca = pca;
    }

    public Date getTr() {
        return tr;
    }

    public void setTr(Date tr) {
        this.tr = tr;
    }

    public Date getTa() {
        return ta;
    }

    public void setTa(Date ta) {
        this.ta = ta;
    }

    public Date getStatusR() {
        return statusR;
    }

    public void setStatusR(Date statusR) {
        this.statusR = statusR;
    }

    public Date getStatusNr() {
        return statusNr;
    }

    public void setStatusNr(Date statusNr) {
        this.statusNr = statusNr;
    }

    public Date getStatusI() {
        return statusI;
    }

    public void setStatusI(Date statusI) {
        this.statusI = statusI;
    }

    public Date getMga() {
        return mga;
    }

    public void setMga(Date mga) {
        this.mga = mga;
    }

    public Date getMra() {
        return mra;
    }

    public void setMra(Date mra) {
        this.mra = mra;
    }

    public Date getIga() {
        return iga;
    }

    public void setIga(Date iga) {
        this.iga = iga;
    }

    public Date getInga() {
        return inga;
    }

    public void setInga(Date inga) {
        this.inga = inga;
    }

    public Boolean getIfb() {
        return ifb;
    }

    public void setIfb(Boolean ifb) {
        this.ifb = ifb;
    }

    public Boolean getIfr() {
        return ifr;
    }

    public void setIfr(Boolean ifr) {
        this.ifr = ifr;
    }

    public Boolean getHivHistory() {
        return hivHistory;
    }

    public void setHivHistory(Boolean hivHistory) {
        this.hivHistory = hivHistory;
    }

}