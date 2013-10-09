/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.logic;

import org.hibernate.Criteria;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.apache.commons.beanutils.BeanUtils;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;
import org.cidrz.webapp.dynasite.valueobject.ClientSettings;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.utils.HibernateUtil;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Dave Peckham
 * Date: Mar 29, 2004
 * Time: 5:29:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClientSettingsManager extends BasePersistenceManager {
    /**
     * Gets a single instance with the matching id.
     *
     * @param example
     * @return The matching instance
     * @throws org.cidrz.webapp.dynasite.exception.PersistenceException    If an error occurs in the persistence layer
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException If no match was found.
     */
    public Identifiable getOne(Identifiable example) throws PersistenceException, ObjectNotFoundException {
        Identifiable object = null;
        try {
            Map exampleProps = BeanUtils.describe(example);
            // let's start a new session
            HibernateUtil.closeSession();
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.clazz);
            if (exampleProps.containsKey("ipAddress")) {
                crit.add(Expression.eq("ipAddress", exampleProps.get("ipAddress")));
                crit.setMaxResults(1);
            }
            object = (Identifiable) crit.uniqueResult();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (NonUniqueResultException e) {
            // we need to return a single result
        } catch (Exception e) {
            throw new PersistenceException(e);
            // it's probably not unique - clear out the cruft
            // todo: figure out how duplicates happen
        }
        if (object == null) {
            throw new ObjectNotFoundException();
        }
        return object;
    }

    private ClientSettingsManager() {
        super(ClientSettings.class);
    }

    private static ClientSettingsManager singleton = null;

    /**
     * Singleton implementation
     *
     * @return The singleton instance of this class
     */
    public static PersistenceManager getInstance() {
        if (singleton == null) {
            singleton = new ClientSettingsManager();
        }
        return singleton;
    }
}
