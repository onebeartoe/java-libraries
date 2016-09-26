
package org.onebeartoe.web.utilities.jsp;

import java.io.File;
import java.io.IOException;

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
    /**
     * 
     * @param webRoot the directory of the wep application that is the root context
     * @param childDirectory the path of the directory to index
     * @return 
     */
    public boolean seedIndex(File webRoot, String childDirectory) throws IOException;
}
