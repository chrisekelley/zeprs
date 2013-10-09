/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.valueobject;

import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;

/**
 * Created by IntelliJ IDEA.
 * User: dave
 * Date: Mar 10, 2004
 * Time: 4:07:45 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PatientRecord {
    String getMostRecentString(int field_id) throws ObjectNotFoundException, PersistenceException;

    int getMostRecentInt(int field_id) throws ObjectNotFoundException, PersistenceException;

    float getMostRecentFloat(int field_id) throws ObjectNotFoundException, PersistenceException;

    Patient getPatient();

}
