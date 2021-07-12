package com.example.notes;

import androidx.annotation.NonNull;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class Note {
    private String name;
    private String description;
    private Date created_at;
    private Date modified_at;

    public Note(String name) {
        this.name = name;
        this.description = "";
        this.created_at = Calendar.getInstance().getTime();
        this.modified_at = this.created_at;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated_at() { return created_at; }

    public Date getModified_at() { return modified_at; }

    public void setModified_at(Date modified_at) { this.modified_at = modified_at; }

    public static Comparator<Note> comparatorCreatedAt = new Comparator<Note>() {
        @Override
        public int compare(Note o1, Note o2) {
            return o2.getCreated_at().compareTo(o1.getCreated_at());
        }
    };

    public static Comparator<Note> comparatorModifiedAt = new Comparator<Note>() {
        @Override
        public int compare(Note o1, Note o2) {
            return o2.getModified_at().compareTo(o1.getModified_at());
        }
    };

}
