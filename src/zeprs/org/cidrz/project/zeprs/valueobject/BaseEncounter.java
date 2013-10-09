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
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.rules.OutcomeException;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Nov 21, 2004
 * Time: 2:00:37 PM
 */


public interface BaseEncounter {

    public Long getId();

    public void setId(Long id);

    public Long getFormId();

    public void setFormId(Long formId);

    public Long getPatientId();

    public void setPatientId(Long patientId);

    public Long getPregnancyId();

    public void setPregnancyId(Long pregnancyId);

    public Date getDateVisit();

    public void setDateVisit(Date dateVisit);

    public Long getFlowId();

    public void setFlowId(Long flowId);

    public AuditInfo getAuditInfo();

    public void setAuditInfo(AuditInfo auditInfo);

    public void evaluate(Form formDef) throws OutcomeException;

    public Map getEncounterMap();

    public void setEncounterMap(Map encounterMap);

    public String getFormName();

    public void setFormName(String formName);

    public Long getSiteId();

    public void setSiteId(Long siteId);

    public Long getReferralId();

    public void setReferralId(Long referralId);

    // Recordables
    public Timestamp getLastModified();

    public void setLastModified(Timestamp lastModified);

    public Timestamp getCreated();

    public void setCreated(Timestamp created);

    public String getLastModifiedBy();

    public void setLastModifiedBy(String lastModifiedBy);

    public String getCreatedBy();

    public void setCreatedBy(String createdBy);

    public String getSiteName();

    public void setSiteName(String siteName);

    public String getCreatedByName();

    public void setCreatedByName(String createdByName);

    public String getLastModifiedByName();

    public void setLastModifiedByName(String lastModifiedByName);

	public String getUuid();

	public void setUuid(String uuid);

}
