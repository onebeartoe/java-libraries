
package org.onebeartoe.io.buffered;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.onebeartoe.io.TextFileReader;

/**
 *
 * @author Roberto Marquez
 * 
 */
public class BufferedTextFileReader implements TextFileReader
{
    @Override
    public List<String> readLines(InputStream instream) throws IOException
    {
        List<String> names = new ArrayList();	
        
        BufferedReader br = new BufferedReader(new InputStreamReader(instream));
	String line = br.readLine();  	
	while (line != null)   
	{
	    names.add(line);
	    line = br.readLine();
	}	
	instream.close();
        
        return names;
    }
    
    @Override
    public String readTextFromClasspath(String infileClaspath) throws IOException
    {
        List<String> lines = readTextLinesFromClasspath(infileClaspath);
        StringBuilder sb = new StringBuilder();
        for(String line : lines)
        {
            sb.append(line);
        }
        
        return sb.toString();
    }    
    
    @Override
    public List<String> readTextLinesFromClasspath(String infileClaspath) throws IOException
    {
        
	InputStream instream = getClass().getResourceAsStream(infileClaspath);
	
        List<String> lines = readLines(instream);
        
        return lines;
    }
}
