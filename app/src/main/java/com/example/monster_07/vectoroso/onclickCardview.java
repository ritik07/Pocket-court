package com.example.monster_07.vectoroso;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class onclickCardview extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onclick_cardview);
        Bundle bundle = getIntent().getExtras();
        final TextView tv = findViewById(R.id.idcaseno);
        if(bundle!=null&&bundle.containsKey("s")){
            String s = bundle.getString("s");
            Toast.makeText(this,s,Toast.LENGTH_LONG).show();


            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference("Data");

            databaseReference.child(s).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    tv.setText(dataSnapshot.child("title").getValue(String.class));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}
