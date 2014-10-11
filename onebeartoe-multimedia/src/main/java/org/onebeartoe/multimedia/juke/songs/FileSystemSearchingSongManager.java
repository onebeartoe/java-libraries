
package org.onebeartoe.multimedia.juke.songs;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.onebeartoe.filesystem.FileHelper;

import org.onebeartoe.filesystem.FileSystemSearcher;
import org.onebeartoe.filesystem.FileType;

import org.onebeartoe.multimedia.juke.SongList;
import org.onebeartoe.multimedia.juke.links.LinkManager;

/**
 * @deprecated use the version at https://github.com/onebeartoe/java-libraries
 * @author Roberto Marquez
 */
public class FileSystemSearchingSongManager implements SongListManager//extends JuniorSongListManager 
{
	List<String> songsPlayed;	

	public FileSystemSearchingSongManager()
	{
		songLists = new HashMap<String, File>();
		songsPlayed = new ArrayList<String>();
	}	
	
	@Override
	public void clearSongLists() 
	{
		songLists.clear();
	}
	
protected HashMap<String, File> songLists;
	
	@Override
	public SongList getSongListFor(String listName)
	{
		SongList list = null;
		File dir = songLists.get(listName);
		if(dir != null)
		{
			list = new SongList(listName);
			File [] lists = dir.listFiles();

			for(File currentFile : lists)
			{
				String filename = currentFile.getName();
				boolean isSong = FileHelper.isAudioFile(filename) || FileHelper.isMultimediaFile(filename);  
				if(isSong)
				{
					try 
					{
						String songName = currentFile.getName();
						URL songUrl = currentFile.toURL();
						list.addSong(songName, songUrl);
					}
					catch (MalformedURLException mue) 
					{
						mue.printStackTrace();
					}
				}	
			}
		}
		
		return list;
	}
	
	/**
	 * This constructor takes the URL of representing the path to the directory 
	 * containing filesystem-based SongLists. 
	 * @param initalParentPath
	 */
	public FileSystemSearchingSongManager(URL initalParentPath)
	{
		songLists = new HashMap<String, File>();
		discoverSongLists(initalParentPath);
		songsPlayed = new ArrayList<String>();
	}
	
	@Override
	public int discoverSongLists(URL parentPathUrl) 
	{			
		String urlStr = parentPathUrl.toString();
		System.out.println("\nurlStr: " + urlStr);
		
		urlStr = urlStr.replace("file:", "");					
		System.out.println("encoded urlStr: " + urlStr);
		
		File songsDir = new File(urlStr);
		
		ArrayList <FileType> targets = new ArrayList<FileType>();
		targets.add(FileType.AUDIO);
		targets.add(FileType.MULTIMEDIA);
		
		FileSystemSearcher filesystemSearcher = new FileSystemSearcher(songsDir, targets, true);		
		ArrayList<File> targetDirectories = filesystemSearcher.findTargetDirectories();
		int discoveredCount = targetDirectories.size();
		
		for(File file : targetDirectories)
		{
			String songlistTitle = file.getName();
			songLists.put(songlistTitle, file);
		}
						
		return discoveredCount;
	}	
	
	/**
	 * @return an arrayList object. the list is returned alphabetized.
	 */
	@Override
	public ArrayList<String> getSongListTitles()
	{
		ArrayList<String> songListTitles = new ArrayList<String>();

		for(String title : songLists.keySet() )
		{
			songListTitles.add( title );
		}

		Collections.sort (songListTitles);
		
		return songListTitles;
	}

	@Override
	public LinkManager getLinkManager() throws Exception 
	{
		throw new Exception("operation not implemented yet");
	}
	
}
