package com.example.b07finalproject.ui.login;

import androidx.annotation.NonNull;

import com.example.b07finalproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginModel {
    FirebaseDatabase db;

    public loginModel(){
        db = FirebaseDatabase.getInstance("https://bo7app-default-rtdb.firebaseio.com");
    }

    public void queryDB(loginPresenter presenter, String username, String password){
        DatabaseReference ref = db.getReference();
        DatabaseReference query = ref.child("users").child(username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot userSnapshot = snapshot.child(password);
                if (userSnapshot.exists()){
                    if (snapshot.child("isAdmin").exists()){
                        presenter.login(userSnapshot.getValue(Admin.class));
                    }
                    else{
                        presenter.login(userSnapshot.getValue(Student.class));
                    }
                    return;
                }
                if (snapshot.exists()){
                    presenter.setPasswordError(R.string.password_incorrect);
                    return;
                }
                presenter.setUsernameError(R.string.username_incorrect);
                return;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void checkUser(loginPresenter presenter, String username, String password, boolean isAdmin) {
        DatabaseReference ref = db.getReference();
        DatabaseReference query = ref.child("users").child(username);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    presenter.setUsernameError(R.string.username_taken);
                    return;
                }
                addUser(presenter, username, password, isAdmin);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void addUser(loginPresenter presenter, String username, String password, boolean isAdmin){
        DatabaseReference ref = db.getReference();
        DatabaseReference newUserRef = ref.child("users").child(username);
        User newUser;

        if (isAdmin){
            newUserRef.child("isAdmin").setValue(true);
            newUser = new Admin(username, password);
        }
        else{
            newUser = new Student(username, password);
        }
        newUserRef.child(password).setValue(newUser);
        presenter.login(newUser);
    }
}
