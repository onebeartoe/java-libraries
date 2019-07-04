
package org.onebeartoe.html;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rmarquez
 */
public abstract class BasicContainerTag extends XhtmlContainerTag
{
    protected List<HtmlTag> innerTags;
    
    public BasicContainerTag()
    {
        innerTags = new ArrayList<HtmlTag>();
    }

    @Override
    public void add(HtmlTag htmlTag)
    {
        innerTags.add(htmlTag);
    }

    @Override
    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        buf.append( getOpenTag() );
        for(HtmlTag tag : innerTags)
        {
            buf.append( tag.toString() );
        }
        buf.append( getCloseTag() );
        
        return buf.toString();
    }
}
