/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.valueobject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @hibernate.class table="report_spec"
 * mutable="true"
 */
public class ReportSpec implements Identifiable {
    /**
     * Commons Logging instance.
     */
    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    private Long id;
    private String label;
    private String sqlQuery;
    private String colLabels;
    private String primaryCategoryColumn;
    private String secondaryCategoryColumn;

    /**
     * @hibernate.id unsaved-value="0"
     * generator-class="identity"
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @hibernate.property update="true"
     * insert="true"
     */
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @hibernate.property update="true"
     * insert="true"
     * @hibernate.column name="sql_query" sql-type="text"
     */
    public String getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    /**
     * @hibernate.property update="true"
     * column="col_labels"
     * insert="true"
     */
    public String getColLabels() {
        return colLabels;
    }

    public void setColLabels(String colLabels) {
        this.colLabels = colLabels;
    }

    /**
     * @hibernate.property update="true"
     * insert="true"
     * column="primary_category_column"
     */
    public String getPrimaryCategoryColumn() {
        return primaryCategoryColumn;
    }

    public void setPrimaryCategoryColumn(String primaryCategoryColumn) {
        this.primaryCategoryColumn = primaryCategoryColumn;
    }

    /**
     * @hibernate.property update="true"
     * insert="true"
     * column="secondary_category_column"
     */
    public String getSecondaryCategoryColumn() {
        return secondaryCategoryColumn;
    }

    public void setSecondaryCategoryColumn(String secondaryCategoryColumn) {
        this.secondaryCategoryColumn = secondaryCategoryColumn;
    }

    public ResultSet executeQuery() throws SQLException, PersistenceException {
        Context ctx = null;
        DataSource ds = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            ctx = new InitialContext();
            if (ctx == null) {
                log.error("No initial context.");
                throw new PersistenceException("No initial context");
            }
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/zeprsDB");

            if (ds != null) {
                conn = ds.getConnection();
                PreparedStatement ps = conn.prepareStatement(this.getSqlQuery());
                rs = ps.executeQuery();
            }
            return rs;
        } catch (Exception e) {
            log.error("Error running the report: " + e.getMessage());
            throw new PersistenceException(e);
        }
    }
}