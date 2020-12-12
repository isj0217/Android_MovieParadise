package com.example.movie_paradise.src.main;

import com.example.movie_paradise.src.main.interfaces.MainActivityView;
import com.example.movie_paradise.src.main.interfaces.MainRetrofitInterface;
import com.example.movie_paradise.src.main.models.AccountTypeResponse;
import com.example.movie_paradise.src.main.models.DefaultResponse;
import com.example.movie_paradise.src.main.models.MovieNameResponse;
import com.example.movie_paradise.src.main.models.SignInResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.movie_paradise.src.ApplicationClass.getRetrofit;

class MainService {
    private final MainActivityView mMainActivityView;
    private HashMap<String, Object> mParams;

    MainService(final MainActivityView mainActivityView) {
        this.mMainActivityView = mainActivityView;
    }

    public MainService(MainActivityView mMainActivityView, HashMap<String, Object> mParams) {
        this.mMainActivityView = mMainActivityView;
        this.mParams = mParams;
    }

    // 1. 회원가입
    void postSignUp() {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.signUp(mParams).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {

                    mMainActivityView.validateFailure(null);
                    return;
                }
                mMainActivityView.signUpSuccess(defaultResponse);

            }
            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);
            }
        });
    }

    // 2. 로그인
    void postSignIn() {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.signIn(mParams).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {

                final SignInResponse signInResponse = response.body();
                if (signInResponse == null) {

                    mMainActivityView.validateFailure(null);
                    return;
                }
                mMainActivityView.signInSuccess(signInResponse);

            }
            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);
            }
        });
    }

    // 3. 현재 빌린 상태인 영화 조회
    void getCurrentlyHeld(int accountNum) {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getCurrentlyHeld(accountNum).enqueue(new Callback<MovieNameResponse>() {
            @Override
            public void onResponse(Call<MovieNameResponse> call, Response<MovieNameResponse> response) {
                System.out.println("111");
                final MovieNameResponse movieNameResponse = response.body();
                if (movieNameResponse == null) {
                    System.out.println("222");

                    mMainActivityView.validateFailure(null);
                    return;
                }
                mMainActivityView.getCurrentlyHeldSuccess(movieNameResponse);
                System.out.println("333");

            }
            @Override
            public void onFailure(Call<MovieNameResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);
                System.out.println("444");

            }
        });
    }


    // 4. 무비큐 조회
    void getMovieQueue(int accountNum) {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getMovieQueue(accountNum).enqueue(new Callback<MovieNameResponse>() {
            @Override
            public void onResponse(Call<MovieNameResponse> call, Response<MovieNameResponse> response) {

                final MovieNameResponse movieNameResponse = response.body();
                if (movieNameResponse == null) {

                    mMainActivityView.validateFailure(null);
                    return;
                }
                mMainActivityView.getMovieQueueSuccess(movieNameResponse);


            }
            @Override
            public void onFailure(Call<MovieNameResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);

            }
        });
    }


    // 5. Account Type 조회
    void getAccountType(int accountNum) {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getAccountType(accountNum).enqueue(new Callback<AccountTypeResponse>() {
            @Override
            public void onResponse(Call<AccountTypeResponse> call, Response<AccountTypeResponse> response) {
                System.out.println("11");
                final AccountTypeResponse accountTypeResponse = response.body();
                if (accountTypeResponse == null) {
                    System.out.println("22");

                    mMainActivityView.validateFailure(null);
                    return;
                }
                mMainActivityView.getAccountTypeSuccess(accountTypeResponse);
                System.out.println("33");


            }
            @Override
            public void onFailure(Call<AccountTypeResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);
                System.out.println("44");

            }
        });
    }


    // 6. 장르별 대여 가능한 영화 조회
    void getAvailableMovies(String genre) {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getAvailableMovies(genre).enqueue(new Callback<MovieNameResponse>() {
            @Override
            public void onResponse(Call<MovieNameResponse> call, Response<MovieNameResponse> response) {
                final MovieNameResponse movieNameResponse = response.body();
                if (movieNameResponse == null) {

                    mMainActivityView.validateFailure(null);
                    return;
                }
                mMainActivityView.getAvailableMoviesSuccess(movieNameResponse);


            }
            @Override
            public void onFailure(Call<MovieNameResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);

            }
        });
    }


    // 7. 영화 제목으로 영화 검색
    void searchMovieByMovieTitle(String movie_title) {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.searchMovieByMovieTitle(movie_title).enqueue(new Callback<MovieNameResponse>() {
            @Override
            public void onResponse(Call<MovieNameResponse> call, Response<MovieNameResponse> response) {
                final MovieNameResponse movieNameResponse = response.body();
                if (movieNameResponse == null) {

                    mMainActivityView.validateFailure(null);
                    return;
                }
                mMainActivityView.searchMovieByMovieTitleSuccess(movieNameResponse);


            }
            @Override
            public void onFailure(Call<MovieNameResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);

            }
        });
    }


    // 8. 배우 이름으로 영화 검색
    void searchMovieByActorName(String actor_name) {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.searchMovieByActorName(actor_name).enqueue(new Callback<MovieNameResponse>() {
            @Override
            public void onResponse(Call<MovieNameResponse> call, Response<MovieNameResponse> response) {
                final MovieNameResponse movieNameResponse = response.body();
                if (movieNameResponse == null) {

                    mMainActivityView.validateFailure(null);
                    return;
                }
                mMainActivityView.searchMovieByActorNameSuccess(movieNameResponse);


            }
            @Override
            public void onFailure(Call<MovieNameResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);

            }
        });
    }



    // 추가 1. 내가 본 영화 검색
    void getWatchedMovies(int accountNum) {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getWatchedMovies(accountNum).enqueue(new Callback<MovieNameResponse>() {
            @Override
            public void onResponse(Call<MovieNameResponse> call, Response<MovieNameResponse> response) {

                final MovieNameResponse movieNameResponse = response.body();
                if (movieNameResponse == null) {

                    mMainActivityView.validateFailure(null);
                    return;
                }
                mMainActivityView.getWatchedMoviesSuccess(movieNameResponse);


            }
            @Override
            public void onFailure(Call<MovieNameResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);

            }
        });
    }







    // 이 아래는 테스트
    void getTest() {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mMainActivityView.validateFailure(null);
                    return;
                }

                mMainActivityView.validateSuccess(defaultResponse.getMessage());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);
            }
        });
    }
}
