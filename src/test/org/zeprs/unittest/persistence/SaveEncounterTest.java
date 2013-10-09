package org.zeprs.unittest.persistence;

import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.project.zeprs.valueobject.gen.PregnancyDating;
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
public class SaveEncounterTest extends ZeprsTest {
    /**
     * This test creates a visit that has data that reports to patient_status table and saves it.
     */
    public void testSaveEncounter() {

        PregnancyDating formObj = new PregnancyDating();
        formObj.setPatientId(CURRENT_PATIENT_ID);
        formObj.setPregnancyId(CURRENT_PREGNANCY_ID);
        formObj.setSiteId(CURRENT_SITE_ID);
        // set today's date
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "yyyy-MM-dd";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        String now = sdf.format(cal.getTime());
        Date visitDateD = Date.valueOf(now);
        formObj.setDateVisit(visitDateD);
        // set flowId
        formObj.setFlowId(new Long("2"));
        // set formId
        formObj.setFormId(new Long("82"));
        // get the form definition
        Form formDef = null;
        try {
            Connection conn = DatabaseUtils.getAdminConnection();
            formDef = (Form) FormDisplayDAO.getFormGraph(conn, new Long("82"));
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
        // now let's load this up with data
        formObj.setField135(new Byte("1"));
        formObj.setField136(null);
        formObj.setField137(null);
        formObj.setField138(null);
        formObj.setField126(new Integer("67"));
        formObj.setField127(Date.valueOf("2005-2-14"));
        formObj.setField128(Date.valueOf("2005-11-22"));
        formObj.setField129(new Integer("115"));
        formObj.setField130(null);
        formObj.setField131(new Integer("68"));
        formObj.setField132(new Integer("4"));
        formObj.setField188(new Integer("4"));
        formObj.setField1615(new Integer("2805"));

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
