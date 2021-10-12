package com.example.mydome.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
            }
        }
    }
}