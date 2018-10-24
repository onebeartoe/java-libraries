
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
        
        Exception e = new Exception("error 1");
        implementation.error = Optional.of(e);
    }
    
    @Test(groups = {"unit"})
    public void toString_pass()
    {
        String expected = "CREATED: \nnull\n" +
                          "error 1\n";
        String actual = implementation.toString();
        
        assert( expected.equals(actual) );
    }
}
