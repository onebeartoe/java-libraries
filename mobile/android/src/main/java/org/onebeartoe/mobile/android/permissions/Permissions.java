
package org.onebeartoe.mobile.android.permissions;

import android.app.TabActivity;
import android.content.pm.PackageManager;

//import androidx.core.app.ActivityCompat;

/**
 * This class provides utility methods for permissions on the Android platform.
 */
public class Permissions 
{
    /**
     * 
     * @param requiredPermissions These values come from package 
     *          <b>
     *              android.Manifest.Manifest.permission.*
     *          </b>
     * 
     *          <br/>
     * 
     *          Examples include: 
     *          <b>
     *          Manifest.permission.RECORD_AUDIO,
     *		Manifest.permission.READ_EXTERNAL_STORAGE,
     *          Manifest.permission.WRITE_EXTERNAL_STORAGE
     * 
     * @return true if any permissions are missing, false otherwise
     */
    public static boolean areMissing(String [] requiredPermissions, TabActivity activity, String packageName)
    {
        boolean missing = false;

        PackageManager pm = activity.getPackageManager();
        
        for(String permission : requiredPermissions)
        {
            int result = pm.checkPermission(permission, packageName);
//            int result = ActivityCompat.checkSelfPermission(activity, permission);

            if(result != PackageManager.PERMISSION_GRANTED)
            {
                missing = true;
                
                break;
            }
        }
        
        return missing;
    }    		
}
