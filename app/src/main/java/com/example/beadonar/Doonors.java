package com.example.beadonar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Doonors extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AdminAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public ArrayList<AdminItem> bookitem;
    public DatabaseReference databaseReference;
    ProgressBar progressBar;
    FirebaseUser firebaseUser;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doonors);

        bookitem=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("Posts");


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdminAdapter(bookitem);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        auth=FirebaseAuth.getInstance();
        firebaseUser =auth.getCurrentUser();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        ReadWriteItemDetails post=dataSnapshot1.getValue(ReadWriteItemDetails.class);

                        Date dt = new Date();
                        SimpleDateFormat dateFormat;
                        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String date=""+dateFormat.format(dt);

                        bookitem.add(new AdminItem(post.name,post.hospitalAddress));
                    }

                }
//                progressBar.setVisibility(View.GONE);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem menuItem=menu.findItem(R.id.action_search);
        MenuItem logoutItem=menu.findItem(R.id.action_logout);



        logoutItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                FirebaseAuth.getInstance().signOut();
                Intent i=new Intent(getApplicationContext(),Login.class);
                startActivity(i);
                finish();
                return false;
            }
        });

        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<AdminItem> filteredbookitem=new ArrayList<>();
                for(AdminItem book:bookitem){
                    if(book.getItemName().toLowerCase().contains(s.toLowerCase(


                    ))){
                        filteredbookitem.add(book);
                    }

                }
                mAdapter = new AdminAdapter(filteredbookitem);
                mRecyclerView.setAdapter(mAdapter);


                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}