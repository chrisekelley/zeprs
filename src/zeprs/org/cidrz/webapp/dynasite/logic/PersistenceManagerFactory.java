/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.logic;

import org.cidrz.webapp.dynasite.valueobject.ClientSettings;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.logic.ClientSettingsManager;
import org.cidrz.webapp.dynasite.logic.BasePersistenceManager;
import org.cidrz.webapp.dynasite.logic.PersistenceManager;
import org.cidrz.webapp.dynasite.exception.PersistenceException;

/**
 * Factory to obtain a concrete instance of PersistenceManager
 */
public class PersistenceManagerFactory {
    /**
     * Obtains the proper PersistenceManager instance for the specified class.
     * Class specified must be a persistent class.
     *
     * @param persistentClass The class to be managed.
     * @return The instance of PersistenceManager
     * @throws org.cidrz.webapp.dynasite.exception.PersistenceException
     */
    public static PersistenceManager getInstance(Class persistentClass) throws PersistenceException {
        //special cases
        if (persistentClass.equals(ClientSettings.class)) {
            return ClientSettingsManager.getInstance();
        } else if (persistentClass.equals(Patient.class)) {
            return PatientManager.getInstance();
        }
        /**
         * If this persistentClass isn't a special case, just use the
         * BasePersistenceManager implementation.
         */
        return BasePersistenceManager.getInstance(persistentClass);
    }

    private PersistenceManagerFactory() {
        // no instances, please
    }
}
