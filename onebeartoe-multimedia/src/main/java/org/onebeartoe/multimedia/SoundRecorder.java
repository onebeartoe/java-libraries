
package org.onebeartoe.multimedia;

import java.io.File;

/**
 * @author Roberto Marquez
 */
public interface SoundRecorder
{
	void start(File outputFile) throws Exception;
	
	void stop();
}
