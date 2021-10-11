package com.example.mydome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mydome.databinding.ActivityMain3Binding;
import com.frame.base.activity.BaseActivity;

public class MainActivity3 extends BaseActivity<ActivityMain3Binding> {


    @Override
    protected void init(Bundle savedInstanceState) {

        viewBinding.listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 22;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view;
                if (convertView == null) {
                    view = View.inflate(MainActivity3.this, R.layout.list_item, null);
                } else {
                    view = convertView;
                }
                TextView textView = view.findViewById(R.id.text);
                textView.setText("第" + position + "个");

                return view;
            }
        });

    }
}