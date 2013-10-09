package org.cidrz.webapp.dynasite.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.ant.Project;
import org.cidrz.webapp.dynasite.Constants;

import com.sun.syndication.io.WireFeedOutput;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Mar 17, 2006
 *         Time: 11:48:00 AM
 */
public class Zip {
    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(Zip.class);
    static final int BUFFER = 2048;

    public static void addFile(String filePath, String fileName, ZipOutputStream zipFile) throws IOException {
        // change to the path
        // File f = new File(filePath + ".");
        // Create a buffer for reading the files
        byte[] buf = new byte[2048];
        // Compress the file
        FileInputStream in = new FileInputStream(filePath + fileName);
        // Add ZIP entry to output stream.
        zipFile.putNextEntry(new ZipEntry(fileName));
        // Transfer bytes from the file to the ZIP file
        int len;
        while ((len = in.read(buf)) > 0) {
            zipFile.write(buf, 0, len);
        }
        // Complete the entry
        zipFile.closeEntry();
        in.close();
    }


    /**
     * zips a directory.
     * Uses the ant Zip, which is pretty easy-to-use and effective.
     * example: Zip.zip(Constants.BACKUP_DIR, Constants.BACKUP_DIR + "db-" + DateUtils.getNowFileFormat() + ".zip", Constants.DATABASE_NAME + File.separator + "**");
     * @param dirToZip
     * @param destinationZip
     * @param comma- or space-separated list of patterns of files that must be included. All files are included when omitted.
     * @throws IOException
     */
    static public void zip(String dirToZip, String destinationZip, String includeList) throws IOException {

    	org.apache.tools.ant.taskdefs.Zip zip = new org.apache.tools.ant.taskdefs.Zip();
    	zip.setDestFile(new File(destinationZip));
    	zip.setBasedir(new File(dirToZip));
    	zip.setIncludes(includeList);
    	zip.setProject(new Project());
    	zip.execute();
    }

    /**
     * Zips up contents of directory and writes out the checksum to disk.
     * Does NOT handle nested directories.
     * kudos: http://java.sun.com/developer/technicalArticles/Programming/compression/
     * @param path - path to directory to zip.
     */
    public static void zipChecksum (String zipPath, String zipFilename, String checksumFilename) {
    	try {
    		BufferedInputStream origin = null;
    		FileOutputStream dest = new FileOutputStream(zipFilename);
    		CheckedOutputStream checksum = new CheckedOutputStream(dest, new Adler32());
    		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(checksum));
    		//out.setMethod(ZipOutputStream.DEFLATED);
    		byte data[] = new byte[BUFFER];
    		// get a list of files from current directory
    		File f = new File(zipPath);
    		String files[] = f.list();

    		for (int i=0; i<files.length; i++) {
        		f = new File(zipPath, files[i]);
    			if ( !f.isDirectory() ) {
    				log.debug("Adding: "+zipPath +files[i]);
        			FileInputStream fi = new FileInputStream(zipPath + files[i]);
        			origin = new BufferedInputStream(fi, BUFFER);
        			ZipEntry entry = new ZipEntry(zipPath + files[i]);
        			entry.setTime(f.lastModified());
        			out.putNextEntry(entry);
        			int count;
        			while((count = origin.read(data, 0, BUFFER)) != -1) {
        				out.write(data, 0, count);
        			}
        			origin.close();
        			fi.close();
    			}
    		}
    		out.close();
    		dest.close();
    		log.debug("checksum: " + checksum.getChecksum().getValue());
    		Writer writer = new FileWriter(Constants.MASTER_ARCHIVE_CHECKSUM_PATH);
    		writer.write(String.valueOf(checksum.getChecksum().getValue()));
			writer.close();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }


    /**
     * extracts a zip archive.
     * kudos: http://java.sun.com/developer/technicalArticles/Programming/compression/
     * @param argv
     */
    public static void unZip (String file) {
    	final int BUFFER = 2048;

    	try {
    		BufferedOutputStream dest = null;
    		FileInputStream fis = new
    		FileInputStream(file);
    		ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
    		ZipEntry entry;
    		while((entry = zis.getNextEntry()) != null) {
    			log.debug("Extracting: " +entry);
    			int count;
    			byte data[] = new byte[BUFFER];
    			// write the files to the disk
    			FileOutputStream fos = new
    			FileOutputStream(entry.getName());
    			dest = new BufferedOutputStream(fos, BUFFER);
    			while ((count = zis.read(data, 0, BUFFER)) != -1) {
    				dest.write(data, 0, count);
    			}
    			dest.flush();
    			dest.close();
    		}
    		zis.close();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }





    /**
     * Recursively zips a directory
     * Usage: CreateZipFile(new File("t.zip"), new File[]{new File("t")}, false);
     * Kudos: http://en.allexperts.com/q/Java-1046/zip-directories-java.htm
     * @param Zip_File
     * @param To_Be_Zipped_Files
     * @param Skip_Dirs
     * @param pathToTruncate - truncate this path from zip file paths
     */
    public static void CreateZipFile(File Zip_File, File[] To_Be_Zipped_Files, boolean Skip_Dirs, String pathToTruncate)
    {
    	try
    	{
    		// Open archive file
    		FileOutputStream stream=new FileOutputStream(Zip_File);
    		ZipOutputStream out=new ZipOutputStream(stream);

    		for (int i=0;i<To_Be_Zipped_Files.length;i++)
    		{
    			log.debug("Adding "+To_Be_Zipped_Files[i].getName());
    			zipEntry(Zip_File, To_Be_Zipped_Files[i], out, pathToTruncate);
    		}
    		out.close();
    		stream.close();
    		log.debug("Finished zipping : " + Zip_File.getAbsolutePath());
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    		log.debug("Error: " + e.getMessage());
    		return;
    	}
    }

    /**
     * Zips file
     * @param Zip_File
     * @param file
     * @param out
     * @param pathToTruncate
     * @throws Exception
     */
    public static void zipEntry(File Zip_File, File file, ZipOutputStream out, String pathToTruncate) throws Exception
    {
    	if (file.isDirectory() && (!file.getName().equals("deletions") && !file.getName().equals("import")
    			&& !file.getName().equals("log")
    			&& !file.getName().equals("appupdates")
    			&& !file.getName().equals("formUpdates")
    			&& !file.getName().equals("patients_deleted")
    			&& !file.getName().equals("patients_restored")
    			&& !file.getName().equals("ZEP")
    			&& !file.getName().equals("WEB-INF"))
    	)
    	{
    		listContents(Zip_File, file, out, pathToTruncate);
    		return;
    	}

    	int BUFFER_SIZE=10240000;                                     // 10 M
    	byte buffer[]=new byte[BUFFER_SIZE];

    	// Add archive entry
    	//ZipEntry zipAdd=new ZipEntry(file.getAbsolutePath());
    	String newPath = file.getAbsolutePath().replace(pathToTruncate, "");
    	ZipEntry zipAdd=new ZipEntry(newPath);
    	//zipAdd.setTime(file.lastModified());
    	out.putNextEntry(zipAdd);
    	// Read input & write to output
    	FileInputStream in= new FileInputStream(file.getAbsolutePath());

    	/*int len;
    	int nRead=in.available();

    	while((len = in.read(buffer)) > 0)
    	{
    		out.write(buffer, 0, nRead);
    	}
    	in.close();
    	out.closeEntry();*/
    	// Transfer bytes from the file to the ZIP file
		int len;
		while ((len = in.read(buffer)) > 0) {
			out.write(buffer, 0, len);
		}

		// Complete the entry
		out.closeEntry();
		in.close();
    }

    public static void listContents( File Zip_File, File dir, ZipOutputStream out, String pathToTruncate )
    throws Exception
    {
    	// Assume that dir is a directory.  List
    	// its contents, including the contents of
    	// subdirectories at all levels.
    	if (!dir.getName().equals("deletions")
    			&& !dir.getName().equals("import")
    			&& !dir.getName().equals("log")
    			&& !dir.getName().equals("appupdates")
    			&& !dir.getName().equals("formUpdates")
    			&& !dir.getName().equals("patients_deleted")
    			&& !dir.getName().equals("patients_restored")
    			&& !dir.getName().equals("ZEP")
    			&& !dir.getName().equals("WEB-INF"))
    	{
        	log.debug("Directory \"" + dir.getName() + "\"");
    		String[] files;  // The names of the files in the directory.
        	files = dir.list();
        	for (int i = 0; i < files.length; i++) {
        		File f;  // One of the files in the directory.
        		f = new File(dir, files[i]);

        		if ( f.isDirectory() )
        		{
        			// Call listContents() recursively to
        			// list the contents of the directory, f.
        			listContents(Zip_File, f, out, pathToTruncate);
        		}
        		else
        		{
        			// For a regular file, just print the name, files[i].
        			zipEntry(Zip_File, f, out, pathToTruncate);
        		}
        	}
    	}
    } // end listContents()


    /**
     * Lachko's Zip app
     */
    public static void createZip(String[] fileNames[], String path, String zipFile) {
    	//String[][] fileNames;
    	// Create a buffer for reading the files
    	byte[] buf = new byte[1024];
    	//boolean hasChanged = true;
    	//fileNames = files;

    	//if(files.length == 0) {hasChanged = false;}

    	//if(hasChanged) {
    	try {
    		// Create the ZIP file
    		//String outFilename = path + "\\outfile.zip";
    		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));

    		// Compress the files
    		for (int i=0; i<fileNames.length; i++) {
    			FileInputStream in = new FileInputStream(path + fileNames[i][0]);

    			// Add ZIP entry to output stream.
    			out.putNextEntry(new ZipEntry(fileNames[i][0]));

    			// Transfer bytes from the file to the ZIP file
    			int len;
    			while ((len = in.read(buf)) > 0) {
    				out.write(buf, 0, len);
    			}

    			// Complete the entry
    			out.closeEntry();
    			in.close();
    		}

    		// Complete the ZIP file
    		out.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	//}
    }

    @SuppressWarnings("unused")
    public static void extractFiles(String[] fileNames[], String zip, String path){
    	//if(hasChanged) {
    	try {
    		// Open the ZIP file
    		String inFilename = zip;
    		ZipInputStream in = new ZipInputStream(new FileInputStream(inFilename));

    		// Get the first entry
    		ZipEntry entry;

    		// Open the output file
    		String outFilename = path;
    		OutputStream out = null;
    		int i = 0;

    		while ((entry = in.getNextEntry()) != null) {
    			outFilename = outFilename + entry;

    			//outFilename = new File(outFilename);
    			File f = new File(outFilename);
    			System.out.print(outFilename  + "\n");
    			int cnt = 0;
    			String shortPath = "";
    			boolean te = false;
    			if (f.getParent() != null){
    				File f1 = new File( f.getParent());

    				cnt = path.length();
    				shortPath = f.getPath().substring(cnt);

    				//create folder structure
    				f1.mkdirs();
    				//}
    			}
    			out = new FileOutputStream(f);

    			// Transfer bytes from the ZIP file to the output file
    			byte[] buf = new byte[1024];
    			int len;
    			while ((len = in.read(buf)) > 0) {
    				out.write(buf, 0, len);
    			}

    			outFilename = path;
    			//Arrays.sort( fileNames);
    			//int j = Arrays. (fileNames, shortPath);
    			int j = searchFileNameArray(fileNames, shortPath);

    			Long l = new Long(fileNames[j][1]);

    			out.close();
    			f.setLastModified(l);
    			System.out.print( i++ + "\n");
    		}
    		// Close the streams
    		out.close();
    		in.close();
    		new File(inFilename).delete();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

    public static int searchFileNameArray(String[] fileNames[], String searchElement){
    	for (int i = 0; i < fileNames.length; i++) {
    		if (fileNames[i][0].equals(searchElement)){
    			return i;
    		}
    	}
    	return -1;
    }

}
