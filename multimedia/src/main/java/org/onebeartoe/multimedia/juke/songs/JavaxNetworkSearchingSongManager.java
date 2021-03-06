
package org.onebeartoe.multimedia.juke.songs;

import org.onebeartoe.multimedia.juke.links.AnyLinkManager;
import org.onebeartoe.multimedia.juke.links.LinkManager;
import org.onebeartoe.multimedia.juke.links.LinkParser;
import org.onebeartoe.multimedia.juke.links.SwingLinkParser;

/**
 * @author Roberto Marquez
 */
public class JavaxNetworkSearchingSongManager extends NetworkSearchingSongManager
{

    @Override
    public LinkManager getLinkManager()
    {
        return new AnyLinkManager()
        {
            @Override
            public LinkParser getLinkParser()
            {
                return new SwingLinkParser();
            }
        };
    }
}
