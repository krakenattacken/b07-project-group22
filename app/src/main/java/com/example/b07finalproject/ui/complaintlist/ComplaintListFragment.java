package com.example.b07finalproject.ui.complaintlist;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.b07finalproject.R;
import com.example.b07finalproject.databinding.ActivityMainBinding;
import com.example.b07finalproject.ui.complaint.Complaint;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ComplaintListFragment extends Fragment {



    public ComplaintListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_complaint_list, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView ComplaintListView = (ListView) view.findViewById(R.id.listview);
        List<Complaint> complaintList = getComplaints();

        ComplaintAdapter adapter = new ComplaintAdapter(requireContext(), R.layout.fragment_item_complaint_list, complaintList);
        ComplaintListView.setAdapter(adapter);

        /* Set item click listener if needed
        ComplaintListView.setOnItemClickListener((parent, view1, position, id) -> {
            // Handle item click
            String selectedComplaint = (String) parent.getItemAtPosition(position);
            Snackbar.make(view, "Selected Complaint: " + selectedComplaint, Snackbar.LENGTH_SHORT).show();
        }); */
    }

    private List<Complaint> getComplaints(){
        List<Complaint> complaints = new ArrayList<>();
        complaints.add(new Complaint("Heading 1", "Body 1"));
        complaints.add(new Complaint("Heading 2", "Body 2"));
        complaints.add(new Complaint("Heading 3", "Body 3"));
        return complaints;
    }


    }








