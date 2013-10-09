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

import java.io.Serializable;
import java.sql.Timestamp;

public final class AuditInfo implements Serializable {
    private Timestamp lastModified;
    private Timestamp created;
    private User lastModifiedBy;
    private User createdBy;
    private Site site;
    private String siteName;

    public AuditInfo() {
    }

    public AuditInfo(Timestamp lastModified, Timestamp created, User lastModifiedBy, User createdBy, Site site, String siteName) {
        this.lastModified = lastModified;
        this.created = created;
        this.lastModifiedBy = lastModifiedBy;
        this.createdBy = createdBy;
        this.site = site;
        this.siteName = siteName;
    }

    /**
     * @hibernate.property column="last_modified"
     */
    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * @hibernate.property column="created"
     */
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    /**
     * @return
     * @hibernate.many-to-one column="last_modified_by" class="org.cidrz.webapp.dynasite.valueobject.User"
     */

    public User getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(User lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * @return
     * @hibernate.many-to-one column="created_by" class="org.cidrz.webapp.dynasite.valueobject.User"
     */

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }


    /**
     * @return
     * @hibernate.many-to-one column="site_id" class="org.cidrz.webapp.dynasite.valueobject.Site"
     */
    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

}

