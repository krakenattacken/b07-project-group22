package com.example.b07finalproject.ui.complaint;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.b07finalproject.DBDependent;
import com.example.b07finalproject.R;
import com.example.b07finalproject.mainViewModel;

import java.util.List;

public class ComplaintFragment extends Fragment {

    private ComplaintViewModel mViewModel;

    private mainViewModel viewModel;

    public static ComplaintFragment newInstance() {
        return new ComplaintFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(mainViewModel.class);
        return inflater.inflate(R.layout.fragment_complaint, container, false);



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ComplaintViewModel.class);
        // TODO: Use the ViewModel
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText EdTxtHeading = (EditText) view.findViewById(R.id.editTextHeading);
        EditText EdTxtBody = (EditText) view.findViewById(R.id.editTextBody);

        view.findViewById(R.id.SubmitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String heading = EdTxtHeading.getText().toString();
                String body = EdTxtBody.getText().toString();
                if (heading.equals("") == false && body.equals("") == false) {
                    NavHostFragment.findNavController(ComplaintFragment.this)
                            .navigate(R.id.action_nav_complaint_to_nav_submission);
                    Complaint complaint = new Complaint(heading, body);
                    viewModel.addToDB(complaint, "complaints", complaint.generateUniqueID(), new DBDependent() {
                        @Override
                        public void loadDataFromDB(List<Object> items) {
                            //Toast.makeText(getActivity(), "Complaint submitted successfully!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onDBFail(String reason) {
                            //Toast.makeText(getActivity(), "Failed to submit complaint: " + reason, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast fillText = Toast.makeText(getActivity(), "Please fill in the required fields", Toast.LENGTH_SHORT);
                    fillText.show();
                }

            }
        }
        );

        view.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ComplaintFragment.this)
                        .navigate(R.id.action_nav_complaint_to_nav_home);
            }
        });

    }

}