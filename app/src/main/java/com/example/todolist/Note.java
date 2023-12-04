package com.example.todolist;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int priority;
    private String text;

    public Note(int id, int priority, String text) {
        this.id = id;
        this.priority = priority;
        this.text = text;
    }

    @Ignore
    public Note(int priority, String text) {
        this.priority = priority;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public String getText() {
        return text;
    }
}
