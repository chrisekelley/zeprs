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
 * Date: Feb 24, 2004
 * Time: 11:24:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class PersistenceException extends LoggingException {

    public PersistenceException() {
        this("Unknown persistence exception.");
    }

    public PersistenceException(String message) {
        super(message);
        log.error("Persistence error. Message: " + message);
    }

    public PersistenceException(Exception nestedException) {
        super(nestedException);
        log.error("Persistence error. Message: " + nestedException.getMessage());
    }

    /**
     * Pass the message and the exception too.
     * @param message
     * @param nestedException
     */
    public PersistenceException(String message, Exception nestedException, Boolean isLogged) {
    	super(message, nestedException);
    	if (isLogged) {
        	log.error("Persistence error. Message: " + message + " Exception: " + nestedException.getMessage());
    	}
    }

}
