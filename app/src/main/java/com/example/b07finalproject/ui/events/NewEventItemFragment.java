package com.example.b07finalproject.ui.events;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.b07finalproject.R;
import com.example.b07finalproject.ui.postChecker.GradeException;
import com.example.b07finalproject.ui.postChecker.POStQuestionsFragment;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewEventItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewEventItemFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewEventItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewEventItemFragment newInstance(String param1, String param2) {
        NewEventItemFragment fragment = new NewEventItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);



        //Add button
        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event event = new Event("BDay", LocalDateTime.now(), "UTSC",
                        "Dog run", 100, 100);
                Bundle bundle = new Bundle();
                bundle.putSerializable("event", (Serializable) event);

                NavHostFragment.findNavController(NewEventItemFragment.this)
                        .navigate(R.id.action_eventitem_to_feedback, bundle);
            }
        });

        Button button2 = view.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event event = new Event("BDay", LocalDateTime.now(), "UTSC",
                        "Dog run", 100, 100);
                Bundle bundle = new Bundle();
                bundle.putSerializable("event", (Serializable) event);

                NavHostFragment.findNavController(NewEventItemFragment.this)
                        .navigate(R.id.action_eventitem_to_admin_feedback, bundle);
            }
        });
    }
}