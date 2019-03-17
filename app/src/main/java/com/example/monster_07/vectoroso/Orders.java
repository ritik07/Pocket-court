package com.example.monster_07.vectoroso;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Orders extends AppCompatActivity {

    Handler handler;
    Runnable runnable;
    TextView Days,Hrs,Min,Sec;
    FirebaseDatabase database;
    DatabaseReference round;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        database = FirebaseDatabase.getInstance();
      //  users = database.getReference("Users");
        round = database.getReference("Timer");
        LinearLayout linearLayout = findViewById(R.id.counter);
        Days = linearLayout.findViewById(R.id.tv_days_num);
        Hrs = linearLayout.findViewById(R.id.tv_hrs_num);
        Min = linearLayout.findViewById(R.id.tv_min_num);
        Sec = linearLayout.findViewById(R.id.tv_sec_num);
        //DatabaseReference round = database.getReference("start");
        round.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s =dataSnapshot.child("start").getValue(String.class);
                countDownStart(s);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    public void countDownStart(final String startTime){//,final String endTime) {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss", Locale.getDefault());
                    Date startDate = simpleDateFormat.parse(startTime);
                    //Date endDate = simpleDateFormat.parse(endTime);
                    Date currentDate = new Date();
                    if (!currentDate.after(startDate)) {
                        long diff = startDate.getTime() - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        String days_ = String.format(Locale.getDefault(), "%02d", days);
                        String hours_ = String.format(Locale.getDefault(), "%02d", hours);
                        String minutes_ = String.format(Locale.getDefault(), "%02d", minutes);
                        String seconds_ = String.format(Locale.getDefault(), "%02d", seconds);
                        Days.setText(days_);
                        Hrs.setText(hours_);
                        Min.setText(minutes_);
                        Sec.setText(seconds_);


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        handler.postDelayed(runnable, 1 * 1000);
    }

}
