
package org.onebeartoe.filesystem;

import org.testng.annotations.Test;

/**
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
    
    @Test(groups = {"unit"})
    public void isMultimediaFileSpecification_fail()
    {
        String not1 = "s.g";
        assert( implementation.isMultimediaFile(not1) == false);

        String not2 = "long.extension";
        assert(implementation.isMultimediaFile(not2) == false);
    }
    
    @Test(groups = {"unit"})
    public void isTextFile()
    {
        String txt = "some.txt";
        assert( implementation.isTextFile(txt) );
        
        String htm = "some.htm";
        assert( implementation.isTextFile(htm) );
        
        String java = "some.java";
        assert( implementation.isTextFile(java) );
        
        String html = "some.html";
        assert( implementation.isTextFile(html) );
    }
    
    @Test(groups = {"unit"})
    public void isZipFile()
    {
        String zip = "some.zip";
        assert( implementation.isZipFormatFile(zip) );
        
        String jar = "some.jar";
        assert( implementation.isZipFormatFile(jar) );
    }
}
