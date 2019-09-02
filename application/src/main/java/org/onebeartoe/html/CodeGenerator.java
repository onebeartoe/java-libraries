/*
 */
package org.onebeartoe.html;

/**
 *
 * @author Roberto Marquez
 */
public class CodeGenerator
{
    public String htmlBottom() 
    {
        return "\n</BODY>\n</HTML>";
    }
    
    /**
            This version takes the URL and returns an anchor tag
            with the url as the text to click on
    */	
    public String getLinkTag( String url ) 
    {
        return getLinkTag(url, url);
    }
    /**
            This version takes the URL and the text to click on,
            then returns an anchor tag.
    */	
    public String getLinkTag( String url, String text ) 
    {
        AnchorTag tag = new AnchorTag(url, text);

        return tag.toString();
    }    
    
    /**
     * @param content Takes the content of the page as a String
     * @return Returns an HTML document with a "no title".
    */
    public String htmlify(String content) 
    {
        StringBuilder html = new StringBuilder( htmlTop("no title") );
        html.append( content );
        html.append( htmlBottom() );
        
        return html.toString();
    }

    /**
            Takes the title of the page and the content of the page as Strings.
            Returns an HTML document.
    */
    public String htmlify(String title, String content) 
    {
        StringBuffer html = new StringBuffer( htmlTop(title) );
        html.append( content );
        html.append( htmlBottom() );
        return html.toString();
    }

    /** 
     * @return a string containing just the html open tag
     */
    public String htmlTop() 
    {
            return("<html>\n");
    }    
    
    /**
     * 
     * @param title the value used in the title tag
     * @return a string with the value of an HTML document includingthe open html tag, a complete 
     * head tag with title, and an open body tag.
     */
    public String htmlTop(String title) 
    {
            return("<html>\n<head><title>" + title + "</title></head>\n<body>");
    }
}
