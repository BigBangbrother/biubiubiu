package com.example.wzw.biubiubiu.HttpUtil;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wzw on 2018/3/21.
 */

public class ObjectLoader {
    protected  <T> Observable<T> observe(Observable<T> observable){
        return observable.subscribeOn(Schedulers.io())
                         .unsubscribeOn(Schedulers.io())
                         .observeOn(AndroidSchedulers.mainThread());
    }
}
