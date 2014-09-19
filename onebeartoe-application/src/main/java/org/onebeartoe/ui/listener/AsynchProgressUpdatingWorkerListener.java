package org.onebeartoe.ui.listener;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JProgressBar;

import org.onebeartoe.ui.worker.AsynchProgressEDTWorker;

public class AsynchProgressUpdatingWorkerListener extends GuiWorkerListener 
{
	
	private JProgressBar progressBar;

	public AsynchProgressUpdatingWorkerListener(Component parent, JProgressBar progressBar) 
	{
		super(parent);		
		
		this.progressBar = progressBar;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		parent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR) );
		
		AsynchProgressEDTWorker worker = new AsynchProgressEDTWorker(parent);
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
