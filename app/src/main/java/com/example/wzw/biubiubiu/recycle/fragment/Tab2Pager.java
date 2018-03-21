package com.example.wzw.biubiubiu.recycle.fragment;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wzw.biubiubiu.HttpUtil.Fault;
import com.example.wzw.biubiubiu.R;
import com.example.wzw.biubiubiu.recycle.AdapterWrapper;
import com.example.wzw.biubiubiu.recycle.SwipeToLoadHelper;
import com.example.wzw.biubiubiu.recycle.movie.Movie.ResultsBean;
import com.example.wzw.biubiubiu.recycle.movie.MovieLoader;
import com.example.wzw.biubiubiu.recycle.movie.Movieadapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;

/**
 * Created by wzw on 2018/3/20.
 */

public class Tab2Pager
        extends Fragment
{

    @Bind(R.id.recycler_view)
    RecyclerView       mRecyclerView;
    @Bind(R.id.swipe)
    SwipeRefreshLayout mSwipe;
    private MovieLoader  mMovieLoader;
    private Movieadapter mMovieadapter;

    private AdapterWrapper    mAdapterWrapper;
    private SwipeToLoadHelper mLoadMoreHelper;

    public static  int page  = 1;
    private List<ResultsBean> mMovies = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View layout = inflater.inflate(R.layout.tab2, null);
        ButterKnife.bind(this, layout);
        init();
        return layout;
    }

    private void init() {

        mMovieLoader = new MovieLoader();
        mRecyclerView.addItemDecoration(new MovieDecoration());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        getMovieList();

        //下拉刷新
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMovieList();
            }
        });



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void getMovieList() {
        mMovieLoader.getGirl("Android", 20,1)
                    .subscribe(new Action1<List<ResultsBean>>() {
                        @Override
                        public void call(List<ResultsBean> movies) {

                            page = 1 ;
                            mMovieadapter = new Movieadapter(getContext(), movies);
                            Log.d("请求","zouzheli");
                            mAdapterWrapper = new AdapterWrapper(mMovieadapter);
                            mLoadMoreHelper = new SwipeToLoadHelper(mRecyclerView, mAdapterWrapper);

                            mLoadMoreHelper.setLoadMoreListener(new SwipeToLoadHelper.LoadMoreListener() {
                                @Override
                                public void onLoad() {
                                    page = page+1;
                                    getLoad();
                                }
                            });
                            mRecyclerView.setAdapter(mAdapterWrapper);

                            Tab2Pager.this.mMovieadapter.notifyDataSetChanged();
                            if (mSwipe.isRefreshing()){
                                mSwipe.setRefreshing(false);
                            }
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Log.e("TAG", "error message:" + throwable.getMessage());

                            if (throwable instanceof Fault) {
                                Fault fault = (Fault) throwable;
                                if (fault.getErrorCode() == 404) {
                                    //错误处理
                                } else if (fault.getErrorCode() == 500) {
                                    //错误处理
                                } else if (fault.getErrorCode() == 501) {
                                    //错误处理
                                }
                            }
                        }
                    });
    }


    private void getLoad() {
        mMovieLoader.getGirl("Android", 20,1)
                    .subscribe(new Action1<List<ResultsBean>>() {
                        @Override
                        public void call(List<ResultsBean> movies) {
                            Log.d("加载","1231456");
                            mMovieadapter.setMovies(movies);
                            mSwipe.setEnabled(true);
                            mLoadMoreHelper.setLoadMoreFinish();
                            mMovieadapter.notifyDataSetChanged();
                            page = page+1;
                            if (mSwipe.isRefreshing()){
                                mSwipe.setRefreshing(false);
                            }
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Log.e("TAG", "error message:" + throwable.getMessage());

                            if (throwable instanceof Fault) {
                                Fault fault = (Fault) throwable;
                                if (fault.getErrorCode() == 404) {
                                    //错误处理
                                } else if (fault.getErrorCode() == 500) {
                                    //错误处理
                                } else if (fault.getErrorCode() == 501) {
                                    //错误处理
                                }
                            }
                        }
                    });
    }


    public static class MovieDecoration
            extends RecyclerView.ItemDecoration
    {
        @Override
        public void getItemOffsets(Rect outRect,
                                   View view,
                                   RecyclerView parent,
                                   RecyclerView.State state)
        {
            outRect.set(0, 0, 0, 20);
        }
    }
}
