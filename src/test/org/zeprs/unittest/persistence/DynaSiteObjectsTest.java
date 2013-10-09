package org.zeprs.unittest.persistence;

import org.cidrz.webapp.dynasite.dao.FormDisplayDAO;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.zeprs.unittest.ZeprsTest;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jul 14, 2005
 *         Time: 1:34:06 PM
 */
public class DynaSiteObjectsTest extends ZeprsTest {

    /**
     * This test creates a remote form key/value pair and saves it
     */
    public void testFormSection() {

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
        Map sections = DynaSiteObjects.getFormSections();
        // Map dependencies = DynaSiteObjects.getFormDependencies();
        HashMap formDeps = (HashMap) DynaSiteObjects.getFormDependencies().get(formId);
        HashMap formMap = (HashMap) DynaSiteObjects.getFormSections().get(new Long("4"));
        Object dep = formDeps.get(new Long("1678"));
        Object section = formMap.get(new Long("3528"));
        // add malaraia test
        Object section2 = formMap.get(new Long("3546"));
        Object section3 = formMap.get(new Long("3558"));
        assertNotNull("dep was null", dep);
        assertNotNull("Sections was null", sections);
        assertNotNull("Section was null", section);
        assertNotNull("section2 was null", section2);
        assertNotNull("section3 was null", section3);
    }

     public void testDependencies() {

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
        Map deps = DynaSiteObjects.getFormDependencies();
        Object dep = deps.get(new Long("1678"));
        assertNotNull("dep was null", dep);
        assertNotNull("Sections was null", deps);
    }

}
