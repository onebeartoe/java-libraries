package org.onebeartoe.application.ui.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JLabel;
import org.onebeartoe.application.ui.GUITools;

/**
 * @author Roberto Marquez
 */
public class TaskPanel extends javax.swing.JPanel implements ActionListener 
{
	static final long serialVersionUID = 3L;
	
	private File infile;
	private boolean on_task;
	private JButton invokeBtn;
	private transient TimerTask task;
	
	public TaskPanel(TimerTask task, String instructions) 
        {
		this.task = task;
		on_task = false;

		JLabel archiveLabel = new JLabel(instructions);
		invokeBtn = new JButton("Generage HTML File");
		invokeBtn.addActionListener(this);
                
		setLayout( new BorderLayout() );
		add(archiveLabel, BorderLayout.CENTER );
		add(invokeBtn, BorderLayout.SOUTH );	
	}
	
	public File getSelectedFile() {
		return infile;
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(on_task) 
		{
                    Date date = new Date();
                    Timer timer = new Timer();
                                
                    
                    
                    timer.schedule(task, date);      
                     
                    
                                
				
		}
		else {
			GUITools.infoMessage("can not perform task yet");
		}
				
	}
	
	public void setOnTask(boolean val) {
		on_task = val;
	}
	
}
