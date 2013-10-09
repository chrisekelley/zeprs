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

import org.cidrz.webapp.dynasite.valueobject.AuditInfo;
import org.cidrz.webapp.dynasite.valueobject.Auditable;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;

import java.util.HashSet;
import java.util.Set;

/**
 * @hibernate.class table="user_group"
 * lazy="false"
 * mutable="true"
 */
public class UserGroup implements Identifiable, Auditable {
    private Long id;
    private String name;
    private Boolean active;
    private AuditInfo auditInfo;
    private Set users = new HashSet();
    private Set roles = new HashSet();

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
     * not-null="true"
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     * @hibernate.property column="active"
     */
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
     * @hibernate.set table="user_group_membership"
     * lazy="false"
     * cascade="all"
     * @hibernate.key column="group_id"
     * @hibernate.element column="id"
     * type="java.lang.String"
     */
    public Set getUsers() {
        return users;
    }

    public void setUsers(Set users) {
        this.users = users;
    }

    /**
     * @hibernate.set table="user_group_role"
     * lazy="false"
     * cascade="all"
     * @hibernate.key column="group_id"
     * @hibernate.element column="role"
     * type="java.lang.String"
     */
    public Set getRoles() {
        return roles;
    }

    public void setRoles(Set roles) {
        this.roles = roles;
    }

}
