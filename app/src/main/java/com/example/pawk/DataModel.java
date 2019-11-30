package com.example.pawk;

public class DataModel {

    public String text;
    public int drawable;
    boolean selected;

    public DataModel(String title, int drawable, Boolean selected)
    {
        text=title;
        drawable=drawable;
        selected=selected;
    }
}
