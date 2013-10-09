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

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jul 7, 2005
 *         Time: 4:13:27 PM
 */
public interface BaseRecord {

    public Long getId();

    public void setId(Long id);

    public Long getPatientId();

    public void setPatientId(Long patientId);

    public Long getPregnancyId();

    public void setPregnancyId(Long pregnancyId);

    public Date getDateVisit();

    public void setDateVisit(Date dateVisit);

    public AuditInfo getAuditInfo();

    public void setAuditInfo(AuditInfo auditInfo);

    public Timestamp getLastModified();

    public void setLastModified(Timestamp lastModified);


    public Timestamp getCreated();

    public void setCreated(Timestamp created);

    public String getCreatedBy();

    public void setCreatedBy(String createdBy);

    public Long getSiteId();

    public void setSiteId(Long siteId);

    public Long getImportId();

    public void setImportId(Long importId);

    String getLastModifiedBy();

    void setLastModifiedBy(String lastModifiedBy);
}
