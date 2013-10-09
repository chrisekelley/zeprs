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
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Mar 16, 2006
 *         Time: 6:07:41 PM
 */

/**
 * @hibernate.class table="archivelog"
 * mutable="true"
 */
public class ArchiveLog {

    private Long id;
    private Timestamp archived;
    private Long checksum;

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
     * @hibernate.property column="archived"
     */
    public Timestamp getArchived() {
        return archived;
    }

    public void setArchived(Timestamp archived) {
        this.archived = archived;
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
}
