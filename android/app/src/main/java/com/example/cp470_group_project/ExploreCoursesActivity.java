package com.example.cp470_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Looper;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.fetcher.ApolloResponseFetchers;
import com.sample.ExploreCoursesQuery;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExploreCoursesActivity extends AppCompatActivity {
    final String ACTIVITY_NAME = "ExploreCoursesActivity";
    public static ApolloClient client;

    public class Course {
        protected String code;
        protected String description;

        public Course(
                String code,
                String description
        ) {
                this.code = code;
                this.description = description;
        }
    }

    protected ArrayList<Course> courses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_courses);

        client = new GraphQLClient().getClient();
        ExploreCoursesQuery exploreCoursesQuery = ExploreCoursesQuery.builder().build();

        client.query(exploreCoursesQuery).enqueue(new ApolloCall.Callback<ExploreCoursesQuery.Data>() {
            @Override public void onResponse(@NotNull Response<ExploreCoursesQuery.Data> dataResponse) {
                Log.i(ACTIVITY_NAME, dataResponse.data().toString());

                for (ExploreCoursesQuery.Node node: dataResponse.data().allCourses().nodes()) {
                    Course course = new Course(
                            node.code(),
                            node.description()
                    );
                    courses.add(course);
                }

                ExploreCoursesActivity.this.runOnUiThread(new Runnable() {
                    @Override public void run() {
                        Log.i(ACTIVITY_NAME, "GraphQL fetch complete");
//                        TextView txtResponse = (TextView) findViewById(R.id.txtResponse);
//                        txtResponse.setText(buffer.toString());
                    }
                });

            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.e(ACTIVITY_NAME, e.getMessage(), e);
            }
        });

    }
}