package org.onebeartoe.application.ui;

import java.awt.GridLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import org.onebeartoe.ui.listener.AsynchEDTWorkerListener;
import org.onebeartoe.ui.listener.AsynchIntermediateProgressUpdatingWorkerListener;
import org.onebeartoe.ui.listener.AsynchProgressUpdatingWorkerListener;
import org.onebeartoe.ui.listener.OnEDTWorkerListener;

public class EDTDemoGui extends JPanel 
{

	private static final long serialVersionUID = -78L;

	public EDTDemoGui()
	{
		OnEDTWorkerListener onEdtWorker = new OnEDTWorkerListener(this);
		URL url = getClass().getResource("101px-Seven_segment_display-animated.gif");
		ImageIcon icon = new ImageIcon(url);
		WorkPanel onEdtPanel = new WorkPanel(onEdtWorker, icon);
		
		AsynchEDTWorkerListener asynchWorker = new AsynchEDTWorkerListener(this);
		url = getClass().getResource("96px-Seven_segment_display-animated.gif");
		icon = new ImageIcon(url);
		WorkPanel asynchWorkPanel = new WorkPanel(asynchWorker, icon);
		
		JProgressBar progressBar = new JProgressBar();
		AsynchProgressUpdatingWorkerListener asynchProgressWorker = new AsynchProgressUpdatingWorkerListener(this, progressBar);
		url = getClass().getResource("101px-Seven_segment_display-animated.gif");
		icon = new ImageIcon(url);		
		ProgressShowingWorkPanel progressPanel = new ProgressShowingWorkPanel(asynchProgressWorker, icon, progressBar);
		
		JProgressBar intermeidateProgressBar = new JProgressBar();
		JTextArea outputArea = new JTextArea();
		AsynchIntermediateProgressUpdatingWorkerListener asynchIntermediateProgressWorker = new AsynchIntermediateProgressUpdatingWorkerListener(this, intermeidateProgressBar, outputArea);
		IntermediateProgressShowingWorkPanel intermediateProgressPanel = new IntermediateProgressShowingWorkPanel(asynchIntermediateProgressWorker, outputArea, intermeidateProgressBar);
			
		setLayout( new GridLayout(1, 4) );
		add(onEdtPanel);
		add(asynchWorkPanel);
		add(progressPanel);
		add(intermediateProgressPanel);
	}	
}