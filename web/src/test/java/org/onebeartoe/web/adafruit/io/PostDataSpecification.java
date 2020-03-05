
package org.onebeartoe.web.adafruit.io;

import java.io.IOException;
import org.testng.annotations.Test;

/**
 *
 * @author Roberto Marquez
 */
public class PostDataSpecification
{
    private PostData implementation;
    
    public PostDataSpecification()
    {
        implementation = new PostData();
    }
    
    @Test(expectedExceptions = {IOException.class})
    public void post_fail() throws IOException
    {
        implementation.post();
    }
}
