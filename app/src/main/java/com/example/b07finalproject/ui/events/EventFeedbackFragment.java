package com.example.b07finalproject.ui.events;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.b07finalproject.R;
import com.example.b07finalproject.ui.postChecker.GradeException;
import com.example.b07finalproject.ui.postChecker.POStQuestionsFragment;
import com.example.b07finalproject.ui.viewmodel.CategoryViewModel;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventFeedbackFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventFeedbackFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters
    private Event event;
    private String comment;
    private int rating;

    public EventFeedbackFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventFeedbackFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventFeedbackFragment newInstance(String param1, String param2) {
        EventFeedbackFragment fragment = new EventFeedbackFragment();
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
        View view = inflater.inflate(R.layout.fragment_event_feedback, container, false);
        //Add spinner
        Spinner spinner = view.findViewById(R.id.rating_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.rating_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        return view;
    }

    private EditText newEditor(View view, int editTextID) {
        EditText editText = view.findViewById(editTextID);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (! s.toString().isEmpty()) {
                    comment = s.toString();
                }
            }
        });

        return editText;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        Bundle receivedBundle = getArguments();
        if (receivedBundle != null) {
            Event event = (Event) receivedBundle.getSerializable("event");

            this.event = event;
            Log.d("FragmentCreate", "Event: " + event.getName());
        }

        // initialize edittext
        EditText commentEditor = newEditor(view, R.id.editTextComment);
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(100);
        commentEditor.setFilters(filters);

        //Add button
        Button button = view.findViewById(R.id.button_event_feedback);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create feedback
                EventFeedback eventFeedback = new EventFeedback(event, comment, rating);
                Toast.makeText(getContext(), event.getName() + "\n" + comment + "\n" + rating,
                        Toast.LENGTH_SHORT).show();

                //NavHostFragment.findNavController(EventFeedbackFragment.this)
                        //.navigate(R.id.action_post_questions_to_post_result);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int rating;
        int index = parent.getSelectedItemPosition();
        String[] ratings = getResources().getStringArray(R.array.rating_array);
        rating = Integer.parseInt(ratings[index]);

        this.rating = rating;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}