
package org.onebeartoe.web.adafruit.io;

/**
 * This class represens a 
 * @author Roberto Marquez
 */
public class FeedData
{
    private String id;
    
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
            String id = split[0].trim();
            
            feedData.setId(id);
        }
        else
        {
            feedData.setId("id-not-set");
        }
        
        if(split.length > 1)
        {
            String topic = split[1].trim();
            
            feedData.setTopic(topic);
        }
        else
        {
            feedData.setTopic("topic-is-not-set");
        }
        
        if(split.length > 2)
        {
            String value = split[2].trim();
            
            feedData.setValue(value);
        }
        else
        {
            feedData.setValue("value-is-not-set");
        }
        
        return feedData;
    }

    private void setId(String id)
    {
        this.id = id;
    }
}
