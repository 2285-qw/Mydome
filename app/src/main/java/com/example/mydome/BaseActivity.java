package com.example.mydome;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/*项目名：   Mydome
 *包名：     com.example.mydome
 *文件名：   BaseActivity
 *创建者：   CEH
 *创建时间： 2021/4/15 21:30
 *描述：     Activity基类
 */
public class BaseActivity<T extends ViewBinding> extends AppCompatActivity {
    protected T viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Class cls = (Class) type.getActualTypeArguments()[0];
        try {
            Method inflate = cls.getDeclaredMethod("inflate", LayoutInflater.class);
            viewBinding = (T) inflate.invoke(null, getLayoutInflater());
            setContentView(viewBinding.getRoot());
        } catch (NoSuchMethodException | IllegalAccessException| InvocationTargetException e) {
            e.printStackTrace();
        }

    }

}
