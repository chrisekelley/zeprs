/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.project.zeprs.valueobject;

import org.cidrz.webapp.dynasite.valueobject.AuditInfo;
import org.cidrz.webapp.dynasite.valueobject.Auditable;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Dec 13, 2004
 * Time: 11:45:25 AM
 */

public class DrugRegimenType implements Identifiable, Auditable {

    private Long id;
    private String name;
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
     * @hibernate.property column="name"
     * not-null="false"
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
}
