package org.zeprs.unittest.torque;

import org.cidrz.webapp.dynasite.valueobject.Address;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.zeprs.unittest.ZeprsTest;

/**
 * Created by IntelliJ IDEA.
 * User: dave
 * Date: Mar 17, 2004
 * Time: 3:54:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class TorqueBehaviorTest extends ZeprsTest {
    /**
     * Tests to see if changes to a ono-to-one mapped child are
     * persisted when the parent is saved.
     */
    public void testOneToOneSaveChild() {
        try {
            Patient p = new Patient();
            p.setFirstName("Jane");
            p.setSurname("Doe");

            Address a = new Address();
            a.setLine1("foo");
            a.setLine2("bar");
            p.setAddress(a);
            //p.save();

            assertNotNull("id is not set", p.getId());
            //p = PatientPeer.retrieveByPK(p.getId());
            assertNotNull("patient is null", p);
            assertNotNull("patient.address is null", p.getAddress());

            p.getAddress().setLine1("changed");
            //p.save();

            //p = PatientPeer.retrieveByPK(p.getId());
            assertNotNull("patient is null", p);
            assertNotNull("patient.address is null", p.getAddress());
            assertEquals("address change was not persisted", "changed", p.getAddress().getLine1());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
