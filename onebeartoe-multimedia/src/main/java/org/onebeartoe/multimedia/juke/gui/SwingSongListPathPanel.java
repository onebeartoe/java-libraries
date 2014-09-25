
package org.onebeartoe.multimedia.juke.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;

import org.onebeartoe.application.ui.GUITools;
import org.onebeartoe.multimedia.juke.gui.SongListPathPanel;

/**
 * @author Roberto Marquez
 */
public class SwingSongListPathPanel extends JPanel implements SongListPathPanel
{
    private static final long serialVersionUID = 173856230L;

    Vector<String> list;

    JList songListPaths;

    ListModel model;

    public SwingSongListPathPanel()
    {
        list = new Vector<String>();
        songListPaths = new JList(list);

        model = songListPaths.getModel();

        JButton addButton = new JButton("Add");
        AddButtonListener addButtonListener = new AddButtonListener();
        addButton.addActionListener(addButtonListener);

        JButton networkAddButton = new JButton("Add Network Path");
        networkAddButton.addActionListener(new NetworkAddButtonListener());

        JButton removeButton = new JButton("Remove");
        RemoveButtonListener removeButtonListener = new RemoveButtonListener();
        removeButton.addActionListener(removeButtonListener);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 20, 20));
        buttonPanel.add(addButton);
        buttonPanel.add(networkAddButton);
        buttonPanel.add(removeButton);

        setLayout(new BorderLayout());

        add(songListPaths, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);
    }

    public List<String> getSongListPaths()
    {
        int size = model.getSize();

        List<String> list = new ArrayList<String>();

        for (int m = 0; m < size; m++)
        {
            Object o = model.getElementAt(m);
            list.add(o.toString());
        }

        return list;
    }

    class NetworkAddButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent event)
        {
            String url = GUITools.getString("Provide an HTTP path to songs on the network");
            if (url != null && url.toLowerCase().startsWith("http"))
            {
                list.add(url);
                songListPaths.setListData(list);
            }
        }
    }

    class AddButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            File songDirectory = GUITools.selectDirectory("Choose a directory containing song/audio files:");

            if (songDirectory == null)
            {
                System.out.println("Add SongListPath action was canceled.");
            } else
            {
                String path;
                try
                {
                    path = songDirectory.toURL().toString();
                    list.add(path);
                    songListPaths.setListData(list);
                } catch (MalformedURLException e1)
                {
                    e1.printStackTrace();
                    GUITools.infoMessage("There was a problem adding the selected directory.");
                }
            }
        }
    }

    class RemoveButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            String path = (String) songListPaths.getSelectedValue();
            list.remove(path);
            songListPaths.setListData(list);
        }
    }

    public void addSongListPath(String path)
    {
        list.add(path);
        songListPaths.setListData(list);
    }

    public void setSongListsPaths(List<String> songListsPaths)
    {
        list.clear();
        list.addAll(songListsPaths);
        songListPaths.setListData(list);
    }

}
