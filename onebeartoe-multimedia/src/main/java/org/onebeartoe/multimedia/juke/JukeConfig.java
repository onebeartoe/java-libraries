package org.onebeartoe.multimedia.juke;

import java.util.ArrayList;
import org.onebeartoe.application.ui.GUIConfig;

/**
 * @author Roberto Marquez
 */
public class JukeConfig extends GUIConfig 
{

    /**
     * Empty constructor for XML encodeing
     */
    public JukeConfig() {
        this(15, 15, 200, 300);
    }

    public JukeConfig(Integer x, Integer y, Integer w, Integer h) 
    {
        super(x, y, w, h);
        
        songTitleUrls = new ArrayList<String>();
    }

    private ArrayList<String> songTitleUrls;

    public ArrayList<String> getSongTitleUrls() 
    {
        return songTitleUrls;
    }

    public void addSongTitlePath(String path) 
    {
        if (songTitleUrls.contains(path)) 
        {
            System.out.println("duplicate path not added: " + path);
        } 
        else 
        {
            songTitleUrls.add(path);
        }
    }

    public void clearSongTitlePaths() 
    {
        songTitleUrls.clear();
    }

    public void setSongTitleUrls(ArrayList<String> songTitlePaths) 
    {
        this.songTitleUrls = songTitlePaths;
    }

}
