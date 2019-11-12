
package org.onebeartoe.application.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * This logger class is used just like System.out in an effort to avoid Sonar issues 
 * when applications are intended to write to standard out.
 */
public class SysoutLoggerFactory
{
    public static Logger getLogger(String name)
    {
        Logger logger = Logger.getLogger(name);
        
        logger.setUseParentHandlers(false);
        
        SysoutRecordFormatter formatter = new SysoutRecordFormatter();
        
        ConsoleHandler consoleHandler = new ConsoleHandler();
        
        consoleHandler.setFormatter(formatter);
        
        logger.addHandler(consoleHandler);        
        
        return logger;
    }

    static class SysoutRecordFormatter extends SimpleFormatter 
    {
        @Override
        public synchronized String format(final LogRecord r) 
        {
            StringBuilder sb = new StringBuilder();

            sb.append(formatMessage(r)).append(System.getProperty("line.separator"));

            if (null != r.getThrown()) 
            {
                sb.append("Throwable occurred: "); //$NON-NLS-1$
                Throwable t = r.getThrown();
                PrintWriter pw = null;
                
                StringWriter sw = new StringWriter();
                pw = new PrintWriter(sw);
                t.printStackTrace(pw);
                sb.append(sw.toString());
            }

            return sb.toString();
        }
    }
}
