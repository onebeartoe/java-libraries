
package org.onebeartoe.multimedia.juke.links;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML.Attribute;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.html.HTMLEditorKit.ParserCallback;
import javax.swing.text.html.parser.ParserDelegator;

import org.onebeartoe.multimedia.juke.links.LinkManager.Link;
import org.onebeartoe.multimedia.juke.links.LinkManager.LinkUnit;

/**
 * @deprecated use the version at https://github.com/onebeartoe/java-libraries
 * @author Roberto Marquez
 */
public class SwingLinkParser extends ParserCallback implements LinkParser 
{

	private boolean saveLinkLabel = false;

	private LinkUnit linkUnit;

	private Link link;	
	
	@Override
	public LinkUnit extractLinks(String name, URL url) throws IOException 
//	public LinkUnit extractLinks(String name, Reader reader) throws IOException 
	{	
		linkUnit = new LinkUnit();
		linkUnit.name = name;
		ParserDelegator parserDelegator = new ParserDelegator();		
		boolean ignoreCharSet = true;
		InputStream instream = url.openStream();
		InputStreamReader reader = new InputStreamReader(instream);
//		LinkParser parser = getLinkParser();
		parserDelegator.parse(reader, this, ignoreCharSet);
		
		return linkUnit;
	}

	@Override
	public void handleEndTag(Tag tag, final int pos) 
	{
		if(tag == Tag.A)
		{
			saveLinkLabel = false;
			linkUnit.add(link);
		}
	}
	
	@Override
	public void handleText(final char[] data, final int pos) 
	{
		if(saveLinkLabel)
		{
			link.label = String.valueOf(data);		
		}
	}

	@Override
	public void handleStartTag(Tag tag, MutableAttributeSet attribute, int pos) 
	{
		if (tag == Tag.A) 
		{
			String address = (String) attribute.getAttribute(Attribute.HREF);			
			link = new Link();
			link.href = address;
			saveLinkLabel = true;
		}
	}	
	
}
