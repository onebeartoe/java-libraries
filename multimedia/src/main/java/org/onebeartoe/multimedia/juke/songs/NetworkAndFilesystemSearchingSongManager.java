
package org.onebeartoe.multimedia.juke.songs;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.onebeartoe.multimedia.juke.SongList;
import org.onebeartoe.multimedia.juke.links.LinkManager;

/**
 * @author Roberto Marquez
 */
public class NetworkAndFilesystemSearchingSongManager implements SongListManager
{

    private FileSystemSearchingSongManager filesystemSongManager;

    private NetworkSearchingSongManager networkSearchingSongManager;

    public NetworkAndFilesystemSearchingSongManager()
    {
        
        filesystemSongManager = new FileSystemSearchingSongManager();        
        
//TODO: why are we not using this?        
//		networkSearchingSongManager = new NetworkSearchingSongManager();
    }

    public void setNetworkSongManager(NetworkSearchingSongManager networkSearchingSongManager)
    {
        this.networkSearchingSongManager = networkSearchingSongManager;
    }

    @Override
    public void clearSongLists()
    {
        filesystemSongManager.clearSongLists();
        networkSearchingSongManager.clearSongLists();
    }

    @Override
    public int discoverSongLists(URL discoveryDiscriptorUrl)
    {
        int count;
        if (discoveryDiscriptorUrl.toString().toLowerCase().startsWith("file:"))
        {
            count = filesystemSongManager.discoverSongLists(discoveryDiscriptorUrl);
        } else
        {
            count = networkSearchingSongManager.discoverSongLists(discoveryDiscriptorUrl);
        }

        return count;
    }

    @Override
    public List<String> getSongListTitles()
    {
        List<String> listTitles = new ArrayList<String>();

        listTitles.addAll(filesystemSongManager.getSongListTitles());
        listTitles.addAll(networkSearchingSongManager.getSongListTitles());

        return listTitles;
    }

    @Override
    public SongList getSongListFor(String listName)
    {
        SongList songList = filesystemSongManager.getSongListFor(listName);

        if (songList == null || songList.getSongTitles().length == 0)
        {
            songList = networkSearchingSongManager.getSongListFor(listName);
        }

        return songList;
    }

    @Override
    public LinkManager getLinkManager() throws Exception
    {
        throw new Exception("not implemented yet");

    }

}
