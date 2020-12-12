package com.example.movie_paradise.src.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_paradise.R;
import com.example.movie_paradise.src.BaseActivity;
import com.example.movie_paradise.src.main.adapters.MovieRateAdapter;
import com.example.movie_paradise.src.main.interfaces.MainActivityView;
import com.example.movie_paradise.src.main.items.MovieItem;
import com.example.movie_paradise.src.main.models.AccountTypeResponse;
import com.example.movie_paradise.src.main.models.DefaultResponse;
import com.example.movie_paradise.src.main.models.MovieIdResponse;
import com.example.movie_paradise.src.main.models.MovieNameResponse;
import com.example.movie_paradise.src.main.models.SignInResponse;

import java.util.ArrayList;

import static android.view.View.GONE;

public class RatingActivity extends BaseActivity implements MainActivityView {

    private String id;
    private int account_num;
    private int movieID;

    private Intent intent;

    private String movie_name;
    private TextView tv_rating_movie_name;

    private EditText et_rating_score;
    private Button btn_rating_confirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);


        movie_name = getIntent().getExtras().getString("movie_name", "movie_name failed");

        bindViews();

        tv_rating_movie_name.setText(movie_name);

        loadIdAndAccountNum();

        tryGetMovieIdByMovieName(movie_name);

        btn_rating_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_rating_score.getText().toString().length() == 0) {
                    showCustomToast("please fill in the form");
                } else {
//                    tryPostRating(id, )
                }
            }
        });

    }

    @Override
    public void getAvailableMoviesSuccess(MovieNameResponse movieNameResponse) {

    }

    private void tryGetMovieIdByMovieName(String movie_name) {
        showProgressDialog();

        final MainService mainService = new MainService(this);
        mainService.getMovieIdByMovieName(movie_name);
    }


    public void bindViews() {
        tv_rating_movie_name = findViewById(R.id.tv_rating_movie_name);

        et_rating_score = findViewById(R.id.et_rating_score);
        btn_rating_confirm = findViewById(R.id.btn_rating_confirm);
    }

    public void loadIdAndAccountNum() {
        SharedPreferences sharedPreferences = getSharedPreferences("id_and_account_num", MODE_PRIVATE);
        id = sharedPreferences.getString("id", "id 불러오기 실패");
        account_num = sharedPreferences.getInt("account_num", 0);

        System.out.println(id);
        System.out.println(account_num);
    }


    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();
//        mTvHelloWorld.setText(text);
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    @Override
    public void signUpSuccess(DefaultResponse defaultResponse) {
        //
    }

    @Override
    public void signInSuccess(SignInResponse signInResponse) {
        //
    }

    @Override
    public void getCurrentlyHeldSuccess(MovieNameResponse movieNameResponse) {

    }

    @Override
    public void getMovieQueueSuccess(MovieNameResponse movieNameResponse) {

    }

    @Override
    public void getAccountTypeSuccess(AccountTypeResponse accountTypeResponse) {

    }

    @Override
    public void searchMovieByMovieTitleSuccess(MovieNameResponse movieNameResponse) {

    }

    @Override
    public void searchMovieByActorNameSuccess(MovieNameResponse movieNameResponse) {

    }

    @Override
    public void getWatchedMoviesSuccess(MovieNameResponse movieNameResponse) {

    }

    @Override
    public void getMovieIdByMovieNameSuccess(MovieIdResponse movieIdResponse) {
        hideProgressDialog();

        movieID = movieIdResponse.getMovieIdResult().getMovieID();

        System.out.println("받아온 movieID = " + movieID);
    }

    public void saveIdAndAccountNum(String id, int account_num) {
        SharedPreferences sharedPreferences = getSharedPreferences("id_and_account_num", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", id);
        editor.putInt("account_num", account_num);
        editor.apply();
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_home_currently_held:
                intent = new Intent(RatingActivity.this, RatingActivity.class);
                startActivity(intent);

                break;
            default:
                break;
        }
    }
}
