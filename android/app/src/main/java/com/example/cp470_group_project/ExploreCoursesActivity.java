package com.example.cp470_group_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.fetcher.ApolloResponseFetchers;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sample.ExploreCoursesQuery;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExploreCoursesActivity extends AppCompatActivity implements ExploreCoursesRecyclerAdapter.ItemClickListener {

    BottomNavigationView bottomNav;
    Intent intent;
    final String ACTIVITY_NAME = "ExploreCoursesActivity";
    final com.example.cp470_group_project.ExploreCoursesActivity ctx = this;
    ExploreCoursesRecyclerAdapter adapter;
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

        RecyclerView recyclerView = findViewById(R.id.explore_course_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
        adapter = new ExploreCoursesRecyclerAdapter(ctx, courses);
        adapter.setClickListener(ctx);
        recyclerView.setAdapter(adapter);

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
                        adapter.notifyDataSetChanged();
                    }
                });

            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.e(ACTIVITY_NAME, e.getMessage(), e);
            }
        });

        bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_dashboard:
                        intent = new Intent(ExploreCoursesActivity.this, Dashboard.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_courses:
                        // since already on navigation page, not need to implement intent
                        Toast.makeText(getApplicationContext(),"Already on explore course page", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.navigation_programs:
                        intent = new Intent(ExploreCoursesActivity.this, exploreProgram2.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_settings:
//                        intent = new Intent(exploreProgram2.this, HelpActivity.class);
//                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(ExploreCoursesActivity.this, CoursePageActivity.class);
        intent.putExtra("code", adapter.getItem(position).code);
        intent.putExtra("description", adapter.getItem(position).description);
        startActivity(intent);

        Toast.makeText(this, "You clicked " + adapter.getItem(position).code, Toast.LENGTH_SHORT).show();
    }
}