package com.example.b07finalproject.ui.announcements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.b07finalproject.DBDependent;
import com.example.b07finalproject.R;
import com.example.b07finalproject.databinding.FragmentPostAnnounBinding;
import com.example.b07finalproject.mainViewModel;

import java.util.ArrayList;
import java.util.List;


public class PostAnnounFragment extends Fragment implements DBDependent {

    Announcement announcementObj;
    AnnouncementAdapter announcementAdapter;

    AnnouncementsFragment announcementsFragment = new AnnouncementsFragment();

    List<Announcement> announcements = new ArrayList<Announcement>();

    String title;
    String time;
    String location;
    String description;

    EditText titleInput;
    EditText timeInput;
    EditText locationInput;
    EditText descriptionInput;

    Button postButton;


    private FragmentPostAnnounBinding binding;

    private mainViewModel viewModel;

    public static PostAnnounFragment newInstance() {
        return new PostAnnounFragment();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentPostAnnounBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public List<Announcement> getAnnouncements(){
        if (announcements != null){
            return announcements;
        }
        return null;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View titleV = requireActivity().findViewById(R.id.titleInput);
        View locationV = requireActivity().findViewById(R.id.locationInput);
        View descriptionV = requireActivity().findViewById(R.id.descriptionInput);
        View timeV = requireActivity().findViewById(R.id.timeInput);

        titleInput = (EditText)titleV.findViewById(R.id.titleInput);
        locationInput = (EditText)locationV.findViewById(R.id.locationInput);
        descriptionInput = (EditText)descriptionV.findViewById(R.id.descriptionInput);
        timeInput = (EditText)timeV.findViewById(R.id.timeInput);

        //AnnouncementAdapter.ViewHolder announViewHolder = announcementAdapter.new ViewHolder(view);

        //Post Button in fragment_post_announ_xml should send notifications to observers (students) and take admin back to AnnouncementsFragment page
        //with the new posted announcement at the top


        PostAnnounFragment thisFragment = this;
        binding.postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //These inputs should be in the database
                title = titleInput.getText().toString();
                time = timeInput.getText().toString();
                location = locationInput.getText().toString();
                description = descriptionInput.getText().toString();



                announcementObj = new Announcement(title, time, location, description);


                //viewModel.addToDB(announcementObj, "announcement", title+location, thisFragment);

                NavHostFragment.findNavController(PostAnnounFragment.this)
                        .navigate(R.id.action_nav_postannoun_to_nav_announcements);
            }
        });


        announcements.add(announcementObj);
        //announcementAdapter = new AnnouncementAdapter(announcements, announcementsFragment);
        //announcementAdapter.setAnnouncements(announcements);
        //announcementAdapter.onBindViewHolder(announViewHolder, 0);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void loadDataFromDB(Object[] items) {

    }

    @Override
    public void onDBFail(String reason) {

    }
}

