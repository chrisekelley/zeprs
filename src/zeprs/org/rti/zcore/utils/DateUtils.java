/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.rti.zcore.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: May 25, 2005
 * Time: 12:48:45 PM
 */
public class DateUtils {

	/**
	 * Does encounter happen within difference between now and the encounter
	 * date?
	 *
	 * @param date
	 * @param diff in hours
	 * @return true if difference between now and the date is less than diff.
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
	 * Checks if expiry date is less than 6 months.
	 *
	 * @param date1
	 * @param expiry
	 * @return
	 */
	public static boolean checkExpiry(Date date1, Date expiry) {

    	boolean result = false;

    	// date 6 months ago
    	Calendar cal1 = new GregorianCalendar();
    	cal1.setTime(date1);
    	cal1.add(java.util.Calendar.MONTH, +6);
		long sixMonthsAhead = cal1.getTime().getTime();

    	// date to compare to
    	Calendar cal2 = new GregorianCalendar();
    	cal2.setTime(expiry);
    	long expiry_date_value = cal2.getTime().getTime();

    	if (expiry_date_value < sixMonthsAhead) {
    		result = true;
    	}
    	return result;
    }

	/**
	 * Used when populating valueObjects from formBeans
	 * Automatically populates for admin forms, which do not have date_visit field
	 *
	 * @param formData
	 * @param formName TODO
	 * @return
	 */
    public static java.sql.Date getVisitDate(Map formData, String formName) {
        java.sql.Date visitDateD = null;
        String dateValue = null;
        String date = null;
        //String name = formData.getDynaClass().getName();
        try {
            if (formName.equals("form91")) {    // counsel
                date = formData.get("field1882").toString();
            } else if (formName.equals("form89")) { // arv
                date = formData.get("field1881").toString();
            } else if (formName.equals("form88")) { // drugs
                date = formData.get("field1852").toString();
            } else if (formName.equals("form90")) { //rpr
                date = formData.get("field1562").toString();
            } else if (formName.equals("form87")) { //labs
                date = formData.get("field1844").toString();
            } else {
                date = formData.get("date_visit").toString();
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
     * @return string in nice format - yyyy-MMM-dd.
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
     * Used for creating archive directories in format yyyy/MMM/W
     * @return
     */
    public static String getNowYearMonthWeeks() {
    	// set today's date
    	Calendar cal = Calendar.getInstance(TimeZone.getDefault());
    	String DATE_FORMAT = "yyyy/MMM/W";
    	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
    	sdf.setTimeZone(TimeZone.getDefault());
    	return sdf.format(cal.getTime());
    }

    /**
     * Get current date/time - useful for filenames.
     * @return string in nice format - yyyyMMddHHmmss
     */
    public static String getNowFileFormat() {
    	// set today's date
    	Calendar cal = Calendar.getInstance(TimeZone.getDefault());
    	String DATE_FORMAT = "yyyy-MM-dd-HHmmssSSS";
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
     * Gets the month in DATE_FORMAT "MM"
     * @return
     */
    public static String getMonth() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "MM";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        String now = sdf.format(cal.getTime());
        return now;
    }


    /**
     * Gets the month in DATE_FORMAT "MM" for the Date
     * @param date
     * @return
     */
    public static String getMonth(Date date) {
         // Calendar cal = Calendar.getInstance(TimeZone.getDefault());
         String DATE_FORMAT = "MM";
         java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
         String value = sdf.format(date);
         return value;
     }

    /**
     * Gets the week in the year in DATE_FORMAT "w"
     * if date param is null, use current date.
     * @param date
     * @return
     */
    public static Integer getWeekInYear(Date date) {
    	if (date == null) {
    		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
    		date = cal.getTime();
    	}
    	String DATE_FORMAT = "w";
    	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
    	String value = sdf.format(date);
    	Integer week = Integer.valueOf(value);
    	return week;
    }

    /**
     * returns current date string formated per dateFormat (MMM for month, yyyy for year, etc.)
     * @param dateFormat
     * @return
     */
    public static String getDateString(String dateFormat) {
    	Calendar cal = Calendar.getInstance(TimeZone.getDefault());
    	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(dateFormat);
    	sdf.setTimeZone(TimeZone.getDefault());
    	String now = sdf.format(cal.getTime());
    	return now;
    }

   /**
    * returns date string formated per dateFormat (MMM for month, yyyy for year, etc.) based on the Timestamp
    * @param dateFormat
    * @param date - Timestamp
    * @should return a string
    * @return
    */
    public static String getDateString(String dateFormat, Timestamp date) {
    	Calendar cal = new GregorianCalendar();
        cal.setTime(date);
    	//Calendar cal = Calendar.getInstance(TimeZone.getDefault());
    	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(dateFormat);
    	sdf.setTimeZone(TimeZone.getDefault());
    	String now = sdf.format(cal.getTime());
    	return now;
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

    /**
     * Calculates difference between two days, in days
     * @param beginDate
     * @param endDate
     * @return difference in days
     */
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
     * DATE_FORMAT = "yyyy-MM-dd";
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
     * Convert timestamp to util.Date
     * kudos: http://www.java2s.com/Tutorial/Java/0340__Database/ConvertajavasqlTimestampObjecttoajavautilDateObject.htm
     * @param timestamp
     * @return
     */
    public static java.util.Date toDate(java.sql.Timestamp timestamp) {
    	long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
    	return new java.util.Date(milliseconds);
    }

    /**
     * Convert timestamp to sql.Date
     * kudos: http://www.java2s.com/Tutorial/Java/0340__Database/ConvertajavasqlTimestampObjecttoajavautilDateObject.htm
     * @param timestamp
     * @return
     */
    public static java.sql.Date toDateSql(java.sql.Timestamp timestamp) {
    	long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
    	return new java.sql.Date(milliseconds);
    }

	/**
	 * Formats for OpenMRS import
	 * kudos to openmrs.org
	 * copied from java.text.DateFormat.FormUtil
	 * @param date
	 * @return
	 */
	public static String dateToISO8601String(Date date) {
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		String dateString = dateFormatter.format(new Date());
		// ISO 8601 requires a colon in time zone offset (Java doesn't
		// include the colon, so we need to insert it
		return dateString.substring(0, 22) + ":" + dateString.substring(22);
	}
}
