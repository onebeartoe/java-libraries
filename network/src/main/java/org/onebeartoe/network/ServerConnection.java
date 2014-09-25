package org.onebeartoe.network;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Roberto Marquez
 */
public abstract class ServerConnection implements Runnable, Cloneable
{

    protected Socket client;

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

    protected void sendHttpResponse(String html, boolean includeHeader) throws IOException
    {
        String heaersMessage = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n";
        heaersMessage += "\r\n";

        OutputStream outs = client.getOutputStream();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(outs, "UTF-8"), true);
        if (includeHeader)
        {
            out.print(heaersMessage);
        }
        out.println(html);
        out.flush();
        out.close();
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
