package com.example.wzw.biubiubiu.recycle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.wzw.biubiubiu.R;
import com.example.wzw.biubiubiu.recycle.fragment.Tab1Pager;
import com.example.wzw.biubiubiu.recycle.fragment.Tab2Pager;
import com.example.wzw.biubiubiu.recycle.fragment.Tab3Pager;
import com.example.wzw.biubiubiu.recycle.fragment.Tab4Pager;
import com.jpeng.jptabbar.BadgeDismissListener;
import com.jpeng.jptabbar.JPTabBar;
import com.jpeng.jptabbar.OnTabSelectListener;
import com.jpeng.jptabbar.anno.NorIcons;
import com.jpeng.jptabbar.anno.SeleIcons;
import com.jpeng.jptabbar.anno.Titles;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wzw on 2018/3/19.
 */

public class RecycleActivity
        extends AppCompatActivity
implements BadgeDismissListener, OnTabSelectListener
{

    @Titles
    private static final String[] mTitles = {"页面一",
                                             "页面二",
                                             "页面三",
                                             "页面四"};

    @SeleIcons
    private static final int[] mSeleIcons = {R.mipmap.ic_launcher,
                                             R.mipmap.ic_launcher,
                                             R.mipmap.ic_launcher,
                                             R.mipmap.ic_launcher};

    @NorIcons
    private static final int[] mNormalIcons = {R.mipmap.ic_launcher,
                                               R.mipmap.ic_launcher,
                                               R.mipmap.ic_launcher,
                                               R.mipmap.ic_launcher};
    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    @Bind(R.id.tabbar)
    JPTabBar  mTabbar;

    private List<Fragment> list = new ArrayList<>();

    private Tab1Pager mTab1;

    private Tab2Pager mTab2;

    private Tab3Pager mTab3;

    private Tab4Pager mTab4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_activity);
        ButterKnife.bind(this);

        init();

    }

    private void init() {

        mTab1 = new Tab1Pager();
        mTab2 = new Tab2Pager();
        mTab3 = new Tab3Pager();
        mTab4 = new Tab4Pager();
        mTabbar.setGradientEnable(true);
        mTabbar.setPageAnimateEnable(true);
        mTabbar.setTabListener(this);
        list.add(mTab1);
        list.add(mTab2);
        list.add(mTab3);
        list.add(mTab4);

        mViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(),list));
        mTabbar.setContainer(mViewPager);

        mTabbar.setDismissListener(this);
        mTabbar.setTabListener(this);
        if (mTabbar.getMiddleView()!=null){
            mTabbar.getMiddleView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(RecycleActivity.this, "中间点击", Toast.LENGTH_SHORT).show();;
                }
            });
        }
    }

    @Override
    public void onDismiss(int position) {
        mTab1.clearCount();
    }

    @Override
    public void onTabSelect(int index) {
        Toast.makeText(RecycleActivity.this,"choose the tab index is "+index,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onInterruptSelect(int index) {
        return false;
    }
    public JPTabBar getTabbar() {
        return mTabbar;
    }
}
