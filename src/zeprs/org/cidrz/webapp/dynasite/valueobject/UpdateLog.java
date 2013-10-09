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

import java.sql.Timestamp;

/**
 * @hibernate.class table="updatelog"
 * mutable="true"
 */
public class UpdateLog {

    private Long id;
    private Timestamp updated;
    private Timestamp builddate;    // timestamp from rss file being imported.
    private Long checksum;
    private String site;
    private String comments;

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
     * @hibernate.property column="updated"
     */
    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }


    /**
     * @return
     * @hibernate.property column="builddate"
     */

    public Timestamp getBuilddate() {
        return builddate;
    }

    public void setBuilddate(Timestamp builddate) {
        this.builddate = builddate;
    }

    /**
     * @return
     * @hibernate.property column="checksum"
     */
    public Long getChecksum() {
        return checksum;
    }

    public void setChecksum(Long checksum) {
        this.checksum = checksum;
    }

    /**
     * @return
     * @hibernate.property column="site"
     */
    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    /**
     * @return
     * @hibernate.property column="comments"
     */
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
