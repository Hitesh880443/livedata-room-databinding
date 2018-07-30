package com.hitesh.livedata.mynotes.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
public class Note {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int noteNo;
    @NonNull
    private String noteMsg;
    @TypeConverters(DateConverter.class)
    Date noteDate;


    public Note() {
    }

    @NonNull
    public int getNoteNo() {
        return noteNo;
    }

    public void setNoteNo(@NonNull int note_no) {
        this.noteNo = note_no;
    }

    @NonNull
    public String getNoteMsg() {
        return noteMsg;
    }

    public void setNoteMsg(@NonNull String note_msg) {
        this.noteMsg = note_msg;
    }

    @NonNull
    public Date getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(@NonNull Date note_date) {
        this.noteDate = note_date;
    }


    public String formatDate() {
        SimpleDateFormat spf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aaa");
        return spf.format(noteDate);
    }
}
