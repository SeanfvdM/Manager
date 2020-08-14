package com.badas.datamanager.Manager;

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
     * Retrieves the base for calling
     * @return The base
     */
    public E get() {
        return this.type;
    }
}