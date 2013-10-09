package org.zeprs.unittest.persistence;

import org.cidrz.webapp.dynasite.dao.StaffDAO;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.Staff;
import org.zeprs.unittest.ZeprsTest;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Dave Peckham
 * Date: Mar 29, 2004
 * Time: 3:00:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class StaffTest extends ZeprsTest {
    public void test() {
        String username = "demo";
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            Staff staff = null;
            try {
                staff = (Staff) StaffDAO.getOne(conn, username);
            } catch (Exception e) {
                fail("no staff found with id=demo... " + e.getMessage());
            }
            assertNotNull("saved settings do not have a site", staff);

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
