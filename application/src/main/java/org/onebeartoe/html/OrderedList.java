package org.onebeartoe.html;

import java.util.List;

public class OrderedList extends UnorderedList
{
    public OrderedList(List<String> elements)
    {
        super(elements);

        beginTag = "<ol>";
        endTag = "</ol>";
    }
}
