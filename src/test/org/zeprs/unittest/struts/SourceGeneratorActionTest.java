package org.zeprs.unittest.struts;

import servletunit.struts.MockStrutsTestCase;

import java.io.File;
import java.security.Principal;

import junit.framework.Test;
import org.cidrz.test.TestPrincipal;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jun 22, 2005
 *         Time: 11:01:21 AM
 */
public class SourceGeneratorActionTest extends MockStrutsTestCase {

    // private ProblemForm bookEditForm;

    public SourceGeneratorActionTest(String testName) {
        super(testName);
    }

    public void setUp() throws Exception {
        super.setUp();
        // to find the /WEB-INF/web.xml
        setContextDirectory(new File("webapps/zeprs"));
    }

    public void testSourceGenerateorAction() {

        setRequestPathInfo("/admin/dynagen");
        addRequestParameter("gen", "4");
        request.setRemoteAddr("127.0.0.1");
        TestPrincipal principal = new TestPrincipal();
        principal.setName("demo");
        request.setUserPrincipal(principal);
        String ipAddress = request.getRemoteAddr();
        actionPerform();
        //assertNotNull(((BookListForm)getActionForm()).getBooks());
        verifyForward("success");

    }
}
