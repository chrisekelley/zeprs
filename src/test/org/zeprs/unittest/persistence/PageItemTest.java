package org.zeprs.unittest.persistence;

import org.zeprs.unittest.ZeprsTest;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.PageItem;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.dao.PageItemDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;

import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Dave Peckham
 * Date: Mar 29, 2004
 * Time: 3:55:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class PageItemTest extends ZeprsTest {

    private String listName = "Rss";
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

    public void testList() {

        Class daoClazz = null;
        try {
            daoClazz = Class.forName(dao);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        List list = null;

        try {
            DataSource ds = DatabaseUtils.getAdminUnitTestDatasource();
            conn = ds.getConnection();
            Method m = null;
            try {
                m = daoClazz.getDeclaredMethod("getAll", new Class[]{Connection.class});
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            args = new Object[]{conn};
            try {
                list = (List) m.invoke(null, args);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                fail("Failed to get list of " + listName);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                fail("Failed to get list of " + listName);
            }
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
        try {
            DataSource ds = DatabaseUtils.getAdminUnitTestDatasource();
            conn = ds.getConnection();
            Method m = null;
            try {
                m = daoClazz.getDeclaredMethod("getAll", new Class[]{Connection.class, String.class});
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            try {
                list = (List) m.invoke(list, new Object[]{conn, new String("name")});
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                fail("Failed to get list of " + listName);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                fail("Failed to get list of " + listName);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertNotNull(listName + " list was null", list);
    }

    public void testListByFormId() {

        Class daoClazz = null;
        try {
            daoClazz = Class.forName(dao);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        List list = null;
        try {
            DataSource ds = DatabaseUtils.getAdminUnitTestDatasource();
            conn = ds.getConnection();
            Method m = null;
            try {
                m = daoClazz.getDeclaredMethod("getAllforForm", new Class[]{Connection.class, Long.class});
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            try {
                list = (List) m.invoke(list, new Object[]{conn, new Long("1")});
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                fail("Failed to get list of " + listName);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                fail("Failed to get list of " + listName);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertNotNull(listName + " list was null", list);
    }

    /**
     * Tests creation of a pageItem
     */
    public void testSaveNewPageItem() {

        Class daoClazz = null;
        try {
            daoClazz = Class.forName(dao);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            DataSource ds = DatabaseUtils.getAdminUnitTestDatasource();
            conn = ds.getConnection();
            PageItem pageItem = PageItemDAO.getPageItemGraph(conn, new Long("3502"));
            pageItem.setFormId(new Long("96"));  // changes it to the Test form.
            Method m = null;
            try {
                // Connection conn, String databaseName, PageItem pageItem, String userName, Long siteId
                m = daoClazz.getDeclaredMethod("save", new Class[]{Connection.class, String.class, PageItem.class, String.class, Long.class});
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            try {
                object = m.invoke(object, new Object[]{conn, "admin", pageItem, "zepadmin", new Long("1")});
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                fail("Failed to get list of " + listName);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                fail("Failed to get list of " + listName);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        assertNotNull(object + " object was null", object);
    }

    /**
     * Tests creation of a shared field
     */
    public void testSaveSharedField() {

        Class daoClazz = null;
        try {
            daoClazz = Class.forName(dao);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            DataSource ds = DatabaseUtils.getAdminUnitTestDatasource();
            conn = ds.getConnection();
            Long formId = new Long("96");
            Long formFieldId = new Long("193");
            Long siteId = new Long("1");
            // find the number of pageitems in the current form to get the  displayOrder
            List pageItems = PageItemDAO.getAllforForm(conn, formId);
            Integer displayOrder =  pageItems.size() + 1;
            Method m = null;
            try {
                // Connection conn, String databaseName, Long formId, Long formFieldId, String userName, Long siteId, Integer displayOrder
                m = daoClazz.getDeclaredMethod("saveSharedfield", new Class[]{Connection.class, String.class, Long.class, Long.class, String.class, Long.class, Integer.class});
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            try {
                object = m.invoke(object, new Object[]{conn, "admin", formId, formFieldId, "zepadmin", siteId, displayOrder});
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                fail("Failed to get list of " + listName);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                fail("Failed to get list of " + listName);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertNotNull(object + " object was null", object);
    }

}
