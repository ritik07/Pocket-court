package com.example.monster_07.vectoroso;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import static android.content.Intent.EXTRA_TEXT;

public class nav extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView mrecyclerview;
    private FirebaseAuth mAuth;
    FirebaseDatabase mfirebasedatabase;
    DatabaseReference mref;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Model,ViewHolder> adapter;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("POCKET COURT");

        mrecyclerview = findViewById(R.id.recyclerview);
        mrecyclerview.setHasFixedSize(true);

        mrecyclerview.setLayoutManager(new GridLayoutManager(this,3));

        mfirebasedatabase = FirebaseDatabase.getInstance();
        mref = mfirebasedatabase.getReference("Data");
        mref.keepSynced(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Your Suggestions are Always Welcome :)", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent suggestionint = new Intent(nav.this, Suggestions.class);
                startActivity(suggestionint);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("advocate");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    advocate advocate_ = postSnapshot.getValue(advocate.class);
                    common.advocateList.add(advocate_);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
                     //   model.getTitle();
                     //   adapter.getRef(position);
                   //     adapter.getRef(position).getKey();
                        String s =adapter.getRef(position).getKey();
                        Intent intent = new Intent(nav.this,onclickCardview.class);
                        intent.putExtra("s",s);
                        startActivity(intent);
                        //Toast.makeText(nav.this,(""+adapter.getRef(position).getKey()+""+model.getTitle()),Toast.LENGTH_LONG).show();
//                        Toast.makeText(nav.this,(""+adapter.getRef(position).getKey()+""+model.getTitle()),Toast.LENGTH_LONG).show();
                    }
                });
            }
        };
        adapter.notifyDataSetChanged();
        mrecyclerview.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    } */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_order) {
            Intent order = new Intent(nav.this, Orders.class);
            startActivity(order);
        } else if (id == R.id.nav_Social) {

            Intent social = new Intent(nav.this, Social.class);
            startActivity(social);

        } else if (id == R.id.nav_GiveAways) {

            Intent giveaway = new Intent(nav.this, Giveaways.class);
            startActivity(giveaway);

        } else if (id == R.id.nav_manage) {

            Intent aboutus = new Intent(nav.this, About_us.class);
            startActivity(aboutus);

        } else if (id == R.id.nav_share) {

            Intent newact = new Intent(nav.this, problem_share.class);
            startActivity(newact);
          /*  Intent shareint = new Intent(Intent.ACTION_SEND);
            shareint.setType("text/plain");
            String Sharelink = "https://drive.google.com/file/d/1gUuGgZ6bSM2XLclYz4NLxJxiYfzIxT9W/view?usp=drivesdk";
            shareint.putExtra(Intent.EXTRA_TEXT, Sharelink);
            startActivity(shareint.createChooser(shareint, "Share Using"));*/

        }
        else if (id == R.id.log_out) {

            FirebaseAuth.getInstance().signOut();
            sendtostart();
        }

        else if (id == R.id.nav_send) {

            Intent todev = new Intent(nav.this, profileScroll.class);
            startActivity(todev);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onStart() {

        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            sendtostart();
        }
      /*  FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model, ViewHolder>(
                        Model.class,
                        R.layout.cardview_item,
                        ViewHolder.class,
                        mref
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {
                      //  viewHolder.setDetails(getApplicationContext(), model.getTitle(), model.getImage());
                        //======================GETTING POSTION OF EVERY CARD SO THAT WE CAN OPEN DIFFERENT ACTIVITY AND LOAD DIFFERENT DATA CORROSPONDINGLY====//

                        final String card_id = getRef(position).getKey();



                        //==============SETONCLICKLISTNER================//
                       /* viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent ne = new Intent(nav.this, Unique_Card_View.class);
                                ne.putExtra("Data", card_id);
                                startActivity(ne);
                            }
                        });*/
/*
                    }

                };

        mrecyclerview.setAdapter(firebaseRecyclerAdapter);

 */



    }

    private void sendtostart() {
        Intent sa = new Intent(nav.this,Login.class);
        startActivity(sa);
        finish();
    }


}
