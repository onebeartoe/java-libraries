
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
public interface DesktopApplication
{
    public int x = 0;
    public int y = 0;
    
    public final int DEFAULT_WIDTH = 300;
    public final int DEFAULT_HEIGHT = 400;
    
    public static String buildPropertiesPath(String applicationId)
    {
        if(applicationId == null)
        {
            throw new IllegalArgumentException("be sure to set the ID on the WindowProperties object");
        }        
        
        String userHome = System.getProperty("user.home");
        String path = userHome + "/.onebeartoe/" + applicationId;
        
        return path;
    }
    
    public static String buildPropertiesPath(WindowProperties wp)
    {        
        String path = buildPropertiesPath(wp.id);
        
        return path;
    }
 
    default void persistWindowProperties(WindowProperties wp) throws IOException
    {        
        String path = buildPropertiesPath(wp);
        File outDir = new File(path);
        
        outDir.mkdirs();
        
        if(wp.applicationName == null)
        {
            throw new IllegalArgumentException("set the application name on the WindowProperits object");
        }
        
        File outfile = new File(outDir, wp.applicationName);
        
        SerializedObjects.saveObject(outfile, wp);
    }
    
    default WindowProperties restoreWindowProperties(String applicationId) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        String inpath = buildPropertiesPath(applicationId);
        File dir = new File(inpath);
        
        int start = applicationId.lastIndexOf(".") + 1;
        String filename = applicationId.substring(start);
        File infile = new File(dir, filename);
        InputStream input = new FileInputStream(infile);
                
        WindowProperties wp = (WindowProperties) SerializedObjects.retrieve(input);
        if(wp.applicationName == null)
        {
            wp.applicationName = filename;
        }
        
        return wp;
    }
}