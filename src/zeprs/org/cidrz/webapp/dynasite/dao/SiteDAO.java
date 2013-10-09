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

import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Site;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jun 22, 2005
 *         Time: 11:23:04 AM
 */
public class SiteDAO {
	/**
	 * Fetches a site object
	 * @param conn
	 * @param siteId
	 * @param criteria - by id or site_apha_id
	 * @return Site object
	 * @throws SQLException
	 * @throws ServletException
	 * @throws ObjectNotFoundException
	 */
    public static Object getOne(Connection conn, Long siteId, String criteria) throws SQLException, ServletException, ObjectNotFoundException {
        Object result = null;
        ArrayList values = new ArrayList();
        String sql = null;
        if (criteria.equals("id")) {
        	sql = "SELECT s.id, s.site_alpha_id AS siteAlphaId, s.district_id AS districtId, s.abbrev AS abbreviation, " +
            "s.site_name AS name, s.site_type_id as siteTypeId, s.inactive AS inactive " +
            "FROM site s " +
            "WHERE s.id=?";
        } else {
        	sql = "SELECT s.id, s.site_alpha_id AS siteAlphaId, s.district_id AS districtId, s.abbrev AS abbreviation, " +
            "s.site_name AS name, s.site_type_id as siteTypeId, s.inactive AS inactive " +
            "FROM site s " +
            "WHERE s.site_alpha_id=?";
        }
        values.add(siteId);
        result = DatabaseUtils.getBean(conn, Site.class, sql, values);
        return result;
    }

    /**
     * Gets site by abbreviation (e.g. "AIR")
     * @param conn
     * @param siteAbbrev
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static Object getOne(Connection conn, String siteAbbrev) throws SQLException, ServletException, ObjectNotFoundException {
    	Object result = null;
    	ArrayList values = new ArrayList();
    	String sql = "SELECT s.id, s.site_alpha_id AS siteAlphaId, s.district_id AS districtId, s.abbrev AS abbreviation, " +
    		"s.site_name AS name, s.site_type_id as siteTypeId, s.inactive AS inactive " +
    		"FROM site s " +
    		"WHERE s.abbrev=?";
    	values.add(siteAbbrev);
    	result = DatabaseUtils.getBean(conn, Site.class, sql, values);
    	return result;
    }

    /**
     * @return List of clinics excluding "ZEPRS master" and "LIMS" (lab results import form LIMS system)
     * @throws SQLException
     * @throws ServletException
     * @param conn
     */
    public static List getClinics(Connection conn) throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "SELECT s.id, s.site_alpha_id AS siteAlphaId, s.district_id AS districtId, s.abbrev AS abbreviation, " +
                "s.site_name AS name, s.site_type_id as siteTypeId, s.inactive AS inactive " +
                "FROM site s " +
                "WHERE district_id='5040' " +
                "AND site_alpha_id != 'MST'" +
                "AND site_alpha_id != 'LIM'" +
                "ORDER BY name";
        list = DatabaseUtils.getList(conn, Site.class, sql, values);
        return list;
    }

    /** removed  "GROUP BY siteAlphaId " + so that UTH sites could share siteAlphaId
     * Do not use this if you need a list of clinics only.
     * includes ZEPRS master
     * @return List of objects
     * @throws SQLException
     * @throws ServletException
     * @param conn
     */
    public static List getSites(Connection conn) throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "SELECT s.id, s.site_alpha_id AS siteAlphaId, s.district_id AS districtId, s.abbrev AS abbreviation, " +
                "s.site_name AS name, s.site_type_id as siteTypeId, s.inactive AS inactive " +
                "FROM site s " +
                "WHERE district_id='5040' " +
                "ORDER BY name";
        list = DatabaseUtils.getList(conn, Site.class, sql, values);
        return list;
    }

    /**
     * List of objects, ordered
     * unused
     * @param order
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static List getAll(Connection conn, String order) throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "SELECT s.id, s.site_alpha_id AS siteAlphaId, s.district_id AS districtId, s.abbrev AS abbreviation, " +
                "s.site_name AS name, s.site_type_id as siteTypeId, s.inactive AS inactive " +
                "FROM site s " +
                "ORDER BY ?";
        values.add(order);
        list = DatabaseUtils.getList(conn, Site.class, sql, values);
        return list;
    }

    /**
     * List all active sites
     * @param conn
     * @return list of Site objects
     * @throws SQLException
     * @throws ServletException
     */
    public static List getAllActive(Connection conn) throws SQLException, ServletException {
    	List list = new ArrayList();
    	ArrayList values = new ArrayList();
    	values = new ArrayList();
    	String sql = "SELECT DISTINCT site_id AS id FROM zeprs.encounter;";
    	list = DatabaseUtils.getList(conn, Site.class, sql, values);
    	return list;
    }



}
