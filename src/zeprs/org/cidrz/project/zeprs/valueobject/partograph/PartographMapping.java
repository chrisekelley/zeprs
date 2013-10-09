/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.project.zeprs.valueobject.partograph;

import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.utils.XmlUtils;
import org.dom4j.DocumentException;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jul 7, 2005
 *         Time: 3:24:23 PM
 */
public class PartographMapping {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(PartographMapping.class);

        /**
     * Whenever you add a new partograph class, add it to this list.
     * @return list of partograph class names
     */
    public static List partoClassNameList() {
        List partoClasses = new ArrayList();
        partoClasses.add("BloodPressure");
        partoClasses.add("Cervix");
        partoClasses.add("Contractions");
        partoClasses.add("Descent");
        partoClasses.add("DrugsDispensed");
        partoClasses.add("FetalHeartRate");
        partoClasses.add("Liquor");
        partoClasses.add("Moulding");
        partoClasses.add("Oxytocin");
        partoClasses.add("Pulse");
        partoClasses.add("Temperature");
        partoClasses.add("UrinalysisAcetone");
        partoClasses.add("UrinalysisGlucose");
        partoClasses.add("UrinalysisProtein");
        partoClasses.add("UrineAmount");
        partoClasses.add("VaginalExamParto");
        partoClasses.add("Respiration");
        return partoClasses;
    }

    /**
     * Assembles HashMap of partograph tables in format classname,tableName
     * @return HashMap in format classname,tableName
     * @throws DocumentException
     * @throws IOException
     */
    public static HashMap getPartoTables() throws DocumentException, IOException {
        HashMap fields = null;
        String path = Constants.PARTO_XML_PATH;
        String ext = ".hbm.xml";
        //List partoClasses = listPartoClassesPaths(path, ext);
        List partoClassNames = partoClassNameList();
        fields = XmlUtils.getClassTableNames(partoClassNames, path, ext);
        return fields;
    }

    /**
     * Assembles HashMap of partograph fields in format classname, fields
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    public static HashMap getPartoFields() throws DocumentException, IOException {
        HashMap fields = null;
        String path = Constants.PARTO_XML_PATH;
        String ext = ".hbm.xml";
        List partoClassNames = partoClassNameList();
        fields = XmlUtils.getPropertyColumnNames(partoClassNames, path, ext);
        return fields;
    }


}
