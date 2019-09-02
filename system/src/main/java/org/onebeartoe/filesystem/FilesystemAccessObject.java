
package org.onebeartoe.filesystem;

import java.io.File;
import java.util.Collection;

/**
 * @author roberto
 */
public interface FilesystemAccessObject
{
	
	Collection<File> findEmptyDirectories(File parentDirectory, boolean recursivly);
	
	Collection<File> findFilesByExtention(File parentDirectory, String extension, boolean recursivly);

}
