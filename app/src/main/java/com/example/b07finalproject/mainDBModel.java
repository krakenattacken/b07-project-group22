package com.example.b07finalproject;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.b07finalproject.ui.login.Student;
import com.example.b07finalproject.ui.login.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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
                Iterable<DataSnapshot> dbItems = snapshot.getChildren();
                int counter = 0;
                for (DataSnapshot i: dbItems) {
                    counter++;
                }
                Object[] items = new Object[counter];
                counter = 0;
                for (DataSnapshot shot: dbItems) {
                    items[counter] = shot.getValue(itemClass);
                    counter++;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                presenter.onDBFail("Something went wrong");
            }
        });
    }

    public void tryToAdd(Object item, String path, String id, DBDependent presenter){
        DatabaseReference ref = db.getReference().child(path);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
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

    public void startNotif(MainActivity main){
        DatabaseReference ref = db.getReference().child("notifications");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String notifType = snapshot.child("notifs").getValue(String.class);
                if (!snapshot.exists() ||"".equals(notifType)){
                    return;
                }
                if ("events".equals(notifType)){
                    main.showNotif(R.string.notif_event);
                }
                if ("announcements".equals(notifType) ||"announcement".equals(notifType)){
                    main.showNotif(R.string.notif_announce);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
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
