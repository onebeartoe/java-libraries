package org.onebeartoe.ui.listener;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		}
		parent.setCursor(Cursor.getDefaultCursor() );
	}

}
