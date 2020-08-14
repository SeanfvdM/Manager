package com.badas.datamanager.File;

import android.Manifest;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Project: StorageManager
 * By: Seanf
 * Created: 14,August,2020
 */
class Reader extends Writer {
    /***
     * Retrieves all of the files from the file if there are any to retrieve.
     * @param file The file you would like to retrieve data from.
     * @return The list of file that are contained within the parent file.
     */
    public ArrayList<File> retrieveFiles(File file) {
        return new ArrayList<>(Arrays.asList(Objects.requireNonNull(file.listFiles())));
    }

    @Override
    protected String[] getPermission() {
        String[] temp = Arrays.copyOf(super.getPermission(), 2);
        temp[1] = Manifest.permission.READ_EXTERNAL_STORAGE;
        return temp;
    }
}
