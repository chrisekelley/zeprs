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

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Mar 16, 2005
 * Time: 11:00:14 AM
 */
public class StringManipulation {
    public static String firstCharToUpperCase(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    public static String firstCharToLowerCase(String s) {
        return Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }

    /**
     * @param s
     * @return Nicely-formatted string for use in classname
     */
    public static String fixClassname(String s) {
        String niceName;
        int dash = s.indexOf("_");
        if (dash < 0) {
            niceName = Character.toUpperCase(s.charAt(0)) + s.substring(1);
        } else {
            char cap = Character.toUpperCase(s.charAt(dash + 1));
            niceName = Character.toUpperCase(s.charAt(0)) + s.substring(1, dash) + cap + s.substring(dash + 2);
        }
        return niceName;
    }

    /**
     * Used for XML generation for field names
     * @param s
     * @return
     */
        public static String removeForbiddenChars(String s) {
        return s.replaceAll("\\.", s);
    }

        /**
         * Filter an input field - some names have an "'" in them; filter out other bad stuff.
         * Taken from javax.management.Query
         * @param s
         * @return
         */
        public static String escapeString(String s) {
            if (s == null)
                return null;
            s = s.replace("\\", "\\\\");
            s = s.replace("*", "\\*");
            s = s.replace("?", "\\?");
            s = s.replace("[", "\\[");
            s = s.replace("'", "\\'");
            return s;
        }

}
