package org.onebeartoe.application.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class ProgressShowingWorkPanel extends JPanel 
{

	private static final long serialVersionUID = 991L;

	private JProgressBar progressBar;
	
	public ProgressShowingWorkPanel(ActionListener listener, ImageIcon image, JProgressBar progressBar)
	{
		this.progressBar = progressBar;
		
		JLabel picLabel = new JLabel(image);
		
		JButton button = new JButton("Run");
		button.addActionListener(listener);
		
		JPanel bottomPanel = new JPanel( new GridLayout(2, 1) );		
		bottomPanel.add(progressBar);
		bottomPanel.add(button);
		
		setLayout( new BorderLayout() );		
		add( picLabel, BorderLayout.CENTER );	
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
}
