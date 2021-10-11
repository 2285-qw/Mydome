package com.example.mydome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.example.mydome.databinding.ActivityMainBinding;
import com.frame.base.activity.BaseActivity;
import com.frame.util.CustomClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private static int i = 3;
    List list;

    protected void init(Bundle savedInstanceState) {

        Log.d("list", "=====");
        //list=getList();
        Log.d("list", list + "=====");

        //简单的回调
        myMethod(new main() {
            @Override
            public String call() {
                return null;
            }

            public void eat() {
                System.out.println("qqqqqqq");
            }
        });
        viewBinding.button.setOnClickListener(new CustomClickListener() {
            @Override
            public void onSingleClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));

            }
        });

        viewBinding.button1.setOnClickListener(new CustomClickListener() {
            @Override
            public void onSingleClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity3.class));
            }
        });

        viewBinding.button2.setOnClickListener(new CustomClickListener() {
            @Override
            public void onSingleClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity4.class));
            }
        });
    }

    public static void myMethod(GenericMethod g) {
        if (i == 2) {
            System.out.println("This method called " + g.call());
        } else {
            g.eat();
        }

    }

    /*接口*/
    public interface GenericMethod {
        public String call();

        public void eat();
    }

    //实现接口
    public class main implements GenericMethod {

        @Override
        public String call() {
            return "3";
        }

        @Override
        public void eat() {
            System.out.println("yyyyyy");
        }
    }


    //获取手机里的视频文件
    public List<MMM> getList() {
        List<MMM> list = null;
        if (this != null) {
            Cursor cursor = this.getContentResolver().query(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null,
                    null, null);
            if (cursor != null) {
                list = new ArrayList<MMM>();
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(cursor
                            .getColumnIndexOrThrow(MediaStore.Video.Media._ID));
                    String title = cursor
                            .getString(cursor
                                    .getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));
                    String album = cursor
                            .getString(cursor
                                    .getColumnIndexOrThrow(MediaStore.Video.Media.ALBUM));
                    String artist = cursor
                            .getString(cursor
                                    .getColumnIndexOrThrow(MediaStore.Video.Media.ARTIST));
                    String displayName = cursor
                            .getString(cursor
                                    .getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
                    String mimeType = cursor
                            .getString(cursor
                                    .getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE));
                    String path = cursor
                            .getString(cursor
                                    .getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                    long duration = cursor
                            .getInt(cursor
                                    .getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
                    long size = cursor
                            .getLong(cursor
                                    .getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
                    MMM video = new MMM();
                    video.setName1(title);
                    video.setSize1(size);
                    video.setUrl1(path);
                    video.setDuration1(duration);
                    list.add(video);
                }
                cursor.close();
            }
        }
        return list;
    }
}