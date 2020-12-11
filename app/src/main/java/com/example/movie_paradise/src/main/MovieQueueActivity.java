package com.example.movie_paradise.src.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_paradise.R;
import com.example.movie_paradise.src.BaseActivity;
import com.example.movie_paradise.src.main.adapters.MovieAdapter;
import com.example.movie_paradise.src.main.interfaces.MainActivityView;
import com.example.movie_paradise.src.main.items.MovieItem;
import com.example.movie_paradise.src.main.models.DefaultResponse;
import com.example.movie_paradise.src.main.models.MovieNameResponse;
import com.example.movie_paradise.src.main.models.SignInResponse;

import java.util.ArrayList;

import static android.view.View.GONE;

public class MovieQueueActivity extends BaseActivity implements MainActivityView {

    private String id;
    private int account_num;

    private Intent intent;

    private ArrayList<MovieItem> m_movie_item_list;
    private MovieAdapter movie_adapter;
    private RecyclerView rv_movie_queue;
    private LinearLayoutManager linear_layout_manager;

    private LinearLayout ll_movie_queue_empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_queue);

        bindViews();
        linear_layout_manager = new LinearLayoutManager(getApplicationContext());
        rv_movie_queue.setLayoutManager(linear_layout_manager);

        m_movie_item_list = new ArrayList<>();
        movie_adapter = new MovieAdapter(m_movie_item_list);
        rv_movie_queue.setAdapter(movie_adapter);


        loadIdAndAccountNum();

        tryGetMovieQueue(account_num);
    }

    private void tryGetMovieQueue(int accountNum) {
        showProgressDialog();

        final MainService mainService = new MainService(this);
        mainService.getMovieQueue(accountNum);
    }


    public void bindViews() {
        ll_movie_queue_empty = findViewById(R.id.ll_movie_queue_empty);

        rv_movie_queue = findViewById(R.id.rv_movie_queue);
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
        hideProgressDialog();

        showCustomToast(movieNameResponse.getMessage());

        switch (movieNameResponse.getCode()) {

            case 100:
                /**
                 * MovieItem 형식의 ArrayList에 모두 넣어두고 어댑터를 이용해서 하나하나 레이아웃에 갖다 붙이자!!
                 * */

                int num_of_movies = movieNameResponse.getMovieNameResults().size();


                if (num_of_movies > 0) {

                    ll_movie_queue_empty.setVisibility(GONE);

                    for (int i = 0; i < num_of_movies; i++) {
                        MovieItem movieItem = new MovieItem();

                        System.out.println(movieNameResponse.getMovieNameResults().get(i).getMovieName());

                        movieItem.setMovieName(movieNameResponse.getMovieNameResults().get(i).getMovieName());
                        m_movie_item_list.add(movieItem);
                    }
                    movie_adapter.notifyDataSetChanged();
                }
                break;
        }
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
                intent = new Intent(MovieQueueActivity.this, MovieQueueActivity.class);
                startActivity(intent);

                break;
            default:
                break;
        }
    }
}
