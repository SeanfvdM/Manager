package com.badas.datamanager.File;

import com.badas.datamanager.Manager.Base;
import java.io.File;

/**
 * Project: StorageManager
 * By: Seanf
 * Created: 14,August,2020
 */
class Writer extends Base<File> {
    protected FileListener fileListener;

    public File createDirectory(File dirPath, String dirName) {
        File dir = new File(dirPath,dirName);
        if (!dir.exists()){
            if (!dir.mkdir()){
                if(fileListener!=null)
                    fileListener.onDirectoryCreated(dir);
            } else {
                if(fileListener!=null)
                    fileListener.onDirectoryCreateFailed(dir);
            }
        } else {
            if(fileListener!=null)
                fileListener.onDirectoryExists(dir);
        }
        return dir;
    }

    public File createJSONDataFile(File path, String name) throws Exception {
        File file = new File(path,name+".json");
        if (!file.exists()){
            if (!file.createNewFile()){
                if(fileListener!=null)
                    fileListener.onFileCreated(file);
            } else {
                if(fileListener!=null)
                    fileListener.onFileCreateFailed(file);
            }
        } else {
            if(fileListener!=null)
                fileListener.onFileExists(file);
        }
        return file;
    }
}
