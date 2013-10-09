/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.utils;

import org.cidrz.webapp.dynasite.valueobject.Recordable;

import java.util.Comparator;

public class LastModifiedComparator implements Comparator {
    public int compare(Object o, Object o1) {
        try {
            Recordable e, e1;
            e = (Recordable) o;
            e1 = (Recordable) o1;

            if (e.getLastModified().getTime() > e1.getLastModified().getTime()) {
                return -1;
            } else if (e.getLastModified().getTime() < e1.getLastModified().getTime()) {
                return 1;
            }
        } catch (ClassCastException ex) {
            return 0;
        }
        return 0;
    }

}