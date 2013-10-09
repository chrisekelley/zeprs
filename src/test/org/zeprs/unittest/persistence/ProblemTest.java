package org.zeprs.unittest.persistence;

import org.cidrz.webapp.dynasite.dao.CommentDAO;
import org.cidrz.webapp.dynasite.dao.ProblemDAO;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.DateUtils;
import org.cidrz.webapp.dynasite.valueobject.Comment;
import org.cidrz.webapp.dynasite.valueobject.Problem;
import org.zeprs.unittest.ZeprsTest;

import javax.servlet.ServletException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Dave Peckham
 * Date: Mar 29, 2004
 * Time: 3:55:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProblemTest extends ZeprsTest {

    private String listName = "Problems";
    private String name = "Problem";
    private String className = "org.cidrz.webapp.dynasite.valueobject.Problem";
    private String dao = "org.cidrz.webapp.dynasite.dao.ProblemDAO";
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

    public void testSave() {

        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(null);
            Problem problem = new Problem();
            problem.setActive(true);
            problem.setOnsetDate(DateUtils.getNow());
            problem.setPatientId(CURRENT_PATIENT_ID);
            problem.setPregnancyId(CURRENT_PREGNANCY_ID);
            problem.setProblemName("Test");
            problem.setSiteId(CURRENT_SITE_ID);

            Comment comment = new Comment();
            comment.setActionPlan("Test Action Plan");
            comment.setPatientId(CURRENT_PATIENT_ID);
            comment.setPregnancyId(CURRENT_PREGNANCY_ID);
            comment.setSiteId(CURRENT_SITE_ID);
            Long problemId = null;
            try {
                problemId = ProblemDAO.save(conn, problem, "demo", CURRENT_SITE_ID);
            } catch (SQLException e) {
                e.printStackTrace();
                fail("Failed to save problem");
            } catch (ServletException e) {
                e.printStackTrace();
                fail("Failed to save problem");
            }
            assertNotNull("problemId was null", problemId);

            Long commentId = null;
            comment.setProblemId(problemId);
            try {
                commentId = CommentDAO.save(conn, comment, "demo", CURRENT_SITE_ID);
            } catch (SQLException e) {
                e.printStackTrace();
                fail("Failed to save problem");
            } catch (ServletException e) {
                e.printStackTrace();
                fail("Failed to save problem");
            }
            assertNotNull("commentId was null", commentId);
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
    }

}
