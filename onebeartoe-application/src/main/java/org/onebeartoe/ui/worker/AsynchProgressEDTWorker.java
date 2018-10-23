package org.onebeartoe.ui.worker;

import java.awt.Component;
import java.awt.Cursor;

public class AsynchProgressEDTWorker extends AsynchEDTWorker 
{

	public AsynchProgressEDTWorker(Component guiParent) 
	{
		super(guiParent);		
	}

    @Override
    public String doInBackground() 
    {
    	int [] nums = {1,2,3,4,5,6,7,8,9,10};
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
            setProgress(100 * i / nums.length);
    	}
    			
        return "doing";
    }

    @Override
    protected void done() 
    {
    	guiParent.setCursor(Cursor.getDefaultCursor() );
    }
    
}
