package com.example.b07finalproject.ui.postChecker;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.b07finalproject.R;
import com.example.b07finalproject.ui.viewmodel.CategoryViewModel;
import com.example.b07finalproject.ui.viewmodel.SatisfactionViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link POStResult#newInstance} factory method to
 * create an instance of this fragment.
 */
public class POStResult extends Fragment {

    private SatisfactionViewModel satisfactionViewModel;
    private LiveData<Boolean> receivedSatisfaction;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public POStResult() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment POStResult.
     */
    // TODO: Rename and change types and number of parameters
    public static POStResult newInstance(String param1, String param2) {
        POStResult fragment = new POStResult();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        satisfactionViewModel = new ViewModelProvider(requireActivity())
                .get(SatisfactionViewModel.class);
        receivedSatisfaction = satisfactionViewModel.getSatisfied();

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_result, container, false);
    }

    private void updateLayout(View view, boolean satisfied){
        Resources resources = getResources();
        TextView messageTextView = (TextView) view.findViewById(R.id.post_result);
        if(satisfied){
            messageTextView.setText(resources.getString(R.string.post_success_message));
        }
        else{
            messageTextView.setText(resources.getString(R.string.post_fail_message));
        }

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        receivedSatisfaction.observe(getViewLifecycleOwner(), satisfied -> {
            // Update the layout depending on the satisfaction
            updateLayout(view, satisfied);
        });
    }
}