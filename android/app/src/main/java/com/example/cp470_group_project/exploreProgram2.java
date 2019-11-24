package com.example.cp470_group_project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import java.util.List;
import java.util.ArrayList;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;



public class exploreProgram2 extends AppCompatActivity {

    String[] programArray = {"Computer Science","Math","English","Sociology"};
    String[] infoArray={"this is computer scie","tis is math","this is eng", "this is soc"};
    ListView listView;

    public boolean isButtonVisible = true;
    public boolean isTextViewVisible= false;
    private TextView programDescription;

    Button learnMore;

    private String ACTIVITY_NAME = "exploreProgram";

    private Toolbar toolbar2;

    programAdapter  adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_program2);

        // create instance of ProgramListAdapter class

        List<programData> programList = new ArrayList<>();

        for (int i = 0; i<programArray.length;i++){
            programList.add(new programData(programArray[i],infoArray[i]));
        }

        adapter = new programAdapter(programList);
        RecyclerView recyclerView = findViewById(R.id.recview);

        //((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        toolbar2 = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar2);
        getSupportActionBar().setTitle("Programs");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_nav, menu);

        Log.i(ACTIVITY_NAME,"I'm here");

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            Toast.makeText(exploreProgram2.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
