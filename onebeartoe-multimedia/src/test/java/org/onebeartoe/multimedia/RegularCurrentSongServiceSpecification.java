
package org.onebeartoe.multimedia;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.onebeartoe.multimedia.juke.services.RegularCurrentSongService;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class RegularCurrentSongServiceSpecification
{
    RegularCurrentSongService implementation;
    
    public RegularCurrentSongServiceSpecification()
    {
        implementation = new RegularCurrentSongService();
    }
    
    @Test(groups = {"unit"})
    public void likeCount() throws Exception
    {
        int count = implementation.likeCount();
        
        assert( count >= 0 );
    }
    
    @Test(groups = {"unit"})
    public void like()
    {
        try 
        {
            implementation.like("Song song", "localhost");
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(RegularCurrentSongServiceSpecification.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test(groups = {"unit"})
    public void next()
    {
        try 
        {
            implementation.next("Some Song", "localhost");
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(RegularCurrentSongServiceSpecification.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test(groups = {"unit"})
    public void nextCount() throws Exception
    {
        int count = implementation.nextCount();
        
        assert( count >= 0 );
    }
}
