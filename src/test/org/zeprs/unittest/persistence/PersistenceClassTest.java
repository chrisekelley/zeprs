package org.zeprs.unittest.persistence;

import junit.framework.TestCase;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.logic.PersistenceManager;
import org.cidrz.webapp.dynasite.logic.PersistenceManagerFactory;

import java.util.List;

public class PersistenceClassTest extends TestCase {
    private final Class clazz;

    public PersistenceClassTest(Class clazz) {
        super("test"); // Passing test method name
        this.clazz = clazz;
    }

    public void test() {
        try {
            PersistenceManager pm = PersistenceManagerFactory.getInstance(clazz);
            assertNotNull("No PersistenceManager for class: " + clazz.getName(), pm);

            List all = pm.getAll();
            assertNotNull("Got an empty list back for this class: " + clazz.getName(), all);

            try {
                Object o = pm.getOne(Identifiable.NEW);
                fail("Hmmm, all the id's should be > 0");
            } catch (ObjectNotFoundException e) {
                //ok
            }
        } catch (PersistenceException e) {
            fail(e.getMessage());
        }
    }

    public String getName() {
        // Test representation in tree view
        return clazz.getName();
    }
}
