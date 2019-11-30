package com.example.cp470_group_project;

import android.app.SearchManager;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import android.view.Menu;
import android.view.MenuInflater;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class exploreProgram2 extends AppCompatActivity{


    public boolean isButtonVisible = true;
    public boolean isTextViewVisible= false;
    private TextView programDescription;

    Button learnMore;

    private String ACTIVITY_NAME = "exploreProgram2";

    private Toolbar toolbar2;

    programAdapter  adapter;

    BottomNavigationView bottomNav;

    MenuItem programNav;

    private RecyclerView recyclerView;

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_program2);

        /* TODO THIS IS THE TESTING BLOCK SO YOU SHOULD REPLACE WITH DATABSE STUFF HERE */

        ArrayList<programData> programList = new ArrayList<>();

        String[] programArray = {"Computer Science","Math","English","Sociology"};
        String[] infoArray={"this is computer scie","tis is math","this is eng", "this is soc"};

        String[] highlightsArray={"You can make experience","you are cool","i am cool"};
        String[] requirementsArray={"highschol","preeschool","meschool"};
        Course requiredCourse = new Course("cp103",5);
        Course requiredCourse1 = new Course("cp104",3);
        Course requiredCourse2 = new Course("cp123",1);
        Course requiredCourse3 = new Course("su103",4);
        Course requiredCourse4 = new Course("cp233",3);

        ArrayList<Course> sampleCourses = new ArrayList<Course>();

        sampleCourses.add(requiredCourse);
        sampleCourses.add(requiredCourse1);
        sampleCourses.add(requiredCourse2);
        sampleCourses.add(requiredCourse3);
        sampleCourses.add(requiredCourse4);


        programData comsci = new programData(programArray[0],infoArray[0],highlightsArray,requirementsArray,sampleCourses);
        programList.add(comsci);

        programData math = new programData(programArray[1],infoArray[1],highlightsArray,requirementsArray,sampleCourses);
        programList.add(math);

        programData eng = new programData(programArray[2],infoArray[2],highlightsArray,requirementsArray,sampleCourses);
        programList.add(eng);

        programData soc = new programData(programArray[3],infoArray[3],highlightsArray,requirementsArray,sampleCourses);
        programList.add(soc);


        /* TODO TESTING STUFF IS ABOVE THIS TO DO... DELETE LATER */

        recyclerView = findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // PROBABLY HAVE TO DELETE THIS LINE..
        adapter = new programAdapter(programList, this, new programAdapter.OnNoteListener() {
            @Override
            public void OnNoteClick(View view, int position) {
//                Log.i(ACTIVITY_NAME,"how bout hre?");
//                Log.i(ACTIVITY_NAME,"onNoteClicked: " + position);
//
//                // added this last minute
//                adapter.notifyDataSetChanged();
            }
        });

        recyclerView.setAdapter(adapter);



        toolbar2 = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar2);
        getSupportActionBar().setTitle("Programs");
        searchView = (SearchView)findViewById(R.id.search);


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            Toast.makeText(exploreProgram2.this, "Action clicked", Toast.LENGTH_LONG).show();

            //SEARCH
        }

        if (id == R.id.navigation_settings){
            Intent intent = new Intent(this, programFilter.class );
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_nav, menu);

        // reference to menu item search
        MenuItem searchItem = menu.findItem(R.id.search);

        // refer to search view
        final SearchView searchView = (SearchView)searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        Log.i(ACTIVITY_NAME,"In onCreateOptionsMenu");

        return true;
    }
}
