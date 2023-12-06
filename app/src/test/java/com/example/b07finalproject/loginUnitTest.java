package com.example.b07finalproject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.b07finalproject.ui.login.Admin;
import com.example.b07finalproject.ui.login.Student;
import com.example.b07finalproject.ui.login.loginFragment;
import com.example.b07finalproject.ui.login.loginModel;
import com.example.b07finalproject.ui.login.loginPresenter;

import org.junit.runner.RunWith;

@RunWith(MockitoJUnitRunner.class)
public class loginUnitTest {

    @Mock
    loginFragment view;
    @Mock
    loginModel model;

    @Test
    public void loginFormatTest(){
        loginPresenter presenter = new loginPresenter(model, view);
        boolean loginValid = presenter.isLoginValid("    ", "");
        InOrder order = Mockito.inOrder(view, view);
        order.verify(view).setUsernameError(R.string.empty_name);
        order.verify(view).setPasswordError(R.string.empty_password);
        assertFalse(loginValid);
    }

    @Test
    public void signUpTest(){
        loginPresenter presenter = new loginPresenter(model, view);
        presenter.beginSignUp("John", "123", false);
        //doNothing().when(model).checkUser(presenter, "John", "123", false);
        verify(model).checkUser(presenter, "John", "123", false);
    }

    @Test
    public void beginLoginTest(){
        loginPresenter presenter = new loginPresenter(model, view);
        presenter.beginLogin("John", "123");
       // doNothing().when(model).queryDB(presenter, "John", "123");
        verify(model).queryDB(presenter, "John", "123");
    }

    @Test
    public void finishLoginTest(){
        Admin testAdmin = new Admin("Paul", "456");
        //doNothing().when(view).displayLogin(testAdmin);
        loginPresenter presenter = new loginPresenter(model, view);
        presenter.login(testAdmin);
        verify(view).displayLogin(testAdmin);
    }
}
