package com.example.monster_07.vectoroso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

 public class order extends AppCompatActivity {

    private DatabaseReference manotherdatabase;
    private EditText mname,memail,mdescription;
    private Button mbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

       // mbtn = (Button) findViewById(R.id.submit_btn_order);


        //mname = (EditText) findViewById(R.id.name_order);
     /*   memail = (EditText) findViewById(R.id.email_order);*/
        //mdescription = (EditText) findViewById(R.id.details_order);




        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = mname.getText().toString().trim();
           /*        String email = memail.getText().toString().trim();*/
                String descp = mdescription.getText().toString().trim();

                manotherdatabase.push().setValue(name);
              /*  manotherdatabase.push().setValue(email);*/
                manotherdatabase.push().setValue(descp);


            }
        });


    }
}
