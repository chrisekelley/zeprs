package org.apache.catalina.realm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.Principal;
import java.sql.*;
import java.util.ArrayList;


/**
 * Usage in server.xml:
 * <p/>
 * <Realm  className="org.apache.catalina.realm.UserGroupRoleRealm" debug="99"
 * driverName="com.mysql.jdbc.Driver"
 * connectionURL="jdbc:mysql://localhost/zeprs"
 * connectionName="root" connectionPassword="thePassword"
 * userTable="mail.accountuser" userNameCol="username" userCredCol="password"
 * groupTable="user_group_membership" userFK="username" groupPK="id"
 * userRoleTable="user_group_role" roleNameCol="role" groupFK="group_id" mysqlPassword="true"/>
 */
public class UserGroupRoleRealm extends RealmBase {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(UserGroupRoleRealm.class);

    private String groupTable;
    private String userFK;
    private String groupPK;
    private String groupFK;
    private String userNameCol;
    private String userTable;
    private String userCredCol;
    private String roleNameCol;
    private String userRoleTable;
    private String driverName;
    private String connectionURL;
    private String connectionName;
    private String connectionPassword;
    private String encryptionMethod;

    public String getDriverName() {
        return this.driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getConnectionURL() {
        return this.connectionURL;
    }

    public void setConnectionURL(String connectionURL) {
        this.connectionURL = connectionURL;
    }

    public String getConnectionPassword() {
        return this.connectionPassword;
    }

    public void setConnectionPassword(String connectionPassword) {
        this.connectionPassword = connectionPassword;
    }

    public String getConnectionName() {
        return this.connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    public String getUserNameCol() {
        return userNameCol;
    }

    public void setUserNameCol(String userNameCol) {
        this.userNameCol = userNameCol;
    }

    public String getUserTable() {
        return userTable;
    }

    public void setUserTable(String userTable) {
        this.userTable = userTable;
    }

    public String getUserCredCol() {
        return userCredCol;
    }

    public void setUserCredCol(String userCredCol) {
        this.userCredCol = userCredCol;
    }

    public String getRoleNameCol() {
        return roleNameCol;
    }

    public void setRoleNameCol(String roleNameCol) {
        this.roleNameCol = roleNameCol;
    }

    public String getUserRoleTable() {
        return userRoleTable;
    }

    public void setUserRoleTable(String userRoleTable) {
        this.userRoleTable = userRoleTable;
    }

    public String getUserSql() {
        return userSql;
    }

    public void setUserSql(String userSql) {
        this.userSql = userSql;
    }

    public String getRoleSql() {
        return roleSql;
    }

    public void setRoleSql(String roleSql) {
        this.roleSql = roleSql;
    }

    public boolean isMysqlPassword() {
        return mysqlPassword;
    }

    public void setMysqlPassword(boolean mysqlPassword) {
        this.mysqlPassword = mysqlPassword;
    }

    private boolean mysqlPassword = false;

    public String getGroupTable() {
        return groupTable;
    }

    public void setGroupTable(String groupTable) {
        this.groupTable = groupTable;
    }

    public String getUserFK() {
        return userFK;
    }

    public void setUserFK(String userFK) {
        this.userFK = userFK;
    }

    public String getGroupPK() {
        return groupPK;
    }

    public void setGroupPK(String groupPK) {
        this.groupPK = groupPK;
    }

    public String getGroupFK() {
        return groupFK;
    }

    public void setGroupFK(String groupFK) {
        this.groupFK = groupFK;
    }
    
    

    private String userSql = null;
    private String roleSql = null;
    
    private String userSql() {
        if (userSql == null) {
            userSql = "SELECT " + userNameCol + " FROM " + userTable + " WHERE " + userTable + "." + userNameCol + " = ? " + " AND " + userCredCol + " = password(?)";
            log.debug("role SQL:" + roleSql);
        }
        return userSql;
    }

/*SELECT role
FROM user_group_role,user_group_membership, mail.accountuser
WHERE mail.accountuser.username = ?
AND user_group_membership.id = mail.accountuser.username
AND user_group_membership.group_id = user_group_role.group_id;
*/
    private String roleSql() {
        if (roleSql == null) {
            roleSql = "SELECT " + roleNameCol + " FROM " + userRoleTable + "," + groupTable + "," + userTable + " WHERE " + userTable + "." + userNameCol + " = ? " + " AND " + groupTable + "." + userFK + " = " + userTable + "." + userNameCol + " AND " + groupTable + "." + groupFK + " = " + userRoleTable + "." + groupFK;
            log.debug("role SQL:" + roleSql);
        }
        return roleSql;
    }

    /**
     * Return the Principal associated with the specified username and
     * credentials, if there is one; otherwise return <code>null</code>.
     *
     * @param username    Username of the Principal to look up
     * @param credentials Password or other credentials to use in
     *                    authenticating this username
     */
    public synchronized Principal authenticate(String username, String credentials) {

        // log("username==null: " + (username==null || username.equals("")));
        // log("password==null: " + (credentials==null || credentials.equals("")));
        try {
            Class.forName(getDriverName());
        } catch (ClassNotFoundException e) {
            // log("Failed to load driver... " + e.getMessage());
        }

        Connection dbConnection = null;

        String dbCredentials = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            dbConnection = DriverManager.getConnection(getConnectionURL(), getConnectionName(), getConnectionPassword());
            // log("dbConnection==null: " + (dbConnection==null));
            // log("dbConnection.isClosed(): " + (dbConnection.isClosed()));
            dbConnection.setReadOnly(true);
            // log("preparing statement from sql: " + userSql());
            stmt = dbConnection.prepareStatement(userSql());
            // log("stmt==null: " + (stmt==null));
            stmt.setString(1, username);
            stmt.setString(2, credentials);
            // log("params loaded into stmt.");
            rs = stmt.executeQuery();
            // log("query executed, rs==null: " + (rs==null));

            if (!rs.next()) {
                return null; //no matching rows.
            }

            rs.close();
            rs = null;

            // Accumulate the user's roles
            ArrayList roleList = new ArrayList();
            // log("dbConnection==null: " + (dbConnection==null));
            // log("dbConnection.isClosed(): " + (dbConnection.isClosed()));
            // log("preparing statement from sql: " + roleSql());
            log.debug("preparing statement from roleSql: " + roleSql());
            stmt = dbConnection.prepareStatement(roleSql());
            // log("stmt==null: " + (stmt==null));
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            // log("query executed, rs==null: " + (rs==null));
            while (rs.next()) {
                String role = rs.getString(1);
                if (null != role && !role.trim().equals("")) {
                    roleList.add(role.trim());
                }
            }
            rs.close();
            rs = null;

            // Create and return a suitable Principal for this user
            Principal p = new GenericPrincipal(this, username, credentials, roleList);
            // log("returning a new pricipal. p==null: " + (p==null));
            // dbConnection.close();
            return (p);
        } catch (SQLException e) {
            log.error(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    log.error(sm.getString("jdbcUserGroupRealm.abnormalCloseResultSet"));
                }
            }
            try {
                // dbConnection.commit();
                dbConnection.close();
                log.debug("Closing connection");
            } catch (SQLException e) {
                log.error(e);
            }
        }
        return null;
    }

    protected String getName() {
        return this.getClass().getName();
    }

    protected String getPassword(String s) {
        return "";
    }

    /**
     * Return the Principal associated with the given user name.
     */
    protected Principal getPrincipal(String username) {
        return (null);
    }
}
