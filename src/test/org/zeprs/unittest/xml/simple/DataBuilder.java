package org.zeprs.unittest.xml.simple;

import org.cidrz.webapp.dynasite.valueobject.Patient;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: Dave Peckham
 * Date: Mar 27, 2004
 * Time: 12:24:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataBuilder {
    public static final String DATA_PATH = "/tmp";

    public static void main(String[] args) {
        Patient p = new Patient();
        XMLEncoder e = null;
        try {
            e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(DataBuilder.DATA_PATH + "/Patient.xml")));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        e.writeObject(p);
        e.close();

    }
}
