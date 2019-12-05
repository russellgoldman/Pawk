package com.example.pawk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<DataModel> logoList;
    private Context mContext;
    private RecyclerViewAdapter.OnItemClickListener listener;


    public RecyclerViewAdapter(Context mContext, List<DataModel> logoList,OnItemClickListener listener) {

        this.mContext = mContext;
        this.logoList = logoList;
        this.listener = listener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favourite_course, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final DataModel logo = getLogo(position);

        holder.title.setText(logo.text);
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(logo);

            }
        });

    }


    private DataModel getLogo(int position) {
        return logoList.get(position);
    }

    public List<DataModel> getLogoList() {
        return logoList;
    }

    @Override
    public int getItemCount() {
        return logoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView thumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            // get logo view
            title = (TextView) itemView.findViewById(R.id.title);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(DataModel item);
    }
}