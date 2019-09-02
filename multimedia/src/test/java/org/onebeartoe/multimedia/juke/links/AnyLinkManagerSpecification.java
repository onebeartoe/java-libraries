
package org.onebeartoe.multimedia.juke.links;

import java.io.IOException;
import java.net.URL;
import org.testng.annotations.Test;

/**
 * @author Roberto Marquez
 */
public class AnyLinkManagerSpecification 
{
    private AnyLinkManager implementation;
    
    public AnyLinkManagerSpecification()
    {
        implementation = new AnyLinkManager() 
        {
            @Override
            public LinkParser getLinkParser() 
            {
                return new LinkParser() 
                {
                    @Override
                    public LinkManager.LinkUnit extractLinks(String name, URL url) throws IOException 
                    {
                        return new LinkManager.LinkUnit();
                    }
                };         
            }
        };
    }
    
    @Test(groups = {"unit"})
    public void add()
    {
        implementation.add(null);
    }
    
    @Test(groups = {"unit"})
    public void clear()
    {
        implementation.clear();
    }
    
    @Test(groups = {"unit"})
    public void getLinsUnitFor()
    {
        implementation.getLinksUnitFor("unitName");
    }
    
    @Test(groups = {"unit"})
    public void getLinkUnitNames()
    {
        implementation.getLinkUnitNames();
    }
    
    @Test(groups = {"unit"})
    public void remove()
    {
        implementation.remove(null);
    }
    
    @Test(groups = {"unit"})
    public void setRootUrl() throws Exception
    {
        implementation.setRootUrl("localhost", "fun");
    }
}
