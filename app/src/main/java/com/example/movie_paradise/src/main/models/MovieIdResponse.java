package com.example.movie_paradise.src.main.models;

import com.google.gson.annotations.SerializedName;

public class MovieIdResponse {

    public class MovieIdResult {

        @SerializedName("movieID")
        private int movieID;

        public int getMovieID() {
            return movieID;
        }
    }

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    private MovieIdResult movieIdResult;


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public MovieIdResult getMovieIdResult() {
        return movieIdResult;
    }
}