package com.example.cp470_group_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 <h1>Dashboard</h1>
  This is the Dashboard for the PAWK App
  From this you can navigate to the pages that contain
  Explore Courses, Programs, Help, Settings, Favourites, and Ratings
 <h2>USE CASE DESCRIPTION</h2>
 This Activity is used as the Hub for the app
 It is used to access all the other features of the app and is considered the Home Screen of the app once
 you have logged in. This is used for users to navigate through the course and to access essential features.
 */

public class Dashboard extends AppCompatActivity {
    BottomNavigationView bottomNav;
    ImageButton courseRegi;
    ImageButton viewFavs;
    ImageButton viewRatings;
    Intent intent;

    /**
     * This method creates the view and functions and sets the intents
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intent = null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        String date_n = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
        TextView date  = findViewById(R.id.todays_date);
        date.setText(date_n);
        courseRegi = findViewById(R.id.viewCourseRegi);
        courseRegi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Dashboard.this, CourseReg.class);
                startActivity(intent);
            }
        });
        viewFavs = findViewById(R.id.viewFavs);
        viewFavs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
 //               intent = new Intent(Dashboard.this, CourseReg.class);
   //             startActivity(intent);
            }
        });
        viewRatings = findViewById(R.id.viewYourRatings);
        viewRatings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
 //               intent = new Intent(Dashboard.this, CourseReg.class);
  //              startActivity(intent);
            }
        });
        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_dashboard:
                        // since already on programs page, not need to implement intent
                        Toast.makeText(getApplicationContext(),"Already on Dashboard page", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.navigation_courses:
                        intent = new Intent(Dashboard.this, ExploreCoursesActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_programs:
                        intent = new Intent(Dashboard.this, exploreProgram2.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_settings:
                        intent = new Intent(Dashboard.this, CourseReg.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });



    }
}
