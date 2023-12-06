package com.example.b07finalproject.ui.events;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.b07finalproject.R;
public class FeedbackSubmittedFragment extends Fragment {

    public FeedbackSubmittedFragment() {
        // Required empty public constructor
    }
    public static FeedbackSubmittedFragment newInstance(String param1, String param2) {
        FeedbackSubmittedFragment fragment = new FeedbackSubmittedFragment();
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
        return inflater.inflate(R.layout.fragment_feedback_submitted, container, false);
    }
}