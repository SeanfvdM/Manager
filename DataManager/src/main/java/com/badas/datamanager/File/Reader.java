package com.badas.datamanager.File;

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
    public ArrayList<File> retrieveFiles(File file){
        return new ArrayList<>(Arrays.asList(Objects.requireNonNull(file.listFiles())));
    }
}
