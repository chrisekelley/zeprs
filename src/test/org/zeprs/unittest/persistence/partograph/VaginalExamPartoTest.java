package org.zeprs.unittest.persistence.partograph;

import org.zeprs.unittest.ZeprsTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: Chris Kelley
 * Date: Jul 4, 2005
 * Time: 3:55:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class VaginalExamPartoTest extends ZeprsTest {

    private String name = "VaginalExamParto";
    private String className = "org.cidrz.project.zeprs.valueobject.partograph.VaginalExamParto";
    private String dao = "org.cidrz.webapp.dynasite.dao.partograph.VaginalExamPartoDAO";
    private Class[] argClazz = new Class[]{Long.class, Long.class};
    private Object[] args = new Object[]{new Long("1"), new Long("1")};

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

}
