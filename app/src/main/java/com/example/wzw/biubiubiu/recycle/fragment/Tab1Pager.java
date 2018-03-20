package com.example.wzw.biubiubiu.recycle.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.wzw.biubiubiu.R;
import com.example.wzw.biubiubiu.recycle.RecycleActivity;
import com.jpeng.jptabbar.JPTabBar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wzw on 2018/3/20.
 */

public class Tab1Pager
        extends Fragment
        implements View.OnClickListener, TextWatcher
{

    @Bind(R.id.textView)
    TextView    mTextView;
    @Bind(R.id.imageButton1)
    ImageButton mImageButton1;
    @Bind(R.id.imageButton2)
    ImageButton mImageButton2;
    @Bind(R.id.et_count)
    EditText    mEtCount;
    @Bind(R.id.button1)
    Button      mButton1;
    @Bind(R.id.button2)
    Button      mButton2;
    @Bind(R.id.button3)
    Button      mButton3;
    private JPTabBar mTabBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View layout = inflater.inflate(R.layout.tab1, null);

        ButterKnife.bind(this, layout);
        init(layout);
        return layout;
    }

    private void init(View layout) {
        mTabBar = ((RecycleActivity) getActivity()).getTabbar();
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mEtCount.addTextChangedListener(this);
        mImageButton1.setOnClickListener(this);
        mImageButton2.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        int count = Integer.parseInt(mEtCount.getText().toString());
        if (v == mImageButton1) {
            count--;
            mEtCount.setText(count + "");
        } else if(v==mImageButton2) {
            count++;
            mEtCount.setText(count + "");
        }
        else if(v==mButton1){
            mTabBar.showBadge(0,"文字",true);
        }
        else if(v==mButton2){
            mTabBar.showCircleBadge(0,true);
        }
        else{
            mTabBar.hideBadge(0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(s!=null&&s.toString().equals("0")){
            mTabBar.showBadge(0, ""+0,true);
            mTabBar.hideBadge(0);
            return;
        }
        if (s.toString().equals("")) {
            mTabBar.showBadge(0, ""+0,true);
            return;
        }
        int count = Integer.parseInt(s.toString());
        if(mTabBar!=null)
            mTabBar.showBadge(0, count+"",true);
    }


    public void clearCount() {
        //当徽章拖拽爆炸后,一旦View被销毁,不判断就会空指针异常
        if(mEtCount!=null)
            mEtCount.setText("0");
    }
}
