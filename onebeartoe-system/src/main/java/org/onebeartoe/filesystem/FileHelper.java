
package org.onebeartoe.filesystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * File name: FileHelper.java Written by: Roberto H. Marquez Last Modified:
 * 4/17/2004 Added split() method to break up a single file into smaller ones.
 * Made FILES_ONLY and DIRECTORIES_ONLY public. Modified: April 29th, 2003;
 * added isTextFile() method Created: September 1st 2002
 */
public class FileHelper 
{

/*
 * use FileType
 * 
 * 	
	public static enum FILE_TYPES
	{
		ALL_FILES ,
		IMAGE_FILES ,
		AUDIO_FILES ,
		MULTIMEIDA_FILES 
	}

	
	public static final int ALL_FILES = 575;
	public static final int IMAGE_FILES = 16575;
	public static final int AUDIO_FILES = 187536575;
	public static final int MULTIMEIDA_FILES = 655951357;
*/
	
	
	/**
	 * Writes the data of two files to a third. The first parameter represents
	 * the first file to write to the new file. The second parameter is the
	 * second file that will be written to the new file. The last parameter is
	 * the new file created or the overwritten existing file.
	 */
	public static boolean concat(File first, File second, File newfile) 
	{
		boolean result;
		int data;

		FileInputStream input1;
		FileInputStream input2;

		try {
			input1 = new FileInputStream(first);
			input2 = new FileInputStream(second);
			FileOutputStream output = new FileOutputStream(newfile);
			while ((data = input1.read()) != -1) {
				output.write(data);
			}
			input1.close();

			while ((data = input2.read()) != -1) {
				output.write(data);
			}
			input2.close();
			output.close();
		} catch (FileNotFoundException fnfe) {
			return false;
		} catch (IOException ioe) {
			return false;
		}
		return true;
	}

	/**
	 * Breaks up the original file into separate files. The files will contain
	 * file_size bytes, and likely one one of those will have
	 * <p>
	 * </p>
	 * <p>
	 * </p>
	 * (total_bytes modulus file_size) bytes.
	 * <p>
	 * </p>
	 * NOTE: any value for file_size less than 500,000 bytes will be adjusted to
	 * 500,000.
	 */
	public static boolean split(File original, int file_size) {
		int FILE_SIZE_MIN = 500000;
		int byte_count = 0;
		char part = 'a';
		int data;
		FileInputStream input;
		String filename;

		if (file_size < FILE_SIZE_MIN) {
			file_size = FILE_SIZE_MIN;
		}

		try {
			input = new FileInputStream(original);
			filename = original.getName() + '_' + part;
			File outfile = new File(original.getParent(), filename);
			FileOutputStream output = new FileOutputStream(outfile);
			while ((data = input.read()) != -1) {
				if (byte_count < file_size) {
					output.write(data);
					byte_count++;
				} else {
					output.close();
					part++;
					filename = original.getName() + '_' + part;
					outfile = new File(original.getParent(), filename);
					output = new FileOutputStream(outfile);
					output.write(data);
					byte_count = 1;
				}
			}
			output.close();
			input.close();
		} catch (FileNotFoundException fnfe) {
			return false;
		} catch (IOException ioe) {
			return false;
		}
		return true;
	}

	public static boolean copyFile(File original, File parent) {
		try {
			FileInputStream input = new FileInputStream(original);
			File outputFile = new File(parent, original.getName());
			FileOutputStream output = new FileOutputStream(outputFile);
			int data;
			while ((data = input.read()) != -1) {
				output.write(data);
			}
			input.close();
			output.close();
			return true;
		} catch (FileNotFoundException fnfe) {
			return false;
		} catch (IOException ioe) {
			return false;
		}
	}

	/** supports .txt and .java files */
	public static boolean isTextFile(String filename) 
	{
		int length = filename.length();
		String ending;
		boolean is = false;

		if (length > 4) 
		{
			ending = filename.substring(length - 4, length);
			if (ending.equalsIgnoreCase(".txt")) 
			{
				is = true;
			} 
			else if (ending.equalsIgnoreCase(".htm")) 
			{
				
				is = true;
			}
		}

		if (length > 5) 
		{
			ending = filename.substring(length - 5, length);
			if (ending.equalsIgnoreCase(".java")) 
			{
				is = true;
			} 
			else if (ending.equalsIgnoreCase(".html")) 
			{
				is = true;
			}
		}

		return is;
	}

	/**
	 * supports .zip and .jar files
	 */
	public static boolean isZipFormatFile(String filename) 
	{
		boolean isZip = false;
		int length = filename.length();

		if (length > 4) 
		{
			String ending = filename.substring(length - 4, length);

			if (ending.equalsIgnoreCase(".zip")
					|| ending.equalsIgnoreCase(".jar")) 
			{
				isZip = true;
			}
		}

		return isZip;
	}

	/**
	 * Tests to see if the filename has a any of the standard file extensions of
	 * common image formats
	 * 
	 * @param filename
	 *            must have a length of at least 4 due to this method looking or
	 *            a '.' before the file extentsion
	 * @return true if the sting contains any of the standard file extensions
	 *         for JPEG, GIF, PNG, and Bitmap image formats
	 */
	public static boolean isImageFile(String filename) {
		boolean isImage = false;
		if (!(filename.length() > 4)) {
			return false;
		}

		int i = filename.length();
		String ending = filename.substring(i - 4, i);

		if (ending.equalsIgnoreCase(".gif")) {
			isImage = true;
		} else if (ending.equalsIgnoreCase(".bmp")) {
			isImage = true;
		} else if (ending.equalsIgnoreCase(".jpg")) {
			isImage = true;
		} else if (ending.equalsIgnoreCase(".png")) {
			isImage = true;
		}

		// the ending needs a new call to substring for the files with 4 digit
		// extensions
		ending = filename.substring(i - 5, i);
		if (ending.equalsIgnoreCase(".jpeg")) {
			isImage = true;
		}

		return isImage;
	}

	public static boolean hasIndexFile(File dir) {
		File f = new File(dir.getPath() + "\\index.html");
		return f.exists() ? true : false;
	}

	public static boolean isAudioFile(String filename) 
	{
		if (!(filename.length() > 3)) {
			return false;
		}

		int i = filename.length();
		String ending = filename.substring(i - 3, i);
		if (ending.equalsIgnoreCase(".au")) {
			return true;
		}

		ending = filename.substring(i - 4, i);

		if (ending.equalsIgnoreCase(".mp3"))
			return true;

		if (ending.equalsIgnoreCase(".wav")) {
			return true;
		}
		
		if (ending.equalsIgnoreCase(".ogg")) {
			return true;
		}
		
		if (ending.equalsIgnoreCase(".3gp")) {
			return true;
		}

		if (ending.equalsIgnoreCase(".mid"))
			return true;

		int index = i-5;
		if(index >= 0)
		{
			ending = filename.substring(index, i);
			if (ending.equalsIgnoreCase(".fake"))
			{
				return true;
			}	
		}
		
		
		return false;
	}

	public static boolean isMultimediaFile(String filename) 
	{
		if (!(filename.length() > 3)) 
		{
			return false;
		}

		int i = filename.length();
		String ending = filename.substring(i - 3, i);
		if (ending.equalsIgnoreCase(".--")) 
		{
			return true;
		}

		ending = filename.substring(i - 4, i);
		if (ending.equalsIgnoreCase(".avi"))
		{
			return true;
		}

		if (ending.equalsIgnoreCase(".mpg")) 
		{
			return true;
		}

		int index = i-5;
		if(index >= 0)
		{
			ending = filename.substring(index, i);
			if (ending.equalsIgnoreCase(".mpeg"))
			{
				return true;
			}
		}
		

		return false;
	}

}
