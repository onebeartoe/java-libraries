
package org.onebeartoe.html.pages;

import java.util.HashMap;
import java.util.Set;
import org.onebeartoe.html.CodeGenerator;

public class Menu 
{
    long id;

    String title;

    /**
     * the keys are the label on the menu, the map values are the Page link
     */
    HashMap<String, String> options;
    
    private CodeGenerator codeGenerator;

    public Menu()
    {
            id = -1;
            title = "";
            options = new HashMap<String, String>();
            
            codeGenerator = new CodeGenerator();
    }
	
	public Menu(String title, HashMap<String, String> options)
	{
		id = -1;
		this.title = title;
		this.options = options;
	}
	
	public void addOption(String label, String link)
	{
		options.put(label, link);
	}
	
	public String toString()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("<div class=\"menu\">" + "\n");
		buf.append("\t<h3>" + title + "</h3>" + "\n");
		
		Set<String> keys = options.keySet();
		String label, link;
		for(String key : keys)
		{
			label = key;
			link = options.get(key);			
			String anchorTag = codeGenerator.getLinkTag(link, label);		
			buf.append("\t\t" + anchorTag  + "\n");
		}
				
		buf.append("</div>"  + "\n\n");
		
		return buf.toString();
	}

	
	public String getTitle() 
	{
		return title;
	}

	
	public void setTitle(String title) 
	{
		this.title = title;
	}

	public HashMap<String, String> getOptions() 
	{
		return options;
	}

    public void setOptions(HashMap<String, String> options) 
    {
            this.options = options;
    }

    /**
     * 
     * @return a negative value for objects that have not been persisted, otherwise the primary key of this 
     * object in the database.
     */
    public long getId() 
    {
            return id;
    }

    public void setId(long id) 
    {
            this.id = id;
    }
}
