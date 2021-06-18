package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class EditActivity extends AppCompatActivity {

    Note note;
    int noteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Objects.requireNonNull(getSupportActionBar()).hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Get position sent by MainActivity
        Intent intent = getIntent();
        noteID = intent.getIntExtra("noteID", -1);

        EditText noteName = ((EditText) findViewById(R.id.noteName));
        EditText noteDescription = ((EditText) findViewById(R.id.noteDescription));

        // Show details of note at given position
        if(noteID != -1) {
            note = (Note) MainActivity.notes.get(noteID);
            noteName.setText(note.getName());
            noteDescription.setText(note.getDescription());

        }

        Button applyButton = (Button) findViewById(R.id.applyButton);
        applyButton.setOnClickListener(new View.OnClickListener() {
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

                    // Notify the adapter in MainActivity
                    MainActivity.adapter.notifyDataSetChanged();

                    // Save data Permanently
//                    SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                    SharedPreferences.Editor editor = sharedPrefs.edit();
//                    Gson gson = new Gson();
//                    String json = gson.toJson(MainActivity.notes);
//                    editor.putString("notes", json);
//                    editor.apply();
                    MainActivity.saveNotes();

                    // Finish Activity
                    finish();
                }
            }
        });

    }
}