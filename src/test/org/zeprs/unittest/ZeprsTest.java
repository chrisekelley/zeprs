package org.zeprs.unittest;

import junit.framework.Test;
import junit.framework.TestCase;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;

import javax.servlet.ServletException;
import java.security.Principal;
import java.sql.Connection;

/**
 * Created by IntelliJ IDEA.
 * User: dave
 * Date: Mar 8, 2004
 * Time: 12:17:48 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class ZeprsTest extends TestCase {
    private static final Long PERSISTED_PATIENT_ID = new Long(1);
    protected static final Long CURRENT_PATIENT_ID = new Long(36);
    protected static final Long CURRENT_PREGNANCY_ID = new Long(30);
    protected static final Long CURRENT_FLOW_ID = new Long(100);
    protected static final Long CURRENT_SITE_ID = new Long(1);
    protected static final String LOGFILE = "C:\\Documents and Settings\\ckelley\\IdeaProjects\\zeprs\\src\\zeprs\\log4j.properties";


    protected Patient getPersistedPatient() {

         Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(null);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        try {
            return (Patient) PatientDAO.getOne(conn, PERSISTED_PATIENT_ID);
        } catch (Exception e) {
            fail("Persisted patient not found: " + e.getMessage());
        }
        return null;
    }

    protected Principal getPrincipal() {
        return new TestPrincipal(this);
    }

}

class TestPrincipal implements Principal {
    private Test test;

    public TestPrincipal(Test test) {
        this.test = test;
    }

    public String getName() {
        return test.getClass().getName();
    }

}
