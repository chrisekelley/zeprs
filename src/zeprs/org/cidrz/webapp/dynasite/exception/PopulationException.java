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

public class PopulationException extends LoggingException {
    public PopulationException() {
        super();
        log.warn("Population error.");
    }

    public PopulationException(String message, Throwable cause) {
        super(message, cause);
        log.warn("Population error.");
    }
}