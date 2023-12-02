package com.example.b07finalproject.ui.postChecker;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.b07finalproject.R;
import com.example.b07finalproject.ui.viewmodel.CategoryViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link POStChecker#newInstance} factory method to
 * create an instance of this fragment.
 */
public class POStChecker extends Fragment implements AdapterView.OnItemSelectedListener{

    // ViewModel to communicate data
    private CategoryViewModel categoryViewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public POStChecker() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment POStChecker.
     */
    // TODO: Rename and change types and number of parameters
    public static POStChecker newInstance(String param1, String param2) {
        POStChecker fragment = new POStChecker();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post_checker, container, false);
        //Add spinner
        Spinner spinner = view.findViewById(R.id.category_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(), R.array.category_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Add button
        Button button = view.findViewById(R.id.button_to_questions);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(POStChecker.this).navigate(R.id.action_post_checker_to_post_questions);
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedCategory;
        int index = parent.getSelectedItemPosition();
        String[] categories = getResources().getStringArray(R.array.category_array);
        //Toast.makeText(getContext(), "Hello " + categories[index], Toast.LENGTH_SHORT).show();
        selectedCategory = categories[index];
        // create viewModel to share category selected
        categoryViewModel = new ViewModelProvider(requireActivity()).get(CategoryViewModel.class);
        categoryViewModel.setCategory(selectedCategory);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}