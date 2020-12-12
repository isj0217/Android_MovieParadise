package com.example.movie_paradise.src.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

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

public class SearchActivity extends BaseActivity implements MainActivityView {

    // 탐색 카테고리 2개
    private Button btn_search_movies_by_movie_title;
    private Button btn_search_movies_by_actor_name;
    private boolean is_category_movie;
    private boolean is_category_actor;

    private EditText et_search_movies_text;

    private ImageView iv_search_movies_search;

    private LinearLayout ll_search_movies_not_found;

    private String id;
    private int account_num;

    private Intent intent;

    private ArrayList<MovieItem> m_movie_item_list;
    private MovieAdapter movie_adapter;
    private RecyclerView rv_search_movies;
    private LinearLayoutManager linear_layout_manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);

        bindViews();

        initCategories();

        setClickListenersToCategories();

        linear_layout_manager = new LinearLayoutManager(getApplicationContext());
        rv_search_movies.setLayoutManager(linear_layout_manager);

        m_movie_item_list = new ArrayList<>();
        movie_adapter = new MovieAdapter(m_movie_item_list);
        rv_search_movies.setAdapter(movie_adapter);


//        loadIdAndAccountNum();

        iv_search_movies_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_movie_item_list.clear();

                switch(whichCategoryIsActivatedNow()){
                    case 1:
                        trySearchMovieByMovieTitle(et_search_movies_text.getText().toString());
                        break;
                    case 2:
                        trySearchMovieByActorName(et_search_movies_text.getText().toString());
                        break;
                }
            }
        });

    }

    @Override
    public void getAvailableMoviesSuccess(MovieNameResponse movieNameResponse) {

    }

    private void trySearchMovieByMovieTitle(String movie_title) {
        showProgressDialog();

        final MainService mainService = new MainService(this);
        mainService.searchMovieByMovieTitle(movie_title);
    }

    private void trySearchMovieByActorName(String actor_name) {
        showProgressDialog();

        final MainService mainService = new MainService(this);
        mainService.searchMovieByActorName(actor_name);
    }


    public void bindViews() {

        btn_search_movies_by_movie_title = findViewById(R.id.btn_search_movies_by_movie_title);
        btn_search_movies_by_actor_name = findViewById(R.id.btn_search_movies_by_actor_name);

        et_search_movies_text = findViewById(R.id.et_search_movies_text);

        iv_search_movies_search = findViewById(R.id.iv_search_movies_search);

        ll_search_movies_not_found = findViewById(R.id.ll_search_movies_not_found);

        rv_search_movies = findViewById(R.id.rv_search_movies);
    }

    public void loadIdAndAccountNum() {
        SharedPreferences sharedPreferences = getSharedPreferences("id_and_account_num", MODE_PRIVATE);
        id = sharedPreferences.getString("id", "id 불러오기 실패");
        account_num = sharedPreferences.getInt("account_num", 0);

        System.out.println(id);
        System.out.println(account_num);
    }

    public void initCategories() {

        // 초기에는 'movie'로 기본 셋팅!!
        is_category_movie = true;
        is_category_actor = false;

        // 초기에는 'movie'로 기본 셋팅!!
        btn_search_movies_by_movie_title.setBackgroundResource(R.drawable.btn_dark_orange_round_corner_light_grey);
        btn_search_movies_by_actor_name.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
    }

    public void setClickListenersToCategories() {
        btn_search_movies_by_movie_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (whichCategoryIsActivatedNow() != 1) {

                    makeCategoryMovieTitle();
                    m_movie_item_list.clear();
                }
            }
        });

        btn_search_movies_by_actor_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (whichCategoryIsActivatedNow() != 2) {

                    makeCategoryActorName();
                    m_movie_item_list.clear();
                }
            }
        });


    }

    /**
     * 어떤 카테고리가 현재 적용되어 있는지를 판단해주는 메서드로, 2가지 경우에 따라 1 또는 2를 반환하고, 아무것도 선택되어있지 않으면 0을 반환한다.
     */
    public int whichCategoryIsActivatedNow() {
        if (is_category_movie)
            return 1;
        else if (is_category_actor)
            return 2;

        else
            return 0;
    }


    // 카테고리 변경하는 2가지 경우
    public void makeCategoryMovieTitle() {
        is_category_movie = true;
        btn_search_movies_by_movie_title.setBackgroundResource(R.drawable.btn_dark_orange_round_corner_light_grey);

        is_category_actor = false;
        btn_search_movies_by_actor_name.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
    }

    public void makeCategoryActorName() {
        is_category_movie = false;
        btn_search_movies_by_movie_title.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);

        is_category_actor = true;
        btn_search_movies_by_actor_name.setBackgroundResource(R.drawable.btn_dark_orange_round_corner_light_grey);
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
        hideProgressDialog();

        showCustomToast(movieNameResponse.getMessage());

        switch (movieNameResponse.getCode()) {

            case 100:
                /**
                 * MovieItem 형식의 ArrayList에 모두 넣어두고 어댑터를 이용해서 하나하나 레이아웃에 갖다 붙이자!!
                 * */

                int num_of_movies = movieNameResponse.getMovieNameResults().size();

                if (num_of_movies == 0){
                    ll_search_movies_not_found.setVisibility(View.VISIBLE);
                } else {
                    ll_search_movies_not_found.setVisibility(View.GONE);

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

    @Override
    public void searchMovieByActorNameSuccess(MovieNameResponse movieNameResponse) {
        hideProgressDialog();

        showCustomToast(movieNameResponse.getMessage());

        switch (movieNameResponse.getCode()) {

            case 100:
                /**
                 * MovieItem 형식의 ArrayList에 모두 넣어두고 어댑터를 이용해서 하나하나 레이아웃에 갖다 붙이자!!
                 * */

                int num_of_movies = movieNameResponse.getMovieNameResults().size();

                if (num_of_movies == 0){
                    ll_search_movies_not_found.setVisibility(View.VISIBLE);
                } else {
                    ll_search_movies_not_found.setVisibility(View.GONE);

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
                intent = new Intent(SearchActivity.this, SearchActivity.class);
                startActivity(intent);

                break;
            default:
                break;
        }
    }
}
