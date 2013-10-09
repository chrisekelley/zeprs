package org.cidrz.webapp.dynasite.utils.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.utils.Zip;


public class FileList {
	private String pathCurrent;
	private String pathNew;
	private File dirCurrent;
	private File dirNew;
	private FileWriter out;
	private FileWriter outNew;
	private FileWriter temp;
	private HashMap<String, String> orig = new HashMap();
	private HashMap<String, String> upd = new HashMap();
	private ArrayList<String[]> removedFiles = new ArrayList<String[]>();
	private static  ArrayList<String[]> newFiles = new ArrayList<String[]>();
	private static  ArrayList<String[]> diffFiles = new ArrayList<String[]>();


	private boolean foundOrig = false;
	private boolean foundUpd = false;
	private String origFileName;
	private String origDate;
	private String updFileName;
	private String updDate;

	private Iterator i;
	private Iterator j;


	public static String lineSeparator = (String) java.security.AccessController.doPrivileged(
			new sun.security.action.GetPropertyAction("line.separator"));

	public static void main(String args[]) throws IOException{
		args[0] = "c:\\users\\lachko\\desktop\\vb\\";
		args[1] = "c:\\users\\lachko\\desktop\\vb1\\";
		args[2] = "c:\\users\\lachko\\desktop\\vb\\listing.txt";
		args[3] = 	"c:\\users\\lachko\\desktop\\vb1\\listingNew.txt";

		if (args[0] == null || args[1] == null || args[2] == null || args [3] == null) {
			System.out.print("Please enter all arguments");
			return;
		}

		FileList application = new FileList(args[0],args[1]);
		application.createFile(args[2],true);
		application.createFile(args[3],false);
		application.listAllFiles(new File(args[0]), true);
		application.listAllFiles(new File(args[1]), false);
		application.closeFile();
		application.loadFiles(new File(args[2]), new File(args[3]) );
		application.processFileDiff( );
		application.removeDeleted();
		String[] list[] = new String[newFiles.size() + diffFiles.size()][0];
		int j = 0;
		for (int i = 0; i < newFiles.size(); i++) {
			list[i] = newFiles.get(i);
			j++;
		}
		for (int i = 0; i < diffFiles.size(); i++) {
			list[j] = diffFiles.get(i);
			j++;
		}

		Zip.createZip(list,args[1], Constants.MASTER_ARCHIVE_ZIP_PATH);
		Zip.extractFiles(list, args[1]+"outfile.zip", args[0]);

	}

	public File getDir() {
		return dirCurrent;
	}

	public void setDir(File dir) {
		this.dirCurrent = dir;
	}

	public FileList(String pathCur, String pathN) {
		setPath(pathCur);
		setPathNew(pathN);
	}

	void setPath(String path){
		this.pathCurrent= path;
	}

	void setPathNew(String path){
		this.pathNew= path;
	}

	String getPathCur(){
		return this.pathCurrent;
	}

	String getPathNew(){
		return this.pathNew;
	}

	void createFile(String path, boolean Old) {

		try {
			if (Old) {
				dirCurrent = new File(path);
				out = new FileWriter(path);
			}else{
				//dirNew = new File(path);
				outNew = new FileWriter(path);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void loadFiles (File original, File update) throws FileNotFoundException{

		Scanner o = new Scanner( original );
		Scanner u = new Scanner( update );

		while (o.hasNext()) {
			StringTokenizer strOrig = new StringTokenizer(o.nextLine(), "|");

			String strOrigFileName = strOrig.nextToken();
			String strOrigDate = strOrig.nextToken();

			orig.put(strOrigFileName, strOrigDate);
		}

		while (u.hasNext()) {
			StringTokenizer strUpd = new StringTokenizer(u.nextLine(), "|");
			String strUpdFileName = strUpd.nextToken();
			String strUpdDate = strUpd.nextToken();

			upd.put(strUpdFileName, strUpdDate);
		}
		o.close();
		u.close();
	}

	void listAllFiles(File f, boolean Old) {


		try {
			if (f.isDirectory()){
				File allFiles[] = f.listFiles();
				for(File aFile : allFiles){
					listAllFiles(aFile, Old);
				}
			}else if (f.isFile()) {
				int cnt = (Old) ? getPathCur().length():getPathNew().length();
				String shortPath = f.getPath().substring(cnt);
				String str = shortPath + "|" + f.lastModified() + lineSeparator;
				if (Old){
					out.write(str);
				}else{
					outNew.write(str);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void closeFile() {
		try {
			out.close();
			outNew.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void processFileDiff() throws IOException{
		i = orig.keySet().iterator();


		//foundOrig = orig.containsKey(updFileName);

		while (i.hasNext()) {
			origFileName = (String) i.next();
			origDate = (String) orig.get(origFileName);

			foundUpd = upd.containsKey(origFileName);
			updDate = (String) upd.get(origFileName);

			if (foundUpd){
				if(updDate.compareTo(origDate) != 0) {
					//add to changed
					String[] array = {origFileName,updDate};
					diffFiles.add(array);// + updDate);
					i.remove();
					upd.remove(origFileName);


				}else if(updDate.compareTo(origDate) == 0) {
					i.remove();
					upd.remove(origFileName);


				}
			}else if(!foundUpd){
				String[] array = {origFileName,origDate};
				removedFiles.add(array);


			}
		}

		//add the new files to array
		//
		j = upd.keySet().iterator();

		if (j.hasNext()){
			//updFileName = (String) j.next();
			//updDate = (String) upd.get(updFileName);

			while(j.hasNext()) {
				updFileName = (String) j.next();
				updDate = (String) upd.get(updFileName);

				String[] array = {updFileName,updDate};
				newFiles.add(array);
			}
		}

		this.listDiff(newFiles, "new");
		this.listDiff(removedFiles, "removed");
		this.listDiff(diffFiles, "diff");
	}

	void listDiff(ArrayList<String[]> differece,String name) throws IOException {
		temp = new FileWriter(dirCurrent.getParent() + "\\" + name +".txt");

		String[] list[] = new String[differece.size()][0];
		for (int i = 0; i < differece.size(); i++) {
			list[i] = differece.get(i);
		}

		for (int i = 0; i < list.length; i++) {
			temp.write(list[i][0] + "|" + list[i][1] + lineSeparator) ;
		}
		temp.close();
	}

	void removeDeleted(){
		boolean deleted = false;
		String[] list[]= new String[1][0];
		for (int i = 0; i < removedFiles.size(); i++) {
			list[0] = removedFiles.get(i);
			if (!list[0][0].equals("new.txt")  && !list[0][0].equals("removed.txt")  && !list[0][0].equals("diff.txt") && !list[0][0].equals("listing.txt")){
				deleted = new File(getPathCur() + list[0][0]).delete();
			}
		}
	}
}