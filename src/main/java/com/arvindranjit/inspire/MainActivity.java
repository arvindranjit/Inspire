package com.arvindranjit.inspire;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
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

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerAdapter mAdapter;
    private List<Todo> todosList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private TextView nogoalsTextView;


    private DatabaseHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);


        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Inspire");

        collapsingToolbar.setExpandedTitleTextAppearance(R.style.AppBarExpanded);
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.AppBarCollapsed);

        coordinatorLayout = findViewById(R.id.coordinator_layout);
        recyclerView = findViewById(R.id.recyclerview);
        nogoalsTextView = findViewById(R.id.nogoalstextview);


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
        ViewCompat.setNestedScrollingEnabled(recyclerView, false);



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


                Todo t = todosList.get(position);

                if(t.getStatus()==0){
                    t.setStatus(1);
                } else {
                    t.setStatus(0);
                }

                db.updateTodo(t);


                todosList.removeAll(todosList);
                todosList.addAll(db.getAllNotes());
                mAdapter.notifyDataSetChanged();
                toggleEmptyGoals();
                //mAdapter.notifyItemChanged(position);


            }
        }));

    }







    @Override
    public void onResume(){
        super.onResume();

        todosList.removeAll(todosList);
        todosList.addAll(db.getAllNotes());
        mAdapter.notifyDataSetChanged();
        toggleEmptyGoals();

    }

    private void toggleEmptyGoals() {


        if (db.getNotesCount() > 0) {
            nogoalsTextView.setVisibility(View.GONE);
        } else {
            nogoalsTextView.setVisibility(View.VISIBLE);
        }
    }







}



