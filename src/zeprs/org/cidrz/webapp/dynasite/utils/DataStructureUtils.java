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

import org.cidrz.webapp.dynasite.utils.sort.DisplayOrderComparator;
import org.cidrz.webapp.dynasite.valueobject.PageItem;

import java.util.*;

/**
 * This is a TreeSet so it will keep any sorts that the list throws at it...
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jun 17, 2005
 *         Time: 9:06:31 PM
 */
public class DataStructureUtils {
    private static DisplayOrderComparator c;

    /**
     * Note that this is for items such as fields that use display order.
     * @param list
     * @return Sorted set
     */
    public static final Set listToSet(List list) {
        // Declare a Set parameterized with the same type as the input List
        Set set = new TreeSet(new DisplayOrderComparator());
        // Loop over the list using regular for-loop
        // (using "for (T t : list)" breaks the compiler
        for (int i = 0; i < list.size(); i++) {
            Object o = list.get(i);
            // Add each member of the list into the set
            // (Set semantics dispose of duplicates)
            set.add(o);
        }
        return set;
    }

    public static final Set listToSet(List list, Comparator c) {

    // Declare a Set parameterized with the same type as the input List
        // c = new DisplayOrderComparator();
        Set set = new TreeSet(c);

    // Loop over the list using regular for-loop
    // (using "for (T t : list)" breaks the compiler
    for (int i=0;i<list.size();i++) {

        // Add each member of the list into the set
        // (Set semantics dispose of duplicates)
        set.add(list.get(i));
    }

    return set;
  }

    public static final Set listToSetNoComparator(List list) {

    // Declare a Set parameterized with the same type as the input List
    Set set = new HashSet();

    // Loop over the list using regular for-loop
    // (using "for (T t : list)" breaks the compiler
    for (int i=0;i<list.size();i++) {

        // Add each member of the list into the set
        // (Set semantics dispose of duplicates)
        set.add(list.get(i));
    }

    return set;
  }

    public static void createPageItem(PageItem pageItem) {
        PageItem newPageItem = new PageItem();
        newPageItem.setFormId(pageItem.getFormId());
        newPageItem.setForm_field(pageItem.getForm_field());
        newPageItem.setFormFieldId(pageItem.getFormFieldId());
        newPageItem.setInputType(pageItem.getInputType());
        newPageItem.setVisible(pageItem.isVisible());
        newPageItem.setDisplayOrder(pageItem.getDisplayOrder());
        newPageItem.setCols(pageItem.getCols());
        newPageItem.setColspan(pageItem.getColspan());
        newPageItem.setMaxlength(pageItem.getMaxlength());
        newPageItem.setSize(pageItem.getSize());
        newPageItem.setRows(pageItem.getRows());
        newPageItem.setCols(pageItem.getCols());
        newPageItem.setColumnNumber(pageItem.getColumnNumber());
        newPageItem.setColspan(pageItem.getColspan());
        newPageItem.setCloseRow(pageItem.isCloseRow());
        newPageItem.setVisibleDependencies1(pageItem.getVisibleDependencies1());
        newPageItem.setVisibleDependencies2(pageItem.getVisibleDependencies2());
        newPageItem.setVisibleEnumIdTrigger1(pageItem.getVisibleEnumIdTrigger1());
        newPageItem.setVisibleEnumIdTrigger2(pageItem.getVisibleEnumIdTrigger2());
        newPageItem.setAuditInfo(pageItem.getAuditInfo());
        newPageItem.setSelectedEnum(pageItem.getSelectedEnum());
    }
}
