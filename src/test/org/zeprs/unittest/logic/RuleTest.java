package org.zeprs.unittest.logic;

import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.FormField;
import org.cidrz.webapp.dynasite.valueobject.RuleDefinition;
import org.zeprs.unittest.ZeprsTest;

/**
 * Created by IntelliJ IDEA.
 * User: dave
 * Date: Mar 8, 2004
 * Time: 12:21:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class RuleTest extends ZeprsTest {
    private Form testForm = null;
    private FormField testField = null;

    public void testRules() {
        /*
        EncounterRecord encounter = new EncounterRecord();
        EncounterValue value = new EncounterValue();
        try
        {
            //encounter.addEncounterValue(value);
            encounter.setForm(testForm);
            //value.setFormField(testField);
            value.setValue("10");
            try
            {
                encounter.evaluate();
            }
            catch (OutcomeException oe)
            {
                List outcomes = oe.getAllOutcomes();
                assertEquals("incorrect number of outcomes", 1, outcomes.size());
                Outcome outcome = (Outcome) outcomes.get(0);
                assertEquals("outcome of wrong type", EncounterOutcome.class, outcome.getClass());
            }
            value.setValue("1");
            try
            {
                encounter.evaluate();
            }
            catch (OutcomeException oe)
            {
                fail("should have passed this one." + oe.getMessage());
            }
        }
        catch (TorqueException e)
        {
            fail(e.getMessage());
        }
        */
    }

    protected void setUp() throws Exception {
        super.setUp();
        testForm = new Form();
        testForm.setLabel(this.getClass().getName() + this.hashCode());
        testForm.setName(testForm.getLabel());
        testField = new FormField();
        testField.setLabel(this.getClass().getName() + this.hashCode());
        testField.setType("Integer");
        RuleDefinition ruleDef = new RuleDefinition();
        ruleDef.setRuleClass("org.cidrz.webapp.dynasite.rules.impl.SimpleComparison");
        ruleDef.setOutcomeClass("org.cidrz.webapp.dynasite.rules.impl.EncounterOutcome");
        /*
        RuleDefinitionParam param = new RuleDefinitionParam();
        param.setParamName("operator");
        param.setParamValue(">=");
        ruleDef.addRuleDefinitionParam(param);
        param = new RuleDefinitionParam();
        param.setParamName("operand");
        param.setParamValue("5");
        ruleDef.addRuleDefinitionParam(param);
        testField.addRuleDefinition(ruleDef);
        testForm.addFormField(testField);
        */
    }

    protected void tearDown() throws Exception {
        this.testForm = null;
        super.tearDown();
    }
}
