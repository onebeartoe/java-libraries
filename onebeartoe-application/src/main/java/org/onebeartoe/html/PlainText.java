
package org.onebeartoe.html;

public class PlainText extends HtmlTag
{
	
	String text;		
	
	public PlainText() 
	{
		text = "";
	}
	
	public PlainText(String text) 
	{
		this.text = text;
	}

	public void setText(String text) 
	{
		this.text = text;
	}

	@Override
	public String toString() 
	{
		return text;
	}
	
}
