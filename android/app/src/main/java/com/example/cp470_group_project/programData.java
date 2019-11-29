package com.example.cp470_group_project;

import android.os.Parcel;
import android.os.Parcelable;

public class programData implements Parcelable {
    private String programName;
    private String programBlurb;

    // State of Item
    private boolean expanded;

    public programData(String programName, String programBlurb){
        this.programName = programName;
        this.programBlurb = programBlurb;
    }


    public String getProgramBlurb() {
        return programBlurb;
    }

    public String getProgramName(){
        return programName;
    }

    public void setProgramName(String programName){
        this.programName = programName;
    }

    public void setProgramBlurb(String programBlurb){
        this.programBlurb = programBlurb;
    }

    public boolean isExpanded(){
        return expanded;
    }

    public void setExpanded(boolean expanded){
        this.expanded = expanded;
    }

    // adds all class properties to parcel in preparation for transfer
    public void writeToParcel(Parcel dest, int flags){
        // write all properties to the parcel
        dest.writeString(programName);
        dest.writeString(programBlurb);
    }


    public programData(Parcel parcel){
        // read and set saved values from parcel
        programName = parcel.readString();
        programBlurb = parcel.readString();
    }

    // creator - used when unparcling our parcel (creating obkects)
    public static final Parcelable.Creator<programData> CREATOR = new Parcelable.Creator<programData>(){
        @Override
        public programData createFromParcel(Parcel parcel){
            return new programData(parcel);
        }

        @Override
        public programData[] newArray(int size){
            return new programData[0];
        }
    };

    // return hashcode of object
    public int describeContents(){
        return hashCode();
    }
}
