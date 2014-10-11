
package org.onebeartoe.filesystem;

/**
 * Created on May 2, 2007
 * @author roberto.marquez
 */
public enum FileType 
{
   MULTIMEDIA("multimedia"),
   AUDIO("audio"),
   IMAGE("images"),
   DIRECTORY("directories"),
   TEXT("text"),
   REGULAR("Regular"),
   UNKNOWN("Unkown"),
   ZIP("Zip Formatted Files"),
   ALL_FILES("Any type");
   
   private String message;

   FileType(String message) 
   {
    this.message = message;
   }

   public String getMessage() 
   {
       return message;
   }
}
