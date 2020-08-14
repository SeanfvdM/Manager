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

    @Override
    public File getMain() {
        return main;
    }

    @Override
    public void setMain(File main) {
        this.main = main;
        if(current == null)
            setCurrent(main);
    }

    private void setCurrent(File file){
        if (!file.equals(main.getParentFile())){
            if (fileListener!=null)
                fileListener.onCurrentDirectoryChanged(current,file);
            current = file;
        }
    }

    @Override
    public File getCurrent() {
        return current;
    }

    public File navigateIn(File file) {
        if (fileListener!=null)
            fileListener.onFileNavigate(current,file);
        setCurrent(file);
        return current;
    }

    public File navigateOut() {
        if (fileListener!=null)
            fileListener.onFileNavigate(current,current.getParentFile());
        setCurrent(Objects.requireNonNull(current.getParentFile()));
        return current;
    }

    public ArrayList<File> retrieveCurrentFile(){
        return retrieveFiles(current);
    }

    public boolean compare(File file1, File file2){
        return file1.equals(file2);
    }
}