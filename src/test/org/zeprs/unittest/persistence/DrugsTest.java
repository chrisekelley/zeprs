package org.zeprs.unittest.persistence;

import org.cidrz.webapp.dynasite.dao.DrugsDAO;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.zeprs.unittest.ZeprsTest;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Dave Peckham
 * Date: Mar 29, 2004
 * Time: 3:55:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class DrugsTest extends ZeprsTest {

    public void testDrugs() {
        Connection conn = DatabaseUtils.getAdminConnection();
        List drugs = null;
        try {
            drugs = DrugsDAO.getAll(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            fail("failed to get drugs");
        } catch (ServletException e) {
            e.printStackTrace();
            fail("failed to get drugs");
        }
        assertNotNull("drugs list was null", drugs);
    }
}
