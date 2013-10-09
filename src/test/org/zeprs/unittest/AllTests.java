package org.zeprs.unittest;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests extends TestSuite {
    public AllTests() {
        super("All tests");
        //addTest(org.zeprs.unittest.logic.AllTests.suite());
        addTest(org.zeprs.unittest.persistence.AllTests.suite());
        addTest(org.zeprs.unittest.struts.AllTests.suite());
    }

    public static Test suite() {
        return new AllTests();
    }
}
