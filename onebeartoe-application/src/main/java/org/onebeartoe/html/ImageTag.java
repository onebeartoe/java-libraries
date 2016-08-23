
package org.onebeartoe.html;

/**
 * created 2007.10.23
 * @author rob
 */
public class ImageTag 
{

	// the model:
	//
	//		<img src="../01/cento_thmb.gif" width="100" height="100" alt="cento.gif (12247 bytes)" />
	
	private String src;
	private Integer width;
	private Integer height;
	private String alt;
	
	public ImageTag(String src, Integer width, Integer height, String alt)
	{
		this.src = src;
		this.width = width;
		this.height = height;
		this.alt = alt;
	}
	
	public String toString()
	{
		StringBuffer buf = new StringBuffer("<img src=\"");
		buf.append(src);
		buf.append("\" width=\"");
		buf.append(width);
		buf.append("\" heigth=\"");
		buf.append(height);
		buf.append("\" alt=\"");
		buf.append(alt);
		buf.append("\" />");
		
		return buf.toString(); 
	}
	
}
