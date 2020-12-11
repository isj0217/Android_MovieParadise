package com.example.movie_paradise.src.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.movie_paradise.R;
import com.example.movie_paradise.src.BaseActivity;
import com.example.movie_paradise.src.main.interfaces.MainActivityView;
import com.example.movie_paradise.src.main.models.DefaultResponse;
import com.example.movie_paradise.src.main.models.MovieNameResponse;
import com.example.movie_paradise.src.main.models.SignInResponse;

import java.util.HashMap;

public class HomeActivity extends BaseActivity implements MainActivityView {

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

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_home_currently_held:
                intent = new Intent(HomeActivity.this, CurrentlyHeldActivity.class);
                startActivity(intent);

                break;
            default:
                break;
        }
    }
}
