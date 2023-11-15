package com.example.b07finalproject.ui.events;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.b07finalproject.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private final OnItemClickListener onItemClickListener;
    private List<Event> events;

    public EventAdapter(OnItemClickListener onItemClickListener) {
        this.events = new ArrayList<>();
        this.onItemClickListener = onItemClickListener;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView eventName;
        public TextView eventTime;
        public TextView eventLocation;
        public ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            eventLocation = (TextView) itemView.findViewById(R.id.event_location);
            eventTime = (TextView) itemView.findViewById(R.id.event_time);
            eventName= (TextView) itemView.findViewById(R.id.event_name);

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
    public void setEvents(List<Event> events){
        // will probably be changed
        this.events = events;
        notifyDataSetChanged();
    }

    //following 3 fns are required for recyclerview adapter
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View eventView = inflater.inflate(R.layout.event_view_item, parent, false);

        return new ViewHolder(eventView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Event event = events.get(position);
        holder.eventLocation.setText(event.getLocation());
        holder.eventName.setText(event.getName());
        holder.eventTime.setText(event.getTime());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }


}
