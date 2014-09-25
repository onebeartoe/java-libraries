
package org.onebeartoe.multimedia.juke.gui;

import java.util.List;

/**
 * @author Roberto Marquez
 */
public interface SongListPathPanel
{
    List<String> getSongListPaths();

    void setSongListsPaths(List<String> songListsPaths);

    void addSongListPath(String path);
}
