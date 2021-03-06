package com.example.cp470_group_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.widget.Button;
import android.util.Log;
import android.content.Intent;
import android.content.Context;
import android.widget.Filter;
import android.widget.Filterable;


/**
 *
 * <h1>programAdapter</h1>
 * This displays the programs on explore programs page
 * as well as keeps track of information between the explore program (all) activity
 * and the actual specific program page activity.
 *
 */

public class programAdapter extends RecyclerView.Adapter<programAdapter.RecViewHolder> implements Filterable {
    ArrayList<programData> programList, programListFull;
    //FilterList is a copy of programList for the filter..
    TextView desc;

    private OnNoteListener MOnNoteListener;

    private String ACTIVITY_NAME = "programAdapter";

    private Context mContext;

    public programAdapter(ArrayList<programData> programList, ArrayList<programData> programListCopy, Context context, OnNoteListener MOnNoteListener) {
        this.programList = programList;
        this.mContext = context;
        this.MOnNoteListener = MOnNoteListener;
        // makes copy of list for the search thing
        programListFull = programListCopy;
    }

    @Override
    public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_program, parent, false);

        return new RecViewHolder(view, MOnNoteListener);
    }

    @Override
    public void onBindViewHolder(RecViewHolder holder, int position){
        final programData program = programList.get(position);
        holder.bind(program);

        final int position2 = position;

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                boolean expanded = program.isExpanded();
                program.setExpanded(!expanded);
                notifyItemChanged(position2);
            }
        });

    }

    @Override
    public int getItemCount() {
        return programList == null ? 0 : programList.size();
    }


    /**
     * Function for real time search implemented for explore program page.
     * @return
     */

    @Override
    public Filter getFilter() {
        return programFilter;
    }

    private Filter programFilter = new Filter(){
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            // return filter results
            List<programData> filteredList = new ArrayList<>();

            // if constraints emptyu, show all the results.. b/c no filter
            if (constraint == null || constraint.length() == 0) {

                Log.i(ACTIVITY_NAME,"NO SEARCH FILTER APPLIED");
                filteredList.addAll(programListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (programData item : programListFull) {
                    if (item.getProgramName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        /**
         *
         * Function publishes search results for the search button
         * @param constraint
         * @param results
         */

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results){
            // clear recycle view
            programList.clear();

            // add filtered results to our recycleview list
            programList.addAll((List)results.values);

            //update
            notifyDataSetChanged();
        }
    };


    /**
     *
     * This creates the layout for all programs in the database and
     * keeps track of data for the actual specific program page
     *
     * <h2> USE CASES </h2>
     * User can click on each program and get additional information about the program or just view a preview of it.
     *
     */

    public class RecViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            private TextView title;
            private TextView desc;
            private View subItem;
            private Button learnMore;
            OnNoteListener onNoteListener;

            public RecViewHolder(View itemView, final OnNoteListener onNoteListener) {
                super(itemView);

                title = itemView.findViewById(R.id.item_title);
                desc = itemView.findViewById(R.id.sub_item_desc);
                subItem = itemView.findViewById(R.id.sub_item);
                learnMore = itemView.findViewById(R.id.learnMoreButton2);

                learnMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(onNoteListener!=null) {
                            Log.i(ACTIVITY_NAME,"did i get here?");
                            Log.i(ACTIVITY_NAME,"I'm in this onClick");
                            Log.i(ACTIVITY_NAME, "Position: " + getAdapterPosition());

                            Context context = view.getContext();
                            Intent intent = new Intent(context, programPage.class);
                            Bundle data = new Bundle();
                            int pos = getAdapterPosition();
                            programData p = programList.get(pos);

                            data.putString("programName",p.getProgramName());
                            data.putString("programDesc",p.getProgramBlurb());
                            data.putInt("programID",getAdapterPosition());
                            data.putInt("duration",p.getDuration());
                            data.putInt("coopTerms",p.getCoopTerms());

                            List<String> courses = p.getSampleCourses();

                            // put random rating
                            List<String> ratings = new ArrayList<>();
                            for (int i = 0; i<courses.size();i++){
                                    int x = (int)(Math.random()*((5-1)+1))+1;
                                    ratings.add(Integer.toString(x));
                            }

                            String[] ratingsList = ratings.toArray(new String[0]);
                            data.putStringArray("sampleCourseRatings",ratingsList);

                            String[] courseList = courses.toArray(new String[0]);
                            data.putStringArray("sampleCourses",courseList);
                            intent.putExtras(data);
                            context.startActivity(intent);

                        } else {
                            Log.i(ACTIVITY_NAME,"it's null");
                        }
                    }
                });

             }

             @Override
             public void onClick(View view) {

             }

            private void bind(programData program){
                boolean expanded = program.isExpanded();
                subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);
                title.setText(program.getProgramName());
                desc.setText(program.getProgramBlurb());
            }
    }

    public interface OnNoteListener{
        void OnNoteClick(View view, int position);
    }

}
