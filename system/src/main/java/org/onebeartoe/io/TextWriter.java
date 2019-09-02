
package org.onebeartoe.io;

import java.io.File;
import java.io.IOException;

public interface TextWriter 
{
	boolean writeText(File outfile, String text) throws IOException;
	
	boolean writeText(File outfile, String text, boolean append) throws IOException;
}
