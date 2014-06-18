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

import org.cidrz.project.zeprs.valueobject.gen.UserInfo;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.Staff;
import org.cidrz.webapp.dynasite.valueobject.UserGroup;
import org.cidrz.webapp.dynasite.valueobject.UserPermissions;

import javax.servlet.ServletException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jun 22, 2005
 *         Time: 12:20:17 PM
 */
public class UserDAO {

    /**
     * Checks if user is assigned to a group.
     *
     * @param conn
     * @param username
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException
     *
     */
    public static Object getUserGroup(Connection conn, String username) throws SQLException, ServletException, ObjectNotFoundException {
        Object result = null;
        ArrayList values = new ArrayList();
        String sql = "select m.group_id AS groupId " +
                "from user_group_membership m " +
                "where m.id=?";
        values.add(username);
        result = DatabaseUtils.getBean(conn, UserGroup.class, sql, values);
        return result;
    }
    
    /**
     * Checks if user exists
     * @param conn
     * @param username
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     * @should return an Object
     */
    public static Object getUser(Connection conn, String username) throws SQLException, ServletException, ObjectNotFoundException {
    	Object result = null;
    	ArrayList values = new ArrayList();
    	String sql = "SELECT " + Constants.USERINFO_USERNAME + " AS username " +
    	"FROM " + Constants.USERINFO_TABLE + " " +
    	"WHERE " + Constants.USERINFO_USERNAME + "=?";
    	values.add(username);
    	result = DatabaseUtils.getBean(conn, UserInfo.class, sql, values);
    	return result;
    }

    /**
     * get all permissions
     * unused
     *
     * @param sortOrder
     * @param offset
     * @param rowCount
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static Object getAllPerms(Connection conn, String sortOrder, int offset, int rowCount) throws SQLException, ServletException, ObjectNotFoundException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        String sql = "select u.nickname AS id, u.firstname AS firstName, u.lastname AS lastName, a.pa_role_name_fk " +
                "from userdata.address u " +
                "LEFT JOIN userdata.account_permissions a ON u.nickname = a.pa_username_fk" +
                "ORDER BY " + sortOrder +
                "LIMIT " + offset + "," + rowCount + ";";
        list = DatabaseUtils.getList(conn, Staff.class, sql, values);
        return list;
    }

    /**
     * Fetches credentials for user
     * @param conn
     * @param username
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static List getSearchResults(Connection conn, String username) throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        String sql = "select u.nickname AS id, u.firstname AS firstName, u.lastname AS lastName, m.group_id AS groupId, " +
                "g.name AS groupName " +
                "FROM userdata.address u " +
                "LEFT JOIN user_group_membership m ON m.id = u.nickname " +
                "LEFT JOIN user_group g ON m.group_id = g.id " +
                "WHERE u.nickname LIKE '%" + username + "%';";

        list = DatabaseUtils.getList(conn, Staff.class, sql, values);
        return list;
    }

    /**
     * Fetches list of users
     * @param conn
     * @param sortOrder
     * @param offset
     * @param rowCount
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static List getAll(Connection conn, String sortOrder, int offset, int rowCount) throws SQLException, ServletException, ObjectNotFoundException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        String sql = "select u.nickname AS id, u.firstname AS firstName, u.lastname AS lastName, m.group_id AS groupId, " +
                "g.name AS groupName " +
                "FROM userdata.address u " +
                "LEFT JOIN user_group_membership m ON m.id = u.nickname " +
                "LEFT JOIN user_group g ON m.group_id = g.id " +
                "ORDER BY " + sortOrder +
                " LIMIT " + offset + "," + rowCount + ";";
        list = DatabaseUtils.getList(conn, Staff.class, sql, values);
        return list;
    }

    /**
     * Fetches list of groups
     * @param conn
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static List getAllGroups(Connection conn) throws SQLException, ServletException, ObjectNotFoundException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        String sql = "select * " +
                "FROM user_group " +
                "WHERE active=true " +
                "AND id !=1";
        list = DatabaseUtils.getList(conn, UserGroup.class, sql, values);
        return list;
    }

    /**
     * Fetches group for username
     * unused
     * @param conn
     * @param username
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static Object getPermissions(Connection conn, String username) throws SQLException, ServletException, ObjectNotFoundException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        String sql = "SELECT m.id AS username, m.group_id AS groupId, g.name AS groupName " +
                "from user_group_membership m, user_group g " +
                "WHERE m.group_id = g.id " +
                "AND m.id=?";
        values.add(username);
        list = DatabaseUtils.getList(conn, UserPermissions.class, sql, values);
        return list;
    }

    public static Object insertGroup(Connection conn, String username, Long group) throws SQLException, ServletException {
        ArrayList values = new ArrayList();
        values.add(username);
        values.add(group);
        String sql = "INSERT INTO user_group_membership set id=?, group_id=?";
        Long encId = (Long) DatabaseUtils.create(conn, sql, values.toArray());
        return encId;
    }
    
    public static void insertUser(Connection conn, String username, String firstname, String lastname, 
    		String email, String mobile, String phone) throws SQLException, ServletException {
        ArrayList values = new ArrayList();
        values.add(username);
        values.add(firstname);
        values.add(lastname);
        values.add(email);
        values.add(mobile);
        values.add(phone);
        String sql = "INSERT INTO userdata.address set nickname=?, firstname=?, lastname=?, "
        		+ "email=?, ad_mobile=?, ad_phone=? ";
        DatabaseUtils.save(conn, sql, values.toArray());
    }
    
    public static void insertPassword(Connection conn, String username, String password) throws SQLException, ServletException {
        ArrayList values = new ArrayList();
        values.add(username);
        values.add(password);
        String sql = "INSERT INTO mail.accountuser set username=?, password=password(?)";
        DatabaseUtils.save(conn, sql, values.toArray());
    }

    public static Object updateGroup(Connection conn, String username, Long group) throws SQLException, ServletException {
        ArrayList values = new ArrayList();
        values.add(group);
        values.add(username);
        String sql = "UPDATE user_group_membership set group_id=? WHERE id=?";
        Long encId = (Long) DatabaseUtils.create(conn, sql, values.toArray());
        return encId;
    }

    public static Object getGroup(Connection conn, Long groupId) throws SQLException, ServletException, ObjectNotFoundException {
        Object result = null;
        ArrayList values = new ArrayList();
        String sql = "select * " +
                "FROM user_group " +
                "WHERE id=?";
        values.add(groupId);
        result = DatabaseUtils.getBean(conn, UserGroup.class, sql, values);
        return result;
    }
}