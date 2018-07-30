package com.hitesh.livedata.mynotes.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

@Dao
@TypeConverters(DateConverter.class)
public interface DaoAccess {

    @Insert
    void insertSingleNote(Note note);

    @Query("SELECT * FROM  Note ORDER by noteNo DESC")
    LiveData<List<Note>> fetchAllNotes();

    @Delete
    void deleteNote(Note note);


}
