package com.badas.datamanager.File;

import com.badas.datamanager.Manager.Base;

import java.io.File;
import java.io.IOException;

/**
 * Project: StorageManager
 * By: Seanf
 * Created: 14,August,2020
 */
class Writer extends Base<File> {
    protected FileListener fileListener;

    /***
     * Creates a folder at the desired destination with the new name.
     * @param dirPath The location where the folder will be created. This will fail if <code>dirPath.isFile() == true</code>
     * @param dirName The name of the folder.
     * @return The new Folder for easy access.
     */
    public File createDirectory(File dirPath, String dirName) {
        File dir = new File(dirPath, dirName);
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                if (fileListener != null)
                    fileListener.onDirectoryCreated(dir);
            } else {
                if (fileListener != null)
                    fileListener.onDirectoryCreateFailed(dir);
            }
        } else {
            if (fileListener != null)
                fileListener.onDirectoryExists(dir);
        }
        return dir;
    }

    /***
     * Creates a JSON file at the desired destination with the new name.
     * @param path The location where the file will be created. This will fail if <code>dirPath.isFile() == true</code>
     * @param name The name of the file.
     * @return The new Folder for easy access.
     * @throws IOException If the path is invalid.
     */
    public File createJSONDataFile(File path, String name) throws IOException {
        File file = new File(path, name + ".json");
        if (!file.exists()) {
            if (!file.createNewFile()) {
                if (fileListener != null)
                    fileListener.onFileCreated(file);
            } else {
                if (fileListener != null)
                    fileListener.onFileCreateFailed(file);
            }
        } else {
            if(fileListener!=null)
                fileListener.onFileExists(file);
        }
        return file;
    }
}
