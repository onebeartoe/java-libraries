package org.onebeartoe.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * @deprecated use the version at https://github.com/onebeartoe/java-libraries
 * @author Roberto Marquez
 */
public class SerializedObjects
{

    public static Object retrieve(InputStream input, boolean closeStream) throws IOException, ClassNotFoundException
    {

        ObjectInputStream input_stream = new ObjectInputStream(input);
        Object o = (Object) input_stream.readObject();

        if (closeStream)
        {
            input_stream.close();
        }

        return o;
    }

    public static boolean store(OutputStream outstream, Object object, boolean closeStream) throws IOException
    {
        ObjectOutputStream objectOutstream = new ObjectOutputStream(outstream);
        objectOutstream.writeObject(object);
        objectOutstream.flush();

        if (closeStream)
        {
            objectOutstream.close();
        }

        return true;
    }

    public static boolean saveObject(File f, Object object, boolean closeStream) throws FileNotFoundException, IOException
    {
        OutputStream output = new FileOutputStream(f);

        return store(output, object, closeStream);
    }

}
