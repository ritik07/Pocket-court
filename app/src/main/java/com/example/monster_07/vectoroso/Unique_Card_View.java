package com.example.monster_07.vectoroso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Unique_Card_View extends AppCompatActivity {


    private ImageView mimage;
    private DatabaseReference mdatabaseref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unique__card__view);


       mdatabaseref = FirebaseDatabase.getInstance().getReference().child("Data").child("01");

        mimage = (ImageView) findViewById(R.id.new_book_img);
        Picasso.get().load("image").into(mimage);

    }
}
