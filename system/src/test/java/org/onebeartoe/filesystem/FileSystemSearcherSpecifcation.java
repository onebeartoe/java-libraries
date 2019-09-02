
package org.onebeartoe.filesystem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;

/**
 * @author Roberto Marquez
 */
public class FileSystemSearcherSpecifcation 
{
    private final File dataDirectory;
    
    private final FileSystemSearcher implementation;
    
    public FileSystemSearcherSpecifcation()
    {
        dataDirectory = new File("src/test/resources/filesystem-searcher");
        assert( dataDirectory.exists() );
        
        FileType ft1 = FileType.IMAGE;
        FileType ft2 = FileType.ZIP;
        FileType ft3 = FileType.MULTIMEDIA;
        
        List<FileType> targets = new ArrayList();
        targets.add(ft1);
        targets.add(ft2);
        targets.add(ft3);
        
        boolean recursive = true;
        
        implementation = new FileSystemSearcher(dataDirectory, targets, recursive);        
    }
    
    @Test(groups = {"unit"}, expectedExceptions = NullPointerException.class)
    public void constructor_fail_nullDirectory()
    {
        File dir = null;
        List<FileType> targets = new ArrayList();
        
        FileSystemSearcher nullDir = new FileSystemSearcher(dir, targets);
    }
    
    @Test(groups = {"unit"}, expectedExceptions = NullPointerException.class)
    public void constructor_fail_nullTargets()
    {
        File dir = new File(".");
        List<FileType> targets = null;
        
        FileSystemSearcher nullTargets = new FileSystemSearcher(dir, targets);
    }    
    
    @Test(groups = {"unit"})
    public void findTargetDirectories()
    {                        
        List<File> imageDirs = implementation.findTargetDirectories();
        
        assert(imageDirs.size() == 4);
    }
    
    @Test(groups = {"unit"})
    public void findTargetFiles()
    {
        List<File> imageFiles = implementation.findTargetFiles();
        
        assert(imageFiles.size() == 4);
    }
}
