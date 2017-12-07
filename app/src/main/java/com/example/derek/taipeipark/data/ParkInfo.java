package com.example.derek.taipeipark.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by derek_chang on 2017/12/7.
 */

public class ParkInfo implements Parcelable {
    String _id;
    String ParkName;
    String Name;
    String YearBuilt;
    String OpenTime;
    String Image;
    String Introduction;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.ParkName);
        dest.writeString(this.Name);
        dest.writeString(this.YearBuilt);
        dest.writeString(this.OpenTime);
        dest.writeString(this.Image);
        dest.writeString(this.Introduction);
    }

    public ParkInfo() {
    }

    protected ParkInfo(Parcel in) {
        this._id = in.readString();
        this.ParkName = in.readString();
        this.Name = in.readString();
        this.YearBuilt = in.readString();
        this.OpenTime = in.readString();
        this.Image = in.readString();
        this.Introduction = in.readString();
    }

    public static final Parcelable.Creator<ParkInfo> CREATOR = new Parcelable.Creator<ParkInfo>() {
        @Override
        public ParkInfo createFromParcel(Parcel source) {
            return new ParkInfo(source);
        }

        @Override
        public ParkInfo[] newArray(int size) {
            return new ParkInfo[size];
        }
    };


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getParkName() {
        return ParkName;
    }

    public void setParkName(String parkName) {
        ParkName = parkName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getYearBuilt() {
        return YearBuilt;
    }

    public void setYearBuilt(String yearBuilt) {
        YearBuilt = yearBuilt;
    }

    public String getOpenTime() {
        return OpenTime;
    }

    public void setOpenTime(String openTime) {
        OpenTime = openTime;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getIntroduction() {
        return Introduction;
    }

    public void setIntroduction(String introduction) {
        Introduction = introduction;
    }
}
