package com.example.mydome.activity;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.example.mydome.R;
import com.example.mydome.databinding.ActivityPropertyAnimationBinding;
import com.frame.util.CustomClickListener;

public class PropertyAnimationActivity extends BaseActivity<ActivityPropertyAnimationBinding> {
    private int width;
    private int height;

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        viewBinding.btnOne.setOnClickListener(new MyOnClick());
        viewBinding.btnTwo.setOnClickListener(new MyOnClick());
        viewBinding.btnThree.setOnClickListener(new MyOnClick());
        viewBinding.btnFour.setOnClickListener(new MyOnClick());
        viewBinding.imgBabi.setOnClickListener(new MyOnClick());
    }

    private class MyOnClick extends CustomClickListener {
        @Override
        public void onSingleClick(View v) {
            switch (v.getId()) {
                case R.id.btn_one:
                    lineAnimator();
                    break;
                case R.id.btn_two:
                    scaleAnimator();
                    break;
                case R.id.btn_three:
                    raAnimator();
                    break;
                case R.id.btn_four:
                    circleAnimator();
                    break;
                case R.id.img_babi:
                    Toast.makeText(mContext, "不愧是coder-pig~", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }


    //定义一个修改ImageView位置的方法
    private void moveView(View view, int rawX, int rawY) {
        int left = rawX - viewBinding.imgBabi.getWidth() / 2;
        int top = rawY - viewBinding.imgBabi.getHeight();
        int width = left + view.getWidth();
        int height = top + view.getHeight();
        view.layout(left, top, width, height);
    }


    //定义属性动画的方法：

    //按轨迹方程来运动
    private void lineAnimator() {
        width = viewBinding.lyRoot.getWidth();
        height = viewBinding.lyRoot.getHeight();
        ValueAnimator xValue = ValueAnimator.ofInt(height, 0, height / 4, height / 2, height / 4 * 3, height);
        xValue.setDuration(3000L);
        xValue.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 轨迹方程 x = width / 2
                int y = (Integer) animation.getAnimatedValue();
                int x = width / 2;
                moveView(viewBinding.imgBabi, x, y);
            }
        });
        xValue.setInterpolator(new LinearInterpolator());
        xValue.start();
    }

    //缩放效果
    private void scaleAnimator() {

        //这里故意用两个是想让大家体会下组合动画怎么用而已~
        final float scale = 0.5f;
        AnimatorSet scaleSet = new AnimatorSet();
        ValueAnimator valueAnimatorSmall = ValueAnimator.ofFloat(1.0f, scale);
        valueAnimatorSmall.setDuration(500);

        ValueAnimator valueAnimatorLarge = ValueAnimator.ofFloat(scale, 1.0f);
        valueAnimatorLarge.setDuration(500);

        valueAnimatorSmall.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float scale = (Float) animation.getAnimatedValue();
                viewBinding.imgBabi.setScaleX(scale);
                viewBinding.imgBabi.setScaleY(scale);
            }
        });
        valueAnimatorLarge.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float scale = (Float) animation.getAnimatedValue();
                viewBinding.imgBabi.setScaleX(scale);
                viewBinding.imgBabi.setScaleY(scale);
            }
        });

        scaleSet.play(valueAnimatorLarge).after(valueAnimatorSmall);
        scaleSet.start();

        //其实可以一个就搞定的
//        ValueAnimator vValue = ValueAnimator.ofFloat(1.0f, 0.6f, 1.2f, 1.0f, 0.6f, 1.2f, 1.0f);
//        vValue.setDuration(1000L);
//        vValue.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float scale = (Float) animation.getAnimatedValue();
//                img_babi.setScaleX(scale);
//                img_babi.setScaleY(scale);
//            }
//        });
//        vValue.setInterpolator(new LinearInterpolator());
//        vValue.start();
    }


    //旋转的同时透明度变化
    private void raAnimator() {
        ValueAnimator rValue = ValueAnimator.ofInt(0, 360);
        rValue.setDuration(1000L);
        rValue.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int rotateValue = (Integer) animation.getAnimatedValue();
                viewBinding.imgBabi.setRotation(rotateValue);
                float fractionValue = animation.getAnimatedFraction();
                viewBinding.imgBabi.setAlpha(fractionValue);
            }
        });
        rValue.setInterpolator(new DecelerateInterpolator());
        rValue.start();
    }

    //圆形旋转
    protected void circleAnimator() {
        width = viewBinding.lyRoot.getWidth();
        height = viewBinding.lyRoot.getHeight();
        final int R = width / 4;
        ValueAnimator tValue = ValueAnimator.ofFloat(0,
                (float) (2.0f * Math.PI));
        tValue.setDuration(1000);
        tValue.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 圆的参数方程 x = R * sin(t) y = R * cos(t)
                float t = (Float) animation.getAnimatedValue();
                int x = (int) (R * Math.sin(t) + width / 2);
                int y = (int) (R * Math.cos(t) + height / 2);
                moveView(viewBinding.imgBabi, x, y);
            }
        });
        tValue.setInterpolator(new DecelerateInterpolator());
        tValue.start();
    }
}