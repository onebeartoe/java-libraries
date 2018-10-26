
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
        List<FileType> targets = new ArrayList();
        targets.add(ft1);
        
        boolean recursive = true;
        
        implementation = new FileSystemSearcher(dataDirectory, targets, recursive);        
    }
    
    @Test(groups = {"unit"})
    public void findTargetDirectories()
    {                        
        List<File> imageDirs = implementation.findTargetDirectories();
        
        assert(imageDirs.size() == 1);
    }
    
    @Test(groups = {"unit"})
    public void findTargetFiles()
    {
        List<File> imageFiles = implementation.findTargetFiles();
        
        assert(imageFiles.size() == 1);
    }
}
