package com.example.cp470_group_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Adapter class used to set View with list of courses
 */
public class ExploreCoursesRecyclerAdapter extends RecyclerView.Adapter<ExploreCoursesRecyclerAdapter.ViewHolder> {
    private ArrayList<ExploreCoursesActivity.Course> courses;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;

    ExploreCoursesRecyclerAdapter(Context context, ArrayList<ExploreCoursesActivity.Course> courses) {
        this.inflater = LayoutInflater.from(context);
        this.courses = courses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.explore_course_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ExploreCoursesActivity.Course course = courses.get(position);
        holder.courseName.setText(course.code);
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView courseName, courseRating;

        ViewHolder(View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.course_name);
            courseRating = itemView.findViewById(R.id.course_rating);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    ExploreCoursesActivity.Course getItem(int id) {
        return courses.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}