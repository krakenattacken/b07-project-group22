package com.example.b07finalproject.ui.announcements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07finalproject.R;
import com.example.b07finalproject.databinding.FragmentAnnouncementsBinding;
import com.example.b07finalproject.ui.announcements.AnnouncementsViewModel;

import java.util.List;


public class AnnouncementsFragment extends Fragment {

    private FragmentAnnouncementsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AnnouncementsViewModel announcementsViewModel =
                new ViewModelProvider(this).get(AnnouncementsViewModel.class);

        binding = FragmentAnnouncementsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textannouncement;
        announcementsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AnnouncementsFragment.this)
                        .navigate(com.example.b07finalproject.R.id.action_nav_announcements_to_nav_postannoun);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}


/*

public class AnnouncementsFragment extends Fragment implements OnItemClickListener{


    private AnnouncementsViewModel mViewModel;
    private List<Announcement> announList;

    public static AnnouncementsFragment newInstance() {
        return new AnnouncementsFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_announcements, container, false);
        //get ref to recyclerView,
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.rvNewAnnouncements);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        AnnouncementAdapter mAdapter = new AnnouncementAdapter(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        AnnouncementsViewModel eventViewModel = new ViewModelProvider(this).get(AnnouncementsViewModel.class);
        eventViewModel.getItemList().observe(getViewLifecycleOwner(), new Observer<List<Announcement>>() {
            @Override
            public void onChanged(List<Announcement> announcements) {
                mAdapter.setAnnouncements(announcements);
            }
        });
        return root;
    }

    @Override
    public void onItemClick(int position) {
        NavHostFragment.findNavController(AnnouncementsFragment.this).navigate(R.id.action_newevents_to_eventitem);
    }
}

 */