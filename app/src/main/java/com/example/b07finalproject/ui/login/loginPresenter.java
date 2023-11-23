package com.example.b07finalproject.ui.login;

import android.widget.Toast;

import androidx.navigation.fragment.NavHostFragment;

import com.example.b07finalproject.R;

public class loginPresenter {
    loginModel model;
    loginFragment view;

    public loginPresenter(loginModel model, loginFragment view){
        this.model = model;
        this.view = view;
    }

    public boolean isLoginValid(String username, String password) {
        int errorCount = 0;
        if (username.trim().isEmpty()){
            setUsernameError(R.string.empty_name);
            errorCount++;
        }
        if (password.isEmpty()){
            setPasswordError(R.string.empty_password);
            errorCount++;
        }
        return (errorCount == 0);
    }

    public void beginLogin(String username, String password){
        if (isLoginValid(username, password)) {
            model.queryDB(this, username, password);
        }
    }

    public void beginSignUp(String username, String password, Boolean isAdmin){
        if (isLoginValid(username, password)) {
            model.checkUser(this, username, password, isAdmin);
        }
    }
    public void setUsernameError(int messageID){
        view.usernameInput.setError(view.getResources().getString(messageID));
    }
    public void setPasswordError(int messageID){
        view.passwordInput.setError(view.getResources().getString(messageID));
    }

    public void login(User user){
        //something here to set stuff to the new user
        Toast.makeText(view.getContext(), view.getResources().getString(R.string.welcome_user)
                        + " " + user + "!", Toast.LENGTH_SHORT).show();
        NavHostFragment.findNavController(view).navigate(R.id.nav_home);
    }
}
