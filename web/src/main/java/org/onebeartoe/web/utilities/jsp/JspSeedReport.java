
package org.onebeartoe.web.utilities.jsp;

/**
 *
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 */
public class JspSeedReport 
{
    public SeedResult index;
    
    public SeedResult webinfIndex;
    
    public SeedResult webinfProperties;
    
    public SeedResult  webinfBottom;
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append( index.toString() );
        sb.append("\n");
        sb.append( webinfIndex.toString() );
        sb.append("\n");
        sb.append( webinfProperties.toString() );
        sb.append("\n");
        sb.append( webinfBottom.toString() );
        sb.append("\n");

        return sb.toString();
    }
}
