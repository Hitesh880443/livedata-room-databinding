package com.hitesh.livedata.mynotes;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {


    private static NotesDatabase INSTANCE;

    public static NotesDatabase getDataBase(Context mContext) {

        if (INSTANCE == null)
            INSTANCE = Room.databaseBuilder(mContext.getApplicationContext(),
                    NotesDatabase.class,
                    Constatnts.DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();

        return INSTANCE;
    }


    public abstract DaoAccess daoAccess();

}
