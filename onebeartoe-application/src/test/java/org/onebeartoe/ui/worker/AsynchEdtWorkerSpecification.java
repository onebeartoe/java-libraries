
package org.onebeartoe.ui.worker;

import javax.swing.JFrame;
import org.testng.annotations.Test;

/**
 * @author Roberto Marquez
 */
public class AsynchEdtWorkerSpecification 
{
    private AsynchEDTWorker implementation;
    
    public AsynchEdtWorkerSpecification()
    {
        JFrame parent = new JFrame();
        implementation = new AsynchEDTWorker(parent);
    }
    
    @Test(groups = {"unit"})
    public void doInBackgroundAndDone()
    {
        // calling interrupt() on the current Thread causes the inturreupted exception (for code coverage).
//        Thread.currentThread().interrupt();
        
        implementation.doInBackground();
        
        implementation.done();
    }
}
