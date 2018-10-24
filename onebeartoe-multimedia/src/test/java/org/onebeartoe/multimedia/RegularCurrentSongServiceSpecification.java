package org.onebeartoe.multimedia;

import org.onebeartoe.multimedia.juke.services.RegularCurrentSongService;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class RegularCurrentSongServiceSpecification
{
    @Test(groups = {"unit"})
    public void instantiation() throws Exception
    {
        RegularCurrentSongService rcss = new RegularCurrentSongService();
        
        int count = rcss.likeCount();
        
        assert( count >= 0 );
    }
}
