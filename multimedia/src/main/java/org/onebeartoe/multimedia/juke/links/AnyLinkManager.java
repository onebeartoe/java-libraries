
package org.onebeartoe.multimedia.juke.links;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Roberto Marquez
 */
public abstract class AnyLinkManager implements LinkManager 
{
    private List<LinkUnit> linkUnits;
	
	public AnyLinkManager() 
	{
            linkUnits = new ArrayList<LinkUnit>();
	}
	
	@Override
	public List<String> getLinkUnitNames() 
	{
		List<String> names = new ArrayList<String>();
		for(LinkUnit unit : linkUnits)
		{
			names.add(unit.name);
		}
		
		return names;
	}

	@Override
	public void setRootUrl(String host, String path) throws Exception
	{
            Link rootLink = new Link();
            rootLink.label = host + path;
            rootLink.href = host + path;		
            
            List<Link> unfollowedLinks = new ArrayList<Link>();
            unfollowedLinks.add(rootLink);

            while( ! unfollowedLinks.isEmpty() )
            {	
                Link currentLink = unfollowedLinks.remove(0);
                try
                {
                    setRootUrlOneLink(currentLink, host, unfollowedLinks);
                }
                catch(Exception fnfe)
                {
                    System.out.println("\ncould not load link: " + currentLink.href);
                }
            }		
	}
        
        private void setRootUrlOneLink(Link currentLink, String host, List<Link> unfollowedLinks) throws MalformedURLException, IOException
        {
            System.out.print("processing: " + currentLink);
            URL url = new URL(currentLink.href);


            int port = url.getPort();
            LinkParser parser = getLinkParser();
            LinkUnit linkUnit = parser.extractLinks(currentLink.href, url);

            List<String> followedHrefs = new ArrayList<String>();            
            
            followedHrefs.add(currentLink.href);
            for(Link nextLink : linkUnit.links)
            {
                String possibleSlash = ! currentLink.href.endsWith("/") && ! nextLink.href.startsWith("/") ? "/" : "";
                String href = currentLink.href + possibleSlash + nextLink.href;
                if( nextLink.href.startsWith("/") )
                {
                    href = host;
                    if(port != -1)
                    {
                            href += ":" + port;
                    }
                    href += nextLink.href;
                }
                nextLink.href = href;

                if(nextLink.href.contains("?") )
                {
                    nextLink.href = nextLink.href.substring(0, nextLink.href.indexOf('?')  );
                }					

                boolean looper = nextLink.href.contains("/./") || nextLink.href.indexOf("http:") != nextLink.href.lastIndexOf("http:");

                // don't follow links that have already been followed
                // stay on the original host
                // try to avoid following binary files
                if( !followedHrefs.contains(nextLink.href) 
                                && nextLink.href.startsWith(host)
                                && !binaryFile(nextLink.href) 
                                && !looper) 
                {
                    unfollowedLinks.add(nextLink);
                }
            }
            linkUnits.add(linkUnit);
            System.out.println(" - done");            
        }
	
//TODO: Move this to FileHelper.java        
	private boolean binaryFile(String href)
	{
		boolean binary = false;
		String lHref = href.toLowerCase();
		if(lHref.endsWith(".zip")
				|| lHref.endsWith(".mp3")
				|| lHref.endsWith(".exe")
				|| lHref.endsWith(".png")
				|| lHref.endsWith(".gif")
				|| lHref.endsWith(".jpg")
				|| lHref.endsWith(".jpeg")
				|| lHref.endsWith(".mpeg")
				|| lHref.endsWith(".mpg")
				|| lHref.endsWith(".wma")
				|| lHref.endsWith(".wav")
				|| lHref.endsWith(".apk"))
		{
			binary = true;
		}
		
		return binary;
	}

	@Override
	public LinkUnit getLinksUnitFor(String linkUnitName) 
	{
		LinkUnit unit = null;
		
		for(LinkUnit u : linkUnits)
		{
			if( u.name.equals(linkUnitName) )
			{
				unit = u;
				break;
			}
		}
		
		return unit;
	}

	@Override
	public void clear() 
	{
		linkUnits.clear();
	}
	
	@Override
	public void add(LinkUnit linkUnit) 
	{
		linkUnits.add(linkUnit);		
	}

	@Override
	public void remove(LinkUnit linkUnit) 
	{
		LinkUnit target = null;
		for(LinkUnit lu : linkUnits)
		{
			if( lu.name.equals(linkUnit.name) )
			{
				target = linkUnit;
				break;
			}
		}		
		
		linkUnits.remove(target);
		System.out.println("link unit size: " + linkUnits.size());
	}
}
