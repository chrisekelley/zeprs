package org.zeprs.unittest.persistence;

import org.cidrz.webapp.dynasite.valueobject.Site;
import org.cidrz.webapp.dynasite.valueobject.ReportSpec;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.logic.PersistenceManagerFactory;
import org.zeprs.unittest.ZeprsTest;

/**
 * Created by IntelliJ IDEA.
 * User: Dave Peckham
 * Date: Mar 29, 2004
 * Time: 3:55:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleSaveTest extends ZeprsTest {
    /**
     * This test tests the saving of a very simple object to
     * ensure at least the most simple cases work. No point in
     * debugging anything else until this works.
     */
    public void testSaveReportSpec() {
        ReportSpec report = new ReportSpec();
        report.setLabel("foo");
        report.setSqlQuery("select now()");
        Site site = new Site();
        site.setId(new Long("1"));
        try {
            report = (ReportSpec) PersistenceManagerFactory.getInstance(ReportSpec.class).save(report, getPrincipal(), site);
        } catch (PersistenceException e) {
            fail("failed to save report");
        }
    }
}
