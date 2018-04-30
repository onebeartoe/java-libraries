
package org.onebeartoe.html;

public class AnchorTag implements HtmlTag
{
    private String href;
    private String text;
	
	public AnchorTag(String href, String text)
	{
		this.href = href;
		this.text= text;
	}
	
        @Override
	public String toHtml()
	{
		StringBuffer buf = new StringBuffer("<a href=\"");
		buf.append(href);
		buf.append("\">");
		buf.append(text);
		buf.append("</a>");		
		return buf.toString();
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}	
}