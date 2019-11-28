package com.example.cp470_group_project;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourseRequisitesFragment extends Fragment {
    public String id;
    public String title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_requisites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle extras = this.getArguments();
        id = extras.getString("id");
        title = extras.getString("title");

        // get requisites array
        ArrayList<CoursePageActivity.Course> courseRequisites = extras.getParcelableArrayList(id);

        TextView titleView = getView().findViewById(R.id.requisite_title);
        titleView.setText(title);

        TextView requisiteNumView = getView().findViewById(R.id.requisite_num);
        requisiteNumView.setText(Integer.toString(courseRequisites.size()));

        // create ListView adapter
        CourseRequisiteAdapter adapter = new CourseRequisiteAdapter(getActivity(), courseRequisites);
        ListView requisiteListView = getView().findViewById(R.id.requisite_courses);
        requisiteListView.setAdapter(adapter);
    }

    /*
        Reference
        https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView
    */
    public class CourseRequisiteAdapter extends ArrayAdapter<CoursePageActivity.Course> {
        public CourseRequisiteAdapter(Context context, ArrayList<CoursePageActivity.Course> courses) {
            super(context, 0, courses);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CoursePageActivity.Course course = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.course_requisite_item, parent, false);
            }

            TextView code = convertView.findViewById(R.id.code);
            TextView rating = convertView.findViewById(R.id.rating);

            code.setText(course.code);
            //rating.setText(course.rating);

            return convertView;
        }
    }
}
