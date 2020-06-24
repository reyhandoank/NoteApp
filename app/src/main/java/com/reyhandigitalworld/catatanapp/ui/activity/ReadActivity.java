package com.reyhandigitalworld.catatanapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.reyhandigitalworld.catatanapp.R;
import com.reyhandigitalworld.catatanapp.room.AppDatabase;
import com.reyhandigitalworld.catatanapp.room.NoteEntity;

public class ReadActivity extends AppCompatActivity {

    TextView tvTitle, tvNote;
    AppDatabase database;
    String title, note;
    NoteEntity notes = new NoteEntity();
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        init();

        id = getIntent().getIntExtra("id", 0);
        title = getIntent().getStringExtra("judul");
        note = getIntent().getStringExtra("note");

        tvTitle.setText(title);
        tvNote.setText(note);

        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbNote")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
    }

    private void init() {
        tvTitle = findViewById(R.id.tv_title);
        tvNote = findViewById(R.id.tv_note);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_choose, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {
            deleteNote();
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteNote() {
        database.catatanDao().deleteNote(id);
        Intent intent = new Intent(ReadActivity.this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
    }
}
