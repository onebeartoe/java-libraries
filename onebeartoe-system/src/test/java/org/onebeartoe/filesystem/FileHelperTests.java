/*
 */

package org.onebeartoe.filesystem;

import org.testng.annotations.Test;

/**
 *
 * @author Roberto Marquez
 */
public class FileHelperTests 
{
    FileHelper implementation;
    
    public FileHelperTests() 
    {
        implementation = new FileHelper();
    }
    
    @Test(groups = {"unit"})
    public void isAudioFileSpecification()
    {
        String au = "some.au";        
        assert( implementation.isAudioFile(au) );
        
        String mp3 = "some.mp3";
        assert( implementation.isAudioFile(mp3) );
        
        String notAudio = "some.not";
        assert( implementation.isAudioFile(notAudio) == false);
        
        String ogg = "some.ogg";
        assert( implementation.isAudioFile(ogg) );
        
        String threegp = "some.3gp";
        assert( implementation.isAudioFile(threegp) );
        
        String mid = "some.mid";
        assert( implementation.isAudioFile(mid) );
    }
        
    @Test(groups = {"unit"})
    public void isMultimediaFileSpecification()
    {
        String mpg = "some.mpg";
        assert( implementation.isMultimediaFile(mpg) );
        
        String mpeg = "some.mpeg";
        assert( implementation.isMultimediaFile(mpeg) );
        
        String avi = "some.avi";
        assert( implementation.isMultimediaFile(avi) );
    }
}
