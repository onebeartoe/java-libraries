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
    }
}
