
package org.onebeartoe.application.ui.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
        
import org.onebeartoe.application.filesystem.FileSelectionMethods;
import org.onebeartoe.application.ui.GUITools;
import org.onebeartoe.filesystem.FileSystemSearcher;
import org.onebeartoe.filesystem.FileType;

public class FileSelectionPanel extends JPanel implements ActionListener 
{
	private static final long serialVersionUID = -7589899404797359860L;

	private FileType targetType;

	private String selectDirectoryTitle = "What Dir?";    

	private JButton selectButton;

	private File currentDirectory;
	
	private File currentFile;

	public static String title = "Resize Image Helper";

	private JTextField source;		

	JList selectFiles;
			
	ScrollableTextArea outputPanel;

	FileSelectionMethods fileSelectionMode;
	
	JCheckBox recursiveCheckbox;

	public FileSelectionPanel(FileType type)
	{
		this(type, FileSelectionMethods.SINGLE_DIRECTORY, false);
	}
	
	/**
	 * This GUI component shows the files that fit the targeted type. 
	 * @param type specifies what type of files to target.  Use values from onebeartoe.FileHelper, but if
	 * an invalid file type is pased in then the target type will be all files.
	 * @see onebeartoe.FileHelper 
	 */
	public FileSelectionPanel(FileType type, FileSelectionMethods mode, boolean showRecursive) 
	{
    	targetType = validateType(type);
    	
    	fileSelectionMode = mode;
	    	
		// set up GUI display componets
		JLabel sourceLabel = new JLabel("Enter the source directory here:");
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout(FlowLayout.LEFT) );
        labelPanel.add(sourceLabel);
          
        // set up input GUI componets
        source = new JTextField( "");
        source.setEnabled(false);
        selectButton = new JButton("Select Directory");
        selectButton.addActionListener(this);
        
        recursiveCheckbox = new JCheckBox("Include Sub-folders");
        recursiveCheckbox.addChangeListener( new ChangeListener() 
        {	
			public void stateChanged(ChangeEvent arg0) 
			{
				updateOutputPanel();			
			}
		});

        // set the layout for the file seletion  panel
        JPanel dirPanel = new JPanel( new BorderLayout() );                
        dirPanel.add(labelPanel, BorderLayout.NORTH);
        dirPanel.add(source, BorderLayout.CENTER);
        dirPanel.add(selectButton, BorderLayout.EAST);
        if(showRecursive)
        {
        	dirPanel.add(recursiveCheckbox, BorderLayout.SOUTH);
        }
        
                                			
		outputPanel = new ScrollableTextArea("\n \n \n");
		
		setLayout( new BorderLayout() );		
		add(dirPanel, BorderLayout.NORTH);
		add(outputPanel, BorderLayout.CENTER);
		
		// cause the GUI to update itself to reflect the inital state of the selection panel
		File initalDir = new File( System.getProperty("user.home") + File.separator +  "Desktop" );		
		source.setText( initalDir.getPath() );
		currentDirectory = initalDir;
		updateOutputPanel();
	}
	
	private FileType validateType(FileType type)
	{
		FileType validType = FileType.ALL_FILES;
    	
    	if(type == FileType.MULTIMEDIA)
    	{
    	    validType = type;
    	}
    	else if(type == FileType.IMAGE)
    	{
    	    validType = type;
    	}
    	
    	return validType;
	}
	
	public void actionPerformed(ActionEvent ae) 
	{		
		Object eventSource = ae.getSource();	
    	if(eventSource == selectButton) 
    	{
    		if(fileSelectionMode == FileSelectionMethods.SINGLE_DIRECTORY)
    		{
    			currentDirectory = GUITools.selectDirectory(selectDirectoryTitle);
        		if(currentDirectory != null) 
        		{
        			source.setText( currentDirectory.getPath() );	    	
        		}
    		}
    		else 
    		{
    			// single file selection
    			String title = "Select a file";
    			currentFile = GUITools.selectFile(FileSelectionMethods.SINGLE_FILE, title);
    			String path = currentFile.getParent();
    			source.setText(path);    			
    		}
    		
    		updateOutputPanel();
    	}
	}

	/**
	 * this method is called only when the currentDirectory has a value
	 *
	 */
	private void updateOutputPanel() 
	{
	    File [] files = getTargetedFiles();
	    
	    StringBuffer buf = new StringBuffer("\n");
	    for(File file : files) 
	    {
	    	buf.append("\n" + file.getName() + "\n");
	    }
	    outputPanel.setText( buf.toString() );
	}
	
	public File [] getTargetedFiles() 
	{
		File [] files;
		
		if(fileSelectionMode == FileSelectionMethods.SINGLE_DIRECTORY)
		{
			boolean recursive = recursiveCheckbox.isSelected();
			List<FileType> targets = new ArrayList<FileType>();
			targets.add(targetType);
			FileSystemSearcher searcher = new FileSystemSearcher(currentDirectory, targets, recursive);
			List<File> filesList = searcher.findTargetFiles();
    		files = filesList.toArray( new File[0] );
	    	
	    	if(files == null)
	    	{
	    		files = new File[0];
	    	}
		}
		else
		{
			if(currentFile == null)
			{
				files = new File[0];
			}
			else
			{
				files = new File[1];
				files[0] = currentFile;				
			}			
		}	    		    
	    
    	return files;
	}

}
