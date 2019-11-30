package com.example.cp470_group_project;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

/*
    This is for the exploreProgram area
 */
public class ProgramListAdapter extends ArrayAdapter {
    // references the activity (stores activity the list view is on)
    private final Activity context;

    // stores program names
    private final String[] nameArray;

    //stores program description
    private final String[] infoArray;

    private String ACTIVITY_NAME = "programListAdapter";

    /*
           Constructor

     */
    public ProgramListAdapter(Activity context, String[] nameArrayParam, String[] infoArrayParam){
        super(context,R.layout.listview_row,nameArrayParam);

        this.context = context;
        this.infoArray = infoArrayParam;
        this.nameArray = nameArrayParam;
    }

    /*
        Used by app to populate data into each row
     */
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listview_row, null,true);

        // Get references to objects in listview_row
        TextView nameTextField = rowView.findViewById(R.id.programTitleTextView);
        TextView infoTextField = rowView.findViewById(R.id.programDescTextView);
        Button learnButton = rowView.findViewById(R.id.learnMoreButton);

        // sets values of the objects to values from array
        nameTextField.setText(nameArray[position]);
        infoTextField.setText(infoArray[position]);

        learnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(ACTIVITY_NAME,"is it here?");
            }
        });

        return rowView;
    };


}
