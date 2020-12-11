package com.example.movie_paradise.src.main.models;

import com.google.gson.annotations.SerializedName;

public class SignInResponse {

    public class SignInResult {

        @SerializedName("accountNum")
        private int accountNum;

        public int getAccountNum() {
            return accountNum;
        }
    }

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    private SignInResult signInResult;


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public SignInResult getSignInResult() {
        return signInResult;
    }
}