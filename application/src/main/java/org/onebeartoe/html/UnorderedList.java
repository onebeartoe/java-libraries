package org.onebeartoe.html;

import java.util.List;

public class UnorderedList extends HtmlTag
{

    protected String beginTag;

    protected String endTag;

    protected List<String> elements;

    public UnorderedList(List<String> elements)
    {
        beginTag = "<ul>";

        endTag = "</ul>";

        this.elements = elements;
    }

    @Override
    public String toString()
    {
        StringBuffer buf = new StringBuffer(beginTag + "\n");

        for (String listItem : elements)
        {
            buf.append("\t<li>\n\t\t");
            buf.append(listItem);
            buf.append("\n\t</li><br/>\n");
        }

        buf.append(endTag + "\n");

        return buf.toString();
    }
}
