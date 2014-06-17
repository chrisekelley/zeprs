/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.rti.zcore;

import java.io.Serializable;

import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.valueobject.AuditInfo;
import org.cidrz.webapp.dynasite.valueobject.Auditable;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.rti.zcore.dynasite.DynasiteFormat;

/**
 * Describes the form's domain: what does the form describe? Is it for a particular type of user, such as a mother or infant, or both?
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Jan 24, 2004
 * Time: 10:24:25 AM
 */

/**
 * @hibernate.class table="form_domain"  lazy="false"
 * mutable="true"
 */
public class FormDomain implements Identifiable, Auditable, Serializable, DynasiteFormat {

	private static final long serialVersionUID = 2698534238580165930L;
	private Long id;
    private String name;
    private String globalIdentifierName;
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
     * class="org.rti.zcore.AuditInfo"
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


    public String getGlobalIdentifierName() {
		return globalIdentifierName;
	}

	public void setGlobalIdentifierName(String globalIdentifierName) {
		this.globalIdentifierName = globalIdentifierName;
	}

	@Override
	@JsonIgnore
	public String getDynasiteFormat() {
		String dynasiteFormat = Constants.DYNASITE_FORMAT;
		return dynasiteFormat;
	}
}
