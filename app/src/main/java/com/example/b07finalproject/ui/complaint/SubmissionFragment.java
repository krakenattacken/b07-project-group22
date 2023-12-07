package com.example.b07finalproject.ui.complaint;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.b07finalproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SubmissionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubmissionFragment extends Fragment {

    public SubmissionFragment() {
        // Required empty public constructor
    }
    public static SubmissionFragment newInstance(String param1, String param2) {
        SubmissionFragment fragment = new SubmissionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_complaint_submission, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(SubmissionFragment.this)
                        .navigate(R.id.action_nav_submission_to_nav_home);
                                                           }

                                                       }

        );

    }
}
