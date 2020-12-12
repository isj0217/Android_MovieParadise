package com.example.movie_paradise.src.main.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_paradise.R;
import com.example.movie_paradise.src.main.items.MovieItem;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.CustomViewHolder> {

    private ArrayList<MovieItem> movie_item_list;

    private OnItemClickListener mListener = null;

    public MovieAdapter(ArrayList<MovieItem> movie_item_list) {
        this.movie_item_list = movie_item_list;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }


    @NonNull
    @Override
    public MovieAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_title, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.CustomViewHolder holder, int position) {

        holder.tv_item_movie_title.setText(movie_item_list.get(position).getMovieName());

        holder.itemView.setTag(position);

        /**
         * click listener 달 거면 여기에 달 것
         * */
    }

    @Override
    public int getItemCount() {
        return (null != movie_item_list ? movie_item_list.size() : 0);
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView tv_item_movie_title;

        public CustomViewHolder(@NonNull final View itemView) {
            super(itemView);

            this.tv_item_movie_title = itemView.findViewById(R.id.tv_item_movie_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Context context = view.getContext();

                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {


//                        Intent intent = new Intent(view.getContext(), InPostActivity.class);
//
//                        context.startActivity(intent);
//                        ((Activity) context).finish();

                        if (mListener != null) {
                            mListener.onItemClick(view, pos);
                        }
                    }
                }
            });
        }
    }


}


