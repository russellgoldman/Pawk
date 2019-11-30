package com.example.pawk;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity {

    List<DataModel> list=new ArrayList<>();
    RecyclerView recyclerView;
    private ActionBar toolbar;
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        toolbar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Favourite Course");
        
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        linearLayout = (LinearLayout)findViewById(R.id.linear_fav);


        for (int i=0;i<5;i++){
            list.add(new DataModel("CP31"+i,0,false));
        }

        RecyclerViewAdapter mLogoGridAdapter = new RecyclerViewAdapter(this, list,new RecyclerViewAdapter.OnItemClickListener(){
            @Override public void onItemClick(DataModel item) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(FavouriteActivity.this,R.style.CustomAlertDialog);

                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = getLayoutInflater().inflate(R.layout.custom_dialog, viewGroup, false);
                Button buttonOk=dialogView.findViewById(R.id.buttonyes);
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                buttonOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        recyclerView.setAdapter(mLogoGridAdapter);




    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbar_favourite, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorite) {

            Snackbar.make(linearLayout,"Editing Courses...",Snackbar.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
