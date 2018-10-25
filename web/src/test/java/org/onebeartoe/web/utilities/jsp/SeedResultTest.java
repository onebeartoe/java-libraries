
package org.onebeartoe.web.utilities.jsp;

import java.util.Optional;
import org.testng.annotations.Test;

/**
 * @author Roberto Marquez
 */
public class SeedResultTest
{
    SeedResult implementation;
    
    public SeedResultTest()
    {
        implementation = new SeedResult();
        implementation.type = SeedResults.CREATED;
    }
    
    @Test(groups = {"unit"})
    public void toString_pass()
    {
        implementation.error = Optional.empty();
        
        String expected = "CREATED: \nnull\n";
        String actual = implementation.toString();
        
        assert( expected.equals(actual) );
    }
    
    @Test(groups = {"unit"})
    public void toString_fail()
    {
        Exception e = new Exception("error 1");
        implementation.error = Optional.of(e);
        
        String expected = "CREATED: \nnull\n" +
                          "error 1\n";
        String actual = implementation.toString();
        
        assert( expected.equals(actual) );        
    }
}
