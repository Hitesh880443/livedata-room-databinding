package com.hitesh.livedata.mynotes.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.hitesh.livedata.mynotes.db.Note;
import com.hitesh.livedata.mynotes.db.NotesDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NoteViewModel extends AndroidViewModel {


    private NotesDatabase mNotesDatabase;
    private LiveData<List<Note>> noteList;
    private final Executor executor;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        mNotesDatabase = NotesDatabase.getDataBase(this.getApplication());
        executor = Executors.newSingleThreadExecutor();
    }


    public LiveData<List<Note>> getNoteList() {
        noteList = mNotesDatabase.daoAccess().fetchAllNotes();
        return noteList;
    }

    public void deleteNote(final Note note) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mNotesDatabase.runInTransaction(new Runnable() {
                    @Override
                    public void run() {
                        mNotesDatabase.daoAccess().deleteNote(note);
                    }
                });

            }
        });
    }

    public void insertSingleNote(final Note note) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mNotesDatabase.runInTransaction(new Runnable() {
                    @Override
                    public void run() {
                        mNotesDatabase.daoAccess().insertSingleNote(note);
                    }
                });

            }
        });
    }

    public boolean gotTheNoteList() {
        if (noteList != null && noteList.getValue() != null && noteList.getValue().size() > 0)
            return true;
        return false;
    }


}
