package com.example.mydome.mvvmdome.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mydome.R;
import com.example.mydome.databinding.ActivityQueryWeatherBinding;
import com.example.mydome.mvvmdome.viewmodel.QueryWeatherViewModel;

public class QueryWeatherActivity extends BaseDataActivity<ActivityQueryWeatherBinding> {

    // ViewModel
    private QueryWeatherViewModel mViewModel;

    @Override
    protected void init(Bundle savedInstanceState) {
        mViewModel = new QueryWeatherViewModel();
        viewBinding.setViewModel(mViewModel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消请求
        mViewModel.cancelRequest();
    }
}



/*
*
* public class QueryWeatherActivity extends AppCompatActivity {

    // ViewModel
    private QueryWeatherViewModel mViewModel;

    // DataBinding
    private ActivityQueryWeatherBinding mDataBinding;

      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_weather);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_query_weather);
        // 创建ViewModel
        mViewModel = new QueryWeatherViewModel();
        // 绑定View和ViewModel
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消请求
        mViewModel.cancelRequest();
    }
}*/