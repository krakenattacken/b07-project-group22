package com.example.b07finalproject.ui.announcements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.Fragment;

import com.example.b07finalproject.R;
import com.example.b07finalproject.databinding.FragmentPostAnnounBinding;

import java.util.List;

public class PostAnnounFragment extends Fragment{

    private FragmentPostAnnounBinding binding;

    public static PostAnnounFragment newInstance() {
        return new PostAnnounFragment();
    }
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentPostAnnounBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Post Button in fragment_post_announ_xml should send notifications to observers (students) and take admin back to AnnouncementsFragment page
        //with the new posted announcement at the top
        binding.postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(PostAnnounFragment.this)
                        .navigate(R.id.action_nav_postannoun_to_nav_announcements);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}

