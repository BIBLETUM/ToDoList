package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;


public class AddNoteActivity extends AppCompatActivity {
    private EditText textViewNoteText;
    private RadioButton radioButtonLow;
    private RadioButton radioButtonMedium;
    private AddNoteViewModel addNoteViewModel;
    private RadioButton radioButtonHigh;
    private Button buttonSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initViews();

        addNoteViewModel = new ViewModelProvider(this).get(AddNoteViewModel.class);

        addNoteViewModel.getShouldCloseScreen().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean shouldClose) {
                if(shouldClose)
                    finish();
            }
        });

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
            Note note = new Note(priority, text);
            addNoteViewModel.addNote(note);
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