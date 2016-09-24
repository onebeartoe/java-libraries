
package org.onebeartoe.web.utilities.jsp;

/**
 *
 * Given the filesystem location of a JEE Web directory and an accessible (target) 
 * directoy, create supporting index.jsp, WEB-INF/jsp/.../bottom.jsp, 
 * WEB-INF/jsp/.../index.jsp, and properties.jsp
 * 
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 */
public interface JspSeederService 
{
    public boolean indexSeed();
}
