package com.example.movie_paradise.src.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class MovieNameResponse {

    @SerializedName("result")
    private List<MovieNameResult> movieNameResults;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public List<MovieNameResult> getMovieNameResults() {
        return movieNameResults;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}