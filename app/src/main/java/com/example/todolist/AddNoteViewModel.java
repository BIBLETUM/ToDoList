package com.example.todolist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class AddNoteViewModel extends AndroidViewModel {
    private NoteDataBase noteDataBase;
    private MutableLiveData<Boolean> shouldCloseScreen = new MutableLiveData<Boolean>();
    public AddNoteViewModel(@NonNull Application application) {
        super(application);
        noteDataBase = NoteDataBase.getInstance(application);
    }

    public LiveData<Boolean> getShouldCloseScreen() {
        return shouldCloseScreen;
    }

    public void addNote(Note note){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                noteDataBase.notesDao().add(note);
                shouldCloseScreen.postValue(true);
            }
        });
        thread.start();
    }

}
