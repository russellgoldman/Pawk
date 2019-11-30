package com.example.cp470_group_project;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;
import androidx.annotation.NonNull;

import java.util.ArrayList;

/*
    THIS CLASS IS NOT USED LOL
 */

public class programFragment extends Fragment {

    exploreProgram2 exploreProgramWindow;
    private static final String ACTIVITY_NAME = "programFragment";

    public programFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sample_course_fragment, container, false);
        GridView gridView = (GridView) v.findViewById(R.id.gridview);
        int[] books = {1,2,3,4,5};

        gridView.setAdapter(new sampleCourseFragmentAdapter(v.getContext(),books,getFragmentManager()));

        Log.i(ACTIVITY_NAME, "In onCreateView");
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);


//        final Bundle data = this.getArguments();
//        Log.i(ACTIVITY_NAME, "Bundle: " + data.isEmpty() + "");
//        Log.i(ACTIVITY_NAME, "Program Name: " + data.getString("programName"));
//        Log.i(ACTIVITY_NAME, "Program Desc: " + data.getString("programDesc"));
    }

    public class sampleCourseAdapter extends ArrayAdapter<programData>{
        public sampleCourseAdapter(Context context, ArrayList<programData> programs){
            super(context, 0, programs);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){

            return convertView;
        }
    }

}