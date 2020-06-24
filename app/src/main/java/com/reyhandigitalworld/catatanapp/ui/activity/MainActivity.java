package com.reyhandigitalworld.catatanapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.reyhandigitalworld.catatanapp.R;
import com.reyhandigitalworld.catatanapp.room.AppDatabase;
import com.reyhandigitalworld.catatanapp.room.NoteEntity;
import com.reyhandigitalworld.catatanapp.ui.adapter.ListNoteAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ArrayList<NoteEntity> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rvNote = findViewById(R.id.rv_note);
        ListNoteAdapter adapter = new ListNoteAdapter(notes, this);

        AppDatabase database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbNote")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();

        notes.addAll(Arrays.asList(database.catatanDao().getAll()));

        rvNote.setHasFixedSize(true);
        rvNote.setLayoutManager(new LinearLayoutManager(this));
        rvNote.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CreateActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }
}
