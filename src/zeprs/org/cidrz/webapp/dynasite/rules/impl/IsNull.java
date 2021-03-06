/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

/* Generated by Together */

package org.cidrz.webapp.dynasite.rules.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.rules.Outcome;
import org.cidrz.webapp.dynasite.rules.OutcomeException;
import org.cidrz.webapp.dynasite.rules.Rule;
import org.cidrz.webapp.dynasite.rules.RuleSubject;
import org.cidrz.webapp.dynasite.valueobject.RuleDefinition;


public class IsNull implements Rule {

    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    public int getOperand() {
        return operand;
    }

    public void setOperand(int operand) {
        this.operand = operand;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    private int operand;
    private String operator;

    // void evaluate(RuleSubject subject) throws OutcomeException;


    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    private Outcome outcome;

    public void setRuleDefinition(RuleDefinition ruleDef) {
        this.ruleDef = ruleDef;
    }

    public RuleDefinition getRuleDefinition() {
        return ruleDef;
    }

    private RuleDefinition ruleDef;


    public void evaluate(RuleSubject subject, RuleDefinition ruleDef) throws OutcomeException {
        if (subject.getEvaluationValue().toString().equals("")) {
            outcome.setFormFieldId(subject.getFormFieldId());
            outcome.setRuleDefinitionId(ruleDef.getId());
            throw new OutcomeException(outcome);

        }
    }
}
