package com.example.mydome.activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.blankj.utilcode.util.LogUtils;
import com.example.mydome.R;
import com.example.mydome.Util.DemoUtil;
import com.example.mydome.Util.Downloader;
import com.example.mydome.databinding.ActivityDownBinding;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.SpeedCalculator;
import com.liulishuo.okdownload.core.Util;
import com.liulishuo.okdownload.core.breakpoint.BlockInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.listener.DownloadListener1;
import com.liulishuo.okdownload.core.listener.DownloadListener4WithSpeed;
import com.liulishuo.okdownload.core.listener.assist.Listener1Assist;
import com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend;

import java.io.File;
import java.util.List;
import java.util.Map;

public class DownActivity extends BaseActivity<ActivityDownBinding> {
    private DownloadTask task;

    private NotificationCompat.Builder builder;//创建通知对象
    private NotificationManager manager;

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        viewBinding.start.setOnClickListener(new MyOnclick());
        viewBinding.cancel.setOnClickListener(new MyOnclick());
    }

    private class MyOnclick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.start:
                    manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);//获取NotificationManager实例
                    if (Build.VERSION.SDK_INT >= 26) {//判断Android的版本
                        NotificationChannel channel = new NotificationChannel(String.valueOf(1), "name",
                                NotificationManager.IMPORTANCE_LOW);  //当Android版本大于等于8时，创建通知渠道（Notification Channels）
                        manager.createNotificationChannel(channel);
                        builder = new NotificationCompat.Builder(DownActivity.this, String.valueOf(1));//获取
                    } else {
                        builder = new NotificationCompat.Builder(DownActivity.this);//当版本低于8时使用
                    }
                    Intent it = new Intent(mContext, OtherActivity.class);
                    PendingIntent pit = PendingIntent.getActivity(mContext, 0, it, 0);
                    builder.setContentTitle("正在下载...") //设置通知标题
                            .setSmallIcon(R.mipmap.ic_launcher_round)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round)) //设置通知的大图标
                            .setDefaults(Notification.DEFAULT_LIGHTS) //设置通知的提醒方式： 呼吸灯
                            .setPriority(NotificationCompat.PRIORITY_MAX) //设置通知的优先级：最大
                            .setAutoCancel(false)//设置通知被点击一次是否自动取消
                            .setContentText("下载进度:" + "0%")
                            .setProgress(100, 0, false);
                    Notification notification = builder.build();
                    notification.flags |= Notification.FLAG_ONGOING_EVENT;
                    manager.notify(2, notification);

                    DownLoad();


                    break;
                case R.id.cancel:
                    task.cancel();
                    break;
            }
        }
    }

    private void DownLoad() {
        String filename = "single-test.apk";
        String url = "https://tenfei05.cfp.cn/creative/vcg/veer/1600water/veer-151439349.jpg";
        File parentFile = DemoUtil.getParentFile(this);

        task = new DownloadTask.Builder(url, parentFile)
                .setFilename(filename)
                .setMinIntervalMillisCallbackProcess(2000) // 下载进度回调的间隔时间（毫秒）
                .setPassIfAlreadyCompleted(false)// 任务过去已完成是否要重新下载
                .setPriority(10)
                .build();

        task.enqueue(new DownloadListener4WithSpeed() {
            @Override
            public void taskStart(@NonNull DownloadTask task) {

            }

            @Override
            public void connectStart(@NonNull DownloadTask task, int blockIndex, @NonNull Map<String, List<String>> requestHeaderFields) {

            }

            @Override
            public void connectEnd(@NonNull DownloadTask task, int blockIndex, int responseCode, @NonNull Map<String, List<String>> responseHeaderFields) {

            }

            @Override
            public void infoReady(@NonNull DownloadTask task, @NonNull BreakpointInfo info, boolean fromBreakpoint, @NonNull Listener4SpeedAssistExtend.Listener4SpeedModel model) {

            }

            @Override
            public void progressBlock(@NonNull DownloadTask task, int blockIndex, long currentBlockOffset, @NonNull SpeedCalculator blockSpeed) {

            }

            @Override
            public void progress(@NonNull DownloadTask task, long currentOffset, @NonNull SpeedCalculator taskSpeed) {
                String speed = taskSpeed.speed();
                int percent = (int) (((float) task.getInfo().getTotalOffset() / task.getInfo().getTotalLength()) * 100);
                String totalSize = Util.humanReadableBytes(task.getInfo().getTotalLength(), true).toString();
                String size = totalSize + "(" + (int) percent + "%)";


                viewBinding.speed.setText("下载网速:" + speed);
                viewBinding.progressBar.setProgress(percent);
                viewBinding.progress.setText("下载进度" + percent + "%");

                //更新下载进度
                builder.setContentText("下载进度:" + percent + "%")
                        .setProgress(100, percent, false);
                Notification build = builder.build();
                build.flags |= Notification.FLAG_ONGOING_EVENT;

                manager.notify(2, build);

            }

            @Override
            public void blockEnd(@NonNull DownloadTask task, int blockIndex, BlockInfo info, @NonNull SpeedCalculator blockSpeed) {

            }

            @Override
            public void taskEnd(@NonNull DownloadTask task, @NonNull EndCause cause, @Nullable Exception realCause, @NonNull SpeedCalculator taskSpeed) {
                int percent = (int) (((float) task.getInfo().getTotalOffset() / task.getInfo().getTotalLength()) * 100);
                String totalSize = Util.humanReadableBytes(task.getInfo().getTotalLength(), true).toString();
                String size = totalSize + "(" + (int) percent + "%)";
                viewBinding.progressBar.setProgress(percent);
                viewBinding.progress.setText("下载进度" + percent + "%");

                /*  EndCause.CANCELED;//取消 EndCause.COMPLETED;//完成
                EndCause.ERROR; //错误EndCause.FILE_BUSY;//文件访问繁忙
                EndCause.PRE_ALLOCATE_FAILED;//预分配失败
                EndCause.SAME_TASK_BUSY;//任务繁忙
                */

                if (cause == EndCause.COMPLETED) {
                    //下载完成
                    viewBinding.progress.setText("下载完成");
                    //更新下载进度
                    builder.setContentText("下载完成" + percent + "%")
                            .setProgress(100, percent, false);
                    Notification build = builder.build();
                    build.flags |= Notification.FLAG_ONGOING_EVENT;
                    manager.notify(2, build);

                } else if (cause == EndCause.CANCELED) {
                    //下载取消
                    viewBinding.progress.setText("下载取消");
                } else if (cause == EndCause.FILE_BUSY) {
                    //文件访问繁忙
                    viewBinding.progress.setText("文件访问繁忙");
                } else if (cause == EndCause.PRE_ALLOCATE_FAILED) {
                    //预分配失败
                    viewBinding.progress.setText("预分配失败");
                } else {
                    //下载错误
                    viewBinding.progress.setText("下载错误");
                    builder.setContentText("下载错误" + percent + "%")
                            .setProgress(100, percent, false);
                    Notification build = builder.build();
                    build.flags |= Notification.FLAG_ONGOING_EVENT;
                    manager.notify(2, build);
                }


            }
        });
    }
}