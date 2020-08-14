package com.badas.datamanager.Manager;

import java.util.Random;

/**
 * Project: StorageManager
 * By: Seanf
 * Created: 14,August,2020
 */
public class Base<T> {
    /***
     * Base variables
     */
    protected T current, main;

    public int PERMISSION_REQUEST = new Random().nextInt(1000);

    /***
     * The permissions the class requires
     * @return The permissions
     */
    protected String[] getPermission() {
        return new String[0];
    }
}

