/*
 */
package org.onebeartoe.html;

/**
 *
 * @author Roberto Marquez
 */
public class BreakTag implements HtmlTag
{
    
    @Override
    public String toHtml()
    {
        return "<br/>";
    }
    
}
