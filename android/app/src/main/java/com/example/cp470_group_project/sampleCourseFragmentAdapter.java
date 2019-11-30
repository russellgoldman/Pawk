package com.example.cp470_group_project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

public class sampleCourseFragmentAdapter extends BaseAdapter {

    private final Context mContext;
    private final int[] books;
//    Course course;
    FragmentManager fragManager;
    private static final String ACTIVITY_NAME = "sampleCourseFragAdapter";

    // 1
    public sampleCourseFragmentAdapter(Context context, int[] books, FragmentManager fragManager) {
        this.mContext = context;
//        this.course = course;
        this.books = books;
        this.fragManager = fragManager;
    }


    // 2
    @Override
    public int getCount() {
        return books.length;
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
        return convertView;
    }

}

