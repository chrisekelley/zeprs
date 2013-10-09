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
import org.cidrz.webapp.dynasite.utils.PopulatePatientRecord;
import org.cidrz.webapp.dynasite.valueobject.*;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import servletunit.ServletConfigSimulator;
import servletunit.struts.MockStrutsTestCase;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.io.File;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.*;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jun 22, 2005
 *         Time: 11:01:21 AM
 */
public class CreatePatientRecordTest extends MockStrutsTestCase {

    // private ProblemForm bookEditForm;

    public static DynaActionFormClass dynaClass = null;
    /**
     * The basic <code>DynaActionForm</code> to use for testing.
     */
    public static DynaActionForm dynaForm = null;

    /**
     * The <code>FormBeanConfig</code> structure for the form bean we will
     * be creating.
     */
    protected FormBeanConfig beanConfig = null;

    public static SessionPatient sessionPatient = null;

    // public DataSource zeprsDatasource = null;

    public CreatePatientRecordTest(String testName) {
        super(testName);
    }

    public void setUp() throws Exception {
        super.setUp();
        // set the context directory to /zeprs
        // to find the /WEB-INF/web.xml
        this.setContextDirectory(new File("webapps/zeprs"));
        this.setConfigFile("/WEB-INF/foo.xml");
        ServletConfigSimulator cfgSim = new ServletConfigSimulator();
        Enumeration initParams = cfgSim.getInitParameterNames();
        ServletContext cntx = cfgSim.getServletContext();
        DataSource dataSource = DatabaseUtils.createConnectionPool();
        cntx.setAttribute("java:comp/env/jdbc/zeprsDB", dataSource);
        // Construct a FormBeanConfig to be used
        beanConfig = new FormBeanConfig();
        beanConfig.setName("dynaForm");
        beanConfig.setType("org.apache.struts.action.DynaActionForm");
        // zeprsDatasource = DatabaseUtils.createConnectionPool();
    }

    public void createPatientTest() {

        setRequestPathInfo("/form1/save");
        Long formId = new Long("1");
        HashMap forms = new HashMap();
        Form form = null;

         Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(null);
        } catch (ServletException e) {
            e.printStackTrace();
        }


        try {
            form = FormDisplayDAO.getFormGraph(conn, formId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        forms.put(form.getId(), form);
        DynaSiteObjects.setForms(forms);
        addRequestParameter("password", "demo11");
        request.setRemoteAddr("127.0.0.1");
        TestPrincipal principal = new TestPrincipal();
        principal.setName("demo");
        request.setUserPrincipal(principal);
        actionPerform();
        ModuleConfig modCfg = (ModuleConfig) this.context.getAttribute("org.apache.struts.action.MODULE");
        String firstName = "Test";
        dynaForm = PopulatePatientRecord.createPatient(modCfg, firstName, Long.valueOf(1));
        assertNotNull("dynaForm is null", dynaForm);
        setActionForm(dynaForm);
        actionPerform();
        verifyForward("pregnancyDating");
        // setup vars to pass to other tests
        Patient patient = null;
        // get patientId of most recently-added patient
        try {
            patient = PatientDAO.getNewest(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Long patientId = patient.getId();


        try {
            sessionPatient = SessionPatientDAO.getSessionPatient(conn, patientId, new Long("0"));
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }

        Long pregnancyId = sessionPatient.getCurrentPregnancyId();
        // other tests
        createPregnancyDatingTest(patientId, pregnancyId);
        prevPregnanciesTest(patientId, pregnancyId);
        createSafeMotherhoodTest(patientId, pregnancyId);
        createRoutineAntenatalTest(patientId, pregnancyId);
    }

    public void prevPregnanciesTest(Long patientId, Long pregnancyId) {
        Connection conn = DatabaseUtils.getAdminConnection();
        Long formId = new Long("2");
        setRequestPathInfo("/form" + formId.intValue() + "/save");
        HashMap forms = new HashMap();
        Form form = null;
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
        fillData(modCfg, formId, form);

        addRequestParameter("patientId", patientId.toString());
        addRequestParameter("pregnancyId", pregnancyId.toString());
        addRequestParameter("forward", "");
        assertNotNull("patientId is null", patientId);
        assertNotNull("pregnancyId is null", pregnancyId);
        assertNotNull("dynaForm is null", dynaForm);
        setActionForm(dynaForm);
        try {
            actionPerform();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // verifyNoActionErrors();
        // verifyNoActionMessages();
        // verifyForward("/form" + new Long("70") + "/new");
    }

    public void createSafeMotherhoodTest(Long patientId, Long pregnancyId) {
        setRequestPathInfo("/form4/save");
        Long formId = new Long("4");
        HashMap forms = new HashMap();
        Form form = null;
        try {
            Connection conn = DatabaseUtils.getAdminConnection();
            form = FormDisplayDAO.getFormGraph(conn, formId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        forms.put(form.getId(), form);
        DynaSiteObjects.setForms(forms);
        addRequestParameter("patientId", patientId.toString());
        addRequestParameter("pregnancyId", pregnancyId.toString());
        addRequestParameter("password","demo11");
        request.setRemoteAddr("127.0.0.1");
        TestPrincipal principal = new TestPrincipal();
        principal.setName("demo");
        request.setUserPrincipal(principal);
        actionPerform();
        ModuleConfig modCfg = (ModuleConfig) this.context.getAttribute("org.apache.struts.action.MODULE");
        createSafeMotherhood(modCfg);
        String field1675 = (String) dynaForm.get("field1675");
        assertNotNull("form4 is null", dynaForm);
        assertEquals("field1675 is not = 1", field1675, "1");
        setActionForm(dynaForm);
        actionPerform();
        // verifyForward("problem");
    }

    public void createPregnancyDatingTest(Long patientId, Long pregnancyId) {
        Long formId = new Long("82");
        setRequestPathInfo("/form" + formId + "/save");
        HashMap forms = new HashMap();
        Form form = null;
        try {
            Connection conn = DatabaseUtils.getAdminConnection();
            form = FormDisplayDAO.getFormGraph(conn, formId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        forms.put(form.getId(), form);
        DynaSiteObjects.setForms(forms);
        addRequestParameter("patientId", patientId.toString());
        addRequestParameter("pregnancyId", pregnancyId.toString());
        addRequestParameter("password","demo11");
        request.setRemoteAddr("127.0.0.1");
        TestPrincipal principal = new TestPrincipal();
        principal.setName("demo");
        request.setUserPrincipal(principal);
        actionPerform();
        ModuleConfig modCfg = (ModuleConfig) this.context.getAttribute("org.apache.struts.action.MODULE");
        createDynaForm(modCfg, formId.toString());
        // lmp_reliability_126
        dynaForm.set("field126", "67");
        //lmpdate
        dynaForm.set("field127", "2005-01-15");
        //edd
        dynaForm.set("field128", "2005-11-23");
        // ega
        dynaForm.set("field129", "165");
        // dating method
        dynaForm.set("field1615", "2805");

        dynaForm.set("field1", DateUtils.getNow().toString());
        assertNotNull("form" + formId + " is null", dynaForm);
        setActionForm(dynaForm);
        actionPerform();
        // verifyForward("problem");
    }

    public void createRoutineAntenatalTest(Long patientId, Long pregnancyId) {
         Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(null);
        } catch (ServletException e) {
            e.printStackTrace();
        }

        Long formId = new Long("80");
        setRequestPathInfo("/form" + formId + "/save");
        HashMap forms = new HashMap();
        Form form = null;
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

        // this is for the actionForward
        Form initVisitPhysicalExam = null;
        try {
            initVisitPhysicalExam = FormDisplayDAO.getFormGraph(conn, new Long("77"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        forms.put(new Long("77"), initVisitPhysicalExam);
        DynaSiteObjects.setForms(forms);
        addRequestParameter("patientId", patientId.toString());
        addRequestParameter("pregnancyId", pregnancyId.toString());
        addRequestParameter("password","demo11");
        request.setRemoteAddr("127.0.0.1");
        TestPrincipal principal = new TestPrincipal();
        principal.setName("demo");
        request.setUserPrincipal(principal);
        actionPerform();
        ModuleConfig modCfg = (ModuleConfig) this.context.getAttribute("org.apache.struts.action.MODULE");
        createDynaForm(modCfg, formId.toString());
        // get current ega form sessionpatient
        try {
            sessionPatient = SessionPatientDAO.updateSessionPatient(conn, patientId, pregnancyId, request.getSession(), sessionPatient);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (SessionUtil.AttributeNotFoundException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        Integer currentEga = sessionPatient.getCurrentEgaDaysDB();
        dynaForm.set("field129", currentEga.toString());
        //fundal_height_232
        dynaForm.set("field232", "20");
        //presentation_314
        dynaForm.set("field314", "169");
        // engaged_234
        dynaForm.set("field234", "122");
        // foetal_heart_rate_230
        dynaForm.set("field230", "118");
        // foetal_heart_rhythm_229
        dynaForm.set("field229", "118");
        // bp_systolic_224
        dynaForm.set("field224", "2403");
        // bp_diastolic_225
        dynaForm.set("field225", "1844");
        // oedema_231
        dynaForm.set("field231", "1124");
        // weight_228
        dynaForm.set("field228", "55");
        // urinalysis_ace_244
        dynaForm.set("field244", "1131");
        // urinalysis_alb_242
        dynaForm.set("field242", "1671");
        // urinalysis_glu_243
        dynaForm.set("field243", "1444");

        dynaForm.set("field1", DateUtils.getNow().toString());
        assertNotNull("form" + formId + " is null", dynaForm);
        setActionForm(dynaForm);
        actionPerform();
        // verifyForward("problem");
    }

    private static void createDynaForm(ModuleConfig modCfg, String formId) {
        FormBeanConfig formCfg = modCfg.findFormBeanConfig("form"+ formId);
        dynaClass = DynaActionFormClass.createDynaActionFormClass(formCfg);
        try {
            dynaForm = (DynaActionForm) dynaClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        dynaForm.set("field1", DateUtils.getNow().toString());
    }

    private static void createSafeMotherhood(ModuleConfig modCfg) {
        FormBeanConfig form4Ccfg = modCfg.findFormBeanConfig("form4");
        dynaClass = DynaActionFormClass.createDynaActionFormClass(form4Ccfg);
        try {
            dynaForm = (DynaActionForm) dynaClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        //  dynaForm.initialize(mapping);
        dynaForm.set("field1675", "1");
        dynaForm.set("field1", DateUtils.getNow().toString());
    }

    private static void fillData(ModuleConfig modCfg, Long formId, Form form) {
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
    }

}


