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

import java.io.Serializable;
import java.util.Comparator;

import org.cidrz.webapp.dynasite.valueobject.DateVisitOrderable;

/**
 * Sorts FlowOrderComparator objects. Other object types will default to natural order (see java.lang.Comparable).
 */
public class DateVisitOrderComparator implements Comparator, Serializable {
    public int compare(Object o1, Object o2) {
        if ((o1 instanceof DateVisitOrderable) && (o2 instanceof DateVisitOrderable)) {
        	DateVisitOrderable do1, do2;
            do1 = (DateVisitOrderable) o1;
            do2 = (DateVisitOrderable) o2;
            if (do1.getDateVisit() == null) {
            	 return -1;
            }
            if (do2.getDateVisit() == null) {
            	return -1;
            }
            if (do1.getDateVisit().getTime() > do2.getDateVisit().getTime()) {
                return 1;
            } else if (do1.getDateVisit().getTime() < do2.getDateVisit().getTime()) {
                return -1;
            }
        }
        return 0;
    }

}
