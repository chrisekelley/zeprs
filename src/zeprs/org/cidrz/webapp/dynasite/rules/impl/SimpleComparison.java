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
import org.cidrz.webapp.dynasite.valueobject.RuleDefinition;
import org.cidrz.webapp.dynasite.rules.Rule;
import org.cidrz.webapp.dynasite.rules.Outcome;
import org.cidrz.webapp.dynasite.rules.OutcomeException;
import org.cidrz.webapp.dynasite.rules.RuleSubject;


public class SimpleComparison implements Rule {

    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    public float getOperand() {
         return operand;
        // float operand = new Float(ruleDef.getOperand());
    }

    public void setOperand(float operand) {
        this.operand = operand;
    }

    public String getOperator() {
        return operator;
        // return ruleDef.getOperator();
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    private float operand;
    private String operator;

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
        try {
            if (!subject.getEvaluationValue().toString().equals("")) {
                /*String hack = String.valueOf(this.operand);
                if ( operator.equals("eq") )
            {
                if ( hack.equals("0") )
                {
                    throw new OutcomeException(outcome);
                }
            }

            } else {*/

                Float subjectValue = new Float(subject.getEvaluationValue().toString());
                if (operator.equals("gte")) {
                    if (subjectValue.floatValue() >= this.operand) {
                        outcome.setFormFieldId(subject.getFormFieldId());
                        outcome.setRuleDefinitionId(ruleDef.getId());
                        throw new OutcomeException(outcome);
                    }
                } else if (operator.equals("eq")) {
                    if (subjectValue.floatValue() == this.operand) {
                        outcome.setFormFieldId(subject.getFormFieldId());
                        outcome.setRuleDefinitionId(ruleDef.getId());
                        throw new OutcomeException(outcome);
                    }
                } else if (operator.equals("lte")) {
                    if (subjectValue.floatValue() <= this.operand) {
                        outcome.setFormFieldId(subject.getFormFieldId());
                        outcome.setRuleDefinitionId(ruleDef.getId());
                        throw new OutcomeException(outcome);
                    }
                } else if (operator.equals("lt")) {
                    if (subjectValue.floatValue() < this.operand) {
                        outcome.setFormFieldId(subject.getFormFieldId());
                        outcome.setRuleDefinitionId(ruleDef.getId());
                        throw new OutcomeException(outcome);
                    }
                } else if (operator.equals("gt")) {
                    if (subjectValue.floatValue() > this.operand) {
                        outcome.setFormFieldId(subject.getFormFieldId());
                        outcome.setRuleDefinitionId(ruleDef.getId());
                        throw new OutcomeException(outcome);
                    }
                } else if (operator.equals("ne")) {
                    if (subjectValue.floatValue() != this.operand) {
                        outcome.setFormFieldId(subject.getFormFieldId());
                        outcome.setRuleDefinitionId(ruleDef.getId());
                        throw new OutcomeException(outcome);
                    }
                } else {
                    log.error("Evaluation failed. Bad operator: " + this.operator);
                }
            }
        } catch (NumberFormatException e) {
            log.error("Evaluation failed. Subject supplied an incompatible evaluationValue type. " + e.getMessage());
            log.error("Form Field: "+ ruleDef.getField().getLabel());
            log.error("Rule ID: "+ ruleDef.getId().toString());
        }
    }
}
