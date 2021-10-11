package com.example.mydome.activity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.blankj.utilcode.util.LogUtils;
import com.example.mydome.R;
import com.example.mydome.Util.Downloader;


import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {
    private Context mContext;
    private NotificationManager mNManager;
    private Notification notify1;
    Bitmap LargeBitmap = null;
    private static final int NOTIFYID_1 = 1;

    NotificationCompat.Builder builder;//创建通知对象

    private Button btn_show_normal;
    private Button btn_close_normal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        mContext = NotificationActivity.this;
        //创建大图标的Bitmap
        LargeBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.iv_lc_icon);
        mNManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        bindView();


    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_normal:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Downloader downloader = new Downloader();
                            downloader.download();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);//获取NotificationManager实例

                if (Build.VERSION.SDK_INT >= 26) {//判断Android的版本
                    NotificationChannel channel = new NotificationChannel(String.valueOf(1), "name",
                            NotificationManager.IMPORTANCE_HIGH);  //当Android版本大于等于8时，创建通知渠道（Notification Channels）
                    manager.createNotificationChannel(channel);
                    builder = new NotificationCompat.Builder(NotificationActivity.this, String.valueOf(1));//获取
                } else {
                    builder = new NotificationCompat.Builder(NotificationActivity.this);//当版本低于8时使用
                }
                Intent it = new Intent(mContext, OtherActivity.class);
                PendingIntent pit = PendingIntent.getActivity(mContext, 0, it, 0);
                builder.setContentTitle("正在更新...") //设置通知标题
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round)) //设置通知的大图标
                        .setDefaults(Notification.DEFAULT_LIGHTS) //设置通知的提醒方式： 呼吸灯
                        .setPriority(NotificationCompat.PRIORITY_MAX) //设置通知的优先级：最大
                        .setAutoCancel(false)//设置通知被点击一次是否自动取消
                        .setContentText("下载进度:" + "0%")
                        .setProgress(100, 0, false);
                Notification notification = builder.build();
                notification.flags |= Notification.FLAG_ONGOING_EVENT;
                manager.notify(1, notification);


                //更新下载进度
                builder.setContentText("下载进度:" + "20%")
                        .setProgress(100, 20, false);
                Notification build = builder.build();
                build.flags |= Notification.FLAG_ONGOING_EVENT;
                manager.notify(1, build);
                break;

            case R.id.btn_close_normal:
                //除了可以根据ID来取消Notification外,还可以调用cancelAll();关闭该应用产生的所有通知
                mNManager.cancel(NOTIFYID_1);                           //取消Notification
                break;

        }
    }

    private void bindView() {
        btn_show_normal = (Button) findViewById(R.id.btn_show_normal);
        btn_close_normal = (Button) findViewById(R.id.btn_close_normal);
        btn_show_normal.setOnClickListener(this);
        btn_close_normal.setOnClickListener(this);
    }




}