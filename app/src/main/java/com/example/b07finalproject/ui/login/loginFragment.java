package com.example.b07finalproject.ui.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.b07finalproject.R;
import com.example.b07finalproject.mainViewModel;

public class loginFragment extends Fragment {

    EditText usernameInput;
    EditText passwordInput;
    loginPresenter presenter;
    Switch adminSwitch;
    private mainViewModel viewModel;
    public loginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View loginView = inflater.inflate(R.layout.fragment_login, container, false);
        usernameInput = loginView.findViewById(R.id.usernameText);
        passwordInput = loginView.findViewById(R.id.passwordText);
        adminSwitch = loginView.findViewById(R.id.admin_switch);
        presenter = new loginPresenter(new loginModel(), this);
        viewModel = new ViewModelProvider(getActivity()).get(mainViewModel.class);
        return loginView;
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.beginLogin(usernameInput.getText().toString(), passwordInput.getText().toString());
            }
        });
        view.findViewById(R.id.sign_up_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                presenter.beginSignUp(usernameInput.getText().toString(),
                        passwordInput.getText().toString(), adminSwitch.isChecked());
            }
        });
    }

    public void setUsernameError(int messageID){
        usernameInput.setError(getResources().getString(messageID));
    }

    public void setPasswordError(int messageID){
        passwordInput.setError(getResources().getString(messageID));
    }

    public void displayLogin(User user){
        Toast.makeText(getContext(), getResources().getString(R.string.welcome_user)
                + " " + user + "!", Toast.LENGTH_SHORT).show();
        NavHostFragment.findNavController(this).navigate(R.id.nav_home);
        viewModel.currentUser = user;
    }
}