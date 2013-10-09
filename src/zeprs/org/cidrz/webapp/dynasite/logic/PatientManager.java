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

import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.logic.BasePersistenceManager;
import org.cidrz.webapp.dynasite.logic.PersistenceManager;

/**
 * Created by IntelliJ IDEA.
 * User: Dave Peckham
 * Date: May 24, 2004
 * Time: 8:18:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class PatientManager extends BasePersistenceManager {
    /**
     * Commons Logging instance.
     */
    //private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    private PatientManager() {
        super(Patient.class);
    }

    private static PatientManager singleton = null;

    /**
     * Singleton implementation
     *
     * @return The singleton instance of this class
     */
    public static PersistenceManager getInstance() {
        if (singleton == null) {
            singleton = new PatientManager();
        }
        return singleton;
    }

}
