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


/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Nov 5, 2004
 * Time: 12:21:35 PM
 */

/**
 * @hibernate.class table="live_report_form"
 * mutable="true"
 */

public class LiveReportForm implements Identifiable, Auditable {

    private Long id;
    private LiveReport liveReport;
    private Form form;
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
     * @hibernate.many-to-one column="live_report_id"
     * cascade="none"
     */
    public LiveReport getLiveReport() {
        return liveReport;
    }

    public void setLiveReport(LiveReport liveReport) {
        this.liveReport = liveReport;
    }

    /**
     * @return
     * @hibernate.many-to-one column="form_id"
     * cascade="none"
     */
    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
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
}
