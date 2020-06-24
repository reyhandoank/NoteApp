package com.reyhandigitalworld.catatanapp.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.reyhandigitalworld.catatanapp.R;
import com.reyhandigitalworld.catatanapp.room.AppDatabase;
import com.reyhandigitalworld.catatanapp.room.NoteEntity;

public class CreateActivity extends AppCompatActivity {

    AppDatabase database;
    EditText edtTitle, edtNote;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        init();
        btnSave.setOnClickListener(view -> {
            NoteEntity data = new NoteEntity();

            data.setTitle(edtTitle.getText().toString());
            data.setNote(edtNote.getText().toString());
            addData(data);

            edtTitle.setText("");
            edtNote.setText("");

            Intent intent = new Intent(CreateActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void init() {
        edtTitle = findViewById(R.id.edt_title);
        edtNote = findViewById(R.id.edt_note);
        btnSave = findViewById(R.id.btn_save);

        database = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbNote").fallbackToDestructiveMigration().build();
    }

    @SuppressLint("StaticFieldLeak")
    private void addData(final NoteEntity noteEntity) {
        new AsyncTask<Void, Void, Long>() {

            @Override
            protected Long doInBackground(Void... voids) {
                return database.catatanDao().addNote(noteEntity);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                Toast.makeText(CreateActivity.this, "Berhasil disimpan", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
}
