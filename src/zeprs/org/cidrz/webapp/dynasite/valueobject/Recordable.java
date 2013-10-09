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
 *         Date: Jun 21, 2005
 *         Time: 10:30:36 PM
 */
public interface Recordable {
    Timestamp getLastModified();

    void setLastModified(Timestamp lastModified);

    Timestamp getCreated();

    void setCreated(Timestamp created);

    String getLastModifiedBy();

    void setLastModifiedBy(String lastModifiedBy);

    String getCreatedBy();

    void setCreatedBy(String createdBy);

    Long getSiteId();

    void setSiteId(Long siteId);
}
