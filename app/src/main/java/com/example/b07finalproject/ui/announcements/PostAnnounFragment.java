package com.example.b07finalproject.ui.announcements;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07finalproject.DBDependent;
import com.example.b07finalproject.R;
import com.example.b07finalproject.databinding.FragmentPostAnnounBinding;
import com.example.b07finalproject.mainDBModel;
import com.example.b07finalproject.mainViewModel;
import com.example.b07finalproject.ui.events.EventAdapter;
import com.example.b07finalproject.ui.events.NewEventViewModel;
import com.example.b07finalproject.ui.login.User;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


public class PostAnnounFragment extends Fragment {


    AnnouncementAdapter announcementAdapter;

    AnnouncementsFragment announcementsFragment = new AnnouncementsFragment();

    //List<Announcement> announcements = new ArrayList<Announcement>();
    private mainDBModel dbModel;

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

    public PostAnnounFragment(){
    }

    public static PostAnnounFragment newInstance(String param1, String param2) {
        PostAnnounFragment fragment = new PostAnnounFragment();
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
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        /*dbModel = new mainDBModel();
        binding = FragmentPostAnnounBinding.inflate(inflater, container, false);
        return binding.getRoot();

         */
        return inflater.inflate(R.layout.fragment_post_announ, container, false);
    }

    private EditText newEditor(View view, int editTextID, String field, int filter) {
        EditText editText = view.findViewById(editTextID);
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(filter);
        editText.setFilters(filters);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (! s.toString().isEmpty()) {
                    if (field.equals("title")) {
                        title = s.toString();
                    }
                    else if (field.equals("time")) {
                        DateTimeFormatter formatter = DateTimeFormatter.
                                ofPattern("MMM dd, yyyy HH:mm");
                        try {
                            LocalDateTime parsedDateTime = LocalDateTime.parse(s.toString(), formatter);
                            LocalDateTime dateTime = parsedDateTime;
                            time = s.toString();
                        }
                        catch (DateTimeParseException e){
                            editText.setError("Please type in the right format");
                        }
                    }
                    else if (field.equals("location")) {
                        location = s.toString();
                    }
                    else if (field.equals("description")) {
                        description = s.toString();
                    }
                }
            }
        });

        return editText;
    }

    private boolean validValues() {
        return title != null &&
                time != null &&
                location != null &&
                description != null;
    }

    private boolean validTime() {
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(time, formatter);

        // ideally from now but it will be harder to test feedback
        if (localDateTime.isAfter(now.minusDays(0)) && localDateTime.isBefore(now.plusYears(1))) {
            return true;
        }
        return false;
    }


    private void reset() {
        title = null;
        time = null;
        location = null;
        description = null;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*
        View titleV = requireActivity().findViewById(R.id.titleInput);
        View locationV = requireActivity().findViewById(R.id.locationInput);
        View descriptionV = requireActivity().findViewById(R.id.descriptionInput);
        View timeV = requireActivity().findViewById(R.id.timeInput);

        titleInput = (EditText)titleV.findViewById(R.id.titleInput);
        locationInput = (EditText)locationV.findViewById(R.id.locationInput);
        descriptionInput = (EditText)descriptionV.findViewById(R.id.descriptionInput);
        timeInput = (EditText)timeV.findViewById(R.id.timeInput);

         */

        //AnnouncementAdapter.ViewHolder announViewHolder = announcementAdapter.new ViewHolder(view);

        //Post Button in fragment_post_announ_xml should send notifications to observers (students) and take admin back to AnnouncementsFragment page
        //with the new posted announcement at the top

        reset();

        EditText titleInput = newEditor(view, R.id.titleInput, "title", 20);
        EditText timeInput = newEditor(view, R.id.timeInput, "time", 18);
        EditText locationInput = newEditor(view, R.id.locationInput,
                "location", 30);
        EditText descriptionInput = newEditor(view, R.id.descriptionInput,
                "description", 100);


        Button button = view.findViewById(R.id.postButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validValues() && validTime()) {
                    Announcement announcement = new Announcement(title, time, location, description);

                    dbModel.add(announcement, "announcements", title);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("announcement", announcement);
                    NavHostFragment.findNavController(PostAnnounFragment.this)
                            .navigate(R.id.action_nav_postannoun_to_nav_announcements,bundle);
                }
                else if (! validValues()) {
                    Toast.makeText(requireContext(), "Please fill in all required fields",
                            Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(requireContext(), "Occasion should be within 1 year",
                            Toast.LENGTH_SHORT).show();
                }



                //These inputs should be in the database
                /*
                title = titleInput.getText().toString();
                time = timeInput.getText().toString();
                location = locationInput.getText().toString();
                description = descriptionInput.getText().toString();

                announcementObj = new Announcement(title, time, location, description);

                dbModel.add(announcementObj, "announcement", title+location);



                NavHostFragment.findNavController(PostAnnounFragment.this)
                        .navigate(R.id.action_nav_postannoun_to_nav_announcements);

                 */
            }
        });


        //announcements.add(announcementObj);
        //announcementAdapter = new AnnouncementAdapter(announcements, announcementsFragment);
        //announcementAdapter.setAnnouncements(announcements);
        //announcementAdapter.onBindViewHolder(announViewHolder, 0);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}