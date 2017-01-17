package com.example.android.effectivenavigation;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lincoln on 18/05/16.
 */
public class Album implements Parcelable {
    private String name;
    private String imageFile;
    private String videoPath;



    public Album() {
    }

    public Album(String name, String imageFile, String videoPath) {
        this.name = name;
        this.imageFile=imageFile;
        this.videoPath=videoPath;
    }
    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }
    public String getName() {
        return name;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Album(Parcel parcel){

        name=parcel.readString();
        imageFile=parcel.readString();
        videoPath=parcel.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(name);
        parcel.writeString(imageFile);
        parcel.writeString(videoPath);


    }

    public static final Parcelable.Creator<Album> CREATOR=new Parcelable.Creator<Album>(){

        @Override
        public Album createFromParcel(Parcel parcel) {
            return new Album(parcel);
        }

        @Override
        public Album[] newArray(int i) {
            return new Album[i];
        }
    };
}
