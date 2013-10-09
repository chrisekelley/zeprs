package org.zeprs.unittest.xml;

import org.zeprs.unittest.ZeprsTest;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.PageItem;
import org.cidrz.webapp.dynasite.dao.PageItemDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;

import javax.sql.DataSource;
import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.SQLException;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Dave Peckham
 * Date: Mar 29, 2004
 * Time: 3:55:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class PatientCompareTest extends ZeprsTest {

    private String listName = "PageItems";
    private String name = "PageItem";
    private String className = "org.cidrz.webapp.dynasite.valueobject.PageItem";
    private String dao = "org.cidrz.webapp.dynasite.dao.PageItemDAO";
    private Class[] argClazz = new Class[]{Connection.class, Long.class};
    private Connection conn = null;
    private Object[] args = new Object[]{};
    private Object object;

    //  private Class[] argClazz = new Class[]{Long.class, Long.class};
   //  private Object[] args = new Object[]{new Long("1"), new Long("1")};

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

        try {
            DataSource ds = DatabaseUtils.getAdminUnitTestDatasource();
            conn = ds.getConnection();
            Method m = null;
            try {
                m = daoClazz.getDeclaredMethod("getOne", argClazz);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            args = new Object[]{conn, new Long("10")};

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
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        assertNotNull(name + " was null", object);
    }





}
