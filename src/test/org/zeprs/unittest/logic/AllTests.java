package org.zeprs.unittest.logic;

import junit.framework.Test;
import junit.framework.TestSuite;


public class AllTests extends TestSuite {
    public AllTests() {
        super("All logic tests");
        addTestSuite(org.zeprs.unittest.logic.EncounterManagerTest.class);
        addTestSuite(org.zeprs.unittest.logic.RuleTest.class);
    }

    public static Test suite() {
        return new AllTests();
    }
}
