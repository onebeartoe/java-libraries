
package org.onebeartoe.html;

/**
 *
 * @author Roberto Marquez
 * 
 */
public abstract class LinkTag implements HtmlTag 
{

//       <link rel="stylesheet" type="text/css" href="http://www.onebeartoe.com/tours/web/css/layouts/toughest/style.css" />
	
	
	String rel;
	String type;
	String href;
	
	public LinkTag() 
	{
//		breakTag = "<br/>";
	}
	
	public LinkTag(String breakTag) 
	{
//		this.breakTag = breakTag;
	}
	
        @Override
	public String toHtml()
	{
		StringBuffer tag = new StringBuffer();

		tag.append("<link rel=\"");
		tag.append(rel);
		tag.append("\"");
		tag.append(" type=\"");
		tag.append(type);
		tag.append("\"");
		tag.append(" href=\"");
		tag.append(href);
		tag.append("\" />");
		
		return tag.toString();		
	}
	
}
