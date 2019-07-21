
package org.onebeartoe.html;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


/**
 * @author rmarquez
 */
public abstract class BasicContainerTag extends XhtmlContainerTag
{
    protected List<HtmlTag> innerTags;
    
    private boolean prettyPrint;
    
//    private final Tidy tidy;

    public boolean isPrettyPrint()
    {
        return prettyPrint;
    }

    public void setPrettyPrint(boolean prettyPrint)
    {
        this.prettyPrint = prettyPrint;
    }
    
    public BasicContainerTag()
    {
//        tidy = new Tidy();
//        tidy.setXHTML(false);
//        tidy.setIndentContent(true);
//        tidy.setPrintBodyOnly(true);
//        tidy.setQuiet(true);
        
        innerTags = new ArrayList();
    }

    @Override
    public void add(HtmlTag htmlTag)
    {
        innerTags.add(htmlTag);
    }

    @Override
    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        
        buf.append( getOpenTag() );
        
        for(HtmlTag tag : innerTags)
        {
            buf.append( tag.toString() );
        }
        
        buf.append( getCloseTag() );

        String tag = buf.toString();
        
        if(prettyPrint)
        {
            Document.OutputSettings settings = new Document.OutputSettings();
            settings.prettyPrint(true);
            settings.indentAmount(4);
            settings.outline(true);
            Document doc = Jsoup.parseBodyFragment(tag);
            doc.outputSettings(settings);
                        
//            String s = new StringWriter().toString();
            
//            tag = doc.html();
            tag = doc.body().html();
        }    
        
        return tag;
    }
}

/**
 * JTidy stuff
            InputStream htmlInputStream = new ByteArrayInputStream(tag.getBytes() );
            OutputStream noopStream = null;
            Document document = tidy.parseDOM(htmlInputStream, noopStream);
            
            Node body = document.getElementsByTagName("body").item(0);
     
            NodeList childNodes = body.getChildNodes();
            
            OutputStream outstream = new ByteArrayOutputStream();
            
            for(int i = 0; i < childNodes.getLength(); i++)
            {
                tidy.pprint(childNodes.item(i), outstream);
            }
            
            tag = outstream.toString(); 
 * 
 */
