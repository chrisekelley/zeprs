/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.rules.impl;

import bsh.Interpreter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.rules.Outcome;
import org.cidrz.webapp.dynasite.rules.OutcomeException;
import org.cidrz.webapp.dynasite.rules.Rule;
import org.cidrz.webapp.dynasite.rules.RuleSubject;
import org.cidrz.webapp.dynasite.valueobject.*;
import org.cidrz.project.zeprs.valueobject.EncounterData;

import java.util.Map;


/**
 * This is a rule implementation that uses BeanShell (http://www.beanshell.org) as an interpreter
 * for evaluating the supplied expressions. Expressions for this application should evaluate to a
 * boolean result. ScriptRule instances can be attached to any RuleSubject (i.e. Forms and
 * FormFields). A special variable is available to use in the expression: patient. Patient is a
 * proxy of the PatientRecord. It implements the PatientRecord interface.
 * <p/>
 * If the expression evaluates "true", then an OutcomeException will be thrown.
 * <p/>
 * Here are some example expressions:
 * 1==1 (true)
 * 1==4 (false)
 * 7>10 (false)
 * patient.getMostRecentInt(115) > 100  (checks the most recent response to field 115 in the PatientRecord)
 */
public class ScriptRule implements Rule {
    /**
     * Commons Logging instance.
     */
    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    private String operand;
    private Outcome outcome;
    private RuleDefinition ruleDef;

    /**
     * Evaluates the expression.
     *
     * @param subject
     * @throws org.cidrz.webapp.dynasite.rules.OutcomeException
     *          If the expression evaluates to "true"
     */
    public void evaluate(RuleSubject subject, RuleDefinition ruleDef) throws OutcomeException {
        boolean result = false; //default to not throwing the exception.
        try {
            if (!subject.getEvaluationValue().toString().equals("")) {
                Float subjectValue = new Float(subject.getEvaluationValue().toString());
                Interpreter i = new Interpreter();  // Construct an interpreter
                SessionPatient sessionPatient = null;
                EncounterData encounter = null;
                if (subject instanceof EncounterValue) {
                    sessionPatient = ((EncounterValue) subject).getEncounter().getSessionPatient();
                    encounter = ((EncounterValue) subject).getEncounter();
                }
                // String operand = (String) ruleDef.getRuleParams().get("operand");
                String operand = ruleDef.getOperand();
                Map encounterMap = encounter.getEncounterMap();
                int formId = encounter.getFormId().intValue();
                this.operand = operand;
                i.set("sessionPatient", new SessionPatientRecordProxy(sessionPatient));
                i.set("subjectValue", subjectValue);
                i.set("formId", formId);
                i.set("encounterMap", encounterMap);
                result = ((Boolean) i.eval(this.operand)).booleanValue();
            }

        } catch (Exception e) {
            log.error("failed to evaluate expression in rule id " + ruleDef.getId() + ": " + operand + "\nError: " + e.getMessage());
        }
        if (result) {
            outcome.setFormFieldId(subject.getFormFieldId());
            outcome.setRuleDefinitionId(ruleDef.getId());
            throw new OutcomeException(outcome);
        }
    }

    public String getOperand() {
        return operand;
    }

    public void setOperand(String operand) {
        this.operand = operand;
    }

    // void evaluate(RuleSubject subject) throws OutcomeException;

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public void setRuleDefinition(RuleDefinition ruleDef) {
        this.ruleDef = ruleDef;
    }

    public RuleDefinition getRuleDefinition() {
        return ruleDef;
    }
}

class PatientRecordProxy implements PatientRecord {
    private PatientRecord record = null;
    private Patient patient = null;

    PatientRecordProxy(PatientRecord record) {
        this.record = record;
    }

    PatientRecordProxy(Patient patient) {
        this.patient = patient;
    }

    public String getMostRecentString(int field_id) throws ObjectNotFoundException, PersistenceException {
        return record.getMostRecentString(field_id);
    }

    public int getMostRecentInt(int field_id) throws ObjectNotFoundException, PersistenceException {
        return record.getMostRecentInt(field_id);
    }

    public float getMostRecentFloat(int field_id) throws ObjectNotFoundException, PersistenceException {
        return record.getMostRecentFloat(field_id);
    }

    public Patient getPatient() {
        return record.getPatient();
    }
}

class SessionPatientRecordProxy implements SessionPatientRecord {
    private SessionPatientRecord record = null;
    private SessionPatient sessionPatient = null;

    SessionPatientRecordProxy(SessionPatientRecord record) {
        this.record = record;
    }

    SessionPatientRecordProxy(SessionPatient sessionPatient) {
        this.sessionPatient = sessionPatient;
    }

    public SessionPatient getSessionPatient() {
        return record.getSessionPatient();
    }

     public boolean isMotherHivPositive() {
        return sessionPatient.isMotherHivPositive();
    }
}
