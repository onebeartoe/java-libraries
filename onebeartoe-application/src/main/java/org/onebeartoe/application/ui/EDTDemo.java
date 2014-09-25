package org.onebeartoe.application.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class EDTDemo extends JFrame 
{
	
	static final long serialVersionUID = 7891L;

	public EDTDemo()
	{
		setSize(650, 400);		
		setLocation(20, 700);			
		setLayout( new BorderLayout() );
		
		EDTDemoGui gui = new EDTDemoGui();
		add(gui, BorderLayout.CENTER);
		
		setVisible(true);
	}
		
	public static void main(String[] args) 
	{
		EDTDemo demo = new EDTDemo();
		demo.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
