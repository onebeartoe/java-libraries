
package org.onebeartoe.html;

/**
 * @author Roberto Marquez
 */
public abstract class XhtmlContainerTag extends HtmlTag
{
    abstract void add(HtmlTag htmlTag);

    abstract String getOpenTag();

    abstract String getCloseTag();
}
