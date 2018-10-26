package org.onebeartoe.filesystem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @author Roberto Marquez
 */
public class FileSystemSearcher
{

    File dir;

    List<FileType> targets;

    FileType type;

    boolean recursive;

    public FileSystemSearcher(File dir, List<FileType> targets)
    {
        this(dir, targets, false);
    }

    public FileSystemSearcher(File dir, List<FileType> targets, boolean recursive)
    {
        if (dir == null)
        {
            throw new NullPointerException("search directory can't have a null value");
        }

        if (targets == null)
        {
            throw new NullPointerException("target list can't have a null value");
        }

        this.dir = dir;

        this.targets = targets;

        this.recursive = recursive;
    }

    public List<File> findTargetDirectories()
    {
        ArrayList<File> targetDirs = new ArrayList<File>();
        Vector<File> directories = new Vector<File>();
        directories.add(dir);
        while (!directories.isEmpty())
        {
            File currentDir = directories.remove(0);
            
            boolean currentContainsTargets = findTargetDirectoriesOneLevel(currentDir, directories);
            
            if (currentContainsTargets)
            {
                targetDirs.add(currentDir);
            }
        }

        return targetDirs;
    }
    
    private boolean findTargetDirectoriesOneLevel(File currentDir, Vector<File> directories)
    {
        boolean currentContainsTargets = false;
        
        boolean targetNotFoundYet = true;
                        
        File[] dirContents = currentDir.listFiles();

        if (dirContents != null)
        {
            for (File file : dirContents)
            {
                if (file.isDirectory() && recursive)
                {
                    directories.add(file);
                } 
                else
                {
                    if (targetNotFoundYet)
                    {
                        // Only come here if a target has not been found.						
                        FileType type = determinFileType(file);
                        if (targets.contains(type))
                        {
                            currentContainsTargets = true;
                            // If a target has been found in the current dir, we can skip the next file check
                            targetNotFoundYet = false;
                        }
                    }
                }
            }   
        }
        
        return currentContainsTargets;
    }

    public List<File> findTargetFiles()
    {
        List<File> targetFiles = new ArrayList<File>();
        Vector<File> directories = new Vector<File>();
        directories.add(dir);
        while (!directories.isEmpty())
        {
            findTargetFilesOneLevel(directories, targetFiles);
        }

        return targetFiles;
    }
    
    private void findTargetFilesOneLevel(Vector<File> directories, List<File> targetFiles)
    {
        File currentDir = directories.remove(0);
        File[] dirContents = currentDir.listFiles();
        if (dirContents != null)
        {
            for (File file : dirContents)
            {
                if (file.isDirectory() && recursive)
                {
                    directories.add(file);
                } 
                else
                {
                    FileType type = determinFileType(file);
                    if (targets.contains(type))
                    {
                        targetFiles.add(file);
                    }
                }
            }
        }        
    }

    private FileType determinFileType(File file)
    {
        FileType type = FileType.UNKNOWN;
        String name = file.getName();

        if (FileHelper.isAudioFile(name))
        {
            type = FileType.AUDIO;
        }

        if (FileHelper.isZipFormatFile(name))
        {
            type = FileType.ZIP;
        }

        if (FileHelper.isMultimediaFile(name))
        {
            type = FileType.MULTIMEDIA;
        }

        if (FileHelper.isImageFile(name))
        {
            type = FileType.IMAGE;
        }

        if (FileHelper.isTextFile(name))
        {
            type = FileType.TEXT;
        }
        
        if( file.isDirectory() )
        {
            type = FileType.DIRECTORY;
        }
        
        return type;
    }

}
