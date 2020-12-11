package com.example.movie_paradise.src.main;

import com.example.movie_paradise.src.main.interfaces.MainActivityView;
import com.example.movie_paradise.src.main.interfaces.MainRetrofitInterface;
import com.example.movie_paradise.src.main.models.DefaultResponse;
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
