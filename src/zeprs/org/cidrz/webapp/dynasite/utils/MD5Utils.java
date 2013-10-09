package org.cidrz.webapp.dynasite.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * kudos: http://www.jroller.com/JamesGoodwill/entry/md5_comparisons_with_jakarta_commons
 * and http://www.javalobby.org/forums/thread.jspa?threadID=84420
 * @date Apr 27, 2009
 */
public class MD5Utils {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(MD5Utils.class);
    private static final char HEX_FF = 0xff;
    private static final int BYTES_IN_KB = 1024;


	/**
	 * This method generates an md5Hex checksum of the compareFile and compares it to the supplied digest checksum.
	 * kudos: http://www.jroller.com/JamesGoodwill/entry/md5_comparisons_with_jakarta_commons
	 * @param compareFile -
	 * @param digest
	 * @return
	 */
    public static boolean isFileValid(String compareFile, String digest) {
        try {
            byte[] buffer = FileUtils.readFile(new File(compareFile));
            String md5sum = DigestUtils.md5Hex(buffer);
            return md5sum.equalsIgnoreCase(digest);
        }
        catch (IOException e) {
            throw new RuntimeException("Unable to process file for MD5 comparison.", e);
        }
    }

    /**
     * This method generates an md5Hex checksum of the compareFile and compares it to the supplied digest checksum file
     * kudos: http://www.jroller.com/JamesGoodwill/entry/md5_comparisons_with_jakarta_commons
     * @param file
     * @param digest
     * @return
     */
    public static boolean isFileValidChecksum(String compareFile, String checksumFile) {
    	try {
    		byte[] buffer = FileUtils.readFile(new File(compareFile));
    		String md5sum = DigestUtils.md5Hex(buffer);
    		String digest = FileUtils.readTextFile(checksumFile);
    		return md5sum.equalsIgnoreCase(digest);
    	}
    	catch (IOException e) {
    		throw new RuntimeException("Unable to process file for MD5 comparison.", e);
    	}
    }

    /**
     * kudos: http://www.javalobby.org/forums/thread.jspa?threadID=84420
     * @param f
     * @return
     * @throws NoSuchAlgorithmException
     * @throws FileNotFoundException
     */
    public static String writeMD5(File f) throws NoSuchAlgorithmException, FileNotFoundException {
    	String output = null;
    	MessageDigest digest = MessageDigest.getInstance("MD5");
    	InputStream is = new FileInputStream(f);
    	byte[] buffer = new byte[8192];
    	int read = 0;
    	try {
    		while( (read = is.read(buffer)) > 0) {
    			digest.update(buffer, 0, read);
    		}
    		byte[] md5sum = digest.digest();
    		BigInteger bigInt = new BigInteger(1, md5sum);
    		output = bigInt.toString(16);
    		//System.out.println("MD5: " + output);
    	}
    	catch(IOException e) {
    		throw new RuntimeException("Unable to process file for MD5", e);
    	}
    	finally {
    		try {
    			is.close();
    		}
    		catch(IOException e) {
    			throw new RuntimeException("Unable to close input stream for MD5 calculation", e);
    		}
    	}
		return output;
    }

    /**
     * kudos: http://sqlunit.cvs.sourceforge.net/
     * @param istream
     * @return
     */
    public static String getMD5CheckSum(final InputStream istream) {
    		MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		byte[] clearbytes = readBytesFromStream(istream);
    		byte[] digest = md.digest(clearbytes);
    		StringBuffer buffer = new StringBuffer("md5:");
    		for (int i = 0; i < digest.length; i++) {
    			String hex = Integer.toHexString(digest[i] & HEX_FF);
    			if (hex.length() < 2) { hex = '0' + hex; }
    			buffer.append(hex);
    		}
    		try {
				istream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return buffer.toString();

    }

    /**
     * kudos: http://sqlunit.cvs.sourceforge.net/
     * Reads an InputStream and returns an array of bytes.
     * @param istream the InputStream to read.
     * @return a byte array.
     * @exception SQLUnitException if there was a problem with reading.
     */
    public static byte[] readBytesFromStream(final InputStream istream) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[BYTES_IN_KB];
            int len = 0;
            try {
            	while ((len = istream.read(buf, 0, BYTES_IN_KB)) != -1) {
                    bos.write(buf, 0, len);
                }
				istream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            byte[] bytes = bos.toByteArray();
            return bytes;

    }





}
