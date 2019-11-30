package com.example.cp470_group_project;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button;
import android.util.Log;


public class programPage extends AppCompatActivity {

    private String ACTIVITY_NAME = "programPage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_page);

        Log.i(ACTIVITY_NAME,"in onCreate()");

        Bundle data = this.getIntent().getExtras();
        if (data != null) {
            String programName = data.getString("programName");
            String programDesc = data.getString("programDesc");

            Log.i(ACTIVITY_NAME, "Bundle: " + data.isEmpty() + "");
            Log.i(ACTIVITY_NAME, "Program Name: " + data.getString("programName"));
            Log.i(ACTIVITY_NAME, "Program Desc: " + data.getString("programDesc"));
        } else {
            Log.i(ACTIVITY_NAME, "Bundle is null");
        }

        ImageButton backButton = findViewById(R.id.course_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView programName = findViewById(R.id.programNameTextView);
        programName.setText(data.getString("programName"));

        TextView programDesc = findViewById(R.id.programBlurbTextView);
        programDesc.setText(data.getString("programDesc"));

        programFragment newFragment = new programFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.sampleCourseFrame, newFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
