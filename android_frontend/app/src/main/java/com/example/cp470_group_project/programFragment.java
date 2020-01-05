package com.example.cp470_group_project;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.util.Log;
import androidx.annotation.NonNull;

/**
 *
 * <h1>programFragment</h1>
 * This contains the fragment needed to show sample courses on the program pages.
 *
 * <h2>Use Cases</h2>
 * This is for users to see sample courses from the program.
 *
 */

public class programFragment extends Fragment {

    private static final String ACTIVITY_NAME = "programFragment";

    public programFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sample_course_fragment, container, false);
        return v;
    }

    /**
     *
     * This creates a new fragment for sample courses for gridView.
     *
     * @param view
     * @param savedInstanceState
     */

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        Log.i(ACTIVITY_NAME, "In onCreateView");

        super.onViewCreated(view, savedInstanceState);

        Bundle data = getActivity().getIntent().getExtras();

        String[] sampleCourses = data.getStringArray("sampleCourses");
        String[] ratings = data.getStringArray("sampleCourseRatings");

        sampleCourseFragmentAdapter adapter = new sampleCourseFragmentAdapter(getActivity(), sampleCourses,ratings);
        final GridView gridView = (GridView) view.findViewById(R.id.gridview);
        gridView.setAdapter(adapter);
    }

    /**
     *
     * <h1>sampleCourseFragmentAdapter</h1>
     * This inflates the layout for each sample course to be displayed in the sample course fragment.
     *
     */

public class sampleCourseFragmentAdapter extends BaseAdapter {

    private final Context mContext;
    private final String[] courses;
    private final String[] ratings;
    private static final String ACTIVITY_NAME = "sampleCourseFragAdapter";

    public sampleCourseFragmentAdapter(Context context, String[] courses, String[] ratings) {
        this.mContext = context;
        this.courses = courses;
        this.ratings = ratings;
    }

    @Override
    public int getCount() {
        return courses.length;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(R.layout.sample_course_item, null);

        TextView titleView = convertView.findViewById(R.id.sampleCourseCode);
        titleView.setText(courses[position]);

        TextView rating = convertView.findViewById(R.id.sampleCourseRating);
        rating.setText(ratings[position]);

        return convertView;
    }

}
}