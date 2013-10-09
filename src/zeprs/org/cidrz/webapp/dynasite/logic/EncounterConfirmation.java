/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.logic;

import org.cidrz.project.zeprs.valueobject.BaseEncounter;
import org.cidrz.webapp.dynasite.rules.Outcome;
import org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dave
 * Date: Mar 1, 2004
 * Time: 3:58:50 PM
 * To change this template use File | Settings | File Templates.
 */

public class EncounterConfirmation {
    private BaseEncounter encounter;
    private List outcomes = new ArrayList();

    public EncounterConfirmation(BaseEncounter encounter) {
        this.encounter = encounter;
    }

    public BaseEncounter getEncounter() {
        return encounter;
    }

    public void addOutcomes(List allOutcomes) throws NullPointerException {
        this.outcomes.addAll(allOutcomes);
    }

    public List getOutcomes() {
        return outcomes;
    }

    public List getOutcomes(Class outcomeType) {
        List filtered = new ArrayList();
        List allOutcomes = getOutcomes();
        for (int i = 0; i < allOutcomes.size(); i++) {
            Object o = allOutcomes.get(i);
            if (o.getClass().isAssignableFrom(outcomeType)) {
                filtered.add(o);
            }
        }
        return filtered;
    }

    private List referralReasons = null;

    /**
     * Gets a List of the reasons for referral. Should be used after checking requiresReferral(). One can not
     * assume that an empty reason list means referral is not required.
     *
     * @return List of string reasons (possibly empty)
     */

    public List getReferralReasons() {
        if (referralReasons == null) {
            referralReasons = new ArrayList();
            Outcome outcome;
            for (int i = 0; i < outcomes.size(); i++) {
                outcome = (Outcome) outcomes.get(i);
                if (outcome instanceof ReferralOutcome) {
                    referralReasons.add(((ReferralOutcome) outcome).getReason());
                }
            }
        }
        return referralReasons;
    }

    /**
     * Convenience method used to determine if a referral is required.
     *
     * @return True if a referral is required.
     */
    public boolean getRequiresReferral() {
        if (referralReasons != null) {
            return true;
        }
        Outcome outcome;
        for (int i = 0; i < outcomes.size(); i++) {
            outcome = (Outcome) outcomes.get(i);
            if (outcome instanceof ReferralOutcome) {
                return true;
            }
        }
        return false;
    }
}
