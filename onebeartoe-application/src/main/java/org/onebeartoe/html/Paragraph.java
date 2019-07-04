package org.onebeartoe.html;

public class Paragraph extends HtmlTag
{
	String text;
	
	
	public String toString()	
	{
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<p>" + "\n");
		stringBuffer.append("\t");
		stringBuffer.append(text);
		stringBuffer.append("\n" + "</p>" + "\n");
					
		return stringBuffer.toString();
	}


	public void setText(String text) 
	{
		this.text = text;
	}
	
}
