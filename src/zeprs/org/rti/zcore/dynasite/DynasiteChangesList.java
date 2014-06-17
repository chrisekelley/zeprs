/**
 *
 */
package org.rti.zcore.dynasite;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.utils.DynasiteSourceGenerator;
import org.rti.zcore.dynasite.utils.DynasiteUtils;
import org.cidrz.webapp.dynasite.utils.SqlGenerator;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.DateUtils;
import org.cidrz.webapp.dynasite.utils.FileUtils;
import org.cidrz.webapp.dynasite.utils.GenerateStrutsConfig;
import org.cidrz.webapp.dynasite.utils.XmlUtils;

/**
 * Track changes to Forms managed by Dynasite
 * These are persisted as an XML file with every change to a form.
 * @author chrisk
 *
 */
public class DynasiteChangesList {

	public static Log log = LogFactory.getFactory().getInstance(DynasiteUtils.class);

	private List<DynasiteChange> changesList = new ArrayList<DynasiteChange>();
	private Timestamp modified;

	public List<DynasiteChange> getChangesList() {
		return changesList;
	}
	public void setChangesList(List<DynasiteChange> changesList) {
		this.changesList = changesList;
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
	 * Adds id to list of forms that need their XML files re-rendered.
	 * @param id
	 * @param changeType Type of change - "Form", "Flow", etc.
	 * @throws IOException
	 * @throws IOException
	 */
	public static void makeDirty(Long id, String changeType) throws IOException {
		Boolean dev = DynaSiteObjects.getDev();
		String path = null;
		String path2 = null;
		if (dev == true) {
            path = Constants.DEV_XML_PATH;
            path2 = Constants.DYNASITE_XML_PATH;
        } else {
            path = Constants.DYNASITE_XML_PATH;
        }
		// fetch the changes file
		String filePath = path + "DynasiteChanges.xml";
		DynasiteChangesList changes = null;
		try {
//			changes = (DynasiteChangesList) XmlUtils.getOne(filePath, null, null);
			// changed to ZEPRS method.
			changes = (DynasiteChangesList) XmlUtils.getOne(filePath, null);
		} catch (IOException e) {
			Class cause = e.getClass();
			if (cause.toString().equals("class java.io.FileNotFoundException")) {
				changes = new DynasiteChangesList();
			} else {
				e.printStackTrace();
			}
		}

		List<DynasiteChange> changesList = changes.getChangesList();
		// loop through current changes and see if changes to this object have already been logged.
		Boolean addChange = true;
		for (DynasiteChange dynasiteChange : changesList) {
			if (dynasiteChange.getId().longValue() == id.longValue()) {
				if (dynasiteChange.getChangeType().equals(changeType)) {
					addChange = false;
				}
			}
		}
		if (addChange == true) {
			DynasiteChange newChange = new DynasiteChange();
			newChange.setId(id);
			newChange.setChangeType(changeType);
			changesList.add(newChange);
			changes.setModified(DateUtils.generateTimestamp());
			XmlUtils.save(changes, filePath);
			if (path2 != null) {
				filePath = path2 + "DynasiteChanges.xml";
				XmlUtils.save(changes, filePath);
			}
		}
	}


	/**
	 * Query the DynasiteChanges.xml file to see if any forms are "dirty;" if yes, generate all of the required files.
	 * @param dev
	 * @return
	 * @throws ServletException
	 */
	public static String processFormChanges(Boolean dev) throws ServletException,IOException, IllegalStateException {
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
		String filePath = path + "DynasiteChanges.xml";
		DynasiteChangesList changes = null;
		try {
//			changes = (DynasiteChangesList) XmlUtils.getOne(filePath, null, null);
			changes = (DynasiteChangesList) XmlUtils.getOne(filePath, null);
		} catch (IOException e) {
			Class cause = e.getClass();
			if (cause.toString().equals("class java.io.FileNotFoundException")) {
				changes = new DynasiteChangesList();
			} else {
				e.printStackTrace();
			}
		}
		List<DynasiteChange> changesList = changes.getChangesList();
		if (changesList.size()>0) {
			Connection conn = null;
			try {
				conn = DatabaseUtils.getAdminConnection();
				for (DynasiteChange currentChange : changesList) {
					Long id = currentChange.getId();
					String changeType = currentChange.getChangeType();
					if (changeType.equals("Form")) {
						// run the form-rendering scripts.
						sbuf.append("Creating xml files\n");
			            String outputFormsMessage = DynasiteUtils.outputForms(dev, conn, id);
			            sbuf.append(outputFormsMessage);
			            sbuf.append("Outputting DynaSiteConfig\n");
			            try {
			            	String outputDynaSiteConfigMessage = DynasiteUtils.outputDynaSiteConfig(dev);
			            	sbuf.append(outputDynaSiteConfigMessage);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			            sbuf.append("Creating source files\n");
			            try {
							new DynasiteSourceGenerator().createSourceFiles("record", dev, id);
						} catch (ObjectNotFoundException e) {
							log.debug(e);
							break;
						} catch (Exception e) {
							e.getStackTrace();
							break;
						}
			            sbuf.append("Creating locale file\n");
			            DynasiteSourceGenerator.createLocaleResources(dev, id);
			            sbuf.append("Creating SQL file\n");
			            new SqlGenerator().createSourceFiles(dev);
			            //sbuf.append("Creating sql delete script\n");
			            //new SqlGenerator().createSqlDeleteScript();
			           // sbuf.append("Creating dynaStrutsConfig and dynaValidation.xml\n");
			            new GenerateStrutsConfig().init(dev);
			            sbuf.append("Creating report files\n");
			          //  new DynasiteSourceGenerator().createSourceFiles("report", false);
			            try {
							new DynasiteSourceGenerator().createSourceFiles("report", dev, id);
						} catch (ObjectNotFoundException e) {
							break;
						}
//						if (Constants.OUTPUT_OPENMRS_SOURCE != null) {
//							sbuf.append("Creating OpenMRS files\n");
//				            try {
//								new DynasiteSourceGenerator().createSourceFiles("openmrs", dev, null);
//							} catch (ObjectNotFoundException e) {
//								break;
//							}
//						}
					} else if (changeType.equals("Flow")) {
						XmlUtils.outputFlow(dev, conn, path, path2, sbuf);
					} else if (changeType.equals("FormType")) {
						XmlUtils.outputFormTypes(dev, conn, path, path2, sbuf);
					}
					//todo place ApplicationDefinitionBuilder method here
				}
				// copy to the archive formUpdates dir
				String archivePath = Constants.ARCHIVE_PATH_FORM_UPDATES + "DynasiteChanges" + DateUtils.generateTimestamp().getTime() + ".xml";
				try {
					FileUtils.copyQuick(filePath, archivePath);
					changes = new DynasiteChangesList();
					// reset the form changes list
					XmlUtils.save(changes, filePath);
					if (path2 != null) {
						String filePath2 = path2 + "DynasiteChanges.xml";
						FileUtils.copyQuick(filePath, filePath2);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (ServletException e) {
	            throw new ServletException();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (PersistenceException e) {
				e.printStackTrace();
			} catch (ObjectNotFoundException e) {
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
		return sbuf.toString();
	}
}
