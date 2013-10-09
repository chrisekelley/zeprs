/**
 *
 */
package org.cidrz.webapp.dynasite.utils.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * kudos: http://www.java2s.com/Code/Java/File-Input-Output/CompressfilesusingtheJavaZIPAPI.htm
 * @date Feb 10, 2009
 */
public class Compress {

	/**
	 *
	 * @param from
	 * @param to
	 * @throws IOException
	 */
	public static void gzipFile(String from, String to) throws IOException {
		FileInputStream in = new FileInputStream(from);
		GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(to));
		byte[] buffer = new byte[4096];
		int bytesRead;
		while ((bytesRead = in.read(buffer)) != -1)
			out.write(buffer, 0, bytesRead);
		in.close();
		out.close();
	}

	/**
	 * Zip the contents of the directory, and save it in the zipfile
	 * @param dir
	 * @param zipfile
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	public static void zipDirectory(String dir, String zipfile)
	throws IOException, IllegalArgumentException {
		// Check that the directory is a directory, and get its contents
		File d = new File(dir);
		if (!d.isDirectory())
			throw new IllegalArgumentException("Not a directory:  "
					+ dir);
		String[] entries = d.list();
		byte[] buffer = new byte[4096]; // Create a buffer for copying
		int bytesRead;

		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));

		for (int i = 0; i < entries.length; i++) {
			File f = new File(d, entries[i]);
			if (f.isDirectory())
				continue;//Ignore directory
			FileInputStream in = new FileInputStream(f); // Stream to read file
			ZipEntry entry = new ZipEntry(f.getPath()); // Make a ZipEntry
			out.putNextEntry(entry); // Store entry
			while ((bytesRead = in.read(buffer)) != -1)
				out.write(buffer, 0, bytesRead);
			in.close();
		}
		out.close();
	}


	/**
	 *
	 * @param args
	 * @throws IOException
	 */
	public static void main(String args[]) throws IOException {
		String from = ".";
		File f = new File(from);
		boolean directory = f.isDirectory(); // Is it a file or directory?

		Compress.zipDirectory(from, from + ".zip");
		Compress.gzipFile(from, from + ".gz");
	}

	/**
	 * Recursive function to add a directory with all sub-directories to zip z
	 * output format (in the zip) is linux compatible, i.e. with "/" seperating dirs
	 * while the input might be from windows machines
	 * kudos: http://74.125.47.132/search?q=cache:U-5RwfVjupsJ:panmental.de/public/programming_projects/HowTos/useCondorWithSubdirectories.php+java+create+zip+recursive+directory&hl=en&ct=clnk&cd=1&gl=za&client=firefox-a
	 * http://panmental.de/public/programming_projects/HowTos/useCondorWithSubdirectories.php
	 */
	public static void zip(File x,String Dir,ZipOutputStream z){
		try{
			if(!x.exists())
				System.err.println("file not found");
			if(!x.isDirectory()){
				z.putNextEntry(new ZipEntry((Dir+x.getName()).replace('\\','/')));
				FileInputStream y=new FileInputStream(x);
				byte[] a=new byte[(int)x.length()];
				int did=y.read(a);
				if(did!=x.length())
					System.err.println("DID NOT GET WHOLE FILE "+Dir+x.getName()+" ; only "+ did+ " of "+x.length());
				z.write(a,0,a.length);
				z.closeEntry();
				y.close();
				x=null;
			}
			else  //recurse
			{
				String nnn=Dir+x.getName()+File.separator;
				x=null;
				z.putNextEntry(new ZipEntry(nnn.replace('\\','/')));
				z.closeEntry();
				String[] dirlist=(new File(nnn)).list();
				for(int i=0;i<dirlist.length;i++){
					zip(new File(nnn+dirlist[i]),nnn,z);
				}
			}
		}catch(Exception e){System.err.println("Error in zip-Method!!"+e);}
	}

	//only creates the zip and initiates the recursive zipping
	//here all folders beginning with dirsStartingWith are included
	public static void zipAll(String dirsStartingWith, String name){
		try{
			File here=new File(".");
			String[] dirlist=here.list();
			ZipOutputStream z=new ZipOutputStream(new FileOutputStream(name));
			for(int i=0;i<dirlist.length;i++)
				if(dirlist[i].startsWith(dirsStartingWith))
					zip(new File("."+File.separator+dirlist[i]),"."+File.separator,z);
			z.close();
		}catch(Exception e){System.err.println("Error in zipAll-Method!!"+e);}
	}


}
