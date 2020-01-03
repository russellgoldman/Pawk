package com.example.cp470_group_project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * <h1>Acquiring Courses Rating Fragment</h1>
 * This is the fragment class to insert ratings for specific courses
 * This fragment is loaded for every course and displays that course's rating
 *
 */
public class CourseAcquiredRatingFragment extends Fragment {
    private int rating = 0;
    private final int maxRating = 5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // get rating from bundle
        rating = getArguments().getInt("rating");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_acquired_rating, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final int[] starViewGroups = {R.id.star1, R.id.star2, R.id.star3, R.id.star4, R.id.star5};

        for (int i = 0; i < maxRating; i++) {
            int star = (i < rating) ? R.drawable.course_rating_acquired : R.drawable.course_rating_unacquired;

            ImageView starViewGroup = getView().findViewById(starViewGroups[i]);
            starViewGroup.setImageResource(star);
        }
    }
}
