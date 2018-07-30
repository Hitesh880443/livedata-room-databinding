package com.hitesh.livedata.mynotes.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.hitesh.livedata.mynotes.db.Note;
import com.hitesh.livedata.mynotes.db.NotesDatabase;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {


    private NotesDatabase mNotesDatabase;
    private final LiveData<List<Note>> noteList;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        mNotesDatabase = NotesDatabase.getDataBase(this.getApplication());
        noteList = mNotesDatabase.daoAccess().fetchAllNotes();
    }


    public LiveData<List<Note>> getNoteList() {
        return noteList;
    }

    public void deleteNote(final Note note) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mNotesDatabase.daoAccess().deleteNote(note);
            }
        }).start();
    }

    public boolean gotTheNoteList() {
        if (noteList != null && noteList.getValue() != null && noteList.getValue().size() > 0)
            return true;
        return false;
    }


}
