package org.zeprs.unittest.persistence;

import org.apache.log4j.PropertyConfigurator;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.project.zeprs.valueobject.gen.ProbLabor;
import org.cidrz.webapp.dynasite.dao.FormDAO;
import org.cidrz.webapp.dynasite.dao.FormDisplayDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.logic.EncounterConfirmation;
import org.cidrz.webapp.dynasite.logic.EncounterProcessor;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.zeprs.unittest.ZeprsTest;

import javax.servlet.ServletException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by IntelliJ IDEA.
 * User: Dave Peckham
 * Date: Mar 29, 2004
 * Time: 3:55:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class EncounterProblemTest extends ZeprsTest {
    /**
     * This test creates a Problem/Labour visit that triggers a problem and saves it.
     */
    public void testEncounterProblem() {

        /*sampleDao = new SampleDAO();
        DataSource ds = new DataSource();
        ds.setDriverClassName("org.hsqldb.jdbcDriver");
        ds.setUrl("jdbc:hsqldb:hsql://localhost");
        ds.setUsername("sa");
        ds.setPassword("");
        sampleDao.setDataSource(ds);*/

        // here's the line I added to make the logging output work ...
        PropertyConfigurator.configure(LOGFILE);


        ProbLabor formObj = new ProbLabor();
        formObj.setPatientId(CURRENT_PATIENT_ID);
        formObj.setPregnancyId(CURRENT_PREGNANCY_ID);
        formObj.setSiteId(CURRENT_SITE_ID);
        // set today's date
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "yyyy-MM-dd";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        String now = sdf.format(cal.getTime());
        Date visitDateD = java.sql.Date.valueOf(now);
        formObj.setDateVisit(visitDateD);
        // set unassigned flow
        formObj.setFlowId(new Long("100"));
        formObj.setFormId(new Long("65"));
        // get the form definition
        Form formDef = null;
        try {
            Connection conn = DatabaseUtils.getAdminConnection();
            formDef = (Form) FormDisplayDAO.getFormGraph(conn, new Long("65"));
        } catch (SQLException e) {
            e.printStackTrace();
            fail("failed to get formDef");
        } catch (ServletException e) {
            e.printStackTrace();
            fail("failed to get formDef");
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
            fail("failed to get formDef");
        }
        EncounterData vo = null;
        EncounterData enc = (EncounterData) formObj;
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(null);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        try {
            vo = FormDAO.create(conn, enc, "demo", new Long("1"), formDef, formDef.getFlowId());
        } catch (Exception e) {
            e.printStackTrace();
            fail("failed to save encounter");
        }

        EncounterConfirmation confirmation = null;
        //this persists any outcomes to db. We're not using the value it returns in the view at this poinr, but if
        // we needed it, simply pass it in the response.
        try {
            confirmation = EncounterProcessor.getConfirmation(conn, formDef, vo, "demo");
        } catch (Exception e) {
            e.printStackTrace();
            fail("failed to save outcomes");
        }
        assertNotNull("confirmation was null", confirmation);
    }
}
