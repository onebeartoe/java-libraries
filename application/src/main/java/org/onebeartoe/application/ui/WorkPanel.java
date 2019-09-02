package org.onebeartoe.application.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WorkPanel extends JPanel 
{

	private static final long serialVersionUID = 991L;

	public WorkPanel(ActionListener listener, ImageIcon image)
	{		
		JLabel picLabel = new JLabel(image);
		
		JButton button = new JButton("Run");
		button.addActionListener(listener);
		
		setLayout( new BorderLayout() );		
		add( picLabel, BorderLayout.CENTER );	
		add(button, BorderLayout.SOUTH);
	}
	
}
