package com.badas.datamanager.File;

import com.badas.datamanager.Manager.ManagerBase;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Project: StorageManager
 * By: Seanf
 * Created: 14,August,2020
 */
public class ReaderWriter extends Reader implements ManagerBase<File> {

//    @Override
//    protected String[] getPermission() {
//        return new String[]{Manifest.permission_group.STORAGE};
//    }

    @Override
    public File getMain() {
        return main;
    }

    @Override
    public void setMain(File main) {
        this.main = main;
        if (current == null)
            setCurrent(main);
    }

    private void setCurrent(File file){
        if (!file.equals(main.getParentFile())){
            if (fileListener!=null)
                fileListener.onCurrentDirectoryChanged(current, file);
            current = file;
        }
    }

    @Override
    public File getCurrent() {
        return current;
    }

    /***
     * Deprecated - Was replaced by navigate
     */
    @Deprecated
    public File navigateIn(File file) {
        if (fileListener != null)
            fileListener.onFileNavigate(current, file);
        setCurrent(file);
        return current;
    }

    /***
     * Will navigate from the current file to the specified file.
     * @param file The file that is being navigated to.
     * @return The current set file for easy access.
     */
    public File navigate(File file) {
        if (fileListener != null)
            fileListener.onFileNavigate(current, file);
        setCurrent(file);
        return current;
    }

    /***
     * Will navigate to the current files parent.
     * If the current is equal to main it will not navigate.
     * @return The current set file for easy access.
     */
    public File navigateOut() {
        if (fileListener != null)
            fileListener.onFileNavigate(current, current.getParentFile());
        setCurrent(Objects.requireNonNull(current.getParentFile()));
        return current;
    }

    /***
     * Retrieves all of the child files for the current.
     * @return The files that where retrieved.
     */
    public ArrayList<File> retrieveCurrentFile() {
        return retrieveFiles(current);
    }

    /***
     * A simple compare method
     * @param file1 First file to compare
     * @param file2 Second file to compare
     * @return Will return <code>true</code> if they match else <code>false</code>
     */
    public boolean compare(File file1, File file2) {
        return file1.equals(file2);
    }

    /***
     * Deletes the current file
     * @param file the file to delete
     */
    public File delete(File file) {
        File temp = file.getParentFile();
        recursiveDelete(file);
        return temp;
    }

    private void recursiveDelete(File file) {
        if (file.isDirectory())
            for (File child : Objects.requireNonNull(file.listFiles()))
                recursiveDelete(child);

        //noinspection UnnecessaryLocalVariable
        File temp = file;
        if (file.delete()) {
            if (fileListener != null)
                if (temp.isFile())
                    fileListener.onFileDeleted(temp);
                else
                    fileListener.onDirectoryDeleted(temp);
        }
    }
}