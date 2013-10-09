/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.utils.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.report.DbOutput;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.XmlUtils;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Nov 28, 2005
 *         Time: 12:23:41 PM
 */
public class DummyJob implements Job {

    static Log logger = LogFactory.getLog(DummyJob.class);

    public void execute(JobExecutionContext context)
            throws JobExecutionException {

            // Every job has it's own job detail
            JobDetail jobDetail = context.getJobDetail();

            // The name is defined in the job definition
            String jobName = jobDetail.getName();

            // Log the time the job started
            logger.info(jobName + " fired at " + new Date());


            // Log the time the job ended
            logger.info(jobName + " completed at " + new Date());

    }
}
