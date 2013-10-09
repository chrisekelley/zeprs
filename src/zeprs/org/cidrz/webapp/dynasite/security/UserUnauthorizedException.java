/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.security;

/**
 * Created by IntelliJ IDEA.
 * User: Dave Peckham
 * Date: Apr 28, 2004
 * Time: 2:19:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserUnauthorizedException extends Exception {
    public UserUnauthorizedException() {
        super();
    }

    public UserUnauthorizedException(String message) {
        super(message);
    }
}
