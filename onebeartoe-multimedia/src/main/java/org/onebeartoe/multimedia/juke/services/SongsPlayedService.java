package org.onebeartoe.multimedia.juke.services;

import java.util.List;

/**
 * @author Roberto Marquez
 */
public interface SongsPlayedService 
{
    void retrieveSongsPlayed() throws Exception;

    void storeSongsPlayed() throws Exception;

    void addPlayedSong(String playedSong) throws Exception;

    void clearPlayedSongs() throws Exception;

    boolean hasBeenPlayed(String songTitle) throws Exception;

    List<String> getSongsPlayed();
}
