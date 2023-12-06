package com.example.b07finalproject.ui.announcements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07finalproject.DBDependent;
import com.example.b07finalproject.R;
import com.example.b07finalproject.mainViewModel;
import com.example.b07finalproject.ui.login.Admin;
import com.example.b07finalproject.ui.login.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementsFragment extends Fragment implements OnItemClickListener, DBDependent {


    private AnnouncementsViewModel mViewModel;
    private List<Announcement> announList;

    PostAnnounFragment postAnnounFragment;

    public User user;
    private mainViewModel viewModel;

    public static AnnouncementsFragment newInstance() {
        return new AnnouncementsFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_new_announ, container, false);
        //get ref to recyclerView,
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.rvNewAnnouncements);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        AnnouncementAdapter mAdapter = new AnnouncementAdapter(announList,this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        AnnouncementsViewModel eventViewModel = new ViewModelProvider(this).get(AnnouncementsViewModel.class);
        eventViewModel.getItemList().observe(getViewLifecycleOwner(), new Observer<List<Announcement>>() {
            @Override
            public void onChanged(List<Announcement> announcements) {
                mAdapter.setAnnouncements(announcements);
            }
        });
        viewModel = new ViewModelProvider(getActivity()).get(mainViewModel.class);

        return root;
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        super.onViewCreated(view,savedInstanceState);
        announList = new ArrayList<Announcement>();
        View appBarMainView = requireActivity().findViewById(R.id.new_post);
        FloatingActionButton admin_see_button = appBarMainView.findViewById(R.id.new_post);

        //add this
        announList = Announcement.createAnnouncementList();

        user = viewModel.getCurrentUser();

        if (user instanceof Admin) {
            admin_see_button.setVisibility(View.VISIBLE);
            admin_see_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavHostFragment.findNavController(AnnouncementsFragment.this)
                            .navigate(R.id.action_nav_announcements_to_nav_postannoun);
                }
            });
        }
        else {
            admin_see_button.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(int position) {


        Announcement clickedAnnoun = announList.get(position);

        Bundle bundle = new Bundle();
        bundle.putSerializable("clicked_announ", clickedAnnoun);
        bundle.putSerializable("user", user);

        NavHostFragment.findNavController(AnnouncementsFragment.this)
                .navigate(R.id.action_nav_announcements_to_nav_postannoun, bundle);

    }



    @Override
    public void loadDataFromDB(List<Object> items) {

    }

    @Override
    public void onDBFail(String reason) {

    }
}