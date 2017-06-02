
package org.onebeartoe.io.serial;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.onebeartoe.system.OperatingSystem;

/**
 * @author Roberto Marquez
 */
public class SerialPorts 
{
    /**
     * The port we're normally going to use.
     */
    private static final String [] PORT_NAMES = 
    {
        "COM17"
//        "COM24"            
//            , // Windows
//            "/dev/tty.usbserial-A9007UX1", // Mac OS X
//            "/dev/ttyACM0", // Raspberry Pi
//            "/dev/ttyUSB0" // Linux
            
    };
    
    /**
     * Milliseconds to block while waiting for port open
     */
    private static final int TIME_OUT = 2000;
    
    /**
     * Default bits per second for COM port.
     */
    private static final int DATA_RATE = 9600;    
    
    /**
     * Don't for get to call addEventListener() on the returned SerialPort object.
     * 
     * @param portName - The path or name of the serial port.  Paths are used on 
     * Linux type operating systems and names are used on MS Windows type operating 
     * systems.
     *
     * @return A serial port object found at the first path in PORT_NAMES list.
     * 
     * @throws Exception
     * @throws NoClassDefFoundError 
     */
    public static SerialPort get(String portName) throws Exception, NoClassDefFoundError
    {
        // the next line checks if the
        // JRE is on the is on Linux (including Raspberry Pi Debian        
        OperatingSystem os = new OperatingSystem();
        if( !os.seemsLikeMsWindows() )
        {
            // assume we are on Linux
            // and as suggested here: http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
            // TODO: Should the second parameter be the portName?
            System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");
            System.out.println("The serial port system property is set.");
        }

        CommPortIdentifier portId = null;
                        
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
        //First, Find an instance of serial port as set in PORT_NAMES.
        while (portEnum.hasMoreElements()) 
        {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            
            System.out.println("port ID: " + currPortId);
            System.out.println("trying: " + portName);
            
            if (currPortId.getName().equals(portName)) 
            {
                portId = currPortId;
                break;
            }
        }

        SerialPort serialPort = null;
        
        if (portId == null) 
        {
            throw new Exception("Could not find COM port.");
        }
        else
        {
            String name = SerialPorts.class.getName();
            // open serial port, and use class name for the appName.
            serialPort = (SerialPort) portId.open(name, TIME_OUT);

            // set port parameters
            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
                
                // open the streams
//                InputStream is = serialPort.getInputStream();
//                InputStreamReader isr = new InputStreamReader(is);
//                input = new BufferedReader(isr);
//
//                output = serialPort.getOutputStream();
//
//                // add event listeners
//                serialPort.addEventListener(this);

            serialPort.notifyOnDataAvailable(true);
        }
        
        if(serialPort == null)
        {
            throw new Exception("could not find a serial port");
        }
        
        return serialPort;
    }
    
    /**
     * This method closes the input and output streams of the serial port argument.'
     * It also removes event listener and closes the serial port.
     * @param serialPort 
     */
    public static void shutdown(SerialPort serialPort)
    {
        if (serialPort != null) 
        {
            try
            {
                OutputStream outputStream = serialPort.getOutputStream();
                outputStream.close();
            }
            catch (IOException ex)
            {
                Logger.getLogger(SerialPorts.class.getName()).log(Level.SEVERE, null, ex);
            }

            try
            {
                InputStream inputStream = serialPort.getInputStream();
                inputStream.close();
            }
            catch (IOException ex)
            {
                Logger.getLogger(SerialPorts.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            serialPort.removeEventListener();
            serialPort.close();
        }
    }
}
