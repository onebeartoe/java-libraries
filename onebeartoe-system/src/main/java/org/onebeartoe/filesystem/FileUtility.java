
package org.onebeartoe.filesystem;

import java.io.File;
import java.util.Vector;

/**
 * Created on Oct 20, 2006
 */
public class FileUtility 
{
	public static void main(String[] args) 
        {
		
		String parentPath = args[0];
				
		File parentDir = new File(parentPath);
		 
		if( !parentDir.exists() ) {
			System.out.println("parent directory doesn't exit: \n" + parentDir.getPath() );
			System.out.println("not continuing.");
			System.exit(1);
		}
		String parentName = parentDir.getName();
		
		File [] dirs = findDirs(parentDir);
				
		int subdirCount = 0;
		for(int x=0; x<dirs.length; x++) {
			String newFileName = parentDir.getPath() + File.separator + parentName + " - " + dirs[x].getName();
			File newfile = new File(newFileName);
			dirs[x].renameTo(newfile);
		}
		
		System.out.println("Done");		
		
	}
	
	public static File [] findDirs(File dir) {
		File [] allFiles = dir.listFiles();
		Vector dirs = new Vector();
		
		for(int x=0; x<allFiles.length; x++) {
			if( allFiles[x].isDirectory() ) {
				dirs.add(allFiles[x]);
			}			 
		}
		
		File [] f = new File[0];
		
		return (File []) dirs.toArray(f);
	}

}

