package com.example.b07finalproject.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.b07finalproject.R;

public class loginFragment extends Fragment {

    EditText usernameInput;
    EditText passwordInput;
    loginPresenter presenter;
    Switch adminSwitch;
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
}