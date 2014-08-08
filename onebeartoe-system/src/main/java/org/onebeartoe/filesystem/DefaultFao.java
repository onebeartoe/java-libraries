
package org.onebeartoe.filesystem;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Object of this class can recursively search a filesystem directory for 
 * files with a user specified extension.
 * 
 * @author Roberto Marquez
 */
public class DefaultFao implements FilesystemAccessObject 
{

	/**
	 * @param directory
	 * @param text
	 * @return
	 */
	public Collection <File> findFilesByExtention(File directory, String extension, boolean recursivly) 
	{
		if(directory == null)
		{
			throw new IllegalArgumentException("dir arguement cannot be null");
		}
		
		if( !directory.isDirectory())
		{
			throw new IllegalArgumentException("dir arguement must represent a directory on the filesystem");
		}		
		
		ArrayList<File> directories = new ArrayList<File>();
		ArrayList<File> targetFiles = new ArrayList<File>();
		directories.add(directory);
		while( !directories.isEmpty() )			
		{
			File currentDirectory = directories.remove(0);
			File [] files = currentDirectory.listFiles();
			
                        if(files == null)
                        {
                            System.out.println("List files returned null: " + currentDirectory.getAbsolutePath() );
                        }
                        else
                        {
                            for(File file : files)
                            {
                                if(file.isDirectory() && recursivly)
                                {
                                        directories.add(file);
                                }
                                else
                                {
                                        String filename = file.getName().toLowerCase(); 
                                        if( filename.endsWith( extension.toLowerCase() ) )
                                        {
                                                targetFiles.add(file);
                                        }
                                }
                            }
                        }
		} 

		return targetFiles;
	}
	

	public Collection<File> findEmptyDirectories(File parentDirectory, boolean recursivly) 
	{
		ArrayList<File> emptyDirectories = new ArrayList<File>();		
		if( !parentDirectory.exists() ) 
		{
			System.out.println("parent directory doesn't exist: \n" + parentDirectory.getPath() );
			System.out.println("not continuing.");
			System.exit(1);
		}
		String parentName = parentDirectory.getName();
		
		File [] dirs = FileUtility.findDirs(parentDirectory);
		
		File [] files;
		int deleteCount = 0;
		for(int x=0; x<dirs.length; x++) 
		{
			files = dirs[x].listFiles();
			if( files.length == 0 ) 
			{				
				emptyDirectories.add(dirs[x]);
				deleteCount++;				
			}			
		}
		
		return emptyDirectories;
	}	 
		
}
