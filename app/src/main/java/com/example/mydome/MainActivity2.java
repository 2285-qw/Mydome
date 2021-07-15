package com.example.mydome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.example.mydome.databinding.ActivityMain2Binding;
import com.example.mydome.presenter.AddCareForPt;
import com.frame.base.BaseRequestView;
import com.frame.base.activity.BaseActivity;
import com.frame.base.activity.BaseRequestActivity;
import com.frame.bean.BaseBean;
import com.frame.util.CustomClickListener;
import com.frame.util.ToastUtil;

public class MainActivity2 extends BaseRequestActivity<ActivityMain2Binding, AddCareForPt, BaseBean> {

    @Override
    protected void init(Bundle savedInstanceState) {

        LogUtils.d(mPresenter + "");
        viewBinding.button.setOnClickListener(new CustomClickListener() {
            @Override
            public void onSingleClick(View v) {
                mPresenter.addConcernedPeople("11");
                showLoadingView();
                showEmptyView();
            }
        });

    }

    @Override
    protected AddCareForPt setPresenter() {
        return new AddCareForPt(this);
    }

    @Override
    protected void reRequest() {

    }

    @Override
    public void requestSuccess(BaseBean data, Object tag, int pageIndex, int pageCount) {
        switch (tag.toString()) {
            case "addConcernedPeople":
                ToastUtil.showShortToast("添加成功");
                viewBinding.text.setText(data.msg+"=="+data.error + "66"+data.serverTime+data.code);
                break;
        }

    }


}