
package org.onebeartoe.multimedia;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import sun.audio.AudioStream;

public class SunAudioClipPlayer implements AudioPlayer 
{

	private AudioClip instanceClip;
	AudioStream as;
	
	public SunAudioClipPlayer(URL url)	
	{
		if(url == null)
		{
			throw new IllegalArgumentException("URL cannot be null for AudioClipPlayer's constructor.");
		}
		
		InputStream in;
		 
		try 
		{
			in = url.openStream();
			as = new AudioStream(in);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		InputStream in = new FileInputStream(Filename);

		         

		
//		instanceClip = Applet.newAudioClip(url);
//		if(instanceClip == null)
//		{
//			throw new IllegalArgumentException("URL provided for AudioClipPlayer object might not be an audio clip: \n\t\t" + url);
//		}		
	}
	
	public static void play(URL url) 
	{
		SunAudioClipPlayer clipPlayer = new SunAudioClipPlayer(url);				
		clipPlayer.play();
		
	}	

	
	public void play() 
	{		
		System.out.println("method play(auiodstream) of " + getClass().getName() + " called.");

		
		sun.audio.AudioPlayer.player.start(as);
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
