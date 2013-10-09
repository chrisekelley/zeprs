package org.zeprs.unittest.xml.output;

import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.utils.XmlUtils;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.project.zeprs.valueobject.partograph.PartographMapping;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.zeprs.unittest.ZeprsTest;
import org.dom4j.DocumentException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jul 7, 2005
 *         Time: 10:39:32 AM
 */
public class XmlUtilsTest extends ZeprsTest {

    public void outputFormsTest() {
        Connection conn = DatabaseUtils.getAdminConnection();
        String message = null;
        Boolean dev = false;
        try {
            message = XmlUtils.outputForms(dev, conn, null);
        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQL error: " + e.getMessage());
        } catch (ServletException e) {
            fail("Servlet error: " + e.getMessage());
        } catch (IOException e) {
            fail("IOException error: " + e.getMessage());
        } catch (ObjectNotFoundException e) {
            fail("ObjectNotFoundException error: " + e.getMessage());
        }
        assertNotNull("Method did not output a message.", message);
    }

    /**
     * Provide list of partograph tables to be used to get Mapping fiels
     */
    public void getPartoTableMapTest() {
        HashMap fields = null;
        String path = Constants.PARTO_XML_PATH;
        String ext = ".hbm.xml";
        List partoClasses = new ArrayList();
        partoClasses.add(path + "BloodPressure" + ext);
        partoClasses.add(path + "Cervix" + ext);
        partoClasses.add(path + "Contractions" + ext);
        partoClasses.add(path + "Descent" + ext);
        partoClasses.add(path + "DrugsDispensed" + ext);
        partoClasses.add(path + "FetalHeartRate" + ext);
        partoClasses.add(path + "Liquor" + ext);
        partoClasses.add(path + "Moulding" + ext);
        partoClasses.add(path + "Oxytocin" + ext);
        partoClasses.add(path + "Pulse" + ext);
        partoClasses.add(path + "Temperature" + ext);
        partoClasses.add(path + "UrinalysisAcetone" + ext);
        partoClasses.add(path + "UrinalysisGlucose" + ext);
        partoClasses.add(path + "UrinalysisProtein" + ext);
        partoClasses.add(path + "UrineAmount" + ext);
        partoClasses.add(path + "VaginalExamParto" + ext);
        try {
            fields = XmlUtils.getClassTableNames(partoClasses, path, ext);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        assertNotNull("Method did not output fields.", fields);
    }

    /**
     * Provide list of partograph fields to be used to get Mapping fiels
     */
    public void getPartoFieldsTest() {
        HashMap fields = null;
        try {
            fields = PartographMapping.getPartoFields();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull("Method did not output fields.", fields);
    }


}
