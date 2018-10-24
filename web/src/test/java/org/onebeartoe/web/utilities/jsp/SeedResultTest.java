
package org.onebeartoe.web.utilities.jsp;

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
    
    @Test
    public void toString_pass()
    {
        String expected = "CREATED: \nnull\n";
        String actual = implementation.toString();
        
        assert( expected.equals(actual) );
    }
}
