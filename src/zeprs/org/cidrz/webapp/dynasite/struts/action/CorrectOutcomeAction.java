/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.struts.action;

import org.cidrz.webapp.dynasite.dao.OutcomeDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.struts.action.generic.SaveObjectAction;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.SQLException;

public class CorrectOutcomeAction extends SaveObjectAction {

    protected Identifiable getSubject(Connection conn, Class clazz, Long id) throws PersistenceException, ObjectNotFoundException {

        
        Identifiable subject = null;
        try {
            subject = OutcomeDAO.getOne(conn, id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return subject;
    }
}