/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.exception;

import org.cidrz.webapp.dynasite.exception.LoggingException;


/**
 * Created by IntelliJ IDEA.
 * User: dave
 * Date: Mar 10, 2004
 * Time: 3:51:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class ObjectNotFoundException extends LoggingException {
    public ObjectNotFoundException() {
        //  this("Object not found, perhaps while searching the database - incorrect URL?.");
    }

    public ObjectNotFoundException(String message) {
        super(message);
        log.error(message);
    }

    public ObjectNotFoundException(Exception nestedException) {
        super(nestedException);
        log.error("Object not found perhaps while searching the database. Message: " + nestedException.getMessage());
    }

}
