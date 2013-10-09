package org.zeprs.unittest.persistence;

import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.zeprs.unittest.ZeprsTest;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by IntelliJ IDEA.
 * User: Dave Peckham
 * Date: Mar 29, 2004
 * Time: 3:55:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class PatientTest extends ZeprsTest {

     private String listName = "Patients";
    private String name = "Patient";
    private String className = "org.cidrz.webapp.dynasite.valueobject.Patient";
    private String dao = "org.cidrz.webapp.dynasite.dao.PatientDAO";
    private Class[] argClazz = new Class[]{Long.class};
    private Object[] args = new Object[]{new Long("1")};

    public void testOne() {

        Class clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Object object = null;
        try {
            object = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        Class daoClazz = null;
        try {
            daoClazz = Class.forName(dao);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Method m = null;
        try {
            m = daoClazz.getDeclaredMethod("getOne", argClazz);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            //object = FlowDAO.getOne(id);
            object = m.invoke(object, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail("Failed to get " + name);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            fail("Failed to get " + name);
        }
        assertNotNull(name + " was null", object);
    }

    public void testList() {

        Class daoClazz = null;
        try {
            daoClazz = Class.forName(dao);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        List list = null;
        Method m = null;
        try {
            m = daoClazz.getDeclaredMethod("getAll", new Class[]{});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            list = (List) m.invoke(null, new Object[]{});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail("Failed to get list of " + listName);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            fail("Failed to get list of " + listName);
        }
        assertNotNull(listName + " list was null", list);
    }

    public void testListOrder() {

        Class daoClazz = null;
        try {
            daoClazz = Class.forName(dao);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        List list = null;
        Method m = null;
        try {
            m = daoClazz.getDeclaredMethod("getAll", new Class[]{String.class});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            list = (List) m.invoke(list, new Object[]{new String("surname")});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail("Failed to get list of " + listName);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            fail("Failed to get list of " + listName);
        }
        assertNotNull(listName + " list was null", list);
    }

    /**
     * Used for patientID checker in patient registration form
     */
    public void testPatientId() {

        // here's the line I added to make the logging output work ...
        //PropertyConfigurator.configure(LOGFILE);
        Connection conn = DatabaseUtils.getAdminConnection();
        Boolean status = null;
        String patientId = "5040-330-99999-8";
        try {
             status = PatientDAO.checkPatientId(conn, patientId);
        } catch (SQLException e) {
            e.printStackTrace();
            fail("failed to get patientId");
        } catch (ServletException e) {
            e.printStackTrace();
            fail("failed to get patientId");
        }
        assertTrue("Not a unique ID - ID already in the db", status.booleanValue());

        status = null;
        patientId = "5040-330-11111-1";
        try {
             status = PatientDAO.checkPatientId(conn, patientId);
        } catch (SQLException e) {
            e.printStackTrace();
            fail("failed to get patientId");
        } catch (ServletException e) {
            e.printStackTrace();
            fail("failed to get patientId");
        }
        assertFalse("Unique ID - expected a non-unique ID", status.booleanValue());
    }
}
