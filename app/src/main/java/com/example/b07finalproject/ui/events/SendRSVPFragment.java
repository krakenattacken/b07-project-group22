package com.example.b07finalproject.ui.events;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.time.format.DateTimeParseException;

import com.example.b07finalproject.R;
import com.example.b07finalproject.ui.postChecker.GradeException;
import com.example.b07finalproject.ui.postChecker.POStQuestionsFragment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;


public class SendRSVPFragment extends Fragment {

    private String title;
    private LocalDateTime time;
    private String location;
    private String description;
    private int capacity;

    public SendRSVPFragment() {
        // Required empty public constructor
    }

    public static SendRSVPFragment newInstance(String param1, String param2) {
        SendRSVPFragment fragment = new SendRSVPFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_send_rsvp, container, false);
    }

    private void reset() {
        title = null;
        time = null;
        location = null;
        description = null;
        capacity = 0;

    }

    private EditText newEditor(View view, int editTextID, String field, int filter) {
        EditText editText = view.findViewById(editTextID);
        // Want to limit the chars to 3
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
                            LocalDateTime localDateTime = LocalDateTime.parse(s.toString(), formatter);
                            time = localDateTime;
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
                    else if (field.equals("capacity")) {
                        capacity = Integer.parseInt(s.toString());
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
                description != null &&
                capacity != 0;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        reset();

        EditText titleText = newEditor(view, R.id.eventTitleEditText, "title", 20);

        EditText timeText = newEditor(view, R.id.eventTimeEditText, "time", 18);
        EditText locationText = newEditor(view, R.id.eventLocationEditText,
                "location", 30);
        EditText descriptionText = newEditor(view, R.id.eventDescriptionEditText,
                "description", 100);
        EditText capacityText = newEditor(view, R.id.eventCapacityEditText,
                "capacity", 3);

        //Add button
        Button button = view.findViewById(R.id.submitEventButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validValues()) {
                    Event event = new Event(title, time, location, description, capacity);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("event", event);
                    NavHostFragment.findNavController(SendRSVPFragment.this)
                            .navigate(R.id.action_send_rsvp_to_submitted, bundle);
                }
                else {
                    Toast.makeText(requireContext(), "Please fill in all required fields",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}