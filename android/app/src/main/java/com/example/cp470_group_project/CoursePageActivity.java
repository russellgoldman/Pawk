package com.example.cp470_group_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

public class CoursePageActivity extends AppCompatActivity {
    final String ACTIVITY_NAME = "CoursePageActivity";

    final boolean prerequisites = true;
    final boolean corequisites = false;
    final boolean exclusions = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);

        // add CourseAcquiredRatingFragment
        Fragment courseRatings = new CourseAcquiredRatingFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.course_acquired_rating_fragment, courseRatings);
        transaction.commit();

        // add CourseRequisitesFragment
        transaction = getSupportFragmentManager().beginTransaction();

        if (prerequisites) {
            Fragment prerequisites = new CourseRequisitesFragment();
            Bundle data = new Bundle();

            data.putString("id", "prerequisites");
            data.putString("title", "Prerequisites");
            prerequisites.setArguments(data);

            transaction.replace(R.id.course_prerequisites, prerequisites);
        }
        if (corequisites) {
            Log.i(ACTIVITY_NAME, "coreqs");

            Fragment corequisites = new CourseRequisitesFragment();
            Bundle data = new Bundle();

            data.putString("id", "corequisites");
            data.putString("title", "Corequisites");
            corequisites.setArguments(data);

            transaction.replace(R.id.course_corequisites, corequisites);
        }
        if (exclusions) {
            Log.i(ACTIVITY_NAME, "exclusions");

            Fragment exclusions = new CourseRequisitesFragment();
            Bundle data = new Bundle();

            data.putString("id", "exclusions");
            data.putString("title", "Exclusions");
            exclusions.setArguments(data);

            transaction.replace(R.id.course_exclusions, exclusions);
        }

        transaction.commit();
    }
}
