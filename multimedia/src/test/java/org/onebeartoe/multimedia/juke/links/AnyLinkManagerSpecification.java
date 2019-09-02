
package org.onebeartoe.multimedia.juke.links;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
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
        int currentCount = implementation.getLinkUnitNames().size();
        
        String name = "some-unit";
        
        LinkManager.LinkUnit unit = new LinkManager.LinkUnit();
        
        unit.name = name;
        
        implementation.add(unit);
        
        int actual = implementation.getLinkUnitNames().size();
        
        int expected = currentCount + 1;
        
        assertEquals(actual, expected);
    }
    
    @Test(groups = {"unit"})
    public void clear()
    {
        String name = "some-unit";
        
        LinkManager.LinkUnit unit = new LinkManager.LinkUnit();
        
        unit.name = name;
        
        implementation.add(unit);

        int currentSize = implementation.getLinkUnitNames().size();
        
        assertNotEquals(currentSize, 0);
        
        implementation.clear();
        
        currentSize = implementation.getLinkUnitNames().size();
        
        assertEquals(currentSize, 0);
    }
    
    @Test(groups = {"unit"})
    public void getLinksUnitFor()
    {
        String name = "some-unit";
        
        LinkManager.LinkUnit unit = new LinkManager.LinkUnit();
        
        unit.name = name;
        
        implementation.add(unit);
        
        LinkManager.LinkUnit actualUnit = implementation.getLinksUnitFor(name);
        
        String expected = name;
        
        String actual = actualUnit.name;
                
        assertEquals(actual, expected);
    }
    
    @Test(groups = {"unit"})
    public void getLinkUnitNames()
    {
        List<String> linkUnitNames = implementation.getLinkUnitNames();
        
        assertNotNull(linkUnitNames);
    }
    
    @Test(groups = {"unit"})
    public void remove()
    {
        String name = "some-unit";
        
        LinkManager.LinkUnit unit = new LinkManager.LinkUnit();
        
        unit.name = name;
        
        implementation.add(unit);
        
        int currentCount = implementation.getLinkUnitNames().size();
        
        implementation.remove(unit);
        
        int expected = currentCount - 1;
        
        int actual = implementation.getLinkUnitNames().size();
        
        assertEquals(actual, expected);
    }
    
    @Test(groups = {"unit"})
    public void setRootUrl() throws Exception
    {
        // remove any residual links
        implementation.clear();
        
        String host = "onebeartoe.org";
        
        // this path is to a text file and should have no links
        String path = "/software/development/continuous/source/code/coverage/jacoco/sonar.properties";
        
        implementation.setRootUrl(host, path);
        
        List<String> names = implementation.getLinkUnitNames();
        
        int acutal = names.size();
        
        int expected = 0;
        
        assertEquals(acutal, expected);
    }
}
