package com.example.movie_paradise.src.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import static android.view.View.GONE;

public class AvailableActivity extends BaseActivity implements MainActivityView {

    // 영화 카테고리 4가지 - 드라마, 코미디, 외국, 액션
    private Button btn_available_drama;
    private Button btn_available_comedy;
    private Button btn_available_foreign;
    private Button btn_available_action;
    private boolean is_category_drama;
    private boolean is_category_comedy;
    private boolean is_category_foreign;
    private boolean is_category_action;

    private String id;
    private int account_num;

    private Intent intent;

    private ArrayList<MovieItem> m_movie_item_list;
    private MovieAdapter movie_adapter;
    private RecyclerView rv_available_movies;
    private LinearLayoutManager linear_layout_manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_movies);

        bindViews();

        initCategories();

        setClickListenersToCategories();

        linear_layout_manager = new LinearLayoutManager(getApplicationContext());
        rv_available_movies.setLayoutManager(linear_layout_manager);

        m_movie_item_list = new ArrayList<>();
        movie_adapter = new MovieAdapter(m_movie_item_list);
        rv_available_movies.setAdapter(movie_adapter);


//        loadIdAndAccountNum();

        tryGetAvailableMovies("drama");
    }

    @Override
    public void getAvailableMoviesSuccess(MovieNameResponse movieNameResponse) {
        hideProgressDialog();

        showCustomToast(movieNameResponse.getMessage());

        switch (movieNameResponse.getCode()) {

            case 100:
                /**
                 * MovieItem 형식의 ArrayList에 모두 넣어두고 어댑터를 이용해서 하나하나 레이아웃에 갖다 붙이자!!
                 * */

                int num_of_movies = movieNameResponse.getMovieNameResults().size();

                for (int i = 0; i < num_of_movies; i++) {
                    MovieItem movieItem = new MovieItem();

                    System.out.println(movieNameResponse.getMovieNameResults().get(i).getMovieName());

                    movieItem.setMovieName(movieNameResponse.getMovieNameResults().get(i).getMovieName());
                    m_movie_item_list.add(movieItem);
                }
                movie_adapter.notifyDataSetChanged();

                break;
        }
    }

    private void tryGetAvailableMovies(String genre) {
        showProgressDialog();

        final MainService mainService = new MainService(this);
        mainService.getAvailableMovies(genre);
    }


    public void bindViews() {
        btn_available_drama = findViewById(R.id.btn_available_category_drama);
        btn_available_comedy = findViewById(R.id.btn_available_category_comedy);
        btn_available_foreign = findViewById(R.id.btn_available_category_foreign);
        btn_available_action = findViewById(R.id.btn_available_category_action);

        rv_available_movies = findViewById(R.id.rv_available_movies);
    }

    public void loadIdAndAccountNum() {
        SharedPreferences sharedPreferences = getSharedPreferences("id_and_account_num", MODE_PRIVATE);
        id = sharedPreferences.getString("id", "id 불러오기 실패");
        account_num = sharedPreferences.getInt("account_num", 0);

        System.out.println(id);
        System.out.println(account_num);
    }

    public void initCategories() {

        // 초기에는 'drama'로 기본 셋팅!!
        is_category_drama = true;
        is_category_comedy = false;
        is_category_foreign = false;
        is_category_action = false;

        // 초기에는 '전체'로 기본 셋팅!!
        btn_available_drama.setBackgroundResource(R.drawable.btn_dark_orange_round_corner_light_grey);
        btn_available_comedy.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        btn_available_foreign.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        btn_available_action.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
    }

    public void setClickListenersToCategories() {
        btn_available_drama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (whichCategoryIsActivatedNow() != 1) {

                    makeCategoryDrama();
                    m_movie_item_list.clear();
                    tryGetAvailableMovies("drama");
                }
            }
        });

        btn_available_comedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (whichCategoryIsActivatedNow() != 2) {

                    makeCategoryComedy();
                    m_movie_item_list.clear();
                    tryGetAvailableMovies("comedy");
                }
            }
        });

        btn_available_foreign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (whichCategoryIsActivatedNow() != 3) {

                    makeCategoryForeign();
                    m_movie_item_list.clear();
                    tryGetAvailableMovies("foreign");

                }
            }
        });

        btn_available_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (whichCategoryIsActivatedNow() != 4) {

                    makeCategoryAction();
                    m_movie_item_list.clear();
                    tryGetAvailableMovies("action");

                }
            }
        });

    }

    /**
     * 어떤 카테고리가 현재 적용되어 있는지를 판단해주는 메서드로, 4가지 경우에 따라 1~4을 반환하고, 아무것도 선택되어있지 않으면 0을 반환한다.
     */
    public int whichCategoryIsActivatedNow() {
        if (is_category_drama)
            return 1;
        else if (is_category_comedy)
            return 2;
        else if (is_category_foreign)
            return 3;
        else if (is_category_action)
            return 4;

        else
            return 0;
    }


    // 카테고리 변경하는 4가지 경우
    public void makeCategoryDrama() {
        is_category_drama = true;
        btn_available_drama.setBackgroundResource(R.drawable.btn_dark_orange_round_corner_light_grey);

        is_category_comedy = false;
        btn_available_comedy.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);

        is_category_foreign = false;
        btn_available_foreign.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);

        is_category_action = false;
        btn_available_action.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
    }

    public void makeCategoryComedy() {
        is_category_drama = false;
        btn_available_drama.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);

        is_category_comedy = true;
        btn_available_comedy.setBackgroundResource(R.drawable.btn_dark_orange_round_corner_light_grey);

        is_category_foreign = false;
        btn_available_foreign.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);

        is_category_action = false;
        btn_available_action.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
    }

    public void makeCategoryForeign() {
        is_category_drama = false;
        btn_available_drama.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);

        is_category_comedy = false;
        btn_available_comedy.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);

        is_category_foreign = true;
        btn_available_foreign.setBackgroundResource(R.drawable.btn_dark_orange_round_corner_light_grey);

        is_category_action = false;
        btn_available_action.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
    }

    public void makeCategoryAction() {
        is_category_drama = false;
        btn_available_drama.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);

        is_category_comedy = false;
        btn_available_comedy.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);

        is_category_foreign = false;
        btn_available_foreign.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);

        is_category_action = true;
        btn_available_action.setBackgroundResource(R.drawable.btn_dark_orange_round_corner_light_grey);
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
                intent = new Intent(AvailableActivity.this, AvailableActivity.class);
                startActivity(intent);

                break;
            default:
                break;
        }
    }
}
