package com.example.b07finalproject.ui.postChecker;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.b07finalproject.R;

import java.util.Arrays;


public class POStResult extends Fragment {

    private String category;
    private boolean[] results;

    public POStResult() {
        results = new boolean[4];
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_result, container, false);
    }

    private void updateLayout(View view){
        Resources resources = getResources();
        TextView minorTextView = (TextView) view.findViewById(R.id.minor_result);
        TextView majorTextView = (TextView) view.findViewById(R.id.major_result);
        TextView specialistTextView = (TextView) view.findViewById(R.id.specialist_result);
        String csString = resources.getString(R.string.computer_science);
        String mathString = resources.getString(R.string.math);
        String statsString = resources.getString(R.string.statistics);

        if (category.equals(csString)) {
            if (results[0]) {
                minorTextView.setText(resources.getString(R.string.cs_minor_success_message));
            }
            else {
                minorTextView.setText(resources.getString(R.string.cs_minor_fail_message));
            }

            if (results[1]) {
                majorTextView.setText(resources.getString(R.string.cs_major_success_message));
            }
            else {
                majorTextView.setText(resources.getString(R.string.cs_major_fail_message));
            }

            if (results[2]) {
                specialistTextView.setText(resources.getString(R.string.cs_specialist_success_message));
            }
            else {
                specialistTextView.setText(resources.getString(R.string.cs_specialist_fail_message));
            }
        }

        else if (category.equals(mathString)) {
            if (results[0]) {
                minorTextView.setText(resources.getString(R.string.math_minor_success_message));
            }
            else {
                minorTextView.setText(resources.getString(R.string.math_minor_fail_message));
            }

            if (results[1]) {
                majorTextView.setText(resources.getString(R.string.math_major_success_message));
            }
            else {
                majorTextView.setText(resources.getString(R.string.math_major_fail_message));
            }

            if (results[2]) {
                specialistTextView.setText(resources.getString(R.string.math_specialist_success_message));
            }
            else {
                specialistTextView.setText(resources.getString(R.string.math_specialist_fail_message));
            }
        }

        else if (category.equals(statsString)) {
            TextView machineTextView = (TextView) view.findViewById(R.id.machine_result);

            if (results[0]) {
                minorTextView.setText(resources.getString(R.string.stats_minor_success_message));
            }
            else {
                minorTextView.setText(resources.getString(R.string.stats_minor_fail_message));
            }

            if (results[1]) {
                majorTextView.setText(resources.getString(R.string.stats_major_success_message));
            }
            else {
                majorTextView.setText(resources.getString(R.string.stats_major_fail_message));
            }

            if (results[2]) {
                specialistTextView.setText(resources.getString(R.string.stats_specialist_success_message));
            }
            else {
                specialistTextView.setText(resources.getString(R.string.stats_specialist_fail_message));
            }

            if (results[3]) {
                machineTextView.setText(resources.getString(R.string.
                        stats_machine_specialist_success_message));
            }
            else {
                machineTextView.setText(resources.getString(R.string.
                        stats_machine_specialist_fail_message));
            }
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        // want to change view depending on val received so in onViewCreated
        Bundle receivedBundle = getArguments();
        if (receivedBundle != null) {
            String category = receivedBundle.getString("category_string");
            boolean[] results = receivedBundle.getBooleanArray("result_array");

            this.category = category;
            this.results = results;
        }
        Log.d("FragmentCreate", "results: " + Arrays.toString(results));

        updateLayout(view);
    }
}