package com.example.mydome.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mydome.mvvmdome.activity.CeshiActivity;
import com.example.mydome.mvvmdome.activity.QueryWeatherActivity;
import com.example.mydome.R;
import com.example.mydome.databinding.ActivityHomeBinding;

public class HomeActivity extends BaseActivity<ActivityHomeBinding> {

    @Override
    protected void init(Bundle savedInstanceState) {

        initView();

    }

    private void initView() {
        viewBinding.button1.setOnClickListener(new MyOnClick());
        viewBinding.button2.setOnClickListener(new MyOnClick());
        viewBinding.button3.setOnClickListener(new MyOnClick());
        viewBinding.button4.setOnClickListener(new MyOnClick());
        viewBinding.button5.setOnClickListener(new MyOnClick());
        viewBinding.button6.setOnClickListener(new MyOnClick());
        viewBinding.button7.setOnClickListener(new MyOnClick());
        viewBinding.button8.setOnClickListener(new MyOnClick());

    }

    private class MyOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button1:
                    startActivity(new Intent(HomeActivity.this, NotificationActivity.class));
                    break;
                case R.id.button2:
                    startActivity(new Intent(mContext, DownActivity.class));
                    break;
                case R.id.button3:
                    startActivity(new Intent(mContext, QueryWeatherActivity.class));
                    break;
                case R.id.button4:
                    startActivity(new Intent(mContext, CeshiActivity.class));
                    break;
                case R.id.button5:
                    startActivity(new Intent(mContext, ViewActivity.class));
                    break;
                case R.id.button6:
                    startActivity(new Intent(mContext, FrameAnimationActivity.class));
                    break;
                case R.id.button7:
                    startActivity(new Intent(mContext, TweenAnimationActivity.class));
                    break;
                case R.id.button8:
                    startActivity(new Intent(mContext, PropertyAnimationActivity.class));
                    break;


            }
        }
    }
}