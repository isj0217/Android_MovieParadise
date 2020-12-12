package com.example.movie_paradise.src.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.movie_paradise.R;
import com.example.movie_paradise.src.BaseActivity;
import com.example.movie_paradise.src.main.interfaces.MainActivityView;
import com.example.movie_paradise.src.main.models.AccountTypeResponse;
import com.example.movie_paradise.src.main.models.DefaultResponse;
import com.example.movie_paradise.src.main.models.MovieIdResponse;
import com.example.movie_paradise.src.main.models.MovieNameResponse;
import com.example.movie_paradise.src.main.models.SignInResponse;


public class HomeActivity extends BaseActivity implements MainActivityView {

    private long mBackKeyPressedTime = 0;
    private Toast mToast;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bindViews();

        loadIdAndAccountNum();

    }


    public void bindViews() {

    }

    public void loadIdAndAccountNum() {
        SharedPreferences sharedPreferences = getSharedPreferences("id_and_account_num", MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "id 불러오기 실패");
        int account_num = sharedPreferences.getInt("account_num", 0);

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

    public void saveIdAndAccountNum(String id, int account_num) {
        SharedPreferences sharedPreferences = getSharedPreferences("id_and_account_num", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", id);
        editor.putInt("account_num", account_num);
        editor.apply();
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
    public void getAvailableMoviesSuccess(MovieNameResponse movieNameResponse) {

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

    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_home_currently_held:
                intent = new Intent(HomeActivity.this, CurrentlyHeldActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_home_movie_queue:
                intent = new Intent(HomeActivity.this, MovieQueueActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_home_account_type:
                intent = new Intent(HomeActivity.this, AccountTypeActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_home_available:
                intent = new Intent(HomeActivity.this, AvailableActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_home_search_movies:
                intent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_home_rate_movies_I_watched:
                intent = new Intent(HomeActivity.this, WatchedActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_home_log_out:
                intent = new Intent(HomeActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {

        if (System.currentTimeMillis() > mBackKeyPressedTime + 2000) {
            mBackKeyPressedTime = System.currentTimeMillis();
            mToast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            mToast.show();
        } else if (System.currentTimeMillis() <= mBackKeyPressedTime + 2000) {

            finish();
            mToast.cancel();
        }

    }


}
