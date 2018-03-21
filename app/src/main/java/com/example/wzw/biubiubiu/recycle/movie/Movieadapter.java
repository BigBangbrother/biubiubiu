package com.example.wzw.biubiubiu.recycle.movie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wzw.biubiubiu.R;
import com.example.wzw.biubiubiu.recycle.movie.Movie.ResultsBean;

import java.util.List;

/**
 * Created by wzw on 2018/3/21.
 */

public class Movieadapter
        extends RecyclerView.Adapter
{

    private List<ResultsBean> mMovies;
    private Context     mContext;


    public Movieadapter(Context context, List<ResultsBean> list) {
        mContext = context;
        mMovies = list;
    }

    public void setMovies(List<ResultsBean> movies) {
        mMovies.addAll(movies);
    }

    public void clearMovies() {
        mMovies.clear();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MovieHolder(LayoutInflater.from(parent.getContext())
                                             .inflate(R.layout.movie_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ResultsBean movie    = mMovies.get(position);
        MovieHolder       movieHolder = (MovieHolder) holder;

        final List<String> image = movie.getImages();
        if (image!=null&&image.size() > 0){
            movieHolder.mGankItemImage.setVisibility(View.VISIBLE);
           Glide.with(mContext).load(image.get(0)).asBitmap().into(((MovieHolder) holder).mGankItemImage);
        }else{
            movieHolder.mGankItemImage.setVisibility(View.GONE);
        }
        movieHolder.mGankItemTitle.setText(movie.getDesc());
        movieHolder.mGankItemSubtitle.setText(movie.getPublishedAt());
    }

    @Override
    public int getItemCount() {
        return mMovies == null
               ? 0
               : mMovies.size();
    }

    private class MovieHolder
            extends RecyclerView.ViewHolder
    {
        TextView  mGankItemTitle;
        TextView  mGankItemSubtitle;
        ImageView mGankItemImage;
        public MovieHolder(View itemView) {
            super(itemView);
            mGankItemTitle = (TextView)itemView.findViewById(R.id.gank_item_title);
            mGankItemSubtitle = (TextView)itemView.findViewById(R.id.gank_item_subtitle);
            mGankItemImage = (ImageView)itemView.findViewById(R.id.gank_item_image);
        }
    }
}
