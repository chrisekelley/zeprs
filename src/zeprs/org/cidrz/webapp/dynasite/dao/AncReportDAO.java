package org.cidrz.webapp.dynasite.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.cidrz.project.zeprs.report.valueobject.AntenatalSummary;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.ArchiveLog;

/**
 * Used to cache ANC report results
 * Uses zeports database.
 * @author chrisk
 *
 */
public class AncReportDAO {

	/**
	 * Get a single instance.
	 * @param conn
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws ServletException
	 * @throws ObjectNotFoundException
	 */
    public static Object getOne(Connection conn, Long id) throws SQLException, ServletException, ObjectNotFoundException {
        Object result;
        ArrayList values;
        values = new ArrayList();
        String sql = "SELECT id,date,newancclient,totalancattendees,revisits,counselednewanc," +
        		"counseledrevisit,pretestcounseled,hivtestednewanc,hivtestedrevisit,totaltested," +
        		"refusedtesting,receivedresults,hivpositive,hivnegative,hivindeterm,referredarvclinic," +
        		"maternalnvp,maternalazt,infantnvp,rprtested,rprpositive,rprnegative,syphilistreated," +
        		"hgbdone,hgbgt10,sp1,sp2,sp3,tt1,tt2,tt3,tt4,tt5,ttprotected,ttcomplete,firstvisit1sttrim," +
        		"firstvisit2ndtrim,firstvisit3rdtrim,vermox,site_id " +
        		"FROM zeports.anc_report " +
        		"WHERE id=?" +
        		"ORDER BY date+visit;";
        values.add(id);
        result = DatabaseUtils.getBean(conn, ArchiveLog.class, sql, values);
        return result;
    }

    /**
     * Fetch list of archive operations
     * @param conn
     * @return  list of archive operations
     * @throws SQLException
     * @throws ServletException
     */
    public static List getAll(Connection conn) throws SQLException, ServletException {
        List results;
        ArrayList values = new ArrayList();
        String sql = "SELECT id,date_visit AS dateVisit,newancclient AS newANCClient,totalancattendees AS totalANCAttendees,revisits AS revisits,counselednewanc AS counselledNew," +
        "counseledrevisit AS counselledRevisits,pretestcounseled AS preTestCounseled,hivtestednewanc AS hivTestedNewAnc,hivtestedrevisit AS hivTestedRevisit,totaltested AS hivTested," +
        "refusedtesting AS refusedTesting,receivedresults AS receivedResults,hivpositive AS hivPositive,hivnegative AS hivNegative,hivindeterm AS hivIndeterminate,referredarvclinic AS referredArt," +
        "maternalnvp AS maternalNVP,maternalazt AS maternalAzt,infantnvp AS infantNVP,rprtested AS rprTested,rprpositive AS rprPositive,rprnegative AS rprNegative,syphilistreated AS syphilisTreated," +
        "hgbdone AS hgbDone,hgbgt10 AS hgbLT10,sp1,sp2,sp3,tt1,tt2,tt3,tt4,tt5,ttprotected AS ttProtected,ttcomplete AS ttComplete,firstvisit1sttrim AS firstVisit1stTrim," +
        "firstvisit2ndtrim AS firstVisit2ndTrim,firstvisit3rdtrim AS firstVisit3rdTrim,vermox AS vermoxGiven,site_id AS siteId " +
        "FROM zeports.anc_report " +
        "ORDER BY date_visit;";
        results = DatabaseUtils.getList(conn, AntenatalSummary.class, sql, values);
        return results;
    }

    /**
     * Fetch list of archive operations
     * @param conn
     * @return  list of archive operations
     * @throws SQLException
     * @throws ServletException
     */
    public static List getAll(Connection conn, Long siteId) throws SQLException, ServletException {
        List results;
        ArrayList values = new ArrayList();
        String sql = "SELECT id,date_visit AS dateVisit,newancclient AS newANCClient,totalancattendees AS totalANCAttendees,revisits AS revisits,counselednewanc AS counselledNew," +
        "counseledrevisit AS counselledRevisits,pretestcounseled AS preTestCounseled,hivtestednewanc AS hivTestedNewAnc,hivtestedrevisit AS hivTestedRevisit,totaltested AS hivTested," +
        "refusedtesting AS refusedTesting,receivedresults AS receivedResults,hivpositive AS hivPositive,hivnegative AS hivNegative,hivindeterm AS hivIndeterminate,referredarvclinic AS referredArt," +
        "maternalnvp AS maternalNVP,maternalazt AS maternalAzt,infantnvp AS infantNVP,rprtested AS rprTested,rprpositive AS rprPositive,rprnegative AS rprNegative,syphilistreated AS syphilisTreated," +
        "hgbdone AS hgbDone,hgbgt10 AS hgbLT10,sp1,sp2,sp3,tt1,tt2,tt3,tt4,tt5,ttprotected AS ttProtected,ttcomplete AS ttComplete,firstvisit1sttrim AS firstVisit1stTrim," +
        "firstvisit2ndtrim AS firstVisit2ndTrim,firstvisit3rdtrim AS firstVisit3rdTrim,vermox AS vermoxGiven,site_id AS siteId " +
        "FROM zeports.anc_report " +
        "WHERE site_id = ? " +
        "ORDER BY date_visit;";
        values.add(siteId);
        results = DatabaseUtils.getList(conn, AntenatalSummary.class, sql, values);
        return results;
    }

    /**
     * Saves new anc_report instance in zeports database
     * @param conn
     * @param time
     * @param checksun
     * @return id of new record
     * @throws SQLException
     */
    public static Object save(Connection conn, AntenatalSummary antenatalSummary) throws SQLException {
        Object result = 0;
        ArrayList values;
        values = new ArrayList();
        String sql = "INSERT into zeports.anc_report " +
                "SET date_visit=?,newancclient=?,totalancattendees=?,revisits=?,counselednewanc=?," +
                "counseledrevisit=?,pretestcounseled=?,hivtestednewanc=?,hivtestedrevisit=?,totaltested=?," +
                "refusedtesting=?,receivedresults=?,hivpositive=?,hivnegative=?,hivindeterm=?," +
                "referredarvclinic=?,maternalnvp=?,maternalazt=?,infantnvp=?,rprtested=?,rprpositive=?," +
                "rprnegative=?,syphilistreated=?,hgbdone=?,hgbgt10=?,sp1=?,sp2=?,sp3=?," +
                "tt1=?,tt2=?,tt3=?,tt4=?,tt5=?,ttprotected=?,ttcomplete=?,firstvisit1sttrim=?," +
                "firstvisit2ndtrim=?,firstvisit3rdtrim=?,vermox=?,site_id=?";
        values.add(antenatalSummary.getDateVisit());
        values.add(antenatalSummary.getNewANCClient());
        values.add(antenatalSummary.getTotalANCAttendees());
        values.add(antenatalSummary.getRevisits());
        values.add(antenatalSummary.getCounselledNew());
        values.add(antenatalSummary.getCounselledRevisits());
        values.add(antenatalSummary.getPreTestCounseled());
        values.add(antenatalSummary.getHivTestedNewAnc());
        values.add(antenatalSummary.getHivTestedRevisit());
        values.add(antenatalSummary.getHivTested());
        values.add(antenatalSummary.getRefusedTesting());
        values.add(antenatalSummary.getReceivedResults());
        values.add(antenatalSummary.getHivPositive());
        values.add(antenatalSummary.getHivNegative());
        values.add(antenatalSummary.getHivIndeterminate());
        values.add(antenatalSummary.getReferredArt());
        values.add(antenatalSummary.getMaternalNVP());
        values.add(antenatalSummary.getMaternalAztNewStart());
        values.add(antenatalSummary.getInfantNVP());
        values.add(antenatalSummary.getRprTested());
        values.add(antenatalSummary.getRprPositive());
        values.add(antenatalSummary.getRprNegative());
        values.add(antenatalSummary.getSyphilisTreated());
        values.add(antenatalSummary.getHgbDone());
        values.add(antenatalSummary.getHgbLT10());
        values.add(antenatalSummary.getSp1());
        values.add(antenatalSummary.getSp2());
        values.add(antenatalSummary.getSp3());
        values.add(antenatalSummary.getTt1());
        values.add(antenatalSummary.getTt2());
        values.add(antenatalSummary.getTt3());
        values.add(antenatalSummary.getTt4());
        values.add(antenatalSummary.getTt5());
        values.add(antenatalSummary.getTtProtected());
        values.add(antenatalSummary.getTtComplete());
        values.add(antenatalSummary.getFirstVisit1stTrim());
        values.add(antenatalSummary.getFirstVisit2ndTrim());
        values.add(antenatalSummary.getFirstVisit3rdTrim());
        values.add(antenatalSummary.getVermoxGiven());
        values.add(antenatalSummary.getSiteId());
        result = DatabaseUtils.create(conn, sql, values.toArray());
        return result;
    }

    /**
     * Reset the anc_report table
     * @param conn
     * @throws SQLException
     */
    public static void deleteAll(Connection conn) throws SQLException {
        Object result = 0;
        ArrayList values;
        values = new ArrayList();
        String sql = "delete from zeports.anc_report;";
        result = DatabaseUtils.create(conn, sql, values.toArray());
    }
}
