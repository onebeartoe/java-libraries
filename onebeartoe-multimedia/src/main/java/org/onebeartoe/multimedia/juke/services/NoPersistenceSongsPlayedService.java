
package org.onebeartoe.multimedia.juke.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.onebeartoe.io.ObjectRetriever;
import org.onebeartoe.io.ObjectSaver;
        
/**
 * @author Roberto Marquez
 */
public class NoPersistenceSongsPlayedService implements SongsPlayedService 
{
	Map<String, Date> songsPlayed;

	String filename = "RandomJukeSongsPlayed.xml";
	
	public void addPlayedSong(String playedSong) throws Exception 
	{
		if(songsPlayed == null)
		{
			retrieveSongsPlayed();
		}
		Date date = new Date();
		songsPlayed.put(playedSong, date);		
	}

	public boolean hasBeenPlayed(String songTitle) throws Exception 
	{
		Date date = songsPlayed.get(songTitle);
		boolean played = true;
		if(date == null)
		{
			played = false;
		}
		
		return played;
	}

	public void retrieveSongsPlayed() throws Exception
	{
		File infile = new File(filename);
		try
		{
			songsPlayed = (HashMap<String, Date>) ObjectRetriever.decodeObject(infile);
		}
		catch(Exception e)
		{
			songsPlayed = new  HashMap<String, Date>();
		}		
	}

	public void storeSongsPlayed() throws Exception 
	{
		File outfile = new File(filename);
		ObjectSaver.encodeObject(songsPlayed, outfile);
	}

	public void clearPlayedSongs() throws Exception 
	{
		songsPlayed.clear();
		
	}

	public List<String> getSongsPlayed() 
	{
		Set<String> set = songsPlayed.keySet();
		List<SongPlayed> songsPlayedList = new ArrayList<SongPlayed>();
		for(String song : set)
		{
			Date date = songsPlayed.get(song);
			SongPlayed sp = new SongPlayed();
			sp.song = song;
			sp.date = date;
			songsPlayedList.add(sp);
		}
		
		Collections.sort(songsPlayedList, new SongPlayedComparator() );
		
		List<String> songs = new ArrayList<String>();
		
		for(SongPlayed sp : songsPlayedList)
		{
			songs.add(sp.song);
		}
		
		return songs;
	}
	
	private class SongPlayedComparator implements Comparator<SongPlayed> 
	{

		@Override
		public int compare(SongPlayed o1, SongPlayed o2) 
		{
			int comparison = o1.date.compareTo(o2.date);
				
			return comparison;
		}
		
	}
	
	private class SongPlayed
	{
		public String song;
		
		public Date date;
	}

}
