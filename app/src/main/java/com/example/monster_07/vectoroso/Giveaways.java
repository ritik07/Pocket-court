package com.example.monster_07.vectoroso;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class Giveaways extends AppCompatActivity {
    private DatabaseReference mref;
    private ImageView mimgview;
    private ImageView rulesimg;
    public TextView mfirre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giveaways);
        String scheme = "http://instagram.com/_u/USER";
        String path = "https://instagram.com/USER";
        String nomPackageInfo ="com.instagram.android";
        mimgview = (ImageView) findViewById(R.id.giveaway_img);
        rulesimg = (ImageView) findViewById(R.id.giveaway_rules_img);
        mfirre = (TextView) findViewById(R.id.firedata);

        mref = FirebaseDatabase.getInstance().getReference().child("Giveaways");
        mimgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://drive.google.com/drive/folders/1Gr2ZsSeCZjCX_9xkGGz5n0PoGtICFYlG?usp=sharing"));
               //     intent.setPackage("com.instagram.android");
                    startActivity(intent);
                }
                catch (android.content.ActivityNotFoundException anfe)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/")));
                }

            }
        });
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                final String giveawayimg = dataSnapshot.child("giveaway_img").getValue().toString();
                final String giveawayrulesimg = dataSnapshot.child("giveaway_txt").getValue().toString();


                Picasso.get().load(giveawayimg).networkPolicy(NetworkPolicy.OFFLINE).into(mimgview, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get().load(giveawayimg).into(mimgview);

                    }
                });
                Picasso.get().load(giveawayrulesimg).networkPolicy(NetworkPolicy.OFFLINE).into(rulesimg, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get().load(giveawayrulesimg).into(rulesimg);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
