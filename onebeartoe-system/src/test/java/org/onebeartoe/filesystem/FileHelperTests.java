
package org.onebeartoe.filesystem;

import java.io.File;
import org.testng.annotations.Test;

/**
 * @author Roberto Marquez
 */
public class FileHelperTests 
{
    FileHelper implementation;
    
    File pom = new File("pom.xml");
    
    public FileHelperTests() 
    {
        implementation = new FileHelper();
    }
    
    @Test(groups = {"unit"})
    public void concat()
    {
        File infile1 = pom;
        
        File outfile = new File("target/pom-pom.xml");
        
        implementation.concat(infile1, infile1, outfile);
    }
    
    @Test(groups = {"unit"}, expectedExceptions = NullPointerException.class)
    public void concat_fail_NPE()
    {
        implementation.concat(null, null, null);
    }
    
    @Test(groups = {"unit"})
    public void concat_fail_FNF()
    {
        File fnf = new File("does-not.exist");
        File outfile = new File("target/fnf.out");
        
        boolean success = implementation.concat(fnf, fnf, outfile);
        
        assert(success == false);
    }
    
    @Test(groups = {"unit"})
    public void hasIndex()
    {
        File f = new File(".");
        implementation.hasIndexFile(f);
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
        
        String wav = "some.wav";
        assert( implementation.isAudioFile(wav));
    }
    
    @Test(groups = {"unit"})
    public void isImageFile()
    {
        String gif = "some.gif";
        assert( implementation.isImageFile(gif));
        
        String bmp = "some.bmp";
        assert( implementation.isImageFile(bmp) );
        
        String jpg = "some.jpg";
        assert( implementation.isImageFile(jpg) );
        
        String jpeg = "some.jpeg";
        assert( implementation.isImageFile(jpeg) );
        
        String png = "some.png";
        assert( implementation.isImageFile(png) );
    }
    
    @Test(groups = {"unit"})
    public void isImageFile_fail()
    {
        String not1 = "i.i";
        assert( implementation.isImageFile(not1) == false );
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
    
    @Test(groups = {"unit"})
    public void split()
    {        
        File infile = new File("pom.xml");
        implementation.split(infile, 5);
    }
    
    @Test(groups = {"unit"}, expectedExceptions = NullPointerException.class)
    public void split_fail()
    {        
        implementation.split(null, 0);
    }
}
