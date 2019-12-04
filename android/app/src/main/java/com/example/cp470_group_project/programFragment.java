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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

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
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        Bundle data = getActivity().getIntent().getExtras();
        Log.i(ACTIVITY_NAME + " onV","sampleCourses: " + data.getStringArray("sampleCourses"));
        Log.i(ACTIVITY_NAME,"programName: " + data.getString("programName"));


        String[] sampleCourses = data.getStringArray("sampleCourses");
        //gridView.setAdapter(new sampleCourseFragmentAdapter(view.getContext(),sampleCourses,getFragmentManager()));

        sampleCourseFragmentAdapter adapter = new sampleCourseFragmentAdapter(getActivity(), sampleCourses);
        final GridView gridView = (GridView) view.findViewById(R.id.gridview);
        gridView.setAdapter(adapter);


        Log.i(ACTIVITY_NAME, "In onCreateView");

    }
public class sampleCourseFragmentAdapter extends BaseAdapter {

    private final Context mContext;
    // private final int[] books;
    private final String[] courses;
    //    Course course;
    FragmentManager fragManager;
    private static final String ACTIVITY_NAME = "sampleCourseFragAdapter";

    // 1
    public sampleCourseFragmentAdapter(Context context, String[] courses) {
        this.mContext = context;
        this.courses = courses;
    }


    // 2
    @Override
    public int getCount() {
        return courses.length;
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return null;
    }

    // 5
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(R.layout.sample_course_item, null);

        TextView titleView = convertView.findViewById(R.id.sampleCourseCode);
        Log.i(ACTIVITY_NAME, "sampleCourse: " + courses[position]);
        titleView.setText(courses[position]);


        return convertView;
    }

}




}