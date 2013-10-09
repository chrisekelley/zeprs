/**
 *
 */
package org.cidrz.project.zeprs.report;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.cidrz.webapp.dynasite.dao.SiteDAO;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.Site;

/**
 * @author chrisk
 *
 */
public class AggregateReport {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			int reportId = 0;
			int siteId = 0;
			Date beginDate = null;
			Date endDate = null;
			Boolean allSites = false;

			switch (args.length) {

			case 3:
				reportId = Integer.valueOf(args[0]);
				beginDate   = Date.valueOf(args[1]);
				endDate   = Date.valueOf(args[2]);
				break;
			case 4:
				reportId = Integer.valueOf(args[0]);
				siteId   = Integer.valueOf(args[1]);
				beginDate   = Date.valueOf(args[2]);
				endDate   = Date.valueOf(args[3]);
				break;

			default:
				String name = AggregateReport.class.getClass().getName();
			System.out.println("Usage: " + name + " [option] <pid>");
			System.out.println("where option must be one of:");
			System.out.println("For a single site, enter a comma-delimited list of variables that corresponds to the following:");
			System.out.println("reportId (1), siteId, beginDate (2006-01-01), and endDate");
			System.out.println("For all sites, enter a comma-delimited list of variables that corresponds to the following:");
			System.out.println("reportId (2), beginDate (2006-01-01), and endDate");
			}

			ZEPRSReport report = null;
			HashMap reportProps = new HashMap();

			switch (reportId) {
			case 1:
				report = new MonthlyAncReport();
				report.setSiteId(siteId);
				//report.setSiteName(siteName);
				reportProps = new HashMap();
				reportProps.put("print", Boolean.TRUE);
				reportProps.put("year", Boolean.TRUE);
				reportProps.put("save", Boolean.TRUE);
				System.out.println("Startng report.");
				report.setReportProperties(reportProps);
				//name = "SaveSummary";
				try {
						report.loadReport(beginDate, endDate);
					} catch (RuntimeException e) {
						// TODO Auto-generated catch block
						System.out.println("error running loadReport");
						e.printStackTrace();
						System.err.println(e);
					}
				System.out.println("Finished Report.");
				break;

			case 2:
				List sites = null;
				Connection conn = null;
				System.out.println("Fetching active site id's.");
				try {
					DataSource dataSource = null;
					dataSource = DatabaseUtils.getZEPRSDataSource();
					conn = dataSource.getConnection();
					sites = SiteDAO.getAllActive(conn);
					conn.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					try {
						if (!conn.isClosed()) {
							System.out.println("Trying to close conn.");
							conn.close();
							System.out.println("Closed conn.");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				for (Iterator iter = sites.iterator(); iter.hasNext();) {
					Site site = (Site) iter.next();
					report = new MonthlyAncReport();
					report.setSiteId(site.getId().intValue());
					if (site.getId() != null) {
						reportProps = new HashMap();
						reportProps.put("print", Boolean.TRUE);
						reportProps.put("year", Boolean.TRUE);
						reportProps.put("save", Boolean.TRUE);
						System.out.println("Startng report.");
						report.setReportProperties(reportProps);
						//name = "SaveSummary";
						try {
							report.loadReport(beginDate, endDate);
						} catch (RuntimeException e) {
							// TODO Auto-generated catch block
							System.out.println("error running loadReport");
							e.printStackTrace();
							System.err.println(e);
						}
						System.out.println("Finished Report for site id:" + site.getId());
					}
					System.out.println("Finished All Reports");
				}
				break;

			default:
				System.out.println("Incorrect reportId");
				break;
			}
			System.out.println("Finished Application.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error running AggregateReport");
			e.printStackTrace();
			System.err.println(e);
		}
	}

}
