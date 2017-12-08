package com.example.derek.taipeipark.callback;

/**
 * Created by derek_chang on 2017/12/8.
 */

public interface DownloadCallback {
    void showProgressing();
    void hideProgressing();
    void onResult(String result);
}
