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

public class Drugs implements Identifiable, Auditable {
    private Long id;
    private String name;
    private AuditInfo auditInfo;
    private Boolean inActive;
    private String teratogenic;

    /**
     * @return
     * @hibernate.id column="id"
     * unsaved-value="0"
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
     * @hibernate.property column="name"
     * not-null="true"
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
     * @hibernate.property column="inactive"
     */

    public Boolean getInActive() {
        return inActive;
    }

    public void setInActive(Boolean inActive) {
        this.inActive = inActive;
    }

    /**
     * @return
     * @hibernate.property column="teratogenic"
     */

    public String getTeratogenic() {
        return teratogenic;
    }

    public void setTeratogenic(String teratogenic) {
        this.teratogenic = teratogenic;
    }
}
