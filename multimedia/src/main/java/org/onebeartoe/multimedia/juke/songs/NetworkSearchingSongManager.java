package org.onebeartoe.multimedia.juke.songs;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.onebeartoe.multimedia.juke.SongList;
import org.onebeartoe.multimedia.juke.links.LinkManager;
import org.onebeartoe.multimedia.juke.links.LinkManager.Link;
import org.onebeartoe.multimedia.juke.links.LinkManager.LinkUnit;

/**
 * @deprecated use the version at https://github.com/onebeartoe/java-libraries
 * @author Roberto Marquez
 */
public abstract class NetworkSearchingSongManager implements SongListManager 
{

	List<LinkManager> linkManagers;
	
	public NetworkSearchingSongManager()
	{
		linkManagers = new ArrayList<LinkManager>();
	}
	
	@Override
	public void clearSongLists() 
	{
		for(LinkManager linkManager : linkManagers)
		{
			linkManager.clear();
		}
	}

	@Override
	public int discoverSongLists(URL discoveryDiscriptorUrl) 
	{
		String host = "http://" + discoveryDiscriptorUrl.getHost();
		String url = discoveryDiscriptorUrl.toString();
		String path = url.replace(host, "");
		int listCount = 0;
		
		try 
		{
			LinkManager linkManager = getLinkManager();
			linkManager.setRootUrl(host, path);			
						
			linkManager = removeNoMediaLinkUnits(linkManager);			
						
			if(linkManager.getLinkUnitNames().size() > 0)
			{
				linkManagers.add(linkManager);
				listCount += linkManager.getLinkUnitNames().size();
			}			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}				
		
		return listCount;
	}
	
	/**
	 * remove LinkUnits with no media links
	 * @param linkManager
	 */
	private LinkManager removeNoMediaLinkUnits(LinkManager linkManager)
	{
		List<String> names = linkManager.getLinkUnitNames();
		List<LinkUnit> unitsToAdd = new ArrayList<LinkManager.LinkUnit>();
		List<LinkUnit> unitsToRemove = new ArrayList<LinkManager.LinkUnit>();
		
		for(String name : names)
		{
			LinkUnit linkUnit = linkManager.getLinksUnitFor(name);
			boolean hasMediaLinks = false;
			for(Link link : linkUnit.links)
			{
				if( acceptableMediaExtension(link.href) )
				{
					unitsToAdd.add(linkUnit);
					
					hasMediaLinks = true;
					break;
				}
			}
			if(!hasMediaLinks)
			{
				unitsToRemove.add(linkUnit);
			}
		}
		
		linkManager.clear();
		
		for(LinkUnit linkUnit : unitsToAdd)
		{
			// does not work!
			linkManager.add(linkUnit);
		}
		
		
		return linkManager;
	}
	
	private boolean acceptableMediaExtension(String extension)
	{
		boolean acceptable = false;
		
		if(extension.endsWith(".mp3"))
		{
			acceptable = true;
		}
		
		return acceptable;
	}

	@Override
	public List<String> getSongListTitles() 
	{
		List<String> titles = new ArrayList<String>();
		for(LinkManager linkManager : linkManagers)
		{
			List<String> names = linkManager.getLinkUnitNames();
			titles.addAll(names);
		}
		
		return titles;
	}

    @Override
    public SongList getSongListFor(String listName) 
    {
        LinkUnit linkUnit = null;
        for(LinkManager linkManager : linkManagers)
        {
            List<String> names = linkManager.getLinkUnitNames();
            for(String name : names)
            {
                if(name.equals(listName))
                {
                    linkUnit = linkManager.getLinksUnitFor(listName);
                    break;
                }
            }
        }

        SongList songList = new SongList(listName); 

        if(linkUnit != null)
        {
            getSongListForLinkUnit(linkUnit, songList);
        }

        return songList;
    }
    
    private void getSongListForLinkUnit(LinkUnit linkUnit, SongList songList)
    {
        for(Link link : linkUnit.links)
        {
            if( acceptableMediaExtension(link.href) )
            {
                URL url;
                try 
                {
                    url = new URL(link.href);
                    songList.addSong(link.label, url);
                } 
                catch (MalformedURLException e) 
                {
                    System.err.println("could not create a URL for: " + link.href);
                    e.printStackTrace();
                }					
            }
        }        
    }
}
