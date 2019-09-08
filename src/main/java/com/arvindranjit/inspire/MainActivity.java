package com.arvindranjit.inspire;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerAdapter mAdapter;
    private List<Todo> todosList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private TextView noNotesView;

    private DatabaseHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        coordinatorLayout = findViewById(R.id.coordinator_layout);
        recyclerView = findViewById(R.id.recyclerview);
        noNotesView = findViewById(R.id.empty_notes_view);


        db = new DatabaseHelper(this);

        todosList.addAll(db.getAllNotes());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(MainActivity.this, NoteDetails.class);
                MainActivity.this.startActivity(newIntent);


            }
        });

        mAdapter = new RecyclerAdapter(this, todosList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);




        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                Intent myIntent = new Intent(MainActivity.this, Todo_Edit.class);
                myIntent.putExtra("position", position);
                startActivity(myIntent);
            }

            @Override
            public void onLongClick(View view, int position) {

                db.deleteNote(todosList.get(position));


                todosList.remove(position);
                mAdapter.notifyItemRemoved(position);

            }
        }));

    }







    @Override
    public void onResume(){
        super.onResume();

        todosList.removeAll(todosList);
        todosList.addAll(db.getAllNotes());
        mAdapter.notifyDataSetChanged();

    }









}



