package org.zeprs.unittest.struts;

import org.apache.struts.action.DynaActionForm;
import org.apache.struts.action.DynaActionFormClass;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.cidrz.test.TestPrincipal;
import org.cidrz.webapp.dynasite.dao.FormDisplayDAO;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.dao.SessionPatientDAO;
import org.cidrz.webapp.dynasite.utils.DateUtils;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.*;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import servletunit.struts.MockStrutsTestCase;

import javax.servlet.ServletException;
import java.io.File;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jun 22, 2005
 *         Time: 11:01:21 AM
 */
public class CreatePrevPregnanciesTest extends MockStrutsTestCase {


    public DynaActionFormClass dynaClass = null;
    /**
     * The basic <code>DynaActionForm</code> to use for testing.
     */
    protected DynaActionForm dynaForm = null;

    public CreatePrevPregnanciesTest(String testName) {
        super(testName);
    }

    public void setUp() throws Exception {
        super.setUp();
        // set the context directory to /zeprs
        // to find the /WEB-INF/web.xml
        this.setContextDirectory(new File("webapps/zeprs"));
        this.setConfigFile("/WEB-INF/foo.xml");

    }

    public void prevPregnanciesTest() {

        Long formId = new Long("2");
        setRequestPathInfo("/form" + formId.intValue() + "/save");
        HashMap forms = new HashMap();
        Form form = null;
        Connection conn = DatabaseUtils.getAdminConnection();
        try {
            form = FormDisplayDAO.getFormGraph(conn, formId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        forms.put(form.getId(), form);

        // setup form for the forward
        Form form70 = null;
         try {
            form70 = FormDisplayDAO.getFormGraph(conn, new Long("70"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
         forms.put(new Long("70"), form70);
        DynaSiteObjects.setForms(forms);

        addRequestParameter("password", "demo11");
        request.setRemoteAddr("127.0.0.1");
        TestPrincipal principal = new TestPrincipal();
        principal.setName("demo");
        request.setUserPrincipal(principal);

        actionPerform();

        ModuleConfig modCfg = (ModuleConfig) this.context.getAttribute("org.apache.struts.action.MODULE");
        FormBeanConfig formCfg = modCfg.findFormBeanConfig("form" + formId.intValue());
        dynaClass = DynaActionFormClass.createDynaActionFormClass(formCfg);
        try {
            dynaForm = (DynaActionForm) dynaClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        // create random number
        Random generator = new Random();

        int first = generator.nextInt(100000);
        int sur = generator.nextInt(10000);
        int addNum = generator.nextInt(10000);
        int age = generator.nextInt(60) + 13;
        int six = generator.nextInt(6) + 1;
        int floatRan = generator.nextInt(100);
        int textRan = generator.nextInt(100000);

        /*PrevPregnancies prevPregnancies = new PrevPregnancies();
        Map prevMap = new HashMap();
        try {
            prevMap = BeanUtils.describe(prevPregnancies);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }*/

        Set pageItems = form.getPageItems();
        String value = null;
        for (Iterator iterator = pageItems.iterator(); iterator.hasNext();) {
            PageItem pageItem = (PageItem) iterator.next();
            FormField formField = pageItem.getForm_field();
            if (formField.isEnabled()) {
                if (!formField.getType().equals("Display")) {
                    value = "";
                    int min = 0;
                    int max = 0;
                    if (formField.getMinValue() != null) {
                        if (formField.getMinValue().intValue() > 0) {
                            min = formField.getMinValue().intValue();
                        }
                    }
                    if (formField.getMaxValue() != null) {
                        if (formField.getMaxValue().intValue() > 0) {
                            max = formField.getMaxValue().intValue();
                        }
                    }
                    if (formField.getType().equals("Boolean")) {
                        value = "0";
                    } else if (formField.getType().equals("Long")) {
                        value = String.valueOf(six);
                    } else if (formField.getType().equals("enum")) {
                        Set fieldEnums = formField.getEnumerations();
                        Object[] enumArray = fieldEnums.toArray();
                        FieldEnumeration fieldEnum = (FieldEnumeration) enumArray[0];
                        value = fieldEnum.getId().toString();
                    } else if (formField.getType().equals("Enum")) {
                        Set fieldEnums = formField.getEnumerations();
                        Object[] enumArray = fieldEnums.toArray();
                        FieldEnumeration fieldEnum = (FieldEnumeration) enumArray[0];
                        value = fieldEnum.getId().toString();
                    } else if (formField.getType().equals("Float")) {
                        if (formField.getMinValue() != null) {
                            if (min > 0) {
                                int intVal = generator.nextInt(max - min) + min;
                                value = String.valueOf(intVal);
                            } else if (max > 0) {
                                int intVal = generator.nextInt(max - min) + min;
                                value = String.valueOf(intVal);
                            } else {
                                value = String.valueOf(floatRan);
                            }
                        } else {
                            value = String.valueOf(floatRan);
                        }
                    } else if (formField.getType().equals("Date")) {
                        value = DateUtils.getNow().toString();
                    } else if (formField.getType().equals("Text")) {
                        value = "test value" + textRan;
                    } else if (formField.getType().equals("Time")) {
                        value = DateUtils.getTime();
                    } else if (formField.getType().equals("sex")) {
                        value = "1";
                    } else if (formField.getType().equals("Yes/No")) {
                        value = "0";
                    } else if (formField.getType().equals("Year")) {
                        if (formField.getMinValue() != null) {
                            if (min > 0) {
                                max = new Integer(DateUtils.getYear()).intValue();
                                int intVal = generator.nextInt(max - min) + min;
                                value = String.valueOf(intVal);
                            } else {
                                value = String.valueOf(floatRan);
                            }
                        }

                    } else if (formField.getType().startsWith("Integer")) {
                        if (formField.getMinValue() != null) {
                            if (min > 0) {
                                int intVal = generator.nextInt(max - min) + min;
                                value = String.valueOf(intVal);
                            } else if (max > 0) {
                                int intVal = generator.nextInt(max - min) + min;
                                value = String.valueOf(intVal);
                            } else {
                                value = String.valueOf(floatRan);
                            }
                        } else {
                            value = String.valueOf(floatRan);
                        }
                    } else {
                        value = "test value" + textRan;
                    }
                    dynaForm.set("field" + formField.getId(), value);
                }
            }
        }

        dynaForm.set("field1", DateUtils.getNow().toString());
        Patient patient = null;
        // get patientId of most recently-added patient
        try {
            patient = PatientDAO.getNewest(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }


        String patientId = patient.getId().toString();

        addRequestParameter("patientId", patientId);

        SessionPatient sessionPatient = null;
        try {
            sessionPatient = SessionPatientDAO.getSessionPatient(conn, patient.getId(), new Long("0"));
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }

        String pregnancyId = null;
        pregnancyId = sessionPatient.getCurrentPregnancyId().toString();
        addRequestParameter("pregnancyId", pregnancyId);
        addRequestParameter("forward", "");
        assertNotNull("patientId is null", patientId);
        assertNotNull("pregnancyId is null", pregnancyId);
        assertNotNull("dynaForm is null", dynaForm);

        // assertEquals("field1675 is not = 1", field1675, "1");
        // verifyForward("success");

        setActionForm(dynaForm);
        actionPerform();
        verifyNoActionErrors();
        // verifyNoActionMessages();
        verifyForward("/form" + new Long("70") + "/new");
    }
}


