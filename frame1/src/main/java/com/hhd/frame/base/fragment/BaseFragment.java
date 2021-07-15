package com.hhd.frame.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.hhd.frame.base.BaseView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * Fragment基类
 */
public abstract class BaseFragment<T extends ViewBinding> extends Fragment implements BaseView {
    protected Activity mActivity;
    //protected LoadingDialog progressDialog;
    protected T viewBinding;

    public BaseFragment() {
        super();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Class cls = (Class) type.getActualTypeArguments()[0];
        try {
            Method inflate = cls.getDeclaredMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
            viewBinding = (T) inflate.invoke(null, inflater, container, false);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        initCommon();
        //        if (isRegisterBus())
//            BusUtils.register(this);
        return viewBinding.getRoot();
    }


    protected void initCommon() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init(savedInstanceState);
    }

    protected abstract void init(Bundle savedInstanceState);

    protected String getResString(int res) {
        return getResources().getString(res);
    }

    protected int getResInt(int res) {
        return getResources().getInteger(res);
    }

    /**
     * 是否需要注册BusUtils
     */
    protected boolean isRegisterBus() {
        return false;
    }

    /* *//**
     * 显示加载对话框
     *//*
    @Override
    public void showLoadingDialog(String msg, boolean isCancel) {
        String message = TextUtils.isEmpty(msg) ? getResString(R.string.frame_load_ing) : msg;
        if (progressDialog == null)
            progressDialog = new LoadingDialog(mActivity);
        progressDialog.setCancel(isCancel);
        progressDialog.setMsg(message);
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    */

    /**
     * 隐藏加载对话框
     *//*
    public void dismissLoadingDialog() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }*/
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewBinding = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //页面销毁时隐藏dialog，否则重新打开页面时可能会报java.lang.IllegalArgumentException: View not attached to window manager
        dismissLoadingDialog();
//        if (isRegisterBus())
//            BusUtils.unregister(this);
    }
}