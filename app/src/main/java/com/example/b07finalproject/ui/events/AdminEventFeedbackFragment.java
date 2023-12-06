package com.example.b07finalproject.ui.events;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

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

import com.example.b07finalproject.DBDependent;
import com.example.b07finalproject.R;
import com.example.b07finalproject.mainDBModel;
import com.example.b07finalproject.ui.DBInterface;

import org.checkerframework.checker.units.qual.A;
import org.w3c.dom.Text;

import java.time.LocalDateTime;

import com.example.b07finalproject.ui.login.Admin;
import com.example.b07finalproject.ui.login.Student;
import com.example.b07finalproject.ui.login.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminEventFeedbackFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminEventFeedbackFragment extends Fragment implements DBDependent {

    private Event event;
    private User user;
    ArrayList<EventFeedback> feedbacksList;
    private mainDBModel dbModel;

    public AdminEventFeedbackFragment() {
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
        dbModel = new mainDBModel();
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

        dbModel.getAllFromDB("eventFeedbacks", this, EventFeedback.class);
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

    @Override
    public void loadDataFromDB(List<Object> items) {

        if (items.size() == 0) {
            Toast.makeText(getContext(), "none", Toast.LENGTH_SHORT).show();
        }

        for (int i = 0; i < items.size(); i++) {
            EventFeedback feedback = (EventFeedback) items.get(i);
            if (feedback != null) {
                feedbacksList.add(feedback);
                Toast.makeText(getContext(), "found", Toast.LENGTH_SHORT).show();
            }
        }

        updateLayout();
    }

    @Override
    public void onDBFail(String reason) {

    }
}