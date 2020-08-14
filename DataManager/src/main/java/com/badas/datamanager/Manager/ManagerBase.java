package com.badas.datamanager.Manager;

/**
 * Project: StorageManager
 * By: Seanf
 * Created: 14,August,2020
 */
public interface ManagerBase <T> {
    T getMain();
    void setMain(T main);
    T getCurrent();
}
