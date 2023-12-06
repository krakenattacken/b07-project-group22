package com.example.b07finalproject.ui.complaintlist;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.b07finalproject.R;
import com.example.b07finalproject.ui.complaint.Complaint;

import java.util.List;

public class ComplaintAdapter extends ArrayAdapter<Complaint> {
    public ComplaintAdapter(Context context, int resource, List<Complaint> complaints) {
        super(context, resource, complaints);
    }




    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_item_complaint_list, parent, false);
        }

        Complaint complaint = getItem(position);

        if(complaint != null) {
            TextView headingTextView = convertView.findViewById(R.id.textViewHeading);
            TextView bodyTextView = convertView.findViewById(R.id.textViewBody);

            headingTextView.setText(complaint.getHeading());
            bodyTextView.setText(complaint.getBody());
        } else {
            TextView headingTextView = convertView.findViewById(R.id.textViewHeading);
            TextView bodyTextView = convertView.findViewById(R.id.textViewBody);

            headingTextView.setText("N/A");
            bodyTextView.setText("N/A");
        }

        return convertView;
    }
}
