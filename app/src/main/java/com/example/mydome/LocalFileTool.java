package com.example.mydome;

/**
 * Time:         2021/3/29
 * Author:       C
 * Description:  LocalFileTool
 * on:
 */
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class LocalFileTool {
    public static final String[] picType = new String[]{"image/bmp", "image/jpeg", "image/png"};
    public static final String[] aviType = new String[]{"video/3gpp", "video/x-ms-asf", "video/x-msvideo",
            "video/vnd.mpegurl", "video/x-m4v", "video/quicktime", "video/mp4", "video/mpeg",
    };
    public static final String[] volumType = new String[]{"audio/x-mpegurl", "audio/mp4a-latm", "audio/x-mpeg", "audio/mpeg", "audio/ogg", "audio/x-wav", "audio/x-ms-wma"};
    public static final String[] docType = new String[]{"application/msword", "application/pdf", "application/vnd.ms-powerpoint", "text/plain", "application/vnd.ms-works"};
    public static final String[] zipType = new String[]{"application/x-gtar", "application/x-gzip", "application/x-compress", "application/zip"};
    // final static Object lock=new Object();

    public static final String[] worldType = new String[]{"application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"};
    public static final String[] excelType = new String[]{"application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"};
    public static final String[] pdfType = new String[]{"application/pdf"};
    public static final String[] pptType = new String[]{"application/vnd.ms-powerpoint", "application/vnd.openxmlformats-officedocument.presentationml.presentation"};

    public static void readFile(final String[] mimeType, Context context, final IReadCallBack iReadCallBack) {
        Observable.just(context).map(new Function<Context, Object>() {
            @Override
            public Object apply(Context context1) throws Exception {
                List<String> paths = new ArrayList<>();
                Uri[] fileUri = null;
                fileUri = new Uri[]{MediaStore.Files.getContentUri("external")};
                String[] colums = new String[]{MediaStore.Files.FileColumns.DATA};
                String[] extension = mimeType;
                //构造筛选语句
                String selection = "";
                for (int i = 0; i < extension.length; i++) {
                    if (i != 0) {
                        selection = selection + " OR ";
                    }
                    selection = selection + MediaStore.Files.FileColumns.MIME_TYPE + " LIKE '%" + extension[i] + "'";
                }
                //获取内容解析器对象
                ContentResolver resolver = context1.getContentResolver();
                //获取游标
                for (int i = 0; i < fileUri.length; i++) {
                    Cursor cursor = resolver.query(fileUri[i], colums, selection, null, null);
                    if (cursor == null) {
                        return null;
                    }//游标从最后开始往前递减，以此实现时间递减顺序（最近访问的文件，优先显示）
                    long beginTime = System.currentTimeMillis();
                    if (cursor.moveToLast()) {
                        do {
                            //输出文件的完整路径
                            String data = cursor.getString(0);
                            Log.v("asdasdadsa", cursor.getString(0) + "   " + cursor.getColumnName(0));
                            paths.add(data);
                        } while (cursor.moveToPrevious());
                    }
                    cursor.close();
                    android.util.Log.e("endTime", System.currentTimeMillis() - beginTime + "");
                }
                return paths;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object strings) throws Exception {
                iReadCallBack.callBack(strings);
            }
        });
    }

    public interface IReadCallBack {
        void callBack(Object localPath);
    }


}
