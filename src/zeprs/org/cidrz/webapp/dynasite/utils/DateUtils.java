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

import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import org.apache.struts.action.DynaActionForm;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: May 25, 2005
 * Time: 12:48:45 PM
 */
public class DateUtils {

    /**
     * Does encounter happen within difference between now and the encounter date?
     *
     * @param date
     * @param diff
     * @return
     */
    public static boolean checkDifference(Timestamp date, long diff) {

        boolean result = false;
        Calendar cal = new GregorianCalendar();
        Date now = cal.getTime();
        long long_now = now.getTime();

        Calendar cal2 = new GregorianCalendar();
        cal2.setTime(date);
        Date compareTime = cal2.getTime();
        long long_compareTime = compareTime.getTime();

        long diffMillis = diff * 3600 * 1000;
        long difference = (long_now - long_compareTime);

        if (difference < diffMillis) {
            result = true;
        }
        return result;
    }

    /**
     * Used when populating valueObjects from formBeans
     *
     * @param dynaForm
     * @return
     */
    public static java.sql.Date getvisitDate(DynaValidatorForm dynaForm) {
        java.sql.Date visitDateD = null;
        String dateValue = null;
        String date = null;
        String name = dynaForm.getDynaClass().getName();
        try {
            if (name.equals("form91")) {    // counsel
                date = dynaForm.get("field1882").toString();
            } else if (name.equals("form89")) { // arv
                date = dynaForm.get("field1881").toString();
            } else if (name.equals("form88")) { // drugs
                date = dynaForm.get("field1852").toString();
            } else if (name.equals("form90")) { //rpr
                date = dynaForm.get("field1562").toString();
            } else if (name.equals("form87")) { //labs
                date = dynaForm.get("field1844").toString();
            } else {
                date = dynaForm.get("field1").toString();
            }
        } catch (Exception e) {
            // it's null or does not exist. Set it today
            date = "";
            visitDateD = getNow();
        }
        if (!date.equals("")) {
            visitDateD = java.sql.Date.valueOf(date);
        }
        return visitDateD;
    }

    public static java.sql.Date getvisitDate(DynaActionForm dynaForm) {
        java.sql.Date visitDateD = null;
        String dateValue = null;
        String date = null;
        String name = dynaForm.getDynaClass().getName();
        try {
            if (name.equals("form91")) {    // counsel
                date = dynaForm.get("field1882").toString();
            } else if (name.equals("form89")) { // arv
                date = dynaForm.get("field1881").toString();
            } else if (name.equals("form88")) { // drugs
                date = dynaForm.get("field1852").toString();
            } else if (name.equals("form90")) { //rpr
                date = dynaForm.get("field1562").toString();
            } else if (name.equals("form87")) { //labs
                date = dynaForm.get("field1844").toString();
            } else {
                date = dynaForm.get("field1").toString();
            }
        } catch (Exception e) {
            // it's null or does not exist. Set it today
            date = "";
            visitDateD = getNow();
        }
        if (!date.equals("")) {
            visitDateD = java.sql.Date.valueOf(date);
        }
        return visitDateD;
    }

    /**
     * Get current date in sql format yyyy-MM-dd
     * @return
     */
    public static java.sql.Date getNow() {
        // set today's date
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "yyyy-MM-dd";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        String now = sdf.format(cal.getTime());
        return java.sql.Date.valueOf(now);
    }

    /**
     *
     * @return string in nice format.
     */
    public static String getNowPretty() {
        // set today's date
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "yyyy-MMM-dd";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(cal.getTime());
    }

    /**
     *
     * @return string in DATE_FORMAT = "yyyy-MMM-dd-HHmmss";
     */
    public static String getDatetimePretty() {
    	// set today's date
    	Calendar cal = Calendar.getInstance(TimeZone.getDefault());
    	String DATE_FORMAT = "yyyy-MMM-dd-HHmmss";
    	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
    	sdf.setTimeZone(TimeZone.getDefault());
    	return sdf.format(cal.getTime());
    }

    /**
     * Nice formatted date
     * @param datelong
     * @return String formated like 02 Jul 2002 14:26:12
     */
    public static String getFormattedDate(long datelong) {
    	Format formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
    	String s = formatter.format(datelong);
    	return s;
    }

    /**
     * Suitable for filenames
     * @param datelong
     * @return
     */
    public static String getFormattedDateDashes(long datelong) {
    	Format formatter = new SimpleDateFormat("yyyy-MMM-dd-HHmmss");
    	String s = formatter.format(datelong);
    	return s;
    }

    public static String getMonth() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "MM";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        String now = sdf.format(cal.getTime());
        return now;
    }

   public static String getMonth(Date date) {
        // Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "MM";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        String value = sdf.format(date);
        return value;
    }

    public static String getDay() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "dd";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        String now = sdf.format(cal.getTime());
        return now;
    }

    public static String getYear() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "yyyy";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        String now = sdf.format(cal.getTime());
        return now;
    }

    public static String getYear(Date date) {
        // Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "yyyy";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        String value = sdf.format(date);
        return value;
    }

    /**
     * returns time formatted "HH:mm:ss"
     * @return
     */
    public static String getTime() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "HH:mm:ss";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        String now = sdf.format(cal.getTime());
        return now;
    }

    public static String getTime(Date date) {
        // Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "HH:mm:ss";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        // sdf.setTimeZone(TimeZone.getDefault());
        String formattedTime = sdf.format(date);
        return formattedTime;
    }


    public static long calculateDays(Date beginDate, Date endDate) {
        GregorianCalendar beginCalendar = new GregorianCalendar();
        beginCalendar.setTime(beginDate);
        GregorianCalendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);
        java.util.Date d1 = beginCalendar.getTime();
        java.util.Date d2 = endCalendar.getTime();
        long l1 = d1.getTime();
        long l2 = d2.getTime();
        // there are 86400 seconds in a day
        long diffSeconds = (l2 - l1) / 1000;
        long diffDays = diffSeconds / 86400;
        return diffDays;
    }

    /**
     *
     * @return list of years from present year to 1900
     */
    public static List getYearList() {
        List yearList = new ArrayList();
        int yearNow = new Integer(getYear()) +1;
        for (int i = 1900; i < yearNow; i++) {
            yearList.add(i);
        }
        Collections.reverse(yearList);
        return yearList;
    }

    /**
     * This is used for testing
     * @return random birthdate
     * @param age
     */
        public static java.sql.Date generateBirthdate(int age) {
        // create random number
        Random generator = new Random();
        // there are 86400 seconds in a day * 1000 for miliseconds. 365 days/year = 31536000000 needs to be at least 13
        // 409968000000
        int month = generator.nextInt(12) + 1;
        int day = generator.nextInt(30) + 1;

        String DATE_FORMAT = "yyyy-MM-dd";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        // offset by years
        int currentYear = new Integer(DateUtils.getYear());
        int year = currentYear - age;
        GregorianCalendar gCal = new GregorianCalendar();
        gCal.set(gCal.YEAR, year);
        gCal.set(gCal.MONTH, month);
        try {
            gCal.set(gCal.DAY_OF_MONTH, day);
        } catch (Exception e) {
            gCal.set(gCal.MONTH, 1);
        }
        String birthDate = sdf.format(gCal.getTime());

        return java.sql.Date.valueOf(birthDate);
    }

    /**
     * This is used for testing
     * @return date based on offset
     * @param offsetDays
     */
        public static java.sql.Date generateDate(int offsetDays) {
        // Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "yyyy-MM-dd";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        GregorianCalendar gCal = new GregorianCalendar();
        // Date offsetDate = new Date(offset);
        gCal.add(Calendar.DATE, 0-offsetDays);
        String getCalDate = sdf.format(gCal.getTime());
        return java.sql.Date.valueOf(getCalDate);
    }

    /**
     *
     * @param lmpDate
     * @param offSet
     * @return string date value from offset - future or past
     */
    public static String createFutureDate(java.sql.Date lmpDate, int offSet) {
        GregorianCalendar eddCal = new GregorianCalendar();
        String DATE_FORMAT = "yyyy-MM-dd";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        eddCal.setTime(java.sql.Date.valueOf(lmpDate.toString()));
        eddCal.add(Calendar.DATE, offSet);
        String newDate = sdf.format(eddCal.getTime());
        return newDate;
    }

    /**
     * Generate timestamp.
     * @return timestamp
     */
    public static Timestamp generateTimestamp() {
    	java.sql.Timestamp now = new java.sql.Timestamp( System.currentTimeMillis());
    	return now;
    }

    /**
     * Convert timestamp to Date
     * kudos: http://www.java2s.com/Tutorial/Java/0340__Database/ConvertajavasqlTimestampObjecttoajavautilDateObject.htm
     * @param timestamp
     * @return
     */
     public static java.util.Date toDate(java.sql.Timestamp timestamp) {
         long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
         return new java.util.Date(milliseconds);
     }
}
