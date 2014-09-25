
package org.onebeartoe.multimedia.juke;

import java.net.URL;
import java.util.HashMap;

/**
 * @author Roberto Marquez
 */
public class SongList 
{

	HashMap<String, URL> list;
	
	private String name;
	
	public SongList(String listName)
	{
		name = listName;
		list = new HashMap<String, URL>();
	}
	
	public void addSong(String song, URL url)
	{
		list.put(song, url);
	}
	
	public URL getUrlFor(String songTitle)
	{
		return list.get(songTitle);
	}
	
	public String [] getSongTitles()
	{		
		return (String []) list.keySet().toArray( new String[0] );
	}

	public String getName() {
		return name;
	}
	
}
