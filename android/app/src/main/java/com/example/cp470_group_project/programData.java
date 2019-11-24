package com.example.cp470_group_project;

public class programData {
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
}
