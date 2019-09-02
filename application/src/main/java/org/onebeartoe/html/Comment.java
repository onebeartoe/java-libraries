
package org.onebeartoe.html;

/**
 *
 * @author Roberto Marquez
 */
public class Comment extends HtmlTag
{
    private String content;
    
    public Comment(String content)
    {
        this.content = content;
    }
    
    @Override
    public String toString()
    {
        StringBuffer tag = new StringBuffer();
        
        tag.append("<!-- ");
        tag.append(content);
        tag.append(" -->");
        
        return tag.toString();
    }
}
