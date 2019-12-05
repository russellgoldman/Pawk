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
import android.widget.Toast;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.sample.CourseQuery;
import com.sample.ExploreCoursesQuery;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Course Page</h1>
 * This page displays the specific information about a Laurier Course
 * <h2>Use Case Description</h2>
 * user accesses this course activity through the ExploreCases
 * said user can see all releveant information about a specific course and can see courses related to said course
 *
 */
public class CoursePageActivity extends AppCompatActivity {
    final String ACTIVITY_NAME = "CoursePageActivity";
    public String code = "CP212";
    public String description;
    public int rating = 5;
    public static ApolloClient client;
    Course course;

    public class Course {
        protected String code;
        protected String name;
        protected Double credits;
        protected Double lectureHours;
        protected Double labHours;
        protected String description;
        protected String prerequisiteDescription;
        protected List<String> prerequisiteCourses;
        protected String corequisiteDescription;
        protected List<String> corequisiteCourses;
        protected String exclusionsDescription;
        protected List<String> exclusionsCourses;

        public Course(
            String code,
            String name,
            Double credits,
            Double lectureHours,
            Double labHours,
            String description,
            String prerequisiteDescription,
            List<String> prerequisiteCourses,
            String corequisiteDescription,
            List<String> corequisiteCourses,
            String exclusionsDescription,
            List<String> exclusionsCourses
        ) {
            this.code = code;
            this.name = name;
            this.credits = credits;
            this.lectureHours = lectureHours;
            this.labHours = labHours;
            this.description = description;
            this.prerequisiteDescription = prerequisiteDescription;
            this.prerequisiteCourses = prerequisiteCourses;
            this.corequisiteDescription = corequisiteDescription;
            this.corequisiteCourses = corequisiteCourses;
            this.exclusionsDescription = exclusionsDescription;
            this.exclusionsCourses = exclusionsCourses;
        }
    }

    public class Requisite implements Parcelable {
        public String code;
        public int rating;

        public Requisite(String code, int rating) {
            this.code = code;
            this.rating = rating;
        }

        public Requisite(Parcel source) {
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

        public final Creator<Requisite> CREATOR = new Creator<Requisite>() {
            @Override
            public Requisite[] newArray(int size) {
                return new Requisite[size];
            }

            @Override
            public Requisite createFromParcel(Parcel source) {
                return new Requisite(source);
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);

        Bundle extras = getIntent().getExtras();
        code = extras.getString("code");
        description = extras.getString("description");

        if (code != null) {
            TextView codeView = findViewById(R.id.title_code);
            codeView.setText(code);
        }
        if (description != null) {
            TextView descriptionView = findViewById(R.id.course_description);
            descriptionView.setText(description);
        }

        client = new GraphQLClient().getClient();
        CourseQuery courseQuery = CourseQuery.builder().code(code).build();

        client.query(courseQuery).enqueue(new ApolloCall.Callback<CourseQuery.Data>() {
            @Override public void onResponse(@NotNull Response<CourseQuery.Data> dataResponse) {
                Log.i(ACTIVITY_NAME, dataResponse.data().toString());

                CourseQuery.CourseByCode data = dataResponse.data().courseByCode();

                // account for null courses (webscraper errors)
                if (data == null) {
                    Log.i(ACTIVITY_NAME, "Data is null");
                    course = null;
                } else {
                    course = new CoursePageActivity.Course(
                        data.code(),
                        data.name(),
                        data.credits(),
                        data.lectureHours(),
                        data.labHours(),
                        data.description(),
                        data.prerequisiteDescription(),
                        data.prerequisiteCourses(),
                        data.corequisiteDescription(),
                        data.corequisiteCourses(),
                        data.exclusionsDescription(),
                        data.exclusionsCourses()
                    );
                }

                CoursePageActivity.this.runOnUiThread(new Runnable() {
                    @Override public void run() {
                        if (course == null) {
                            invalidCourse();
                        } else {
                            Log.i(ACTIVITY_NAME, "GraphQL fetch complete");

                            TextView descriptionView = findViewById(R.id.course_description);
                            descriptionView.setText(course.description);

                            // prerequisites
                            ArrayList<Requisite> coursePrerequisites = new ArrayList<>();
                            if (course.prerequisiteCourses != null) {
                                for (String requisite : course.prerequisiteCourses) {
                                    coursePrerequisites.add(new Requisite(requisite, 5));
                                }
                            }

                            // corequisites
                            ArrayList<Requisite> courseCorequisites = new ArrayList<>();
                            if (course.corequisiteCourses != null) {
                                for (String requisite : course.corequisiteCourses) {
                                    courseCorequisites.add(new Requisite(requisite, 5));
                                }
                            }

                            // exclusions
                            ArrayList<Requisite> courseExclusions = new ArrayList<>();
                            if (course.exclusionsCourses != null) {
                                for (String requisite : course.exclusionsCourses) {
                                    courseExclusions.add(new Requisite(requisite, 5));
                                }
                            }

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
                                Fragment corequisites = new CourseRequisitesFragment();
                                data = new Bundle();

                                data.putString("id", "corequisites");
                                data.putString("title", "Corequisites");
                                data.putParcelableArrayList("corequisites", courseCorequisites);
                                corequisites.setArguments(data);

                                transaction.replace(R.id.course_corequisites, corequisites);
                            }
                            if (courseExclusions.size() > 0) {
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
                });

            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.e(ACTIVITY_NAME, e.getMessage(), e);
            }
        });

        ImageButton backButton = findViewById(R.id.course_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void invalidCourse() {
        finish();
        Toast.makeText(getApplicationContext(), "Invalid course, cannot display", Toast.LENGTH_LONG).show();
    }
}
