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

import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Demonstrates how to take advantage of the beanification introduced
 * in BeanUtils 1.7.0. Replaces the inherited implementation of
 * getNestedProperty() with a version that handles
 * delimiter characters more flexibly.
 * <p/>
 * usage:
 * BeanUtilsBean.setInstance(new BeanUtilsBean(new ConvertUtilsBean(), new KindPropertyUtilsBean()));
 *
 * @author Jonathan Lehr
 */
public class KindPropertyUtilsBean extends PropertyUtilsBean {
    Log logger = LogFactory.getLog(PropertyUtilsBean.class);

    /**
     * Replaces inherited implementation to add support for finding a key in
     * a Map when the key contains one or more dot characters (e.g.,
     * "foo.bar"). This notation is commonly used in property file keys.
     * Support for mapped properties (i.e. keypaths of the form
     * "foo(bar)", "foo(bar.baz)", etc.) has been omitted for the
     * sake of simplicity.
     * <p/>
     * A further simplification is the assumption that keys using the
     * property file key syntax (embedded dots) occur only at the ends of
     * keypaths. Additional logic would be needed to handle them more
     * generically (i.e. occurring at arbitrary points within a keypath).
     *
     * @see org.apache.commons.beanutils.PropertyUtilsBean#getNestedProperty(java.lang.Object, java.lang.String)
     */
    public Object getNestedProperty(Object bean, String name)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        if (bean == null || name == null)
            throw new IllegalArgumentException("No " + bean == null ? "bean" : "name" + " specified");

        Object currObj = bean;
        Object value = null;
        Keypath keypath = new Keypath(name);
        while (keypath.hasMoreKeys()) {
            String key = keypath.nextKey();
            if (currObj instanceof Map)
                value = getPropertyFromMap((Map) currObj, key, keypath);
            else if (key.indexOf(PropertyUtils.INDEXED_DELIM) > -1)
                value = getIndexedProperty(currObj, key);
            else
                value = getSimpleProperty(currObj, key);

            currObj = value;
        }
        return value;
    }

    /**
     * Tries to get a property from map using the provided
     * key. If the Map contains the key, the associated property value
     * is obtained from the Map and returned. Otherwise, checks to see if
     * the Map contains a key matching the remainder of the keypath. If so,
     * the property value stored under the alternate key is returned.
     */
    public Object getPropertyFromMap(Map map, String key, Keypath keypath) {
        String alternateKey = keypath.remainingKeypath();
        if (!(map.containsKey(key) || map.containsKey(alternateKey)))
            throw new NestedNullException("No property named " + key +
                    " or " + alternateKey);
        if (map.containsKey(key))
            return map.get(key);

        if (map.containsKey(alternateKey))
            keypath.invalidate();
        return map.get(alternateKey);
    }

    public static class Keypath {
        private String keypath;
        private int currKeyStartIndex;
        private int currKeyEndIndex;

        public Keypath(String keypath) {
            this.keypath = keypath;
        }

        public boolean hasMoreKeys() {
            return keypath.length() - 1 > currKeyEndIndex;
        }

        public String nextKey() {
            if (currKeyEndIndex != 0) {
                currKeyStartIndex = currKeyEndIndex + 1;
            }
            int end = keypath.indexOf(PropertyUtils.NESTED_DELIM,
                    currKeyStartIndex);
            // currKeyEndIndex = end == -1 ? keypath.length() - 1 : end;
            currKeyEndIndex = end == -1 ? keypath.length() : end;
            return keypath.substring(currKeyStartIndex, currKeyEndIndex);
        }

        public String remainingKeypath() {
            return keypath.substring(currKeyStartIndex);
        }

        public void invalidate() {
            currKeyEndIndex = keypath.length() - 1;
        }
    }

}
