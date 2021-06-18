package com.example.notes;

import androidx.annotation.NonNull;

public class Note {
    private String name;
    private String description;

    public Note(String name) {
        this.name = name;
        this.description = "";
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
}
