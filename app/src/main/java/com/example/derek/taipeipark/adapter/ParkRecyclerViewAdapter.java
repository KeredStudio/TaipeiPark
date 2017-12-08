package com.example.derek.taipeipark.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.derek.taipeipark.R;
import com.example.derek.taipeipark.data.ParkInfo;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by derek_chang on 2017/12/7.
 */

public class ParkRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private FragmentActivity mActivity;
    private List<ParkInfo> mParkInfos;

    public ParkRecyclerViewAdapter(FragmentActivity activity) {
        mActivity = activity;
    }

    public void setParks(List<ParkInfo> infos) {
        mParkInfos = infos;
    }

    @Override
    public int getItemCount() {
        if (mParkInfos != null) {
            return mParkInfos.size();
        } else {
            return 0;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.park_item_view, parent, false);
        return new ParkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof ParkViewHolder){
            ParkInfo info = mParkInfos.get(position);
            ParkViewHolder holder = (ParkViewHolder)viewHolder;

            holder.simpleDraweeView.setImageURI(info.getImage());
            holder.parkName.setText(info.getParkName());
            holder.name.setText(info.getName());
        }
    }

    class ParkViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView;
        TextView parkName;
        TextView name;
        public ParkViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.park_imageView);
            parkName = itemView.findViewById(R.id.parkName_textView);
            name = itemView.findViewById(R.id.name_textView);

        }
    }
}
