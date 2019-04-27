package io.adafruit.service;


import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.onebeartoe.web.adafruit.io.AdafruitIoService;
import org.onebeartoe.web.adafruit.io.AioKeyLoader;
import org.onebeartoe.web.adafruit.io.ApacheAdafruitIoService;
import org.testng.annotations.Test;

/**
 *
 * @author Roberto Marquez
 */
public class FeedsTests
{
    private AdafruitIoService adafruitIoService;
    
    public FeedsTests()
    {
        String aioKey;
        try
        {
            aioKey = AioKeyLoader.load();
            adafruitIoService = new ApacheAdafruitIoService(aioKey);
        }
        catch (IOException ex)
        {
            Logger.getLogger(FeedsTests.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    @Test
    public void addData() throws IOException 
    {
        String name = "lizard-enclosure-top-temperature";
        String value = String.valueOf(7);
        
        adafruitIoService.addFeedData(name, value);
    }
    
    @Test
    public void createFeed() throws IOException
    {
        long milis = (new Date()).getTime();
        String feedName = "okay-to-delete-" + milis;
        
        adafruitIoService.createFeed(feedName);
    }
}
