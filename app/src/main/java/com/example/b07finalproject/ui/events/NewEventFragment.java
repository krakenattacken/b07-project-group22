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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.b07finalproject.R;
import com.example.b07finalproject.ui.login.Admin;
import com.example.b07finalproject.ui.login.Student;
import com.example.b07finalproject.ui.login.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NewEventFragment extends Fragment implements OnItemClickListener{

    private NewEventViewModel mViewModel;
    private List<Event> eventList;

    User user;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        eventList = Event.createEventList(5);
        user = new Admin();
        user = new Student();

        if (user instanceof Admin) {
            View appBarMainView = requireActivity().findViewById(R.id.app_bar_main);
            FloatingActionButton fab = appBarMainView.findViewById(R.id.fab);
            if (fab != null) {
                fab.setVisibility(View.VISIBLE);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        fab.setVisibility(View.INVISIBLE);
                        NavHostFragment.findNavController(NewEventFragment.this)
                                .navigate(R.id.action_newevents_to_send_rsvp);
                    }
                });
            } else {
                Log.e("NewEventFragment", "FAB is null");
            }
        }
    }
    @Override
    public void onItemClick(int position) {
        if (eventList == null) {
            Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();
        }
        Event clickedEvent = eventList.get(position);

        View appBarMainView = requireActivity().findViewById(R.id.app_bar_main);
        FloatingActionButton fab = appBarMainView.findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);

        Bundle bundle = new Bundle();
        bundle.putSerializable("clicked_event", clickedEvent);
        bundle.putSerializable("user", user);

        NavHostFragment.findNavController(NewEventFragment.this)
                .navigate(R.id.action_newevents_to_eventitem, bundle);
    }
}