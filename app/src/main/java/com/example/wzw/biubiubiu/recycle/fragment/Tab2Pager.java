package com.example.wzw.biubiubiu.recycle.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wzw.biubiubiu.R;

/**
 * Created by wzw on 2018/3/20.
 */

public class Tab2Pager
        extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View layout = inflater.inflate(R.layout.tab2,null);
        return layout;
    }
}
