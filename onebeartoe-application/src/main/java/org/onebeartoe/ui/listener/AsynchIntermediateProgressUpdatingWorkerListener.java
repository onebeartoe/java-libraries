package org.onebeartoe.ui.listener;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import org.onebeartoe.ui.worker.AsynchIntermediateProgressEDTWorker;

public class AsynchIntermediateProgressUpdatingWorkerListener extends GuiWorkerListener 
{
	
	protected JProgressBar progressBar;
	
	protected JTextArea outputArea;

	public AsynchIntermediateProgressUpdatingWorkerListener(Component parent, JProgressBar progressBar, JTextArea outputArea) 
	{
		super(parent);		
		
		this.progressBar = progressBar;
		this.outputArea = outputArea;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		parent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR) );
		
		AsynchIntermediateProgressEDTWorker worker = new AsynchIntermediateProgressEDTWorker(parent, outputArea);
		worker.addPropertyChangeListener(
			     new PropertyChangeListener() 
			     {
			         public  void propertyChange(PropertyChangeEvent evt) 
			         {
			             if ("progress".equals(evt.getPropertyName())) 
			             {
			                 progressBar.setValue((Integer)evt.getNewValue());
			             }
			         }
			     });

		worker.execute();
	}

}
