
package org.onebeartoe.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Roberto Marquez
 */
public class TextFileReader
{

    public static String readText(InputStream instream)
    {
        try
        {
            InputStreamReader reader = new InputStreamReader(instream);
            BufferedReader input = new BufferedReader(reader);
            StringBuffer text = new StringBuffer();

            String line = input.readLine();
            if (line == null)
            {
                System.out.println("TextFileReader attempts to read empty text file.");
            }

            while (line != null)
            {
                text.append(line);
                text.append(System.getProperty("line.separator"));
                line = input.readLine();
            }

            input.close();

            return text.toString();
        } catch (FileNotFoundException fnf)
        {
            System.out.println("FileNotFoundException within TextFileReader.readText");

            return null;
        } catch (IOException ioe)
        {
            System.out.println("IOException within TextFileReader.readText");
            return null;
        }
    }

    /**
     * @param filename path to file
     * @return text contained in the specified file or null if the file does not
     * exist
     */
    public static String readText(String filename)
    {
        File file = new File(filename);

        if (file.exists())
        {
            return readText(file);
        } else
        {
            return null;
        }
    }

    /**
     *
     * @param f
     * @return the text contained in the specified file, null if the file is not
     * found
     */
    public static String readText(File f)
    {
        try
        {
            FileInputStream instream = new FileInputStream(f);
            return readText(instream);
        } catch (FileNotFoundException fnfe)
        {
            return null;
        }
    }

}
