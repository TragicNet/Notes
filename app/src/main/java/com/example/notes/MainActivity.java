package com.example.notes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // abc

    static ArrayList<Note> notes;
    static NoteAdapter adapter;

    static WeakReference<Context> weakAppContext;

    public static void saveNotes() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(weakAppContext.get());
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(MainActivity.notes);
        editor.putString("notes", json);
        editor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView notesList = findViewById(R.id.notesListView);

        weakAppContext = new WeakReference<>(getApplicationContext());
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = sharedPrefs.getString("notes", "");
        Type type = new TypeToken<ArrayList<Note>>() {}.getType();
        notes = gson.fromJson(json, type);

        if(notes == null) {
            notes = new ArrayList<>();
            notes.add(new Note("New Note"));
        }

        adapter = new NoteAdapter(this, R.layout.note_list_item, notes);
        notesList.setAdapter(adapter);

        notesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                intent.putExtra("noteID", position);
                startActivity(intent);
            }
        });

        notesList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Delete?")
                        .setMessage("Are you sure you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                notes.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        })

                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });

        FloatingActionButton createNoteButton = findViewById(R.id.createNoteButton);
        createNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                startActivity(intent);
            }
        });

    }
}