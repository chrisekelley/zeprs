package org.zeprs.unittest.persistence;

import org.cidrz.webapp.dynasite.dao.ClientSettingsDAO;
import org.cidrz.webapp.dynasite.dao.SiteDAO;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.ClientSettings;
import org.cidrz.webapp.dynasite.valueobject.Site;
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
public class ClientSettingsTest extends ZeprsTest {
    public void test() {
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(null);
            String testIp = this.getClass().getName() + System.currentTimeMillis(); //not an IP, but who cares?
            ClientSettings settings = new ClientSettings();
            settings.setIpAddress(testIp);
            Site site = null;
            Long siteId = new Long(1);
            Long clientSettingsId = null;
            try {
                site = (Site) SiteDAO.getOne(conn, siteId, "id");
            } catch (Exception e) {
                fail("no site found with id=1... " + e.getMessage());
            }
            settings.setSite(site);
            try {
                clientSettingsId = ClientSettingsDAO.create(settings, getPrincipal(), site);
                assertNotNull("setting saved without id", clientSettingsId);
            } catch (Exception e) {
                e.printStackTrace();
                fail("failed to save settings... " + e.getMessage());
            }

            try {
                settings = (ClientSettings) ClientSettingsDAO.getOne(conn, testIp);
            } catch (Exception e) {
                fail("failed to retreive settings... " + e.getMessage());
            }
            assertNotNull("saved settings do not have a site", settings.getSiteId());
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
