
package org.onebeartoe.web.adafruit.io;

/**
 * This class represens a 
 * @author Roberto Marquez
 */
public class FeedData
{
    private String topic;
    
    private String value;

    public String getTopic()
    {
        return topic;
    }

    public void setTopic(String topic)
    {
        this.topic = topic;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
    
    public static FeedData valueOf(String arduinoMessage)
    {
        String[] split = arduinoMessage.split(":");
        
        FeedData feedData = new FeedData();
        
        if(split.length > 0)
        {
            String topic = split[0].trim();
            
            feedData.setTopic(topic);
        }
        
        if(split.length > 1)
        {
            String value = split[1].trim();
            
            feedData.setValue(value);
        }
        
        return feedData;
    }
}
