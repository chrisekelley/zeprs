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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Base exception class that has internal logging capability.
 * Unless instructed otherwise, this class logs all exceptions as
 * severity "error" so that we find everything that might have
 * slipped the developers' minds. So, developers should remember to
 * handle lesser exceptions by passing in an appropriate log level.
 */
public abstract class LoggingException extends Exception {
    public static final int SEVERITY_DEBUG = 0;
    public static final int SEVERITY_INFO = 1;
    public static final int SEVERITY_WARN = 2;
    public static final int SEVERITY_ERROR = 3;
    public static final int SEVERITY_FATAL = 4;

    private static final String NO_MESSAGE = "No message provided.";

    /**
     * Commons Logging instance.
     */
    protected Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    public LoggingException() {
        this(NO_MESSAGE);
    }

    public LoggingException(String message) {
        super(message);
    }

    public LoggingException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoggingException(Throwable cause) {
        this(NO_MESSAGE, cause);
    }
}
