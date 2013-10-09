/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.remote;

import org.apache.commons.beanutils.BeanUtils;
import org.cidrz.project.zeprs.valueobject.gen.PartographStatus;
import org.cidrz.webapp.dynasite.valueobject.*;
import org.cidrz.webapp.dynasite.valueobject.Drugs;
import org.cidrz.webapp.dynasite.exception.PopulationException;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.logic.PersistenceManagerFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.hibernate.NonUniqueResultException;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: May 3, 2005
 * Time: 4:29:25 PM
 */
public class Antenatal {

    private Object setupBean(String value, String column, String valueName, String timeName, String patientId, String pregnancyId, Object bean, String dateVisitValue, String user, Site site) throws PopulationException, ObjectNotFoundException, PersistenceException, IllegalAccessException, InstantiationException, InvocationTargetException {

        Class beanClass = bean.getClass();
        try {
            bean = PersistenceManagerFactory.getInstance(beanClass).getOne(new Long(column));
        } catch (PersistenceException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            // it's ok
        }

        Form form = (Form) PersistenceManagerFactory.getInstance(Form.class).getOneForm(Long.valueOf("80"));

        HashMap fields = new HashMap();
        fields.put("patientId", patientId);
        fields.put("pregnancyId", pregnancyId);
        fields.put("dateVisit", dateVisitValue);
        fields.put("form", form);
        fields.put("flow", form.getFlow());
        fields.put(valueName, value);
        // BeanPopulator.populate(fields, bean);
        BeanUtils.copyProperties(bean, fields);
        return bean;
    }

    public String insertValue(String dataType, String value, String column, String classNameString, String propertyName, String patientId, String pregnancyId, String user, String siteId, String dateVisitValue) throws PersistenceException, ObjectNotFoundException, PopulationException, IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        Class className = Class.forName("org.cidrz.project.zeprs.valueobject.gen." + classNameString);
        Identifiable bean = (Identifiable) className.newInstance();
        String timeName = "";
        Site site = (Site) PersistenceManagerFactory.getInstance(Site.class).getOne(new Long(siteId));

        bean = (Identifiable) setupBean(value, column, propertyName, timeName, patientId, pregnancyId, bean, dateVisitValue, user, site);
        String result = "";
        try {
            PersistenceManagerFactory.getInstance(className).save(bean, user, site);
            if (result.equals("")) {
                if (dataType.equals("enum")) {
                    FieldEnumeration fenum = (FieldEnumeration) PersistenceManagerFactory.getInstance(FieldEnumeration.class).getOne(new Long(value));
                    result = fenum.getEnumeration() + "=" + column;
                } else {
                    result = value + "=" + column;
                }
            }
        } catch (PersistenceException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            result = "Error saving this value.";
        }
        return result;
    }

    public String updatePartographStatus(String value, String column, String classNameString, String propertyName, String patientId, String pregnancyId, String user, String siteId) throws PersistenceException, ObjectNotFoundException, PopulationException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        PartographStatus partographStatus = null;
        String result = "";
        try {
            partographStatus = (PartographStatus) PersistenceManagerFactory.getInstance(PartographStatus.class).getPatientData(Long.decode(patientId), Long.decode(pregnancyId));
        } catch (PersistenceException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            // not good - cannot close Partograph without any data.
        } catch (NonUniqueResultException e) {
            e.printStackTrace();
        }
        if (partographStatus.getField1551() != null) {
            // re-open partograph by making this field null
            partographStatus.setField1551(null);
            result = "Partograph Re-opened";
        } else {
            Date visitDate = Date.valueOf(value);
            partographStatus.setField1551(visitDate);
            result = "Partograph Closed";
        }

        Site site = (Site) PersistenceManagerFactory.getInstance(Site.class).getOne(new Long(siteId));

        try {
            PersistenceManagerFactory.getInstance(PartographStatus.class).save(partographStatus, user, site);
        } catch (PersistenceException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            result = "Error saving this value.";
        }
        return result;
    }

    /**
     * Returns time in addition to value
     *
     * @param value
     * @param column
     * @param classNameString
     * @param propertyName
     * @param patientId
     * @param pregnancyId
     * @param user
     * @param siteId
     * @param dateVisitValue
     * @return
     * @throws org.cidrz.webapp.dynasite.exception.PersistenceException
     *
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException
     *
     * @throws org.cidrz.webapp.dynasite.exception.PopulationException
     *
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public String insertValueTime(String value, String column, String classNameString, String propertyName, String patientId, String pregnancyId, String user, String siteId, String dateVisitValue) throws PersistenceException, ObjectNotFoundException, PopulationException, IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        Class className = Class.forName("org.cidrz.project.zeprs.valueobject.partograph." + classNameString);
        Identifiable bean = (Identifiable) className.newInstance();
        String valueName = "";
        valueName = new StringBuffer().append(propertyName).append(column).toString();
        String timeName = "";
        timeName = "timeObservation" + column;
        Site site = (Site) PersistenceManagerFactory.getInstance(Site.class).getOne(new Long(siteId));

        bean = (Identifiable) setupBean(value, column, valueName, timeName, patientId, pregnancyId, bean, dateVisitValue, user, site);
        String result = "";
        String timeValue = "";
        try {
            timeValue = BeanUtils.getProperty(bean, timeName);
        } catch (InvocationTargetException e) {
            // it's ok
        } catch (NoSuchMethodException e) {
            // it's ok
        }
        //if (className.toString().equals("class org.cidrz.project.zeprs.valueobject.partograph.DrugsDispensed")) {
        if (propertyName.equals("drugsDispensed")) {
            StringBuffer drugList = new StringBuffer();
            for (StringTokenizer stringTokenizer = new StringTokenizer(value, ","); stringTokenizer.hasMoreTokens();) {
                String drugId = stringTokenizer.nextToken();
                Drugs drug = (Drugs) PersistenceManagerFactory.getInstance(Drugs.class).getOne(new Long(drugId));
                drugList.append(drug.getName() + "<br>");
            }
            //result = drugList.toString() + "=" + column;
            result = drugList.toString() + "=" + column + "=" + timeValue;
        }

        try {
            PersistenceManagerFactory.getInstance(className).save(bean, user, site);
            if (result.equals("")) {
                result = value + "=" + column + "=" + timeValue;
            }
        } catch (PersistenceException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            result = "Error saving this value.";
        }
        return result;
    }


}
