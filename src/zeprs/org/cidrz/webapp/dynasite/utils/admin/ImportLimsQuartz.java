/**
 *
 */
package org.cidrz.webapp.dynasite.utils.admin;

import java.net.ConnectException;
import java.net.NoRouteToHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author ckelley
 * Imports records from LIMS lab system
 * This version is for the Quartz automated process.
 */
public class ImportLimsQuartz implements Job {

	/**
	 * Commons Logging instance.
	 */
	private static Log log = LogFactory.getFactory().getInstance(ImportLimsQuartz.class);

/**
 * Import records from LIMS into ZEPRS.
 * Import only cd4 and hgb records.
 */

	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			ImportLimsRecords.importRecords(Boolean.FALSE,Boolean.TRUE,Boolean.TRUE);
			log.info("Imported LIMS records.");
        } catch (ConnectException e) {
        	log.debug("Unable to connect to LIMS server. Error: " + e);
        } catch (NoRouteToHostException e) {
        	log.debug("Unable to connect to  LIMS server.  Error: " + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
