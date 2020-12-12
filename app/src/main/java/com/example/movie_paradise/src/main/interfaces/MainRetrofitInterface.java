package com.example.movie_paradise.src.main.interfaces;

import com.example.movie_paradise.src.main.models.AccountTypeResponse;
import com.example.movie_paradise.src.main.models.DefaultResponse;
import com.example.movie_paradise.src.main.models.MovieIdResponse;
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

    // 7. 영화 제목으로 영화 조회
    @GET("/search")
    Call<MovieNameResponse> searchMovieByMovieTitle(@Query("query") String movie_title);

    // 8. 배우 이름으로 영화 조회
    @GET("/search_from_actor")
    Call<MovieNameResponse> searchMovieByActorName(@Query("query") String actor_name);

    // 9. 영화 평점 매기기
    @POST("/rating")
    Call<DefaultResponse> postRating(@Body HashMap<String, Object> params);



    // 추가 1. 내가 본 영화 조회
    @GET("/watched/{account_num}")
    Call<MovieNameResponse> getWatchedMovies(@Path("account_num") int accountNum);

    // 추가 2. 영화 이름으로 movieID 조회
    @GET("/movie_id")
    Call<MovieIdResponse> getMovieIdByMovieName(@Query("query") String movie_name);









    // 이 아래는 테스트


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
