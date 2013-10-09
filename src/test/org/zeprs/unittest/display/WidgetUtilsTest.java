package org.zeprs.unittest.display;

import org.cidrz.webapp.dynasite.dao.PageItemDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.WidgetUtils;
import org.cidrz.webapp.dynasite.valueobject.PageItem;
import org.zeprs.unittest.ZeprsTest;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jul 12, 2005
 *         Time: 5:37:32 PM
 */
public class WidgetUtilsTest extends ZeprsTest {

    // test select (dropdown)
    public void testSelect() {
        Connection conn = DatabaseUtils.getAdminConnection();
        String widget = null;
        try {
            PageItem pageItem = PageItemDAO.getPageItemGraph(conn, new Long("3502"));
            //widget = WidgetUtils.getOne(pageItem, value, className);
            // public static String getOne(PageItem pageItem, String value, Long fieldId, String className, Long formId, String userName, String jSessionId, Long patientId, Long pregnancyId, Long siteId) {

        } catch (SQLException e) {
            fail("failed to get widget");
            e.printStackTrace();
        } catch (ServletException e) {
            fail("failed to get widget");
        } catch (ObjectNotFoundException e) {
            fail("failed to get widget");
        }
        assertNotNull("widget was null", widget);
    }

    // test select (dropdown)
    public void testEmptyDate() {
        Connection conn = DatabaseUtils.getAdminConnection();
        String widget = null;
        try {
            PageItem pageItem = PageItemDAO.getPageItemGraph(conn, new Long("3501"));
            widget = WidgetUtils.getOne(conn, pageItem, "2005-07-11", new Long("4"), new Long("367"), "Form");
        } catch (SQLException e) {
            fail("failed to get widget");
            e.printStackTrace();
        } catch (ServletException e) {
            fail("failed to get widget");
        } catch (ObjectNotFoundException e) {
            fail("failed to get widget");
        }
        assertNotNull("widget was null", widget);
    }
}
