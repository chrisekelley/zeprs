/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.rules.impl;

import org.cidrz.webapp.dynasite.rules.Outcome;

/**
 * Outcome that is purely informational. Allow the configurator to send messages
 * (i.e. reminder or suggestion) to a user when certain conditions are met (or not met).
 *
 * @hibernate.subclass  discriminator-value="info"
 *
 */
public class InfoOutcome extends Outcome {
    private String message;

    /**
     * @return The message to be displayed
     * @hibernate.property
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message to be displayed
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
