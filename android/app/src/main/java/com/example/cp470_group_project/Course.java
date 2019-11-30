package com.example.cp470_group_project;

import android.os.Parcel;
import android.os.Parcelable;

public class Course implements Parcelable {
    public String code;
    public int rating;

    public Course(String code, int rating) {
        this.code = code;
        this.rating = rating;
    }

    public Course(Parcel source) {
        code = source.readString();
        rating = source.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeInt(rating);
    }

    public final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }

        @Override
        public Course createFromParcel(Parcel source) {
            return new Course(source);
        }
    };
}