package com.example.b07finalproject.ui.complaintlist;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.b07finalproject.DBDependent;
import com.example.b07finalproject.R;
import com.example.b07finalproject.mainDBModel;
import com.example.b07finalproject.ui.complaint.Complaint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComplaintListFragment extends Fragment implements DBDependent {

    private mainDBModel DBModel;
    private ComplaintAdapter adapter;
    private List<Complaint> complaintList;
    ListView listView;

    public static ComplaintListFragment newInstance() {
        return new ComplaintListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_complaint_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DBModel = new mainDBModel();
        complaintList = new ArrayList<>();
        listView = view.findViewById(R.id.listview);


        // Fetch data from the database
        DBModel.getAllFromDB("complaints", this, Complaint.class);

        view.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ComplaintListFragment.this)
                        .navigate(R.id.action_nav_complaintlist_to_nav_home);
            }
        });
    }

    @Override
    public void loadDataFromDB(List<Object> items) {
        complaintList.clear();

        for (Object item : items) {
            if (item instanceof Complaint) {
                complaintList.add((Complaint) item);
            }
        }
        adapter = new ComplaintAdapter(getContext(), R.layout.fragment_item_complaint_list, complaintList);
        listView.setAdapter(adapter);

    }

    @Override
    public void onDBFail(String reason) {
        // Handle database failure
    }


}












