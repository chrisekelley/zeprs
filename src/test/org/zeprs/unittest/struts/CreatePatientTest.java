package org.zeprs.unittest.struts;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.action.DynaActionFormClass;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.cidrz.project.zeprs.valueobject.gen.PatientRegistration;
import org.cidrz.test.TestPrincipal;
import org.cidrz.webapp.dynasite.dao.FormDisplayDAO;
import org.cidrz.webapp.dynasite.utils.DateUtils;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import servletunit.ServletConfigSimulator;
import servletunit.struts.MockStrutsTestCase;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.*;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jun 22, 2005
 *         Time: 11:01:21 AM
 */
public class CreatePatientTest extends MockStrutsTestCase {

    public DynaActionFormClass dynaClass = null;
    /**
     * The basic <code>DynaActionForm</code> to use for testing.
     */
    protected DynaActionForm dynaForm = null;

    /**
     * The <code>FormBeanConfig</code> structure for the form bean we will
     * be creating.
     */
    protected FormBeanConfig beanConfig = null;

    // public DataSource zeprsDatasource = null;

    public CreatePatientTest(String testName) {
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

    public void testDynaFormAction() {
        // setConfigFile("zeprs", "/WEB-INF/foo.xml");
        // setRequestPathInfo("/form4/new");
        setRequestPathInfo("/form1/save");
        Long formId = new Long("1");
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
        addRequestParameter("password", "demo11");
        request.setRemoteAddr("127.0.0.1");
        TestPrincipal principal = new TestPrincipal();
        principal.setName("demo");
        request.setUserPrincipal(principal);
        // String ipAddress = request.getRemoteAddr();
        actionPerform();
        ModuleConfig modCfg = (ModuleConfig) this.context.getAttribute("org.apache.struts.action.MODULE");
        FormBeanConfig form4Ccfg = modCfg.findFormBeanConfig("form1");

        dynaClass = DynaActionFormClass.createDynaActionFormClass(form4Ccfg);
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


        PatientRegistration registration = new PatientRegistration();
        registration.setField6("Patient" + sur);
        registration.setField7("Test" + first);
        registration.setField1135(Integer.valueOf(String.valueOf(age)));
        registration.setField19(addNum + " Park Place");

        Map regMap = new HashMap();

        try {
            regMap = BeanUtils.describe(registration);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Set dynaSet = dynaForm.getMap().entrySet();
        for (Iterator iterator = dynaSet.iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            dynaForm.set(key, regMap.get(key));
        }

        dynaForm.set("field1", DateUtils.getNow().toString());

        assertNotNull("dynaForm is null", dynaForm);
        // assertEquals("field1675 is not = 1", field1675, "1");
        // verifyForward("success");

        setActionForm(dynaForm);
        actionPerform();
        verifyForward("pregnancyDating");
    }
}


