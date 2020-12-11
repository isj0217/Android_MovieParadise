package com.example.movie_paradise.src.main.models;

import com.google.gson.annotations.SerializedName;

public class AccountTypeResponse {

    public class AccountTypeResult {

        @SerializedName("accountType")
        private String accountType;

        public String getAccountType() {
            return accountType;
        }
    }

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    private AccountTypeResult accountTypeResult;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public AccountTypeResult getAccountTypeResult() {
        return accountTypeResult;
    }
}