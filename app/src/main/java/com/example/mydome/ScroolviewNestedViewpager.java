package com.example.mydome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ScroolviewNestedViewpager extends AppCompatActivity {

    ViewPager viewPager;
    List views;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroolview_nested_viewpager);

        views=new ArrayList();
        views.add(getLayoutInflater().inflate(R.layout.ce1,null));
        views.add(getLayoutInflater().inflate(R.layout.ce2,null));

        viewPager=findViewById(R.id.viewpager);
        viewPager.setAdapter(new pagerAdapter());

    }

    private class pagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            container.addView((View) views.get(position));
            return views.get(position);


        }
    }
}