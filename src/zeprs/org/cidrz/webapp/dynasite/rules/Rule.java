/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.rules;

import org.cidrz.webapp.dynasite.valueobject.RuleDefinition;


public interface Rule {
    /**
     * evaluates this Rule against the supplied RuleSubject.
     */
    // void evaluate(RuleSubject subject) throws OutcomeException;

    void evaluate(RuleSubject subject, RuleDefinition ruleDef) throws OutcomeException;

    void setOutcome(Outcome outcome);

    void setRuleDefinition(RuleDefinition ruleDef);

    RuleDefinition getRuleDefinition();
}
