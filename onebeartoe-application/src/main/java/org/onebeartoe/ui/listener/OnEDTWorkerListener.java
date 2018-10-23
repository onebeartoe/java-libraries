package org.onebeartoe.ui.listener;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;

public class OnEDTWorkerListener extends GuiWorkerListener
{

	public OnEDTWorkerListener(Component parent) {
		super(parent);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		parent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR) );
		try 
		{
			Thread.sleep(6000);
		} 
		catch (InterruptedException e1) 
		{			
			e1.printStackTrace();
                        
                        Thread.currentThread().interrupt();
		}
		parent.setCursor(Cursor.getDefaultCursor() );
	}

}
