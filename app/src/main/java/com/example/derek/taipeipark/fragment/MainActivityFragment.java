package com.example.derek.taipeipark.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.derek.taipeipark.R;
import com.example.derek.taipeipark.adapter.ParkRecyclerViewAdapter;
import com.example.derek.taipeipark.config.SourceUrl;
import com.example.derek.taipeipark.connection.DownloadAsyncTask;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    public static final String TAG = MainActivityFragment.class.getSimpleName();
    private ProgressDialog mProgressDialog;
    private View mMainLayout;
    private RecyclerView mParkRecyclerView;
    private ParkRecyclerViewAdapter mParkAdapter;

    public MainActivityFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setCancelable(false);
        showProgress("Loading Data...");
        new DownloadAsyncTask(SourceUrl.TAIPEI_PARK_URL).execute();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dismissProgress();
    }

    public void showProgress(String message) {
        mProgressDialog.setMessage(message);
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }

    public void dismissProgress() {
        mProgressDialog.dismiss();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMainLayout = inflater.inflate(R.layout.fragment_main, container, false);
        mParkRecyclerView = mMainLayout.findViewById(R.id.park_recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mParkRecyclerView.setLayoutManager(linearLayoutManager);

        mParkAdapter = new ParkRecyclerViewAdapter(getActivity());
        mParkRecyclerView.setAdapter(mParkAdapter);
        return mMainLayout;
    }

    private void initViews(){

    }

}
