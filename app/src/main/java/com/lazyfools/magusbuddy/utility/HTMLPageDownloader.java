package com.lazyfools.magusbuddy.utility;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HTMLPageDownloader extends AsyncTask<Void, Void, String> {
    public static interface HTMLPageDownloaderListener {
        public abstract void completionCallBack(String html);
    }
    public HTMLPageDownloaderListener listener;
    public String link;
    public HTMLPageDownloader (String aLink, HTMLPageDownloaderListener aListener) {
        listener = aListener;
        link = aLink;
    }

    @Override
    protected String doInBackground(Void... params) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        if (!isCancelled()) {
            listener.completionCallBack(result);
        }
    }
}