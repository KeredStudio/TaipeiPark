package com.example.derek.taipeipark.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.derek.taipeipark.R;
import com.example.derek.taipeipark.adapter.ParkRecyclerViewAdapter;
import com.example.derek.taipeipark.callback.DownloadCallback;
import com.example.derek.taipeipark.config.SourceUrl;
import com.example.derek.taipeipark.connection.DownloadAsyncTask;
import com.example.derek.taipeipark.data.ParkInfo;
import com.example.derek.taipeipark.data.ParkResult;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements DownloadCallback {
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

    }

    @Override
    public void onDetach() {
        super.onDetach();
        dismissProgress();
    }

    public void showProgress(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.setMessage(message);
            mProgressDialog.setCancelable(true);
            mProgressDialog.show();
        }
    }

    public void dismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
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


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new DownloadAsyncTask(SourceUrl.TAIPEI_PARK_URL, this).execute();
    }


    @Override
    public void showProgressing() {
        showProgress("Loading Data...");
    }

    @Override
    public void hideProgressing() {
        dismissProgress();
    }

    @Override
    public void onResult(String result) {
        try {
            if (TextUtils.isEmpty(result)) {
                /* server may error 500 */
                result = loadJSONFromAsset();
            }

            /* update data */
            JSONObject jsonObject = new JSONObject(result);
            ParkResult parkResult = new Gson().fromJson(jsonObject.get("result").toString(), ParkResult.class);
            for (ParkInfo info : parkResult.getResults()) {
                Log.d(TAG, info.getParkName());
            }
            if (mParkAdapter != null) {
                mParkAdapter.setParks(parkResult.getResults());
                mParkAdapter.notifyDataSetChanged();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset() {
        String json = "";
        try {
            InputStream is = getActivity().getResources().openRawResource(R.raw.parks);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
