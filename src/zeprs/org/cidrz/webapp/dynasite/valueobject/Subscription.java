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
 * Servers may subscribe to an rss file published by each site.
 * This is used to manage the sites that this server subscribes to.
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Mar 20, 2006
 *         Time: 11:35:27 AM
 */

/**
 * @hibernate.class table="subscription"  lazy="true"
 * mutable="true"
 */
public class Subscription {

    private Long id;
    private String url;
    private Timestamp datesubscribed;
    private Boolean enabled;
    private String site;

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
     * @hibernate.property column="url"
     */
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return
     * @hibernate.property column="datesubscribed"
     */
    public Timestamp getDatesubscribed() {
        return datesubscribed;
    }

    public void setDatesubscribed(Timestamp datesubscribed) {
        this.datesubscribed = datesubscribed;
    }


    /**
     * @return
     * @hibernate.property column="enabled"
     */
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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
}
