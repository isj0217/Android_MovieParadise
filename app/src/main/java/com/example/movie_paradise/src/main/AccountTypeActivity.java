package com.example.movie_paradise.src.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_paradise.R;
import com.example.movie_paradise.src.BaseActivity;
import com.example.movie_paradise.src.main.adapters.MovieAdapter;
import com.example.movie_paradise.src.main.interfaces.MainActivityView;
import com.example.movie_paradise.src.main.items.MovieItem;
import com.example.movie_paradise.src.main.models.AccountTypeResponse;
import com.example.movie_paradise.src.main.models.DefaultResponse;
import com.example.movie_paradise.src.main.models.MovieNameResponse;
import com.example.movie_paradise.src.main.models.SignInResponse;

import java.util.ArrayList;

import static android.view.View.GONE;

public class AccountTypeActivity extends BaseActivity implements MainActivityView {

    private String id;
    private int account_num;

    private Intent intent;

    private TextView tv_account_type_account_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_type);

        bindViews();


        loadIdAndAccountNum();

        tryGetAccountType(account_num);
    }

    private void tryGetAccountType(int accountNum) {
        showProgressDialog();

        final MainService mainService = new MainService(this);
        mainService.getAccountType(accountNum);
    }


    public void bindViews() {
        tv_account_type_account_type = findViewById(R.id.tv_account_type_account_type);
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
        hideProgressDialog();

        switch (accountTypeResponse.getCode()){
            case 100:
                showCustomToast(accountTypeResponse.getMessage());
                tv_account_type_account_type.setText(accountTypeResponse.getAccountTypeResult().getAccountType());
                break;
        }
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
                intent = new Intent(AccountTypeActivity.this, AccountTypeActivity.class);
                startActivity(intent);

                break;
            default:
                break;
        }
    }
}
