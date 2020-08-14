package com.badas.datamanager.Manager;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: StorageManager
 * By: Seanf
 * Created: 14,August,2020
 */
public class Manager<E extends ManagerBase<?>> {
    private E type;

    /***
     * Initialise the manager
     * @param base the constructor of the base
     */
    public void init(E base) {
        type = base;
    }

    /***
     * Initialise the manager. Will also check for any required permissions.
     * @param base the constructor of the base
     * @param activity Used for checking permissions
     * @return Will return <code>true</code> if the permissions are granted or there are no permissions.
     */
    public boolean init(E base, Activity activity) {
        type = base;
        return checkPermissions(activity);
    }

    /***
     * Retrieves the base for calling
     * @return The base
     */
    public E get() {
        return this.type;
    }

    /***
     * Will check permissions required by the base.
     * @param activity Give the ability to request permission.
     * @return Will return <code>true</code> if all permissions are granted
     */
    public boolean checkPermissions(Activity activity) {
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String perm : ((Base) type).getPermission()) {
            if (ContextCompat.checkSelfPermission(activity, perm) == PackageManager.PERMISSION_DENIED) {
                listPermissionsNeeded.add(perm);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), ((Base) type).PERMISSION_REQUEST);
            return false;
        }
        return true;
    }
}