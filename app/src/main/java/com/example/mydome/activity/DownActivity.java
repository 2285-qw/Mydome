package com.example.mydome.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mydome.R;
import com.example.mydome.Util.DemoUtil;
import com.example.mydome.Util.Downloader;
import com.example.mydome.databinding.ActivityDownBinding;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.listener.DownloadListener1;
import com.liulishuo.okdownload.core.listener.assist.Listener1Assist;

import java.io.File;

public class DownActivity extends BaseActivity<ActivityDownBinding> {
    private DownloadTask task;

    @Override
    protected void init(Bundle savedInstanceState) {

        initView();

    }

    private void initView() {
        viewBinding.start.setOnClickListener(new MyOnclick());
    }

    private class MyOnclick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.start:

                    break;
                case R.id.cancel:

                    break;
            }
        }
    }

    private void DownLoad() {
        String filename = "single-test";
        String url = "https://cdn.llscdn.com/yy/files/tkzpx40x-lls-LLS-5.7-785-20171108-111118.apk";
        File parentFile = DemoUtil.getParentFile(this);

        task = new DownloadTask.Builder(url, parentFile)
                .setFilename(filename)
                .setMinIntervalMillisCallbackProcess(30) // 下载进度回调的间隔时间（毫秒）
                .setPassIfAlreadyCompleted(false)// 任务过去已完成是否要重新下载
                .setPriority(10)
                .build();

        task.enqueue(new DownloadListener1() {
            @Override//开始
            public void taskStart(@NonNull DownloadTask task, @NonNull Listener1Assist.Listener1Model model) {

            }

            @Override//重试
            public void retry(@NonNull DownloadTask task, @NonNull ResumeFailedCause cause) {

            }

            @Override//链接
            public void connected(@NonNull DownloadTask task, int blockCount, long currentOffset, long totalLength) {

            }

            @Override//下载
            public void progress(@NonNull DownloadTask task, long currentOffset, long totalLength) {

            }

            @Override//结束
            public void taskEnd(@NonNull DownloadTask task, @NonNull EndCause cause, @Nullable Exception realCause, @NonNull Listener1Assist.Listener1Model model) {

            }
        });
    }
}