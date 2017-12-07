package com.example.derek.taipeipark.connection;

import android.os.AsyncTask;
import android.util.Log;

import com.example.derek.taipeipark.data.ParkResult;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by derek_chang on 2017/12/7.
 */

public class DownloadAsyncTask extends AsyncTask<String, String, String> {
    public static final String TAG = DownloadAsyncTask.class.getSimpleName();
    private String mUrl;

    public DownloadAsyncTask(String url) {
        mUrl = url;
    }

    @Override
    protected String doInBackground(String... strings) {
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

            String result = stringBuffer.toString();

            ParkResult parkResult = new Gson().fromJson(result, ParkResult.class);

            return stringBuffer.toString();
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
        return "";
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d(TAG, s);
        super.onPostExecute(s);
    }
}
