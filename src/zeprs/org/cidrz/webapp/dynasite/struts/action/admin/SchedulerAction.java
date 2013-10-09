/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.struts.action.admin;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.webapp.dynasite.utils.admin.ExportPatientIdentifierList;
import org.cidrz.webapp.dynasite.utils.admin.GenerateAirSiteArchiveZip;
import org.cidrz.webapp.dynasite.utils.admin.GenerateRssFile;
import org.cidrz.webapp.dynasite.utils.admin.GenerateSiteArchiveZip;
import org.cidrz.webapp.dynasite.utils.admin.GenerateSiteXml;
import org.cidrz.webapp.dynasite.utils.admin.QuartzUtil;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Nov 28, 2005
 *         Time: 11:40:04 AM
 */
public class SchedulerAction extends Action {

	static Log log = LogFactory.getLog(SchedulerAction.class);

	public ActionForward execute(ActionMapping mapping,
                                 ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

    	String action = "list";
    	if (request.getParameter("action") != null) {
    		action = String.valueOf(request.getParameter("action"));
        }
    	// Retrieve the scheduler from the factory
        Scheduler scheduler = QuartzUtil.getScheduler(request);
    	if (action.equals("start")) {
    		// Start the scheduler
            if (scheduler.isShutdown()) {
            	scheduler = createSchedulerAndUpdateApplicationContext("QuartzScheduler");
			}
            scheduler.start();
            request.setAttribute("message", "Scheduler started.");
    	} else if (action.equals("stop")) {
    		// Start the scheduler
            scheduler.shutdown();
            request.setAttribute("message", "Scheduler shutdown.");
    	} else if (action.equals("export")) {
    			// Define job instance
    			JobDetail job = new JobDetail("job1", "DEFAULT", GenerateSiteXml.class);
    			// Define a Trigger that will fire "now"
    			Trigger trigger = new SimpleTrigger("trigger1", "DEFAULT", new Date());
    			// Schedule the job with the trigger
    			scheduler.scheduleJob(job, trigger);
        		request.setAttribute("message", "Export scheduled for now. Check log for progress.");
                return mapping.findForward("success");
    	} else if (action.equals("update")) {
    		// Define job instance
    		JobDetail job = new JobDetail("GenerateRssFileManual", "DEFAULT", GenerateRssFile.class);
    		// Define a Trigger that will fire "now"
    		Trigger trigger = new SimpleTrigger("GenerateRssFileManualTrigger", "DEFAULT", new Date());
    		// Schedule the job with the trigger
    		scheduler.scheduleJob(job, trigger);
    		request.setAttribute("message", "Update scheduled for now. Check log for progress.");
    		return mapping.findForward("success");
    	} else if (action.equals("archive")) {
    		// Define job instance
    		JobDetail job = new JobDetail("MasterSiteArchiver", "DEFAULT", GenerateSiteArchiveZip.class);
    		// Define a Trigger that will fire "now"
    		Trigger trigger = new SimpleTrigger("MasterSiteArchiverTrigger", "DEFAULT", new Date());
    		// Schedule the job with the trigger
    		scheduler.scheduleJob(job, trigger);
    		request.setAttribute("message", "Zip archive creation scheduled for now. Check log for progress.");
    		return mapping.findForward("success");
    	} else if (action.equals("archiveAir")) {
    		// Define job instance
    		JobDetail job = new JobDetail("MasterAirSiteArchiver", "DEFAULT", GenerateAirSiteArchiveZip.class);
    		// Define a Trigger that will fire "now"
    		Trigger trigger = new SimpleTrigger("MasterAirSiteArchiverTrigger", "DEFAULT", new Date());
    		// Schedule the job with the trigger
    		scheduler.scheduleJob(job, trigger);
    		request.setAttribute("message", "Zip archive creation for Air scheduled for now. Check log for progress.");
    		return mapping.findForward("success");
    	} else if (action.equals("export2sites")) {
    		// Define job instance
    		JobDetail job = new JobDetail("job2", "DEFAULT", GenerateSiteXml.class);
    		job.getJobDataMap().put("export2sites", Boolean.TRUE);
    		// Define a Trigger that will fire "now"
    		Trigger trigger = new SimpleTrigger("trigger1", "DEFAULT", new Date());
    		// Schedule the job with the trigger
    		scheduler.scheduleJob(job, trigger);
    		request.setAttribute("message", "Export scheduled for now. Check log for progress.");
    		return mapping.findForward("success");
    	} else if (action.equals("exportPatientIdentifierList")) {
    		// Define job instance
    		JobDetail job = new JobDetail("ExportPatientIdentifierListManual", "DEFAULT", ExportPatientIdentifierList.class);
    		// Define a Trigger that will fire "now"
    		Trigger trigger = new SimpleTrigger("ExportPatientIdentifierList-Manual-trigger", "DEFAULT", new Date());
    		// Schedule the job with the trigger
    		scheduler.scheduleJob(job, trigger);
    		request.setAttribute("message", "Export Patient Identifier List scheduled for now. Check log for progress.");
    		return mapping.findForward("success");
    	} else if (action.equals("updatearchive")) {
    		// Define job instance
    		JobDetail job = new JobDetail("MasterSiteArchiverManual", "DEFAULT", GenerateSiteArchiveZip.class);
    		// Define a Trigger that will fire "now"
    		Trigger trigger = new SimpleTrigger("MasterSiteArchiverManual-Manual-trigger", "DEFAULT", new Date());
    		// Schedule the job with the trigger
    		scheduler.scheduleJob(job, trigger);
    		request.setAttribute("message", "Updating archive zip scheduled for now. Check log for progress.");
    		return mapping.findForward("success");
       	} else  {
    		// List scheduled jobs.
    		String[] jobGroups = scheduler.getJobGroupNames();
    		StringBuffer sbuf = new StringBuffer();
    		if (jobGroups.length == 0) {
    			sbuf.append("<p>Scheduler is shutdown.</p>");
    		}
			sbuf.append("<p>");
    		for (String groupName : jobGroups) {
				String[] jobs = scheduler.getJobNames(groupName);
				sbuf.append("<li>Job Group: " + groupName + "</li>");
				sbuf.append("<ul>");
				for (String job : jobs) {
					JobDetail jobDetail = scheduler.getJobDetail(job, groupName);
					String key = job;
					sbuf.append("<li>Job key: " + key + "</li>");
					sbuf.append("<ul>");
					Trigger[] triggers = scheduler.getTriggersOfJob(groupName, job);
					for (Trigger trigger : triggers) {
						sbuf.append("<li>trigger key: " + trigger.getFullName() + "</li>");
						sbuf.append("<li>trigger NextFireTime: " + trigger.getNextFireTime() + "</li>");
						sbuf.append("<li>trigger PreviousFireTime: " + trigger.getPreviousFireTime() + "</li>");
						sbuf.append("<li>trigger StartTime: " + trigger.getStartTime() + "</li>");
						sbuf.append("<li>trigger EndTime: " + trigger.getEndTime() + "</li>");
					}
					sbuf.append("</ul>");
				}
				sbuf.append("</ul>");
    		}
    		String[] triggerGroups = scheduler.getTriggerGroupNames();

			for (String groupName : triggerGroups) {
				String[] triggerNames = scheduler.getTriggerNames(groupName);
				for (String triggerName : triggerNames) {
					Trigger trigger =
						scheduler.getTrigger(triggerName, groupName);
					sbuf.append("<ul>");

					sbuf.append("<li>trigger key: " + trigger.getFullName() + "</li>");
					sbuf.append("<li>trigger NextFireTime: " + trigger.getNextFireTime() + "</li>");
					sbuf.append("<li>trigger PreviousFireTime: " + trigger.getPreviousFireTime() + "</li>");
					sbuf.append("<li>trigger StartTime: " + trigger.getStartTime() + "</li>");
					sbuf.append("<li>trigger EndTime: " + trigger.getEndTime() + "</li>");
					sbuf.append("</ul>");

			}
		}
			sbuf.append("</p>");
    		String message = sbuf.toString();
    		request.setAttribute("message", message);
       	}
        // Forward to success page
        return mapping.findForward("success");
    }

    /**
     * kudos: org.quartz.ui.web.action.schedule.ScheduleBase
     * @param schedulerName
     * @return
     */
    public static Scheduler createSchedulerAndUpdateApplicationContext(String schedulerName) {
		Scheduler currentScheduler = null;

		   try {
				if 	(schedulerName != null && schedulerName.length() > 0) {
					currentScheduler = new StdSchedulerFactory().getScheduler(schedulerName);
				} else {
					currentScheduler = StdSchedulerFactory.getDefaultScheduler();
				}
		   } catch (SchedulerException e) {
			   log.error("Problem creating scheduler",e);
		   }

		return currentScheduler;
	}
}

