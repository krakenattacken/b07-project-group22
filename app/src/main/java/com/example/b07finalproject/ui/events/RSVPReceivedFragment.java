package com.example.b07finalproject.ui.events;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.b07finalproject.R;
import com.example.b07finalproject.ui.login.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RSVPReceivedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RSVPReceivedFragment extends Fragment {

    private boolean status;
    public RSVPReceivedFragment() {
        // Required empty public constructor
    }

    public static RSVPReceivedFragment newInstance(String param1, String param2) {
        RSVPReceivedFragment fragment = new RSVPReceivedFragment();
        Bundle args = new Bundle();
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
        return inflater.inflate(R.layout.fragment_rsvp_received, container, false);
    }

    private TextView newTextView(View view, int textViewID) {
        TextView textView = view.findViewById(textViewID);

        return textView;
    }
    private void updateLayout() {
        TextView rsvpResult = newTextView(getView(), R.id.acceptance_status);

        if (status) {
            rsvpResult.setText("We are looking forward to seeing you at the event!");
        }
        else {
            rsvpResult.setText("You can still come back if you want to participate! ");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            status = bundle.getBoolean("status");
        }

        updateLayout();
    }
}