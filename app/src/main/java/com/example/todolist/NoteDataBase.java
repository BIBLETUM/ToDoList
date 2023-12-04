package com.example.todolist;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDataBase extends RoomDatabase {
    private static String DB_NAME = "MyNotes.db";

    private static NoteDataBase instance = null;
    public static NoteDataBase getInstance(Application application){
        if (instance == null) {
            instance = Room.databaseBuilder(
                    application,
                    NoteDataBase.class,
                    DB_NAME
            ).allowMainThreadQueries()
             .build();
        }
        return instance;
    }

    public abstract NotesDao notesDao();
}
