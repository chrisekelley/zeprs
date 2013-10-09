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

/**
 * @hibernate.class table="task"
 * mutable="true"
 */

public class Task implements Identifiable, Auditable, FlowOrderable {

    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    private Long id;
    private Long formId;
    private Long encounterId;
    private Long patientId;
    private Long pregnancyId;
    private boolean active = true;
    private boolean incomplete = false;
    private AuditInfo auditInfo;
    private Long flowId;
    private String label;
    private Integer maxSubmissions;
    private Integer submissions;
    private String className;
    private Integer sex;
    private String siteName;
    private String messageType;
    private Integer taskType;   // mother or infant task
    private Integer flowOrder;

    public Task() {
    }


    public Task(Long form_id, Long flow_id, String str_label) {
        formId = form_id;
        flowId = flow_id;
        label = str_label;
    }

    /**
     * used to create the form tasklist
     *
     * @param form_id
     * @param flow_id
     * @param str_label
     * @param time_created
     * @param max_submissions
     */
    public Task(Long form_id, Long flow_id, String str_label, AuditInfo time_created, Integer max_submissions) {
        formId = form_id;
        flowId = flow_id;
        label = str_label;
        auditInfo = time_created;
        submissions = new Integer("0");
        maxSubmissions = max_submissions;
    }

    /**
     * Use to create the encounter task list
     *
     * @param form_id
     * @param flow_id
     * @param str_label
     * @param time_created
     * @param lo_encounterId
     * @param max_submissions
     * @param class_name
     */
    public Task(Long form_id, Long flow_id, String str_label, AuditInfo time_created, Long lo_encounterId, Integer max_submissions, String class_name) {
        formId = form_id;
        flowId = flow_id;
        label = str_label;
        auditInfo = time_created;
        encounterId = lo_encounterId;
        submissions = new Integer("0");
        maxSubmissions = max_submissions;
        className = class_name;
    }

/*    public Task() {
        super();
    }*/

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
     * @hibernate.property column="active"
     */
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    /**
     * Some of the encounters may be incomplete
     *
     * @return
     * @hibernate.property column="incomplete"
     */
    public boolean isIncomplete() {
        return incomplete;
    }

    public void setIncomplete(boolean incomplete) {
        this.incomplete = incomplete;
    }

    /**
     * @return
     * @hibernate.property column="form_id"
     */

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    /**
     * @return
     * @hibernate.property column="encounter_id"
     */
    public Long getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(Long encounterId) {
        this.encounterId = encounterId;
    }

    /**
     * @return
     * @hibernate.property column="patient_id"
     */

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getPregnancyId() {
        return pregnancyId;
    }

    public void setPregnancyId(Long pregnancyId) {
        this.pregnancyId = pregnancyId;
    }

    /**
     * @return
     * @hibernate.property column="auditInfo"
     */

    public org.cidrz.webapp.dynasite.valueobject.AuditInfo getAuditInfo() {
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
     * @hibernate.property column="flow_id"
     */
    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    /**
     * @return
     * @hibernate.property column="label"
     */
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return
     * @hibernate.property column="submissions"
     */
    public Integer getSubmissions() {
        return submissions;
    }

    public void setSubmissions(Integer submissions) {
        this.submissions = submissions;
    }

    /**
     * @return
     * @hibernate.property column="max_submissions"
     */

    public Integer getMaxSubmissions() {
        return maxSubmissions;
    }

    public void setMaxSubmissions(Integer maxSubmissions) {
        this.maxSubmissions = maxSubmissions;
    }

    /**
     * @return
     * @hibernate.property column="class_name"
     */
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Integer getFlowOrder() {
        return flowOrder;
    }

    public void setFlowOrder(Integer flowOrder) {
        this.flowOrder = flowOrder;
    }

}
