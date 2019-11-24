package com.example.cp470_group_project;

import android.view.View;


/*
    Animations for program Chord
 */
public class programAnimations {
    public static boolean toggleArrow(View view, boolean isExpanded) {

        if (isExpanded) {
            view.animate().setDuration(200).rotation(180);
            return true;
        } else {
            view.animate().setDuration(200).rotation(0);
            return false;
        }
    }
}
