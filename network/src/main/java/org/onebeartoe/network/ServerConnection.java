package org.onebeartoe.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import org.onebeartoe.io.TextFileReader;

/**
 * @author Roberto Marquez
 */
public abstract class ServerConnection implements Runnable, Cloneable
{

    protected Socket client;

    protected final String path = "/onebeartoe/juke/ui/";
    
    /**
     * @param client the client to set
     */
    public void setClient(Socket client)
    {
        this.client = client;
    }

    public Object clone()
    {
        Object obj = null;
        try
        {
            obj = super.clone();
        } catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return obj;
    }

    protected void sendHttpResponse(String html, boolean includeHeaders) throws IOException
    {
        String headers = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n";
        headers += "\r\n";

        OutputStream outs = client.getOutputStream();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(outs, "UTF-8"), true);
        if (includeHeaders)
        {
            out.print(headers);
        }
        out.println(html);
        out.flush();
        out.close();
    }
    
    
    protected void sendNonHtmlResponse(String filename) throws IOException
    {
        InputStream instream = getClass().getResourceAsStream(path + filename);
        String html = TextFileReader.readText(instream);
        boolean includeHeader = false;
        sendHttpResponse(html, includeHeader);        
    }

    protected String invalidRequest(String request)
    {
        StringBuilder out = new StringBuilder();
        out.append("Request Recieved Busta: " + request);
        out.append("<br /><br />" + getClass().getSimpleName() + " accepts requests like \"GET filename.html\"<br />");
        out.append("<br /><br />or something like \"filename.zip\"<br />");

        return out.toString();
    }

}
