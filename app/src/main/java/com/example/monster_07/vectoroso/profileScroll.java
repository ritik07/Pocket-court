package com.example.monster_07.vectoroso;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profileScroll extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_scroll);

        LinearLayout profile1 = findViewById(R.id.profile1);
        TextView tv_name = profile1.findViewById(R.id.profile_name);
        TextView tv_designation = profile1.findViewById(R.id.profile_designation);
        final TextView tv_bio = profile1.findViewById(R.id.profile_bio);
        TextView tv_viewMore = profile1.findViewById(R.id.profile_viewMore);
        ImageView imageView = profile1.findViewById(R.id.profile_img);
        tv_bio.setText(common.advocateList.get(0).getRating());




        tv_viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_bio.getVisibility()==View.VISIBLE)
                    tv_bio.setVisibility(View.GONE);
                else
                    tv_bio.setVisibility(View.VISIBLE);
            }
        });
    }
}
