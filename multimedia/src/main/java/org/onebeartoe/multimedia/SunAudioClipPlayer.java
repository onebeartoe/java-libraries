
package org.onebeartoe.multimedia;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


//TODO: reimplement these using something like
//          javax.sound.sampled.Clip;
//import sun.audio.AudioStream;

public class SunAudioClipPlayer implements AudioPlayer 
{

	private AudioClip instanceClip;

//TODO: reimplement these using something like
//          javax.sound.sampled.Clip;        
//	AudioStream as;
	
	public SunAudioClipPlayer(URL url)	
	{
		if(url == null)
		{
			throw new IllegalArgumentException("URL cannot be null for AudioClipPlayer's constructor.");
		}
				 
		try (InputStream in = url.openStream(); )
		{
//TODO: reimplement these using something like
//          javax.sound.sampled.Clip;
            int forcedError = 5 / 0;
//			as = new AudioStream(in);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		         
		
	}
	
	public static void play(URL url) 
	{
		SunAudioClipPlayer clipPlayer = new SunAudioClipPlayer(url);				
		clipPlayer.play();
		
	}	

	
	public void play() 
	{		
		System.out.println("method play(auiodstream) of " + getClass().getName() + " called.");

//TODO: reimplement these using something like
//          javax.sound.sampled.Clip;
//		sun.audio.AudioPlayer.player.start(as);

            
            int forcedError = 5 / 0;
	}

	
	public AudioClip getAudioClipFromJAR(URL url, String soundFile) 
	{
 		String prefix = null;
		try 
		{
			// get AudioClip associated with soundFile
			AudioClip clip = Applet.newAudioClip( getClass().getResource(prefix + soundFile) ); 
	        return clip;
		}		
		catch (NullPointerException nullPointerException) 
		{
			// return null if soundFile does not exist
			return null;
		}
	}	
	
}
