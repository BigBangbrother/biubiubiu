package com.example.wzw.biubiubiu.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.example.wzw.biubiubiu.AppConstant;
import com.example.wzw.biubiubiu.MainActivity;
import com.example.wzw.biubiubiu.R;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by wzw on 2018/3/19.
 */

public class SplashActivity
        extends Activity
{
    @Bind(R.id.iv_splash)
    ImageView mIvSplash;

    private Subscription mSubscription;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initSplashImage();
    }

    private void initSplashImage() {
        mSubscription = Observable.timer(200, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        animateImage();
                    }
                });
    }
        //属性动画
    public void animateImage(){
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mIvSplash, "scaleX", 1f, AppConstant.SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mIvSplash, "scaleY", 1f, AppConstant.SCALE_END);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(AppConstant.ANIMATION_DURATION).play(animatorX).with(animatorY);
        set.start();

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                SplashActivity.this.finish();
                //作用是实现两个 Activity 切换时的动画

                //在Activity中使用 有两个参数：进入动画和出去的动画

                //要求就是：必须在 StartActivity() 或 finish() 之后立即调用
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mSubscription !=null && mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
    }
}
