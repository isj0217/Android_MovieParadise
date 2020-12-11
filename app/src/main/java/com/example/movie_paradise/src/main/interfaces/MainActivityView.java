package com.example.movie_paradise.src.main.interfaces;

import com.example.movie_paradise.src.main.models.DefaultResponse;
import com.example.movie_paradise.src.main.models.SignInResponse;

public interface MainActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void signUpSuccess(DefaultResponse defaultResponse);

    void signInSuccess(SignInResponse signInResponse);
}