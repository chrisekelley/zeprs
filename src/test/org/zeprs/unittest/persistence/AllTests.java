package org.zeprs.unittest.persistence;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.zeprs.unittest.struts.ProblemActionTest;

public class AllTests extends TestSuite {
    public AllTests() {
        super("All persistence tests");
        addTestSuite(EncounterProblemTest.class);
        addTestSuite(ProblemListTest.class);
        addTestSuite(CommentsListTest.class);
        addTestSuite(ClientSettingsTest.class);
        addTestSuite(StaffTest.class);
        addTestSuite(ProblemActionTest.class);
        addTestSuite(FormGraphTest.class);
        addTestSuite(SaveEncounterTest.class);
       /* addTestSuite(PatientGraphTest.class);
        addTest(PersistenceClassesSuite.suite());

        addTestSuite(MenuGraphTest.class);
        addTestSuite(ClientSettingsTest.class);
        addTestSuite(SimpleSaveTest.class);*/
    }

    public static Test suite() {
        return new AllTests();
    }
}
