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

import org.hibernate.*;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.valueobject.*;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.utils.HibernateUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.rules.Outcome;

/**
 * Basic implementation of PersistenceManager
 */
public class BasePersistenceManager implements PersistenceManager {
    /**
     * Commons Logging instance.
     */
    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    /**
     * Not exactly singletons, but only one instance per Class
     */
    private static Map instances = new HashMap();

    /**
     * Class that this instance manages
     */
    protected Class clazz;

    protected BasePersistenceManager(Class clazz) {
        this.clazz = clazz;
    }

    /**
     * Obtains all instances of the class this PersistenceManager handles,
     * as determined when the implementation of this PersistenceManager
     * was obtained from the PersistenceManagerFactory.
     *
     * @return A List of instances of the managed class.
     * @throws org.cidrz.webapp.dynasite.exception.PersistenceException If an error occurs in the persistence layer
     */
    public List getAll() throws PersistenceException {
        List results = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            //Criteria crit = session.createCriteria(this.clazz);
            //results = crit.list();

            Query allItems = session.createQuery("from " + this.clazz.getName());
            allItems.setCacheable(true);
            allItems.setCacheRegion("query.allItems");
            results = allItems.list();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (results == null) {
            throw new PersistenceException();
        }
        return results;
    }

    /**
     * Obtains all instances of the class this PersistenceManager handles,
     * in the specified order
     *
     * @param orderName
     * @return A List of instances of the managed class.
     * @throws PersistenceException If an error occurs in the persistence layer
     */
    public List getAll(String orderName) throws PersistenceException {
        List results = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
            } catch (HibernateException e) {
                /*HibernateUtil.closeSession();
                session = HibernateUtil.newSession();*/
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            Criteria crit = session.createCriteria(this.clazz)
                    .addOrder(Order.asc(orderName));
            crit.setCacheable(true);
            crit.setCacheRegion("query.allItems");
            results = crit.list();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
        if (results == null) {
            throw new PersistenceException();
        }
        return results;
    }

    /**
     * Gets all instances with the matching patient. Provide name of patient id in the class.
     *
     * @param patient
     * @param patientIdName Name of patient id in the class.
     * @param pregnancyId
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException If no match was found.
     */
    public List getAllForPatient(String patientIdName, Patient patient, Long pregnancyId) throws PersistenceException, org.cidrz.webapp.dynasite.exception.ObjectNotFoundException {
        List results;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from " + this.clazz.getName() + " c" +
                    " where c.encounter.pregnancy.id = :pregnancyId " +
                    " and c.patient.id = :patient");
            // " where c.patient.id = :patient");

            query.setLong("pregnancyId", pregnancyId.longValue());
            query.setLong("patient", patient.getId().longValue());

            //Criteria crit = session.createCriteria(this.clazz).add(Expression.eq(patientIdName, patient))
            /*.createAlias("PatientStatusReport", "PatientStatusReport")
                    .add(Expression.eq("problem.active", status))*/
            // .add(Expression.eq("patient.patientStatusreport.currentPregnancy", currentPregnancy));

            results = query.list();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (results == null) {
            throw new org.cidrz.webapp.dynasite.exception.ObjectNotFoundException();
        }
        return results;
    }



    /**
     * Gets a single instance with the matching id.
     *
     * @param id
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException If no match was found.
     */
    public Identifiable getOne(Long id) throws PersistenceException, org.cidrz.webapp.dynasite.exception.ObjectNotFoundException {
        Identifiable object = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.clazz).add(Expression.eq("id", id));
            object = (Identifiable) crit.uniqueResult();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (object == null) {
            throw new org.cidrz.webapp.dynasite.exception.ObjectNotFoundException("An object with the id: " + id + " was not found.");
        }
        return object;
    }

    /**
     * Fetches fresh version of form. No caching allowed.
     *
     * @param id
     * @return Matching instance, non cached.
     * @throws PersistenceException
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException
     */
    public Identifiable getFreshOne(Long id) throws PersistenceException, ObjectNotFoundException, IOException {
        Identifiable object = null;

        try {
            Session session = HibernateUtil.currentSession();
            session.evict(clazz.getName());
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.clazz).add(Expression.eq("id", id))
                    .setFetchMode("pageItems", FetchMode.EAGER)
                    .setFetchMode("formfields", FetchMode.EAGER);
            crit.setCacheable(false);
            object = (Identifiable) crit.uniqueResult();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (object == null) {
            throw new org.cidrz.webapp.dynasite.exception.ObjectNotFoundException();
        }
        return object;
    }

    /**
     * Fetches fresh version of form. Lazy on the enums for pageitems. No caching allowed.
     *
     * @param id
     * @return Matching instance, non cached.
     * @throws PersistenceException
     * @throws ObjectNotFoundException
     */
    public Identifiable getFreshForm(Long id) throws PersistenceException, org.cidrz.webapp.dynasite.exception.ObjectNotFoundException, IOException {
        Identifiable object = null;

        try {
            Session session = HibernateUtil.currentSession();
            session.evict(clazz.getName());
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.clazz).add(Expression.eq("id", id))
                    .setFetchMode("pageItems", FetchMode.EAGER)
                    .setFetchMode("formfields", FetchMode.EAGER);
            crit.setCacheable(false);
            object = (Identifiable) crit.uniqueResult();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (object == null) {
            throw new org.cidrz.webapp.dynasite.exception.ObjectNotFoundException();
        }
        return object;
    }


    /**
     * Gets a single instance with the matching id.
     *
     * @param example
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */
    public Identifiable getOne(Identifiable example) throws PersistenceException, ObjectNotFoundException {
        try {
            Map exampleParams = BeanUtils.describe(example);
            return this.getOne((Long) exampleParams.get("id"));
        } catch (IllegalAccessException e) {
            throw new PersistenceException(e);
        } catch (InvocationTargetException e) {
            throw new PersistenceException(e);
        } catch (NoSuchMethodException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Gets all instances with this parent id. Only useful for classes that have recursive relationships.
     * <p/>
     * todo: this class could use the mappings or reflection to figure out the parent column.
     *
     * @param parentId
     * @return A List of matching instances
     * @throws PersistenceException If an error occurs in the persistence layer
     */
    public List getChildren(Long parentId) throws PersistenceException {
        List results = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.clazz).add(Expression.eq("parentId", parentId));
            results = crit.list();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (results == null) {
            throw new PersistenceException();
        }
        return results;
    }

    /**
     * Gets all instances with this parent id. Only useful for classes that have recursive relationships.
     * <p/>
     * todo: this class could use the mappings or reflection to figure out the parent column.
     *
     * @param parentId
     * @return A List of matching instances
     * @throws PersistenceException If an error occurs in the persistence layer
     */
    public List getChildren(Long parentId, String orderName) throws PersistenceException {
        List results = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.clazz).add(Expression.eq("parentId", parentId))
                    .addOrder(Order.asc(orderName));
            results = crit.list();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (results == null) {
            throw new PersistenceException();
        }
        return results;
    }

    /**
     * Gets all instances of patients with this parent id.
     *
     * @param parent
     * @return A List of matching instances
     * @throws PersistenceException If an error occurs in the persistence layer
     */
    public List getChildren(Patient parent) throws PersistenceException {
        List results = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(Patient.class).add(Expression.eq("parent", parent));
            results = crit.list();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (results == null) {
            throw new PersistenceException();
        }
        return results;
    }

    /**
     * Persists the object
     *
     * @param persistentObject
     * @param site
     * @return The saved object in it's post-save state.
     * @throws PersistenceException If an error occurs in the persistence layer
     */

    public Identifiable save(Identifiable persistentObject, Principal user, Site site) throws PersistenceException {
        /*if (persistentObject instanceof Configuration && SystemStateManager.getCurrentState() != SystemStateManager.STATUS_LOCKED) {
            //todo: throw a systemstateexception, so that the app can redirect to the correct page
            throw new PersistenceException("Can't change Configuration items when system is not in STATE_LOCKED.");
        }
        if (!(persistentObject instanceof Configuration) && SystemStateManager.getCurrentState() == SystemStateManager.STATUS_LOCKED) {
            //todo: throw a systemstateexception, so that the app can redirect to the correct page
            throw new PersistenceException("System is locked, non-Configuration persistence is not permitted.");
        }*/

        if (persistentObject instanceof Auditable) {
            AuditInfo info = ((Auditable) persistentObject).getAuditInfo();
            info.setLastModified(new Timestamp(System.currentTimeMillis()));
            User userInfo = null;
            try {
                userInfo = (User) PersistenceManagerFactory.getInstance(User.class).getUser(user.getName());
            } catch (org.cidrz.webapp.dynasite.exception.ObjectNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            info.setLastModifiedBy(userInfo);
            info.setSite(site);
            if (info.getCreated() == null) {
                info.setCreated(info.getLastModified());
                info.setCreatedBy(info.getLastModifiedBy());
            }
        }

        if (persistentObject instanceof Auditable) {
            AuditInfo info = ((Auditable) persistentObject).getAuditInfo();
            User userObject = new User();
            userObject.setId(user.getName());
            info.setLastModified(new Timestamp(System.currentTimeMillis()));
            info.setLastModifiedBy(userObject);
            info.setSite(site);
            if (info.getCreated() == null) {
                info.setCreated(info.getLastModified());
                info.setCreatedBy(userObject);
            }
        }

        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.currentSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(persistentObject);
            //session.refresh(persistentObject);
            tx.commit();
            HibernateUtil.closeSession();
        } catch (HibernateException e) {
            if (tx != null)
                try {
                    tx.rollback();
                } catch (HibernateException rollbackException) {
                    log.error(rollbackException);
                }
            throw new PersistenceException(e);
        } finally {
            try {
                if (session.isOpen()) {
                   session.close();
                }                
            } catch (HibernateException e) {
                log.error(e);
            }
        }
        return persistentObject;
    }

    /**
     * Persists the object - used by remote
     *
     * @param persistentObject
     * @param user
     * @param site
     * @return
     * @throws PersistenceException
     */

    public Identifiable save(Identifiable persistentObject, String user, Site site) throws PersistenceException {
        /*if (persistentObject instanceof Configuration && SystemStateManager.getCurrentState() != SystemStateManager.STATUS_LOCKED) {
            //todo: throw a systemstateexception, so that the app can redirect to the correct page
            throw new PersistenceException("Can't change Configuration items when system is not in STATE_LOCKED.");
        }
        if (!(persistentObject instanceof Configuration) && SystemStateManager.getCurrentState() == SystemStateManager.STATUS_LOCKED) {
            //todo: throw a systemstateexception, so that the app can redirect to the correct page
            throw new PersistenceException("System is locked, non-Configuration persistence is not permitted.");
        }*/

        if (persistentObject instanceof Auditable) {
            AuditInfo info = ((Auditable) persistentObject).getAuditInfo();
            info.setLastModified(new Timestamp(System.currentTimeMillis()));
            User userInfo = null;
            try {
                userInfo = (User) PersistenceManagerFactory.getInstance(User.class).getUser(user);
            } catch (org.cidrz.webapp.dynasite.exception.ObjectNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            info.setLastModifiedBy(userInfo);
            info.setSite(site);
            if (info.getCreated() == null) {
                info.setCreated(info.getLastModified());
                info.setCreatedBy(info.getLastModifiedBy());
            }
        }

        if (persistentObject instanceof org.cidrz.webapp.dynasite.valueobject.Auditable) {
            AuditInfo info = ((Auditable) persistentObject).getAuditInfo();
            User userObject = new User();
            userObject.setId(user);
            info.setLastModified(new Timestamp(System.currentTimeMillis()));
            info.setLastModifiedBy(userObject);
            info.setSite(site);
            if (info.getCreated() == null) {
                info.setCreated(info.getLastModified());
                info.setCreatedBy(userObject);
            }

        }

        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.currentSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(persistentObject);
            //session.refresh(persistentObject);
            tx.commit();
            HibernateUtil.closeSession();
        } catch (HibernateException e) {
            if (tx != null)
                try {
                    tx.rollback();
                } catch (HibernateException rollbackException) {
                    log.error(rollbackException);
                }
            throw new PersistenceException(e);
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                log.error(e);
            }
        }
        return persistentObject;
    }


    /**
     * Quasi-Singleton/Factory implementation
     *
     * @return The single instance which services the specified class
     */
    public static PersistenceManager getInstance(Class clazz) {
        if (!instances.containsKey(clazz)) {
            instances.put(clazz, new BasePersistenceManager(clazz));
        }
        return (PersistenceManager) instances.get(clazz);
    }

    /**
     * Deletes the object
     *
     * @param persistentObject
     * @throws PersistenceException If an error occurs in the persistence layer
     */
    public void delete(Identifiable persistentObject, Principal user) throws PersistenceException {
        /*if (persistentObject instanceof Configuration && SystemStateManager.getCurrentState() != SystemStateManager.STATUS_LOCKED) {
            //todo: throw a systemstateexception, so that the app can redirect to the correct page
            throw new PersistenceException("Can't change Configuration items when system is not in STATE_LOCKED.");
        }
        if (!(persistentObject instanceof Configuration) && SystemStateManager.getCurrentState() == SystemStateManager.STATUS_LOCKED) {
            //todo: throw a systemstateexception, so that the app can redirect to the correct page
            throw new PersistenceException("System is locked, non-Configuration persistence is not permitted.");
        }*/

        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.currentSession();
            tx = session.beginTransaction();
            //session.saveOrUpdate(persistentObject);

            session.delete(persistentObject);
            tx.commit();
            HibernateUtil.closeSession();
        } catch (HibernateException e) {
            if (tx != null)
                try {
                    tx.rollback();
                } catch (HibernateException rollbackException) {
                    log.error(rollbackException);
                }
            throw new PersistenceException(e);
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                log.error(e);
            }
        }
    }


    public List getSharedfields(Patient patient) throws PersistenceException, org.cidrz.webapp.dynasite.exception.ObjectNotFoundException {
        List results = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(EncounterValue.class)
                    .add(Expression.eq("patient", patient));
            //todo: filter out empty values
            Disjunction any = Expression.disjunction();
            any.add(Expression.eq("fieldId", new Long(129)));
            /*any.add(Expression.eq("fieldId", new Long(224)));
            any.add(Expression.eq("fieldId", new Long(225)));*/
            /* any.add(Expression.eq("fieldId", new Long(228)));*/
            any.add(Expression.eq("fieldId", new Long(232)));
            /*any.add(Expression.eq("fieldId", new Long(235)));*/
            any.add(Expression.eq("fieldId", new Long(242)));
            any.add(Expression.eq("fieldId", new Long(243)));
            any.add(Expression.eq("fieldId", new Long(244)));
            crit.add(any);
            crit.addOrder(Order.asc("visitDate"))
                    .setFetchMode("pageItem.getForm_field().getEnumerations()", FetchMode.EAGER);
            // .setFetchMode("pageItem.form_field).enumerations", FetchMode.EAGER);
            results = crit.list();
            //log.info("test logging");
            //log.info(sql);
            //log.error(sql);
            //System.out.println("sql: " + sql.toString());
            //results = q.list();
            tx.commit();
            HibernateUtil.closeSession();

        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (results == null) {
            throw new PersistenceException();
        }
        return results;
    }

    /**
     * Gets a patient with the matching id.
     *
     * @param id
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */
    public Identifiable getPatient(Long id) throws PersistenceException, org.cidrz.webapp.dynasite.exception.ObjectNotFoundException {
        Identifiable object = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.clazz).add(Expression.eq("id", id))
                    //           .setFetchMode("encounters", FetchMode.EAGER)
                    ;
            object = (Identifiable) crit.uniqueResult();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (object == null) {
            throw new org.cidrz.webapp.dynasite.exception.ObjectNotFoundException();
        }
        return object;
    }

    /**
     * Gets a single encounter with the matching id. Fetch the whole mutha(FetchMode.EAGER).
     *
     * @param id
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException If no match was found.
     */
    public Identifiable getEncounter(Long id) throws PersistenceException, ObjectNotFoundException {
        Identifiable object = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.clazz).add(Expression.eq("id", id))
                    .setFetchMode("encounterValues", FetchMode.EAGER)
                    .setFetchMode("encounterValues.pageItem.form_field).enumerations", FetchMode.EAGER);
            ;
            object = (Identifiable) crit.uniqueResult();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (object == null) {
            throw new org.cidrz.webapp.dynasite.exception.ObjectNotFoundException();
        }
        return object;
    }

    /**
     * Gets problems from patient with the matching id.
     *
     * @param patient
     * @param status
     * @param pregnancyId
     * @return List of problems
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException If no match was found.
     */
    public List getProblems(Patient patient, Boolean status, Long pregnancyId) throws PersistenceException, ObjectNotFoundException {
        List results = null;
        Boolean thisstatus;
        if (status != null) {
            thisstatus = status;
        } else {
            thisstatus = Boolean.valueOf(true);
        }
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(Problem.class).add(Expression.eq("patient", patient))
                    .add(Expression.eq("active", thisstatus))
                    .add(Expression.eq("pregnancy.id", pregnancyId))
                    .addOrder(Order.desc("auditInfo.created"));
            results = crit.list();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (results == null) {
            throw new org.cidrz.webapp.dynasite.exception.ObjectNotFoundException();
        }
        return results;
    }

    /**
     * Gets comments from patient/problemwith the matching id.
     *
     * @param patientId
     * @param problem
     * @return List of comments
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException If no match was found.
     */
    public List getComments(Long patientId, Outcome problem) throws PersistenceException, org.cidrz.webapp.dynasite.exception.ObjectNotFoundException {


        List comments = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(Comment.class).add(Expression.eq("patient.id", patientId))
                    .add(Expression.eq("problem", problem))
                    .addOrder(Order.desc("auditInfo.created"))
                    ;
            comments = crit.list();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (comments == null) {
            throw new ObjectNotFoundException();
        }
        return comments;
    }

    /**
     * Gets comments from patient/outcome with the matching id.
     *
     * @param patientId
     * @param outcome
     * @return List of comments
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException If no match was found.
     */
    public List getComments(Long patientId, Problem outcome) throws PersistenceException, org.cidrz.webapp.dynasite.exception.ObjectNotFoundException {

        List comments = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(Comment.class).add(Expression.eq("patient.id", patientId))
                    .add(Expression.eq("outcome", outcome))
                    .addOrder(Order.desc("auditInfo.created"))
                    ;
            comments = crit.list();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (comments == null) {
            throw new org.cidrz.webapp.dynasite.exception.ObjectNotFoundException();
        }
        return comments;
    }

    /**
     * Gets most recent instance comments for patient, depending upon the status
     *
     * @param patientId
     * @param status
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException If no match was found.
     */
    public Identifiable getMostrecentcomments(Long patientId, Boolean status) throws PersistenceException, ObjectNotFoundException {
        Identifiable object = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.clazz)
                    .add(Expression.eq("patient.id", patientId))
                    .setFetchMode("problem", FetchMode.EAGER)
                    .createAlias("problem", "problem")
                    .add(Expression.eq("problem.active", status))
                    .addOrder(Order.desc("auditInfo.created"))
                    .setMaxResults(1)
                    ;
            object = (Identifiable) crit.uniqueResult();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (object == null) {
            throw new org.cidrz.webapp.dynasite.exception.ObjectNotFoundException();
        }
        return object;
    }

    /**
     * Gets most recent instance comments for patient, depending upon the status
     *
     * @param patientId
     * @param status
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */
    public Identifiable getMostrecentOutcomecomments(Long patientId, Boolean status) throws PersistenceException, org.cidrz.webapp.dynasite.exception.ObjectNotFoundException {
        Identifiable object = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.clazz)
                    .add(Expression.eq("patient.id", patientId))
                    .setFetchMode("outcome", FetchMode.EAGER)
                    .createAlias("outcome", "outcome")
                    .add(Expression.eq("outcome.active", status))
                    .addOrder(Order.desc("auditInfo.created"))
                    .setMaxResults(1)
                    ;
            object = (Identifiable) crit.uniqueResult();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (object == null) {
            throw new org.cidrz.webapp.dynasite.exception.ObjectNotFoundException();
        }
        return object;
    }

    /**
     * Gets a single problem with the matching id. Fetch the whole mutha(FetchMode.EAGER).
     *
     * @param id
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException If no match was found.
     */

    public Identifiable getProblem(Long id) throws PersistenceException, org.cidrz.webapp.dynasite.exception.ObjectNotFoundException {
        Identifiable object = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.clazz)
                    .setFetchMode("comments", FetchMode.EAGER)
                    .add(Expression.eq("id", id))
                    ;
            object = (Identifiable) crit.uniqueResult();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (object == null) {
            throw new org.cidrz.webapp.dynasite.exception.ObjectNotFoundException();
        }
        return object;
    }

    /**
     * Gets a single outcome with the matching id. Fetch the whole mutha(FetchMode.EAGER).
     *
     * @param id
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */

    public Identifiable getOutcome(Long id) throws PersistenceException, org.cidrz.webapp.dynasite.exception.ObjectNotFoundException {
        Identifiable object = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.clazz)
                    .setFetchMode("comments", FetchMode.EAGER)
                    .add(Expression.eq("id", id))
                    ;
            object = (Identifiable) crit.uniqueResult();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (object == null) {
            throw new ObjectNotFoundException();
        }
        return object;
    }


    /**
     * Gets a list of all outcomes and problems for this patient
     *
     * @param patient
     * @param status
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException If no match was found.
     */
    public Identifiable getAlerts(Patient patient, Boolean status) throws PersistenceException, org.cidrz.webapp.dynasite.exception.ObjectNotFoundException {
        Identifiable object = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(Patient.class)
                    .add(Expression.eq("id", patient.getId()))
                    .setFetchMode("problems", FetchMode.EAGER)
                    //.setFetchMode("outcomes", FetchMode.EAGER)
                    .createAlias("problems", "problems")
                    .add(Expression.eq("problems.active", status))
                    .addOrder(Order.desc("auditInfo.created"))
                    ;
            object = (Identifiable) crit.uniqueResult();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (object == null) {
            throw new ObjectNotFoundException();
        }
        return object;
    }

    /**
     * Gets the outcomes with the matching patient id.
     *
     * @param patientId
     * @param status
     * @param pregnancyId
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException If no match was found.
     */
    public List getOutcomes(Long patientId, Boolean status, Long pregnancyId) throws PersistenceException, org.cidrz.webapp.dynasite.exception.ObjectNotFoundException {
        List results;
        Boolean thisstatus;
        if (status != null) {
            thisstatus = status;
        } else {
            thisstatus = Boolean.valueOf(true);
        }
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.clazz).add(Expression.eq("patientId", patientId))
                    .add(Expression.eq("active", thisstatus))
                    .add(Expression.eq("pregnancy.id", pregnancyId))
                    //           .setFetchMode("encounters", FetchMode.EAGER)
                    ;
            results = crit.list();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (results == null) {
            throw new org.cidrz.webapp.dynasite.exception.ObjectNotFoundException();
        }
        return results;
    }


    /**
     * Gets the outcomes with the matching patient id and encounter id.
     *
     * @param patientId
     * @param status
     * @param pregnancyId
     * @param encounterId
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException If no match was found.
     */
    public List getOutcomes(Long patientId, Boolean status, Long pregnancyId, Long encounterId) throws PersistenceException, ObjectNotFoundException {
        List results;
        Boolean thisstatus;
        if (status != null) {
            thisstatus = status;
        } else {
            thisstatus = Boolean.valueOf(true);
        }
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.clazz).add(Expression.eq("patientId", patientId))
                    .add(Expression.eq("active", thisstatus))
                    .add(Expression.eq("pregnancy.id", pregnancyId))
                    .add(Expression.eq("encounterId", encounterId))
                    //           .setFetchMode("encounters", FetchMode.EAGER)
                    ;
            results = crit.list();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (results == null) {
            throw new org.cidrz.webapp.dynasite.exception.ObjectNotFoundException();
        }
        return results;
    }

    /**
     * Get all fields for populating the formbean. Cannot use the standard getAll because some of the pageitems are display-only
     * and should not be put into formbean.
     *
     * @return A List of instances of the managed class.
     * @throws PersistenceException If an error occurs in the persistence layer
     */
    /* public List getAllFormFields() throws PersistenceException {
         List results = null;
         try {
             Session session = HibernateUtil.currentSession();
             Transaction tx = session.beginTransaction();
             Query allFields = session.createQuery("from FormField field where field.type !='Display' ");
             results = allFields.list();
             tx.commit();
             HibernateUtil.closeSession();
         } catch (Exception e) {
             throw new PersistenceException(e);
         }
         if (results == null) {
             throw new PersistenceException();
         }
         return results;
     }*/

    /**
     * Find if user has filled out Problem/Labour visit form
     *
     * @param id
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException If no match was found.
     */
    public Identifiable getFormFromEncounter(Long id) throws PersistenceException, org.cidrz.webapp.dynasite.exception.ObjectNotFoundException {
        Identifiable object = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.clazz).add(Expression.eq("form.id", id));
            object = (Identifiable) crit.uniqueResult();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (object == null) {
            throw new org.cidrz.webapp.dynasite.exception.ObjectNotFoundException();
        }
        return object;
    }

    /**
     * Return list of antenatal record exam.
     *
     * @param patientId
     * @param pregnancyId
     * @return
     * @throws PersistenceException
     * @throws ObjectNotFoundException
     */
    public List getPatientRecords(Long patientId, Long pregnancyId) throws PersistenceException, org.cidrz.webapp.dynasite.exception.ObjectNotFoundException {
        List results = null;
        try {
            Session hysession = HibernateUtil.currentSession();
            Transaction tx = hysession.beginTransaction();
            Query query = hysession.createQuery("from  " + this.clazz.getName() + " er " +
                    " where er.pregnancyId = :pregnancyId " +
                    " and er.patientId= :patientId ");
            query.setLong("pregnancyId", pregnancyId.longValue());
            query.setLong("patientId", patientId.longValue());
            results = query.list();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (results == null) {
            throw new ObjectNotFoundException();
        }
        return results;
    }

    /**
     * Gets all enabled forms.
     *
     * @return The matching instance
     * @throws PersistenceException If an error occurs in the persistence layer
     */
    public List getAllForms() throws PersistenceException {
        List object = null;
        Boolean enabled = Boolean.valueOf(true);
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(Form.class)
                    .add(Expression.eq("enabled", enabled))
                    //       .setFetchMode("pageItems", FetchMode.EAGER)
                    //       .setFetchMode("formfields", FetchMode.EAGER)
                    ;
            object = crit.list();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        return object;
    }


    /**
     * Gets a single instance with the matching id. Fetch the whole mutha(FetchMode.EAGER).
     *
     * @param id
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException If no match was found.
     */
    public Identifiable getOneForm(Long id) throws PersistenceException, ObjectNotFoundException {
        Identifiable object = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(Form.class).add(Expression.eq("id", id))
                    .setFetchMode("pageItems", FetchMode.EAGER)
                    .setFetchMode("formfields", FetchMode.EAGER)
                    .setFetchMode("pageItems.getForm_field().getEnumerations()", FetchMode.EAGER);
            object = (Identifiable) crit.uniqueResult();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (object == null) {
            throw new org.cidrz.webapp.dynasite.exception.ObjectNotFoundException();
        }
        return object;
    }

    /**
     * Gets all instances with this fieldId.
     * <p/>
     * todo: this class could use the mappings or reflection to figure out the parent column.
     *
     * @param fieldId
     * @return A List of matching instances
     * @throws PersistenceException If an error occurs in the persistence layer
     */
    public List getEnumerations(Long fieldId) throws PersistenceException {
        List results = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.clazz).add(Expression.eq("field.id", fieldId));
            results = crit.list();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (results == null) {
            throw new PersistenceException();
        }
        return results;
    }

    /**
     * get list of all shared FormFields
     *
     * @return
     * @throws PersistenceException
     */
    public List getAllSharedfields() throws PersistenceException {
        List results = null;
        Boolean shared = Boolean.valueOf(true);
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(FormField.class)
                    .add(Expression.eq("shared", shared));
            crit.addOrder(Order.asc("label"))
                    .setFetchMode("enumerations", FetchMode.LAZY);
            results = crit.list();
            tx.commit();
            HibernateUtil.closeSession();

        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (results == null) {
            throw new PersistenceException();
        }
        return results;
    }

    /**
     * Gets all instances with this ipaddress.
     *
     * @param ipAddress
     * @return A List of matching instances
     * @throws PersistenceException If an error occurs in the persistence layer
     */
    public List getSettings(String ipAddress) throws PersistenceException {
        List results = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.clazz).add(Expression.eq("ipAddress", ipAddress));
            results = crit.list();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (results == null) {
            throw new PersistenceException();
        }
        return results;
    }

    /**
     * Find if we've got a duplicate patient id
     *
     * @param artPatientid
     * @return
     * @throws PersistenceException
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException
     */
    public org.cidrz.webapp.dynasite.valueobject.Identifiable getPatientId(String artPatientid) throws PersistenceException, ObjectNotFoundException {
        org.cidrz.webapp.dynasite.valueobject.Identifiable object = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.clazz).add(Expression.eq("districtPatientid", artPatientid));
            object = (org.cidrz.webapp.dynasite.valueobject.Identifiable) crit.uniqueResult();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (object == null) {
            throw new org.cidrz.webapp.dynasite.exception.ObjectNotFoundException();
        }
        return object;
    }

    /**
     * Gets a single flow with the matching flowOrder.
     *
     * @param flowOrder
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException If no match was found.
     */
    public Identifiable getFlowByOrder(Long flowOrder) throws PersistenceException, org.cidrz.webapp.dynasite.exception.ObjectNotFoundException {
        Identifiable object = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.clazz).add(Expression.eq("flowOrder", flowOrder));
            object = (Identifiable) crit.uniqueResult();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (object == null) {
            throw new ObjectNotFoundException();
        }
        return object;
    }

    /**
     * Obtains all reports for form. Removes duplicates
     *
     * @param form
     * @return
     * @throws PersistenceException
     */
    public List getReports(Form form) throws PersistenceException {
        List results = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            //Criteria crit = session.createCriteria(this.clazz).add(Expression.eq("form", form));
            //results = crit.list();
            Query q = session.createQuery("select distinct field.liveReport from LiveReportField field" +
                    " where form_id=" + form.getId() +
                    " and shared='false'");
            results = q.list();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
        if (results == null) {
            throw new PersistenceException();
        }
        /*HashSet h = new HashSet(results);
        results.clear();
        results.addAll(h);*/
        return results;
    }

    /**
     * Obtains all reports. Removes duplicates
     *
     * @return
     * @throws PersistenceException
     */
    public List getReports() throws PersistenceException {
        List results = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("select distinct field.liveReport from LiveReportField field");
            results = q.list();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
        if (results == null) {
            throw new PersistenceException();
        }
        return results;
    }

    /**
     * Obtains all reports for a form_field.
     *
     * @param form_field
     * @return
     * @throws PersistenceException
     */
    public List getReports(FormField form_field) throws PersistenceException {
        List results = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from LiveReportField field " +
                    "where field.form_field = " + form_field.getId());
            results = q.list();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
        if (results == null) {
            throw new PersistenceException();
        }
        return results;
    }

    /**
     * Obtains all reports that have shared fields
     *
     * @return
     * @throws PersistenceException
     */
    public List getSharedReports() throws PersistenceException {
        List results = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("select distinct field.liveReport from LiveReportField field" +
                    " where field.shared='1'");
            results = q.list();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
        if (results == null) {
            throw new PersistenceException();
        }
        return results;
    }

    /**
     * Gets a single instance with the matching id.
     *
     * @param id
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException If no match was found.
     */
    public User getUser(String id) throws PersistenceException, org.cidrz.webapp.dynasite.exception.ObjectNotFoundException {
        User object = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.clazz).add(Expression.eq("id", id));
            object = (User) crit.uniqueResult();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (object == null) {
            throw new org.cidrz.webapp.dynasite.exception.ObjectNotFoundException("The user: " + id + " was not found in the ZEPRS database.");
        }
        return object;
    }

    /**
     * Gets a single instance with the matching id from userdata db.
     *
     * @param id
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException If no match was found.
     */
    public Staff getStaff(String id) throws PersistenceException, ObjectNotFoundException {
        Staff object = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.clazz).add(Expression.eq("id", id));
            object = (Staff) crit.uniqueResult();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (object == null) {
            throw new org.cidrz.webapp.dynasite.exception.ObjectNotFoundException("The user: " + id + " was not found in the userdata  database.");
        }
        return object;
    }

    /**
     * Fetches the regimen, sorted by name. Feed it the regimentype.
     *
     * @param regimenType
     * @return list of regimens
     */
    public List getRegimen(Long regimenType) throws PersistenceException {
        List results = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.clazz)
                    .add(Expression.eq("regimenType", regimenType))
                    .addOrder(Order.asc("name"));
            results = crit.list();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
            throw new PersistenceException(e);
        }
        if (results == null) {
            throw new PersistenceException();
        }
        return results;
    }

    /**
     * Gets instance of this object for this patient id and pregnancy
     *
     * @param patientId
     * @return the object
     * @throws PersistenceException If an error occurs in the persistence layer
     */
    public Identifiable getPatientData(Long patientId, Long pregnancyId) throws PersistenceException, org.cidrz.webapp.dynasite.exception.ObjectNotFoundException, NonUniqueResultException {
        Identifiable object = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.clazz).add(Expression.eq("patientId", patientId))
                    .add(Expression.eq("pregnancyId", pregnancyId));
            object = (Identifiable) crit.uniqueResult();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (object == null) {
            throw new org.cidrz.webapp.dynasite.exception.ObjectNotFoundException();
        }
        return object;
    }

    /**
     * Gets most recent record for this patient id and pregnancy
     *
     * @param patientId
     * @return the object
     * @throws PersistenceException If an error occurs in the persistence layer
     */
    public Identifiable getMostRecent(Long patientId, Long pregnancyId) throws PersistenceException, ObjectNotFoundException {
        Identifiable object = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.clazz)
                    .add(Expression.eq("patientId", patientId))
                    .add(Expression.eq("pregnancyId", pregnancyId))
                    .addOrder(Order.desc("auditInfo.created"))
                    .setMaxResults(1);
            object = (Identifiable) crit.uniqueResult();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (object == null) {
            throw new org.cidrz.webapp.dynasite.exception.ObjectNotFoundException();
        }
        return object;
    }

    /**
     * Gets a single instance with the matching property.
     *
     * @param property
     * @param id
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException If no match was found.
     */
    public Identifiable getOnebyProperty (String property, Long id) throws PersistenceException, org.cidrz.webapp.dynasite.exception.ObjectNotFoundException {
        Identifiable object = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.clazz).add(Expression.eq(property, id));
            object = (Identifiable) crit.uniqueResult();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (object == null) {
            throw new ObjectNotFoundException("An object with the property: " + property + " and the id: " + id + " was not found.");
        }
        return object;
    }

    /**
     * Gets a single instance with the matching property.
     *
     * @param property
     * @param id
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException If no match was found.
     */
    public Identifiable getOnebyProperty (String property, Integer id) throws PersistenceException, org.cidrz.webapp.dynasite.exception.ObjectNotFoundException {
        Identifiable object = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.clazz).add(Expression.eq(property, id));
            object = (Identifiable) crit.uniqueResult();
            tx.commit();
            HibernateUtil.closeSession();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
        if (object == null) {
            throw new org.cidrz.webapp.dynasite.exception.ObjectNotFoundException("An object with the property: " + property + " and the id: " + id + " was not found.");
        }
        return object;
    }
}
