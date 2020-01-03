package com.example.cp470_group_project;
import java.util.List;


/**
 *
 * <h1>programData</h1>
 * This is an object that stores all the program data on explore programs page.
 *
 */

public class programData {
    private String programName;
    private String programBlurb;

    private int coopTerms;
    private int duration;
    private List<String> sampleCourses;

    // State of Item
    private boolean expanded;

    public programData(String programName, String programBlurb, int coopTerms, int duration, List<String> sampleCourses){
        this.programName = programName;
        this.programBlurb = programBlurb;
        this.duration = duration;
        this.coopTerms = coopTerms;
        this.sampleCourses = sampleCourses;
    }

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

    public boolean isExpanded(){
        return expanded;
    }

    public void setExpanded(boolean expanded){
        this.expanded = expanded;
    }

}
