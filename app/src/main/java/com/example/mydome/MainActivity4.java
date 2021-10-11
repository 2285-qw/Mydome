 package com.example.mydome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mydome.databinding.ActivityMain5Binding;
import com.frame.base.activity.BaseActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity4 extends BaseActivity<ActivityMain5Binding> {


    private Integer[] images = {R.mipmap.ceshi1,R.mipmap.ceshi1,R.mipmap.ceshi1,R.mipmap.ceshi1};
    private List<Integer> imageLists = new ArrayList<>();
    private int currentPosition;

    @Override
    protected void init(Bundle savedInstanceState) {


        initView();

    }

    private void initView() {
        imageLists.addAll(Arrays.asList(images));


        imageLists.add(0, imageLists.get(imageLists.size() - 1));
        imageLists.add(imageLists.get(1));

        //设置缓存的页面数量
        viewBinding.viewPager.setOffscreenPageLimit(5);
        viewBinding.viewPager.setPageMargin(200);
        viewBinding.viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imageLists.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(MainActivity4.this);
                imageView.setImageResource(imageLists.get(position));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                container.addView(imageView);
                return imageView;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });

        viewBinding.viewPager.setCurrentItem(1, false);
        viewBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    if (currentPosition == 0) {
                        viewBinding.viewPager.setCurrentItem(imageLists.size() - 2, false);
                    } else if (currentPosition == imageLists.size() - 1) {
                        viewBinding.viewPager.setCurrentItem(1, false);
                    }
                }
            }
        });


    }
}