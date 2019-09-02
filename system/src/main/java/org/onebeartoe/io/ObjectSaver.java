
package org.onebeartoe.io;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Roberto H. Marquez Created: Febuary 4 2003
 */
public class ObjectSaver
{

    /**
     * Save the object in XML format to the given file uses
     * java.beans.XMLEncoder. &nbsp;Writes the object encoded as XML to the
     * specified output stream. This method closes the output steam after
     * writing the object
     */
    public static boolean encodeObject(Object obj, File outfile)
    {
        boolean object_encoded = false;
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(outfile);
            object_encoded = encodeObject(obj, fileOutputStream);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            object_encoded = false;
            System.out.println(e.toString());
        }

        return object_encoded;
    }

    /**
     * writes the object encoded as XML to the specified output stream. This
     * method closes the output steam after writing the object
     *
     * @param obj
     * @param outstream
     * @return
     */
    public static boolean encodeObject(Object obj, OutputStream outstream)
    {
        boolean object_encoded = false;

        XMLEncoder encoder;
        encoder = new XMLEncoder(outstream);
        encoder.writeObject(obj);
        encoder.close();
        object_encoded = true;

        return object_encoded;
    }

    /**
     * creates a file and name it the value of the String arguement then
     * write/store the instance object to the file. The object must implement
     * java.io.Serializable
     */
    public static boolean saveObject(Object obj, String file_name)
    {
        // construct the name of the file that will hold the inquiry 
        File f = new File(file_name);

        return saveObject(obj, f);
    }

    /**
     * Take the object to store, the relative path to storage directory, and the
     * name of the file
     */
    public static boolean saveObject(Object obj, String dir, String file_name)
    {
        // construct the name of the file that will hold the inquiry 
        File f = new File(dir, file_name);

        return saveObject(obj, f);
    }

    /**
     * saves the specified object to the specified file. The object must
     * implement java.io.Serializable
     */
    public static boolean saveObject(Object obj, File f)
    {
        boolean object_saved = false;
        try
        {
            SerializedObjects.saveObject(f, obj, true);
            object_saved = true;
        } catch (IOException ioe)
        {
            ioe.printStackTrace();
            object_saved = false;
        }
        return object_saved;
    }

}
