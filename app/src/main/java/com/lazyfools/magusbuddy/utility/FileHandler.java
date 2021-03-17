package com.lazyfools.magusbuddy.utility;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public final class FileHandler {
    private static final FileHandler ourInstance = new FileHandler();
    private Context _appContext;
    public void setAppContext(Context appContext) {
        _appContext = appContext;
    }

    public static FileHandler getInstance() {
        return ourInstance;
    }

    private FileHandler() {
    }

    public static void writeToFile(String fileName, String data) throws NullPointerException {
        if (ourInstance._appContext == null){
            throw new NullPointerException("Context is not set");
        }
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(ourInstance._appContext.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static String readFromFile(String fileName) throws NullPointerException  {
        if (ourInstance._appContext == null){
            throw new NullPointerException("Context is not set");
        }
        String ret = "";

        try {
            InputStream inputStream = ourInstance._appContext.openFileInput(fileName);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
