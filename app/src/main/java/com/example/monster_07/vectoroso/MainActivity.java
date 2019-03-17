package com.example.monster_07.vectoroso;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    RecyclerView mrecyclerview;
    FirebaseDatabase mfirebasedatabase;
    DatabaseReference mref;
    private FirebaseAuth.AuthStateListener mauthlistner;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Model,ViewHolder> adapter;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("POCKET COURT");





        mrecyclerview = findViewById(R.id.recyclerview);
        mrecyclerview.setHasFixedSize(true);

        mrecyclerview.setLayoutManager(new GridLayoutManager(this,3));

        mfirebasedatabase = FirebaseDatabase.getInstance();
        mref = mfirebasedatabase.getReference("Data");
        mref.keepSynced(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mrecyclerview = findViewById(R.id.recyclerview);
        mrecyclerview.setHasFixedSize(true);
        //layoutManager = new LinearLayoutManager(this);
        mrecyclerview.setLayoutManager(new GridLayoutManager(this,3));
        load();
    }

    private void load() {
        adapter = new FirebaseRecyclerAdapter<Model, ViewHolder>(
                Model.class,
                R.layout.cardview_item,
                ViewHolder.class,
                mref
        ) {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, final Model model, final int position) {
                viewHolder.tv.setText(model.getTitle());
                Picasso.get().load(model.getImage()).into(viewHolder.im);

                viewHolder.setItemClickListener_(new itemClickListener() {
                    @Override
                    public void onClick(View view, int pos, boolean isLongClick) {
                        Toast.makeText(MainActivity.this,String.format("%d|%s",adapter.getRef(position).getKey(),model.getTitle()),Toast.LENGTH_LONG).show();
                    }
                });
            }
        };
        adapter.notifyDataSetChanged();
        mrecyclerview.setAdapter(adapter);
    }
}
