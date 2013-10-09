package org.zeprs.unittest.struts;

import org.apache.struts.action.DynaActionForm;
import org.apache.struts.action.DynaActionFormClass;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.validator.DynaValidatorForm;
import org.cidrz.test.TestPrincipal;
import org.cidrz.webapp.dynasite.dao.FormDisplayDAO;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.utils.DateUtils;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import servletunit.ServletConfigSimulator;
import servletunit.struts.MockStrutsTestCase;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.File;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jun 22, 2005
 *         Time: 11:01:21 AM
 */
public class SafeMotherhoodActionTest extends MockStrutsTestCase {

    // private ProblemForm bookEditForm;
    private DynaValidatorForm form4;

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

    public SafeMotherhoodActionTest(String testName) {
        super(testName);
    }

    public void setUp() throws Exception {
        super.setUp();
        // set the context directory to /zeprs
        // to find the /WEB-INF/web.xml
        this.setContextDirectory(new File("webapps/zeprs"));
        this.setConfigFile("/WEB-INF/strutstest.xml");
        ServletConfigSimulator cfgSim = new ServletConfigSimulator();
        Enumeration initParams = cfgSim.getInitParameterNames();
        ServletContext cntx = cfgSim.getServletContext();
        // set the book into the action form
        //bookEditForm = new BookEditForm();
        //bookEditForm.setBook(new Book(1, "laliluna", "StrutsTestCases Tutorial", true));

        // set the form data in the action form
        // ActionForm form

        form4 = new DynaValidatorForm();
        form4.getDynaClass();

        // Construct a FormBeanConfig to be used
        beanConfig = new FormBeanConfig();
        beanConfig.setName("dynaForm");
        beanConfig.setType("org.apache.struts.action.DynaActionForm");

    }

    public void testDynaFormAction() {
        // setConfigFile("zeprs", "/WEB-INF/foo.xml");
        // setRequestPathInfo("/form4/new");
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
        // setActionForm(form4);
        addRequestParameter("patientId", "36");
        addRequestParameter("pregnancyId", "30");
        addRequestParameter("password","demo11");
        request.setRemoteAddr("127.0.0.1");
        TestPrincipal principal = new TestPrincipal();
        principal.setName("demo");
        request.setUserPrincipal(principal);
        // String ipAddress = request.getRemoteAddr();
        actionPerform();
        ModuleConfig modCfg = (ModuleConfig) this.context.getAttribute("org.apache.struts.action.MODULE");
        FormBeanConfig form4Ccfg = modCfg.findFormBeanConfig("form4");
        /* ActionFormBeans actionformbeans = (ActionFormBeans) context.getAttribute("org.apache.struts.action.FORM_BEANS");
         ActionFormBean form4 = (ActionFormBean) actionformbeans.findFormBean("form4");*/
        // ActionFormBean form4 = (ActionFormBean) formbeans.get("form4");
        //assertNotNull(((DynaActionForm)getActionForm()).getDynaClass());
        // DynaActionForm testForm = new DynaActionForm();
        /*ActionMappings mappings = (ActionMappings) this.context.getAttribute("org.apache.struts.action.MAPPINGS");
        ActionMapping mapping = mappings.findMapping("/form4/new");*/
        // testForm.initialize(mapping);

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
        String field1675 = (String) dynaForm.get("field1675");
        assertNotNull("form4 is null", dynaForm);
        assertEquals("field1675 is not = 1", field1675, "1");
        // verifyForward("success");

        setActionForm(dynaForm);
        actionPerform();
        //ArrayList bookDB = (ArrayList) getSession().getAttribute("bookDB");
        //assertEquals("StrutsTestCases Tutorial", ((Book) bookDB.get(bookDB.size() - 1)).getTitle());
        verifyForward("problem");
    }
}


