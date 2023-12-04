package com.example.b07finalproject.ui.events;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.b07finalproject.R;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NewEventFragment extends Fragment implements OnItemClickListener{

    private NewEventViewModel mViewModel;
    private List<Event> eventList;

    public static NewEventFragment newInstance() {
        return new NewEventFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_new_event, container, false);
        //get ref to recyclerView,
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.rvNewEvents);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        EventAdapter mAdapter = new EventAdapter(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        NewEventViewModel eventViewModel = new ViewModelProvider(this).get(NewEventViewModel.class);
        eventViewModel.getItemList().observe(getViewLifecycleOwner(), new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {
                mAdapter.setEvents(events);
            }
        });
        return root;
    }

    @Override
    public void onItemClick(int position) {
        NavHostFragment.findNavController(NewEventFragment.this).navigate(R.id.action_newevents_to_eventitem);
    }
}