package org.cidrz.webapp.dynasite.utils;

import org.cidrz.webapp.dynasite.valueobject.Auditable;

import java.util.Comparator;

public class AuditInfoLastModifiedComparator implements Comparator {
    public int compare(Object o, Object o1) {
        try {
            Auditable e, e1;
            e = (Auditable) o;
            e1 = (Auditable) o1;

            if (e.getAuditInfo().getLastModified().getTime() > e1.getAuditInfo().getLastModified().getTime()) {
                return -1;
            } else if (e.getAuditInfo().getLastModified().getTime() < e1.getAuditInfo().getLastModified().getTime()) {
                return 1;
            }
        } catch (ClassCastException ex) {
            return 0;
        }
        return 0;
    }

}