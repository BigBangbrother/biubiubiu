package com.example.wzw.biubiubiu.recycle;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.wzw.biubiubiu.R;

/**
 * Created by wzw on 2018/3/19.
 */

public class RecycleActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_activity);
        requestJson();
    }

    private void requestJson() {
        String url = "http://gank.io/api/data/Android/10/1";

    }
}
