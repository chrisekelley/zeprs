/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.struts.action.generic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;

/**
 * Base class for generic actions that manipulate persistent objects.
 * To be used properly, the mappings for this class must have the "parameter" attribute
 * set to a fully-qualified classname for the class to be manuipulated
 */
public abstract class PersistentObjectAction extends BaseAction {
    /**
     * Commons Logging instance.
     */
    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    protected Class getMappedClass(ActionMapping mapping) throws Exception {

        String classname = mapping.getParameter();
        try {
            return Class.forName(classname);
        } catch (ClassNotFoundException e) {
            log.error("bad class for this mapping. Mapping path=" + mapping.getPath() + ", Class=" + classname);
            throw new Exception("bad mapping, no class--see log");
        }
    }
}
