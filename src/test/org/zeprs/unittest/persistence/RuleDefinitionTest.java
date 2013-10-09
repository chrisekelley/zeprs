package org.zeprs.unittest.persistence;

import org.cidrz.webapp.dynasite.dao.RuleDefinitionDAO;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.zeprs.unittest.ZeprsTest;

import javax.servlet.ServletException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Dave Peckham
 * Date: Mar 29, 2004
 * Time: 3:55:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class RuleDefinitionTest extends ZeprsTest {

    private String listName = "RuleDefinitions";
    private String name = "RuleDefinition";
    private String className = "org.cidrz.webapp.dynasite.valueobject.RuleDefinition";
    private String dao = "org.cidrz.webapp.dynasite.dao.RuleDefinitionDAO";
    private Class[] argClazz = new Class[]{Long.class};
    private Object[] args = new Object[]{new Long("1266")};


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
            m = daoClazz.getDeclaredMethod("getAll", argClazz);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            list = (List) m.invoke(list, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail("Failed to get list of " + listName);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            fail("Failed to get list of " + listName);
        }
        assertNotNull(listName + " list was null", list);
    }

    public void getAll() {
        Connection conn = DatabaseUtils.getAdminConnection();
        HashMap rules = null;
        try {
            rules = RuleDefinitionDAO.getAll(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Failed to get map of " + listName);
        } catch (ServletException e) {
            e.printStackTrace();
            fail("Failed to get map of " + listName);
        }

        assertNotNull("All rules" + " map was null", rules);
    }

}
