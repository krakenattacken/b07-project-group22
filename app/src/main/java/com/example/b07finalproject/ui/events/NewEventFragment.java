package com.example.b07finalproject.ui.events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.b07finalproject.R;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.b07finalproject.DBDependent;
import com.example.b07finalproject.R;
import com.example.b07finalproject.mainDBModel;
import com.example.b07finalproject.mainViewModel;
import com.example.b07finalproject.ui.login.Admin;
import com.example.b07finalproject.ui.login.Student;
import com.example.b07finalproject.ui.login.User;
import com.example.b07finalproject.ui.viewmodel.CategoryViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NewEventFragment extends Fragment implements OnItemClickListener, DBDependent {

    //private NewEventViewModel mViewModel;
    private List<Event> eventList;
    private mainDBModel dbModel;
    private mainViewModel viewModel;
    User user;

    public static NewEventFragment newInstance() {
        return new NewEventFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbModel = new mainDBModel();
        viewModel = new ViewModelProvider(requireActivity()).get(mainViewModel.class);
        user = viewModel.getCurrentUser();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_new_event, container, false);
        //get ref to recyclerView,
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.rvNewEvents);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        EventAdapter mAdapter = new EventAdapter(eventList, this);
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
        user = viewModel.getCurrentUser();
        eventList = new ArrayList<Event>();

        dbModel.getAllFromDB("events", this, Event.class);
    }
    @Override
    public void onItemClick(int position) {
        if (eventList == null) {
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

    @Override
    public void loadDataFromDB(List<Object> items) {

        for (int i = 0; i < items.size(); i++) {
            Event event = (Event) items.get(i);
            if (event != null) {
                LocalDateTime now = LocalDateTime.now();
                // if the event is held more than 10 days ago then delete from database
                if (event.toDateTime().isBefore(now.minusDays(10))) {
                    dbModel.remove("events", event.toString());
                }
                else{
                    eventList.add(event);
                }
            }
        }

        RecyclerView recyclerView = getView().findViewById(R.id.rvNewEvents);
        EventAdapter mAdapter = new EventAdapter(eventList, this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


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
    public void onDBFail(String reason) {
    }
}