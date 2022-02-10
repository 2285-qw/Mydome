package com.example.mydome.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import com.example.mydome.R;
import com.example.mydome.databinding.ActivityTweenAnimationBinding;
import com.frame.util.CustomClickListener;

/**
 * 2022-02-09
 * 补间动画
 */
public class TweenAnimationActivity extends BaseActivity<ActivityTweenAnimationBinding> {

    private Animation animation;

    @Override
    protected void init(Bundle savedInstanceState) {
        initAnimationView();
        initView();

    }

    //设置view从下方加载动画
    private void initAnimationView() {
        //设置动画，从自身位置的最下端向上滑动了自身的高度，持续时间为500ms
        final TranslateAnimation ctrlAnimation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 0,
                TranslateAnimation.RELATIVE_TO_SELF, 1, TranslateAnimation.RELATIVE_TO_SELF, 0);
        ctrlAnimation.setDuration(500l);     //设置动画的过渡时间
        viewBinding.mainLine.postDelayed(new Runnable() {
            @Override
            public void run() {
                viewBinding.mainLine.setVisibility(View.VISIBLE);
                viewBinding.mainLine.startAnimation(ctrlAnimation);
            }
        }, 0);

    }

    private void initView() {
        viewBinding.btnAlpha.setOnClickListener(new MyOnClick());
        viewBinding.btnRotate.setOnClickListener(new MyOnClick());
        viewBinding.btnScale.setOnClickListener(new MyOnClick());
        viewBinding.btnSet.setOnClickListener(new MyOnClick());
        viewBinding.btnTran.setOnClickListener(new MyOnClick());
    }

    private class MyOnClick extends CustomClickListener {
        @Override
        public void onSingleClick(View v) {
            switch (v.getId()) {
                case R.id.btn_alpha:
                    animation = AnimationUtils.loadAnimation(mContext,
                            R.anim.anim_alpha);
                    viewBinding.imgShow.startAnimation(animation);
                    break;
                case R.id.btn_scale:
                    animation = AnimationUtils.loadAnimation(mContext,
                            R.anim.anim_scale);
                    viewBinding.imgShow.startAnimation(animation);
                    break;
                case R.id.btn_tran:
                    animation = AnimationUtils.loadAnimation(mContext,
                            R.anim.anim_translate);
                    animation.setRepeatCount(3);
                    viewBinding.imgShow.startAnimation(animation);
                    break;
                case R.id.btn_rotate:
                    animation = AnimationUtils.loadAnimation(mContext,
                            R.anim.anim_rotate);
                    viewBinding.imgShow.startAnimation(animation);
                    break;
                case R.id.btn_set:
                    animation = AnimationUtils.loadAnimation(mContext,
                            R.anim.anim_set);
                    viewBinding.imgShow.startAnimation(animation);
                    break;
            }
        }
    }
}