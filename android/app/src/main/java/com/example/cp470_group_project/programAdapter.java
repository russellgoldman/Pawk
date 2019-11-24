package com.example.cp470_group_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.widget.LinearLayout;
import android.widget.Button;
import android.util.Log;
import android.content.Intent;
import android.content.Context;


public class programAdapter extends RecyclerView.Adapter<programAdapter.RecViewHolder> {
    private List<programData> list;

    public programAdapter(List<programData> list) {
        this.list = list;
    }

    TextView desc;

    private String ACTIVITY_NAME = "programAdapter";

    @Override
    public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_program, parent, false);
        return new RecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecViewHolder holder, int position){
        final programData program = list.get(position);
        holder.bind(program);

        final int position2 = position;

        final RecViewHolder holder2 = holder;

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                boolean expanded = program.isExpanded();
                program.setExpanded(!expanded);
                notifyItemChanged(position2);
            }
        });

        holder.learnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getContext(), "The position is: "+holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();

                //startActivity(intent);
              //  mContext.startActivity(intent);
                Context context = view.getContext();
                Log.i(ACTIVITY_NAME, "position: "+ holder2.getAdapterPosition());
                Intent intent = new Intent(context, programPage.class);
                intent.putExtra("position",holder2.getAdapterPosition());
                context.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class RecViewHolder extends RecyclerView.ViewHolder{
            private TextView title;
            private TextView desc;
            private View subItem;
            private Button learnMore;


            public RecViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.item_title);
            desc = itemView.findViewById(R.id.sub_item_desc);
            subItem = itemView.findViewById(R.id.sub_item);
            learnMore = itemView.findViewById(R.id.learnMoreButton2);

//            learnMore.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Log.i(ACTIVITY_NAME,"do we ntent hre?");
//                }
//            });
             }

//
            private void bind(programData program){
                boolean expanded = program.isExpanded();
                subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);
                title.setText(program.getProgramName());
                desc.setText(program.getProgramBlurb());
            }
    }
}
