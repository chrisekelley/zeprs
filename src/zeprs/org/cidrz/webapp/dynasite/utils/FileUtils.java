package org.cidrz.webapp.dynasite.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.report.ReportItem;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.struts.action.admin.ImportPatientAction;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Site;


import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.net.URL;
import java.nio.channels.FileChannel;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Feb 16, 2006
 *         Time: 6:19:29 PM
 */
public class FileUtils {

    /**
     * Commons Logging instance.
     */
    public static Log log = LogFactory.getFactory().getInstance(FileUtils.class);

    /**
     * kudos: http://www.javazoid.com/foj_file.html
     * BufferedReader allows the function to read from a buffer instead of directly reading from some input stream that the FileReader represents.
     * In some network conditions, this can greatly speed up I/O. From your local hard disk, it probably doesn't make much difference.
     * However, because we don't know if you are loading from a local drive or from a drive mapped to a server halfway around the world, we buffer the FileReader.
     * @param fullPathFilename
     * @return
     * @throws IOException
     */
    public static String readTextFile(String fullPathFilename) throws IOException {
    	StringBuffer sb = new StringBuffer(1024);
    	BufferedReader reader = new BufferedReader(new FileReader(fullPathFilename));

    	char[] chars = new char[1024];
    	int numRead = 0;
    	while( (numRead = reader.read(chars)) > -1){
    		sb.append(String.valueOf(chars));
    	}

    	reader.close();
    	return sb.toString();
    }

    /**
	 * kudos: http://www.jroller.com/JamesGoodwill/entry/md5_comparisons_with_jakarta_commons
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static byte[] readFile(File file) throws IOException {
	    InputStream is = new FileInputStream(file);
	    byte[] bytes = new byte[(int)file.length()];
	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length &&
	        (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
	        offset += numRead;
	    }
	    is.close();
	    return bytes;
	}

    public static String createDirectory(String path) {
        String result = null;
        File aDir = new File(path);
        boolean created = false;
        try {
            created = aDir.mkdir();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (created) {
            //System.out.println(aDir.getName() + " created.");
            result = aDir.getName() + " created.";
        } else {
            //System.out.println(aDir.getName() + " already exists.");
            result = aDir.getName() + " already exists.";
        }
        return result;
    }

    /**
     * @return List of reports
     */
    public static List listReports() {

        // The list of files can also be retrieved as File objects
        //File[] files = dir.listFiles();


        List reports = new ArrayList();
        File name = new File(Constants.REPORTS_XML_PATH);
        String[] directory = name.list();
        List files = new ArrayList();
        for (int i = 0; i < directory.length; i++) {
            String fileName = directory[i];
            File reportFile = new File(Constants.REPORTS_XML_PATH, fileName);
            files.add(reportFile);
        }
        Collections.sort(files);
        for (int j = 0; j < files.size(); j++) {
            File reportFile = (File) files.get(j);
            ReportItem report = new ReportItem();
            report.setFileName(reportFile.getName());
            report.setLastModified(new Date(reportFile.lastModified()));
            report.setLength(reportFile.length());
            reports.add(report);
        }
        for (int i = 0; i < directory.length; i++) {
            String fileName = directory[i];
            File reportFile = new File(Constants.REPORTS_XML_PATH, fileName);
            ReportItem report = new ReportItem();
            report.setFileName(reportFile.getName());
            report.setLastModified(new Date(reportFile.lastModified()));
            report.setLength(reportFile.length());
            reports.add(report);
        }

        return reports;
    }

    /**
       Given a file name, attempt to find the file name in the class
       path.  If the file name is found, return the absolute path on
       the local file system.  If the file is not found in the class
       path, return null.

     Kudos: http://www.bearcave.com/software/java/misl/index.html

       @param filename the file name for the resource
     */

    private String getPathForResource( String filename )
    {
        ClassLoader loader = this.getClass().getClassLoader();
        URL url = loader.getResource( filename );
        String path = null;
        if (url != null) {
            path = url.getFile();
        }
        return path;
    } // getPathForResource

    /**
     * Watch the file length!
     * kudos: http://www.bandddesigns.com/ml/arch/002099.html
     * @param from
     * @param to
     * @throws IOException
     */
    public static void copy(String from, String to) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(from);
            out = new FileOutputStream(to);
            int length = 128 * 10240; // danger!
            byte[] bytes = new byte[length];
            int read = 0;
            for (; ;) {
                read = in.read(bytes, 0, length);
                if (read == -1) {
                    break;
                }
                out.write(bytes, 0, read);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * Quick way to copy files
     * @param source
     * @param dest
     * @throws IOException
     */
    public static void copyQuick(String source, String dest) throws Exception {
        FileChannel in = new FileInputStream(source).getChannel();
        FileChannel out = new FileOutputStream(dest).getChannel();
        in.transferTo(0, in.size(), out);
        in.close();
        out.close();
    }

    /**
     * create/refresh the archive filesystem.
     * @return messages generated by this process.
     */
    public static StringBuffer createArchive() {
        List clinics = DynaSiteObjects.getSiteList();
        StringBuffer message = new StringBuffer();
        message.append("<p>Directory Creation</p>");
        message.append("<ul>\n");
        for (int i = 0; i < clinics.size(); i++) {
            Site site =  (Site) clinics.get(i);
            message.append("<li>").append(createDirectory(Constants.ARCHIVE_PATH + site.getAbbreviation())).append("</li>\n");
            message.append("<ul>\n");
            message.append("<li>").append(createDirectory(Constants.ARCHIVE_PATH + site.getAbbreviation() + "/deletions")).append("</li>\n");
            message.append("<li>").append(createDirectory(Constants.ARCHIVE_PATH + site.getAbbreviation() + "/import")).append("</li>\n");
            message.append("<li>").append(createDirectory(Constants.ARCHIVE_PATH + site.getAbbreviation() + "/local")).append("</li>\n");
            message.append("<li>").append(createDirectory(Constants.ARCHIVE_PATH + site.getAbbreviation() + "/log")).append("</li>\n");
            message.append("<li>").append(createDirectory(Constants.ARCHIVE_PATH + site.getAbbreviation() + "/syncReports")).append("</li>\n");
            message.append("</ul>\n");
        }
        message.append("<li>").append(createDirectory(Constants.ARCHIVE_PATH  + "/temp")).append("</li>\n");
        message.append("<li>").append(createDirectory(Constants.ARCHIVE_PATH  + "/cache")).append("</li>\n");
        message.append("<li>").append(createDirectory(Constants.ARCHIVE_PATH  + "/appupdates")).append("</li>\n");
        message.append("<li>").append(createDirectory(Constants.ARCHIVE_PATH  + "/appupdates/log")).append("</li>\n");
        message.append("<li>").append(createDirectory(Constants.ARCHIVE_PATH  + "/appupdates/data")).append("</li>\n");
        message.append("<li>").append(createDirectory(Constants.ARCHIVE_PATH  + "/formUpdates")).append("</li>\n");
        message.append("<li>").append(createDirectory(Constants.ARCHIVE_PATH  + "/logs")).append("</li>\n");
        message.append("</ul>");
        return message;
    }

    /**
     * Delete all xml files in archive except for file changes.
     * @return
     */
    public static StringBuffer cleanArchive() {
    	List clinics = DynaSiteObjects.getSiteList();
    	StringBuffer message = new StringBuffer();
    	message.append("<p>Directory Cleaning</p>");
    	message.append("<ul>\n");
    	for (int i = 0; i < clinics.size(); i++) {
    		Site site =  (Site) clinics.get(i);
    		message.append("<li>").append(Constants.ARCHIVE_PATH + site.getAbbreviation()).append("</li>\n");
    		message.append("<ul>\n");
    		message.append("<li>").append(cleanDir(Constants.ARCHIVE_PATH + site.getAbbreviation() + "/deletions")).append("</li>\n");
    		message.append("<li>").append(cleanDir(Constants.ARCHIVE_PATH + site.getAbbreviation() + "/import")).append("</li>\n");
    		message.append("<li>").append(cleanDir(Constants.ARCHIVE_PATH + site.getAbbreviation() + "/local")).append("</li>\n");
    		message.append("<li>").append(cleanDir(Constants.ARCHIVE_PATH + site.getAbbreviation() + "/log")).append("</li>\n");
    		message.append("<li>").append(cleanDir(Constants.ARCHIVE_PATH + site.getAbbreviation() + "/syncReports")).append("</li>\n");
    		message.append("</ul>\n");
    	}
    	//message.append("<li>").append(cleanDir(Constants.ARCHIVE_PATH  + "/deleted_patient_records")).append("</li>\n");
    	message.append("<li>").append(cleanDir(Constants.ARCHIVE_PATH  + "/temp")).append("</li>\n");
    	message.append("<li>").append(cleanDir(Constants.ARCHIVE_PATH  + "/cache")).append("</li>\n");
    	message.append("</ul>");
    	return message;
    }

    public static void renameTo(File src, File tgt) throws ObjectNotFoundException {
        if (src.exists()) {
            if (tgt.exists()) {
                // System.out.format("%s exists%n", tgt);
                long now = System.currentTimeMillis();
                String modStr = String.valueOf(tgt.lastModified());
                String bakName = tgt.getName() + ".bak";
                File bak = new File(tgt.getParentFile(), bakName);
                if (bak.exists()) {
                	bakName = tgt.getName() + "." + modStr  + ".bak";
                	bak = new File(tgt.getParentFile(), bakName);
                }
                boolean rename = tgt.renameTo(bak);
                //System.out.format("rename target = %b%n", rename);
            }
            boolean renameTo = src.renameTo(tgt);
            //System.out.format("renameTo = %b%n", renameTo);
        } else {
        	// log.debug(src + "doesn't exist.");
        	// throw new ObjectNotFoundException(src + " doesn't exist.");
        	throw new ObjectNotFoundException();
        }
    }

    /**
     * Deletes all files under dir.
     * Returns message if all deletions were successful.
     * If a deletion fails, the method stops attempting to delete and returns an error message.
     * kudos: http://exampledepot.com/egs/java.io/DeleteDir.html
     * @param dirName
     * @return
     */
    public static String cleanDir(String dirName) {
    	File dir = new File(dirName);
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                //boolean success = deleteDir(new File(dir, children[i]));
            	File file = new File(dir, children[i]);
            	boolean success = file.delete();
                if (!success) {
                	String filePath = dirName.concat(children[i]);
                    return "Unable to delete " + children[i];
                }
            }
        }
        return "Deleted " + dirName;
    }
}
