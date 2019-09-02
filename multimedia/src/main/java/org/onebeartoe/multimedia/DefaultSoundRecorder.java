
package org.onebeartoe.multimedia;

import java.io.File;

import java.util.Calendar;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

/**
 * @author Roberto Marquez
 */
public class DefaultSoundRecorder implements SoundRecorder
{
	
	private TargetDataLine targetDataLine;
	
	private DataLine.Info dataLineInfo;
	
	private AudioFormat audioFormat;
	
	private AudioFileFormat.Type fileType;
	
	private String extention;
	
	private boolean recording;
	
	private File outputFile;
	
	public DefaultSoundRecorder(DataLine.Info dataLineInfo, AudioFormat audioFormat, AudioFileFormat.Type fileType, String extention, File outdir)
	{
		this.dataLineInfo = dataLineInfo;
		this.audioFormat = audioFormat;
		this.fileType = fileType;
		this.extention = extention;
//		this.outdir = outdir;
	}

	@Override
	public void start(File outputFile) throws LineUnavailableException 
	{
		targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);

                this.outputFile = outputFile;
                
		// Create a thread to capture the microphone
		// data into an audio file and start the
		// thread running. It will run until the
		// Stop button is clicked. This method
		// will return after starting the thread.
		new CaptureThread().start();
		
		recording = true;
	}

	@Override
	public void stop() 
	{
		if(targetDataLine != null)
		{
			// Terminate the capturing of input data from the microphone.
			targetDataLine.stop();
			targetDataLine.close();
		}
		
		recording = false;
	}

	/**
	 *  Inner class to capture data from microphone
	 *  and write it to an output audio file.	
	 */
	class CaptureThread extends Thread 
	{
		public void run() 
		{					
			Calendar cal = Calendar.getInstance();
			long milli = cal.getTimeInMillis();
			String outfilePath = "audio" + "." + milli + extention;
//			File audioFile = new File(outdir, outfilePath);

			try 
			{
//				System.out.println("outfile path: " + audioFile.getAbsolutePath() );
				targetDataLine.open(audioFormat);
				targetDataLine.start();
				AudioInputStream audioInstream = new AudioInputStream(targetDataLine); 
				AudioSystem.write(audioInstream, fileType, outputFile);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 *  
	 *  This method creates and returns an
	 	AudioFormat object for a given set of format
	 	parameters. If these parameters don't work
 		well for you, try some of the other
	 	allowable parameter values, which are shown
	 	in comments following the declarations.
	 * @return
	 */
	public static AudioFormat getAudioFormat() 
	{
		// 8000,11025,16000,22050,44100
		float sampleRate = 8000.0F;
		
		// 8,16
		int sampleSizeInBits = 16;

		// 1,2
		int channels = 1;

		// true,false
		boolean signed = true;

		// true,false
		boolean bigEndian = false;
		
		AudioFormat audioFormat = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
		
		return audioFormat;
	}

	public boolean isRecording() 
        {
		return recording;
	}
	
	
}
