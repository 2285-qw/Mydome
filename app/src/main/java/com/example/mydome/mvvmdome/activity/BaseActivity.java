package com.example.mydome.mvvmdome.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
/**
 * Efficiency
 * <p>
 * Created by JIULANG on 2020/10/5
 * <p>
 * Describe:基类Activity，可以拓展
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected ViewDataBinding dataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            //结束activity
            finish();
            return;
        }

        //这里是activity中使用 dataBinding 我这里就是简单的写了个抽象类节省下事情
        dataBinding = DataBindingUtil.setContentView(this, getLayout());
        init();
    }

    public abstract void init();
    public abstract int getLayout();
    protected  <T extends ViewDataBinding>  T getViewDataBinding() {
        return (T) dataBinding;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(dataBinding!=null){
            dataBinding.unbind();
        }
    }

}
