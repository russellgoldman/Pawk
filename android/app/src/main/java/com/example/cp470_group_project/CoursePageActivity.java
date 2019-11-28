package com.example.cp470_group_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class CoursePageActivity extends AppCompatActivity {
    final String ACTIVITY_NAME = "CoursePageActivity";
    public String code = "CP212";
    public int rating = 4;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            code = extras.getString("code");
            rating = extras.getInt("rating");
            TextView codeView = findViewById(R.id.title_code);
            codeView.setText(code);
        }

        ImageButton backButton = findViewById(R.id.course_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /* TODO - replace with GraphQL requisite data based on course code */
        // prerequisites
        ArrayList<Course> coursePrerequisites = new ArrayList<Course>();
        coursePrerequisites.add(new Course("CP102", 4));
        coursePrerequisites.add(new Course("CP104", 5));
        coursePrerequisites.add(new Course("MA103", 3));

        // corequisites
        ArrayList<Course> courseCorequisites = new ArrayList<Course>();
        courseCorequisites.add(new Course("CP101", 2));
        courseCorequisites.add(new Course("CP102", 4));

        // exclusions
        ArrayList<Course> courseExclusions = new ArrayList<Course>();
        courseExclusions.add(new Course("CP202", 3));

        // add CourseAcquiredRatingFragment
        Fragment courseRatings = new CourseAcquiredRatingFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        Bundle data = new Bundle();
        data.putInt("rating", rating);
        courseRatings.setArguments(data);

        transaction.replace(R.id.course_acquired_rating_fragment, courseRatings);
        transaction.commit();

        // add CourseRequisitesFragment
        transaction = getSupportFragmentManager().beginTransaction();

        if (coursePrerequisites.size() > 0) {
            Fragment prerequisites = new CourseRequisitesFragment();
            data = new Bundle();

            data.putString("id", "prerequisites");
            data.putString("title", "Prerequisites");
            data.putParcelableArrayList("prerequisites", coursePrerequisites);
            prerequisites.setArguments(data);

            transaction.replace(R.id.course_prerequisites, prerequisites);
        }
        if (courseCorequisites.size() > 0) {
            Log.i(ACTIVITY_NAME, "coreqs");

            Fragment corequisites = new CourseRequisitesFragment();
            data = new Bundle();

            data.putString("id", "corequisites");
            data.putString("title", "Corequisites");
            data.putParcelableArrayList("corequisites", courseCorequisites);
            corequisites.setArguments(data);

            transaction.replace(R.id.course_corequisites, corequisites);
        }
        if (courseExclusions.size() > 0) {
            Log.i(ACTIVITY_NAME, "exclusions");

            Fragment exclusions = new CourseRequisitesFragment();
            data = new Bundle();

            data.putString("id", "exclusions");
            data.putString("title", "Exclusions");
            data.putParcelableArrayList("exclusions", courseExclusions);
            exclusions.setArguments(data);

            transaction.replace(R.id.course_exclusions, exclusions);
        }

        transaction.commit();
    }
}
