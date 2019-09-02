
package org.onebeartoe.html;

public class Div extends BasicContainerTag
{

    private String otherProperties;

    public String getOtherProperties()
    {
        return otherProperties;
    }

    public void setOtherProperties(String otherProperties)
    {
        this.otherProperties = otherProperties;
    }

    @Override
    public String getOpenTag() 
    {
        StringBuffer tag = new StringBuffer();
        
        tag.append("<div ");
        
        if( classes != null )
        {
            tag.append("class=\"");
            tag.append(classes);
            tag.append("\" ");
        }
        
        if(style != null)
        {
            tag.append("style=\"");
            tag.append(style);
            tag.append("\" ");
        }            
        
        if(otherProperties != null)
        {
            tag.append(otherProperties);
        }
        
        tag.append(">");
        
        return tag.toString();
    }

    @Override
    public String getCloseTag() 
    {
        return "</div>";
    }
}
