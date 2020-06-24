package com.reyhandigitalworld.catatanapp.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {NoteEntity.class}, version = 14, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDao catatanDao();
}
