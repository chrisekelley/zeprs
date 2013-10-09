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

/**
 * Interface for objects that have unique identifiers. This is primarily
 * a persistence concept.
 */
public interface Identifiable {
    public static final Long NEW = new Long(0);

    public Long getId();

    public void setId(Long id);
}
