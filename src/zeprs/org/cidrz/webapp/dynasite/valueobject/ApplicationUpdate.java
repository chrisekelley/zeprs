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

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Logs updates to this application - this includes sql updates
 *
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Dec 28, 2005
 *         Time: 12:50:46 PM
 */

/**
 * @hibernate.class table="appupdate"
 * mutable="true"
 */
public class ApplicationUpdate {
    private Long id;
    private Long updateid;
    private Timestamp dateposted;
    private Timestamp dateinstalled;
    private String type;
    private String job;
    private String message;
    private Boolean logJob;

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
     * @return id from admin db record
     * @hibernate.property column="updateid"
     */
    public Long getUpdateid() {
        return updateid;
    }

    public void setUpdateid(Long updateid) {
        this.updateid = updateid;
    }

    /**
     * @return date this item was created on the master or dev system
     * @hibernate.property column="dateposted"
     * not-null="false"
     * unique="false"
     */
    public Timestamp getDateposted() {
        return dateposted;
    }

    public void setDateposted(Timestamp dateposted) {
        this.dateposted = dateposted;
    }

    /**
     * @return date this was installed on this system
     * @hibernate.property column="dateinstalled"
     * not-null="false"
     * unique="false"
     */
    public Timestamp getDateinstalled() {
        return dateinstalled;
    }

    public void setDateinstalled(Timestamp dateinstalled) {
        this.dateinstalled = dateinstalled;
    }

    /**
     * @return type of object to be replicated.  Types: S (SQL), W (war)
     * @hibernate.property column="type"
     * not-null="false"
     * unique="false"
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return job details - sql statement(s) or other
     * @hibernate.property column="job"
     * not-null="false"
     * unique="false"
     */

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    /**
     * @return message if any
     * @hibernate.property column="message"
     * not-null="false"
     * unique="false"
     */

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Used in sql form to indicate if job needs to be saved to the database. Notmally appupdates are automatically
     * logged; however the system can have jobs manually added.
     *
     * @return logJob
     */
    public Boolean getLogJob() {
        return logJob;
    }

    public void setLogJob(Boolean logJob) {
        this.logJob = logJob;
    }

}
