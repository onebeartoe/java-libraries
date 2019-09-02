
package org.onebeartoe.multimedia.juke.songs;

import java.net.URL;
import java.util.List;
import org.onebeartoe.multimedia.juke.SongList;

import org.onebeartoe.multimedia.juke.links.LinkManager;

/**
 * @author roberto
 */
public interface SongListManager 
{
    void clearSongLists();

    int discoverSongLists(URL discoveryDiscriptorUrl);

    List<String> getSongListTitles();

    SongList getSongListFor(String listName);

    public LinkManager getLinkManager() throws Exception;
}
