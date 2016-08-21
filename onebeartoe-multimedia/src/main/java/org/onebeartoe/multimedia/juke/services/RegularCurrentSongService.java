
package org.onebeartoe.multimedia.juke.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Roberto Marquez
 */
public class RegularCurrentSongService implements CurrentSongService 
{
	String currentSong;
	
	long inactivityDuration = 1000 * 60 * 6;
	
	enum RequestTypes 
	{
		LIKE,
		NEXT,
		REQUEST
	}
	
	Map<String, Date> clients;

	Map<String, String> likes;
	
	Map<String, String> nexts;

	public RegularCurrentSongService() 
	{
		clients = new HashMap<String, Date>();
		likes = new HashMap<String, String>();
		nexts = new HashMap<String, String>();
	}
	
	private void registerClient(String address)
	{
		clients.put(address, new Date() );
	}
	
	public void like(String songTitle, String clientAddress) throws Exception 
	{
		registerClient(clientAddress);
		
		if(songTitle != null && songTitle.equals(currentSong) )
		{
			likes.put(songTitle, clientAddress);
		}
	}

	public boolean next(String songTitle, String clientAddress) throws Exception 
	{
		registerClient(clientAddress);
		
		cleanup();
		
		nexts.put(songTitle, clientAddress);

// I forgot what the heck is goin on here.  This is set to true so that the app 'works'.                
		boolean next = true;
//		boolean next = false;
		

                System.out.println("The regular current song service is playing the next song.");

		if(songTitle == null || songTitle.equals(currentSong) )
		{
			float percentage = (float) nexts.size() / clients.size();
// remove when enabling voting/like system for mobile divices			
percentage = 1;			
			if(percentage > .5 || clientAddress.equals("localhost") )
			{
				next = true;				
				likes.clear();
				nexts.clear();
			}
			else
			{
				System.out.println("not gonna skip - percentage: " + percentage + " client: " + clientAddress);
			}
		}
		
		return next;
	}

	private void cleanup()
	{
		Set<String> keys = clients.keySet();
		List<String> removes = new ArrayList<String>();
		for(String k : keys)
		{			
			Date date = clients.get(k);
			
			Date timeOut = new Date( Calendar.getInstance().getTimeInMillis() - inactivityDuration );
			if( date.before(timeOut) )
			{
				if(clients.size() != 1)
				{
					removes.add(k);
//					clients.remove(k);
				}
			}
		}
		
		for(String k : removes)
		{
			clients.remove(k);
		}
	}

	public void setCurrentSong(String song) throws Exception 
	{
		currentSong = song;
		
	}

	public int likeCount() throws Exception 
	{
		return likes.size();
	}

	public int nextCount() throws Exception 
	{
		
		return nexts.size();
	}
	
}
