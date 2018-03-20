package com.example.wzw.biubiubiu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity
        extends Activity
{

    @Bind(R.id.list_view)
    ListView mListView;
    private Object mData;
    private List<Bean> mDatas;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        mDatas = new ArrayList<Bean>();

        Bean bean1 = new Bean(R.mipmap.default_splash,"基本操作");
        mDatas.add(bean1);
        Bean bean2 = new Bean(R.mipmap.default_splash,"基本操作");
        mDatas.add(bean2);
        Bean bean3 = new Bean(R.mipmap.default_splash,"基本操作");
        mDatas.add(bean3);
        Bean bean4 = new Bean(R.mipmap.default_splash,"基本操作");
        mDatas.add(bean4);
        mAdapter = new MyAdapter(this, mDatas);

        mListView.setAdapter(mAdapter);

    }

}