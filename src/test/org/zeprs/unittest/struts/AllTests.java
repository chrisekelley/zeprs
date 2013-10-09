package org.zeprs.unittest.struts;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.zeprs.unittest.persistence.EncounterProblemTest;


public class AllTests extends TestSuite {
    public AllTests() {
        super("All struts tests");
        addTestSuite(ProblemActionTest.class);
    }

    public static Test suite() {
        return new AllTests();
    }
}
