package com.example.erlangga.finalproject;

import com.orm.SugarRecord;

public class Favorite extends SugarRecord {
    int movieId;
    String note;

    public Favorite() {
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
