package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NoteAdapter extends ArrayAdapter<Note> {

    int resource;

    public NoteAdapter(@NonNull Context context, int resource, List<Note> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Note note = getItem(position);

        if(convertView==null){
            LayoutInflater inflater= LayoutInflater.from(this.getContext());
            convertView = inflater.inflate(this.resource, parent, false);

        }

        TextView noteName = (TextView) convertView.findViewById(R.id.note_list_item_name);
        noteName.setText(note.getName());

        TextView noteModified = (TextView) convertView.findViewById(R.id.note_list_item_modified);
        Date modified_at = note.getModified_at();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.US);
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy    HH:mm", Locale.US);

        if(dateFormat.format(modified_at).equals(dateFormat.format(Calendar.getInstance().getTime())))
            noteModified.setText(timeFormat.format(modified_at));
        else
            noteModified.setText(dateTimeFormat.format(modified_at));

        return convertView;
    }
}
