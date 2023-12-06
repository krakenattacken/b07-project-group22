package com.example.b07finalproject.ui.announcements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.b07finalproject.R;
import com.example.b07finalproject.mainDBModel;
import com.example.b07finalproject.ui.login.User;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AnnouncementItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class AnnouncementItemFragment extends Fragment {

    private Announcement announcement;
    private User user;
    private mainDBModel dbModel;
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
        dbModel = new mainDBModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_announ_item, container, false);
    }
}

