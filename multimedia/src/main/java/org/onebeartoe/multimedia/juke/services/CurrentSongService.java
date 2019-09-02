
package org.onebeartoe.multimedia.juke.services;

/**
 * @author Roberto Marquez
 */
public interface CurrentSongService 
{
    void like(String songTitle, String clientAddress) throws Exception;

    boolean next(String songTitle, String clientAddress) throws Exception;

    String getCurrentSong();
    
    void setCurrentSong(String song) throws Exception;

    int likeCount() throws Exception;

    int nextCount() throws Exception;
}
