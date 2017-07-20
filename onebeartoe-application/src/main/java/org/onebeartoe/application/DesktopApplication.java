
package org.onebeartoe.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.onebeartoe.application.ui.WindowProperties;
import org.onebeartoe.io.SerializedObjects;

/**
 * @author rmarquez
 */
public abstract class DesktopApplication
{
    public int x = 0;
    public int y = 0;
    
    public final int DEFAULT_WIDTH = 300;
    public final int DEFAULT_HEIGHT = 400;
    
    public
//            static 
        String buildPropertiesPath(WindowProperties wp)
    {        
        if(wp.id == null)
        {
            throw new IllegalArgumentException("be sure to set the ID on the WindowProperties object");
        }        
        
        String userHome = System.getProperty("user.home");
        
        String parentPath = userHome + "/.onebeartoe/";
        
        File parentDir = new File(parentPath);
        parentDir.mkdirs();
        
        String path = parentPath + wp.id;
        
        return path;
    }

    public WindowProperties loadWindowProperties(WindowProperties wp) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        String inpath = buildPropertiesPath(wp);
        File infile = new File(inpath);
        
//        File dir = new File(inpath);        
//        int start = wp.id.lastIndexOf(".") + 1;
//        String filename = wp.id.substring(start);
//        File infile = new File(dir, filename);
        
        InputStream input = new FileInputStream(infile);
                
        wp = (WindowProperties) SerializedObjects.retrieve(input);
        
//        if(wp.applicationName == null)
//        {
//            wp.applicationName = filename;
//        }
        
        return wp;
    }

    public void persistWindowProperties(WindowProperties wp) throws IOException
    {        
        String path = buildPropertiesPath(wp);
        File outfile = new File(path);
        
//        File outDir = new File(path);
//        
//        outDir.mkdirs();
//        
//        if(wp.applicationName == null)
//        {
//            throw new IllegalArgumentException("set the application name on the WindowProperits object");
//        }
//        
//        File outfile = new File(outDir, wp.applicationName);
        
        SerializedObjects.saveObject(outfile, wp);
    }    
}