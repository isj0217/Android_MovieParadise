package com.example.movie_paradise.src.main.interfaces;

import com.example.movie_paradise.src.main.models.AccountTypeResponse;
import com.example.movie_paradise.src.main.models.DefaultResponse;
import com.example.movie_paradise.src.main.models.MovieNameResponse;
import com.example.movie_paradise.src.main.models.SignInResponse;

public interface MainActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void signUpSuccess(DefaultResponse defaultResponse);

    void signInSuccess(SignInResponse signInResponse);

    void getCurrentlyHeldSuccess(MovieNameResponse movieNameResponse);

    void getMovieQueueSuccess(MovieNameResponse movieNameResponse);

    void getAccountTypeSuccess(AccountTypeResponse accountTypeResponse);

    void getAvailableMoviesSuccess(MovieNameResponse movieNameResponse);

    void searchMovieByMovieTitleSuccess(MovieNameResponse movieNameResponse);

    void searchMovieByActorNameSuccess(MovieNameResponse movieNameResponse);
}