package com.example.b07finalproject.ui.complaintlist;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.b07finalproject.R;
import com.example.b07finalproject.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class ComplaintListFragment extends Fragment {

   /* ListView ComplaintList;
    String[] headers;
    String[] texts;*/


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
        ListView ComplaintList = (ListView) view.findViewById(R.id.listview);

    }




}

