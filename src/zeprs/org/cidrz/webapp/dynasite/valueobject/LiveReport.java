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

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Nov 5, 2004
 * Time: 12:21:35 PM
 */

/**
 * @hibernate.class table="live_report"
 * mutable="true"
 */

public class LiveReport implements Identifiable, Auditable {

    private Long id;
    private String reportName;
    private Set fields;
    private AuditInfo auditInfo;

    /**
     * @return
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
     * @return
     * @hibernate.property column="report_name"
     */
    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }


    /**
     * @hibernate.component
     * class="org.cidrz.webapp.dynasite.valueobject.AuditInfo"
     */
    public AuditInfo getAuditInfo() {
        if (auditInfo == null) {
            auditInfo = new AuditInfo();
        }
        return auditInfo;
    }

    public void setAuditInfo(AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }

    /**
     * @return
     * @hibernate.set inverse="true"
     * lazy="false"
     * cascade="save-update"
     * @hibernate.key column="live_report_id"
     * @hibernate.one-to-many class="org.cidrz.webapp.dynasite.valueobject.LiveReportField"
     */

    public Set getFields() {
        return fields;
    }

    public void setFields(Set fields) {
        this.fields = fields;
    }
}
