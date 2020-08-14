package com.badas.datamanager.Manager;

/**
 * Project: StorageManager
 * By: Seanf
 * Created: 14,August,2020
 */
public class Manager <E extends ManagerBase<?>> {
    private E type;

    public Manager() {
    }

    public void init(E base){
        type = base;
    }

    public E get(){
        return this.type;
    }
}