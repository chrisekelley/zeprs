package org.zeprs.unittest.persistence;

import org.cidrz.webapp.dynasite.dao.FormDisplayDAO;
import org.zeprs.unittest.ZeprsTest;

import javax.servlet.ServletException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Dave Peckham
 * Date: Mar 29, 2004
 * Time: 3:55:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class FormTest extends ZeprsTest {

    private String listName = "Forms";
    private String name = "Form";
    private String className = "org.cidrz.webapp.dynasite.valueobject.Form";
    private String dao = "org.cidrz.webapp.dynasite.dao.FormDisplayDAO";
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
            m = daoClazz.getDeclaredMethod("getFormGraph", argClazz);
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

    public void testFormList() {

        // here's the line I added to make the logging output work ...
        //PropertyConfigurator.configure(LOGFILE);

        Long formId = new Long("1");
        List list = null;
        try {
            list = FormDisplayDAO.getFormTop(formId);
        } catch (SQLException e) {
            e.printStackTrace();
            fail("failed to get list");
        } catch (ServletException e) {
            e.printStackTrace();
            fail("failed to get list");
        }
        assertNotNull("Form was null", list);
    }


}
