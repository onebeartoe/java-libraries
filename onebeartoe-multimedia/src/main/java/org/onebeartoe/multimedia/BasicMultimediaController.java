
package org.onebeartoe.multimedia;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Port;

/**
 * @author Roberto Marquez
 */
public class BasicMultimediaController implements MultimediaController 
{
	
	private FloatControl controlIn;
	
	public BasicMultimediaController()
	{
		final Port lineOut;
		float volume = -1;
		try
		{
			System.out.println();
			if (AudioSystem.isLineSupported(Port.Info.LINE_OUT)) 
			{
				lineOut = (Port) AudioSystem.getLine(Port.Info.LINE_OUT);
				lineOut.open();
				
			} 
			else if (AudioSystem.isLineSupported(Port.Info.HEADPHONE)) 
			{
				lineOut = (Port) AudioSystem.getLine(Port.Info.HEADPHONE);
				lineOut.open();
			} 
			else if (AudioSystem.isLineSupported(Port.Info.SPEAKER)) 
			{
				lineOut = (Port) AudioSystem.getLine(Port.Info.SPEAKER);
				lineOut.open();
			} 
			else 
			{
				System.out.println("Unable to get Output Port");
				lineOut = null;
			}

			if(lineOut != null)
			{
				controlIn = (FloatControl) lineOut.getControl(FloatControl.Type.VOLUME);							
			}
			
			System.out.println("LINE_OUT : volume = " + volume);
			
		}
		catch(final Exception e)
		{
			System.out.println(e  + " LINE_OUT");
		}
	}
	
	@Override
	public int currentVolume() 
	{
		float volume = -1;
		
		if(controlIn != null)
		{
			volume = 100 * (controlIn.getValue() / controlIn.getMaximum());
		}
		
		return (int) volume;
	}

	public void setVolume(int level) 
	{
		if(level >= 0 && level <= 100)
		{
			if(controlIn != null)
			{
				float fv =  level / (float) 100.0;
				controlIn.setValue(fv);
			}
		}
	}

}

