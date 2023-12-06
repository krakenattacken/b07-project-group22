package com.example.b07finalproject.ui.announcements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.b07finalproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AnnouncementItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class AnnouncementItemFragment extends Fragment {

    public AnnouncementItemFragment() {
        // Required empty public constructor
    }

    public static AnnouncementItemFragment newInstance(String param1, String param2) {
        AnnouncementItemFragment fragment = new AnnouncementItemFragment();
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
        return inflater.inflate(R.layout.fragment_announ_item, container, false);
    }
}
