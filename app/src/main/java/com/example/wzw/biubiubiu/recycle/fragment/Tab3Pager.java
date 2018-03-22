package com.example.wzw.biubiubiu.recycle.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wzw.biubiubiu.R;
import com.example.wzw.biubiubiu.httpUtil.Fault;
import com.example.wzw.biubiubiu.recycletutil.AdapterWrapper;
import com.example.wzw.biubiubiu.recycletutil.SwipeToLoadHelper;
import com.example.wzw.biubiubiu.recycle.movie.Girl.ResultsBean;
import com.example.wzw.biubiubiu.recycle.movie.Girladapter;
import com.example.wzw.biubiubiu.recycle.movie.MovieLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;

/**
 * Created by wzw on 2018/3/20.
 */

public class Tab3Pager
        extends Fragment
{
    @Bind(R.id.tap3recycle)
    RecyclerView       mTap3recycle;
    @Bind(R.id.tap3swip)
    SwipeRefreshLayout mTap3swip;
    private MovieLoader mMovieLoader;

    private Girladapter mGirladapter;

    private SwipeToLoadHelper mSwipeToLoadHelper;
    private AdapterWrapper mAdapterWrapper;

    public static  int page  = 1;
    private RecyclerView.LayoutManager mManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View layout = inflater.inflate(R.layout.tab3, null);
        ButterKnife.bind(this, layout);
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        mMovieLoader = new MovieLoader();
        mTap3recycle.addItemDecoration(new MovieDecoration());
        mManager = new GridLayoutManager(getActivity().getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);

        mTap3recycle.setLayoutManager(mManager);

        getGirl();

        mTap3swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getGirl();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void getGirl() {

        mMovieLoader.getGirl("福利", 20, 1)
                    .subscribe(new Action1<List<ResultsBean>>() {
                        @Override
                        public void call(List<ResultsBean> list) {

                            mGirladapter = new Girladapter(getContext(), list);
                            mAdapterWrapper = new AdapterWrapper(mGirladapter);

                            mSwipeToLoadHelper = new SwipeToLoadHelper(mTap3recycle, mAdapterWrapper);

                            mSwipeToLoadHelper.setLoadMoreListener(new SwipeToLoadHelper.LoadMoreListener() {
                                @Override
                                public void onLoad() {
                                    Log.d("jinqu le ","");
                                    page = page+1;
                                    getLoad();
                                }
                            });

                            mTap3recycle.setAdapter(mAdapterWrapper);

                            mGirladapter.notifyDataSetChanged();
                            if (mTap3swip.isRefreshing()){
                                mTap3swip.setRefreshing(false);
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

    public void getLoad() {
        mMovieLoader.getGirl("福利", 20, page)
                    .subscribe(new Action1<List<ResultsBean>>() {

                        @Override
                        public void call(List<ResultsBean> list) {

                            mGirladapter.setList(list);
                            mTap3swip.setEnabled(true);
                            mSwipeToLoadHelper.setLoadMoreFinish();
                            mGirladapter.notifyDataSetChanged();
                            page = page +1;
                            if (mTap3swip.isRefreshing()){
                                mTap3swip.setRefreshing(false);
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
