package com.example.cp470_group_project;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.util.Log;


public class programPage extends AppCompatActivity {

    private String ACTIVITY_NAME = "programPage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_page);

        Long savedExtra = getIntent().getLongExtra("position",0);
        TextView myText = (TextView) findViewById(R.id.programNameTextView);
        Log.i(ACTIVITY_NAME, "Pos: " + savedExtra);
        //myText.setText(savedExtra);
    }
}
