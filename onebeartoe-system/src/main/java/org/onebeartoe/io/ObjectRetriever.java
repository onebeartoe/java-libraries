
package org.onebeartoe.io;

import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URL;

/**
 * @author Roberto Marquez Created: Febuary 4 2003
 */
public class ObjectRetriever 
{

    public static Object loadFromClasspath(String pathOnClasspath) throws IOException 
    {
        String resourceName = "/" + pathOnClasspath;
        URL url = ObjectRetriever.class.getResource(resourceName);
        InputStream instream = url.openStream();
        Object object = ObjectRetriever.decodeObject(instream);

        return object;
    }

    public static Object decodeObject(File infile) throws FileNotFoundException 
    {
        FileInputStream instream = new FileInputStream(infile);
        return decodeObject(instream);
    } 
    
    /**
     * This method will decode a object from an XML document from disk.
     * @param instream
     * @return 
     */
    public static Object decodeObject(InputStream instream) 
    {
        Object obj;
        XMLDecoder decoder = new XMLDecoder(instream);
        obj = decoder.readObject();
        decoder.close();

        return obj;
    }

    /**
     * the file sent to this method should contain a serialized Java object
     */
    public static Object retrieveObject(File infile) 
    {
        try 
        {
            FileInputStream input = new FileInputStream(infile);
            return retrieveObject(input);
        } 
        catch (FileNotFoundException fnfe) 
        {
            fnfe.printStackTrace();
            return null;
        }
    }

    //the file sent to this method should contain a serialized Java object
    public static Object retrieveObject(InputStream input) 
    {
        Object o = null;
        try 
        {
            ObjectInputStream input_stream = new ObjectInputStream(input);
            o = (Object) input_stream.readObject();
            input_stream.close();

            return o;
        } 
        catch (ClassNotFoundException cnfe) 
        {
            cnfe.printStackTrace();
            return o;
        } 
        catch (FileNotFoundException fnfe) 
        {
            fnfe.printStackTrace();
            return o;
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
            return o;
        }
    }
    
    /**
     * The file sent to this method should contain a serialized Java object.
     * @param file_name
     * @return 
     */
    public static Object retrieveObject(String file_name) 
    {
        File f = new File(file_name);
        
        return retrieveObject(f);
    }

    public static Object retrieveObject(File parent, String file_name) 
    {
        File input_file = new File(parent, file_name);
        
        return retrieveObject(input_file);
    }

}
