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
 * This class manages what site this server instance publishes itself as.
 * This choice affects the query that generates the rss patient list.
 * These settings are stored in publisher.xml.
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Mar 20, 2006
 *         Time: 1:37:06 PM
 */
public class Publisher {

    private Long siteId;
    private Site site;
    private Timestamp dateModified;
    private String url;
    private Boolean redirectLocal;

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }


    public Timestamp getDateModified() {
        return dateModified;
    }

    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getRedirectLocal() {
        return redirectLocal;
    }

    public void setRedirectLocal(Boolean redirectLocal) {
        this.redirectLocal = redirectLocal;
    }
}
