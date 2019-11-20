package com.example.cp470_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.content.Intent;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;

public class exploreProgram extends AppCompatActivity {

    String[] programArray = {"Computer Science","Math","English","Sociology"};
    String[] infoArray={"this is computer scie","tis is math","this is eng", "this is soc"};
    ListView listView;

    public boolean isButtonVisible = true;
    public boolean isTextViewVisible= false;
    private TextView programDescription;

    private String ACTIVITY_NAME = "exploreProgram";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_program);

        // create instance of ProgramListAdapter class
        ProgramListAdapter programAdapter = new ProgramListAdapter(this, programArray, infoArray);

        // Tells listview xml to use our adaptor
        listView = (ListView) findViewById(R.id.listviewID);
        listView.setAdapter(programAdapter);

        Button learnMore = findViewById(R.id.learnMoreButton);
//        learnMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(exploreProgram.this,programPage.class);
//                String message = programArray[position];
//                intent.putExtra("programName","program");
//                startActivity(intent);
//            }
//        });
//        programDescription = findViewById(R.id.programDescTextView);
//
//        listView.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position) {
//                Intent intent = new Intent(exploreProgram.this,programPage.class);
//                String message = programArray[position];
//                intent.putExtra("programName","program");
//                startActivity(intent);
//            }
//        });

    }
}
