/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.utils.sort;

import org.cidrz.webapp.dynasite.valueobject.NumericValueOrderable;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Sorts NumericOrder objects. Other object types will default to natural order (see java.lang.Comparable).
 */
public class EnumNumericOrderComparator implements Comparator, Serializable {
    public int compare(Object o1, Object o2) {
        if ((o1 instanceof NumericValueOrderable) && (o2 instanceof NumericValueOrderable)) {
            NumericValueOrderable nv1, nv2;
            nv1 = (NumericValueOrderable) o1;
            nv2 = (NumericValueOrderable) o2;
            int value1 = new Integer(nv1.getNumericValue());
            int value2 = new Integer(nv2.getNumericValue());
            if (value1 > value2) {
                return 1;
            } else if (value1 < value2) {
                return -1;
            }
        }
        return 0;
    }

}
