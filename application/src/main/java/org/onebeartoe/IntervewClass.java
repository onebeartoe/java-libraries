/**
 * Cog nis ant 2023-01-09
 */


package org.onebeartoe;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author roberto
 */
public class IntervewClass 
{
    
    public static void main(String[] args) 
    {
        IntervewClass app = new IntervewClass();
        
        String input = "hello world" ;
        
        Map<String, Integer> map = app.histogram(input);
        
        app.removeDuplicates();                
    }
    
    /**
        Remove duplicate row in a 2 dimensional Array

String[][] employee = {
	            {"1","Joe","DCT"},
	            {"1","David","DCT"},
	            {"1","Siva","DCT"},
	            {"1","Siva","DCT"},
	            {"1","Mike","DCT"},
	            {"1","Mike","DCT"} };

ID Name   Dept
1  Joe    DCT
1  David  DCT
1  Siva   DCT
1  Mike   DCT
     */
    public void removeDuplicates()
    {
        
    }
    
    
    /**
     * Histogram of character occurances
     * @param input
     * @return 
     */
    public Map<String, Integer> histogram(String input)
    {
        Map charsMap = new HashMap<String, Integer>();
        
        char [] chars = input.toCharArray();
        
        for(char c : chars)
        {
            String s = String.valueOf(c);
            
            Integer value = (Integer) charsMap.get(s);
            
            if(value == null)
            {
                charsMap.put(s, 1);
            }
            else
            {
                Integer incrementedValue = value + 1;
                
                charsMap.put(s, incrementedValue);
            }
        }
        
        Set<String> keySet = charsMap.keySet();
        
        for(String key : keySet)
        {
            Integer value = (Integer) charsMap.get(key);
            
            System.out.println(key + ": " + value);
        }        
        
        return charsMap;
    }
}
