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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.HibernateUtil;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome;

import javax.servlet.ServletException;
import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;
import java.sql.*;

/**
 * Fetch list of referrals
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: May 26, 2005
 * Time: 1:23:12 PM
 */
public class ReferralDAO {

    public static Result getList(Connection conn, String sortOrder, int offset, int rowCount) throws ServletException {

        /**
         * Commons Logging instance.
         */

        Log log = LogFactory.getFactory().getInstance(ReferralDAO.class);
        ResultSet rs = null;
        Result results = null;
        String sql = "";
        try {
            sql = "SELECT p.id, p.first_name, p.surname, o.id AS referralId, o.encounter_id, o.reason, " +
                    "o.priority_of_referral, o.transport, o.last_modified, o.uth_admission_date, o.arrival_condition, " +
                    "o.created, o.uth_ward_id, o.referring_site_id, o.firm, s.site_name, " +
                    "false_labour, true_labor, rupture_of_membranes, intact_membranes, preeclamp_hypert_1265, premature_labour, " +
                    "malaria_diag, anaemia, high_bp_diag, vaginal_bleeding_diag, intrauterine_death, uti_diag, pneumonia_diag, " +
                    "tb_diag, vaginal_thrush_diag, oral_thrush_diag, eclampsia, abruptia_placenta, miscarriage, diag_other, " +
                    "broken_episiotomy, puerperal_sepsis, breast_engorgement, secondary_pph, mastitis, breast_abscess, " +
                    "bowel_obstruction, indigestion, opthalmia_neonatorum, dehydration, umbilical_infection, diarrhoea\n " +
                    "FROM outcome o\n" +
                    "INNER JOIN (patient p) ON o.patient_id = p.id\n" +
                    "INNER JOIN (site s) ON o.referring_site_id = s.id\n" +
                    "LEFT JOIN (referral_reasons r) ON o.encounter_id = r.encounter_id\n" +
                    "WHERE o.outcome_type = 'referral'\n" +
                    "AND o.active = true " +
                    "ORDER BY o.created DESC ";
            sql = sql +  "LIMIT " + offset + "," + rowCount +";";
            Statement s = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            rs = s.executeQuery(sql);
            results = ResultSupport.toResult(rs);
            rs.close();
        } catch (Exception ex) {
            log.info("Referral sql: " + sql);
            log.error(ex);
            throw new ServletException("Cannot retrieve results:", ex);
        }
        return results;
    }


    /**
     * Fetch the most recent active referral
     * unused
     * @param patientId
     * @param active
     * @return
     * @throws PersistenceException
     * @throws ObjectNotFoundException
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

    /**
     * Get number of referrals during the time period.
     * @param beginDate
     * @param endDate
     * @param conn
     * @return
     * @throws ServletException
     */
    public static int getReferralCount(Date beginDate, Date endDate, Connection conn) throws ServletException {
        ResultSet rs = null;
        int count = 0;
        try {

            String sql = "SELECT COUNT(id) FROM outcome " +
                    "WHERE outcome_type = 'referral' " +
                    "AND DATE(created) >=? AND DATE(created) <=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, beginDate);
            ps.setDate(2, endDate);
            rs = ps.executeQuery();

            while (rs.next()) {
                 count =  rs.getInt(1);
            }
            rs.close();
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
        return count;
    }
}
