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

import org.cidrz.webapp.dynasite.valueobject.DisplayOrderable;

import java.util.Comparator;
import java.io.Serializable;

/**
 * Sorts DisplayOrderable objects. Other object types will default to natural order (see java.lang.Comparable).
 */
public class DisplayOrderComparator implements Comparator, Serializable {
    public int compare(Object o1, Object o2) {
        if ((o1 instanceof DisplayOrderable) && (o2 instanceof DisplayOrderable)) {
            DisplayOrderable do1, do2;
            do1 = (DisplayOrderable) o1;
            do2 = (DisplayOrderable) o2;
            if (do1.getDisplayOrder() == null) {
                do1.setDisplayOrder(new Integer(0));
            }
            if (do2.getDisplayOrder() == null) {
                do2.setDisplayOrder(new Integer(0));
            }
            if (do1.getDisplayOrder().intValue() > do2.getDisplayOrder().intValue()) {
                return 1;
            } else if (do1.getDisplayOrder().intValue() < do2.getDisplayOrder().intValue()) {
                return -1;
            } else if (do1.getDisplayOrder().intValue() == do2.getDisplayOrder().intValue()) {
                return 1;
            }
        }
        return 0;
    }

}
