/**
 * 
 */
package org.cidrz.webapp.dynasite.valueobject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.DateUtils;
import org.cidrz.webapp.dynasite.utils.DynasiteSourceGenerator;
import org.cidrz.webapp.dynasite.utils.FileUtils;
import org.cidrz.webapp.dynasite.utils.GenerateStrutsConfig;
import org.cidrz.webapp.dynasite.utils.SqlGenerator;
import org.cidrz.webapp.dynasite.utils.XmlUtils;

/**
 * Track changes to Forms managed by Dynasite
 * These are persisted as an XML file with every change to a form.
 * @author chrisk
 * 
 */
public class FormChanges {

	private Long formId;
	private List<Long> formList = new ArrayList<Long>();
	private Timestamp modified;
	
	/**
	 * @return the formId
	 */
	public Long getFormId() {
		return formId;
	}
	/**
	 * @param formId the formId to set
	 */
	public void setFormId(Long formId) {
		this.formId = formId;
	}
	/**
	 * @return the formList
	 */
	public List<Long> getFormList() {
		return formList;
	}
	/**
	 * @param formList the formList to set
	 */
	public void setFormList(List<Long> formList) {
		this.formList = formList;
	}
		
	/**
	 * @return the modified
	 */
	public Timestamp getModified() {
		return modified;
	}
	/**
	 * @param modified the modified to set
	 */
	public void setModified(Timestamp modified) {
		this.modified = modified;
	}
	/**
	 * Adds formId to list of forms that need their XML files re-rendered.
	 * @param formId
	 * @throws IOException 
	 * @throws IOException
	 */
	public static void makeDirty(Long formId) throws IOException {
		Boolean dev = DynaSiteObjects.getDev();
		String path = null;
		String path2 = null;
		if (dev == true) {
            path = Constants.DEV_XML_PATH;
            path2 = Constants.DYNASITE_XML_PATH;
        } else {
            path = Constants.DYNASITE_XML_PATH;
        }
		String filePath = path + "FormChanges.xml";
		FormChanges formChanges = null;
		try {
			formChanges = (FormChanges) XmlUtils.getOne(filePath);
		} catch (IOException e) {
			Class cause = e.getClass();
			if (cause.toString().equals("class java.io.FileNotFoundException")) {
				formChanges = new FormChanges();
			} else {
				e.printStackTrace();
			}			
		}
		List<Long> formChangesList = formChanges.getFormList();
		Boolean makeChange = true;
		if (!formChangesList.contains(formId)) {
			formChangesList.add(formId);
			formChanges.setModified(DateUtils.generateTimestamp());
			XmlUtils.save(formChanges, filePath);
			if (path2 != null) {
				filePath = path2 + "FormChanges.xml";
				XmlUtils.save(formChanges, filePath);
			}
		}
		
		/*for (Long currentFormId : formChangesList) {
			if (currentFormId.longValue() == )
			
		}
		formChangesList.add(formId);
		XmlUtils.save(formChanges, filePath);
		if (path2 != null) {
			filePath = path2 + "FormChanges.xml";
			XmlUtils.save(formChanges, filePath);
		}*/
	}
	

	/**
	 * Query the FormChanges.xml file to see if any forms are "dirty;" if yes, generate all of the required files.
	 * @param dev
	 * @return
	 */
	public static String processFormChanges(Boolean dev) {
		// setup messaging
        StringBuffer sbuf = new StringBuffer();
		String path = null;
		String path2 = null;
		if (dev == true) {
            path = Constants.DEV_XML_PATH;
            path2 = Constants.DYNASITE_XML_PATH;
        } else {
            path = Constants.DYNASITE_XML_PATH;
        }
		String filePath = path + "FormChanges.xml";
		String message = null;
		FormChanges formChanges = null;
		try {
			formChanges = (FormChanges) XmlUtils.getOne(filePath);
		} catch (IOException e) {
			Class cause = e.getClass();
			if (cause.toString().equals("class java.io.FileNotFoundException")) {
				formChanges = new FormChanges();
			} else {
				e.printStackTrace();
			}			
		}
		List<Long> formChangesList = formChanges.getFormList();
		if (formChangesList.size()>0) {
			Connection conn = null;
			try {
				conn = DatabaseUtils.getAdminConnection();
				for (Long currentFormId : formChangesList) {
					// run the form-rendering scripts.
					sbuf.append("Creating xml files\n");
		            message = XmlUtils.outputForms(dev, conn, currentFormId);
		            message = message + XmlUtils.outputDynaSiteConfig(dev);
		            sbuf.append(message);
		            sbuf.append("Creating source files\n");
		            new DynasiteSourceGenerator().createSourceFiles("record", dev, currentFormId);
		            sbuf.append("Creating SQL file\n");
		            new SqlGenerator().createSourceFiles(dev);
		            //sbuf.append("Creating sql delete script\n");
		            //new SqlGenerator().createSqlDeleteScript();
		           // sbuf.append("Creating foo and bar.xml\n");
		            new GenerateStrutsConfig().init(dev);
		            sbuf.append("Creating report files\n");
		          //  new DynasiteSourceGenerator().createSourceFiles("report", false);
		            new DynasiteSourceGenerator().createSourceFiles("report", dev, currentFormId);
				}
				// copy to the archive formUpdates dir
				String archivePath = Constants.ARCHIVE_PATH + "formUpdates/FormChanges" + DateUtils.generateTimestamp().getTime() + ".xml";
				try {
					FileUtils.copyQuick(filePath, archivePath);
					formChanges = new FormChanges();
					// reset the form changes list
					XmlUtils.save(formChanges, filePath);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			} catch (ServletException e) {
	            e.printStackTrace();
	        } catch (ObjectNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (PersistenceException e) {
				e.printStackTrace();
			} finally {
	            try {
					if (conn != null && !conn.isClosed()) {
					    conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
	        }			
		}
		return message;
	}
}
