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

import androidx.annotation.NonNull;
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
import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sample.ExploreProgramsQuery;

import org.jetbrains.annotations.NotNull;


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

    public static ApolloClient client;

    Intent intent;

    final public ArrayList<programData> programList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_program2);

        client = new GraphQLClient().getClient();
        ExploreProgramsQuery exploreProgramQuery = ExploreProgramsQuery.builder().build();

        client.query(exploreProgramQuery).enqueue(new ApolloCall.Callback<ExploreProgramsQuery.Data>() {
            @Override public void onResponse(@NotNull Response<ExploreProgramsQuery.Data> dataResponse) {
                Log.i(ACTIVITY_NAME, dataResponse.data().toString());

                for (ExploreProgramsQuery.Node node: dataResponse.data().allPrograms().nodes()) {
                    programData pData = new programData(
                            node.name(),
                            node.description(),
                            node.description(),
                            node.description(),
                            node.requiredCourses()
                    );
                    programList.add(pData);
                }

                Log.i(ACTIVITY_NAME,"size: "+ programList.size());

                exploreProgram2.this.runOnUiThread(new Runnable() {
                    @Override public void run() {
                        Log.i(ACTIVITY_NAME, "GraphQL fetch complete");
                    }
                });

            }

            // SEARCH FILTER DOESN'T WORK BECAUSE LIST SI EMPTY OUTSIDE OF THE FUNCTION

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.e(ACTIVITY_NAME, e.getMessage(), e);
            }
        });


        recyclerView = findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // PROBABLY HAVE TO DELETE THIS LINE..
        Log.i(ACTIVITY_NAME,"size before adapter code: "+programList.size());


        adapter = new programAdapter(programList, this, new programAdapter.OnNoteListener() {
            @Override
            public void OnNoteClick(View view, int position) {
                Log.i(ACTIVITY_NAME,"how bout hre?");
                Log.i(ACTIVITY_NAME,"onNoteClicked: " + position);

                // added this last minute
                adapter.notifyDataSetChanged();
            }
        });

        recyclerView.setAdapter(adapter);

        toolbar2 = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar2);
        getSupportActionBar().setTitle("Programs");
        searchView = (SearchView)findViewById(R.id.search);

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_courses:
                        intent = new Intent(exploreProgram2.this, CoursePageActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_settings:
//                        intent = new Intent(exploreProgram2.this, CourseReg.class);
//                        startActivity(intent);
                        break;
                }
                return true;
            }
        });


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