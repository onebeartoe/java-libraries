
package org.onebeartoe.multimedia.juke.links;

import java.io.IOException;
import java.net.URL;
import org.onebeartoe.multimedia.juke.links.LinkManager.LinkUnit;

/**
 * This provides an interface to extract URL links from HTML documents.
 * @author roberto.marquez
 */
public interface LinkParser 
{
	LinkUnit extractLinks(String name, URL url) throws IOException;
}
