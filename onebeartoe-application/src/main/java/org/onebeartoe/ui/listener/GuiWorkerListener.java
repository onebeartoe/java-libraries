package org.onebeartoe.ui.listener;

import java.awt.Component;
import java.awt.event.ActionListener;

public abstract class GuiWorkerListener implements ActionListener 
{

	protected Component parent;
	
	public GuiWorkerListener(Component parent)
	{
		this.parent = parent;
	}

}

