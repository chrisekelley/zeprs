/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.project.zeprs.valueobject.report;

import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.webapp.dynasite.valueobject.Patient;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Nov 21, 2004
 * Time: 2:00:37 PM
 */


public interface BaseReport {

    public EncounterData getEncounter();

    public void setEncounter(EncounterData encounter);


    public Patient getPatient();

    public void setPatient(Patient patient);
}
