/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.dao;

import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.project.zeprs.valueobject.gen.PregnancyDating;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Oct 24, 2005
 *         Time: 6:54:11 PM
 */
public class PregnancyDatingDAO {

    public static java.util.Date getEgaDate(Connection conn, Long encounterId) throws ServletException, SQLException, ObjectNotFoundException {
        String sql;
        ArrayList values;
        EncounterData encounter = null;
        sql = "select id, date_visit AS datevisit from encounter where id=? ";
        values = new ArrayList();
        values.add(encounterId);
        encounter = (EncounterData) DatabaseUtils.getBean(conn, PregnancyDating.class, sql, values);
        java.util.Date dateVisit = encounter.getDateVisit();
        return dateVisit;
    }
}
