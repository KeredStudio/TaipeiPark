package com.example.derek.taipeipark.connection;

import android.os.AsyncTask;
import android.util.Log;

import com.example.derek.taipeipark.callback.DownloadCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by derek_chang on 2017/12/7.
 *
 * Download url file
 */

public class DownloadAsyncTask extends AsyncTask<String, String, String> {
    public static final String TAG = DownloadAsyncTask.class.getSimpleName();
    private String mUrl;
    private DownloadCallback mDownloadCallback;
    public DownloadAsyncTask(String url, DownloadCallback callback) {
        mUrl = url;
        mDownloadCallback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mDownloadCallback.showProgressing();
    }

    @Override
    protected String doInBackground(String... strings) {
        String result = "";
        URLConnection urlConnection;
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(mUrl);
            urlConnection = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            result = stringBuffer.toString();
            return result;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mDownloadCallback.onResult(s);
        mDownloadCallback.hideProgressing();
    }


}
