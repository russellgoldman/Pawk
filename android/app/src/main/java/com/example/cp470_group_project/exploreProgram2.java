package com.example.cp470_group_project;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import java.util.List;
import java.util.ArrayList;
import android.view.Menu;
import android.view.MenuInflater;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class exploreProgram2 extends AppCompatActivity{

    String[] programArray = {"Computer Science","Math","English","Sociology"};
    String[] infoArray={"this is computer scie","tis is math","this is eng", "this is soc"};

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_program2);

        // create instance of ProgramListAdapter class

        List<programData> programList = new ArrayList<>();

        for (int i = 0; i<programArray.length;i++){
            programList.add(new programData(programArray[i],infoArray[i]));
        }

        recyclerView = findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // PROBABLY HAVE TO DELETE THIS LINE..
        adapter = new programAdapter(programList, this, new OnNoteListener() {
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

    }


//    @Override
//    public void OnNoteClick(View view, int position){
//        Log.i(ACTIVITY_NAME,"how bout hre?");
//        Log.i(ACTIVITY_NAME,"onNoteClicked: " + position);
//        Bundle data = new Bundle();
//        data.putString("programName",);
//        data.putString("programDesc",);
//        Log.i(ACTIVITY_NAME,"programName: " + data.get("programName"));
//        Log.i(ACTIVITY_NAME,"programDesc: " + data.get("programDesc"));
//        Log.i(ACTIVITY_NAME,"in OnNoteClick");
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        programFragment programFragment = new programFragment();
//        programFragment.setArguments(data);
//        ft.replace(R.id.programDetailsEmptyFrame,programFragment);
// }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_nav, menu);

        Log.i(ACTIVITY_NAME,"In onCreateOptionsMenu");

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
