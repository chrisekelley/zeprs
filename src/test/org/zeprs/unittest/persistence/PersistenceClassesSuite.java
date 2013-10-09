package org.zeprs.unittest.persistence;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.cidrz.webapp.dynasite.rules.impl.InfoOutcome;
import org.cidrz.webapp.dynasite.valueobject.*;

import java.util.ArrayList;
import java.util.List;

public class PersistenceClassesSuite extends TestSuite {
    public PersistenceClassesSuite() {
        super("Persistence Suite"); // Suite node name in tree view
        List classes = new ArrayList();
        classes.add(Address.class);
        classes.add(ClientSettings.class);
        // classes.add(DisplayHint.class);
        // classes.add(EncounterRecord.class);
        classes.add(EncounterValue.class);
        classes.add(FieldEnumeration.class);
        classes.add(Form.class);
        classes.add(FormField.class);
        classes.add(MenuItem.class);
        classes.add(Patient.class);
        classes.add(ReportSpec.class);
        classes.add(RuleDefinition.class);
        classes.add(Site.class);
        classes.add(UserGroup.class);
        classes.add(InfoOutcome.class);

        Class aClass;
        for (int i = 0; i < classes.size(); i++) {
            aClass = (java.lang.Class) classes.get(i);
            addTest(new PersistenceClassTest(aClass));
        }
    }

    public static Test suite() {
        return new PersistenceClassesSuite();
    }
}