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
 * @hibernate.class table="live_report_field"
 * mutable="true"
 */

public class LiveReportField implements Identifiable, Auditable, Configuration {

    private Long id;
    private LiveReport liveReport;
    private Long liveReportId;
    private Form form;
    private Long formId;
    private PageItem pageItem;
    private Long pageItemId;
    private FormField form_field;
    private Long FormFieldId;
    private String reportProperty;
    private boolean shared;
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

    public Long getLiveReportId() {
        return liveReportId;
    }

    public void setLiveReportId(Long liveReportId) {
        this.liveReportId = liveReportId;
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

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    /**
     * @return
     * @hibernate.many-to-one column="page_item_id"
     * cascade="none"
     */
    public PageItem getPageItem() {
        return pageItem;
    }

    public void setPageItem(PageItem pageItem) {
        this.pageItem = pageItem;
    }

    public Long getPageItemId() {
        return pageItemId;
    }

    public void setPageItemId(Long pageItemId) {
        this.pageItemId = pageItemId;
    }

    /**
     * @return
     * @hibernate.many-to-one column="form_field_id"
     * cascade="none"
     */
    public FormField getForm_field() {
        return form_field;
    }

    public void setForm_field(FormField form_field) {
        this.form_field = form_field;
    }

    public Long getFormFieldId() {
        return FormFieldId;
    }

    public void setFormFieldId(Long formFieldId) {
        FormFieldId = formFieldId;
    }

    /**
     * @return
     * @hibernate.property column="report_property"
     */
    public String getReportProperty() {
        return reportProperty;
    }

    public void setReportProperty(String reportProperty) {
        this.reportProperty = reportProperty;
    }

    /**
     * @return
     * @hibernate.property column="shared"
     */
    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
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
