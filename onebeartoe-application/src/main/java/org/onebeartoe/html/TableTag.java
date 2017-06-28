
package org.onebeartoe.html;

/**
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 */
public class TableTag implements HtmlTag
{
    Object [][] cells;
    
    int tablePercent;
    
    public TableTag(Object [][] cells, int tablePercent)
    {
        this.cells = cells;
        
        this.tablePercent = tablePercent;
    }
    
    @Override
    public String toHtml() 
    {
        int rows = cells.length;
        int columns = cells[0].length;
        
        StringBuffer tag = new StringBuffer();        
        tag.append("<table border=\"1\" width=\"" + tablePercent + "%\">");
        
        for(int r=0; r<rows; r++) 
        {
            tag.append("<tr>\n");
            for(int c=0; c<columns; c++) 
            {
                tag.append("<td width=\"" + (tablePercent/columns) + "%\" align=\"center\">");

                Object o = cells[r][c];
System.out.println("object o: " + o);

                o = o == null ? "" : o;
                
                String cell = o.toString();
                tag.append(cell);

                tag.append("</td>\n");
            }
            tag.append("</tr>\n");
        }
        tag.append("</table>");
        
        String html = tag.toString();
                
        return html;
    }    
}    