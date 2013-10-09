package org.cidrz.webapp.dynasite.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.tar.TarEntry;

import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;


/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Mar 17, 2006
 *         Time: 11:48:00 AM
 */
public class Tar {
    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(Tar.class);

    /**
     * Adds a file to a tar archive:
     * Tar.addFile(filePath, fileName, tarFile);
     *
     * @param filePath
     * @param fileName
     * @param tarFile
     * @throws IOException
     */
    public static void addFile(String filePath, String fileName, org.apache.tools.tar.TarOutputStream tarFile) throws IOException {
        // change to the path
        // File f = new File(filePath + ".");
        // Create a buffer for reading the files
        byte[] buf = new byte[2048];
        // Compress the file
        FileInputStream in = new FileInputStream(filePath + fileName);
        // Add tar entry to output stream.
        File f = new File(filePath + fileName);
        TarEntry entry = new TarEntry(f);
        entry.setName(f.getName());
        //tarFile.writeEntry(entry, false);
        //tarFile.writeEntry(entry, false);
        tarFile.putNextEntry(entry);
        // Transfer bytes from the file to the tar file
        int len;
        while ((len = in.read(buf)) > -1) {
            //log.debug("len: " + len + " buf: " + buf.toString());
            tarFile.write(buf, 0, len);
        }
        // Complete the entry
        tarFile.closeEntry();
        in.close();
    }
}
