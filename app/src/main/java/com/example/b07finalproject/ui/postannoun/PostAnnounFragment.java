package com.example.b07finalproject.ui.postannoun;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.b07finalproject.databinding.FragmentPostannounBinding;

public class PostAnnounFragment extends Fragment{

    private FragmentPostannounBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PostAnnounViewModel postAnnounViewModel;
        postAnnounViewModel = new ViewModelProvider(this).get(PostAnnounViewModel.class);

        binding = FragmentPostannounBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textPostAnnoun;
        postAnnounViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
