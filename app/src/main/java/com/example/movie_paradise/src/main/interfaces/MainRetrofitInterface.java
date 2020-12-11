package com.example.movie_paradise.src.main.interfaces;

import com.example.movie_paradise.src.main.models.AccountTypeResponse;
import com.example.movie_paradise.src.main.models.DefaultResponse;
import com.example.movie_paradise.src.main.models.MovieNameResponse;
import com.example.movie_paradise.src.main.models.SignInResponse;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainRetrofitInterface {

    // 1. 회원가입
    @POST("/user")
    Call<DefaultResponse> signUp(@Body HashMap<String, Object> params);

    // 2. 로그인
    @POST("/signin")
    Call<SignInResponse> signIn(@Body HashMap<String, Object> params);

    // 3. 현재 빌린 영화들 조회
    @GET("/currently_held/{account_num}")
    Call<MovieNameResponse> getCurrentlyHeld(@Path("account_num") int accountNum);

    // 4. 무비큐 조회
    @GET("/movie_queue/{account_num}")
    Call<MovieNameResponse> getMovieQueue(@Path("account_num") int accountNum);

    // 5. Account Type 조회
    @GET("/account_type/{account_num}")
    Call<AccountTypeResponse> getAccountType(@Path("account_num") int accountNum);

    // 6. 장르별 대여 가능한 영화 조회
    @GET("/available")
    Call<MovieNameResponse> getAvailableMovies(@Query("genre") String genre);







    //    @GET("/test")
    @GET("/jwt")
    Call<DefaultResponse> getTest();

    @GET("/test/{number}")
    Call<DefaultResponse> getTestPathAndQuery(
            @Path("number") int number,
            @Query("content") final String content
    );

    @POST("/test")
    Call<DefaultResponse> postTest(@Body RequestBody params);
}
