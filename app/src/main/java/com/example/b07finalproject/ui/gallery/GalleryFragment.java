package com.example.b07finalproject.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.b07finalproject.DBDependent;
import com.example.b07finalproject.R;
import com.example.b07finalproject.databinding.FragmentGalleryBinding;
import com.example.b07finalproject.mainViewModel;
import com.example.b07finalproject.ui.login.Student;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private mainViewModel viewModel;
    TextView text;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        viewModel = new ViewModelProvider(getActivity()).get(mainViewModel.class);
        return root;
    }

    public void loadDataFromDB(ArrayList<Object> items){
        //Student[] cItems = new Student[items.length];
        //for (int i = 0; i< items.length; i++){
        //    cItems[i] = (Student)items[i];
        //}
        text.setText("did it");

    }


    public void onDBFail(String reason){

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}