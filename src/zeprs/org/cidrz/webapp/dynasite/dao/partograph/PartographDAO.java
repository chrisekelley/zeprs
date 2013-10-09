/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.dao.partograph;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.valueobject.BaseRecord;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.project.zeprs.valueobject.gen.PartographStatus;
import org.cidrz.project.zeprs.valueobject.partograph.*;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.dao.PatientStatusDAO;
import org.cidrz.webapp.dynasite.dao.PregnancyDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Partograph;
import org.cidrz.webapp.dynasite.valueobject.AuditInfo;
import org.cidrz.webapp.dynasite.valueobject.Pregnancy;

import javax.servlet.ServletException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jul 7, 2005
 *         Time: 3:38:29 PM
 */
public class PartographDAO {

    /**
     * Assembles partograph object from database records;
     * This is used in patient record export.
     *
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @return
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     */
    public static Partograph getPartograph(Connection conn, Long patientId, Long pregnancyId) throws SQLException, IOException, ServletException {

        Partograph partograph = new Partograph();
        try {
            PartographStatus partographStatus = null;
            partographStatus = (PartographStatus) EncountersDAO.getMostRecentRecord(conn, patientId, pregnancyId, new Long("79"), PartographStatus.class);
            partograph.setPartographStatus(partographStatus);
        } catch (ObjectNotFoundException e) {
            // it's ok
        }
        try {
            FetalHeartRate fetalHr = null;
            FetalHeartRateDAO dao = new FetalHeartRateDAO();
            fetalHr = (FetalHeartRate) dao.getOne(conn, patientId, pregnancyId);
            partograph.setFetalHeartRate(fetalHr);
        } catch (ObjectNotFoundException e) {
            // it's ok
        }
        Liquor liquor = null;
        try {
            LiquorDAO ldao = new LiquorDAO();
            liquor = (Liquor) ldao.getOne(conn, patientId, pregnancyId);
            partograph.setLiquor(liquor);
        } catch (ObjectNotFoundException e) {
            // it's ok
        }
        try {
            Moulding moulding = null;
            MouldingDAO mdao = new MouldingDAO();
            moulding = (Moulding) mdao.getOne(conn, patientId, pregnancyId);
            partograph.setMoulding(moulding);
        } catch (ObjectNotFoundException e) {
            // it's ok
        }
        try {
            Cervix cervix = null;
            CervixDAO cdao = new CervixDAO();
            cervix = (Cervix) cdao.getOne(conn, patientId, pregnancyId);
            partograph.setCervix(cervix);
        } catch (ObjectNotFoundException e) {
            // it's ok
        }
        try {
            Descent descent = null;
            DescentDAO ddao = new DescentDAO();
            descent = (Descent) ddao.getOne(conn, patientId, pregnancyId);
            partograph.setDescent(descent);
        } catch (ObjectNotFoundException e) {
            // it's ok
        }
        try {
            Contractions contractions = null;
            ContractionsDAO condao = new ContractionsDAO();
            contractions = (Contractions) condao.getOne(conn, patientId, pregnancyId);
            partograph.setContractions(contractions);
        } catch (ObjectNotFoundException e) {
            // it's ok
        }
        try {
            Oxytocin oxytocin = null;
            OxytocinDAO odao = new OxytocinDAO();
            oxytocin = (Oxytocin) odao.getOne(conn, patientId, pregnancyId);
            partograph.setOxytocin(oxytocin);
        } catch (ObjectNotFoundException e) {
            // it's ok
        }
        try {
            DrugsDispensed drugsDispensed = null;
            DrugsDispensedDAO drugsdao = new DrugsDispensedDAO();
            drugsDispensed = (DrugsDispensed) drugsdao.getOne(conn, patientId, pregnancyId);
            partograph.setDrugsDispensed(drugsDispensed);
        } catch (ObjectNotFoundException e) {
            // it's ok
        }
        try {
            BloodPressure bloodPressure = null;
            BloodPressureDAO blooddao = new BloodPressureDAO();
            bloodPressure = (BloodPressure) blooddao.getOne(conn, patientId, pregnancyId);
            partograph.setBloodPressure(bloodPressure);
        } catch (ObjectNotFoundException e) {
            // it's ok
        }
        try {
            Pulse pulse = null;
            PulseDAO pulsedao = new PulseDAO();
            pulse = (Pulse) pulsedao.getOne(conn, patientId, pregnancyId);
            partograph.setPulse(pulse);
        } catch (ObjectNotFoundException e) {
            // it's ok
        }
        try {
            Temperature temperature = null;
            TemperatureDAO tempdao = new TemperatureDAO();
            temperature = (Temperature) tempdao.getOne(conn, patientId, pregnancyId);
            partograph.setTemperature(temperature);
        } catch (ObjectNotFoundException e) {
            // it's ok
        }
        try {
            Respiration respiration = null;
            RespirationDAO respdao = new RespirationDAO();
            respiration = (Respiration) respdao.getOne(conn, patientId, pregnancyId);
            partograph.setRespiration(respiration);
        } catch (ObjectNotFoundException e) {
            // it's ok
        }
        try {
            UrineAmount urineAmount = null;
            UrineAmountDAO urineadao = new UrineAmountDAO();
            urineAmount = (UrineAmount) urineadao.getOne(conn, patientId, pregnancyId);
            partograph.setUrineAmount(urineAmount);
        } catch (ObjectNotFoundException e) {
            // it's ok
        }
        try {
            UrinalysisProtein urinalysisProtein = null;
            UrinalysisProteinDAO updao = new UrinalysisProteinDAO();
            urinalysisProtein = (UrinalysisProtein) updao.getOne(conn, patientId, pregnancyId);
            partograph.setUrinalysisProtein(urinalysisProtein);
        } catch (ObjectNotFoundException e) {
            // it's ok
        }
        try {
            UrinalysisAcetone urinalysisAcetone = null;
            UrinalysisAcetoneDAO uadao = new UrinalysisAcetoneDAO();
            urinalysisAcetone = (UrinalysisAcetone) uadao.getOne(conn, patientId, pregnancyId);
            partograph.setUrinalysisAcetone(urinalysisAcetone);
        } catch (ObjectNotFoundException e) {
            // it's ok
        }
        try {
            UrinalysisGlucose urinalysisGlucose = null;
            UrinalysisGlucoseDAO ugdao = new UrinalysisGlucoseDAO();
            urinalysisGlucose = (UrinalysisGlucose) ugdao.getOne(conn, patientId, pregnancyId);
            partograph.setUrinalysisGlucose(urinalysisGlucose);
        } catch (ObjectNotFoundException e) {
            // it's ok
        }
        try {
            VaginalExamParto vaginalExam = null;
            VaginalExamPartoDAO vdao = new VaginalExamPartoDAO();
            vaginalExam = (VaginalExamParto) vdao.getOne(conn, patientId, pregnancyId);
            partograph.setVaginalExamParto(vaginalExam);
        } catch (ObjectNotFoundException e) {
            // it's ok
        }
        return partograph;
    }

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(PartographDAO.class);

    /**
     * Updates the table associated with the partograph classname and touches the patient_status table.
     * @param conn
     * @param vo
     * @param value
     * @param propertyName
     * @param className
     * @param user
     * @param siteId
     * @param timeValue
     * @param timeName
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static Long create(Connection conn, BaseRecord vo, String value, String propertyName, String className, String user, String siteId, String timeValue, String timeName) throws SQLException, ServletException {
        Long encounterId = null;
        String table = (String) DynaSiteObjects.getPartoTables().get("org.cidrz.project.zeprs.valueobject.partograph." + className);
        HashMap map = (HashMap) DynaSiteObjects.getPartoFields().get(className);
        String column = (String) map.get(propertyName);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        String timeColumn = null;
        try {
            timeColumn = (String) map.get(timeName);
        } catch (Exception e) {
            // it's ok - no time col in this table
        }
        String sql = null;
        ArrayList values = new ArrayList();
        if (vo.getId() != null) {
            if (timeColumn != null) {
                sql = "UPDATE " + table +
                        " SET last_modified='" + now +
                        "', last_modified_by='" + user +
                        "', site_id=" + siteId +
                        ", " + column + "=?" +
                        ", " + timeColumn + "=?" +
                        " WHERE id=?" +
                        "; ";
                values.add(value);
                values.add(timeValue);
                values.add(vo.getId());
            } else {
                sql = "UPDATE " + table +
                        " SET last_modified='" + now +
                        "', last_modified_by='" + user +
                        "', site_id=" + siteId +
                        ", " + column + "=?" +
                        " WHERE id=?" +
                        "; ";
                values.add(value);
                values.add(vo.getId());
            }
        } else {
            if (timeColumn != null) {
                sql = "INSERT INTO " + table +
                        " SET patient_id=" + vo.getPatientId() +
                        ", date_visit='" + vo.getDateVisit() +
                        "', pregnancy_id=" + vo.getPregnancyId() +
                        ", last_modified='" + now +
                        "', created='" + now +
                        "', last_modified_by='" + user +
                        "', created_by='" + user +
                        "', site_id=" + siteId +
                        ", " + column + "=?" +
                        ", " + timeColumn + "=?" +
                        "; ";
                values.add(value);
                values.add(timeValue);
                // log.debug("SQL: " + sql + " Values: " + values);
            } else {
                sql = "INSERT INTO " + table +
                        " SET patient_id=" + vo.getPatientId() +
                        ", date_visit='" + vo.getDateVisit() +
                        "', pregnancy_id=" + vo.getPregnancyId() +
                        ", last_modified='" + now +
                        "', created='" + now +
                        "', last_modified_by='" + user +
                        "', created_by='" + user +
                        "', site_id=" + siteId +
                        ", " + column + "=?" +
                        "; ";
                values.add(value);
            }
        }
        encounterId = (Long) DatabaseUtils.create(conn, sql, values.toArray());
        EncounterData eo = new EncounterData();
        eo.setLastModified(now);
        eo.setLastModifiedBy(user);
        eo.setSiteId(Long.valueOf(siteId));
        try {
            PatientStatusDAO.touchPatientStatus(conn, eo, user, Long.valueOf(siteId), vo.getPatientId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encounterId;
    }

    /**
     * Deletes a value from the partograph by updating the value in the table to null.
     * @param conn
     * @param vo
     * @param propertyName
     * @param className
     * @param user
     * @param siteId
     * @param timeName
     * @throws SQLException
     * @throws ServletException
     */
    public static void delete(Connection conn, BaseRecord vo, String propertyName, String className, String user, String siteId, String timeName) throws SQLException, ServletException {

        Long encounterId = null;
        String table = (String) DynaSiteObjects.getPartoTables().get("org.cidrz.project.zeprs.valueobject.partograph." + className);
        HashMap map = (HashMap) DynaSiteObjects.getPartoFields().get(className);
        String column = (String) map.get(propertyName);
        String timeColumn = null;
        try {
            timeColumn = (String) map.get(timeName);
        } catch (Exception e) {
            // it's ok - no time col in this table
        }
        String sql = null;
        ArrayList values = new ArrayList();
        if (vo.getId() != null) {
            if (timeColumn != null) {
                sql = "UPDATE " + table +
                        " SET last_modified='" + new Timestamp(System.currentTimeMillis()) +
                        "', last_modified_by='" + user +
                        "', site_id=" + siteId +
                        ", " + column + "=?" +
                        ", " + timeColumn + "=?" +
                        " WHERE id=?" +
                        "; ";
                values.add(null);
                values.add(null);
                values.add(vo.getId());
                // log.debug("SQL: " + sql + " Values: " + values);
            } else {
                sql = "UPDATE " + table +
                        " SET last_modified='" + new Timestamp(System.currentTimeMillis()) +
                        "', last_modified_by='" + user +
                        "', site_id=" + siteId +
                        ", " + column + "=?" +
                        " WHERE id=?" +
                        "; ";
                values.add(null);
                values.add(vo.getId());
                // log.debug("SQL: " + sql + " Values: " + values);
            }
            encounterId = (Long) DatabaseUtils.create(conn, sql, values.toArray());
        }
    }

    /**
     * Delete patient records in partograph tables for *all* pregnancies
     *
     * @param conn
     * @param patientId
     * @return
     * @throws Exception
     */
    public static String delete(Connection conn, Long patientId, String tableName) throws Exception {
        String result = null;
        Statement stmt;
        conn.setAutoCommit(false);
        stmt = conn.createStatement();
        stmt.execute("START TRANSACTION;");
        stmt.execute("SET FOREIGN_KEY_CHECKS = 0;");
        String sql = "DELETE " + tableName.toLowerCase() +
                " FROM \n" + tableName.toLowerCase() +
                " WHERE patient_id = " + patientId;
        stmt.execute(sql);
        stmt.execute("Commit");
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Delete patient records in partograph tables for a pregnancy
     *
     * @param conn
     * @param patientId
     * @return
     * @throws Exception
     */
    public static String delete(Connection conn, Long patientId, Long pregnancyId, String tableName) throws Exception {
    	String result = null;
    	Statement stmt;
    	conn.setAutoCommit(false);
    	stmt = conn.createStatement();
    	stmt.execute("START TRANSACTION;");
    	stmt.execute("SET FOREIGN_KEY_CHECKS = 0;");
    	String sql = "DELETE " + tableName.toLowerCase() +
    	" FROM \n" + tableName.toLowerCase() +
    	" WHERE patient_id = " + patientId +
    	" AND pregnancy_id = " + pregnancyId;
    	stmt.execute(sql);
    	stmt.execute("Commit");
    	try {
    		conn.commit();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return result;
    }

    /**
     * Deletes all partograph values for a patient for *all* pregnancies.
     * This is used when deleting a whole patient record.
     * @param conn
     * @param patientId
     * @throws Exception
     */
    public static void deletePartograph(Connection conn, Long patientId) throws Exception {
        HashMap partoMap = DynaSiteObjects.getPartoTables();
        Set partoSet = partoMap.entrySet();
        for (Iterator iterator = partoSet.iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String tableName = (String) entry.getValue();
            PartographDAO.delete(conn, patientId, tableName);
        }
    }

    /**
     * Deletes all partograph values for a patient for this pregnancy.
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @throws Exception
     */
    public static void deletePartograph(Connection conn, Long patientId, Long pregnancyId) throws Exception {
    	HashMap partoMap = DynaSiteObjects.getPartoTables();
    	Set partoSet = partoMap.entrySet();
    	for (Iterator iterator = partoSet.iterator(); iterator.hasNext();) {
    		Map.Entry entry = (Map.Entry) iterator.next();
    		String tableName = (String) entry.getValue();
    		PartographDAO.delete(conn, patientId, pregnancyId, tableName);
    	}
    }

    /**
     * Save the whole partograph, which comes from xml patient record.
     * Using sql assembled on-the-fly to speed things up - there may be just a couple values in each record.
     * Fair amount of reflection going on here. Alot depends on PartographMapping.partoClassNameList() being correct.
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @param partograph
     * @return
     * @throws IOException
     * @throws SQLException
     */
    public static Object save(Connection conn, Long patientId, Long pregnancyId, Partograph partograph) throws IOException, SQLException, IllegalAccessException {
        Object result = null;
        List partoClasses = PartographMapping.partoClassNameList();
        for (int i = 0; i < partoClasses.size(); i++) {
            Field field = null;
            Class clazz = null;
            String className = (String) partoClasses.get(i);
            Method method;
            Object item = null;
            try {
                 clazz = Class.forName("org.cidrz.project.zeprs.valueobject.partograph." + className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                method = Partograph.class.getMethod("get" + className);
                item = method.invoke(partograph, new Object[0]);
                if (item != null) {
                    clazz.cast(item);
                    result = saveOneItem(className, item, patientId, pregnancyId, conn, result);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static Object saveOneItem(String className, Object item, Long patientId, Long pregnancyId, Connection conn, Object result) throws SQLException {
        ArrayList values = new ArrayList();
        String table = (String) DynaSiteObjects.getPartoTables().get("org.cidrz.project.zeprs.valueobject.partograph." + className);
        HashMap map = (HashMap) DynaSiteObjects.getPartoFields().get(className);
        Set mapSet = map.entrySet();
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO " + table + " SET ");
        int cnt = 0;
        for (Iterator iterator = mapSet.iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String fieldName = (String) entry.getKey();
            String column = (String) entry.getValue();
            if (!column.equals("id") && !column.equals("import_id")) {  // exclude id column since we're autonumbering id's
                /*if (column.equals("id")) {
                   column = "import_id";
                }*/
                String voValue = null;
                try {
                    voValue = BeanUtils.getProperty(item, fieldName);
                    if (voValue != null) {
                        cnt ++;
                        if (cnt == 1) {
                            sql.append(column + "=?");
                        } else {
                            sql.append(", " + column + "=?");
                        }
                        if (fieldName.equals("patientId")) {
                            values.add(patientId);
                        } else if (fieldName.equals("pregnancyId")) {
                            values.add(pregnancyId);
                        } else {
                            values.add(voValue);
                        }
                    }
                } catch (ConversionException e) {
                    log.error(e);
                    voValue = null;
                } catch (NullPointerException e) {
                    log.error(e);
                    voValue = null;
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        sql.append(", import_id=?");
        BaseRecord record = (BaseRecord) item;
        if (record.getImportId() != null) {
            values.add(record.getImportId());
        } else {
            values.add(record.getId());
        }

        // save it
        result = DatabaseUtils.create(conn, sql.toString(), values.toArray());
        return result;
    }

    /**
     * Updates the whole partograph, which comes from xml patient record.
     * Using sql assembled on-the-fly to speed things up - there may be just a couple values in each record.
     * Fair amount of reflection going on here. Alot depends on PartographMapping.partoClassNameList() being correct.
     * @param conn
     * @param patientId
     * @param importPregnancyId
     * @param importedPartograph
     * @param partograph
     * @param currentPregnancyId
     * @param comments
     * @return
     * @throws SQLException
     */
    public static Object update(Connection conn, Long patientId, Long importPregnancyId, Partograph importedPartograph, Partograph currentPartograph, Long currentPregnancyId, StringBuffer comments) throws SQLException, IllegalAccessException, IOException, ServletException {
        Object result = null;
        boolean partoUpdated = false;
        // create a map of import_preg_id to pregId
       /* List currentPregnancies = PregnancyDAO.getPatientPregnancies(conn, patientId);
        HashMap pregMap = new HashMap();
        for (int i = 0; i < currentPregnancies.size(); i++) {
            Pregnancy pregnancy = (Pregnancy) currentPregnancies.get(i);
            pregMap.put(pregnancy.getImportPregnancyId(), pregnancy.getId());
        }*/
        // Long currentPregnancyId = (Long) pregMap.get(importPregnancyId);
        // fetch the current partograph from the db
        // Partograph partograph = PartographDAO.getPartograph(conn, patientId, currentPregnancyId);
        List partoClasses = PartographMapping.partoClassNameList();
        for (int i = 0; i < partoClasses.size(); i++) {
            Field field = null;
            Class clazz = null;
            String className = (String) partoClasses.get(i);
            Method method;
            BaseRecord item = null;
            try {
                 clazz = Class.forName("org.cidrz.project.zeprs.valueobject.partograph." + className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //log.debug("Class: " + className);
            try {
                method = Partograph.class.getMethod("get" + className);
                item = (BaseRecord) method.invoke(importedPartograph, (Object[]) new BaseRecord[0]);
                BaseRecord currentItem = (BaseRecord) method.invoke(currentPartograph, (Object[]) new BaseRecord[0]);
                if (item != null) {
                    Timestamp importLastModified = item.getLastModified();
                    clazz.cast(item);
                    //comments.append(" currentAuditInfo " + auditInfo.getLastModified());
                    if (currentItem != null) {
                        Timestamp currentLastModified = currentItem.getLastModified();
                        // log.debug("currentLastModified: " + currentLastModified);
                        //comments.append(" currentAuditInfo " + currentAuditInfo.getLastModified());
                        if ((currentLastModified == null) || (importLastModified != null && importLastModified.getTime() > currentLastModified.getTime()))
                        {
                            // comments.append(" Updating ").append(item.getClass().getName() + " id: " + currentItem.getId());
                            partoUpdated = true;
                            //Map map = BeanUtils.describe(item);
                            ArrayList values = new ArrayList();
                            String table = (String) DynaSiteObjects.getPartoTables().get("org.cidrz.project.zeprs.valueobject.partograph." + className);
                            HashMap map = (HashMap) DynaSiteObjects.getPartoFields().get(className);
                            Set mapSet = map.entrySet();
                            StringBuffer sql = new StringBuffer();
                            sql.append("UPDATE " + table + " SET ");
                            int cnt = 0;
                            for (Iterator iterator = mapSet.iterator(); iterator.hasNext();) {
                                Map.Entry entry = (Map.Entry) iterator.next();
                                String fieldName = (String) entry.getKey();
                                String column = (String) entry.getValue();
                                if (!column.equals("patient_id") && !column.equals("pregnancy_id") && !column.equals("created") && !column.equals("created_by") ) {
                                    String voValue = null;
                                    try {
                                        voValue = BeanUtils.getProperty(item, fieldName);
                                        if (voValue != null) {
                                            cnt ++;
                                            if (cnt == 1) {
                                                sql.append(column + "=?");
                                            } else {
                                                sql.append(", " + column + "=?");
                                            }
                                            if (column.equals("import_id")) {
                                                values.add(item.getId());
                                            } else {
                                                values.add(voValue);
                                            }
                                        }
                                    } catch (ConversionException e) {
                                        log.error(e);
                                        voValue = null;
                                    } catch (NullPointerException e) {
                                        log.error(e);
                                        voValue = null;
                                    } catch (NoSuchMethodException e) {
                                        e.printStackTrace();
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    } catch (InvocationTargetException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            sql.append(" WHERE id=?");
                            values.add(currentItem.getId());
                            // log.debug(" sql: " + sql + " values: " + values);
                            // save it
                            result = DatabaseUtils.create(conn, sql.toString(), values.toArray());
                        }
                    } else {
                        result = saveOneItem(className, item, patientId, currentPregnancyId, conn, result);
                        // comments.append(" Need to add to partograph: ").append(item.getClass());
                    }
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        if (partoUpdated) {
            comments.append(" Partograph; ");
        }
        return result;
    }
}