package com.example.b07finalproject.ui.home;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.b07finalproject.R;
import com.example.b07finalproject.databinding.FragmentHomeBinding;
import com.example.b07finalproject.mainViewModel;
import com.example.b07finalproject.ui.login.Admin;
import com.example.b07finalproject.databinding.FragmentHomeBinding;
import com.example.b07finalproject.ui.login.Student;
import com.example.b07finalproject.ui.login.User;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private mainViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        viewModel = new ViewModelProvider(getActivity()).get(mainViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button complaintButton = view.findViewById(R.id.complaintButton);
        User currentUser = viewModel.getCurrentUser();
        if (currentUser != null)  {
            if (currentUser instanceof Student) {
                complaintButton.setText("Trouble? Let Us Know!");
            } else if (currentUser instanceof Admin) {
                complaintButton.setText("Complaints");
            }
        }


        complaintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUser != null)
                {
                    if (currentUser instanceof Student) {
                        NavHostFragment.findNavController(HomeFragment.this)
                                .navigate(R.id.action_nav_home_to_nav_complaint);

                    } else if (currentUser instanceof Admin) {
                        NavHostFragment.findNavController(HomeFragment.this)
                                .navigate(R.id.action_nav_home_to_nav_complaintlist);
                    }
                }

            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}