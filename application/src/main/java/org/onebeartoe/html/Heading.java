
package org.onebeartoe.html;

/**
 * This class abstracts the HTML heading tag.
 */
public class Heading extends HtmlTag
{
    private String text;

    private int number;
    
    public Heading(String text, int number)
    {
        this.number = number;
        
        this.text = text;
    }
    
    public static Heading h1(String text)
    {
        Heading h = new Heading(text, 1);
        
        return h;
    }
    
    public static Heading h2(String text)
    {
        Heading h = new Heading(text, 2);
        
        return h;
    }
    
    public static Heading h3(String text)
    {
        Heading h = new Heading(text, 3);
        
        return h;
    }
    
    public static Heading h4(String text)
    {
        Heading h = new Heading(text, 4);
        
        return h;
    }
    
    public String toString()	
    {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<h" + number + ">");
        stringBuffer.append(text);
        stringBuffer.append("</h" + number + ">" + "\n");

        return stringBuffer.toString();
    }
}
