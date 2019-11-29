package com.example.cp470_group_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.widget.Button;
import android.util.Log;
import android.content.Intent;
import android.content.Context;


public class programAdapter extends RecyclerView.Adapter<programAdapter.RecViewHolder> {
    private List<programData> programList;
    TextView desc;

    private OnNoteListener MOnNoteListener;

    private String ACTIVITY_NAME = "programAdapter";

    private Context mContext;



    public programAdapter(List<programData> programList, Context context, OnNoteListener MOnNoteListener) {
        this.programList = programList;
        this.mContext = context;
        this.MOnNoteListener = MOnNoteListener;
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

}
