
package org.onebeartoe.ui.worker;

import javax.swing.JFrame;
import org.testng.annotations.Test;

/**
 *
 * @author Roberto Marquez
 */
public class AsynchProgressEDTWorkerSpecification 
{
    private AsynchProgressEDTWorker implementation;
    
    public AsynchProgressEDTWorkerSpecification()
    {
        JFrame parent = new JFrame();
        implementation = new AsynchProgressEDTWorker(parent);
    }
    
    @Test(groups = {"unit"})
    public void doInBackgroundAndDone()
    {
        implementation.doInBackground();
        
        implementation.done();
    }
}
