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

import org.cidrz.webapp.dynasite.dao.ProblemDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.struts.action.generic.SaveObjectAction;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.sql.Connection;

public class CorrectProblemAction extends SaveObjectAction {

    protected Identifiable getSubject(Connection conn, Class clazz, Long id) throws PersistenceException, ObjectNotFoundException {
        Identifiable subject = null;
        try {
            subject = ProblemDAO.getOne(conn, id);
        } catch (SQLException e) {
            log.error(e);
        } catch (ServletException e) {
            log.error(e);
        }
        return subject;
    }
}

