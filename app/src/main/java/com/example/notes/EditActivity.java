package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class EditActivity extends AppCompatActivity {

    Note note;
    int noteID;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Objects.requireNonNull(getSupportActionBar()).hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Get position sent by MainActivity
        Intent intent = getIntent();
        noteID = intent.getIntExtra("noteID", -1);

        EditText noteName = findViewById(R.id.noteNameView);
        EditText noteDescription = findViewById(R.id.noteDescriptionView);

        // Show details of note at given position
        if(noteID != -1) {
            note = MainActivity.notes.get(noteID);
            noteName.setText(note.getName());
            noteDescription.setText(note.getDescription());
        } else {
            note = new Note("");
        }

        FloatingActionButton updateNoteButton = findViewById(R.id.updateNoteButton);
        updateNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = noteName.getText().toString();
                String data = noteDescription.getText().toString();

                // Toast if empty Name
                if(name.equals("")) {
                    Toast.makeText(EditActivity.this, "Enter a Valid Name", Toast.LENGTH_SHORT).show();
                } else {
                    // Set members of note
                    note.setName(name);
                    note.setDescription(data);
                    if(noteID == -1) {
                        MainActivity.notes.add(note);
                    }

                    // Notify the adapter in MainActivity
                    MainActivity.adapter.notifyDataSetChanged();

                    // Save data Permanently
                    MainActivity.saveNotes();

                    // Finish Activity
                    finish();
                }
            }
        });

    }
}