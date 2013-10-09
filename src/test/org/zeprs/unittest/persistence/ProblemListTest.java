package org.zeprs.unittest.persistence;

import org.apache.log4j.PropertyConfigurator;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.zeprs.unittest.ZeprsTest;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by IntelliJ IDEA.
 * User: Dave Peckham
 * Date: Mar 29, 2004
 * Time: 3:55:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProblemListTest extends ZeprsTest {

    public void testProblemList() {
         Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(null);
        } catch (ServletException e) {
            e.printStackTrace();
        }

        /*sampleDao = new SampleDAO();
        DataSource ds = new DataSource();
        ds.setDriverClassName("org.hsqldb.jdbcDriver");
        ds.setUrl("jdbc:hsqldb:hsql://localhost");
        ds.setUsername("sa");
        ds.setPassword("");
        sampleDao.setDataSource(ds);*/

        // here's the line I added to make the logging output work ...
        PropertyConfigurator.configure(LOGFILE);

        Long patientId = CURRENT_PATIENT_ID;
        Long pregnancyId = CURRENT_PREGNANCY_ID;
        Boolean status = Boolean.TRUE;
        SessionPatient sessionPatient = new SessionPatient();
        List activeProblems = null;
        try {
             activeProblems = PatientRecordUtils.assembleProblemList(conn, patientId, pregnancyId, status);
        } catch (SQLException e) {
            e.printStackTrace();
            fail("failed to get activeProblems");
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
            fail("failed to get activeProblems");
        } catch (PersistenceException e) {
            e.printStackTrace();
            fail("failed to get activeProblems");
        } catch (ServletException e) {
            e.printStackTrace();
            fail("failed to get activeProblems");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail("failed to get activeProblems");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        assertNotNull("confirmation was null", activeProblems);
    }
}
