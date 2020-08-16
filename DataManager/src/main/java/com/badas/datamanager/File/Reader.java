package com.badas.datamanager.File;

import android.Manifest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
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

    /***
     * Reads the file.
     * @param file The file to be read.
     * @return The data retrieved from the file.
     * @throws IOException If there is an error with the file.
     */
    public String read(File file) throws IOException {
        if (!file.isFile())
            throw new InvalidObjectException(file.getName() + " is not a readable file.");
        long length = file.length();
        int totalBytes;
        byte[] bytes = new byte[(int) length];

        try (FileInputStream stream = new FileInputStream(file)) {
            totalBytes = stream.read(bytes);
        }

        String contents = new String(bytes);
        fileListener.onFileRead(file, contents, totalBytes);
        return contents;
    }

    /***
     * Reads the file.
     * @param file The file to be read.
     * @return The data retrieved from the file as a JSONObject.
     * @throws IOException If there is an error with the file.
     * @throws JSONException If there is an error with retrieving the JSON.
     */
    public JSONObject readAsJson(File file) throws IOException, JSONException {
        if (!file.isFile())
            throw new InvalidObjectException(file.getName() + " is not a readable file.");
        long length = file.length();
        int totalBytes;
        byte[] bytes = new byte[(int) length];

        try (FileInputStream stream = new FileInputStream(file)) {
            totalBytes = stream.read(bytes);
        }

        JSONObject contents = new JSONObject(new String(bytes));
        fileListener.onFileRead(file, contents.toString(), totalBytes);
        return contents;
    }
}
