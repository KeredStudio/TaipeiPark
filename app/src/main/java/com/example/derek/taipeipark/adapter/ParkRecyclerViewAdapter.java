package com.example.derek.taipeipark.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.derek.taipeipark.R;

/**
 * Created by derek_chang on 2017/12/7.
 */

public class ParkRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private FragmentActivity mActivity;


    public ParkRecyclerViewAdapter(FragmentActivity activity) {
        mActivity = activity;
    }

    public void setParks(){

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.park_item_view, parent, false);
        return new ParkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    class ParkViewHolder extends RecyclerView.ViewHolder {
        public ParkViewHolder(View itemView) {
            super(itemView);
        }
    }
}
