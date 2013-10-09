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

import org.cidrz.project.zeprs.valueobject.report.gen.InfantDischargeSummaryReport;
import org.cidrz.project.zeprs.valueobject.report.gen.NewbornEvalReport;
import org.cidrz.project.zeprs.valueobject.report.gen.StillbirthDeliveryRecordReport;
import org.cidrz.webapp.dynasite.valueobject.Patient;

import java.util.List;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Dec 8, 2005
 *         Time: 3:55:21 PM
 */
public class NewbornReport extends Patient {
    private NewbornEvalReport newbornEval;
    private InfantDischargeSummaryReport infantDischarge;
    private StillbirthDeliveryRecordReport stillbirth;
    private List postnatalVisits;

    public NewbornEvalReport getNewbornEval() {
        return newbornEval;
    }

    public void setNewbornEval(NewbornEvalReport newbornEval) {
        this.newbornEval = newbornEval;
    }

    public InfantDischargeSummaryReport getInfantDischarge() {
        return infantDischarge;
    }

    public void setInfantDischarge(InfantDischargeSummaryReport infantDischarge) {
        this.infantDischarge = infantDischarge;
    }

    public StillbirthDeliveryRecordReport getStillbirth() {
        return stillbirth;
    }

    public void setStillbirth(StillbirthDeliveryRecordReport stillbirth) {
        this.stillbirth = stillbirth;
    }

    public List getPostnatalVisits() {
        return postnatalVisits;
    }

    public void setPostnatalVisits(List postnatalVisits) {
        this.postnatalVisits = postnatalVisits;
    }
}
