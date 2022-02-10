package com.example.mydome.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;

import com.example.mydome.databinding.ActivityFrameAnimationBinding;
import com.frame.util.CustomClickListener;

/**
 * 2022-02-09
 * 帧动画页面
 */
public class FrameAnimationActivity extends BaseActivity<ActivityFrameAnimationBinding> {

    private AnimationDrawable animationDrawable;

    @Override
    protected void init(Bundle savedInstanceState) {

        initView();
    }

    private void initView() {
        animationDrawable = (AnimationDrawable) viewBinding.imgShow.getBackground();
        viewBinding.btnStart.setOnClickListener(new CustomClickListener() {
            @Override
            public void onSingleClick(View v) {
                animationDrawable.start();
            }
        });

        viewBinding.btnStop.setOnClickListener(new CustomClickListener() {
            @Override
            public void onSingleClick(View v) {
                animationDrawable.stop();
            }
        });

    }
}