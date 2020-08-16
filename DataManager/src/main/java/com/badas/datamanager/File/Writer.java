package com.badas.datamanager.File;

import android.Manifest;

import com.badas.datamanager.Manager.Base;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;

/**
 * Project: StorageManager
 * By: Seanf
 * Created: 14,August,2020
 */
class Writer extends Base<File> {
    protected FileListener fileListener;

    @Override
    protected String[] getPermission() {
        return new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
    }

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
            if (fileListener != null)
                fileListener.onFileExists(file);
        }
        return file;
    }

    /***
     * Creates a file at the desired destination with the new name.
     * @param path The location where the file will be created. This will fail if <code>dirPath.isFile() == true</code>
     * @param name The name of the file.
     * @param extension The file's extension.
     * @return The new Folder for easy access.
     * @throws IOException If the path is invalid.
     */
    public File createDataFile(File path, String name, String extension) throws IOException {
        if (!extension.startsWith("."))
            extension = "." + extension;
        File file = new File(path, name + extension);
        if (!file.exists()) {
            if (!file.createNewFile()) {
                if (fileListener != null)
                    fileListener.onFileCreated(file);
            } else {
                if (fileListener != null)
                    fileListener.onFileCreateFailed(file);
            }
        } else {
            if (fileListener != null)
                fileListener.onFileExists(file);
        }
        return file;
    }

    /***
     * Will write data to the specified file
     * @param file The file to be written to.
     * @param data The data that will be written.
     * @throws IOException If there is an error with the file.
     */
    public void write(File file, String data) throws IOException {
        if (!file.isFile())
            throw new InvalidObjectException(file.getName() + " is not a writable file.");
        try (FileOutputStream stream = new FileOutputStream(file)) {
            stream.write(data.getBytes());
            if (fileListener != null)
                fileListener.onFileWrite(file, data);
        }
    }

    /***
     * Will write data to the specified file
     * @param file The file to be written to.
     * @param data The data that will be written.
     * @throws IOException If there is an error with the file.
     */
    public void write(File file, JSONObject data) throws IOException {
        if (!file.isFile())
            throw new InvalidObjectException(file.getName() + " is not a writable file.");
        try (FileOutputStream stream = new FileOutputStream(file)) {
            stream.write(data.toString().getBytes());
            if (fileListener != null)
                fileListener.onFileWrite(file, data.toString());
        }
    }

    /***
     * Will write data to the specified file
     * @param file The file to be written to.
     * @param data The data that will be written.
     * @param indentSpace the number of spaces to indent for each level of nesting.
     * @throws IOException If there is an error with the file.
     * @throws JSONException If there is an error with the data.
     */
    public void write(File file, JSONObject data, int indentSpace) throws IOException, JSONException {
        if (!file.isFile())
            throw new InvalidObjectException(file.getName() + " is not a writable file.");
        try (FileOutputStream stream = new FileOutputStream(file)) {
            stream.write(data.toString(indentSpace).getBytes());
            if (fileListener != null)
                fileListener.onFileWrite(file, data.toString(indentSpace));
        }
    }
}
