package org.zeprs.unittest.persistence;

import org.cidrz.webapp.dynasite.dao.OutcomeDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.rules.impl.InfoOutcome;
import org.cidrz.webapp.dynasite.rules.Outcome;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.zeprs.unittest.ZeprsTest;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.sql.Connection;

/**
 * Created by IntelliJ IDEA.
 * User: Dave Peckham
 * Date: Jun 11, 2004
 * Time: 1:33:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class OutcomeTest extends ZeprsTest {

    private String listName = "Outcomes";
    private String name = "Outcome";
    private String className = "org.cidrz.webapp.dynasite.rules.Outcome";
    private String dao = "org.cidrz.webapp.dynasite.dao.OutcomeDAO";
    private Class[] argClazz = new Class[]{Long.class};
    private Object[] args = new Object[]{new Long("98")};

    public void testOne() {

        Outcome object = new InfoOutcome();
         Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(null);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        try {
            //object = FlowDAO.getOne(id);
            //object = m.invoke(object, args);
            OutcomeDAO.getOne(conn, new Long("98"));
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Failed to get " + name);
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
            fail("Failed to get " + name);
        } catch (ServletException e) {
            e.printStackTrace();
            fail("Failed to get " + name);
        }
        assertNotNull(name + " was null", object);
    }

    /*public void testInheritance() {
        InfoOutcome io = new InfoOutcome();
        io.setMessage("foo");
        Site site = new Site();
        site.setId(new Long("1"));
        try {
            Outcome saved = (Outcome) PersistenceManagerFactory.getInstance(Outcome.class).save(io, this.getPrincipal(), site);
            // List allOutcomes = PersistenceManagerFactory.getInstance(Outcome.class).getAll();
            List allOutcomes = OutcomeDAO.getAll(new Long("1"),new Long("1"), Boolean.TRUE);
            Outcome test;
            boolean found = false;
            for (int i = 0; i < allOutcomes.size(); i++) {
                test = (Outcome) allOutcomes.get(i);
                if (test.getId().equals(saved.getId())) {
                    found = true;
                    break;
                }
            }
            if (!found) fail("new outcome not retreived.");

            assertEquals("messages not same.", io.getMessage(), ((InfoOutcome) saved).getMessage());
        } catch (PersistenceException e) {
            fail(e.getMessage());
        }
    }*/
}
