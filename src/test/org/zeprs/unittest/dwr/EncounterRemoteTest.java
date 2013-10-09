package org.zeprs.unittest.dwr;

import org.cidrz.webapp.dynasite.dao.FormDisplayDAO;
import org.cidrz.webapp.dynasite.dao.PageItemDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.remote.Encounter;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.PageItem;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.zeprs.unittest.ZeprsTest;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.HashMap;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jul 14, 2005
 *         Time: 1:34:06 PM
 */
public class EncounterRemoteTest extends ZeprsTest {

    /**
     * This test creates a remote form key/value pair and saves it
     */
    public void testEncounter() {
        Connection conn = DatabaseUtils.getAdminConnection();
        Long pageItemId = new Long("3502");
        Long formId = new Long("4");
        HashMap pageItems = new HashMap();

        // Load the pageItem into DynaSiteObjects, which is normally only loaded by DynaSiteConfig servlet when the app starts.
        // Access to DynaSiteObjects is used by the next class we call, Encounter.
        PageItem pageItem = null;
        try {
            pageItem = PageItemDAO.getPageItemGraph(conn, pageItemId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        pageItems.put(pageItem.getId(), pageItem);
        DynaSiteObjects.setPageItems(pageItems);
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
        DynaSiteObjects.setForms(forms);
        String inputType = "enum";
        Long encounterId = new Long("360");
        String value = "635";
        String results = null;
        results = Encounter.update(inputType, value, pageItemId, formId, encounterId, "form", (long) 0);
        assertNotNull("Results was null", results);
    }

}
