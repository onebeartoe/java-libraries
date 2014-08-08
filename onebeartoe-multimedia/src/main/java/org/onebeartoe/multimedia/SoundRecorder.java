
package org.onebeartoe.multimedia;

import java.io.File;

/**
 * @author roberto
 * @deprecated Use https://github.com/onebeartoe/java-libraries instead
 */
public interface SoundRecorder 
{
	void start(File outputFile) throws Exception;
	
	void stop();
}
