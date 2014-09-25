
package org.onebeartoe.filesystem;

/**
 * @deprecated use the version at https://github.com/onebeartoe/java-libraries
 * Created on May 2, 2007
 * @author roberto.marquez
 */
public enum FileType 
{

   MULTIMEDIA("great"),
   AUDIO("good"),
   IMAGE("ok"),
   DIRECTORY("eh"),
   TEXT("loser"),
   REGULAR("Regular"),
   UNKNOWN("Unkown"),
   ZIP("Zip Formatted Files"),
   ALL_FILES("ANY TYPE");
   

   private String message;

   FileType(String message) 
   {
	  this.message = message;
   }

   String getMessage() 
   {
	  return message;
   }

}

