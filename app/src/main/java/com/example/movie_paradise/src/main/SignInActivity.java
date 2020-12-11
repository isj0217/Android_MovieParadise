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
import com.example.movie_paradise.src.main.models.SignInResponse;

import java.util.HashMap;

public class SignInActivity extends BaseActivity implements MainActivityView {

    private EditText et_sign_in_id;
    private Button btn_sign_in_sign_in, btn_sign_in_sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        bindViews();

        btn_sign_in_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryPostSignIn(et_sign_in_id.getText().toString());
            }
        });

        btn_sign_in_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    private void tryPostSignIn(String id) {

        showProgressDialog();

        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);

        final MainService mainService = new MainService(this, params);
        mainService.postSignIn();
    }

    public void bindViews() {
        et_sign_in_id = findViewById(R.id.et_sign_in_id);

        btn_sign_in_sign_in = findViewById(R.id.btn_sign_in_sign_in);
        btn_sign_in_sign_up = findViewById(R.id.btn_sign_in_sign_up);
    }

    private void tryGetTest() {
        showProgressDialog();

        final MainService mainService = new MainService(this);
        mainService.getTest();
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
        hideProgressDialog();

        switch (signInResponse.getCode()){
            case 100:
                saveIdAndAccountNum(et_sign_in_id.getText().toString(), signInResponse.getSignInResult().getAccountNum());

                showCustomToast(signInResponse.getMessage());
                Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;

            default:
                showCustomToast(signInResponse.getMessage());
        }
    }

    public void saveIdAndAccountNum(String id, int account_num) {
        SharedPreferences sharedPreferences = getSharedPreferences("id_and_account_num", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", id);
        editor.putInt("account_num", account_num);
        editor.apply();
    }

    public void loadIdAndAccountNum() {
        SharedPreferences sharedPreferences = getSharedPreferences("id_and_account_num", MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "id 불러오기 실패");
        int account_num = sharedPreferences.getInt("account_num", 0);
    }



    //    public void customOnClick(View view) {
//        switch (view.getId()) {
//            case R.id.main_btn_hello_world:
//                tryGetTest();
//                break;
//            default:
//                break;
//        }
//    }
}
