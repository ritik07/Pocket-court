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

public class do_login extends AppCompatActivity {
    EditText mlogemail;
    EditText mlogpass;
    Button mloginbtn;
    private ProgressDialog mlogprogress;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_login);

        mAuth = FirebaseAuth.getInstance();
        mlogprogress = new ProgressDialog(this);
        mlogemail = findViewById(R.id.log_email);
        mlogpass = findViewById(R.id.log_pass);
        mloginbtn = findViewById(R.id.login_btn);

        mloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mlogemail.getText().toString().trim();
                String password = mlogpass.getText().toString().trim();

                if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){
                    mlogprogress.setTitle("Logging in");
                    mlogprogress.setMessage("Please wait while we cross check your details");
                    mlogprogress.setCanceledOnTouchOutside(false);
                    mlogprogress.show();

                    loginuser(email,password);
                }
            }
        });


    }

    private void loginuser(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    mlogprogress.dismiss();

                    Intent loginintent = new Intent(do_login.this,nav.class);
                    loginintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(loginintent);
                    finish();

                }  else {
                    mlogprogress.hide();
                    Toast.makeText(do_login.this,"Cannot Log in. Please check the form and try again.", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
    }

