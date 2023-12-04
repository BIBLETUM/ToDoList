package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class AddNoteActivity extends AppCompatActivity {
    private NoteDataBase noteDataBase = NoteDataBase.getInstance(getApplication());
    private EditText textViewNoteText;
    private RadioButton radioButtonLow;
    private RadioButton radioButtonMedium;
    private RadioButton radioButtonHigh;
    private Button buttonSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initViews();


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

    }

    public void initViews() {
        textViewNoteText = findViewById(R.id.textViewNoteText);
        radioButtonLow = findViewById(R.id.radioButtonLow);
        radioButtonMedium = findViewById(R.id.radioButtonMedium);
        radioButtonHigh = findViewById(R.id.radioButtonHigh);
        buttonSave = findViewById(R.id.buttonSave);
    }

    private void saveNote() {
        String text = textViewNoteText.getText().toString().trim();
        if (text.isEmpty()) {
            Toast.makeText(
                    this,
                    "Введите текст заметки!",
                    Toast.LENGTH_SHORT
            ).show();
        } else {
            int priority = getPriority();
            int id = noteDataBase.notesDao().getNotes().size();
            Note note = new Note(id, priority, text);
            noteDataBase.notesDao().add(note);

            finish();
        }
    }

    private int getPriority() {
        if (radioButtonLow.isChecked()) {
            return 0;
        } else if (radioButtonMedium.isChecked()) {
            return 1;
        } else {
            return 2;
        }
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, AddNoteActivity.class);
        return intent;
    }

}