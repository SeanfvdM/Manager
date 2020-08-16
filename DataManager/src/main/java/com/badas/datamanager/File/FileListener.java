package com.badas.datamanager.File;

import java.io.File;

/**
 * Project: StorageManager
 * By: Seanf
 * Created: 14,August,2020
 */
public interface FileListener {
    void onFileCreated(File file);
    void onDirectoryCreated(File directory);
    void onFileCreateFailed(File file);
    void onDirectoryCreateFailed(File directory);
    void onFileExists(File file);
    void onDirectoryExists(File directory);
    void onFileDeleted(File deletedFile);
    void onDirectoryDeleted(File deletedDirectory);
    void onFileRead(File file, String data);

    void onFileWrite(File file, String data);

    void onCurrentDirectoryChanged(File newDir, File oldDir);
    void onFileNavigate(File from, File To);
}
