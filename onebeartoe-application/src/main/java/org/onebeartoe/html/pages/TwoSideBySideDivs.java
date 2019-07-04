
package org.onebeartoe.html.pages;

import org.onebeartoe.html.Div;
import org.onebeartoe.html.HtmlTag;

/**
 *
 * @author Roberto Marquez
 */
public class TwoSideBySideDivs extends Div
{
    private Div leftDiv;
    
    private Div rightDiv;
    
    public TwoSideBySideDivs()
    {
        super();
        
        setClasses("sideBySide");
        
        leftDiv = new Div();
        leftDiv.setClasses("sideBySideLeft");
        
        rightDiv = new Div();
        rightDiv.setClasses("sideBySideRight");
        
        add(leftDiv);
        add(rightDiv);
    } 

    public void setLeftContent(HtmlTag content)
    {
        
        leftDiv.add(content);
    }
    
    public void setLeftStyle(String style)
    {
        leftDiv.setStyle(style);
    }

    public void setRightContent(HtmlTag content)
    {
        rightDiv.add(content);
    }
}
