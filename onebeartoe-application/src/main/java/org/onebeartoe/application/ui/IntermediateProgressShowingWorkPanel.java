package org.onebeartoe.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class IntermediateProgressShowingWorkPanel extends JPanel 
{

	private static final long serialVersionUID = 991L;

	private JProgressBar progressBar;
	
	public IntermediateProgressShowingWorkPanel(ActionListener listener, JTextArea outputArea, JProgressBar progressBar)
	{
		this.progressBar = progressBar;
		
		JScrollPane scrollPane = new JScrollPane(outputArea);
		
		JButton button = new JButton("Run");
		button.addActionListener(listener);
		
		JPanel bottomPanel = new JPanel( new GridLayout(2, 1) );		
		bottomPanel.add(progressBar);
		bottomPanel.add(button);
		
		setLayout( new BorderLayout() );		
		add(scrollPane, BorderLayout.CENTER );	
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
}
