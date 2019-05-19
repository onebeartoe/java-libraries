
package org.onebeartoe.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet sub-class sends plaintext responses.
 */
public abstract class PlainTextResponseServlet extends HttpServlet
{
    protected abstract String buildText(HttpServletRequest request, HttpServletResponse response);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        doResponse(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        doResponse(request, response);
    }

    private void doResponse(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String text = buildText(request, response);
        
        OutputStream os = response.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        pw.println(text);
        pw.flush();
        pw.close();        
    }
}
