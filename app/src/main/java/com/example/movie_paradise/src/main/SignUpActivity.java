package com.example.movie_paradise.src.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.movie_paradise.R;
import com.example.movie_paradise.src.BaseActivity;
import com.example.movie_paradise.src.main.interfaces.MainActivityView;
import com.example.movie_paradise.src.main.models.AccountTypeResponse;
import com.example.movie_paradise.src.main.models.DefaultResponse;
import com.example.movie_paradise.src.main.models.MovieNameResponse;
import com.example.movie_paradise.src.main.models.SignInResponse;

import java.util.HashMap;

public class SignUpActivity extends BaseActivity implements MainActivityView {

    private EditText et_sign_up_id, et_sign_up_last_name, et_sign_up_first_name, et_sign_up_address,
            et_sign_up_city, et_sign_up_state, et_sign_up_zipcode, et_sign_up_telephone, et_sign_up_email,
            et_sign_up_credit_card, et_sign_up_account_type;

    private Button btn_sign_up_sign_up;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        bindViews();

        btn_sign_up_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryPostSignUp(et_sign_up_id.getText().toString(),
                        et_sign_up_last_name.getText().toString(),
                        et_sign_up_first_name.getText().toString(),
                        et_sign_up_address.getText().toString(),
                        et_sign_up_city.getText().toString(),
                        et_sign_up_state.getText().toString(),
                        et_sign_up_zipcode.getText().toString(),
                        et_sign_up_telephone.getText().toString(),
                        et_sign_up_email.getText().toString(),
                        et_sign_up_credit_card.getText().toString(),
                        et_sign_up_account_type.getText().toString());
            }
        });

    }

    private void tryPostSignUp(String id, String lname, String fname, String address, String city, String state, String zipcode, String telephone, String email, String credit_card, String account_type) {

        showProgressDialog();

        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("last_name", lname);
        params.put("first_name", fname);
        params.put("address", address);
        params.put("city", city);
        params.put("state", state);
        params.put("zipcode", zipcode);
        params.put("telephone", telephone);
        params.put("email", email);
        params.put("credit_card", credit_card);
        params.put("account_type", account_type);

        final MainService mainService = new MainService(this, params);
        mainService.postSignUp();
    }

    public void bindViews() {
        et_sign_up_id = findViewById(R.id.et_sign_up_id);
        et_sign_up_last_name = findViewById(R.id.et_sign_up_last_name);
        et_sign_up_first_name = findViewById(R.id.et_sign_up_first_name);
        et_sign_up_address = findViewById(R.id.et_sign_up_address);
        et_sign_up_city = findViewById(R.id.et_sign_up_city);
        et_sign_up_state = findViewById(R.id.et_sign_up_state);
        et_sign_up_zipcode = findViewById(R.id.et_sign_up_zipcode);
        et_sign_up_telephone = findViewById(R.id.et_sign_up_telephone);
        et_sign_up_email = findViewById(R.id.et_sign_up_email);
        et_sign_up_credit_card = findViewById(R.id.et_sign_up_credit_card);
        et_sign_up_account_type = findViewById(R.id.et_sign_up_id);

        btn_sign_up_sign_up = findViewById(R.id.btn_sign_up_sign_up);
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

//    public void customOnClick(View view) {
//        switch (view.getId()) {
//            case R.id.main_btn_hello_world:
//                tryGetTest();
//                break;
//            default:
//                break;
//        }
//    }


    @Override
    public void signUpSuccess(DefaultResponse defaultResponse) {
        hideProgressDialog();

        switch (defaultResponse.getCode()){
            case 100:
                showCustomToast(defaultResponse.getMessage());
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
                break;

            default:
                showCustomToast(defaultResponse.getMessage());
                break;
        }
    }

    @Override
    public void signInSuccess(SignInResponse signInResponse) {

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
}
