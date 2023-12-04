package com.example.b07finalproject.ui.events;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b07finalproject.R;
import com.example.b07finalproject.ui.DBInterface;

import org.checkerframework.checker.units.qual.A;
import org.w3c.dom.Text;

import java.time.LocalDateTime;

import com.example.b07finalproject.ui.login.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.EventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminEventFeedbackFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminEventFeedbackFragment extends Fragment {

    FirebaseDatabase db;
    private Event event;
    private User user;
    ArrayList<EventFeedback> feedbacksList;

    public AdminEventFeedbackFragment() {
        // Required empty public constructor
        //db = FirebaseDatabase.getInstance("https://bo7app-default-rtdb.firebaseio.com/");
        feedbacksList = new ArrayList<EventFeedback>();
    }

    public static AdminEventFeedbackFragment newInstance(String param1, String param2) {
        AdminEventFeedbackFragment fragment = new AdminEventFeedbackFragment();
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
        return inflater.inflate(R.layout.fragment_admin_event_feedback, container, false);
    }

    private TextView newTextView(View view, int textViewID) {
        TextView textView = view.findViewById(textViewID);

        return textView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            event = (Event) bundle.getSerializable("event");
            user = (User) bundle.getSerializable("user");
        }

        //Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();
        //event = new Event("BDay", LocalDateTime.now(), "UTSC",
                //"Dog run", 100, 100);

        //Event event1 = new Event("BDay", LocalDateTime.now(), "UTSC",
                //"Dog run", 100, 100);
        //EventFeedback feedback1 = new EventFeedback(event1, "It was fun", 3);
        //Event event2 = new Event("BDay", LocalDateTime.now(), "UTSC",
                //"Dog run", 100, 100);
        //EventFeedback feedback2 = new EventFeedback(event2, "It was boring", 1);
        //Event event3 = new Event("BDay", LocalDateTime.now(), "UTSC",
                //"Dog run", 100, 100);
        //EventFeedback feedback3 = new EventFeedback(event3, "It was okay", 5);
        //Event event4 = new Event("Gym", LocalDateTime.now(), "UTSC",
                //"Dog run", 100, 100);
        //EventFeedback feedback4 = new EventFeedback(event4, "It was amazing", 5);

        updateLayout();

    }

    private void updateLayout() {
        EventFeedback[] feedbacks = feedbacksList.toArray(new EventFeedback[0]);
        EventStats stats = new EventStats(feedbacks);


        // initialize edittext
        TextView eventName = newTextView(getView(), R.id.eventNameTextview);

        eventName.setText(event.getName());


        TextView eventCommentsEditor = newTextView(getView(), R.id.eventComments);

        String[] comments = stats.getComments(event);
        StringBuilder allComments = new StringBuilder();

        for (String comment : comments) {
            allComments.append(comment).append("\n");
        }

        if (comments.length > 0) {
            allComments.setLength(allComments.length() - 1);
        }

        eventCommentsEditor.setText(allComments);

        TextView eventRatings = newTextView(getView(), R.id.eventRatings);
        double averageRating = stats.getAverage(event);
        eventRatings.setText(String.valueOf(averageRating));

        TextView eventCounts = newTextView(getView(), R.id.eventCounts);
        int countFeedbacks = stats.getCount(event);
        eventCounts.setText(String.valueOf(countFeedbacks));
    }
}