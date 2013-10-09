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

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.AuditInfoBeanProcessor;
import org.cidrz.webapp.dynasite.utils.ZEPRSRowProcessor;
import org.cidrz.webapp.dynasite.utils.HibernateUtil;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;
import org.cidrz.webapp.dynasite.valueobject.Task;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome;

import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Fetch list of referrals
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: May 26, 2005
 * Time: 1:23:12 PM
 */
public class TaskListDAO {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(TaskListDAO.class);

    /**
     * Create a task for each encounter
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @param flowId
     * @return list
     */
    public static List getEncounterTasks(Connection conn, Long patientId, Long pregnancyId, Long flowId) {
        List all_encounters = null;
        ArrayList values;
        String sql;
        values = new ArrayList();
        if (flowId == 2) {  // history needs to show records from prev. pregnancies
            sql = "select 'Encounter' AS label, e.form_id AS formId, e.patient_id AS patientId, '100' AS maxSubmissions, e.id AS encounterId, " +
                    "u.firstName AS 'auditInfo.lastModifiedBy.firstName', " +
                    "u.lastName AS 'auditInfo.lastModifiedBy.lastName', " +
                    "e.last_modified AS 'auditInfo.lastModified', " +
                    "s.site_name AS siteName " +
                   // "f.name AS className " +
                    "from encounter e " +
                    "LEFT JOIN (userdata.address u) ON (e.last_modified_by=u.nickname) " +
                    "LEFT JOIN (site s) ON (e.site_id = s.id) " +
                    "where e.patient_id=? " +
                    "and e.flow_id=? " +
                  //  "and is_enabled=true " +
                    "and e.form_id !=55 " +
                    "and e.form_id !=77 " +
                    "and e.form_id !=82 ";
            values.add(new Integer(patientId.intValue()));
            values.add(new Integer(flowId.intValue()));
        } else {
            sql = "select 'Encounter' AS label, e.form_id AS formId, e.patient_id AS patientId, '100' AS maxSubmissions, e.id AS encounterId, " +
                    "u.firstName AS 'auditInfo.lastModifiedBy.firstName', " +
                    "u.lastName AS 'auditInfo.lastModifiedBy.lastName', " +
                    "e.last_modified AS 'auditInfo.lastModified', " +
                    "s.site_name AS siteName " +
                   // "f.name AS className " +
                    "from encounter e " +
                     "LEFT JOIN (userdata.address u) ON (e.last_modified_by=u.nickname) " +
                    "LEFT JOIN (site s) ON (e.site_id = s.id) " +
                    "where e.patient_id=? " +
                    "and e.pregnancy_id=? " +
                    "and e.flow_id=? ";
                   // "and is_enabled=true";
            values.add(new Integer(patientId.intValue()));
            values.add(new Integer(pregnancyId.intValue()));
            values.add(new Integer(flowId.intValue()));
        }

        BeanProcessor beanprocessor = new AuditInfoBeanProcessor();
        RowProcessor convert = new ZEPRSRowProcessor(beanprocessor);
        try {
            all_encounters = DatabaseUtils.getList(conn, Task.class, sql, values, convert);
        } catch (ServletException e) {
            log.error(e);
        } catch (SQLException e) {
            log.error(e);
        }
        for (int i = 0; i < all_encounters.size(); i++) {
            Task task = (Task) all_encounters.get(i);
            Form form = (Form) DynaSiteObjects.getForms().get(task.getFormId());
            if (form != null) {
            	task.setLabel(form.getLabel());
            }            
        }
        return all_encounters;
    }

    public static List getEncounterTasksHistory(Connection conn, Long patientId, Long pregnancyId) {
        List all_encounters = null;
        ArrayList values;
        String sql;
        values = new ArrayList();
            sql = "select 'Encounter' AS label, e.form_id AS formId, e.patient_id AS patientId, '100' AS maxSubmissions, e.id AS encounterId, " +
                    "u.firstName AS 'auditInfo.lastModifiedBy.firstName', " +
                    "u.lastName AS 'auditInfo.lastModifiedBy.lastName', " +
                    "e.last_modified AS 'auditInfo.lastModified', " +
                    "s.site_name AS siteName " +
                  //  "f.name AS className " +
                    "from encounter e " +
                    "LEFT JOIN (userdata.address u) ON (e.last_modified_by=u.nickname) " +
                    "LEFT JOIN (site s) ON (e.site_id = s.id) " +
                    "where e.patient_id=? " +
                    "and e.pregnancy_id=? " +
                    "and e.flow_id=2 " +
                  //  "and is_enabled=true " +
                    "and (e.form_id =55 " +
                    "OR e.form_id =77 " +
                    "OR e.form_id =82) ";
            values.add(new Integer(patientId.intValue()));
            values.add(new Integer(pregnancyId.intValue()));
        BeanProcessor beanprocessor = new AuditInfoBeanProcessor();
        RowProcessor convert = new ZEPRSRowProcessor(beanprocessor);
        try {
            all_encounters = DatabaseUtils.getList(conn, Task.class, sql, values, convert);
        } catch (ServletException e) {
            log.error(e);
        } catch (SQLException e) {
            log.error(e);
        }
        for (int i = 0; i < all_encounters.size(); i++) {
            Task task = (Task) all_encounters.get(i);
            Form form = (Form) DynaSiteObjects.getForms().get(task.getFormId());
            task.setLabel(form.getLabel());
        }
        return all_encounters;
    }

    /**
     * Creates list of tasks by querying if newborn eval (form id 23) has been submitted.
     * @deprecated
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @return
     */
    public static List createChildrenAsTasks(Connection conn, Long patientId, Long pregnancyId) {
        ArrayList values;
        String sql;
        List children = new ArrayList();
        values = new ArrayList();
        sql = "select p.id AS patientId, CONCAT_WS(' ',first_name, surname, 'Newborn Evaluation') AS label, " +
                "'23' AS formId, '0' AS encounterId, false AS active, '3' AS flowId, " +
                "sex, e.id AS encounterId, " +
                "u.firstName AS 'auditInfo.lastModifiedBy.firstName', " +
                "u.lastName AS 'auditInfo.lastModifiedBy.lastName', " +
                "p.last_modified AS 'auditInfo.lastModified', " +
                "s.site_name AS siteName " +
                "FROM  encounter e\n" +
                "LEFT JOIN patient p ON e.patient_id=p.id\n" +
                "LEFT JOIN userdata.address u ON p.last_modified_by=u.nickname\n" +
                "LEFT JOIN site s ON e.site_id = s.id\n" +
                "where e.form_id=23 " +
                "and p.parent_id=? " +
                "and p.pregnancy_id=?";
        values.add(patientId.intValue());
        values.add(pregnancyId.intValue());
        BeanProcessor beanprocessor = new AuditInfoBeanProcessor();
        RowProcessor convert = new ZEPRSRowProcessor(beanprocessor);
        try {
            children = DatabaseUtils.getList(conn, Task.class, sql, values, convert);
        } catch (ServletException e) {
            log.error(e);
        } catch (SQLException e) {
            log.error(e);
        }
        return children;
    }
    
    /**
     * Creates task if newborn eval (form id 23) has been submitted.
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @return
     * @throws ObjectNotFoundException 
     */
    public static Task getNewbornTask(Connection conn, Long patientId) throws ObjectNotFoundException {
    	ArrayList values;
    	String sql;
    	Task task = null;
    	values = new ArrayList();
    	sql = "select p.id AS patientId, CONCAT_WS(' ',first_name, surname, 'Newborn Evaluation') AS label, " +
    	"'23' AS formId, '0' AS encounterId, false AS active, '3' AS flowId, " +
    	"sex, e.id AS encounterId, " +
    	"u.firstName AS 'auditInfo.lastModifiedBy.firstName', " +
    	"u.lastName AS 'auditInfo.lastModifiedBy.lastName', " +
    	"p.last_modified AS 'auditInfo.lastModified', " +
    	"s.site_name AS siteName " +
    	"FROM  encounter e\n" +
    	"LEFT JOIN patient p ON e.patient_id=p.id\n" +
    	"LEFT JOIN userdata.address u ON p.last_modified_by=u.nickname\n" +
    	"LEFT JOIN site s ON e.site_id = s.id\n" +
    	"where e.form_id=23 " +
    	"and p.id=?";
    	values.add(patientId.intValue());
    	BeanProcessor beanprocessor = new AuditInfoBeanProcessor();
    	RowProcessor convert = new ZEPRSRowProcessor(beanprocessor);
    	try {
    		task = (Task) DatabaseUtils.getZEPRSBean(conn, Task.class, sql, values);
    		//(conn, Task.class, sql, values, convert);
    	} catch (ServletException e) {
    		log.error(e);
    	} catch (SQLException e) {
    		log.error(e);
    	}
    	return task;
    }

    /**
     * get Forms For Task
     * unused
     * @param conn
     * @param flowId
     * @param problemLabourFormdone
     * @param isMother
     * @return
     */
    public static List getFormsForTask(Connection conn, Long flowId, boolean problemLabourFormdone, Boolean isMother) {
        
        int formType;
        List all_forms = null;
        ArrayList values = new ArrayList();
        String sql = "";
        if (flowId.equals(Long.valueOf("7")) && problemLabourFormdone == false) {
            sql = "select f.label AS label, f.id AS formId, f.max_submissions AS maxSubmissions from form f where f.id=65 ORDER BY flow_order";
            // all_forms = TaskListQuery.getProblemLabourTask();
        } else {
            sql = "select f.label AS label, f.id AS formId, f.max_submissions AS maxSubmissions from form f where is_enabled=true and flow_id=? and (form_type_id=? or form_type_id=4) ORDER BY flow_order";
            if (!isMother.equals(Boolean.TRUE)) {
                // select infant-only forms
                formType = 2;
            } else {
                formType = 1;
            }
            values.add(new Integer(flowId.intValue()));
            values.add(new Integer(formType));
        }
        try {
            all_forms = DatabaseUtils.getList(conn, Task.class, sql, values);
        } catch (ServletException e) {
            log.error(e);
        } catch (SQLException e) {
            log.error(e);
        }
        return all_forms;
    }
    /**
     * get list of all forms, excluding routine antenatal and puerperal forms - we don't want to display those.
     * unused
     * @param flowId
     * @return list
     * @deprecated Joins w/ flow, which is now in admin db.
     */
     public static List getPreviousForms(Connection conn, Long flowId) {
        List all_forms = null;
        ArrayList values = new ArrayList();
        String sql = "";
            sql = "select f.label AS label, f.id AS formId, f.max_submissions AS maxSubmissions " +
                    "from form f, flow fl " +
                    "where is_enabled=true " +
                    "and f.flow_id = fl.id " +
                    "and fl.flow_order<=? " +
                    "order by fl.flow_order, f.flow_order";
            values.add(new Integer(flowId.intValue()));
        try {
            all_forms = DatabaseUtils.getList(conn, Task.class, sql, values);
        } catch (ServletException e) {
            log.error(e);
        } catch (SQLException e) {
            log.error(e);
        }
        return all_forms;
    }

    /**
     * Fetches all forms that meet the params and plugs them into a list of tasks
     * unused
     * @param flowId
     * @param formTypeId
     * @return
     * @throws ServletException
     * @throws SQLException
     */
    public static List getList(int flowId, int formTypeId) throws ServletException, SQLException {

        /**
         * Commons Logging instance.
         */

        Log log = LogFactory.getFactory().getInstance(TaskListDAO.class);

        String sql = "";

        DataSource dataSource = null;
        dataSource = DatabaseUtils.getZEPRSDataSource();
        QueryRunner run = new QueryRunner(dataSource);
        ResultSetHandler h = new BeanListHandler(Task.class);
        ArrayList values = new ArrayList();
        if (formTypeId == 0) {
            sql = "select f.label AS label, f.id AS formId, f.max_submissions AS maxSubmissions from form f where is_enabled=true and flow_id=?";
            values.add(new Integer(flowId));
        } else {
            sql = "select f.label AS label, f.id AS formId, f.max_submissions AS maxSubmissions from form f where is_enabled=true and flow_id=? and (form_type_id=? or form_type_id=4)";
            values.add(new Integer(flowId));
            values.add(new Integer(formTypeId));
        }

        // return the results in a new Task object generated by the BeanHandler.
        List taskList = (List) run.query(sql, values.toArray(), h);
        return taskList;
    }

    /**
     * Fetches the Problem/Labour form
     * unused
     * @return a list w/ a single problem/labour task
     * @throws ServletException
     * @throws SQLException
     */
    public static List getProblemLabourTask() throws ServletException, SQLException {

        /**
         * Commons Logging instance.
         */

        Log log = LogFactory.getFactory().getInstance(TaskListDAO.class);

        String sql = "";

        DataSource dataSource = null;
        dataSource = DatabaseUtils.getZEPRSDataSource();
        QueryRunner run = new QueryRunner(dataSource);
        ResultSetHandler h = new BeanListHandler(Task.class);
        ArrayList values = new ArrayList();
        sql = "select f.label AS label, f.id AS formId, f.max_submissions AS maxSubmissions from form f where f.id=65";
        // return the results in a new Task object generated by the BeanHandler.
        List taskList = (List) run.query(sql, values.toArray(), h);
        return taskList;
    }

    /**
     * Fetch the most recent active referral
     *
     * @param patientId
     * @param active
     * @return
     * @throws org.cidrz.webapp.dynasite.exception.PersistenceException
     *
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException
     *
     */
    public static Identifiable getMostRecentReferral(Long patientId, boolean active) throws PersistenceException, ObjectNotFoundException {
        Identifiable object = null;
        Boolean status = Boolean.valueOf(active);
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria crit = session.createCriteria(ReferralOutcome.class)
                    .add(Expression.eq("patientId", patientId))
                    .add(Expression.eq("active", status))
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
            throw new ObjectNotFoundException();
        }
        return object;
    }
}
