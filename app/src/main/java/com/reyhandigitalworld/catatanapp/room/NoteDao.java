package com.reyhandigitalworld.catatanapp.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addNote(NoteEntity note);

    @Query("SELECT * FROM tNote")
    NoteEntity[] getAll();

    @Update
    int updateNote(NoteEntity note);

    @Query("DELETE FROM tNote WHERE id = :id")
    void deleteNote(int id);
}
