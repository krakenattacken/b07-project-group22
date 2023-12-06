package com.example.b07finalproject;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.b07finalproject.ui.login.Student;
import com.example.b07finalproject.ui.login.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class mainDBModel {
    FirebaseDatabase db;

    public mainDBModel(){
        db = FirebaseDatabase.getInstance("https://bo7app-default-rtdb.firebaseio.com/");
    }

    public void updateUser(User user){
        DatabaseReference ref = db.getReference().child(user.getUsername()).child(user.getPassword());
        ref.setValue(user);
    }

    public void getAllFromDB(String path, DBDependent presenter, Class itemClass){
        DatabaseReference ref = db.getReference().child(path);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()){
                    presenter.onDBFail("Path is empty");
                    return;
                }
                List<Object> itemsList = new ArrayList<>();


                for (DataSnapshot shot : snapshot.getChildren()) {
                    Object item = shot.getValue(itemClass);
                    itemsList.add(item);
                }



                presenter.loadDataFromDB(itemsList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                presenter.onDBFail("Something went wrong");
            }
        });
    }

    public void tryToAdd(Object item, String path, String id, DBDependent presenter, mainViewModel viewModel){
        DatabaseReference ref = db.getReference().child(path);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(id).exists()){
                    presenter.onDBFail("ID is taken.");
                }
                else{
                    add(item, path, id);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                presenter.onDBFail("Something went wrong");
            }
        });
    }

    public void add(Object item, String path, String id){
        DatabaseReference ref = db.getReference().child(path).child(id);
        ref.setValue(item);
    }

    public void remove(String path, String id){
        DatabaseReference ref = db.getReference().child(path).child(id);
        ref.removeValue();
    }


}
