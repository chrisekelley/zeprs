/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.utils;

import org.cidrz.webapp.dynasite.valueobject.FormField;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.logic.PersistenceManagerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldMappingUtil {
    private static Map patientFields = null;
    private static Map encounterRecordFields = null;
    private static Map patientStatusFields = null;
    private static Map patientLabFields = null;

    public static Map getPatientFieldMap() {
        if (patientFields == null) {
            patientFields = new HashMap();
            try {
                List fields = PersistenceManagerFactory.getInstance(FormField.class).getAll();
                FormField field;
                for (int i = 0; i < fields.size(); i++) {
                    field = (FormField) fields.get(i);
                    if (field.getPatientProperty() != null && !field.getPatientProperty().equals("")) {
                        patientFields.put("field" + field.getId().toString(), field.getPatientProperty());
                    }
                }
            } catch (PersistenceException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return patientFields;
    }

    public static Map getEncounterRecordFieldMap() {
        if (encounterRecordFields == null) {
            encounterRecordFields = new HashMap();
            try {
                List fields = PersistenceManagerFactory.getInstance(FormField.class).getAll();
                FormField field;
                for (int i = 0; i < fields.size(); i++) {
                    field = (FormField) fields.get(i);
                    if (field.getEncounterRecordproperty() != null && !field.getEncounterRecordproperty().equals("")) {
                        encounterRecordFields.put("field" + field.getId().toString(), field.getEncounterRecordproperty());

                    }
                }
            } catch (PersistenceException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return encounterRecordFields;
    }

    public static Map getPatientStatusFieldMap() {
        if (patientStatusFields == null) {
            patientStatusFields = new HashMap();
            try {
                List fields = PersistenceManagerFactory.getInstance(FormField.class).getAll();
                FormField field;
                for (int i = 0; i < fields.size(); i++) {
                    field = (FormField) fields.get(i);
                    if (field.getPatientStatusproperty() != null && !field.getPatientStatusproperty().equals("")) {
                        patientStatusFields.put("field" + field.getId().toString(), field.getPatientStatusproperty());
                    }
                }
            } catch (PersistenceException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return patientStatusFields;
    }

    public static Map getPatientLabFieldMap() {
        if (patientLabFields == null) {
            patientLabFields = new HashMap();
            try {
                List fields = PersistenceManagerFactory.getInstance(FormField.class).getAll();
                FormField field;
                for (int i = 0; i < fields.size(); i++) {
                    field = (FormField) fields.get(i);
                    if (field.getPatientLabproperty() != null && !field.getPatientLabproperty().equals("")) {
                        patientLabFields.put("field" + field.getId().toString(), field.getPatientLabproperty());
                    }
                }
            } catch (PersistenceException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return patientLabFields;
    }
}
