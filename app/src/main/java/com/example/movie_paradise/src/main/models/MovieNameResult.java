package com.example.movie_paradise.src.main.models;

import com.google.gson.annotations.SerializedName;


public class MovieNameResult {

    String movieName;

    public MovieNameResult(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieName() {
        return movieName;
    }
}