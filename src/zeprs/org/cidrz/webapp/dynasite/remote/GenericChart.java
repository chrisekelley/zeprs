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
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.exception.PopulationException;
import org.cidrz.webapp.dynasite.logic.PersistenceManagerFactory;
import org.cidrz.webapp.dynasite.valueobject.FieldEnumeration;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;
import org.cidrz.webapp.dynasite.valueobject.Site;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: May 3, 2005
 * Time: 4:29:25 PM
 */
public class GenericChart {

    private Object setupBean(String value, String column, String valueName, String timeName, String patientId, String pregnancyId, Object bean, String dateVisitValue, String user, Site site, Long formId) throws PopulationException, ObjectNotFoundException, PersistenceException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class beanClass = bean.getClass();
        try {
            bean = PersistenceManagerFactory.getInstance(beanClass).getOne(new Long(column));
        } catch (PersistenceException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            // it's ok
        }

        Form form = (Form) PersistenceManagerFactory.getInstance(Form.class).getOneForm(formId);

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

    public String insertValue(String dataType, String value, String column, String classNameString, String propertyName, String patientId, String pregnancyId, String user, String siteId, String dateVisitValue, Long formId) throws PersistenceException, ObjectNotFoundException, PopulationException, IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        Class className = Class.forName("org.cidrz.project.zeprs.valueobject.gen." + classNameString);
        Identifiable bean = (Identifiable) className.newInstance();
        String timeName = "";
        Site site = (Site) PersistenceManagerFactory.getInstance(Site.class).getOne(new Long(siteId));

        bean = (Identifiable) setupBean(value, column, propertyName, timeName, patientId, pregnancyId, bean, dateVisitValue, user, site, formId);
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

}
