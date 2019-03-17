package com.example.monster_07.vectoroso;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class dosign_up extends AppCompatActivity {
    private FirebaseAuth mAuth;

    EditText etEmail;
    EditText etPass;
    EditText etDisplay;
    Button btnRegister;
    private DatabaseReference mdatabase;
    private ProgressDialog mpd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosign_up);
        mAuth = FirebaseAuth.getInstance();

        //androidfield
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        etDisplay = findViewById(R.id.etDisplay);

        btnRegister = findViewById(R.id.btnRegister);
        mpd = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String display = etDisplay.getText().toString().trim();
                String pass = etPass.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                if (!TextUtils.isEmpty(display)|| !TextUtils.isEmpty(pass)|| !TextUtils.isEmpty(email)){
                    mpd.setTitle("Regestring user");
                    mpd.setMessage("Please wait while we create your Account!");
                    mpd.setCanceledOnTouchOutside(false);
                    mpd.show();
                    register(display,pass,email);


                }



            }
        });


    }

    private void register(final String display, String pass, String email) {

        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = current_user.getUid();
                    mdatabase = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
                    HashMap<String, String> usermap = new HashMap<>();
                    usermap.put("name", display);


                    mdatabase.setValue(usermap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){

                                mpd.dismiss();
                                Intent intent = new Intent(dosign_up.this,nav.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            } else{
                                mpd.dismiss();
                                //mpd.hide();
                                Toast.makeText(dosign_up.this,"Cannot sign in. Please check the form and try again.", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }
                else{
                    mpd.dismiss();
                    //mpd.hide();
                    Toast.makeText(dosign_up.this,"Cannot sign in. Please check the form and try again.", Toast.LENGTH_LONG).show();

                }
            }
        });

            }
}
