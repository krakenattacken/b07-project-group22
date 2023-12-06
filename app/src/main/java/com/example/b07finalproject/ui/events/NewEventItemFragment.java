package com.example.b07finalproject.ui.events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;
import com.example.b07finalproject.R;
import com.example.b07finalproject.mainDBModel;
import com.example.b07finalproject.ui.login.Admin;
import com.example.b07finalproject.ui.login.Student;
import com.example.b07finalproject.ui.login.User;


import com.example.b07finalproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewEventItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewEventItemFragment extends Fragment {


    private Event event;
    private User user;
    private boolean attendEvent;
    private mainDBModel dbModel;

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
        dbModel = new mainDBModel();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_item, container, false);
    }
  
    private TextView newTextView(View view, int textViewID) {
        TextView textView = view.findViewById(textViewID);

        return textView;
    }

    private void updateLayout() {
        TextView eventName = newTextView(getView(), R.id.item_event_title);
        TextView eventDescription = newTextView(getView(), R.id.item_event_description);
        TextView eventTime = newTextView(getView(), R.id.event_time_tv);
        TextView eventLocation = newTextView(getView(), R.id.event_location_tv);

        eventName.setText(event.getName());
        eventDescription.setText(event.getDescription());
        eventTime.setText(event.getTime());
        eventLocation.setText(event.getLocation());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            event = (Event) bundle.getSerializable("clicked_event");
            user = (User) bundle.getSerializable("user");
        }

        updateLayout();

        if (user instanceof Student) {
            Student student = (Student) user;
            LocalDateTime now = LocalDateTime.now();
            // ideally only show checkbox if event is after now but
            // if now is within 5 days after event then you can still receive RSVP
            if (! event.participating(student) &&
                    event.toDateTime().isAfter(now.minusDays(5))) {
                TextView rsvp = newTextView(getView(), R.id.student_rsvp);
                rsvp.setText("Are you interested in the event?");
                CheckBox rsvpCheck = view.findViewById(R.id.rsvp_response);
                rsvpCheck.setVisibility(View.VISIBLE);

                rsvpCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    if (isChecked) {
                        attendEvent = true;
                    }
                    else {
                        attendEvent = false;
                    }
                });

                Button button = view.findViewById(R.id.button);
                button.setText("Submit RSVP");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (attendEvent) {
                            //((Student) user).acceptRSVP(event);
                            if (event.hasSpace()) {
                                event.attendeesIncreased(student);
                                dbModel.add(event, "events", event.toString());
                            }
                            else {
                                Toast.makeText(getContext(), "Sorry we are full...",
                                        Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }

                        Bundle bundle = new Bundle();
                        bundle.putBoolean("status", attendEvent);
                        NavHostFragment.findNavController(NewEventItemFragment.this)
                                .navigate(R.id.action_eventitem_to_rsvp_received, bundle);
                    }
                });
            }
            else if (event.participating(student)){
                //Add button
                Button button = view.findViewById(R.id.button);
                button.setText("Send Feedback");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (LocalDateTime.now().compareTo(event.toDateTime()) < 0) {
                            Toast.makeText(getContext(),
                                    "Please wait until the event is done!",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("event", event);
                            bundle.putSerializable("user", user);

                            NavHostFragment.findNavController(NewEventItemFragment.this)
                                    .navigate(R.id.action_eventitem_to_feedback, bundle);
                        }

                    }
                });

            }
            else {
                Button button = view.findViewById(R.id.button);
                button.setText("Submission blocked");
            }
        }

        else if (user instanceof Admin) {
            Button button = view.findViewById(R.id.button);
            button.setText("View Feedback");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("event", event);
                    bundle.putSerializable("user", user);

                    NavHostFragment.findNavController(NewEventItemFragment.this)
                            .navigate(R.id.action_eventitem_to_admin_feedback, bundle);
                }
            });
        }

    }
}