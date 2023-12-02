package com.example.b07finalproject.ui.postChecker;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b07finalproject.R;
import com.example.b07finalproject.ui.viewmodel.CategoryViewModel;
import com.example.b07finalproject.ui.viewmodel.SatisfactionViewModel;
import com.example.b07finalproject.ui.viewmodel.SatisfactionViewModel;

import java.util.ArrayList;
import java.util.Arrays;


public class POStQuestionsFragment extends Fragment {

    // ViewModel to communicate data
    private CategoryViewModel categoryViewModel;
    private LiveData<String> receivedCategory;
    String category;
    int[] grades;
    String[] courses;
    double credit;



    public POStQuestionsFragment() {
        // Required empty public constructor
        grades = new int[6];
        courses = new String[6];

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // instantiate ViewModel
        categoryViewModel = new ViewModelProvider(requireActivity()).get(CategoryViewModel.class);
        receivedCategory = categoryViewModel.getSelectedCategory();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_questions, container, false);
    }

    private void updateLayout(@NonNull View view, String category) {
        // Update the layout depending on the category
        Resources resources = getResources();
        TextView titleTextView = (TextView) view.findViewById(R.id.selected_title);

        // extract string from resources
        String csString = resources.getString(R.string.computer_science);
        String mathString = resources.getString(R.string.math);
        String statsString = resources.getString(R.string.statistics);

        // want to make it as reusable
        if (category.equals(csString)) {
            // change the question to cs
            this.category = csString;
            titleTextView.setText("Post requirements for " + csString);
        }
        else if (category.equals(mathString)) {
            // change the question to math
            this.category = mathString;
            titleTextView.setText("Post requirements for " + mathString);
        }
        else if (category.equals(statsString)) {
            // change the question to stats
            this.category = statsString;
            titleTextView.setText("Post requirements for " + statsString);
        }
    }

    private Spinner newSpinner(View view, int spinnerID, int arrayID, int idx) {
        Spinner spinner = view.findViewById(spinnerID);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(), arrayID, android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                courses[idx] = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        return spinner;
    }

    private EditText newEditor(View view, int editTextID, int idx) {
        EditText editText = view.findViewById(editTextID);
        // Want to limit the chars to 3
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(3);
        editText.setFilters(filters);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (! s.toString().isEmpty()) {
                    grades[idx] = Integer.parseInt(s.toString());
                    if (Integer.parseInt(s.toString()) > 100) {
                        editText.setError("Type value between 0 to 100");
                    }
                }
            }
        });

        return editText;
    }

    private CheckBox newCheckBox(View view, int checkBoxID) {
        CheckBox checkBox = view.findViewById(checkBoxID);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    credit = 4.0;
                }
                else {
                    credit = 0;
                }
            }
        });

        return checkBox;
    }

    // get result; either qualified or not for POSt
    private boolean[] getResult(){
        Resources resources = getResources();

        // extract string from resources
        String csString = resources.getString(R.string.computer_science);
        String mathString = resources.getString(R.string.math);
        String statsString = resources.getString(R.string.statistics);

        Grades myGrades = new Grades(courses, grades, credit);
        boolean qualifyMinor = false;
        boolean qualifyMajor = false;
        boolean qualifySpecialist = false;
        boolean qualifyMachine = false;
        ArrayList<Boolean> qualifyPOSt = new ArrayList<>();

        if (category.equals(csString)) {
            CSPOSt csPOSt = new CSPOSt(myGrades);
            qualifyMinor = csPOSt.qualifiedForMinor();
            qualifyMajor = csPOSt.qualifiedForMajor();
            qualifySpecialist = csPOSt.qualifiedForSpecialist();
        }

        else if (category.equals(mathString)) {
            MathPOSt mathPOSt = new MathPOSt(myGrades);
            qualifyMinor = mathPOSt.qualifiedForMinor();
            qualifyMajor = mathPOSt.qualifiedForMajor();
            qualifySpecialist = mathPOSt.qualifiedForSpecialist();
        }

        else if (category.equals(statsString)) {
            StatsPOSt statsPOSt = new StatsPOSt(myGrades);
            qualifyMinor = statsPOSt.qualifiedForMinor();
            qualifyMajor = statsPOSt.qualifiedForMajor();
            qualifySpecialist = statsPOSt.qualifiedForSpecialist();
            qualifyMachine = statsPOSt.qualifiedForMachine();
        }
        qualifyPOSt.add(qualifyMinor);
        qualifyPOSt.add(qualifyMajor);
        qualifyPOSt.add(qualifySpecialist);
        qualifyPOSt.add(qualifyMachine);

        boolean[] qualifyPOStArray = new boolean[qualifyPOSt.size()];
        for (int i = 0; i < qualifyPOStArray.length; i++) {
            qualifyPOStArray[i] = qualifyPOSt.get(i);
        }

        return qualifyPOStArray;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        receivedCategory.observe(getViewLifecycleOwner(), category -> {
            // Update the layout depending on the category
            updateLayout(view, category);
        });

        // initialize spinner
        Spinner a08Spinner = newSpinner(view, R.id.a08_spinner, R.array.a08_array, 0);
        Spinner a48Spinner = newSpinner(view, R.id.a48_spinner, R.array.a48_array, 1);
        Spinner a67Spinner = newSpinner(view, R.id.a67_spinner, R.array.a67_array, 2);
        Spinner a22Spinner = newSpinner(view, R.id.a22_spinner, R.array.a22_array, 3);
        Spinner a31Spinner = newSpinner(view, R.id.a31_spinner, R.array.a31_array, 4);
        Spinner a37Spinner = newSpinner(view, R.id.a37_spinner, R.array.a37_array, 5);
        // initialize edittext
        EditText a08Editor = newEditor(view, R.id.a08_score, 0);
        EditText a48Editor = newEditor(view, R.id.a48_score, 1);
        EditText a67Editor = newEditor(view, R.id.a67_score, 2);
        EditText a22Editor = newEditor(view, R.id.a22_score, 3);
        EditText a31Editor = newEditor(view, R.id.a31_score, 4);
        EditText a37Editor = newEditor(view, R.id.a37_score, 5);
        // initialize checkbox
        CheckBox creditCheckBox = newCheckBox(view, R.id.credit_checkbox);


        //Add button
        Button button = view.findViewById(R.id.button_to_result);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // don't want fragment to proceed if values are more than 100
                try{
                    boolean[] qualifyPOSt = getResult();
                    Bundle bundle = new Bundle();
                    bundle.putString("category_string", category);
                    bundle.putBooleanArray("result_array", qualifyPOSt);
                    Log.d("ButtonClick", "qualifyPOSt: " + Arrays.toString(qualifyPOSt));
                    NavHostFragment.findNavController(POStQuestionsFragment.this)
                            .navigate(R.id.action_post_questions_to_post_result, bundle);
                }
                catch (GradeException e){
                    Toast.makeText(getContext(), "Cannot proceed unless valid input given",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}