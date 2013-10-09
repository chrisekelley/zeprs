package org.zeprs.unittest.logic;

import org.apache.commons.beanutils.BeanUtils;
import org.cidrz.project.zeprs.valueobject.BaseEncounter;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.dao.SiteDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.exception.PopulationException;
import org.cidrz.webapp.dynasite.logic.EncounterConfirmation;
import org.cidrz.webapp.dynasite.logic.EncounterProcessor;
import org.cidrz.webapp.dynasite.logic.PersistenceManagerFactory;
import org.cidrz.webapp.dynasite.rules.impl.EncounterOutcome;
import org.cidrz.webapp.dynasite.rules.impl.InfoOutcome;
import org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome;
import org.cidrz.webapp.dynasite.utils.BeanPopulator;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.valueobject.Site;
import org.zeprs.unittest.ZeprsTest;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: dave
 * Date: Feb 24, 2004
 * Time: 5:21:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class EncounterManagerTest extends ZeprsTest {
    private static final int INITIAL_PATIENT_RECORD_FORM_ID = 1;
    private static final int ENCOUNTER_ACTION_TEST_FORM_ID = 2;
    private static final int SCRIPT_RULE_TEST_FORM_ID = 3;
    private static final int TEST_SITE_ID = 4;
    private static final String ENCOUNTER_ACTION_TEST_FIELD = "field105";
    private static final String REFERRAL_ACTION_TEST_FIELD = "field106";
    private static final int MOST_RECENT_TEST_FIELD_ID = 115;
    private Patient testPatient = null;

    protected void tearDown() throws Exception {
        //PatientPeer.doDelete(testPatient);
        testPatient = null;
        super.tearDown();
    }

    protected void setUp() throws Exception {
        super.setUp();
        testPatient = new Patient();
        testPatient.setFirstName(this.getClass().getName() + this.hashCode());
        testPatient.setSurname(this.getClass().getName() + this.hashCode());
        //testPatient.save();
    }

    public void testEncounterOutcome() {
        Map values = new HashMap();
        values.put(ENCOUNTER_ACTION_TEST_FIELD, "2"); //not >=10

        BaseEncounter encounter;

        //case 1
        encounter = getNewEncounter(ENCOUNTER_ACTION_TEST_FORM_ID, testPatient);
        Form formDef = null;
        // Get the form - used for evaluate
        try {
            formDef = (Form) PersistenceManagerFactory.getInstance(Form.class).getOneForm(encounter.getId());
        } catch (PersistenceException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        try {
            try {
                BeanPopulator.populate(values, encounter);
            } catch (ObjectNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (PersistenceException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        } catch (PopulationException e) {
            fail("population failure: " + e.getMessage());
        }
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(null);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        EncounterConfirmation confirmation = EncounterProcessor.getConfirmation(conn, formDef, encounter, this.getPrincipal().getName());
        assertNotNull("confirmation is null", confirmation);
        assertEquals("confirmation contained unexpected Outcomes", 0, confirmation.getOutcomes().size());
        //EncounterRecordPeer.doDelete(encounter);

        //case 2
        values.put(ENCOUNTER_ACTION_TEST_FIELD, "200"); // >=10
        encounter = getNewEncounter(ENCOUNTER_ACTION_TEST_FORM_ID, testPatient);
        try {
            BeanPopulator.populate(values, encounter);
        } catch (PopulationException e) {
            fail("population failure: " + e.getMessage());
        } catch (PersistenceException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        confirmation = EncounterProcessor.getConfirmation(conn, formDef, encounter, this.getPrincipal().getName());
        assertNotNull("confirmation is null", confirmation);
        assertEquals("confirmation contained unexpected Outcomes", 1, confirmation.getOutcomes().size());
        EncounterOutcome outcome = null;
        try {
            outcome = (EncounterOutcome) confirmation.getOutcomes().get(0);
        } catch (ClassCastException e) {
            fail("outcome of wrong type. expected EncounterOutcome, got " + outcome.getClass());
        }
        assertEquals("outcome has incorrect forward", new Integer(2), outcome.getRequiredFormId());
        //EncounterRecordPeer.doDelete(encounter);

        //case 3
        values = new HashMap();
        values.put(REFERRAL_ACTION_TEST_FIELD, "200"); // >=10
        encounter = getNewEncounter(ENCOUNTER_ACTION_TEST_FORM_ID, testPatient);
        try {
            BeanPopulator.populate(values, encounter);
        } catch (PopulationException e) {
            fail("population failure: " + e.getMessage());
        } catch (PersistenceException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        confirmation = EncounterProcessor.getConfirmation(conn, formDef, encounter, this.getPrincipal().getName());
        assertNotNull("confirmation is null", confirmation);
        assertEquals("confirmation contained unexpected Outcomes", 1, confirmation.getOutcomes().size());
        assertEquals("confirmation contained unexpected Outcomes", 1, confirmation.getOutcomes(ReferralOutcome.class).size());
        assertEquals("confirmation contained unexpected Outcomes", 0, confirmation.getOutcomes(InfoOutcome.class).size());
        ReferralOutcome refferalOutcome = null;
        try {
            refferalOutcome = (ReferralOutcome) confirmation.getOutcomes().get(0);
        } catch (ClassCastException e) {
            fail("outcome of wrong type. expected ReferralOutcome, got " + refferalOutcome.getClass());
        }
        assertNotNull("referral has no reason.", refferalOutcome.getReason());
        assertTrue("confirmation is not properly summarizing referral.", confirmation.getRequiresReferral());
        assertEquals("referral reasons list has incorrect count.", 1, confirmation.getReferralReasons().size());
        assertNotNull("first referral reason is null", confirmation.getReferralReasons().get(0));
        //EncounterRecordPeer.doDelete(encounter);
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void testScriptRule() {
        Map values = new HashMap();

        BaseEncounter encounter;
        Site site = new Site();
        site.setId(new Long("1"));
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(null);
        } catch (ServletException e) {
            e.printStackTrace();
        }

        try {
            encounter = getNewEncounter(SCRIPT_RULE_TEST_FORM_ID, getPersistedPatient());
            try {
                //todo use our domain specific BeanPopulator and a dynaBean
                BeanUtils.populate(encounter, values);
            } catch (Exception e) {
                fail("failed to populate the encounter.");
            }
            PersistenceManagerFactory.getInstance(BaseEncounter.class).save((Identifiable) encounter, getPrincipal(), site);

            Form formDef = null;
            // Get the form - used for evaluate
            try {
                formDef = (Form) PersistenceManagerFactory.getInstance(Form.class).getOneForm(encounter.getId());
            } catch (PersistenceException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (ObjectNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }


            EncounterConfirmation confirmation = EncounterProcessor.getConfirmation(conn, formDef, encounter, this.getPrincipal().getName());
            assertNotNull("confirmation is null", confirmation);
            assertEquals("confirmation contained unexpected Outcomes", 1, confirmation.getOutcomes().size());
            try {
                InfoOutcome infoOutcome = (InfoOutcome) confirmation.getOutcomes().get(0);
            } catch (ClassCastException e) {
                fail("outcome was not of the expected type." + e.getMessage());
            }
        } catch (PersistenceException e) {
            fail(e.getMessage());
        }
    }

    private BaseEncounter getNewEncounter(int form_id, Patient patient) {
        Connection conn = null;
        EncounterData encounter = new EncounterData();
        try {
            conn = DatabaseUtils.getZEPRSConnection(null);

            Long formId = new Long(form_id);
            try {
                encounter.setFormId(formId);
            } catch (Exception e) {
                fail("Failed to fetch the requested form, id=" + form_id + "... " + e.getMessage());
            }
            encounter.setPatientId(patient.getId());
            try {
                Site site = (Site) SiteDAO.getOne(conn, new Long(TEST_SITE_ID),"id");
                encounter.setSiteId(site.getId());
            } catch (Exception e) {
                fail("Failed to fetch the test site, id=" + TEST_SITE_ID + "... " + e.getMessage());
            }
            //encounter.setLastModifiedBy(this.getClass().getName() + this.hashCode());
        } catch (ServletException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return encounter;
    }

    public void testGetRecent() {
        /*try {
            Patient p = getPersistedPatient();
            assertEquals("most recent value is incorrect", "100", p.getMostRecentString(MOST_RECENT_TEST_FIELD_ID));
        } catch (ObjectNotFoundException e) {
            fail("ObjectNotFoundException " + e.getMessage());
        }*/
    }

    public void testNewPatient() {
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(null);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        Patient p = new Patient();
        BaseEncounter rec = getNewEncounter(INITIAL_PATIENT_RECORD_FORM_ID, p);
        // p.getEncounters().add(rec);
        Map values = new HashMap();
        values.put("field6", "surname");
        values.put("field7", "firstName");
        values.put("field9", "123456/12/1");
        Site site = new Site();
        site.setId(new Long("1"));
        try {
            BeanPopulator.populate(values, rec);
            p = (Patient) PersistenceManagerFactory.getInstance(Patient.class).save(p, getPrincipal(), site);
        } catch (PersistenceException e) {
            fail(e.getMessage());
        } catch (PopulationException e) {
            fail(e.getMessage());
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        try {
            //p = (Patient) PersistenceManagerFactory.getInstance(Patient.class).getOne(p.getId());
            p = (Patient) PatientDAO.getOne(conn, p.getId());
            // assertNotNull("patient encounters is null", p.getEncounters());
           //  assertEquals("patient encounters count is wrong", 1, p.getEncounters().size());
            // todo - fix
            //rec = (EncounterRecord) p.getEncounters().iterator().next();
            //assertNotNull("encounter values is null", rec.getEncounterValues());
            //assertTrue("encounter values has wrong count", rec.getEncounterValues().size() >= 3);
        } catch (Exception e) {
            fail("patient not retreived: " + e.getMessage());
        }
    }
}
