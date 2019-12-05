package com.example.cp470_group_project;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

//public class programData implements Parcelable
public class programData {
    private String programName;
    private String programBlurb;
//    private String[] programHighlights;
//    private String[] programRequirements;
//    private String programHighlights;
//    private String programRequirements;

    private int coopTerms;
    private int duration;
    private List<String> sampleCourses;
   // private List<String> sampleCourses;

    // State of Item
    private boolean expanded;

//    public programData(String programName, String programBlurb, String[] programHighlights, String[] programRequirements, ArrayList<Course> sampleCourses){
////        this.programName = programName;
////        this.programBlurb = programBlurb;
////        this.programHighlights = programHighlights;
////        this.programRequirements = programRequirements;
////        this.sampleCourses = sampleCourses;
////    }

    public programData(String programName, String programBlurb, int coopTerms, int duration, List<String> sampleCourses){
        this.programName = programName;
        this.programBlurb = programBlurb;
//        this.programHighlights = programHighlights;
//        this.programRequirements = programRequirements;
        this.duration = duration;
        this.coopTerms = coopTerms;
        this.sampleCourses = sampleCourses;
    }

//    public String[] getProgramHighlights(){
//        return programHighlights;
//    }
//
//    public String[] getProgramRequirements(){
//        return programRequirements;
//    }

//    public String getProgramHighlights(){
//        return programHighlights;
//    }
//
//
//
//    public String getProgramRequirements(){
//        return programRequirements;
//    }

//    public ArrayList<Course> getSampleCourses(){
//        return sampleCourses;
//    }

    public List<String> getSampleCourses(){
        return sampleCourses;
    }

    public String getProgramBlurb() {
        return programBlurb;
    }

    public String getProgramName(){
        return programName;
    }

    public void setCoopTerms(int coopTerms){
        this.coopTerms = coopTerms;
    }

    public void setDuration(int duration){
        this.duration = duration;
    }

    public int getCoopTerms(){
        return coopTerms;
    }

    public int getDuration(){
        return duration;
    }

//    public void setProgramName(String programName){
//        this.programName = programName;
//    }
//
//    public void setProgramBlurb(String programBlurb){
//        this.programBlurb = programBlurb;
//    }

    public boolean isExpanded(){
        return expanded;
    }

    public void setExpanded(boolean expanded){
        this.expanded = expanded;
    }

}
