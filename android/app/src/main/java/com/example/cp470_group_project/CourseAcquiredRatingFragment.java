package com.example.cp470_group_project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class CourseAcquiredRatingFragment extends Fragment {
    private final int ratings = 4;
    private final int maxRatings = 5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_acquired_rating, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final int[] starViewGroups = {R.id.star1, R.id.star2, R.id.star3, R.id.star4, R.id.star5};

        for (int i = 0; i < maxRatings; i++) {
            int star = (i < ratings) ? R.drawable.course_rating_acquired : R.drawable.course_rating_unacquired;

            ImageView starViewGroup = getView().findViewById(starViewGroups[i]);
            starViewGroup.setImageResource(star);
        }
    }
}
