package org.onebeartoe.ui.worker;

import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JTextArea;

public class AsynchIntermediateProgressEDTWorker extends AsynchEDTWorker 
{

	private JTextArea outputArea;
	
	public AsynchIntermediateProgressEDTWorker(Component guiParent, JTextArea outputArea) 
	{
		super(guiParent);
		
		this.outputArea = outputArea;
	}

    @Override
    public String doInBackground() 
    {
    	publish("Running tasks...\n");
    	
    	int [] nums = {1,2,3};
    	for(int i : nums)
    	{
    		try 
    		{
    			Thread.sleep(1000);
    		} 
    		catch (InterruptedException e1) 
    		{
    			System.err.println("error in iteration " + i);
    			e1.printStackTrace();
			
			Thread.currentThread().interrupt();
    		}
    		publish("task " + i + " is complete.\n");
    		setProgress(100 * i / nums.length);    		    		    		    	
    	}
    			
		publish("The last task is finished.");
		
        return "doing";
    }
    
    @Override
    protected void process(java.util.List<Object> chunks) 
    {
    	for(Object o : chunks)
    	{
    		String s = (String) o;
    		outputArea.append(s);
    	}
    }
    
    @Override
    protected void done() 
    {
    	guiParent.setCursor(Cursor.getDefaultCursor() );
    }

    
}
