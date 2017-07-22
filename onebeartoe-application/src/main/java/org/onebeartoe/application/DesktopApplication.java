
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
    protected String id;
    
    protected WindowProperties wp;
    
    public DesktopApplication(String id)
    {
        this.id = id;
        
        wp = new WindowProperties();
    }
    
    public String buildPropertiesPath(WindowProperties wp)
    {        
        if(id == null)
        {
            throw new IllegalArgumentException("be sure to set the ID on the WindowProperties object");
        }        
        
        String userHome = System.getProperty("user.home");
        
        String parentPath = userHome + "/.onebeartoe/";
        
        File parentDir = new File(parentPath);
        parentDir.mkdirs();
        
        String path = parentPath + id;
        
        return path;
    }

    @Deprecated("use GragicsEvnironment.centerPoint()")
    public abstract int defaultX();
    
    @Deprecated("use GragicsEvnironment.centerPoint()")
    public abstract int defaultY();
    
    public abstract int defaultWidth();
    
    public abstract int defaultHeight();    
    
    public WindowProperties loadWindowProperties()//WindowProperties wp)
            throws FileNotFoundException, IOException, ClassNotFoundException
    {
        String inpath = buildPropertiesPath(wp);
        File infile = new File(inpath);
        
        InputStream input = new FileInputStream(infile);
                
        wp = (WindowProperties) SerializedObjects.retrieve(input);
        
        return wp;
    }

    public void persistWindowProperties() throws IOException
    {        
        String path = buildPropertiesPath(wp);
        File outfile = new File(path);
        
        SerializedObjects.saveObject(outfile, wp);
    }

    /**
     * For consistency, use getClass().getName() for the id value.
     */    
    public void setApplicationId(String id)
    {
        this.id = id;
    }    
}
