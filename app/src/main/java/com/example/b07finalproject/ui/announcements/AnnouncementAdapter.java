package com.example.b07finalproject.ui.announcements;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.b07finalproject.R;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.ViewHolder> {
    private final OnItemClickListener onItemClickListener;
    private List<Announcement> announcements;

    PostAnnounFragment postAnnounFragment;

    public AnnouncementAdapter(List<Announcement> announcements, OnItemClickListener onItemClickListener) {
        this.announcements = announcements;
        this.onItemClickListener = onItemClickListener;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView announName;
        public TextView announTime;
        public TextView announLocation;
        public ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            announLocation = (TextView) itemView.findViewById(R.id.announ_location);
            announTime = (TextView) itemView.findViewById(R.id.announ_time);
            announName= (TextView) itemView.findViewById(R.id.announ_name);

            //for click behaviour of list items
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        int position = getBindingAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onItemClickListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
    public void setAnnouncements(List<Announcement> announcements){
        // will probably be changed
        this.announcements = announcements;
        notifyDataSetChanged();
    }

    //following 3 fns are required for recyclerview adapter
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View announView = inflater.inflate(R.layout.announ_view_item, parent, false);

        return new ViewHolder(announView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Announcement announ = announcements.get(position);
        holder.announLocation.setText(announ.getLocation());
        holder.announName.setText(announ.getName());
        holder.announTime.setText(announ.getTime());
    }

    @Override
    public int getItemCount() {
        return announcements.size();
    }


}






