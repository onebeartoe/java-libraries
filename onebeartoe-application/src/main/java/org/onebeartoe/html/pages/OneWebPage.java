
package org.onebeartoe.html.pages;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roberto Marquez
 */
public class OneWebPage
{
    /**
     * This is a list of HTML elements that make up the main content of 
     * a oneweb page.
     */
    public List<Object> content;
    
    /**
     * These are all the navigation menus.
     */
    public List<Menu> menus;
    
    public OneWebPage()
    {
        content = new ArrayList();
        
        menus = new ArrayList();
    }
}
