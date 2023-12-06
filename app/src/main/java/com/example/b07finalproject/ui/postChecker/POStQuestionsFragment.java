package com.example.b07finalproject.ui.postChecker;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.b07finalproject.R;
import com.example.b07finalproject.ui.viewmodel.CategoryViewModel;
import com.example.b07finalproject.ui.viewmodel.SatisfactionViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link POStQuestionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class POStQuestionsFragment extends Fragment {

    // ViewModel to communicate data
    private CategoryViewModel categoryViewModel;
    private SatisfactionViewModel satisfactionViewModel;
    private LiveData<String> receivedCategory;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public POStQuestionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment POStQuestionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static POStQuestionsFragment newInstance(String param1, String param2) {
        POStQuestionsFragment fragment = new POStQuestionsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // instantiate ViewModel
        categoryViewModel = new ViewModelProvider(requireActivity()).get(CategoryViewModel.class);
        receivedCategory = categoryViewModel.getSelectedCategory();
        satisfactionViewModel = new ViewModelProvider(requireActivity())
                .get(SatisfactionViewModel.class);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
        CheckBox checkBox1 = (CheckBox) view.findViewById(R.id.checkBox1);
        CheckBox checkBox2 = (CheckBox) view.findViewById(R.id.checkBox2);
        CheckBox checkBox3 = (CheckBox) view.findViewById(R.id.checkBox3);

        // extract string from resources
        String csString = resources.getString(R.string.computer_science);
        String mathString = resources.getString(R.string.math);
        String statsString = resources.getString(R.string.statistics);

        // want to make it as reusable
        if (category.equals(csString)) {
            // change the question to cs
            titleTextView.setText("Post requirements for " + csString);
            checkBox1.setText("Is your GPA above 2.5?");
            checkBox2.setText("Did you get a minimum grade of B (73-76%) in Introduction to Computer Science II?");
            checkBox3.setText("Did you get a minimum grade of C- (60-62%) in two of Discrete Mathematics, " +
                    "Linear Algebra I for Mathematical Sciences, and Calculus II for Mathematical Sciences?");
        }
        else if (category.equals(mathString)) {
            // change the question to math
            titleTextView.setText("Post requirements for " + mathString);
            checkBox1.setText("Is your GPA above 2.0?");
            checkBox2.setText("Did you get a minimum grade of B (73-76%) in one of Discrete Mathematics, " +
                    "Linear Algebra I for Mathematical Sciences, or Calculus II for Mathematical Sciences?");
            checkBox3.setVisibility(View.INVISIBLE);
            //checkbox3 is going to be true
            checkBox3.setChecked(true);
        }
        else if (category.equals(statsString)) {
            // change the question to stats
            titleTextView.setText("Post requirements for " + statsString);
            checkBox1.setText("Is your GPA above 2.3?");
            checkBox2.setVisibility(View.INVISIBLE);
            checkBox3.setVisibility(View.INVISIBLE);
            //checkbox2/3 is going to be true
            checkBox2.setChecked(true);
            checkBox3.setChecked(true);
        }
    }
    // if all boxes are checked then requirements satisfied
    private boolean reqSatisfied(ArrayList<CheckBox> checkBoxes){
        for(int i = 0; i < checkBoxes.size(); i++){
            if(! (checkBoxes.get(i).isChecked())){
                return false;
            }
        }
        return true;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        receivedCategory.observe(getViewLifecycleOwner(), category -> {
            // Update the layout depending on the category
            updateLayout(view, category);
        });


        // maybe making a checkbox structure is an option?
        CheckBox checkBox1 = (CheckBox) view.findViewById(R.id.checkBox1);
        CheckBox checkBox2 = (CheckBox) view.findViewById(R.id.checkBox2);
        CheckBox checkBox3 = (CheckBox) view.findViewById(R.id.checkBox3);
        // make a list so is more efficient
        ArrayList<CheckBox> checkBoxes = new ArrayList<CheckBox>();
        checkBoxes.add(checkBox1);
        checkBoxes.add(checkBox2);
        checkBoxes.add(checkBox3);

        // check if checkboxes are updated
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = (buttonView, isChecked) -> {
            if (isChecked) {
                // check if satisfied
                boolean satisfied = reqSatisfied(checkBoxes);
                // write into satisfaction model
                satisfactionViewModel = new ViewModelProvider(requireActivity()).get(SatisfactionViewModel.class);
                satisfactionViewModel.setSatisfied(satisfied);
            }
        };

        // listener for all checkboxes
        for (int i = 0; i < checkBoxes.size(); i++) {
            checkBoxes.get(i).setOnCheckedChangeListener(onCheckedChangeListener);
        }






        //Add button
        Button button = view.findViewById(R.id.button_to_result);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(POStQuestionsFragment.this).navigate(R.id.action_post_questions_to_post_result);
            }
        });
    }
}