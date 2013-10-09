package org.zeprs.unittest.persistence;

import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.zeprs.unittest.ZeprsTest;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;

/**
 * Fetch list of encounters
 */
public class EncounterListTest extends ZeprsTest {

    public void testEncounterList() {

        Long patientId = new Long("1");
        Long pregnancyId = new Long("1");
        List chartItems = null;


/*        BeanProcessor beanprocessor = new AuditInfoBeanProcessor();
RowProcessor convert = new ZEPRSRowProcessor(beanprocessor);*/
         Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(null);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        try {
            chartItems = EncountersDAO.getAllRoutineAnte(conn, patientId, pregnancyId, "SQL_RETRIEVE80");
        } catch (ServletException e) {
            e.printStackTrace();
            fail("failed to get chartItems");
        } catch (SQLException e) {
            e.printStackTrace();
            fail("failed to get chartItems");
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotNull("chartItems were null", chartItems);
    }

}
