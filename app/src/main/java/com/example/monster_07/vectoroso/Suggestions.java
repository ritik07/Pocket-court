package com.example.monster_07.vectoroso;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Suggestions extends AppCompatActivity {

    private Button msuggbtn;
    private EditText suggtxt;
    DatabaseReference dataref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);

        msuggbtn = (Button) findViewById(R.id.suggbtn);
        suggtxt =  (EditText) findViewById(R.id.sugtxt);
        dataref = FirebaseDatabase.getInstance().getReference().child("Suggestions");

        msuggbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String sugg = suggtxt.getText().toString().trim();

                Intent emailint = new Intent(Intent.ACTION_SEND);
                emailint.putExtra(Intent.EXTRA_EMAIL, new String[]{"prateek0987.ps@gmail.com"});
                emailint.putExtra(Intent.EXTRA_SUBJECT,"Suggestion@Vectoroso");
                emailint.putExtra(Intent.EXTRA_TEXT, sugg);

                emailint.setType("message/rfc822");
                startActivity(Intent.createChooser(emailint, "Email"));

                dataref.push().setValue(sugg);
            }
        });


    }
}
