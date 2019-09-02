
package org.onebeartoe.ui.worker;

import java.awt.Component;
import java.awt.Cursor;

import javax.swing.SwingWorker;

public class AsynchEDTWorker extends SwingWorker<String, Object> 
{
	protected Component guiParent;
	
	public AsynchEDTWorker(Component guiParent)
	{
		this.guiParent = guiParent;
	}
	
    @Override
    public String doInBackground() 
    {
    	try 
        {
            Thread.sleep(6000);
        } 
        catch (InterruptedException e1) 
        {			
//            e1.printStackTrace();
            
            Thread.currentThread().interrupt();
        }
		
        return "doing";
    }

    @Override
    protected void done() 
    {
    	guiParent.setCursor(Cursor.getDefaultCursor() );
    }

}
