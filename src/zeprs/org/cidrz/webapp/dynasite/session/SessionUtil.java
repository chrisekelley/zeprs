/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.session;

import org.cidrz.webapp.dynasite.exception.LoggingException;
import org.cidrz.webapp.dynasite.valueobject.ClientSettings;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;

import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 * User: dave
 * Date: Mar 2, 2004
 * Time: 2:26:05 PM
 */
public class SessionUtil {
    private static final String SESSION_KEY = "zeprs_session";
    private ClientSettings clientSettings = null;
    private String fullname = null;
    private String firstname = null;
    private String lastname = null;
    private SessionPatient sessionPatient = null;

    public boolean isClientConfigured() {
        return (clientSettings != null);
    }

    public static void clear(HttpSession session) {
        session.removeAttribute(SessionUtil.SESSION_KEY);
    }

    /**
     * gets Full name of user currently logged-in
     * @return
     * @throws AttributeNotFoundException
     */
    public String getFullname()  throws AttributeNotFoundException {
        if (fullname == null) {
           throw new AttributeNotFoundException();
        }
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * gets firstname of user currently logged-in
     * @return
     * @throws AttributeNotFoundException
     */
    public String getFirstname() throws AttributeNotFoundException {
        if (firstname == null) {
           throw new AttributeNotFoundException();
        }
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * gets lastname of user currently logged-in
     * @return
     * @throws AttributeNotFoundException
     */
    public String getLastname() throws AttributeNotFoundException {
        if (lastname == null) {
           throw new AttributeNotFoundException();
        }
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public static SessionUtil getInstance(HttpSession session) {
        SessionUtil sessionCopy = (SessionUtil) session.getAttribute(SessionUtil.SESSION_KEY);
        if (sessionCopy == null) {
            sessionCopy = new SessionUtil();
            session.setAttribute(SessionUtil.SESSION_KEY, sessionCopy);
        }
        return sessionCopy;
    }


    public void setClientSettings(ClientSettings settings) {
        this.clientSettings = settings;
    }

    public ClientSettings getClientSettings() throws AttributeNotFoundException {
        if (clientSettings == null) {
            throw new AttributeNotFoundException();
        }
        return clientSettings;
    }

    public class AttributeNotFoundException extends LoggingException {
    }

    public SessionPatient getSessionPatient() throws AttributeNotFoundException {
        if (sessionPatient == null) {
            throw new AttributeNotFoundException();
        }
        return sessionPatient;
    }

    public void setSessionPatient(SessionPatient sessionPatient) {
        this.sessionPatient = sessionPatient;
    }

}
