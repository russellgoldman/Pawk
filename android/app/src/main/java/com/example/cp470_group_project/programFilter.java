package com.example.cp470_group_project;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class programFilter extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    public String ACTIVITY_NAME = "programFilter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program_filter);

        /* YOUR SPINNED DOES NOT WORK MICHELLE */
//        Spinner spinner = (Spinner) findViewById(R.id.facultyChoicesSpinner);
//        Log.i(ACTIVITY_NAME,"Found: "+ spinner);
//
//        spinner.setOnItemSelectedListener(this);
//        loadSpinnerData();
    }

    private void loadSpinnerData(){
        // load spinner data;
        //Databasehandler db = new DatabaseHandler(getApplicationContext());

        List<String> labels = new ArrayList<>();
        labels.add("Waterloo");
        labels.add("Brantford");
        labels.add("Kitchener");

       // ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, labels);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,labels);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        String label = parent.getItemAtPosition(position).toString();

        Toast.makeText(parent.getContext(), "Selected: "+label, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0){
        //AUTO GENERATED
    }
}
