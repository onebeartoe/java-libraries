package org.onebeartoe.ui.listener;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;

import org.onebeartoe.ui.worker.AsynchEDTWorker;

public class AsynchEDTWorkerListener extends GuiWorkerListener 
{

	public AsynchEDTWorkerListener(Component parent) 
	{
		super(parent);		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		parent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR) );
		
		AsynchEDTWorker worker = new AsynchEDTWorker(parent);
		worker.execute();
	}

}
